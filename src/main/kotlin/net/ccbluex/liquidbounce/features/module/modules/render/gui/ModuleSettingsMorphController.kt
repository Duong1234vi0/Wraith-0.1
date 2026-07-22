package net.ccbluex.liquidbounce.features.module.modules.render.gui

import net.ccbluex.liquidbounce.features.module.ClientModule
import kotlin.math.max
import kotlin.math.min

enum class ModuleSettingsMorphPhase {
    CLOSED,
    OPENING,
    OPENING_SETTLE,
    OPEN,
    SWITCH_FADE_OUT,
    SWITCH_OPENING,
    SWITCH_SETTLE,
    PREPARING_CLOSE,
    CLOSING,
    CLOSING_SETTLE,
}

data class ModuleSettingsMorphSnapshot(
    val phase: ModuleSettingsMorphPhase,
    val selectedModule: ClientModule?,
    val sourceCategory: Any?,
    val sourceIndex: Int,
    val sourceRect: SilentRect,
    val latestSourceRect: SilentRect,
    val transitionStartedAt: Long,
    val pendingModule: ClientModule?,
)

/** Pure state/timing holder. Rendering and module-list resolution stay in the screen. */
class ModuleSettingsMorphController(
    private val clock: () -> Long = { System.currentTimeMillis() },
) {
    var phase: ModuleSettingsMorphPhase = ModuleSettingsMorphPhase.CLOSED
        private set
    var selectedModule: ClientModule? = null
        private set
    var sourceCategory: Any? = null
        private set
    var sourceIndex: Int = -1
        private set
    var sourceRect: SilentRect = SilentRect.ZERO
        private set
    var latestSourceRect: SilentRect = SilentRect.ZERO
        private set
    var closingStartRect: SilentRect = SilentRect.ZERO
        private set
    var transitionStartedAt: Long = 0L
        private set
    var pendingModule: ClientModule? = null
        private set
    var pendingPage: SilentClickGuiPage? = null
        private set
    var pendingSearchText: String? = null
        private set

    private var pendingCategory: Any? = null
    private var pendingIndex: Int = -1
    private var pendingSourceRect: SilentRect = SilentRect.ZERO

    fun open(module: ClientModule, category: Any?, index: Int, card: SilentRect) {
        selectedModule = module
        sourceCategory = category
        sourceIndex = index
        sourceRect = finiteRect(card)
        latestSourceRect = sourceRect
        pendingModule = null
        pendingCategory = null
        pendingIndex = -1
        pendingSourceRect = SilentRect.ZERO
        pendingPage = null
        pendingSearchText = null
        transitionStartedAt = clock()
        phase = ModuleSettingsMorphPhase.OPENING
    }

    /** Keep the old module selected until its panel has faded out. */
    fun switch(module: ClientModule, category: Any?, index: Int, card: SilentRect) {
        if (phase != ModuleSettingsMorphPhase.OPEN || selectedModule === module) return
        pendingModule = module
        pendingCategory = category
        pendingIndex = index
        pendingSourceRect = finiteRect(card)
        transitionStartedAt = clock()
        phase = ModuleSettingsMorphPhase.SWITCH_FADE_OUT
    }

    fun requestClose(page: SilentClickGuiPage? = null, search: String? = null) {
        if (page != null) pendingPage = page
        if (search != null) pendingSearchText = search
        if (phase == ModuleSettingsMorphPhase.CLOSED || phase == ModuleSettingsMorphPhase.CLOSING ||
            phase == ModuleSettingsMorphPhase.CLOSING_SETTLE || phase == ModuleSettingsMorphPhase.PREPARING_CLOSE) {
            return
        }

        pendingModule = selectedModule
        closingStartRect = when (phase) {
            ModuleSettingsMorphPhase.OPENING -> SilentClickGuiMorph.openingRect(sourceRect, progress())
            ModuleSettingsMorphPhase.OPENING_SETTLE,
            ModuleSettingsMorphPhase.OPEN,
            ModuleSettingsMorphPhase.SWITCH_SETTLE,
            -> SilentClickGuiLayout.sidePanel
            else -> SilentClickGuiLayout.sidePanel
        }
        transitionStartedAt = clock()
        phase = ModuleSettingsMorphPhase.PREPARING_CLOSE
    }

    /** The screen resolves the current card after filtering/scrolling changes. */
    fun setCloseTarget(rect: SilentRect, needsReveal: Boolean) {
        if (phase != ModuleSettingsMorphPhase.PREPARING_CLOSE) return
        latestSourceRect = finiteRect(rect)
        if (needsReveal) return
    }

    fun setClosingStart(rect: SilentRect) {
        closingStartRect = finiteRect(rect)
    }

    fun updateLatestSourceRect(rect: SilentRect) {
        latestSourceRect = finiteRect(rect)
    }

    fun tick(now: Long = clock()) {
        val elapsed = (now - transitionStartedAt).coerceAtLeast(0L)
        when (phase) {
            ModuleSettingsMorphPhase.OPENING -> if (elapsed >= OPEN_TRAVEL_MS) {
                transitionStartedAt = now
                phase = ModuleSettingsMorphPhase.OPENING_SETTLE
            }

            ModuleSettingsMorphPhase.OPENING_SETTLE -> if (elapsed >= OPEN_SETTLE_MS) {
                phase = ModuleSettingsMorphPhase.OPEN
            }

            ModuleSettingsMorphPhase.PREPARING_CLOSE -> if (elapsed >= CLOSE_PREP_MS) {
                transitionStartedAt = now
                phase = ModuleSettingsMorphPhase.CLOSING
            }

            ModuleSettingsMorphPhase.CLOSING -> if (elapsed >= CLOSE_TRAVEL_MS) {
                transitionStartedAt = now
                phase = ModuleSettingsMorphPhase.CLOSING_SETTLE
            }

            ModuleSettingsMorphPhase.CLOSING_SETTLE -> Unit

            ModuleSettingsMorphPhase.SWITCH_FADE_OUT -> if (elapsed >= SWITCH_FADE_MS) {
                selectedModule = pendingModule
                sourceCategory = pendingCategory
                sourceIndex = pendingIndex
                sourceRect = pendingSourceRect
                latestSourceRect = sourceRect
                pendingModule = null
                transitionStartedAt = now
                phase = ModuleSettingsMorphPhase.SWITCH_OPENING
            }

            ModuleSettingsMorphPhase.SWITCH_OPENING -> if (elapsed >= SWITCH_TRAVEL_MS) {
                transitionStartedAt = now
                phase = ModuleSettingsMorphPhase.SWITCH_SETTLE
            }

            ModuleSettingsMorphPhase.SWITCH_SETTLE -> if (elapsed >= SWITCH_SETTLE_MS) {
                phase = ModuleSettingsMorphPhase.OPEN
            }

            else -> Unit
        }
    }

    fun completeClose() {
        clear()
    }

    fun clear() {
        phase = ModuleSettingsMorphPhase.CLOSED
        selectedModule = null
        sourceCategory = null
        sourceIndex = -1
        sourceRect = SilentRect.ZERO
        latestSourceRect = SilentRect.ZERO
        closingStartRect = SilentRect.ZERO
        transitionStartedAt = 0L
        pendingModule = null
        pendingCategory = null
        pendingIndex = -1
        pendingSourceRect = SilentRect.ZERO
        pendingPage = null
        pendingSearchText = null
    }

    fun consumePendingPage(): SilentClickGuiPage? = pendingPage.also { pendingPage = null }
    fun consumePendingSearch(): String? = pendingSearchText.also { pendingSearchText = null }

    fun snapshot() = ModuleSettingsMorphSnapshot(
        phase, selectedModule, sourceCategory, sourceIndex, sourceRect, latestSourceRect,
        transitionStartedAt, pendingModule
    )

    /** Progress for the active phase. Rectangle interpolation always uses 0 -> 1. */
    fun progress(now: Long = clock()): Float {
        val duration = when (phase) {
            ModuleSettingsMorphPhase.OPENING -> OPEN_TRAVEL_MS
            ModuleSettingsMorphPhase.OPENING_SETTLE -> OPEN_SETTLE_MS
            ModuleSettingsMorphPhase.PREPARING_CLOSE -> CLOSE_PREP_MS
            ModuleSettingsMorphPhase.CLOSING -> CLOSE_TRAVEL_MS
            ModuleSettingsMorphPhase.CLOSING_SETTLE -> CLOSE_SETTLE_MS
            ModuleSettingsMorphPhase.SWITCH_FADE_OUT -> SWITCH_FADE_MS
            ModuleSettingsMorphPhase.SWITCH_OPENING -> SWITCH_TRAVEL_MS
            ModuleSettingsMorphPhase.SWITCH_SETTLE -> SWITCH_SETTLE_MS
            else -> 1L
        }
        return ((now - transitionStartedAt).toFloat() / duration.toFloat()).coerceIn(0f, 1f)
    }

    private fun finiteRect(rect: SilentRect): SilentRect {
        val x = if (rect.x.isFinite()) rect.x else 0f
        val y = if (rect.y.isFinite()) rect.y else 0f
        val w = if (rect.w.isFinite()) max(0f, rect.w) else 0f
        val h = if (rect.h.isFinite()) max(0f, rect.h) else 0f
        return SilentRect(x, y, w, h)
    }

    companion object {
        const val OPEN_PREP_MS = 55L
        const val OPEN_TRAVEL_MS = 185L
        const val OPEN_SETTLE_MS = 80L
        const val CLOSE_PREP_MS = 65L
        const val CLOSE_TRAVEL_MS = 160L
        const val CLOSE_SETTLE_MS = 45L
        const val SWITCH_FADE_MS = 60L
        const val SWITCH_TRAVEL_MS = 160L
        const val SWITCH_SETTLE_MS = 60L
        const val OPEN_MS = OPEN_TRAVEL_MS
        const val CLOSE_MS = CLOSE_PREP_MS + CLOSE_TRAVEL_MS + CLOSE_SETTLE_MS
        const val SWITCH_MS = SWITCH_FADE_MS + SWITCH_TRAVEL_MS + SWITCH_SETTLE_MS
    }
}

object SilentClickGuiMorph {
    fun lerp(a: Float, b: Float, t: Float): Float = a + (b - a) * t.coerceIn(0f, 1f)

    fun rect(a: SilentRect, b: SilentRect, t: Float): SilentRect {
        val p = t.coerceIn(0f, 1f)
        return SilentRect(lerp(a.x, b.x, p), lerp(a.y, b.y, p), lerp(a.w, b.w, p), lerp(a.h, b.h, p))
    }

    fun easeInOut(t: Float): Float {
        val p = t.coerceIn(0f, 1f)
        return p * p * (3f - 2f * p)
    }

    fun quadraticPoint(start: SilentPoint, control: SilentPoint, end: SilentPoint, t: Float): SilentPoint {
        val p = t.coerceIn(0f, 1f)
        val one = 1f - p
        return SilentPoint(
            one * one * start.x + 2f * one * p * control.x + p * p * end.x,
            one * one * start.y + 2f * one * p * control.y + p * p * end.y,
        )
    }

    fun morphRect(start: SilentRect, end: SilentRect, t: Float): SilentRect {
        val eased = easeInOut(t)
        val raw = rect(start, end, eased)
        val startCenter = SilentPoint(start.x + start.w * 0.5f, start.y + start.h * 0.5f)
        val endCenter = SilentPoint(end.x + end.w * 0.5f, end.y + end.h * 0.5f)
        val control = SilentPoint((startCenter.x + endCenter.x) * 0.5f, min(startCenter.y, endCenter.y) - 14f)
        val center = quadraticPoint(startCenter, control, endCenter, eased)
        return SilentRect(center.x - raw.w * 0.5f, center.y - raw.h * 0.5f, max(0f, raw.w), max(0f, raw.h))
    }

    fun openingRect(source: SilentRect, progress: Float): SilentRect {
        val prep = ModuleSettingsMorphController.OPEN_PREP_MS.toFloat() /
            (ModuleSettingsMorphController.OPEN_PREP_MS + ModuleSettingsMorphController.OPEN_TRAVEL_MS).toFloat()
        if (progress <= prep) return source
        return morphRect(source, SilentClickGuiLayout.sidePanel, (progress - prep) / (1f - prep))
    }

    fun openingStageProgress(progress: Float): Float {
        val prep = ModuleSettingsMorphController.OPEN_PREP_MS.toFloat() /
            (ModuleSettingsMorphController.OPEN_PREP_MS + ModuleSettingsMorphController.OPEN_TRAVEL_MS).toFloat()
        return ((progress - prep) / (1f - prep)).coerceIn(0f, 1f)
    }

    fun switchRect(source: SilentRect, progress: Float): SilentRect {
        val prep = 40f / ModuleSettingsMorphController.SWITCH_TRAVEL_MS.toFloat()
        if (progress <= prep) return source
        return morphRect(source, SilentClickGuiLayout.sidePanel, (progress - prep) / (1f - prep))
    }

    fun switchStageProgress(progress: Float): Float {
        val prep = 40f / ModuleSettingsMorphController.SWITCH_TRAVEL_MS.toFloat()
        return ((progress - prep) / (1f - prep)).coerceIn(0f, 1f)
    }

    fun radius(t: Float): Float = lerp(6f, 8f, easeInOut(t))
    fun cardContentAlpha(t: Float): Float = (1f - t.coerceIn(0f, 1f) / 0.35f).coerceIn(0f, 1f)
    fun panelContentAlpha(t: Float): Float = t.coerceIn(0f, 1f)
}

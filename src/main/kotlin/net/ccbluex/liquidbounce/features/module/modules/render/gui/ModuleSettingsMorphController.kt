package net.ccbluex.liquidbounce.features.module.modules.render.gui

import net.ccbluex.liquidbounce.features.module.ClientModule
import kotlin.math.max
import kotlin.math.min

enum class ModuleSettingsMorphPhase {
    CLOSED, OPENING, OPEN, SWITCHING, PREPARING_CLOSE, CLOSING
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

    fun open(module: ClientModule, category: Any?, index: Int, card: SilentRect) {
        selectedModule = module
        sourceCategory = category
        sourceIndex = index
        sourceRect = finiteRect(card)
        latestSourceRect = sourceRect
        pendingModule = null
        pendingPage = null
        pendingSearchText = null
        transitionStartedAt = clock()
        phase = ModuleSettingsMorphPhase.OPENING
    }

    fun switch(module: ClientModule, category: Any?, index: Int, card: SilentRect) {
        if (phase != ModuleSettingsMorphPhase.OPEN || selectedModule === module) return
        selectedModule = module
        sourceCategory = category
        sourceIndex = index
        sourceRect = finiteRect(card)
        latestSourceRect = sourceRect
        pendingModule = null
        transitionStartedAt = clock()
        phase = ModuleSettingsMorphPhase.SWITCHING
    }

    fun requestClose(page: SilentClickGuiPage? = null, search: String? = null) {
        if (phase == ModuleSettingsMorphPhase.CLOSED || phase == ModuleSettingsMorphPhase.CLOSING || phase == ModuleSettingsMorphPhase.PREPARING_CLOSE) {
            if (page != null) pendingPage = page
            if (search != null) pendingSearchText = search
            return
        }
        pendingPage = page ?: pendingPage
        pendingSearchText = search ?: pendingSearchText
        pendingModule = selectedModule
        closingStartRect = if (phase == ModuleSettingsMorphPhase.OPENING) {
            SilentClickGuiMorph.morphRect(sourceRect, SilentClickGuiLayout.sidePanel, progress())
        } else {
            SilentClickGuiLayout.sidePanel
        }
        transitionStartedAt = clock()
        phase = ModuleSettingsMorphPhase.PREPARING_CLOSE
    }

    fun setCloseTarget(rect: SilentRect, needsReveal: Boolean) {
        if (phase != ModuleSettingsMorphPhase.PREPARING_CLOSE) return
        latestSourceRect = finiteRect(rect)
        if (!needsReveal) {
            transitionStartedAt = clock()
            phase = ModuleSettingsMorphPhase.CLOSING
        }
    }

    fun setClosingStart(rect: SilentRect) {
        closingStartRect = finiteRect(rect)
    }

    fun updateLatestSourceRect(rect: SilentRect) {
        latestSourceRect = finiteRect(rect)
    }

    fun tick(now: Long = clock(), target: SilentRect? = null) {
        when (phase) {
            ModuleSettingsMorphPhase.OPENING -> if (now - transitionStartedAt >= OPEN_MS) phase = ModuleSettingsMorphPhase.OPEN
            ModuleSettingsMorphPhase.SWITCHING -> if (now - transitionStartedAt >= SWITCH_MS) phase = ModuleSettingsMorphPhase.OPEN
            else -> Unit
        }
        if (target != null && phase == ModuleSettingsMorphPhase.OPEN) latestSourceRect = finiteRect(target)
    }

    fun reverseOpening() {
        if (phase == ModuleSettingsMorphPhase.OPENING) {
            transitionStartedAt = clock()
            phase = ModuleSettingsMorphPhase.CLOSING
        }
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
    }

    fun consumePendingPage(): SilentClickGuiPage? = pendingPage.also { pendingPage = null }
    fun consumePendingSearch(): String? = pendingSearchText.also { pendingSearchText = null }

    fun snapshot() = ModuleSettingsMorphSnapshot(
        phase, selectedModule, sourceCategory, sourceIndex, sourceRect, latestSourceRect,
        transitionStartedAt, pendingModule
    )

    fun progress(now: Long = clock()): Float {
        val duration = when (phase) {
            ModuleSettingsMorphPhase.OPENING -> OPEN_MS
            ModuleSettingsMorphPhase.CLOSING -> CLOSE_MS
            ModuleSettingsMorphPhase.SWITCHING -> SWITCH_MS
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
        const val OPEN_MS = 320L
        const val CLOSE_MS = 280L
        const val SWITCH_MS = 160L
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

    fun radius(t: Float): Float = lerp(6f, 8f, easeInOut(t))
    fun cardContentAlpha(t: Float): Float = (1f - t.coerceIn(0f, 1f) / 0.35f).coerceIn(0f, 1f)
    fun panelContentAlpha(t: Float): Float = ((t.coerceIn(0f, 1f) - 0.7f) / 0.3f).coerceIn(0f, 1f)
}

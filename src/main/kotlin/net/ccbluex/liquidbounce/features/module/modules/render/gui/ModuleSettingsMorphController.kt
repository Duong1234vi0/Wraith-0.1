package net.ccbluex.liquidbounce.features.module.modules.render.gui

import net.ccbluex.liquidbounce.features.module.ClientModule
import kotlin.math.hypot
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToLong

enum class ModuleSettingsMorphPhase {
    CLOSED,
    OPEN_PREP,
    OPEN_TRAVEL,
    OPEN_SETTLE,
    OPEN,
    SWITCH_FADE_OUT,
    SWITCH_PREP,
    SWITCH_TRAVEL,
    SWITCH_SETTLE,
    CLOSE_FADE_OUT,
    CLOSE_TRAVEL,
    CLOSE_SETTLE,
}

enum class ModuleSettingsMorphContent {
    NONE,
    CARD,
    PANEL,
}

data class SilentRectVelocity(
    val x: Float,
    val y: Float,
    val w: Float,
    val h: Float,
) {
    fun isNearlyZero(epsilon: Float = 0.0001f): Boolean =
        max(max(kotlin.math.abs(x), kotlin.math.abs(y)), max(kotlin.math.abs(w), kotlin.math.abs(h))) <= epsilon

    companion object {
        val ZERO = SilentRectVelocity(0f, 0f, 0f, 0f)
    }
}

data class ModuleSettingsMorphFrame(
    val timestampNanos: Long,
    val phase: ModuleSettingsMorphPhase,
    val progress: Float,
    val rect: SilentRect,
    val radius: Float,
)

data class ModuleSettingsCloseCompletion(
    val pendingPage: SilentClickGuiPage?,
    val pendingSearch: String?,
)

enum class ModuleSettingsMorphEscapeAction {
    CLOSE_SETTINGS,
    CLOSE_ROOT,
}

object ModuleSettingsMorphInputPolicy {
    fun isBusy(phase: ModuleSettingsMorphPhase): Boolean =
        phase != ModuleSettingsMorphPhase.CLOSED && phase != ModuleSettingsMorphPhase.OPEN

    fun ownsSurface(phase: ModuleSettingsMorphPhase): Boolean = phase in setOf(
        ModuleSettingsMorphPhase.OPEN_PREP,
        ModuleSettingsMorphPhase.OPEN_TRAVEL,
        ModuleSettingsMorphPhase.OPEN_SETTLE,
        ModuleSettingsMorphPhase.CLOSE_FADE_OUT,
        ModuleSettingsMorphPhase.CLOSE_TRAVEL,
        ModuleSettingsMorphPhase.CLOSE_SETTLE,
        ModuleSettingsMorphPhase.SWITCH_PREP,
        ModuleSettingsMorphPhase.SWITCH_TRAVEL,
        ModuleSettingsMorphPhase.SWITCH_SETTLE,
    )

    fun shouldHideModuleCard(
        phase: ModuleSettingsMorphPhase,
        selectedModule: ClientModule?,
        module: ClientModule,
    ): Boolean = phase != ModuleSettingsMorphPhase.CLOSED && selectedModule === module

    fun escapeAction(phase: ModuleSettingsMorphPhase): ModuleSettingsMorphEscapeAction = when (phase) {
        ModuleSettingsMorphPhase.OPEN,
        ModuleSettingsMorphPhase.OPEN_PREP,
        ModuleSettingsMorphPhase.OPEN_TRAVEL,
        ModuleSettingsMorphPhase.OPEN_SETTLE,
        -> ModuleSettingsMorphEscapeAction.CLOSE_SETTINGS

        else -> ModuleSettingsMorphEscapeAction.CLOSE_ROOT
    }
}

data class ModuleSettingsMorphSnapshot(
    val phase: ModuleSettingsMorphPhase,
    val selectedModule: ClientModule?,
    val sourceCategory: Any?,
    val sourceIndex: Int,
    val sourceRect: SilentRect,
    val latestSourceRect: SilentRect,
    val transitionStartedAtNanos: Long,
    val pendingModule: ClientModule?,
)

private data class MorphMotion(
    val rect: SilentRect,
    val velocity: SilentRectVelocity,
)

/** Pure state/timing holder. Rendering and module-list resolution stay in the screen. */
class ModuleSettingsMorphController(
    private val clockNanos: () -> Long = System::nanoTime,
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
    var transitionStartedAtNanos: Long = 0L
        private set
    var activeTravelDurationMs: Long = OPEN_MIN_TRAVEL_MS
        private set
    var pendingModule: ClientModule? = null
        private set
    var pendingPage: SilentClickGuiPage? = null
        private set
    var pendingSearchText: String? = null
        private set
    var closeContent: ModuleSettingsMorphContent = ModuleSettingsMorphContent.NONE
        private set
    var closeCardAlphaStart: Float = 0f
        private set
    var closeHeaderAlphaStart: Float = 0f
        private set
    var closeSettingsAlphaStart: Float = 0f
        private set

    private var pendingCategory: Any? = null
    private var pendingIndex: Int = -1
    private var pendingSourceRect: SilentRect = SilentRect.ZERO
    private var openingStartRect: SilentRect = SilentRect.ZERO
    private var openingStartVelocity: SilentRectVelocity = SilentRectVelocity.ZERO
    private var openingStartRadius = CARD_RADIUS
    private var openingUsesHermite = false
    private var closingStartVelocity: SilentRectVelocity = SilentRectVelocity.ZERO
    private var closingStartRadius = PANEL_RADIUS
    private var closingUsesHermite = false
    private var closeFadeDurationMs = CLOSE_FADE_MS
    private var closeCompletion: ModuleSettingsCloseCompletion? = null

    fun nowNanos(): Long = clockNanos()

    fun open(module: ClientModule, category: Any?, index: Int, card: SilentRect) {
        val now = clockNanos()
        tick(now)
        val reopening = phase in CLOSE_PHASES
        val current = if (reopening) currentMotion(now) else null
        val currentRadius = if (reopening) radius(now) else CARD_RADIUS

        selectedModule = module
        sourceCategory = category
        sourceIndex = index
        sourceRect = finiteRect(card)
        latestSourceRect = sourceRect
        clearPendingSwitch()
        pendingPage = null
        pendingSearchText = null
        transitionStartedAtNanos = now

        if (current != null) {
            openingStartRect = current.rect
            openingStartVelocity = current.velocity
            openingStartRadius = currentRadius
            openingUsesHermite = true
            activeTravelDurationMs = travelDurationMs(
                openingStartRect, SilentClickGuiLayout.sidePanel, OPEN_MIN_TRAVEL_MS, OPEN_MAX_TRAVEL_MS
            )
            phase = if (SilentClickGuiMorph.centerDistance(openingStartRect, SilentClickGuiLayout.sidePanel) < 0.01f) {
                ModuleSettingsMorphPhase.OPEN_SETTLE
            } else {
                ModuleSettingsMorphPhase.OPEN_TRAVEL
            }
            return
        }

        openingStartRect = sourceRect
        openingStartVelocity = SilentRectVelocity.ZERO
        openingStartRadius = CARD_RADIUS
        openingUsesHermite = false
        activeTravelDurationMs = travelDurationMs(
            sourceRect, SilentClickGuiLayout.sidePanel, OPEN_MIN_TRAVEL_MS, OPEN_MAX_TRAVEL_MS
        )
        phase = ModuleSettingsMorphPhase.OPEN_PREP
    }

    /** Keep the old module selected until its panel has faded out. */
    fun switch(module: ClientModule, category: Any?, index: Int, card: SilentRect) {
        val now = clockNanos()
        tick(now)
        if (phase != ModuleSettingsMorphPhase.OPEN || selectedModule === module) return
        pendingModule = module
        pendingCategory = category
        pendingIndex = index
        pendingSourceRect = finiteRect(card)
        transitionStartedAtNanos = now
        phase = ModuleSettingsMorphPhase.SWITCH_FADE_OUT
    }

    fun requestClose(page: SilentClickGuiPage? = null, search: String? = null) {
        val now = clockNanos()
        tick(now)
        if (page != null) pendingPage = page
        if (search != null) pendingSearchText = search
        if (phase == ModuleSettingsMorphPhase.CLOSED || phase in CLOSE_PHASES) return

        val oldPhase = phase
        val oldProgress = progress(now)
        val motion = currentMotion(now)
        closingStartRect = motion.rect
        closingStartVelocity = motion.velocity
        closingStartRadius = radius(now)
        closingUsesHermite = !closingStartVelocity.isNearlyZero()
        pendingModule = selectedModule

        when (oldPhase) {
            ModuleSettingsMorphPhase.OPEN_PREP,
            ModuleSettingsMorphPhase.SWITCH_PREP,
            -> {
                closeContent = ModuleSettingsMorphContent.CARD
                closeCardAlphaStart = 1f - SilentClickGuiMorph.alphaEase(oldProgress)
                closeHeaderAlphaStart = 0f
                closeSettingsAlphaStart = 0f
                closeFadeDurationMs = CLOSE_FADE_MS
            }

            ModuleSettingsMorphPhase.OPEN_SETTLE,
            ModuleSettingsMorphPhase.SWITCH_SETTLE,
            -> {
                closeContent = ModuleSettingsMorphContent.PANEL
                closeCardAlphaStart = 0f
                closeHeaderAlphaStart = SilentClickGuiMorph.panelHeaderAlpha(oldProgress)
                closeSettingsAlphaStart = SilentClickGuiMorph.panelSettingsAlpha(oldProgress)
                closeFadeDurationMs = CLOSE_FADE_MS
            }

            ModuleSettingsMorphPhase.OPEN,
            ModuleSettingsMorphPhase.SWITCH_FADE_OUT,
            -> {
                val visible = if (oldPhase == ModuleSettingsMorphPhase.SWITCH_FADE_OUT) {
                    1f - SilentClickGuiMorph.alphaEase(oldProgress)
                } else {
                    1f
                }
                closeContent = ModuleSettingsMorphContent.PANEL
                closeCardAlphaStart = 0f
                closeHeaderAlphaStart = visible
                closeSettingsAlphaStart = visible
                closeFadeDurationMs = CLOSE_FADE_MS
            }

            else -> {
                closeContent = ModuleSettingsMorphContent.NONE
                closeCardAlphaStart = 0f
                closeHeaderAlphaStart = 0f
                closeSettingsAlphaStart = 0f
                // There is no content to fade while reversing an active flight.
                closeFadeDurationMs = 0L
            }
        }

        activeTravelDurationMs = travelDurationMs(
            closingStartRect, latestSourceRect, CLOSE_MIN_TRAVEL_MS, CLOSE_MAX_TRAVEL_MS
        )
        transitionStartedAtNanos = now
        phase = ModuleSettingsMorphPhase.CLOSE_FADE_OUT
    }

    /** The screen resolves the current card after filtering/scrolling changes. */
    fun setCloseTarget(rect: SilentRect, needsReveal: Boolean) {
        if (phase !in CLOSE_PHASES) return
        latestSourceRect = finiteRect(rect)
        activeTravelDurationMs = travelDurationMs(
            closingStartRect, latestSourceRect, CLOSE_MIN_TRAVEL_MS, CLOSE_MAX_TRAVEL_MS
        )
        if (needsReveal) return
    }

    fun setClosingStart(rect: SilentRect) {
        closingStartRect = finiteRect(rect)
        activeTravelDurationMs = travelDurationMs(
            closingStartRect, latestSourceRect, CLOSE_MIN_TRAVEL_MS, CLOSE_MAX_TRAVEL_MS
        )
    }

    fun updateLatestSourceRect(rect: SilentRect) {
        latestSourceRect = finiteRect(rect)
    }

    /**
     * Advances every fully elapsed stage and carries late-frame remainder into the next one.
     * The returned phases let the screen handle one-shot ownership changes even after a long frame.
     */
    fun tick(now: Long = clockNanos()): List<ModuleSettingsMorphPhase> {
        val entered = mutableListOf<ModuleSettingsMorphPhase>()
        repeat(MAX_PHASE_ADVANCES_PER_TICK) {
            val duration = stageDurationNanos() ?: return entered
            val elapsed = (now - transitionStartedAtNanos).coerceAtLeast(0L)
            if (elapsed < duration) return entered
            transitionStartedAtNanos += duration
            val next = advancePhase() ?: return entered
            entered += next
        }
        return entered
    }

    fun clear() {
        resetState()
        closeCompletion = null
    }

    fun consumeCloseCompletion(): ModuleSettingsCloseCompletion? =
        closeCompletion.also { closeCompletion = null }

    private fun resetState() {
        phase = ModuleSettingsMorphPhase.CLOSED
        selectedModule = null
        sourceCategory = null
        sourceIndex = -1
        sourceRect = SilentRect.ZERO
        latestSourceRect = SilentRect.ZERO
        closingStartRect = SilentRect.ZERO
        transitionStartedAtNanos = 0L
        activeTravelDurationMs = OPEN_MIN_TRAVEL_MS
        clearPendingSwitch()
        pendingPage = null
        pendingSearchText = null
        openingStartRect = SilentRect.ZERO
        openingStartVelocity = SilentRectVelocity.ZERO
        openingStartRadius = CARD_RADIUS
        openingUsesHermite = false
        closingStartVelocity = SilentRectVelocity.ZERO
        closingStartRadius = PANEL_RADIUS
        closingUsesHermite = false
        closeFadeDurationMs = CLOSE_FADE_MS
        closeContent = ModuleSettingsMorphContent.NONE
        closeCardAlphaStart = 0f
        closeHeaderAlphaStart = 0f
        closeSettingsAlphaStart = 0f
    }

    fun snapshot() = ModuleSettingsMorphSnapshot(
        phase, selectedModule, sourceCategory, sourceIndex, sourceRect, latestSourceRect,
        transitionStartedAtNanos, pendingModule
    )

    /** Progress for the active explicit phase. */
    fun progress(now: Long): Float {
        val duration = stageDurationNanos() ?: return 1f
        if (duration <= 0L) return 1f
        return ((now - transitionStartedAtNanos).toDouble() / duration.toDouble()).toFloat().coerceIn(0f, 1f)
    }

    fun frame(now: Long): ModuleSettingsMorphFrame = ModuleSettingsMorphFrame(
        timestampNanos = now,
        phase = phase,
        progress = progress(now),
        rect = currentRect(now),
        radius = radius(now),
    )

    fun velocity(now: Long): SilentRectVelocity = currentMotion(now).velocity

    fun stageDurationMs(): Long? = stageDurationMsFor(phase, activeTravelDurationMs, closeFadeDurationMs)

    private fun advancePhase(): ModuleSettingsMorphPhase? {
        val previous = phase
        val next = successorOnExpiration(previous) ?: return null
        when (previous) {
            ModuleSettingsMorphPhase.SWITCH_FADE_OUT -> {
                selectedModule = pendingModule
                sourceCategory = pendingCategory
                sourceIndex = pendingIndex
                sourceRect = pendingSourceRect
                latestSourceRect = sourceRect
                openingStartRect = sourceRect
                openingStartVelocity = SilentRectVelocity.ZERO
                openingStartRadius = CARD_RADIUS
                openingUsesHermite = false
                activeTravelDurationMs = travelDurationMs(
                    sourceRect, SilentClickGuiLayout.sidePanel, SWITCH_MIN_TRAVEL_MS, SWITCH_MAX_TRAVEL_MS
                )
                clearPendingSwitch()
            }

            ModuleSettingsMorphPhase.CLOSE_SETTLE -> {
                val completion = ModuleSettingsCloseCompletion(pendingPage, pendingSearchText)
                resetState()
                closeCompletion = completion
                return phase
            }

            else -> Unit
        }
        phase = next
        return phase
    }

    private fun currentRect(now: Long): SilentRect = when (phase) {
        ModuleSettingsMorphPhase.CLOSED -> SilentRect.ZERO
        ModuleSettingsMorphPhase.OPEN_PREP -> sourceRect
        ModuleSettingsMorphPhase.OPEN_TRAVEL -> openingTravelRect(progress(now))
        ModuleSettingsMorphPhase.OPEN_SETTLE,
        ModuleSettingsMorphPhase.OPEN,
        ModuleSettingsMorphPhase.SWITCH_FADE_OUT,
        ModuleSettingsMorphPhase.SWITCH_SETTLE,
        -> SilentClickGuiLayout.sidePanel
        ModuleSettingsMorphPhase.SWITCH_PREP -> sourceRect
        ModuleSettingsMorphPhase.SWITCH_TRAVEL -> openingTravelRect(progress(now))
        ModuleSettingsMorphPhase.CLOSE_FADE_OUT -> closingStartRect
        ModuleSettingsMorphPhase.CLOSE_TRAVEL -> closingTravelRect(progress(now))
        ModuleSettingsMorphPhase.CLOSE_SETTLE -> latestSourceRect
    }

    private fun radius(now: Long): Float = when (phase) {
        ModuleSettingsMorphPhase.CLOSED -> CARD_RADIUS
        ModuleSettingsMorphPhase.OPEN_PREP,
        ModuleSettingsMorphPhase.SWITCH_PREP,
        -> CARD_RADIUS
        ModuleSettingsMorphPhase.OPEN_TRAVEL,
        ModuleSettingsMorphPhase.SWITCH_TRAVEL,
        -> SilentClickGuiMorph.radius(openingStartRadius, PANEL_RADIUS, progress(now))
        ModuleSettingsMorphPhase.OPEN_SETTLE,
        ModuleSettingsMorphPhase.OPEN,
        ModuleSettingsMorphPhase.SWITCH_FADE_OUT,
        ModuleSettingsMorphPhase.SWITCH_SETTLE,
        -> PANEL_RADIUS
        ModuleSettingsMorphPhase.CLOSE_FADE_OUT -> closingStartRadius
        ModuleSettingsMorphPhase.CLOSE_TRAVEL ->
            SilentClickGuiMorph.radius(closingStartRadius, CARD_RADIUS, progress(now))
        ModuleSettingsMorphPhase.CLOSE_SETTLE -> CARD_RADIUS
    }

    private fun currentMotion(now: Long): MorphMotion {
        val p = progress(now)
        return when (phase) {
            ModuleSettingsMorphPhase.OPEN_TRAVEL -> sampledMotion(p, activeTravelDurationMs, ::openingTravelRect)
            ModuleSettingsMorphPhase.SWITCH_TRAVEL -> sampledMotion(p, activeTravelDurationMs, ::openingTravelRect)
            ModuleSettingsMorphPhase.CLOSE_TRAVEL -> sampledMotion(p, activeTravelDurationMs, ::closingTravelRect)
            else -> MorphMotion(currentRect(now), SilentRectVelocity.ZERO)
        }
    }

    private fun openingTravelRect(progress: Float): SilentRect = if (openingUsesHermite) {
        SilentClickGuiMorph.hermiteRect(
            openingStartRect, SilentClickGuiLayout.sidePanel, openingStartVelocity,
            SilentRectVelocity.ZERO, activeTravelDurationMs, progress
        )
    } else {
        SilentClickGuiMorph.morphRect(
            openingStartRect, SilentClickGuiLayout.sidePanel, progress, activeTravelDurationMs
        )
    }

    private fun closingTravelRect(progress: Float): SilentRect = if (closingUsesHermite) {
        SilentClickGuiMorph.hermiteRect(
            closingStartRect, latestSourceRect, closingStartVelocity,
            SilentRectVelocity.ZERO, activeTravelDurationMs, progress
        )
    } else {
        SilentClickGuiMorph.morphRect(closingStartRect, latestSourceRect, progress, activeTravelDurationMs)
    }

    private fun sampledMotion(
        progress: Float,
        durationMs: Long,
        sampler: (Float) -> SilentRect,
    ): MorphMotion {
        val p = progress.coerceIn(0f, 1f)
        val delta = 0.0005f
        val p0 = (p - delta).coerceAtLeast(0f)
        val p1 = (p + delta).coerceAtMost(1f)
        val rect = sampler(p)
        val elapsedMs = (p1 - p0) * durationMs.toFloat()
        if (elapsedMs <= 0f || !elapsedMs.isFinite()) return MorphMotion(rect, SilentRectVelocity.ZERO)
        val before = sampler(p0)
        val after = sampler(p1)
        return MorphMotion(
            rect,
            SilentRectVelocity(
                (after.x - before.x) / elapsedMs,
                (after.y - before.y) / elapsedMs,
                (after.w - before.w) / elapsedMs,
                (after.h - before.h) / elapsedMs,
            ).finite(),
        )
    }

    private fun stageDurationNanos(): Long? = stageDurationMs()?.times(NANOS_PER_MILLISECOND)

    private fun clearPendingSwitch() {
        pendingModule = null
        pendingCategory = null
        pendingIndex = -1
        pendingSourceRect = SilentRect.ZERO
    }

    private fun finiteRect(rect: SilentRect): SilentRect {
        val x = if (rect.x.isFinite()) rect.x else 0f
        val y = if (rect.y.isFinite()) rect.y else 0f
        val w = if (rect.w.isFinite()) max(0f, rect.w) else 0f
        val h = if (rect.h.isFinite()) max(0f, rect.h) else 0f
        return SilentRect(x, y, w, h)
    }

    private fun SilentRectVelocity.finite() = SilentRectVelocity(
        x.takeIf { it.isFinite() } ?: 0f,
        y.takeIf { it.isFinite() } ?: 0f,
        w.takeIf { it.isFinite() } ?: 0f,
        h.takeIf { it.isFinite() } ?: 0f,
    )

    companion object {
        const val OPEN_PREP_MS = 70L
        const val OPEN_SETTLE_MS = 100L
        const val CLOSE_FADE_MS = 75L
        const val CLOSE_SETTLE_MS = 65L
        const val SWITCH_FADE_MS = 70L
        const val SWITCH_PREP_MS = 55L
        const val SWITCH_SETTLE_MS = 90L

        const val OPEN_MIN_TRAVEL_MS = 260L
        const val OPEN_MAX_TRAVEL_MS = 340L
        const val CLOSE_MIN_TRAVEL_MS = 220L
        const val CLOSE_MAX_TRAVEL_MS = 300L
        const val SWITCH_MIN_TRAVEL_MS = 240L
        const val SWITCH_MAX_TRAVEL_MS = 320L

        const val CARD_RADIUS = 6f
        const val PANEL_RADIUS = 8f
        private const val PRACTICAL_NEAR_DISTANCE = 150f
        private const val PRACTICAL_FAR_DISTANCE = 275f
        private const val NANOS_PER_MILLISECOND = 1_000_000L
        private const val MAX_PHASE_ADVANCES_PER_TICK = 12

        val CLOSE_PHASES = setOf(
            ModuleSettingsMorphPhase.CLOSE_FADE_OUT,
            ModuleSettingsMorphPhase.CLOSE_TRAVEL,
            ModuleSettingsMorphPhase.CLOSE_SETTLE,
        )

        internal fun stageDurationMsFor(
            phase: ModuleSettingsMorphPhase,
            travelDurationMs: Long = OPEN_MIN_TRAVEL_MS,
            closeFadeDurationMs: Long = CLOSE_FADE_MS,
        ): Long? = when (phase) {
            ModuleSettingsMorphPhase.OPEN_PREP -> OPEN_PREP_MS
            ModuleSettingsMorphPhase.OPEN_TRAVEL,
            ModuleSettingsMorphPhase.SWITCH_TRAVEL,
            ModuleSettingsMorphPhase.CLOSE_TRAVEL,
            -> travelDurationMs
            ModuleSettingsMorphPhase.OPEN_SETTLE -> OPEN_SETTLE_MS
            ModuleSettingsMorphPhase.SWITCH_FADE_OUT -> SWITCH_FADE_MS
            ModuleSettingsMorphPhase.SWITCH_PREP -> SWITCH_PREP_MS
            ModuleSettingsMorphPhase.SWITCH_SETTLE -> SWITCH_SETTLE_MS
            ModuleSettingsMorphPhase.CLOSE_FADE_OUT -> closeFadeDurationMs
            ModuleSettingsMorphPhase.CLOSE_SETTLE -> CLOSE_SETTLE_MS
            else -> null
        }

        internal fun successorOnExpiration(phase: ModuleSettingsMorphPhase): ModuleSettingsMorphPhase? = when (phase) {
            ModuleSettingsMorphPhase.OPEN_PREP -> ModuleSettingsMorphPhase.OPEN_TRAVEL
            ModuleSettingsMorphPhase.OPEN_TRAVEL -> ModuleSettingsMorphPhase.OPEN_SETTLE
            ModuleSettingsMorphPhase.OPEN_SETTLE -> ModuleSettingsMorphPhase.OPEN
            ModuleSettingsMorphPhase.SWITCH_FADE_OUT -> ModuleSettingsMorphPhase.SWITCH_PREP
            ModuleSettingsMorphPhase.SWITCH_PREP -> ModuleSettingsMorphPhase.SWITCH_TRAVEL
            ModuleSettingsMorphPhase.SWITCH_TRAVEL -> ModuleSettingsMorphPhase.SWITCH_SETTLE
            ModuleSettingsMorphPhase.SWITCH_SETTLE -> ModuleSettingsMorphPhase.OPEN
            ModuleSettingsMorphPhase.CLOSE_FADE_OUT -> ModuleSettingsMorphPhase.CLOSE_TRAVEL
            ModuleSettingsMorphPhase.CLOSE_TRAVEL -> ModuleSettingsMorphPhase.CLOSE_SETTLE
            ModuleSettingsMorphPhase.CLOSE_SETTLE -> ModuleSettingsMorphPhase.CLOSED
            else -> null
        }

        fun travelDurationMs(start: SilentRect, end: SilentRect, minMs: Long, maxMs: Long): Long {
            val minimum = min(minMs, maxMs)
            val maximum = max(minMs, maxMs)
            val distance = SilentClickGuiMorph.centerDistance(start, end).takeIf { it.isFinite() } ?: 0f
            val normalized = ((distance - PRACTICAL_NEAR_DISTANCE) /
                (PRACTICAL_FAR_DISTANCE - PRACTICAL_NEAR_DISTANCE)).coerceIn(0f, 1f)
            return (minimum + (maximum - minimum) * normalized).roundToLong().coerceIn(minimum, maximum)
        }

        fun openingTotalMs(start: SilentRect, end: SilentRect = SilentClickGuiLayout.sidePanel): Long =
            OPEN_PREP_MS + travelDurationMs(start, end, OPEN_MIN_TRAVEL_MS, OPEN_MAX_TRAVEL_MS) + OPEN_SETTLE_MS

        fun closingTotalMs(start: SilentRect, end: SilentRect): Long =
            CLOSE_FADE_MS + travelDurationMs(start, end, CLOSE_MIN_TRAVEL_MS, CLOSE_MAX_TRAVEL_MS) + CLOSE_SETTLE_MS

        fun switchingTotalMs(start: SilentRect, end: SilentRect = SilentClickGuiLayout.sidePanel): Long =
            SWITCH_FADE_MS + SWITCH_PREP_MS +
                travelDurationMs(start, end, SWITCH_MIN_TRAVEL_MS, SWITCH_MAX_TRAVEL_MS) + SWITCH_SETTLE_MS
    }
}

object SilentClickGuiMorph {
    private const val HEIGHT_DELAY_MS = 40f

    fun lerp(a: Float, b: Float, t: Float): Float = a + (b - a) * t.coerceIn(0f, 1f)

    fun rect(a: SilentRect, b: SilentRect, t: Float): SilentRect {
        val p = t.coerceIn(0f, 1f)
        return SilentRect(lerp(a.x, b.x, p), lerp(a.y, b.y, p), lerp(a.w, b.w, p), lerp(a.h, b.h, p))
    }

    fun smooth(t: Float): Float {
        val p = t.coerceIn(0f, 1f)
        return p * p * (3f - 2f * p)
    }

    fun cubicBezier(progress: Float, x1: Float, y1: Float, x2: Float, y2: Float): Float {
        val x = progress.coerceIn(0f, 1f)
        if (x == 0f || x == 1f) return x
        var low = 0.0
        var high = 1.0
        repeat(28) {
            val parameter = (low + high) * 0.5
            if (cubicCoordinate(parameter, x1.toDouble(), x2.toDouble()) < x) low = parameter else high = parameter
        }
        return cubicCoordinate((low + high) * 0.5, y1.toDouble(), y2.toDouble()).toFloat().coerceIn(0f, 1f)
    }

    /** CSS cubic-bezier(0.22, 1, 0.36, 1), solved against its X curve. */
    fun positionEase(t: Float): Float = cubicBezier(t, 0.22f, 1f, 0.36f, 1f)

    /** CSS standard material ease cubic-bezier(0.4, 0, 0.2, 1). */
    fun sizeEase(t: Float): Float = cubicBezier(t, 0.4f, 0f, 0.2f, 1f)

    fun alphaEase(t: Float): Float = cubicBezier(t, 0.33f, 1f, 0.68f, 1f)

    fun heightEase(t: Float, durationMs: Long): Float {
        val duration = durationMs.coerceAtLeast(1L).toFloat()
        val elapsed = t.coerceIn(0f, 1f) * duration
        val delayed = ((elapsed - HEIGHT_DELAY_MS) / max(1f, duration - HEIGHT_DELAY_MS)).coerceIn(0f, 1f)
        return sizeEase(delayed)
    }

    fun centerDistance(start: SilentRect, end: SilentRect): Float {
        val dx = (end.x + end.w * 0.5f) - (start.x + start.w * 0.5f)
        val dy = (end.y + end.h * 0.5f) - (start.y + start.h * 0.5f)
        return hypot(dx, dy)
    }

    fun arcHeight(start: SilentRect, end: SilentRect): Float =
        (centerDistance(start, end) * 0.035f).coerceIn(5f, 11f)

    fun quadraticPoint(start: SilentPoint, control: SilentPoint, end: SilentPoint, t: Float): SilentPoint {
        val p = t.coerceIn(0f, 1f)
        val one = 1f - p
        return SilentPoint(
            one * one * start.x + 2f * one * p * control.x + p * p * end.x,
            one * one * start.y + 2f * one * p * control.y + p * p * end.y,
        )
    }

    fun morphRect(start: SilentRect, end: SilentRect, t: Float, durationMs: Long = 300L): SilentRect {
        val p = t.coerceIn(0f, 1f)
        if (p == 0f) return start
        if (p == 1f) return end
        val position = positionEase(p)
        val width = sizeEase(p)
        val height = heightEase(p, durationMs)
        val startCenter = SilentPoint(start.x + start.w * 0.5f, start.y + start.h * 0.5f)
        val endCenter = SilentPoint(end.x + end.w * 0.5f, end.y + end.h * 0.5f)
        val control = SilentPoint(
            (startCenter.x + endCenter.x) * 0.5f,
            (startCenter.y + endCenter.y) * 0.5f - arcHeight(start, end),
        )
        val center = quadraticPoint(startCenter, control, endCenter, position)
        val nextWidth = max(0f, lerp(start.w, end.w, width))
        val nextHeight = max(0f, lerp(start.h, end.h, height))
        return SilentRect(center.x - nextWidth * 0.5f, center.y - nextHeight * 0.5f, nextWidth, nextHeight)
    }

    fun hermiteRect(
        start: SilentRect,
        end: SilentRect,
        startVelocity: SilentRectVelocity,
        endVelocity: SilentRectVelocity,
        durationMs: Long,
        t: Float,
    ): SilentRect {
        val p = t.coerceIn(0f, 1f)
        if (p == 0f) return start
        if (p == 1f) return end
        val duration = durationMs.coerceAtLeast(1L).toFloat()
        return SilentRect(
            hermite(start.x, end.x, startVelocity.x * duration, endVelocity.x * duration, p),
            hermite(start.y, end.y, startVelocity.y * duration, endVelocity.y * duration, p),
            max(0f, hermite(start.w, end.w, startVelocity.w * duration, endVelocity.w * duration, p)),
            max(0f, hermite(start.h, end.h, startVelocity.h * duration, endVelocity.h * duration, p)),
        )
    }

    fun radius(start: Float, end: Float, t: Float): Float = lerp(start, end, smooth(t))

    fun cardContentAlpha(t: Float): Float = 1f - alphaEase(t)

    fun panelHeaderAlpha(t: Float): Float = alphaEase((t.coerceIn(0f, 1f) / 0.72f).coerceIn(0f, 1f))

    fun panelSettingsAlpha(t: Float): Float = alphaEase(((t.coerceIn(0f, 1f) - 0.18f) / 0.82f).coerceIn(0f, 1f))

    fun closingSettingsAlpha(t: Float): Float = 1f - alphaEase((t.coerceIn(0f, 1f) / 0.72f).coerceIn(0f, 1f))

    fun closingHeaderAlpha(t: Float): Float =
        1f - alphaEase(((t.coerceIn(0f, 1f) - 0.12f) / 0.88f).coerceIn(0f, 1f))

    private fun cubicCoordinate(t: Double, p1: Double, p2: Double): Double {
        val one = 1.0 - t
        return 3.0 * one * one * t * p1 + 3.0 * one * t * t * p2 + t * t * t
    }

    private fun hermite(start: Float, end: Float, startTangent: Float, endTangent: Float, t: Float): Float {
        val p2 = t * t
        val p3 = p2 * t
        val h00 = 2f * p3 - 3f * p2 + 1f
        val h10 = p3 - 2f * p2 + t
        val h01 = -2f * p3 + 3f * p2
        val h11 = p3 - p2
        return h00 * start + h10 * startTangent + h01 * end + h11 * endTangent
    }
}

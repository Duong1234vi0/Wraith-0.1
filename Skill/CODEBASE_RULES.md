# Current Codebase Rules

Derived from the uploaded current `src/` snapshot. Re-check the live repository before editing because this document is a navigation aid, not a substitute for source inspection.

## Source roots

- Kotlin root: `src/main/kotlin/net/ccbluex/liquidbounce/`
- Java/mixin root: `src/main/java/net/ccbluex/liquidbounce/`
- Minecraft compatibility aliases/overrides also exist under `src/main/kotlin/net/minecraft/` and `src/main/java/net/minecraft/`/`com/mojang/`.
- Resources: `src/main/resources/`

## Module system

Key files:

- `features/module/ClientModule.kt`
- `features/module/Category.kt`
- `features/module/ModuleManager.kt`

Rules:

- Built-in modules are registered in `ModuleManager.registerInbuilt()`.
- Runtime categories are only:
  - `Category.COMBAT`
  - `Category.PLAYER`
  - `Category.MOVEMENT`
  - `Category.RENDER`
  - `Category.MISC`
- Folder names such as `exploit`, `world`, and `client` are organizational only. Use the constructor category as runtime truth.
- `enabled` is configuration/toggle state. `running` additionally requires the parent/listener state and in-game state.
- Module enable/disable side effects are suppressed during config loading through current lifecycle logic. Read `ClientModule.kt` before adding toggle side effects.
- `disableOnQuit` is an explicit constructor behavior; do not add it unless source/user requires it.
- Built-in registration is followed by `walkKeyPath()` and fallback-description verification.

## Settings/config system

Key files:

- `config/types/Value.kt`
- `config/types/nesting/Configurable.kt`
- `config/types/nesting/ChoiceConfigurable.kt`
- `config/AutoConfig.kt`
- `config/ConfigSystem.kt`

Common DSL:

- `boolean`
- `int`, `float`
- `intRange`, `floatRange`
- `enumChoice`
- `multiEnumChoice`
- `color`
- `text`, `textList`
- `bind`, `key`
- `items`, `blocks`, `sounds`, `statusEffects`
- `choices(...)` for `Choice` modes

Porting rules:

- Keep source names/defaults/ranges/units/visibility.
- Use `Choice` when modes have separate settings, mutable state, lifecycle, or handlers.
- A simple enum is appropriate only for simple selection without mode-owned behavior.
- Do not add `tagBy`, aliases, independent descriptions, config migration, or hidden options unless source/current compatibility needs them.
- Keep per-mode settings inside the mode; do not expose all mode settings simultaneously.

## Event system

Key files:

- `event/Event.kt`
- `event/EventListener.kt`
- `event/EventManager.kt`
- `event/events/*.kt`

Handlers are normally registered as properties:

```kotlin
@Suppress("unused")
private val tickHandler = handler<GameTickEvent> { event ->
    // ...
}
```

Important behavior:

- Listener `running` gates handlers through module/choice parent state.
- Priorities matter. Inspect existing conventions before choosing one.
- `sequenceHandler` and tick sequences are not equivalent to a simple tick callback.
- Event class existence does not prove it is dispatched.

Observed dispatch status in the uploaded snapshot:

- `WorldRenderEvent` is dispatched from `MixinGameRenderer`.
- `OverlayRenderEvent` is dispatched from `MixinInGameHud` on the GUI extraction path.
- `ScreenRenderEvent` is defined but no dispatch call was found in the uploaded source snapshot.
- `DrawOutlinesEvent` is defined but no dispatch call was found in the uploaded source snapshot.

Always search for `EventManager.callEvent(...)` before using an event.

## World rendering

Key files:

- `event/events/DrawEvents.kt`
- `injection/mixins/minecraft/render/MixinGameRenderer.java`
- `render/compat/RenderCompat.kt`
- module-specific pipelines such as `features/module/modules/render/targetesp/RichEspPipelines.kt`

Rules:

- `WorldRenderEvent` supplies a `PoseStack`, `Camera`, and partial ticks.
- Verify whether the supplied matrix is already camera-relative before subtracting camera position.
- Preserve exact blend/depth/cull/light/render-target behavior when porting.
- `RenderCompat.blendFunc` history contains no-op/compat pitfalls; inspect the current implementation and pipeline cache before relying on state calls.
- A vanilla `RenderType`, a custom `RenderPipeline`, and the compat mesh submitter can enter different render targets/stages and interact differently with Iris.
- Do not infer shader/bloom behavior from a RenderType name alone.

## GUI/HUD rendering

Key files:

- `event/events/DrawEvents.kt`
- `injection/mixins/minecraft/gui/MixinInGameHud.java`
- `features/module/modules/render/gui/`
- `render/gui/`

Current compatibility detail:

```kotlin
net.minecraft.client.gui.DrawContext
```

is a typealias to `GuiGraphicsExtractor` in this fork.

Rules:

- Use the real `event.context` supplied by `OverlayRenderEvent`.
- Do not create a throwaway GUI context and expect vanilla to flush it.
- Use the current GUI pose/render-state path; do not copy old `MatrixStack`, old `RenderSystem.pushMatrix`, or raw GL GUI code.
- Verify logical coordinates, GUI scale, scissor, hit testing, and draw order.
- Custom GUI shaders should follow current GUI render-state patterns already used by rounded/glow/texture/font helpers.
- `blurBeforeThisStratum()` is a full GUI-stratum behavior, not rounded per-panel glass. Never use it as a fake masked panel blur.

## Rendering resources

Current custom helpers include:

- `render/gui/RoundedRectGui.kt`
- `render/gui/RoundedTextureGui.kt`
- `render/gui/GlowRectGui.kt`
- `render/gui/CustomFontGui.kt`
- `render/gui/SkinFaceGui.kt`
- `render/engine/BlurEffectRenderer.kt`

Before reusing a helper, verify:

- render stage;
- target;
- blend;
- depth;
- color range;
- alpha representation;
- GUI/world coordinate assumptions;
- whether the path is live or historical/dead.

## Texture/resource rules

- Dynamic image loading is sensitive to format on Android/FCL.
- Historical testing found grayscale PNGs can fail in current native image loading; prefer valid RGBA assets for dynamically registered textures.
- A white-on-black additive texture is not automatically suitable for normal alpha blending.
- When converting luminance to alpha, preserve the intended visual shape and do not overwrite a shared source asset blindly.
- Resource paths and namespace must match the current registration helper.

## Audio rules

- Prefer Minecraft native sound events/manager for FCL/Android compatibility.
- Avoid desktop-only `javax.sound.sampled` implementations.
- Preserve source asset, trigger timing, volume curve, pitch, and duplicate behavior.

## Mixins

Key root:

- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/`

Rules:

- Verify exact current-version target descriptor and injection point.
- Search existing mixins before adding a new one.
- Register the mixin and required access widener only when needed.
- Do not replace exact injection timing with a generic event unless semantics remain equivalent.

## Translations

`ClientModule.verifyFallbackDescription()` expects module description coverage after key paths are built.

When adding a module, inspect current fallback language resources and add only required keys. Do not rewrite unrelated translations.

## Build/runtime environment

See `Notes_/WORK_NOTES.md`.

Important distinction:

- `assemble` verifies compilation/packaging;
- FCL in-game testing verifies rendering, native resources, mixins, timing, shader behavior, and actual gameplay behavior.

## Historical notes rule

`Agent1.md` is chronological. It contains failed attempts, reverts, deleted modules, and old architecture states.

Never copy a historical solution without confirming it exists in current source.

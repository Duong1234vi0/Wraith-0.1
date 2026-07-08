# Current Source System Path Map

Use this file to locate systems quickly. It was rebuilt from the uploaded current source snapshot and intentionally separates system navigation from the full module list.

For registered modules, use `MODULE_CATALOG.md`.

## Core roots

| Logical system | Current source path | Role |
|---|---|---|
| `/Core/LiquidBounce` | `LiquidBounce.kt` | Main client initialization/lifecycle. |
| `/Config` | `config/` | Config roots, values, nesting, serialization, AutoConfig. |
| `/Event` | `event/` | Event classes, manager, handlers, sequences/coroutines. |
| `/Module` | `features/module/` | Module base, categories, registration, lifecycle. |
| `/Commands` | `features/command/` | Command registry/builders/built-ins. |
| `/Render` | `render/` | Fonts, render compatibility, GUI pipelines, legacy shader utilities. |
| `/ModuleRenderGUI` | `features/module/modules/render/gui/` | ClickGUI, theme, HUD systems and render composition. |
| `/Utils` | `utils/` | Aiming, combat, movement, inventory, packets, render, resources, entity helpers. |
| `/Script` | `script/` | Script API and bindings. |
| `/Lang` | `lang/` | Language manager/translation utilities. |
| `/Mixins` | `src/main/java/net/ccbluex/liquidbounce/injection/mixins/` | Minecraft/library hooks. |
| `/Resources` | `src/main/resources/` | Mod metadata, mixins JSON, lang, images, shaders, sounds, native assets. |

## Module architecture

| Concern | File/path |
|---|---|
| Module base/lifecycle | `features/module/ClientModule.kt` |
| Runtime categories | `features/module/Category.kt` |
| Built-in registration | `features/module/ModuleManager.kt` |
| Modes/choices | `config/types/nesting/ChoiceConfigurable.kt` |
| General setting DSL | `config/types/nesting/Configurable.kt` |
| Value listeners/serialization | `config/types/Value.kt` |
| AutoConfig/loading state | `config/AutoConfig.kt` |

## Event architecture

| Concern | File/path |
|---|---|
| Event marker/base | `event/Event.kt` |
| `handler`, `sequenceHandler`, listener hierarchy | `event/EventListener.kt` |
| Registration/dispatch | `event/EventManager.kt` |
| Client/game events | `event/events/ClientEvents.kt`, `GameEvents.kt` |
| Network events | `event/events/NetworkEvents.kt` |
| Player/input events | `event/events/PlayerEvents.kt` |
| World events | `event/events/WorldEvents.kt` |
| Render events | `event/events/DrawEvents.kt` |
| UI events | `event/events/UserInterfaceEvents.kt` |

### Verified render event dispatch in this snapshot

| Event | Dispatch source | Status |
|---|---|---|
| `WorldRenderEvent` | `injection/mixins/minecraft/render/MixinGameRenderer.java` | Live call site found. |
| `OverlayRenderEvent` | `injection/mixins/minecraft/gui/MixinInGameHud.java` | Live call site found. |
| `ScreenRenderEvent` | — | Defined, no call site found. Verify before use. |
| `DrawOutlinesEvent` | — | Defined, no call site found. Verify before use. |

## Rendering paths

| Logical path | File/path | Notes |
|---|---|---|
| `/Render/Compat` | `render/compat/RenderCompat.kt` | Compatibility state/pipeline/mesh submission. Inspect implementation before relying on GL-style state calls. |
| `/Render/WorldEvent` | `event/events/DrawEvents.kt`, `MixinGameRenderer.java` | World matrix/camera/partial ticks. |
| `/Render/WorldToScreen` | `utils/render/WorldToScreen.kt` | Projection/view/camera-based world-to-screen. |
| `/Render/GUI/RoundedRect` | `render/gui/RoundedRectGui.kt`, `RoundedRectShader.kt` | Current GUI rounded SDF path. |
| `/Render/GUI/RoundedTexture` | `render/gui/RoundedTextureGui.kt`, `RoundedTextureShader.kt` | Rounded image/avatar path. |
| `/Render/GUI/GlowRect` | `render/gui/GlowRectGui.kt`, `GlowRectShader.kt` | GUI glow path; do not confuse with shaderpack bloom. |
| `/Render/GUI/Font` | `render/gui/CustomFontGui.kt`, `render/FontManager.kt` | Batched custom font/glyph path. |
| `/Render/GUI/SkinFace` | `render/gui/SkinFaceGui.kt` | Skin face/hat rendering. |
| `/Render/Blur` | `render/engine/BlurEffectRenderer.kt` | Inspect whether call path is live before reuse; old raw framebuffer code may be incompatible. |
| `/Render/TargetESP` | `features/module/modules/render/targetesp/` | TargetESP module and owned pipelines. |
| `/Render/PopChams` | `features/module/modules/render/popchams/` | PopChams model/render logic. |
| `/Render/HUD` | `features/module/modules/render/ModuleHUD.kt`, `.../gui/hud/` | HUD manager/elements/material/surface/animation/editor. |
| `/Render/ClickGUI` | `features/module/modules/render/gui/ClickGuiScreen.kt` | Current ClickGUI dashboard/settings rendering. |

## Main utility areas

| Logical path | Source path | Role |
|---|---|---|
| `/Utils/Aiming` | `utils/aiming/` | Rotation manager, rotation targets, smoothing, movement correction. |
| `/Utils/Combat` | `utils/combat/` | Combat state/calculations and target helpers. |
| `/Utils/Clicking` | `utils/clicking/` | Click scheduling/patterns/cooldowns. |
| `/Utils/Entity` | `utils/entity/` | Entity/player helpers, compatibility extensions, simulation/cache. |
| `/Utils/Inventory` | `utils/inventory/` | Inventory manager/actions/slot utilities. |
| `/Utils/Movement` | `utils/movement/` | Directional input and movement math. |
| `/Utils/Network` | `utils/network/` | Packet utilities, queues, protocol helpers. |
| `/Utils/Render` | `utils/render/` | World render helpers, placement renderers, projection. |
| `/Utils/Block` | `utils/block/` | Block/chunk scanning and placement helpers. |
| `/Utils/Client` | `utils/client/` | Minecraft singleton access, logger, notifications, resource helpers. |
| `/Utils/Input` | `utils/input/` | Bind/input tracking. |
| `/Utils/Kotlin` | `utils/kotlin/` | Collections, priority conventions, utility extensions. |

## Registration/navigation rules

- To find a module currently available to users, start with `ModuleManager.registerInbuilt()`.
- Then open the module object and read its constructor category; do not infer category from folder.
- Trace dependencies through imports and call sites.
- Search resources/translations/mixins before declaring the port complete.
- If a class/event exists but is not registered/dispatched, it is not necessarily live.

## Regeneration note

The uploaded snapshot contained:

- 616 Kotlin files;
- 152 Java files;
- 120 resource files;
- 111 registered built-in modules.

Regenerate this map and `MODULE_CATALOG.md` after large source changes.

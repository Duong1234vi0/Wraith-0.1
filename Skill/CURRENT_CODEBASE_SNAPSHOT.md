# Current Codebase Snapshot

Generated from the uploaded `src/` snapshot used to improve this skill pack.

This file is a navigation snapshot, not permanent truth. Re-scan the live repository before editing.

## Scan summary

| Item | Count/path |
|---|---:|
| Kotlin files | 616 |
| Java files | 152 |
| Resource files | 120 |
| Registered built-in modules | 111 |
| Kotlin root | `src/main/kotlin/net/ccbluex/liquidbounce/` |
| Java/mixin root | `src/main/java/net/ccbluex/liquidbounce/` |
| Resources root | `src/main/resources/` |
| Main module manager | `features/module/ModuleManager.kt` |
| Module base | `features/module/ClientModule.kt` |
| Event base/handlers | `event/Event.kt`, `event/EventListener.kt`, `event/EventManager.kt` |
| Config DSL | `config/types/nesting/Configurable.kt` |

## Kotlin area counts

| Area | Kotlin files |
|---|---:|
| `features/` | 297 |
| `utils/` | 180 |
| `render/` | 45 |
| `config/` | 39 |
| `script/` | 22 |
| `event/` | 14 |
| `deeplearn/` | 8 |
| `integration/` | 3 |
| `lang/` | 2 |

## Runtime categories

The current `Category.kt` defines only:

- Combat
- Player
- Movement
- Render
- Misc

Source folders named `exploit`, `world`, and `client` still register modules into one of those runtime categories.

## Important current render facts

- `WorldRenderEvent` is dispatched from `MixinGameRenderer`.
- `OverlayRenderEvent` is dispatched from `MixinInGameHud` through the current GUI extraction path.
- `ScreenRenderEvent` is defined but no dispatcher was found in this source snapshot.
- `DrawOutlinesEvent` is defined but no dispatcher was found in this source snapshot.
- `DrawContext` is aliased to `GuiGraphicsExtractor`.
- Current custom GUI primitives include rounded rectangle, rounded texture, glow rectangle, custom font, and skin-face render paths.
- Multiple world render paths coexist: vanilla `RenderType`, module-owned pipelines, compat mesh submission, and shader/Iris-captured paths. They are not behaviorally interchangeable.

## Current modules relevant to recent work

- `features/module/modules/render/targetesp/ModuleTargetESP.kt`
- `features/module/modules/render/targetesp/RichEspPipelines.kt`
- `features/module/modules/render/popchams/ModulePopChams.kt`
- `features/module/modules/render/ModuleHUD.kt`
- `features/module/modules/render/gui/hud/`
- `features/module/modules/misc/ModuleClientSound.kt`
- `features/module/modules/render/ModuleTotemParticles.kt`
- `features/module/modules/render/ModuleFragEffects.kt`

## Version caution

Historical notes use both “1.26.2” and “26.1.2” naming. Do not infer the exact target version from this snapshot or Agent1 alone.

Read the live repository’s Gradle properties, mappings, Fabric metadata, and dependency declarations before making version-specific API decisions.

## Current-source precedence

This snapshot was generated from one uploaded source tree. If the live opened repository differs, the live repository wins.

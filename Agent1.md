# AI CLI Read-First Notice

> This file is a chronological engineering journal, not a current-state specification.

Before using any entry below:

1. Read `Skill/README.md` and `Skill/Agen.md`.
2. For ports, read `Skill/CLAUDE_PORT_SKID_MODULE_GUIDE.md` and complete `Skill/PORT_EVIDENCE_TEMPLATE.md`.
3. Open the live current repository source and verify what actually exists.
4. Read all later entries for the same feature, especially entries marked `FAILED`, `REVERTED`, `DELETED`, `attempt`, or `not yet tested`.
5. Treat reference source as authoritative for port behavior and current repository source as authoritative for current integration.

## Authority rules

- User request controls scope and desired behavior.
- Current repository source controls current code/API/event/render state.
- Supplied reference source controls source behavior for a port.
- Build files/mappings control target version.
- This file explains history only.

## Strict no-invention rule for ports

Do not add modes, settings, effects, fallbacks, heuristics, sounds, notifications, aliases, extra passes, or redesigned logic unless they exist in the inspected source or the user explicitly asks for them. If a dependency or equivalent API cannot be proven, stop and report the blocker rather than implementing a “reasonable approximation.”

## Current-source warning

The source snapshot used to prepare the accompanying skill pack differs from some older notes in this journal. Always search the live source before following a historical path. In particular, event classes may exist without dispatch, modules may have moved into subpackages, and old render experiments may have been reverted.

---

# LiquidBounce Fork — 1.21.4 → 1.26.2 Migration Notes

Snapshot of fixes/work completed during the 2026-05-28 → 2026-05-29 sessions. Use this as quick context for new sessions before reading code.

## Project state

- LiquidBounce fork running on Minecraft 1.26.2 (was 1.21.4).
- Build: `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH ./gradlew --no-daemon assemble -x detekt` — output: `build/libs/liquidbounce-0.31.2.jar`.
- Skip `detekt` (style checks unrelated to runtime).
- Reference template (do not edit, read-only): `AnotherProject/WorkSpace/LiquidBounce-1.26.2-CCBLUEX/`.
- Grim source for module audits: `AnotherProject/Grim/`.
- Path map / module catalog: `Skill/System_path_map_EN.md`. Grim checks catalog: `Skill/GrimAC_Checks_Catalog.md`.

## Working agreement

- Workflow rule from `Skill/Agen.md`: investigate before patching. Restate symptom → read related code/log → form hypothesis tied to evidence → patch narrowly → verify with build (and in-game when needed). Don't guess from memory; read the file. Final response uses the `Checked / Root cause / Changed / Verification / Test` shape.
- User language style: terse, direct. Confirms when things work. Mixes Vietnamese.
- User tests on FCL (Android Minecraft launcher). Logs land in `AnotherProject/debug/latest_game.log`. Screenshots/videos appear in same folder.
- Don't add modules or features unless asked. Surgical edits only.

## Render layer (1.26.2 GPU API) — fixed bugs

The render layer was thoroughly broken because the previous Claude/Codex compat layer stubbed too many methods. State of fixes:

### `src/main/kotlin/net/ccbluex/liquidbounce/render/compat/RenderCompat.kt`
1. **Color modulator was lost** — `setShaderColor` was a no-op; modules using `withColor { drawLines(...) }` got white/colorless geometry. Fix: track `shaderColor: Vector4f` and write it to the `DynamicTransforms` UBO inside `CompatMeshSubmitter.drawUploaded`.
2. **`mapBuffer` inside open render pass** — `getDynamicUniforms().writeTransform(...)` was called *while* a `RenderPass` was open; 1.26.2 GPU API forbids that. Fix: compute the transform slice **before** opening the pass, then use the cached slice inside.
3. **POSITION pipeline missed Fog UBO** — `core/position.fsh` calls `apply_fog(ColorModulator, ...)`, which reads from the Fog UBO. The pipeline declared only `MATRICES_PROJECTION_SNIPPET`. Fix: use `MATRICES_FOG_SNIPPET` for POSITION format. `bindDefaultUniforms` then auto-binds the current shader fog. Without this, nametag backgrounds rendered white instead of black.

### `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/render/MixinGameRenderer.java`
4. **WorldRenderEvent at TAIL of renderLevel was too late** — the matrix stack was no longer the level matrix. Fix: hook at FIELD GETFIELD on `CameraEntityRenderState.isSleeping` and build a fresh `PoseStack` from the local `modelViewMatrix`.
5. **WorldToScreen matrices** — added a separate `extractCamera` TAIL hook that pushes `cameraState.projectionMatrix`, `viewRotationMatrix`, and `cameraState.pos` into `WorldToScreen.setMatrices(...)`. Use the **raw** camera projection — not the local `projectionMatrix` from `renderLevel` (which has bob/portal multipliers applied).

### `src/main/kotlin/net/ccbluex/liquidbounce/utils/render/WorldToScreen.kt`
6. Replaced the broken `WorldRenderEvent` handler that read `RenderSystem.getProjectionMatrix()` (returned identity) with a `setMatrices` entry point matching the upstream template. Stores `projModelViewMatrix = projection × view` and `cachedCameraPos`. `calculateScreenPos` does `(pos - camPos).mulProject(projModelViewMatrix)` then NDC → screen.

### 2D ESP / Nametags / ItemTags — `matrices.translate` was a no-op
7. **`DrawContext.matrices` is a fake `PoseStack`** stored in a `WeakHashMap` (`utils/entity/GuiAndItemCompatExtensions.kt:38`). Vanilla 1.26.2's `GuiGraphicsExtractor` uses `Matrix3x2fStack pose`, which never reads our stack. Calling `matrices.translate(x, y, z)` does nothing.
8. **`newDrawContext()` is throwaway** — creates a `GuiGraphicsExtractor(mc, GuiRenderState(), 0, 0)` whose `GuiRenderState` is never flushed by vanilla's `GuiRenderer`. Drawing into it goes nowhere.
9. Fix pattern: use `event.context` from `OverlayRenderEvent` (the real GUI context) and pass **absolute** screen coords to `fill`/`drawHorizontalLine`/`drawVerticalLine`/`drawItem`. Removed `translate(x, y) + draw at (0, 0)` patterns.
10. Fixed in: `Esp2DMode.kt`, `ModuleItemTags.kt` (`drawItemTags`), `NametagRenderer.kt` (`drawItemList` — now takes `DrawContext` arg from caller `ModuleNametags.drawNametags`).
11. **Limitation:** `dc.drawItem` doesn't honor `Matrix3x2fStack` scale via our compat path either; per-item scaling beyond native 16×16 needs a deeper rewrite using `dc.pose().pushMatrix().scale()`.

### Notes
- Render package upstream port (Phases 1–6 of triage) is **not** done. The compat layer is patched enough to work, but proper Render2D/Render3D/ClientRenderPipelines port from template would be cleaner long-term.
- KillAnimation work was completed in a previous session (per old `CLAUDE_CONTINUE_KILLANIMATION.md` notes).

## Sprint module fixes
- `ModuleSprint.kt`: gated LEGIT mode on `forwards && !backwards` (vanilla only sprints with W) AND on `!horizontalCollision || ignoreCollision`. Without these, Grim's SprintE/Simulation flagged sprint-into-wall.
- Grim Sprint check audit lives in `Skill/GrimAC_Checks_Catalog.md`.

## NoSlow
- Deleted old `ModuleNoSlow` and `ModuleTest` (legacy implementations).
- Re-ported VCore `MoveFix.NoSlow`'s **GrimTick** mode only as `ModuleNoSlow.kt` (Movement). Counts ticks while `isUsingItem`, ready after 2, `canNoSlow()` consumes the window. Hooked into `MixinClientPlayerEntity.modifyInput` → `isUsingItem` ModifyExpressionValue.

## InventoryMove StopOnAction (1.26.2 fix)
- Symptom: Grim flagged MultiActionsC/D when clicking inventory while Sprint+InventoryMove active.
- Root cause: 1.26.2 added `PLAYER_INPUT` packet carrying explicit sprint bit. Zeroing input alone wasn't enough — sprint stayed true.
- Fix in `ModuleInventoryMove.kt`: when StopOnAction queues a container packet, set `stopOnActionPending` and hold the click for ONE full game tick (`pendingFlushTicks = 1`). New `sprintSuppressHandler` (FINAL_DECISION) forces `event.sprint = false` on the SprintEvent.INPUT during that tick. New `tickFlushHandler` flushes the queued packet on the NEXT tick — by then `STOP_SPRINTING` and empty `PLAYER_INPUT` have hit the wire.

## TargetStrafe — VCore port
- Deleted old `ModuleTargetStrafe.kt` (Dreamcore Collision-only).
- Re-ported VCore `TargetStrafe` with two modes: `Default` (orbit around target with safety check for lava/fire/cobweb/void) and `Collision` (push toward target).
- Module structure follows project pattern: `combat/targetstrafe/ModuleTargetStrafe.kt` declares `choices("Mode")`; mode-specific settings live on each `Choice` subobject (`TargetStrafeDefault`, `TargetStrafeCollision`). User noted: settings must be per-mode, not all at once.

## AntiBot — VCore port (full replace)
- Deleted the upstream 4-mode design (Custom/Matrix/IntaveHeavy/Horizon + `IAntiBotMode`) and the `modes/` folder.
- Re-ported VCore `combat/AntiBot.java` single "Matrix" heuristic into `misc/antibot/ModuleAntiBot.kt`: a `RemotePlayer` showing visual armor but with no inventory armor (or `armorValue == 0`) is flagged. Settings: `Remove` (default off, deletes the entity via `world.removeEntity(id, KILLED)`), `OnlyAura` (only checks `ModuleKillAura.targetTracker.target`), `Notify`.
- Kept the public contract intact: `isBot(Entity)` + `TagEntityEvent.ignore()` + `WorldChangeEvent` reset, so `NametagTextFormatter.kt:74`, combat targeting, and the `ClientModule.kt:74` special case still work.
- Caveat: remote players' `inventory.armor` is never synced client-side, so `invArmor` is effectively always false — the heuristic flags most armored players. Faithful to VCore; tune if false positives appear.

## Aura rotations (Track, Grim) + LockTarget — VCore port
- Mapped VCore's `aura/rotation/{Track,Grim}.java` onto the fork's existing aim architecture instead of porting `AuraRotationManager` verbatim. The fork already separates aim-point selection (`PointTracker`) from rotation smoothing (`AngleSmooth` choices) and applies GCD/sensitivity automatically in `Rotation.normalize()` (`RotationUtil.gcd`).
- `Track` → `utils/aiming/.../anglesmooth/impl/TrackAngleSmooth.kt` (a `FactorAngleSmooth`). Yaw = random step in `MinYawStep..MaxYawStep`; pitch accelerates (`AimedPitchStep` when already facing target via `facingEnemy`, else `prev*PitchAccelerate` capped at `MaxPitchStep`). Pitch-accel field only mutated on the live path (`rotationTarget != null`); sim path (`calculateTicks`) uses `maxPitchStep` so it can't corrupt state.
- `Grim` → `GrimAngleSmooth.kt` (a raw `AngleSmooth`). Adds ±`Jitter`° (default 1) to target yaw/pitch then snaps (`towardsLinear 180/180`); `calculateTicks` = 1. GCD handled by engine, NOT duplicated.
- Both registered in `RotationsConfigurable.kt` gated on `combatSpecific` (same as Interpolation/Minarai) so they only show for combat rotations.
- `LockTarget` → `KillAuraTargetTracker.kt` (`val lockTarget by boolean(..., true)`) + `ModuleKillAura.updateTarget()`: when on, keep current target while it's in the in-range candidate list and still passes `processTarget` (which fires the rotation), else fall through to normal best-target selection. Deviation from VCore: does not force-switch on `Priority.DIRECTION` (VCore's `Sort.FOV`) — kept simple.

## Deleted modules
- `ModuleBedPlates` (user request).
- Old `ModuleNoSlow`, `ModuleTest` (replaced).
- AntiBot modes `CustomAntiBotMode`/`MatrixAntiBotMode`/`IntaveHeavyAntiBotMode`/`HorizonAntiBotMode` (replaced by VCore port).
- Exploit modules (user request): `ModuleDisabler` (+ `disabler/` folder), `ModuleServerCrasher` (+ `servercrasher/` folder), `ModuleAntiReducedDebugInfo`, `ModuleClip`, `ModuleNameCollector`, `ModulePortalMenu`, `ModuleVehicleOneHit`, `ModuleNoPitchLimit`, `ModuleSleepWalker`. Also removed their `ModuleManager` registrations and the mixin hooks that powered them: `hookNoPitchLimit` (`MixinEntity`), `hookNetherClosingScreen` (`MixinClientPlayerEntity`), `injectReducedDebugInfo` (`MixinPlayerEntity`) — each reverted to vanilla behavior.

## Module audit catalog
- `Skill/GrimAC_Checks_Catalog.md` — full catalog of GrimAC checks (Aim, BadPackets A–Z, Breaking, Combat, Crash A–I, Elytra A–I, Exploit, MultiActions A–G, PacketOrder A–P, Sprint A–G, Timer, Misc, Prediction). Use this to audit any client module against Grim's flag conditions.

## ClickGUI premium rewrite (2026-05-31 session) — SDF shader + blur + custom font

Goal was: ClickGUI looked "plastic", no rounded corners. Did a full GPU-shader rewrite. **All landed and confirmed working in-game.**

### Custom `.fsh` shader loading on 1.26.2 (NEW capability — first real custom shader on this fork)
- `gpuDevice.precompilePipeline(pipeline, ShaderSource)` is the 1.26.2 mechanism (verified in bytecode `com/mojang/blaze3d/systems/GpuDevice.class`). A `ShaderSource` impl resolves `liquidbounce:shader/{type}/{name}` ids to GLSL strings read from resources.
- `MixinShaderManager` (`src/main/java/.../injection/mixins/minecraft/render/MixinShaderManager.java`) injects at TAIL of `ShaderManager.apply(Configs, ResourceManager, ProfilerFiller)` and calls `RoundedRectShader.INSTANCE.precompile()`. Registered in `liquidbounce.mixins.json` client array as `minecraft.render.MixinShaderManager`.
- accesswidener ALREADY exposes `GuiGraphicsExtractor.guiRenderState/scissorStack`, all `RenderPipelines.*_SNIPPET`, `RenderPipeline.Builder.withSnippet` — no AW edits were needed.

### SDF rounded-rect (fixes "plastic, no rounded corners")
- Root cause: `ExosGuiRenderer.roundedRect/roundedGradientRect/roundedOutline` all early-returned via `drawContextForMatrices(matrices)?.let { context.fillGradient(...); return }`. That branch ALWAYS fired → `radius` dropped (square corners), `fillGradient` only takes 2 colors (no 4-corner gradient), open-animation scale lost. The tessellator-based rounded geometry below was dead code.
- Fix: `src/main/resources/resources/liquidbounce/shaders/rounded_rect/rounded_rect.{vsh,fsh}` — SDF rounded-box (anti-aliased fill + stroke), per-vertex `HalfSize`/`RadiusStroke` ivec2 fixed-point (px×16) so one pipeline does fill/outline/gradient with no per-element UBO.
- `src/main/kotlin/.../render/gui/RoundedRectShader.kt` — `ShaderSource` impl + custom `VertexFormat` + `RenderPipeline` (GUI snippet, QUADS, TRANSLUCENT) + `precompile()`.
- `src/main/java/.../render/gui/RoundedRectGuiElementRenderState.java` — vanilla `GuiElementRenderState` record submitted via `addVertexWith2DPose(...).setUv().setColor().setUv1().setUv2()`.
- `src/main/kotlin/.../render/gui/RoundedRectGui.kt` — `GuiGraphicsExtractor.drawRoundedRect/drawRoundedOutline` extensions; captures scissor+pose+bounds the vanilla GUI way.
- `ExosGuiRenderer.kt` rewritten: routes everything through SDF, signatures unchanged so `ClickGuiScreen`/`HudElement` callers untouched. `ringSegment` = tiled rounded-rects.
- **KEY ARCHITECTURE NOTE**: custom GUI shaders MUST go through `context.guiRenderState.addGuiElement(GuiElementRenderState)` (the batched vanilla flush path — correct scissor/z/projection), NOT the `CompatMeshSubmitter` RenderPass hack (draws during `extractRenderState`, composites wrong). Same reason ESP/nametags use `event.context`.

### Frosted-glass blur (Phase 2)
- The OLD blur stack is DEAD on 1.26.2 (`Shader.kt`/`FramebufferShader.kt`/`BlitShader.kt` = GL20/30; `RenderTarget.colorAttachment` stubbed to 0). Did NOT revive it.
- Instead used vanilla's built-in `GuiRenderState.blurBeforeThisStratum()` + `nextStratum()` — the official 1.26.2 menu-blur path (Pause/Options use it). `ClickGuiScreen.extractRenderState` calls `nextStratum()` before drawing the panel; vanilla blurs the world stratum behind.
- CRASH FIX: calling `blurBeforeThisStratum()` ourselves throws `IllegalStateException: Can only blur once per frame` because Fabric Screen API's `extractBackground` already calls it. So we only call `nextStratum()`.

### Custom Inter font (resolution-aware)
- `src/main/java/.../render/gui/GlyphGuiElementRenderState.java` + `src/main/kotlin/.../render/gui/CustomFontGui.kt`: `GuiGraphicsExtractor.drawCustomFontText` drives `FontManager.FONT_RENDERER` (Inter) to fill its glyph cache, drains via reflection, submits per-glyph quads through `guiRenderState.addGuiElement` with `TextureSetup.singleTexture(view, sampler)` from the glyph atlas `DynamicTexture`.
- `ExosGuiRenderer.text` gates: `mc.window.guiScale * size >= 22f` + glyph manager ready → Inter; else fallback vanilla `mc.font` (sharp at low GUI scale). try/catch → vanilla on any failure.
- `FontManager.glyphManagerOrNull()` added (non-throwing).
- Caveat: underline/strikethrough dropped in the new path (ClickGUI/HUD don't use them).

### Removed per user feedback
- Glow behind active rows + inner-highlight + drop-shadow ("khói smoke") were added then REMOVED — they tanked FPS (glow = 3 SDF quads × every enabled module/frame). Current state: SDF corners + 4-corner gradient + sharper panel outline (`0x4F`) + real blur + Inter font. No glow/shadow.

## ChunkFinder / SusChunkFinder (DELETED at end of session)
- Ported full 1:1 from Kryptic 1.21.11 (`AnotherProject/WorkSpace/Kryptic[1.21.11].jar`, decompiled to `~/.cache/kryptic_decomp/`) for DonutSMP base-raiding. Tick-driven BFS scan (radius 12), 9 detectors (rotated deepslate, long dripstone/vine, full kelp, diorite/obsidian vein, amethyst clusters, rails, cluster bypass) + SusChunkFinder (exposed-amethyst). Render Plate Y=60 / Volume modes via `BoxRenderer.drawWith`.
- **User had me DELETE both modules** (`features/module/modules/render/chunkfinder/` folder gone, 2 ModuleManager entries removed). Decompiled source + recon still in `~/.cache/kryptic_decomp/` if re-porting. Behavior was correct; user just didn't want them.

## JumpCircle — VCore skid, FIXED (2026-05-31)
- Re-skidded `ModuleJumpCircle.kt` from VCore (`AnotherProject/WorkSpace/VCore-0.8-main/.../render/JumpCircle.java`): tracks players leaving ground, spawns circle, 2-pass glow boost (alpha×0.65 + alpha×1.0), modes Default/Portal/Custom, default color WHITE (no rainbow per user), `Custom` loads `<gamedir>/liquidbounce/jumpcircle/circle.png`.
- Textures copied to `src/main/resources/resources/liquidbounce/{vcore,thunderhack}/particles/{circle,hit_bubble/hitbubble}.png`.
- **CRASH FIXED**: `ModuleKillAnimation` (NOT JumpCircle) crashed startup with `ExceptionInInitializerError` at `ModuleManager.kt` register line — it referenced `thunderhack/particles/circle.png` + `hitbubble.png` which had gone MISSING (empty dir). `registerAsDynamicImageFromClientResources()` does `getResourceAsStream(...)!!` → NPE in static init. Restored those PNGs (RGBA).
- **BLACK-SQUARE BUG FIXED (rebake-texture path chosen)**: circle rendered on an opaque black square. Cause: `vcore/particles/{circle,hit_bubble}.png` were RGBA but **alpha=255 on every pixel** — the circle was RGB luminance (white-on-black) made for ADDITIVE blending (`SRC_ALPHA, ONE`), but the compat `RenderSystem.blendFunc(...)` is a NO-OP (`RenderCompat.kt:84`), so the `POSITION_TEX_COLOR` pipeline always uses normal translucent alpha → opaque black stays. Fix: rebaked both PNGs in-place (pure-Python codec; no Pillow on Termux) so **luminance → alpha channel, RGB → white**; corner now (255,255,255,0). Then changed `ModuleJumpCircle.kt:147` blend request from `SRC_ALPHA/ONE` to `SRC_ALPHA/ONE_MINUS_SRC_ALPHA` so the code is honest (won't break the rebaked texture if compat `blendFunc` is ever made real). Originals backed up to `~/.cache/jumpcircle_orig/`. Build + jar-content alpha verified.
- **IMPORTANT — texture format gotcha**: `NativeImage.read()` on 1.26.2 REJECTS grayscale PNGs (colorType 0) → `ExceptionInInitializerError` on module static init. Any texture fed to `registerAsDynamicImageFromClientResources` MUST be RGBA (colorType 6). Check with `od -An -tu1 -j25 -N1 file.png`. The rebake preserves colorType 6.

## TargetESP — same black-square texture bug, FIXED (2026-05-31)
- `ModuleTargetESP.kt` has 4 modes: Dreamcore (glow billboards), RichCircle, RichGhosts, RichCube. Same opaque-black-square symptom as JumpCircle on **RichGhosts** and **RichCube (default Capture=1)**.
- **RichCircle is unaffected** — it's pure `POSITION_COLOR` geometry (no texture), so it never had the bug.
- Diagnosed each `rich/` texture by decoding alpha: `bloom.png` (RichGhosts) and `capture1.png` (RichCube default) were RGBA but **alpha=255 everywhere** (white-on-black additive art). `capture3.png` was **colorType 2 (RGB, no alpha channel at all)** → loads fully opaque. `capture2/4/5.png` already had a real alpha channel and render fine.
- Fix (same as JumpCircle): rebaked **only the 3 broken** PNGs in place (`bloom`, `capture1`, `capture3`) → luminance→alpha, RGB→white, all output colorType 6 (capture3 needed RGB→RGBA conversion). Left capture2/4/5 untouched (surgical). Changed both `blendFunc(SRC_ALPHA, ONE)` requests (RichGhosts ~L402, RichCube ~L439) to `SRC_ALPHA/ONE_MINUS_SRC_ALPHA`. Originals backed up to `~/.cache/targetesp_orig/`. Build + jar-content alpha verified.
- Server is **survival/semi-anarchy economy + raid base** (per `Skill/DONUT.md`, `Skill/AstroAc.md`). Anti-cheat = **AstroAC** (SaaS cloud, dashboard+replay) + **Grim Simulation** bolted on. Has `/sus` command: mod TPs to flagged players in Spectator to manually verify.
- Sign/translation-key detection (MC-265322) exists but per user only targets Meteor, not LiquidBounce — empirically LB hasn't been client-blocked there.
- Use-case is **raid base** → anti-ESP makes player ESP useless; ChunkFinder/SusChunkFinder-style chunk-anomaly scanners are the play (100% client-side, no packets, AstroAC can't see them; plate render is client-only so `/sus` spectate won't reveal it).

## Environment gotchas (this session)
- `grep`/`ugrep` binary is BROKEN (`ugrep: ... libc.so: invalid ELF header`). Use `awk '/pattern/{print NR": "$0}'` or the dedicated Grep tool instead of shell grep.
- `./gradlew runClient` does NOT work on Termux (missing native libs: libc.so.6 via JNA, liblwjgl.so). Build with `assemble` only; user tests the jar on FCL (Android).
- Build cmd: `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH ./gradlew --no-daemon assemble -x detekt`. After deleting files, use `clean assemble` (Gradle's up-to-date check misses deletions). Verify jar contents with `python3 -c "import zipfile; ..."` since grep is broken.
- Crash logs: `AnotherProject/debug/latest_game.log` (can be 100s of MB if spamming — read tail only). Real crash trace with `Caused by` is in `/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/logs/latest.log` and crash-reports/. NOTE: the fork's ErrorHandler often logs `Error: null` and swallows the real cause — diagnose from the `ModuleManager.kt:<line>` register line (line number = which module's static init threw).

## Outstanding TODOs
- `Sprint.Ignore.BLINDNESS` toggle reads but no matching mixin exists for SprintD (BLINDNESS) flag — only HUNGER and COLLISION are wired. Module will still flag if you sprint into blindness. Add `hookSprintIgnoreBlindness` similar to existing toggles when needed.
- BlockOutline still subtracts `event.camera.position()` in module code. After the WorldRenderEvent matrix fix, this is consistent with other modules; user said it works correctly now.
- Render layer structural migration (Render3D/Render2D port from template) — not started. Compat layer patches are sufficient for current modules.

## File-quick-reference

| Concern | File |
|---|---|
| Compat layer (state, pipelines) | `src/main/kotlin/.../render/compat/RenderCompat.kt` |
| WorldToScreen | `src/main/kotlin/.../utils/render/WorldToScreen.kt` |
| GameRenderer hooks | `src/main/java/.../injection/mixins/minecraft/render/MixinGameRenderer.java` |
| GUI Extractor / overlay event | `src/main/java/.../injection/mixins/minecraft/gui/MixinInGameHud.java` |
| Sprint | `src/main/kotlin/.../features/module/modules/movement/ModuleSprint.kt` |
| NoSlow (GrimTick) | `src/main/kotlin/.../features/module/modules/movement/noslow/ModuleNoSlow.kt` |
| InventoryMove | `src/main/kotlin/.../features/module/modules/movement/inventorymove/ModuleInventoryMove.kt` |
| TargetStrafe | `src/main/kotlin/.../features/module/modules/combat/targetstrafe/` |
| ESP 2D | `src/main/kotlin/.../features/module/modules/render/esp/modes/Esp2DMode.kt` |
| Nametags | `src/main/kotlin/.../features/module/modules/render/nametags/` |
| ItemTags | `src/main/kotlin/.../features/module/modules/render/ModuleItemTags.kt` |
| SDF shader (GLSL) | `src/main/resources/resources/liquidbounce/shaders/rounded_rect/rounded_rect.{vsh,fsh}` |
| RoundedRect ShaderSource+pipeline | `src/main/kotlin/.../render/gui/RoundedRectShader.kt` |
| RoundedRect GUI element + draw API | `src/main/kotlin/.../render/gui/RoundedRectGui.kt`, `.../render/gui/RoundedRectGuiElementRenderState.java` |
| MixinShaderManager (precompile hook) | `src/main/java/.../injection/mixins/minecraft/render/MixinShaderManager.java` |
| Custom Inter font GUI path | `src/main/kotlin/.../render/gui/CustomFontGui.kt`, `.../render/gui/GlyphGuiElementRenderState.java` |
| ExosGuiRenderer (GUI primitives) | `src/main/kotlin/.../features/module/modules/render/gui/ExosGuiRenderer.kt` |
| ClickGUI screen (blur stratum here) | `src/main/kotlin/.../features/module/modules/render/gui/ClickGuiScreen.kt` |
| JumpCircle (VCore skid, has bug) | `src/main/kotlin/.../features/module/modules/render/ModuleJumpCircle.kt` |
| Skin face GUI draw helper | `src/main/kotlin/.../render/gui/SkinFaceGui.kt` |
| HUD Manager (element registration) | `src/main/kotlin/.../features/module/modules/render/gui/hud/HudManager.kt` |
| HUD elements (TargetHud, Watermark, PotionHud) | `src/main/kotlin/.../features/module/modules/render/gui/hud/HudElement.kt` |
| ModuleHUD (toggle settings + render handlers) | `src/main/kotlin/.../features/module/modules/render/ModuleHUD.kt` |

## HUD Rewrite (2026-06-05 session)

### Elements stripped / kept
- **HudManager.initialize** now registers only: `TargetHudElement`, `SylentWatermarkElement`, `PotionHudElement`.
- All other element classes remain in code but are NOT instantiated — they don't render, don't appear in editor.
- **ModuleHUD** toggle settings: all removed except `targetHud`. The others were inert (their `findElement` returned null).

### Rendering stack (shared by all 3 HUD elements)
- **Inter font forced** via `ExosGuiRenderer.textInter()` — bypasses the 22px MIN_CUSTOM_FONT_PIXELS gate. Falls back to vanilla only if glyph manager isn't ready.
- **Real Matrix3x2fStack** (`context.pose()`) — proven push/translate/scale/rotate/pop pattern from `MixinHandledScreen.java:276`. The fake PoseStack (`context.matrices`) is a no-op for positioning on 1.26.2.
- **Soft glow** — 3 layered low-alpha `roundedOutline` rings just outside the panel.
- **Bold bright outline** — `0xAAFFFFFF` at 1.6px stroke.
- **Dark panel** — `0xE8080808` fill with rounded corners.
- **Scissor clipping** — `ExosGuiRenderer.enableScissor/disableScissor` (proven from ClickGuiPanel).

### TargetHudElement
- **Two-box layout**: detached avatar square (left, AV=46px) + gap (5px) + info panel (right, 138px).
- **Real player skin face** via `SkinFaceGui.kt` (`GuiGraphicsExtractor.drawSkinFace`). Draws face+hat UVs through `GlyphGuiElementRenderState` + `TextureSetup.singleTexture(view, sampler)`. Falls back to letter-on-black for mobs/null.
- **Bounce + red hurt flash**: on rising edge of `target.hurtTime`, the avatar tile scales up 14% and lifts 4px via `pose().pushMatrix/translate/scale/popMatrix`. A red wash overlay decays over 420ms. Frame outline flushes red.
- **Corner ticks** on the avatar frame.
- **Health bar**: fatter pill (7px, fully rounded), white fill eases DOWN gradually on damage (8%/frame lerp — not instant snap), snaps up on heal. Yellow absorption (`target.absorptionAmount`) stacks to the RIGHT of white health.
- **Name/HP overlap fix**: HP measured first, name truncated with ellipsis to remaining width.
- **Diagonal sheen** on the info panel: a gradient band (transparent→bright→transparent) rotated to the panel diagonal angle, sweeping bottom-left → top-right over 3.4s, scissor-clipped.

### SylentWatermarkElement
- Long rounded pill bar: `SYLENT  •  username  •  1  •  HH:mm:ss (GMT+7)`.
- **No sheen/ripple** — clean flat dark bar per user request.
- Text size 12 fills the bar, auto-sizes width.
- Bold bright outline + soft glow.

### PotionHudElement
- Shows active potion effects (up to 8 rows) in a dark panel.
- Each row: colored dot (effect's actual color) | name+amplifier | duration timer (right-aligned).
- Auto-sizes width/height based on effects present. Shows "No Effects" when empty.
- Same rendering stack: dark panel, bold outline, glow, Inter font.
- No sheen/ripple.

### HUD disappears in chat — FIXED
- **Root cause**: `ModuleHud.renderHandler` (on `OverlayRenderEvent`) bailed out with `mc.screen is ChatScreen` and deferred to `chatScreenRenderHandler` (on `ScreenRenderEvent`). But `ScreenRenderEvent` is **defined but never fired by any mixin**. So the HUD vanished in chat.
- **Fix**: folded the editor rendering into the overlay handler (which fires during chat). When chat is open, renders with selection highlight; otherwise renders preview. Removed the dead `chatScreenRenderHandler` + `ScreenRenderEvent` import.

### drawItem broken (items render at top-left) — FIXED
- **Root cause**: `HudElement.drawItem` used the fake PoseStack (`context.matrices.translate/scale`) which is a no-op. Items always rendered at (0,0).
- **Fix**: rewrote to use real `context.pose()` for scale transform + pass absolute screen coords to `context.drawItem/drawStackOverlay`.

### New helpers added
- `SkinFaceGui.kt` — `GuiGraphicsExtractor.drawSkinFace(skinId, x, y, size, argb)`: draws player skin face+hat through the batched GUI render path.
- `ExosGuiRenderer.textInter(matrices, text, x, y, color, size)` — forces Inter font at any size.
- `ExosGuiRenderer.enableScissor/disableScissor(matrices, ...)` — scissor clip helpers.

## HUD Potions + theme color + glow shader (2026-06-05 session)

### Single HUD theme color
- `ModuleHud` now has `color("Color", Color4b white)` setting. Public accessor `ModuleHud.themeColor: Int` (ARGB, alpha forced 255). All three HUD elements derive gradients/shimmer from this one selection (default `#FFFFFF`).
- `ExosGuiRenderer.shade(color, factor)` — returns a darker shade of `color` in the SAME hue (scales RGB by factor, keeps alpha). Used for monochrome dark→bright gradients so a flat white reads dim-white→bright-white, not one tone.

### Bespoke GUI glow shader (NEW pipeline — second custom GUI shader on this fork)
- `src/main/resources/resources/liquidbounce/shaders/glow_rect/glow_rect.{vsh,fsh}` — SDF rounded-box OUTER glow. Same per-vertex packing as `rounded_rect` (HalfSize + ivec2), but `RadiusStroke.y` carries the glow SPREAD (px) and the quad is CPU-expanded by spread. Fragment fades SDF distance outward (`exp` core × `smoothstep` tail), mild Reinhard tonemap for a Complementary-Unbound-ish bright edge.
- `render/gui/GlowRectShader.kt` (ShaderSource + pipeline, **ADDITIVE blend** so layers bloom), `render/gui/GlowRectGuiElementRenderState.java` (record, encodes spread in `setUv2.y`), `render/gui/GlowRectGui.kt` (`GuiGraphicsExtractor.drawGlowRect(x,y,w,h,radius,color,spread)` — expands bounds by spread).
- Precompiled in `MixinShaderManager` alongside RoundedRect (`GlowRectShader.INSTANCE.precompile()`).
- `ExosGuiRenderer.shaderGlow(matrices, x,y,w,h,radius,color,spread)` — routes through the shader, try/catch fallback to the old layered-outline `glow()`.
- NOTE for user: ComplementaryUnbound (in Debug/Shaders) is a world-space Iris/OptiFine pack — it physically can't drive a 2D GUI frame (no gbuffer for a HUD widget). The glow shader is bespoke and only borrows its brightness/tonemap *feel*.

### PotionHudElement rewrite (`HudElement.kt`)
- **Title separator**: "Potions" title + a long monochrome bar beneath it (dim→bright→dim gradient halves of theme color). HEADER_H bumped to 24f.
- **Shimmer/glint**: `drawTitleShimmer` sweeps a narrow bright theme-color band L→R across the title (scissor-clipped), then redraws crisp title on top. ~2.6s period.
- **Built-in effect icons (no manual assets)**: `Gui.getMobEffectSprite(effect.effectType)` (effectType is `Holder<MobEffect>` via `MojangCompatExtensions.kt:486`) → `context.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, x,y,13,13, argb)`. `DrawContext` is a typealias for `GuiGraphicsExtractor` (`src/main/kotlin/net/minecraft/client/gui/DrawContext.kt`), so blitSprite resolves directly. Old colored dot removed (fallback theme square only if sprite fails).
- **Slide-down animation**: `rowAnims: HashMap<descriptionId, RowAnim(firstSeen)>`. New effect slides down (easeOutQuad over 260ms) + fades in; rows scissor-clipped to panel.
- **Blink on expire**: when `effect.duration in 0..60` ticks, row alpha oscillates via `sin`.
- **Red level number**: amplifier (`+1`) drawn in `0xFFFF4D4D` right after the name; name stays theme color.
- Panel outline now a dark→bright vertical gradient of theme + shader glow halo (alpha 70).

### TargetHud health bar
- Flat white fill replaced with monochrome dark→bright gradient of `ModuleHud.themeColor` (`shade(theme,0.4f)`→theme). Drop/heal easing + yellow absorption unchanged.

### Verified
- `assemble -x detekt` BUILD SUCCESSFUL → `liquidbounce-0.31.2.jar`. Jar contains glow_rect.{fsh,vsh} + GlowRect* classes. Not yet in-game tested by user (FCL).

## PotionHud render refinement (2026-06-05 session 2) — from screenshot + reference video

User feedback after first pass: glow/outline too thick & strong, separator had a center seam (two halves), and wanted the title shimmer replaced with a moving monochrome wave. All in `HudElement.kt` `PotionHudElement`.

- **Glow/outline lightened**: `shaderGlow` dropped from alpha 70/spread 7 → **alpha 30/spread 4**. Removed the old "gradient-fill rect + re-stroke dark panel over interior" trick (that was the extra dark covering ring the user disliked) and replaced with a SINGLE `roundedOutline` hairline (`withAlpha(theme,140)`, 1px).
- **Separator is now ONE continuous line**: previously two `roundedGradientRect` halves (dim→bright + bright→dim) meeting at center → visible seam. Now a single solid `roundedRect` bar (`withAlpha(theme,200)`, 1.6px). A 4-corner gradient can't center-peak on one quad, so tonal variation lives in the title wave instead, not the line.
- **Title shimmer → moving monochrome wave** (`drawTitleShimmer` deleted, `drawTitleWave` added): redraws "Potions" in **16 vertical column strips**, each scissor-clipped and tinted via `ExosGuiRenderer.shade(theme, f)` where `f` = `0.35..1.0`. A travelling raised-cosine crest (`sin(t*PI/2)`, arc-eased) sweeps the bright spot L→R over 3s and **wraps** (signed distance folded to [-0.5,0.5]), giving a continuous dark→bright→dark flowing wave (the "semi-circular sweep" the user described). Strips overlap +0.6px to avoid int-scissor seams. No `roundedGradientRect` glint band anymore.
- `ExosGuiRenderer.shade()` (monochrome hue-preserving darkener) is the key primitive for both the wave and the TargetHud health bar.
- Build: `assemble -x detekt` BUILD SUCCESSFUL → jar. Not yet FCL-tested by user.

## PotionHud exit anim + TargetHud overhaul (2026-06-05 session 3)

All in `HudElement.kt`.

### PotionHud
- **Outline/glow thinned again** per feedback (still too thick/white): outline `withAlpha(theme,140)`/1px → **`60`/0.75px**; `shaderGlow` `30`/spread4 → **`16`/spread3**.
- **Reverse exit animation added** (mirror of the slide-down entry). `RowAnim` now carries `removeStart` + `lastEffect`; rows are NOT pruned on effect-end — instead they slide UP + fade out over `EXIT_MS=240ms` (easeInQuad `easeExit`) then drop. Render builds rows from `rowAnims` (a `LinkedHashMap`, entry order) not the live list; a re-appearing effect cancels its exit. Panel height eases via an `occupancy` accumulator (exiting rows collapse their slot fraction). `drawEffectRow` was refactored to take absolute `rowY` + explicit `rowAlpha` instead of `index`.

### TargetHud — info fixes
- **Real player name**: was `target.type.description.string` → always "Player". Now `when { target is Player -> target.gameProfile.name; customName != null -> ...; else -> type.description }`. (`Player.getGameProfile().getName()`, mapping `gameProfile.name`.)
- **Skin fallback (no more purple face)**: `drawTargetFace` checks `target.skin.secure()` (PlayerSkin.secure() = authenticated). If insecure/missing → `DefaultPlayerSkin.get(target.uuid).body().id()` (guaranteed Steve/Alex by UUID) instead of the missing-texture sprite. `.body()` returns a `ClientAsset.Texture`; `.id()` (base `ClientAsset`) → `Identifier`. Imports `net.minecraft.client.resources.DefaultPlayerSkin`. NOTE: fork wrapper `AbstractClientPlayer.skinTextures.texture` (MojangCompatExtensions:613) doesn't expose secure(), so use raw `target.skin` (getSkin()) here.
- **Armor layout**: was slotSize 12 / item-scale 0.5 / computed gap (~16px, too loose+tiny). Now slotSize **15**, gap **2px**, item-scale **0.81**, sits `y+AV-slotSize-3`. Grouped left, larger, tight.

### TargetHud — SpringPop appear/disappear
- New state: `present/appearing/transitionStart/lastTarget/hudAlpha`. `render()` detects target present↔absent transitions, picks appear vs disappear curve, and wraps the whole element in a `pose.scale` about its centre; when disappear finishes it renders nothing and releases `lastTarget` (kept alive so the shrink can still draw the gone target).
- `springPop(p)` = `1 - 0.25·e^(-3.42p)·cos(3π p)` → 0.75 → ~1.08 → ~0.97 → 1.0 over `APPEAR_MS=360ms`. `springDrop(p)` = slight rise (`sin(πt)·0.06`) then shrink (`1-0.2t²`) over `DISAPPEAR_MS=240ms`. `hudAlpha` fades in/out and is threaded through every colour via `fade(color)` (multiplies the alpha byte). Body draws moved into `renderBody(context, target, now)`.
- CAVEAT: targeting falls back to `nearestPlayer()` when KillAura has no target, so on populated servers a target almost always exists → disappear anim mostly only shows in singleplayer / when truly no players nearby. Targeting logic left unchanged (not requested).
- Build: `assemble -x detekt` BUILD SUCCESSFUL → `liquidbounce-0.31.2.jar`. Not yet FCL-tested.

## TargetESP RichGhost — Shader/Bloom research (2026-06-07 session)

### Goal
Make TargetESP mode RichGhost **glow/bloom when Iris shaderpack is active** — like the reference video where a client's target ESP looks bright/chói with shader on.

### What was tried and results

1. **Additive blend (Cách A) — FAILED, REVERTED.**
   - Added `additiveBlend` flag to `RenderCompat.kt`, detected `SRC_ALPHA/ONE` in `blendFunc`/`blendFuncSeparate`, picked `BlendFunction.ADDITIVE` in `pipelineFor`.
   - Result: Ghost/RichGhost rendered as **opaque dark block** instead of glowing. Reason: ADDITIVE without HDR/bloom just saturates to ugly white on dark background. Also affected TargetESP other modes (shared pipeline). **Fully reverted.**

2. **Emissive RenderType (vanilla bufferSource path) — PARTIALLY WORKS, KEPT.**
   - Added `drawEmissiveBillboards()` helper in `ModuleTargetESP.kt` that draws billboard quads through `mc.renderBuffers().bufferSource().getBuffer(RenderTypes.entityTranslucentEmissive(texture))` + `endBatch()`.
   - RichGhost switched from `drawGlowBillboards` (CompatMeshSubmitter, bypasses Iris) → `drawEmissiveBillboards` (vanilla entity buffer, Iris patches this into gbuffer).
   - Result with Iris + Derivative Main shaderpack: **renders correctly (no crash, no ugly block)** but **NO bloom/glow effect**. The geometry appears but the shaderpack does NOT flag it for bloom.
   - This is because `entityTranslucentEmissive` RenderType is NOT automatically mapped to emissive/bloom by all shaderpacks. The Derivative Main pack uses `materialIDs` (from `entity.properties`) to decide bloom, NOT RenderType name.

### Root cause analysis (confirmed from source)
- Iris bloom works via: `gbuffers_textured.fsh` writes `materialIDs` to `colortex7.z`. The bloom downsample pass (`DownSample0.glsl`) reads `colortex5` (HDR scene) — pixels with high HDR value bloom. Emissive entities get high values because the shaderpack's lighting code treats specific `materialIDs` (mapped in `entity.properties`) as self-illuminating.
- `entity.properties` maps specific **entity types** (not RenderTypes) to material IDs: `lightning_bolt`, `slime`, `ender_pearl`, `experience_orb`, etc. Custom mod-rendered geometry using `entityTranslucentEmissive` RenderType does NOT get a special material ID → no bloom.
- The compat layer's `CompatMeshSubmitter` path (used by `drawGlowBillboards`) bypasses Iris gbuffer entirely — geometry goes straight to `mainRenderTarget.colorTextureView` via a custom RenderPass, outside of what Iris captures.

### Current state (emissive path KEPT in code)
- `ModuleTargetESP.kt` RichGhost uses `drawEmissiveBillboards` (emissive RenderType via vanilla bufferSource).
- Helper `drawEmissiveBillboards` + `emissiveVertex` added after `drawGlowBillboards`.
- Imports added: `RenderTypes`, `OverlayTexture`.
- Other modes (Dreamcore, RichCircle, RichCube) **unchanged** — still use old `drawGlowBillboards`/`drawCustomMesh` path.
- `RenderCompat.kt` is **clean** — additive patch fully reverted.
- Build: `assemble -x detekt` BUILD SUCCESSFUL.

### Next steps to actually get bloom
To make Iris bloom custom mod geometry, possible approaches (not yet implemented):
1. **Write to a specific colortex** that the shaderpack reads for bloom — requires Iris-specific API or writing a custom gbuffer shader program. Iris does NOT expose a public API for this.
2. **Use `eyes()` or `beaconBeam()` RenderType** — some shaderpacks flag these for bloom. Worth testing with Derivative Main.
3. **Write a companion shaderpack override** — add `entity.properties` entries or custom `gbuffers_textured` that flags LiquidBounce's emissive geometry. Requires shaderpack modification, not client code.
4. **Render into the world as an actual entity with emissive material ID** — complex, would require spawning fake entities or using entity rendering pipeline directly.

### File reference
| Concern | File |
|---|---|
| RichGhost emissive render | `src/main/kotlin/.../render/ModuleTargetESP.kt` (drawEmissiveBillboards helper + RichGhostsMode.render) |
| Compat layer (unchanged) | `src/main/kotlin/.../render/compat/RenderCompat.kt` |
| Shader mẫu (Iris) | `AnotherProject/debug/Derivative Main d24.4.4.zip` |
| Iris mod jar | `AnotherProject/debug/iris-fabric-1.10.9+mc26.1.1.jar` |

## TargetESP Shader Bloom — Celestial-style glow port attempt + revert (2026-06-07 session 2)

### What was attempted
1. **Celestial.jar reverse-engineered**: extracted `a/Yp.class` (TargetESP), `a/abh.class` (shader helper), `a/rf.class` (framebuffer wrapper), all shaders (`outline.frag`, `glow_esp`/`bloom.frag`, `gaussian.frag`, `down/up.frag`). Celestial is a **legacy LWJGL2 / MC-1.8 client** — raw-GL FBOs + `#version 120` immediate-mode GLSL. Concept ports; code does not.
2. **Built a ShaderGlow mode**: ported Celestial's outline/glow concept to 1.26.2 using the fork's working custom-shader infra (`precompilePipeline` + `ShaderSource` + `TextureTarget` + manual `RenderPass`). Created 4 shaders (`target_glow.vsh`, `target_glow_outline.fsh`, `target_glow_glow.fsh`, `target_glow_composite.fsh`), `TargetGlowRenderer.kt` (ping-pong post-process), `MixinLevelRenderer` (intercept `doEntityOutline`), `MixinEntity` (force-glow target). Build passed.
3. **User clarified this was the WRONG goal**: they wanted existing RichGhost glow.png particles to look bright/dazzling **when an Iris shaderpack is active** (the pack's own bloom, not a custom outline/glow). The real problem was render-path (CompatMeshSubmitter bypasses Iris), not post-process.

### What was reverted
- Entire ShaderGlow mode: 4 shader files deleted, `TargetGlowRenderer.kt` deleted, `MixinLevelRenderer.java` deleted, glow-flag hook removed from `MixinEntity.java`, precompile call removed from `MixinShaderManager.java`, mixin registration removed from `liquidbounce.mixins.json`.
- Also attempted a RenderType-toggle approach (enum `EmissiveRenderType` with Eyes/Emissive options) — reverted too since user said it doesn't work.
- **Final state of ModuleTargetESP.kt**: identical to before this session. RichGhost still uses `drawEmissiveBillboards` with hardcoded `entityTranslucentEmissive`. No bloom under tested packs (confirmed non-functional for this goal).

### Key findings (carry forward)
- **Complementary Unbound bloom is brightness-based** (`composite4.glsl` `BloomTile` blurs whatever is bright in `colortex0`). NOT material-ID-gated for arbitrary geometry.
- For custom-mod geometry to bloom: it must (a) be written into Iris's gbuffer through a vanilla entity RenderType Iris captures, AND (b) be flagged emissive/full-bright by the pack's lighting code. `entityTranslucentEmissive` gets captured but the pack doesn't treat it as emissive → no bloom.
- `eyes()` RenderType is a stronger candidate (spider/enderman eyes — commonly treated as emissive by packs). Not yet tested on FCL.
- The fork's legacy framebuffer stack (`OutlineShader`/`FramebufferShader`, GL20/30) is dead. `DrawOutlinesEvent` is never dispatched (orphaned). Vanilla's `entityOutlineTarget` exists and holds sobel+blur edges (not a solid fill).
- Custom GLSL shaders DO work via `precompilePipeline` + `ShaderSource` (proven RoundedRect/GlowRect/TargetGlow). `TextureTarget` is alive. `outputColorTextureOverride` works for redirecting compat draws. This infra is reusable for future custom post-process if needed.
- Reference shaderpack moved to `AnotherProject/debug/ComplementaryUnbound_r5.8.1.zip`.

## ClickGUI Dashboard rewrite — IN PROGRESS (2026-06-07 session 2)

### Goal
Replace the current Thunder-style dropdown ClickGUI (5 horizontal panels, modules listed vertically with settings expanding inline below) with a **Dashboard-style** layout:
- **Sidebar** (left): vertical category list (Combat/Movement/Render/Player/Misc). Click to switch active category.
- **Grid** (center): modules of active category displayed as 2-column card grid. Left-click = toggle; right-click = open settings panel; middle-click = bind.
- **Floating settings panel** (right or left, auto-flip): opens for 1 module at a time on right-click. Header = module name on accent overlay. Body = existing setting widgets (boolean/slider/choice/etc). Click outside = close.
- **Topbar**: logo left, search center, config button right.

### Design decisions (confirmed with user)
- Settings panel floats adjacent to grid, auto-flips to whichever side has more space. One module at a time.
- Old 5-panel dropdown layout is **replaced entirely** (not kept as an option).
- Remembers last selected category across sessions (`selectedCategory` added to `ClickGuiScreenState`).
- Grid = 2 columns.

### What was done
- Added `selectedCategory: String?` field to `ClickGuiScreenState` data class in `ClickGuiConfigManager.kt` (backwards-compatible nullable field).
- Full structural analysis of `ClickGuiScreen.kt` completed (1527 lines). Identified all reusable components: `collectSettings`, `SettingEntry`/`EntryKind`, all `renderSetting`/`renderBoolean`/`renderNumber`/etc helpers, `handleSettingClick`, drag/bind/choice logic, `ExosGuiRenderer` primitives, sound system, theme system, search/filter.
- Rewrite not yet started (file write interrupted).

### File reference
| Concern | File |
|---|---|
| ClickGUI screen (to rewrite) | `src/main/kotlin/.../render/gui/ClickGuiScreen.kt` |
| State persistence | `src/main/kotlin/.../render/gui/ClickGuiConfigManager.kt` |
| Drawing primitives | `src/main/kotlin/.../render/gui/ExosGuiRenderer.kt` |
| Module that opens GUI | `src/main/kotlin/.../render/ModuleClickGui.kt` (line 106: `mc.setScreen(ClickGuiScreen())`) |

## Codex note — ClickGUI Dashboard continuation (2026-06-10)

- User asked to keep explicit edit notes because both Codex and Claude may continue this repo later.
- Current task: replace old dropdown ClickGUI layout with dashboard layout only (sidebar + topbar/search + module card grid + floating settings panel). Do not spend time on glassmorphism/render polish yet.
- First Codex edit before interruption: `ClickGuiScreen.kt` class comment changed from Thunder/dropdown wording to dashboard wording; added dashboard runtime state fields: `selectedCategory`, `selectedModule`, `settingsScrollOffset`, `settingsScrollTarget`.
- Continue from here by replacing `renderPanel/renderModule` dropdown flow and `handlePanelClick` inline settings behavior with dashboard render/click/scroll flow. Keep existing `renderSetting`, setting collection, slider/choice/bind logic where possible.

### Codex completed layout pass (2026-06-10)

- User clarified: every edited source file should also carry direct inline notes like `#Edit By Codex 2026-06-10`, not only this `Agent1.md`. Added those comments in `ClickGuiScreen.kt` around dashboard state, render routing, dashboard shell, detached settings panel, click routing, scroll routing, bounds helpers, and constants.
- `ClickGuiScreen.kt` now renders one dashboard instead of five dropdown panels:
  - topbar: `SYLENT`, `SearchModule...` field, theme color chips moved to the top-right.
  - sidebar: vertical category list using existing categories (`Combat`, `Movement`, `Render`, `Player`, `Misc`), with filtered module count.
  - center grid: active category modules in a fixed 2-column card grid; cards show name, short description, bind text, ON/OFF pill.
  - right-click module: opens a detached settings panel with gradient header and existing setting widgets. The panel auto-places right of dashboard when screen space allows, left if needed, otherwise overlays inside screen bounds.
  - left-click module: toggles module; middle-click: starts bind capture; click outside settings panel closes it.
  - mouse wheel scrolls settings panel first when hovered, otherwise active module grid.
- Existing setting render/click logic was reused (`renderSetting`, sliders, ranges, choices/dropdowns, binds, text edit, section toggles), so value behavior should remain close to the previous GUI.
- `ClickGuiConfigManager.kt` already had `selectedCategory: String?`; `ClickGuiScreen.kt` now restores and saves it.
- Old private dropdown helper functions still exist in `ClickGuiScreen.kt` but are no longer called by the render/click/scroll path. They can be deleted in a cleanup pass after FCL testing if desired.
- Build command used on Termux: `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Build result: **BUILD SUCCESSFUL** → `build/libs/liquidbounce-0.31.2.jar`.
- Not yet FCL/in-game tested. Test next: open ClickGUI, switch categories in sidebar, search, scroll module grid, left/right/middle click cards, adjust sliders/dropdowns inside settings panel, click outside panel to close.

### Codex settings panel adjustment (2026-06-10)

- User reported that opening a module settings panel pushed the main dashboard slightly left. Root cause: `dashboardBounds()` centered the combined width of dashboard + settings panel when `selectedModule != null`.
- Fixed in `ClickGuiScreen.kt`: dashboard bounds are now independent from the settings panel; the dashboard stays centered and the settings panel positions itself separately to the right/left/inside screen bounds.
- Added `settingsPanelOpenedAt` and a TargetHud-style spring-pop scale animation for the settings panel (`settingsPanelScale()` / `springPop()`), applied around the panel center. Inline source comments use `#Edit By Codex 2026-06-10` per user instruction.
- Build command: `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Build result: **BUILD SUCCESSFUL**.
- Still needs FCL visual test: right-click modules repeatedly and confirm the main dashboard no longer moves; settings panel should pop/bounce in place.

### Codex visible settings animation fix (2026-06-10)

- User reported the settings panel still appeared instantly; the previous PoseStack scale was not visibly affecting the 1.26 GUI render states.
- Fixed in `ClickGuiScreen.kt`: settings panel animation now uses CPU-side scaled `Bounds` via `scaleBoundsFromCenter(...)`, so panel x/y/w/h actually grow from the center each frame. Kept inline `#Edit By Codex 2026-06-10` comments.
- Spring changed to a more visible pop: starts around 65% scale, overshoots above 100%, then settles.
- Build command: `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Build result: **BUILD SUCCESSFUL**.
- FCL visual test: right-click a module settings panel; expected visible scale-up/bounce instead of instant appearance.

### Codex Theme page rewrite (2026-06-10)

- User requested moving the theme color selector out of the topbar. Implemented in `ClickGuiScreen.kt` as a ClickGUI-only sidebar page named `Theme`, not a real `Category` in the module system.
- Sidebar now renders normal module categories first, then a thin white divider, then `Theme` at the bottom. Selecting `Theme` clears any open module settings panel.
- Theme chips are no longer rendered/clicked in the topbar. The center grid renders theme presets as module-style cards:
  - card background gradient uses the preset's own two colors,
  - card shows name + short description,
  - selected card shows `ACTIVE`, others show `SELECT`,
  - left-click theme card sets `selectedTheme`.
- Added theme metadata arrays: `THEME_NAMES`, `THEME_DESCRIPTIONS`; selected page persists through `ClickGuiScreenState.selectedCategory` using sentinel `THEME`.
- Search filters theme cards by name/description when the Theme page is active; theme grid has its own scroll state.
- Inline comments added with `#Edit By Codex 2026-06-10`.
- Build command: `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Build result: **BUILD SUCCESSFUL**.
- FCL visual test: open ClickGUI, verify topbar has no color chips, sidebar has divider + Theme, Theme page shows colored cards and selecting one updates ClickGUI accents.

## Codex TargetESP shader-path pass (2026-06-10)

- User confirmed the shaderpack to study is `AnotherProject/debug/ComplementaryUnbound_r5.8.1.zip`.
- Checked Complementary Unbound r5.8.1 shader programs:
  - `gbuffers_spidereyes.glsl` multiplies eye colors brighter, so vanilla `RenderTypes.eyes(...)` is a useful Iris/OptiFine bloom candidate.
  - `gbuffers_beaconbeam.glsl` boosts color much harder (`color.rgb *= color.rgb * emission * 1.75`) and writes through the beacon-beam path, so it is the best first test for the bright/chói TargetESP look.
  - Bloom is still brightness-based from scene color/composite passes; texture alpha/shape matters, but the render path decides whether the shaderpack sees the billboard as bright enough.
- Edited `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleTargetESP.kt`:
  - Added `RichGhosts -> ShaderPath` enum setting with `EntityEmissive`, `Eyes`, `BeaconBeam`.
  - Default is now `BeaconBeam` for RichGhosts because Complementary Unbound has the strongest shader boost on that path.
  - Kept `EntityEmissive` as the legacy baseline and `Eyes` as a second shader-friendly test path.
  - `drawEmissiveBillboards(...)` now receives the selected shader path and uses `RenderTypes.entityTranslucentEmissive`, `RenderTypes.eyes`, or `RenderTypes.beaconBeam(texture, true)`.
  - Added a separate BLOCK-format vertex writer for `BeaconBeam`; `EntityEmissive`/`Eyes` keep the ENTITY-format writer with overlay/light/normal.
  - Inline source notes added with `#Edit By Codex 2026-06-10` near the setting, render call, enum, and vertex-format split.
- Build command: `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Build result: **BUILD SUCCESSFUL**.
- FCL test next:
  1. Enable Complementary Unbound r5.8.1.
  2. Set TargetESP mode to `RichGhosts`.
  3. Test `ShaderPath=BeaconBeam` first, then `Eyes`, then `EntityEmissive`.
  4. Compare bloom/chói around the ghost particles. If BeaconBeam is too harsh or has dithered edges, try Eyes; if both fail to bloom, the next step is a client-side bloom/post-process path rather than more texture edits.

## Codex TargetESP RichGhost brightness pass (2026-06-10)

- User provided extracted reference frames in `AnotherProject/debug/Video Frame Extractor 2026-06-10 9_53_25 GMT+7` and reported the previous `BeaconBeam/Eyes` patch still did not work.
- Visual read of frames `050.png`/`100.png`: the target effect has multiple curved/teardrop ghost streaks with a very bright white core, colored pink/orange rim, and a broad shader halo. It is not just a soft circular alpha texture.
- Checked RichClient source:
  - `RichClient/src/main/java/fun/rich/utils/display/geometry/Render3D.java` `drawGhosts(...)` still uses `textures/features/particles/bloom.png`, but queues quads into a batch rendered with additive-like blend (`SRC_ALPHA`, `ONE_MINUS_CONSTANT_ALPHA`, effectively additive with default constant alpha).
  - Rich color logic uses `multRedAndAlpha(...)`: strong alpha and RGB compression during hit, so particles get a brighter center than the current port.
- Checked texture stats:
  - Current `src/main/resources/resources/liquidbounce/rich/particles/bloom.png` is the Rich radial bloom rebaked to RGBA: RGB white, alpha 0..252, nonzero alpha about 57.5%.
  - Original Rich `bloom.png` is RGBA with alpha 255 everywhere and grayscale RGB on black background. Shape is effectively the same radial bloom; the repo version is safer for normal alpha because it avoids black squares.
  - Conclusion: the asset is not the main blocker; the missing pieces were additive brightness and a white-hot core pass.
- Edited `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleTargetESP.kt`:
  - Added `RichGhostShaderPath.ENERGY_SWIRL` using `RenderTypes.energySwirl(texture, 0.0F, 0.0F)`. This is emissive + additive and is closer to Rich's original draw path than translucent `BeaconBeam/Eyes`.
  - Changed new default `RichGhosts -> ShaderPath` to `EnergySwirl`.
  - Changed new default `Opacity` from `75` to `100`.
  - Added `Intensity` setting, default `1.35`.
  - Added `HotCore` setting, default `true`.
  - RichGhosts now builds two billboard batches: outer colored halo and smaller white-hot core. The core is always drawn through `EnergySwirl` so existing configs saved as `BeaconBeam` still get the bright center pass.
  - Added `Color4b.mixWhite(...)` helper for the white-core color.
  - Inline source notes added with `#Edit By Codex 2026-06-10`.
- Build command: `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Build result: **BUILD SUCCESSFUL**.
- FCL test next:
  1. TargetESP mode `RichGhosts`.
  2. Set `ShaderPath=EnergySwirl` manually if an old config still shows `BeaconBeam`.
  3. Keep `HotCore=true`, `Opacity=100`, start `Intensity=1.35`; raise to `1.7` if still weak, lower to `1.0` if overexposed.
  4. If still no broad shader halo, the remaining route is a dedicated client-side bloom/post-process, not further `bloom.png` changes.

## Codex TargetESP RichGhost streak-shape pass (2026-06-10)

- User provided `AnotherProject/debug/Screenshot_2026-06-10-10-06-24-61_c8a66bc90793a73eafa5773962b202df.jpg` and pointed out the close-up issue: RichGhosts looked like many circular glow sprites stacked into a line, not one smooth stretched glow.
- Root cause: the port rendered one square/circular `bloom.png` billboard at every RichGhost sample point. Close up, the individual circles are visible, especially with `HotCore`.
- Edited `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleTargetESP.kt`:
  - `GlowBillboard` now carries optional `width`, `height`, and `rollDegrees`.
  - RichGhosts no longer adds one circular billboard per point. It computes adjacent point pairs and adds stretched segment billboards centered between the points.
  - Segment roll is calculated in camera plane from the projected point-to-point delta, so the oval aligns with the visible trail direction.
  - Outer halo is a wide/flat streak; `HotCore` is now a smaller stretched streak too, not a small circular dot.
  - `drawGlowBillboards(...)`, `drawEmissiveBillboards(...)`, and `drawBillboardQuad(...)` now respect per-billboard width/height/roll. Dreamcore remains compatible because default width/height still equal `size`.
  - Inline source note added with `#Edit By Codex 2026-06-10` near the segment conversion.
- Build command: `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Build result: **BUILD SUCCESSFUL**.
- FCL test next: zoom close to RichGhosts. Expected result is fewer obvious circle blobs and more elongated streaks. If still too beaded, next adjustment is either increasing `streakWidth`/lowering `streakHeight` or replacing the billboard chain with a true ribbon mesh.

## Codex TargetESP streak-shape revert (2026-06-10)

- User provided `AnotherProject/debug/Screenshot_2026-06-10-10-16-23-35_c8a66bc90793a73eafa5773962b202df.jpg` and reported the stretched texture/ribbon patch broke the texture.
- Reverted the **stretched segment/ellipse** experiment in `ModuleTargetESP.kt`.
- Kept the brightness work:
  - `ShaderPath` default remains `EnergySwirl`.
  - `Opacity` default remains `100`.
  - `Intensity` remains available.
  - `HotCore` white-core pass remains available.
- RichGhosts is back to Rich-style point billboards (`bloom.png` drawn as square/circular billboards per point), but with the brighter additive path and optional white core.
- Removed the segment-only data/logic from `GlowBillboard` (`width`, `height`, `rollDegrees`) and removed `RichGhostPoint`, `midpoint`, `projectedDelta`, `atan2`, `sqrt`.
- Inline note added in `ModuleTargetESP.kt` near RichGhosts loop: `#Edit By Codex 2026-06-10: reverted the stretched-segment experiment...`.
- Build command: `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Build result: **BUILD SUCCESSFUL**.

## Codex TargetESP HotCore no-extra-sprites pass (2026-06-10)

- User found the key detail: `ShaderPath=Eyes` with `HotCore=true` is bright enough, but the stacked circular bloom artifact appears only when `HotCore` is enabled. With `HotCore=false`, the artifact mostly disappears.
- Root cause: `HotCore` was drawing a second smaller white billboard batch (`coreBillboards`) at every RichGhost point. That doubled the visible sprite count and made the circular bloom texture obvious.
- Edited `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleTargetESP.kt`:
  - Removed `coreBillboards` and the second `drawEmissiveBillboards(..., EnergySwirl)` call.
  - `HotCore` now boosts the existing/main billboard only: mixes the color toward white and raises alpha, without adding extra sprites.
  - Kept `ShaderPath`, `Intensity`, `Opacity=100`, and the point-sprite layout.
  - Inline source note added near the `HotCore` alpha/color calculation.
- Build command: `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Build result: **BUILD SUCCESSFUL**.
- FCL test next: `RichGhosts`, `ShaderPath=Eyes`, `HotCore=true`. Expected: keeps much of the chói from HotCore but should no longer add a second layer of small circular bloom sprites.

## Codex TargetESP HotCore restore (2026-06-10)

- User asked to restore HotCore to the original behavior because the inline-only boost lost the desired brightness.
- Restored the separate HotCore pass in `ModuleTargetESP.kt`:
  - `coreBillboards` is back.
  - `HotCore=true` again draws a second additive batch through `RenderTypes.energySwirl(texture, 0.0F, 0.0F)`.
  - The main halo billboard logic stays unchanged from the reverted point-sprite layout.
  - The old inline-only boost removal is no longer used.
- Inline note added near the second pass: `#Edit By Codex 2026-06-10: restored the separate additive HotCore pass.`
- Build command: `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Build result: **BUILD SUCCESSFUL**.

## Claude TargetESP HotCore footprint fix (2026-06-10)

- User confirmed: `RichGhosts` + `ShaderPath=Eyes` + `HotCore=true` looks bright/chói and is accepted. BUT with HotCore ON the texture "breaks" — instead of one smooth glow trail it shows many discrete bloom circles stacked into a line (the "chuoi hinh tron" artifact). Screenshot: `AnotherProject/debug/Screenshot_2026-06-10-10-06-24-61_*.jpg`.
- Root cause (confirmed from `RichGhostsMode.render`): HotCore drew a second sprite batch at the SAME trail points but at `haloSize * 0.46F` (less than half the halo size). The RichGhost trail points are spaced far apart (yOffset sin phase jumps ~1.2-3.6 rad per `i`), so the big halo sprites OVERLAP and read as a smooth glow, but the half-size core sprites do NOT overlap -> each `bloom.png` shows as its own circle. This is why the artifact only appears when HotCore is enabled (Codex's earlier inline-only boost had no extra sprites so no artifact, but lost brightness; restoring the separate pass brought back the artifact because the size was still 0.46x).
- Fix (`ModuleTargetESP.kt`): HotCore core sprite now uses the FULL `haloSize` (removed the `* 0.46F`). The white-hot additive pass now overlaps exactly like the halo -> continuous bright trail, no discrete circles. White still concentrates in the center (bloom.png fades center->edge) and the colored rim survives because the white fades at the edges. Brightness preserved (still a separate additive pass through EnergySwirl).
- Inline note: `#Edit By Claude 2026-06-10: HotCore now matches the HALO footprint instead of shrinking to 0.46x...`
- Build command: `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Build result: **BUILD SUCCESSFUL** → `liquidbounce-0.31.2.jar`.
- FCL test next: `RichGhosts` + `ShaderPath=Eyes` + `HotCore=true`. Expected: same brightness/chói as before, but the trail is now one smooth glow instead of a chain of separate bloom circles. If the white core now washes out the colored rim too much, lower `Intensity` or reduce `mixWhite(0.72F)`.

## Claude TargetESP — ALL Codex bloom work REVERTED (2026-06-10)

- User verdict: the full-size HotCore fix killed the brightness ("hết chói với sáng"). User asked to delete ALL of Codex's edits and move back to the ClickGUI task.
- **Reverted `RichGhostsMode` to the 2026-05-31 confirmed-working baseline** in `ModuleTargetESP.kt`:
  - Removed settings `ShaderPath`, `Intensity`, `HotCore`. RichGhosts is back to `Color/Speed/Size/Opacity` only (Opacity default back to `80`).
  - Removed the `coreBillboards` second pass and the whole emissive/shaderpack render route.
  - RichGhosts now draws through `drawGlowBillboards` (the CompatMeshSubmitter path, same as Dreamcore) with `bloom.png`, `depthMask(false)` + `disableCull` + lightmap disable + `blendFunc(SRC_ALPHA, ONE_MINUS_SRC_ALPHA)` + `setShaderTexture(richBloom)`. Plain point sprites, no shaderpack dependency.
- **Deleted the now-dead code Codex added**: enum `RichGhostShaderPath`, `WorldRenderEnvironment.drawEmissiveBillboards`, `emissiveVertex`, `blockLightVertex`, `Color4b.mixWhite`. Removed their now-unused imports (`VertexConsumer`, `RenderType`, `RenderTypes`, `OverlayTexture`, `Identifier`, `NamedChoice`).
- Other modes (Dreamcore, RichCircle, RichCube) untouched.
- NET RESULT: `ModuleTargetESP.kt` is effectively back to its pre-2026-06-07 state for RichGhosts. The Iris-bloom-for-RichGhost goal is **abandoned** — confirmed non-viable through this render path (the brightness the user liked came from the additive emissive pass, which can't be kept without the discrete-circle artifact; the smooth-trail compat path is not chói). The Agent1.md sections "TargetESP RichGhost — Shader/Bloom research" through "Claude TargetESP HotCore footprint fix" are historical only; the code no longer reflects them.
- Build: `env JAVA_HOME=... sh ./gradlew --no-daemon clean assemble -x detekt` → **BUILD SUCCESSFUL** → `liquidbounce-0.31.2.jar`.

## Claude ClickGUI + PotionHud visual rework (2026-06-10)

### ClickGUI sidebar highlight
- Removed the accent-colored gradient line under the topbar (was syncing with theme and looked like extra highlight).
- Removed the full-width white separator line under topbar.
- Removed the 3px vertical accent gradient bar (`|`) at the left edge of selected category rows (both normal categories and Theme page row).
- **Added animated chroma highlight** on selected category row, Theme page row, and active module cards. The effect pulses brightness between bright and dark shades of the two theme accent colors (`accentA`↔`accentB`), with each corner phase-shifted so colors appear to travel around the border. Period: `CHROMA_PERIOD_MS = 1100ms`. Brightness range: `0.16→0.78` (dark enough to show "đen nhạt" contrast). Implemented via `renderChromaHighlight()` + `themeBlend()` in `ClickGuiScreen.kt`.

### Theme palette rework
- Removed "Lime Flash" (too bright/chói).
- Softened harsh neons: Mint Flux, Sunset Pulse, Coral Heat.
- Added themed presets: Twilight (blue→purple), Ocean (teal→deep blue), Rose Quartz (pink→periwinkle), Sandstone (gold→olive).
- Added monochrome themes: **White** (light) and **Black** (dark).
- Total: **11 themes**.
- Moved theme data to shared `ClickGuiTheme.kt` object so HUD elements read the SAME selection as the ClickGUI (previously they used separate `ModuleHud.themeColor`).

### Shared theme object (`ClickGuiTheme.kt`)
- New file: `src/main/kotlin/.../render/gui/ClickGuiTheme.kt`.
- Contains `COLORS`, `NAMES`, `DESCRIPTIONS` arrays + `selected` index + `accentA`/`accentB` accessors.
- `ClickGuiScreen.selectedTheme` now delegates to `ClickGuiTheme.selected`.
- PotionHud reads `ClickGuiTheme.accentA/accentB` directly → changing theme in ClickGUI changes HUD immediately.

### PotionHud title band
- Removed the old separator line under "Potions" title.
- Title band now uses the **animated theme fill** (`ExosGuiRenderer.animatedThemeFill`) as full background across the header area.
- Band is **flush with panel top** (no inset) and only has **2 rounded corners at the top** (matching panel radius). Bottom edge is a **flat horizontal line** (achieved by overlaying a flat rect with radius 0 on the lower portion to square off bottom corners).
- Syncs to ClickGUI theme via `ClickGuiTheme.accentA/accentB`.
- Title text drawn **white + shadow** for maximum readability over the chroma fill.

### New ExosGuiRenderer helpers
- `animatedThemeBorder(...)` — draws only a border ring (gradient rect + interior re-stamp). Reusable API for other elements.
- `animatedThemeFill(...)` — full rounded gradient fill with brightness-pulsing theme colors. Used by PotionHud title band.

### Constants added to ClickGuiScreen companion
- `CHROMA_PERIOD_MS = 1100L` (spin speed)
- `CHROMA_BORDER = 1.7f` (border thickness for `animatedThemeBorder`)
- `CHROMA_FILL_ALPHA = 55` (faint interior wash alpha)

### File reference
| Concern | File |
|---|---|
| Shared theme object | `src/main/kotlin/.../render/gui/ClickGuiTheme.kt` |
| ClickGUI chroma highlight | `src/main/kotlin/.../render/gui/ClickGuiScreen.kt` (renderChromaHighlight, themeBlend) |
| ExosGuiRenderer new helpers | `src/main/kotlin/.../render/gui/ExosGuiRenderer.kt` (animatedThemeBorder, animatedThemeFill) |
| PotionHud title band | `src/main/kotlin/.../render/gui/hud/HudElement.kt` (PotionHudElement.render) |

### Build
- `env JAVA_HOME=... sh ./gradlew --no-daemon assemble -x detekt` → **BUILD SUCCESSFUL** → `liquidbounce-0.31.2.jar` (20:09:30).
- FCL test: ClickGUI chroma on selected items + Theme page; PotionHud title band syncs with theme; 2 rounded top corners + flat bottom.

## PotionHud fixes (2026-06-10 session)

### Horizontal line seam — FIXED
- **Root cause**: Title band was drawn with TWO `animatedThemeFill` calls (full rounded rect + flat overlay on bottom strip to square off corners). Each maps the SAME four corner colours onto a DIFFERENT rectangle → at the overlay boundary the main fill's mid-gradient colour met the overlay's top-gradient colour = visible seam.
- **Fix**: Single continuous gradient fill extended DOWN by `RADIUS` and scissor-clipped to the real band height. Rounded bottom corners fall below the clip region → flat bottom edge, one uninterrupted gradient, no seam.

### Animated chroma outline — ADDED
- Replaced the static hairline outline + additive `shaderGlow` with ONE thin `roundedOutline` stroke using the animated chroma colour (synced to ClickGUI theme highlights: same accent colours, wave curve, 1100ms period, same phase direction).
- Previous versions were too "thick" because the additive glow (GlowRectShader composites with `ONE`) was stacking on top of the outline at the panel edge. Removing the glow and keeping a single thin stroke (alpha 190 / 1.15px) fixed the perceived thickness.

## ClickGUI Dashboard rewrite — continued (2026-06-10 → 2026-06-11 sessions)

### Open/Close ScaleUp/ScaleOut animation — ADDED (2026-06-11)
- Found the existing open animation used the **fake** `context.matrices` (a no-op on this fork) — never visually showed.
- Rewrote to scale via the **real** `context.pose()` (`Matrix3x2fStack`).
- ScaleUp on open (0.82 → 1.0, eased over 200ms); ScaleOut on close (1.0 → 0.82 over 160ms).
- ESC/close now flags `closing`, keeps rendering the shrinking panel, and defers the actual screen dismissal (`performClose`) until the animation finishes.
- Constants: `OPEN_ANIM_MS=200`, `CLOSE_ANIM_MS=160`, `OPEN_MIN_SCALE=0.82`.

### Category icons — PNG textures (2026-06-11)
- User converted the src-theme tabgui SVGs → white RGBA PNGs (15×15 / 17×15 etc.) and placed them in `AnotherProject/debug/`.
- Copied to `src/main/resources/resources/liquidbounce/clickgui/icons/` (combat, movement, render, player, misc, world).
- Created `src/main/kotlin/.../render/gui/IconGui.kt` — a `drawIcon` extension on `GuiGraphicsExtractor` (mirrors `drawSkinFace`, UV 0..1, full-image quad with tint colour).
- `drawCategoryIcon` now tries the real PNG first (letterboxed to preserve aspect ratio), falls back to the procedural glyph if texture isn't ready.
- **Icon collision bug FIXED (2026-06-11)**: `registerAsDynamicImageFromClientResources()` derives the Identifier from `System.currentTimeMillis().toString(36)` — registering all icons in the same frame produced identical timestamps → same Identifier → texture clobbered each other (Render showed Player's icon). Fixed by giving each icon a stable, unique Identifier (`clickgui-icon-<name>`) via `registerDynamicImageFromResources`.

### Module descriptions — 16 missing keys added (2026-06-11)
- Modules without a description key in `en_us.json`: AutoCart, ChunkAnimator, ClickAction, CustomModels, Hats, InventoryAnimation, ItemPhysics, JumpCircle, KillAnimation, LegitHelper, MaceSwap, MacroChat, SwingAnimations, TargetESP, ViewModel, Wings.
- Read each module's source, wrote accurate one-line descriptions, added to the lang file. JSON validated.

### Layout changes (2026-06-10 → 2026-06-11, IN PROGRESS)
- **Sidebar widened** 128 → 150 so "Movement" + icon fits.
- **Dashboard narrowed** then re-tuned: 640 → 544 → 470. Height raised 390 → 440.
- **Card height** 62 → 74 → 46 (to fit 14 modules = 7 rows without scrolling).
- **GRID_TITLE_SPACE** 38 → 20 (pulls grid up close under search bar).
- **GRID_GAP** 10 → 6; **GRID_BOTTOM_PAD** 16 → 12.
- Module description wraps **2 lines at size 9** to fit shorter cards.
- Removed: per-category module count in sidebar, category name title above module grid, hover-tooltip feature (`hoveredDescription`), ON/OFF pill, bind text on cards.
- Added: gear icon ⚙️ next to module name when the module has settings.
- **Background darkened** to solid black (`0xF2000000`).

### Layout TODO — DONE (2026-06-11, Claude)
All four resolved in `ClickGuiScreen.kt`, build SUCCESSFUL → `liquidbounce-0.31.2.jar`:
- **Dashboard widened** `DASHBOARD_WIDTH` 470 → 560 (`MIN_DASHBOARD_WIDTH` 400 → 440).
- **Scrollbar gutter**: new const `SCROLL_GUTTER = 16f`. `gridBounds()` subtracts it from card width so the bar no longer overlaps the right cards. Generic `renderScrollbar(..., gutter = 0f)` gained a `gutter` param: when >0 the bar is centered in the reserved lane to the RIGHT of the content; settings panel still passes 0 (bar at inner edge). Bar is now a rounder pill: `barW = 4f`, fully rounded, min thumb 14px (was 2.5px). Both module grid + theme grid pass `SCROLL_GUTTER`.
- **Card text vertically centered**: `renderModuleCard` measures the name+description block height then places `nameY = y + (cardH - blockH)/2`. Gear icon + description follow `nameY` (no longer pinned at y+9 / y+24).
- **"N modules" count text removed** from `renderModuleGrid` (it overlapped the top-right card). Theme grid keeps its "N styles" count (different layout, has a title row).
- FCL test next: open ClickGUI, confirm wider panel, scrollbar sits in its own gutter clear of cards, card name+desc centered, no count text over the top-right card.

### File reference (new files this session)
| Concern | File |
|---|---|
| Icon texture helper | `src/main/kotlin/.../render/gui/IconGui.kt` |
| Category icon PNGs | `src/main/resources/resources/liquidbounce/clickgui/icons/*.png` |
| Lang descriptions (16 added) | `src/main/resources/resources/liquidbounce/lang/en_us.json` |

## ClickGUI render improve — celestial-inspired SDF upgrades (2026-06-11, Claude)

Source studied: `AnotherProject/debug/celestial/shaders/` — but it's **GLSL `#version 120` (MC 1.8 legacy, immediate-mode, `gl_FragColor`/`gl_TexCoord`)**, so NOTHING ports as code. Only concepts port. The fork's GUI shaders are already SDF + 4-corner gradient + Inter font + blur (NOT vanilla). Took the two ideas the fork was actually missing, plus a free outline variant:

### 1. Dithering (anti-banding) — `rounded_rect.fsh`
- Celestial's gradient shaders all add `±0.5/255` hash noise (`NOISE`) to kill 8-bit colour banding. The fork's `rounded_rect.fsh` didn't. Added the same hash dither (`fract(sin(dot(gl_FragCoord.xy, vec2(12.9898,78.233)))*43758.5453)`) right before output. GLOBAL win — every dark ClickGUI/HUD gradient is now smooth (no visible sọc màu). Zero pipeline change, ~0 risk.

### 2. Per-corner radius — vsh + fsh + element state + draw API
- **Hard constraint confirmed from MC source** (`minecraft-merged-...-sources.jar`): 1.26.2 `VertexConsumer` exposes ONLY `setUv`/`setUv1`/`setUv2` — there is NO third `ivec2` slot. So a 4th attribute for per-corner radii is impossible without a new vertex format.
- **Solution**: pack a **4-bit corner mask** into the high bits of the existing `RadiusStroke.y` short. Layout of `RadiusStroke.y`: bits 0..10 = stroke width (px*16, up to ~127px), bits 11..14 = corner mask (bit0 TL, bit1 TR, bit2 BR, bit3 BL). `rounded_rect.vsh` unpacks to `vStroke` + `vCornerMask`; `.fsh` uses `sdRoundBoxPerCorner` (IQ vec4-radii SDF) selecting per quadrant.
- **Default mask = 0xF (all four corners)** ⇒ every existing call-site renders IDENTICALLY. Verified: only one record construction site (`RoundedRectGui.kt`).
- `RoundedRectGuiElementRenderState.java`: added `int cornerMask` field, packs `strokeFixed | (mask << 11)`.
- `RoundedRectGui.kt`: added `CORNER_*`/`CORNERS_*` consts (TOP/BOTTOM/LEFT/RIGHT/ALL), `cornerMask` param on `drawRoundedRect`, a solid-fill+mask overload, and `drawRoundedGradientOutline` (4-colour stroke).
- `ExosGuiRenderer.kt`: new `roundedRectCorners`, `roundedGradientRectCorners`, `roundedGradientOutline`; `animatedThemeFill` gained a `cornerMask` param (default 0xF).

### 3. Applied (real usages, not just API)
- **PotionHud title band** (`HudElement.kt`): replaced the old scissor-clip flat-bottom HACK (fill extended `+RADIUS` then clipped — the thing that caused the historical seam) with ONE `animatedThemeFill(..., cornerMask = CORNERS_TOP)`. Real flat bottom, no scissor, no seam.
- **ClickGUI settings panel header** (`ClickGuiScreen.kt`): header gradient now `roundedGradientRectCorners(..., CORNERS_TOP)` so its bottom edge is flush/flat against the panel body (was rounding inward over content with all-4 radius 12).

### Build / verify
- `assemble -x detekt` → **BUILD SUCCESSFUL** → `liquidbounce-0.31.2.jar`.
- Jar-checked: both updated shaders ship (`fsh` has DITHER+vCornerMask, `vsh` has vCornerMask).
- NOT yet FCL-tested. Test next: (1) all gradients look smoother / no banding; (2) PotionHud title band has clean flat bottom, no seam; (3) settings panel header bottom edge flush; (4) every other rounded rect unchanged (regression check — default mask 0xF).

### File reference (render improve)
| Concern | File |
|---|---|
| SDF fsh (dither + per-corner SDF) | `src/main/resources/resources/liquidbounce/shaders/rounded_rect/rounded_rect.fsh` |
| SDF vsh (mask unpack) | `src/main/resources/resources/liquidbounce/shaders/rounded_rect/rounded_rect.vsh` |
| Element state (mask pack) | `src/main/java/.../render/gui/RoundedRectGuiElementRenderState.java` |
| Draw API + CORNERS_* consts | `src/main/kotlin/.../render/gui/RoundedRectGui.kt` |
| Corner/gradient-outline helpers | `src/main/kotlin/.../features/module/modules/render/gui/ExosGuiRenderer.kt` |

## OffHand AutoTotem — GrimStop sprint/input suppress (2026-06-11, Claude)

### Symptom
PvP netherite pot: after a totem pops while the player is being chased (sprinting + holding WASD), the GrimStop-guarded re-totem flags `MultiActionsC (sprinting, input)` + `MultiActionsD (sprinting, input)` repeatedly. User repro: pop totem mid-air via MaceSwap windburst, land taking fall damage with no armor → totem pops → re-totem spams flags.

### Root cause (confirmed from Grim + fork source)
- `multiactions/MultiActionsC.java` = flags `CLICK_WINDOW` (inventory click) while moving; `MultiActionsD.java` = flags `CLOSE_WINDOW` while moving. "moving" = `isVerboseSprinting` (sprint bit set) OR `isVerboseInput` (`knownInput.moving()` — PLAYER_INPUT reports WASD held). Reason "sprinting, input" = both true.
- With `Totem.GrimStop` (TotemGuard) ON, after a pop `markPop()` → `shouldUseGuardedTiming()` true for 1500ms → re-totem routes through the GUARDED refill (`startGuardedTotemRefill`/`continueGuardedTotemRefill`): opens inventory → `performPickup` (CLICK_WINDOW) → `CloseInventorySilentlyAction` (CLOSE_WINDOW).
- GrimStop only SPACED the clicks (delay ticks); it never stopped sprint/input. So those guarded container packets went out while sprint bit + WASD were live → C/D flags.
- NOTE: the NON-guarded path on 1.16+ (`SwitchMode=Automatic` → SWAP_ITEM_WITH_OFFHAND, a PlayerAction packet) never opens a container, so it can't hit C/D. Only the post-pop guarded window does. Matches the user report exactly.

### Reference studied
- VCore `combat/AutoTotem.java` `startGrimStop()` → `InputBlocker.block()`; `InputBlocker`/`MixinKeyBinding` force forward/back/left/right/jump/sneak/sprint keys to return false during the swap. i.e. VCore stops BOTH sprint and movement input around the totem swap.
- Fork already has the exact precedent: `ModuleInventoryMove.STOP_ON_ACTION` (holds container packet 1 tick + forces `sprint=false` on SprintEvent.INPUT + zeroes MovementInputEvent so STOP_SPRINTING & empty PLAYER_INPUT hit the wire first).

### Fix
- `Totem.kt` `TotemGuard`: added `StopSprint` (default true) + `StopInput` (default true) booleans.
- `ModuleOffhand.kt`: added two FINAL_DECISION handlers gated on `guardedTotemSwap != null && TotemGuard.enabled` (`guardSuppressActive()`):
  - `guardSprintSuppressHandler` (SprintEvent, source == INPUT) → `event.sprint = false`.
  - `guardInputSuppressHandler` (MovementInputEvent) → `directionalInput = NONE`, `jump = false`, `sneak = false`.
- Only active during the brief guarded refill window, so normal movement is untouched. Mirrors InventoryMove StopOnAction + VCore InputBlocker.
- Build: `assemble -x detekt` → **BUILD SUCCESSFUL**.

### FCL test
- PvP/repro: pot down, get hit to low HP while sprinting+moving, let GrimStop re-totem fire. Expected: NO more MultiActionsC/D "sprinting, input". The player briefly stops sprinting/moving (~the swap ticks) during the guarded refill, then resumes. If movement stutter is too visible, lower `Totem.GrimStop.TicksDelay`; if a flag still slips, the swap may need the click held one extra tick (InventoryMove-style) — escalate then.

### File reference
| Concern | File |
|---|---|
| GrimStop toggles (StopSprint/StopInput) | `src/main/kotlin/.../player/offhand/Totem.kt` (object TotemGuard) |
| Sprint/input suppress handlers | `src/main/kotlin/.../player/offhand/ModuleOffhand.kt` (guardSuppressActive + 2 handlers) |
| Grim checks | `AnotherProject/Grim/.../checks/impl/multiactions/MultiActions{C,D}.java` |

## Wonderful skid — TargetESP 3 modes + Cubes module (2026-06-11, Claude)

Skidded from `AnotherProject/WorkSpace/wonderful-1.0.0.jar` (no source — decompiled with Vineflower 1.11.1 from gradle cache to `~/.cache/wonderful_decomp/`). Wonderful is Fabric MC 1.26.x using INTERMEDIARY mappings (`class_XXXX`) + raw Tessellator + `RenderSystem.setShader(field_53880=POSITION_TEX_COLOR / field_53876=POSITION_COLOR)`. Mapped onto the fork's Mojang-mapped compat render layer (`drawCustomMesh` / `drawGlowBillboards` / `RenderCompat`).

### Decompile recipe (for future skids)
- `find ~/.gradle -iname "vineflower*.jar"` → `org.vineflower/vineflower/1.11.1`.
- `unzip` target classes from the jar into a dir, then `java -jar vineflower.jar in out`.
- Wonderful TargetESP mode array (Russian): `[Картинка 1, Картинка 2, Кольцо, Души, Кубы, Кристаллы]` = [Image1, Image2, Ring, Souls, Cubes, Crystals]. The "last 3" = **Souls / Cubes / Crystals**.

### Task 1: 3 last TargetESP modes → added to existing `ModuleTargetESP.kt`
- New choices appended (existing 4 untouched): `WonderfulSouls`, `WonderfulCubes`, `WonderfulCrystals`.
- **WonderfulSouls** (Души): 3 interleaved orbiting soul trails (20 glow billboards each, 2 sizes per point for a glow halo) via `drawGlowBillboards`. appear ease-in/out.
- **WonderfulCubes** (Кубы): own `CubeParticle` list spawned at a ring on the target, rises to shoulder height then fades (140ms in / 320ms out). 2 passes: faint additive QUAD faces + DEBUG_LINES wireframe edges. Per-particle local matrix (`Matrix4f(base).translate(camera-relative).rotateXYZ.scale`).
- **WonderfulCrystals** (Кристаллы): octahedron/diamond shapes (TRIANGLES) orbiting at 20° steps + bloom billboards behind each. rotation + appear anim.
- **Asset**: ALL three use wonderful's OWN `targetesp/bloom.png` (copied to `resources/liquidbounce/wonderful/targetesp/bloom.png`) — NOT the rich/dreamcore textures (user: skid nhiều nên không lấy chung asset). bloom.png is RGBA colortype 6 with real alpha (min0/max252) so NO rebake needed (unlike the rich/jumpcircle additive textures).
- Wonderful used `getThemeColor()`; fork uses a per-mode `color()` setting (default LIQUID_BOUNCE). hurtRed/mixRed reused from existing module.

### Task 2: standalone `Cubes` module → new `ModuleCubes.kt`
- Full 1:1 skid of wonderful `Cubes.java` (3D кубы по миру). Registered in `ModuleManager.kt` after `ModuleJumpCircle`. Category RENDER.
- Floating cubes/triangles drift around the PLAYER (not a target). Settings: Color, Animation (Scatter/Falling), Shape (Cubes/Triangles), Count(5..100), Size, Speed.
- 3 render passes: additive glow billboards (3 layered scales) + faces + dashed wireframe edges. Spawn ring radius 12, max-render-dist² 900, frustum cull via look dir.
- **Hit impulse**: on attack-key rising edge, ray-pick the cube under the crosshair and push it away.
- **Asset**: wonderful's `particle/bloom.png` (same image as targetesp bloom) → `resources/liquidbounce/wonderful/particle/bloom.png`.
- Lang: added `liquidbounce.module.cubes.description` to en_us.json.

### Mapping gotcha (carry forward)
- `Camera.yRot` / `Camera.xRot` are **private** on 1.26.2 — cannot read yaw/pitch directly. Derive look direction from the rotation quaternion: `Vector3f(0,0,-1).rotate(camera.rotation)`. Billboard right/up: `Vector3f(1,0,0)/(0,1,0).rotate(camera.rotation)` (same as TargetESP's `billboardAxes`).
- Mode `choiceName`s render directly in the dashboard ClickGUI (no lang key needed); only the module itself needs a `.description` key.

### Build / verify
- `assemble -x detekt` → **BUILD SUCCESSFUL** → `liquidbounce-0.31.2.jar`.
- Jar-verified: both bloom assets (colortype 6), `ModuleCubes.class`, the 3 `ModuleTargetESP$Wonderful*Mode.class`, and the cubes lang key all present.
- NOT yet FCL-tested. Test: TargetESP → set Mode to WonderfulSouls/WonderfulCubes/WonderfulCrystals (needs a KillAura target); enable Cubes module standalone, try Scatter/Falling × Cubes/Triangles, attack to push a cube.

### File reference
| Concern | File |
|---|---|
| 3 wonderful TargetESP modes | `src/main/kotlin/.../render/ModuleTargetESP.kt` (WonderfulSouls/Cubes/CrystalsMode + CubeParticle) |
| Standalone Cubes module | `src/main/kotlin/.../render/ModuleCubes.kt` |
| Module registration | `src/main/kotlin/.../features/module/ModuleManager.kt` (ModuleCubes after ModuleJumpCircle) |
| Wonderful assets | `src/main/resources/resources/liquidbounce/wonderful/{targetesp,particle}/bloom.png` |
| Decompiled source | `~/.cache/wonderful_decomp/out/fun/wonderful/client/modules/impl/render/{TargetESP,CubeParticle,Cubes}.java` |

## Wonderful Crystal + Souls(Glow) fixes (2026-06-12, Claude)

User feedback (with screenshots `AnotherProject/debug/Esp_{Crystal,Glow}_{Now,WonderFun}`): Cube modes accepted; **Crystal** heavily broken (a big dark/bright BLOB covering the entity instead of small orbiting crystals), **Souls/"Glow"** works but dull (looks like RichGhost, not bright/chói like wonderful).

### Root cause 1 — Crystal blob (sizing)
- The crystal SHAPES (drawCustomMesh diamonds, w=0.06/h=0.2) were already world-sized and fine.
- The crystal GLOW billboards used wonderful's `drawBillboard`, which **screen-scales** size by `max(0.1, distance*0.007)`. The fork's `drawGlowBillboards` treats size as a raw WORLD size, so passing `glowBaseSize` (~4.5-7) painted a 4.5-block blob over the entity.
- **Fix** (`ModuleTargetESP.kt` WonderfulCrystals glow loop): multiply each glow billboard size by `max(0.1, dist*0.007)` (same screen-scale factor wonderful used), `dist` = distance from camera to that billboard. Souls was unaffected (it used `drawStaticBillboard` = already world-sized).

### Root cause 2 — dull Souls/"Glow" (blend)
- `RenderCompat.blendFunc()` / `blendFuncSeparate()` are NO-OPs, and the compat pipeline hardcoded `BlendFunction.TRANSLUCENT`. wonderful's glow draws ADDITIVE (`blendFunc(770,1)` = SRC_ALPHA/ONE) so overlapping glows sum white-hot. The port silently got translucent → dull.
- **Fix** (`RenderCompat.kt`): added an OPT-IN `additiveBlend` flag + `RenderSystem.enableAdditive()`. When set, `CompatMeshSubmitter.pipelineFor` uses `BlendFunction.ADDITIVE`; otherwise TRANSLUCENT. The flag is part of the pipeline cache key (so additive/translucent variants of the same format/mode are distinct pipelines) and is auto-cleared by `defaultBlendFunc()`/`disableBlend()` so it never leaks past one module's draw.
- **CRITICAL design choice**: did NOT auto-detect additive from `blendFunc(dst==ONE)` args — Dreamcore (blendFuncSeparate dst=ONE), KillAnimation, Wings, TargetRenderer all pass dst=ONE and were tuned for the translucent no-op. Auto-detect would have flipped them and broken them (matches the failed "global additive" attempt noted earlier in this file). Only callers that EXPLICITLY call `enableAdditive()` get additive.
- Applied `enableAdditive()` to: WonderfulSouls glow + WonderfulCrystals glow. Souls' blend also changed from `ONE_MINUS_SRC_ALPHA` → `SRC_ALPHA, ONE` to match wonderful.

### Untouched (per user: Cube is fine)
- WonderfulCubes mode, standalone ModuleCubes — not modified. (They call `blendFunc(SRC_ALPHA, ONE)` but do NOT call `enableAdditive()`, so they keep their current accepted look. If the user later wants them brighter too, add `enableAdditive()` there.)

### Build / verify
- `assemble -x detekt` → **BUILD SUCCESSFUL** → `liquidbounce-0.31.2.jar`.
- NOT yet FCL-tested. Test: TargetESP → WonderfulCrystals (small orbiting diamonds + soft glow halo, NOT a blob); WonderfulSouls (bright/chói additive glow trails like Esp_Glow_WonderFun). Regression-check Dreamcore / RichGhost / RichCube / KillAnimation / Wings / JumpCircle still look unchanged (none call enableAdditive()).

### File reference
| Concern | File |
|---|---|
| Opt-in additive blend | `src/main/kotlin/.../render/compat/RenderCompat.kt` (additiveBlend, enableAdditive, pipelineFor, PipelineKey) |
| Crystal size + Souls additive | `src/main/kotlin/.../render/ModuleTargetESP.kt` (WonderfulCrystals glow loop, WonderfulSouls blend) |

## Wonderful Glow/Crystal blob — REAL fix: wrong blend constant (2026-06-12, Claude)

After the additive opt-in, user reported Glow(Souls) became a giant over-bright white BLOB bigger than the entity, and Crystal kept an oversized bright bloom per crystal (screenshots `Esp_Crystal.jpg`, `Es0_Glow.jpg`). Investigated deeper per request.

### Investigated wonderful's shaders (first time — had skipped these)
- `assets/wonderful/shaders/core/shaderesp/{glow,fill}.fsh` + `ShaderEsp.class`: these are a SEPARATE module's full **kawase-blur screen-space bloom** post-process pyramid (down/up sample), NOT used by the TargetESP modes. Red herring for this bug.
- The real issue is the BLEND CONSTANT mapping.

### Root cause (confirmed from MC BlendFunction source)
- `BlendFunction.ADDITIVE = (SourceFactor.ONE, DestFactor.ONE)`
- `BlendFunction.LIGHTNING = (SourceFactor.SRC_ALPHA, DestFactor.ONE)`
- wonderful uses `blendFunc(770, 1)` = `GL_SRC_ALPHA, GL_ONE` = **LIGHTNING**, NOT ADDITIVE.
- `bloom.png` is **white RGB across the ENTIRE texture** (corners included: `[255,255,255,0]`); the radial falloff lives ONLY in the alpha channel. With ADDITIVE the GPU IGNORES alpha and adds full white over the whole quad → every billboard becomes a solid white square. 120 overlapping Souls squares → one giant white blob; each crystal → a bright white square halo. With LIGHTNING the source is multiplied by alpha → the soft radial glow wonderful actually shows.

### Fix
- `RenderCompat.kt` `pipelineFor`: the opt-in additive path now uses `BlendFunction.LIGHTNING` (SRC_ALPHA/ONE) instead of `BlendFunction.ADDITIVE` (ONE/ONE). One-line change; the opt-in flag + cache key + Souls/Crystal `enableAdditive()` calls from the previous session are unchanged.
- The earlier crystal-glow screen-scale fix (`* max(0.1, dist*0.007)`) stays — that was a real second bug (size), independent of blend.

### Build / verify
- `assemble -x detekt` → **BUILD SUCCESSFUL** → `liquidbounce-0.31.2.jar`.
- NOT yet FCL-tested. Test: WonderfulSouls = soft bright orbiting glow trails (no white blob); WonderfulCrystals = small diamonds + soft glow halo. If still slightly too bright, the knob is the per-mode Opacity / glow alpha; if blob persists, re-check that enableAdditive() is actually reaching the compat layer (additiveBlend true at draw time).

## ModuleTargetESP DELETED (2026-06-12, Claude)

User requested full deletion of the module after iterating on render bugs (Crystal orbit wrong direction, Glow ugly with non-white colors, bloom too strong). The module had too many accumulated issues from porting wonderful's render approach through the fork's compat layer.

### What was removed
- `src/main/kotlin/.../render/ModuleTargetESP.kt` — the entire file (all modes: Dreamcore, RichCircle, RichGhosts, RichCube, WonderfulSouls, WonderfulCubes, WonderfulCrystals + CubeParticle helper).
- `src/main/resources/resources/liquidbounce/wonderful/targetesp/bloom.png` — wonderful's bloom asset for TargetESP modes.
- `src/main/resources/resources/liquidbounce/rich/targetesp/` — rich capture textures.
- `src/main/resources/resources/liquidbounce/dreamcore/` — dreamcore glow texture.
- `ModuleManager.kt` registration line for `ModuleTargetESP`.
- `en_us.json` lang key `liquidbounce.module.targetESP.description`.

### What STAYS
- `ModuleCubes.kt` — the standalone world Cubes module (unrelated, user said it works fine).
- `src/main/resources/resources/liquidbounce/wonderful/particle/bloom.png` — used by ModuleCubes.
- `RenderCompat.kt` additive blend opt-in (`enableAdditive()` / `BlendFunction.LIGHTNING`) — used by ModuleCubes and potentially future modules.
- `rich/particles/bloom.png` — used by other modules (JumpCircle, etc.).

### Build
- `clean assemble -x detekt` → **BUILD SUCCESSFUL** → `liquidbounce-0.31.2.jar`.

## Claude Rich TargetESP startup crash guard (2026-06-13, Codex)

User reported FCL crash after Claude's new Rich TargetESP port. FCL launcher log repeatedly showed
`java.lang.ExceptionInInitializerError` at `ModuleManager.registerInbuilt(...)`, while the game log did
not expose the underlying `Caused by`.

### What Codex changed
- `render/targetesp/ModuleTargetESP.kt`
  - #Edit By Codex 2026-06-13: Rich textures are no longer registered during `ModuleTargetESP` object
    initialization. Each texture is lazy-loaded only when its mode renders.
  - Texture load failures are caught and logged with the real throwable instead of crashing the client.
  - TargetESP render is guarded with `runCatching`; if a mode/pipeline throws at runtime, TargetESP disables
    itself instead of taking down the whole client.
  - `provider.endBatch()` and `PoseStack.pop()` are protected with `finally`.
- `render/targetesp/RichEspPipelines.kt`
  - #Edit By Codex 2026-06-13: Rich pipelines are lazy per mode. A bad pipeline should not fail the whole
    `RichEspPipelines` initializer.

### Why
- The crash happened while registering modules, before TargetESP could actually render. The dangerous
  initializer work in Claude's version was eager registration of four large Rich PNG textures.
- Source/build/jar paths contained all four assets, so this patch targets early resource/texture registration
  timing and adds real logging for any remaining texture/pipeline failure.

## FCL startup crash actual source: missing Dreamcore CustomModels texture (2026-06-13, Codex)

After the first TargetESP guard patch, FCL still crashed with `ExceptionInInitializerError` at
`ModuleManager.registerInbuilt(ModuleManager.kt:245)`. In this source tree line 245 is `ModuleCustomModels`,
not TargetESP.

### Root cause
- Claude's previous TargetESP deletion note removed `src/main/resources/resources/liquidbounce/dreamcore/`.
- `ModuleCustomModels.kt` still eagerly loaded `dreamcore/models/rabbit.png` during object initialization:
  `"dreamcore/models/rabbit.png".registerAsDynamicImageFromClientResources()`.
- `registerDynamicImageFromResources()` uses `getResourceAsStream(...)!!`; with the Dreamcore asset missing,
  this throws during module initialization and gets wrapped as `ExceptionInInitializerError`.

### Fix
- `ModuleCustomModels.kt`
  - #Edit By Codex 2026-06-13: Rabbit texture now lazy-loads through a guarded helper.
  - Missing texture is logged once and Rabbit rendering is skipped instead of crashing startup.
  - `shouldUseRabbit()` returns false when the texture is unavailable so armor/model rendering is not hidden.

## FCL startup crash follow-up: missing Wonderful Cubes bloom (2026-06-13, Codex)

After fixing `ModuleCustomModels`, FCL startup moved to
`ModuleManager.registerInbuilt(ModuleManager.kt:262)`, which maps to `ModuleCubes`.

### Root cause
- `ModuleCubes.kt` eagerly loads `wonderful/particle/bloom.png`.
- The source tree no longer had `src/main/resources/resources/liquidbounce/wonderful/particle/bloom.png`,
  even though older notes said this asset should stay for standalone Cubes.

### Fix
- Restored `src/main/resources/resources/liquidbounce/wonderful/particle/bloom.png` from
  `AnotherProject/WorkSpace/wonderful-1.0.0.jar` (`assets/wonderful/textures/particle/bloom.png`).
- `ModuleCubes.kt`
  - #Edit By Codex 2026-06-13: added an inline note next to the eager texture registration explaining that
    this asset must stay or startup will crash.

## TargetESP theme color sync + longer Ghost trail (2026-06-13, Codex)

User requested TargetESP color cleanup: the previous `Color1`/`Color2`/`Color3` settings were confusing and
not all modes visibly honored them. The desired behavior is to sync TargetESP colors with the ClickGUI
ThemeSystem/MixColor.

### Changes
- `render/targetesp/ModuleTargetESP.kt`
  - #Edit By Codex 2026-06-13: removed the separate `Color1`/`Color2`/`Color3` value settings from the
    render path.
  - Added `themeColor(progress)` using `ClickGuiTheme.accentA` and `ClickGuiTheme.accentB`, matching the
    ClickGUI-selected theme.
  - Rhomb, the then-existing Ghost mode, Chain, Crystals, and Circle now sampled the ClickGUI theme colors.
    Chain uses
    accent A/B directly; Crystals use a mixed theme color; hurt red overlay remains.
  - Ghost trail length increased by raising particle count and reducing alpha falloff, so the orbiting
    ghosts leave a longer visible trail.

## TargetESP ThunderHackV2 mode + remove Ghost mode (2026-06-13, Codex)

User reported the lengthened Ghost mode looked too worm-like and requested removing Ghost, then porting
ThunderHack's better Aura ESP mode from `AnotherProject/WorkSpace/ThunderHack-Recode-main`.

### Source checked
- `ThunderHack-Recode-main/src/main/java/thunder/hack/features/modules/combat/Aura.java`
  - `ESP.NextGen` calls `Render3DEngine.renderGhosts(espLength, espShaking, target)`.
- `ThunderHack-Recode-main/src/main/java/thunder/hack/utility/render/Render3DEngine.java`
  - `renderGhosts(...)` uses 3 orbiting firefly particle bands, default length 25, scale 0.27, alpha fade
    `255 - i * 15`.
- `TextureStorage.firefly`
  - `assets/thunderhack/textures/particles/firefly.png`.

### Changes
- `render/targetesp/ModuleTargetESP.kt`
  - #Edit By Codex 2026-06-13: removed `Mode.GHOST` from the enum and render dispatch.
  - Added `Mode.THUNDER_HACK_V2("ThunderHackV2")`.
  - Added `renderThunderHackV2(...)`, porting ThunderHack NextGen orbit math but keeping ClickGUI
    ThemeSystem colors instead of ThunderHack HudEditor colors.
  - Added lazy firefly texture registration at `thunderhack/particles/firefly.png`.
- `src/main/resources/resources/liquidbounce/thunderhack/particles/firefly.png`
  - Copied from ThunderHack Recode.
- `en_us.json`
  - TargetESP description now lists `ThunderHackV2` instead of `Ghost`.

## ModuleCubes startup crash hardening after Wonderful asset deletion (2026-06-13, Codex)

User confirmed Wonderfun/Wonderful assets were deleted while trying to clean old TargetESP assets. The new
FCL crash still reported `ModuleManager`, and the current source map points line 262 to `ModuleCubes`.

### Changes
- Restored `src/main/resources/resources/liquidbounce/wonderful/particle/bloom.png` from
  `AnotherProject/WorkSpace/wonderful-1.0.0.jar`
  (`assets/wonderful/textures/particle/bloom.png`).
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleCubes.kt`
  - #Edit By Codex 2026-06-13: replaced eager `registerAsDynamicImageFromClientResources()` with a lazy,
    guarded `glowTexture()` helper.
  - If the Wonderful bloom asset disappears again, Cubes logs the texture failure once and skips only the
    glow pass instead of crashing during `ModuleManager.registerInbuilt()`.

## TargetESP ThunderHackV2 orbit sync with Rich-Modern (2026-06-13, Codex)

User liked ThunderHackV2 rendering but reported the orbit looked slightly out of sync with the target.
Checked `AnotherProject/WorkSpace/Rich-Modern/src/main/java/rich/modules/impl/render/TargetESP.java`,
especially Rich's `renderGhost(...)` and `particle(...)`.

### Changes
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/targetesp/ModuleTargetESP.kt`
  - #Edit By Codex 2026-06-13: kept ThunderHackV2 firefly texture, scale, alpha fade, and render pipeline.
  - Adjusted ThunderHackV2 orbit center to `target.bbHeight * 0.5`, matching Rich-Modern's target-centered
    orbit instead of ThunderHack's fixed `0.75` Y offset.
  - Adjusted the 3 orbit band directions to Rich-Modern Ghost's direction pattern.
  - Added Rich-Modern-style per-particle Z spin so the orbit rotation direction matches Rich while the
    visual asset remains ThunderHack's firefly.

## TargetESP + Iris/Complementary shader bloom path (2026-06-14, Codex)

User provided TikTok frame extracts (`AnotherProject/debug/Video Frame Extractor 2026-06-13 18_52_54 GMT+7`),
ComplementaryUnbound r5.8.1, Iris 26.1 source, Sodium 26.1.2 source, and Celestial resource dump.

### Evidence
- Frames 2-30: no-shader TargetESP ghosts are bright sprites, but the world is flat-lit and there is no shader
  bloom/tonemap pickup.
- Frames 57+: shader-on ghosts look brighter because the scene is darker/tonemapped and the ghost pixels enter
  the shaderpack bloom/emissive path.
- Celestial dump in `AnotherProject/debug/celestial` is resources only, no TargetESP source.
- Iris 26.1 source:
  - Shaderpacks override known vanilla `RenderPipeline`s in `MixinShaderManager_Overrides`.
  - `IrisPipelines` maps `RenderPipelines.ENTITY_TRANSLUCENT_EMISSIVE` to `ShaderKey.ENTITIES_EYES_TRANS`
    (`gbuffers_spidereyes`) and maps custom unknown pipelines to nothing.
  - Vanilla entity draws are wrapped with `EntityRenderStateShard` / `GbufferPrograms.beginEntities()`.
- ComplementaryUnbound:
  - Has `gbuffers_spidereyes.glsl`, which boosts bright eye/emissive pixels before final composite bloom.
- Current TargetESP problem:
  - The normal `WorldRenderEvent` fires from `MixinGameRenderer` after `LevelRenderer.renderLevel(...)` returns.
    Iris finalizes/composites inside `LevelRenderer.renderLevel` before returning, so the old TargetESP path is
    too late for Complementary bloom.
  - TargetESP's custom `RichEspPipelines` are not in Iris' `IrisPipelines` map, so even if drawn earlier they are
    not shaderpack-known entity/emissive programs.

### Changes
- `MixinWorldRenderer.java`
  - #Edit By Codex 2026-06-14: injects into `LevelRenderer.lambda$addMainPass$0` after translucent entity
    features flush and calls `ModuleTargetESP.renderShaderWorldPass(cameraPos)`.
  - This is before Iris final composite, so shaderpacks can see TargetESP pixels.
- `render/targetesp/ModuleTargetESP.kt`
  - Added reflection-only Iris detection (`net.irisshaders.iris.api.v0.IrisApi.isShaderPackInUse`) so there is
    no compile-time Iris dependency.
  - Added a shader-world pass only for `Mode.THUNDER_HACK_V2` when an Iris shaderpack is active.
  - Shader pass uses vanilla `RenderTypes.entityTranslucentEmissive(texture, false)` instead of custom
    `RichEspPipelines`, so Iris maps it to Complementary's `gbuffers_spidereyes` path.
  - ThunderHackV2 vertices now write overlay, fullbright light (`0xF000F0`), and normal data so they satisfy
    vanilla `DefaultVertexFormat.ENTITY`; the old `POSITION_TEX_COLOR` path ignores these extra setters.
  - If the shader pass renders, the normal post-world `WorldRenderEvent` TargetESP render is skipped for that
    frame to prevent duplicate ghosts.
  - If the shader pass throws once, it disables itself and falls back to the normal post-world renderer.

### Build / verify
- `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk sh ./gradlew assemble -x detekt --no-daemon`
  → **BUILD SUCCESSFUL** → `build/libs/liquidbounce-0.31.2.jar`.

## Current TargetESP state after shader-all-modes request (2026-06-14, Codex)

- Latest active code: `ModuleTargetESP.renderShaderWorldPass(...)` supports all TargetESP modes, not only
  `ThunderHackV2`.
- Shaderpack/Iris active:
  - `Rhomb`, `ThunderHackV2`, `Chain`, and crystal bloom billboards render through
    `RenderTypes.entityTranslucentEmissive(...)`.
  - `Circle` and crystal body/glow render through `RenderTypes.lightning()`.
  - Normal post-world TargetESP render is skipped when the shader-world pass renders, preventing duplicates.
- Shaderpack/Iris inactive or shader pass fails:
  - TargetESP falls back to the normal old post-world renderer.
- Build verified:
  `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk sh ./gradlew assemble -x detekt --no-daemon`
  → **BUILD SUCCESSFUL**.

## TargetESP Rhomb shader depth placement fix (2026-06-14, Codex)

User reported the rotating square frame looked forced into the entity body after shader logic was applied to all
TargetESP modes.

### Cause
- Rhomb previously used `RichEspPipelines.romb`, whose pipeline has no depth test, so the billboard stayed visually
  in front of the target.
- The shader-world path changed Rhomb to vanilla `RenderTypes.entityTranslucentEmissive(...)`, which Iris maps to the
  shaderpack emissive path but also uses depth testing. Since the Rhomb billboard is centered on the entity, the
  target body can clip it and make it look pushed inside.

### Changes
- `ModuleTargetESP.kt`
  - Added `translateTowardCamera(...)`.
  - In shader Rhomb only (`renderType != null`), the billboard is pulled slightly toward the camera before applying
    the camera-facing rotation.
  - Normal no-shader Rhomb is unchanged.

### Build / verify
- `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk sh ./gradlew assemble -x detekt --no-daemon`
  → **BUILD SUCCESSFUL**.

## TargetESP shader logic applied to all modes (2026-06-14, Codex)

User confirmed ThunderHackV2 shader brightness/halo is fine and asked to apply the same shader logic to the remaining
TargetESP modes.

### Evidence
- Existing shader-world hook in `MixinWorldRenderer.java` already runs before Iris/Complementary final composite.
- `ModuleTargetESP.renderShaderWorldPass(Vec3)` was still gated to `Mode.THUNDER_HACK_V2`, so Rhomb, Chain,
  Crystals, and Circle still used the normal post-world `WorldRenderEvent` path after shader final composite.
- Iris 26.1 maps vanilla pipelines:
  - `RenderPipelines.ENTITY_TRANSLUCENT_EMISSIVE` → `ShaderKey.ENTITIES_EYES_TRANS`
  - `RenderPipelines.LIGHTNING` → `ShaderKey.LIGHTNING`
- Rhomb/ThunderHackV2/Chain/Crystal bloom are textured quads and can use `ENTITY_TRANSLUCENT_EMISSIVE`.
- Circle and crystal body/glow are color-only geometry, so they use vanilla `RenderTypes.lightning()` in the
  shader-world pass.

### Changes
- `render/targetesp/ModuleTargetESP.kt`
  - Removed the `Mode.THUNDER_HACK_V2` gate from `renderShaderWorldPass`.
  - Shader-world dispatch now handles all modes:
    - `Rhomb` → `RenderTypes.entityTranslucentEmissive(cubeTexture, false)`
    - `ThunderHackV2` → existing firefly emissive path
    - `Chain` → `RenderTypes.entityTranslucentEmissive(chainTexture, false)`
    - `Crystals` → crystal body/glow via `RenderTypes.lightning()`, bloom billboards via
      `RenderTypes.entityTranslucentEmissive(glowTexture, false)`
    - `Circle` → `RenderTypes.lightning()`
  - Added `addTexturedVertex(...)` so textured modes write overlay/light/normal only when using the vanilla entity
    emissive format.
  - If a shader-world mode renders, the normal post-world render is skipped to avoid duplicate ESP.

### Build / verify
- `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk sh ./gradlew assemble -x detekt --no-daemon`
  → **BUILD SUCCESSFUL** → `build/libs/liquidbounce-0.31.2.jar`.

- Jar-verified: `MixinWorldRenderer.class`, `ModuleTargetESP.class`, `ModuleTargetESP$IrisShaderPack.class`, and
  `resources/liquidbounce/thunderhack/particles/firefly.png`.
- Bytecode-verified: `ModuleTargetESP.renderShaderWorldPass(Vec3)` is a real static method for Java mixin calls.

### FCL test
- Install/run with Iris + Sodium + ComplementaryUnbound enabled.
- Enable KillAura + TargetESP, set TargetESP mode to `ThunderHackV2`, attack/select a target.
- Expected with shaderpack ON: ghosts render once, no duplicate trail, and the firefly ghosts should be picked up
  by Complementary bloom/emissive tonemapping more like the Celestial shader frames.
- Toggle shaderpack OFF or remove Iris: TargetESP should fall back to the old normal post-world renderer and still
  look like before.
- If startup/mixin crash mentions `lambda$addMainPass$0`, the injection point changed and the hook must be moved
  to the 1.26.2 `LevelRenderer` main-pass lambda after `renderTranslucentFeatures()` / second `BufferSource.endBatch()`.

## TargetESP ThunderHackV2 4 ghosts + stronger shader intensity (2026-06-14, Codex)

User confirmed the Iris/Complementary shader-world pass works, but the ghosts were only moderately bright and
ThunderHackV2 still had 3 orbiting ghosts. User also warned not to stretch the texture into a worm shape and pointed
to Celestial frame ranges:
- `AnotherProject/debug/Video Frame Extractor 2026-06-14 10_02_11 GMT+7`, frames 18-49.
- `AnotherProject/debug/Video Frame Extractor 2026-06-13 18_52_54 GMT+7`, frames 2-47.

### Evidence
- Celestial's look is four compact bright heads with short/medium cyan-magenta trails, not one stretched sprite.
- The apparent length comes from multiple fading billboards along a path; the head stays larger/brighter than the tail.
- The shader-on frames gain brightness through bloom/tonemap, so TargetESP needs stronger emissive pixels before
  Complementary's final composite, not a post-composite overlay.

### Changes
- `render/targetesp/ModuleTargetESP.kt`
  - ThunderHackV2 now renders 4 ghosts.
  - Replaced the old 3 hard-coded Rich-style offsets with 4 quarter-phased diagonal orbit planes. The 4th ghost is a
    real rotated path, not a duplicate of one of the old bands.
  - Extended the visible trail by using more fading billboards and slower alpha falloff instead of stretching the
    firefly texture.
  - Slightly increased billboard head size while keeping tail particles compact, so the shape stays firefly/ghost-like
    instead of worm-like.
  - Shader-world pass (`RenderTypes.entityTranslucentEmissive`) now mixes ghost colors further toward white, boosts
    alpha, and draws a small head-only halo/core. Normal no-shader rendering gets only a mild brightness bump.

### Build / verify
- `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk sh ./gradlew assemble -x detekt --no-daemon`
  → **BUILD SUCCESSFUL** → `build/libs/liquidbounce-0.31.2.jar`.

## TargetESP ThunderHackV2 RichClient route correction (2026-06-14, Codex)

This supersedes the previous 24-step diagonal 4-ghost attempt.

User reported the previous 4-ghost attempt looked bad: two ghosts felt like they shared an orbit, and the longer
tail looked like a sharp "earring" instead of a smooth ghost trail. User then added the intended reference source:
`AnotherProject/debug/RichClient no webhook/RichClient`.

### Evidence
- `RichClient no webhook/RichClient/src/main/java/fun/rich/features/impl/render/TargetESP.java`
  - Ghosts mode delegates to `Render3D.drawGhosts(lastTarget, anim, red, 0.62F)`.
- `RichClient no webhook/RichClient/src/main/java/fun/rich/utils/display/geometry/Render3D.java`
  - `drawGhosts(...)` already renders 4 ghosts.
  - Uses `length = 10`, so only 11 billboards per ghost. The previous LiquidBounce attempt used 23-24, which made
    the trail too kinked/long.
  - Uses a smooth circular base orbit with `j * 90` phase separation.
  - Uses alternating per-ghost vertical sine waves, so each ghost has a distinct route even though the horizontal
    orbit is circular.
  - Uses a soft bloom texture rather than ThunderHack's pointed firefly texture.

### Changes
- `render/targetesp/ModuleTargetESP.kt`
  - ThunderHackV2 now prefers `rich/particle/ghost-glow.png` and only falls back to the ThunderHack firefly texture
    if the Rich texture fails.
  - Normal fallback path uses `RichEspPipelines.ghosts(texture)` instead of the Rhomb no-depth pipeline.
  - Replaced the previous diagonal 24-step orbit with the RichClient-style 4-ghost orbit:
    short 10-step trail, 90-degree phase offsets, smooth circular motion, alternating vertical sine.
  - Kept the existing Iris/Complementary shader logic: shader pass still uses
    `RenderTypes.entityTranslucentEmissive(...)`.
  - Shader intensity is preserved through color/alpha boost and a small head-core redraw, but the route no longer
    relies on excessive trail length.

### Build / verify
- `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk sh ./gradlew assemble -x detekt --no-daemon`
  → **BUILD SUCCESSFUL** → `build/libs/liquidbounce-0.31.2.jar`.
- Jar-verified: `ModuleTargetESP.class`, `resources/liquidbounce/rich/particle/ghost-glow.png`, and
  `resources/liquidbounce/thunderhack/particles/firefly.png`.

### Restore note
- User rejected this RichClient route/texture result ("That's terrible") and asked to restore it.
- Restored `ModuleTargetESP.kt` to the previous ThunderHack firefly 4-ghost shader-intensity version:
  - `fireflyTexture` is again the primary ThunderHackV2 texture.
  - Shader pass still uses `RenderTypes.entityTranslucentEmissive(...)`.
  - Normal fallback again uses `RichEspPipelines.romb(texture)`.
  - The 24-step diagonal 4-ghost route from the prior attempt is restored.
- Rebuilt with:
  `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk sh ./gradlew assemble -x detekt --no-daemon`
  → **BUILD SUCCESSFUL**.

## TargetESP ThunderHackV2 oversized shader halo removed (2026-06-14, Codex)

User reported the shader brightness is good, but when a ghost particle faces the camera it creates a large radiating
"aura" region around the particle.

### Cause
- The shader pass had an extra head halo for `i <= 4`:
  `drawEntityReadyQuad(..., ghostScale * (1.48F - i * 0.07F), haloColor)`.
- This deliberately oversized quad made Complementary bloom pick up a broad transparent region around the firefly
  sprite, especially when the billboard faced the viewer directly.

### Changes
- `render/targetesp/ModuleTargetESP.kt`
  - Removed the oversized shader-only halo quad.
  - Kept the normal firefly quad and the small core redraw for `i <= 1`, so brightness stays concentrated without
    the large outer aura.
  - Kept the Iris/Complementary shader-world pass unchanged.

### Build / verify
- `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk sh ./gradlew assemble -x detekt --no-daemon`
  → **BUILD SUCCESSFUL** → `build/libs/liquidbounce-0.31.2.jar`.

## Current TargetESP state after shader-all-modes request (2026-06-14, Codex)

- Latest active code: `ModuleTargetESP.renderShaderWorldPass(...)` supports all TargetESP modes, not only
  `ThunderHackV2`.
- Shaderpack/Iris active:
  - `Rhomb`, `ThunderHackV2`, `Chain`, and crystal bloom billboards render through
    `RenderTypes.entityTranslucentEmissive(...)`.
  - `Circle` and crystal body/glow render through `RenderTypes.lightning()`.
  - Normal post-world TargetESP render is skipped when the shader-world pass renders, preventing duplicates.
- Shaderpack/Iris inactive or shader pass fails:
  - TargetESP falls back to the normal old post-world renderer.
- Build verified:
  `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk sh ./gradlew assemble -x detekt --no-daemon`
  → **BUILD SUCCESSFUL**.

## PopChams startup crash fixed (2026-06-15, Codex)

User reported Claude's new `ModulePopChams` crashed on FCL. FCL wrapper log
`/storage/emulated/0/FCL/log/latest_game.log` repeated `ExceptionInInitializerError` at
`ModuleManager.registerInbuilt(ModuleManager.kt:267)`, which maps to `ModulePopChams`.

### Root cause
- `TotemPopEvent` was added in `event/events/EntityEvents.kt`, and `ModulePopChams` registers
  `handler<TotemPopEvent>` during object initialization.
- `EventManager.ALL_EVENT_CLASSES` did not include `TotemPopEvent`, so
  `EventManager.registerEventHook(...)` threw `error("The event ... is not registered")`.
- That init-time throw was wrapped by Kotlin/JVM as `ExceptionInInitializerError`, and FCL hid the real cause.

### Changes
- `event/EventManager.kt`
  - Added `TotemPopEvent::class` to `ALL_EVENT_CLASSES`.
- `render/popchams/ModulePopChams.kt`
  - Pop handler now accepts only `AbstractClientPlayer` entities before cloning/reading skin textures.
  - `Person` now receives `initialAlpha` explicitly instead of reading `ModulePopChams.color` inside the nested
    class initializer.

### Build / verify
- `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk sh ./gradlew assemble -x detekt --no-daemon`
  → **BUILD SUCCESSFUL**.

## FakePlayer local hit damage + AutoTotem test path (2026-06-15, Codex)

User wanted `ModuleFakePlayer` to actually take damage when hit by the local user so its `AutoTotem` setting can
activate.

### Cause
- The module had a melee `AttackEntityEvent` handler, but the fake clone is client-only. Vanilla
  `MultiPlayerGameMode.attack` sends `ServerboundAttackPacket(entityId)` before running local attack effects, and
  `Entity.hurtOrSimulate(...)` returns false on the client for normal entities.
- Result: relying on vanilla attack does not produce reliable client-side fake-player damage, and it can send an
  invalid fake entity id to the server.

### Changes
- `features/module/modules/misc/fakeplayer/ModuleFakePlayer.kt`
  - If `AutoTotem` is enabled, the clone now gets a totem immediately on spawn instead of waiting for the next tick.
  - Attacks against the fake clone are canceled at `AttackEntityEvent` after local simulation, preventing the fake
    entity id attack packet from reaching the server.
  - Local hit simulation now reduces absorption/health, calls `handleDamageEvent(...)` + `animateHurt(...)` so the
    fake player visibly flashes/hurts, then runs the existing totem-pop check.
  - `handleTotemPop` now plays local totem particles and the totem-use sound before firing `TotemPopEvent`, so
    PopChams and visual feedback both trigger.

### Build / verify
- `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk sh ./gradlew assemble -x detekt --no-daemon`
  → **BUILD SUCCESSFUL**.

### Follow-up: hits still looked like no damage
- The first patch still used `target.getEffectiveDamage(...)`; that helper returns `0` in Peaceful difficulty and
  also the clone spawned with long Regen/Absorption/Resistance effects, masking damage.
- Follow-up fix in `ModuleFakePlayer.kt`:
  - Removed the spawn-time Regen/Absorption/Resistance dummy effects.
  - Spawn now resets to normal max health and zero absorption.
  - Melee simulation now uses raw local attack damage with a `MIN_MELEE_DAMAGE = 1.0f` floor, so manual hits damage
    the fake player even in Peaceful/client-only testing worlds.
- Build verified again:
  `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk sh ./gradlew assemble -x detekt --no-daemon`
  → **BUILD SUCCESSFUL**.

## PopChams VCore render-size/brightness correction (2026-06-15, Codex)

User reported PopChams rendered as an oversized white player model after a totem pop, making the body shape/detail
hard to see.

### VCore evidence
- Source checked:
  `AnotherProject/WorkSpace/VCore-0.8-main/VCore-0.8-main/vcore/features/modules/render/PopChams.java`
- VCore does call `prepareScale(-1,-1,1; 1.6,1.8,1.6; translate -1.501)`, but it also constructs a dedicated
  `PlayerEntityModel` per pop and immediately applies `modelPlayer.method_2838().scale(new Vector3f(-0.3F, ...))`.
- VCore renders the model with light value `10`, not full-bright `0xF000F0`.

### Fix
- `features/module/modules/render/popchams/ModulePopChams.kt`
  - Added `VCORE_ROOT_SCALE_OFFSET = -0.3f` and applies it temporarily to the vanilla renderer model root only
    during the PopChams draw, then restores the model.
  - Normal render pass now uses VCore's light value `10` so the model is not full-bright white.
  - Shader pass still uses full-bright/emissive so Iris/Complementary bloom logic remains active.
  - Second-layer visibility is now saved/restored so PopChams does not permanently mutate the shared vanilla
    player renderer model.

### Build / verify
- `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk sh ./gradlew assemble -x detekt --no-daemon`
  → **BUILD SUCCESSFUL**.

## PopChams fallback color/timing correction (2026-06-15, Codex)

User repro config: `Mode=Simple`, `SecondLayer=false`, `Color=#FF0000`, `YSpeed=1`, `AlphaSpeed=35`,
`RotationSpeed=0`. With Iris/shader pass ON the pop model was red and obeyed the settings; with shader OFF the
fallback model turned black and visually faded/rose too fast.

### Root cause
- VCore's Simple path renders raw unlit POSITION geometry and applies `RenderSystem.setShaderColor(red, alpha)`.
- The 1.26.2 fallback port used `RenderTypes.entityTranslucent(...)` with light value `10`. That vanilla lit entity
  material multiplies the configured red by lightmap/per-face lighting, so `#FF0000` becomes nearly black without the
  shader/emissive path.
- The clone Y position was also advanced inside `renderEntity(...)`. That makes vertical speed render-path/FPS
  dependent, while `AlphaSpeed` is tick based.

### Fix
- `ModulePopChams.kt`
  - #Edit By Codex 2026-06-15: each `Person` now stores `previousYOffset/yOffset` and `previousAlpha/alpha`.
  - `GameTickEvent` advances Y and alpha once per tick; both shader-world and fallback render paths only interpolate
    that state.
  - No-shader fallback now uses `RenderTypes.eyes(textureFor(person))`, an unlit translucent entity material, so the
    configured `Color` and alpha remain visible without Iris.
  - Shader-world path still uses `RenderTypes.entityTranslucentEmissive(...)` for Iris/Complementary bloom pickup.
  - VCore transform/model sizing (`prepareScale` + root scale offset `-0.3`) was left untouched for the next
    side-by-side visual pass.

### Build / verify
- `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk sh ./gradlew assemble -x detekt --no-daemon`
  → **BUILD SUCCESSFUL**.

### FCL test next
- Shader OFF: pop a totem/fake player with the repro config. Expected: red Simple model rises slowly and fades at the
  configured rate, not black.
- Shader ON: confirm it still renders once through the shader-world pass and remains bright/red.
- Next visual pass: compare model height/width against VCore. If still too large, tune the root scale/matrix scale
  separately; do not mix that with the fallback color fix.

## PopChams vanilla player model scale correction (2026-06-15, Codex)

User reported the fallback/rendering color was fine, but the PopChams model still did not fit the actual player skin
model: head/body dimensions looked off in the debug screenshot.

### Source/mapping evidence
- `EntityRenderDispatcher.getRenderer(AbstractClientPlayer)` dispatches to `getAvatarRenderer(...)`.
- `getAvatarRenderer(...)` reads `entity.getSkin().model()` and selects `PlayerModelType.WIDE` or
  `PlayerModelType.SLIM`.
- `AvatarRenderer(context, slimSteve)` bakes `ModelLayers.PLAYER` or `ModelLayers.PLAYER_SLIM` and creates
  `PlayerModel(..., slimSteve)`.
- `AvatarRenderer.scale(...)` applies vanilla player scale `0.9375F`.
- Vanilla `LivingEntityRenderer.submit(...)` applies: entity scale → body rotation → `scale(-1,-1,1)` →
  renderer scale (`0.9375F` for avatars) → translate `-1.501F`.

### Root cause
- `ModulePopChams.kt` was already getting the correct WIDE/SLIM renderer from `EntityRenderDispatcher`, but then it
  bypassed the vanilla avatar transform.
- It still used VCore's raw-model transform: `scale(-1,-1,1)` + `scale(1.6,1.8,1.6)` + root model scale offset
  `-0.3`. On 1.26.2 this makes the PopChams model larger/differently proportioned than the real player renderer.

### Fix
- `ModulePopChams.kt`
  - #Edit By Codex 2026-06-15: render-state extraction now happens before the model transform, so PopChams uses the
    renderer's interpolated `bodyRot`, `scale`, and crouch state.
  - Replaced `prepareScale(...)` with `preparePlayerScale(...)` mirroring vanilla avatar transform:
    crouch render offset, `state.scale`, yaw, `scale(-1,-1,1)`, `0.9375F`, `translate(0,-1.501,0)`.
  - Removed the VCore root-scale mutation (`-0.3`) from the shared vanilla renderer model.
  - Kept `SecondLayer` visibility save/restore, fallback color fix, and shader-world path unchanged.

### Build / verify
- `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk sh ./gradlew assemble -x detekt --no-daemon`
  → **BUILD SUCCESSFUL**.

### FCL test next
- Use the same fake-player/totem-pop screenshot setup.
- Expected: PopChams head/body/arms match the real player model proportions much more closely, including slim skins.
- If it now feels too small compared with VCore's stylized original, add a small explicit `ModelScale` setting later;
  do not reintroduce the VCore `1.6/1.8` raw scale as the default.

## PopChams ClickGUI theme color sync (2026-06-15, Codex)

User requested deleting PopChams' separate `Color` setting and syncing the effect colour with the ClickGUI
ThemeSystem/MixColor.

### Source checked
- `ClickGuiTheme.kt`: shared theme object with `accentA`/`accentB`; ClickGUI persists `ClickGuiTheme.selected`.
- `ModuleTargetESP.kt`: already uses `ClickGuiTheme.accentA/accentB` + `lerpColor(...)` to sync render colors.
- `ModulePopChams.kt`: still exposed `color("Color", ...)`, used `color.a` as initial alpha, and built the render
  color from `color.r/g/b`.

### Fix
- `ModulePopChams.kt`
  - Removed the `Color` value setting and `Color4b` import. ClickGUI no longer shows a PopChams-specific color row.
  - Added `ClickGuiTheme` import.
  - Pop instances now start at `INITIAL_ALPHA = 255`.
  - `colorWithAlpha(alpha)` now samples `ClickGuiTheme.accentA -> accentB` with fade progress and applies the
    current PopChams alpha over that theme color.
  - Added local `themeColor`, `lerpColor`, and `withAlpha` helpers, matching the TargetESP pattern.
  - Shader and no-shader paths both use the same theme-derived color.

### Build / verify
- `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk sh ./gradlew assemble -x detekt --no-daemon`
  → **BUILD SUCCESSFUL**.

### FCL test next
- Open ClickGUI → Theme page → switch themes.
- Pop a totem/fake player with PopChams enabled. Expected: model color follows the selected ClickGUI theme, blending
  from accent A toward accent B as it fades. There should be no `Color` setting under PopChams.

## Offhand backup port + Argon combat macro ports (2026-06-15, Codex)

User asked to replace current Offhand with the 1.21.4 backup implementation, then port Argon's crystal/anchor macro
modules from `AnotherProject/debug/argon-main`.

### Offhand restore from 1.21.4 backup
- Source read:
  - `backups/liquidbounce-1.21.4-backup-20260517/src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/player/offhand/ModuleOffhand.kt`
  - `backups/liquidbounce-1.21.4-backup-20260517/src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/player/offhand/Totem.kt`
- Current files changed:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/player/offhand/ModuleOffhand.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/player/offhand/Totem.kt`
- Restored behavior to match the backup while keeping required 1.26.2 mapping/API adaptations.
- Removed the newer local GrimStop movement/sprint suppression handlers from `ModuleOffhand.kt`.
- Removed `Totem.TotemGuard.StopSprint` and `StopInput` settings.
- Registration in `ModuleManager` was already intact; no registration change needed.

### Argon AutoCrystal port
- Source read:
  - `AnotherProject/debug/argon-main/src/main/java/dev/lvstrng/argon/module/modules/combat/AutoCrystal.java`
  - Argon helpers: `CrystalUtils`, `InventoryUtils`, `WorldUtils`, `BlockUtils`
- Added standalone module:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleAutoCrystal.kt`
- Registered in:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt`
- Ported settings/logic:
  - `ActivateKey` default right mouse, `PlaceDelay`, `BreakDelay`, `PlaceChance`, `BreakChance`
  - `StopOnKill`, `FakePunch`, `ClickSimulation`, `DamageTick`, `AntiWeakness`, `ParticleChance`
  - Requires holding end crystals, uses the current crosshair target, places on obsidian/bedrock, breaks crystals/slimes
  - Cancels vanilla item use on obsidian/bedrock through existing `PlayerInteractItemEvent`
  - Anti-weakness silently swaps to a sword from hotbar, attacks, then swaps back
- No Argon utility classes were copied. Local equivalents used: `interaction`, `Slots.Hotbar`, item tag helpers,
  `InputTracker`, and a module-local crystal placement check.
- Caveat: Argon's `ClickSimulation` feeds Argon's own CPS/mouse simulator. This fork has no matching global CPS
  simulator, so the setting is preserved and counted internally, but it does not create a visible CPS HUD signal.

### Argon AutoAnchor / AnchorMacro port
- Argon does not have a class literally named `AutoAnchor`; matching source is:
  - `AnotherProject/debug/argon-main/src/main/java/dev/lvstrng/argon/module/modules/combat/AnchorMacro.java`
- `DoubleAnchor.java` was inspected but NOT ported; it is a separate helper module.
- Added standalone module:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleAutoAnchor.kt`
- Registered in:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt`
- Ported settings/logic:
  - Hold right mouse over a respawn anchor
  - `WhileUse`, `StopOnKill`, `ClickSimulation`, `SwitchDelay`, `SwitchChance`
  - `PlaceChance`, `GlowstoneDelay`, `GlowstoneChance`
  - `ExplodeDelay`, `ExplodeChance`, `ExplodeSlot`, `OnlyOwn`, `OnlyCharge`
  - Swaps to glowstone and charges uncharged anchors
  - Swaps to configured slot and detonates charged anchors unless `OnlyCharge` is enabled
  - Tracks owned anchors from module/crosshair right-click state without adding a new block-use mixin
- Caveat: because this fork only exposes `PlayerInteractItemEvent` for item use and not a full Argon-style
  `doItemUse` block-use event, `OnlyOwn` tracking is module-local/crosshair-based. Manual anchor placements outside
  this module may not be tracked.

### Build / verify
- Offhand restore build:
  - `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
  - Result: **BUILD SUCCESSFUL**.
- AutoCrystal build:
  - Same command.
  - Result: **BUILD SUCCESSFUL**.
  - Verified `ModuleAutoCrystal` exists inside `build/libs/liquidbounce-0.31.2.jar`.
- AutoAnchor build:
  - Same command.
  - Result: **BUILD SUCCESSFUL**.
  - Verified `ModuleAutoAnchor` exists inside `build/libs/liquidbounce-0.31.2.jar`.

### FCL test next
- Offhand:
  - Enable `Offhand`; test Totem low-HP switch, `H` cycle, Gapple/Crystal/Strength binds.
  - Expected: 1.21.4 backup behavior; no `StopSprint` / `StopInput` GrimStop settings.
- AutoCrystal:
  - Enable `AutoCrystal`; hold end crystals and hold right mouse.
  - Aim at obsidian/bedrock to place, aim at crystal/slime to break.
  - Test `FakePunch`, `DamageTick`, and `AntiWeakness`.
- AutoAnchor:
  - Enable `AutoAnchor`; hold right mouse while aiming at a respawn anchor.
  - Expected: uncharged anchor switches to glowstone and charges; charged anchor switches to `ExplodeSlot` and
    detonates unless `OnlyCharge` is enabled.

## AutoCrystal Ghost Crystal / Grim MultiPlace fix (2026-06-15, Codex)

User reported "Ghost Crystal": with `PlaceDelay=0` and `BreakDelay=0`, holding right mouse rapidly lowered the
client-side crystal stack count, but after stopping the count server-resynced and only 1-2 real crystals had placed.
Grim logged repeated `MultiPlace` flags where `pos`/`face` were often the same but `cursor` differed slightly.

### Evidence
- Grim `MultiPlace.java` stores the first block-place packet in a tick and flags a second place before the next tick
  packet when `face`, `cursor`, or `pos` differs. It is not checking "cursor too still"; it is checking multiple
  places in one tick.
- User tested `AutoCrystal.ActivateKey=C` instead of right mouse. `MultiPlace` almost disappeared, confirming the
  right-mouse path was double-sending: vanilla `useItemOn` plus the module's manual `interaction.interactBlock(...)`.
- `MultiPlayerGameMode.useItemOn(...)` performs local item-use prediction before sending
  `ServerboundUseItemOnPacket`, so server/Grim rejection explains the temporary fake hotbar count.
- Current `ModuleAutoCrystal.shouldPlaceOn(...)` had the Argon precedence bug:
  `obsidian || bedrock && canPlace`, so obsidian skipped the crystal-space occupancy check.

### Fix
- Added `PlayerInteractBlockEvent` and registered it in `EventManager.ALL_EVENT_CLASSES`.
- Added a `MixinClientPlayerInteractionManager.useItemOn` HEAD hook that can cancel vanilla block-use before local
  prediction and packet send.
- `ModuleAutoCrystal.kt`
  - #Edit By Codex 2026-06-15: cancels vanilla `useItemOn` only when the local player is holding end crystals,
    AutoCrystal activation is pressed, main hand is used, and the targeted block is a valid crystal base.
  - Fixed `shouldPlaceOn(...)` to require `canPlaceCrystalClientAssumeBase(pos)` for both obsidian and bedrock.

### Build / verify
- `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
  → **BUILD SUCCESSFUL**.

### FCL test next
- Set `AutoCrystal.ActivateKey=RIGHT`, `PlaceDelay=0`, `BreakDelay=0`.
- Hold right mouse on obsidian/bedrock with crystals.
- Expected: behavior should match the earlier `C` key test: no duplicate vanilla/module place packet, greatly reduced
  or eliminated Grim `MultiPlace`, and fewer fake crystal stack-count drops.
- Regression-check normal right-click crystal use with AutoCrystal disabled.

## AutoCrystal one-key break loop correction (2026-06-15, Codex)

User clarified the previous fix exposed another behavior issue:
- With `ActivateKey=Y`, manually right-clicking to place a crystal on obsidian, then pressing `Y`, caused AutoCrystal
  to break that manually placed crystal.
- Desired behavior: hold one activation key and let the module place then break its own crystal automatically. Manual
  right-clicked crystals should not be treated as AutoCrystal-owned break targets by default.

### Root cause
- The Argon-style port broke any crosshair `EndCrystal` when activation was pressed. It did not distinguish crystals
  placed by the module from crystals placed manually/externally.
- The module also only broke `EntityHitResult` under the crosshair, so a clean one-key loop still depended on the
  crosshair/server entity update lining up.

### Fix
- `ModuleAutoCrystal.kt`
  - Added `BreakOnlyPlaced` setting, default `true`.
  - Tracks base block positions when the module's own `interactBlock(...)` placement succeeds.
  - While activation is held, scans for an `EndCrystal` above a tracked base and attacks it automatically, without
    needing a second manual click.
  - Crosshair entity breaking now respects `BreakOnlyPlaced`; manual right-clicked crystals are ignored unless the
    setting is disabled.
  - Clears tracked placements on disable, world change, activation release, or after a short timeout.

### Build / verify
- `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
  → **BUILD SUCCESSFUL**.

### FCL test next
- Put crystals in hand, set `ActivateKey=Y` or another single key, `PlaceDelay=0`, `BreakDelay=0`,
  `BreakOnlyPlaced=true`.
- Hold the activation key while aiming at obsidian/bedrock.
- Expected: module places and then breaks the module-owned crystal as soon as it appears, without requiring manual
  right-click then activation.
- Manual test: with `ActivateKey=Y`, place a crystal manually with right-click, then tap `Y`. Expected:
  AutoCrystal should not break that manual crystal while `BreakOnlyPlaced=true`.

## AutoCrystal aggressive CPVP behavior restored (2026-06-15, Codex)

This supersedes the previous `BreakOnlyPlaced=true` note. User correctly pointed out that AutoCrystal should not act
like a gentle/manual helper; it is a CPVP macro that should place crystals on obsidian/bedrock and break crystals
aggressively while one activation key is held.

### Fix
- `ModuleAutoCrystal.kt`
  - Removed `BreakOnlyPlaced`.
  - Restored original behavior where activation breaks crosshair `EndCrystal`/`Slime`.
  - Kept module-owned placement tracking, but now uses it only as an extra fast-break source, not as a restriction.
  - Added `breakAvailableCrystal(...)`: while activation is held, it scans for an `EndCrystal` above the aimed
    obsidian/bedrock base or above a tracked module placement and attacks it immediately.
  - Kept the earlier right-click duplicate `useItemOn` cancellation and obsidian placement predicate fix.

### Build / verify
- `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
  → **BUILD SUCCESSFUL**.

### FCL test next
- Set one activation key such as `Y` or `1`, `PlaceDelay=0`, `BreakDelay=0`.
- Hold that key while aiming at obsidian/bedrock with crystals in hand.
- Expected: AutoCrystal places on the base and breaks any crystal above that aimed base / crosshair crystal
  aggressively. It should no longer require manual right-click + activation.

## AutoCrystal RIGHT activation self-cancel fix (2026-06-15, Codex)

User reported that after the aggressive restore, `ActivateKey=RIGHT` only placed the crystal and did not break it.

### Root cause
- The `PlayerInteractBlockEvent` hook correctly cancels vanilla right-click `useItemOn` to avoid duplicate
  `MultiPlace`, but it could also intercept AutoCrystal's own `interaction.interactBlock(...)` placement call.
- That made RIGHT activation inconsistent and could prevent the module-owned placement from being tracked for the
  fast break pass.

### Fix
- `ModuleAutoCrystal.kt`
  - Added `moduleOwnedBlockUse` guard.
  - AutoCrystal sets this guard only around its own `interaction.interactBlock(...)` call.
  - The block-use event handler now skips cancellation while this guard is set, so vanilla RIGHT is suppressed but
    the module's own placement is allowed and tracked.
  - `breakAvailableCrystal(...)` now breaks a crosshair `EndCrystal`/`Slime` before trying placement/base scanning.
  - Increased tracked placement timeout from 12 ticks to 40 ticks for higher-ping servers.

### Build / verify
- `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
  → **BUILD SUCCESSFUL**.

### FCL test next
- Set `ActivateKey=RIGHT`, `PlaceDelay=0`, `BreakDelay=0`.
- Hold right mouse on obsidian/bedrock with crystals in hand.
- Expected: vanilla duplicate place is still cancelled, but AutoCrystal's own place is allowed, tracked, and followed
  by an automatic crystal break.

## AutoCrystal Argon break behavior restored after Reach/Hitboxes (2026-06-15, Codex)

This supersedes the "one-key break loop" and "RIGHT activation self-cancel" scan/tracking behavior above.

User reported Grim `Reach` and `Hitboxes` after the aggressive restore. Re-read:
- Argon `AutoCrystal.java`: only breaks `mc.crosshairTarget instanceof EntityHitResult`.
- Grim `Reach.java`: queues attack packets, then ray-checks the player's look against the attacked entity. `Hitboxes`
  is raised when the look ray never intersects the target box; `Reach` is raised when the intercept distance exceeds
  allowed reach. End crystals are explicitly checked with a hardcoded crystal box.

### Root cause
- Codex-added `breakAvailableCrystal(...)` scanned `world.entitiesForRendering()` and attacked a crystal above the
  aimed obsidian/bedrock base or tracked placement even while the crosshair was still on the block.
- That can send an attack packet for a crystal the player is not actually ray-targeting, causing Grim `Hitboxes`, and
  can attack beyond normal interaction reach, causing Grim `Reach`.

### Fix
- `ModuleAutoCrystal.kt`
  - Removed `trackedCrystalBases`, `breakAvailableCrystal(...)`, `WorldChangeEvent` tracking, crystal-base scan, and
    all tracked-placement timeout constants.
  - Restored Argon-faithful break behavior: break only the current `EntityHitResult` (`EndCrystal`/`Slime`, or any
    entity when `FakePunch` is enabled).
  - Kept only the MultiPlace protection: `PlayerInteractBlockEvent`/`useItemOn` cancel for vanilla right-click, with
    `moduleOwnedBlockUse` allowing AutoCrystal's own `interaction.interactBlock(...)` call through.
  - Kept the valid crystal-space check before placement to avoid client/server ghost placement; it does not attack
    entities and should not affect Reach/Hitboxes.

### Build / verify
- `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
  → **BUILD SUCCESSFUL**.

### FCL test next
- Test `ActivateKey=RIGHT`, `PlaceDelay=0`, `BreakDelay=0`.
- Expected: no Grim `MultiPlace` duplicate-place spam from vanilla+module right-click, and no extra Grim
  `Reach`/`Hitboxes` from attacking crystals outside the actual crosshair entity hit.
- Behavior is again Argon-style: after placing, the module breaks when the crosshair target becomes the crystal.

## Prestige AutoHitCrystal port (2026-06-15, Codex)

User requested porting Prestige's `AutoHitCrystal` from
`AnotherProject/WorkSpace/prestige-src-main`, with full logic, no redundant utility copies, and minimal edits.

### Source checked
- `prestige-src-main/src/main/java/dev/zprestige/prestige/client/module/impl/combat/AutoHitCrystal.java`
- Direct helpers only for behavior mapping:
  - `TimerUtil` / `DragSetting` for randomized millisecond delay.
  - `InventoryUtil` for hotbar slot behavior.
  - `BlockUtil` for entity collision box semantics.
  - `EntityUtil` / `OneLineUtil` for target and pause-on-kill logic.
  - `RotationUtil` / `MoveEvent` for the pitch-only fallback crystal hit.

### Changes
- Added `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleAutoHitCrystal.kt`.
- Registered `ModuleAutoHitCrystal` in `ModuleManager.kt`.
- Added `liquidbounce.module.autoHitCrystal.description` to `en_us.json`.

### Ported behavior
- State machine mirrors Prestige:
  1. switch to obsidian,
  2. place obsidian or skip if already aiming at obsidian,
  3. switch to end crystal,
  4. place crystal,
  5. break crystal,
  6. optionally place/break a second crystal for `DoubleTap`.
- Settings:
  - `Delay` as `30..50ms` randomized range, configurable `0..200ms`.
  - `Crystal`: `None`, `SingleTap`, `DoubleTap`.
  - `Silent`, `PerfectTiming`, `PauseOnKill`.
- No Prestige utility classes were copied. Collision, target lookup, hotbar switching, pitch calculation, and timing are
  local to the module or use existing project APIs.
- Intentional correction: Prestige's `findItemInHotbar` returns slot `0` when missing; the port treats missing
  obsidian/crystals as missing instead of accidentally selecting slot 0.

### Build / verify
- `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
  → **BUILD SUCCESSFUL**.
- Jar verified:
  - `ModuleAutoHitCrystal.class`
  - `ModuleAutoHitCrystal$CrystalMode.class`

### FCL test next
- Enable `AutoHitCrystal`.
- Put obsidian and end crystals in hotbar.
- Aim at a block face near a target and enable/trigger the module.
- Expected:
  - `SingleTap`: switches to obsidian, places/uses obsidian base, switches to crystal, places crystal, breaks one
    crystal, then disables.
  - `DoubleTap`: repeats the place/break crystal step once more before disabling.
  - `PerfectTiming=true`: waits while the selected nearby target still has non-zero `hurtTime`.
  - `PauseOnKill=true`: pauses if a dead nearby player body is detected.

## Prestige SilentAim port (2026-06-15, Codex)

User requested Prestige's `SilentAim` module from `AnotherProject/WorkSpace/prestige-src-main`.

### Source checked
- `prestige-src-main/src/main/java/dev/zprestige/prestige/client/module/impl/combat/SilentAim.java`
- Direct framework dependencies:
  - `IRotatable`
  - `RotationManager`
  - `SwingHandEvent`
  - `EntityUtil`
  - `RotationUtil`

### Source behavior
- `SilentAim` registers as an `IRotatable` only while enabled.
- On a main-hand swing:
  - if not already doing its own redirected click, it requires:
    - `Weapon Only=false`, or main hand is sword/axe,
    - crosshair target is `MISS`,
    - Prestige's current player target exists,
    - target is either within 3 blocks or visible.
  - then cancels the original swing and sets `preClick=true`.
- On the rotation pass:
  - if `preClick=true`, it swings/attacks the target once, clears the click flags, and returns a rotation to the
    target eye position.

### Changes
- Added small reusable event hook:
  - `SwingHandEvent` in `EntityEvents.kt`.
  - Registered it in `EventManager.ALL_EVENT_CLASSES`.
  - Called it from `MixinClientPlayerEntity.swing(...)` HEAD before NoSwing/SwingAnimations handling.
- Added `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleSilentAim.kt`.
- Registered `ModuleSilentAim` in `ModuleManager.kt`.
- Added `liquidbounce.module.silentAim.description` to `en_us.json`.

### Port notes
- No Prestige utility classes were copied.
- Prestige's sword/axe class checks were mapped to the existing local `ItemStack.isSword` / `isAxe` helpers because
  this project's mappings do not expose `SwordItem` under Prestige's name.
- Prestige's `IRotatable.getRotation()` path was mapped to this fork's `RotationUpdateEvent` +
  `RotationManager.setRotationTarget(...)` with a one-shot callback that performs the redirected attack.
- Target selection intentionally follows Prestige's `EntityUtil.getPlayer()` style: the last alive other player in
  the world list, not a new nearest-target utility.

### Build / verify
- `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
  → **BUILD SUCCESSFUL**.
- Jar verified:
  - `ModuleSilentAim.class`
  - `SwingHandEvent.class`

### FCL test next
- Enable `SilentAim`.
- Optional: turn `WeaponOnly=true` and hold a sword/axe.
- Aim so the vanilla crosshair target is `MISS`, with another player either within 3 blocks or visible.
- Left click once.
- Expected: the original missed swing is canceled and redirected into one attack on the Prestige-style player target,
  with silent rotation toward the target eyes.

## SilentAim Grim PacketOrderB / Reach / Hitboxes fix (2026-06-16, Codex)

User reported Grim flags after the Prestige SilentAim port:
- `PacketOrderB (pre-attack)`
- `Reach: 4.77595 blocks, type=player`
- `Hitboxes: type=player`

### Evidence checked
- Grim `PacketOrderB.java`: for modern clients, Grim requires a valid main-hand animation around attacks. The
  current module cancelled both the original missed swing and SilentAim's own redirected swing, so an attack could
  reach Grim with no animation context and flag `pre-attack`.
- Grim `Reach.java`: attacks are queued, then checked against the player's current/last yaw+pitch and the target
  hitbox. `Hitboxes` is raised when the look ray never intersects the attacked box; `Reach` is raised when the ray
  intercept is farther than the allowed reach.
- `ModuleSilentAim.kt`: target selection allowed any visible player even when farther than 3 blocks because the
  condition was `distance > 3 && !canSee`. This directly explains the 4.77-block Reach flag.
- `RotationManager.update()`: `whenReached` runs before the normal movement packet is sent. Attacking inside that
  callback can send the attack before the server receives the silent rotation, which explains Hitboxes.

### Fix
- `ModuleSilentAim.kt`
  - Original missed swing is no longer cancelled. It is kept as a legitimate animation packet before the redirected
    attack.
  - Module-owned swing during redirect is allowed through via the existing `postClick` guard.
  - Redirect attack no longer runs directly inside `whenReached`; it schedules through `PostRotationExecutor` with
    `postMove=true`, so the silent rotation packet is sent first.
  - Final attack guard checks `RotationManager.actualServerRotation` with `raytraceEntity(3.0)` and attacks only if
    the server-side rotation ray actually intersects the pending target.
  - Target candidate gate now requires visible, alive, non-spectator player within vanilla 3.0 boxed reach. Visible
    players beyond 3.0 are no longer valid redirect targets.
  - Uses the existing combat helper `target.attack(SwingMode.DO_NOT_HIDE, keepSprint=false)` instead of raw
    `interaction.attackEntity(...)`, keeping local combat hooks consistent.

### Build / verify
- `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
  → **BUILD SUCCESSFUL**.
- Jar verified:
  - `ModuleSilentAim.class`

### FCL test next
- Enable `SilentAim`, hold a normal sword/axe if `WeaponOnly=true`.
- Stand within real vanilla hit range of a player target, aim so the vanilla crosshair is `MISS`, then left click.
- Expected: SilentAim redirects only when the silent server rotation can ray-hit the target within 3.0 blocks.
- Expected Grim result: no `PacketOrderB pre-attack`, no `Reach` from >3.0 hits, and no `Hitboxes` from attacks
  sent before the rotation reaches the server.

## SilentAim PacketOrderO/Post timing correction (2026-06-16, Codex)

User tested the previous SilentAim Grim fix. `PacketOrderB` was gone, but new flags appeared:
- `PacketOrderO: type=ANIMATION`
- `PacketOrderO: type=INTERACT_ENTITY`
- `Post: interact entity v26.1`

### Evidence checked
- Grim `PacketOrderO.java`: after a flying/movement packet, until `CLIENT_TICK_END`, any non-async packet such as
  `ANIMATION` or `INTERACT_ENTITY` flags.
- Grim `Post.java`: action packets after a tick/flying packet are queued and later flagged when prediction confirms
  reliable ticking. `INTERACT_ENTITY` after flying directly matches the user's `Post` verbose.
- The previous fix used `PostRotationExecutor.addTask(..., postMove=true)`, which intentionally runs after the normal
  movement packet. That solved "attack before server rotation" but moved the entity action into exactly the
  PacketOrderO/Post forbidden window.
- `ModuleKillAura` was re-read for timing: it prepares rotations ahead (`FOR_NEXT_TICK`) and performs attacks in the
  tick/sequence path, not in post-move. Its optional ON_TICK path sends its own rotation packet before attack instead
  of attacking after flying.

### Fix
- `ModuleSilentAim.kt`
  - Removed `PostRotationExecutor` use from SilentAim.
  - `whenReached` now only arms `attackQueued=true` and records `attackQueuedAtAge = player.age`.
  - Added a `GameTickEvent` handler that runs the queued redirect only when `player.age > attackQueuedAtAge`, so the
    attack happens on the next client tick before that tick's movement packet, not in the previous tick's post-move
    phase.
  - Kept the previous Reach/Hitboxes guard: before attacking, `RotationManager.actualServerRotation` must
    `raytraceEntity(3.0)` into the pending target.
  - `clearPending()` now also clears the queued-attack state.

### Build / verify
- `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
  → **BUILD SUCCESSFUL**.
- Jar verified:
  - `ModuleSilentAim.class`

### FCL test next
- Enable `SilentAim`.
- Click while crosshair is `MISS` and the target is within real 3.0 reach.
- Expected: no `PacketOrderO` for `ANIMATION`/`INTERACT_ENTITY`, no `Post: interact entity`, while still avoiding the
  earlier `PacketOrderB`, `Reach`, and `Hitboxes`.

## AutoCrystal BaseBreak height logic (2026-06-16, Codex)

User wanted AutoCrystal to break crystals when the cursor is aimed at the obsidian/bedrock base block, even if the
crystal sits higher/lower relative to the player and the vanilla crosshair is not directly on the crystal entity.

### Evidence / constraints
- Current `ModuleAutoCrystal` was stable after reverting aggressive entity scans; normal break behavior only attacked
  `EntityHitResult` crystals/slimes.
- The previous scan-based fast-break caused Grim `Reach`/`Hitboxes` because it attacked crystals that the server-side
  look ray did not intersect.
- Grim `Reach.java` explicitly ray-checks the attack against current/last yaw/pitch and raises `Hitboxes` when the
  ray never intersects the target box. Therefore base-block breaking must rotate first and must only attack after the
  server has received a rotation that can ray-hit the crystal.

### Fix
- `ModuleAutoCrystal.kt`
  - Added `BaseBreak` setting, default `true`.
  - Added `baseBreakRotations = RotationsConfigurable(..., MovementCorrection.SILENT, combatSpecific=true)`.
  - When crosshair is a block target and that block is obsidian/bedrock, AutoCrystal searches for an `EndCrystal`
    in the vanilla crystal space above that base.
  - If found, it queues a base-break target instead of immediately attacking.
  - `RotationUpdateEvent` silently aims at the queued crystal's bounding-box center.
  - `whenReached` only arms the break; it does not attack immediately.
  - `GameTickEvent` runs the break on the next tick only if:
    - activation key is still held,
    - the player is still holding end crystals,
    - the crystal still exists and is within 3.0 boxed reach,
    - `RotationManager.actualServerRotation` can `raytraceEntity(3.0)` into that exact crystal.
  - While a base-break is queued, the normal place/entity path pauses so it does not send an early attack before the
    rotation packet reaches the server.
  - Existing direct crosshair `EntityHitResult` break path is unchanged.

### Build / verify
- `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
  → **BUILD SUCCESSFUL**.
- Jar verified:
  - `ModuleAutoCrystal.class`

### FCL test next
- Enable `AutoCrystal`, `BaseBreak=true`, `PlaceDelay=0`, `BreakDelay=0`.
- Aim at the obsidian/bedrock block under an existing crystal from lower/higher elevation and hold activation.
- Expected: module silently rotates to the crystal and breaks it even when the visible cursor is on the base block.
- Grim expectation: no `Reach`/`Hitboxes`; if the crystal is not actually reachable/ray-hittable within vanilla
  3.0, the module should simply not send the attack.

## AutoCrystal BaseBreak speed + DuplicateRotPlace correction (2026-06-16, Codex)

User reported two issues after BaseBreak:
- BaseBreak felt slow because it exposed/used the normal `RotationsConfigurable` smoothing tree.
- Grim flagged `DuplicateRotPlace`, e.g. `x=0.0 xdots=0.0 y=50.400005`.

### Evidence checked
- Grim `DuplicateRotPlace.java`: this is a block-place check. It records rotation deltas and, after block place, flags
  when the horizontal rotation delta (`deltaX`) is >2 degrees and exactly matches the previous placed rotation delta
  (`xDiff < 0.0001`). The verbose `x=0.0` is the difference between the current and previous yaw deltas, not a block
  coordinate.
- The first BaseBreak version always rotated to the exact crystal box center via a configurable smoother. Repeating
  base->crystal->base cycles can produce highly identical deltas before subsequent crystal placements.

### Fix
- `ModuleAutoCrystal.kt`
  - Removed the BaseBreak `RotationsConfigurable` tree/settings. BaseBreak no longer appears as a full rotation
    settings group and no longer waits on normal smoothing.
  - BaseBreak now uses a direct `RotationTarget` with no processors, `MovementCorrection.SILENT`, `ticksUntilReset=1`.
    This keeps the necessary server-rotation wait to one tick instead of smoothing over multiple ticks.
  - Added `queuedBaseAimPoint`.
  - `queueBaseBreak(...)` now chooses a randomized point inside the crystal bounding box instead of always aiming at
    the exact center.
  - The randomized point keeps the ray inside the crystal hitbox but prevents identical repeated base->crystal yaw
    deltas, which is what Grim `DuplicateRotPlace` was flagging around later block places.
  - Existing guard remains: attack is only sent when `RotationManager.actualServerRotation` can ray-hit the exact
    crystal within 3.0.

### Build / verify
- `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
  → **BUILD SUCCESSFUL**.

### FCL test next
- `AutoCrystal`: `BaseBreak=true`, `PlaceDelay=0`, `BreakDelay=0`.
- Hold activation while aiming at the base block under a crystal.
- Expected: BaseBreak is much faster than the smoothed version, roughly one tick after the valid server rotation is
  sent.
- Expected Grim result: no `Reach`/`Hitboxes`; `DuplicateRotPlace` should stop or at least decay because repeated
  placements no longer follow exactly identical base-break rotation deltas.

## AutoCrystal BaseBreak aggressive same-tick mode (2026-06-16, Codex)

User wanted BaseBreak to be less "angel" and closer to `PlaceDelay=0` / `BreakDelay=0` speed. The one-tick safe wait
was still too slow.

### Evidence / reasoning
- KillAura `ON_TICK` can send a rotation packet in the same tick, but sending `ROT -> ATTACK` directly would recreate
  the `PacketOrderO` / `Post` issue seen in SilentAim: entity action after a flying packet.
- `ServerboundClientTickEndPacket.INSTANCE` exists on 26.1.2, but `Post.java` does not reliably clear `sentFlying`
  from `CLIENT_TICK_END` after a movement packet was already sent in that tick, so `ROT -> TICK_END -> ATTACK` is not
  a clean solution for Grim Post.
- Grim `Reach.java` queues attacks and checks them when an update packet arrives. Therefore the aggressive usable
  order is `ANIMATION -> ATTACK -> ROT`: action happens before flying, then the following rotation update is what Grim
  uses for the queued reach/hitbox check.

### Fix
- `ModuleAutoCrystal.kt`
  - Added `BaseBreakTiming` enum setting:
    - `Aggressive` (default)
    - `SafeNextTick` (previous safe queued behavior)
  - `Aggressive` path:
    - Finds the crystal above the aimed base block.
    - Picks a randomized point inside the crystal bounding box.
    - Builds and normalizes a rotation to that point.
    - Verifies local `raytraceEntity(3.0, rotation)` hits the exact crystal.
    - Sends `player.swingHand(MAIN_HAND)` before attack so PacketOrderB has an animation context.
    - Sends the attack immediately.
    - Sends `ServerboundMovePlayerPacket.Rot(...)` immediately after attack so Grim's queued Reach/Hitboxes check sees
      the intended rotation.
  - `SafeNextTick` still exists as fallback if a server dislikes this aggressive order.
  - AntiWeakness slot restore is done before the post-attack rotation packet to avoid held-slot packets after flying.

### Build / verify
- `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
  → **BUILD SUCCESSFUL**.
- Jar verified:
  - `ModuleAutoCrystal.class`
  - `ModuleAutoCrystal$BaseBreakTiming.class`

### FCL test next
- `AutoCrystal`: `BaseBreak=true`, `BaseBreakTiming=Aggressive`, `PlaceDelay=0`, `BreakDelay=0`.
- Hold activation on a base block with a crystal above it.
- Expected speed: breaks in the same client tick once the crystal entity is present, not after the safe queued tick.
- Watch Grim for `PacketOrderB`, `PacketOrderO`, `Post`, `Reach`, and `Hitboxes`. If only this aggressive path flags,
  switch `BaseBreakTiming=SafeNextTick`.

## AutoCrystal BaseBreak rollback to safe queued timing (2026-06-16, Codex)

User tested the aggressive same-tick BaseBreak and Grim flagged:
- `TickTimer: type=flying/end`
- `Timer`
- `PacketOrderB: post-attack`

### Evidence / cause
- The aggressive path sent an extra `ServerboundMovePlayerPacket.Rot` immediately after the crystal attack.
- That added an additional flying/movement packet in the same client tick, matching the `TickTimer` / `Timer`
  verbose.
- The post-attack rotation also put a non-animation packet after the entity attack path, which matched
  `PacketOrderB: post-attack`.

### Rollback
- `ModuleAutoCrystal.kt`
  - Removed the `BaseBreakTiming` setting and enum.
  - Removed the aggressive same-tick function.
  - Removed the extra post-attack rotation packet send.
  - Restored `breakEntity` / `hitEntity` to the normal AutoCrystal order: `attackEntity` then `swingHand`.
  - BaseBreak now always uses the previous safe queued path:
    - queue crystal above the aimed base block,
    - set a direct silent rotation target with no smoothing processors,
    - arm when the rotation reaches,
    - attack on the next tick only if `actualServerRotation` ray-hits the exact crystal within 3.0.

### Build / verify
- `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
  -> **BUILD SUCCESSFUL**.

### FCL test next
- `AutoCrystal`: `BaseBreak=true`, `PlaceDelay=0`, `BreakDelay=0`.
- There is no `BaseBreakTiming` setting anymore.
- Aim at the obsidian/bedrock base under a crystal from lower/higher elevation and hold activation.
- Expected: slower than the aggressive attempt, but no extra same-tick rotation packet; Grim should not repeat the
  `TickTimer` / `Timer` / `PacketOrderB post-attack` cluster from the aggressive mode.

## AutoCrystal AutoObsidian + AutoHitCrystal bind behavior + Argon ports (2026-06-16, Codex)

### AutoCrystal AutoSwitch / AutoObsidian
- User requested two convenience paths:
  - If holding `ActivateKey` without a crystal in hand, switch to an end crystal from hotbar automatically.
  - If the aimed block is not obsidian/bedrock, place obsidian first, then continue normal AutoCrystal.
- `ModuleAutoCrystal.kt`:
  - Added `AutoSwitch` (default true).
  - Added `AutoObsidian` (default true).
  - `tryAutoPlaceObsidian(...)` places obsidian at the aimed block face only if the adjacent placement position is air
    and entity-free, then lets the existing crystal place/break logic continue.
  - `blockUseHandler` also cancels vanilla block-use for AutoObsidian cases so `ActivateKey=RIGHT` does not double-send
    vanilla right-click plus module place.

### AutoCrystal PacketOrderE / obsidian spam correction
- User reported Grim:
  `PacketOrderE ... rightClicking=true`
  and also that holding `ActivateKey` could rapidly place a line of obsidian when the camera moved.
- Root cause: after placing obsidian, the first AutoObsidian patch switched back to crystal in the same tick as the
  block right-click. Grim `PacketOrderE.java` flags `HELD_ITEM_CHANGE` while `packetOrderProcessor.isRightClicking()`.
- Fix in `ModuleAutoCrystal.kt`:
  - Added `pendingCrystalSwitchAfterObsidian`.
  - Added `autoObsidianPlacedThisHold`.
  - Crystal switch is delayed to the next tick after obsidian placement.
  - AutoObsidian now places only one obsidian per `ActivateKey` hold; releasing the bind resets it.
- Expected FCL test: hold `ActivateKey` while moving camera; only one obsidian should place until the bind is released,
  and Grim should stop reporting `PacketOrderE rightClicking=true` from the obsidian -> crystal switch.

### AutoHitCrystal trigger -> hold-bind behavior
- User said `ModuleAutoHitCrystal` felt like a trigger module. It now behaves like AutoCrystal:
  - Added `ActivateKey` (default right mouse).
  - The module must be enabled, but the sequence only runs while `ActivateKey` is held.
  - Releasing `ActivateKey` resets the sequence.
  - The old self-disable behavior (`enabled=false`) was removed; completed sequences reset state instead.
- Follow-up bug: `DoubleTap` worked, but if the bind stayed held it repeated two-crystal sequences forever.
- Fix:
  - Added `completedThisHold`.
  - One `ActivateKey` hold now executes one full Prestige sequence only.
  - Releasing and pressing the bind again arms the next sequence.

### Argon TriggerBot / InventoryTotem / KeyPearl ports
- Source path: `AnotherProject/debug/argon-main`.
- Read source modules:
  - `combat/TriggerBot.java`
  - `combat/AutoInventoryTotem.java`
  - `misc/KeyPearl.java`
  - `utils/InventoryUtils.java`
- Added new modules:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleTriggerBot.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleInventoryTotem.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/misc/ModuleKeyPearl.kt`
- Registered in `ModuleManager.kt`:
  - `ModuleInventoryTotem` and `ModuleTriggerBot` under Combat.
  - `ModuleKeyPearl` under Misc.
- Added lang keys in `en_us.json`:
  - `liquidbounce.module.inventoryTotem.description`
  - `liquidbounce.module.keyPearl.description`
  - `liquidbounce.module.triggerBot.description`

#### TriggerBot port notes
- Preserved Argon settings:
  `WorkInScreen`, `WhileUse`, `OnLeftClick`, `AllItems`, `SwordDelay`, `AxeDelay`, `CheckShield`,
  `OnlyCritSword`, `OnlyCritAxe`, `SwingHand`, `WhileAscending`, `ClickSimulation`, `StrayBypass`,
  `AllEntities`, `UseShield`, `ShieldTime`, `SamePlayer`.
- Uses local helpers:
  - `ItemStack.isSword` / `isAxe`
  - `FriendManager.isFriend`
  - `LivingEntity.wouldBlockHit(player)` for shield-facing check
  - `interaction.attackEntity(...)`
- Mapping fix: Minecraft 1.26.2 source did not expose `net.minecraft.world.entity.monster.Zombie`; used
  `entity.type == EntityType.ZOMBIE` for `StrayBypass`.
- It also mirrors Argon's attack listener behavior by cancelling direct attack events when left mouse is not pressed.
  If this is too aggressive with other combat modules, revisit the `AttackEntityEvent` handler.

#### InventoryTotem port notes
- Preserved Argon settings:
  `Mode` (`Blatant`, `Random`), `Delay`, `Hotbar`, `TotemSlot`, `AutoSwitch`, `ForceTotem`,
  `AutoOpen`, `StayOpenFor`.
- Uses `interaction.clickSlot(syncId, slot, 40, ContainerInput.SWAP, player)` for offhand totem refill.
- Uses `interaction.clickSlot(syncId, slot, hotbarSlot, ContainerInput.SWAP, player)` for hotbar totem refill.
- Argon used a custom `FakeInvScreen`; this fork has no equivalent, so `AutoOpen` uses the vanilla
  `InventoryScreen(player)` and closes it after refill when opened by the module.

#### KeyPearl port notes
- Preserved Argon settings:
  `ActivateKey`, `Delay`, `SwitchBack`, `SwitchDelay`.
- Behavior:
  - press bind once,
  - save previous hotbar slot,
  - switch to ender pearl in hotbar,
  - throw via `interaction.interactItem`,
  - optionally switch back after `SwitchDelay`.
- `ActivateKey` default is unbound (`InputConstants.UNKNOWN`), matching Argon's unbound `-1` default.

### Build status
- A local build was started after the ports. First compile error was only `ModuleTriggerBot.kt` importing the wrong
  zombie class; fixed by switching to `EntityType.ZOMBIE`.
- User then said they already built locally and requested updating this file, so this note records the code state.
- If future agent needs to verify again, run:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`

## TargetESP Crystals → Wonderful "Кристаллы" port (2026-06-16, Claude)

User: TargetESP Crystals mode looked bad; port Wonderful's Crystal mode (the last TargetESP mode) from
`AnotherProject/WorkSpace/wonderful-1.0.0.jar`.

### Source checked
- Decompiled `~/.cache/wonderful_decomp/.../render/TargetESP.java`. Mode array (Russian) =
  `[Картинка1, Картинка2, Кольцо, Души, Кубы, Кристаллы]`; Кристаллы = `renderCrystals3D` (L854).
- Behavior: 18 diamond bipyramids (`renderCrystalShape`, w=0.06 h=0.2) orbit the target at 20° steps,
  weave up/down (`yOffset = 0.1 + height*|sin(i)|`, raw degree index used as radians), each oriented so its
  +Y axis points at the target centre (axis-angle from up→dir), orbitScale `1.2 - 0.5*anim` (eases 1.2→0.7).
  Then 2 additive bloom billboards behind each crystal (`bloom.png`).
- **KEY GOTCHA** (matches Agent1.md 2026-06-12 blob bug): `drawBillboard` screen-scales size by
  `getDistanceScale = max(0.1, dist*0.007)`. The old port passed raw `glowBaseSize` (~4.5–7) as world size →
  4.5-block blob. Preserved the screen-scale here.

### Changes
- `RichEspPipelines.kt`: `CRYSTAL_FILLED_PIPELINE` + `BLOOM_PIPELINE` depth changed LEQUAL→no-depth
  (`Optional.empty()`) to match Wonderful's `disableDepthTest`. Both pipelines are crystal-mode-only (verified),
  so no other mode is affected. Bloom stays LIGHTNING (SRC_ALPHA/ONE) additive.
- `ModuleTargetESP.kt`: replaced the Rich static-crystal section (`createCrystals`, `class Crystal`,
  `drawFilledCrystal`, `drawBloomEffect`, `billboardQuad`, fields `crystalList`/`lastRenderedTarget`) with the
  Wonderful orbit port: new `renderCrystals(stack, provider, target, alpha, camPos, smoothed, shaderPass, bloomRenderType)`
  + `drawCrystalShape` + `crystalTri` + `crystalBillboard`. Colors still sync ClickGUI theme (`themeColor(0.35F)`)
  with hurt-red overlay (`0xFFFF0303`). Reuses existing `rich/particle/glow.png` (RGBA colortype 6) — no new asset.
- Both call sites updated (normal post-world + Iris shader-world pass). Shader pass: shapes→`RenderTypes.lightning()`,
  bloom→`entityTranslucentEmissive(glow)`. Crystal rotation now advances via `rotationAngle += crystalRotationSpeed`
  each frame (CrystalRotationSpeed setting kept).
- Inline note `#Edit By Claude 2026-06-16` on the new section.

### Build / verify
- `assemble -x detekt` → **BUILD SUCCESSFUL** → `liquidbounce-0.31.2.jar`.
- NOT yet FCL-tested.

### FCL test next
- KillAura on + a target; TargetESP Mode=Crystals. Expected: small diamond crystals orbiting the target (eases
  inward as it appears), each pointing at the body centre, with a soft additive glow halo behind — NOT a blob.
  Hurt flashes red. Test both shaderpack OFF (RichEspPipelines path) and Iris/Complementary ON (emissive path).

## ClickGUI Custom Fonts — Celestial montserrat (titles) + pixel (body) (2026-06-17, Claude)

User: ClickGUI had no real custom font (montserrat = titles like module/category names; pixel = body like
descriptions/notes). Source fonts: `AnotherProject/debug/celestial/fonts/{montserrat,pixel}.ttf`.

### Investigation (why there was no custom font before)
- `FontManager.fontFaces` only ever held `COMMON_FONT`; `queueFile`/`queueFolder` have NO caller anywhere
  (upstream PR #3884 not merged — see the TODO on `FONT_RENDERER`). So `fontFace("Inter Regular")` was always
  null and every `ExosGuiRenderer.text`/`textInter` fell back to the system font. The bundled Inter ttf lives in
  `src-theme/` (the Svelte theme), never loaded by the Kotlin client.
- `drawCustomFontText(renderer, …)` (CustomFontGui.kt) takes the renderer explicitly; each `FontManager.FontFace`
  added to `fontFaces` BEFORE `createGlyphManager()` (called in `LiquidBounce.prepareGuiStage`) gets its own glyph
  page + lazy `renderer`. Resource load prefix is `/resources/liquidbounce/$path` (IdentifierExtension.kt).

### Changes
- Bundled both ttf → `src/main/resources/resources/liquidbounce/font/{montserrat,pixel}.ttf`.
- `FontManager.kt`: added `registerResourceFont(name, path)` (loads ttf from the jar via getResourceAsStream,
  `Font.createFont` + fillStyle, guarded — a missing/corrupt font logs & skips, never crashes startup). An `init`
  block registers `Montserrat` + `Pixel` (runs before createGlyphManager). Exposed `TITLE_FONT_RENDERER`
  (montserrat) + `BODY_FONT_RENDERER` (pixel), each falling back to Inter then COMMON_FONT.
- `ExosGuiRenderer.kt`: added `title()/titleWidth()` (montserrat) + `body()/bodyWidth()` (pixel) via a shared
  `drawWithRenderer(renderer,…)` that ALWAYS uses the custom atlas (no MIN_CUSTOM_FONT_PIXELS gate), vanilla
  fallback only if glyph manager not ready / draw throws. The generic `text()`/`textWidth()` are UNCHANGED so
  HUD/nametags/world text keep their current look.
- `ClickGuiScreen.kt`: routed the LIVE dashboard text — titles→`title` (SYLENT logo, category names, Theme sidebar
  row, Theme grid header, module card name, theme card name, settings-panel header, every setting/section NAME),
  body→`body` (module + theme descriptions, "No modules"/"No settings", style count, search placeholder, setting
  VALUES: number/range/choice-current/choice-options/text, ON/OFF + chevron glyphs). `truncate()`/`wrapText()`
  gained an optional `measure` fn so truncation/wrapping measure with the SAME font (default Inter for untouched
  callers). The dead dropdown helpers `renderPanel`/`renderModule`/`renderThemeBar` (no caller) were left on the
  old `text()` — they don't render.

### Build / verify
- `assemble -x detekt` → **BUILD SUCCESSFUL** → `liquidbounce-0.31.2.jar`.
- Jar-verified both ttf ship at `resources/liquidbounce/font/` (montserrat 198072B, pixel 61052B).
- NOT yet FCL-tested.

### FCL test next
- Open ClickGUI. Expected: module/category names, headers and the SYLENT logo render in Montserrat; module
  descriptions, setting values, search hint and counts render in Pixel. If a font fails to load it silently falls
  back (Inter→system), so check the startup log line "Fonts: [ … Montserrat, Pixel ]" to confirm both registered.
- Pixel is a small bitmap-style face: if body text looks too large/small at the current sizes, tune the `size`
  args on the `body(...)` calls (descriptions are 9–10px, values are SETTING_TEXT_SIZE).

## 6-task batch: ThemeCard text / font scale / InventoryAnim / SwingAnim / JumpEffect / MainScreen (2026-06-17, Claude)

### 1. Theme card "SELECT/ACTIVE" text removed
- `ClickGuiScreen.renderThemeCard`: dropped the status pill + SELECT/ACTIVE label. Selected theme still
  shown by the bright outline. (Also fixed name/desc truncate to measure with title/body fonts.)

### 2. ClickGUI font scale fix (description too tiny)
- Root cause measured via AWT probe at DEFAULT_FONT_SIZE=43: cap-height(H) ratio — Inter 0.727 (baseline
  every `size` was tuned for), Montserrat Bold 0.700 (~Inter, reads a bit big), Pixel "Bit Cell" **0.438**
  (~60% of Inter → descriptions at size 9-10 rendered ~4px = unreadable).
- Fix in `ExosGuiRenderer`: `TITLE_FONT_SCALE = 0.92`, `BODY_FONT_SCALE = 1.6` applied in BOTH the draw
  (`title`/`body`) and width (`titleWidth`/`bodyWidth`) so layout/truncation stay consistent.

### 3. InventoryAnimation — re-ported from VCore 0.8
- VCore (`vcore.injection.MixinHandledScreen`/`MixinAbstractInventoryScreen`) = ONE time-based easeOutBack
  scale-up from screen centre on open (250ms). No close anim, no dim, no 9 types, no cursor hide.
- `ModuleInventoryAnimation.kt` rewritten: `Duration` setting + `shouldAnimate` + `backOutScale`.
- `MixinScreen.java`: replaced the whole ThunderHack block (progress lerp / closing / dim / cursor / type
  switch / InventoryAnimationAddition impl) with a simple time-based easeOutBack scale around
  `extractRenderStateWithTooltipAndSubtitles` (kept the 1.26.2 orchestrator-wrap insight so window+items
  scale together). `MixinHandledScreen.java`: removed the `onClose` zoom-out hook + interface ref. Deleted
  `interfaces/InventoryAnimationAddition.java`.

### 4. SwingAnimation — re-ported from Rich-Modern
- Rich (`rich.modules.impl.render.SwingAnimation`) REPLACES the whole vanilla arm transform. 9 types
  (Chop/Swipe/Down/Smooth/Smooth2/Power/Feast/Twist/Default), settings HitStrength/SwingSpeed/OnlySwing/
  OnlyAura. No "swingless".
- `ModuleSwingAnimations.kt` rewritten: `applySwing(matrix, swingProgress)` holds all 9 transforms (math
  via kotlin.math Double→Float; `Mth` resolved to Double under Kotlin here so avoided it), `swingDuration`
  (SwingSpeed*6) + `shouldAnimate` (OnlyAura gate).
- `MixinHeldItemRenderer.java`: `swingArm` HEAD now just calls `applySwing` and cancels if it drew; deleted
  all 10 old `liquid_bounce$smooth/snap/fap/...` transform methods. `MixinLivingEntityCompatibility.java`:
  swing duration now from `swingDuration`. `MixinClientPlayerEntity.java`: removed the swingless branch +
  unused import.

### 5. JumpEffect — deleted
- Removed `ModuleJumpEffect.kt`, `ModuleManager` registration, lang key. (`DodgePlanner` `isJumpEffective`
  is an unrelated local var — untouched.)

### 6. CustomMainScreen — replaced with Rich-Modern concept port
- OLD native menu drew a 300-point 3D plexus cloud via `DrawContext.fill` every frame = the reported lag.
- Replaced `LiquidBounceMenuScreen.kt` with Rich's `MainMenuScreen` LOOK/FLOW mapped to the fork's SDF
  renderer: lock screen (dim zoomed bg + pulsing "Press any key" → click/key unlock) → zoom settle
  (1.08→1.0) → big clock + date slide/fade in → centred row of rounded glow buttons (Singleplayer/
  Multiplayer/Options/Exit) fading in staggered with hover-scale. Montserrat titles + Pixel body.
- NOT ported (user scope choice "main menu only"): Rich ALT_SCREEN alt-account manager (AccountConfig/
  AccountRenderer/SessionChanger) + its `Render2D` blur/texture pipeline + `Fonts`/static background image. The fork
  has none — `DrawContext.drawTexture` is a no-op stub, so background is a dark theme-tinted gradient, not
  the Rich static image. The 5th Rich button (Accounts) dropped.

### Build / verify
- All six: `assemble -x detekt` → **BUILD SUCCESSFUL** → `liquidbounce-0.31.2.jar`. NOT yet FCL-tested.
- FCL test: (1) Theme page cards have no SELECT/ACTIVE text. (2) ClickGUI descriptions now readable, names
  not oversized. (3) open a container → easeOutBack pop. (4) SwingAnimations modes + OnlyAura/OnlySwing.
  (5) JumpEffect gone from the module list. (6) main menu: press-any-key → clock + 4 round buttons, no lag.

## SwingAnimation→VCore Animations + Rich MainScreen icons/background (2026-06-17, Claude)

### Task 1: deleted Rich SwingAnimation, ported VCore 0.8 "Animations" (fixes 2 bugs)
- User report on the Rich port: (a) item SHRINKS when switching slots, (b) animation FALLS BACK to vanilla
  when attacking an entity (custom only when aiming at ground). Root cause: Rich hooked `swingArm` HEAD only,
  but vanilla `renderArmWithItem` already ran `applyItemArmTransform` (base offset, incl. the per-frame
  `inverseArmHeight = swapAnimationScale*(1-handHeight)` equip-swap dip = the shrink) BEFORE swingArm, and only
  on the attack/WHACK branch — so the two transforms fought on hit and the swap-dip scaled the item.
- VCore's `Animations` instead REPLACES the whole first-person melee render. Ported as `ModuleAnimations.kt`
  (modes Default/Smooth/Fade/Self/Old + Power/Offset/OnlyAura/SlowAnimation+SlowValue), math via kotlin.math.
- `MixinHeldItemRenderer.java`: removed the old `swingArm` HEAD hook + delegate; added a `renderArmWithItem`
  HEAD cancellable hook (`hookAnimations`) that, for plain main-hand melee swings (not using/charging/spinning),
  pushes a FRESH pose via `ModuleAnimations.applyTransform` + `applyMainHandOffset` and submits the item once
  via shadowed `renderItem`, then cancels vanilla. → no double transform, no equip-swap shrink, identical pose
  whether aiming at ground or hitting an entity. `hookViewModel` now skips when Animations owns the hand (it
  applies its own ViewModel offset). Off-hand / crossbow / bow / eat / use keep vanilla.
- `MixinLivingEntityCompatibility.java`: swing-duration constant now `+ ModuleAnimations.extraSwingDuration()`
  (VCore SlowAnimation/SlowValue) instead of the Rich `swingDuration`.
- `ModuleManager` reg `ModuleSwingAnimations`→`ModuleAnimations`; deleted `ModuleSwingAnimations.kt`; lang key
  `swingAnimations`→`animations`. (`liquid_bounce$applySwingOffset` kept — still used by the 1.7 autoblock anim.)

### Task 2: Rich MainScreen real icons (MSDF) + static-image background
- User: replace the S/M/O/E letters with Rich's `mainmenuicons` and add the Rich static menu image.
- Icon font is an msdfgen MSDF atlas (4096², distanceRange 8, 6 glyphs a/b/c/i/s/x packed in one corner). The
  fork's AWT-glyph font system can't render MSDF and a 4096² texture is too heavy for FCL — so:
  - Cropped the atlas to the 477×104 glyph region as RGBA via a PURE-PYTHON PNG codec (no PIL on Termux;
    un-filtered scanlines + re-encoded with zlib) → `resources/liquidbounce/menu/menuicons.png`. Per-glyph UVs +
    plane bounds computed from `mainmenuicons.json` (yOrigin=bottom → flipped).
  - New MSDF GUI shader pipeline (mirrors RoundedRect/GlowRect infra): `shaders/menu_icon/menu_icon.{vsh,fsh}`
    (median-of-RGB SDF decode, screen-px-range AA, `Sampler0`, TRANSLUCENT blend), `render/gui/MenuIconShader.kt`
    (ShaderSource + `withSampler` pipeline), `render/gui/MenuIconGuiElementRenderState.java` (textured GUI
    element), `render/gui/MenuIconGui.kt` (lazy atlas register + `Icon` enum w/ UV+plane + `draw(...)`).
    Precompiled in `MixinShaderManager` alongside RoundedRect/GlowRect.
  - The Rich static menu image (5120×2880 RGBA) copied to `resources/liquidbounce/menu/`. `LiquidBounceMenuScreen` now blits
    it via the REAL `GuiGraphicsExtractor.blit(Identifier, x0,y0,x1,y1, u0,u1,v0,v1)` (the `DrawContext.drawTexture`
    extension is a no-op stub — don't use it), zoom-expanded as a cover fill, with the dark theme-gradient as a
    guarded fallback. Buttons map Singleplayer→A, Multiplayer→B, Options→S, Exit→X; fall back to a letter glyph if
    the atlas fails to load.

### Build / verify
- Both tasks: `assemble -x detekt` → **BUILD SUCCESSFUL** → `liquidbounce-0.31.2.jar`.
- Jar-verified: menu_icon.{fsh,vsh}, static menu image, menuicons.png, MenuIconShader.class, ModuleAnimations.class
  present; ModuleSwingAnimations gone.
- NOT yet FCL-tested. Test: (1) Animations — switch modes, item must NOT shrink on slot switch; attack an
  entity → same custom pose as aiming at ground (no vanilla fallback); SlowAnimation slows the swing. (2) main
  menu shows the static Rich background + the 4 real Rich MSDF icons (crisp), hover scales + shows label.
- KNOWN RISK: the MSDF px-range uses `fwidth` only (the atlas distanceRange/size aren't passed as a uniform);
  if icons look too soft/sharp at the chosen 20px box, pass an explicit pxRange uniform instead. Background
  `blit` UV is full-texture; if the 16:9 source looks stretched on odd aspect ratios, switch to an aspect-correct
  UV crop.

## ClickGUI font FPS fix + MainScreen footer/icon fixes (2026-06-18, Claude)

### ClickGUI FPS: 500 → 15-20 after adding fonts — FIXED
- Root cause: `CustomFontGui.drainCachedGlyphs()` called `getDeclaredField(...) + isAccessible` (Java
  reflection + class scan) on EVERY text draw to reach FontRenderer's private `cache.renderedGlyphs`/`lines`.
  Before the Montserrat/Pixel fonts, ClickGUI text went through `text()` which gated <22px to the cheap
  vanilla bitmap font, so this path was rarely hit. Once `title()`/`body()` forced ALL ClickGUI text through
  the custom atlas, that per-glyph-flush reflection ran hundreds of times per frame → tanked FPS.
- Fix: resolved the 3 `Field`s ONCE via top-level `by lazy` (CACHE_FIELD/RENDERED_GLYPHS_FIELD/LINES_FIELD,
  using `CACHE_FIELD.type` = the concrete `FontRendererCache` class) and reuse them. No per-draw reflection.

### MainScreen: removed version footer
- Deleted `renderFooter` (the "LiquidBounce · v0.31.2" line) + its call from `LiquidBounceMenuScreen`.

### MainScreen: real Rich icons now render (were falling back to letters)
- Root cause: `MenuIconGui.draw` resolved the DynamicTexture's `textureView`/`sampler` at SUBMIT time and
  returned false when null — and on the main-menu frames the freshly-registered dynamic texture had no GPU
  view yet, so every icon fell back to its letter.
- Fix: route through the vanilla `GuiGraphicsExtractor.blit(pipeline, Identifier, x, y, u, v, w, h, srcW,
  srcH, texW, texH, color)` overload using `MenuIconShader.PIPELINE` + the atlas Identifier + source-pixel
  UVs (converted from the normalised crop UVs via ATLAS_W=477 / ATLAS_H=104). vanilla's `innerBlit` defers
  the texture bind into the GUI flush (BlitRenderState) — the same path every vanilla GUI texture uses — and
  emits POSITION+UV0+COLOR vertices matching the MSDF pipeline's FORMAT. Deleted the now-unused
  `MenuIconGuiElementRenderState.java`. Letter fallback kept only for a genuine load failure.

### Build / verify
- `assemble -x detekt` → **BUILD SUCCESSFUL** → `liquidbounce-0.31.2.jar`. NOT yet FCL-tested.
- FCL test: (1) open ClickGUI — FPS should be back near normal. (2) main menu — no version text; the 4 buttons
  show the real Rich MSDF icons (A/B/S/X) crisp, not letters. If icons still letter-fallback, check the FCL log
  for "Failed to draw menu icon" (pipeline/atlas issue) vs silent (atlas register failed).

## ClickGUI FPS drop real root cause — duplicate lang key spam (2026-06-19, Codex)

User tested the previous font reflection-cache build and ClickGUI still dropped to ~20 FPS. Checked
`/storage/emulated/0/FCL/log/latest_game.log`.

### Evidence
- `latest_game.log` was ~36 MB and contained **10,261** repeats of:
  `Failed to load language en_us`
  `JsonSyntaxException: duplicate key: liquidbounce.module.animations.description`
- Stack trace path:
  `LanguageManager.loadLanguage` -> `Value.description` -> `ClickGuiScreen.renderModuleCard(ClickGuiScreen.kt:493)`
  -> render frame.
- So every ClickGUI frame calling `module.description.get()` retried loading `en_us`, Gson threw, and the client
  printed a full stacktrace on the render thread. This also explains the user's suspicion that missing module
  descriptions were involved.

### Fixes
- `src/main/resources/resources/liquidbounce/lang/en_us.json`: removed the older duplicate
  `liquidbounce.module.animations.description` entry, keeping the new accurate one:
  `"Custom first-person hand swing animations."`
- Checked all `resources/liquidbounce/lang/*.json` for duplicate keys with awk/sort/uniq — none remain.
- Also connected the already-existing `GlyphBatchGuiElementRenderState` to `CustomFontGui.flushGlyphs`: custom-font
  text now submits one batched GUI element per glyph page per string instead of one `GlyphGuiElementRenderState` per
  character. This keeps Montserrat/Pixel but reduces GUI state churn.

### Build / verify
- `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
  -> **BUILD SUCCESSFUL**.
- Jar-verified:
  - `resources/liquidbounce/lang/en_us.json` has no duplicate keys.
  - `liquidbounce.module.animations.description` appears once.
  - `CustomFontGui.class` and `GlyphBatchGuiElementRenderState.class` are present.

### FCL test next
- Install the new jar and open ClickGUI.
- Expected: no repeated `Failed to load language en_us` spam in `/storage/emulated/0/FCL/log/latest_game.log`, module
  descriptions render normally, and FPS should no longer collapse from exception/log spam. If FPS still drops after
  the log spam is gone, the next target is reducing animated SDF/chroma draws, not language/font loading.

## ClickGUI settings frame radii + Cubes theme/lifetime/shader + SmoothCamera (2026-06-19, Codex)

### ClickGUI detached module-settings frame
- User reported the module settings card (e.g. right-click KillAura in ClickGUI) had wrong corner sync: top corners
  looked square and bottom corners were the only rounded ones.
- Root cause: `rounded_rect.fsh` treated positive SDF UV Y as "top", but this GUI quad sends top vertices with
  `UV.y = -1` and bottom with `UV.y = +1`. So `CORNERS_TOP` visually affected the bottom.
- Fix:
  - `rounded_rect.fsh`: top/bottom corner selection now treats `p.y < 0` as top.
  - `rounded_rect.vsh` + `RoundedRectGuiElementRenderState.java`: added backwards-compatible top/bottom radius
    packing. Positive `RadiusStroke.x` remains the old shared-radius path; negative means top/bottom radii packed
    as 7-bit fixed point (`px * 4`), safe for the short vertex attribute.
  - `RoundedRectGui.kt` + `ExosGuiRenderer.kt`: added `drawRoundedRectVerticalRadii` /
    `roundedGradientRectVerticalRadii`.
  - `ClickGuiScreen.kt`: detached settings panel now uses top radius `16f`, panel bottom radius `6f`, header bottom
    radius `5f`. This gives strong top corners and subtly rounded lower corners, not a full pill.
- Build: `assemble -x detekt` → **BUILD SUCCESSFUL**. Jar verified with updated rounded_rect shaders +
  `RoundedRectGuiElementRenderState.class` + `ClickGuiScreen.class`.
- FCL test: right-click a module card. Expected: top corners strongly rounded; lower corners slight; `CORNERS_TOP`
  usages like PotionHud title band should no longer appear inverted.

### Cubes module color/lifetime/shader
- User requested removing manual `Color` setting because hex input is inconvenient and syncing with ClickGUI theme.
- `ModuleCubes.kt`:
  - Removed manual `color("Color", ...)`.
  - Cubes color now lerps `ClickGuiTheme.accentA` → `accentB` over a short cycle (`THEME_CYCLE_MS=2200`).
  - Added `LifeTime` setting (`0.5..6.0`, default `1.0`). It is a multiplier over Wonderful's original random
    lifetime: Falling `260..480`, Scatter `420..840`. Existing particles keep their old max life until respawn.
  - Added reflection-only Iris detection and `renderShaderWorldPass(cameraPos)`.
  - `MixinWorldRenderer.java` now calls `ModuleCubes.renderShaderWorldPass(...)` in the same pre-composite hook used
    by TargetESP/PopChams.
  - Shader path renders only the Cubes **glow** through `RenderTypes.entityTranslucentEmissive(...)` before Iris final
    composite. Normal `WorldRenderEvent` still draws the accepted cube/triangle faces and dashed wireframe, but skips
    the late glow when shader glow already rendered. This avoids duplicating bodies while letting shaderpacks pick up
    bloom.
- Build: `assemble -x detekt` → **BUILD SUCCESSFUL**. Jar verified `ModuleCubes.class` and `MixinWorldRenderer.class`.
- FCL test: Cubes settings should show no `Color`, should show `LifeTime`; changing ClickGUI Theme recolors Cubes;
  shaderpack ON should pick up the glow more naturally.

### Cubes click-push restore
- User then reported the "push cube when clicked" behavior seemed lost.
- Old path still polled `mc.options.attackKey.isDown` inside `WorldRenderEvent`, but that can miss short click pulses
  and Cubes are visual particles, so a click on a cube does not necessarily produce an entity attack.
- Fix in `ModuleCubes.kt`:
  - Added `MouseButtonEvent` handler for raw attack mouse press.
  - Added `AttackEntityEvent` handler as a backup when the normal interaction path consumes the click.
  - Kept render-loop key-edge fallback.
  - Added `IMPULSE_DEBOUNCE_MS = 75L` so one click does not double-apply impulse through multiple hooks.
- Build after this final Cubes change: `assemble -x detekt` → **BUILD SUCCESSFUL**.
- FCL test: enable Cubes, aim at a cube, left-click. Expected: cube receives push impulse again.

### SmoothCamera port
- Source read: `AnotherProject/WorkSpace/LiquidBounce/src/main/kotlin/.../render/ModuleSmoothCamera.kt` plus reference
  `MixinCamera.java` hook.
- Added `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleSmoothCamera.kt`.
- Ported settings/logic:
  - `EnableFirstPOV` default false.
  - `ResetOnPerspectiveChange` default true.
  - `HorizontalFactor` default `0.9`, `VerticalFactor` default `0.93`.
  - Maintains `smoothPos` and interpolates camera X/Z with horizontal factor, Y with vertical factor.
  - Local zero check replaces upstream `utils.math.isLikelyZero`, which this fork did not have.
  - Uses this fork's `Category.RENDER` and `disable()` lifecycle.
- `MixinCamera.java`: added `ModifyArgs` on `Camera.alignWithEntity -> setPosition(DDD)` to call
  `ModuleSmoothCamera.cameraUpdate(original)` and replace args with `smoothPos` when active. It skips smoothing while
  FreeLook is running, matching the reference.
- `ModuleManager.kt`: registered `ModuleSmoothCamera` near `ModuleCameraClip`.
- `en_us.json`: added `liquidbounce.module.smoothCamera.description`.
- Build: `assemble -x detekt` → **BUILD SUCCESSFUL**. Jar verified `ModuleSmoothCamera.class`, updated
  `MixinCamera.class`, and description key exists once.
- FCL test: enable SmoothCamera, test third person first. Higher Horizontal/Vertical factors = smoother/slower camera
  position. First-person smoothing only applies if `EnableFirstPOV=true`.

## WorldRender delete + LineGlyphs port + TargetHud update (2026-06-20, Codex)

### WorldRender removed
- User requested deleting the bad VegaLine `WorldRender` module and replacing it with Wonderful's line-glyph visual.
- Deleted `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleWorldRender.kt`.
- Removed `ModuleWorldRender` registration from `ModuleManager.kt`.
- Removed all direct `ModuleWorldRender` calls from mixins. Mixins that only existed for WorldRender were left as harmless empty mixin shells if still referenced by `liquidbounce.mixins.json`; mixins that also powered other features kept their non-WorldRender logic.
- Important reverted behavior:
  - Barrier rendering now only comes from `ModuleTrueSight`.
  - World time/weather now only comes from `ModuleCustomAmbience`.
  - Particle gravity/speed/count, item physics, fog, custom skins, fullbright lightmap, chunk fade/light hacks, custom FOV roll/distance/reverse camera, WorldRender bloom all removed.
  - Network particle exploit limits remain: too many particles -> `100`, too fast speed -> `10.0f`.
- `MixinWorldRenderer.java` no longer has the chunk fade `ModifyArg`.

### LineGlyphs port from Wonderful
- Added `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleLineGlyphs.kt`.
- Registered in `ModuleManager.kt` as `LineGlyphs`, aliases: `"Line Glyphs"`, `"LineGlyphes"`.
- Ported Wonderful `LineGlyphes` behavior:
  - Animated orthogonal line paths spawned around camera/player.
  - Settings: `GlyphsCount`, `SlowSpeed`, `DashedLines`, `DashStep`, `Glow`.
  - Deterministic random seed `93882L`, path fade in/out, step-based movement, free-space spawn checks, dashed segment rendering, point-cross markers.
- Color:
  - No manual color setting.
  - Theme sync uses `ClickGuiTheme.accentA` -> `accentB` over `THEME_CYCLE_MS=2200`.
- Shader logic:
  - `MixinWorldRenderer.java` calls `ModuleLineGlyphs.renderShaderWorldPass(levelRenderState.cameraRenderState.pos)` in the same pre-Iris-final-composite hook used by TargetESP, PopChams, and Cubes.
  - Reflection-only Iris detection, matching Cubes/TargetESP style.
  - Shader pass renders line ribbons through `RenderTypes.lightning()`.
  - Initial shader version skipped the normal post-world line render, which made shader-on lines look darker/less clear under shaderpack tonemapping.
  - Fixed after user report: shader pass is now a shader-visible underlay; normal `WorldRenderEvent` still draws crisp `DEBUG_LINES`. Only duplicate normal glow is skipped if shader glow already rendered, matching Cubes' pattern.
  - Shader alpha/width boosted via `SHADER_ALPHA_BOOST=1.85`, `SHADER_WIDTH_BOOST=1.35`.

### TargetHud update
- File: `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/HudElement.kt`, class `TargetHudElement`.
- Layout:
  - Replaced old detached two-box layout (`avatar square + gap + info frame`) with a compact single-frame layout.
  - New bounds: `154 x 62`.
  - Avatar/head now lives inside the main information panel (`FACE=33`) instead of its own separate tile.
  - Equipment row (main hand + armor) moved above the panel frame, not inside the frame.
  - Panel size constants: `PANEL_W=154`, `PANEL_H=43`, `EQUIP_H=16`, `EQUIP_GAP=3`.
- Color / effects:
  - Removed old diagonal sheen/ripple (`drawSheen`, `SHEEN_MS`) from TargetHud.
  - TargetHud outline/glow uses `ClickGuiTheme.accentA/accentB`.
  - Health bar uses ClickGUI ThemeSystem (`themeColor(0f)` -> `themeColor(1f)`) instead of `ModuleHud.themeColor`.
  - Use cooldown ring also uses the TargetHud theme color.
- Hit effects:
  - Existing avatar hit bounce remains, but now applies to the in-panel face.
  - Added HUD-local themed hit particles spawned on rising edge of `target.hurtTime`.
  - Particles are small rounded dots/crosses around the avatar, theme-colored, short-lived (`430..720ms`), capped to avoid accumulation.
  - This references the visual style of `ModuleParticles` without coupling TargetHud to the world-space particle module.

### Verified
- Build command used:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Final build after TargetHud changes: **BUILD SUCCESSFUL**.
- Output jar updated: `build/libs/liquidbounce-0.31.2.jar`.
- Source sweeps:
  - No remaining `ModuleWorldRender` or `ClientModule("WorldRender")` under `src/`.
  - `LineGlyphs` registration and shader hook present.
  - TargetHud old `drawSheen`/`SHEEN_MS`/`INFO_W`/`PANEL_OUTLINE` references removed.

## HUD visual refinement — TargetHud / PotionsHud / Watermark (2026-06-20 → 2026-06-21, Codex)

User iterated from screenshots after the compact TargetHud pass. Latest active code is in
`src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/HudElement.kt`.

### TargetHud latest state
- The earlier `154 x 62` TargetHud note above is now superseded.
- Current constructor/default bounds: `TargetHudElement(..., 136, 56)`.
- Current constants:
  - `PANEL_W=136f`
  - `PANEL_H=41f`
  - `FACE=30f`
  - `EQUIP_H=13f`
  - `EQUIP_GAP=2f`
- `HudManager.kt` default placement updated to center the shorter width:
  `TargetHudElement(screenWidth / 2 - 68, screenHeight - 142)`.
- Equipment row:
  - Raw item/armor icons only.
  - Old rounded slot cells / hotbar-style boxes removed.
  - Row is left-aligned from `x + PAD`, so it defines the start of the HUD instead of floating on the right.
- Outline:
  - The separate hand-tuned TargetHud glow/outline was removed.
  - TargetHud now copies PotionsHud's chroma outline formula exactly: `chromaPeriod=1100L`, same `chromaGlow`
    shade curve, `withAlpha(..., 190)`, stroke `1.15f`.
- Background:
  - Flat pitch-black `PANEL_BG` fill is no longer drawn directly.
  - Added shared helper `themedHudSurface(progress, tint, alpha)` at top of `HudElement.kt`.
  - TargetHud body now uses a subtle ClickGUI-theme-tinted dark 4-corner gradient plus a faint top highlight line.
- Particles:
  - Hit particles are larger and easier to identify.
  - `HIT_PARTICLE_COUNT=18`.
  - Spawn size is now `3.4f..6.2f`, lifetime `520f..820f`, wider spawn spread, stronger alpha cap `245`.
  - Each particle now draws a theme-colored halo + core layer using `ClickGuiTheme.accentA/accentB` through
    `themeColor(...)`.

### PotionsHud latest state
- PotionsHud now uses the same Celestial custom font paths as ClickGUI:
  - Title `"Potions"` uses `ExosGuiRenderer.title(...)` / Montserrat.
  - Potion names, amplifier number, and duration timer use `ExosGuiRenderer.body(...)` / Pixel.
  - Width calculations use `titleWidth(...)` / `bodyWidth(...)` so layout matches the rendered fonts.
- Title size increased from `10f` to `12f`.
- Background:
  - Flat black `BG` fill is no longer drawn directly for the body.
  - Body uses the same `themedHudSurface(...)` dark theme-tinted 4-corner gradient as TargetHud, plus a faint
    top highlight line.
- Title band and animated theme fill are kept.
- Existing PotionsHud chroma outline remains the visual source of truth for TargetHud and Watermark.

### SylentWatermark latest state
- Watermark outline was changed to match PotionsHud.
- Removed old white soft-glow stack and bold white outline (`OUTLINE_COLOR` / `OUTLINE_W` constants removed).
- Current outline uses the same PotionsHud-style chroma wave:
  - `chromaPeriod=1100L`
  - `ClickGuiTheme.accentA/accentB`
  - shade curve `0.16f + 0.62f * wave`
  - `withAlpha(..., 190)`
  - stroke `1.15f`
- Background pill remains dark, text path remains `textInter(...)`.

### Build / verify
- After each small HUD pass, ran:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/bin sh ./gradlew --no-daemon assemble -x detekt`
- Latest build result after themed backgrounds: **BUILD SUCCESSFUL**.
- Output jar remains `build/libs/liquidbounce-0.31.2.jar`.

### FCL test focus
- TargetHud:
  - Verify width is shorter, equipment has no box backgrounds, and outline visually matches PotionsHud.
  - Hit particles should be visibly larger, with theme-colored dot/cross shapes and soft halo.
- PotionsHud:
  - Verify `"Potions"` is larger and uses Montserrat title font.
  - Potion names/timers should use Pixel body font and still align correctly.
  - Body should look dark/tinted, not flat pitch black.
- Watermark:
  - Verify outline color/animation matches PotionsHud rather than the old white border.

## MainScreen Rich removal → interactive blue wave shader menu (2026-06-21, Codex)

User allowed deleting the current Rich-style MainScreen. Replaced the Rich static-image flow with a
modern interactive shader menu matching the user's described video direction.

### Changes
- `LiquidBounceMenuScreen.kt`
  - Removed the Rich lock/unlock flow and static image loading path.
  - Main screen now renders immediately with:
    - full-screen animated blue aurora/wave background,
    - cursor-reactive lens/ripple push around the mouse,
    - top-center clock/date,
    - compact bottom-center dock icon buttons.
  - Existing MSDF `menuicons.png` dock icons remain in use.
- Added shader/pipeline files:
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.vsh`
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.fsh`
  - `render/gui/MenuWaveShader.kt`
  - `render/gui/MenuWaveGui.kt`
  - `render/gui/MenuWaveGuiElementRenderState.java`
- `MixinShaderManager.java`
  - Precompiles `MenuWaveShader` with the other custom GUI pipelines.
- Removed binary asset:
  - the previous static menu background image

### Technical note
- The wave shader uses the same 1.26.2 GUI RenderPipeline pattern as RoundedRect/GlowRect/MenuIcon.
- Time, aspect ratio, and mouse coordinates are packed into UV1/UV2 vertex attributes instead of custom uniforms,
  avoiding the dead legacy framebuffer shader path.
- Shader is lightweight for FCL: one full-screen quad, no texture, no framebuffer, no dynamic loop count.

### Build / verify
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/bin sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.

- Jar verified:
  - `MenuWaveShader.class`
  - `MenuWaveGui.class`
  - `MenuWaveGuiElementRenderState.class`
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.{vsh,fsh}`
  - `resources/liquidbounce/menu/menuicons.png`
  - no previous static menu background image

### FCL test next
- Open the main menu.
- Expected: animated blue wave/aurora background reacts to mouse movement; clock is top-center; dock icons are bottom-center.
- If shader fails on device, fallback dark gradient should still render behind the menu.

## MainScreen shader wave → Liquid Glass / Blob Field (2026-06-21, Codex)

User requested replacing the blue aurora/wave background with a darker liquid-glass blob-field look while keeping the
same shader file names and GUI pipeline.

### Changes
- `resources/liquidbounce/shaders/menu_wave/menu_wave.fsh`
  - Removed the wave-line / aurora formula.
  - Added a lightweight blob-field shader: several slow moving soft metaball-like blobs, midnight navy base, cyan
    rim glow, glassy inner highlights, subtle fresnel-style edge light, and fine dithering.
  - Kept cursor reactivity using the existing mouse varying: the cursor applies a soft pressure/lens distortion and
    slightly increases local cyan glow.
  - Darkens the top-center and bottom-center UI zones slightly so the clock/date and dock remain readable.
- `menu_wave.vsh`, `MenuWaveShader.kt`, `MenuWaveGui.kt`, and `MenuWaveGuiElementRenderState.java` stay on the same
  resource/path contract.

### Build / verify
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/bin sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified:
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.fsh`
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.vsh`
  - `MenuWaveShader.class`
  - `MenuWaveGui.class`
  - `MenuWaveGuiElementRenderState.class`
  - no static menu background image in `resources/liquidbounce/menu/`
- FCL test next: MainScreen should show a dark liquid-glass/blob background, clock/date top-center, dock bottom-center,
  and subtle cursor lens/glow interaction.

### Follow-up: stronger reference-style glass blobs
- User provided `AnotherProject/debug/IMG_20260621_100550.png` and said the first blob shader was too faint.
- `menu_wave.fsh` was strengthened to better match the image:
  - larger cropped glass masses instead of small/faint blobs,
  - metaball iso-surface rims,
  - stronger cyan/electric-blue edges,
  - white-blue specular glints,
  - caustic-like contour highlights,
  - small floating bubble rings,
  - stronger but still controlled cursor pressure/lens response.
- Kept the same filename, shader path, vertex shader, Kotlin pipeline, and GUI render state.
- Build result after this stronger pass: **BUILD SUCCESSFUL**.
- Jar verified:
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.fsh`
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.vsh`
  - `MenuWaveShader.class`
  - `MenuWaveGui.class`
  - `MenuWaveGuiElementRenderState.class`
  - no previous static background image reference.

### Follow-up: blob scale / junction highlight correction
- User noted the first max pass still did not match the reference: bubbles used an awkward out-param + `+=`, blobs
  were too large/blurred, and junctions did not light up enough.
- `menu_wave.fsh` updated:
  - `bubble(...)` now returns a pure float; no out-param mutation.
  - Main metaballs scaled down roughly 15-30%.
  - Added several small/medium secondary metaballs for depth.
  - Added `junction` highlight around neck/merge zones using field threshold + gradient strength.
  - Tightened rim bands and increased cyan/electric rim intensity.
  - Increased white-blue specular glints around rims and junctions.
  - Reduced broad interior haze so blobs read as glass shapes instead of one blurred mass.
  - Relaxed tone mapping so highlights stay punchier.
- Pipeline/path unchanged.
- Build result: **BUILD SUCCESSFUL**.

### Follow-up: continuous liquid-glass mass rewrite
- User rejected the ring/bubble direction: it looked like separated 2D neon bubble rings instead of connected glossy
  liquid glass.
- `src/main/resources/resources/liquidbounce/shaders/menu_wave/menu_wave.fsh` was rewritten again:
  - main visual now comes from a continuous scalar/metaball field threshold (`ISO`) instead of drawing ring objects;
  - overlapping anisotropic ellipsoid blobs are kept close enough to form one cropped liquid mass with smooth necks;
  - domain warp/noise makes edges organic instead of circular;
  - field gradients are sampled with central differences and converted into fake 3D normals;
  - normals drive Fresnel rim, white/cyan specular highlights, fake refraction, and subtle caustic contours;
  - junction/neck areas use adjacent contribution products, so blob merge zones light up white-cyan/electric blue;
  - bubbles were demoted to tiny low-opacity internal inclusions only, not layout-defining rings;
  - cursor interaction now deforms the field/refraction pressure without drawing a visible cursor circle.
- Kept `menu_wave.vsh`, `MenuWaveShader.kt`, `MenuWaveGui.kt`, and `MenuWaveGuiElementRenderState.java` unchanged.
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/bin sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified:
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.fsh`
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.vsh`
- FCL test next: MainScreen should read as a dark continuous glossy liquid-glass mass with connected necks, specular
  glints, refraction-like interior shading, and no dominant 2D bubble rings.

### Follow-up: true 3D raymarched Liquid Glass / Blob Field
- User requested stopping the 2D field/bubble direction entirely and replacing the visual core with a true optimized
  3D raymarcher. Reference read: `AnotherProject/debug/ex.fsh`.
- `src/main/resources/resources/liquidbounce/shaders/menu_wave/menu_wave.fsh` was refactored into a raymarched shader:
  - `RAY_STEPS=26`, `BLOB_COUNT=6`, no textures, no framebuffer, no compute;
  - camera ray setup from existing `vUv` / `vAspect`;
  - six animated 3D ellipsoid SDF blobs merged with smooth union;
  - cursor interaction warps 3D SDF space near the mouse instead of drawing a cursor ring;
  - SDF gradient normal via central differences;
  - glass material includes subtle diffuse, strong Blinn/Phong specular, Fresnel rim, fake refraction against a
    procedural navy background, and absorption/thickness tint;
  - junction detection compares the strongest two nearby blob contributions and boosts sticky necks with
    white/cyan/electric-blue highlights;
  - micro air pockets are tiny 3D embedded inclusions only, not flat rings;
  - top-center clock and bottom-center dock zones are softly attenuated for readability.
- Kept unchanged:
  - `menu_wave.vsh`
  - `MenuWaveShader.kt`
  - `MenuWaveGui.kt`
  - `MenuWaveGuiElementRenderState.java`
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/bin sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified:
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.fsh`
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.vsh`
  - `MenuWaveShader.class`
  - `MenuWaveGui.class`
  - `MenuWaveGuiElementRenderState.class`
  - no `backmenu` / `backmenu.png` references found in source or jar.
- FCL test next: MainScreen should show actual 3D liquid-glass blobs with depth/curvature, cropped merged masses,
  sticky bright junctions, white-blue specular glints, cyan Fresnel rim, and readable clock/dock.

### Follow-up: constellation / neural-network MainScreen concept
- User requested replacing the liquid/wave direction with a modern minimalist constellation/neural-network visual:
  dark blue-black background, glowing nodes, thin connections, subtle depth, cyan/blue/violet glow, mouse-reactive
  distortion, top clock, top FPS counter, and bottom-center dock.
- `src/main/resources/resources/liquidbounce/shaders/menu_wave/menu_wave.fsh` was replaced again:
  - no liquid/raymarch/wave visual core remains;
  - procedural 6x5 node grid with deterministic jitter, slow drift, depth-weighted brightness, and thin connection
    segments;
  - pulsing connection highlights and soft node halos use cyan/blue/violet palette;
  - 18 faint particles add depth without clutter;
  - mouse interaction bends the graph field/lens subtly without drawing a visible ring;
  - top clock/FPS and bottom dock zones are darkened for readability;
  - no textures, no framebuffer, no compute, same `menu_wave` resource path and GUI pipeline.
- `LiquidBounceMenuScreen.kt`:
  - added a compact top-right glass FPS pill using `mc.currentFps`;
  - reduced clock size and moved it slightly upward for a cleaner top-center read;
  - kept dock action buttons and existing menu icon pipeline.
- Kept unchanged:
  - `menu_wave.vsh`
  - `MenuWaveShader.kt`
  - `MenuWaveGui.kt`
  - `MenuWaveGuiElementRenderState.java`
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/bin sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified:
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.fsh`
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.vsh`
  - `MenuWaveShader.class`
  - `MenuWaveGui.class`
  - `MenuWaveGuiElementRenderState.class`
  - `LiquidBounceMenuScreen.class`
  - no `backmenu` / `backmenu.png` references found in source or jar.
- FCL test next: MainScreen should show a clean high-tech constellation graph, readable small top-center clock,
  top-right FPS counter, bottom dock, and no wave/liquid visual.

### Follow-up: constellation sparsity / non-lattice correction
- User said the constellation shader still read like a regular connected lattice: points too evenly spaced and too
  fully stitched together.
- `src/main/resources/resources/liquidbounce/shaders/menu_wave/menu_wave.fsh` was patched:
  - removed the 6x5 grid placement (`GRID_X`/`GRID_Y`) and adjacent-neighbor connection logic;
  - switched to `NODES=24` indexed points;
  - points now come from uneven mini-clusters plus sparse singles, with cluster push-away to create empty pockets;
  - links are chosen through deterministic per-node quotas and partner picks instead of connecting all candidates;
  - most nodes get 1-3 link picks, only a few hub nodes get 4-5;
  - links still require local distance/range gating, random rejection, varied brightness, varied pulse, and varied
    segment length;
  - node/line intensity was reduced slightly to keep the scene airy and readable.
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/bin sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified:
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.fsh`
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.vsh`
  - `MenuWaveShader.class`
  - `MenuWaveGui.class`
  - `MenuWaveGuiElementRenderState.class`
  - `LiquidBounceMenuScreen.class`
  - no `backmenu` / `backmenu.png` references found in source.
- FCL test next: constellation should read airy/random with selective local links and visible empty pockets, not a
  geometric mesh.

### Follow-up: constellation sharpness / bend / performance pass
- User reported MainScreen constellation was still too heavy (10-20 FPS), lines were blurry, lines moved too randomly,
  and wanted links to be flexible/bendable around the middle rather than rigid straight rods.
- `src/main/resources/resources/liquidbounce/shaders/menu_wave/menu_wave.fsh` was optimized and adjusted:
  - reduced nodes from `24` to `18`;
  - reduced particles from `18` to `10`;
  - removed the O(N²) all-pairs link pass entirely;
  - links now render from a small explicit per-node budget: `MAX_LINK_PICKS=5`, with most nodes still capped by
    `linkQuota(...)`;
  - stroke rendering changed from broad Gaussian line blur to a narrow `stroke(...)` using `fwidth` antialiasing;
  - glow contribution was reduced so lines read sharper at higher resolution;
  - node drift now uses bounded triangular motion and clamps to screen bounds, giving a subtle bounce-at-margin feel;
  - every link now bends through a midpoint control point and renders as two joined segments with a faint joint glow,
    so the link can flex in the middle without visually breaking;
  - particle movement/brightness reduced to preserve the clean premium UI read.
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/bin sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified:
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.fsh`
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.vsh`
  - `MenuWaveShader.class`
  - `MenuWaveGui.class`
  - `MenuWaveGuiElementRenderState.class`
  - `LiquidBounceMenuScreen.class`
  - no `backmenu` / `backmenu.png` references found in source.
- FCL test next: MainScreen should have much lower shader cost, sharper thin links, subtle bounded motion, and bendable
  midpoint links instead of blurry rigid random lines.

### Follow-up: constellation contrast / perceived blur pass
- User noticed lines become clear at full phone brightness, so the issue was likely contrast/gamma/luminance crush on
  a very dark background rather than actual stroke blur.
- `src/main/resources/resources/liquidbounce/shaders/menu_wave/menu_wave.fsh` adjusted:
  - lifted the dark navy background floor slightly while keeping the overall dark theme;
  - increased radial background fill a little so near-black areas do not crush thin cyan lines;
  - increased link alpha/core contribution;
  - reduced broad link glow contribution so brightness comes from the crisp stroke rather than haze;
  - boosted lineFine cyan contribution and node core slightly;
  - added a tiny pre-tonemap lift and relaxed tonemapping (`0.88` -> `0.82`, exposure `1.36` -> `1.40`).
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/bin sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified:
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.fsh`
  - `resources/liquidbounce/shaders/menu_wave/menu_wave.vsh`
  - `MenuWaveShader.class`
  - `MenuWaveGui.class`
  - `MenuWaveGuiElementRenderState.class`
  - `LiquidBounceMenuScreen.class`
  - no `backmenu` / `backmenu.png` references found in source.
- FCL test next: test at normal phone brightness. Lines should read clearer without needing max screen brightness,
  but should not regain the previous blurry glow.

## Custom MainScreen deleted / Vanilla title restored (2026-06-22, Codex)

User requested deleting the current custom MainScreen and reverting to vanilla.

### Changes
- `MixinMinecraftClient.java`
  - Removed the `TitleScreen` redirect that canceled vanilla and opened `LiquidBounceMenuScreen`.
  - Removed the `AccessibilityOnboardingScreen` redirect to the custom menu.
  - Vanilla title/accessibility screens now pass through the existing `ScreenEvent` hook normally.
- Deleted custom menu screen and menu-only render pipeline/assets:
  - `LiquidBounceMenuScreen.kt`
  - `MenuIconGui.kt`
  - `MenuIconShader.kt`
  - `MenuWaveGui.kt`
  - `MenuWaveShader.kt`
  - `MenuWaveGuiElementRenderState.java`
  - `resources/liquidbounce/menu/menuicons.png`
  - `resources/liquidbounce/shaders/menu_icon/`
  - `resources/liquidbounce/shaders/menu_wave/`
- `MixinShaderManager.java`
  - Removed precompile calls for `MenuIconShader` and `MenuWaveShader`.

### Build / verify
- Ran:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/bin sh ./gradlew --no-daemon clean assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar/source verified no remaining `LiquidBounceMenuScreen`, `MenuWave`, `MenuIcon`, `menu_wave`, `menu_icon`, or
  `menuicons` entries.

### FCL test next
- Launch client to title screen.
- Expected: vanilla Minecraft title screen, not the custom LiquidBounce constellation/dock menu.

## ClickGUI/HUD rotating chroma highlight removed (2026-06-22, Codex)

User requested removing the rotating color/chroma highlight effect from ClickGUI and HUD.

### Changes
- `ClickGuiScreen.kt`
  - Replaced `renderChromaHighlight(...)` with `renderStaticThemeHighlight(...)`.
  - Selected category row, selected Theme sidebar row, and enabled module cards now use a static ClickGUI-theme
    fill/gradient outline instead of a time-based rotating/chroma fill.
  - Removed `themeBlend(...)` and the `CHROMA_*` constants.
- `HudElement.kt`
  - PotionsHud outline now uses a static mixed ClickGUI accent.
  - PotionsHud title band now uses a static two-accent gradient with the same top-corner shape.
  - TargetHud and SylentWatermark outlines now use the same static mixed accent.
  - Base HUD `accent(offset)` helper no longer uses `Color.HSBtoRGB` / `System.currentTimeMillis`; it maps offsets
    to a static ClickGUI accent blend.
- `ExosGuiRenderer.kt`
  - Removed unused `animatedThemeBorder(...)` and `animatedThemeFill(...)` helpers so the old effect is not reused.

### Build / verify
- Source sweep: no remaining `animatedThemeBorder`, `animatedThemeFill`, `renderChromaHighlight`, `themeBlend`,
  `CHROMA_*`, `chromaGlow`, `chromaPeriod`, or `chromaT` symbols.
- Ran:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/bin sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.

### FCL test next
- Open ClickGUI and HUD.
- Expected: selected categories, enabled module cards, PotionsHud, TargetHud, and Watermark keep static theme-colored
  accents but no longer cycle/rotate colors.

## All HUD elements deleted (2026-06-22, Codex)

User requested deleting all HUD elements.

### Changes
- `HudManager.kt`
  - No longer registers any elements. `HudManager.elements` stays empty.
- `HudElement.kt`
  - Removed every concrete HUD element implementation:
    Watermark, ArrayList, text chips, item counters, PotionHud, KeyBinds, TargetHud, SylentWatermark, radar,
    staff board, crosshair widgets, hotbar, legacy HUD, particles, etc.
  - Kept only the abstract `HudElement` base contract so `ModuleHUD`/`HudScreen` still compile and can support future
    new elements.
- `ModuleHUD.kt`
  - Removed the stale TargetHud toggle and HUD color setting.
  - Element enable/sync functions now safely no-op against the empty manager.

### Build / verify
- Ran:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/bin sh ./gradlew --no-daemon clean assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified: no old concrete HUD element classes remain. HUD jar entries are only:
  `HudConfig`, `HudElement`, `HudManager`, `HudScreen`, and `HudScreenHelper`.

### FCL test next
- Enable HUD/open chat/HUD editor.
- Expected: no HUD overlays render; the HUD editor has no elements to select or drag.

## Simple top-left watermark added (2026-06-22, Codex)

User requested starting a new simple HUD watermark:
- small user avatar/head at the top-left,
- text content `SYLENT 0.1`,
- compact frame sized to the content,
- background synced with ClickGUI style,
- white text using the Celestial font path.

### Changes
- `HudElement.kt`
  - Added `SimpleWatermarkElement`, element name `Watermark`.
  - Uses `drawSkinFace(...)` with the local `AbstractClientPlayer.skin.body().id()` texture; falls back to the
    vanilla default skin and then a small `S` tile if the texture is not ready.
  - Draws two compact frames: 18px avatar square and a text frame sized from `ExosGuiRenderer.titleWidth("SYLENT 0.1")`.
  - Background uses dark surfaces mixed with `ClickGuiTheme.accentA/accentB`; text is white and rendered through
    `ExosGuiRenderer.title(...)` (Celestial/Montserrat title font).
- `HudManager.kt`
  - Registers only `SimpleWatermarkElement(6, 6)`.
  - No other HUD elements are restored.

### Build / verify
- Ran:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/bin sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified `SimpleWatermarkElement.class` is present.

### FCL test next
- Enable HUD in-game.
- Expected: top-left small skin head plus `SYLENT 0.1` in a compact ClickGUI-themed frame, white Celestial font text.

### Follow-up: avatar size + default skin fallback (2026-06-22, Codex)
- User said the avatar head was too small and some default skins could show as the purple missing-texture tile.
- `SimpleWatermarkElement` changes:
  - Avatar frame increased from `18px` to `24px`; face draw area is now `20px` after inset.
  - Text frame stays compact at `20px` height and is vertically centered beside the larger avatar.
  - `localSkinId()` now uses the player skin only when `skin.secure()` is true and the body id is not
    `MissingTextureAtlasSprite.getLocation()`.
  - Otherwise it falls back to `DefaultPlayerSkin.get(player.uuid).body().id()`, covering the broader modern default
    skin set instead of only Steve/Alex assumptions.
- Build:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/bin sh ./gradlew --no-daemon assemble -x detekt`
  → **BUILD SUCCESSFUL**.

## Module cleanup + render module repair batch (2026-06-22, Codex)

User requested:
- delete the current ClickAction and port VCore 0.8 ClickAction;
- fix FullBright Gamma;
- fix CustomAmbience Fog / ModifyWorld / Color / light-related settings;
- fix CustomModels Rabbit;
- fix ItemPhysics;
- delete a large group of old combat/render/misc/player modules.

### Deleted modules
- Removed module source files and `ModuleManager` registrations for:
  `NoBob`, `NoHurtCam`, `NoFov`, `NoSignRender`, `NoSwing`, `ProphuntESP`, `QuickPerspectiveSwap`,
  `MurderMystery`, `LogoffSpot`, `AttackEffects`, `AutoF5`, `CrystalView`, `CombineMobs`,
  `DamageParticles`, `HoleESP`, `Notifier`, `AutoFish`, `TNTTimer`, `Freeze`, `InventoryTotem`,
  `TriggerBot`, `SilentAim`, `AutoCrystal`, `AutoAnchor`, `KeyPearl`, `AutoHitCrystal`.
- Removed or neutralized their dependent hooks/references:
  - `ModuleManager.kt` registrations/imports.
  - `ModuleAutoDodge.kt` no longer checks `ModuleMurderMystery`.
  - `PhaseBlink.kt` KDoc no longer links `ModuleFreeze`.
  - `MixinFeatureRenderer.java` no longer references `ModuleLogoffSpot`.
  - `MixinEndCrystalEntityModel.java` / `MixinEndCrystalEntityRenderer.java` no longer apply `CrystalView`.
  - `MixinGameRenderer.java`, `MixinCamera.java`, `MixinAbstractClientPlayerEntity.java`,
    `MixinClientPlayerEntity.java`, `MixinEntityRenderer.java`, `ModuleZoom.kt` cleaned of removed modules.
- Source sweep and jar/source-jar scans confirmed no deleted module names/classes remained.

### ClickAction — VCore 0.8 port
- Replaced `ModuleClickAction.kt` with VCore-style behavior:
  - toggles: `Pearl`, `XP`, `Friend`;
  - pearl modes: `Legit` / `Rage`;
  - one-shot pearl/friend binds;
  - hold-to-throw XP bottles;
  - XP `AntiWaste` armor durability threshold;
  - XP `Rotation` pitch setting;
  - moving legit inventory-pearl path blocks movement briefly and swaps/throws/restores.
- Follow-up fix after FCL/user test:
  - VCore also keeps the player pitch spoofed on sync while XP is held.
  - Added `PlayerNetworkMovementTickEvent` PRE handler that sets `player.pitch = XpRotation.pitch`
    when XP Rotation is active.
  - XP bottle use now calls the 1.26 packet-aware `interaction.interactItem(player, hand, yaw, pitch)`
    overload, so `ServerboundUseItemPacket` receives the configured pitch.

### FullBright Gamma
- `MixinLightmapRenderStateExtractor.java` was previously effectively stubbed for Gamma.
- Added 1.26 lightmap extraction hook:
  - replaces gamma option value with `ModuleFullBright.FullBrightGamma.gamma` while Gamma mode runs;
  - preserves XRay fullbright behavior;
  - preserves AntiBlind darkness cancellation.
- Result: Gamma mode now affects the 1.26 `LightmapRenderStateExtractor` path instead of only animating an unused value.

### CustomAmbience
- Old framebuffer/GL path was obsolete on 1.26.2.
- Reworked `ModuleCustomAmbience.kt` around 1.26 render states:
  - `FogConfigurable.modifyFogColor(Vector4f)`;
  - `FogConfigurable.modifyFogData(FogData)`;
  - `FogConfigurable.modifyClearColor(Int)`;
  - `FogConfigurable.shouldDisableWorldFog()`;
  - `ModifyWorld.modifyColor(Int)`;
  - `CustomLightColor.edit(GpuTexture, LightmapRenderState)`.
- Added/filled hooks:
  - `MixinBackgroundRenderer.java` targets `FogRenderer.computeFogColor` and `FogRenderer.updateBuffer`.
  - `MixinGameRenderer.java` can force `FogRenderer.FogMode.NONE` for DisableWorldFog.
  - `MixinWorldRenderer.java` modifies the level clear color.
  - `MixinSkyRenderer.java` modifies `SkyRenderer.extractRenderState` sky color through `ModifyWorld`.
  - `MixinLightmapTextureManager.java` makes the lightmap texture writable and applies custom lightmap edits.
- Removed obsolete global `RenderSystem.clearColor` cancel from `MixinRenderSystem.java`; the world renderer hook is the precise path.
- `CustomLightColor` was made opt-in by default so simply enabling CustomAmbience does not fully overwrite the lightmap texture.

### CustomModels Rabbit
- Runtime log path note: user said `latestlog.log`, but FCL folder contained `/storage/emulated/0/FCL/log/latest_game.log`.
- FCL log root cause:
  `Failed to load CustomModels rabbit texture; disabling Rabbit model rendering.`
  The NPE was from `registerDynamicImageFromResources("dreamcore/models/rabbit.png")` because the asset was missing.
- Restored asset from backup:
  `src/main/resources/resources/liquidbounce/dreamcore/models/rabbit.png`.
- Jar/source-jar verified contain:
  `resources/liquidbounce/dreamcore/models/rabbit.png`.
- Render pipeline port:
  - `ModuleCustomModels.renderRabbit(...)` now submits `RabbitPlayerModel` through `SubmitNodeCollector.submitModel`.
  - `RabbitPlayerModel` stores copied vanilla `PlayerModel` limb/head angles and reapplies them from `setupAnim`,
    because model render submission can call setup later.
  - `MixinLivingEntityRenderer.java` hides vanilla player body when Rabbit applies and submits Rabbit once before layers.
- If Rabbit fails after a missing-texture run, restart FCL/client: old runtime set `rabbitTextureFailed = true` in memory.

### ItemPhysics
- `ModuleItemPhysics.kt` was a stub and `MixinItemEntityRenderer.java` was empty.
- Ported to the 1.26 submit pipeline:
  - `MixinItemEntityRenderer.submit(...)` HEAD calls `ModuleItemPhysics.submitItemEntity(...)` and cancels vanilla when handled.
  - Uses `ItemStackRenderState.getModelBoundingBox()` instead of old private quad/model accessors.
  - Preserves vanilla item layer/tint/foil/special-model submission via `ItemStackRenderState.submit(...)`.
  - Adds grounded/fluid-aware transforms and airborne rotation maps keyed by item UUID.

### Verification
- Because this checkout's `gradlew` was not executable, use `sh ./gradlew`.
- Build commands used:
  - `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon clean assemble -x detekt`
  - `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- `git` is not installed in this Termux workspace.
- `python3` is not installed; jar scans were done with:
  `jar tf build/libs/liquidbounce-0.31.2.jar | awk '...'`.
- Runtime still needs FCL visual testing for:
  - ClickAction XP pitch while holding XP key;
  - CustomModels Rabbit after restart;
  - CustomAmbience sky/fog/lightmap settings in-world;
  - ItemPhysics dropped-item rotation.

## ClickGUI fully removed (2026-06-22, Codex)

User requested removing ClickGUI and "all about it".

### Removed
- Deleted `ModuleClickGui.kt` and removed `ModuleClickGui` registration from `ModuleManager.kt`.
- Deleted the native ClickGUI screen/config/panel/settings stack:
  - `ClickGuiScreen.kt`
  - `ClickGuiConfigManager.kt`
  - `ClickGuiPanel.kt`
  - `ClickGuiPanelWidgetFactory.kt`
  - `ModuleSettingsPopup.kt`
  - `ModuleSettingsScreen.kt`
  - `gui/settings/SettingWidget.kt`
- Deleted ClickGUI-specific tests:
  - `ClickGuiPanelWidgetFactoryTest.kt`
  - `ClickGuiPanelWidgetFactoryValidationTest.kt`
  - `ClickGuiScrollTest.kt`
  - `ClickGuiSettingsTest.kt`
  - `BlurEffectSettingsScreenTest.kt`
- Deleted ClickGUI assets/resources:
  - `resources/liquidbounce/clickgui/`
  - `render/gui/IconGui.kt`
  - `assets/liquidbounce/sounds/clickgui_*.ogg`
  - `clickgui_*` entries from `assets/liquidbounce/sounds.json`
  - `liquidbounce.module.clickGUI.description` from all lang JSONs.

### Reference cleanup
- Removed ClickGUI-only event classes and event registrations:
  - `ClickGuiScaleChangeEvent`
  - `ClickGuiValueChangeEvent`
- Removed stale `ModuleClickGui` imports/comments from:
  - `AutoConfig.kt`
  - `ModelHolster.kt`
  - `ScriptManager.kt`
  - bind/binds/targets/value/model commands
  - `ModuleInventoryMove.kt`
- Removed Java mixin hooks that only existed for the ClickGUI placeholder/client screen:
  - `MixinMinecraftClient.isAClientScreen(...)`
  - attack-cooldown client-screen guard
  - passthrough input handler for client screens
  - `MixinClientPlayerEntity.preventCloseScreen(...)`
- Removed `ModuleSettingsScreen` blur exclusion from `BlurEffectRenderer.kt`.

### Shared theme replacement
- `ClickGuiTheme.kt` was deleted.
- Added `ClientTheme.kt` as the neutral shared accent palette used by surviving render/HUD effects:
  - `SimpleWatermarkElement`
  - `ModuleCubes`
  - `ModuleLineGlyphs`
  - `ModulePopChams`
  - `ModuleTargetESP`
- `GuiConfig.kt` was trimmed to HUD editor grid settings only; old panel/search/color settings were removed.
- `ExosGuiRenderer`, custom font comments, and remaining source comments were updated to remove stale ClickGUI wording.

### Verification
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar/source-jar scans verified no remaining entries for:
  `ClickGUI`, `ClickGui`, `clickgui`, `ModuleClickGui`, `ModuleSettingsScreen`, `ModuleSettingsPopup`,
  `SettingWidget`, or `IconGui`.
- Source/resource sweep also found no remaining ClickGUI references except binary-scan warnings from `.ogg`/`.nbt`
  files during `awk` scans.

### FCL test next
- Launch client and confirm there is no `ClickGUI` module.
- Pressing the old Right Shift bind should no longer open any ClickGUI.
- Existing HUD watermark and theme-colored render effects should still work through `ClientTheme`.

## New native Style 4 ClickGUI replacement added (2026-06-22, Codex)

User provided `AnotherProject/debug/ClickGUI_Style_4_English.md` plus a screenshot reference and requested replacing
the deleted ClickGUI with a new practical premium layout.

### Changes
- Added `ModuleClickGui.kt` back as a small isolated opener module:
  - name `ClickGUI`;
  - category `Render`;
  - default bind `RSHIFT`;
  - `disableActivation=true`, so it opens the screen without staying enabled.
- Added fresh `ClickGuiScreen.kt`; this does NOT restore the old deleted dashboard/dropdown stack.
- Layout follows the Style 4 report:
  - fixed left sidebar;
  - reserved empty category-icon lane for future assets;
  - text logo area matching the reference;
  - bottom profile/account block with username and skin-face fallback;
  - middle module search + A-Z grid;
  - no module icon space in module cards;
  - right settings panel for the selected module.
- Implemented first-pass controls:
  - module toggles from cards/header;
  - category switching;
  - search field;
  - settings scroll;
  - boolean/toggleable controls;
  - int/float sliders;
  - choice/dropdown-style click-to-cycle controls;
  - color preview click cycles to a theme preset;
  - bind fields can capture keyboard or mouse input.
- Uses existing branch infrastructure:
  - `ExosGuiRenderer`;
  - `ClientTheme`;
  - custom title/body fonts;
  - rounded-rect SDF pipeline;
  - `drawSkinFace`.
- Added lang key:
  `liquidbounce.module.clickGUI.description`.
- Registered `ModuleClickGui` in `ModuleManager.kt` near other Render modules.

### Verification
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified:
  - `ModuleClickGui.class`;
  - `ClickGuiScreen.class`;
  - no old `ModuleSettingsScreen`, `ModuleSettingsPopup`, `SettingWidget`, `IconGui`, or `resources/liquidbounce/clickgui/` entries.

### FCL test next
- Launch in-game and press Right Shift.
- Expected: new three-column ClickGUI opens over the game, does not pause, shows account info at bottom-left, has
  category text with blank icon space, module cards without icon space, and a right settings panel.
- Test controls:
  - toggle a harmless module;
  - search by module name;
  - click a boolean setting;
  - drag an int/float slider;
  - click a choice setting to cycle;
  - click a bind field, press a key/mouse button, confirm the bind text updates.

## Style 4 ClickGUI refinements — glass, settings animation, icons, Config/Cloud pages (2026-06-26, Codex)

All work stayed in the new Style 4 stack, mainly:
`src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/ClickGuiScreen.kt`.
No old dashboard/dropdown ClickGUI files were restored.

### Main ClickGUI / module cards
- Removed the enabled-module vertical purple stripe from module cards. Enabled/selected modules now keep only the card fill/outline styling.
- Narrowed the main ClickGUI panel over multiple passes; current constants:
  - `MAIN_WIDTH_RATIO = 0.56f`
  - `MAIN_MIN_W = 430f`
  - `MAIN_MAX_W = 680f`
- Main panel positioning no longer centers the combined `main panel + detached settings panel` group. It keeps the main GUI as centered as possible and shifts only enough to fit the detached panel.
- Module card style is still intentionally dark/readable; transparency/blur is NOT applied to card frames.

### Glass background / blur
- Overrode `extractBackground(...)` in `ClickGuiScreen` to skip vanilla's heavy in-game screen shade.
- `extractRenderState(...)` now draws a lighter `SCREEN_DIM` and calls `context.blurBeforeThisStratum()` guarded by `runCatching`, then advances to the main panel stratum.
- Main ClickGUI container uses transparent glass colors:
  - `SURFACE_0 = 0xB810141A`
  - `SURFACE_1 = 0xAE151A20`
- Sidebar/category background was also made glassy, not solid black:
  - `SIDEBAR_TOP = 0x3A141922`
  - `SIDEBAR_BOTTOM = 0x340D1117`
- Cards, settings panel, setting rows, buttons, sliders, and text remain on their own dark surfaces for readability.

### Detached SettingsModule panel
- Removed the thick purple/pink glow and outline from the settings panel body and animation.
- Added an `X` close button in the settings panel title bar.
- Opening animation was changed from robotic `smoothStep` staging to fast-start/soft-finish easing:
  - `expoOut` for position
  - `cubicOut` for initial card collapse
  - `quintOut` for width/height expansion
- The source module card disappears from the module grid while its settings panel is open, leaving an empty slot.
- Reset logic: right-clicking a different module now always restarts the animation from the newly clicked card, instead of morphing from the previous settings panel or popping instantly.
- The settings panel top Y remains aligned with the main ClickGUI top Y.

### Sidebar / account / new pages
- Account/profile block now only shows avatar, username, and `Premium`; removed the bottom text buttons `Settings`, `Cloud`, and `Exit`.
- Sidebar now has five standard pages plus two special pages:
  - Standard: `Combat`, `Player`, `Movement`, `Render`, `Misc`
  - Special: `Config`, `Cloud`
- A short divider line separates the standard module categories from `Config`/`Cloud`.
- Added internal `ClickGuiPage` enum in `ClickGuiScreen.kt` so `Config` and `Cloud` do not require adding runtime module categories.

### Category icons
- Source icons came from `AnotherProject/debug/Icon/{Cloud,Config,Combat,Misc,Movement,Player,Render}.png`.
- Icons were copied/rebaked to white RGBA while preserving alpha:
  `src/main/resources/resources/liquidbounce/clickgui/icons/{cloud,config,combat,misc,movement,player,render}.png`.
- `ClickGuiScreen.pageIcon(...)` registers them with stable dynamic IDs:
  `liquidbounce:clickgui-icon-<page>`.
- `renderPageIcon(...)` draws them through `RenderPipelines.GUI_TEXTURED`; fallback is the small plus mark if a resource fails.
- Verified icon PNGs are colorType 6 and all visible RGB pixels are white.

### Config page
- New `Config` page uses the existing local config folder:
  `ConfigSystem.userConfigsFolder`.
- Lists local `.json` configs in module-style cards with:
  - `Load` button: `AutoConfig.withLoading { AutoConfig.loadAutoConfig(reader) }`
  - `Delete` button: deletes the selected local config file
  - top `Save` button: writes `config-yyyy-MM-dd_HH-mm-ss.json` via `AutoConfig.serializeAutoConfig(...)`
- Config page has its own scroll state (`configScroll`) and search text (`Search configs...`).
- Action results are sent to chat with `regular(...)` / `markAsError(...)`.

### Cloud page
- New `Cloud` page currently renders only centered title text:
  `Nothing Found`.
- No cloud behavior is implemented yet.

### Verification
- Build command used:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified contains:
  - `resources/liquidbounce/clickgui/icons/cloud.png`
  - `resources/liquidbounce/clickgui/icons/combat.png`
  - `resources/liquidbounce/clickgui/icons/config.png`
  - `resources/liquidbounce/clickgui/icons/misc.png`
  - `resources/liquidbounce/clickgui/icons/movement.png`
  - `resources/liquidbounce/clickgui/icons/player.png`
  - `resources/liquidbounce/clickgui/icons/render.png`

### FCL test next
- Open ClickGUI with Right Shift.
- Confirm sidebar icons render white and `Config`/`Cloud` appear below the divider.
- Confirm account block has no `Settings`/`Cloud`/`Exit` text buttons.
- Confirm `Config` page lists local configs; test `Save`, `Load`, and `Delete` on a disposable config.
- Confirm `Cloud` page displays `Nothing Found`.
- Confirm settings-panel animation resets from the newly clicked module card when switching modules without closing the current panel.

## Style 4 ClickGUI close icon/open animation + LineGlyphs optimization (2026-06-26, Codex)

### ClickGUI
- User added `AnotherProject/debug/Icon/X.png` for the SettingsModule close button.
- Rebaked it to white-on-transparent RGBA:
  `src/main/resources/resources/liquidbounce/clickgui/icons/x.png`.
- `ClickGuiScreen.kt`:
  - close button now draws the PNG through a stable dynamic id `liquidbounce:clickgui-icon-close`;
  - account/profile block moved down by `11f * scale` so it has more space from the `Cloud` sidebar row;
  - added Style 4 main panel open/close scale animation using real `context.pose()` transforms, not the fake compat
    `context.matrices`;
  - open animation is monotonic bounce-like scale-up (`0.78 -> 1.0`) with no rebound/overshoot;
  - close animation is the same curve reversed and delays `super.onClose()` until the scale-out finishes.

### LineGlyphs optimization
- `ModuleLineGlyphs.kt`:
  - shader-world pass now returns immediately unless `Glow=true`, so default/crisp LineGlyphs no longer renders a
    duplicate Iris pre-composite copy before the normal `WorldRenderEvent` pass;
  - shader-world pass now renders only the glow underlay, while the normal pass keeps the crisp DEBUG_LINES geometry;
  - glow stack reduced from three full vertex rebuilds to two layers;
  - visible-glyph and line-vertex scratch buffers are reused instead of allocating fresh lists for each pass.

### Verification
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified contains:
  - `resources/liquidbounce/clickgui/icons/x.png`
  - `ClickGuiScreen.class`
  - `ModuleLineGlyphs.class`

### FCL test next
- Open ClickGUI: main panel should scale in without rebound; ESC/close should reverse-scale out.
- Right-click a module: SettingsModule close button should show the new white X icon, not text.
- Sidebar profile/account block should sit lower and no longer feel crowded against `Cloud`.
- Test LineGlyphs with `Glow=false` and `Glow=true`, with and without shaderpack if possible. Expected: noticeably
  less FPS drop, especially when Iris/shaderpack is active or Glow is enabled.

## Style 4 ClickGUI setting widget pass — CPS range + Technique boxes (2026-06-26, Codex)

### Source checked
- KillAura click settings come from `utils/clicking/Clicker.kt`, not from `KillAuraClicker.kt` directly:
  - `CPS` is `intRange("CPS", 5..8, 1..maxCps, "clicks")`;
  - `Technique` is `enumChoice("Technique", ClickPatterns.STABILIZED)`.
- Current Style 4 renderer lived entirely in:
  `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/ClickGuiScreen.kt`.

### Changes
- Rebaked user asset `AnotherProject/debug/Icon/Tick.png` to white-on-transparent RGBA:
  `src/main/resources/resources/liquidbounce/clickgui/icons/tick.png`.
- `ClickGuiScreen.kt`:
  - `ChoiceConfigurable` rows now render as a title plus a black framed option grid;
  - selected choice boxes draw the tick icon and accent outline;
  - clicking a choice box selects that exact mode instead of cycling when the active row is clicked;
  - `RangedValue` now supports `IntRange` / floating ranges visually and interactively;
  - CPS-style range sliders use two smaller inset handles and fill the active segment between them;
  - slider track is inset by the knob radius so handles no longer clip at the left edge.

### Verification
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified contains:
  - `resources/liquidbounce/clickgui/icons/tick.png`
  - `ClickGuiScreen.class`

### FCL test next
- Open ClickGUI -> KillAura settings.
- Under `Clicker`, `CPS` should show two smaller handles around the `5-8` default range with no left-edge clipping.
- `Technique` should show selectable boxes under the title; clicking a box should move the checkmark to that mode.

### Follow-up: enum/ChooseList settings also boxed (2026-06-26, Codex)
- User clarified the same UI treatment is needed for KillAura settings like:
  `PointTracker.HighestPoint`, `PointTracker.LowestPoint`, `PointTracker.BoxPoint`, `RotationTiming`, `Criticals`,
  `Raycast`, etc.
- Root distinction:
  - `AngleSmooth` and `Technique` are `ChoiceConfigurable` and were already covered by the first boxed-selector pass.
  - `HighestPoint`, `LowestPoint`, `BoxPoint`, `RotationTiming`, `Criticals`, and `Raycast` are `enumChoice(...)`,
    which renders as `ChooseListValue`, so they still used the old single-value cycle row.
- `ClickGuiScreen.kt` now renders `ChooseListValue` with the same title + black framed option boxes + tick icon
  selector as `ChoiceConfigurable`.
- Clicking a specific enum box selects that exact enum value through `setByString(...)`; it no longer just cycles the
  row.
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.

## Wyvern-style HUD cleanup — remove ArrayList/StaffList + Watermark rework (2026-06-27, Codex)

User requested a narrow continuation of the Wyvern HUD port:
- remove `ArrayList`
- remove `StaffList`
- rework `Watermark` closer to Wyvern-DLC
- add the missing Watermark asset/icon
- do **not** touch ClickGUI, PotionsHud, HotkeysHud, TargetHUD, Inventory, Notifications, or other HUD elements.

### Important carry-forward state
- Recent PotionsHud and HotkeysHud alignment/animation fixes are considered good. Do **not** rewrite or undo them.
- PotionsHud/HotkeysHud still use centered title/icon/row layout math and row animation state in `WyvernHudElements.kt`.
- This pass intentionally did not change those elements.

### Source checked
- Target:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleHUD.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/HudManager.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/WyvernHudElements.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/HudConfig.kt`
- Reference:
  - `AnotherProject/debug/wyvern-dlc/src/main/java/wtf/wyvern/client/hud/elements/component/WatermarkComponent.java`
  - `AnotherProject/debug/wyvern-dlc/src/main/resources/assets/wyvern/fonts/msdf/logo.png`

### Root cause
- ArrayList and StaffList were still part of the live HUD settings/registration path, so they could render and appear in the HUD editor.
- Current Watermark used a rough local layout instead of the Wyvern-DLC structure:
  left logo block, client name/version, username, FPS, and server/singleplayer segments.
- Wyvern-DLC's actual logo reference is an MSDF font atlas, not a directly usable HUD PNG, so the target project needed a normal drawable texture.

### Changes
- `ModuleHUD.kt`
  - Removed live HUD booleans for `ArrayList` and `Staff`.
  - `isWyvernElementVisible(WyvernHudElement.ARRAY_LIST)` now returns `false`.
  - `isWyvernElementVisible(WyvernHudElement.STAFF)` now returns `false`.
- `HudManager.kt`
  - Removed live registrations for:
    - `WyvernArrayListElement("ArrayList", ...)`
    - `WyvernStaffElement("Staff", ...)`
    - `WyvernArrayListElement("ArrayListV2", ...)`
    - `WyvernStaffElement("StaffV2", ...)`
  - Active registration list is now Watermark, Information, Potions, Keybinds, TargetHUD, Notify, WatermarkV2, KeybindsV2, PotionsV2, TargetHUDV2, Inventory, and Hotbar.
- `WyvernHudElements.kt`
  - Reworked `WyvernWatermarkElement.renderClassic(...)` to use a compact Wyvern-DLC-like 18px dark rounded bar.
  - Classic Watermark now has:
    - left black logo area
    - accent logo icon
    - `Wyvern` text
    - accent version text
    - username segment
    - FPS segment
    - server/singleplayer segment
    - centered text/icon math
    - dynamic width with server truncation to avoid mobile overflow
  - Reworked compact HUD2 Watermark to use the Wyvern-like black circle + small black card layout.
  - Added `HudDraw.WYVERN_LOGO` dynamic resource id and a small `HudDraw.dot(...)` helper for segment markers.
- Asset added:
  - `src/main/resources/resources/liquidbounce/hud/icons/wyvern_logo.png`
  - This is a normal transparent PNG derived from the Wyvern logo intent, suitable for the current GUI texture renderer.

### Notes
- `WyvernArrayListElement` and `WyvernStaffElement` classes still exist in `WyvernHudElements.kt`, but they are dormant because nothing registers them.
- Old saved HUD config keys for ArrayList/StaffList are inert: `HudConfig` only stores properties by name, and no live element reads those names now.
- Do not delete the dormant classes unless the user specifically asks for source cleanup; leaving them unregistered keeps this pass narrow.

### Verification
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified contains:
  - `resources/liquidbounce/hud/icons/wyvern_logo.png`
  - `resources/liquidbounce/hud/icons/hotkey.png`
  - `resources/liquidbounce/hud/icons/potion.png`

### FCL test next
- Enable HUD in-game.
- Confirm ArrayList no longer renders.
- Confirm StaffList no longer renders.
- Open chat/HUD editor and confirm ArrayList/StaffList are not draggable/editable elements.
- Confirm Watermark shows the Wyvern-style logo and compact dark segmented layout.
- Confirm PotionsHud and HotkeysHud alignment/animations still look unchanged from the previous accepted state.

## TargetHud Skin Frame note (2026-06-27, Codex)

Latest user clarification:
- The user does **not** want a full entity/body/full skin rendered inside the TargetHud circle.
- The desired avatar is the **head/skin face only**.
- The square head should be adjusted/scaled so it fits cleanly inside the circular skin frame.
- User later requested "Restore the previous version and then update Agent1", but interrupted that turn and replaced it with
  "Update Agent1 only". No source restore was performed in that interrupted turn.

Current source state after the last completed TargetHud avatar edit:
- File:
  `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/WyvernHudElements.kt`
- `drawTargetFace(...)` renders player targets through the existing `drawSkinFace(...)` helper.
- Non-player `LivingEntity` targets use `entityHeadTexture(...)` + `drawHeadTextureRegion(...)` to draw a texture crop instead of
  rendering the full entity model.
- Classic circular TargetHud mode scales the square head tile to roughly `size * 0.7071f` and centers it, so the square face fits
  inside the circle instead of covering the circular frame.
- HUD2 keeps the larger square-ish face behavior with `size - 2f`.
- Fallback uses the default player skin face, then accent placeholder if the skin/texture is unavailable.

Verification already done for that source state:
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/system/bin sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL** in 35s.

Carry-forward caution:
- Do not reintroduce the full `GuiGraphicsExtractor.entity(...)` / full body avatar path unless the user explicitly asks for that
  exact restore again.
- Keep this scoped to TargetHud avatar rendering; do not touch ClickGUI, Watermark, PotionsHud, HotkeysHud, ArrayList, StaffList,
  or combat/module logic for this issue.

## HUD chat-drag + item/speed element cleanup (2026-06-28, Codex)

User said current HUD is mostly good and requested only a narrow pass:
1. Fix dragging HUD elements while chat (`T`) is open. Hotkeys could only move on Y; Watermark was the only fully draggable element.
2. Remove the unknown item HUD that showed `x2` when picking/dropping items.
3. Remove the useless center-screen speed readout with default-looking `0.00`.

### Root cause
- Chat drag handler in `ModuleHUD.kt` already set both `element.x` and `element.y`.
- The real problem was inside individual HUD element renderers:
  - `WyvernKeybindsElement` recomputed `x` from `rightOffset` every frame, so drag X was overwritten.
  - `WyvernTargetHudElement` recomputed `x/y` from screen center every frame.
  - `WyvernNotifyElement.renderClassic` recomputed `x/y` from screen center/startY every frame.
- The speed readout was `WyvernInformationElement` (`String.format("%.2f", bps)`), registered as `Information`.
- The `x2` item text was `WyvernHotbarElement.drawCount(...)`, registered as `Hotbar`.

### Changes
- `ModuleHUD.kt`
  - Removed live settings for `Information` and `Hotbar`.
  - `isWyvernElementVisible(WyvernHudElement.INFORMATION)` and `.HOTBAR` now return `false`.
- `HudManager.kt`
  - Removed live registrations for `WyvernInformationElement` and `WyvernHotbarElement`.
  - Active registration list is now:
    `Watermark`, `Potions`, `Keybinds`, `TargetHUD`, `Notify`, `WatermarkV2`, `KeybindsV2`, `PotionsV2`, `TargetHUDV2`, `Inventory`.
  - Because TargetHUD no longer self-centers every frame, its old centered default position is computed once during initialization.
- `WyvernHudElements.kt`
  - Deleted `WyvernInformationElement`.
  - Deleted `WyvernHotbarElement`.
  - `WyvernKeybindsElement` no longer accepts/uses `rightOffset`; it renders from saved/editor `x/y`.
  - `WyvernTargetHudElement.renderClassic/renderV2` now render from saved/editor `x/y`.
  - `WyvernNotifyElement.renderClassic` now uses saved top-left `x/y` instead of forcing screen-center.

### Verification
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified:
  - no `WyvernInformationElement.class`
  - no `WyvernHotbarElement.class`

### FCL test next
- Open chat with `T`.
- Drag `Hotkeys/Keybinds`, `Potions`, `TargetHUD`, `Notify`, and `Watermark`; expected: all draggable on both X and Y.
- Pick up/drop items; expected: no HUD `x2` count from the removed Hotbar element.
- Center screen; expected: no `0.00` speed/BPS element.

### Carry-forward caution
- `WyvernArrayListElement` and `WyvernStaffElement` are still dormant classes only; do not re-register them unless user asks.
- `Inventory` remains registered but disabled by setting by default; do not remove it unless user identifies it separately.

## NameTags Wyvern-DLC port cleanup (2026-06-28, Codex)

User identified the top-left "HUD" glitch was actually `NameTags`, then requested a source-faithful Wyvern-DLC port with no invented fallback/features.

### Source checked
- Target:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/nametags/ModuleNameTags.kt`
  - `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/render/MixinEntityRenderer.java`
- Reference:
  - `AnotherProject/debug/wyvern-dlc/src/main/java/wtf/wyvern/client/modules/impl/render/NameTags.java`

### Root cause
- The top-left glitch came from GUI matrix misuse: the module used the fake `context.matrices`/`PoseStack` path for translation/scale, but vanilla 1.26 GUI extraction reads the real `context.pose()` `Matrix3x2fStack`.
- The previous local port had extra behavior not present in Wyvern-DLC, including non-player/item/mob custom tag behavior and unrelated fallback-style features.

### Changes
- `ModuleNameTags.kt`
  - Reworked render transform to use `context.pose().pushMatrix()/translate()/scale()/popMatrix()`.
  - Kept the Wyvern-style compact player nametag path only.
  - Removed invented item/mob custom tag rendering paths and unrelated max-distance/outline behavior from the current implementation.
  - Kept the exposed `EntityTypes` setting shape, but actual custom render path is player-only to match the checked Wyvern source.
  - Added `shouldCancelVanillaNametag(entity)` and made it player-only.
  - Armor/item row adjusted per user feedback: item scale/box spacing increased so the armor row is wider/taller and less cramped.
  - Name/health text vertical alignment adjusted upward inside the black plate so it reads optically centered instead of sitting low.
- `MixinEntityRenderer.java`
  - Vanilla nametag cancellation now respects `ModuleNameTags.shouldCancelVanillaNametag(entity)`, so it cancels only player nametags while this module is active.

### Verification
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL** after each NameTags adjustment.

### Carry-forward caution
- Do not re-add item/mob nametag rendering into `ModuleNameTags` unless user explicitly asks for a non-Wyvern extension.
- If another overlay item renders at top-left, check for fake `context.matrices` transforms first; use real `context.pose()` for GUI translate/scale.

## Predictions bow preview / ClientBow port (2026-06-28, Codex)

User moved from NameTags to `Prediction` and reported the missing Wyvern behavior: when aiming a bow at a block under/near the crosshair, the predicted path should include marker/landing highlight boxes before the arrow is fired.

### Important source finding
- `AnotherProject/debug/wyvern-dlc/src/main/java/wtf/wyvern/client/modules/impl/render/Predictions.java` only simulates already-existing projectile/item entities.
- The exact bow preview behavior the user described is **not** in Wyvern `Predictions.java`; it is in:
  `AnotherProject/debug/wyvern-dlc/src/main/java/wtf/wyvern/client/modules/impl/render/ClientBow.java`.
- To avoid adding a new user-facing module without request, the `ClientBow` behavior was ported into the existing:
  `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/trajectories/ModulePredictions.kt`.

### Wyvern behavior ported
- Bow preview activates only when main/offhand item is a `BowItem`.
- Uses Wyvern/vanilla pull progress formula:
  `power = (t*t + t*2) / 3`, capped at `1.0`, velocity `power * 3.0`.
- If pull progress is `0` and the player is not using an item, return; if progress is `0` while using, use full `1.0f`, matching `ClientBow.java`.
- Uses player yaw/pitch crosshair direction.
- Simulates 100 ticks with:
  - drag `0.99` each axis;
  - gravity `motionY -= 0.05`;
  - block raycast `ClipContext.Block.COLLIDER`, fluid none;
  - stops on first non-miss hit.
- Rendering:
  - line thickness setting `Thickness` default `2.0`, range `0.5..5.0`;
  - `Markers` setting default `true`, draws small boxes every 3 line segments;
  - `LandingBox` setting default `true`, draws a larger box at block hit position;
  - colors use current `ClientTheme.accentA`, matching local theme infrastructure.

### Other fixes in same file
- `worldRenderHandler` no longer returns early when there are no existing projectile entities if bow preview is available.
- The render pass now disables depth test for this prediction pass to match Wyvern's `drawLine(..., false)` / `drawBox(..., false)` behavior, so preview/landing highlights are not hidden behind blocks.
- Existing projectile landing label item icon was changed from fake `context.matrices` transform to real `context.pose()` transform to avoid the same top-left GUI matrix bug class seen in NameTags.

### Verification
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.

### FCL test next
- Enable `Predictions`.
- Hold/use a bow and aim at a nearby block/floor.
- Expected: path line appears before firing, marker boxes appear along the path, and a landing box appears on the predicted block hit.
- Fire an arrow or throw/drop projectile entities; existing projectile prediction labels/lines should still render.

### Carry-forward caution
- If user asks to compare with Wyvern again, remember bow preview belongs to Wyvern `ClientBow.java`, not `Predictions.java`.
- Do not invent always-show bow preview while merely holding an idle bow unless user explicitly requests a deviation; current behavior follows Wyvern's `pullProgress == 0 && !isUsingItem() return`.

## Animations VCore full render-hook correction (2026-06-28, Codex)

User reported `Animations` modes from VCore 0.8 worked on normal attack, but KillAura attacks / air clicks fell back to the vanilla hand animation for most modes; `Self` was the only mode that did not show the issue.

### Source checked
- Target:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleAnimations.kt`
  - `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/item/MixinHeldItemRenderer.java`
- Reference:
  - `AnotherProject/WorkSpace/VCore-0.8-main/VCore-0.8-main/vcore/features/modules/render/Animations.java`
  - `AnotherProject/WorkSpace/VCore-0.8-main/VCore-0.8-main/vcore/injection/MixinHeldItemRenderer.java`
  - `AnotherProject/WorkSpace/VCore-0.8-main/VCore-0.8-main/vcore/injection/accesors/IHeldItemRenderer.java`

### Root cause
- The earlier port only took over the main-hand melee render branch (`shouldHandle` restricted to sword/axe/pickaxe and skipped use-state/off-hand/crossbow/bow/spear/brush paths).
- VCore's real mixin cancels first-person item rendering at HEAD for every non-map item while `Animations.shouldAnimate(item)` is true, then rebuilds the whole transform path itself. Letting vanilla handle the other branches caused KillAura / air-click paths to show original game animation.

### Changes
- `ModuleAnimations.kt`
  - Replaced the partial melee-only transform with a VCore-style transform dispatcher:
    crossbow, item use states (`NONE/BLOCK/EAT/DRINK/BOW/TRIDENT/SPEAR/BRUSH`), spin attack, and normal swing.
  - Added VCore settings `DisableSwapMain` and `DisableSwapOff`.
  - `shouldRenderCustom(item)` now matches the VCore hook shape: module active + aura gate + non-empty + non-map.
  - Preserved VCore mode selection: configured mode only for sword/axe/pickaxe; other items use `Fade`.
  - Ported the VCore left-arm `Self/Old` special branch instead of forcing the local right-hand transform.
  - Re-applies the existing `ModuleViewModel` translation after custom transforms, matching VCore's post-transform `EventHeldItemRenderer` step without adding a new event system.
- `MixinHeldItemRenderer.java`
  - HEAD hook now cancels and custom-renders all non-map items through `ModuleAnimations.applyVCoreTransforms(...)`, not just main-hand melee.
  - Added renderer tick hook for VCore `DisableSwapMain/Off`: pins `mainHandHeight` / `offHandHeight` to `1.0F` and refreshes visible stacks.
  - Existing map rendering stays vanilla because 1.26 maps use the private map render path.

### Verification
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified:
  - `ModuleAnimations.class`
  - `ModuleAnimations$Mode.class`
  - `MixinHeldItemRenderer.class`

### FCL test next
- Enable `Animations`.
- Test every mode with:
  - normal left-click hit,
  - clicking air,
  - KillAura attacking a target.
- Expected: all modes use the VCore custom animation path consistently, without falling back to vanilla except maps / module disabled / `OnlyAura=true` with no Aura target.

## Animations Wyvern-DLC missing modes added (2026-06-28, Codex)

User requested adding Wyvern-DLC `SwingAnimation` modes into the existing `Animations` module, but only the modes that this project did not already have.

### Source checked
- Target:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleAnimations.kt`
- Reference:
  - `AnotherProject/debug/wyvern-dlc/src/main/java/wtf/wyvern/client/modules/impl/render/SwingAnimation.java`
  - `AnotherProject/debug/wyvern-dlc/src/main/java/wtf/wyvern/utility/mixin/client/HeldItemRendererMixin.java`
  - `AnotherProject/debug/wyvern-dlc/src/main/java/wtf/wyvern/utility/mixin/client/PlayerEntityMixin.java`

### Mode comparison
- Wyvern modes: `Smooth`, `Self`, `Self2`, `Down`, `Forward`, `Touch`, `Pander`, `Curt`, `BlockHit`.
- Existing project modes already present: `Smooth`, `Self`.
- Ported only missing modes: `Self2`, `Down`, `Forward`, `Touch`, `Pander`, `Curt`, `BlockHit`.

### Changes
- `ModuleAnimations.kt`
  - Added Wyvern's `OnlyOnHit` gate: records `AttackEntityEvent` time and applies animation only for 300ms after hit when enabled.
  - Added Wyvern's `Angle` setting for `Self2`.
  - Added exact matrix formulas for `Self2`, `Down`, `Forward`, `Touch`, `Pander`, `Curt`, and `BlockHit`.
  - Kept existing `Smooth` and `Self` untouched per user rule, even though Wyvern also has modes with those names.
  - Wyvern custom modes keep Wyvern's right-arm-only behavior; left arm falls back to the original held-item swing path like Wyvern's mixin.

### Verification
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=$JAVA_HOME/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified:
  - `ModuleAnimations.class`
  - `ModuleAnimations$Mode.class`

### FCL test next
- Open `Animations` settings.
- Confirm new modes exist: `Self2`, `Down`, `Forward`, `Touch`, `Pander`, `Curt`, `BlockHit`.
- Test each with normal click, air click, and KillAura target.
- Toggle `OnlyOnHit=true`; expected: custom swing appears only briefly after real attacks, matching Wyvern behavior.

## TotemParticles MasEffects revive-ring port (2026-06-30, Codex)

User clarified the wanted totem-pop visual is not Dimasik's resource/particle setup. The correct reference is:
`AnotherProject/WorkSpace/MasEffects 1.21.11.jar`.

### Source checked
- `fabric.mod.json`: mod id `maseffects`, version `2.1.0`, Minecraft `~1.21.11`.
- `maseffects.client.mixins.json`: registers `TotemMixin` and `TotemParticleMixin`.
- `net.masuno.events.TotemEvent` bytecode:
  - gated by `MasConfig.CustomTotemEffect` (default true);
  - on entity status `35`, spawns `17` `REVIVE` particles with xSpeed/scaler `2.5`, ySpeed entity id, zSpeed `0`;
  - spawns `99` `REVIVE_SPARK` particles with xSpeed entity id, y/z speed `0`.
- `net.masuno.mixin.TotemParticleMixin`: when custom totem is enabled, sets vanilla `TotemParticle` alpha to `0`.
- `net.masuno.particles.ReviveParticle`: 20 tick lifetime; follows entity center; rotates a two-sided quad using random X/Y/Z angles and `rotY += 20`; alpha follows `sqrt(sin(age / lifetime * PI)) / 1.2 * opacity`; scale is alpha-scaled by `2.5`.
- `net.masuno.particles.ReviveSparkParticle`: lifetime `30..48`; initial random velocity `[-10..10] / 25` per axis; after `lifetime / 4`, computes normalized direction to entity center and sets velocity to `targetDir * ((age - 15) * 0.05)`; expires/vanishes when within `2.0` blocks.

### False lead ruled out
- Dimasik source only has vanilla totem path:
  `ClientPlayNetHandler` status `35` -> `ParticleManager.emitParticleAtEntity(entity, ParticleTypes.TOTEM_OF_UNDYING, 30)` -> vanilla random `EmitterParticle` -> `TotemOfUndyingParticle`.
- Dimasik `Particles.java` declares `"Totem Pop"` but search found no actual usage of that option. It is not the ring-then-pull effect.
- The earlier `ModuleTotemAnimation` sprite-frame approach was wrong for this target and was removed.

### Changes
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleTotemParticles.kt`
  - Replaced the previous Dimasik-style custom particle port with a MasEffects-style effect.
  - Uses existing `TotemPopEvent` from `CombatManager` (already fired on real status-35 packet and fake-player pop), so no new packet event path was needed.
  - Spawns `17` `ReviveParticle` instances plus `99` `ReviveSparkParticle` instances per pop.
  - Settings:
    - `Opacity` default `1.0`, range `0.0..1.0`.
    - `HideVanilla` default `true`.
  - `ReviveParticle` follows player center and draws two-sided rotating quads using MasEffects revive textures.
  - `ReviveSparkParticle` starts at player center, scatters, then returns toward the popped player center.
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/client/MixinSingleQuadParticle.java`
  - No longer remaps totem sprite frames.
  - Redirects `SingleQuadParticle.setSpriteFromAge` and calls `setAlpha(0.0F)` for vanilla `TotemParticle` when `ModuleTotemParticles.shouldHideVanillaParticles()` is true.
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt`
  - Removed `ModuleTotemAnimation` registration.
  - Kept `ModuleTotemParticles` registration.
- `src/main/resources/resources/liquidbounce/lang/en_us.json`
  - Replaced `totemAnimation` description with `totemParticles` description.
- Assets copied from the jar to:
  - `src/main/resources/resources/liquidbounce/maseffects/particles/revive_0.png`
  - `src/main/resources/resources/liquidbounce/maseffects/particles/revive_1.png`
  - `src/main/resources/resources/liquidbounce/maseffects/particles/revive_2.png`
  - `src/main/resources/resources/liquidbounce/maseffects/particles/revive_spark_0.png`
  - `src/main/resources/resources/liquidbounce/maseffects/particles/revive_spark_1.png`
  - `src/main/resources/resources/liquidbounce/maseffects/particles/revive_spark_2.png`
  - `src/main/resources/resources/liquidbounce/maseffects/particles/revive_spark_3.png`
  - `src/main/resources/resources/liquidbounce/maseffects/particles/revive_spark_4.png`

### Verification
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.

### FCL test next
- Enable `TotemParticles`.
- Keep `HideVanilla=true`.
- Pop a totem on self or fake player.
- Expected: vanilla green/yellow totem burst is hidden; revive ring/quads appear around player center; sparks scatter outward briefly and then pull back into the popped entity.

## ElytraTarget module deleted (2026-06-30, Codex)

User requested deleting `ElytraTarget` and updating this file.

### Changes
- Deleted the full module folder:
  `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/elytratarget/`
  - `ModuleElytraTarget.kt`
  - `AutoFirework.kt`
  - `ElytraRotationProcessor.kt`
  - `FireworkUseMode.kt`
  - `TargetEntityMovementPrediction.kt`
  - `TargetRotatePosition.kt`
- `ModuleManager.kt`
  - Removed `ModuleElytraTarget` import.
  - Removed `ModuleElytraTarget` from built-in module registration.
- `ModuleKillAura.kt`
  - Removed the ElytraTarget target-render suppression.
  - Removed the ElytraTarget sprint-block bypass.
  - Removed the `canIgnoreKillAuraRotations` bypass from the facing check.
  - KillAura now behaves normally without any ElytraTarget coordination.
- `MixinLivingEntity.java`
  - Removed the `isFallFlying` return hook that powered ElytraTarget `AlwaysGlide`.
  - Removed now-unused `ModuleElytraTarget`, `LocalPlayer`, `ServerboundPlayerCommandPacket`, and
    `CallbackInfoReturnable` imports.
- Removed `liquidbounce.module.elytraTarget.description` from all language JSON files.

### Verification
- Source sweep found no remaining `ModuleElytraTarget`, `ElytraTarget`, `elytratarget`, or `elytraTarget`
  references under `src/main/kotlin`, `src/main/java`, or `src/main/resources`.
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified no remaining `ElytraTarget` / `elytratarget` entries.

### FCL test next
- Open module list and confirm `ElytraTarget` is gone.
- Regression-check KillAura target rendering and attack rotation without ElytraTarget installed.
- Regression-check normal ElytraFly/ElytraRecast behavior; the deleted `AlwaysGlide` hook should no longer force
  fall-flying restarts.

## VCore ElytraTarget + ElytraMotion port (2026-06-30, Codex)

User requested a source-faithful VCore 0.8 port after deleting the old weak ElytraTarget.

### Source checked
- `AnotherProject/WorkSpace/VCore-0.8-main/VCore-0.8-main/vcore/features/modules/combat/ElytraTarget.java`
- `AnotherProject/WorkSpace/VCore-0.8-main/VCore-0.8-main/vcore/features/modules/movement/ElytraMotion.java`
- Relevant VCore Aura integration:
  `Aura.java`, `aura/rotation/AuraRotationManager.java`, `aura/rotation/Grim.java`, `aura/rotation/Track.java`

### Behavior mapped
- `ModuleElytraTarget` added under Combat.
  - Settings ported: `Prediction`, `Visual`, `Color`, `Mode` (`Auto`/`Default`), `PredictRange`, `PredictMax`.
  - Tracks current KillAura target.
  - Active only when module running, prediction status true, local player is gliding, target is gliding, and local hurt disable window is not active.
  - Prediction point follows VCore formula: clamp player eye into target AABB, then add `target.deltaMovement * predictionScale` only when target horizontal BPS >= 20.
  - Auto prediction scale = `sqrt(targetBps) / 2`, capped by `PredictMax`.
  - If local player is hurt, disables forward targeting for 500ms like VCore `disableForward`.
  - Visual renders a small predicted-point box.
- `ModuleElytraMotion` added under Movement.
  - Settings ported: `AttackDistance`, `AutoFirework`, `MatrixBypass`.
  - Freezes movement when gliding, there is a KillAura target nearby, and ElytraTarget cannot currently target it.
  - Freeze maps VCore `EventMove.cancel + x/y/z=0` to local `PlayerMoveEvent.movement = Vec3.ZERO` and `player.deltaMovement = Vec3.ZERO`.
  - Firework path supports offhand, hotbar, and inventory swap cases using local sequenced interact/silent-hotbar/clickSlot APIs.
  - VCore `EMaceHelper PeakAssist` pause was not ported because this project has no `EMaceHelper`/PeakAssist equivalent.
- `ModuleKillAura.kt` integration:
  - Added VCore-style `ElytraRange=0.5` and `ElytraAimRange=32`.
  - While gliding, scan/rotation range uses `ElytraRange + ElytraAimRange`.
  - ElytraTarget candidates use predicted-point distance instead of live boxed distance.
  - Aim spot becomes the predicted intercept point when ElytraTarget is active.
  - Attack-facing validation for ElytraTarget uses VCore `canAttackElytraTarget`: distance from player pos to predicted point <= prediction - 0.5, or predicted point intersects player box.

### Files changed
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleElytraTarget.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/ModuleElytraMotion.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/ModuleKillAura.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt`
- `src/main/resources/resources/liquidbounce/lang/en_us.json`

### Verification
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified contains:
  - `ModuleElytraTarget.class`
  - `ModuleElytraMotion.class`

### FCL test next
- Enable `KillAura` + `ElytraTarget`; glide toward another gliding player/fake test target.
- Expected: KillAura aims at the predicted box ahead of the target and attacks only when the predicted point is within the VCore hit window.
- Enable `ElytraMotion`; fly near a target that is not yet valid for predictive targeting.
- Expected: movement freezes near the target until ElytraTarget can line up the predictive attack point.
- If using `AutoFirework`/`MatrixBypass`, keep rockets in offhand, hotbar, then inventory to test all three use paths.

## ElytraMotion AutoFirework Grim PacketOrderE fix (2026-06-30, Codex)

User reported Grim flag:
`PacketOrderE (vl:53.0): attacking=false, rightClicking=true, openingInventory=false, releasing=false, sneaking=false, sprinting=false, bed=false, gliding=false, mountJumping=false`
when `ElytraMotion.AutoFirework=true` was used with KillAura/ElytraTarget.

### Grim source checked
- `AnotherProject/Grim/common/src/main/java/ac/grim/grimac/checks/impl/packetorder/PacketOrderE.java`
- `AnotherProject/Grim/common/src/main/java/ac/grim/grimac/checks/impl/packetorder/PacketOrderProcessor.java`
- `AnotherProject/Grim/common/src/main/java/ac/grim/grimac/checks/Check.java`

### Root cause
- `PacketOrderProcessor` sets `using=true` on `USE_ITEM` and exposes `rightClicking=true`.
- It resets that packet-order state only on a tick packet (`flying` / eligible `CLIENT_TICK_END`).
- `PacketOrderE` flags any `HELD_ITEM_CHANGE` while any conflicting action flag is active.
- `ElytraMotion.useFireworkFromHotbar()` selected the rocket slot silently, sent `USE_ITEM`, then immediately reset the silent slot. A later `ensureHasSentCarriedItem()` in the same packet window could restore the original held slot, producing `HELD_ITEM_CHANGE` while Grim still had `rightClicking=true`.

### Fix
- `ModuleElytraMotion.kt`
  - Tracks outgoing `ServerboundUseItemPacket` / `ServerboundUseItemOnPacket` as a local right-click packet window.
  - Clears that window on `PlayerNetworkMovementTickEvent.POST`, matching Grim's tick-packet reset boundary.
  - Hotbar firework no longer calls `SilentHotbar.resetSlot()` immediately after `USE_ITEM`; it holds the silent rocket slot until after movement POST.
  - If AutoFirework would require a held-slot change while the local right-click window is already open, it defers the firework request to the next clean tick instead of sending a PacketOrderE-triggering slot change.
  - Offhand rocket usage remains immediate because it does not require held-slot change.

### Verification
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.

### FCL test next
- Enable `KillAura`, `ElytraTarget`, `ElytraMotion.AutoFirework=true`.
- Test rockets in hotbar first; expected: no `PacketOrderE rightClicking=true` when firework is used near Aura attacks.
- Test offhand rockets; expected: still immediate, no held slot changes.
- Test inventory rockets; expected: still usable, with slot-change guarded if a right-click packet is already active.

## ElytraMotion AutoFirework combat restore correction (2026-06-30, Codex)

User reported the first PacketOrderE fix stopped the flag but also stopped real KillAura hits when
`ElytraTarget + ElytraMotion.AutoFirework + KillAura` were enabled.

### Additional source checked
- VCore `ElytraMotion.useFireworkFromHotbar()`:
  `switchToSilent(fireworkSlot) -> PlayerInteractItemC2SPacket -> switchToSilent(returnSlot)`.
- LiquidBounce `ModuleAutoWeapon.kt`, `SilentHotbar.kt`, `CombatExtensions.kt`, and KillAura attack validation.
- Grim `PacketOrderI.java`, `Post.java`, and relevant `MultiActionsA/E/F.java` in addition to `PacketOrderE.java`.

### Root cause
- The first fix held `SilentHotbar` on the rocket slot until movement POST.
- `ModuleAutoWeapon` also uses `SilentHotbar.serversideSlot` to decide whether an attack must be cancelled and
  retried after weapon switching.
- Holding the rocket slot made AutoWeapon repeatedly see the wrong server-side weapon state, so Aura flew/fireworked
  but did not reliably attack.
- Grim also makes same-tick hotbar firework + attack unsafe:
  - `USE_ITEM` before attack can trip `PacketOrderI` with `rightClicking=true`.
  - Attack before held-slot firework switch can trip `PacketOrderE` with `attacking=true`.

### Fix
- `ModuleElytraMotion.kt`
  - Hotbar firework no longer uses LiquidBounce `SilentHotbar`.
  - It now sends direct `ServerboundSetCarriedItemPacket(fireworkSlot)`, then direct sequenced
    `ServerboundUseItemPacket`, matching VCore's explicit packet style more closely.
  - The restore slot is captured from `SilentHotbar.serversideSlot` so prepared AutoWeapon state is restored.
  - Restore is delayed to the next clean `GameTickEvent` at priority `1003`, before KillAura tick sequences and before
    the next movement packet, avoiding both PacketOrderE and Grim Post ordering.
  - Firework use runs pre-combat at priority `1002`; a local `fireworkWindowOpen` signal is cleared on
    `PlayerNetworkMovementTickEvent.POST`.
- `ModuleKillAura.kt`
  - `validateAttack()` now refuses only the exact firework `USE_ITEM` packet window while gliding.
  - This intentionally skips the one tick used for a hotbar/offhand firework, then Aura resumes after the restore tick.
  - This replaces the old behavior where AutoWeapon could get stuck cancelling attacks because the global
    `SilentHotbar` state stayed on rockets.

### Verification
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.

### FCL test next
- Enable `KillAura`, `ElytraTarget`, `ElytraMotion.AutoFirework=true`.
- Put rockets in hotbar first.
- Expected: firework may consume exactly one combat tick, then the next tick restores the weapon slot and KillAura
  attacks again. It should not turn into continuous “fly only, no hits”.
- Watch Grim for `PacketOrderE rightClicking=true`, `PacketOrderE attacking=true`, and `PacketOrderI attack
  rightClicking=true`.

## ElytraMotion AutoFirework BadPacketsJ + hotbar crit fix (2026-07-01, Codex)

This supersedes the previous "combat restore correction" note above. User reported two regressions after the
PacketOrderE work:
- Grim `BadPacketsJ` still happened occasionally, then increased over long fights.
- Flying crits mostly failed when fireworks were in the hotbar and the offhand held a non-firework item. Offhand
  fireworks only missed rarely.

### Source checked
- VCore `ElytraMotion.java` / `ElytraTarget.java`.
- Grim `BadPacketsJ.java`, `PacketOrderE.java`, `PacketOrderG.java`, `PacketOrderI.java`, and
  `PacketOrderProcessor.java`.
- Current `ModuleElytraMotion.kt`, `ModuleKillAura.kt`, `KillAuraClicker.kt`, `RotationManager.kt`,
  `MixinClientPlayerEntity.java`, and `MixinPlayerInteractItemC2SPacket.java`.

### Root cause
- `BadPacketsJ` compares each `USE_ITEM` packet yaw/pitch to the next tick/movement packet rotation.
- The first priority-only fix made ElytraMotion run after `RotationManager`, but it did not cover explicit movement
  packets sent by KillAura `ON_TICK`. If KillAura sent `Full(...)` with a slightly different attack rotation before
  vanilla `sendPosition`, Grim compared the firework `USE_ITEM` rotation against that packet and flagged.
- Hotbar firework usage changed the server main-hand slot to the rocket. The delayed restore avoided
  `PacketOrderE`, but it also meant KillAura could attack while the server still saw rockets in main hand. This
  explains why hotbar rockets caused mostly normal hits, while offhand rockets mostly preserved crits.
- The previous `ModuleKillAura.validateAttack()` firework-window gate was too broad and has been removed; it was the
  source of the earlier "fly only / no hits" behavior.

### Fix
- `RotationManager.kt`
  - Added `useItemRotationOverride`.
  - On outgoing `ServerboundUseItemPacket`, store the exact packet yaw/pitch.
  - Clear the override only when an outgoing `ServerboundMovePlayerPacket` is seen.
- `MixinClientPlayerEntity.java`
  - Silent movement yaw/pitch now uses `RotationManager.movementPacketRotation()`, which prefers the pending
    `USE_ITEM` rotation over normal `currentRotation`.
- `KillAuraClicker.kt`
  - KillAura `ON_TICK` pre-attack `Full(...)` packets also use `RotationManager.useItemRotationOverride` when
    present, so they cannot be the first mismatching tick packet after a firework `USE_ITEM`.
- `ModuleElytraMotion.kt`
  - Hotbar fireworks no longer use main hand directly.
  - The rocket hotbar slot is temporarily selected, swapped into offhand with `SWAP_ITEM_WITH_OFFHAND`, then the
    server main-hand slot is restored before sending `USE_ITEM` from `OFF_HAND`.
  - Offhand restoration is delayed until the right-click packet window has reset, avoiding the original
    `PacketOrderE` right-click slot-change problem.
  - `canUseTimedFirework()` now waits for pending offhand restore as well as pending held-slot restore.

### Current behavior
- Offhand rockets: still use `OFF_HAND` immediately.
- Hotbar rockets: borrow through offhand, keep server main hand on the combat slot for KillAura, then restore offhand
  next clean tick.
- Inventory rockets: still use the existing inventory-swap path; if this later shows the same crit problem, port the
  hotbar offhand-borrow path to the inventory temporary slot too.

### Verification
- Build command:
  `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.

### FCL test next
- Enable `KillAura`, `ElytraTarget`, `ElytraMotion.AutoFirework=true`.
- Test long fights with rockets in hotbar and a non-firework offhand item.
- Expected: no increasing `BadPacketsJ`; hotbar rockets should no longer force normal hits because Aura's server
  main hand stays on the combat slot.
- Watch Grim for `BadPacketsJ`, `PacketOrderE`, `PacketOrderG`, and `PacketOrderI`.

## ElytraTarget/ElytraMotion VCore correction — prediction visual + hotbar rocket route (2026-07-01, Codex)

User reported two remaining VCore-port issues:
- ElytraTarget prediction worked but felt incomplete/slow, and the predicted square visual was missing.
- Crits worked better with offhand fireworks than with hotbar fireworks; the current hotbar path borrowed rockets
  through offhand and could flag Grim.

### Source checked
- VCore:
  - `VCore-0.8-main/vcore/features/modules/combat/ElytraTarget.java`
  - `VCore-0.8-main/vcore/features/modules/movement/ElytraMotion.java`
  - `VCore-0.8-main/vcore/features/modules/combat/Aura.java`
  - `VCore-0.8-main/vcore/features/modules/combat/aura/rotation/Track.java`
- Grim:
  - `PacketOrderE.java`
  - `PacketOrderG.java`
  - `BadPacketsJ.java`

### Findings
- VCore `ElytraTarget` has no separate "PredictSpeed" setting. Prediction scale is still:
  `sqrt(targetBps) / 2`, capped by `PredictMax`, and only applied when target BPS is at least `20`.
- VCore speed matching is actually `ElytraMotion.AutoFirework`: when not frozen, Aura target exists, and local BPS
  is `<= 30`, use a firework every `500ms`.
- VCore hotbar fireworks use main hand with silent slot packets:
  `switchToSilent(fireworkSlot) -> USE_ITEM MAIN_HAND -> switchToSilent(returnSlot)`.
- The current fork had drifted into a non-VCore offhand-borrow path:
  selected hotbar rocket -> `SWAP_ITEM_WITH_OFFHAND` -> `USE_ITEM OFF_HAND` -> delayed offhand restore.
  That can trip Grim `PacketOrderG` (`action=swap`) and also leaves combat timing less faithful to VCore.
- The prediction box existed but manually subtracted camera coordinates instead of using the current 1.26 world render
  helper. Other working world boxes use `withPositionRelativeToCamera(...)`.
- Current Track smoothing missed VCore's elytra branch:
  after more than 5 elytra-flying ticks, VCore uses `pitchStep = 180F`, so the predicted point is reached without
  slow pitch acceleration.

### Changes
- `ModuleElytraTarget.kt`
  - Prediction visual now draws a local `0.2` box inside `withPositionRelativeToCamera(predicted)`.
  - This keeps the original VCore filled box + outline behavior but uses the correct fork render transform.
- `ModuleElytraMotion.kt`
  - Removed the hotbar offhand-borrow path and all pending offhand restore state.
  - Hotbar fireworks now follow the VCore main-hand route: send `ServerboundSetCarriedItemPacket(rocketSlot)`,
    send `USE_ITEM MAIN_HAND`, then restore the previous effective server slot.
  - Restore is still delayed until the local right-click packet window resets, because Grim `PacketOrderE` flags
    held-slot changes while `USE_ITEM` is still active. No `SWAP_ITEM_WITH_OFFHAND` packet is sent anymore.
  - Existing `BadPacketsJ` rotation override remains: firework `USE_ITEM` yaw/pitch is held for the next movement
    packet and KillAura `ON_TICK` packet.
- `TrackAngleSmooth.kt`
  - Added VCore's sustained-elytra pitch snap: if `player.isGliding && player.fallFlyingTicks > 5`, pitch step is
    `180f`. This prevents Track mode from lagging behind the predicted ElytraTarget point.

### Verification
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified contains updated:
  - `ModuleElytraTarget.class`
  - `ModuleElytraMotion.class`
  - `TrackAngleSmooth.class`

### FCL test next
- Enable `KillAura`, `ElytraTarget`, `ElytraMotion.AutoFirework=true`.
- Test with both players gliding:
  - Expected: the small prediction square appears at the predicted intercept point.
  - Expected: Track mode should snap pitch to the predicted point after sustained gliding instead of slowly drifting.
- Put fireworks in the hotbar and keep the offhand as a non-firework item.
  - Expected: ElytraMotion uses main-hand hotbar firework silently, then restores the combat slot on the next clean
    packet window.
  - Expected: no offhand swap packet, so watch specifically for `PacketOrderG action=swap` disappearing.
  - Continue watching `BadPacketsJ`, `PacketOrderE`, and `PacketOrderI`.

## EMaceHelper rollback (2026-07-01, Codex)

User rejected the EMaceHelper/mace-gated direction for Predict Speed. Rolled back that path:
- Deleted `ModuleEMaceHelper.kt`.
- Removed `ModuleEMaceHelper` from `ModuleManager`.
- Removed KillAura EMace hooks: no extra aim-range override, no PeakAssist aim offset, no EMace post-attack marker.
- Removed `ModuleMaceSwap.hasMaceInHotbar()` that was only added for EMace.
- Removed EMace-specific external firework request API from `ModuleElytraMotion`.

Kept the previous ElytraTarget/ElytraMotion patch state: prediction visual, VCore main-hand hotbar rocket route,
and the non-EMace hotbar firework packet-order queue.

## ElytraTarget/ElytraMotion clean VCore reset (2026-07-01, Codex)

This supersedes the previous ElytraMotion packet-order queue / rotation-override notes above.

### Source checked
- Current broken files:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/ModuleElytraMotion.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleElytraTarget.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/ModuleKillAura.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/RotationManager.kt`
- VCore reference:
  - `AnotherProject/WorkSpace/VCore-0.8-main/VCore-0.8-main/vcore/features/modules/movement/ElytraMotion.java`
  - `AnotherProject/WorkSpace/VCore-0.8-main/VCore-0.8-main/vcore/features/modules/combat/ElytraTarget.java`
  - VCore `Aura.java`, `AuraRotationManager.java`, `Track.java`, `Grim.java`

### Changes
- `ModuleElytraMotion.kt` was replaced with a clean VCore-behavior port:
  - settings: `AttackDistance`, `AutoFirework`, `MatrixBypass`;
  - state: `freeze` plus a 500ms `Chronometer`;
  - update flow: pause seam, gliding gate, current KillAura target, `check(target, ElytraTarget)`, MatrixBypass firework while frozen, AutoFirework when not frozen and player BPS <= 30;
  - move flow: if `freeze`, zeroes `PlayerMoveEvent.movement` and local delta;
  - firework flow is immediate VCore style: offhand use, hotbar silent switch -> use -> switch back, inventory SWAP -> use -> SWAP back, main-hand-use offhand SWAP path.
- `ModuleElytraTarget.kt` was replaced with a clean VCore-behavior port:
  - settings restored to VCore names/defaults including `Predict Range`, `Predict Max`, and orange `Color` `16752640`;
  - `status`, `disableForward`, hurt 500ms gate, target BPS, auto scale `sqrt(bps)/2` capped by Predict Max;
  - predicted point = player eye clamped into target AABB, plus target velocity * prediction only when target BPS >= 20;
  - visual draws the VCore 0.2 filled/outlined prediction box through the current 1.26 world render helper.
- EMace/PeakAssist:
  - no current source module exists, so no helper was reintroduced;
  - ElytraMotion keeps the VCore pause seam as a safe false-returning function, not a fake stub module.
- Removed broken custom glue:
  - `ModuleElytraMotion.shouldBlockAuraAttack` and KillAura's attack-cancel gate;
  - delayed right-click windows / pending restore / deferred or queued fireworks;
  - `RotationManager.useItemRotationOverride` and KillAuraClicker's use of it.

### Verification
- Source sweep under `src/`: no `ModuleEMaceHelper` / `EmaceHelper` / `EmaceHepler` / `MaceHelper`, no
  `shouldBlockAuraAttack`, no `useItemRotationOverride`, no old ElytraMotion queued/deferred firework state.
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Jar verified contains only the corrected Elytra classes:
  - `ModuleElytraTarget.class`
  - `ModuleElytraMotion.class`
  - no EMace helper class entries.

### FCL test next
- Enable `KillAura`, `ElytraTarget`, and `ElytraMotion`.
- While both players are gliding, confirm:
  - ElytraTarget prediction box appears at the predicted point;
  - KillAura aims/attacks using the predicted point;
  - ElytraMotion freezes only when VCore `check()` says Aura cannot yet target and the target is within `AttackDistance`.
- Test fireworks:
  - offhand rockets -> `OFF_HAND` use;
  - hotbar rockets -> immediate silent main-hand switch/use/switch-back;
  - inventory rockets -> SWAP into hotbar/use/SWAP back;
  - while using main hand -> temporary offhand SWAP/use/SWAP back.

## FakePlayer hit-based melee damage + ElytraMode (2026-07-01, Codex)

User wanted `FakePlayer` fixed only, without touching ClickGUI/HUD/Theme/ElytraTarget/ElytraMotion/KillAura or
unrelated combat logic.

### Source checked
- Target:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/misc/fakeplayer/ModuleFakePlayer.kt`
- Related fake player classes read but not edited:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/command/commands/ingame/fakeplayer/FakePlayer.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/command/commands/ingame/fakeplayer/MovingFakePlayer.kt`
- Existing helpers checked:
  - `AttackEntityEvent` hook in `MixinClientPlayerInteractionManager.java`
  - `CombatExtensions.kt` attack/swing flow
  - `ModuleCriticals.kt`
  - `ItemExtensions.kt` stack attack modifiers / sharpness helpers

### Root cause
- Melee damage was skipped when `clone.invulnerableTime != 0`.
- `applyEstimatedDamage(...)` called `clone.handleDamageEvent(...)` / `clone.animateHurt(...)`, and vanilla
  `handleDamageEvent` sets `invulnerableTime = 20`, so the fake dummy became time-window based instead of
  attack-event based.
- Initial follow-up finding: the first new damage formula used `player.getAttributeValue(ATTACK_DAMAGE)`, but on the
  cancelled client-only fake attack path that can remain the bare player value (`1.0`). That made weapon/crit tests
  feel capped around 1 damage.
- Swing issue: normal vanilla clicks swing outside `gameMode.attack`, but KillAura/module attacks swing after the
  cancellable `AttackEntityEvent`. Because FakePlayer cancels that event to avoid sending the fake entity id to the
  server, the normal KillAura swing call never runs for FakePlayer.

### Changes
- `ModuleFakePlayer.kt`
  - Melee path no longer returns on `clone.invulnerableTime != 0`.
  - Fake entity attack packet is still cancelled with `event.cancelEvent()`.
  - Melee calls `applyEstimatedDamage(..., preservePreviousInvulnerability = true)`, so hurt animation/particles still
    show, but melee restores the previous invulnerability counter and does not block the next fake hit.
  - Explosion damage path still keeps the old `clone.invulnerableTime` guard, so one explode packet is not multiplied.
  - Attack cooldown is reset after fake melee hits via `player.resetLastAttackedTicks()`.
  - Follow-up damage correction:
    - damage now takes the max of the player attack attribute and held-stack `ATTACK_DAMAGE` attribute modifier
      (`1.0 + stack modifier`), so swords/axes are not reduced to bare hand damage;
    - sharpness fallback uses `ItemStack.getSharpnessDamage()`;
    - enchant damage remains cooldown-scaled;
    - crit multiplier remains `1.5x`;
    - active Criticals packet-style conditions are treated as critical for the local fake-player damage estimate.
  - Follow-up swing correction:
    - FakePlayer local hit now calls `player.swingHand(MAIN_HAND)` before cancelling the outbound fake attack.
    - This is scoped to FakePlayer and does not change KillAura or global combat behavior.
- Added `ElytraMode` settings:
  - `ElytraMode` default `false`
  - `ElytraTakeoffHeight` default `12.0`, range `3.0..60.0`
  - `ElytraSpeed` default `1.2`, range `0.1..5.0`
  - `ElytraDirection`: `SpawnYaw` / `PlayerLook`
  - `ElytraFireworkVisual` default `true`
- ElytraMode behavior:
  - equips Elytra in chest slot;
  - gives rockets in main hand when AutoTotem owns offhand, otherwise offhand;
  - stores spawn position and initial direction;
  - calls the normal `startFallFlying()` API;
  - takeoff phase moves upward until `spawnY + ElytraTakeoffHeight`;
  - straight phase moves forward along stored direction with a tiny descent;
  - keeps yaw/body/head aligned to flight direction.
- Cleanup:
  - module disable removes FakePlayer and clears `CombatManager.popList` entry;
  - world change clears fake reference, death state, and Elytra flight state.

### Verification
- Build command:
  `JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result after first pass: **BUILD SUCCESSFUL**.
- Result after follow-up damage/swing correction: **BUILD SUCCESSFUL**.
- Jar verified contains updated:
  - `ModuleFakePlayer.class`
  - `ModuleFakePlayer$ElytraDirection.class`
  - `ModuleFakePlayer$ElytraFlightPhase.class`
  - `ModuleFakePlayer$MeleeDamageEstimate.class`

### FCL test next
- Spawn FakePlayer and click/attack it 10 times. Expected: all 10 attack events process damage; no melee i-frame wait.
- Test with sword/axe vs hand. Expected: weapon damage is clearly above 1 HP.
- Test normal hit vs jump/packet critical. Expected: crit visibly deals more damage.
- Enable KillAura against FakePlayer. Expected: FakePlayer loses health only on actual attacks and the local hand
  swing is visible.
- Test AutoTotem/pop/explosion once. Expected: TotemPopEvent/PopChams/counters still work; explosion behavior still
  uses its own cooldown guard.
- Enable `ElytraMode`. Expected: FakePlayer equips Elytra/rockets, rises, starts fall-flying, then flies straight as
  a moving ElytraTarget/KillAura test target.

## FakePlayer VCore damage correction + ElytraMode hitbox fix (2026-07-01, Codex)

User requested a narrow FakePlayer-only follow-up. Do not treat this as permission to touch ClickGUI, HUD,
ThemeSystem, ElytraMotion, ElytraTarget, KillAura, or unrelated combat modules.

### Source checked
- Current target:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/misc/fakeplayer/ModuleFakePlayer.kt`
- Related current source read but not edited:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/command/commands/ingame/fakeplayer/FakePlayer.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/command/commands/ingame/fakeplayer/MovingFakePlayer.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/criticals/ModuleCriticals.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/utils/item/ItemExtensions.kt`
  - `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/network/MixinClientPlayerInteractionManager.java`
- Read-only VCore reference:
  - `AnotherProject/WorkSpace/VCore-0.8-main/VCore-0.8-main/vcore/features/modules/misc/FakePlayer.java`
  - `AnotherProject/WorkSpace/VCore-0.8-main/VCore-0.8-main/vcore/utility/player/InventoryUtility.java`
  - `AnotherProject/WorkSpace/VCore-0.8-main/VCore-0.8-main/vcore/features/modules/combat/Criticals.java`
  - `AnotherProject/WorkSpace/VCore-0.8-main/VCore-0.8-main/vcore/features/modules/combat/Aura.java`
- API checked with the local 26.1.2 mapped jar:
  - `Entity.setPose(Pose)`
  - `Entity.refreshDimensions()`
  - `Entity.getDimensions(Pose)`
  - `Pose.FALL_FLYING`

### Root cause
- The previous follow-up damage formula still used the local Minecraft/enchant/critical prediction stack:
  `ATTACK_DAMAGE` attributes, sharpness fallback, enchant scaling, `wouldDoCriticalHit(...)`, and
  `canDoCriticalHit(...)`. That disagreed with VCore's simpler FakePlayer path, especially for crits.
- VCore FakePlayer only uses full fake melee damage when Aura attack cooldown is at least `0.85`; otherwise it applies
  `1.0F`.
- VCore `InventoryUtility.getHitDamage(...)` uses fixed weapon damage values:
  sword `7.0`, axe `9.0`, default `1.0`; then crit if `player.fallDistance > 0` or Criticals is enabled; then
  Strength bonus `3 * level`; then armor/toughness reduction.
- ElytraMode visually called `startFallFlying()`, but did not set `Pose.FALL_FLYING` or refresh entity dimensions.
  On 26.1.2, `startFallFlying()` alone can leave the fake player's pose/dimensions as standing, so targeting hitbox
  tests still see an upright player box.

### Changes
- `ModuleFakePlayer.kt`
  - Replaced `calculateAttackDamage(...)` with VCore-style local damage:
    - attack cooldown `< 0.85` -> direct `1.0F`;
    - sword -> `7.0F`;
    - axe -> `9.0F`;
    - default -> `1.0F`;
    - crit if `player.fallDistance > 0.0F || ModuleCriticals.running`;
    - crit adds 50%;
    - Strength adds `3.0F * (amplifier + 1)`;
    - armor/toughness reduction uses `CombatRules.getDamageAfterAbsorb(...)`.
  - Removed the overcomplicated FakePlayer melee estimate path:
    - no local `ATTACK_DAMAGE`/stack modifier max;
    - no sharpness fallback;
    - no enchant-damage scaling;
    - no `wouldDoCriticalHit(...)` / `canDoCriticalHit(...)` critical detector.
  - Melee feedback now always plays the player hurt sound at the fake player's position when damage is applied.
    Crits play `PLAYER_ATTACK_CRIT` and spawn crit particles using the VCore crit condition.
  - Existing hit-count behavior stayed intact:
    - attack packet is still cancelled for the client-only fake entity id;
    - local swing is still forced before cancel;
    - melee still calls `applyEstimatedDamage(..., preservePreviousInvulnerability = true)`;
    - explosion damage still keeps its own `invulnerableTime` guard.
- ElytraMode:
  - Added `ensureElytraPose(clone)`.
  - Setup and every ElytraMode tick now:
    - call `startFallFlying()` if the fake is no longer fall-flying;
    - force `Pose.FALL_FLYING` if the pose was reset;
    - compare current `clone.dimensions` with `clone.getDimensions(Pose.FALL_FLYING)`;
    - call `refreshDimensions()` only when pose/dimensions need updating.
  - No mixin was added. The fix stays scoped to the FakePlayer clone.

### Verification
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.
- Recent source mtime check showed only:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/misc/fakeplayer/ModuleFakePlayer.kt`
- The shell checkout is not discoverable as a Git repo here, so `git diff/status` could not be used.

### FCL test next
- Spawn FakePlayer and wait for full attack cooldown.
- Hit with hand, sword, and axe. Expected: hand is low, sword is VCore-style stronger, axe is strongest.
- Jump/fall hit or enable Criticals. Expected: crit damage is visibly higher than normal full-cooldown damage.
- Apply Strength. Expected: fake melee damage increases by the VCore-style `3 * level` bonus.
- Spam low-cooldown hits. Expected: each real attack still processes, but fast hits use the low `1.0F` path.
- Test AutoTotem/pop/explosion once. Expected: TotemPopEvent/PopChams/counters remain stable.
- Enable `ElytraMode`. Expected: FakePlayer keeps `Pose.FALL_FLYING`, refreshes to the fall-flying dimensions, and no
  longer has a standing upright hitbox while visually flying.

## Celestial EntityESP mode port + visual polish (2026-07-01, Codex)

User requested a focused port of Celestial Premium 1.12.2 `EntityESP` visual behavior into the current ESP system.
Scope stayed limited to the new ESP mode plus the tiny vanilla-name suppression hook needed by that mode.

### Source checked
- Current target:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/esp/ModuleESP.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/esp/modes/EspMode.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/esp/modes/Esp2DMode.kt`
  - `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/render/MixinEntityRenderer.java`
- Current helpers:
  - `WorldToScreen.kt`
  - `ExosGuiRenderer.kt`
  - `ModuleNameTags.kt`
  - `ModuleItemTags.kt`
  - `GuiAndItemCompatExtensions.kt`
  - current item/entity extension helpers
- Read-only reference:
  - `AnotherProject/WorkSpace/hacked-client-collection/Celestial Premium 6.0 (1.12.2)/org/celestial/client/feature/impl/visuals/EntityESP.java`

### Initial port
- Added new mode:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/esp/modes/EspCelestialMode.kt`
- Registered `EspCelestialMode` in `ModuleESP.modes`.
- Added mode-level vanilla nametag cancellation:
  - `EspMode.shouldCancelVanillaNametag(entity)` default false;
  - `ModuleESP.shouldCancelVanillaNametag(entity)` delegates only when ESP is running;
  - `MixinEntityRenderer` now cancels names for NameTags OR active ESP mode tag rendering.
- Implemented Celestial-style core features:
  - `BorderMode`: `Box` / `Corner`;
  - `BorderRect`;
  - `RenderTags` + `TagsBackground`;
  - `RenderArmor`;
  - `HealthBar`, `HealthText`, `HealthColorMode`;
  - `FriendHighlight`;
  - `MobESP`;
  - `ItemESP`, `ItemBoxes`, `ItemTags`, `ItemHighlight`, `ItemHighlightColor`;
  - local theme/custom/rainbow color modes for this mode.
- Did not port old raw GL features:
  - no `GLU.gluProject`;
  - no `GL11` wireframe/glow;
  - no raw 1.12 Forge/MCP classes.

### Projection / corner / equipment fixes
- First follow-up fixed three runtime visual issues:
  - far `Corner` ESP no longer collapses into a tiny X/O/3x3 shape;
  - Box/Corner projection no longer drops the ESP as aggressively near screen edges;
  - equipment column now scales with projected box height instead of fixed `0.65` scale / `12px` step.
- `projectBox(...)` now:
  - projects all AABB corners plus center/top/bottom fallback points;
  - accepts a wider valid depth window for current `WorldToScreen` output;
  - clamps bounds near screen edges with a small margin;
  - rejects only clearly unusable/offscreen/sanity-failing bounds.
- Corner lengths now clamp to the actual projected box size:
  - no fixed minimum can exceed the box;
  - tiny boxes are inflated to a small readable minimum;
  - if still too small, Corner falls back to Box for that entity.
- Equipment layout now:
  - uses available box height;
  - calculates icon size/gap from item count;
  - uses larger near/mid icons up to vanilla 16px;
  - shrinks or skips cleanly when the projected box is too small;
  - flips to the left side if right side would go off-screen.

### Final polish
- Celestial border lines were made thinner/cleaner:
  - colored line constant reduced to `0.75f`;
  - black backing reduced from `3px` to `2px`;
  - segment draw helpers now emit crisp integer-pixel lines instead of rounding subpixel fills into overly thick strokes;
  - health bar width reduced slightly to `1.5f`.
- Self ESP was removed:
  - local `mc.player` is skipped unconditionally in `shouldRenderLiving`, including third person.
- Pose/bounds fix:
  - `screenBox(...)` now uses the entity's actual current `boundingBox`, offset by interpolated render position.
  - This preserves current pose dimensions for standing, crouching, swimming, and `Pose.FALL_FLYING`/Elytra instead of rebuilding a standing-style box from dimensions.

### Verification
- Build after initial port:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
  - Result: **BUILD SUCCESSFUL**.
- Build after projection/corner/equipment fix:
  - Result: **BUILD SUCCESSFUL**.
- Build after final line/pose/self polish:
  - Result: **BUILD SUCCESSFUL**.
- Jar verified contains updated:
  - `EspCelestialMode.class`
  - `ModuleESP.class`
  - `EspMode.class`
  - `MixinEntityRenderer.class`
- Targeted grep found no old `GL11`, `GLU`, MCP entity names, `AxisAlignedBB`, `ScaledResolution`, or `org.celestial`
  references in the touched ESP files.

### FCL test next
- Enable `ESP`, set mode to `Celestial`.
- Test `BorderMode=Box` and `BorderMode=Corner` at close/mid/far distances.
  - Expected: thin black-backed colored lines; no over-thick premium-border look.
  - Expected: Corner mode does not collapse into X/O/3x3 at distance.
- Turn camera near screen edges while entity remains visible.
  - Expected: Box/Corner stay visible without flicker or giant offscreen boxes.
- Test standing, crouching, swimming, and Elytra/fall-flying players or FakePlayer ElytraMode.
  - Expected: projected bounds follow actual pose/hitbox; Elytra is not drawn as a tall standing box.
- Confirm local player/self has no Celestial ESP in first or third person.
- Confirm equipment column remains aligned to the box height and does not overlap or grow weirdly with distance.

## FragEffects player death trigger + SmoothCamera reference re-port (2026-07-02, Codex)

User explicitly scoped this pass to:
- `FragEffects` / old `KillAnimation` visual module.
- `SmoothCamera`.
- Do **not** touch ClickGUI, HUD, ESP, combat modules, movement modules, KillAura logic, Elytra modules, or unrelated render modules.

### Source checked
- Current target files:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleFragEffects.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleSmoothCamera.kt`
  - `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/render/MixinCamera.java`
- Current event/remove plumbing:
  - `AttackEntityEvent`
  - `GameTickEvent`
  - `WorldEntityRemoveEvent`
  - `WorldChangeEvent`
  - `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/client/MixinClientWorld.java`
- Read-only reference:
  - `AnotherProject/WorkSpace/LiquidBounce/src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleSmoothCamera.kt`
  - `AnotherProject/WorkSpace/LiquidBounce/src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/render/MixinCamera.java`

### FragEffects root cause
- `ModuleFragEffects` remembered attacked/aimed `LivingEntity` instances but only triggered when the live reference later had `health == 0.0f`.
- That worked for mobs but was too strict for players because player death/removal can happen before the client observes a clean zero-health state.
- The module had `WorldEntityRemoveEvent` available already, fired from `ClientLevel.removeEntity` before `onClientRemoval()`, but FragEffects did not consume it.

### FragEffects changes
- Added a `WorldEntityRemoveEvent` handler in `ModuleFragEffects`.
- The handler only triggers for a currently remembered entity; it does not react to random unrelated removals.
- `EntityDeathMemory` now stores:
  - entity id;
  - uuid;
  - last known body/flick position;
  - last known particle spawn position;
  - whether the remembered target was a player;
  - the last live entity reference when still valid;
  - a `hasTriggered` duplicate guard.
- Replaced the strict trigger check with `isDeathLikeState(entity)`:
  - `health <= 0.0f`;
  - `isDeadOrDying`;
  - `!isAlive`;
  - `isRemoved`.
- Particle spawning and camera flick can now use the saved snapshot position if the entity has already been removed.
- `NoMobsDetect` behavior kept stable but made explicit internally:
  - players are always valid remembered targets;
  - mobs are remembered only when `NoMobsDetect=false`.
- World change and disable now clear memories, particles, deceased snapshots, timer/desaturate/strike state.
- Visual behavior was not rewritten: strike animation, timer slowdown, desaturation/vignette, sound, camera flick, particle bursts, and world-render particles remain the same effect pipeline.

### SmoothCamera root cause
- The local SmoothCamera had drifted from LiquidBounce reference behavior:
  - missing `EnableFirstPOV`;
  - custom `applySmoothPosition` gate;
  - first-person/reset cases avoided writing the current camera position through the mixin;
  - missing the reference SmoothCamera `Vec3.add(...)` redirect in `MixinCamera` used by the vehicle/cart camera path.

### SmoothCamera changes
- `ModuleSmoothCamera.kt` was re-aligned with LiquidBounce reference behavior while keeping this fork's `Category.RENDER` module API:
  - added `EnableFirstPOV=false`;
  - kept `ResetOnPerspectiveChange=true`;
  - kept `HorizontalFactor=0.9`;
  - kept `VerticalFactor=0.93`;
  - `smoothPos: Vec3` + `lastPerspective`;
  - removed custom `applySmoothPosition`;
  - `shouldApplyChanges()` now returns `running`, matching the reference.
- Camera update behavior:
  - disabled module updates `lastPerspective` and does not smooth;
  - perspective change with reset enabled sets `smoothPos` to the current camera position and returns;
  - first person with `EnableFirstPOV=false` sets `smoothPos` to current camera position and returns;
  - otherwise initializes zero `smoothPos` from current position and interpolates X/Z with horizontal factor, Y with vertical factor.
- Added a `WorldChangeEvent` reset and kept disable reset so stale camera positions do not survive world changes.
- `MixinCamera.java`:
  - kept FreeCam, FreeLook, and RotationManager `CHANGE_LOOK` hooks intact;
  - added the SmoothCamera-specific `Vec3.add(Vec3)` redirect from the LiquidBounce reference;
  - existing `ModifyArgs` still captures the original `setPosition(DDD)` args, calls `ModuleSmoothCamera.cameraUpdate(original)`, and writes `smoothPos.x/y/z` only when `shouldApplyChanges()` is true.

### Verification
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result after FragEffects/SmoothCamera edits: **BUILD SUCCESSFUL**.
- A second build was run after removing a stale SmoothCamera comment:
  - Result: **BUILD SUCCESSFUL**.

### FCL test next
- FragEffects:
  - Kill a mob with FragEffects enabled; expected: existing mob effect still triggers once.
  - Manually attack/kill a player; expected: player death or matched removal triggers FragEffects once.
  - KillAura target player death; expected: remembered KillAura target triggers once.
  - Aim/crosshair target player death; expected: remembered target triggers once.
  - Watch for duplicate strike/particle spam after health update + removal in the same death.
- SmoothCamera:
  - Test third person walking up/down blocks, jumping/falling, flying/elytra, and vehicles.
  - Toggle first person with `EnableFirstPOV=false`; expected: no smoothing, no stale snap.
  - Enable `EnableFirstPOV=true`; expected: first-person smoothing applies.
  - Switch camera perspective with `ResetOnPerspectiveChange=true`; expected: clean reset without stale interpolation.
  - Regression-check FreeCam, FreeLook, and RotationManager `CHANGE_LOOK` camera orientation behavior.

## HUD shared material / surface / animation refactor (2026-07-03, Codex)

User requested a significant visual-quality pass for the **current HUD only**:
- improve depth/glass/shadow/gradient/animation/material consistency;
- do not touch ClickGUI;
- do not touch combat/movement/player/world or unrelated render modules;
- do not rewrite the HUD as one monolithic class;
- do not bring back removed ArrayList/StaffList.

### Source checked
- Current HUD:
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleHUD.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/HudElement.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/HudManager.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/HudScreen.kt`
  - `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/gui/hud/WyvernHudElements.kt`
- Render primitives:
  - `ExosGuiRenderer.kt`
  - `RoundedRectGui.kt`
  - `RoundedTextureGui.kt`
  - `GlowRectGui.kt`
  - `CustomFontGui.kt`
  - `BlurEffectRenderer.kt`
  - `MixinInGameHud.java`

### Findings
- Active HUD elements are the Wyvern set from `HudManager`: `Watermark`, `Potions`, `Keybinds`, `TargetHUD`, `Notify`, and optional `Inventory`, plus HUD2 variants. `ArrayList` and `Staff` classes still exist but remain unregistered/invisible.
- `HudDraw.blurPanel(...)` was intentionally empty, so existing panel backgrounds were just ad hoc black rounded rectangles.
- The old `BlurEffectRenderer.startOverlayDrawing/endOverlayDrawing` path is not the live overlay path. `MixinInGameHud` fires `OverlayRenderEvent` directly from `Gui.extractRenderState`.
- Current-version safe blur is vanilla GUI stratum blur (`context.blurBeforeThisStratum(); context.nextStratum()`), already proven in `ClickGuiScreen`, not old raw framebuffer/GL blur.

### New shared HUD architecture
- Added `HudMaterial.kt`:
  - `HudMaterial`
  - `HudMaterialPreset`: `Flat`, `Glass`, `Depth`, `Minimal`
  - `HudDensity`: `Compact`, `Normal`
  - `HudMaterials.preset(...)`
- Added `HudSurfaceRenderer.kt`:
  - `HudBlurRenderer.beginFrame(context)` requests vanilla GUI blur once per HUD frame when enabled.
  - `HudSurfaceRenderer.drawPanel(...)` draws shared surface layers:
    soft shadow, dark translucent gradient, subtle accent glow, top highlight, low-alpha border.
  - `HudSurfaceRenderer.drawHeaderPanel(...)` draws a shared panel plus a subtle header tint.
  - `HudSurfaceRenderer.drawAccentStrip(...)` draws the small material-consistent accent line.
- Added `HudAnimations.kt`:
  - `TweenFloat`
  - `SpringFloat`
  - `AnimatedBounds`
  - `HudEasing`

### ModuleHUD settings
- Added global HUD visual settings:
  - `Material` default `Glass`
  - `Density` default `Normal`
  - `Blur` default `true`
  - `BlurStrength` default `0.75`
  - `BackgroundAlpha` default `1.0`
  - `PanelRadius` default `6`
  - `Shadow` default `true`
  - `ShadowStrength` default `0.72`
  - `Outline` default `true`
  - `OutlineAlpha` default `0.78`
  - `HUDScale` default `1.0`, range `0.70..1.20`
- `ModuleHud.currentMaterial` derives the selected preset plus user radius/blur strength.
- `ModuleHud.renderHandler` now calls `HudBlurRenderer.beginFrame(event.context)` once before rendering HUD elements and then renders each element through scaled render helpers.

### HUD element migration
- `HudElement.kt`
  - Added `renderScaled(...)` / `renderPreviewScaled(...)`.
  - `isMouseOver(...)` now accounts for global HUD scale.
  - Stored `x/y` positions remain logical GUI pixels; scale is visual-only.
- `HudScreen.kt`
  - Editor renders scaled elements.
  - Drag offset/clamp logic accounts for `HUDScale` without saving scaled positions.
- `ModuleHUD.kt`
  - Chat drag path also accounts for `HUDScale`.

### Wyvern HUD visual changes
- `HudDraw.drawHeaderPanel(...)` now delegates to `HudSurfaceRenderer.drawHeaderPanel(...)`.
  This migrates Potions and Hotkeys/Keybinds to the shared material without changing row logic.
- Watermark:
  - Classic and compact modes now use `HudSurfaceRenderer.drawPanel(...)`.
  - Kept identity/layout/icons/text intact.
  - Added subtle accent strip in classic mode.
- TargetHUD:
  - Uses shared surface in both HUD1/HUD2.
  - Added entrance scale/fade (`0.94 -> 1.0`).
  - Health/old-health/absorption now use `SpringFloat`.
  - Added subtle health bar glow.
  - Avatar/head keeps rounded texture path and gets a small hurt pulse/red tint from `hurtTime`.
  - Kept target logic, entity/mob fallback, armor/item behavior.
- Inventory:
  - Uses shared panel surface.
  - Slot cells remain, but colors are toned down so the shared material owns the depth.
- Notifications:
  - Classic and V2 use shared panel surface.
  - Each notification row has a Y spring (`animatedY`) for smoother stacking/enter movement.
  - Existing notification logic, duration, text, icons, Classic/V2 differences kept.
- Potions/Hotkeys:
  - Existing centered header/icon/row logic kept.
  - `Density=Compact` scales header/row text/spacing down modestly.

### Blur note
- HUD blur now uses the safe current-version approximation:
  - one vanilla GUI blur stratum request per HUD frame;
  - HUD panels/content are drawn in the next stratum so text, icons, item stacks, and health bars stay sharp.
- This intentionally does **not** revive the old `BlurEffectRenderer` raw GL/framebuffer overlay path and does not run a per-panel blur pass.
- `HudDraw.blurPanel(...)` remains empty with an updated comment so dormant old elements do not accidentally trigger per-rect blur work.

### Verification
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon assemble -x detekt`
- Result after implementation: **BUILD SUCCESSFUL**.
- Final result after comment cleanup: **BUILD SUCCESSFUL**.
- Jar verified contains:
  - `HudMaterial.class`
  - `HudMaterialPreset.class`
  - `HudMaterials.class`
  - `HudDensity.class`
  - `HudSurfaceRenderer.class`
  - `HudBlurRenderer.class`
  - `TweenFloat.class`
  - `SpringFloat.class`
  - `AnimatedBounds.class`

### FCL test next
- Enable HUD and test `Material=Glass`, `Depth`, `Flat`, `Minimal`.
- Toggle `Blur`, `Shadow`, `Outline`, `HUDScale`, and `Density`.
- Open chat/HUD editor and drag Watermark, Potions, Hotkeys, TargetHUD, Notify, and Inventory.
  Expected: dragging matches scaled visual bounds; saved positions are not scaled.
- TargetHUD:
  - acquire/release KillAura target;
  - expected: soft scale/fade entrance, spring health, subtle health glow, no layout change.
  - damage target; expected: tiny avatar hurt pulse/tint, not exaggerated.
- Notifications:
  - toggle several modules quickly;
  - expected: rows stack smoothly with no overlap/jump.
- Potions/Hotkeys:
  - expected: existing row alignment and icons remain correct; Compact density only tightens spacing.
- Watch mobile FPS with `Blur=true`; if heavy on FCL, keep material on `Depth`/`Glass` but disable `Blur`.

## PopChams VCore core re-port

- Re-ported PopChams core behavior from VCore.
- Preserved ThemeSystem as the RGB source.
- Preserved the existing Iris Shader/Bloom integration.
- Restored dedicated per-pop player models, VCore transform order, frozen pose, render-time Y movement, alpha-based rotation, SecondLayer behavior, no-depth fallback rendering, solid Simple mode, and additive Textured mode.
- New fallback pipelines live in `render/popchams/PopChamsPipelines.kt`.
- Build passed with `assemble -x detekt`.
- FCL result: working.

## HandShader — AlphaDLC port (2026-07-07)

Ported AlphaDLC `HandShader` to Minecraft 26.1.2.

### Implementation

Modes:

- Full
- Partial
- WebShader
- Plasma

Settings and shader equations match AlphaDLC.

HandShader uses a dedicated color setting with default `#9682FF`.
It does not use ThemeSystem.

### Render path

The module hooks around the actual:

`GameRenderer.renderItemInHand(CameraRenderState, float, Matrix4fc)`

Frame flow:

1. Capture main color before hand rendering.
2. Vanilla renders first-person hand/item.
3. Capture color after hand rendering.
4. Sample after-hand color and main depth texture.
5. Run one fullscreen post-process.
6. Copy the result back to the main target.

The original AlphaDLC depth mask is preserved:

```glsl
depth >= 0.001 && depth < 0.9999
No stencil, color-difference, fixed screen region, CPU blur, duplicate hand rendering, or visual fallback was added.
GPU adaptations
Legacy framebuffer API → TextureTarget and current CommandEncoder
Shader JSON → ShaderSource and RenderPipeline
Separate GLSL uniforms → std140 UBO
Fullscreen quad → fullscreen triangle
Pipeline precompiled through MixinShaderManager
Capture targets are reused and resized safely. No per-frame shader compilation or framebuffer allocation.
Important WebShader note
AlphaDLC WebShader is a screen-space procedural web/vein pattern clipped by the hand/item depth mask.
It does not use item UVs, model coordinates, surface normals, or world position.
On thin pixel-extruded items such as a Diamond Sword, it may appear as segmented lines or rectangular sections. Do not rewrite it to imitate Plasma.
The smooth wave effect from the reference video was Plasma, not WebShader.
Files
ModuleHandShader.kt
HandShaderPipeline.kt
resources/liquidbounce/shaders/hand_shader/
MixinGameRenderer.java
MixinShaderManager.java
ModuleManager.kt
Deviations
AlphaDLC color presets were not recreated.
Mode-dependent setting visibility was not recreated.
These are UI differences only.
Verification
git diff --check: passed
assemble -x detekt: passed
WebShader tested on FCL
Remaining full FCL tests: Full, Partial, Plasma, offhand/both hands, map, crossbow, Iris, resize, resource reload
Future-edit rules
Do not add or replace the implementation with:
generic/CPU blur
fallback rendering
Iris emissive paths
shaderpack bloom
ThemeSystem colors
secondary gradients
replacement textures
duplicate hand rendering
For visual bugs, inspect depth mask, fullscreen UVs, output-target coverage, and MobileGlues precision before changing shader equations.
```

## BlockOverlay AlphaDLC shader fidelity correction (2026-07-07, Codex)

### Request

Correct the current AlphaDLC `BlockOverlay` port because the three current fragment shaders had been visually rewritten instead of API-adapted. Do not redesign, do not touch ESP or NameTags, and keep the AlphaDLC reference source read-only.

### Current files checked

- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleBlockOutline.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/alphadlc/AlphaDlcRenderPipelines.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/alphadlc/AlphaDlcWorldRenderer.kt`
- `src/main/resources/resources/liquidbounce/shaders/block_overlay/block_overlay.vsh`
- `src/main/resources/resources/liquidbounce/shaders/block_overlay/block_overlay_energy.fsh`
- `src/main/resources/resources/liquidbounce/shaders/block_overlay/block_overlay_plasma.fsh`
- `src/main/resources/resources/liquidbounce/shaders/block_overlay/block_overlay_honeycomb.fsh`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/render/MixinWorldRenderer.java`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/render/MixinShaderManager.java`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/render/MixinGameRenderer.java`

### Reference files checked

- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/features/impl/render/BlockOverlay.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/utils/display/render/geometry/ShaderBox3D.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/utils/display/render/geometry/Render3D.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/mixins/GameRendererMixin.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/events/render/WorldRenderEvent.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/resources/assets/mre/shaders/core/block_overlay_shader.vsh`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/resources/assets/mre/shaders/core/block_overlay_shader.fsh`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/resources/assets/mre/shaders/core/block_plasma.fsh`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/resources/assets/mre/shaders/core/block_hex.fsh`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/resources/assets/mre/shaders/core/block_overlay_shader.json`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/resources/assets/mre/shaders/core/block_plasma.json`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/resources/assets/mre/shaders/core/block_hex.json`

### Root cause / source behavior

The existing `block_overlay_energy/plasma/honeycomb.fsh` files contained invented shader math: custom hash constants, FBM transforms, ridged logic, plasma bands, extra color palettes, honeycomb grid scaling, and alpha multiplied by `vertexColor.a`.

AlphaDLC fragment shaders use `vertexColor.rgb` as tint and compute final alpha only from `Opacity` and the source fill value. They do not multiply output alpha by vertex color alpha.

### Changed

Replaced only the fragment shader equations in:

- `block_overlay_energy.fsh`
- `block_overlay_plasma.fsh`
- `block_overlay_honeycomb.fsh`

The current pipeline/UBO wrapper remains as the API adaptation:

- `#version 150` -> `#version 330`
- `uniform float Time/Opacity` -> `layout(std140) uniform BlockOverlayConfig { vec4 Params; }`
- `#define Time Params.x`
- `#define Opacity Params.y`

No module settings, smoothing, target validation, VoxelShape handling, `0.0025` expansion, depth/cull/blend setup, shader fallback, vanilla outline suppression, ESP, or NameTags code was changed.

### Geometry / matrix audit

`appendShaderBox` face order matches AlphaDLC `ShaderBox3D.addBox` for bottom, top, north, south, west, and east.

UV mapping matches AlphaDLC:

- plane 0: `u = x * 4`, `v = z * 4`
- plane 1: `u = x * 4`, `v = y * 4`
- plane 2: `u = z * 4`, `v = y * 4`

Current vertices should remain camera-relative. AlphaDLC's `GameRendererMixin` appends `translate(-camera)` into the event matrix. This repo's `WorldRenderEvent` matrix is the raw `modelViewMatrix`, so subtracting camera from vertex positions is the equivalent current-version adaptation. UVs still use full world coordinates before camera subtraction.

### Deviations

None for shader math. The only syntax/API adaptations are GLSL version and current UBO access.

### Verification

- Forbidden-value search found no matches for:
  `456.21`, `45.32`, `mat2(1.62`, `7.31`, `p1`, `p2`, `bands`, `uv * 3.2`, `vec3(1.0, 0.45, 0.95)`, `vec3(1.0, 0.72, 0.18)`, `vertexColor.a`.
- Normalized shader comparison, ignoring only GLSL version, UBO declaration/access, output variable name, and whitespace:
  - Energy: match
  - Plasma: match
  - Honeycomb: match
- `git diff --check`: passed.
- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon clean assemble -x detekt`
- Result: **BUILD SUCCESSFUL**.

### Runtime status

Current status: shader mathematics and geometry mapping are source-faithful; full BlockOverlay runtime fidelity is not yet confirmed until FCL visual testing passes.

FCL visual proof is still pending. Plasma must be compared against AlphaDLC after literal shader restoration. Also test Energy, Honeycomb, opacity behavior, outline overlay, camera movement around the selected block, and shader-to-Normal fallback.

## KillAura V-Core Track/Grim + Focused Full architectural re-port (2026-07-11, Codex)

### Request

Replace the simplified Track/Grim AngleSmooth mappings with complete V-Core 0.8 Aura rotation strategies, and restrict MovementCorrection `Focused Full` to KillAura-owned rotation requests.

### Current files checked

KillAura targeting/click/rotation paths, RotationManager/RotationTarget, movement input/velocity/jump/glide hooks, PointTracker/raytracing, AutoCart, ElytraTarget, Style 4 ClickGUI, Value/Configurable, CombatManager, and the mapped 26.1.2 Minecraft jar.

### Reference files checked

V-Core 0.8 `MoveFix.java`, `Aura.java`, `AuraRotationManager.java`, `Track.java`, `Grim.java`, `PlayerManager.java`, `WallsBypass.java`, `EMaceHelper.java`, `ElytraTarget.java`, `PlayerUtility.java`, and `MathUtility.java`.

### Root cause / source behavior

The previous port reduced Track and Grim to generic AngleSmooth processors after PointTracker. V-Core instead owns aim-point selection, yaw/pitch state, target-locked block/entity raytracing, readiness, and mode-specific GCD quantization. Generic normalization and `towardsLinear` are not equivalent.

### Changed

- Added `VCoreAuraRotationController` with Track dynamic-point state, exact point scans, Track/Grim formulas, target-locked `checkRtx`, ElytraTarget integration, and controller-owned `lookingAtHitbox`.
- Added the V-Core-only AimRange, IgnoreWalls, RayTrace, WallsBypass, and Track settings.
- Track/Grim remain serialized AngleSmooth choices but are marker containers; KillAura bypasses PointTracker, timing, Fail, ShortStop, and generic GCD for them.
- Added UI-only `Value.visibleWhen` support and Style 4 dynamic subtree filtering.
- Focused Full now requires a KillAura-owned rotation request for the current target and gates input, velocity, jump, and glide correction against riding, AutoCart, combat/rotation pause, removed targets, and invalid rotations.

### Deviations

PeakAssist has no active current-project equivalent. The controller contains an isolated identity hook and does not invent PeakAssist state.

### Verification

`assemble -x detekt` and `git diff --check` passed. Compilation verifies mapping/integration only.

### Runtime status

FCL runtime comparison is pending for Track/Grim motion, raytrace/wall bypass behavior, Focused Full movement, Elytra movement, and live ClickGUI visibility.

### Supersedes / reverts

Supersedes the 2026-05-29 journal entry that mapped V-Core Track/Grim onto generic AngleSmooth processors.

## SwingAnimation New HMI-derived dual-hand first-person state (2026-07-14, Codex)

### Request

Enhance Wraith's existing `SwingAnimation` module `New` mode with Hold My Items 5.1.1-inspired first-person behavior for both `InteractionHand.MAIN_HAND` and `InteractionHand.OFF_HAND`, without importing HMI as a dependency, adding a Lua framework, or replacing Wraith's animation system.

### Current files checked

- `CHANGELOG.md`
- `Skill/README.md`
- `Skill/Agen.md`
- `Skill/CLAUDE_PORT_SKID_MODULE_GUIDE.md`
- `Skill/PORT_EVIDENCE_TEMPLATE.md`
- `Skill/PORT_REVIEW_CHECKLIST.md`
- `Skill/RENDER_PORTING_GUIDE.md`
- `Skill/CODEBASE_RULES.md`
- `Skill/AGENT1_USAGE.md`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleAnimations.kt`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/item/MixinHeldItemRenderer.java`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/entity/MixinLivingEntityCompatibility.java`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/client/MixinMinecraftClient.java`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/network/MixinClientPlayerInteractionManager.java`
- `src/main/resources/liquidbounce.mixins.json`
- Current mapped Minecraft 26.1.2 sources for `ItemInHandRenderer`, `LivingEntity`, `Minecraft`, `ItemUseAnimation`, `ItemStack`, and `DataComponents`.

Bounded searches covered `ModuleAnimations`, `applySwingTransform`, `shouldRenderNewSpecial`, `applyNewSpecialArmPose`, `applyNewSpecialItemPose`, `visualSwingDuration`, `transformEquipProgress`, `resolveMainArm`, `renderArmWithItem`, `renderPlayerArm`, `swingArm`, `getCurrentSwingDuration`, `InteractionHand.OFF_HAND`, `swingingArm`, `swing(`, `swingOffHand`, `getAttackAnim`, `getSwingProgress`, `ItemInHandRenderer`, current hand-selection/render-selection logic, current item-use and attack hooks, and world-change/disconnect/module-disable reset patterns.

### HMI reference checked

Reference JAR: `WorkSpace/Mods/HMI 5.1.1 (1.21.11).jar`.

Decompiled and inspected:

- `com.holdmylua.source.mixin.player.LivingEntityMixin`
- `com.holdmylua.source.access.LivingEntityAccessor`
- `com.holdmylua.source.mixin.render.HeldItemRendererMixin`
- `com.holdmylua.source.mixin.render.HandRenderTypeMixin`
- `com.holdmylua.source.mixin.client.MinecraftClientMixin`
- `com.holdmylua.source.mixin.client.GameRendererMixin`
- `com.holdmylua.source.mixin.property.UseDurationMixin`
- `com.holdmylua.source.mixin.property.UsingItemMixin`
- `com.holdmylua.source.global.LuaContext`
- `com.holdmylua.source.global.GlobalsStorage`

Bundled scripts inspected:

- `resourcepacks/pack_test/assets/minecraft/holdmyitems/hand_pose.lua`
- `resourcepacks/pack_test/assets/minecraft/holdmyitems/item_pose.lua`
- `resourcepacks/pack_test/assets/minecraft/holdmyitems/hand_relative_pose.lua`

### Root cause / source behavior

Wraith's current `New` mode had partial offhand rendering through `NewLeftHand`, but the animation state was shared. `visualSwingDuration`, `transformEquipProgress`, and normal `applySwingTransform` were main-hand biased, the slash transform was gated to main hand in the special path, and the `newPrimarySlash/newWasSwinging` pair made slash alternation global instead of per logical hand.

HMI maintains independent main/offhand swing ticks, previous/current swing progress, swinging flags, interaction flags, swing event flags, and item-switch events. Its renderer chooses swing progress and switch events by rendered logical hand. HMI also globally forces both hand render flags, but that was not ported because Wraith must keep vanilla/tightly gated hand selection and the current 26.1.2 renderer already renders both hands for normal non-two-handed states.

### Changed

- Replaced the shared `New` slash state with `EnumMap<InteractionHand, NewHandState>`.
- Added per-hand swing ticks, previous/current swing progress, independent primary slash alternation, event flags, pending interaction flags, and item-switch progress/state.
- Added module enable/disable, game tick, world-change, disconnect, player replacement, mode/aura gating, and non-finite-value resets.
- Added local attack/use trigger hooks in `MixinMinecraftClient` and a local-player `LivingEntity.swing(hand, boolean)` observer in `MixinLivingEntityCompatibility`.
- Updated `MixinHeldItemRenderer` calls so `New` mode receives tick delta and the rendered logical hand for per-hand interpolation.
- Extended `visualSwingDuration`, `transformEquipProgress`, `applySwingTransform`, and `applyNewSpecialArmPose` to use hand-specific `New` state.
- Removed the main-hand-only slash restriction after making slash state per hand.
- Added independent item-switch/equip transition detection using copied stacks and visual component comparisons for item, item model, custom model data, map id, and charged projectiles.
- Added `New` use-action handling for eat, drink, toot horn, block/shield, brush, trident, and spear use where current mappings expose stable state.
- Preserved Wraith's existing `New` item category poses for swords, axes, shovels, generic tools, torch-like items, block-like items, and generic items while feeding them HMI-derived per-hand progress.
- Left `liquidbounce.mixins.json` unchanged; no new mixin registration was required.

### MainHand improvements

Main hand now uses the same `NewHandState` model as offhand: independent interpolated swing progress, independent slash alternation, local attack/use trigger capture, copied-stack item-switch detection, and smoothed switch progress.

### Offhand implementation

When `SwingAnimation` is running, `Mode == New`, `NewLeftHand == true`, and the local first-person renderer is active, offhand now receives its own visual swing state, slash alternation, item-switch progress, equip/switch transform, use-action transform for supported actions, physical-arm mirroring through `newSpecialArm`, and copied-stack item identity tracking. Offhand no longer derives progress or slash direction from main hand.

### Supported actions

Implemented or preserved in `New` path:

- idle/non-use held item
- empty hand
- generic swing
- sword-like item
- axe-like item
- shovel-like item
- generic digging tool
- generic item
- eat
- drink
- toot horn
- block/shield
- spear/trident-like use
- brush
- throwable item switch accent
- generic block and torch-like item poses

Vanilla fallback remains for map, bow, crossbow, spyglass/scoping, and any active use animation not explicitly supported by the constrained Wraith `New` path.

### Deviations and exclusions

- This is a constrained first-person adaptation of HMI behavior into Wraith's existing `New` mode, not a full Hold My Items port.
- Did not port LuaJ, resource-pack script execution, HMI item model/property framework, particles, camera animation, sound, third-person animation, HUD/debug UI, block renderer changes, fishing/sign changes, or Iris compatibility code unrelated to first-person hands.
- Did not globally force both hand render flags like HMI `HandRenderTypeMixin`; Wraith keeps vanilla selection except existing `NewLeftHand` special rendering where the current renderer already calls the offhand path.
- Bow, crossbow, map, and spyglass/scoping stay vanilla to avoid duplicated two-handed items or broken scoping without HMI's wider render/model framework.
- HMI `UseDurationMixin` and `UsingItemMixin` were inspected but not ported because they feed HMI's Lua/item-model system rather than required Wraith first-person hand pose state.

### Verification

- `git diff --check`: passed.
- Final build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon clean assemble -x detekt`
- Final build result: **BUILD SUCCESSFUL**.
- JAR path: `build/libs/liquidbounce-0.31.2.jar`
- Confirmed no reference archive, decompiled output, cache, or HMI extraction was staged or edited.
- Confirmed no duplicate mixin registration was added.
- Confirmed no additional packets or gameplay interactions were introduced.

### Runtime status

Runtime status: not tested in FCL/in-game. Build success only proves compilation and packaging.

FCL checklist to run:

- Module disabled; another mode; `New` with `NewLeftHand=false`; `New` with `NewLeftHand=true`.
- `OnlyAura=false`; `OnlyAura=true` with and without a current target.
- Right-handed player, `RenderHand=RIGHT`, `RenderHand=LEFT`.
- Main item only, offhand item only, both hands holding items, empty hand cases.
- Main item switch, offhand item switch, and near-simultaneous switches.
- Main attack, main use, offhand use, rapid alternating main/offhand interactions, and block breaking.
- Eat, drink, shield/block, bow, crossbow, trident/spear, brush, map, generic block, torch-like item, generic tool, and empty hand.
- Check for duplicated arm/item, missing offhand, cross-hand progress bleed, slash alternation bleed, per-frame equip restarts, progress 0/1 teleporting, wrong physical-arm mirroring, camera clipping, duplicated map/bow/crossbow, stale pose after disable/world change, scoping regression, and first-person Iris/shader regression.

### Files changed

- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleAnimations.kt`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/item/MixinHeldItemRenderer.java`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/entity/MixinLivingEntityCompatibility.java`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/client/MixinMinecraftClient.java`
- `Agent1.md`

### Commit / push

Commit hash: pending at entry time; final hash reported after commit creation.

Branch: `main`

Remote: `origin https://github.com/Duong1234vi0/Wraith-0.1.git`

Push status: pending at entry time.

### Supersedes / reverts

Supersedes any older Hold My Item / Hold My Items / `New` mode attempt that used shared `newPrimarySlash/newWasSwinging` state or derived offhand animation from main-hand progress. No specific older HMI entry was found in `Agent1.md`.

## Removed VCore Elytra modules and Track/Grim rotations (2026-07-15, Codex)

### Request

Completely remove the previously ported VCore Elytra combat features and VCore-derived Track/Grim KillAura rotation modes from Wraith. Do not replace them with new modules, modes, settings, or compatibility behavior.

### Current files inspected

- `Skill/README.md`
- `Skill/Agen.md`
- `Skill/CODEBASE_RULES.md`
- `Skill/AGENT1_USAGE.md`
- `Agent1.md` sections for ElytraTarget, ElytraMotion, ElytraRange, ElytraAimRange, firework rotation integration, Track, Grim, `VCoreAuraRotationController`, and `VCoreRotationResult`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleElytraTarget.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/ModuleElytraMotion.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/ModuleKillAura.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/KillAuraClicker.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/KillAuraRotationsConfigurable.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/VCoreAuraRotationController.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/VCoreAuraRotationsConfigurable.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/RotationsConfigurable.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/features/processors/anglesmooth/impl/TrackAngleSmooth.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/features/processors/anglesmooth/impl/GrimAngleSmooth.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/features/processors/anglesmooth/impl/HumanizedTrackAngleSmooth.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/config/ConfigSystem.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/config/types/nesting/ChoiceConfigurable.kt`
- `src/main/resources/resources/liquidbounce/lang/en_us.json`

### Deleted

- Deleted `ModuleElytraTarget.kt`.
- Deleted `ModuleElytraMotion.kt`.
- Deleted `TrackAngleSmooth.kt`.
- Deleted `GrimAngleSmooth.kt`.
- Deleted `HumanizedTrackAngleSmooth.kt` because it was a direct Track-derived rotation choice and exposed `Track` in ClickGUI.
- Deleted the already-unreferenced VCore rotation bridge files:
  - `VCoreAuraRotationController.kt`
  - `VCoreAuraRotationsConfigurable.kt`
- Removed `ModuleElytraTarget` and `ModuleElytraMotion` from `ModuleManager.registerInbuilt()`.
- Removed KillAura `ElytraRange` and `ElytraAimRange`.
- Removed KillAura Elytra-specific attack/scan range, predicted target-point, gliding target sort, special facing, and firework packet-rotation accessor behavior.
- Removed Track/Grim/HumanizedTrack choice registration and VCore-only rotation helpers from `RotationsConfigurable`.
- Removed English module descriptions for ElytraTarget and ElytraMotion.

### Preserved

- Ordinary KillAura `Range`, `WallRange`, `ScanExtraRange`, target tracker, point tracker, raycast, target sorting, facing check, and rotation target flow remain.
- Gliding players remain ordinary valid entities under standard KillAura targeting rules.
- Remaining rotation modes remain registered in their prior relative order: Linear, Sigmoid, Interpolation, Acceleration, and Minarai for combat-specific configs.
- Focused Full movement correction and `visibleWhen` remain because they are used by live non-Elytra code.
- Shared firework, inventory, packet, OffHandSlot, SilentHotbar, and NetworkUtils helpers remain because other live modules use them.
- Unrelated Grim anti-cheat modes/checks remain, including NoSlow Grim, Speed Grim, Fly Grim, Criticals Grim, ServerObserver text, and Grim-related documentation/usernames.
- No attached ZIP, JAR, decompiled cache, or VCore reference source was edited.

### Config compatibility

- Old configs with removed scalar values such as `ElytraRange` and `ElytraAimRange` are ignored by the ordinary config loader because it only deserializes values that still exist in the live configurable tree.
- Old configs selecting removed `Track`, `Grim`, or `HumanizedTrack` AngleSmooth choices log an active-choice deserialization error and keep the current default choice, because `ConfigSystem.deserializeValue` catches `ChoiceConfigurable.setByString(...)` failures.
- No explicit migration was added and no replacement mode was introduced.

### Files changed / deleted

- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleElytraTarget.kt` (deleted)
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/ModuleElytraMotion.kt` (deleted)
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/KillAuraClicker.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/KillAuraRotationsConfigurable.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/ModuleKillAura.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/VCoreAuraRotationController.kt` (deleted)
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/VCoreAuraRotationsConfigurable.kt` (deleted)
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/RotationsConfigurable.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/features/processors/anglesmooth/impl/TrackAngleSmooth.kt` (deleted)
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/features/processors/anglesmooth/impl/GrimAngleSmooth.kt` (deleted)
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/features/processors/anglesmooth/impl/HumanizedTrackAngleSmooth.kt` (deleted)
- `src/main/resources/resources/liquidbounce/lang/en_us.json`
- `Agent1.md`

### Static verification

- `git diff --check`: passed before and after cleanup.
- Required symbol search under `src/main/kotlin`, `src/main/java`, and `src/main/resources` found no remaining:
  - `ElytraTarget`
  - `ElytraMotion`
  - `ElytraRange`
  - `ElytraAimRange`
  - `getPredictedVec`
  - `canAttackElytraTarget`
  - `squaredDistanceToPredicted`
  - `isInAimRange`
  - `activeAttackRange`
  - `activeScanRange`
  - `shouldBlockAuraAttack`
  - `useItemRotationOverride`
  - `rotationForPacketUse`
  - `VCoreAuraRotationController`
  - `VCoreRotationResult`
  - `TrackAngleSmooth`
  - `GrimAngleSmooth`
  - `HumanizedTrackAngleSmooth`
- Remaining `Track`/`Grim` matches are unrelated input-tracking comments, ServerObserver text, usernames, Grim anti-cheat mode names, and Grim movement/combat module modes.

### Build and JAR inspection

- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt`
- Build result: **BUILD SUCCESSFUL** in 2m 3s.
- JAR path: `build/libs/liquidbounce-0.31.2.jar`
- JAR inspection found zero class/resource matches for:
  - `ModuleElytraTarget`
  - `ModuleElytraMotion`
  - `TrackAngleSmooth`
  - `GrimAngleSmooth`
  - `HumanizedTrackAngleSmooth`
  - `VCoreAuraRotationController`
  - `VCoreRotationResult`
  - `VCoreAuraRotationsConfigurable`
- JAR still contains `ModuleKillAura`, `KillAuraRotationsConfigurable`, and `RotationsConfigurable`.

### Runtime status

Runtime status: not tested in FCL/in-game. Build success only proves compilation and packaging.

FCL checklist:

- Start with a clean/default config.
- Start with an old config that previously selected Track.
- Start with an old config that previously selected Grim.
- Start with an old config containing ElytraRange/ElytraAimRange.
- Open ClickGUI and confirm ElytraTarget, ElytraMotion, Track, Grim, HumanizedTrack, and Elytra KillAura settings are absent.
- Enable KillAura while grounded.
- Enable KillAura while gliding.
- Attack a gliding player under ordinary KillAura rules.
- Switch through every remaining rotation mode.
- Use fireworks normally outside ElytraMotion.
- Confirm no startup crash from unknown config choices.
- Confirm no missing-choice exception.
- Confirm no stale module state or translation key appears.

### Commit / push

Cleanup commit hash: `d24a1eb873daa89b6679baf9379c34958f6e2bf6`

Branch: `main`

Remote: `origin https://github.com/Duong1234vi0/Wraith-0.1.git`

Push status: pushed to `origin/main` successfully (`29e800b96..d24a1eb87`).

### Supersedes / reverts

Supersedes and removes the 2026-06-30 `VCore ElytraTarget + ElytraMotion port`, all 2026-06-30 to 2026-07-01 ElytraTarget/ElytraMotion correction plans, and the 2026-07-11 `KillAura V-Core Track/Grim + Focused Full architectural re-port` portions that introduced Track/Grim/VCore rotation adapters. Focused Full itself remains because it is shared by live non-Elytra code.

## HitEffect Xyeta batching/cache and render-parity fix (2026-07-15, Codex)

### Request

Fix Wraith's existing Xyeta/WildClient `HitEffect` port because FCL/MobileGlues FPS drops when the module renders up to 400 affected blocks per wave. Preserve the current Xyeta wave timing, expansion, fade, local alpha, scan order, block cap, attack-trigger behavior, and multiple simultaneous waves. Replace only the per-block immediate render submission path with a batched/cached renderer modeled on Xyeta `RenderUtil3D`.

### Current Wraith files checked

- `Skill/README.md`
- `Skill/Agen.md`
- `Skill/CLAUDE_PORT_SKID_MODULE_GUIDE.md`
- `Skill/PORT_EVIDENCE_TEMPLATE.md`
- `Skill/PORT_REVIEW_CHECKLIST.md`
- `Skill/RENDER_PORTING_GUIDE.md`
- `Skill/CODEBASE_RULES.md`
- `Skill/AGENT1_USAGE.md`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleHitEffect.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/alphadlc/AlphaDlcWorldRenderer.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/alphadlc/AlphaDlcRenderPipelines.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/render/compat/RenderCompat.kt`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/render/MixinGameRenderer.java`
- `src/main/kotlin/net/ccbluex/liquidbounce/event/events/DrawEvents.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/block/BlockCompatExtensions.kt`
- `net/minecraft/world/level/Level.java`
- `src/main/resources/resources/liquidbounce/lang/en_us.json`
- Current mapped 26.1.2 classes inspected with `javap`: `BlendFunction`, `SourceFactor`, `DestFactor`, `ColorTargetState`, `RenderPass`, `BufferBuilder`, `Tesselator`, `ByteBufferBuilder`, and `BlockPos.MutableBlockPos`.

### Xyeta files checked

- `WorkSpace/Client/Xyeta/src/main/java/dev/client/modules/impl/render/HitEffect.java`
- `WorkSpace/Client/Xyeta/src/main/java/dev/client/util/render/RenderUtil3D.java`
- `WorkSpace/Client/Xyeta/src/main/java/dev/client/mixins/hooks/MixinGameRenderer.java`
- `WorkSpace/Client/Xyeta/src/main/java/dev/client/event/classes/WorldRenderEvent.java`
- `WorkSpace/Client/Xyeta/src/main/java/dev/client/managers/EventManager.java`
- `WorkSpace/Client/Xyeta/src/main/java/dev/client/mixins/hooks/ClientPlayerInteractionManagerMixin.java`
- `WorkSpace/Client/Xyeta/src/main/java/dev/client/mixins/other/IWorldRendererMixin.java`
- `WorkSpace/Client/Xyeta/src/main/java/dev/client/util/color/ColorUtil.java`

### Root cause

`ModuleHitEffect.renderWave()` called `AlphaDlcWorldRenderer.drawShapeAlternative(...)` once for every rendered affected block. That helper immediately built and submitted a fill mesh and then immediately built and submitted an outline mesh. With Xyeta's 400-block cap, the old Wraith path could reach about 400 fill submissions + 400 outline submissions per wave per frame. Several overlapping waves multiplied that cost and caused FCL/MobileGlues frame spikes.

### Xyeta source behavior

- `HitEffect` queues waves on attack at the attacked entity block position.
- `WaveEffect.render()` scans `x/z` from `-8..8`, caps at 400 rendered blocks, searches downward before upward for 10 blocks, and calls `RenderUtil3D.drawShapeAlternative(renderPos, shape, color, lineWidth, true, true)`.
- `RenderUtil3D.drawShapeAlternative` does not submit immediately. It caches `VoxelShape` local bounding boxes and local edge lines, translates them by the block position, queues fill quads in `QUAD_DEPTH`, and queues lines in `LINE_DEPTH`.
- `RenderUtil3D.onRender3D()` flushes queued geometry once per frame using batched `BufferBuilder`s. It uses `RenderSystem.blendFunc(SRC_ALPHA, ONE_MINUS_CONSTANT_ALPHA)`, disables cull, enables depth for depth queues, sets `depthMask(false)` for depth lines, and clears the frame queues after drawing.
- Xyeta buckets line drawing by exact line width because old GL line width was global state.

### Changed

- Added `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/hiteffect/HitEffectBatchRenderer.kt`.
- `ModuleHitEffect` now begins one HitEffect frame batch per `WorldRenderEvent`, appends all active-wave block shapes, flushes one fill mesh and one outline mesh, and clears builders in `finally`.
- Removed the per-block immediate `AlphaDlcWorldRenderer.drawShapeAlternative(...)` path from HitEffect.
- Removed per-block `runCatching`; the module now catches only `Exception` once around the frame batch, logs the first batch failure, and does not catch `Throwable`.
- Added full block-read validation through `Level.isInWorldBounds(pos)` plus `level.worldBorder.isWithinBounds(pos)` before `getBlockState`.
- Added `liquidbounce.module.hitEffect.description` to `en_us.json`.

### Renderer architecture

- HitEffect keeps the source wave/scanning/alpha logic.
- `HitEffectBatchRenderer.beginFrame(camera)` resets frame builders and stores the current camera position.
- Each accepted block calls `appendShape(origin, shape, color, lineWidth)`.
- `appendShape` frustum-culls the unit block through `AlphaProjection.canSee`, looks up cached local geometry, appends fill quads with per-vertex color and source `0.1 * alpha` fill opacity, and appends outline lines with per-vertex color and exact per-block line width.
- `flush(modelViewMatrix)` builds at most one fill `MeshData` and one line `MeshData`, uploads them through renderer-owned streaming buffers, opens at most one fill render pass and one line render pass, then clears the frame builders.
- The renderer is HitEffect-only and does not change generic `AlphaDlcWorldRenderer`, `AlphaDlcRenderPipelines`, or `RenderCompat` behavior.

### Geometry cache design

- Cache key: deterministic local geometry signature from all local bounding boxes and all local edges, encoded as normalized `Double.doubleToLongBits` values.
- Cache value: local-space boxes and local-space edges only.
- Fast path: a `WeakHashMap<VoxelShape, CachedShape>` avoids re-decoding stable shape instances.
- Bounded fallback: a 256-entry access-order signature LRU deduplicates equivalent shape geometry if shape instances churn.
- The cache stores no world positions, colors, alpha, camera-relative positions, matrices, block states, levels, or uploaded GPU buffers.
- The geometry is independent of world lifecycle and may safely survive world changes; frame-local builders are still cleared after every render event and exception.

### Fill batching

- Fill boxes use the same `0.001` expansion and six-face solid box topology as Xyeta `drawBox`.
- Fill color preserves per-block RGB and uses `color.a * 0.1`.
- Fill pipeline uses `POSITION_COLOR` quads, cull off, depth test enabled, and Xyeta's blend factors.
- Fill is uploaded/submitted once per frame when any fill vertices exist.

### Outline batching and line width

- Outline edges come from `VoxelShape.forAllEdges`, matching Xyeta.
- Wraith's current `POSITION_COLOR_NORMAL_LINE_WIDTH` vertex format supports `setLineWidth(width)` per vertex, so exact source widths (`1 + localAlpha * 2.5`) are preserved inside one line mesh.
- This intentionally avoids Xyeta's old GL width-bucket submissions while preserving the same width values.
- Outline pipeline uses the current `LINES_SNIPPET`, cull off, depth test enabled, depth write disabled, and Xyeta's blend factors.

### Blend behavior

- Added a HitEffect-only blend function: `BlendFunction(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_CONSTANT_ALPHA)`.
- The current 26.1.2 API exposes both factors directly.
- `RenderPass` exposes no blend-constant setter, but Xyeta did not set a blend constant before `blendFunc(SRC_ALPHA, ONE_MINUS_CONSTANT_ALPHA)` either.
- No additive bloom, shaderpack brightness, texture, or generic AlphaDLC pipeline change was added.

### Preserved wave behavior

- Duration remains `2000 ms`.
- Maximum radius remains `8`.
- Ring thickness remains `0.8`.
- Maximum rendered blocks per wave/frame remains `400`.
- Vertical search distance remains `10`.
- Search order remains downward before upward.
- Fill and outline remain enabled.
- Depth remains enabled.
- Wave center remains the attacked entity block position.
- Multiple waves can exist simultaneously.
- Expansion remains `radius = (1 - (1 - progress)^3) * 8`.
- Fade-in remains `fadeInProgress^2 * (3 - 2 * fadeInProgress)`.
- Fade-out remains `(1 - progress)^4.8`.
- Local alpha, cutoff, color alpha, and line-width formulas are unchanged.
- Attack event adaptation remains current Wraith behavior: no wave when the attack event is cancelled.

### Files changed

- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleHitEffect.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/hiteffect/HitEffectBatchRenderer.kt`
- `src/main/resources/resources/liquidbounce/lang/en_us.json`
- `Agent1.md`

### Static verification

- `git diff --check`: passed.
- HitEffect source search found no remaining `drawShapeAlternative`, `AlphaDlcWorldRenderer`, `runCatching`, or `Throwable` references in `ModuleHitEffect.kt` or `HitEffectBatchRenderer.kt`.
- `MAX_BLOCKS_PER_WAVE_FRAME`, `MAX_RADIUS`, `RING_THICKNESS`, `DURATION_MS`, `SEARCH_HEIGHT`, expansion/fade formulas, alpha cutoff, alpha scale, and line-width formula were preserved.
- New renderer issues at most one fill draw and one outline draw per frame when both batches are non-empty.
- No Xyeta ZIP, extracted reference source, or other reference folder was edited.

### Build / JAR inspection

- Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt`
- Java used: `/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk`; default shell `java -version` also reported OpenJDK 21.0.11, so the build command explicitly pinned Java 25.
- Result: **BUILD SUCCESSFUL** in 2m 11s.
- JAR path: `build/libs/liquidbounce-0.31.2.jar`
- JAR inspection confirmed:
  - `ModuleHitEffect.class` present.
  - `features/module/modules/render/hiteffect/HitEffectBatchRenderer*.class` present.
  - `resources/liquidbounce/lang/en_us.json` present with `liquidbounce.module.hitEffect.description`.

### Runtime / performance status

Runtime status: not tested in FCL/in-game. Build success only proves compilation and packaging.

Expected submission change:

- Previous path: up to about 400 fill + 400 outline submissions per wave per frame.
- New path: at most 1 fill + 1 outline submission per `WorldRenderEvent` frame for all active HitEffect waves sharing the same compatible render state.

FCL checklist:

- Manual attack and repeated manual attacks.
- KillAura repeated attacks.
- One active wave and several overlapping waves.
- Full blocks, slabs, stairs, fences, and other irregular voxel shapes.
- Air above terrain, terrain above/below the attacked entity, and positions near min/max build height and world border.
- Confirm timing, radius, ring thickness, downward-before-upward search, fill, outline, depth behavior, outline width, and no stale wave after world change.
- Compare FPS/frame-time before and after under the same graphics settings, area, attack frequency, active-wave count, and shader state.

### Deviations

- Xyeta groups line submits by exact width because old GL line width is global. Wraith uses the current 26.1.2 line vertex format's per-vertex `setLineWidth(width)`, preserving exact source widths in one outline batch.
- Wraith keeps its current cancelled-attack guard; Xyeta fires its attack event at `HEAD` without cancellation handling in `HitEffect`, but the user explicitly requested preserving Wraith's current cancelled-attack behavior.

### Commit / push

Implementation commit hash: `f6574dbd867e6092406200ca254e0dd9fedca073`

Branch: `main`

Remote: `origin https://github.com/Duong1234vi0/Wraith-0.1.git`

Push status: implementation commit pushed to `origin/main` successfully (`5cbfcae69..f6574dbd8`).

### Supersedes / reverts

Supersedes the incomplete current Wraith HitEffect port path that preserved Xyeta wave behavior but rendered each affected block through immediate AlphaDLC mesh submission instead of Xyeta-style frame batching/cache.

## Split CritSound from ClientSound into HitSound (2026-07-15, Codex)

### Request

Separate the critical-hit sound behavior from `ClientSound`.

- `ClientSound` must own only module toggle sounds: Wav mode, Button mode, toggle-sound volume, `ModuleClickGui` exclusion, loading/in-game gates, and `ClientModule` integration.
- Remove the nested `CritSound` configurable/mode, its attack listener, critical-hit settings, `WITHER_AMBIENT` playback, and debug logging from `ClientSound`.
- Port the supplied standalone `WorkSpace/Mods/ModuleClientSound.kt` behavior into a new standalone Combat module named `HitSound`.
- Do not edit the supplied source file or `src.zip`.

### Current files inspected

- `Skill/README.md`
- `Skill/Agen.md`
- `Skill/CLAUDE_PORT_SKID_MODULE_GUIDE.md`
- `Skill/PORT_EVIDENCE_TEMPLATE.md`
- `Skill/PORT_REVIEW_CHECKLIST.md`
- `Skill/CODEBASE_RULES.md`
- `Skill/AGENT1_USAGE.md`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/misc/ModuleClientSound.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/ClientModule.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/event/events/EntityEvents.kt`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/network/MixinClientPlayerInteractionManager.java`
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/combat/CombatExtensions.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/entity/MojangCompatExtensions.kt`
- `net/minecraft/world/level/Level.java`
- `net/minecraft/client/multiplayer/ClientLevel.java`
- `src/main/kotlin/net/ccbluex/liquidbounce/config/ConfigSystem.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/config/AutoConfig.kt`
- `src/main/resources/resources/liquidbounce/lang/en_us.json`

### Supplied reference inspected

- `WorkSpace/Mods/ModuleClientSound.kt`

Reference behavior:

- Object name in source: `ModuleClientSound`
- Source category: combat
- Settings:
  - `Volume`: default `1.0f`, range `0.1f..2.0f`
  - `Pitch`: default `1.0f`, range `0.5f..2.0f`
- Event: `AttackEntityEvent`
- Target gate: target must be `LivingEntity` and `isAlive`
- Critical condition: `player.fallDistance > 0.0f && !player.onGround()`
- Playback: `SoundEvents.WITHER_AMBIENT`, `SoundSource.PLAYERS`, local player coordinates, configured volume/pitch.

### Agent1 history checked

Searched `Agent1.md` for:

- `ClientSound`
- `CritSound`
- `WITHER_AMBIENT`
- `critical-hit sound`
- `critical hit sound`
- `Klack`
- `no-sound`
- `no sound`
- `playLocalSound`
- `ModuleClientSound`

No matching historical entries were found. Current live source and the supplied reference controlled the implementation.

### Old ClientSound structure

Current `ModuleClientSound.kt` contained:

- `SoundMode` choices with `Wav` and `Button`.
- Global `Volume` for module toggle sounds.
- `playToggleSound(sourceModule, enabled)` called from `ClientModule`.
- Nested `private object CritSound : ToggleableConfigurable(ModuleClientSound, "CritSound", false)`.
- Nested CritSound settings `Volume` and `Pitch`.
- Nested `AttackEntityEvent` handler at `READ_FINAL_STATE`.
- Critical check using fall distance and on-ground state.
- `playWitherCritSound()` using `level.playLocalSound(..., SoundEvents.WITHER_AMBIENT, SoundSource.PLAYERS, ...)`.
- One-time success/failure debug logging flags.

### CritSound code removed

Removed from `ModuleClientSound.kt`:

- `tree(CritSound)` registration.
- Nested `CritSound` configurable.
- CritSound `Volume` and `Pitch`.
- CritSound `AttackEntityEvent` handler.
- `LivingEntity` attack-target checks.
- Critical-state calculation.
- `playWitherCritSound()`.
- `successLogged` / `failureLogged`.
- CritSound-specific logging.
- CritSound-specific imports, including `ToggleableConfigurable`, `AttackEntityEvent`, `handler`, `READ_FINAL_STATE`, `SoundSource`, and `LivingEntity`.

Preserved in `ModuleClientSound.kt`:

- `SoundMode`
- `Wav`
- `Button`
- global toggle-sound `Volume`
- `playToggleSound(...)`
- `ModuleClickGui` exclusion
- loading/in-game checks
- existing native sound manager playback for toggle sounds
- `ClientModule.kt` call to `ModuleClientSound.playToggleSound(this, new)`

### New HitSound module

Added `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleHitSound.kt`:

```kotlin
object ModuleHitSound : ClientModule(
    "HitSound",
    Category.COMBAT
)
```

Settings:

- `Volume`: default `1.0f`, range `0.1f..2.0f`, current Wraith float step `0.05f`
- `Pitch`: default `1.0f`, range `0.5f..2.0f`, current Wraith float step `0.05f`

Handler:

- `AttackEntityEvent` at `READ_FINAL_STATE`
- skips cancelled attacks
- gets `mc.player` and `mc.level`
- requires attacked entity to be `LivingEntity`
- requires target to still be alive
- uses exact reference critical condition:
  `player.fallDistance > 0.0f && !player.onGround()`
- plays `SoundEvents.WITHER_AMBIENT`
- uses `SoundSource.PLAYERS`
- uses exact configured volume and pitch

No modes, sound choices, cooldowns, random pitch, target filters, KillAura-only logic, damage confirmation, custom assets, fallback sounds, or debug logging were added.

### Event API adaptation

Current Wraith `AttackEntityEvent` is cancellable:

- dispatched from `MixinClientPlayerInteractionManager.hookAttack(...)` at `MultiPlayerGameMode.attack` HEAD;
- dispatched from `utils/combat/CombatExtensions.kt` before the client attack packet path;
- existing final-state readers such as `ModuleHitEffect` and `ModuleParticles` use `READ_FINAL_STATE` and check `event.isCancelled`.

Adaptation:

- HitSound uses `READ_FINAL_STATE`.
- HitSound returns without playing when `event.isCancelled`.

Reason:

- The supplied reference has no cancellation check, but current Wraith attack events may be cancelled before execution. Reading final state preserves Wraith event semantics and prevents a sound for an attack that will not execute.

### Sound API adaptation

The supplied reference uses:

```kotlin
world.playSound(player, player.x, player.y, player.z, SoundEvents.WITHER_AMBIENT, SoundSource.PLAYERS, volume, pitch)
```

Current 26.1.2 source inspection:

- `ClientLevel.playSeededSound(except, x, y, z, ...)` plays locally when `except == minecraft.player`.
- `ClientLevel.playLocalSound(...)` reaches the same client sound manager path.
- The previous nested Wraith CritSound used `playLocalSound(...)`.

Implementation:

- HitSound uses `level.playSound(player, player.x, player.y, player.z, SoundEvents.WITHER_AMBIENT, SoundSource.PLAYERS, volume, pitch)`.

Reason:

- This preserves the supplied reference call shape and remains local/client-side in the current `ClientLevel` implementation. It sends no packets and does not simulate a server sound.

### Config compatibility

Old configs may contain:

- `ClientSound.CritSound`
- `ClientSound.CritSound.Volume`
- `ClientSound.CritSound.Pitch`

Current config loader evidence:

- `ConfigSystem.deserializeConfigurable(...)` builds a map of serialized values but, for ordinary configurables, iterates only the current `configurable.inner` values.
- Values that no longer exist in the current configurable are skipped.

Decision:

- No migration code was added.
- Old nested CritSound config values are removed from the live configurable surface and ignored by the loader.
- New config path is the normal module path for `HitSound`.

### Files changed

Implementation commit:

- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/misc/ModuleClientSound.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleHitSound.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt`
- `src/main/resources/resources/liquidbounce/lang/en_us.json`

This Agent1 entry was added after the implementation commit so it could record the actual implementation commit hash and push status.

### Registration and localization

- Registered `ModuleHitSound` once in `ModuleManager.registerInbuilt()` in the Combat section, after `ModuleCriticals`.
- Kept `ModuleClientSound` registered under Misc.
- `ClientModule.kt` still calls `ModuleClientSound.playToggleSound(this, new)`.
- Added fallback localization:
  `liquidbounce.module.hitSound.description`: `Plays a configurable sound when you perform a critical attack.`
- Existing `liquidbounce.module.clientSound.description` was unchanged because it already described only module enable/disable sounds.
- No CritSound-specific localization key existed in live resources.
- Only `en_us.json` was required for fallback description coverage; newer custom module descriptions in this repo are also added there only.

### Static verification

- `git diff --check`: passed.
- JSON parse for `src/main/resources/resources/liquidbounce/lang/en_us.json`: passed.
- Symbol search after implementation:
  - no live `CritSound` configurable remained;
  - no `playWitherCritSound`;
  - no CritSound `successLogged`;
  - no CritSound `failureLogged`;
  - `WITHER_AMBIENT` referenced by `ModuleHitSound`;
  - `ModuleHitSound` registered exactly once;
  - `ClientModule.kt` still calls `ModuleClientSound.playToggleSound(this, new)`.
- Broad `failureLogged` search only found unrelated TargetESP pipeline state.

### Build / JAR inspection

Direct requested wrapper command:

```sh
env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin ./gradlew --no-daemon --no-build-cache clean assemble -x detekt
```

Result:

- Failed before Gradle execution because `./gradlew` is not executable in this checkout: `env: './gradlew': Permission denied`.

Canonical Termux equivalent used:

```sh
env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt
```

Java:

- Default shell `java -version`: OpenJDK `21.0.11`
- Build command pinned Java: OpenJDK `25.0.3`

Result:

- **BUILD SUCCESSFUL** in `1m 58s`
- JAR path: `build/libs/liquidbounce-0.31.2.jar`

JAR inspection:

- `net/ccbluex/liquidbounce/features/module/modules/combat/ModuleHitSound.class` present.
- `net/ccbluex/liquidbounce/features/module/modules/misc/ModuleClientSound.class` present.
- `ModuleClientSound$Wav.class` present.
- `ModuleClientSound$Button.class` present.
- No `ModuleClientSound$CritSound.class` present.
- `resources/liquidbounce/lang/en_us.json` present with `liquidbounce.module.hitSound.description`.
- `javap` of `ModuleManager` showed both `ModuleHitSound.INSTANCE` and `ModuleClientSound.INSTANCE`.
- `javap` of `ModuleHitSound` showed `AttackEntityEvent`, cancelled check, `LivingEntity`, `isAlive`, fall distance/on-ground critical condition, `SoundEvents.WITHER_AMBIENT`, `SoundSource.PLAYERS`, and `ClientLevel.playSound(...)`.
- `javap` of `ModuleClientSound` showed `playToggleSound(...)` and no `AttackEntityEvent`, `WITHER_AMBIENT`, `playWitherCritSound`, or `CritSound`.

### Runtime status

Runtime status: not tested in FCL/in-game. Build success only proves compilation and packaging.

FCL checklist:

ClientSound:

- ClientSound disabled: no module toggle sounds.
- ClientSound enabled with Wav: enable/disable sounds still work.
- ClientSound enabled with Button: enable/disable sounds still work.
- ClientSound GUI contains no CritSound section.
- Attacking entities with only ClientSound enabled does not play `WITHER_AMBIENT`.

HitSound:

- HitSound disabled: no critical sound.
- HitSound enabled.
- Attack non-living entity: no sound.
- Attack dead `LivingEntity`: no sound.
- Attack while on ground: no sound.
- Attack while `fallDistance == 0`: no sound.
- Attack while airborne with positive fall distance: `WITHER_AMBIENT` plays.
- Cancelled attack event: no sound.
- Manual attack.
- KillAura-triggered attack.
- Volume minimum/default/maximum.
- Pitch minimum/default/maximum.
- Repeated critical attacks.
- Module disable/enable.
- World change and reconnect.

Confirm:

- only one sound plays per qualifying attack event;
- no duplicate sound from old ClientSound handler;
- the local user can hear it;
- no sound is broadcast or duplicated through a server path;
- no logs spam on every attack.

### Commit / push

Implementation commit hash: `c052d711e0bb844ae1aed04eabf3ddd0c0defd27`

Branch: `main`

Remote: `origin https://github.com/Duong1234vi0/Wraith-0.1.git`

Push status: implementation commit pushed to `origin/main` successfully (`16ee336d0..c052d711e`).

### Supersedes / reverts

Supersedes the live nested `ClientSound.CritSound` implementation. No earlier `Agent1.md` ClientSound/CritSound/HitSound historical entry was found to supersede.

## HitSound FCL installation/runtime audit (2026-07-15, Codex)

### User-reported symptom

Wraith has a standalone Combat module named `HitSound`. Expected behavior is: on an attack against a living and alive entity, while `player.fallDistance > 0.0f && !player.onGround()`, play `SoundEvents.WITHER_AMBIENT` on `SoundSource.PLAYERS`. User is using `Criticals` mode `Jump`, but no Wither sound is audible when the critical attack occurs.

Scope for this pass: read-only diagnosis of the actual FCL installation first. No Wraith source code, build output, runtime config, sound settings, JARs, or FCL files were modified during the investigation. Only this `Agent1.md` entry was appended afterward.

### Requested FCL path

Requested version directory:

```text
/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/
```

Top-level contents observed included:

- `.fabric/`
- `26.1.2-Fabric.jar` (`38113927` bytes, modified `2026-06-20 16:15:11 +0700`)
- `26.1.2-Fabric.json` (`124215` bytes, modified `2026-06-20 16:15:37 +0700`)
- `LiquidBounce/`
- `config/`
- `crash-reports/`
- `debug/`
- `downloads/`
- `fclversion.cfg`
- `log4j2.xml`
- `logs/`
- `mods/`
- `options.txt`
- `resourcepacks/`
- `saves/`
- `screenshots/`
- `shaderpacks/`

No symlinks were observed in the shallow version-directory inspection.

### Actual game/isolation directory

Actual active game directory:

```text
/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric
```

Evidence:

- `fclversion.cfg` contains `"isolateGameDir": true`.
- `/storage/emulated/0/FCL/log/latest_game.log` reports `Working directory: /storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric`.
- The launch arguments in the same log include `--gameDir /storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric`.

Conclusion: version isolation is enabled and this profile uses the version-local game directory, not a global `.minecraft` game directory.

### Active mods directory

Actual active mods directory:

```text
/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods
```

How determined:

- The active game directory is the isolated version directory.
- That directory contains `mods/`.
- `/storage/emulated/0/FCL/.minecraft/mods` does not exist.
- `/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/.minecraft/mods` does not exist.
- Fabric loader in `latest.log` reports `Loading 118 mods`, and the active mods listed match the version-local `mods/` inventory.

### Installed JAR inventory

JARs in the active mods directory:

```text
/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/fabric-api-0.151.0+26.1.2.jar
size=2498064
sha256=8dae604caafc4179617ee87f73bfb4a7885f7db21ed752b375c64e41924641d6

/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/sodium-fabric-0.8.12+mc26.1.2.jar
size=1844226
sha256=e084c5c3c520dc0718dbaef1deafef0ba2c7c0d77c701ac34c07646f6ccb81df

/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/iris-fabric-1.10.9+mc26.1.1.jar
size=2803860
sha256=aea0eefb174d19ad97a812b6de2e304f0d3abc50357b316908cf1035f1514b07

/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/fabric-language-kotlin-1.13.12+kotlin.2.4.0.jar
size=8076848
sha256=36c5dd8b728e3470d2882ae63119b93a20500fc0ea6f5c945c12bf65b5ab1832

/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar
size=18901064
modified=2026-07-15 13:20:43 +0700
sha256=5018f90255d998f264fa1520a9d043c4ee0360402bd68fa4404f9ec96d7ef48d

/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/appleskin-fabric-mc26.1.2-3.0.10.jar
size=181584
sha256=6a03ad4fcf0a2c831ebabd56fc1b5aca2463e35f289a927adfdbaaf0e944d41c

/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/sodium-extra-fabric-0.8.7+mc26.1.1.jar
size=401602
sha256=ba7787879e9154a1d8aa2b9c1e64b718816bd133f80c11242f424d566815211c

/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/reeses-sodium-options-fabric-2.1.0+mc26.1.2.jar
size=139606
sha256=c4d90f58363239b33d75ae654bc1a286b4513e6328be3de05e33b3dec3e915b9

/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/ViaFabricPlus-4.5.5.jar
size=27037935
sha256=47318151754b535bb2defac802c3436c7cc61525a22f06bad85241b48febd237
```

### Wraith/LiquidBounce candidate JAR

Candidate:

```text
/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar
```

Evidence:

- `fabric.mod.json`: id `liquidbounce`, name `LiquidBounce`, version `0.31.2`.
- Contains `net/ccbluex/liquidbounce/features/module/modules/combat/ModuleHitSound.class`: yes.
- Contains `net/ccbluex/liquidbounce/features/module/modules/misc/ModuleClientSound.class`: yes.
- Contains `net/ccbluex/liquidbounce/features/module/ModuleManager.class`: yes.
- Contains obsolete `net/ccbluex/liquidbounce/features/module/modules/misc/ModuleClientSound$CritSound.class`: no.
- Contains HitSound localization in `resources/liquidbounce/lang/en_us.json`: yes, including `liquidbounce.module.hitSound.description`.

Conclusion: the active mods directory contains the current/new HitSound build. It is not missing `ModuleHitSound` bytecode and it does not still contain the obsolete nested `ClientSound.CritSound` class.

### Repository build comparison

Repository build inspected:

```text
build/libs/liquidbounce-0.31.2.jar
size=18901064
modified=2026-07-15 13:14:14 +0700
sha256=5018f90255d998f264fa1520a9d043c4ee0360402bd68fa4404f9ec96d7ef48d
```

Installed FCL JAR:

```text
/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar
size=18901064
modified=2026-07-15 13:20:43 +0700
sha256=5018f90255d998f264fa1520a9d043c4ee0360402bd68fa4404f9ec96d7ef48d
```

Checksum match: yes.

Both JARs contain `ModuleHitSound.class`, contain `ModuleClientSound.class`, contain HitSound localization, and do not contain `ModuleClientSound$CritSound.class`.

Conclusion: this is not an outdated installed JAR and the built JAR was installed into the active version-local mods directory.

### Version JSON and launch metadata

Version JSON:

```text
/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/26.1.2-Fabric.json
```

Findings:

- `id`: `26.1.2-Fabric`
- `type`: `release`
- `mainClass`: `net.fabricmc.loader.impl.launch.knot.KnotClient`
- no `inheritsFrom` value was present.
- libraries include Fabric Loader `net.fabricmc:fabric-loader:0.18.6`.
- Wraith/LiquidBounce is not referenced directly as a version JSON library/classpath entry.
- The version JAR is referenced as the Minecraft client jar by FCL (`-Dminecraft.client.jar=/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/26.1.2-Fabric.jar`).

Version JAR inspected:

```text
/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/26.1.2-Fabric.jar
size=38113927
sha256=b1b3158572666445eff01e82fad8c7de2e4953db6d354f311730d77a8359d0b0
```

It does not contain `ModuleHitSound.class`, `ModuleClientSound.class`, `ModuleManager.class`, obsolete `ModuleClientSound$CritSound.class`, or `fabric.mod.json`.

Conclusion: Wraith/LiquidBounce is loaded as a normal Fabric mod from the isolated profile's `mods/` directory, not from the main version JAR and not as a direct version JSON library.

### Runtime log findings

Logs inspected:

```text
/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/logs/latest.log
/storage/emulated/0/FCL/log/latest_game.log
```

Relevant evidence:

- `latest.log:1`: `Loading Minecraft 26.1.2 with Fabric Loader 0.18.6`
- `latest.log:3`: `Loading 118 mods`
- `latest.log:70`: `- liquidbounce 0.31.2`
- `latest.log:243`: resource manager includes `liquidbounce`
- `latest.log:244`: `Launching LiquidBounce vunofficial by CCBlueX`
- `latest.log:355`: `LiquidBounce has been successfully initialized.`
- `latest.log:361`: `OpenAL initialized on device OpenSL`
- `latest.log:362`: `Sound engine started`
- `latest_game.log:19`: `liquidbounce-0.31.2 | liquidbounce | 0.31.2 | FABRIC`
- `latest_game.log:104`: active working directory is `/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric`

The log confirms the mod id/version, not a full absolute source JAR path. The exact loaded JAR path is inferred from the active isolated game directory, the active version-local `mods/` directory, and the fact that there is only one LiquidBounce/Wraith JAR in that active mods directory.

No `HitSound`, `ModuleHitSound`, `CritSound`, or `WITHER_AMBIENT` runtime lines were found. That is not proof that the attack handler did or did not run, because this module does not log every attack or every predicate failure.

Relevant errors/warnings seen, none directly tied to HitSound audio playback:

- Mapping load error: `Unable to load mappings`, `Unsupported format`.
- ScriptAPI initialization failure due `ClassNotFoundException: org.graalvm.nativeimage.ImageInfo`.
- ScriptManager cannot load scripts before the script engine initializes.
- Iris/render pipeline missing program warnings.
- One Netty read timeout.

No OpenAL failure was found. Sound engine initialized and restarted successfully.

### Module configuration state

Active module config path:

```text
/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/LiquidBounce/modules.json
```

Evidence:

- `latest.log` reports `Successfully loaded config 'modules'.`
- `latest.log` reports `Successfully saved config 'modules'.`
- The isolated version-local `LiquidBounce/modules.json` timestamp matches the latest run (`2026-07-15 13:23:20 +0700`).
- Global `/storage/emulated/0/FCL/.minecraft/LiquidBounce` does not exist.

Values parsed from active modules config:

```text
HitSound entry exists: yes
HitSound enabled: true
HitSound Volume: 1.0
HitSound Pitch: 1.0

ClientSound entry exists: yes
ClientSound enabled: true
ClientSound SoundMode: Wav
ClientSound Volume: 67

Old ClientSound.CritSound values remain in active modules config: no

Criticals enabled: true
Criticals Mode: Jump
Criticals Jump Height: 0.42
Criticals Jump Range: 6.0
Criticals Jump OptimizeForCooldown: true
Criticals Jump CheckKillaura: true
Criticals Jump CheckAutoClicker: true
Criticals Jump CanBeSeen: true

KillAura enabled: false
KillAura Criticals selection: Smart
```

Conclusion: the missing sound is not explained by HitSound being absent or disabled in the active module config. The old nested `ClientSound.CritSound` state is absent from the active config and does not need to be migrated for this active runtime state.

### Audio option state

Active options file:

```text
/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/options.txt
modified=2026-07-15 13:25:06 +0700
```

Global `/storage/emulated/0/FCL/.minecraft/options.txt` does not exist.

Relevant options:

```text
soundDevice:""
soundCategory_master:1.0
soundCategory_music:0.4941830590653367
soundCategory_record:1.0
soundCategory_weather:1.0
soundCategory_block:1.0
soundCategory_hostile:1.0
soundCategory_neutral:1.0
soundCategory_player:1.0
soundCategory_ambient:1.0
soundCategory_voice:1.0
soundCategory_ui:1.0
```

Conclusion: `SoundSource.PLAYERS` should be audible according to active options. Master volume is `1.0`; Players category volume is `1.0`; Players category is not muted.

Asset sanity:

- `/storage/emulated/0/FCL/.minecraft/assets/indexes/30.json` contains `minecraft/sounds.json`.
- Wither idle sound assets exist for `idle1.ogg`, `idle2.ogg`, `idle3.ogg`, and `idle4.ogg`.
- No missing Wither sound-resource error was found in the runtime logs.

### Duplicates and stale installations

Search under `/storage/emulated/0/FCL/.minecraft` found one LiquidBounce/Wraith candidate:

```text
/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar
sha256=5018f90255d998f264fa1520a9d043c4ee0360402bd68fa4404f9ec96d7ef48d
contains ModuleHitSound.class: yes
contains obsolete ModuleClientSound$CritSound.class: no
```

Other name matches were unrelated libraries or Minecraft/Fabric artifacts, including `httpclient`, `client-intermediary.jar`, and `fabric-client-tags-api`.

Conclusion: no old/new LiquidBounce duplicate JAR risk was found in the searched FCL `.minecraft` tree. The new JAR is not in the wrong folder; it is installed in the active isolated mods directory.

### Source and bytecode comparison

Current repository source:

```text
src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleHitSound.kt
```

Observed behavior:

- Event handler: `AttackEntityEvent`
- Priority: `READ_FINAL_STATE`
- Cancel check: returns when `event.isCancelled`
- Target checks: target must be `LivingEntity` and `target.isAlive`
- Critical predicate: `player.fallDistance > 0.0f && !player.onGround()`
- Sound call: `level.playSound(player, player.x, player.y, player.z, SoundEvents.WITHER_AMBIENT, SoundSource.PLAYERS, volume, pitch)`
- Volume setting: default `1.0f`, range `0.1f..2.0f`, step `0.05f`
- Pitch setting: default `1.0f`, range `0.5f..2.0f`, step `0.05f`

Installed JAR bytecode matches the repository source:

- `ModuleHitSound.class` registers an `AttackEntityEvent` handler at priority `-1000` (`READ_FINAL_STATE`).
- It checks `AttackEntityEvent.isCancelled()`.
- It casts target to `LivingEntity`.
- It checks `LivingEntity.isAlive()`.
- It checks `LocalPlayer.fallDistance > 0.0f` and `!onGround()`.
- It calls `ClientLevel.playSound(Entity, DDD, SoundEvent, SoundSource, FF)` with `SoundEvents.WITHER_AMBIENT` and `SoundSource.PLAYERS`.

Runtime sound API check:

- `Level.playSound(except, x, y, z, ...)` delegates to `playSeededSound(except, ...)`.
- `ClientLevel.playSeededSound(except, x, y, z, ...)` plays locally when `except == this.minecraft.player`.
- Therefore the current `level.playSound(player, ...)` call shape is not, by itself, proven to suppress the local sound in this mapped client.

### Diagnosis table

| Check | Result | Evidence | Likely impact |
|---|---|---|---|
| Active game directory | version-local `26.1.2-Fabric` | FCL log working directory and `--gameDir`; `isolateGameDir=true` | Correct isolated profile inspected |
| Active mods directory | version-local `mods/` | global mods path missing; active game dir has `mods/` | Correct mod folder inspected |
| Installed Wraith JAR path | `/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar` | only LiquidBounce candidate in active mods dir | Correct installed candidate |
| Installed JAR has `ModuleHitSound` | yes | JAR content inspection | HitSound bytecode installed |
| Installed JAR lacks old `CritSound` class | yes | no `ModuleClientSound$CritSound.class` | Not stale nested implementation |
| Installed checksum matches repository build | yes | both SHA-256 `5018f90255d998f264fa1520a9d043c4ee0360402bd68fa4404f9ec96d7ef48d` | Built JAR is installed |
| Fabric log confirms client loaded | yes for mod id/version; exact path inferred | `latest.log` has `liquidbounce 0.31.2`; `latest_game.log` has `liquidbounce-0.31.2 | liquidbounce | 0.31.2 | FABRIC` | Loaded mod matches active candidate by isolation and uniqueness |
| HitSound config entry exists | yes | active `LiquidBounce/modules.json` | Config has new standalone module |
| HitSound enabled | true | active modules config | Not disabled |
| Volume/Pitch | `1.0` / `1.0` | active modules config | Audible module settings |
| Master volume | `1.0` | active `options.txt` | Not muted globally |
| Players volume | `1.0` | `soundCategory_player:1.0` | `SoundSource.PLAYERS` not muted |
| Sound engine | initialized | `OpenAL initialized on device OpenSL`; `Sound engine started` | Audio backend available |
| Runtime errors | none tied to HitSound/sound assets | errors are mappings, ScriptAPI, Iris/render, ping/timeout | No concrete audio-blocking error found |
| Duplicate/old JARs | none found for LiquidBounce/Wraith | duplicate search found only active LiquidBounce candidate | Wrong-JAR/stale-JAR cause unlikely |
| Criticals mode | `Jump`, enabled | active modules config | User's stated mode confirmed |
| KillAura Criticals selection | `Smart`; KillAura disabled | active modules config | Aura path likely inactive unless enabled later |

### Likely root cause

The installation side is clean: current JAR installed, correct isolated mods directory, loaded mod id/version present in logs, HitSound class present, config enabled, volume/pitch audible, Players category not muted, sound engine initialized, and no duplicate stale LiquidBounce JAR was found.

The remaining likely cause is runtime predicate timing: `HitSound` only plays when the `AttackEntityEvent` handler sees `player.fallDistance > 0.0f && !player.onGround()` at `READ_FINAL_STATE`. With `Criticals` mode `Jump`, the user can observe a critical attack while the local player state at the exact attack event may not satisfy this predicate. The module currently logs neither event entry nor predicate values, so the latest logs cannot prove which of `event fired`, `target valid`, `fallDistance`, or `onGround` failed at runtime.

No source-code patch is justified from installation evidence alone. The smallest next step is a runtime probe or debug build that logs one line for `HitSound` attack decisions: event cancelled, target class/alive, `fallDistance`, `onGround`, and whether the sound call is reached. After that, either keep the predicate as-is if it is not reached by design, or adjust the predicate/sound trigger with concrete runtime evidence.

### Recommendation

Do not replace JARs, configs, or audio settings for this issue. The installed FCL profile already has the current HitSound build enabled and audible by settings.

Recommended next action, not performed in this audit:

```text
Add temporary HitSound attack-decision logging, build/install/test once in FCL, then remove or convert to proper debug logging after confirming whether the fallDistance/onGround predicate is false at the attack event.
```

Explicitly not performed:

- no Wraith source patch;
- no rebuild;
- no JAR replacement;
- no runtime config edit;
- no module enable/disable;
- no sound setting edit;
- no runtime file deletion or move.

## HitSound vanilla critical-signal fix (2026-07-15, Codex)

### Request

User confirmed the 2026-07-15 installation/config/audio audit was not enough: `HitSound` was installed, enabled, loaded
from the active isolated FCL JAR, and audio settings were valid, but both Criticals `Jump` and a manually performed
falling critical attack still produced no Wither sound.

Task: stop using `AttackEntityEvent` plus the early `fallDistance > 0.0f && !onGround()` heuristic. Fire a client-only
critical signal from the actual local Minecraft critical branch and make `HitSound` play `WITHER_AMBIENT` from that
signal only.

### Current files checked

- `Skill/README.md`
- `Skill/Agen.md`
- `Skill/CODEBASE_RULES.md`
- `Skill/AGENT1_USAGE.md`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleHitSound.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/event/Event.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/event/EventManager.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/event/EventListener.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/event/events/EntityEvents.kt`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/entity/MixinPlayerEntity.java`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/network/MixinClientPlayerInteractionManager.java`
- `src/main/resources/liquidbounce.mixins.json`
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/combat/CombatExtensions.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/criticals/ModuleCriticals.kt`
- `net/minecraft/world/entity/player/Player.java`
- `net/minecraft/client/player/LocalPlayer.java`
- `net/minecraft/client/multiplayer/MultiPlayerGameMode.java`
- `net/minecraft/client/multiplayer/ClientLevel.java`
- Deobf bytecode jar:
  `.gradle/loom-cache/minecraftMaven/net/minecraft/minecraft-merged-7f56e53844/26.1.2/minecraft-merged-7f56e53844-26.1.2.jar`

### Source / bytecode findings

`MultiPlayerGameMode.attack(Player, Entity)` in 26.1.2 sends `ServerboundAttackPacket`, calls `player.attack(entity)`,
then resets attack strength. The existing `MixinClientPlayerInteractionManager` dispatches cancellable
`AttackEntityEvent` at `MultiPlayerGameMode.attack` HEAD, so a cancelled attack naturally does not reach
`Player.attack`.

Mapped `Player.attack(Entity)` source computes:

```text
fullStrengthAttack = attackStrengthScale > 0.9F
criticalAttack = fullStrengthAttack && this.canCriticalAttack(entity)
```

When damage is accepted, it calls:

```text
attackVisualEffects(entity, criticalAttack, sweepAttack, fullStrengthAttack, false, magicBoost)
```

Mapped `Player.attackVisualEffects(Entity, boolean, boolean, boolean, boolean, float)` contains the confirmed critical
feedback branch:

```text
if (criticalAttack) {
    playServerSideSound(SoundEvents.PLAYER_ATTACK_CRIT)
    crit(entity)
}
```

`javap` verified descriptors:

```text
Player.attack(Entity): (Lnet/minecraft/world/entity/Entity;)V
Player.attackVisualEffects(Entity, boolean, boolean, boolean, boolean, float):
  (Lnet/minecraft/world/entity/Entity;ZZZZF)V
Player.crit(Entity): (Lnet/minecraft/world/entity/Entity;)V
```

`javap -c` verified there is one `crit(Entity)` invocation inside `attackVisualEffects`, immediately after
`PLAYER_ATTACK_CRIT` sound playback, and `magicCrit(Entity)` is a separate branch.

`LocalPlayer.crit(Entity)` only creates the vanilla CRIT tracking emitter. Hooking before `Player.crit(Entity)` gives a
local client branch signal before the vanilla visual feedback call.

### Root cause

The old `ModuleHitSound` decided critical state too early from `AttackEntityEvent` using only:

```text
player.fallDistance > 0.0f && !player.onGround()
```

That did not prove the attack entered the vanilla critical branch. It could miss legitimate cases where local state at
the attack event did not match the later actual branch, and it did not include vanilla's full critical gates or the
damage-accepted path.

The old sound call used `ClientLevel.playSound(player, ...)`. Source inspection showed that overload can route locally
when `except == minecraft.player`, but the implementation was changed to explicit `playLocalSound(...)` anyway because
the user had already proved the previous runtime path was silent.

### Changed

- Added `CriticalAttackEvent(attacker: Player, target: LivingEntity)` in `EntityEvents.kt`.
- Registered `CriticalAttackEvent` in `ALL_EVENT_CLASSES`.
- Added one dispatch site in the existing `MixinPlayerEntity`:
  - method selector:
    `attackVisualEffects(Lnet/minecraft/world/entity/Entity;ZZZZF)V`
  - injection point:
    before `Lnet/minecraft/world/entity/player/Player;crit(Lnet/minecraft/world/entity/Entity;)V`
  - gates:
    local player only (`this == Minecraft.getInstance().player`);
    target must be `LivingEntity`;
    target must be alive.
- Replaced `ModuleHitSound`'s `AttackEntityEvent` handler with a `CriticalAttackEvent` handler.
- Removed the old `fallDistance/onGround` critical predicate from `ModuleHitSound`.
- Replaced `level.playSound(player, ...)` with:

```kotlin
level.playLocalSound(
    player.x,
    player.y,
    player.z,
    SoundEvents.WITHER_AMBIENT,
    SoundSource.PLAYERS,
    volume,
    pitch,
    false
)
```

### Preserved

- `HitSound` remains a standalone Combat module.
- `HitSound` does not depend on `ModuleCriticals`.
- `CriticalsJump` was not edited.
- `ClientSound` remains separate and still owns only module toggle sounds.
- `Volume`: default `1.0f`, range `0.1f..2.0f`, step `0.05f`.
- `Pitch`: default `1.0f`, range `0.5f..2.0f`, step `0.05f`.
- Sound remains `SoundEvents.WITHER_AMBIENT`.
- Sound source remains `SoundSource.PLAYERS`.
- No setting, mode, fallback sound, custom asset, packet, damage, cooldown, or particle behavior was added.

### Static verification

- `git diff --check`: passed.
- Source search confirmed:
  - `ModuleHitSound` no longer imports or handles `AttackEntityEvent`;
  - `ModuleHitSound` no longer contains `fallDistance` or `onGround`;
  - `ModuleHitSound` calls `ClientLevel.playLocalSound(..., false)`;
  - `CriticalAttackEvent` has exactly one dispatch site;
  - dispatch is local-player gated;
  - no direct `ModuleCriticals` dependency was added.
- Build bytecode inspection confirmed:
  - `ModuleHitSound` calls
    `ClientLevel.playLocalSound(DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FFZ)V`;
  - `CriticalAttackEvent` stores `Player attacker` and `LivingEntity target`;
  - `MixinPlayerEntity.hookCriticalAttack(...)` gates local player and live `LivingEntity`, then dispatches the event.
- JAR inspection confirmed the built JAR contains:
  - `CriticalAttackEvent.class`;
  - `ModuleHitSound.class`;
  - `MixinPlayerEntity.class`;
  - no `ModuleClientSound$CritSound.class` match in the checked output.

### Build / install

Java:

```text
openjdk version "25.0.3" 2026-04-21
OpenJDK Runtime Environment (build 25.0.3)
OpenJDK 64-Bit Server VM (build 25.0.3, mixed mode)
```

Build command:

```sh
env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk \
  PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin \
  sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt
```

Build result: **BUILD SUCCESSFUL** in `2m 7s`.

Output JAR:

```text
build/libs/liquidbounce-0.31.2.jar
sha256=052fd835700270e3e3f1ab28ee0dc69583dd79b0017ac36b278b9208d74cd173
```

Installed FCL path:

```text
/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar
sha256=052fd835700270e3e3f1ab28ee0dc69583dd79b0017ac36b278b9208d74cd173
```

Previous installed JAR backup:

```text
/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar.bak-20260715-142236
sha256=5018f90255d998f264fa1520a9d043c4ee0360402bd68fa4404f9ec96d7ef48d
```

### Runtime status

FCL runtime attack tests were **not executed by Codex** in this shell because there is no interactive Minecraft/FCL
gameplay control here. The final JAR was installed for user-side FCL testing.

Required FCL checks still pending:

- Manual falling critical with `Criticals` disabled: expected one critical event and one Wither ambient sound.
- Standing attack and rising non-critical attack: expected no event and no Wither sound.
- Criticals `Jump`: expected sound only when the attack reaches the real vanilla critical branch.
- KillAura attack under conditions that reach the vanilla critical branch: expected one sound per branch entry.
- Cancelled attack: expected no `Player.attack` critical branch and no sound.
- Reconnect/world change: expected no stale signal or duplicate event.

### Limitation

Packet-only Criticals modes are still outside this fix. If a packet mode produces a server-side critical without entering
the local vanilla `Player.attack -> attackVisualEffects -> crit(Entity)` branch, this new signal will not fire for it.

### Files changed

- `src/main/kotlin/net/ccbluex/liquidbounce/event/events/EntityEvents.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/event/EventManager.kt`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/entity/MixinPlayerEntity.java`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleHitSound.kt`
- `Agent1.md`

### Commit / push

Commit hash: pending until commit creation; final hash is reported in the assistant response.

Branch: `main`

Remote: `origin https://github.com/Duong1234vi0/Wraith-0.1.git`

Push status: pending at entry time.

### Supersedes / reverts

Supersedes the unresolved timing diagnosis in `HitSound FCL installation/runtime audit (2026-07-15, Codex)`.

## HitSound strict Klack behavior re-port (2026-07-15, Codex)

### Request

User requested a strict source-faithful re-port of standalone `HitSound` from `WorkSpace/Client/klack-obfuscated.jar`.
The previous Wraith experiment added a custom `CriticalAttackEvent`, a `Player.attackVisualEffects(...) -> Player.crit(...)`
mixin hook, vanilla-critical branch detection, `playLocalSound(...)`, final-state attack priority, and cancellation
filtering. This pass intentionally removes that unproven hook and restores the decompiled Klack behavior exactly.

### Klack source / bytecode checked

- `WorkSpace/Client/klack-obfuscated.jar`
- `net.bean.klack.features.module.modules.combat.ModuleClientSound`
- `net.bean.klack.event.events.AttackEntityEvent`
- `net.bean.klack.injection.mixins.minecraft.network.MixinMultiPlayerGameMode`
- Klack event handler bytecode registration showed priority `0`.

Klack `ModuleClientSound` bytecode behavior:

- Module name decrypts to the standalone combat sound module behavior; target Wraith module remains `HitSound`.
- Settings:
  - `Volume`, default `1.0`, range `0.1..2.0`.
  - `Pitch`, default `1.0`, range `0.5..2.0`.
- Registers `handler<AttackEntityEvent>` with default priority `0`.
- Does not inspect event cancellation.
- Requires local player.
- Requires `event.entity is LivingEntity && event.entity.isAlive`.
- Critical predicate is exactly `player.fallDistance > 0.0 && !player.onGround()`.
- Calls:
  `ClientLevel.playSound(Entity, DDD, SoundEvents.WITHER_AMBIENT, SoundSource.PLAYERS, volume, pitch)`.

Klack `MixinMultiPlayerGameMode` dispatches `AttackEntityEvent` at `MultiPlayerGameMode.attack` HEAD, then cancels
the method only after `EventManager.callEvent(...)` returns if the event was cancelled. Therefore a default-priority
listener can run before later lower-priority cancellation decisions.

### Current Wraith files checked

- `Skill/README.md`
- `Skill/Agen.md`
- `Skill/CLAUDE_PORT_SKID_MODULE_GUIDE.md`
- `Skill/PORT_EVIDENCE_TEMPLATE.md`
- `Skill/PORT_REVIEW_CHECKLIST.md`
- `Skill/CODEBASE_RULES.md`
- `Skill/AGENT1_USAGE.md`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleHitSound.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/event/events/EntityEvents.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/event/EventManager.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/event/EventListener.kt`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/entity/MixinPlayerEntity.java`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/network/MixinClientPlayerInteractionManager.java`
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/combat/CombatExtensions.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt`
- `src/main/resources/liquidbounce.mixins.json`
- `net/minecraft/world/level/Level.java`
- `net/minecraft/client/multiplayer/ClientLevel.java`
- `src/main/resources/resources/liquidbounce/lang/en_us.json`

### Previous implementation removed

- Removed `CriticalAttackEvent` from `EntityEvents.kt`.
- Removed `CriticalAttackEvent::class` from `ALL_EVENT_CLASSES`.
- Removed the `MixinPlayerEntity` injection into
  `attackVisualEffects(Lnet/minecraft/world/entity/Entity;ZZZZF)V` before
  `Player.crit(Lnet/minecraft/world/entity/Entity;)V`.
- Removed `CriticalAttackEvent` imports and construction.
- Replaced the `CriticalAttackEvent` handler in `ModuleHitSound`.
- Removed the `playLocalSound(...)` HitSound playback path.

No separate mixin file existed exclusively for HitSound, so `liquidbounce.mixins.json` did not need a registration
change. `MixinPlayerEntity` was retained for its other live hooks.

### Restored behavior

- `ModuleHitSound` listens directly to `AttackEntityEvent`.
- Handler priority is default `0`, matching Klack bytecode.
- The handler intentionally does not return on `event.isCancelled`.
- Target gate is `target is LivingEntity && target.isAlive`.
- Predicate is `player.fallDistance > 0.0f && !player.onGround()`.
- Sound call is:
  `level.playSound(player, player.x, player.y, player.z, SoundEvents.WITHER_AMBIENT, SoundSource.PLAYERS, volume, pitch)`.
- `ModuleCriticals`, `CriticalsJump`, KillAura critical settings, movement events, and packet critical modes were not
  modified.
- `ModuleClientSound` remains separate and no nested `CritSound` configurable was restored.

### Diagnostics retained

Temporary bounded diagnostics are intentionally kept in the installed test build.

Prefix:

```text
[HitSoundKlackDiag]
```

The first 10 handled attack events per game session log:

- `AttackEntityEvent reached`
- target type
- target alive
- `fallDistance`
- `onGround`
- event cancellation state for observation only
- module running state

Additional bounded logs:

- `Klack predicate failed; no sound`
- `Klack predicate passed; calling playSound`
- `playSound returned`

If `playSound` throws an ordinary `Exception`, the full exception is logged once. `Throwable` is not caught.

### Static verification

- `git diff --check`: passed.
- Source search found no `CriticalAttackEvent` references under live source/resources.
- Source search found no remaining HitSound `attackVisualEffects -> Player.crit` injection.
- `ModuleHitSound` uses `AttackEntityEvent`.
- `ModuleHitSound` does not import/use `READ_FINAL_STATE`.
- `ModuleHitSound` does not branch on cancellation; cancellation is logged only for diagnostics.
- `ModuleHitSound` no longer uses `playLocalSound`.
- `ModuleHitSound` contains the Klack predicate and the Klack-shaped `playSound` call.
- `ModuleHitSound` remains registered exactly once in `ModuleManager`.
- `liquidbounce.module.hitSound.description` remains present.
- Active FCL config check: `HitSound` exists and is enabled; existing config values were not modified.

### Build / install

Build command:

```sh
env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk \
  PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin \
  sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt
```

Build result: **BUILD SUCCESSFUL** in `1m 54s`.

Built JAR:

```text
build/libs/liquidbounce-0.31.2.jar
sha256=86d0f388cc07ff936686108ba2f86711284aefdb55e56f59c2da35b7a064f17e
```

Installed FCL JAR:

```text
/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar
sha256=86d0f388cc07ff936686108ba2f86711284aefdb55e56f59c2da35b7a064f17e
```

Previous installed JAR backup:

```text
/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar.bak-20260715-150236
sha256=052fd835700270e3e3f1ab28ee0dc69583dd79b0017ac36b278b9208d74cd173
```

Bytecode verification:

- Built `ModuleHitSound.class` registers `AttackEntityEvent` with priority `0`.
- Built `ModuleHitSound.class` calls
  `ClientLevel.playSound(Lnet/minecraft/world/entity/Entity;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V`.
- Built JAR contains `ModuleHitSound.class`.
- Built JAR does not contain `CriticalAttackEvent.class`.
- Built `MixinPlayerEntity.class` has no `attackVisualEffects` / `CriticalAttackEvent` hook.

### Runtime status

Runtime status: awaiting user FCL test. Build success only proves compilation/packaging.

User should fully restart FCL and return every log line containing:

```text
[HitSoundKlackDiag]
```

Test expectations:

- Standing living-entity attack: entry log, predicate failed log, no sound.
- Manual falling attack with Criticals disabled: entry log, `fallDistance > 0`, `onGround=false`, predicate passed,
  playSound called/returned, Wither sound should be audible.
- Criticals `Jump`: sound only if the attack event happens during the falling phase.
- Cancelled/FakePlayer attack if practical: Klack-faithful behavior may still play if the predicate passed before a
  later cancellation.

### Files changed

- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleHitSound.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/event/events/EntityEvents.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/event/EventManager.kt`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/entity/MixinPlayerEntity.java`
- `Agent1.md`

### Commit / push

Implementation commit hash: `26b11d9e338b2570c9615d2ad637658b2e855626`

Branch: `main`

Remote: `origin https://github.com/Duong1234vi0/Wraith-0.1.git`

Push status: succeeded to `origin/main` (`200503444..26b11d9e3`) before this Agent1 metadata line was finalized.

### Supersedes / reverts

Supersedes the 2026-07-15 `HitSound vanilla critical-signal fix` entry. This build restores the decompiled Klack
behavior exactly and intentionally removes the unproven vanilla-critical event hook.

## Removed HitSound and replaced SmoothCamera with AlphaDLC behavior (2026-07-15, Codex)

### Request

Remove the failed standalone `HitSound` module completely, without restoring `ClientSound.CritSound`, and replace the
existing Wraith `SmoothCamera` module with a strict behavior port of AlphaDLC `SmoothCamera` from
`WorkSpace/Client/AlphaSRC/AlphaSRC`.

### Current files inspected

- `Skill/README.md`
- `Skill/Agen.md`
- `Skill/CLAUDE_PORT_SKID_MODULE_GUIDE.md`
- `Skill/PORT_EVIDENCE_TEMPLATE.md`
- `Skill/PORT_REVIEW_CHECKLIST.md`
- `Skill/CODEBASE_RULES.md`
- `Skill/AGENT1_USAGE.md`
- `Skill/RENDER_PORTING_GUIDE.md`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleHitSound.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/misc/ModuleClientSound.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleSmoothCamera.kt`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/render/MixinCamera.java`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt`
- `src/main/resources/resources/liquidbounce/lang/en_us.json`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleFreeLook.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleFreeCam.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/RotationManager.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/ClientModule.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/event/EventManager.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/config/ConfigSystem.kt`
- Minecraft 26.1.2 `net/minecraft/client/Camera.java` from the Loom sources JAR.

### AlphaDLC files inspected

- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/features/impl/render/SmoothCamera.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/mixins/CameraMixin.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/events/render/CameraEvent.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/events/render/CameraPositionEvent.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/utils/features/aura/warp/Turns.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/features/impl/player/FreeLook.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/features/impl/misc/FreeCam.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/features/impl/render/CameraSettings.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/features/module/Module.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/features/module/ModuleRepository.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/utils/client/managers/event/EventManager.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/utils/client/managers/event/EventHandler.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/utils/client/managers/event/types/Priority.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/utils/client/managers/event/events/callables/EventCancellable.java`
- `WorkSpace/Client/AlphaSRC/AlphaSRC/src/main/java/code/alphadlc/utils/client/managers/event/events/EventStoppable.java`

### HitSound removal

- Deleted `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleHitSound.kt`.
- Removed `ModuleHitSound` from `ModuleManager.registerInbuilt()`.
- Removed `liquidbounce.module.hitSound.description` from `en_us.json`.
- Source search confirmed no live `HitSound`, `ModuleHitSound`, `HitSoundKlackDiag`, `CriticalAttackEvent`,
  `attackVisualEffects`, `Player;crit`, `playWitherCritSound`, or `WITHER_AMBIENT` remains under `src/`.
- `ModuleClientSound` was not edited and still owns only module enable/disable sounds.
- No config migration was added. Wraith `ConfigSystem` deserializes only known current values from ordinary
  configurables, so old removed module/settings entries are ignored and dropped on normal store.

### AlphaDLC SmoothCamera behavior

- Setting:
  - `Smooth`, default `0.08f`, range `0.01f..0.30f`.
  - One factor is used for both rotation and all three position axes.
- State:
  - `smoothYaw = 0.0f`
  - `smoothPitch = 0.0f`
  - `smoothPos = null`
  - `initialized = false`
- Activation/deactivation reset:
  - `initialized = false`
  - `smoothPos = null`
- Rotation:
  - First camera-angle update initializes `smoothYaw/smoothPitch` to target and does not interpolate.
  - Later yaw uses `wrapDegrees(targetYaw - smoothYaw)`.
  - Later pitch uses direct `(targetPitch - smoothPitch)`.
  - Updates are `smooth += delta * Smooth`.
- Position:
  - First camera-position update initializes nullable `smoothPos` to target.
  - Later position uses `smooth += (target - smooth) * Smooth` on X, Y, and Z.

### Old Wraith SmoothCamera behavior removed

- Removed settings `EnableFirstPOV`, `ResetOnPerspectiveChange`, `HorizontalFactor`, and `VerticalFactor`.
- Removed `Vec3.ZERO` as an uninitialized position sentinel.
- Removed `lastPerspective`, perspective-change reset behavior, and first-person exclusion.
- Removed old position-only `cameraUpdate(...)`/`shouldApplyChanges()` integration.
- Removed the `Vec3.add(Vec3)` redirect that added a non-AlphaDLC `+1 Y` vehicle/cart camera offset.
- Removed the old `ModifyArgs` position smoothing pass so SmoothCamera no longer stacks an early position smoothing
  pass before the final camera position is known.

### Camera integration design

- Did not create generic Wraith camera events. Used direct module helpers:
  - `updateRotation(targetYaw, targetPitch)`
  - `updatePosition(targetPosition)`
  - `resetState()`
- `MixinCamera` now smooths the selected camera target rotation after vanilla has chosen the base yaw/pitch but before
  vanilla front-third-person inverse handling applies. This keeps the AlphaDLC-style "smooth base angle, then inverse"
  behavior.
- `RotationManager` `MovementCorrection.CHANGE_LOOK` remains visual-only in the camera mixin. SmoothCamera smooths the
  interpolated `CHANGE_LOOK` camera target and does not modify server rotations, player packet rotations, or
  `RotationManager` state.
- Final camera position is smoothed once at `alignWithEntity` TAIL from the current camera position after vanilla
  third-person movement/clipping/sleeping/vehicle positioning has run.
- Wraith lifecycle adaptation:
  - reset on enable;
  - reset on disable;
  - reset on `WorldChangeEvent`;
  - reset on `DisconnectEvent`;
  - reset if the local player reference changes.

### FreeLook / FreeCam / ownership adaptations

- AlphaDLC shares `CameraEvent` and `CameraPositionEvent`; cancellation does not stop later same-priority handlers, and
  same-priority order depends on handler registration/activation order.
- Wraith already has explicit exclusive camera ownership in `MixinCamera`:
  - `ModuleFreeLook` owns yaw/pitch and cancels `alignWithEntity` while active.
  - `ModuleFreeCam` writes the camera position directly.
- Wraith adaptation:
  - while FreeLook owns the camera, SmoothCamera state is reset and FreeLook is not smoothed/fought;
  - while FreeCam owns camera position, SmoothCamera state is reset and FreeCam's explicit position is not pulled back
    toward the player;
  - sleeping camera also resets SmoothCamera state so vanilla sleep rotation stays authoritative.

### Files changed / deleted

- Deleted: `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleHitSound.kt`
- Changed: `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/render/ModuleSmoothCamera.kt`
- Changed: `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/render/MixinCamera.java`
- Changed: `src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt`
- Changed: `src/main/resources/resources/liquidbounce/lang/en_us.json`
- Changed: `Agent1.md`

### Config compatibility

- Old `HitSound` module config entries are not migrated or redirected.
- Old SmoothCamera setting keys (`EnableFirstPOV`, `ResetOnPerspectiveChange`, `HorizontalFactor`, `VerticalFactor`) are
  not migrated into `Smooth`; the AlphaDLC default `0.08f` is used.
- No user config files were edited manually.

### Static verification

- `git diff --check` passed before and after this Agent1 append.
- Bounded source searches showed:
  - no live HitSound source/resource references under `src/`;
  - no live `WITHER_AMBIENT` use under `src/`;
  - no `CriticalAttackEvent` under `src/`;
  - no old SmoothCamera setting names under current SmoothCamera/mixin/localization;
  - no old `cameraUpdate`, `shouldApplyChanges`, `modifySmoothCameraVehiclePosition`,
    or `modifySmoothCameraPosition` hook remains.

### Build / JAR inspection

Java:

```text
openjdk version "25.0.3" 2026-04-21
OpenJDK Runtime Environment (build 25.0.3)
OpenJDK 64-Bit Server VM (build 25.0.3, mixed mode)
```

Build command:

```sh
env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk \
  PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin \
  sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt
```

Build result: **BUILD SUCCESSFUL** in `3m 2s`.

Built JAR:

```text
build/libs/liquidbounce-0.31.2.jar
sha256=86f490a085712547e0f97661177d4210f98995dfaee3b12dff726f90ec177cf1
```

JAR inspection:

- `net/ccbluex/liquidbounce/features/module/modules/render/ModuleSmoothCamera.class` present.
- `net/ccbluex/liquidbounce/features/module/modules/render/SmoothedCameraRotation.class` present.
- `net/ccbluex/liquidbounce/features/module/modules/combat/ModuleHitSound.class` absent.
- `ModuleManager` bytecode references `ModuleSmoothCamera.INSTANCE`.
- `ModuleManager` bytecode has no `ModuleHitSound` reference.
- Packaged `en_us.json` contains the updated SmoothCamera description and no `hitSound` key.

### Runtime status

FCL runtime testing was **not performed** by Codex in this shell. Build success proves compilation and packaging only.

Required FCL checklist:

- Confirm `HitSound` is absent from ClickGUI/module config surfaces.
- Confirm `ClientSound` remains present and module toggle sounds still work.
- Confirm critical/manual attacks no longer produce custom Wither sounds and no `[HitSoundKlackDiag]` log entries exist.
- Test `SmoothCamera` disabled/enabled with `Smooth=0.08`, `0.01`, and `0.30`.
- Test yaw crossing `179 -> -179`, pitch changes, first person, third-person back, third-person front, vehicles, Elytra,
  swimming/crawling/falling/knockback.
- Test FreeLook, FreeCam, and `RotationManager` `CHANGE_LOOK`/KillAura rotations for no fighting, no duplicate
  interpolation, and no packet/server rotation changes.

### Commit / push

Commit hash: pending at entry creation; final hash is reported in the assistant response because the commit cannot
self-record its own final hash inside this file.

Branch: `main`

Remote: `origin https://github.com/Duong1234vi0/Wraith-0.1.git`

Push result: pending at entry creation; final push status is reported in the assistant response.

### Supersedes / reverts

Supersedes all previous HitSound attempts, including `Split CritSound from ClientSound into HitSound`,
`HitSound vanilla critical-signal fix`, and `HitSound strict Klack behavior re-port`.

Supersedes the previous non-Alpha SmoothCamera ports from 2026-06-19 and 2026-07-02 that used first-person gates,
perspective resets, horizontal/vertical position factors, and the vehicle `+1 Y` offset.

## Removed SwingAnimation New and ported full Neverlose HandMyItems runtime (2026-07-15, Codex)

### Request

- Remove SwingAnimation mode `New`, `NewLeftHand`, its independent state machine, special dual-hand renderer, and all
  New-only attack/use/render hooks. The old implementation must not be retained, renamed, aliased, or reused.
- Port the active Neverlose `HandMyItems` wrapper and complete reachable Hold My Items runtime into Wraith as a
  standalone Render module named `HandMyItems` with no settings beyond the module toggle.
- Preserve the embedded Lua runtime, independent hand state, item property/model hooks, particles, camera offsets,
  resource reload behavior, Lua safety policy, and the complete built-in resource pack.
- Neverlose source is authoritative for HMI behavior; current Wraith source and official 26.1.2 mappings are
  authoritative for integration. `Agent1.md` was used as history only.

The hardcoded New implementation was removed because it was a separate, incomplete Wraith animation path and was not
the reference Hold My Items runtime requested by the user.

### Required process checked before editing

Read completely before source edits:

- `Skill/README.md`
- `Skill/Agen.md`
- `Skill/CLAUDE_PORT_SKID_MODULE_GUIDE.md`
- `Skill/PORT_EVIDENCE_TEMPLATE.md`
- `Skill/PORT_REVIEW_CHECKLIST.md`
- `Skill/RENDER_PORTING_GUIDE.md`
- `Skill/CODEBASE_RULES.md`
- `Skill/AGENT1_USAGE.md`

Read all `Agent1.md` history and all later entries involving SwingAnimation, New, NewLeftHand, Hold My Items,
HandMyItems, first-person/offhand rendering, item models/resource packs, and LuaJ. Historical New/HMI experiments were
not used as source behavior.

### Current Wraith files and integration inspected

- `build.gradle.kts`, `gradle.properties`, `fabric.mod.json`, `liquidbounce.mixins.json`, and the current access widener.
- `ModuleAnimations.kt`, `ModuleManager.kt`, module/configurable lifecycle, `ConfigSystem.kt`, `Value.kt`, and
  `ChooseListValue` fallback behavior.
- `MixinHeldItemRenderer.java`, `MixinMinecraftClient.java`, `MixinLivingEntityCompatibility.java`, current
  `MixinCamera.java`, game/item/block/model/feature render classes and their 26.1.2 bytecode descriptors.
- Existing ViewModel, HandShader, Iris hand framebuffer, SilentHotbar, KillAura block visual, FreeLook, FreeCam,
  SmoothCamera, and RotationManager `CHANGE_LOOK` ownership paths.
- Fabric Resource Loader v1 APIs and Wraith's established `includeDependency` nested-JAR packaging path.

Old config behavior was verified: an unavailable `SwingAnimation.Mode = New` fails only that value's deserialization,
is caught by `ConfigSystem`, and leaves the initialized `Mode1` default. Unknown `NewLeftHand` is ignored. No alias or
custom migration was added.

### Neverlose reference inspected

Entrypoints and active integration:

- `neverlose/modules/impl/render/HandMyItems.java`
- `neverlose/util/hmi/HandMyItemsRuntime.java`
- Active reference mixins: `GameRendererMixin`, `MinecraftClientMixin`, `ItemStackMixin`, `CameraMixin`,
  `LivingEntityMixin`, `BlockRenderManagerMixin`, `HandRenderTypeMixin`, `HeldItemRendererMixin`, `ItemRendererMixin`,
  `JavaMethodMixin`, `UseDurationMixin`, `UsingItemMixin`, `HangingSignMixin`, and `ItemRenderStateMixin`.
- Reachable debug output path in the reference in-game HUD mixin.

All 52 files under `com/holdmylua/source` were read and classified:

- Core: `LuaTestHMI`, `SwordAttacks`.
- Access: `AlternateBlockRenderer`, `CameraAccessor`, `ItemStackAccessor`, `LivingEntityAccessor`,
  `ModelElementAccess`, `PipelinesAccessor`.
- Runtime/global/model storage: `DispatcherStorage`, `GlobalsStorage`, `LuaContext`, `ItemModelContext`,
  `ItemModelStorage`.
- Lua: `LuaScriptCache`, `LuaScriptManager`, `ModelScriptCache`, `ScriptHolder`,
  `LuaAnimationResourceLoader`.
- Poses: `ModelPartAnimator`, `PoseX`, `PoseY`, `PoseZ`, `RotationX`, `RotationY`, `RotationZ`, `ScalePose`,
  `Poses`, `AbstractPose`.
- Particles: `LuaConsumer`, `Particle`, `ParticleManager`, `ParticleRenderManager`, `ParticleLifecycleType`,
  `ParticleRenderType`, `RenderSpace`, `ParticleRenderLayers`, `Texture`.
- Script API: `DebugTextRenderer`, `KeyBindManager`, `Sound`, `C`, `ComponentWrapper`, `Easings`, `I`, `JSItems`,
  `JSTags`, `M`, `P`, `S`, `Safe`, `SpearData`, `IrisCompat`.

The full `resourcepacks/handmyitems` tree was inspected: 206 files comprising 101 JSON, one JSON5, four Lua, two RPO,
79 PNG, 17 mcmeta, one OGG, and one properties file.

### Dependency / evidence matrix

| Feature | Reference entrypoint/dependency | Wraith conflict or 26.1.2 adaptation | Status |
|---|---|---|---|
| Module enable/disable | wrapper plus `HandMyItemsRuntime` | singleton module lifecycle, full clear on disable | Ported |
| Reload listener | `LuaAnimationResourceLoader` | Fabric Resource Loader v1 listener, registered once | Ported |
| Built-in pack | reference runtime registration | `liquidbounce:handmyitems`, default enabled, registered before pack repository construction | Ported |
| Hand pose | `hand_pose.lua`, `LuaScriptCache` | `PoseStack` and official mappings | Ported |
| Hand-relative pose | `hand_relative_pose.lua` | target arm invoker and physical arm mapping | Ported |
| Item pose | `item_pose.lua` | target `SubmitNodeCollector` item submission | Ported |
| Item model | `item_model.lua`, model cache/storage | target `ItemFeatureRenderer` quad pipeline | Ported |
| Main-hand swing | `LivingEntityMixin` | independent Wraith-owned accessor state | Ported |
| Offhand swing | `LivingEntityMixin` | separate ticks/progress/flags, no removed New state | Ported |
| Attack reset | `MinecraftClientMixin.startAttack` | exact target swing callsite, no packet change | Ported |
| Interact reset | `startUseItem` redirect | hand-specific reset then original swing | Ported |
| Switch events | held renderer previous-item fields | renderer generation invalidation on lifecycle clear | Ported |
| Block breaking | living accessor plus game mode | official `isDestroying` mapping | Ported |
| Use duration | `UseDurationMixin` | active-and-item-identity gated property override | Ported |
| Using item | `UsingItemMixin` | active-and-item-identity gated condition override | Ported |
| Map | held renderer map branch | target private `renderMap` invoker | Ported |
| Bow/crossbow | opposite-hand suppression | target `ItemUseAnimation`/charged API | Ported |
| Shield/block | direct model and property paths | target BLOCK animation | Ported |
| Spear/trident | direct model path and `SpearData` | target adds `SPEAR`; `KineticWeapon` replaces old trident-only API | Ported |
| Brush | direct model path | target BRUSH animation | Ported |
| Spyglass | reference render suppression | target `isScoping()` equivalent | Ported |
| Throwable items | Lua item script | no hardcoded Wraith fallback | Ported |
| Render as block | alternate block renderer | `BlockModelResolver`/`BlockModelRenderState`, tint and emission APIs | Ported |
| Item model dispatch | dispatcher and model FIFOs | target feature-render boundary cleanup | Ported |
| Particles | particle manager/render layers | custom geometry through `SubmitNodeCollector` | Ported |
| Camera offsets | `CameraAccessor`, `C` wrapper | target vectors/quaternion/dirty flags; composes once after current camera owner | Ported |
| Debug text | runtime debug API plus HUD callsite | target `GuiGraphicsExtractor`, active gated and cleared | Ported |
| Lua safety | `JavaMethodMixin`, `Safe` | exact LuaJ 3.0.1 target/descriptor; permits only `@Safe`, `getOrDefault`, `put` | Ported |
| Iris compatibility | `IrisCompat` and existing hand path | reflection retained; existing outer Wraith Iris/HandShader pass remains | Ported |
| Disabled fallback | all active mixins | inactive renderer calls original and dynamic hand forcing returns vanilla value | Ported |

### Removed from SwingAnimation

- Removed enum entry `NEW("New")` and setting `NewLeftHand`.
- Removed per-hand `NewHandState`, player tracking, tick/world/disconnect lifecycle, switch/equip/swing state, attack and
  interaction observation, all New item-category/use transforms, constants, data classes, imports, and comments.
- Removed the complete `hookNewSwingAnimation` interception and its New-only arm/item renderer shadows.
- Retained ordinary Mode1-Mode7, 360, Slant, Fade, and SpinSlash behavior and unrelated ViewModel, SilentHotbar,
  KillAura block visual, equip, and vanilla swing hooks.

### Added module and runtime

- Added `ModuleHandMyItems` in Render and registered it exactly once in `ModuleManager`.
- The module has no settings, modes, offsets, hand toggles, sliders, reload button, fallback, or compatibility option.
- Added all 52 reference runtime classes plus Wraith-owned `HandMyItemsRuntime` under
  `net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime`.
- `LuaTestHMI` is frame context only and is not a Fabric initializer. Wraith remains mod ID `liquidbounce`.
- No target class imports `neverlose.*` or uses the collision-prone `com.holdmylua` namespace.

Lifecycle:

- Constructor-time idempotent initialization registers the pack and listener before the client pack repository is
  built. Module initialization may call it again safely.
- Enable marks active first and loads current resources. A missing early pack requests one repository reload without a
  frame/tick reload loop.
- Reload preparation builds complete new caches before atomically installing them. Parse/read failures identify the
  resource path and clear to safe `return` caches without leaving mixed old/new data.
- Disable clears primary/addon scripts, global property maps/registry, dispatcher/model FIFOs, model animator,
  particles, debug text, replacement/current items, Iris reflection state, matrices/delta context, independent living
  state, and camera offsets.
- World change, disconnect, local-player replacement, script reload, and reload failure invalidate renderer state.
  Reconnect reactivates the runtime only when the module toggle remains enabled.

### Mixins ported

Registered 16 strict mixins in the existing `liquidbounce.mixins.json`:

- Behavioral graph: `MixinHandMyItemsGameRenderer`, `Minecraft`, `ItemStack`, `Camera`, `LivingEntity`,
  `BlockModelResolver`, `HandSelection`, `HeldItemRenderer`, `ItemFeatureRenderer`, `JavaMethod`, `UseDuration`,
  `IsUsingItem`, `HangingSignRenderer`, `FeatureRenderDispatcher`, and `Gui`.
- Target access helper: `MixinHandMyItemsMinecraftAccessor`.

No `require = 0`, duplicate mixin config, or access-widener entry was added. Private target behavior is reached through
strict accessors/invokers whose 26.1.2 descriptors were checked with `javap`.

The HMI renderer wraps the two vanilla per-hand calls. Inactive or unready state invokes the original exactly. Active
state owns the first-person replacement, so ordinary SwingAnimation and ViewModel transforms do not execute a second
time. Existing HandShader/Iris outer hand-pass capture remains outside this ownership boundary. Matrix pushes/pops are
balanced with `finally`, and model/dispatcher queues are cleared at the target render boundary.

### LuaJ and safety

- Added `org.luaj:luaj-jse:3.0.1` through Wraith's established `includeDependency` mechanism.
- Added LuaJ's MIT license at `META-INF/licenses/luaj.txt`.
- The distributable contains `META-INF/jars/luaj-jse-3.0.1.jar`; the nested JAR contains 354 `org/luaj` entries,
  including the exact `JavaMethod` target.
- Loom adds generated nested dependency metadata with ID `org_luaj_luaj-jse`; it has no initializer or entrypoint and
  does not duplicate Wraith's top-level mod ID. The outer JAR has exactly one top-level `fabric.mod.json`.
- The JavaMethod restriction is necessarily class-target global inside the bundled LuaJ library. Source search found no
  other Wraith LuaJ user, so the current observable scope is the embedded HMI environment.

### Resource pack and asset adaptations

- Copied all 206 files to `src/main/resources/resourcepacks/handmyitems`.
- Preserved exact lookup paths:
  `minecraft:holdmyitems/hand_pose.lua`, `item_pose.lua`, `hand_relative_pose.lua`, and `item_model.lua`, plus the four
  addon stack paths used by external packs.
- Updated only `pack.mcmeta` from the 1.21.11 metadata to target resource format `min_format: 84`, `max_format: 84`.
- The other 205 files retain reference content. Of those, 195 are byte-identical; ten reference text files were
  normalized from CRLF to LF and existing trailing line whitespace was removed so `git diff --check` remains clean.
  No model, texture, sound, RPO, script, or item definition was discarded or redesigned, and the text formatting has
  no runtime semantics.
- All JSON/mcmeta parses. Static 26.1.2 inspection confirmed the used item-model selectors/properties remain available;
  no individual asset/schema rewrite was required. FCL resource loading still requires runtime validation.

### Target adaptations and reasons

- Yarn names were translated to official 26.1.2 mappings and exact target descriptors.
- Old ordered-render APIs became `SubmitNodeCollector`, feature submission, custom geometry, and target render types.
- Old block-render manager hooks became `BlockModelResolver`/`BlockModelRenderState` with target tint/light emission.
- Item model animation moved to target `ItemFeatureRenderer` quad submission while retaining per-quad animator order,
  glint, tint, outline, and cleanup.
- Static reference hand-selection mutation was replaced with active-only dynamic field-result modification. Disabled
  state therefore preserves vanilla selection as required.
- Camera offsets use target quaternion/vector fields and do not recursively call intercepted setters. They are cleared
  on lifecycle reset and skipped for `do_a_barrel_roll`, matching reference compatibility behavior.
- Wraith ViewModel is intentionally skipped while HMI owns rendering because composing it would apply a second
  transform absent from the reference. It works normally when HMI is inactive.
- Target `SPEAR` use animation was added beside reference TRIDENT because 26.1.2 exposes the split target action.
- Transactional cache installation, active-only gating, reconnect reset, and lifecycle-safe dynamic hand selection are
  required Wraith singleton/disable-state adaptations requested by the task.
- Package relocation prevents collision with an independently installed Hold My Items mod.

### Excluded reference code

- `FishingBobberMixin` and `PrevLivingMixin` were excluded because they are absent from the active reference mixin
  configuration and no registered runtime, Lua wrapper, reflection, or reachable class calls them.
- Unrelated Neverlose camera events/modules, HUD, FOV/post-processing, ClickGUI, GlassHands, sound systems, media player,
  voice chat, and client render core were excluded. HMI-specific methods were extracted from mixed Neverlose mixins.
- The small Neverlose wrapper class itself was not copied under a Neverlose package; its enable/disable contract is
  represented by `ModuleHandMyItems` and `HandMyItemsRuntime`.
- No unrelated Neverlose dependency, Lombok, second Fabric initializer, or dead duplicate HMI mixin was ported.

### Files changed / added

- Changed: `build.gradle.kts`.
- Changed: SwingAnimation and its three former New integration files:
  `ModuleAnimations.kt`, `MixinHeldItemRenderer.java`, `MixinMinecraftClient.java`,
  `MixinLivingEntityCompatibility.java`.
- Changed: `ModuleManager.kt`, `liquidbounce.mixins.json`, and `en_us.json`.
- Added: `ModuleHandMyItems.kt`.
- Added: 53 runtime Java files under the Wraith-owned HandMyItems runtime package.
- Added: 16 narrowly scoped HMI mixins.
- Added: all 206 built-in pack files and `META-INF/licenses/luaj.txt`.
- Changed: `Agent1.md`.
- Reference ZIP/source tree, uploaded archives, build cache, and unrelated dirty worktree files were not edited/staged.

### Static verification

- `git diff --check` passed before this entry.
- SwingAnimation-scoped search has no `Mode.NEW`, `NewLeftHand`, `NewHandState`, New state/helper/renderer symbol, or
  New localization/config surface. The unrelated NameTags enum value `NEW("New")` remains.
- No `neverlose.*` or `com.holdmylua` reference remains in target source.
- Module registration count: one. HMI mixin registration count: 16. Runtime source count: 53. Pack file count: 206.
- Reference/target `@Safe` annotation count matches `135/135`.
- All four preprocessed primary scripts compile through packaged LuaJ 3.0.1.
- All 101 JSON and 17 mcmeta files parse with `jq`.

### Build / JAR inspection

Java:

```text
openjdk version "25.0.3" 2026-04-21
OpenJDK Runtime Environment (build 25.0.3)
OpenJDK 64-Bit Server VM (build 25.0.3, mixed mode)
```

Command:

```sh
env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk \
  PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH \
  sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt
```

Result: **BUILD SUCCESSFUL** in `2m 3s`.

Artifact:

```text
build/libs/liquidbounce-0.31.2.jar
sha256=eb3ccf091bb623f3adcc1180cfd91a277830eb43671e01f514d6139e9f2ce44e
```

JAR inspection confirmed:

- `ModuleHandMyItems.class`, 54 runtime class entries (53 source classes plus loader inner class), and all 16 HMI mixin
  classes are present.
- Nested LuaJ, license metadata, all 206 resource files, four primary Lua scripts, item definitions/models/textures,
  particles, pack metadata, localization, and the existing mixin config are present.
- No New-specific SwingAnimation class/state, Neverlose class, `com/holdmylua` class, second top-level
  `fabric.mod.json`, HMI initializer, or stale duplicate HMI mixin is present.

### Runtime status

FCL/MobileGlues runtime testing was **not performed** by Codex in this shell. Build, bytecode, script compilation, and
packaging checks are not runtime proof.

Status pending FCL validation:

- Main hand and independent offhand animation: source/build complete, runtime unverified.
- Item use, map, bow/crossbow, shield, spear/trident, brush, spyglass, throwables: runtime unverified.
- Item models, custom quads/glint, block models, animated textures, and particles: runtime unverified.
- Resource reload, repeated toggle, reconnect/world change, and enabled-at-restart lifecycle: runtime unverified.
- Iris/shaders, HandShader, ViewModel, SwingAnimation remaining modes, camera modules, KillAura, and FCL/MobileGlues:
  runtime unverified.
- Disable fallback and no-double-render guarantees were statically reviewed but require runtime observation.

### Commit / push

Feature commit hash: `54e14b77b3feeb57eb47b5e0e7d1f179f4381040`

Branch: `main`

Remote: `origin https://github.com/Duong1234vi0/Wraith-0.1.git`

Push result: successful; `main` advanced from `d67999a6e` to `54e14b77b` on `origin`. The documentation-only follow-up
that records this result is reported separately in the assistant response because a commit cannot self-record its own
hash.

### Supersedes / reverts

This entry supersedes all previous SwingAnimation New, Hold My Item, Hold My Items, and HandMyItems attempts. The old
hardcoded New implementation is removed and is not a fallback for this runtime.

## HandMyItems constructor-mixin crash and strict runtime audit (2026-07-15, Codex)

### Crash evidence and correction

The user-supplied FCL log reports:

```text
Mixin apply failed: liquidbounce.mixins.json:minecraft.handmyitems.MixinHandMyItemsMinecraft
InvalidInjectionException: @At("HEAD") selector @Inject handler before super() invocation must be static
Target: net.minecraft.client.Minecraft.<init>(GameConfig)
Handler: hmi$initializeRuntime(CallbackInfo)
```

The invalid injector was the instance `@Inject(method = "<init>", at = @At("HEAD"))` in
`MixinHandMyItemsMinecraft`. It was removed completely, rather than made static or moved to another constructor
location. The Neverlose reference has no HMI Minecraft-constructor initializer; Wraith's normal client-start/module
initialization now owns the idempotent built-in-pack and reload-listener registration.

The previous `setLevel` runtime-restore injector and the disconnect handler that disabled HMI were also removed. The
module is the source of truth: enable owns `Runtime.enable()`, disable owns `Runtime.disable()`, and world/player
changes clear transient state while retaining active ownership when the module remains enabled.

### Camera bytecode audit

The merged Minecraft 26.1.2 bytecode shows `Camera.setPosition(DDD)` constructs a `Vec3` and invokes
`Camera.setPosition(Vec3)`. The vector overload directly writes `position` and updates `blockPosition`. Calling the
vector overload from the scalar injector therefore recursively re-entered the scalar injector. The port now injects
both exact descriptors and directly updates those two fields through a unique helper; disabled and
`do_a_barrel_roll` paths fall through to vanilla behavior. No recursion guard or intercepted-overload call remains.

### Current 16-mixin audit

| Wraith mixin | Reference responsibility | 26.1.2 target / descriptor | Result |
|---|---|---|---|
| BlockModelResolver | Block model rendering | `BlockModelResolver.update(BlockModelRenderState,BlockState,BlockDisplayContext)` | verified; Wraith API split |
| Camera | camera rotation/position | `setRotation(FFF)V`, `setPosition(DDD)V`, `setPosition(Vec3)V` | verified; vector companion added |
| FeatureRenderDispatcher | frame cleanup | `renderAllFeatures()V` | verified |
| GameRenderer | frame/runtime hook | `render(...)V` | verified |
| Gui | debug/render-state hook | `extractRenderState(GuiGraphicsExtractor,DeltaTracker)V` | verified Wraith adaptation |
| HandSelection | hand selection | `ItemInHandRenderer.renderHandsWithItems(...)V` field expressions | verified active-only |
| HangingSignRenderer | hanging-sign model | `submitSpecial(...)V` | verified |
| HeldItemRenderer | hand/item rendering | `renderHandsWithItems(...)V`, `renderArmWithItem(...)V` | verified |
| IsUsingItem | using-item predicate | `get(ItemStack,ClientLevel,LivingEntity,int,ItemDisplayContext)Z` | verified |
| ItemFeatureRenderer | item model/quads | `renderItem(BufferSource,OutlineBufferSource,ItemSubmit)V` | verified |
| ItemStack | replacement stack copy | `copy()ItemStack` | verified |
| JavaMethod | script method invocation | `invokeMethod(Object,Varargs)LuaValue` | verified LuaJ 3.0.1 |
| LivingEntity | swing/transient state | `baseTick()V`, `swing(InteractionHand,boolean)V` | verified |
| Minecraft | swing reset | `startAttack()Z`, `startUseItem()V` invocations | verified; constructor/setLevel HMI hooks removed |
| MinecraftAccessor | block-model resolver access | `Minecraft.blockModelResolver` | verified |
| UseDuration | use-duration property | `get(ItemStack,ClientLevel,ItemOwner,int)F` | verified |

The 16 entries are a target-version split/adaptation of the 14 active Neverlose responsibilities; no broken target,
shadow, accessor, descriptor, constructor, or MixinExtras mapping was found, and no `require = 0` suppression was used.

### Strict parity classification

- Source-faithful: enable/disable ownership, script/model/item/hand paths, frame cleanup, active-only selection,
  independent main/offhand state, and the embedded resource pack/scripts.
- Required 26.1.2 adaptations: renamed/split renderer targets, `BlockModelResolver`, feature-dispatcher cleanup,
  Wraith MixinExtras signatures, and direct `Camera` field updates for the target bytecode.
- Required Wraith lifecycle adaptation: registration remains in normal `ModuleHandMyItems.init()` after Wraith client
  start; it is idempotent and is not a second Fabric initializer.
- Invented behavior removed: Minecraft constructor runtime initialization, `setLevel` re-enable/disable arbitration,
  and disconnect-driven runtime deactivation.
- Remaining deviations: FCL disabled/enabled launch and behavior smoke tests are still required; source parity alone is
  not runtime proof.

### Verification

- `git diff --check`: passed.
- Clean command: `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt`.
- Result: **BUILD SUCCESSFUL** (2m 22s).
- Built SHA-256: `26571682961b93fd6327e4f835120c8fb872ffd4cfae9ce457f8342c2aa7bd1c`.
- Installed SHA-256: same; previous JAR backed up as `/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar.bak-20260715-232205`.
- Module-disabled launch: not run in this shell; FCL result pending.
- Module-enabled launch: not run in this shell; FCL result pending.
- World load and main/offhand smoke matrix: not run; runtime unverified.

Runtime status: Compile verified; Mixin bootstrap verified **no**; disabled path verified **no**; enabled path verified
**no**; behavior parity verified **no**. HandMyItems must not be called runtime-verified until both module-disabled and
module-enabled FCL launches succeed.

Feature commit hash: `6a1042bd99ce319efdc95a954aa2596f9a9fe33f`; push result: successful (`main` pushed to `origin`).

This corrects the prior “source-complete” claim: compilation and static review did not validate constructor-injection
rules, target bytecode call graphs, or FCL runtime bootstrap.

## HandMyItems static-pose Lua runtime diagnosis and readiness fix (2026-07-15, Codex)

The user observed a resource reload followed by a rigid HMI dual-hand pose with no attack, interaction, item-switch,
or item/model animation. Mixin bootstrap was already passing. Class/resource counts were not treated as runtime parity.

### Existing log evidence

Inspected `/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/logs/latest.log`; the optional
`latest_game.log` was absent. The relevant context was:

```text
[23:38:26] [Worker-Main-4/WARN]: HandMyItems script minecraft:holdmyitems/hand_pose.lua is missing; using an empty script
[23:38:26] [Worker-Main-4/WARN]: HandMyItems script minecraft:holdmyitems/item_pose.lua is missing; using an empty script
[23:38:26] [Worker-Main-4/WARN]: HandMyItems script minecraft:holdmyitems/hand_relative_pose.lua is missing; using an empty script
[23:38:26] [Worker-Main-4/WARN]: HandMyItems script minecraft:holdmyitems/item_model.lua is missing; using an empty script
[23:38:28] [Render thread/INFO]: Loaded embedded HandMyItems Lua scripts
[23:38:28] [Render thread/ERROR]: HandMyItems built-in pack did not expose its primary Lua scripts after reload
```

An unrelated earlier startup error was also present: `ScriptManager was unable to load scripts` with
`Cannot load scripts before the script engine is initialized.` The old JAR had no `[HMIDiag]` lines. Thus the old log
proves primary resource lookup failure and fallback installation, but proves no Lua compile/runtime error, Java wrapper
failure, missing mapped method, or zero swing progress. It also does not expose the built-in-pack API return value.

### Confirmed root cause and repair

`ScriptHolder.reset()` created non-null `new LuaScriptCache("return")` primary caches, while renderer readiness only
checked object existence. Missing resources were converted to the same no-op source. The renderer therefore owned the
hand path and applied fixed transforms while Lua did nothing. A runtime exception also permanently disabled a cache
without propagating failure or relinquishing ownership.

Implemented:

- Explicit `DISABLED`, `INITIALIZING`, `LOADING`, `READY`, and `FAILED` states. HMI ownership and existing HMI
  properties/particles/models are active only in `READY`; loading and failure delegate to ordinary rendering.
- Source-aware primary/addon caches with source ID/length, compile status, fallback classification, runtime status,
  failure, `isLoaded()`, and `isOperational()`. Fallback caches cannot satisfy readiness.
- Transactional loader: all four required resources are located, read, compiled, and validated in temporary state;
  only then are active holder caches/addons/model storage replaced. Failures discard the transaction and report the
  exact resource-bearing exception.
- Lua execution failures now disable the cache, notify the runtime with script/hand/item context, mark `FAILED`, clear
  HMI state, and propagate to the outer renderer, which restores matrix balance and calls the original renderer.
- Early registration through a Wraith-owned Fabric client entrypoint in the existing `liquidbounce` JAR/mod ID.
  Registration/listener setup is idempotent and attempted once; `packRegistered` is true only for a successful API
  return. A full repository reload is requested only when the current manager lacks the pack's primary resources.
- JavaMethod safety timing restored from Wraith `HEAD` to the Neverlose `RETURN` injection, retaining `@Safe` and the
  narrow `getOrDefault`/`put` allowance.
- 26.1.2 bytecode confirms `startAttack` and `startUseItem` reach `LocalPlayer.swing(InteractionHand)`. The existing
  reference independent main/offhand reset and `baseTick` state machine remains in place; diagnostics now distinguish
  actual swinging flags, event flags, progress/interpolation, interaction, block breaking, switching, delta time,
  active/rendered hand, and item.

### Neverlose behavior matrix

| Behavior | Reference condition/path | Wraith path and evidence | Status |
|---|---|---|---|
| Activation/disable | runtime owns renderer only when active | explicit state gates all HMI paths; old log proved ownership only | repaired; FCL pending |
| Pack registration/reload | early pack/listener, no toggle loop | new client entrypoint; one-time registration; reload only if resources absent | corrected; new log pending |
| Four primary scripts | all required chunks loaded | IDs read/compiled transactionally; old log showed all four missing | corrected; READY pending |
| Script execution | hand/relative/item/model chunks execute per path | first execution and success are bounded diagnostics | pending runtime proof |
| Main attack/offhand use | reset correct independent hand | 26.1.2 targets verified against bytecode; no old swing log | static parity; FCL pending |
| Switch/use/block break | per-hand event/interaction state reaches scripts | existing state path retained and logged | FCL pending |
| Independent swing progress | separate ticks, duration, prev/current interpolation | existing state machine retained; runtime samples added | FCL pending |
| Dynamic/fixed rendering | dynamic scripts plus fixed transforms only when complete | fixed transforms now behind READY and real-cache validation | corrected; visual pending |
| Failure fallback/world change | release custom ownership and clear transient state | FAILED clears state; DISABLED and world/player paths delegate/reset | forced/FCL tests pending |

No material execution or visual row is marked equivalent without user-observed FCL evidence.

### Diagnostics retained

The installed test JAR retains bounded `[HMIDiag]` logs for pack registration result/thread, reload preparation,
resource IDs/bytes, compilation, apply/READY, fallback/ownership, first execution of each primary script, execution
failures with stack/cause/script/hand/item context, and swing-state samples. They remain until the first user log is
returned.

### Build/install and runtime status

- `git diff --check`: passed before the clean build.
- Required Java 25 `clean assemble -x detekt` command: **BUILD SUCCESSFUL**.
- Built SHA-256: `e512e7568884f780077b21b9caa6f055c93bf0ce241fe45ab2e4b6f28166a153`.
- Installed SHA-256: `e512e7568884f780077b21b9caa6f055c93bf0ce241fe45ab2e4b6f28166a153`.
- Backup: `/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar.bak-20260715-hmi-runtime`, SHA-256 `ed9b24f905819060fdf8cd9e9b0b6a525ce01d56cbdc109979b33286790a2ea6`.
- Module-disabled/enabled launches, script READY/execution, main/offhand visual animation, failure fallback, and
  reload/world smoke tests are not executable in this shell and remain pending.

### Files, commit, and push

Changed files are limited to HMI runtime/cache/holder/loader, early bootstrap and existing `fabric.mod.json`, held-item
renderer, LivingEntity/accessor swing path, JavaMethod timing, and this entry. References, logs, extracted workspaces,
and the backup JAR remain unstaged. Commit/push status is recorded after final staging.

The diagnostic/fix commit is `86d6e9cc0` (`fix(render): restore HandMyItems animation runtime`), on branch `main`,
remote `origin`, and was pushed successfully. This Agent1 status addendum is the documentation follow-up after that
push; neither result is a claim of visual parity before the user test.

This entry corrects prior “full/source-complete” descriptions: compile/class/resource counts are not behavioral proof,
and visual parity will not be claimed until the user confirms working MainHand and Offhand animation.

## V-core AutoCrystal and CrystalOptimizer port (2026-07-16, Codex)

### Request and references checked

Ported V-core 0.8's `AutoCrystal` as a separate Wraith Combat module together with its direct
`CrystalOptimizer` dependency. This is explicitly V-core's **crosshair-driven helper**, not a damage-based
CrystalAura: it has no target scan, position scoring, damage/self-damage logic, rotations, ranges, prediction,
rendering, Offhand support, silent switching, or automatic target acquisition.

Inspected in full from the supplied extracted `VCore-0.8` source:

- `vcore/features/modules/combat/AutoCrystal.java`
- `vcore/features/modules/combat/CrystalOptimizer.java`
- `vcore/utility/player/InventoryUtility.java` and `SearchInvResult.java`
- `vcore/events/impl/EventAttack.java` and `EventItemUse.java`
- `vcore/injection/MixinMinecraftClient.java` and `MixinPlayerEntity.java`
- `vcore/features/modules/Module.java` and `vcore/core/manager/client/ModuleManager.java`

Also inspected `CrystalManager.java`, `MixinEndCrystal.java`, and `ICrystal.java`. Direct-reference and repository
searches show no edge from V-core `AutoCrystal` or `CrystalOptimizer` to any of those three; `ICrystal` is only used
by `MixinEndCrystal`, and the manager is isolated from this helper path. They were intentionally not ported.

Wraith inspection covered the module registry and existing crystal helpers, interaction/inventory extensions,
event manager, client/input mixins, target-version Minecraft sources, and packet/entity APIs. `ModuleLegitHelper`
and `CrystalDestroyFeature` were inspected but not changed or reused. They remain independent modules and can make
competing actions if enabled together, matching the absence of mutual exclusion in V-core.

### Implemented behavior

Added `ModuleAutoCrystal` with these exact source-facing settings:

- `ActivateKey` (unbound by default; raw physical HOLD behavior), `PlaceDelay` 0 (`0..10` ticks), and
  `BreakDelay` 0 (`0..10` ticks).
- `SwitchDelay` 0 and `BaseDelay` 0 (`0..10` ticks each), both visible only with `CrystalBase`.
- `CrystalBase`, `StopOnKill`, `DamageTick`, and `AntiWeakness`, all default `false`.
- Hidden-until-`CrystalBase` `BaseSetting` subgroup: `CheckPlace` false, `WorkWithTotem` false,
  `WorkWithCrystal` false, and `SwordSwap` true.

The implementation preserves `placeClock`, `breakClock`, `basePlaceClock`, `baseSwitchClock`, `lastCrystalTick`,
the CrystalBase flags, and readable `crystalling`. Enable, disable, disconnect, world replacement, and local-player
replacement reset exactly the V-core state path. GUI, use-item, StopOnKill, and DamageTick pauses do not reset it.

`runCrystalTick(ignoreActivationKey)` keeps the V-core ordering: screen gate; main delay clocks; pauses; activation
reset; CrystalBase handling/reset; `crystalling`; MainHand-only crystal gate; once-per-`tickCount`; entity first;
block second. Entity targets are only crosshair `EndCrystal` or `Slime`; blocks are only the crosshair block, or the
fixed 5.0-block, no-fluid `player.pick(5.0, 1.0f, false)` fallback behind an optimizer-inhibited crystal. Placement
uses V-core's Obsidian/Bedrock, one-air-block, two-block-tall entity-box rule.

CrystalBase remains crosshair-driven: valid sword/totem/crystal starts, `CheckPlace`, base/charged-anchor handling,
visible hotbar Obsidian selection, base placement, then visible crystal selection. Its switch/place clocks decrement
only when queried. `SwordSwap` only initiates an existing base sequence; it is not a general swap-back setting.

AntiWeakness keeps the original Weakness/Strength comparison and searches only slots `0..8` for the first valid
sword/pickaxe/axe/shovel. It visibly changes `selectedSlot`, calls `ensureHasSentCarriedItem()`, and restores only
the previous slot after an AntiWeakness attack. Target-version tool classes were mapped to the equivalent item tags.
`DamageTick` retains V-core's unusual airborne/hurt-time predicate and local-vehicle check literally.

Added cancellable `AttackInputEvent` and `ItemUseEvent`, registered in `ALL_EVENT_CLASSES`, and dispatched from
`Minecraft.startAttack()` / `startUseItem()` at method HEAD. The attack hook returns `false` when cancelled; item use
cancels the method. AutoCrystal's raw GLFW button-0/button-1 guards match V-core. Its direct
`MultiPlayerGameMode.attack` / `useItemOn` calls intentionally bypass those input events.

Added `ModuleCrystalOptimizer` with only `Blatant` (default `true`) and a concurrent inhibited-ID timestamp map.
It uses `max(150 ms, player ping * 2)`, clears on disable/disconnect/world or player replacement, handles actual
`ServerboundAttackPacket` entity attacks, and uses `ClientboundExplodePacket.center()` for explosion inhibition.
Blatant mode cancels only already-inhibited outgoing attack packets; non-Blatant only removes the attacked crystal
locally. Nearby EndCrystals within squared distance `144.0` are inhibited. `ClientLevel.removeEntity(id, KILLED)`
was selected because its 26.1.2 implementation invokes the client removal callback once, avoiding the old manual
double-callback path. Only inhibited EndCrystals are ignored for placement.

### Files and target-version adaptations

Changed:

- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleAutoCrystal.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleCrystalOptimizer.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/event/events/GameEvents.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/event/EventManager.kt`
- `src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/client/MixinMinecraftClient.java`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt`
- `src/main/resources/resources/liquidbounce/lang/en_us.json`

Minecraft 26.1.2 adaptations are limited to Mojmap/API names: `RespawnAnchorBlock.CHARGE`,
`ServerboundAttackPacket(int entityId)`, `ClientboundExplodePacket.center()`, `ClientLevel.removeEntity`, modern
`EndCrystal`/`Slime`, Wraith's direct interaction extensions, and Wraith `InputBind` plus raw GLFW state. These
adaptations do not introduce a conventional CrystalAura behavior.

### Static/build/install/runtime status

- `git diff --check`: passed.
- Static review confirmed exactly one registration for each module; only one Optimizer setting; no AutoCrystal
  target manager, rotation manager, range/damage/render logic, SilentHotbar, Offhand, CrystalDestroyFeature, or
  changes to ModuleLegitHelper/CrystalDestroyFeature.
- Java: OpenJDK `25.0.3` (2026-04-21).
- Build command:

  ```sh
  env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk \
    PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin \
    sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt
  ```

  Final result: **BUILD SUCCESSFUL** in `2m 35s`.
- Final JAR: `build/libs/liquidbounce-0.31.2.jar`, SHA-256
  `4128bc77d00b5cd25d7ed87073488f8903ef455ab483c170740b48ecfaac85c3`.
- Packaged JAR inspection confirmed both module classes, both input-event classes, `MixinMinecraftClient`, and
  `resources/liquidbounce/lang/en_us.json` entries.
- Installed the final JAR at
  `/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar`; installed SHA-256
  matches the build. Preserved prior JARs as non-overwritten backups, including
  `liquidbounce-0.31.2.jar.bak-20260716-autocrystal` and
  `liquidbounce-0.31.2.jar.bak-20260716-autocrystal-pre-input-order`.
- FCL runtime matrix remains unverified. An authorized launcher attempt used
  `am start -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -p com.tungsten.fcl`, but Android
  returned `Error: Activity not started, unable to resolve Intent ...`. No main-menu/world/placement/break/
  CrystalBase/optimizer runtime claim is made. The remaining deviation is runtime evidence only, not a known
  source-port deviation.

### Commit / push

Feature commit: `ff0ac8522` (`feat(combat): port V-core AutoCrystal`).

Branch: `main`. Remote: `origin https://github.com/Duong1234vi0/Wraith-0.1.git`.

Push result: successful; `origin/main` advanced from `2c04e9818` to `ff0ac8522`. This documentation entry is the
separate follow-up so it can record that completed feature commit and push without self-referential commit metadata.

## Restored Minecraft 1.21.4 Offhand module behavior (2026-07-16, Codex)

### Request and authority

The user requested replacement of Wraith's mixed Offhand implementation with the exact observable behavior and
configuration surface from the supplied Minecraft **1.21.4**, Yarn **1.21.4+build.8** project. The reference source
controls behavior; live Wraith controls 26.1.2 official mappings, event/scheduler integration, and available helpers.
`Agent1.md` was used only to identify the superseded GrimStop history, never as current-source authority.

### Files inspected

Read completely from `WorkSpace/Client/tesststststststst`:

- `features/module/modules/player/offhand/ModuleOffhand.kt` and `Totem.kt`;
- `utils/inventory/InventoryUtils.kt`, `Slots.kt`, and `ItemSlot.kt`;
- `utils/client/Chronometer.kt` and `event/events/PlayerEvents.kt`;
- `features/module/modules/combat/killaura/ModuleKillAura.kt`;
- `features/module/modules/combat/crystalaura/ModuleCrystalAura.kt`;
- `features/module/modules/player/nofall/ModuleNoFall.kt`.

Read live Wraith's Offhand/Totem sources, `src.zip` (the target files match the live copies byte-for-byte),
`InventoryUtils.kt`, `InventoryManager.kt`, scheduling event/action definitions, slots, `ClientModule`,
`ModuleManager`, item compatibility helpers, explosion/fall/block helpers, and 26.1.2 Minecraft mapped sources.
The live module registry has `ModuleAutoCrystal` but no `ModuleCrystalAura` or `ModuleNoFall`.

### Mixed behavior removed

- Removed `Totem.TotemGuard` / `GrimStop`, `TicksDelay`, post-pop timestamps/indexes, randomized pick/place/return
  delays, and its hidden zero-delay constraints.
- Removed Offhand's `guardedTotemSwap`, guarded stages, guarded refill functions, inventory-open holds, and all
  post-pop swap staging.
- Removed the Offhand incoming `ClientboundEntityEventPacket` / status-35 handler and `TOTEM_USE_STATUS` constant.
- Removed top-level `HP`, `ElytraHP`, `GoldenHearts`, flat `Safety`, `SafeHealthThreshold`, and `Delay` settings.
- Removed the forced-zero `OffhandInternal` constraints and the forced false `SendDirectly` constant.

### Restored 1.21.4 behavior and settings

- Restored visible default `PlayerInventoryConstraints`: StartDelay `1..2`, ClickDelay `2..4`, CloseDelay `1..2`,
  MissChance `0`, and configurable requirements.
- Restored top-level `SwitchMode`, `Cycle` (`H`), and `SwitchDelay` (`0`, `0..500 ms`). `Mode.getDelay()` now uses
  the general delay; Totem overrides it with `Totem.SwitchDelay`.
- Preserved `Smart`, `Switch`, `PickUp`, and `Automatic` switch methods. The single clean default registration is
  `Automatic` when ViaFabricPlus is present, otherwise `Switch`, matching the old effective default.
- Restored Gapple defaults/hierarchy: `Gapple` true; `GappleBind`; `WhileHoldingSword` true; nested
  `OnlyWhileKillAura` true. Automatic Gapple only equips under that exact sword/KillAura predicate, while binds,
  cycle, and static mode remain available.
- Restored Crystal defaults: `Crystal` true, `OnlyWhileCrystalAura` false, `WhenNoTotems` true, and `CrystalBind`.
  Totem fallback retains Crystal's normal main-inventory-first lookup.
- Restored StrengthPotion defaults: disabled; `OnlyWhileHoldingSword` true; `OnlyWhileKillAura` true; `StrengthBind`.
  It recognizes only normal `POTION` stacks containing Strength, does not cycle normally, and applies the original
  KillAura/sword/effect gates.
- Restored Totem config: `SwitchDelay` `0`, `SwitchBackDelay` `40`; `Health` true with integer `HealthThreshold`
  `14`; nested `Safety` true / `SafeHealthThreshold` `10`; `SubtractCalculatedDamage` false;
  entity prediction true; block prediction false; fall prediction true / `IgnoreElytra` false; `MissingArmor` true;
  `SwitchBack` true; `SendDirectly` false.
- Restored absorption-inclusive health, hole/burrow Safety threshold, missing-armor early return, lethal-only
  damage mode when subtraction is disabled, entity/bed/anchor/fall prediction order, exact-slot SwitchBack, and
  optional direct Totem sends only.
- Restored the enum order and priority `TOTEM`, `STRENGTH`, `GAPPLE`, `CRYSTAL`, `BACK`, `NONE`, including direct
  bind toggles, cycle behavior, initial static-mode selection, priority-100 scheduling, and ordinary scheduler path.

### Required target adaptations

- Yarn item/packet APIs map to `mainHandItem` / `offhandItem`, `DataComponents.POTION_CONTENTS`,
  `PotionContents.EMPTY`, `MobEffects.STRENGTH`, `ServerboundSetCarriedItemPacket`,
  `ServerboundPlayerActionPacket`, `BlockPos.ZERO`, and `Direction.DOWN`.
- Current Wraith compatibility extensions retain the source's potion effect, status-effect, and packet-send behavior.
- 26.1.2 client entities are iterated through `world.entitiesForRendering()`; its mapped implementation returns the
  client entity collection. Bed/anchor dimension checks use the current `Level.OVERWORLD` / `Level.NETHER` API.
- Because no compiled `CrystalAura` or `NoFall` exists, a narrow case-insensitive exact module-name-or-alias lookup
  is used. It cannot match `AutoCrystal`, `CrystalOptimizer`, `LegitHelper`, or arbitrary names containing
  "Crystal". Thus `OnlyWhileCrystalAura=true` disables Crystal cycling when the exact module is absent, while
  missing NoFall leaves normal fall prediction active.

### Files changed

- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/player/offhand/ModuleOffhand.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/player/offhand/Totem.kt`

No module registration, localization, AutoCrystal, CrystalOptimizer, LegitHelper, KillAura, inventory-manager, or
other module source was changed.

### Static, build, install, and runtime status

- `git diff --check`: passed.
- Source scans found no Offhand/Totem `TotemGuard`, `GrimStop`, guarded-stage, status-35 packet handler,
  `GoldenHearts`, `ElytraHP`, hidden zero-delay constraints, or legacy HP/Delay settings.
- Registration remains exactly once in `ModuleManager`; JAR inspection found `ModuleOffhand` and `Totem`, but no
  `TotemGuard`, `GuardedTotemSwap`, or guarded-stage class/packet handler.
- Java: OpenJDK `25.0.3` (2026-04-21).
- Build command: `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt`.
- Result: **BUILD SUCCESSFUL** in `2m 8s`.
- Built JAR: `build/libs/liquidbounce-0.31.2.jar`, SHA-256
  `24edf735435752c50cffef22b25cfa046459b2f57bfb01b4303a837a173c762d`.
- Installed JAR has the same SHA-256 at
  `/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar`. The prior installed JAR
  was preserved as `liquidbounce-0.31.2.jar.bak-20260716-offhand-restore` with SHA-256
  `4128bc77d00b5cd25d7ed87073488f8903ef455ab483c170740b48ecfaac85c3`.
- FCL runtime is **not verified**. Android denied package-resolution access to this Termux user, and the direct
  `com.tungsten.fcl` launcher intent returned `Activity not started, unable to resolve Intent`. No disabled/enabled
  launch, configuration UI, world, Totem, or switch-mode runtime claim is made.

### Commit / push

Feature commit: `2d46a7e01` (`refactor(player): restore 1.21.4 Offhand`).

Branch: `main`. Remote: `origin https://github.com/Duong1234vi0/Wraith-0.1.git`.

Push result: successful; `origin/main` advanced from `00e2e519b` to `2d46a7e01`.

This supersedes the mixed multi-client Offhand implementation. The mixed current Offhand implementation was replaced
with the Minecraft 1.21.4 behavior; no second Offhand module was added.

## Offhand vanilla-ordered Totem transaction and Grim MultiActions fix (2026-07-16, Codex)

### Requested configuration and reproduction

- Requested configuration: `SwitchMode=Automatic`, `SwitchDelay=0`, all inventory constraints `0..0` with no
  requirements, Totem `SwitchDelay=0`, `SwitchBackDelay=0`, `HealthThreshold=20`, `SwitchBack=false`, and
  `SendDirectly=true`.
- Requested repro: hold a Totem in Offhand, place Obsidian plus an End Crystal, sprint with movement input, pop the
  Totem in the explosion, and let Offhand equip the replacement.

### Grim findings and root cause

- `MultiActionsC` rejects a player-inventory `ServerboundContainerClickPacket` while the server believes the player
  is sprinting, under the old sneaking condition, or while modern `knownInput.moving()` reports forward, backward,
  left, right, or jump. Local velocity changes and a sprint packet alone therefore do not satisfy the check.
- `MultiActionsD` applies the same sprint/input predicate to `ServerboundContainerClosePacket`.
- `PacketOrderE/F/G/K` and `Post` additionally make same-tick held-slot, offhand-swap, use/attack/dig, inventory,
  close, and client-tick-end ordering relevant.
- On 26.1.2, `Automatic` resolves to `Switch`, which creates a container-0 `ClickInventoryAction.performSwap`.
  Previously `Totem.SendDirectly` performed it immediately, bypassing constraints, normal scheduling, and
  `NoMovement`.
- The old InventoryManager policy marked every container-0 click as server-side inventory open and later sent a
  synthetic close. Thus a sprinting/moving direct replacement emitted click then close while the server still knew
  moving/sprinting input, which is the C then D flag sequence.

### Changed transaction ordering

- Added `InventoryActionMovementGate` with `IDLE`, waiting-for-stationary-input, ready, executing,
  waiting-for-close, and next-tick-release states. It records request/source/expected item/request tick, observed
  server input, sprint-stop, expected click(s), Smart offhand swap, execution, and close evidence.
- Reused the proven `InventoryMove.STOP_ON_ACTION` idea without depending on that module: at final-decision input
  priority it clears directional/jump/sneak and clears INPUT-source sprint. Minecraft then emits its own empty
  player-input and one normal sprint-stop update; no manual duplicate sprint packet is sent.
- The gate waits for outgoing 26.1.2 `ServerboundPlayerInputPacket`, `STOP_SPRINTING` when needed, and
  `ServerboundClientTickEndPacket`, then permits the Offhand packet only on the following client tick. It holds
  suppression through all tracked click/swap/held-slot packets and any required close, then releases on a later
  tick.
- Direct Totem sends still bypass unrelated inventory queues and configured scheduler delays, but no longer bypass
  the movement gate. Scheduled sends are not queued until ready and retain configured Start/Click/Close delays.
  The source slot is revalidated until the first expected click; its own subsequent pickup mutation cannot cancel
  the atomic chain.
- Attack/use/interact/dig/drop/pick/held-slot/offhand-swap conflicts are observed and defer only the Offhand action
  to a clean tick. A tick-based bounded safety timeout reports and releases without sending an unsafe stale action.
  World change, disconnect, death, and module disable clear the gate and permit state.

### Container-close audit

- 26.1.2 `MultiPlayerGameMode.handleContainerInput` only verifies the current player menu id is `0`; it does not
  create an inventory screen/session. `AbstractContainerScreen.onClose` is the vanilla source of a close packet.
  The direct detached player-inventory click therefore does not need a synthetic `ServerboundContainerClosePacket(0)`.
- Added a signature-bound, one-shot `InventoryManager.permitDetachedPlayerInventoryClick`. It is consumed only by
  the matching same-tick container-0 click while no real inventory/server-controlled/ViaFabricPlus legacy inventory
  is active. Normal screen/server inventory tracking and legitimate closes remain unchanged.

### Files and diagnostics

- Changed: `ModuleOffhand.kt`, `Totem.kt`, `InventoryManager.kt`, and new
  `utils/inventory/InventoryActionMovementGate.kt`.
- Restored 1.21.4 Offhand settings, mode order, Totem health/prediction/SwitchBack behavior, Gapple/Crystal/Strength
  behavior, search priorities, and switch choices are preserved. No `GrimStop`, `TotemGuard`, random post-pop delay,
  post-pop listener, or second module was added.
- Retained bounded test diagnostics under `[OffhandTxnDiag]`; it logs request/input/sprint states, stationary packet
  evidence, ready/click/swap/close/completion/release, and invalidation. The allowance resets on world/disconnect.

### Verification status

- `git diff --check` passed.
- Final build command: `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt`.
- Final result: `BUILD SUCCESSFUL` in `1m 45s`.
- Final built and installed SHA-256: `ad40acbc0971bb105bdda431b1e8508185aba76e76c5b8c56560ee011b44b107` for
  `build/libs/liquidbounce-0.31.2.jar` and
  `/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar`.
  The immediately preceding installed JAR was saved as
  `liquidbounce-0.31.2.jar.bak-20260716-offhand-txn-5`.
- `unzip -t` passed for both JARs. FCL was then launched after the copy completed and logged
  `LiquidBounce has been successfully initialized` at `14:23:24`; this verifies the final artifact loads, not the
  gameplay reproduction.
- The final JAR performed a live moving/sprinting `Automatic` / `SendDirectly=false` replacement at ticks 1818--1820:
  the request began with outgoing forward+sprint input and same-tick attack/place/use conflicts; it then logged an
  empty `ServerboundPlayerInputPacket`, normal `STOP_SPRINTING`, client-tick-end confirmation, its expected
  container-0 `SWAP` click on tick 1819 with `inventoryOpen=false`, and movement release on tick 1820. No
  Grim `MultiActionsC`, `MultiActionsD`, PacketOrder, Post, or timeout marker appeared in that final log. This is strong
  packet-order evidence, but it did not prove a crystal pop or direct-send mode.
- Exact `SendDirectly=true` sprinting crystal-pop, sideways/jump variants, Smart/Switch/PickUp variants, and
  failure/reset interactions remain runtime-pending because this Termux session has no usable Minecraft input/UI
  automation. The loaded FCL `LiquidBounce/modules.json` currently matches the supplied zero-delay Automatic setup
  but has `Totem.SendDirectly=false`; it was not changed during this work. No C/D, PacketOrder/Post, or
  movement-release pass is claimed for the requested exact direct-send reproduction.

### Commit / push status

- Branch: `main`; remote: `origin https://github.com/Duong1234vi0/Wraith-0.1.git`.
- No fix commit and no push were made. The required exact reproduction has not passed, so the requested
  `fix(player): order Offhand swaps like vanilla` commit is intentionally deferred.

## Argon MacroAnchor strict behavior port (2026-07-16, Codex)

### Request and authority

- Requested a strict observable-behavior port of Argon `AnchorMacro` into Wraith as the separately toggleable
  Combat module `MacroAnchor` (visible identity `MacroAnchor`; source-visible name remains `Anchor Macro`). It is a
  crosshair-driven charge/detonation macro, not an AnchorAura and not an automatic anchor-placement module.
- Behavior authority: Argon for Minecraft `1.21.11`, Yarn `1.21.11+build.4`, Fabric Loader `0.18.4`. Integration
  authority: live Wraith targeting Minecraft `26.1.2` with official mappings. `Agent1.md` history was treated as
  chronology only.
- Read the required port guidance plus complete Argon `AnchorMacro`, `BlockUtils`, `WorldUtils`, `InventoryUtils`,
  `KeyUtils`, `MathUtils`, `MouseSimulation`, `ItemUseListener`, `MinecraftClientMixin`, and `ModuleManager`.
  `DoubleAnchor` and `Prevent` were inspected only to keep their separate behavior excluded.
- Audited current Wraith `ModuleManager`, `ModuleAutoCrystal`, `ModuleLegitHelper`, game/window events,
  `EventManager`, `MixinMinecraftClient`, inventory slots, mapping extensions, `InputTracker`, `MixinMouse`, and
  target-version `MultiPlayerGameMode.useItemOn` bytecode. Later AutoCrystal, LegitHelper, ItemUseEvent, hotbar,
  input, and Grim/packet-order history entries were also read.

### Added module and exact surface

- Added `ModuleMacroAnchor` under Combat and registered it once in `ModuleManager`; added its English description
  and the accurate WhileUse description.
- Custom setting order/defaults: `WhileUse=true`, `StopOnKill=false`, `ClickSimulation=false`, `PlaceChance=100`
  (`0..100`), `SwitchDelay=0` ticks (`0..20`), `SwitchChance=100` (`0..100`), `GlowstoneDelay=0` ticks (`0..20`),
  `GlowstoneChance=100` (`0..100`), `ExplodeDelay=0` ticks (`0..20`), `ExplodeChance=100` (`0..100`),
  `ExplodeSlot=1` (`1..9`), `OnlyOwn=false`, and `OnlyCharge=false`.
- State is exactly integer `switchClock`, `glowstoneClock`, `explodeClock`, plus a hash-set `ownedAnchors`.
  Enable resets only clocks; ordinary disable leaves ownership intact. Disconnect, world replacement, and dimension
  replacement reset clocks and clear ownership, including when the module is disabled.

### Ported behavior and preserved quirks

- Uses only `GameTickEvent`; absent player/world and any open screen return without clock changes. Activation is the
  physical GLFW RMB state, not `useKey` or a hold bind. Once an eligible exact crosshair Respawn Anchor passes
  `OnlyOwn`, it sets only the logical use key false to suppress duplicate vanilla held-use processing.
- `WhileUse` pauses only for physical RMB plus Food data component or a `ShieldItem` in either held hand.
  `StopOnKill` scans other players only, within squared distance `36.0`, using the current dead/dying state.
- Every Argon chance roll remains `Random.Default.nextInt(1, 100)`: 1 through 99, upper bound exclusive, and
  success is `<=` the configured value. Thus 0 never succeeds while 99 and 100 always succeed.
- `PlaceChance` gates the entire uncharged-anchor switch/charge path. The same `switchClock` is shared by Glowstone
  and explosion-slot switching; all delay gates retain `clock != configuredDelay`, no runtime clamping, and chance
  failures retry while the clock remains at the reached delay.
- Glowstone is selected visibly from the first Hotbar slot in `0..8`; no main-inventory search, Offhand selection,
  SilentHotbar, or swap-back occurs. `useItemOn` synchronizes the visible selected slot itself, so no duplicate
  carried-slot packet is sent.
- Charging and detonation use the original `BlockHitResult`, MainHand direct `useItemOn`, and swing only when the
  resulting `InteractionResult` consumes the action. A charged anchor uses `ExplodeSlot - 1`, even if that slot holds
  any item. `OnlyCharge` still permits that slot switch and explosion-clock reset but performs no charged interaction.
- Owned placement is optimistically recorded through the existing physical `ItemUseEvent` before server confirmation;
  a physical charged-anchor use removes the exact target, while direct macro detonation removes it after the attempt.
  Direct macro interaction intentionally bypasses `ItemUseEvent`, as does Argon's source hook placement.

### Click simulation and compatibility

- Added a small client-thread `MacroAnchorClickSimulation` record adapter: enabled direct interactions record one
  synthetic right press and a release after approximately 35 ms. It never dispatches `MouseButtonEvent`, changes
  GLFW/physical input, feeds module binds, sleeps, creates a thread pool, or starts another use action.
- Wraith currently has no right-click CPS/display consumer (the debug CPS recorder tracks left clicks), so the adapter
  intentionally remains an isolated non-gameplay record with press/release counters. It has no gameplay effect.
- `ModuleLegitHelper` was not changed; enabling both anchor helpers can create competing interactions. No mutual
  exclusion was added. `ModuleAutoCrystal`, Offhand, InventoryMove, and HandMyItems were not modified: AutoCrystal's
  physical ItemUse cancellation remains independent, Offhand does not alter MacroAnchor selection, and normal
  MainHand animation remains available.

### Deliberate exclusions and mappings

- Excluded with source-level evidence: no anchor placement, AnchorAura search/target queue, DoubleAnchor/Prevent
  behavior, rotation, damage or self-damage calculation, friend/health/range/wall checks, Offhand use, silent swap,
  swap-back, refill, air-place packets, movement suppression, Grim timing, render/HUD state, or extra ItemUseEvent
  mixin. The module only checks the exact crosshair block and uses direct vanilla interaction.
- Adapted Yarn names to official 26.1.2: `RespawnAnchorBlock.CHARGE`, `DataComponents.FOOD`, `ShieldItem`,
  `InteractionResult`, `MultiPlayerGameMode.useItemOn`, `BlockPos.relative`, and current `isDeadOrDying` state.
  Existing `MixinMinecraftClient` ItemUseEvent was reused. Target bytecode confirms `useItemOn` calls
  `ensureHasSentCarriedItem()` internally, which is why the port does not send it separately.

### Files and verification

- Changed: `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleMacroAnchor.kt`,
  `src/main/kotlin/net/ccbluex/liquidbounce/utils/input/MacroAnchorClickSimulation.kt`,
  `src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt`, and
  `src/main/resources/resources/liquidbounce/lang/en_us.json`; this appended entry preserves older history.
- `git diff --check` passed. Source/JAR checks found one MacroAnchor class and one registration, one existing
  `ItemUseEvent`, localization entries, no Argon package/classes, no new ItemUseEvent mixin, and no changes to
  `ModuleLegitHelper` or `ModuleAutoCrystal`.
- Java: OpenJDK `25.0.3` (2026-04-21). Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt`.
  Result: `BUILD SUCCESSFUL` in `2m 19s`.
- Built JAR: `build/libs/liquidbounce-0.31.2.jar`, SHA-256
  `7b6cea43a7df5e41ad1dea31e6efd7c2ccf1cd744dc0251a733f26b13e826155`.
  Installed JAR at `/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar` has the
  same SHA-256. Its prior contents were preserved as
  `liquidbounce-0.31.2.jar.bak-20260716-macroanchor` with SHA-256
  `ad40acbc0971bb105bdda431b1e8508185aba76e76c5b8c56560ee011b44b107`.
- FCL runtime gameplay testing has not been performed in this session. No claim is made for charging, detonation,
  OnlyOwn, OnlyCharge, ClickSimulation, settings UI, or conflict parity in FCL. The only remaining non-behavioral
  adaptation is the isolated ClickSimulation record because no right-click CPS consumer currently exists.

### Commit / push status

- No MacroAnchor commit or push was made. Before this port, `Agent1.md` already contained an unrelated uncommitted
  Offhand history entry; staging this file as requested would also stage that unrelated work. To preserve the dirty
  worktree, the MacroAnchor source remains unstaged pending authorization to include the pre-existing Agent1 change
  or to commit the port without its history entry.

## Argon KeyPearl and TriggerBot strict behavior ports (2026-07-16, Codex)

### Request, authority, and evidence reviewed

- Requested two separate strict observable-behavior ports from Argon b1.1 for Minecraft `1.21.11`, Yarn
  `1.21.11+build.4`, and Fabric Loader `0.18.4`: Wraith `KeyPearl` in Misc and Wraith `TriggerBot` in Combat.
  The requested compact Wraith names intentionally differ from Argon's visible `Key Pearl` and `Trigger Bot`.
- Argon behavior authority was read completely from `KeyPearl.java`, `TriggerBot.java`, `InventoryUtils.java`,
  `KeyUtils.java`, `TimerUtils.java`, `WorldUtils.java`, `MouseSimulation.java`, `AttackListener.java`,
  `TickListener.java`, `MinecraftClientMixin.java`, `Friends.java`, `FriendManager.java`, `MinMaxSetting.java`, and
  `ModuleManager.java`. `MouseSimulation` and its call paths were traced before adapting either synthetic-input
  setting.
- Live Wraith API/integration authority reviewed `ModuleManager`, game/entity events and event manager,
  `MixinMinecraftClient`, `MixinClientPlayerInteractionManager`, `ModuleAutoPearl`, `ModuleClickAction`,
  `ModuleMiddleClickAction`, `ModuleAutoWeapon`, `ModuleKillAura`, `ModuleTargets`, `FriendManager`, combat/entity/
  item/inventory/input helpers, and the existing MacroAnchor click recorder. Later relevant Agent1 history was read
  as append-only chronology.
- Target `26.1.2` official bytecode was checked for `MultiPlayerGameMode.useItem`,
  `ensureHasSentCarriedItem`, direct `attack`, and `LivingEntity.getLastHurtMob`. `useItem` performs its own
  carried-slot synchronization; `getLastHurtMob` is the target mapping of the Yarn last-attacked value used by
  Argon's `getAttacking()`.
- Reviewed, but did not change, the possible conflicts: KeyPearl with ClickAction Pearl, MiddleClickAction Pearl,
  and AutoPearl; TriggerBot with KillAura, AutoWeapon, Criticals, and any later click-simulation consumer. No
  automatic mutual exclusion was added.

### Added files, identities, registration, and localization

- Added `features/module/modules/misc/ModuleKeyPearl.kt`: `KeyPearl`, category `MISC`, description
  `Switches to an Ender Pearl and throws it while the activation bind is held.`
- Added `features/module/modules/combat/ModuleTriggerBot.kt`: `TriggerBot`, category `COMBAT`, description
  `Automatically attacks eligible entities under the crosshair.`
- Added isolated `utils/input/TriggerBotClickSimulation.kt`; it is a bounded client-thread synthetic record only and
  does not dispatch Wraith mouse events, alter GLFW state, feed module binds, use an item, or create an attack.
- Registered each module exactly once in `ModuleManager` and added the requested English module descriptions plus
  KeyPearl `SwitchDelay` description. No existing Pearl, combat, or input module was merged or modified.

### KeyPearl parity

- Custom setting order/defaults: held runtime `ActivateKey` bound to unknown/unbound; `Delay=0` ticks (`0..20`);
  `SwitchBack=true`; `SwitchDelay=0` ticks (`0..20`), described as the post-throw return delay. Keyboard, mouse,
  keysym, and scancode binds use current `InputBind`; unbound is explicitly inactive rather than AutoCrystal's
  unbound-always-active convention.
- Preserved `active`, `hasActivated`, `clock`, `previousSlot`, and `switchClock`, including enable reset and safe
  reset on disable, disconnect, world replacement, and local-player replacement. An open screen returns before
  activation, clock advancement, slot movement, or active-cycle reset.
- Every eligible held tick sets `active=true` without an edge requirement. A completed cycle resets, so continuing to
  hold the activation bind repeats the throw cycle; release after activation does not abort the already active cycle.
- Captures the current selected slot once, visibly searches only Hotbar indexes `0..8` for the first Ender Pearl,
  and selects that index. It does not search inventory/container/Offhand, rotate, refill, use SilentHotbar, or check
  cooldowns. The missing-Pearl return value is deliberately ignored: if no Hotbar Pearl exists, the still-selected
  MainHand item is used. This dangerous source quirk is intentionally retained.
- Delay is an eligible-tick integer clock: while `clock < Delay`, increment and return. Then direct MainHand
  `useItem` is called once; only an accepted result swings, while `hasActivated` advances even for a rejected result.
  The `26.1.2` interaction method synchronizes a changed carried slot itself, avoiding a duplicate packet.
- `SwitchBack=false` resets while leaving the selected throw slot. Otherwise SwitchDelay advances as an eligible-tick
  clock, visibly restores the stored index, invokes the one required carried-slot synchronization, and resets.

### TriggerBot parity

- Exact custom setting order/defaults: `WorkInScreen=false`, `WhileUse=false`, `OnLeftClick=false`,
  `AllItems=false`, `SwordDelay=540..550 ms`, `AxeDelay=780..800 ms`, `CheckShield=false`,
  `WhileAscending=false`, `SamePlayer=false`, `OnlyCritSword=false`, `OnlyCritAxe=false`, `SwingHand=true`,
  `ClickSimulation=false`, `StrayBypass=false`, `AllEntities=false`, `UseShield=false`, and `ShieldTime=350 ms`
  (`100..1000`). Integer settings retain the source's unit/ranges and unit step.
- Uses one monotonic `System.nanoTime()` timer. It samples delay ranges on enable without resetting the timer, and
  uses explicit upper-exclusive sampling: Sword possible values are `540..549`, Axe values are `780..799`, and equal
  endpoints return that exact value. A successful direct branch resamples only its matching delay then resets the
  shared timer.
- Tick order is screen gate, narrow friend gate, one MainHand stack read, physical raw-GLFW OnLeftClick gate,
  physical RMB plus Offhand Food/Shield WhileUse gate, exact ascending/fall-distance gate, and current crosshair
  `EntityHitResult` only. It does not scan, rotate, custom-raycast, or attack through blocks.
- Friend adaptation is deliberately narrow: a crosshair Player in `FriendManager` is skipped only while global Combat
  Targets does not explicitly include `FRIENDS`; full Wraith `shouldBeAttacked()` filtering is not applied to every
  entity. This maps Argon's unavailable `Friends.AntiAttack` toggle without altering `AllEntities`, `StrayBypass`, or
  non-player behavior.
- Branch order remains Sword, then Axe when AllItems is off; AllItems uses the Sword branch for every item. Sword and
  AllItems use SwordDelay and OnlyCritSword; Axe uses AxeDelay and OnlyCritAxe. Item classification uses Wraith's
  exact sword/axe tags, not damage heuristics.
- Eligibility is Player, or `StrayBypass` plus target `Zombie` class/subclasses, or `AllEntities`; there is no
  added alive/living filter. The misnamed StrayBypass therefore retains its generic Zombie quirk.
- SamePlayer uses the actual vanilla `player.lastHurtMob` / `getLastHurtMob` mapping. It is checked in Sword and
  AllItems, deliberately absent from Axe. Shield skipping applies only to Player targets when `CheckShield`,
  blocking, and `wouldBlockHit(localPlayer)` are all true; this preserves Argon's shield-facing direction. Critical
  settings only require `fallDistance > 0`, with no added vanilla-critical predicate.
- A ready branch calls direct `MultiPlayerGameMode.attack(player, crosshairEntity)` once, optionally swings MainHand,
  records exactly one non-gameplay synthetic left click when configured, resamples, and resets. It does not use the
  higher-level combat helper, custom packets, KeepSprint, rotations, or a second attack.
- The enabled module's default-priority `AttackInputEvent` handler cancels normal physical Minecraft attack input
  whenever raw GLFW LMB is not held, independent of OnLeftClick. Direct interaction-manager attacks bypass that
  input event. Wraith's existing `AttackEntityEvent` mixin still observes/can cancel direct interaction-manager
  attacks; retaining that project-wide safety hook is the explicit integration adaptation.
- ClickSimulation records a left press/release approximately 35 ms apart only. Argon's later synthetic-button
  consumption side effect was omitted because this recorder creates no synthetic physical input marker and current
  Wraith has no proven left-click CPS/gameplay consumer; no additional Minecraft mixin or input event was added.
- UseShield remains source-equivalent synthetic behavior, not invented AutoBlock: a ready real-Sword branch records a
  synthetic RMB release only for actual Offhand Shield plus current blocking; cooldown branches for Sword, Axe, and
  AllItems record an RMB click for ShieldTime. It never starts/release vanilla shield use or sends a second action.

### Mapping and packet adaptations

- Yarn `interactItem`/`attackEntity`, item components, Zombie class, selected slot, and last-attacked player state
  were translated to official 26.1.2 APIs only. Existing `InteractionResult.consumesAction` is used for KeyPearl's
  accepted-result swing.
- KeyPearl relies on target `useItem`'s internal `ensureHasSentCarriedItem` call after visible selection and calls
  the same sync once only after a visible switch-back. TriggerBot performs one direct attack per ready cycle and at
  most its configured manual swing; synthetic records emit no packets or gameplay action.
- Lifecycle cleanup and the narrow friend policy are Wraith singleton/global-target adaptations. No anti-cheat
  timing, packet ordering, movement suppression, silent selection, or extra input mixin was invented.

### Verification and runtime status

- `git diff --check` passed. Static searches confirmed one module object and one registration for KeyPearl and
  TriggerBot, correct categories/settings, Hotbar-only Pearl selection, no target scan/rotation, SamePlayer absent
  from Axe, AllItems SwordDelay behavior, generic Zombie bypass, and isolated ClickSimulation/UseShield code.
- Java: OpenJDK `25.0.3` (2026-04-21). Build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt`.
  Result: `BUILD SUCCESSFUL` in `2m 1s`.
- Built JAR: `build/libs/liquidbounce-0.31.2.jar`, SHA-256
  `fe1650f582c35111ee0870f0be9aaa54c1017ae153e611ac1da01678a22da8f9`. JAR audit found exactly one KeyPearl,
  TriggerBot, AttackInputEvent, MixinMinecraftClient, and MixinClientPlayerInteractionManager class, one
  ModuleManager bytecode reference for each module, packaged localization, and no `dev/lvstrng/argon` package.
- Installed the same SHA-256 to
  `/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar`. The prior JAR was
  preserved without overwriting another backup as
  `liquidbounce-0.31.2.jar.bak-20260716-argon-keypearl-triggerbot`, SHA-256
  `7b6cea43a7df5e41ad1dea31e6efd7c2ccf1cd744dc0251a733f26b13e826155`.
- FCL gameplay runtime is pending: this Termux session cannot resolve an FCL package/activity or provide Minecraft
  UI/input automation. No KeyPearl hold/delay/missing-Pearl/lifecycle or TriggerBot weapon/friend/shield/input/
  ClickSimulation/UseShield gameplay claim is made. The required controlled in-game matrix remains the remaining
  verification work.

### Commit and push status

- Accepted prior MacroAnchor work was preserved first in separate commit `9262845a`
  (`feat(combat): port Argon MacroAnchor`).
- This append is staged with the KeyPearl/TriggerBot source commit; its immutable commit hash and push result are
  recorded in the completion report after the commit is created. No uploaded archive, extracted Argon tree, log, or
  backup JAR is staged.

## KeyPearl and CrystalBase PacketOrderE action-order fix (2026-07-16, Codex)

### Reported reproduction and Grim authority

- The reported server-visible action-tick analogy was precise: selecting an item before its action is valid, but a
  second carried-slot change is a new action phase and must not be placed behind a right-click/attack before the
  server sees its natural tick boundary. Reproductions were KeyPearl with `Delay=0`, `SwitchBack=true`, and
  `SwitchDelay=0`, plus CrystalBase with zero Switch/Base/Place delays.
- Read Grim 2.0 `PacketOrderE`, `PacketOrderProcessor`, and `Check` completely. `PacketOrderE` handles
  `HELD_ITEM_CHANGE` and flags when any current action flag is true. For these reports, `rightClicking` is
  `placing || using || interacting`; therefore a carried-slot packet after `USE_ITEM`/`USE_ITEM_ON` is invalid while
  that state is still live.
- Grim clears these action flags only at a recognized packet boundary: a movement/flying tick packet, or the
  `CLIENT_TICK_END` fallback on 1.21.2+. The target 26.1.2 bytecode confirms that Minecraft naturally emits
  `ServerboundClientTickEndPacket`; no packet was fabricated, cancelled, or reordered by this work.

### Root causes retained as evidence

- KeyPearl previously produced `HELD_ITEM_CHANGE(Pearl) -> USE_ITEM -> HELD_ITEM_CHANGE(previous) ->
  CLIENT_TICK_END`. The first synchronization is the target `MultiPlayerGameMode.useItem` pre-action sync and is
  valid. The immediate restoration is the invalid post-right-click packet.
- CrystalBase could execute both independent source branches in one invocation: `HELD_ITEM_CHANGE(Obsidian) ->
  USE_ITEM_ON(place Obsidian) -> HELD_ITEM_CHANGE(End Crystal) -> CLIENT_TICK_END`. The second slot change is invalid
  because the placement kept Grim `rightClicking=true`.
- AutoCrystal AntiWeakness also had the analogous latent attack sequence: `HELD_ITEM_CHANGE(tool) -> ATTACK ->
  HELD_ITEM_CHANGE(previous) -> CLIENT_TICK_END`. Its final restoration can produce PacketOrderE with
  `attacking=true`.

### Narrow packet-boundary implementation

- Added `utils/network/ActionTickBoundary.kt`. It monotonically increments a completed-action serial only when the
  existing outgoing-packet event observes a natural `ServerboundClientTickEndPacket`; it has no wall clock,
  background thread, packet send, cancellation, movement suppression, global slot interception, or container logic.
- The utility records module-owned expected carried-slot/action packets only while the caller synchronously performs
  them, validates an attempted post-action slot change before local selection is mutated, and clears expectations on
  tick boundary/world change/disconnect. It logs an error and returns no-send permission if an owned action would be
  followed by a held-slot packet too early.
- KeyPearl records a post-use serial regardless of `useItem` acceptance, then waits for a real boundary before
  beginning the existing SwitchDelay clock. `SwitchDelay=0` restores on the first eligible next action tick;
  `SwitchDelay=N` waits N additional eligible ticks. `SwitchBack=false` still resets immediately because it sends no
  post-use restore. Hold-to-repeat and release-after-activation remain cycle-safe.
- CrystalBase records a barrier only after it actually performs the Obsidian placement interaction, returns from that
  action phase, and refuses repeated `runCrystalTick` calls until the serial passes. Crystal selection delay does not
  tick during that barrier. Once it passes, End Crystal selection and placement may still share the new action tick.
  Existing Obsidian/Bedrock bases retain immediate `select Crystal -> USE_ITEM_ON`; charged Respawn Anchor handling
  gains no barrier without an emitted placement action.
- AntiWeakness stores the previous Hotbar slot, boundary serial, and exact player/world identities. It revalidates
  the range, restores only after the real boundary, then returns from that AutoCrystal invocation so no second
  action can follow restoration in the same action tick. Disable, disconnect, world change, and player replacement
  clear all pending barrier/restoration state.
- The only strict-port deviation is the mandatory server packet boundary between a post-action restore/phase switch;
  it is required by Grim's documented ordering semantics. Normal V-core clocks still advance during AntiWeakness's
  no-action restoration tick, and valid pre-action visible switching remains immediate.

### Bounded diagnostics

- Test-build diagnostics use `[ActionOrderDiag]`, capped at 32 cycles and 512 informational records per world/session.
  They include client tick, boundary serial, slots, KeyPearl SwitchDelay clock, and module-owned packet sequence/type
  evidence for `ServerboundSetCarriedItemPacket`, `ServerboundUseItemPacket`, `ServerboundUseItemOnPacket`, target
  26.1.2 `ServerboundAttackPacket` with `ATTACK`, and `ServerboundClientTickEndPacket`.
- The retained logs name: KeyPearl pearl selection/use/boundary/restore/reset; CrystalBase Obsidian selection/place,
  boundary, End Crystal selection/place; and AntiWeakness tool selection/attack/wait/restore. They remain installed
  for the requested FCL reproduction rather than being removed after compilation.

### Files, build, install, and runtime status

- Changed: `src/main/kotlin/net/ccbluex/liquidbounce/utils/network/ActionTickBoundary.kt`,
  `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/misc/ModuleKeyPearl.kt`,
  `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleAutoCrystal.kt`, and this append-only
  `Agent1.md` entry. TriggerBot and MacroAnchor source behavior were not changed. No use of
  `InventoryActionMovementGate`, movement/sprint suppression, manual tick-end packet, or random delay was added.
- `git diff --check` passed. The requested clean command
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt`
  completed `BUILD SUCCESSFUL` in `1m 47s`.
- Built `build/libs/liquidbounce-0.31.2.jar` SHA-256:
  `8a839ab70df74fad124f95e36d4c407410cc7592a935daa4af8621dd9239e62d`. It was installed to
  `/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar` with the identical
  SHA-256. The prior JAR is preserved as `liquidbounce-0.31.2.jar.bak-20260716-action-order`, SHA-256
  `fe1650f582c35111ee0870f0be9aaa54c1017ae153e611ac1da01678a22da8f9`.
- FCL runtime reproduction has not been run from this Termux-only session. KeyPearl exact hold/delay cases,
  CrystalBase zero-delay/existing-base/repeated-call cases, AntiWeakness, and disable/disconnect/world/player
  lifecycle cases remain pending user gameplay verification. Consequently there is no claim yet that PacketOrderE,
  Post/BadPackets slot flags, or any other Grim flags are absent; the installed diagnostics are the evidence to
  collect next.

### Commit / push status

- Accepted Offhand transaction work was preserved separately in local commit `261aab70`
  (`fix(player): order Offhand swaps like vanilla`). Its earlier append-only history already existed and was not
  rewritten or duplicated.
- The current action-order fix is intentionally uncommitted and unpushed until both exact KeyPearl and CrystalBase
  reproductions complete without PacketOrderE, as required. Current branch: `main`; remote: `origin`
  (`https://github.com/Duong1234vi0/Wraith-0.1.git`).

## AutoCrystal plus CrystalOptimizer MultiActionsF action split (2026-07-16, Codex)

### User report and Grim authority

- Reported verbose: `[GrimAC] failed MultiActionsF: place` with `AutoCrystal` and `CrystalOptimizer` enabled.
- Read the live Grim 2.0 `MultiActionsF.java` completely, plus `MultiActionsE`, `PacketOrderB`, `PacketOrderE`,
  `PacketOrderJ`, `Post`, `MultiPlace`, `Check.isTickPacket`, and the relevant packet-order processor paths.
  `MultiActionsF` sets `entity=true` for `INTERACT_ENTITY`, `ATTACK`, or `SPECTATE_ENTITY`; its block-place handler
  emits `action=place` only when that entity state is already true. It clears both state flags at a recognized tick
  packet. On 1.21.2+, Grim recognizes `CLIENT_TICK_END` as the non-movement tick fallback when appropriate.
- Therefore `action=place` proves the server observed `ATTACK/INTERACT_ENTITY -> block placement` before a tick
  boundary, not an inventory, sprint, movement, or held-item-order violation.
- Target 26.1.2 bytecode was checked: `MultiPlayerGameMode.attack` calls `ensureHasSentCarriedItem`, sends
  `ServerboundAttackPacket`, then performs the local attack; `useItemOn` sends a sequenced
  `ServerboundUseItemOnPacket`; `Minecraft.tick` naturally sends `ServerboundClientTickEndPacket.INSTANCE` when
  connected and not paused. No packet was fabricated by this work.

### Confirmed combined cause

- V-core `AutoCrystal` intentionally handles the crosshair entity before the block target in one invocation.
  Live Wraith preserved that order.
- On the first direct crystal attack, live `CrystalOptimizer` marks the attacked crystal and nearby crystals
  inhibited, then removes the attacked crystal client-side. It does **not** directly send the later placement packet.
- After that immediate local inhibition/removal, AutoCrystal's existing optimizer fallback can raycast the block
  behind the crosshair `EntityHitResult`; its existing placement collision rule ignores inhibited EndCrystals.
  That lets AutoCrystal send `ServerboundUseItemOnPacket` in the same action tick after the attack.
- Without CrystalOptimizer, `shouldIgnoreForPlacement` is false for the still-crosshair crystal, so no fallback
  block target is resolved after the break. This is why the reported flag occurs only with the optimizer enabled.

### Changed

- Refactored AutoCrystal entity handling to return explicit `NO_TARGET`, `NO_ACTION`,
  `INHIBITED_WITHOUT_PACKET`, or `ATTACK_ATTEMPTED` outcomes.
- Only `ATTACK_ATTEMPTED` arms a narrow pending entity-action boundary using the existing
  `ActionTickBoundary.currentSerial()` / `hasPassed()` tracker. Before a real observed
  `ServerboundClientTickEndPacket` advances that serial, AutoCrystal returns before CrystalBase, entity, or block
  actions. The next phase may then follow the original entity-before-block priority.
- An already inhibited crystal returns `INHIBITED_WITHOUT_PACKET`; it sends no entity action in that call and may
  still use the existing fallback raycast/placement in the current action tick.
- The existing CrystalBase post-Obsidian and AntiWeakness post-attack held-slot barriers remain intact. In the
  AntiWeakness case, the entity boundary and existing safe restore ordering coexist; the accepted restore path still
  returns before a later action phase.
- Added bounded `[CrystalSequenceDiag]` action-scoped diagnostics to the existing shared boundary observer, including
  AutoCrystal entity result/attempt, optimizer inhibition/removal, pending/passed boundary, actual owned
  `ATTACK`/`USE_ITEM_ON`, and a preflight warning/suppression if a future path would recreate
  `ATTACK -> USE_ITEM_ON` before `CLIENT_TICK_END`.
- `CrystalOptimizer` behavior is unchanged: Blatant setting, inhibition TTL, 12-block chain marking, explosion
  handling, client-side removal, duplicate attack cancellation, placement-ignore behavior, and non-Blatant path
  remain as before. No configured delay, movement suppression, `InventoryActionMovementGate`, manual tick-end
  packet, or optimizer disable was added.

### Files and verification

- Changed: `features/module/modules/combat/ModuleAutoCrystal.kt`,
  `features/module/modules/combat/ModuleCrystalOptimizer.kt`,
  `utils/network/ActionTickBoundary.kt`, and this append-only entry.
- `git diff --check` passed. Clean build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt`
  completed **BUILD SUCCESSFUL** in `1m 54s`.
- Built JAR: `build/libs/liquidbounce-0.31.2.jar`, SHA-256
  `a85556f87a05ede8ca5754c005804eae0353350fd2905746661475082d03de18`.
  It was installed with the identical SHA-256 to
  `/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar`.
  The previously accepted action-order JAR (`8a839ab70df74fad124f95e36d4c407410cc7592a935daa4af8621dd9239e62d`)
  is preserved as `liquidbounce-0.31.2.jar.bak-20260716-multiactionsf-pretest`.

### Runtime and Git status

- Exact optimizer-enabled FCL reproduction is pending. Until it completes without `MultiActionsF: place` or
  `MultiActionsF: entity`, there is no claim that this issue is fixed and no claim about PacketOrderE, PacketOrderJ,
  Post, MultiPlace, or other Grim flags.
- The accepted prior post-action hotbar-switch fix was committed separately as `f1ad7141`
  (`fix(modules): order post-action hotbar switches`) and pushed to `origin/main` before this work.
- This MultiActionsF diagnostic fix is intentionally uncommitted and unpushed pending the requested FCL test. The
  eventual commit must contain only this narrow source change and this entry, using
  `fix(combat): separate crystal attack and placement`.

## AutoCrystal Silent HeadBob base-anchored targeting (2026-07-16, Codex)

### User request, definition, and authority

- User requested one optional `HeadBob` Boolean for the live Minecraft 26.1.2 crosshair-driven AutoCrystal:
  `false` by default, immediately after `BreakDelay`, with the description “Silently targets the crystal above the
  looked-at base without requiring the visible crosshair to touch the crystal.” No range, delay, speed, rotation,
  wall, FOV, mode, bind, aura, damage, mixin, or camera setting was added.
- Manual Crystal head bob is the visible sequence `look at Crystal hitbox -> attack -> look at Obsidian/Bedrock ->
  place`. Silent HeadBob preserves the visible camera/crosshair on the exact Obsidian/Bedrock base while making the
  necessary server-facing silent rotation to the Crystal, attacking it, crossing a real action boundary, silently
  rotating back to the actual base hit point/face, and only then placing.
- No proprietary paid-client source was supplied or used. This is a specification-based Wraith integration; it does
  not claim byte-for-byte or implementation parity with any paid client. The user behavior specification is feature
  authority, live Wraith is integration authority, target bytecode is packet/API authority, and supplied Grim is
  validation authority.

### Evidence and rejected shortcut

- Read the mandatory Skill workflow documents, latest relevant Agent1 history, complete live AutoCrystal,
  CrystalOptimizer, ActionTickBoundary, RotationManager/RotationTarget/RotationsConfigurable/RotationFinding,
  CrystalDestroyFeature, and supplied Grim `Reach`, `Hitboxes`, `MultiActionsF`, `PacketOrderE`, `RotationPlace`,
  `Post`, and related checks.
- Target 26.1.2 bytecode confirmed `MultiPlayerGameMode.attack`, `useItemOn`, LocalPlayer movement rotation packets,
  natural `ServerboundClientTickEndPacket`, EndCrystal placement occupancy, and live entity/block interaction-range
  APIs. RotationManager's `actualServerRotation` tracks an un-cancelled sent rotation packet; desired
  `currentRotation` is not accepted as proof.
- A direct `find crystal above base -> attack` shortcut was rejected. Grim Hitboxes checks EndCrystal interaction
  against current/previous server look vectors, and Grim Reach checks the actual interaction geometry. HeadBob gates
  every virtual Crystal attack on Wraith `facingEnemy` with `actualServerRotation`, target-version entity range, and
  a zero-wall visible `raytraceBox` point. Thus it never attacks merely because a Crystal was found nearby.

### Implementation

- Added `HeadBob` only to `ModuleAutoCrystal` and its English description to `en_us.json`. With HeadBob disabled,
  direct crosshair entity handling, looked-block placement, CrystalBase, CrystalOptimizer, AntiWeakness, delays, and
  the accepted action-boundary behavior retain their existing paths; no HeadBob rotation request is made.
- Added a narrow exact-base resolver: it begins only from the visible `BlockHitResult`, requires that exact block to
  be Obsidian or Bedrock, queries only the one-block-wide/two-block-tall `base.above()` placement column, accepts
  alive EndCrystals whose logical position belongs to that same base, and chooses by column-center squared distance
  then entity ID. It does not scan a radius, select Slimes/players/adjacent-base Crystals, accept MISS/entity hits,
  Respawn Anchors, or automatically choose enemy positions.
- Added explicit `Idle`, `AimingCrystal`, `WaitingAfterAttack`, and `AimingBase` HeadBob states. They continuously
  revalidate the visible base hit and cleanly cancel on look-away, setting disable, activation release, screen,
  player/world replacement, disconnect, missing Crystal, or no held EndCrystal. Cancellation consumes a natural
  safety reset boundary so an old silent rotation cannot immediately become a normal placement.
- Crystal aim requests a short-lived direct `MovementCorrection.SILENT` target at
  `Priority.IMPORTANT_FOR_USAGE_3`; it does not write camera yaw/pitch, mouse deltas, cursor state, or use
  `CHANGE_LOOK`. The request is refreshed only for the active phase and action waits for the sent server rotation to
  hit the selected Crystal.
- HeadBob reuses AutoCrystal's entity handler, optimizer behavior, AntiWeakness switching/restoration, and the
  accepted `pendingEntityActionBoundary`/`ActionTickBoundary` tracker. A real HeadBob attack arms the same boundary;
  base aim does not start until the natural `CLIENT_TICK_END` passed it, and AntiWeakness restoration retains its
  existing priority. Optimizer inhibition before any Crystal rotation follows existing
  `INHIBITED_WITHOUT_PACKET` placement behavior; if it happens after a Crystal rotation was requested, HeadBob still
  enters base aim before placement rather than using the stale Crystal-facing server rotation.
- Base aim uses the current real visible `BlockHitResult` location and face, a silent target, target-version block
  range, and a world `facingBlock` raytrace of `actualServerRotation` to the same base/face before it invokes the
  existing `handleBlockTarget`. No synthetic centered hit result or neighbouring base is used.
- Bounded installed diagnostics use `[HeadBobDiag]` (32 cycles/512 messages). They record base/Crystal resolution,
  requested and actual yaw/pitch, target yaw/pitch, range, exact visible hit, Crystal ID, slot, delay clocks, state,
  attack/boundary/base-raytrace/placement evidence, and cancellation/reset reasons. They remain for gameplay tests.

### Files, static checks, build, and install

- Changed: `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleAutoCrystal.kt`,
  `src/main/resources/resources/liquidbounce/lang/en_us.json`, and this append-only `Agent1.md` entry. No rotation
  helper, CrystalOptimizer, ActionTickBoundary, other module, mixin, archive, backup JAR, log, or dirty Offhand file
  was changed for HeadBob.
- `git diff --check` passed. Static review confirms one Boolean setting, exact-column `getEntities` lookup only,
  `actualServerRotation` action gates, zero wall range, existing ActionTickBoundary reuse, no HeadBob
  `CHANGE_LOOK`/camera mutation, and no new configurable range/delay/rotation or damage-target scan.
- Requested clean command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt`
  completed `BUILD SUCCESSFUL` in `2m`.
- Built `build/libs/liquidbounce-0.31.2.jar` SHA-256:
  `8e2efdade133157ba69a304fc213f8a8c0fb68a805ec49369058376300c639c3`. It was installed to
  `/storage/emulated/0/FCL/.minecraft/versions/26.1.2-Fabric/mods/liquidbounce-0.31.2.jar` with the identical
  SHA-256. The prior accepted MultiActionsF test JAR was preserved outside the repository as
  `liquidbounce-0.31.2.pre-headbob-20260716.jar`, SHA-256
  `a85556f87a05ede8ca5754c005804eae0353350fd2905746661475082d03de18`.

### Runtime, flags, and Git status

- FCL gameplay runtime has not been performed in this Termux-only session. The required disabled regression,
  basic repeat cycle, Crystal/base hitbox proof, look-away, adjacent-base, no-Crystal, direct-crosshair,
  CrystalOptimizer (both Blatant modes), CrystalBase, AntiWeakness, delay, rotation-conflict, and lifecycle matrix
  remain pending. Therefore there is no claim yet that visible-camera stability, break/replacement behavior, or
  absence of Grim Hitboxes, Reach, MultiActionsF, PacketOrderE, RotationPlace, PositionPlace, Post, BadPackets,
  AimDuplicateLook, or AimModulo360 flags has been verified. No known deviation has been observed statically; that is
  not runtime parity proof.
- Accepted prior MultiActionsF fix was committed separately as `ace1f2acc`
  (`fix(combat): separate crystal attack and placement`) and pushed successfully to `origin/main` before HeadBob.
- HeadBob is intentionally uncommitted and unpushed pending those required runtime tests. Its later commit must stage
  only the three files above and use `feat(combat): add AutoCrystal HeadBob`; current branch is `main`, remote is
  `origin` (`https://github.com/Duong1234vi0/Wraith-0.1.git`).

## AutoCrystal HeadBob post-rotation fast path (2026-07-16, Codex)

### Reported latency and event-order finding

- The user accepted the functional silent HeadBob implementation but reported that, with `PlaceDelay = 0` and
  `BreakDelay = 0`, it felt substantially slower than direct legitimate Crystal interaction.
- The cause was an event-order mismatch, not a configurable delay: `MixinMinecraftClient` emits `GameTickEvent` at
  tick start, `RotationManager` owns that event at `FIRST_PRIORITY`, calls `RotationUpdateEvent`, and performs
  `RotationManager.update()` before AutoCrystal's ordinary tick handler. The prior HeadBob handler therefore requested
  its rotation too late for that tick's movement packet.
- `actualServerRotation` only changes after the outgoing rotation-bearing movement packet. The old polling path was
  consequently approximately: request Crystal after update (tick N), apply it (N+1), observe old actual server
  rotation, then attack (N+2); it repeated the same request/apply/observe delay for the base phase.
- An initially investigated post-move action shortcut was rejected against the live Grim build: `PacketOrderO` and
  `Post` reject non-async `ATTACK`/`USE_ITEM_ON` after a movement packet and before natural `CLIENT_TICK_END`.
  The resulting implementation is the fastest verified safe variant, not an unsafe same-tick post-move shortcut.

### Fast safe execution path

- HeadBob planning now runs from `RotationUpdateEvent`, before `RotationManager.update()`, so a silent target at
  `Priority.IMPORTANT_FOR_USAGE_3` reaches the normal movement phase in the same client tick. Initial exact-base
  Crystal acquisition is also performed there; direct visible entity targeting retains priority.
- A `RotationTarget.whenReached` `RestrictedSingleUseAction` schedules only a post-move rotation confirmation through
  `PostRotationExecutor(postMove = true)`. It sends no interaction packet. That confirmation rechecks the exact
  server-facing raytrace, range, lifecycle, delay, visible base, and target identity, then schedules one normal
  `PostRotationExecutor` task for the next GameTick pre-move phase.
- The next pre-move phase revalidates `RotationManager.actualServerRotation` before invoking the existing explicit
  HeadBob entity/block handlers. The live safe timeline is therefore:
  `RotationUpdateEvent request -> MOVE/LOOK -> post-move proof -> next-tick pre-move ATTACK -> CLIENT_TICK_END ->
  RotationUpdateEvent base request -> MOVE/LOOK -> post-move proof -> next-tick pre-move USE_ITEM_ON`.
  There is no old idle state-machine/polling tick; the one-tick server-rotation confirmation before each action and
  the real attack/place boundary remain deliberately intact.
- `ActionTickBoundary`, `pendingEntityActionBoundary`, CrystalBase precedence, CrystalOptimizer inhibition,
  AntiWeakness switching/restoration, zero-delay semantics, exact-base resolution, silent camera behavior, and the
  direct-crosshair/no-Crystal paths remain in place. There is still one `HeadBob` Boolean and no speed setting.
- Delay clocks now use a once-per-player-tick snapshot shared by RotationUpdateEvent and ordinary AutoCrystal ticks,
  avoiding a double decrement while preserving `clock = 1` as "block this tick, permit the following tick." A
  monotonic HeadBob generation plus captured base/Crystal/state checks discard stale post-move or pre-move callbacks
  on look-away, disable, world/player change, target replacement, binding release, or state reset.

### Diagnostics, build, and runtime evidence

- `[HeadBobDiag]` remains bounded and now records phase-entry, rotation-request, rotation-packet, pre-move action,
  boundary serial, rotation/boundary wait counts, and separately measured server/network Crystal-spawn latency.
  It logs Crystal/base requests, proof, attack/place execution, cancellation, and the boundary transition.
- The clean requested build command completed successfully. Final built and installed
  `liquidbounce-0.31.2.jar` SHA-256 matched:
  `a3a1bf46128ea7763dc2249f542b9e3179bfa32386ba9f858264820b022fa734`.
  A dated installed-JAR backup was made before replacement.
- Runtime repeat cycles on `test.ccbluex.net` showed the expected serial cadence: request/rotation packet in one
  movement phase, action in the next pre-move phase, natural action boundary after attack, immediate next eligible
  base request, then next pre-move placement. Typical recorded Crystal spawn/network latency was four client ticks;
  that is reported separately from client state-machine latency. Look-away cancellation was also observed before a
  queued base placement could run.
- In the observed HeadBob ordering window, no `PacketOrderO`, `Post`, `Hitboxes`, `Reach`, `MultiActionsF`,
  `PacketOrderE`, `RotationPlace`, or `PositionPlace` report was emitted. The shared test profile did emit Grim's
  separate experimental `DuplicateRotPlace` diagnostic; it is recorded as separate evidence rather than being
  misrepresented as one of the required order/reach/rotation checks.
- Final static checks before handoff: `git diff --check` clean; compile and clean assemble succeeded. The final
  commit/push was explicitly requested after this append; the terminal handoff records its resulting hash and push
  status.

## Prestige combat ports — 2026-07-17, Codex

### Checked

- Completed the mandatory workflow documents: `Skill/README.md`, `Skill/Agen.md`,
  `Skill/CLAUDE_PORT_SKID_MODULE_GUIDE.md`, `Skill/PORT_EVIDENCE_TEMPLATE.md`,
  `Skill/PORT_REVIEW_CHECKLIST.md`, `Skill/CODEBASE_RULES.md`, and `Skill/AGENT1_USAGE.md`. The completed
  evidence template is retained only in private work notes, outside the staged task files.
- Read the exact Prestige files: `module/impl/combat/TotemHit.java`, `AutoTotem.java`,
  `AutoShieldBreaker.java`, and `PredictDoubleHand.java`; `util/impl/InventoryUtil.java`, `PacketUtil.java`,
  `EntityUtil.java`, `BlockUtil.java`, and `TimerUtil.java`; `setting/impl/DragSetting.java`;
  `managers/DamageManager.java`; `api/mixin/IPlayerInteractEntityC2SPacket.java`, `IHandledScreen.java`, and
  `MixinClientConnection.java`; and `ui/drawables/gui/screens/impl/AutoTotemScreen.java`.
- Read the exact current Wraith paths: `ModuleOffhand.kt`, `offhand/Totem.kt`, `ModuleManager.kt`,
  `InventoryManager.kt`, `InventoryUtils.kt`, `InventoryActionMovementGate.kt`, `ItemSlot.kt`, `Slots.kt`,
  `SilentHotbar.kt`, `Chronometer.kt`, `EntityExtensions.kt`, `MojangCompatExtensions.kt`,
  `BlockExtensions.kt`, `CombatManager.kt`, `CombatExtensions.kt`, `ActionTickBoundary.kt`, `EntityEvents.kt`,
  `NetworkEvents.kt`, `PlayerEvents.kt`, `DrawEvents.kt`, `MixinClientConnection.java`,
  `MixinClientPlayerInteractionManager.java`, `MixinClientPlayerEntity.java`, `ModuleAutoWeapon.kt`,
  `ModuleMaceSwap.kt`, `ModuleLegitHelper.kt`, `ModuleTriggerBot.kt`, and `en_us.json`.
- Searched current code and later chronological history for the requested attack/use/carried-slot packets,
  packet handlers, Totem pop/movement/overlay events, inventory scheduling, `ContainerInput.SWAP`, Offhand
  transaction-gate users, ActionTickBoundary, AutoWeapon, TriggerBot, AutoCrystal, and selected/server hotbar
  behavior. Historical claims were checked against the live implementation rather than carried forward.
- Read the current 26.1.2 merged mappings/source for `ClientLevel`, `ServerExplosion`, and `CombatRules` while
  validating entity iteration, exposure sampling, and armor reduction. No Prestige source/archive was modified.

### Source behavior and current mapping

- `TotemHit`: Prestige's outgoing attack replay maps from `PacketSendEvent`/
  `PlayerInteractEntityC2SPacket` to outgoing `PacketEvent`/`ServerboundAttackPacket`. If the packet entity is a
  Player and the visible main hand is a Totem, Wraith picks the first hotbar Sword, visibly selects and synchronizes
  it, sends the same attack packet once through the normal connection path, restores/synchronizes the original slot,
  and lets the original packet continue. The nested send naturally stops recursion because the visible hand is then
  the Sword; no `AttackEntityEvent.caller()` or extra swing is used.
- `RefillHotbar`: Prestige status-35 handling maps to live `TotemPopEvent` from `CombatManager`. The new enabled
  `Offhand -> Totem -> RefillHotbar` group has `Delay` `30..50 ms` (allowed `0..300`) and one-based `TotemSlot`
  default `8` (allowed `1..9`). One local-pop attempt samples its delay once, waits for the existing primary
  Offhand action to finish and `InventoryActionMovementGate.State.IDLE` to be fully released, then selects a random
  Totem only from `Slots.Inventory` and schedules one detached player-inventory `ContainerInput.SWAP` to
  `HotbarItemSlot(TotemSlot - 1)`. It clears on an existing hotbar Totem, no inventory Totem, completed click,
  disable, player replacement, world change, or disconnect. It never opens/closes a screen and never touches the
  Offhand slot itself.
- `AutoShieldBreaker`: Prestige `MoveEvent` maps to `PlayerMovementTickEvent`, verified at the local player's
  `aiStep` dispatch. Every eligible tick uses only the exact crosshair Player, active Shield use, and first hotbar
  Axe; it visibly switches, synchronizes with `ensureHasSentCarriedItem()`, calls the normal interaction attack,
  and swings the main hand. It has no cooldown/filter/rotation/silent switch/switch-back.
- `PredictDoubleHand`: Prestige `Render2DEvent` maps to live per-frame `OverlayRenderEvent`; its separate Pearl
  listener maps to outgoing `PacketEvent`/`ServerboundUseItemPacket`. The timer samples the initial lower delay,
  then samples/resets before each source check with no enable-time reset. Periodic branches visibly select the first
  hotbar Totem without forcing a carried-slot packet. Pearl intentionally only changes the selected slot inside the
  outgoing callback so the Pearl packet leaves before normal selected-slot synchronization.
- Predictor target selection intentionally preserves `EntityUtil.getPlayer()`: normal `level.players()` iteration,
  retain the final alive non-local Player, then a 10-block local distance gate. It does not use nearest/crosshair/
  KillAura/friend/team/bot/visibility selection. Anchor and Obsidian scans are exact `-5..5` cubes; Crystal scans
  normal live world entity iteration. Trigger order remains Anchors, Crystals, Obsidian, then SwordPop.

### Source defects corrected and preserved quirks

- Corrected the three approved sentinels only: a missing hotbar Totem is nullable rather than false slot `0`, so
  RefillHotbar can detect no hotbar Totem and PredictDoubleHand returns without switching; and a missing Axe returns
  without assigning Prestige's invalid `-1` slot. The excluded Prestige Legit refill branch, including its one-based
  button defect, was not ported.
- Excluded AutoTotem behavior: no standalone AutoTotem/InventoryTotem module, no Legit, DoubleHand, OpenInventory,
  CloseInventory, AutoTotem screen, notification, sound, preview, extra timeout, or GUI operation.
- Preserved PredictDoubleHand's intentional oddities: final world-list Player selection; local-main-hand-only
  IgnoreWhenShield check while using; source trigger order; strict cube/distance comparisons; no switch-back or
  silent hotbar; and all thresholds retain `selectedTarget.health + localPlayer.absorptionAmount - damage`, including
  SwordPop's target-health-plus-local-absorption test.

### Damage evidence

- PredictDoubleHand damage calculation is **fully source-equivalent for the inspected Prestige `DamageManager`
  semantics on current 26.1.2; it is not blocked.** The module deliberately does not call Wraith's non-equivalent
  generic explosion helper. It privately reproduces crystal range 12/power 6, anchor/bed range 10/power 5,
  sampled bounding-box exposure raycasts, vanilla raw explosion math, difficulty, armor/toughness, Resistance,
  Blast/Protection cap, non-negative clamp, and Prestige's crystal-versus-anchor reduction order.
- Anchor exposure excludes the anchor block through the inspected current `BlockGetter.raycast` support, replacing
  Prestige's temporary world mutation without changing the world. Current `CombatRules.getDamageAfterAbsorb` was
  verified to retain the needed armor/toughness formula; current enchantment levels are summed with the inspected
  Prestige explosion Protection/Blast Protection semantics.

### Changed

- Added `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleTotemHit.kt`,
  `ModuleAutoShieldBreaker.kt`, and `ModulePredictDoubleHand.kt`.
- Updated `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/player/offhand/ModuleOffhand.kt` and
  `Totem.kt` for the one-shot RefillHotbar branch; `ModuleManager.kt` for exactly one registration of each new
  combat module; and `src/main/resources/resources/liquidbounce/lang/en_us.json` for only the three required
  English descriptions.
- No mixin, access widener, global entity-extension rewrite, action-boundary, movement suppression, manual
  client-tick packet, packet queue, reference file, ZIP, or unrelated dirty file was changed.

### Verification

- Final static audit: `git diff --check` passed; registrations are exactly one each; no standalone
  AutoTotem/InventoryTotem was added; RefillHotbar has no GUI calls; TotemHit has no `AttackEntityEvent.caller()`;
  Pearl has no pre-use carried-slot synchronization; AutoShieldBreaker has no restoration; and the predictor has no
  nearest-player or invalid `-1`/false-slot-0 fallback.
- Required Termux/FCL build command:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt`
  completed `BUILD SUCCESSFUL` in `1m 59s`.
- `build/libs/liquidbounce-0.31.2.jar` SHA-256:
  `aea2629dfec9d5c0d9ba6e4a50565156d9987c0c334a1e34db4c0b7d5a07d89c`. `jar tf` confirms all three new combat
  classes plus changed `ModuleOffhand`/`Totem` classes, including `Totem$RefillHotbar`, are packaged.

### FCL test plan/status

- Pending user gameplay execution: TotemHit with a hotbar Sword, no Sword, and non-Player target; verify
  Sword replay then restored Totem/original packet order.
- Pending: local Totem pop with no hotbar Totem/inventory Totems and occupied or empty configured target;
  hotbar-Totem/no-inventory-Totem clears; and no inventory screen opens/closes.
- Pending: shield-blocking crosshair Player with Axe, stopped/non-Player target, and no-Axe cases; verify one
  movement-tick attack/swing and no restoration.
- Pending every predictor trigger: uncharged Anchor, existing Crystal, Obsidian-above hypothetical Crystal,
  low-health Sword/Axe target, and outgoing main-hand Pearl. Also test multiple Players/final-list choice, no hotbar
  Totem, local-main-hand Shield use versus offhand-only Shield, Pearl packet order, toggle timing, and no switch-back.
- Compilation/package checks are complete only; no in-game FCL runtime success or anti-cheat outcome is claimed.

### Commit / push

- Feature commit: `f9e620f6` — `feat(combat): port Prestige totem prediction and shield modules`.
- Pushed successfully to `origin/main`: `772ece39f..f9e620f6c  main -> main`.
- The append-only history record itself is committed separately immediately after this entry so it can truthfully
  record the already-created feature hash and push result without rewriting prior history.

## TotemHit Grim ordering + RefillHotbar first-pop correction — 2026-07-17, Codex

### Checked

- Read the mandated workflow documents: `Skill/README.md`, `Skill/Agen.md`, `Skill/CODEBASE_RULES.md`,
  `Skill/AGENT1_USAGE.md`, and `Skill/GrimAC_Checks_Catalog.md`; then read the later chronological `Agent1.md`
  entries covering the Prestige combat port, TotemHit, RefillHotbar/InventoryTotem, Offhand ordering,
  InventoryActionMovementGate, ActionTickBoundary, KeyPearl, and AutoCrystal's deferred AntiWeakness restore.
  History was treated as context only and checked against the live source.
- Read the supplied, unmodified Grim 2.0 sources: `PacketOrderB.java`, `PacketOrderE.java`,
  `PacketOrderProcessor.java`, `Post.java`, and `BadPacketsA.java`.
- Read the mandatory live Wraith modules, inventory/action-boundary utilities, CombatManager/CombatExtensions,
  SilentHotbar, KeyPearl, AutoCrystal deferred restore, both network/interaction mixins, and the current packet,
  game-tick, scheduling, pop, world-change, and disconnect event definitions. Direct call sites and every requested
  packet/action-boundary/gate-state search were inspected.

### Runtime symptoms

- TotemHit produced Grim `PacketOrderB: post-attack` and `PacketOrderE: attacking=true` because it replayed the
  intercepted attack while selected on a Sword, restored the prior slot before the replay's normal animation, and
  then let the original attack continue with only one caller-owned swing.
- RefillHotbar missed the first local pop when a backup Totem already existed in the hotbar: status-35/pop handling
  observed the stale pre-vanilla/pre-Offhand inventory snapshot and cleared the request merely because *any* hotbar
  slot contained a Totem.

### Grim source evidence

- On modern clients `PacketOrderB` requires `ATTACK -> MAIN_HAND ANIMATION`; a non-async packet between them is a
  post-attack violation. `PacketOrderE` rejects held-item changes while its attack flag remains active.
- `PacketOrderProcessor` sets that flag for an attack and only clears action-order state at the natural tick packet;
  `Post` confirms the relevant action/post timing. `BadPacketsA` additionally rejects duplicate selected-slot IDs.
- The live `Connection.send(...)` HEAD mixin dispatches `OutgoingPacketSentEvent` before the outer send returns, so
  neither TotemHit restoration nor RefillHotbar slot selection may be sent from that callback.

### Root cause

- TotemHit's nested packet replay created two attacks with one natural animation and placed a held-slot restore in
  the forbidden attack-to-animation/action-boundary window.
- CombatManager emits `TotemPopEvent` from the incoming status-35 path before vanilla completes the pop update and
  before the primary Offhand transaction settles. The old global hotbar check therefore consumed the refill request
  before the configured backup slot had actually been moved into Offhand.

### TotemHit behavior change

- TotemHit now captures player/level/slots/target/action serial, visibly selects the first hotbar Sword, calls
  `ensureHasSentCarriedItem()` once, and lets the original Player attack continue exactly once. It confirms that
  exact packet through `OutgoingPacketSentEvent` but does not send from the callback.
- A narrowly scoped always-running pending-cycle listener restores only the valid prior visible slot on a later
  `GameTickEvent`, after `ActionTickBoundary.hasPassed(...)`,
  `canSendPostActionHeldItemChange(...)`, and an expected held-item-change attempt. It also safely handles a
  cancelled attack, module disable, player/level replacement, world change, and disconnect.
- “The original Prestige-style nested double-attack sequence was removed because two attacks with one natural swing plus a same-tick post-attack held-slot restoration is incompatible with the inspected Grim PacketOrderB and PacketOrderE state machines. TotemHit now performs one Sword-backed attack and restores the previous slot only after the natural client tick boundary.”

### RefillHotbar race/state-machine fix

- Every eligible local pop now queues one captured refill cycle: local player and level identity, sampled Delay,
  one-time `TotemSlot - 1` index/`HotbarItemSlot`, and pre-settle ActionTickBoundary serial.
- The head cycle waits for Delay, a passed natural pop boundary, and primary Offhand gate `IDLE` **before** inspecting
  anything. It then checks only the captured configured target slot; a Totem in any unrelated hotbar slot no longer
  clears the cycle.
- When needed, it randomly selects only a `Slots.Inventory` Totem and schedules the existing detached player
  inventory `ContainerInput.SWAP`. The precondition rechecks that same target and source so a moved source or an
  independently filled target cancels cleanly.
- Observing the exact container SWAP changes state to `WAITING_FOR_POST_CLICK_BOUNDARY`; it neither clears nor
  selects in `Connection.send(...)` HEAD. After the click boundary and bounded target validation, it visibly selects
  the configured target only when that slot actually contains a Totem. Disabled/lifecycle-invalid/timeout cycles are
  cleared without a delayed click or selection.

### Exact changed files

- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleTotemHit.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/player/offhand/ModuleOffhand.kt`
- `Agent1.md` (this append-only record)

### Packet timelines before and after

- TotemHit before: `HELD_ITEM_CHANGE(Sword) -> ATTACK(nested) -> HELD_ITEM_CHANGE(previous) -> ATTACK(original) -> MAIN_HAND ANIMATION -> CLIENT_TICK_END`.
- TotemHit after: `HELD_ITEM_CHANGE(Sword) -> ATTACK(original, once) -> MAIN_HAND ANIMATION -> CLIENT_TICK_END -> HELD_ITEM_CHANGE(previous)`.
- Refill after: `TotemPop(pre-settle serial) -> Delay + CLIENT_TICK_END + Offhand gate IDLE -> target-only check -> detached SWAP -> observed click -> CLIENT_TICK_END -> visible configured TotemSlot synchronization`.

### Static verification

- `git diff --check` passed. TotemHit contains no nested/replayed `attackPacket` send, no constructed second attack,
  no direct carried-item packet, and its restore is gated by `ActionTickBoundary.hasPassed(...)`.
- RefillHotbar contains no `Slots.Hotbar.any` or `Slots.Inventory.none` terminal condition. Its decision waits for
  Delay, the pop boundary, and gate `IDLE`; both scheduling and precondition inspect the captured target slot.
- The matching click transitions state rather than clearing/selecting in the outgoing callback. Visible selection is
  only attempted after the click boundary, and RefillHotbar-disabled lifecycle checks clear pending work.
- The final JAR contains `ModuleTotemHit`, `TotemHitCycle`, `ModuleOffhand`,
  `ModuleOffhand$HotbarRefillState`, `ModuleOffhand$PendingHotbarRefill`, and `Totem$RefillHotbar` classes.

### Build command/result

- Ran exactly:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt`
- Result: `BUILD SUCCESSFUL` in `2m 9s` (`10 actionable tasks: 7 executed, 3 up-to-date`).

### JAR SHA-256

- `build/libs/liquidbounce-0.31.2.jar`: `16f226cb62a95ba92e97bcb19493a21f4122d61b22739f0b11bbf30c4d5e5bbd`

### FCL/Grim test status

- Not run in FCL in this workspace; compilation/package verification is not presented as Grim runtime success.
- Required pending capture: TotemHit manual Player and standard Wraith path; no-Sword/non-Player; disable during
  pending restore; world/disconnect cleanup. Record exactly one attack and the required Sword/attack/animation/
  tick-end/restore packet timeline with no PacketOrderB, PacketOrderE, or Post.
- Required pending RefillHotbar capture: all five specified inventory scenarios, explicitly first-pop Scenario A;
  record pop serial, Offhand gate state, source/target, observed click, click boundary, and selected target. Also
  test movement/sprinting suppression and later safe execution.

### Known limitations

- Runtime packet sequencing and server-side inventory acknowledgement still need the listed FCL/Grim captures.
  The post-click target validation is intentionally bounded to five game ticks and clears safely rather than
  selecting a non-Totem item or retrying forever.

### Commit hash

- Implementation commit: `6bf7d2e3` — `fix(combat): correct totem hit ordering and refill timing`.

### Push result

- Implementation push succeeded: `f58124aec..6bf7d2e3d  main -> main` on `origin`
  (`https://github.com/Duong1234vi0/Wraith-0.1.git`). This append-only history record is committed separately after
  the implementation so it can truthfully record the already-created commit and push result.

## V-Core ElytraTarget / ElytraMotion / EMaceHelper port — 2026-07-17, Codex

### Checked

- Read the mandatory Skill workflow/rules, the relevant Agent1 history (including the 2026-07-15 removal,
  rollback, PacketOrderE, ActionTickBoundary, movement gate, KeyPearl, AutoCrystal, MaceSwap and ElytraSwap
  entries), and the required V-Core 0.8 and Grim 2.0 source files completely.
- Completed private port evidence notes before editing. Inspected the current Wraith callers and event dispatches
  before accepting a historical path or API.

### Current-state confirmation

- This task intentionally reintroduces ElytraTarget and ElytraMotion after their 2026-07-15 removal.
- The deleted Wraith implementations were reviewed as historical evidence and were not restored blindly. The port
  maps the inspected V-Core decisions into the current Wraith rotation, action-boundary, inventory-scheduler and
  movement-gate architecture.

### V-Core source behavior

- Preserved ElytraTarget's eye-to-live-AABB closest point, horizontal BPS threshold (`>= 20`), `sqrt(bps) / 2`
  auto scale/cap, null-target reset, and local-hurt 500 ms suppression.
- Preserved ElytraMotion's freeze conditions, exact local/full-XYZ BPS test for AutoFirework, 500 ms timing, and
  PeakAssist freeze release.
- Preserved EMaceHelper's Mace/MaceSwap gate, counterintuitive Auto Target skip filter, grass raycast retry,
  high-Y PeakAssist offset, Armor Swap chestplate priority, FireWork Vector thresholds, and Extra AimRange gate.

### Dependency graph

`ModuleElytraTarget -> ModuleKillAura target distance/aim/attack -> ModuleEMaceHelper peak offset`
`ModuleEMaceHelper -> ModuleMaceSwap bridge / safe armor transaction / shared Firework executor`
`ModuleElytraMotion -> ModuleElytraTarget + ModuleEMaceHelper -> shared Firework executor`
`shared Firework executor -> ActionTickBoundary + InventoryActionMovementGate + inventory scheduler`

### Old deleted implementations reviewed

- Later deletion/rollback records superseded earlier implementation notes. No Track, Grim, HumanizedTrack,
  VCoreAuraRotationController, or VCoreAuraRotationsConfigurable choice/controller was reintroduced.
- ElytraBoost, EventFireworkMotion, and ElytraPlus were not ported because no direct dependency remained after
  mapping the requested behavior.

### Port mapping and exact settings/defaults/ranges

- `ElytraTarget`: `Prediction=true`, `Visual=true`, `Color=#FFA000`/`16752640`, `Mode=Auto`, `Predict Range=2.6`
  (`1.5..6.0`), and `Predict Max=10.0` (`1.5..10.0`) with the V-Core visibility rules.
- `ElytraMotion`: `AttackDistance=3.0` (`0.1..5.0`), `AutoFirework=false`, and `MatrixBypass=false`.
- `EMaceHelper`: `Auto Target=false`, `PeakAssist=false`, `Height=100` (`25..1000`), `Armor Swap=false`,
  `FireWork Vector=false`, `Extra AimRange=false`, `Swap Distance=10` (`5..16`), `FireWork Distance=30`
  (`16..64`), and `Aim Distance=900` (`800..1000`), with the V-Core visibility rules.

### KillAura integration

- Gliding uses `ElytraRange=0.5` and `ElytraAimRange=32`; EMaceHelper can override only the active Elytra aim
  range through `Extra AimRange`.
- Elytra candidates use the authoritative prediction exactly once. The resolved aim point bypasses the normal
  enemy-motion PointTracker offset and receives PeakAssist exactly once.
- The V-Core Elytra attack window is retained: local-position distance to predicted point is at most
  `prediction - 0.5`, or the local AABB intersects the zero-size predicted-point AABB. Non-Elytra targets retain
  Wraith's ordinary distance, raycast/facing, range, and wall-range path.
- EMace Auto Target filtering runs in target validation only under its exact gate. The accepted outgoing attack
  packet marks the single V-Core-equivalent post-attack transition.

### EMace behavior

- Added `ModuleMaceSwap.hasMaceInHotbar()` using its existing current mode predicate; no MaceSwap defaults,
  MaceStun, silent behavior, or switch-back behavior was redesigned.
- PeakAssist derives its base from ElytraTarget when applicable, otherwise the target eye, retains its 500 ms
  firework lifecycle/final firework behavior, and makes ElytraMotion release freeze while active.

### Firework packet-order adaptation

- The shared non-configurable executor preserves V-Core offhand/hotbar/main-inventory search and temporary-slot
  decisions, including `currentSlot % 8 + 1`, selected rotation choice, and one swing after accepted use.
- Raw V-Core same-window `HELD_ITEM_CHANGE -> USE_ITEM -> restore` is intentionally not reproduced. Restoration
  waits for observed use, `ActionTickBoundary.hasPassed(actionSerial)`,
  `expectHeldItemChange`, and `canSendPostActionHeldItemChange` at a natural tick boundary. No manual client
  tick-end packet or duplicate carried-slot packet is sent.

### Inventory transaction adaptation

- Firework main-inventory/offhand routes and Armor Swap use the existing inventory scheduler and
  `InventoryActionMovementGate`; they defer while another action owns the window, unsafe movement/sprint state,
  Offhand transaction, or armor transaction is active.
- Armor Swap maps V-Core's pickup/pickup/pickup outcome through a detached safe transaction: the chosen chestplate
  equips and the Elytra returns to the source slot without opening a visible GUI. Lifecycle, target/item failure,
  timeout, world, disconnect, and player replacement clear bounded pending state.

### Files changed

- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleElytraTarget.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleEMaceHelper.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/ModuleElytraMotion.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleMaceSwap.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/KillAuraTargetTracker.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/ModuleKillAura.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/player/offhand/ModuleOffhand.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/inventory/ElytraFireworkExecutor.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/inventory/InventoryActionMovementGate.kt`
- `src/main/resources/resources/liquidbounce/lang/en_us.json`
- `Agent1.md` (this append-only record)

### Static verification

- `git diff --check` passed. V-Core/Grim reference trees were not modified; registration count is exactly one for
  each new module; there is no duplicate ElytraSwap/MaceSwap registration.
- Verified no forbidden old controllers/smoothers or stale symbols remain, no V-Core source was restored wholesale,
  no Firework velocity mixin was added, and the packaged JAR has the three new modules plus changed KillAura and
  MaceSwap classes without unwanted ElytraBoost/ElytraPlus/EventFireworkMotion/controller classes.
- Confirmed ordinary grounded target paths remain conditional on non-gliding/non-Elytra behavior; prediction is
  resolved once and PeakAssist releases ElytraMotion freeze.

### Build command/result

- Ran exactly:
  `env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt`
- Result: `BUILD SUCCESSFUL` in `2m 42s` (`10 actionable tasks: 7 executed, 3 up-to-date`). Compilation/package
  verification does not establish server-side Grim acceptance.

### JAR SHA-256

- `build/libs/liquidbounce-0.31.2.jar`:
  `6cb238588b8d52d100a1fd1f07604d072813c48e0aa52c53acad0ed0fd4a5ce4`

### FCL/Grim test status

- Not run in FCL in this workspace. Required manual captures remain for every supplied ElytraTarget, KillAura,
  ElytraMotion, Firework item-location, EMace Auto Target/PeakAssist/Armor Swap/FireWork Vector/Extra AimRange
  scenario, including the listed PacketOrderE/G/I, BadPacketsA/J, MultiActionsC/D, and Post observations.
- The supplied Grim audit informed the boundary/scheduler mapping only; it is not claimed as runtime success.

### Deviations

- Source decision semantics, settings, state transitions, target conditions, formulas, search order, and visible
  outcomes are source-equivalent. The only intentional deviations are current-protocol safety mappings: deferred
  natural-boundary held-slot restoration, scheduler/gate-deferred inventory transactions, and cancellation of a
  previously deferred ElytraMotion use when PeakAssist takes ownership of the tick.

### Commit

- Implementation commit: `286b710a` — `feat(elytra): port vcore targeting motion and mace support`.
- Follow-up safety commit: `1b02f309` — `fix(elytra): cancel deferred motion rocket for peak assist`.

### Push

- Implementation push succeeded: `3daa52d85..286b710a5  main -> main` on `origin`
  (`https://github.com/Duong1234vi0/Wraith-0.1.git`). Follow-up push succeeded:
  `286b710a5..1b02f309e  main -> main`. This append-only record is committed and pushed separately.

## V-Core raw Elytra combat timing restoration — 2026-07-17

### Checked

- Read the later ElytraTarget, ElytraMotion, TrackAngleSmooth, GrimAngleSmooth, sustained-Elytra pitch snap,
  EMaceHelper, raw-reset, PacketOrderE, 2026-07-15 removal, and current 2026-07-17 re-port history.
- Read the mandatory V-Core and live Wraith sources, current 26.1.2 mappings/APIs, and local Skill guidance.
- V-Core reference sources under `WorkSpace/Client/VCore-0.8` remain untouched.

### Fidelity audit

The current port had introduced Grim-safe boundary scheduling, shared Firework serialization, deferred inventory
transactions, extra gates, and omitted direct Track/Grim rotation dependencies. This correction reimplemented the
current Wraith equivalents from V-Core instead of restoring an old commit wholesale.

### Previous conservative adaptations removed

Removed the shared `ElytraFireworkExecutor`, ActionTickBoundary/inventory movement gates, global pending Firework
state, delayed restoration, extra Offhand/armor transaction interlocks, and the deferred EMace armor transaction.

The previous 2026-07-17 port intentionally prioritized current Grim-safe packet and inventory boundaries. This correction restores V-Core’s raw same-update action timing and direct rotation dependencies at the user’s request. Runtime flags are accepted for later diagnosis.

### Auto Target mapping correction

EMace naked-player filtering now uses `target is Player && target.armorValue == 0`; the incorrect zero-health check is
gone.

### Track/Grim restoration

Added combat-only `TrackAngleSmooth` and `GrimAngleSmooth`, registered once. Track preserves the V-Core settings,
wrapped yaw direction, readiness acceleration, raytrace-aware pitch, sustained Elytra pitch snap after five glide
ticks, and one engine GCD normalization. Grim uses the authoritative target point, shortest yaw path, ±1° jitter,
and one-tick full snap. Fresh combat configurations default to Grim without overwriting saved choices.

### ElytraTarget event-order correction

ElytraTarget now runs after KillAura's target/process handler in the same rotation-update phase, so status, BPS,
scale, and hurt suppression read the resulting target. Source formulas and the exact BPS >= 20 prediction threshold
are unchanged.

### Three Firework profiles

- ElytraMotion uses its direct same-update V-Core route, including inventory-first descending search, the
  main-hand-use offhand route, packet-only hotbar switching, temporary-slot `% 8 + 1`, no close packet, and no
  explicit swing.
- PeakAssist uses the ElytraSwap source profile, including inventory-first search, same-update swaps, source close
  packets, silent hotbar use, and no explicit swing; it does not require the Wraith ElytraSwap module to be enabled.
- FireWork Vector retains its distinct hotbar-first ascending search, visible switching, player-inventory-only
  inventory route, close packets, explicit hand swing, and saved-slot restoration.

### Raw Armor Swap

EMace Armor Swap now performs source-slot pickup, chest-slot pickup, source-slot pickup, and close-container
immediately, with current player-menu slot mapping and a `try/finally` swapping state. Only V-Core guards remain;
there is no automatic Elytra switch-back or deferred scheduler.

### KillAura post-attack correction

Aura action completion now calls `ModuleEMaceHelper.markAuraPostAttack()` unconditionally. The old accepted-outgoing-
attack packet gate and pending entity state were removed. Existing Elytra range, prediction, PeakAssist offset, and
attack-window formulas were otherwise retained.

### Files changed

- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleEMaceHelper.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleElytraTarget.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/ModuleElytraMotion.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/ModuleKillAura.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/RotationsConfigurable.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/features/processors/anglesmooth/impl/TrackAngleSmooth.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/features/processors/anglesmooth/impl/GrimAngleSmooth.kt`
- deleted `src/main/kotlin/net/ccbluex/liquidbounce/utils/inventory/ElytraFireworkExecutor.kt`
- this append-only `Agent1.md` section

### Static verification

- `git diff --check` passed.
- No `target.health == 0.0f`, `pendingPostAttackEntityId`, EMace outgoing-packet marker gate, or removed executor/
  armor-transaction symbols remain in the corrected modules.
- Track and Grim each register exactly once for combat rotations; sustained Track pitch contains `180` after
  `fallFlyingTicks > 5`; fresh combat default resolves to Grim.
- ElytraTarget retains horizontal BPS `hypot(x,z) * 20`, exact threshold `20.0`, Auto `sqrt(BPS)/2` cap, and no
  multiplier/new prediction setting.
- The three raw Firework routes contain no ActionTickBoundary, InventoryActionMovementGate, shared pending request,
  or movement-stop inventory gate. Their search order, offhand-main-use behavior, close packets, swing behavior,
  and visible versus packet-only switching remain distinct.
- Packaged JAR contains ModuleElytraTarget, ModuleElytraMotion, ModuleEMaceHelper, TrackAngleSmooth, GrimAngleSmooth,
  and ModuleKillAura; ElytraFireworkExecutor and EMaceArmorTransaction classes are absent.
- Unrelated pre-existing worktree changes were not staged.

### Build command/result

Ran:
`env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt`

Result: `BUILD SUCCESSFUL`; compilation/package success is not a runtime or Grim acceptance claim.

### JAR SHA-256

`build/libs/liquidbounce-0.31.2.jar` — `87a972f3d7e8bb48971f769f698fe0a742cd445875b32f181878b9ccffd260bd`

### FCL comparison status

The required safe-build versus corrected-raw FCL capture was not run in this workspace. The pending comparison
matrix covers ElytraTarget at 10/19/20/40/high BPS, Grim predicted-cube snap, Track after more than five glide ticks,
all ElytraMotion Firework locations including main-hand use, MatrixBypass/AutoFirework overlap, EMace naked-player,
PeakAssist post-action and source Firework profile, FireWork Vector, and immediate Armor Swap packet order.

### Observed flags

Any Grim flags from the raw same-update routes are observations only and are intentionally deferred to a later
diagnosis task.

### Commit

Focused implementation commit: `2f8527ecb994357552b05296a2aac463abf3facb` —
`refactor(elytra): restore raw vcore combat timing`.

### Push

Push succeeded: `79e4e4a8f..2f8527ecb  main -> main` on the current `origin/main` branch.

## 2026-07-17 — final V-Core Elytra combat-stack fidelity correction

### Checked

Re-read the mandatory V-Core sources and the live Wraith implementations, including ElytraTarget,
ElytraMotion, EMaceHelper, Aura, MaceSwap, ElytraSwap, WallsBypass, ElytraPlus, Aura rotation Track/Grim,
InventoryUtility/SearchInvResult, the current packet/input helpers, registrations, and translations. No file under
`WorkSpace/Client/VCore-0.8` was modified.

### PeakAssist inventory and offhand corrections

PeakAssist no longer owns a copied ElytraSwap Firework routine. It delegates to `ModuleElytraSwap`'s authoritative
`useFireworkForPeakAssist()` implementation. The main-inventory route is now exactly
`SWAP -> CLOSE -> HELD(temp = selected % 8 + 1) -> USE MAIN_HAND -> HELD(original) -> SWAP_BACK -> CLOSE`.
The main-hand-use route is exactly `SWAP offhand -> CLOSE -> USE OFF_HAND -> SWAP original -> CLOSE`, with no
explicit swing. Hotbar use is packet-only held-slot switching and restores the saved slot.

The focused `ModuleElytraSwapFireworkPlanTest` asserts the temporary held-slot packet precedes `USE` and that both
offhand close actions exist. The repository-wide test task is currently blocked by the pre-existing
`PointFindingKtTest` (`math`/`Vec3d` mapping errors); the production compile and assemble succeed.

### Grim/Track GCD correction

Grim now uses V-Core's shortest-yaw path, ±1 jitter, absolute `pow(sensitivity * 0.6 + 0.2, 1.5) * 0.8 * 0.15`
remainder GCD, `(-90, 90)`/`-180` fallback, pitch clamp, one calculation tick, and a normalized-result marker.
It does not call `RotationUtil.gcd`. Track now uses V-Core's remainder-based delta GCD
`pow(sensitivity * 0.6 + 0.2, 3) * 1.2`, marks the result normalized, and its pitch raytrace range is
`activeAttackRange + activeAimRange` without the existing-target `+4` acquisition allowance.

### V-Core MaceSwap replacement

The ThunderHack implementation was replaced by V-Core settings and state: `Mode` (Normal default, Breach,
WindBurst), `Mace Stun=true`, `Switch Back=true`, `Silent=false` visible only with Switch Back, and
`AutoDisable=false`. `isSwitching`, previous/current delay, Aura flags, post-action marking, hotbar mode matching,
silent swap, source switch-back delays (`-1` silent/`2` visible), MaceStun shield-break flow, and AutoDisable are
present. Config migration maps legacy `Mode=None` to `Normal` and accepts legacy no-space names for the renamed
settings. ThunderHack recursive attack-event forcing is removed.

### V-Core ElytraSwap replacement and migration

The former one-shot scheduled-inventory module was fully replaced under the single public name `ElytraSwap` (with
the existing `ChestSwap` alias only for identity compatibility). V-Core settings/defaults are exact:
`SwitchButton` and `FireWorkButton` current key type (unbound), `Delay=false`, `GrimStop=false`, and
`StartFireWork=false`. The port includes 500 ms/200 ms key timers, delayed swap state, chestplate priority,
60 ms GrimStop scheduling/input suppression, START_FALL_FLYING start-firework handling, lifecycle reset on disable,
world change, disconnect, and player replacement, packet profiles for offhand/hotbar/main inventory, and shared
Firework use while the module is disabled.

The old one-shot `ScheduleInventoryActionEvent`, `PlayerInventoryConstraints`, implicit auto-disable, and hidden
legacy behavior were removed. The enabled/key module identity is preserved; no old setting had a semantic V-Core
equivalent, so no fabricated setting mapping was added. Newly introduced V-Core settings use the defaults above.

### WallsBypass and ElytraPlus status

Added one registered `WallsBypass` compatibility module with V-Core `Mode=YawOffset` (plus `PeekHigh`) and wired
the +20 yaw and high-target peek branches into KillAura/Track/Grim. The full V-Core `ElytraPlus` movement exploit is
not ported in this focused task. Its remaining Aura/Track dependencies are documented: ElytraPlus affects Aura's
auto-crit exclusion, the rotation distance +4 branch, and Track's grounded dynamic look-point choice. Enabled-state
compatibility therefore remains an explicit dependency blocker rather than a false claim of closure.

### Firework rotation ownership and shield semantics

ElytraSwap, ElytraMotion, and EMace raw Firework packets now use a narrow KillAura-owned server-facing rotation only
when Aura owns the active current target; otherwise they use the player rotation. Global rotations from unrelated
providers cannot leak into these packets. KillAura now marks EMace post-action state even when the shield-blocking
target's actual attack is skipped, while retaining the existing shield configuration outside that narrow EMace path.

### Files changed

- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/misc/ModuleElytraSwap.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleMaceSwap.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleEMaceHelper.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleAutoWeapon.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/ModuleWallsBypass.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/ModuleKillAura.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/combat/killaura/KillAuraTargetTracker.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/modules/movement/ModuleElytraMotion.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/features/processors/anglesmooth/impl/GrimAngleSmooth.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/utils/aiming/features/processors/anglesmooth/impl/TrackAngleSmooth.kt`
- `src/main/kotlin/net/ccbluex/liquidbounce/features/module/ModuleManager.kt`
- `src/main/resources/resources/liquidbounce/lang/en_us.json`
- `src/test/kotlin/net/ccbluex/liquidbounce/features/module/modules/misc/ModuleElytraSwapFireworkPlanTest.kt`
- this append-only `Agent1.md` section

### Verification

- `git diff --check` passed.
- Exactly one `ModuleElytraSwap` registration remains, with public name `ElytraSwap`.
- PeakAssist contains no duplicated ElytraSwap packet sequence.
- Main-inventory temporary held-slot selection and both offhand close packets are present.
- Grim has no `RotationUtil.gcd`; Grim/Track results are marked normalized.
- Track pitch-raytrace excludes the `+4` acquisition allowance.
- V-Core MaceSwap settings, state, AutoDisable, and post-attack API are present.
- V-Core ElytraSwap settings, key timing, lifecycle, delayed swap, StartFireWork, GrimStop, input state, chestplate
  priority, and all Firework routes are present.
- ElytraTarget and ElytraMotion formulas were not changed by this correction.
- `clean assemble -x detekt` passed; packaged classes include ElytraSwap, MaceSwap, WallsBypass, EMaceHelper,
  Track, and Grim.

### Build/JAR hash

Command:
`env JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:/data/data/com.termux/files/usr/bin:/data/data/com.termux/files/bin sh ./gradlew --no-daemon --no-build-cache clean assemble -x detekt`

Result: `BUILD SUCCESSFUL`.

`build/libs/liquidbounce-0.31.2.jar` SHA-256: `8fc1aff36647526156ae319ed11999cb69ab5243f71b98fca0ee3ef27fb1eef3`.

### Runtime/FCL status and remaining deviations

The requested live runtime matrix (PeakAssist inventory/offhand, Track/Grim packet angles, MaceSwap modes/MaceStun/
switch-back/AutoDisable, ElytraSwap keys/StartFireWork, unrelated rotation ownership, and shield post-action) was
not executable in this headless workspace. No runtime Grim flags were generated; any future flags must remain
observations. FCL comparison was not run. The repository-wide test task remains blocked by the unrelated existing
`PointFindingKtTest` mapping errors. Remaining fidelity deviation is the explicit absence of full ElytraPlus; Wraith's
pre-write `OutgoingPacketSentEvent` is the closest available hook for V-Core's post-send START_FALL_FLYING event.

### Commit

Implementation commit: `4607763a` — `fix(elytra): complete vcore dependency fidelity`.

### Push

Implementation commit pushed successfully to the current `origin/main` branch. A documentation-only follow-up
commit records this final commit/push state.

## V-Core Elytra combat stack — second fidelity audit and runtime-order correction (2026-07-18, Codex)

### Checked

- Read the current Wraith network mixin/event path, `NetworkUtils`, `ClientModule`, ElytraSwap, MaceSwap,
  EMaceHelper, KillAura, KillAura rotation configuration, Raytracing, TrackAngleSmooth, current keyboard/mouse
  events and InputTracker, the existing Firework plan test, AutoWeapon, WallsBypass, GrimAngleSmooth,
  RotationManager/RotationTarget, and the event registry completely.
- Read the V-Core ElytraSwap, MaceSwap, Aura, Aura Track, PlayerManager, Module, InputBlocker, and KeyBinding mixin
  sources completely. V-Core sources under `WorkSpace/Client/VCore-0.8` were not modified.
- Read the latest Elytra combat history entries and verified every historical claim against live Wraith source.

### Second fidelity audit

- `OutgoingPacketSentEvent` remains the accepted pre-dispatch observation at the HEAD of the one-argument
  `Connection.send(Packet)` path. It was not renamed or moved.
- Wraith had no true post-dispatch event, so ElytraSwap StartFireWork was using the HEAD observation and could send
  `USE_ITEM` before the start-flying command completed dispatch.
- MaceSwap AutoDisable called only `disable()`, leaving the framework `Enabled` value true. ElytraSwap key polling
  treated mouse values as keyboard GLFW codes, reset both timers on enable, and Track used an unobstructed raytrace.
- The current Aura callback marked EMace/MaceSwap after the dedicated Axe shield-break plus optional MaceStun hit,
  although V-Core returns before `postAttack()`. WallsBypass was also gated by a fresh `ThroughWalls=false`.

### StartFireWork pre/post-send root cause

- Added `OutgoingPacketPostSendEvent`, dispatched exactly once from a TAIL injection on only
  `Connection.send(Packet)`. The existing HEAD event and cancellation semantics are unchanged; a HEAD-cancelled
  send returns before TAIL and cannot emit the post event. The two-argument/internal overload is not injected.
- ElytraSwap now listens to this post-send event for `START_FALL_FLYING` and invokes the authoritative V-Core
  Firework function in the same callback: `START_FALL_FLYING -> USE_ITEM`, with no packet event, pre-send path,
  tick delay, or ActionTickBoundary scheduling.

### AutoDisable lifecycle bug

- `processAutoDisable()` preserves the V-Core branches: disabled AutoDisable clears only the trigger; a triggered
  switch-back waits while switching; a triggered non-switching or no-switch-back state clears source flags and sets
  `enabled = false`.
- The framework transition now invokes lifecycle cleanup once, unregisters handlers, and updates config/toggle state
  without a recursive direct `disable()` call.

### keyboard/mouse bind mapping

- `InputTracker` now tracks keyboard event state for scancodes and exposes type-aware KEYSYM/SCANCODE/MOUSE polling;
  `InputConstants.Key.isPressed` delegates to that mapping.
- ElytraSwap polls KEYSYM and SCANCODE bindings with the existing 500 ms/200 ms timers. MOUSE bindings are handled
  only on configured `GLFW_PRESS` `MouseButtonEvent`, require no screen, use the corresponding timer, and do not
  repeat merely because the button remains held. Mouse handling is not duplicated in the game-tick path.

### Track raytrace correction

- Track pitch now requires the current Aura target, returns true immediately when Raycast is None, and otherwise
  calls the target-locked five-argument `facingEnemy` helper with equal `traceRange` values for attack and walls.
- `traceRange` remains attack range plus aim range and does not use the existing-target `+4` allowance. Focused
  tests cover visible targets, behind-wall targets inside/outside range, disabled raytrace, and target mismatch.

### shield-break post-action correction

- KillAura identifies the actual dedicated blocking-player Axe/shield-break branch (including optional MaceStun),
  performs the Axe hit and optional Mace hit, and skips EMace/MaceSwap post-action markers for that cycle.
- Normal Aura cycles—including a shield-holding target that is not in the dedicated break branch—retain unconditional
  EMace marking and `MaceSwap.markAuraPostAttack(attacked)`. Click, cooldown, scan-range, and debug bookkeeping stay.

### WallsBypass source-default mapping

- KillAura combat rotation configuration now has one authoritative `ThroughWalls` value with fresh default `true`
  and legacy/source alias `IgoneWall`. Config loading preserves explicit saved `ThroughWalls=false`; no second wall
  toggle was introduced. Track and Grim query the same state. ElytraTarget prediction formulas were unchanged.

### timer-lifecycle correction

- Removed only ElytraSwap enable-time timer resets. Pending-swap, suppression, player replacement, world-change,
  disconnect, and disable cleanup remain unchanged; timers age across toggles as in V-Core's object-lifetime Timer.

### KillAura-owned rotation status

- Audited RotationManager, RotationTarget, Track/Grim processors, and Firework call sites. Wraith exposes KillAura's
  requested target rotation and the global processed rotation only when KillAura owns the active request; it does not
  safely expose a cached KillAura-owned processed Track/Grim rotation when another provider wins.
- Kept the safe behavior: use the narrow KillAura-owned active rotation when proven, otherwise player rotation. No
  stale global rotation cache was invented.

### Tests

- Added focused tests for post-send mixin ordering/one dispatch, StartFireWork callback ordering, keyboard/scancode
  versus mouse press handling, MaceSwap AutoDisable decisions, Track raytrace semantics, and dedicated shield-break
  versus normal post-action markers.
- Focused tests passed with only the pre-existing `PointFindingKtTest` source excluded by a temporary uncommitted
  Gradle init filter. The normal repository test compilation remains blocked by its unrelated `math`/`Vec3d`
  mapping errors.

### Build/JAR hash

- Exact requested `clean assemble -x detekt` command completed with `BUILD SUCCESSFUL`.
- `build/libs/liquidbounce-0.31.2.jar` SHA-256:
  `f90d2a4214da3affb27e2947c21a1f85051e6c84550c610f242d809f948e3939`.
- JAR inspection confirmed `OutgoingPacketPostSendEvent`, ElytraSwap, MaceSwap, KillAura, and InputTracker classes.

### FCL status

- FCL/in-game and server-side Grim capture was unavailable in this headless Termux workspace. Build, focused tests,
  static packet-order checks, and JAR packaging do not establish runtime acceptance. FCL captures remain for
  StartFireWork order, all bind/timer modes, four AutoDisable configurations, Track visible/wall/None cases, and
  dedicated shield-break marker state.

### Remaining ElytraPlus blocker

- Full V-Core ElytraPlus remains intentionally unported. Its enabled-state dependency still affects Track's
  non-vanilla-Elytra look-point path, Aura acquisition `+4`, and Aura SmartCrit exemption. This task does not claim
  full dependency closure.

### Commit hashes

- Implementation: `d99ab4989` — `fix(elytra): correct final vcore runtime ordering`.
- Documentation: this append-only Agent1 record is committed separately after the implementation.

### Push result

- Implementation push succeeded: `12b9e69f5..d99ab4989  main -> main` on `origin`.
- Documentation push is pending the separate documentation commit created from this append-only record.

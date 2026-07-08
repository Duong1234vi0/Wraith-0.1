# Improvements Made

## Main problem addressed

The previous port guide told agents to “read the source” but did not force them to prove source behavior before editing. An agent could still invent settings, modes, fallbacks, visuals, or simplified logic and call it a port.

## Changes

- Added a strict authority/read-order document.
- Rewrote the general agent workflow with mandatory stop conditions.
- Rewrote the port guide around a zero-invention contract.
- Added a required pre-edit port evidence template.
- Added a post-edit source-fidelity audit.
- Added a dedicated render-porting guide covering geometry, blend, depth, light, render stages, Iris, bloom, GUI extraction, and texture alpha.
- Added current codebase rules derived from the uploaded source.
- Rebuilt the source path map from the current source snapshot.
- Generated a current 111-module registration catalog from `ModuleManager.registerInbuilt()`.
- Added a current source snapshot with file counts and verified render-event dispatch status.
- Added safe instructions for reading the large chronological `Agent1.md`.
- Prepended those authority/no-invention warnings to the improved Agent1 copy.
- Rewrote Termux/FCL work notes to separate compile verification from runtime proof.

## Important source findings encoded into the skills

- Runtime categories are only Combat, Player, Movement, Render, and Misc.
- Source folders do not determine runtime category.
- `WorldRenderEvent` and `OverlayRenderEvent` have live dispatch call sites in the uploaded source.
- `ScreenRenderEvent` and `DrawOutlinesEvent` are defined but no dispatch call site was found in the uploaded snapshot.
- `DrawContext` is currently aliased to `GuiGraphicsExtractor`.
- Multiple world render paths coexist and are not interchangeable, especially under Iris/shaderpacks.
- Fullbright, emissive, custom glow, and bloom are explicitly separated.

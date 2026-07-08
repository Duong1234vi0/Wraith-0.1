---
name: current-version-render-porting
scope: world render, HUD, GUI, shaders, particles, models, ESP
---

# Render Porting Guide

Use together with `CLAUDE_PORT_SKID_MODULE_GUIDE.md` for any visual/render port.

A render port is source-faithful only when it preserves both geometry and render state.

Two implementations can draw the same vertices and still look completely different because of blend, depth, light, texture alpha, render target, shader stage, or buffer flush timing.

## Mandatory render evidence matrix

Fill this before editing:

| Property | Source | Current target mapping |
|---|---|---|
| Coordinate space | | |
| Camera subtraction | | |
| Partial-tick interpolation | | |
| Matrix/transform order | | |
| Geometry/topology | | |
| Vertex format | | |
| Texture and alpha format | | |
| Color calculation | | |
| Blend function | | |
| Depth test | | |
| Depth write | | |
| Culling | | |
| Light/fullbright | | |
| Render event/stage | | |
| Render target/framebuffer | | |
| Shader/Iris path | | |
| Buffer flush/order | | |
| State cleanup | | |

If any row is unknown, keep investigating.

## Current world-render path

Observed in the uploaded source snapshot:

- `WorldRenderEvent` is dispatched by `MixinGameRenderer` during `GameRenderer.renderLevel`.
- The event contains a fresh `PoseStack` built from the current `modelViewMatrix`.
- Camera/projection matrices for world-to-screen are fed separately from `extractCamera`.

Do not assume an old source’s matrix semantics match this event.

Before subtracting camera position:

- inspect how neighboring working modules use the event matrix;
- inspect the event injection;
- determine whether source vertices are world absolute or camera relative.

Double camera subtraction causes drifting/offset geometry. Missing subtraction causes geometry to move with the camera incorrectly.

## Current GUI/HUD path

Observed:

- `OverlayRenderEvent` is fired from `Gui.extractRenderState` through `MixinInGameHud`.
- `DrawContext` is a typealias to `GuiGraphicsExtractor`.
- GUI rendering is extracted/batched through current GUI render state.

Rules:

- use the real event context;
- pass correct logical coordinates;
- use `context.pose()`/current GUI pose API where supported;
- use current scissor/render-state helpers;
- do not port old immediate GL GUI code;
- do not create a detached context whose render state is never flushed.

`ScreenRenderEvent` exists in the event package but no dispatcher was found in the uploaded source snapshot. Do not use it until a real call site is proven.

## Render path classes are not interchangeable

The current project contains multiple paths:

- vanilla `MultiBufferSource` / `RenderType`;
- module-owned `RenderPipeline` + `RenderType`;
- compat mesh submission;
- custom GUI `GuiElementRenderState` paths;
- legacy shader/framebuffer utilities;
- Iris-patched vanilla world pipelines.

For each path, inspect:

- which framebuffer/target receives the draw;
- whether Iris captures it;
- whether shaderpack bloom/tone mapping sees it;
- whether depth is shared;
- when the buffer is flushed;
- whether blend state calls are honored or ignored.

Do not choose a path only because its helper name contains `emissive`, `glow`, `outline`, or `shader`.

## Blend fidelity

Common blend behaviors:

- normal alpha: `SRC_ALPHA / ONE_MINUS_SRC_ALPHA`;
- additive-like: source weighted by alpha and destination added;
- pure additive: `ONE / ONE`;
- premultiplied alpha variants.

A source texture designed for additive rendering often has white/colored luminance on a black background with opaque alpha. Under normal alpha that can render a black square.

A texture designed for normal alpha needs transparent background pixels.

Do not change blend mode without inspecting texture channels and source shader/pipeline.

When adapting:

- preserve the source’s intended brightness accumulation;
- avoid introducing duplicate core/halo passes;
- verify overlapping sprites at close range;
- verify day/night and dark-background appearance.

## Depth fidelity

Record separately:

- depth test function;
- depth write enabled/disabled;
- through-wall setting;
- render order relative to entities/translucency.

“No depth write” is not the same as “no depth test.”

Do not disable depth merely to make a post-composite path easy.

## Fullbright, emissive, bloom, and glow are different

- **Fullbright**: object is not darkened by world lighting.
- **Emissive render path**: shader/render type may classify it specially.
- **Bloom**: post-process spreads bright pixels outside their geometry.
- **Client glow/outline**: separate custom effect; may be independent of shaderpack.

Never use these terms interchangeably in code or reports.

If the user wants the model/sprite bright but no surrounding aura:

- use a non-bloom fullbright/final-color path;
- do not feed the entire object into shaderpack HDR bloom;
- do not claim bloom was “fixed” if it was merely reduced.

## Iris/shaderpack work

When shader behavior matters, inspect available Iris/Sodium/shaderpack source before editing.

Prove:

- current draw path enters or bypasses Iris;
- program used for the RenderType;
- color attachment sampled by bloom;
- exact composite/final stage;
- whether depth is valid after that stage;
- whether buffer flush happens before or after composite.

Do not cycle through `eyes`, `beaconBeam`, `energySwirl`, or other RenderTypes by guesswork.

Do not patch the user’s shaderpack unless explicitly requested.

Do not globally disable shaderpack bloom to fix one module.

## GUI blur

Important distinction:

- vanilla GUI stratum blur affects a broad prior stratum;
- real glass panel blur requires scene capture, blur texture, rounded mask, and masked composite.

Never call fullscreen/stratum blur and describe it as per-panel rounded glass.

If real masked blur cannot be implemented safely:

- disable blur;
- use a translucent dark panel fallback;
- report the limitation honestly.

## Shader/custom pipeline ports

When porting custom GLSL:

1. Read vertex and fragment shaders completely.
2. Inventory all uniforms, samplers, attributes, defines, and included files.
3. Identify source framebuffer inputs/outputs.
4. Identify color space/HDR assumptions.
5. Map to the current shader/pipeline loader.
6. Preserve blend/depth/cull/write masks.
7. Verify resource reload/precompile behavior.
8. Verify Android/FCL shader compatibility.

Do not copy legacy `#version 120`/immediate-mode code into the current pipeline.

Port the algorithm and semantics, not obsolete API syntax.

## Model rendering ports

For entity/model effects, inspect:

- exact model source/layer;
- slim/default player model handling;
- pose and animation state;
- age/walk/head/body rotations;
- scale and root translation;
- texture selection;
- overlay/light values;
- model visibility parts;
- one-pass vs multi-pass behavior;
- lifecycle snapshot vs live entity state.

Do not render a generic oversized model when source captures the live player model state.

Do not render the model twice unless source does.

## Billboard/particle ports

Inspect:

- sample count;
- point spacing;
- camera-facing quaternion;
- roll;
- size progression;
- alpha progression;
- color progression;
- texture footprint;
- overlap assumptions;
- normal vs additive blending;
- sorting and depth.

Adding a second smaller `HotCore` sprite at every point can expose the source texture as a chain of circles even when the larger sprites overlap smoothly. Extra passes require source evidence.

## HUD ports

Preserve:

- logical bounds;
- drag/hit test;
- saved position semantics;
- scale origin;
- text baseline and metrics;
- clipping during animation;
- render order;
- shared material architecture.

A visual port should not silently change element data/behavior.

## Performance

Performance adaptation is allowed only when behavior remains equivalent.

Prefer:

- batching;
- reusable buffers/resources;
- no per-frame texture creation;
- no per-element fullscreen pass;
- early alpha/visibility checks.

Do not reduce particle count, animation samples, model detail, or effect passes and still call it 1:1 unless source behavior remains visually equivalent and the deviation is disclosed.

## Render verification checklist

Test at minimum:

- near and far distance;
- multiple camera angles;
- first/third person where relevant;
- day and night;
- shaderpack on/off;
- through-wall/depth option;
- target movement/teleport;
- world change;
- overlapping particles/models;
- GUI scale/HUD scale;
- FCL/MobileGlues;
- state leak into later rendering.

## Final render report

Include exact source and current files, plus:

- render event/stage;
- geometry;
- blend;
- depth;
- cull;
- light;
- texture alpha;
- target/framebuffer;
- Iris/shader interaction;
- deviations;
- runtime tests not yet performed.

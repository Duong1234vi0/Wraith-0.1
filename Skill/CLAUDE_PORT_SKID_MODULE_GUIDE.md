---
name: strict-source-faithful-module-port
scope: LiquidBounce fork module/mode/utility ports
mode: strict-by-default
---

# Strict Source-Faithful Porting Guide

Use this guide whenever the user asks to:

- port a module or mode;
- skid/copy behavior from another client;
- move a feature across Minecraft versions;
- replace a current implementation with a reference implementation;
- reproduce a render, movement, combat, packet, HUD, or utility behavior from supplied source.

## Core contract

A port is not a creative implementation task.

> Reproduce the source’s observable behavior while adapting only the framework/API layer required by the current repository.

The default is **strict port**.

Do not “modernize”, “improve”, “make safer”, “add flexibility”, or “approximate” unless the user explicitly asks for that after the faithful baseline exists.

## Source-of-truth split

- **User request** controls scope and intended replacement/coexistence.
- **Reference source** controls behavior being ported.
- **Current repository** controls available APIs, mappings, architecture, and integration.
- **Build files/mappings** control the target version.
- **Agent1.md** is historical evidence, not the current implementation.

## Zero-invention rule

Unless explicitly present in the source or requested by the user, do not add:

- modes;
- settings;
- changed defaults, ranges, steps, units, or visibility conditions;
- aliases or tags;
- notifications or chat output;
- sounds or previews;
- extra particles, glow, blur, outlines, bloom, animations, safety checks, or visual passes;
- fallback targets or fallback modes;
- heuristics;
- “mobile optimizations” that change behavior;
- anti-cheat bypass logic;
- extra state resets;
- extra target filters;
- extra resource substitutions;
- duplicate render/model/core passes;
- a new manager or renderer when the source does not require one.

If an adaptation would change observable behavior, it is not a framework adaptation. It is a deviation and requires approval.

## Unknown-is-not-permission rule

When source behavior is unclear or a dependency is missing:

1. search deeper;
2. inspect callers and helpers;
3. inspect assets/shaders/mixins;
4. inspect decompiled bytecode if that is the only source;
5. report the missing evidence.

Do not fill gaps with “what this type of module usually does.”

## Mandatory pre-edit gate

Before modifying the current repository, produce an internal port specification using `PORT_EVIDENCE_TEMPLATE.md`.

At minimum, know all of the following.

### A. Source inventory

- module/mode entrypoint;
- superclass/interfaces;
- settings and visibility rules;
- event/callback entrypoints;
- state fields;
- enable/disable/world-change behavior;
- helper methods;
- managers/utilities;
- mixins/accessors;
- packets;
- assets/textures/sounds/shaders;
- registrations;
- cross-module dependencies.

Read complete relevant methods. Search snippets are not enough.

### B. Observable behavior contract

Describe what a user/server/render pipeline can observe:

- trigger conditions;
- timing and event phase;
- target selection;
- calculations/formulas;
- actions performed;
- skip/exemption conditions;
- ordering;
- duration/lifetime;
- rendering appearance and state;
- cleanup/reset;
- interaction with other modules.

### C. Current-project mapping

For every source dependency, identify the exact current equivalent or mark it missing.

Examples:

| Source concept | Current equivalent | Evidence | Behavior-equivalent? |
|---|---|---|---|
| Update event | `GameTickEvent` | current mixin/event call site | yes/no |
| Render callback | `WorldRenderEvent` | `MixinGameRenderer` injection | yes/no |
| Mode system | `ChoiceConfigurable` | current module examples | yes/no |
| Old packet | current Mojang packet class | constructor/call semantics | yes/no |

### D. Deviation ledger

Start with:

```text
Deviations: NONE
```

Add an entry only when strict equivalence is impossible.

Every deviation must include:

- source behavior;
- target limitation;
- proposed replacement;
- user-visible impact;
- whether user approval was obtained.

Do not hide deviations in comments or final prose.

## Port fidelity levels

Use these labels accurately.

### Level 1 — Strict behavioral port

- same settings/defaults;
- same logic/state/timing;
- same observable behavior;
- framework-only API adaptations;
- no invented features.

This is the default meaning of “port.”

### Level 2 — Constrained adaptation

Used only when the target version lacks an equivalent API but behavior can be closely reproduced.

Requires an explicit deviation ledger.

### Level 3 — Reimplementation inspired by source

Behavior or design materially differs.

Do not call this a faithful port. Get user approval before implementing.

## Required source traversal

Do not read only the module class.

Recursively inspect every dependency that contributes behavior.

### Settings

Check:

- names;
- types;
- defaults;
- min/max;
- step/precision;
- suffix/units;
- mode-specific visibility;
- config serialization names;
- tag behavior.

Do not flatten per-mode settings into the module root if the source scopes them per mode.

In this project, separate mode behavior normally belongs in `Choice` implementations under a `ChoiceConfigurable` when modes have different state/settings/events.

### Events and timing

Check:

- exact source event;
- pre/post stage;
- tick vs movement tick vs render frame;
- event priority;
- cancellation behavior;
- sequence/coroutine timing;
- client/render/network thread;
- when buffers are flushed.

A callback with a similar name may run at a different stage.

### State machine

Record:

- every mutable field;
- initialization;
- transition conditions;
- decay/interpolation;
- reset on disable;
- reset on world change/disconnect;
- reset on target/mode change;
- whether state persists intentionally.

Do not add or remove resets casually. Both can alter source behavior.

### Helpers/managers

Open the implementation, not just the signature.

Prove equivalence for:

- target filtering;
- rotations/GCD;
- movement correction;
- inventory slot switching;
- packet queueing;
- click scheduling;
- interpolation/easing;
- color math;
- geometry generation;
- resource registration;
- blend/depth/light behavior.

### Resources

Inventory exact:

- paths;
- namespace;
- dimensions;
- alpha format;
- sound event registration;
- shader files;
- metadata;
- expected blend mode.

Do not invent or substitute assets silently.

### Mixins/accessors

If source behavior relies on an injection or private field:

- locate the source injection target;
- identify the current-version target;
- verify method descriptor/field semantics;
- inspect existing mixins for conflicts;
- register the new mixin/access widener only if required.

Do not move mixin-dependent behavior into a periodic event unless timing remains equivalent.

## Current codebase integration rules

Read `CODEBASE_RULES.md` for details. The most important rules are below.

### Module shape

- Current modules normally extend `ClientModule`.
- Runtime categories are only `Combat`, `Player`, `Movement`, `Render`, and `Misc`.
- Source folder names such as `exploit`, `world`, or `client` do not define runtime category.
- Register built-ins in `ModuleManager.registerInbuilt()`.
- Add fallback description translations where the project requires them.

### Modes

Use the architecture that preserves source separation.

- Simple stateless selection can use an enum value.
- Modes with their own settings/state/handlers should use `Choice` objects.
- Do not implement multiple source modes as one branch with renamed labels.
- Do not alias a requested mode to an existing mode unless source behavior is actually identical.

### Handlers

- Event handlers are generally registered as properties through `handler<T>`.
- `running` is inherited through module/choice parent state.
- Match source timing and priority; do not pick `GameTickEvent` by habit.

### Config loading

- Avoid side effects during config loading.
- Preserve aliases only when needed for an actual rename/config migration.
- Do not add aliases as decoration.

### Registration and resources

A port is incomplete if it omits required:

- module registration;
- mixin registration;
- access widener entries;
- resources/assets;
- `sounds.json` or equivalent;
- translations;
- config migration.

## Domain-specific fidelity checks

## Combat and targeting

Map exactly:

- candidate source;
- target filters;
- range calculation;
- sort/priority;
- lock/switch behavior;
- line of sight;
- friend/team/bot handling;
- attack timing;
- cooldown;
- rotation request and priority;
- silent vs visible rotations;
- packet/action order.

Do not reuse the current project’s generic target tracker unless it matches the source contract.

## Movement

Map exactly:

- input event stage;
- movement vector math;
- on-ground/collision conditions;
- velocity modification order;
- sprint/jump state;
- server packet state;
- tick counters;
- environmental exceptions;
- reset behavior.

Do not convert packet/tick state machines into direct velocity edits.

## Inventory and packets

Map exactly:

- slot numbering;
- inventory/container context;
- silent/visible switch;
- packet type;
- packet sequence;
- action ID/sequence IDs;
- delay units;
- server acknowledgment;
- restoration timing.

A compile-compatible packet sequence can still be behaviorally wrong.

## Rendering

Read `RENDER_PORTING_GUIDE.md`.

At minimum record:

- coordinate space;
- camera offset;
- interpolation;
- transform order;
- geometry;
- texture and alpha representation;
- blend function;
- depth test/write;
- culling;
- light/fullbright;
- render stage/target;
- shader/Iris interaction;
- buffer flush order;
- cleanup/state restoration.

Do not replace source additive rendering with normal alpha or vice versa without noting the visual change.

## HUD/GUI

Map:

- logical vs physical coordinates;
- GUI scale;
- actual `DrawContext`/GUI render state;
- pose stack type used by the current version;
- clipping/scissor;
- z/order/strata;
- text metrics/font;
- hit testing and drag bounds;
- animation timing;
- resource batching.

Do not use a fake/throwaway GUI context.

## Audio

Map:

- exact source asset;
- trigger timing;
- volume curve;
- pitch;
- category/UI/world playback;
- duplicate suppression;
- platform compatibility.

Do not use desktop `javax.sound.sampled` on Android/FCL when Minecraft native audio is available, but preserve source behavior through native APIs.

## Adaptation rules

A framework adaptation is acceptable only if all observable behavior remains equivalent.

### Acceptable examples

- `MatrixStack` → current `PoseStack` with identical transform order;
- old entity position interpolation → current equivalent interpolation;
- old event bus callback → current event fired at the same semantic stage;
- legacy setting class → current DSL with same name/default/range/visibility;
- old sound loader → native sound event using the same asset/volume/pitch;
- old buffer API → current pipeline with the same vertex data and render state.

### Unacceptable examples

- “source blur is difficult, so use fullscreen blur”;
- “source has a missing helper, so use the nearest local mode”;
- “add a HotCore/Intensity option to make it look better”;
- “add fallback nearest-player targeting”;
- “simplify packet order to one interaction call”;
- “remove a condition because it causes compile errors”;
- “add randomized timing for safety”;
- “replace source asset with a generated one.”

## Implementation sequence

1. Freeze scope.
2. Complete source inventory.
3. Complete behavior contract.
4. Complete dependency graph.
5. Complete source-to-current mapping.
6. Record deviations.
7. Identify files to change.
8. Implement framework shell.
9. Port state/math/conditions literally where possible.
10. Port resources and registrations.
11. Walk lifecycle and duplicate paths.
12. Compare final implementation against the source line by line/method by method.
13. Run static searches.
14. Build.
15. State precise runtime tests.

Do not implement while still discovering basic source behavior.

## Post-edit source-fidelity audit

After coding, answer all of these:

- Is every source setting present with the same default/range/visibility?
- Did any target/current setting appear that source does not have?
- Is every source handler represented?
- Is event timing equivalent?
- Are all mutable fields and resets represented?
- Are formulas/constants/units preserved?
- Are helper semantics equivalent?
- Are all source resources included?
- Is any old path still executing in parallel?
- Did the port add extra render/model/sprite/packet passes?
- Did any compile fix delete behavior?
- Are deviations listed honestly?

Use `PORT_REVIEW_CHECKLIST.md` for the full audit.

## Verification standard

### Build

Run the project-supported build command. Build success proves only that the code compiles/packages.

### Runtime

Give tests covering:

- enable/disable;
- each ported mode;
- default settings;
- edge cases;
- world change/reconnect;
- target/mode switch;
- shaderpack/no shaderpack where relevant;
- mobile/FCL if relevant;
- duplicate actions/effects;
- performance/state leaks.

### Comparison

When feasible, compare source and port using the same scenario/settings.

## Mandatory final report

Use this structure.

### Reference files read

List exact source files, methods, assets, shaders, and helpers.

### Source behavior reproduced

Describe the source state/event/math/render/network path.

### Mapping

| Source behavior | Current implementation |
|---|---|
| ... | ... |

### Changed files

List current-repository files only.

### Deviations

`None`, or exact approved/unavoidable deviations.

### Verification

Build command/result and runtime status separately.

### Runtime test

Concrete steps and expected result.

Do not write a generic report such as “ported the module and improved compatibility.”

## Stop condition

If the strict port cannot be completed without inventing behavior, stop and say exactly what is missing.

A smaller honest port or a blocked report is better than a convincing fabricated implementation.

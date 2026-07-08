# Post-Port Source-Fidelity Review

Run this after implementation and before claiming completion.

## Scope

- [ ] Only the opened current repository was modified.
- [ ] Reference sources/archives were read-only.
- [ ] Unrelated modules/utilities were not refactored.
- [ ] User-confirmed working behavior remains.

## Source coverage

- [ ] Exact source entrypoint was read completely.
- [ ] All behavior-affecting helpers were read.
- [ ] Required mixins/accessors/managers were read.
- [ ] Required assets/shaders/sounds were inspected.
- [ ] Source call sites were checked where semantics were unclear.

## Settings and identity

- [ ] Name/category match requested behavior.
- [ ] Settings names/types/defaults/ranges/steps/units match.
- [ ] Visibility conditions match.
- [ ] Per-mode settings remain per mode.
- [ ] No extra settings/modes/tags/aliases were invented.
- [ ] Config migration is deliberate, not decorative.

## Logic

- [ ] Trigger conditions match.
- [ ] Skip/exemption conditions match.
- [ ] Formulas/constants/units match.
- [ ] Randomness distribution and frequency match.
- [ ] Target selection/filter/sort/lock behavior match.
- [ ] Ordering and timing match.
- [ ] No local helper was assumed equivalent by name alone.

## State and lifecycle

- [ ] Every source state field has an equivalent or justified omission.
- [ ] Initial state matches.
- [ ] Enable/disable behavior matches.
- [ ] World change/disconnect behavior matches.
- [ ] Target/mode switch resets match.
- [ ] No stale state survives unintentionally.
- [ ] No extra reset changes observable behavior.

## Event/network/thread

- [ ] Equivalent event stage is used.
- [ ] Priority/order is correct.
- [ ] Cancellation semantics match.
- [ ] Packet classes and order match.
- [ ] Delays use the same units/tick semantics.
- [ ] Code runs on an appropriate thread.

## Rendering

- [ ] Coordinate space and camera offset match.
- [ ] Partial-tick interpolation matches.
- [ ] Transform order matches.
- [ ] Geometry and texture match.
- [ ] Texture alpha format matches blend expectations.
- [ ] Blend function matches.
- [ ] Depth/cull/light state matches.
- [ ] Render stage/target matches.
- [ ] No duplicate base/core/model/halo pass was introduced.
- [ ] Render state is restored.
- [ ] Shader/Iris behavior is intentional and evidenced.

## Integration

- [ ] Module is registered once.
- [ ] Mixins are registered once.
- [ ] Resources are packaged.
- [ ] Translation keys exist where required.
- [ ] Old implementation does not execute in parallel.
- [ ] Imports/dead scaffolding are cleaned.

## Invention audit

Search the final patch specifically for additions not present in source:

- [ ] No invented fallback.
- [ ] No invented heuristic.
- [ ] No invented preview/sound/notification.
- [ ] No invented effect/glow/animation.
- [ ] No invented safety gate.
- [ ] No “quality improvement” disguised as adaptation.
- [ ] Every deviation is listed and approved.

## Verification

- [ ] Final diff inspected.
- [ ] Stale references searched.
- [ ] Build succeeds.
- [ ] Build success is not presented as runtime proof.
- [ ] Runtime test steps are specific.
- [ ] Known limitations are explicit.

## Required final sentence

The final report should be able to state truthfully:

> The port reproduces the inspected source behavior. Framework adaptations are listed, and no unrequested behavior was added.

If that sentence is not true, do not call the result a faithful port.

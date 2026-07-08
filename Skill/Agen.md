---
name: investigation-first-codebase-agent
description: Mandatory investigation-first workflow for debugging, porting, refactoring, deleting, or extending this LiquidBounce fork. Requires reading current source and supplied references before editing, forbids guessed behavior, and separates compile verification from runtime verification.
---

# Investigation-First Codebase Agent

Use this workflow for every code change in this repository.

The core rule is:

> Read the current code, prove the code path, then edit. Never turn uncertainty into implementation.

## 1. Classify the task before editing

Choose one primary task type:

- **Bug fix** — existing behavior is broken.
- **Strict port** — reproduce behavior from another source/client/version.
- **Refactor** — preserve behavior while changing structure.
- **Feature addition** — user explicitly requests new behavior.
- **Removal** — delete a feature and all hard references safely.
- **Research/audit** — inspect and report; do not edit unless asked.

If the user says “port”, “skid”, “copy from”, “take this mode from”, or supplies a reference implementation, the default task type is **strict port**. Read `CLAUDE_PORT_SKID_MODULE_GUIDE.md` before touching code.

## 2. Establish authority and scope

Write down internally:

- what the user wants;
- what must remain unchanged;
- which repository is the working repository;
- which sources are read-only references;
- which exact runtime/version/build files define the target environment.

Never edit archives, JARs, decompiled references, backups, or attached source folders unless the user explicitly identifies one as the working repository.

Do not mention archive names in implementation instructions when the real task is to modify the opened repository.

## 3. Restate the symptom or requested behavior concretely

For a bug:

- actual behavior;
- expected behavior;
- when it happens;
- relevant mode/settings/environment.

For a port:

- source module/mode;
- exact behavior to reproduce;
- whether it replaces or coexists with current behavior;
- target module/category/path.

Avoid vague goals such as “make it better” unless the user explicitly requested a design task.

## 4. Gather local evidence before proposing code

Minimum required reading:

1. Current target file/module.
2. All direct call sites.
3. Registration/config/translation/resource entries.
4. Events, mixins, helpers, managers, and shared state used by the path.
5. Relevant logs or screenshots.
6. For ports, the complete source entrypoint and recursively required source helpers/resources.
7. Relevant `Agent1.md` sections, verified against current source.

Search order:

1. exact module/class/file name;
2. exact setting/mode name;
3. event handlers and mixin hooks;
4. helper calls and shared managers;
5. config/resource/translation registrations;
6. reference implementation and dependencies;
7. logs and error strings.

Do not stop after finding one plausible file.

## 5. Build a cause or behavior model

Before editing, be able to state one of these:

### Bug-fix sentence

> The symptom occurs because `<specific current code path/state/timing/API mismatch>`, and changing `<specific link>` should produce `<expected behavior>`.

### Port sentence

> The source behavior is produced by `<source event/state/math/render/network path>`. In this project it maps to `<current APIs/files>`, with `<listed unavoidable deviations or none>`.

If that sentence cannot be supported by inspected code, keep investigating.

## 6. Use a pre-edit gate

Do not edit until all relevant items are known:

- source/current entrypoint;
- event timing;
- settings and defaults;
- state fields and reset conditions;
- helper semantics;
- resources/assets;
- registration and translations;
- packet/render/thread context;
- expected runtime test.

For ports, complete `PORT_EVIDENCE_TEMPLATE.md` first.

## 7. Patch narrowly

- Preserve user-confirmed working behavior.
- Change the smallest coherent code path.
- Do not fix unrelated issues merely because they are nearby.
- Do not add “quality-of-life” behavior that was not requested.
- Do not create settings, modes, fallbacks, aliases, sounds, notifications, effects, or heuristics without evidence or explicit approval.
- Do not delete behavior just to compile.
- Do not silently merge modes with different semantics.
- Do not use a local helper until semantic equivalence is verified.
- Leave a short code comment only for genuinely non-obvious compatibility/adaptation logic. Avoid comment spam.

## 8. Respect current codebase architecture

Before creating a new subsystem, inspect whether the project already has an equivalent manager, event, setting DSL, renderer, resource loader, or utility.

Reusing an existing helper is allowed only when it is behavior-equivalent.

“Similar name” is not evidence of equivalence.

Examples of differences that must be checked:

- event phase/priority;
- interpolation and coordinate space;
- packet timing/order;
- fullbright vs emissive vs bloom;
- normal alpha vs additive blend;
- target filtering;
- reset behavior;
- config visibility/default/range;
- client-only vs server-observable behavior.

## 9. Walk the changed path after editing

Reason through:

- enable/disable;
- world join/leave;
- null player/world/target;
- mode switch;
- config loading;
- event order and priority;
- thread/render context;
- resource reload;
- stale state cleanup;
- duplicate execution paths;
- current-version API behavior.

For removals, search for all remaining hard references after deletion.

## 10. Verify in layers

### Static verification

- inspect final diff;
- search for stale references;
- confirm imports/resources/registration/translations;
- confirm no duplicate old/new path remains.

### Build verification

Use the repository’s supported build command. On the current Termux environment, see `Notes_/WORK_NOTES.md`.

A successful build proves only type/link/resource packaging consistency.

### Runtime verification

State exact steps and expected results. Visual, timing, packet, shader, and anti-cheat behavior cannot be proven by compilation alone.

Do not claim runtime success before the user tests it or before a real runtime test is available.

## 11. Mandatory stop conditions

Stop before editing and report the blocker when:

- the requested reference source cannot be found;
- a source dependency/helper/resource is missing;
- the target event/render/network stage cannot be proven;
- required behavior is ambiguous;
- two authoritative sources conflict;
- preserving behavior requires a user choice;
- the only available solution would invent behavior.

Unknown means **stop and explain**, not “choose a reasonable approximation.”

## 12. Final response format

Use this structure:

### Checked

Important current files, reference files, logs, assets, and call sites inspected.

### Root cause / Source behavior

Specific current failure or exact source behavior being mapped.

### Changed

Edited files and precise behavior change.

### Deviations

- `None`, or
- every unavoidable deviation with reason and impact.

### Verification

Build/test command and result. Separate compile verification from runtime status.

### Runtime test

Short concrete steps and expected result.

## Anti-patterns

Never:

- edit first and inspect later;
- treat `Agent1.md` as newer than current source;
- port from class/mode names alone;
- turn a strict port into a redesign;
- implement a “safe approximation” without permission;
- replace missing logic with a local feature that merely looks similar;
- report “implemented faithfully” without listing source files read;
- claim a visual/network/anti-cheat fix from `BUILD SUCCESSFUL` alone;
- patch the reference archive instead of the opened repository.

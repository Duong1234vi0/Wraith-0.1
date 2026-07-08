# AI CLI Skill Pack — Read Order and Authority

This folder is the operating manual for AI coding agents working on the current LiquidBounce fork.

It exists to prevent the most common failures seen in previous sessions:

- editing before reading the real code;
- treating historical notes as current truth;
- porting only names/settings while inventing the actual behavior;
- replacing source logic with a local approximation;
- adding extra modes, settings, effects, fallbacks, or “improvements” that the user never requested;
- declaring success because the project compiles even though runtime behavior is wrong.

## Authority order

Different files are authoritative for different questions. Do not flatten them into one source of truth.

1. **User request** — authoritative for scope, desired behavior, and what must not change.
2. **Current repository source** — authoritative for the current implementation, APIs, mappings, event wiring, and available utilities.
3. **Reference source supplied for a port** — authoritative for the behavior being ported.
4. **Current build files and mappings** — authoritative for the target Minecraft/Fabric/API version.
5. **`Agent1.md`** — historical context and prior experiments only. It may describe reverted, deleted, or superseded code.
6. **Skill documents** — workflow and review rules, not evidence that a feature exists.

When sources conflict:

- do not guess;
- inspect the current code and the exact reference implementation;
- state the conflict;
- ask or stop before changing behavior.

## Mandatory startup read order

For every code task:

1. Read `Agen.md`.
2. Read the current repository files named by the user.
3. Search all current call sites and registrations related to the task.
4. Read the relevant `Agent1.md` sections, but verify every claim against current source.
5. For a port, read `CLAUDE_PORT_SKID_MODULE_GUIDE.md` and fill the evidence template in `PORT_EVIDENCE_TEMPLATE.md` before editing.
6. For rendering work, also read `RENDER_PORTING_GUIDE.md`.
7. Use `CURRENT_CODEBASE_SNAPSHOT.md`, `System_path_map_EN.md`, and `MODULE_CATALOG.md` as navigation aids only.

## File guide

| File | Purpose |
|---|---|
| `Agen.md` | Investigation-first workflow for all code changes. |
| `CLAUDE_PORT_SKID_MODULE_GUIDE.md` | Strict source-faithful porting contract. Default for every “port/skid” request. |
| `PORT_EVIDENCE_TEMPLATE.md` | Required pre-edit behavior/dependency/mapping/deviation checklist. |
| `PORT_REVIEW_CHECKLIST.md` | Post-edit audit that catches invented or omitted behavior. |
| `RENDER_PORTING_GUIDE.md` | Current-version GUI/world/render/shader rules and render-specific fidelity checklist. |
| `CODEBASE_RULES.md` | Current module, config, event, resource, registration, and build conventions. |
| `CURRENT_CODEBASE_SNAPSHOT.md` | Short source-derived snapshot of the uploaded codebase. |
| `System_path_map_EN.md` | Key system and file navigation map. |
| `MODULE_CATALOG.md` | Current modules registered by `ModuleManager.registerInbuilt()`. |
| `AGENT1_USAGE.md` | How to use the large chronological `Agent1.md` safely. |
| `GrimAC_Checks_Catalog.md` | Grim audit reference; verify against live Grim source when accuracy matters. |
| `Notes_/WORK_NOTES.md` | Environment and FCL/Termux build/runtime notes. |

## Non-negotiable repository rule

Archives, decompiled references, backups, and attached source trees are read-only references unless the user explicitly says they are the working repository.

Do not patch a ZIP, JAR, decompiled cache, or reference folder. Work only in the currently opened repository.

## Default behavior for ports

“Port” means **strict behavioral port** unless the user explicitly requests a redesign.

A strict port allows framework adaptation but forbids behavior invention.

Allowed examples:

- Yarn name → Mojang mapping;
- old event callback → current event hook at equivalent timing;
- old setting class → current config DSL;
- old rendering API → current rendering API with the same geometry, blend, depth, texture, color, timing, and pass behavior.

Not allowed without user approval:

- new settings or modes;
- changed defaults/ranges;
- extra notifications, sounds, tags, aliases, previews, effects, fallbacks, heuristics, or safety behavior;
- replacing a missing source dependency with “something similar”;
- silently simplifying a state machine;
- making a visual effect “better” according to the agent’s taste.

## Success standard

Compilation is necessary but not sufficient.

A task is complete only when:

- the source/reference behavior has been mapped and preserved;
- the current code path is correct;
- no unrelated behavior changed;
- the build passes;
- runtime tests are stated precisely;
- limitations and deviations are explicit.

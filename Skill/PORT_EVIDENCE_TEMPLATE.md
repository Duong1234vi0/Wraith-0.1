# Port Evidence Template — Complete Before Editing

Copy this into the agent's scratch/work notes and fill every relevant field.

Do not edit current source until this template is sufficiently complete.

## 1. Request and scope

- Requested source module/mode:
- Target current module/path:
- Replace or coexist:
- Behavior explicitly requested:
- Behavior explicitly out of scope:
- User-confirmed working behavior that must remain:

## 2. Working repository and references

- Working repository root:
- Current target version/build evidence:
- Read-only reference root(s):
- Historical notes consulted:
- Missing references, if any:

## 3. Exact source files read

| File | Class/method/asset | Why it affects behavior |
|---|---|---|
| | | |

Do not list a directory as if every file was read.

## 4. Source behavior contract

### Module identity

- Name:
- Category:
- Bind/default state:
- Aliases/tag:

### Settings

| Name | Type | Default | Range/step/units | Visibility condition | Source evidence |
|---|---|---:|---|---|---|
| | | | | | |

### Trigger/event timing

| Source event/hook | Phase/priority/thread | Conditions | Action |
|---|---|---|---|
| | | | |

### State

| Field | Initial value | Updated when | Reset when | Purpose |
|---|---|---|---|---|
| | | | | |

### Logic and formulas

- Target/candidate source:
- Filters/skip cases:
- Sort/priority:
- Core formulas/constants:
- Timing/delay units:
- Ordering requirements:
- Randomness source/distribution:
- Lifecycle:

### Dependencies

| Source dependency | Used behavior | Complete implementation read? |
|---|---|---|
| | | yes/no |

### Resources

| Resource | Source path | Format/dimensions | Expected usage/blend/playback |
|---|---|---|---|
| | | | |

## 5. Current-project integration evidence

### Current files read

| File | Relevant API/path |
|---|---|
| | |

### Source-to-current mapping

| Source concept | Current equivalent | Semantic evidence | Equivalent? |
|---|---|---|---|
| | | | yes/no/unknown |

### Registration/config/resources

- Module registration:
- Mixin registration:
- Access widener:
- Translation:
- Resource namespace/path:
- Config migration/aliases:

## 6. Domain-specific contract

Fill the applicable section.

### Render

- Coordinate space:
- Camera subtraction:
- Partial-tick interpolation:
- Transform order:
- Geometry/vertex layout:
- Texture/alpha representation:
- Blend function:
- Depth test/write:
- Culling:
- Light/fullbright:
- Render event/stage:
- Render target:
- Shader/Iris interaction:
- Buffer flush/order:
- State cleanup:

### Network/inventory

- Packet classes:
- Packet/action order:
- Sequence/action IDs:
- Slot mapping:
- Delay/tick timing:
- Restore behavior:
- Server acknowledgements:

### Movement/combat

- Input stage:
- Rotation stage/priority:
- Movement correction:
- Attack/interact timing:
- Cooldown:
- On-ground/collision gates:
- Anti-cheat relevant packet state:

### GUI/HUD

- Draw context:
- Matrix/pose type:
- Logical/physical coordinates:
- GUI scale:
- Scissor/clipping:
- Hit testing:
- Font/text metrics:
- Layer/stratum:

## 7. Deviation ledger

Start with:

```text
Deviations: NONE
```

If not none:

| Source behavior | Target limitation | Proposed deviation | User impact | Approved? |
|---|---|---|---|---|
| | | | | yes/no |

Do not edit through an unapproved behavior-changing deviation.

## 8. Planned current-repository changes

| Current file | Exact intended change | Source behavior mapped |
|---|---|---|
| | | |

## 9. No-invention confirmation

Confirm before editing:

- [ ] No new modes.
- [ ] No new settings/default changes.
- [ ] No extra effects/sounds/notifications/tags/aliases.
- [ ] No fallback/heuristic not present in source.
- [ ] No source mode aliased to unrelated current logic.
- [ ] No behavior removed merely to compile.
- [ ] Missing dependencies are not being guessed.

## 10. Verification plan

- Static searches:
- Build command:
- Runtime environment:
- Test scenario:
- Expected source-equivalent result:
- Edge cases:
- Regression checks:

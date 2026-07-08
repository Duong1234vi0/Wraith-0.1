# How to Use `Agent1.md` Safely

`Agent1.md` is a chronological engineering journal. It is useful, but it is not a clean current-state specification.

It contains:

- successful fixes;
- failed experiments;
- later reverts;
- deleted modules;
- temporary workarounds;
- source paths that may no longer exist;
- conclusions tied to older snapshots;
- multiple agents changing the same feature over time.

## Mandatory rule

> Current repository source wins over `Agent1.md` for current state.

For a port, the supplied reference source wins for source behavior.

## Safe reading procedure

1. Search `Agent1.md` for the exact module/mode/error.
2. Read all later sections for the same topic, not only the first match.
3. Mark entries containing words such as:
   - `FAILED`
   - `REVERTED`
   - `DELETED`
   - `attempt`
   - `not yet tested`
   - `in progress`
4. Open the current source files and verify what actually remains.
5. Use Agent1 only to explain why current code may look unusual or which approaches already failed.

## Do not do this

- Do not copy code described in an old note without checking current source.
- Do not treat “BUILD SUCCESSFUL” in Agent1 as proof the feature worked in game.
- Do not assume the last chronological note is current if the uploaded source snapshot differs.
- Do not use old file paths as patch targets without locating the live file.
- Do not resurrect deleted behavior merely because it is documented.

## What Agent1 is best for

- FCL/Android gotchas;
- prior crash causes;
- render API discoveries;
- failed approaches to avoid repeating;
- user preferences confirmed during testing;
- build commands/environment constraints;
- rationale behind compatibility code.

## What Agent1 is not authoritative for

- current module list;
- current settings;
- current source file contents;
- current event dispatch;
- current render stage;
- whether an experimental path still exists;
- reference-client behavior.

## Recommended note format for future additions

Append new entries using:

```md
## <Feature/task> — <date, agent>

### Request

### Current files checked

### Reference files checked

### Root cause / source behavior

### Changed

### Deviations

### Verification

### Runtime status

### Supersedes / reverts
```

Always state whether an entry supersedes or reverts an earlier entry.

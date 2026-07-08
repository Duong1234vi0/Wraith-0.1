# Work Notes — Termux / FCL / Android

These are environment notes, not current-feature specifications. Verify the live repository and current logs.

## Build

The historical working Termux command is:

```sh
env \
  JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk \
  PATH=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk/bin:$PATH \
  sh ./gradlew --no-daemon assemble -x detekt
```

Notes:

- Plain `java` may resolve to the wrong JDK.
- `runClient` is not a reliable verification path on Termux because native desktop libraries may be unavailable.
- After deleting source/resources, use `clean assemble` when Gradle incorrectly treats stale outputs as up to date.
- Determine the actual output JAR name from the live build rather than assuming the historical versioned name.

## Verification boundary

`BUILD SUCCESSFUL` does not prove:

- mixin injection works at runtime;
- the module renders visibly;
- Iris/shader interaction is correct;
- packet/tick behavior matches source;
- Android native resources load;
- performance is acceptable;
- anti-cheat behavior is correct.

The user performs final in-game tests on FCL.

## Logs

- Read the newest game log and crash report.
- Find the first meaningful `Caused by`, not only the final launcher message.
- FCL “Network Protocol Error” can wrap client-side initialization/mixin exceptions.
- Very large logs should be searched/tail-read rather than loaded fully.
- The project error handler may log a generic/null message while the true cause is earlier in the Minecraft log.

## Source/history precedence

- Current repository source is authoritative.
- `Agent1.md` is chronological history and can describe reverted/deleted code.
- Build output from an old Agent1 section does not prove the current snapshot contains that implementation.

## GUI/FCL cautions

- Avoid desktop AWT paths that require a display device; Android/FCL can throw `HeadlessException`.
- Prefer headless-safe font metrics and Minecraft-native GUI/audio/resource APIs.
- Use the real GUI extraction context provided by the current overlay event.
- Do not create throwaway GUI contexts.

## Texture cautions

- Dynamic texture loading should use valid formats supported by current `NativeImage` on Android.
- Historical tests found grayscale PNGs problematic; RGBA is the safe baseline for dynamic client resources.
- White-on-black additive art with opaque alpha can render as a black square under normal alpha blending.
- Do not convert shared textures without checking all consumers and intended blend modes.

## Render cautions

- Current world/GUI rendering uses newer GPU/render-state APIs; old GL20/GL30 framebuffer code may compile through wrappers yet be dead or incorrect.
- Verify whether a render helper’s path is actually called.
- `blurBeforeThisStratum()` is not a rounded-panel mask blur.
- Fullbright, emissive, custom glow, and shaderpack bloom are different effects.
- Iris-visible and client-owned render paths may target different stages/framebuffers.

## Event cautions

- Event class existence does not prove dispatch.
- In the uploaded snapshot, live call sites were found for `WorldRenderEvent` and `OverlayRenderEvent`.
- No call site was found for `ScreenRenderEvent` or `DrawOutlinesEvent`; re-check live source before use.

## Removing modules

Deleting the module file is not enough.

Search for:

- `ModuleManager` registration;
- direct imports/references;
- mixin hooks;
- shared helpers owned only by the module;
- resources/translations;
- config migration;
- other modules reading its state.

Build with `clean assemble` after substantial deletions.

## Crash triage order

1. Confirm exact JAR/build used in FCL.
2. Read first meaningful exception and class initialization cause.
3. Map stack line to current source.
4. Check static object initialization/resources.
5. Check mixin application errors.
6. Check version/mapping/API mismatch.
7. Patch the owner path narrowly.
8. Rebuild and retest.

## Porting note

For every port, use:

- `Skill/CLAUDE_PORT_SKID_MODULE_GUIDE.md`
- `Skill/PORT_EVIDENCE_TEMPLATE.md`
- `Skill/PORT_REVIEW_CHECKLIST.md`

Do not use a compile-only shortcut when source behavior is unknown.

# IntelliJ Ready 1.16.5 Client Sources

Open this folder in IntelliJ IDEA as a Gradle project:

`source-intellij-ready-1_16_5`

Run from IntelliJ:

1. Use JDK 21.
2. Open the Gradle tool window.
3. Run `Tasks -> application -> prepareClient` once to verify runtime files.
4. Run `Tasks -> application -> runClient`, or use the included `runClient` run configuration.

Run from PowerShell:

```powershell
powershell -ExecutionPolicy Bypass -File .\run-client.ps1
```

What is inside:

- `src/main/java` - recovered Java sources, 5699 files.
- `src/main/resources` - recovered resources.
- `libs/1_16_5_decrypted.jar` - original runtime JAR used by `runClient`.
- `libs/runtime-stubs.jar` - small runtime stubs needed for launch.
- `module-field-rename-map.csv` - module setting field rename map.
- `module-helper-class-rename-map.csv` - helper class rename map.
- `minecraft-class-rename-map.csv` - Minecraft class rename map.

Runtime fixes included:

- Java process runs from the `run` game directory, so Singleplayer/Multiplayer files are read from the expected place.
- `run-client.ps1` creates `config`, `logs`, `saves`, `resourcepacks`, `server-resource-packs`, and `screenshots`.
- Vanilla 1.16.5 resources are synced into `run/vanilla-assets`, including `assets/minecraft/*` and `data/*`.
- Minecraft font resources are synced from vanilla client jar to prevent missing-glyph/random-symbol text.
- Output is mirrored to `run/logs/latest.log` for crash debugging.
- Gradle no longer fails the whole build only because the game process returns exit code `1`; check `run/logs/latest.log` for the real client error.

Readable source pass:

- helper classes: `Setting`, `BooleanSetting`, `NumberSetting`, `ModeSetting`, `MultiBooleanSetting`, `KeyBindSetting`, `Animation`, `Easing`;
- module lifecycle methods: `onEnable()` / `onDisable()`;
- setting registration: `addSettings(...)`;
- common setting methods: `isEnabled()`, `getValue()`, `isMode(...)`, `isOptionEnabled(...)`, `getKey()`;
- 908 module setting fields renamed from visible UI labels.

Notes:

- Gradle Java compile tasks are disabled intentionally. This project is for IntelliJ source browsing/editing and runtime inspection.
- `runClient` launches the original runtime JAR, while the recovered `src` is used for readable source navigation.

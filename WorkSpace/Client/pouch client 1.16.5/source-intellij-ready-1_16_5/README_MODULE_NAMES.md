# Client Module Name Recovery

This copy builds on `source-minecraft-named-1_16_5` and focuses on the custom client module layer.

Applied:

- helper classes renamed: `Setting`, `BooleanSetting`, `NumberSetting`, `ModeSetting`, `MultiBooleanSetting`, `KeyBindSetting`, `Animation`, `Easing`;
- module lifecycle overrides renamed from `n_1700_B()` / `J_1907_R()` to `onEnable()` / `onDisable()`;
- module setting registration calls renamed to `addSettings(...)`;
- visible setting fields inside module classes renamed from UI labels where possible;
- common setting API calls recovered, for example `isEnabled()`, `getValue()`, `isMode(...)`, `isOptionEnabled(...)`, `getKey()`.

Maps:

- `module-helper-class-rename-map.csv`;
- `module-field-rename-map.csv`.

This is a source-reading/editing tree. It intentionally avoids blind global method renaming for overloaded names because obfuscated methods like `n_1700_B` mean different things depending on receiver type.

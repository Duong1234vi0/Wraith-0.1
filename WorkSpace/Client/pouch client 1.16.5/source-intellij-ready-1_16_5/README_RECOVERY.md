# 1_16_5_decrypted.jar recovery notes

Input artifact:

- `D:\Telegram Desktop\1_16_5_decrypted.jar`

Recovered outputs:

- `decompiled-cfr-1_16_5/` - CFR 0.152 source mirror, 5699 Java files.
- `decompiled-vineflower-1_16_5/` - Vineflower 1.12.0 source mirror, 5696 Java files.
- `client-module-index.csv` - extracted module-name index, 173 module-like classes.
- `package-groups.csv` - package/class group counts from the original JAR.
- `class-list.txt` - full `.class` entry list from the original JAR.

Main entry points:

- Manifest main class: `net.minecraft.client.main.Main`
- Alternate launcher: `MinecraftLauncher.launch()` -> `mcp.client.Start.main()`
- Main client class inferred from launcher flow: `lightning.product.c_1404_X`

Protection / obfuscation state:

- The JAR is a normal Java archive with plain `.class` files, not a runtime-only encrypted VM blob.
- Bytecode is Java 21 classfile level (`major version 65`).
- The largest protected namespace is `lightning.product` with 6243 class files.
- Obfuscation mostly consists of package flattening and identifier renaming:
  classes such as `r_3979_X`, methods such as `n_1700_B`, and fields such as `J_1907_R`.
- Local variable tables and many string constants remain, which allowed module names to be recovered.

Useful recovered module examples:

- `r_3979_X` -> `AttackAura`
- `y_2898_w` -> `AutoTotem`
- `N_3408_T` -> `Flight`
- `X_2658_D` -> `AutoCrystal`
- `U_4523_X` -> `DiscordRPC`
- `N_2577_J` -> `ItemPhysics`

Decompiler notes:

- CFR reported only four noteworthy structural issues in `summary.txt`:
  `lightning.product.q_2753_Q`, `mods.baritone.command.defaults.SelCommand`,
  `mods.baritone.command.defaults.WaypointsCommand`, and
  `net.optifine.shaders.config.MacroProcessor`.
- Most classes decompile into source-like Java. Full source recompilation was not attempted because the JAR embeds a relocated Minecraft/Forge/OptiFine client with many external dependencies and synthetic names.

Recommended first files to inspect:

- `decompiled-cfr-1_16_5/net/minecraft/client/main/Main.java`
- `decompiled-cfr-1_16_5/lightning/product/c_1404_X.java`
- `client-module-index.csv`
- `decompiled-cfr-1_16_5/lightning/product/r_3979_X.java`
- `decompiled-cfr-1_16_5/lightning/product/y_2898_w.java`
- `decompiled-cfr-1_16_5/lightning/product/N_3408_T.java`

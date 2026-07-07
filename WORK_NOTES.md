# Work Notes

## LiquidBounce on FCL / Android

### Build

- On this Termux setup, plain `java` may point to Java 21 even though the project needs Java 25.
- Use this build command:

```sh
JAVA_HOME=/data/data/com.termux/files/usr/lib/jvm/java-25-openjdk ./gradlew assemble -x detekt --no-daemon
```

- Output jar:

```text
build/libs/liquidbounce-0.31.2.jar
```

### Reading FCL Logs

- FCL "Network Protocol Error" can be misleading. It may be a client-side mixin or class initialization failure triggered while handling the login packet.
- For command/client bootstrap issues, check `latest_game.log` around:
  - `Launching LiquidBounce`
  - `LiquidBounce has been successfully initialized.`
  - chat messages like `Command "bind" was not found.`

### Scaffold Removal

- The world crash after entering singleplayer was caused by Scaffold initialization:

```text
NullPointerException: Configurable.tree, parameter configurable
ModuleScaffold.<clinit>
ScaffoldSprintControlFeature
ModuleSprint.getShouldSprintOmnidirectional
```

- Removing Scaffold means also checking references from movement/disabler/silent hotbar paths, not just deleting the module folder.
- After removing a large module, search for remaining hard references:

```sh
grep -R "Scaffold" -n src/main/kotlin src/main/java
```

### Command Registry Issue

- If typing `.` gets a LiquidBounce chat response, the chat mixin and `ChatSendEvent` path are working.
- If every client command says `Command "<name>" was not found`, the command registry is empty or was never initialized.
- In this case the log had `Launching LiquidBounce...` but did not have `LiquidBounce has been successfully initialized.`
- Root cause: `ClientStartEvent` ran, but the initial resource reload had already started before `ClientInitializer` was registered as a reload listener, so `initializeClient()` did not run on first boot.
- Fix used:

```kotlin
if (resourceManager is ReloadableResourceManager) {
    resourceManager.registerReloadListener(ClientInitializer)
    ClientInitializer.run()
}
```

- `initializeClient()` is guarded by `isInitialized`, so calling it once after listener registration is safe.

### ClickGUI Font Crash

- ClickGUI can crash with:

```text
NoClassDefFoundError: Could not initialize class net.ccbluex.liquidbounce.render.FontManager
Caused by: java.awt.HeadlessException
FontManager$FontFace.fillStyle
```

- Root cause: `FontManager.fillStyle()` used `BufferedImage(...).createGraphics().fontMetrics`.
- On FCL/Android this can touch `HeadlessGraphicsEnvironment.getDefaultScreenDevice()` and fail during class initialization.
- Fix: use `Font.getLineMetrics(..., FontRenderContext(...))` instead. This is headless-friendly and already matches the glyph atlas code style.

### TinyFD Runtime Mismatch

- FCL may provide an older LWJGL TinyFD runtime where `tinyfd_messageBox` returns `boolean`.
- The project may compile against a newer LWJGL TinyFD where it returns `int`.
- If fatal error handling logs this:

```text
NoSuchMethodError: TinyFileDialogs.tinyfd_messageBox(..., int)
```

- Use a compatibility wrapper/reflection path for `TinyFileDialogs.tinyfd_messageBox` so both `boolean` and `int` signatures work.

### Practical Debug Order

1. Read the newest `latest_game.log`, not only the crash report.
2. Confirm whether LiquidBounce fully initialized.
3. If prefix works but commands are missing, inspect `CommandManager.registerInbuilt()` and bootstrap logs.
4. If entering world crashes, inspect the first `Caused by`, not the high-level Minecraft error title.
5. Build after each small root-cause fix and test the new jar in FCL.

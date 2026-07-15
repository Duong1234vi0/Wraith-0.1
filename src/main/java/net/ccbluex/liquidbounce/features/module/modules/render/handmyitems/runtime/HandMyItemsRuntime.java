package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.DispatcherStorage;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.GlobalsStorage;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.item_model.ItemModelStorage;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.access.CameraAccessor;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.access.LivingEntityAccessor;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.compat.IrisCompat;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.lua_runtime.ScriptHolder;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.lua_runtime.resource_controller.LuaAnimationResourceLoader;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

public final class HandMyItemsRuntime {
    public enum State {
        DISABLED,
        INITIALIZING,
        LOADING,
        READY,
        FAILED
    }

    public static final Identifier PACK_ID = Identifier.fromNamespaceAndPath("liquidbounce", "handmyitems");
    private static final LuaAnimationResourceLoader LOADER = new LuaAnimationResourceLoader();
    private static boolean initialized;
    private static boolean packRegistrationAttempted;
    private static boolean packRegistered;
    private static boolean repositoryReloadRequested;
    private static volatile State state = State.DISABLED;
    private static volatile Throwable failure;
    private static volatile long stateGeneration;
    private static int diagnosticCount;
    private static State lastRendererState;

    private HandMyItemsRuntime() {
    }

    public static synchronized void initialize() {
        if (!packRegistrationAttempted) {
            packRegistrationAttempted = true;
            FabricLoader.getInstance().getModContainer("liquidbounce").ifPresentOrElse(container -> {
                boolean result;
                try {
                    result = ResourceLoader.registerBuiltinPack(
                        PACK_ID,
                        container,
                        Component.literal("HandMyItems"),
                        PackActivationType.DEFAULT_ENABLED
                    );
                } catch (RuntimeException exception) {
                    result = false;
                    diagnostic("pack registration attempted result=false exception=" + exception);
                    LuaTestHMI.LOGGER.error("HandMyItems built-in pack registration threw", exception);
                }
                if (result) {
                    packRegistered = true;
                }
                diagnostic("pack registration attempted result=" + result + " thread=" + Thread.currentThread().getName());
                if (!result) {
                    LuaTestHMI.LOGGER.warn("Fabric rejected built-in HandMyItems resource pack registration");
                }
            }, () -> {
                diagnostic("pack registration attempted result=false reason=liquidbounce mod container unavailable");
                LuaTestHMI.LOGGER.error("Cannot register HandMyItems pack: liquidbounce mod container is unavailable");
            });
        }

        if (!initialized) {
            ResourceLoader.get(PackType.CLIENT_RESOURCES).registerReloadListener(LuaAnimationResourceLoader.ID, LOADER);
            initialized = true;
        }
    }

    public static synchronized void enable() {
        if (state == State.FAILED) {
            LuaTestHMI.LOGGER.warn("HandMyItems remains failed until the module is disabled and enabled again");
            return;
        }
        state = State.INITIALIZING;
        failure = null;
        initialize();
        if (!packRegistered) {
            fail("registration", PACK_ID.toString(), new IllegalStateException("Built-in pack registration was rejected"));
            return;
        }

        state = State.LOADING;
        diagnostic("reload prepare started");
        Minecraft minecraft = Minecraft.getInstance();
        LuaTestHMI.client = minecraft;
        if (minecraft.getResourceManager() != null && LOADER.hasPrimaryScripts(minecraft.getResourceManager())) {
            if (LOADER.reloadNow(minecraft.getResourceManager())) {
                return;
            }
            return;
        }

        if (!repositoryReloadRequested) {
            repositoryReloadRequested = true;
            minecraft.reloadResourcePacks().whenComplete((unused, throwable) -> {
                synchronized (HandMyItemsRuntime.class) {
                    repositoryReloadRequested = false;
                    if (throwable != null) {
                        fail("repository reload", PACK_ID.toString(), throwable);
                    } else if (!LOADER.hasPrimaryScripts(minecraft.getResourceManager())) {
                        fail("resource lookup", PACK_ID.toString(), new IllegalStateException(
                            "Built-in pack did not expose the required primary Lua scripts after reload"
                        ));
                    } else if (state != State.READY) {
                        LOADER.reloadNow(minecraft.getResourceManager());
                    }
                }
            });
        }
    }

    public static synchronized void disable() {
        state = State.DISABLED;
        failure = null;
        clearState();
    }

    /** True only after the complete primary HMI runtime has been installed and validated. */
    public static boolean isActive() {
        return state == State.READY;
    }

    /** True while the module toggle owns loading/activation, including a failed load. */
    public static boolean isEnabled() {
        return state != State.DISABLED;
    }

    public static boolean isReady() {
        return state == State.READY;
    }

    public static State state() {
        return state;
    }

    public static Throwable failure() {
        return failure;
    }

    public static long stateGeneration() {
        return stateGeneration;
    }

    public static synchronized void onWorldChanged(boolean worldAvailable) {
        clearState();
        if (state == State.FAILED) {
            return;
        }
        if (isEnabled() && worldAvailable) {
            state = State.LOADING;
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.getResourceManager() == null || !LOADER.hasPrimaryScripts(minecraft.getResourceManager())) {
                fail("world reload", PACK_ID.toString(), new IllegalStateException(
                    "Required primary scripts are unavailable after world change"
                ));
            } else if (!LOADER.reloadNow(minecraft.getResourceManager())) {
                // reloadNow records the concrete failure.
            }
        }
    }

    public static synchronized void onLocalPlayerReplaced() {
        clearTransientState();
    }

    public static synchronized void onScriptsReloaded() {
        clearTransientState();
    }

    private static void clearTransientState() {
        DispatcherStorage.clear();
        ItemModelStorage.clear();
        GlobalsStorage.particles.clear();
        GlobalsStorage.modelPartAnimator.clear();
        GlobalsStorage.debugTextRenderer.clear();
        GlobalsStorage.mainHandItem = Items.AIR.getDefaultInstance();
        GlobalsStorage.offHandItem = Items.AIR.getDefaultInstance();
        GlobalsStorage.renderedStack = Items.AIR.getDefaultInstance();
        LuaTestHMI.resetFrameContext();
        resetEntityAndCameraState();
        stateGeneration++;
    }

    public static synchronized void onReloadFailure(Exception exception) {
        onReloadFailure(exception, exception.getMessage() == null ? "<resource reload>" : exception.getMessage());
    }

    public static synchronized void onReloadFailure(Exception exception, String resource) {
        fail("reload", resource, exception);
    }

    public static synchronized void clearState() {
        ScriptHolder.reset();
        GlobalsStorage.renderAsBlock.clear();
        GlobalsStorage.translateItem.clear();
        GlobalsStorage.applyBlockRotation.clear();
        GlobalsStorage.itemSwingSpeed.clear();
        GlobalsStorage.useDuration.clear();
        GlobalsStorage.usingItem.clear();
        GlobalsStorage.registry.clear();
        clearTransientState();
        IrisCompat.reset();
    }

    public static synchronized void onScriptsReady() {
        if (state == State.DISABLED || state == State.FAILED) {
            return;
        }
        state = State.READY;
        failure = null;
        diagnostic("reload apply completed");
        diagnostic("runtime state READY");
    }

    public static synchronized void onScriptFailure(String sourceName, Throwable exception, InteractionHand hand, ItemStack item) {
        String handName = hand == null ? "<none>" : hand.name();
        String itemName = item == null ? "<none>" : item.getItem().toString();
        fail("script execution hand=" + handName + " item=" + itemName, sourceName, exception);
    }

    public static synchronized void recordRendererDecision() {
        State current = state;
        if (current == lastRendererState) {
            return;
        }
        lastRendererState = current;
        if (current == State.READY) {
            diagnostic("renderer HMI ownership state=READY");
        } else {
            diagnostic("renderer fallback state=" + current.name());
        }
    }

    public static synchronized void diagnostic(String message) {
        if (diagnosticCount >= 80) {
            return;
        }
        diagnosticCount++;
        LuaTestHMI.LOGGER.info("[HMIDiag] {}", message);
    }

    private static void fail(String stage, String resource, Throwable exception) {
        if (state == State.FAILED) {
            return;
        }
        state = State.FAILED;
        failure = exception;
        clearState();
        diagnostic("runtime state FAILED stage=" + stage + " resource=" + resource);
        LuaTestHMI.LOGGER.error("[HMIDiag] runtime state FAILED stage={} resource={}", stage, resource, exception);
    }

    private static void resetEntityAndCameraState() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft == null) {
            return;
        }
        if (minecraft.player instanceof LivingEntityAccessor accessor) {
            accessor.hMI5_0$resetAll();
        }
        if (minecraft.gameRenderer != null && minecraft.gameRenderer.getMainCamera() instanceof CameraAccessor accessor) {
            accessor.hMI5_0$setRotationValues(0.0f, 0.0f, 0.0f);
            accessor.hMI5_0$setPosValues(0.0f, 0.0f, 0.0f);
        }
    }
}

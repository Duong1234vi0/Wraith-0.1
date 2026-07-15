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
import net.minecraft.world.item.Items;

public final class HandMyItemsRuntime {
    public static final Identifier PACK_ID = Identifier.fromNamespaceAndPath("liquidbounce", "handmyitems");
    private static final LuaAnimationResourceLoader LOADER = new LuaAnimationResourceLoader();
    private static boolean initialized;
    private static boolean packRegistered;
    private static boolean repositoryReloadRequested;
    private static volatile boolean active;
    private static volatile long stateGeneration;

    private HandMyItemsRuntime() {
    }

    public static synchronized void initialize() {
        if (!packRegistered) {
            FabricLoader.getInstance().getModContainer("liquidbounce").ifPresentOrElse(container -> {
                boolean registered = ResourceLoader.registerBuiltinPack(
                    PACK_ID,
                    container,
                    Component.literal("HandMyItems"),
                    PackActivationType.DEFAULT_ENABLED
                );
                if (!registered) {
                    LuaTestHMI.LOGGER.warn("Fabric rejected built-in HandMyItems resource pack registration");
                }
            }, () -> LuaTestHMI.LOGGER.error("Cannot register HandMyItems pack: liquidbounce mod container is unavailable"));
            packRegistered = true;
        }

        if (!initialized) {
            ResourceLoader.get(PackType.CLIENT_RESOURCES).registerReloadListener(LuaAnimationResourceLoader.ID, LOADER);
            initialized = true;
        }
    }

    public static synchronized void enable() {
        active = true;
        initialize();
        Minecraft minecraft = Minecraft.getInstance();
        LuaTestHMI.client = minecraft;
        if (minecraft.getResourceManager() != null && LOADER.hasPrimaryScripts(minecraft.getResourceManager())) {
            LOADER.reloadNow(minecraft.getResourceManager());
        } else if (!repositoryReloadRequested) {
            repositoryReloadRequested = true;
            minecraft.reloadResourcePacks().whenComplete((unused, throwable) -> {
                synchronized (HandMyItemsRuntime.class) {
                    repositoryReloadRequested = false;
                    if (throwable != null) {
                        LuaTestHMI.LOGGER.error("HandMyItems resource repository reload failed", throwable);
                        clearState();
                    } else if (!LOADER.hasPrimaryScripts(minecraft.getResourceManager())) {
                        LuaTestHMI.LOGGER.error("HandMyItems built-in pack did not expose its primary Lua scripts after reload");
                        clearState();
                    }
                }
            });
        }
    }

    public static synchronized void disable() {
        active = false;
        clearState();
    }

    public static boolean isActive() {
        return active;
    }

    public static long stateGeneration() {
        return stateGeneration;
    }

    public static synchronized void onWorldChanged(boolean worldAvailable) {
        clearState();
        if (active && worldAvailable) {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.getResourceManager() != null) {
                LOADER.reloadNow(minecraft.getResourceManager());
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
        LuaTestHMI.LOGGER.error("HandMyItems resource reload failed; runtime state was cleared", exception);
        clearState();
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

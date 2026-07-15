/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  net.fabricmc.loader.api.FabricLoader
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.compat;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.LuaTestHMI;
import java.lang.reflect.Method;
import net.fabricmc.loader.api.FabricLoader;

public class IrisCompat {
    private static Boolean irisAvailable = null;
    private static Method isShaderPackInUseMethod = null;
    private static Object irisApiInstance = null;
    private static boolean initializationAttempted = false;

    public static boolean isShaderPackInUse() {
        if (!IrisCompat.isIrisAvailable()) {
            return false;
        }
        if (initializationAttempted && irisApiInstance == null) {
            return false;
        }
        try {
            if (irisApiInstance == null) {
                initializationAttempted = true;
                Class<?> irisApiClass = Class.forName("net.irisshaders.iris.api.v0.IrisApi");
                Method getInstanceMethod = irisApiClass.getMethod("getInstance", new Class[0]);
                irisApiInstance = getInstanceMethod.invoke(null, new Object[0]);
                if (irisApiInstance == null) {
                    LuaTestHMI.LOGGER.warn("Iris API instance is unavailable while HandMyItems is checking shader state");
                    return false;
                }
                isShaderPackInUseMethod = irisApiInstance.getClass().getMethod("isShaderPackInUse", new Class[0]);
            }
            return (Boolean)isShaderPackInUseMethod.invoke(irisApiInstance, new Object[0]);
        }
        catch (Exception e) {
            LuaTestHMI.LOGGER.warn("Failed to check Iris shader state for HandMyItems", e);
            irisApiInstance = null;
            isShaderPackInUseMethod = null;
            return false;
        }
    }

    public static boolean isIrisAvailable() {
        if (irisAvailable == null) {
            irisAvailable = FabricLoader.getInstance().isModLoaded("iris");
        }
        return irisAvailable;
    }

    public static void reset() {
        irisApiInstance = null;
        isShaderPackInUseMethod = null;
        initializationAttempted = false;
    }
}

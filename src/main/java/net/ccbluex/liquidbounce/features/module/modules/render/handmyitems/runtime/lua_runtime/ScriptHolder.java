/*
 * Decompiled with CFR 0.152.
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.lua_runtime;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.lua_runtime.LuaScriptCache;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.lua_runtime.ModelScriptCache;
import java.io.IOException;
import java.util.ArrayList;

public class ScriptHolder {
    public static ArrayList<LuaScriptCache> handAddonsCache = new ArrayList();
    public static ArrayList<LuaScriptCache> handRelativeAddonsCache = new ArrayList();
    public static ArrayList<LuaScriptCache> itemAddonsCache = new ArrayList();
    public static ArrayList<ModelScriptCache> itemModelAddonsCache = new ArrayList();
    public static LuaScriptCache handScriptCache;
    public static LuaScriptCache handRelativeScriptCache;
    public static LuaScriptCache itemScriptCache;
    public static ModelScriptCache itemModelCache;

    public static synchronized void install(
        LuaScriptCache hand,
        LuaScriptCache handRelative,
        LuaScriptCache item,
        ModelScriptCache itemModel,
        ArrayList<LuaScriptCache> handAddons,
        ArrayList<LuaScriptCache> handRelativeAddons,
        ArrayList<LuaScriptCache> itemAddons,
        ArrayList<ModelScriptCache> itemModelAddons
    ) {
        handScriptCache = hand;
        handRelativeScriptCache = handRelative;
        itemScriptCache = item;
        itemModelCache = itemModel;
        handAddonsCache = handAddons;
        handRelativeAddonsCache = handRelativeAddons;
        itemAddonsCache = itemAddons;
        itemModelAddonsCache = itemModelAddons;
    }

    public static synchronized void reset() {
        try {
            install(
                LuaScriptCache.fallback(),
                LuaScriptCache.fallback(),
                LuaScriptCache.fallback(),
                ModelScriptCache.fallback(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
            );
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to create empty HandMyItems scripts", exception);
        }
    }

    public static synchronized boolean primaryScriptsOperational() {
        return handScriptCache != null && handScriptCache.isOperational()
            && handRelativeScriptCache != null && handRelativeScriptCache.isOperational()
            && itemScriptCache != null && itemScriptCache.isOperational()
            && itemModelCache != null && itemModelCache.isOperational();
    }

    static {
        reset();
    }
}

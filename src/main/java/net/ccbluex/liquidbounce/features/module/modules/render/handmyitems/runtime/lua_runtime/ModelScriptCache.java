/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.player.AbstractClientPlayer
 *  net.minecraft.client.toast.SystemToast
 *  net.minecraft.client.toast.SystemToast$Type
 *  net.minecraft.client.toast.ToastManager
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.InteractionHand
 *  org.luaj.vm2.Globals
 *  org.luaj.vm2.LuaValue
 *  org.luaj.vm2.lib.jse.CoerceJavaToLua
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.lua_runtime;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.LuaTestHMI;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.GlobalsStorage;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.item_model.ItemModelContext;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.lua_runtime.LuaScriptManager;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.model.ModelPartAnimator;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.custom_api.KeyBindManager;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers.Easings;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers.I;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers.JSItems;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers.JSTags;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers.M;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers.P;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

public class ModelScriptCache {
    private static ItemModelContext data = new ItemModelContext(false, 0.0f, Minecraft.getInstance().player, InteractionHand.MAIN_HAND, false, LuaTestHMI.deltaTime, 0.0f, 0.0f, 0.0f, false, false, false, false, false, false, Items.AIR.getDefaultInstance());
    private final Globals globals;
    private final LuaValue chunk;
    private final String sourceName;
    private boolean canRun = true;
    private final M mInstance = new M();
    private final I iInstance = new I();
    private final JSItems jsItemsInstance = new JSItems();
    private final JSTags jsTagsInstance = new JSTags();
    private final P pInstance = new P();
    private final Easings easingsInstance = new Easings();
    private final KeyBindManager keyBindManagerInstance = new KeyBindManager();

    public ModelScriptCache(String sourceCode) throws IOException {
        this(sourceCode, "<embedded-model>");
    }

    public ModelScriptCache(String sourceCode, String sourceName) throws IOException {
        this.sourceName = sourceName;
        this.globals = LuaScriptManager.getInstance().sharedGlobals;
        this.globals.set("M", CoerceJavaToLua.coerce((Object)this.mInstance));
        this.globals.set("I", CoerceJavaToLua.coerce((Object)this.iInstance));
        this.globals.set("Items", CoerceJavaToLua.coerce((Object)this.jsItemsInstance));
        this.globals.set("Tags", CoerceJavaToLua.coerce((Object)this.jsTagsInstance));
        this.globals.set("P", CoerceJavaToLua.coerce((Object)this.pInstance));
        this.globals.set("Easings", CoerceJavaToLua.coerce((Object)this.easingsInstance));
        this.globals.set("KeyBindManager", CoerceJavaToLua.coerce((Object)this.keyBindManagerInstance));
        this.globals.set("registry", CoerceJavaToLua.coerce(GlobalsStorage.registry));
        this.globals.set("animator", CoerceJavaToLua.coerce((Object)GlobalsStorage.modelPartAnimator));
        this.globals.set("debugger", CoerceJavaToLua.coerce((Object)GlobalsStorage.debugTextRenderer));
        this.globals.set("data", CoerceJavaToLua.coerce((Object)data));
        this.chunk = this.globals.load(sourceCode, sourceName);
    }

    public void executeModel(ItemModelContext data, ItemStack itemStack, AbstractClientPlayer player, ModelPartAnimator modelPartAnimator) {
        if (!this.canRun) {
            return;
        }
        try {
            ModelScriptCache.data.set(data);
            this.chunk.call();
        }
        catch (Exception e) {
            String message = sourceName + ": " + e.getMessage();
            LuaTestHMI.LOGGER.error("HandMyItems model Lua runtime error in {}", sourceName, e);
            SystemToast.add(
                Minecraft.getInstance().getToastManager(),
                SystemToast.SystemToastId.PACK_LOAD_FAILURE,
                Component.literal("HMI model Lua runtime error"),
                Component.literal(message)
            );
            this.canRun = false;
        }
    }
}

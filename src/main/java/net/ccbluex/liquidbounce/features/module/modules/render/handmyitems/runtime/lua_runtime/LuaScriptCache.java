/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.player.AbstractClientPlayer
 *  net.minecraft.client.toast.SystemToast
 *  net.minecraft.client.toast.SystemToast$Type
 *  net.minecraft.client.toast.ToastManager
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.InteractionHand
 *  org.luaj.vm2.Globals
 *  org.luaj.vm2.LuaValue
 *  org.luaj.vm2.lib.jse.CoerceJavaToLua
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.lua_runtime;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.LuaTestHMI;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.HandMyItemsRuntime;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.GlobalsStorage;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global.LuaContext;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.lua_runtime.LuaScriptManager;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.patricles.Particle;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.patricles.ParticleManager;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.patricles.scripting.Texture;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.custom_api.KeyBindManager;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers.C;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers.Easings;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers.I;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers.JSItems;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers.JSTags;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers.M;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers.P;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers.S;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.gui.components.toasts.SystemToast;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

public class LuaScriptCache {
    public static int swingSpeed = 9;
    private final Globals globals;
    private final LuaValue chunk;
    private final String sourceName;
    private final int sourceLength;
    private final boolean fallback;
    private boolean compiledSuccessfully;
    private boolean runtimeEnabled;
    private Throwable failure;
    private boolean firstExecutionCompleted;
    private static final LuaContext context = new LuaContext();
    private final M mInstance = new M();
    private final I iInstance = new I();
    private final Texture textureInstance = new Texture();
    private final JSItems jsItemsInstance = new JSItems();
    private final JSTags jsTagsInstance = new JSTags();
    private final P pInstance = new P();
    private final Easings easingsInstance = new Easings();
    private final KeyBindManager keyBindManagerInstance = new KeyBindManager();
    private final S sInstance = new S();
    private final C cInstance = new C();
    private final ParticleManager particleManagerInstance = new ParticleManager();

    public LuaScriptCache(String sourceCode) throws IOException {
        this(sourceCode, "<fallback>", true);
    }

    public LuaScriptCache(String sourceCode, String sourceName) throws IOException {
        this(sourceCode, sourceName, false);
    }

    private LuaScriptCache(String sourceCode, String sourceName, boolean fallback) throws IOException {
        this.sourceName = sourceName;
        this.sourceLength = sourceCode.getBytes(StandardCharsets.UTF_8).length;
        this.fallback = fallback;
        this.globals = LuaScriptManager.getInstance().sharedGlobals;
        this.globals.set("M", CoerceJavaToLua.coerce((Object)this.mInstance));
        this.globals.set("I", CoerceJavaToLua.coerce((Object)this.iInstance));
        this.globals.set("Texture", CoerceJavaToLua.coerce((Object)this.textureInstance));
        this.globals.set("Items", CoerceJavaToLua.coerce((Object)this.jsItemsInstance));
        this.globals.set("Tags", CoerceJavaToLua.coerce((Object)this.jsTagsInstance));
        this.globals.set("P", CoerceJavaToLua.coerce((Object)this.pInstance));
        this.globals.set("Easings", CoerceJavaToLua.coerce((Object)this.easingsInstance));
        this.globals.set("KeyBindManager", CoerceJavaToLua.coerce((Object)this.keyBindManagerInstance));
        this.globals.set("S", CoerceJavaToLua.coerce((Object)this.sInstance));
        this.globals.set("C", CoerceJavaToLua.coerce((Object)this.cInstance));
        this.globals.set("particleManager", CoerceJavaToLua.coerce((Object)this.particleManagerInstance));
        this.globals.set("swingSpeed", (LuaValue)LuaValue.valueOf((int)swingSpeed));
        this.globals.set("registry", CoerceJavaToLua.coerce(GlobalsStorage.registry));
        this.globals.set("renderAsBlock", CoerceJavaToLua.coerce(GlobalsStorage.renderAsBlock));
        this.globals.set("translateItem", CoerceJavaToLua.coerce(GlobalsStorage.translateItem));
        this.globals.set("itemSwingSpeed", CoerceJavaToLua.coerce(GlobalsStorage.itemSwingSpeed));
        this.globals.set("animator", CoerceJavaToLua.coerce((Object)GlobalsStorage.modelPartAnimator));
        this.globals.set("renderAsBlock", CoerceJavaToLua.coerce(GlobalsStorage.renderAsBlock));
        this.globals.set("translateItem", CoerceJavaToLua.coerce(GlobalsStorage.translateItem));
        this.globals.set("itemSwingSpeed", CoerceJavaToLua.coerce(GlobalsStorage.itemSwingSpeed));
        this.globals.set("useDuration", CoerceJavaToLua.coerce(GlobalsStorage.useDuration));
        this.globals.set("usingItem", CoerceJavaToLua.coerce(GlobalsStorage.usingItem));
        this.globals.set("debugger", CoerceJavaToLua.coerce((Object)GlobalsStorage.debugTextRenderer));
        this.globals.set("applyBlockRotation", CoerceJavaToLua.coerce(GlobalsStorage.applyBlockRotation));
        this.globals.set("context", CoerceJavaToLua.coerce((Object)context));
        this.chunk = this.globals.load(sourceCode, sourceName);
        this.compiledSuccessfully = true;
        this.runtimeEnabled = !fallback;
    }

    public static LuaScriptCache fallback() throws IOException {
        return new LuaScriptCache("return", "<fallback>", true);
    }

    public boolean isLoaded() {
        return this.compiledSuccessfully && !this.fallback;
    }

    public boolean isOperational() {
        return this.isLoaded() && this.runtimeEnabled;
    }

    public String sourceId() {
        return this.sourceName;
    }

    public int sourceLength() {
        return this.sourceLength;
    }

    public Throwable getFailure() {
        return this.failure;
    }

    public void disable(Throwable exception) {
        this.runtimeEnabled = false;
        this.failure = exception;
    }

    private String diagnosticName() {
        if (this.sourceName.endsWith(".lua")) {
            String name = this.sourceName.substring(this.sourceName.lastIndexOf('/') + 1);
            return name.substring(0, name.length() - 4);
        }
        return this.sourceName;
    }

    public void execute(PoseStack matrices, boolean bl, HashMap<String, Object> registry, float swingProgress, ItemStack item, AbstractClientPlayer player, InteractionHand hand, boolean mainHand, float deltaTime, float equipProgress, float mainHandSwingProgress, float offHandSwingProgress, boolean mainHandSwitchEvent, boolean offHandSwitchEvent, boolean swingMHand, boolean swingOHand, boolean interact, boolean blockBreaking, List<Particle> particles) {
        if (!this.runtimeEnabled) {
            return;
        }
        try {
            if (!this.firstExecutionCompleted) {
                HandMyItemsRuntime.diagnostic("execute " + this.diagnosticName());
            }
            context.update(matrices, bl, swingProgress, item, player, hand, mainHand, deltaTime, equipProgress, mainHandSwingProgress, offHandSwingProgress, mainHandSwitchEvent, offHandSwitchEvent, swingMHand, swingOHand, interact, blockBreaking, particles);
            this.chunk.invoke();
            if (!this.firstExecutionCompleted) {
                this.firstExecutionCompleted = true;
                HandMyItemsRuntime.diagnostic("execute succeeded script=" + this.diagnosticName());
            }
        }
        catch (Exception e) {
            String message = sourceName + ": " + e.getMessage();
            this.disable(e);
            HandMyItemsRuntime.onScriptFailure(sourceName, e, hand, item);
            try {
                SystemToast.add(
                    Minecraft.getInstance().getToastManager(),
                    SystemToast.SystemToastId.PACK_LOAD_FAILURE,
                    Component.literal("HMI Lua runtime error"),
                    Component.literal(message)
                );
            } catch (Exception ignored) {
                // The runtime failure has already been recorded; toast display is optional.
            }
            throw new ExecutionException(sourceName, e);
        }
    }

    public static class ExecutionException extends RuntimeException {
        public ExecutionException(String sourceName, Throwable cause) {
            super("HandMyItems script execution failed: " + sourceName, cause);
        }
    }
}

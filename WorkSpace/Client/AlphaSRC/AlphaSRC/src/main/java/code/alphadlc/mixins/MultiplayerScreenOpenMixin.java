/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 *  net.minecraft.class_364
 *  net.minecraft.class_4068
 *  net.minecraft.class_4185
 *  net.minecraft.class_437
 *  net.minecraft.class_500
 *  net.minecraft.class_6379
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.common.proxy.Config;
import code.alphadlc.common.proxy.Proxy;
import code.alphadlc.common.proxy.ProxyScreens;
import code.alphadlc.common.proxy.ProxyServer;
import code.alphadlc.mixins.IScreen;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_364;
import net.minecraft.class_4068;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_500;
import net.minecraft.class_6379;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_500.class})
public class MultiplayerScreenOpenMixin {
    @Inject(method={"init()V"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/screen/multiplayer/MultiplayerScreen;updateButtonActivationStates()V")})
    public void multiplayerGuiOpen(CallbackInfo ci) {
        try {
            String playerName = class_310.method_1551().method_1548().method_1676();
            if (!playerName.equals(Config.lastPlayerName)) {
                Config.lastPlayerName = playerName;
                ProxyServer.proxy = Config.accounts.containsKey(playerName) ? Config.accounts.get(playerName) : (Config.accounts.containsKey("") ? Config.accounts.get("") : new Proxy());
            }
            class_500 ms = (class_500)this;
            ProxyServer.proxyMenuButton = class_4185.method_46430((class_2561)class_2561.method_43470((String)"Proxy"), buttonWidget -> ProxyScreens.open((class_437)ms)).method_46434(5, 5, 120, 20).method_46431();
            IScreen si = (IScreen)ms;
            si.getDrawables().add((class_4068)ProxyServer.proxyMenuButton);
            si.getSelectables().add((class_6379)ProxyServer.proxyMenuButton);
            si.getChildren().add((class_364)ProxyServer.proxyMenuButton);
        }
        catch (Throwable error) {
            System.err.println("Failed to initialize proxy menu on multiplayer screen");
            error.printStackTrace();
        }
    }
}

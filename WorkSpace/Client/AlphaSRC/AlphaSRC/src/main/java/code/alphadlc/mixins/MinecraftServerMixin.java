/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.MinecraftServer
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.AlphaDLC;
import code.alphadlc.utils.client.logs.Logger;
import code.alphadlc.utils.client.managers.file.exception.FileProcessingException;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={MinecraftServer.class})
public class MinecraftServerMixin {
    @Inject(method={"shutdown"}, at={@At(value="HEAD")})
    public void shutdown(CallbackInfo ci) {
        if (AlphaDLC.getInstance().isInitialized()) {
            try {
                AlphaDLC.getInstance().getFileController().saveFiles();
            }
            catch (FileProcessingException e) {
                Logger.error("Error occurred while saving files: " + e.getMessage());
            }
        }
    }
}

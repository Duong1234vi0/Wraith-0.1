/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2666
 *  net.minecraft.class_2672
 *  net.minecraft.class_2680
 *  net.minecraft.class_2818
 *  net.minecraft.class_2826
 *  net.minecraft.class_634
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.events.block.BlockUpdateEvent;
import code.alphadlc.events.chat.ChatEvent;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.interactions.path.BaritoneHelper;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2666;
import net.minecraft.class_2672;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import net.minecraft.class_2826;
import net.minecraft.class_634;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_634.class})
public class ClientPlayNetworkHandlerMixin
implements QuickImports {
    @Inject(method={"onChunkData"}, at={@At(value="RETURN")})
    private void onChunkDataHook(class_2672 packet, CallbackInfo ci) {
        this.scanChunk(ClientPlayNetworkHandlerMixin.mc.field_1687.method_8497(packet.method_11523(), packet.method_11524()), BlockUpdateEvent.Type.LOAD);
    }

    @Inject(method={"onUnloadChunk"}, at={@At(value="HEAD")})
    private void onUnloadChunkHook(class_2666 packet, CallbackInfo ci) {
        this.scanChunk(ClientPlayNetworkHandlerMixin.mc.field_1687.method_8497(packet.comp_1726().field_9181, packet.comp_1726().field_9180), BlockUpdateEvent.Type.UNLOAD);
    }

    @Inject(method={"sendChatMessage(Ljava/lang/String;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void sendChatMessage(String string, CallbackInfo ci) {
        if (BaritoneHelper.handleChatCommand(string)) {
            ci.cancel();
            return;
        }
        ChatEvent event = SafeEvents.dispatch(() -> new ChatEvent(string));
        if (event != null && event.isCancelled()) {
            ci.cancel();
        }
    }

    @Unique
    private void scanChunk(class_2818 worldChunk, BlockUpdateEvent.Type type) {
        CompletableFuture.runAsync(() -> {
            int startX = worldChunk.method_12004().method_8326();
            int startZ = worldChunk.method_12004().method_8328();
            ArrayList<CompletableFuture<Void>> sectionFutures = new ArrayList<CompletableFuture<Void>>();
            int sectionIndex = 0;
            while (sectionIndex <= worldChunk.method_12040()) {
                int finalSectionIndex = sectionIndex++;
                sectionFutures.add(CompletableFuture.runAsync(() -> {
                    class_2826 section = worldChunk.method_38259(finalSectionIndex);
                    for (int sectionY = 0; sectionY < 16; ++sectionY) {
                        int y = finalSectionIndex + (worldChunk.method_31607() >> 4) << 4 | sectionY;
                        for (int x = 0; x < 16; ++x) {
                            for (int z = 0; z < 16; ++z) {
                                class_2680 blockState = type.equals((Object)BlockUpdateEvent.Type.LOAD) ? section.method_12254(x, sectionY, z) : class_2246.field_10124.method_9564();
                                class_2338 pos = new class_2338(startX | x, y, startZ | z);
                                SafeEvents.dispatch(() -> new BlockUpdateEvent(blockState, pos, type));
                            }
                        }
                    }
                }));
            }
            CompletableFuture.allOf(sectionFutures.toArray(new CompletableFuture[0])).join();
        });
    }
}

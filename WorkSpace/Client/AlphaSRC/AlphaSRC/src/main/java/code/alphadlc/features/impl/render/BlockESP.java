/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_2561
 *  net.minecraft.class_2680
 *  net.minecraft.class_2818
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_7923
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.geometry.Render3D;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import net.minecraft.class_2902;
import net.minecraft.class_7923;

public class BlockESP
extends Module {
    ColorSetting color = new ColorSetting("\u0426\u0432\u0435\u0442", "\u0426\u0432\u0435\u0442 \u043f\u043e\u0434\u0441\u0432\u0435\u0442\u043a\u0438 \u0431\u043b\u043e\u043a\u043e\u0432").value(ColorAssist.getColor(255, 0, 0, 255));
    SliderSettings range = new SliderSettings("\u0420\u0430\u0434\u0438\u0443\u0441", "\u0420\u0430\u0434\u0438\u0443\u0441 \u043f\u043e\u0438\u0441\u043a\u0430 \u0431\u043b\u043e\u043a\u043e\u0432").range(1, 128).setValue(32.0f);
    BooleanSetting notifyInChat = new BooleanSetting("\u0423\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u044f", "\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c \u043a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b \u043d\u0430\u0439\u0434\u0435\u043d\u043d\u044b\u0445 \u0431\u043b\u043e\u043a\u043e\u0432 \u0432 \u0447\u0430\u0442\u0435").setValue(false);
    Set<String> blocksToHighlight = new CopyOnWriteArraySet<String>();
    Map<class_2338, class_2680> renderBlocks = new HashMap<class_2338, class_2680>();
    Set<class_2338> notifiedBlocks = new CopyOnWriteArraySet<class_2338>();
    long lastScanTime = 0L;
    int checkCounter = 0;

    public BlockESP() {
        super("BlockESP", "BlockESP", ModuleCategory.RENDER);
        this.setup(this.color, this.range, this.notifyInChat);
    }

    public Set<String> getBlocksToHighlight() {
        return this.blocksToHighlight;
    }

    @Override
    public void activate() {
        super.activate();
        this.notifiedBlocks.clear();
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.renderBlocks.clear();
        this.notifiedBlocks.clear();
    }

    @EventHandler
    public void onRender3D(WorldRenderEvent event) {
        if (!this.state || BlockESP.mc.field_1687 == null || BlockESP.mc.field_1724 == null) {
            this.renderBlocks.clear();
            return;
        }
        if (this.blocksToHighlight.isEmpty()) {
            this.renderBlocks.clear();
            return;
        }
        class_2338 playerPos = BlockESP.mc.field_1724.method_24515();
        long currentTime = System.nanoTime() / 1000000L;
        if (currentTime - this.lastScanTime >= 2000L) {
            this.renderBlocks.clear();
            int chunkRange = 2;
            int yRange = 48;
            for (int x = -chunkRange; x <= chunkRange; ++x) {
                for (int z = -chunkRange; z <= chunkRange; ++z) {
                    class_2818 chunk;
                    int chunkX = (playerPos.method_10263() >> 4) + x;
                    int chunkZ = (playerPos.method_10260() >> 4) + z;
                    if (!BlockESP.mc.field_1687.method_2935().method_12123(chunkX, chunkZ) || (chunk = BlockESP.mc.field_1687.method_2935().method_21730(chunkX, chunkZ)) == null) continue;
                    int cx = chunk.method_12004().field_9181 << 4;
                    int cz = chunk.method_12004().field_9180 << 4;
                    for (int bx = 0; bx < 16; ++bx) {
                        for (int bz = 0; bz < 16; ++bz) {
                            int minY = Math.max(BlockESP.mc.field_1687.method_31607(), playerPos.method_10264() - yRange);
                            int maxY = Math.min(BlockESP.mc.field_1687.method_8624(class_2902.class_2903.field_13202, cx + bx, cz + bz), playerPos.method_10264() + yRange);
                            for (int by = minY; by <= maxY; ++by) {
                                class_2248 block;
                                String blockName;
                                class_2338 pos2 = new class_2338(cx + bx, by, cz + bz);
                                double dist = BlockESP.mc.field_1724.method_5649((double)pos2.method_10263() + 0.5, (double)pos2.method_10264() + 0.5, (double)pos2.method_10260() + 0.5);
                                if (dist > (double)(this.range.getValue() * this.range.getValue()) || !this.blocksToHighlight.contains(blockName = class_7923.field_41175.method_10221((Object)(block = BlockESP.mc.field_1687.method_8320(pos2).method_26204())).toString())) continue;
                                this.renderBlocks.put(pos2.method_10062(), BlockESP.mc.field_1687.method_8320(pos2));
                                if (!this.notifyInChat.isValue() || this.notifiedBlocks.contains(pos2)) continue;
                                this.notifyBlockFound(pos2, blockName);
                                this.notifiedBlocks.add(pos2);
                            }
                        }
                    }
                }
            }
            this.lastScanTime = currentTime;
            this.checkCounter = 0;
        }
        if (this.checkCounter % 5 == 0) {
            int nearChunkRange = 1;
            for (int x = -nearChunkRange; x <= nearChunkRange; ++x) {
                for (int z = -nearChunkRange; z <= nearChunkRange; ++z) {
                    class_2818 chunk;
                    int chunkX = (playerPos.method_10263() >> 4) + x;
                    int chunkZ = (playerPos.method_10260() >> 4) + z;
                    if (!BlockESP.mc.field_1687.method_2935().method_12123(chunkX, chunkZ) || (chunk = BlockESP.mc.field_1687.method_2935().method_21730(chunkX, chunkZ)) == null) continue;
                    int cx = chunk.method_12004().field_9181 << 4;
                    int cz = chunk.method_12004().field_9180 << 4;
                    for (int bx = 0; bx < 16; ++bx) {
                        for (int bz = 0; bz < 16; ++bz) {
                            int minY = Math.max(BlockESP.mc.field_1687.method_31607(), playerPos.method_10264() - 24);
                            int maxY = Math.min(BlockESP.mc.field_1687.method_8624(class_2902.class_2903.field_13202, cx + bx, cz + bz), playerPos.method_10264() + 24);
                            for (int by = minY; by <= maxY; ++by) {
                                class_2248 block;
                                String blockName;
                                class_2338 pos3 = new class_2338(cx + bx, by, cz + bz);
                                double dist = BlockESP.mc.field_1724.method_5649((double)pos3.method_10263() + 0.5, (double)pos3.method_10264() + 0.5, (double)pos3.method_10260() + 0.5);
                                if (dist > 16.0 || !this.blocksToHighlight.contains(blockName = class_7923.field_41175.method_10221((Object)(block = BlockESP.mc.field_1687.method_8320(pos3).method_26204())).toString()) || this.renderBlocks.containsKey(pos3)) continue;
                                this.renderBlocks.put(pos3.method_10062(), BlockESP.mc.field_1687.method_8320(pos3));
                                if (!this.notifyInChat.isValue() || this.notifiedBlocks.contains(pos3)) continue;
                                this.notifyBlockFound(pos3, blockName);
                                this.notifiedBlocks.add(pos3);
                            }
                        }
                    }
                }
            }
        }
        if (this.checkCounter % 60 == 0) {
            this.renderBlocks.entrySet().removeIf(entry -> {
                boolean shouldRemove;
                class_2338 pos = (class_2338)entry.getKey();
                class_2248 block = BlockESP.mc.field_1687.method_8320(pos).method_26204();
                String blockName = class_7923.field_41175.method_10221((Object)block).toString();
                boolean bl = shouldRemove = !this.blocksToHighlight.contains(blockName);
                if (shouldRemove) {
                    this.notifiedBlocks.remove(pos);
                }
                return shouldRemove;
            });
        }
        ++this.checkCounter;
        this.renderBlocks.forEach((pos, state) -> Render3D.drawBox(new class_238(pos), this.color.getColor(), 1.0f));
    }

    private void notifyBlockFound(class_2338 pos, String blockName) {
        if (BlockESP.mc.field_1724 != null) {
            BlockESP.mc.field_1724.method_7353((class_2561)ChatMessage.brandmessage().method_27693("\u041d\u0430\u0439\u0434\u0435\u043d \u0431\u043b\u043e\u043a " + blockName + " \u043d\u0430 \u043a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u0430\u0445 -> " + pos.method_10263() + ", " + pos.method_10264() + ", " + pos.method_10260()), false);
        }
    }

    private String getBlockName(class_2680 state) {
        return state.method_26204().method_8389().toString().replace("minecraft:", "").replace("_ore", "").replace("_", " ");
    }
}

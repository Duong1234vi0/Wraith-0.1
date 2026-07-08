/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_2680
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.block.BlockUpdateEvent;
import code.alphadlc.events.render.WorldLoadEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.MultiSelectSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.render.geometry.Render3D;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2680;

public class XRay
extends Module {
    private final Map<class_2338, class_2680> map = new HashMap<class_2338, class_2680>();
    private final SelectSetting modeSetting = new SelectSetting("\u0420\u0435\u0436\u0438\u043c", "\u0420\u0435\u0436\u0438\u043c \u0440\u0430\u0431\u043e\u0442\u044b \u0434\u043b\u044f XRay").value("\u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u0435 \u0431\u043b\u043e\u043a\u043e\u0432");
    private final MultiSelectSetting blockTypeSetting = new MultiSelectSetting("\u0411\u043b\u043e\u043a\u0438", "\u0411\u043b\u043e\u043a\u0438, \u043a\u043e\u0442\u043e\u0440\u044b\u0435 \u0431\u0443\u0434\u0443\u0442 \u043e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c\u0441\u044f").value("\u0414\u0440\u0435\u0432\u043d\u0438\u0435 \u043e\u0431\u043b\u043e\u043c\u043a\u0438", "\u0410\u043b\u043c\u0430\u0437\u044b", "\u0418\u0437\u0443\u043c\u0440\u0443\u0434\u044b", "\u0416\u0435\u043b\u0435\u0437\u043e", "\u0417\u043e\u043b\u043e\u0442\u043e");

    public XRay() {
        super("XRay", ModuleCategory.RENDER);
        this.setup(this.modeSetting, this.blockTypeSetting);
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent e) {
        this.map.forEach((key, value) -> {
            if (this.blockTypeSetting.getSelected().toString().toLowerCase().contains(this.getBlockName((class_2680)value))) {
                Render3D.drawBox(new class_238(key), this.getColorByBlock((class_2680)value), 1.0f);
            }
        });
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent e) {
        this.map.clear();
    }

    @EventHandler
    public void onBlockUpdate(BlockUpdateEvent e) {
        class_2680 state = e.state();
        class_2338 pos = e.pos();
        switch (e.type()) {
            case UPDATE: {
                if (this.getColorByBlock(state) != -1 && !this.map.containsKey(pos)) {
                    this.map.put(pos, state);
                }
                if (!this.map.containsKey(pos) || this.map.get(pos).equals(state)) break;
                this.map.remove(pos);
                break;
            }
            case UNLOAD: {
                this.map.remove(pos);
            }
        }
    }

    private int getColorByBlock(class_2680 block) {
        return switch (this.getBlockName(block)) {
            case "\u0414\u0440\u0435\u0432\u043d\u0438\u0435 \u043e\u0431\u043b\u043e\u043c\u043a\u0438" -> -5868204;
            case "\u0410\u043b\u043c\u0430\u0437\u044b" -> -15107199;
            case "\u0418\u0437\u0443\u043c\u0440\u0443\u0434\u044b" -> -12482789;
            case "\u0416\u0435\u043b\u0435\u0437\u043e" -> -9090017;
            case "\u0417\u043e\u043b\u043e\u0442\u043e" -> -3819208;
            default -> -1;
        };
    }

    private String getBlockName(class_2680 state) {
        return state.method_26204().method_8389().toString().replace("minecraft:", "").replace("_ore", "").replace("_", " ");
    }
}

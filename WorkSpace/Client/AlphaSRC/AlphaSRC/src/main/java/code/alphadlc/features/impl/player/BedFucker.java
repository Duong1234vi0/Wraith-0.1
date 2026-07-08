/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1922
 *  net.minecraft.class_2244
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 */
package code.alphadlc.features.impl.player;

import code.alphadlc.events.player.RotationUpdateEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.geometry.Render3D;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.warp.TurnsConfig;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.math.task.TaskPriority;
import java.util.Comparator;
import net.minecraft.class_1268;
import net.minecraft.class_1922;
import net.minecraft.class_2244;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_265;
import net.minecraft.class_2680;

public class BedFucker
extends Module {
    private class_2338 targetPos;
    private class_265 targetShape;
    private final BooleanSetting rotateSetting = new BooleanSetting("\u041f\u043e\u0432\u043e\u0440\u043e\u0442", "\u041f\u043e\u0432\u043e\u0440\u0430\u0447\u0438\u0432\u0430\u0442\u044c\u0441\u044f \u043a \u043a\u0440\u043e\u0432\u0430\u0442\u0438").setValue(true);
    private final SliderSettings radiusSetting = new SliderSettings("\u0420\u0430\u0434\u0438\u0443\u0441", "\u041b\u043e\u043c\u0430\u0435\u0442 \u043a\u0440\u043e\u0432\u0430\u0442\u0438 \u0432 \u0440\u0430\u0434\u0438\u0443\u0441\u0435").setValue(4.0f).range(1, 6);

    public BedFucker() {
        super("BedFucker", ModuleCategory.PLAYER);
        this.setup(this.rotateSetting, this.radiusSetting);
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent e) {
        if (this.targetPos != null && this.targetShape != null && !this.targetShape.method_1110()) {
            Render3D.drawShape(this.targetPos, this.targetShape, ColorAssist.getClientColor(), 2.0f);
        }
    }

    @EventHandler
    public void onRotationUpdate(RotationUpdateEvent e) {
        if (e.getType() != 0 || BedFucker.mc.field_1724 == null || BedFucker.mc.field_1687 == null || BedFucker.mc.field_1761 == null) {
            return;
        }
        this.targetPos = BedFucker.mc.field_1724.method_24515();
        int radius = this.radiusSetting.getInt();
        this.targetPos = PlayerInteractionHelper.getCube(BedFucker.mc.field_1724.method_24515(), radius, radius, true).stream().filter(this::isBed).min(Comparator.comparingDouble(p -> p.method_46558().method_1025(BedFucker.mc.field_1724.method_19538()))).orElse(null);
        if (this.targetPos != null) {
            if (this.rotateSetting.isValue()) {
                TurnsConnection.INSTANCE.rotateTo(MathAngle.calculateAngle(this.targetPos.method_46558()), TurnsConfig.DEFAULT, TaskPriority.HIGH_IMPORTANCE_1, this);
            }
            class_2680 state = BedFucker.mc.field_1687.method_8320(this.targetPos);
            this.targetShape = state.method_26218((class_1922)BedFucker.mc.field_1687, this.targetPos);
            BedFucker.mc.field_1761.method_2902(this.targetPos, class_2350.field_11036);
            BedFucker.mc.field_1724.method_6104(class_1268.field_5808);
        } else {
            this.targetShape = null;
        }
    }

    private boolean isBed(class_2338 pos) {
        class_2680 state = BedFucker.mc.field_1687.method_8320(pos);
        return state.method_26204() instanceof class_2244;
    }
}

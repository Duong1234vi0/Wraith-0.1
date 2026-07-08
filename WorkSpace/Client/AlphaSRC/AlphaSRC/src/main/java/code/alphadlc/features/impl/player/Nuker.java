/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1922
 *  net.minecraft.class_2246
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
import java.util.Objects;
import net.minecraft.class_1268;
import net.minecraft.class_1922;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_265;
import net.minecraft.class_2680;

public class Nuker
extends Module {
    public class_2338 pos;
    private class_265 shape;
    private final BooleanSetting rotateSetting = new BooleanSetting("\u041f\u043e\u0432\u043e\u0440\u043e\u0442", "\u041b\u043e\u043c\u0430\u0442\u044c \u0431\u043b\u043e\u043a\u0438 \u043d\u0438\u0436\u0435 \u0438\u0433\u0440\u043e\u043a\u0430").setValue(true);
    private final BooleanSetting downSetting = new BooleanSetting("\u0412\u043d\u0438\u0437", "\u041b\u043e\u043c\u0430\u0442\u044c \u0431\u043b\u043e\u043a\u0438 \u043d\u0438\u0436\u0435 \u0438\u0433\u0440\u043e\u043a\u0430").setValue(true);
    private final SliderSettings radiusSetting = new SliderSettings("\u0420\u0430\u0434\u0438\u0443\u0441", "\u041b\u043e\u043c\u0430\u0435\u0442 \u0431\u043b\u043e\u043a\u0438 \u0432 \u0440\u0430\u0434\u0438\u0443\u0441\u0435 \u0432\u043e\u043a\u0440\u0443\u0433 \u0432\u0430\u0441").setValue(3.0f).range(1, 6);

    public Nuker() {
        super("Nuker", ModuleCategory.PLAYER);
        this.setup(this.rotateSetting, this.downSetting, this.radiusSetting);
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent e) {
        if (this.pos != null && this.shape != null && !this.shape.method_1110()) {
            Render3D.drawShape(this.pos, this.shape, ColorAssist.getClientColor(), 2.0f);
        }
    }

    @EventHandler
    public void onRotationUpdate(RotationUpdateEvent e) {
        if (e.getType() == 0) {
            this.pos = PlayerInteractionHelper.getCube(Nuker.mc.field_1724.method_24515(), this.radiusSetting.getInt(), this.radiusSetting.getInt(), this.downSetting.isValue()).stream().filter(this::validBlock).min(Comparator.comparingDouble(this::blockPriority)).orElse(null);
            if (this.pos != null) {
                if (this.rotateSetting.isValue()) {
                    TurnsConnection.INSTANCE.rotateTo(MathAngle.calculateAngle(this.pos.method_46558()), TurnsConfig.DEFAULT, TaskPriority.HIGH_IMPORTANCE_1, this);
                }
                this.shape = Nuker.mc.field_1687.method_8320(this.pos).method_26218((class_1922)Nuker.mc.field_1687, this.pos);
                Nuker.mc.field_1761.method_2902(this.pos, class_2350.field_11036);
                Nuker.mc.field_1724.method_6104(class_1268.field_5808);
            }
        }
    }

    private double blockPriority(class_2338 pos) {
        return switch (Nuker.mc.field_1687.method_8320(pos).method_26204().method_63499().replace("block.minecraft.", "")) {
            case "blue_bed" -> 0.0;
            case "red_bed" -> 0.0;
            case "pink_bed" -> 0.0;
            case "yellow_bed" -> 0.0;
            case "white_bed" -> 0.0;
            case "gray_bed" -> 0.0;
            case "lightblue_bed" -> 0.0;
            case "cyan_bed" -> 0.0;
            case "purple_bed" -> 0.0;
            case "magent_bed" -> 0.0;
            case "ancient_debris" -> 1.0;
            default -> Nuker.mc.field_1724.method_5707(pos.method_46558());
        };
    }

    private boolean validBlock(class_2338 pos) {
        class_2680 state = Objects.requireNonNull(Nuker.mc.field_1687).method_8320(pos);
        return !PlayerInteractionHelper.isAir(state) && state.method_26204() != class_2246.field_10382 && state.method_26204() != class_2246.field_10164 && state.method_26204() != class_2246.field_9987 && state.method_26204() != class_2246.field_10499;
    }

    public class_2338 getPos() {
        return this.pos;
    }

    public class_265 getShape() {
        return this.shape;
    }

    public BooleanSetting getRotateSetting() {
        return this.rotateSetting;
    }

    public BooleanSetting getDownSetting() {
        return this.downSetting;
    }

    public SliderSettings getRadiusSetting() {
        return this.radiusSetting;
    }
}

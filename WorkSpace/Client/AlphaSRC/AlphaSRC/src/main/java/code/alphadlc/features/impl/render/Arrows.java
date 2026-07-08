/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$class_4534
 *  com.mojang.blaze3d.platform.GlStateManager$class_4535
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10142
 *  net.minecraft.class_10156
 *  net.minecraft.class_1297
 *  net.minecraft.class_243
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_2960
 *  net.minecraft.class_4587
 *  net.minecraft.class_5498
 *  net.minecraft.class_742
 *  net.minecraft.class_7833
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.common.animation.Animation;
import code.alphadlc.common.animation.Direction;
import code.alphadlc.common.animation.implement.Decelerate;
import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.events.render.DrawEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.List;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_5498;
import net.minecraft.class_742;
import net.minecraft.class_7833;
import net.minecraft.class_9801;
import org.joml.Matrix4f;

public class Arrows
extends Module {
    private final class_2960 iconId = class_2960.method_60654((String)"textures/arrow.png");
    private final Animation radiusAnim = new Decelerate().setMs(150).setValue(6.0);
    private final SliderSettings radiusSetting = new SliderSettings("\u0420\u0430\u0434\u0438\u0443\u0441", "\u0420\u0430\u0434\u0438\u0443\u0441 \u0441\u0442\u0440\u0435\u043b\u043e\u043a").setValue(50.0f).range(30, 100);
    private final SliderSettings sizeSetting = new SliderSettings("\u0420\u0430\u0437\u043c\u0435\u0440", "\u0420\u0430\u0437\u043c\u0435\u0440 \u0441\u0442\u0440\u0435\u043b\u043e\u043a").setValue(10.0f).range(8, 20);
    private final ColorSetting colorSetting = new ColorSetting("\u0426\u0432\u0435\u0442", "\u0426\u0432\u0435\u0442 \u0441\u0442\u0440\u0435\u043b\u043e\u043a").value(new Color(150, 130, 255).getRGB()).presets(new Color(150, 130, 255).getRGB(), new Color(120, 180, 255).getRGB(), new Color(235, 120, 220).getRGB(), new Color(255, 255, 255).getRGB());

    public Arrows() {
        super("Arrows", "Arrows", ModuleCategory.RENDER);
        this.setup(this.radiusSetting, this.sizeSetting, this.colorSetting);
    }

    @EventHandler
    public void onTick(TickEvent e) {
        this.radiusAnim.setDirection(Arrows.mc.field_1724.method_5624() ? Direction.FORWARDS : Direction.BACKWARDS);
    }

    @EventHandler
    public void onDraw(DrawEvent e) {
        if (Arrows.mc.field_1687 == null || Arrows.mc.field_1724 == null) {
            return;
        }
        class_4587 matrix = e.getDrawContext().method_51448();
        List<class_742> players = Arrows.mc.field_1687.method_18456().stream().filter(p -> p != Arrows.mc.field_1724).filter(class_1297::method_5805).filter(p -> !p.method_31481()).filter(p -> !p.method_7325()).toList();
        float middleW = (float)mc.method_22683().method_4486() / 2.0f;
        float middleH = (float)mc.method_22683().method_4502() / 2.0f;
        float posY = middleH - this.radiusSetting.getValue() - this.radiusAnim.getOutput().floatValue();
        float size = this.sizeSetting.getValue();
        if (!Arrows.mc.field_1690.field_1842 && Arrows.mc.field_1690.method_31044().equals((Object)class_5498.field_26664) && !players.isEmpty()) {
            RenderSystem.enableBlend();
            RenderSystem.disableCull();
            RenderSystem.disableDepthTest();
            RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_CONSTANT_ALPHA);
            RenderSystem.setShaderTexture((int)0, (class_2960)this.iconId);
            RenderSystem.setShader((class_10156)class_10142.field_53880);
            class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
            players.forEach(player -> {
                int color = FriendUtils.isFriend((class_1297)player) ? ColorAssist.getFriendColor() : this.colorSetting.getColor();
                float yaw = Arrows.getRotations((class_1297)player) - Arrows.mc.field_1773.method_19418().method_19330();
                matrix.method_22903();
                matrix.method_46416(middleW, middleH, 0.0f);
                matrix.method_22907(class_7833.field_40718.rotationDegrees(yaw));
                matrix.method_46416(-middleW, -middleH, 0.0f);
                Matrix4f matrix4f = matrix.method_23760().method_23761();
                buffer.method_22918(matrix4f, middleW - size / 2.0f, posY + size, 0.0f).method_22913(0.0f, 1.0f).method_39415(ColorAssist.multAlpha(ColorAssist.multDark(color, 0.4f), 0.5f));
                buffer.method_22918(matrix4f, middleW + size / 2.0f, posY + size, 0.0f).method_22913(1.0f, 1.0f).method_39415(ColorAssist.multAlpha(ColorAssist.multDark(color, 0.4f), 0.5f));
                buffer.method_22918(matrix4f, middleW + size / 2.0f, posY, 0.0f).method_22913(1.0f, 0.0f).method_39415(color);
                buffer.method_22918(matrix4f, middleW - size / 2.0f, posY, 0.0f).method_22913(0.0f, 0.0f).method_39415(color);
                matrix.method_46416(middleW, middleH, 0.0f);
                matrix.method_22907(class_7833.field_40718.rotationDegrees(-yaw));
                matrix.method_46416(-middleW, -middleH, 0.0f);
                matrix.method_22909();
            });
            class_286.method_43433((class_9801)buffer.method_60800());
            RenderSystem.enableDepthTest();
            RenderSystem.enableCull();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableBlend();
        }
    }

    public static float getRotations(class_1297 entity) {
        float tickDelta = mc.method_61966().method_60637(true);
        class_243 vec3d = entity.method_30950(tickDelta).method_1020(Arrows.mc.field_1724.method_30950(tickDelta));
        return (float)(-(Math.atan2(vec3d.field_1352, vec3d.field_1350) * 57.29577951308232));
    }
}

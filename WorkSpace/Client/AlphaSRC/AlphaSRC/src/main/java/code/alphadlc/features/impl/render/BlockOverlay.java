/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1922
 *  net.minecraft.class_2338
 *  net.minecraft.class_239
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_265
 *  net.minecraft.class_2724
 *  net.minecraft.class_3532
 *  net.minecraft.class_3965
 *  net.minecraft.class_418
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.EntityDeathEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.geometry.Render3D;
import code.alphadlc.utils.display.render.geometry.ShaderBox3D;
import java.awt.Color;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_239;
import net.minecraft.class_265;
import net.minecraft.class_2724;
import net.minecraft.class_3532;
import net.minecraft.class_3965;
import net.minecraft.class_418;

public class BlockOverlay
extends Module {
    private final SelectSetting mode = new SelectSetting("\u0420\u0435\u0436\u0438\u043c", "\u0422\u0438\u043f \u043e\u0442\u0440\u0438\u0441\u043e\u0432\u043a\u0438 \u0431\u043b\u043e\u043a\u0430").value("\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u0428\u0435\u0439\u0434\u0435\u0440").selected("\u0428\u0435\u0439\u0434\u0435\u0440");
    private final SelectSetting style = new SelectSetting("\u0421\u0442\u0438\u043b\u044c", "\u0421\u0442\u0438\u043b\u044c \u0448\u0435\u0439\u0434\u0435\u0440\u0430 \u0431\u043b\u043e\u043a\u0430").value("\u042d\u043d\u0435\u0440\u0433\u0438\u044f", "\u041f\u043b\u0430\u0437\u043c\u0430", "\u0421\u043e\u0442\u044b").selected("\u042d\u043d\u0435\u0440\u0433\u0438\u044f").visible(() -> !this.mode.isSelected("\u041e\u0431\u044b\u0447\u043d\u044b\u0439"));
    private final ColorSetting color = new ColorSetting("\u0426\u0432\u0435\u0442", "\u0422\u043e\u043d\u0438\u0440\u043e\u0432\u043a\u0430 \u0448\u0435\u0439\u0434\u0435\u0440\u0430 \u0438 \u043a\u043e\u043d\u0442\u0443\u0440\u0430").value(new Color(150, 130, 255, 175).getRGB()).presets(new Color(150, 130, 255, 175).getRGB(), new Color(120, 180, 255, 175).getRGB(), new Color(235, 120, 220, 175).getRGB(), new Color(255, 255, 255, 175).getRGB());
    private final SliderSettings speed = new SliderSettings("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u0438 \u0448\u0435\u0439\u0434\u0435\u0440\u0430").range(0.35f, 2.75f).step(0.05f).setValue(1.0f).visible(() -> !this.mode.isSelected("\u041e\u0431\u044b\u0447\u043d\u044b\u0439"));
    private final SliderSettings opacity = new SliderSettings("\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c", "\u041d\u0435\u043f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u043a\u043e\u0441\u043c\u0438\u0447\u0435\u0441\u043a\u043e\u0433\u043e \u0448\u0435\u0439\u0434\u0435\u0440\u0430").range(0.1f, 1.0f).step(0.05f).setValue(0.9f).visible(() -> !this.mode.isSelected("\u041e\u0431\u044b\u0447\u043d\u044b\u0439"));
    private final BooleanSetting outline = new BooleanSetting("\u041a\u043e\u043d\u0442\u0443\u0440", "\u0420\u0438\u0441\u043e\u0432\u0430\u0442\u044c \u043a\u043e\u043d\u0442\u0443\u0440 \u043f\u043e\u0432\u0435\u0440\u0445 \u0448\u0435\u0439\u0434\u0435\u0440\u0430").setValue(true);
    private final SliderSettings outlineWidth = new SliderSettings("\u0422\u043e\u043b\u0449\u0438\u043d\u0430 \u043a\u043e\u043d\u0442\u0443\u0440\u0430", "\u0422\u043e\u043b\u0449\u0438\u043d\u0430 \u043e\u0431\u0432\u043e\u0434\u043a\u0438 \u0431\u043b\u043e\u043a\u0430").range(1.0f, 4.0f).step(0.25f).setValue(2.0f).visible(this.outline::isValue);
    private final SliderSettings smoothness = new SliderSettings("\u041f\u043b\u0430\u0432\u043d\u043e\u0441\u0442\u044c", "\u041f\u043b\u0430\u0432\u043d\u043e\u0441\u0442\u044c \u043f\u0435\u0440\u0435\u043c\u0435\u0449\u0435\u043d\u0438\u044f \u043e\u0432\u0435\u0440\u043b\u0435\u044f").range(1, 10).step(1.0f).setValue(5.0f);
    private float smoothX;
    private float smoothY;
    private float smoothZ;
    private boolean smoothInitialized;

    public static BlockOverlay getInstance() {
        return Instance.get(BlockOverlay.class);
    }

    public BlockOverlay() {
        super("BlockOverlay", "BlockOverlay", ModuleCategory.RENDER);
        this.setup(this.mode, this.style, this.color, this.speed, this.opacity, this.outline, this.outlineWidth, this.smoothness);
    }

    @Override
    public void deactivate() {
        this.resetSmoothState();
        super.deactivate();
    }

    private void resetSmoothState() {
        this.smoothInitialized = false;
        this.smoothZ = 0.0f;
        this.smoothY = 0.0f;
        this.smoothX = 0.0f;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (BlockOverlay.mc.field_1724 != null && event.getEntity() == BlockOverlay.mc.field_1724) {
            this.resetSmoothState();
        }
    }

    @EventHandler
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof class_2724) {
            this.resetSmoothState();
        }
    }

    @EventHandler(value=4)
    public void onWorldRender(WorldRenderEvent e) {
        this.renderOverlay(e);
    }

    public void renderOverlay(WorldRenderEvent e) {
        class_3965 result;
        if (BlockOverlay.mc.field_1724 == null || BlockOverlay.mc.field_1687 == null || !BlockOverlay.mc.field_1724.method_5805() || BlockOverlay.mc.field_1724.method_6032() <= 0.0f) {
            return;
        }
        if (BlockOverlay.mc.field_1755 instanceof class_418) {
            return;
        }
        class_239 class_2392 = BlockOverlay.mc.field_1765;
        if (!(class_2392 instanceof class_3965) || (result = (class_3965)class_2392).method_17783() != class_239.class_240.field_1332) {
            return;
        }
        class_2338 pos = result.method_17777();
        class_265 localShape = BlockOverlay.mc.field_1687.method_8320(pos).method_26218((class_1922)BlockOverlay.mc.field_1687, pos);
        if (localShape.method_1110()) {
            return;
        }
        float targetX = pos.method_10263();
        float targetY = pos.method_10264();
        float targetZ = pos.method_10260();
        if (!this.smoothInitialized || this.smoothness.getValue() <= 1.0f) {
            this.smoothX = targetX;
            this.smoothY = targetY;
            this.smoothZ = targetZ;
            this.smoothInitialized = true;
        } else {
            float t = class_3532.method_15363((float)((11.0f - this.smoothness.getValue()) / 10.0f), (float)0.05f, (float)1.0f);
            float step = t * t * 0.85f + 0.008f;
            step = class_3532.method_15363((float)step, (float)0.008f, (float)1.0f);
            step = 1.0f - (float)Math.pow(1.0f - step, e.getPartialTicks());
            this.smoothX = class_3532.method_16439((float)step, (float)this.smoothX, (float)targetX);
            this.smoothY = class_3532.method_16439((float)step, (float)this.smoothY, (float)targetY);
            this.smoothZ = class_3532.method_16439((float)step, (float)this.smoothZ, (float)targetZ);
        }
        class_2338 renderPos = class_2338.method_49637((double)this.smoothX, (double)this.smoothY, (double)this.smoothZ);
        float offsetX = this.smoothX - (float)renderPos.method_10263();
        float offsetY = this.smoothY - (float)renderPos.method_10264();
        float offsetZ = this.smoothZ - (float)renderPos.method_10260();
        class_265 offsetShape = localShape.method_1096((double)offsetX, (double)offsetY, (double)offsetZ);
        class_265 worldShape = localShape.method_1096((double)this.smoothX, (double)this.smoothY, (double)this.smoothZ);
        int overlayColor = this.color.getColor();
        boolean shaderDrawn = false;
        if (!this.mode.isSelected("\u041e\u0431\u044b\u0447\u043d\u044b\u0439")) {
            float time = ((float)(BlockOverlay.mc.field_1687.method_8510() % 1200L) + e.getPartialTicks()) * 0.045f * this.speed.getValue();
            ShaderBox3D.ShaderType shaderType = this.style.isSelected("\u041f\u043b\u0430\u0437\u043c\u0430") ? ShaderBox3D.ShaderType.PLASMA : (this.style.isSelected("\u0421\u043e\u0442\u044b") ? ShaderBox3D.ShaderType.HEX : ShaderBox3D.ShaderType.SHADER);
            shaderDrawn = ShaderBox3D.drawShaderShape(offsetShape, renderPos, shaderType, overlayColor, time, this.opacity.getValue(), e.getStack().method_23760());
        }
        if (!shaderDrawn) {
            Render3D.drawShapeAlternative(renderPos, offsetShape, overlayColor, this.outlineWidth.getValue(), true, this.outline.isValue(), true);
        } else if (this.outline.isValue()) {
            int outlineColor = ColorAssist.setAlpha(overlayColor, Math.max(140, ColorAssist.getAlpha(overlayColor)));
            Render3D.drawShapeAlternative(renderPos, offsetShape, outlineColor, this.outlineWidth.getValue(), false, true, true);
        }
    }
}

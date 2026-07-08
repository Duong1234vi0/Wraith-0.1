/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1921
 *  net.minecraft.class_238
 *  net.minecraft.class_3532
 *  net.minecraft.class_3882
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_4597$class_4598
 *  net.minecraft.class_572
 *  net.minecraft.class_7833
 *  org.joml.Matrix4f
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.RadioSetting;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.theme.ThemeManager;
import java.awt.Color;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_238;
import net.minecraft.class_3532;
import net.minecraft.class_3882;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_572;
import net.minecraft.class_7833;
import org.joml.Matrix4f;

public class ChinaHat
extends Module {
    private static final float PI = 0.06981317f;
    private final BooleanSetting viewOnOthers;
    private final RadioSetting colorMode = new RadioSetting("\u0420\u0435\u0436\u0438\u043c \u0446\u0432\u0435\u0442\u0430", "\u0412\u044b\u0431\u043e\u0440 \u0438\u0441\u0442\u043e\u0447\u043d\u0438\u043a\u0430 \u0446\u0432\u0435\u0442\u0430", new String[]{"\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439", "\u041a\u0430\u0441\u0442\u043e\u043c"}, "\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439");
    private final ColorSetting customColor = new ColorSetting("\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439 \u0446\u0432\u0435\u0442", "\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439 \u0446\u0432\u0435\u0442 \u0448\u043b\u044f\u043f\u044b").value(ColorAssist.getClientColor()).visible(() -> this.colorMode.get().equals("\u041a\u0430\u0441\u0442\u043e\u043c"));

    public ChinaHat() {
        super("ChinaHat", "ChinaHat", ModuleCategory.RENDER);
        this.viewOnOthers = new BooleanSetting("\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c \u043d\u0430 \u0434\u0440\u0443\u0433\u0438\u0445", "View on Others");
        this.setup(this.viewOnOthers, this.colorMode, this.customColor);
    }

    public void render(class_4587 matrixStack, class_4597 vertexConsumerProvider, class_1657 player, class_3882 model) {
        int darkenedColor;
        int baseColor;
        boolean isLocalPlayer = player == ChinaHat.mc.field_1724;
        boolean isFriend = FriendUtils.isFriend((class_1297)player);
        if (!(isLocalPlayer || isFriend || this.viewOnOthers.isValue())) {
            return;
        }
        if (!(model instanceof class_572)) {
            return;
        }
        class_572 bipedModel = (class_572)model;
        class_238 entityBoundingBox = player.method_5829();
        double radius = entityBoundingBox.field_1320 - entityBoundingBox.field_1323;
        int headSlot = 39;
        class_1799 headStack = player.method_31548().method_5438(headSlot);
        boolean hasHelmet = !headStack.method_7960();
        float offset = hasHelmet ? 0.48f : 0.42f;
        matrixStack.method_22903();
        bipedModel.field_3398.method_22703(matrixStack);
        matrixStack.method_46416(0.0f, -offset, 0.0f);
        matrixStack.method_22907(class_7833.field_40717.rotationDegrees(180.0f));
        matrixStack.method_22907(class_7833.field_40716.rotationDegrees(90.0f));
        Matrix4f matrix4f = matrixStack.method_23760().method_23761();
        if (isFriend) {
            baseColor = new Color(0, 255, 0, 255).getRGB();
            darkenedColor = new Color(0, 170, 0, 255).getRGB();
        } else if (this.colorMode.get().equals("\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439")) {
            baseColor = ThemeManager.primaryColor.getColor();
            darkenedColor = ThemeManager.secondaryColor.getColor();
        } else {
            baseColor = this.customColor.getColor();
            darkenedColor = this.darken(baseColor, 0.5f);
        }
        float y = 0.0f;
        float outlineY = -0.01f;
        float outlineHeight = 0.01f;
        float outlineThickness = 0.01f;
        float radiusOuter = (float)(radius + (double)outlineThickness);
        float radiusInner = (float)(radius - (double)outlineThickness);
        class_4588 buffer = vertexConsumerProvider.getBuffer(class_1921.method_49042());
        long time = System.currentTimeMillis();
        for (int i = 0; i < 360; ++i) {
            float angle1 = (float)i * 0.06981317f;
            float angle2 = (float)(i + 1) * 0.06981317f;
            int gradientAngle = i * 8;
            float x1 = (float)((double)class_3532.method_15374((float)angle1) * radius);
            float z1 = (float)((double)class_3532.method_15362((float)angle1) * radius);
            float x2 = (float)((double)class_3532.method_15374((float)angle2) * radius);
            float z2 = (float)((double)class_3532.method_15362((float)angle2) * radius);
            int gradientColor = this.gradientAnimated(3, gradientAngle, baseColor, darkenedColor, time);
            this.putVertex(buffer, matrix4f, x1, y, z1, gradientColor);
            this.putVertex(buffer, matrix4f, x2, y, z2, gradientColor);
            this.putVertex(buffer, matrix4f, 0.0f, 0.3f, 0.0f, baseColor);
            this.putVertex(buffer, matrix4f, 0.0f, 0.3f, 0.0f, baseColor);
        }
        float prevX1Outer = class_3532.method_15374((float)0.0f) * radiusOuter;
        float prevZ1Outer = class_3532.method_15362((float)0.0f) * radiusOuter;
        float prevX1Inner = class_3532.method_15374((float)0.0f) * radiusInner;
        float prevZ1Inner = class_3532.method_15362((float)0.0f) * radiusInner;
        for (int i = 1; i <= 181; ++i) {
            float angle2 = (float)i * 0.06981317f;
            int gradientAngle = (i - 1) * 8;
            float x2Outer = class_3532.method_15374((float)angle2) * radiusOuter;
            float z2Outer = class_3532.method_15362((float)angle2) * radiusOuter;
            float x2Inner = class_3532.method_15374((float)angle2) * radiusInner;
            float z2Inner = class_3532.method_15362((float)angle2) * radiusInner;
            int gradientColor = this.gradientAnimated(3, gradientAngle, baseColor, darkenedColor, time);
            this.putVertex(buffer, matrix4f, prevX1Outer, outlineY, prevZ1Outer, gradientColor);
            this.putVertex(buffer, matrix4f, prevX1Outer, outlineY + outlineHeight, prevZ1Outer, gradientColor);
            this.putVertex(buffer, matrix4f, x2Outer, outlineY + outlineHeight, z2Outer, gradientColor);
            this.putVertex(buffer, matrix4f, x2Outer, outlineY, z2Outer, gradientColor);
            this.putVertex(buffer, matrix4f, prevX1Inner, outlineY + outlineHeight, prevZ1Inner, gradientColor);
            this.putVertex(buffer, matrix4f, x2Inner, outlineY + outlineHeight, z2Inner, gradientColor);
            this.putVertex(buffer, matrix4f, x2Outer, outlineY + outlineHeight, z2Outer, gradientColor);
            this.putVertex(buffer, matrix4f, prevX1Outer, outlineY + outlineHeight, prevZ1Outer, gradientColor);
            prevX1Outer = x2Outer;
            prevZ1Outer = z2Outer;
            prevX1Inner = x2Inner;
            prevZ1Inner = z2Inner;
        }
        matrixStack.method_22909();
        if (vertexConsumerProvider instanceof class_4597.class_4598) {
            class_4597.class_4598 immediate = (class_4597.class_4598)vertexConsumerProvider;
            immediate.method_22993();
        }
    }

    private void putVertex(class_4588 buffer, Matrix4f matrix, float x, float y, float z, int rgba) {
        buffer.method_22918(matrix, x, y, z).method_1336(this.red(rgba), this.green(rgba), this.blue(rgba), this.alpha(rgba));
    }

    private int darken(int color, float factor) {
        int r = (int)((float)(color >> 16 & 0xFF) * factor);
        int g = (int)((float)(color >> 8 & 0xFF) * factor);
        int b = (int)((float)(color & 0xFF) * factor);
        return 0xFF000000 | r << 16 | g << 8 | b;
    }

    private int gradient(int speed, int gradientAngle, int color1, int color2) {
        float ratio = (float)(gradientAngle % 360) / 360.0f;
        ratio = (float)((Math.sin((double)ratio * Math.PI * 2.0 * (double)speed) + 1.0) / 2.0);
        return ColorAssist.interpolate(color1, color2, ratio);
    }

    private int gradientAnimated(int speed, int gradientAngle, int color1, int color2, long time) {
        float animatedAngle = ((long)gradientAngle + time / 10L) % 360L;
        float ratio = animatedAngle / 360.0f;
        ratio = (float)((Math.sin((double)ratio * Math.PI * 2.0 * (double)speed) + 1.0) / 2.0);
        return ColorAssist.interpolate(color1, color2, ratio);
    }

    private int red(int color) {
        return color >> 16 & 0xFF;
    }

    private int green(int color) {
        return color >> 8 & 0xFF;
    }

    private int blue(int color) {
        return color & 0xFF;
    }

    private int alpha(int color) {
        return color >> 24 & 0xFF;
    }

    public static ChinaHat getInstance() {
        return Instance.get(ChinaHat.class);
    }
}

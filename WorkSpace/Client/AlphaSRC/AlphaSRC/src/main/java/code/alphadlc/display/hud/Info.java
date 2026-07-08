/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_2338
 *  net.minecraft.class_2374
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.hud;

import code.alphadlc.features.impl.misc.FreeCam;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.utils.client.managers.api.draggable.AbstractDraggable;
import code.alphadlc.utils.client.packet.network.Network;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.post.KawaseBlur;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.simulate.Simulations;
import code.alphadlc.utils.math.calc.Calculate;
import java.awt.Color;
import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class Info
extends AbstractDraggable {
    private static final Info INSTANCE = new Info();
    private static final float LINE_H = 14.0f;
    private static final float LINE_GAP = 1.0f;
    private static final float H_PAD = 7.0f;
    private static final float LABEL_VALUE_GAP = 2.0f;
    private static final int MAX_LINES = 4;
    private int fpsCount = 0;
    private boolean showTps = false;
    private boolean showFps = true;
    private boolean showBps = false;
    private boolean showCoords = true;
    private boolean rightSide = false;
    private final String[] lineLabels = new String[4];
    private final String[] lineValues = new String[4];
    private final float[] lineWidths = new float[4];
    private int lineCount = 0;

    private Info() {
        super("Info", 0, 0, 60, 0, false);
    }

    public static Info getInstance() {
        return INSTANCE;
    }

    public boolean isShowTps() {
        return this.showTps;
    }

    public void setShowTps(boolean showTps) {
        this.showTps = showTps;
    }

    public boolean isShowFps() {
        return this.showFps;
    }

    public void setShowFps(boolean showFps) {
        this.showFps = showFps;
    }

    public boolean isShowBps() {
        return this.showBps;
    }

    public void setShowBps(boolean showBps) {
        this.showBps = showBps;
    }

    public boolean isShowCoords() {
        return this.showCoords;
    }

    public void setShowCoords(boolean showCoords) {
        this.showCoords = showCoords;
    }

    public boolean isRightSide() {
        return this.rightSide;
    }

    public void setRightSide(boolean rightSide) {
        this.rightSide = rightSide;
    }

    private class_2338 resolveCoordinateBlockPos() {
        FreeCam freeCam = FreeCam.getInstance();
        if (freeCam != null && freeCam.isState() && freeCam.pos != null) {
            return class_2338.method_49638((class_2374)freeCam.pos);
        }
        return Info.mc.field_1724.method_24515();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean visible() {
        Hud hud = Hud.getInstance();
        if (hud == null) return false;
        if (!hud.isState()) return false;
        if (!Hud.interfaceSettings.isSelected("Info")) return false;
        if (!this.hasAnyInfoLine()) return false;
        return true;
    }

    private boolean hasAnyInfoLine() {
        return this.showTps || this.showFps || this.showBps || this.showCoords;
    }

    @Override
    public void tick() {
        super.tick();
        this.fpsCount = mc.method_47599();
    }

    @Override
    public void drawDraggable(class_332 context) {
        if (Info.mc.field_1724 == null || !this.hasAnyInfoLine()) {
            return;
        }
        class_4587 matrix = context.method_51448();
        class_2338 blockPos = this.resolveCoordinateBlockPos();
        FontRenderer font = Fonts.getSize(13, Fonts.Type.SuisseIntlMedium);
        int valueColor = -1;
        this.lineCount = 0;
        if (this.showTps) {
            this.lineLabels[this.lineCount] = "TPS: ";
            this.lineValues[this.lineCount] = String.valueOf(Calculate.round(Network.TPS, 0.1f));
            ++this.lineCount;
        }
        if (this.showFps) {
            this.lineLabels[this.lineCount] = "FPS: ";
            this.lineValues[this.lineCount] = String.valueOf(this.fpsCount);
            ++this.lineCount;
        }
        if (this.showBps) {
            this.lineLabels[this.lineCount] = "BPS: ";
            this.lineValues[this.lineCount] = String.valueOf(Calculate.round(Simulations.getSpeedSqrt((class_1297)Info.mc.field_1724) * 20.0, 0.25));
            ++this.lineCount;
        }
        if (this.showCoords) {
            this.lineLabels[this.lineCount] = "Coords: ";
            this.lineValues[this.lineCount] = blockPos.method_10263() + ", " + blockPos.method_10264() + ", " + blockPos.method_10260();
            ++this.lineCount;
        }
        if (this.lineCount == 0) {
            return;
        }
        float maxPanelW = 0.0f;
        for (int i = 0; i < this.lineCount; ++i) {
            this.lineWidths[i] = font.getStringWidth(this.lineLabels[i]) + 2.0f + font.getStringWidth(this.lineValues[i]) + 14.0f;
            maxPanelW = Math.max(maxPanelW, this.lineWidths[i]);
        }
        float totalH = (float)this.lineCount * 14.0f + (float)Math.max(0, this.lineCount - 1) * 1.0f;
        float screenW = window.getScaledWidth();
        float screenH = window.getScaledHeight();
        float scale = this.getScaleFactor();
        float sideMargin = 2.0f;
        float bottomMargin = PlayerInteractionHelper.isChat(Info.mc.field_1755) ? 16.0f : 4.0f;
        float baseBottom = screenH - bottomMargin;
        float posX = this.rightSide ? screenW - sideMargin - maxPanelW * (1.0f + scale) / 2.0f : sideMargin + maxPanelW * (scale - 1.0f) / 2.0f;
        float posY = baseBottom - totalH * (1.0f + scale) / 2.0f;
        this.setX(Math.round(posX));
        this.setY(Math.round(posY));
        this.setWidth(Math.round(maxPanelW));
        this.setHeight(Math.round(totalH));
        this.clampToScreen();
        posX = this.getX();
        float lineY = posY = (float)this.getY();
        for (int i = 0; i < this.lineCount; ++i) {
            float lineX = this.rightSide ? posX + maxPanelW - this.lineWidths[i] : posX;
            this.drawInfoLine(matrix, font, lineX, lineY, this.lineWidths[i], this.lineLabels[i], this.lineValues[i], valueColor);
            lineY += 15.0f;
        }
    }

    private void drawInfoLine(class_4587 matrix, FontRenderer font, float x, float y, float width, String label, String value, int valueColor) {
        Hud.getInstance();
        if (Hud.blur.isValue() && !KawaseBlur.INSTANCE.fbos.isEmpty()) {
            Render2D.rectangleWithMask(matrix.method_23760().method_23761(), x, y, width, 14.0f, 7.0f, 0, KawaseBlur.INSTANCE.fbos.getFirst().method_30277());
        }
        rectangle.render(ShapeProperties.create(matrix, x, y, width, 14.0).round(7.0f).softness(2.5f).color(new Color(0, 0, 0, 210).getRGB()).build());
        float textY = this.centeredTextY(font, y);
        float textX = x + 7.0f;
        font.drawRainbowString(matrix, label, textX, textY, ColorAssist.getHudColor(), ColorAssist.getHudColor2());
        font.drawString(matrix, value, textX + font.getStringWidth(label) + 2.0f, textY, valueColor);
    }

    private float centeredTextY(FontRenderer font, float boxY) {
        return font.getCenteredY(boxY, 14.0f);
    }
}

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 *  net.minecraft.class_4587
 */
package code.alphadlc.utils.display.gif;

import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.display.render.shape.implement.Image;
import code.alphadlc.utils.math.frame.FrameRateCounter;
import net.minecraft.class_310;
import net.minecraft.class_4587;

public class GifRender {
    private int currentFrame = 0;
    private float frameTime = 0.0f;
    private final float frameDuration = 0.0015f;
    private final String[] frames;
    private final Image image;

    public GifRender(String path, int frameCount) {
        this.frames = new String[frameCount];
        for (int i = 0; i < frameCount; ++i) {
            this.frames[i] = String.format("minecraft:gif/backgrounds/mainmenutype1/%05d.png", i + 1);
        }
        this.image = new Image();
    }

    public void render(class_4587 matrix, float x, float y, float width, float height) {
        if (!class_310.method_1551().method_1569()) {
            return;
        }
        this.frameTime += FrameRateCounter.INSTANCE.getFps() > 0 ? 1.0f / (float)FrameRateCounter.INSTANCE.getFps() : 0.006f;
        if (this.frameTime >= 0.0015f) {
            this.currentFrame = (this.currentFrame + 1) % this.frames.length;
            this.frameTime = 0.0f;
        }
        this.image.setTexture(this.frames[this.currentFrame]).render(ShapeProperties.create(matrix, x, y, width, height).color(-1).build());
    }
}

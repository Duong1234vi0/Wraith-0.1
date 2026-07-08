/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10142
 *  net.minecraft.class_10149
 *  net.minecraft.class_10156
 *  net.minecraft.class_276
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_2960
 *  net.minecraft.class_5944
 *  net.minecraft.class_6367
 *  net.minecraft.class_9801
 */
package code.alphadlc.utils.display.render.post;

import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.util.CyclicStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10142;
import net.minecraft.class_10149;
import net.minecraft.class_10156;
import net.minecraft.class_276;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_2960;
import net.minecraft.class_5944;
import net.minecraft.class_6367;
import net.minecraft.class_9801;

public class GaussianBlur
implements QuickImports {
    private static final CyclicStack<class_276> stack = new CyclicStack<class_276>(){
        {
            this.add(new class_6367(1, 1, false));
            this.add(new class_6367(1, 1, false));
        }
    };
    private static final class_10156 SHADER_KEY = new class_10156(class_2960.method_60654((String)"core/gaussian_blur"), class_290.field_1592, class_10149.field_53930);

    public static void applyBlurEffect(class_276 read, class_276 write, int countOfPasses, float scaleMultiplier) {
        GaussianBlur.resizeFramebuffers();
        class_276 readBuffer = read;
        for (int i = 0; i < countOfPasses; ++i) {
            class_276 wroteBuffer = stack.next();
            GaussianBlur.executePass(readBuffer, wroteBuffer, scaleMultiplier, i % 2 == 0 ? 1.0f : 0.0f);
            readBuffer = wroteBuffer;
        }
        write.method_1235(false);
        readBuffer.method_35610();
        RenderSystem.setShader((class_10156)class_10142.field_53868);
        GaussianBlur.drawQuad();
        readBuffer.method_1242();
        write.method_1240();
        mc.method_1522().method_1235(false);
    }

    private static void resizeFramebuffers() {
        for (class_276 framebuffer : stack) {
            if (framebuffer.field_1482 == GaussianBlur.mc.method_1522().field_1482 && framebuffer.field_1481 == GaussianBlur.mc.method_1522().field_1481) continue;
            framebuffer.method_1234(GaussianBlur.mc.method_1522().field_1482, GaussianBlur.mc.method_1522().field_1481);
        }
    }

    private static void executePass(class_276 read, class_276 write, float scaleMultiplier, float offsetDirection) {
        write.method_1235(false);
        read.method_35610();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        class_5944 shaderProgram = RenderSystem.setShader((class_10156)SHADER_KEY);
        shaderProgram.method_34582("scaleMultiplier").method_1251(scaleMultiplier);
        shaderProgram.method_34582("offsetDirection").method_1251(offsetDirection);
        GaussianBlur.drawQuad();
        read.method_1242();
        write.method_1240();
        RenderSystem.disableBlend();
    }

    private static void drawQuad() {
        float width = mc.method_22683().method_4486();
        float height = mc.method_22683().method_4502();
        class_287 builder = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1592);
        builder.method_22912(0.0f, 0.0f, 0.0f);
        builder.method_22912(0.0f, height, 0.0f);
        builder.method_22912(width, height, 0.0f);
        builder.method_22912(width, 0.0f, 0.0f);
        class_286.method_43433((class_9801)builder.method_60800());
    }
}

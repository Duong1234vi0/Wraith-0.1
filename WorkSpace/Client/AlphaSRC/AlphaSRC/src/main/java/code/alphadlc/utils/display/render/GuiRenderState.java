/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_10142
 *  net.minecraft.class_10156
 *  net.minecraft.class_310
 *  net.minecraft.class_377
 *  net.minecraft.class_378
 *  net.minecraft.class_380
 */
package code.alphadlc.utils.display.render;

import code.alphadlc.mixins.accessor.FontManagerAccessor;
import code.alphadlc.mixins.accessor.FontStorageAccessor;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_310;
import net.minecraft.class_377;
import net.minecraft.class_378;
import net.minecraft.class_380;

public final class GuiRenderState {
    private GuiRenderState() {
    }

    public static void restoreAfterCustomDraw() {
        class_310 client = class_310.method_1551();
        if (client == null) {
            return;
        }
        client.method_1522().method_1235(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableCull();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.setShader((class_10156)class_10142.field_53880);
        GuiRenderState.restoreFontAtlasesNearest();
    }

    public static void restoreFontAtlasesNearest() {
        class_310 client = class_310.method_1551();
        if (client == null) {
            return;
        }
        class_378 fontManager = client.field_1708;
        if (fontManager == null) {
            return;
        }
        for (class_377 storage : ((FontManagerAccessor)fontManager).alphadlc$getFontStorages().values()) {
            for (class_380 atlas : ((FontStorageAccessor)storage).alphadlc$getGlyphAtlases()) {
                atlas.method_4527(false, false);
            }
        }
    }
}

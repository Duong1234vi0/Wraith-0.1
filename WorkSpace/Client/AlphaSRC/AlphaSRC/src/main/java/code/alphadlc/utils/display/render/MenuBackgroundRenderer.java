/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1921
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 */
package code.alphadlc.utils.display.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;

public final class MenuBackgroundRenderer {
    public static final class_2960 MENU_BACKGROUND_TEXTURE = class_2960.method_60655((String)"minecraft", (String)"textures/mainmenu/background.png");

    private MenuBackgroundRenderer() {
    }

    public static boolean hasBackgroundTexture() {
        class_310 client = class_310.method_1551();
        return client != null && client.method_1478().method_14486(MENU_BACKGROUND_TEXTURE).isPresent();
    }

    public static boolean renderFullscreen(class_332 context, int width, int height) {
        if (!MenuBackgroundRenderer.hasBackgroundTexture()) {
            return false;
        }
        RenderSystem.enableBlend();
        context.method_25290(class_1921::method_62277, MENU_BACKGROUND_TEXTURE, 0, 0, 0.0f, 0.0f, width, height, width, height);
        RenderSystem.disableBlend();
        return true;
    }
}

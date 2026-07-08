/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 */
package code.alphadlc.emotions;

import code.alphadlc.emotions.EmotionWheelManager;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.geometry.Render2D;
import java.util.UUID;
import net.minecraft.class_1657;
import net.minecraft.class_2960;
import net.minecraft.class_332;

public final class ClientBadgeRenderer {
    private static final int CLIENT_ICON_TEXTURE_SIZE = 2100;
    private static class_2960 clientIcon;

    private ClientBadgeRenderer() {
    }

    private static class_2960 getClientIcon() {
        if (clientIcon == null) {
            clientIcon = class_2960.method_60655((String)"mre", (String)"alphadlc_icon.png");
        }
        return clientIcon;
    }

    public static boolean hasCompatibleClient(UUID uuid) {
        EmotionWheelManager manager = EmotionWheelManager.getInstance();
        return manager != null && manager.hasCompatibleClient(uuid);
    }

    public static boolean hasCompatibleClient(class_1657 player) {
        return player != null && ClientBadgeRenderer.hasCompatibleClient(player.method_5667());
    }

    public static void drawRoundedBadge(class_332 context, float x, float y, float size) {
        try {
            Render2D.drawTexture(context, ClientBadgeRenderer.getClientIcon(), x, y, size, size / 2.0f, 2100, 2100, 2100, ColorAssist.rgba(255, 255, 255, 255), ColorAssist.rgba(255, 255, 255, 255));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

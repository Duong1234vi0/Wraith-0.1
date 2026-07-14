/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.util.BitSet;
import lightning.product.B_3871_I;
import lightning.product.L_3848_p;
import net.optifine.Config;
import net.optifine.shaders.Shaders;

public class SmartAnimations {
    private static boolean active;
    private static BitSet spritesRendered;
    private static BitSet texturesRendered;

    public static boolean isActive() {
        return active && !Shaders.isShadowPass;
    }

    public static void update() {
        active = Config.getGameSettings().s_1671_u;
    }

    public static void spriteRendered(B_3871_I sprite) {
        int i;
        if (sprite.c_3005_b() && (i = sprite.multiplayerClientSuggestionProvider()) >= 0) {
            spritesRendered.set(i);
        }
    }

    public static void spritesRendered(BitSet animationIndexes) {
        if (animationIndexes != null) {
            spritesRendered.or(animationIndexes);
        }
    }

    public static boolean isSpriteRendered(B_3871_I sprite) {
        if (!sprite.c_3005_b()) {
            return true;
        }
        int i = sprite.multiplayerClientSuggestionProvider();
        return i < 0 ? false : spritesRendered.get(i);
    }

    public static void resetSpritesRendered(L_3848_p atlasTexture) {
        if (atlasTexture.t_148_a()) {
            spritesRendered.clear();
        }
    }

    public static void textureRendered(int textureId) {
        if (textureId >= 0) {
            texturesRendered.set(textureId);
        }
    }

    public static boolean isTextureRendered(int texId) {
        return texId < 0 ? false : texturesRendered.get(texId);
    }

    public static void resetTexturesRendered() {
        texturesRendered.clear();
    }

    static {
        spritesRendered = new BitSet();
        texturesRendered = new BitSet();
    }
}



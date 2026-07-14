/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.FormattedText;
import lightning.product.U_2871_b;
import lightning.product.Y_4083_F;
import lightning.product.Z_1567_W;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;

public class EnchantmentNames {
    private static final g_2336_b n_1700_B = new g_2336_b("minecraft", "alt");
    private static final Z_1567_W J_1907_R = Z_1567_W.n_1700_B.n_1700_B(n_1700_B);
    private static final EnchantmentNames R_4764_Y = new EnchantmentNames();
    private final Random G_564_y = new Random();
    private final String[] P_1922_E = new String[]{"the", "elder", "scrolls", "klaatu", "berata", "niktu", "xyzzy", "bless", "curse", "light", "darkness", "fire", "air", "earth", "water", "hot", "dry", "cold", "wet", "ignite", "snuff", "embiggen", "twist", "shorten", "stretch", "fiddle", "destroy", "imbue", "galvanize", "enchant", "free", "limited", "range", "of", "towards", "inside", "sphere", "cube", "self", "other", "ball", "mental", "physical", "grow", "shrink", "demon", "elemental", "spirit", "animal", "creature", "beast", "humanoid", "undead", "fresh", "stale", "phnglui", "mglwnafh", "cthulhu", "rlyeh", "wgahnagl", "fhtagn", "baguette"};

    private EnchantmentNames() {
    }

    public static EnchantmentNames n_1700_B() {
        return R_4764_Y;
    }

    public FormattedText n_1700_B(Y_4083_F fontRenderer, int maxWidth) {
        StringBuilder stringbuilder = new StringBuilder();
        int i = this.G_564_y.nextInt(2) + 3;
        for (int j = 0; j < i; ++j) {
            if (j != 0) {
                stringbuilder.append(" ");
            }
            stringbuilder.append(j_3341_s.n_1700_B(this.P_1922_E, this.G_564_y));
        }
        return fontRenderer.J_1907_R().n_1700_B(new U_2871_b(stringbuilder.toString()).J_1907_R(J_1907_R), maxWidth, Z_1567_W.n_1700_B);
    }

    public void n_1700_B(long seed) {
        this.G_564_y.setSeed(seed);
    }
}



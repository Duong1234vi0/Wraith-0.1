/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.H_2506_c;
import lightning.product.MutableComponent;
import lightning.product.TextColor;
import lightning.product.U_2871_b;
import lightning.product.Z_1567_W;

public final class j_1376_w {
    public static MutableComponent n_1700_B(String text, int startColor, int endColor) {
        U_2871_b component = new U_2871_b("");
        if (text == null || text.isEmpty()) {
            return component;
        }
        int length = text.length();
        for (int i = 0; i < length; ++i) {
            char c = text.charAt(i);
            float ratio = length == 1 ? 0.0f : (float)i / (float)(length - 1);
            int color = H_2506_c.n_1700_B(endColor, startColor, ratio);
            U_2871_b letter = new U_2871_b(String.valueOf(c));
            letter.n_1700_B(Z_1567_W.n_1700_B.n_1700_B(TextColor.n_1700_B(color)));
            component.n_1700_B(letter);
        }
        return component;
    }

    public static MutableComponent n_1700_B(String text, int startColor, int endColor, int speed) {
        U_2871_b component = new U_2871_b("");
        if (text == null || text.isEmpty()) {
            return component;
        }
        int length = text.length();
        for (int i = 0; i < length; ++i) {
            char c = text.charAt(i);
            int color = H_2506_c.J_1907_R(speed, i, startColor, endColor);
            U_2871_b letter = new U_2871_b(String.valueOf(c));
            letter.n_1700_B(Z_1567_W.n_1700_B.n_1700_B(TextColor.n_1700_B(color)));
            component.n_1700_B(letter);
        }
        return component;
    }

    public static MutableComponent n_1700_B(String text, int startColor, int endColor, int speed, float ratio) {
        U_2871_b component = new U_2871_b("");
        if (text == null || text.isEmpty()) {
            return component;
        }
        int length = text.length();
        for (int i = 0; i < length; ++i) {
            char c = text.charAt(i);
            int index = (int)((float)i * ratio);
            int color = H_2506_c.J_1907_R(speed, index, startColor, endColor);
            U_2871_b letter = new U_2871_b(String.valueOf(c));
            letter.n_1700_B(Z_1567_W.n_1700_B.n_1700_B(TextColor.n_1700_B(color)));
            component.n_1700_B(letter);
        }
        return component;
    }
}



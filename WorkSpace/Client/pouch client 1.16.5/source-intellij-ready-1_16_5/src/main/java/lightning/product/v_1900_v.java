/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4024_W;
import lightning.product.H_2506_c;
import lightning.product.MutableComponent;
import lightning.product.TextColor;
import lightning.product.K_1200_E;
import lightning.product.U_2871_b;
import lightning.product.Z_1567_W;
import lightning.product.MinecraftAccess;
import lightning.product.q_3148_R;
import lightning.product.x_282_a;

public class v_1900_v
implements MinecraftAccess {
    public static final char n_1700_B = '\u00a7';

    public static MutableComponent n_1700_B(String text) {
        if (text == null || text.isEmpty()) {
            return new U_2871_b("");
        }
        int startColor = q_3148_R.n_1700_B(K_1200_E.w_1457_N);
        int endColor = H_2506_c.J_1907_R(startColor, 0.5f);
        U_2871_b out = new U_2871_b("");
        int denom = Math.max(1, text.length() - 1);
        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            U_2871_b letter = new U_2871_b(String.valueOf(c));
            float progress = (float)i / (float)denom;
            int gradientColor = H_2506_c.n_1700_B(endColor, startColor, progress);
            letter.n_1700_B(Z_1567_W.n_1700_B.n_1700_B(TextColor.n_1700_B(gradientColor & 0xFFFFFF)).J_1907_R(D_4024_W.multiplayerClientSuggestionProvider));
            out.n_1700_B(letter);
        }
        return out;
    }

    public static void n_1700_B(Object message, Object ... objects) {
        if (v_1900_v.c_3005_b.Y_259_p == null) {
            return;
        }
        if (message == null) {
            v_1900_v.n_1700_B("Object is null", new Object[0]);
            return;
        }
        U_2871_b finalText = new U_2871_b("");
        finalText.n_1700_B(v_1900_v.n_1700_B("Pouch Beta "));
        U_2871_b arrow = new U_2871_b("\u21e8");
        arrow.n_1700_B(Z_1567_W.n_1700_B.n_1700_B(TextColor.n_1700_B(0x888888)));
        finalText.n_1700_B(arrow);
        finalText.n_1700_B(new U_2871_b(" "));
        if (message instanceof x_282_a) {
            finalText.n_1700_B((x_282_a)message);
        } else {
            String msg = String.format(message.toString(), objects).replace('&', '\u00a7');
            String cleanMsg = msg.replaceAll("\u00a7[0-9a-fk-or]", "");
            U_2871_b mainText = new U_2871_b(cleanMsg);
            mainText.n_1700_B(Z_1567_W.n_1700_B.n_1700_B(TextColor.n_1700_B(0xFFFFFF)));
            finalText.n_1700_B(mainText);
        }
        v_1900_v.c_3005_b.M_588_G.R_4764_Y().n_1700_B(finalText);
    }

    public static void J_1907_R(Object message, Object ... objects) {
        if (v_1900_v.c_3005_b.Y_259_p == null) {
            return;
        }
        if (message == null) {
            v_1900_v.J_1907_R("Object is null", new Object[0]);
            return;
        }
        U_2871_b finalText = new U_2871_b("");
        finalText.n_1700_B(v_1900_v.n_1700_B("irc "));
        U_2871_b arrow = new U_2871_b("\u21e8");
        arrow.n_1700_B(Z_1567_W.n_1700_B.n_1700_B(TextColor.n_1700_B(0x888888)));
        finalText.n_1700_B(arrow);
        finalText.n_1700_B(new U_2871_b(" "));
        if (message instanceof x_282_a) {
            finalText.n_1700_B((x_282_a)message);
        } else {
            String msg = String.format(message.toString(), objects).replace('&', '\u00a7');
            String cleanMsg = msg.replaceAll("\u00a7[0-9a-fk-or]", "");
            U_2871_b mainText = new U_2871_b(cleanMsg);
            mainText.n_1700_B(Z_1567_W.n_1700_B.n_1700_B(TextColor.n_1700_B(0xFFFFFF)));
            finalText.n_1700_B(mainText);
        }
        v_1900_v.c_3005_b.M_588_G.R_4764_Y().n_1700_B(finalText);
    }
}




/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.time.Duration;
import java.util.Arrays;
import lightning.product.I_1084_e;
import lightning.product.U_2871_b;
import lightning.product.Y_408_h;
import lightning.product.j_3341_s;
import lightning.product.RepeatedNarrator;

public class NarrationHelper {
    private static final RepeatedNarrator n_1700_B = new RepeatedNarrator(Duration.ofSeconds(5L));

    public static void n_1700_B(String p_239550_0_) {
        I_1084_e narratorchatlistener = I_1084_e.J_1907_R;
        narratorchatlistener.J_1907_R();
        narratorchatlistener.n_1700_B(Y_408_h.J_1907_R, new U_2871_b(NarrationHelper.R_4764_Y(p_239550_0_)), j_3341_s.J_1907_R);
    }

    private static String R_4764_Y(String p_239554_0_) {
        return p_239554_0_.replace("\\n", System.lineSeparator());
    }

    public static void n_1700_B(String ... p_239551_0_) {
        NarrationHelper.n_1700_B(Arrays.asList(p_239551_0_));
    }

    public static void n_1700_B(Iterable<String> p_239549_0_) {
        NarrationHelper.n_1700_B(NarrationHelper.J_1907_R(p_239549_0_));
    }

    public static String J_1907_R(Iterable<String> p_239552_0_) {
        return String.join((CharSequence)System.lineSeparator(), p_239552_0_);
    }

    public static void J_1907_R(String p_239553_0_) {
        n_1700_B.n_1700_B(NarrationHelper.R_4764_Y(p_239553_0_));
    }
}



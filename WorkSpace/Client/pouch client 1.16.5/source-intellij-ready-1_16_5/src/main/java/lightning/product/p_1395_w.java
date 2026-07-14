/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lightning.product.D_4024_W;
import lightning.product.FormattedText;
import lightning.product.Y_4083_F;
import lightning.product.Z_1567_W;
import lightning.product.MinecraftClient;
import lightning.product.FormattedCharSequence;
import lightning.product.l_4033_W;
import lightning.product.ComponentCollector;

public class p_1395_w {
    private static final FormattedCharSequence n_1700_B = FormattedCharSequence.n_1700_B(32, Z_1567_W.n_1700_B);

    private static String n_1700_B(String p_238504_0_) {
        return MinecraftClient.A_4115_X().P_4830_p.d_2461_k ? p_238504_0_ : D_4024_W.n_1700_B(p_238504_0_);
    }

    public static List<FormattedCharSequence> n_1700_B(FormattedText p_238505_0_, int p_238505_1_, Y_4083_F p_238505_2_) {
        ComponentCollector textpropertiesmanager = new ComponentCollector();
        p_238505_0_.n_1700_B((p_238503_1_, p_238503_2_) -> {
            textpropertiesmanager.n_1700_B(FormattedText.n_1700_B(p_1395_w.n_1700_B(p_238503_2_), p_238503_1_));
            return Optional.empty();
        }, Z_1567_W.n_1700_B);
        ArrayList list = Lists.newArrayList();
        p_238505_2_.J_1907_R().n_1700_B(textpropertiesmanager.J_1907_R(), p_238505_1_, Z_1567_W.n_1700_B, (p_243256_1_, p_243256_2_) -> {
            FormattedCharSequence ireorderingprocessor = l_4033_W.R_4764_Y().n_1700_B((FormattedText)p_243256_1_);
            list.add(p_243256_2_ != false ? FormattedCharSequence.n_1700_B(n_1700_B, ireorderingprocessor) : ireorderingprocessor);
        });
        return list.isEmpty() ? Lists.newArrayList((Object[])new FormattedCharSequence[]{FormattedCharSequence.n_1700_B}) : list;
    }
}




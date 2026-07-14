/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.OptionsList;
import lightning.product.V_2511_L;
import lightning.product.V_4423_d;
import lightning.product.FormattedCharSequence;
import lightning.product.k_2603_m;
import lightning.product.q_2454_w;
import lightning.product.x_282_a;

public class OptionsSubScreen
extends k_2603_m {
    protected final k_2603_m R_4764_Y;
    protected final V_4423_d G_564_y;

    public OptionsSubScreen(k_2603_m previousScreen, V_4423_d gameSettingsObj, x_282_a textComponent) {
        super(textComponent);
        this.R_4764_Y = previousScreen;
        this.G_564_y = gameSettingsObj;
    }

    @Override
    public void onClose() {
        this.minecraft.P_4830_p.J_1907_R();
    }

    @Override
    public void closeScreen() {
        this.minecraft.n_1700_B(this.R_4764_Y);
    }

    @Nullable
    public static List<FormattedCharSequence> n_1700_B(OptionsList p_243293_0_, int p_243293_1_, int p_243293_2_) {
        Optional<V_2511_L> optional = p_243293_0_.R_4764_Y(p_243293_1_, p_243293_2_);
        if (optional.isPresent() && optional.get() instanceof q_2454_w) {
            Optional<List<FormattedCharSequence>> optional1 = ((q_2454_w)((Object)optional.get())).n_1700_B();
            return optional1.orElse(null);
        }
        return null;
    }
}



/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.B_3241_B;
import lightning.product.I_4764_L;
import lightning.product.TimerCallback;
import lightning.product.U_2912_j;
import lightning.product.g_2336_b;
import net.minecraft.server.G_564_y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class O_1568_Z<C> {
    private static final Logger J_1907_R = LogManager.getLogger();
    public static final O_1568_Z<G_564_y> n_1700_B = new O_1568_Z<G_564_y>().n_1700_B(new B_3241_B.n_1700_B()).n_1700_B(new I_4764_L.n_1700_B());
    private final Map<g_2336_b, TimerCallback.n_1700_B<C, ?>> R_4764_Y = Maps.newHashMap();
    private final Map<Class<?>, TimerCallback.n_1700_B<C, ?>> G_564_y = Maps.newHashMap();

    public O_1568_Z<C> n_1700_B(TimerCallback.n_1700_B<C, ?> p_216340_1_) {
        this.R_4764_Y.put(p_216340_1_.n_1700_B(), p_216340_1_);
        this.G_564_y.put(p_216340_1_.J_1907_R(), p_216340_1_);
        return this;
    }

    private <T extends TimerCallback<C>> TimerCallback.n_1700_B<C, T> n_1700_B(Class<?> p_216338_1_) {
        return this.G_564_y.get(p_216338_1_);
    }

    public <T extends TimerCallback<C>> U_2912_j n_1700_B(T p_216339_1_) {
        TimerCallback.n_1700_B<T, T> serializer = this.n_1700_B(p_216339_1_.getClass());
        U_2912_j compoundnbt = new U_2912_j();
        serializer.n_1700_B(compoundnbt, p_216339_1_);
        compoundnbt.n_1700_B("Type", serializer.n_1700_B().toString());
        return compoundnbt;
    }

    @Nullable
    public TimerCallback<C> n_1700_B(U_2912_j p_216341_1_) {
        g_2336_b resourcelocation = g_2336_b.J_1907_R(p_216341_1_.M_588_G("Type"));
        TimerCallback.n_1700_B<C, ?> serializer = this.R_4764_Y.get(resourcelocation);
        if (serializer == null) {
            J_1907_R.error("Failed to deserialize timer callback: " + String.valueOf(p_216341_1_));
            return null;
        }
        try {
            return serializer.n_1700_B(p_216341_1_);
        }
        catch (Exception exception) {
            J_1907_R.error("Failed to deserialize timer callback: " + String.valueOf(p_216341_1_), (Throwable)exception);
            return null;
        }
    }
}



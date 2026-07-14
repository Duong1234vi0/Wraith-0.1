/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import lightning.product.b_2585_i;
import lightning.product.RecipeBookSettings;
import lightning.product.g_2336_b;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundRecipePacket
implements Packet<ClientGamePacketListener> {
    private n_1700_B n_1700_B;
    private List<g_2336_b> J_1907_R;
    private List<g_2336_b> R_4764_Y;
    private RecipeBookSettings G_564_y;

    public ClientboundRecipePacket() {
    }

    public ClientboundRecipePacket(n_1700_B p_i242083_1_, Collection<g_2336_b> p_i242083_2_, Collection<g_2336_b> p_i242083_3_, RecipeBookSettings p_i242083_4_) {
        this.n_1700_B = p_i242083_1_;
        this.J_1907_R = ImmutableList.copyOf(p_i242083_2_);
        this.R_4764_Y = ImmutableList.copyOf(p_i242083_3_);
        this.G_564_y = p_i242083_4_;
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.n_1700_B(n_1700_B.class);
        this.G_564_y = RecipeBookSettings.n_1700_B(buf);
        int i = buf.u_1723_Y();
        this.J_1907_R = Lists.newArrayList();
        for (int j = 0; j < i; ++j) {
            this.J_1907_R.add(buf.P_4830_p());
        }
        if (this.n_1700_B == lightning.product.ClientboundRecipePacket$n_1700_B.n_1700_B) {
            i = buf.u_1723_Y();
            this.R_4764_Y = Lists.newArrayList();
            for (int k = 0; k < i; ++k) {
                this.R_4764_Y.add(buf.P_4830_p());
            }
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.n_1700_B(this.n_1700_B);
        this.G_564_y.J_1907_R(buf);
        buf.G_564_y(this.J_1907_R.size());
        for (g_2336_b resourcelocation : this.J_1907_R) {
            buf.n_1700_B(resourcelocation);
        }
        if (this.n_1700_B == lightning.product.ClientboundRecipePacket$n_1700_B.n_1700_B) {
            buf.G_564_y(this.R_4764_Y.size());
            for (g_2336_b resourcelocation1 : this.R_4764_Y) {
                buf.n_1700_B(resourcelocation1);
            }
        }
    }

    public List<g_2336_b> J_1907_R() {
        return this.J_1907_R;
    }

    public List<g_2336_b> R_4764_Y() {
        return this.R_4764_Y;
    }

    public RecipeBookSettings G_564_y() {
        return this.G_564_y;
    }

    public n_1700_B P_1922_E() {
        return this.n_1700_B;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.ClientboundRecipePacket$n_1700_B.n_1700_B();
        }
    }
}



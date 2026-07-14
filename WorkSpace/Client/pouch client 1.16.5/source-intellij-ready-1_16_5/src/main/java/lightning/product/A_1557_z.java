/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import lightning.product.V_3137_a;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import lightning.product.Recipe;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class A_1557_z
implements Packet<ClientGamePacketListener> {
    private List<Recipe<?>> n_1700_B;

    public A_1557_z() {
    }

    public A_1557_z(Collection<Recipe<?>> p_i48176_1_) {
        this.n_1700_B = Lists.newArrayList(p_i48176_1_);
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = Lists.newArrayList();
        int i = buf.u_1723_Y();
        for (int j = 0; j < i; ++j) {
            this.n_1700_B.add(A_1557_z.R_4764_Y(buf));
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B.size());
        for (Recipe<?> irecipe : this.n_1700_B) {
            A_1557_z.n_1700_B(irecipe, buf);
        }
    }

    public List<Recipe<?>> J_1907_R() {
        return this.n_1700_B;
    }

    public static Recipe<?> R_4764_Y(b_2585_i p_218772_0_) {
        g_2336_b resourcelocation = p_218772_0_.P_4830_p();
        g_2336_b resourcelocation1 = p_218772_0_.P_4830_p();
        return V_3137_a.s_2632_s.J_1907_R(resourcelocation).orElseThrow(() -> new IllegalArgumentException("Unknown recipe serializer " + String.valueOf(resourcelocation))).J_1907_R(resourcelocation1, p_218772_0_);
    }

    public static <T extends Recipe<?>> void n_1700_B(T p_218771_0_, b_2585_i p_218771_1_) {
        p_218771_1_.n_1700_B(V_3137_a.s_2632_s.J_1907_R(p_218771_0_.E_()));
        p_218771_1_.n_1700_B(p_218771_0_.u_1723_Y());
        p_218771_0_.E_().n_1700_B(p_218771_1_, p_218771_0_);
    }
}



/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lightning.product.A_4388_s;
import lightning.product.Attribute;
import lightning.product.U_1880_G;
import lightning.product.V_3137_a;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundUpdateAttributesPacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private final List<n_1700_B> J_1907_R = Lists.newArrayList();

    public ClientboundUpdateAttributesPacket() {
    }

    public ClientboundUpdateAttributesPacket(int entityIdIn, Collection<A_4388_s> instances) {
        this.n_1700_B = entityIdIn;
        for (A_4388_s modifiableattributeinstance : instances) {
            this.J_1907_R.add(new n_1700_B(this, modifiableattributeinstance.n_1700_B(), modifiableattributeinstance.J_1907_R(), modifiableattributeinstance.R_4764_Y()));
        }
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        this.n_1700_B = buf.u_1723_Y();
        int i = buf.readInt();
        for (int j = 0; j < i; ++j) {
            g_2336_b resourcelocation = buf.P_4830_p();
            Attribute attribute = V_3137_a.l_1233_K.n_1700_B(resourcelocation);
            double d0 = buf.readDouble();
            ArrayList list = Lists.newArrayList();
            int k = buf.u_1723_Y();
            for (int l = 0; l < k; ++l) {
                UUID uuid = buf.w_1484_f();
                list.add(new U_1880_G(uuid, "Unknown synced attribute modifier", buf.readDouble(), U_1880_G.n_1700_B.n_1700_B(buf.readByte())));
            }
            this.J_1907_R.add(new n_1700_B(this, attribute, d0, list));
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        buf.writeInt(this.J_1907_R.size());
        for (n_1700_B sentitypropertiespacket$snapshot : this.J_1907_R) {
            buf.n_1700_B(V_3137_a.l_1233_K.J_1907_R(sentitypropertiespacket$snapshot.n_1700_B()));
            buf.writeDouble(sentitypropertiespacket$snapshot.J_1907_R());
            buf.G_564_y(sentitypropertiespacket$snapshot.R_4764_Y().size());
            for (U_1880_G attributemodifier : sentitypropertiespacket$snapshot.R_4764_Y()) {
                buf.n_1700_B(attributemodifier.n_1700_B());
                buf.writeDouble(attributemodifier.G_564_y());
                buf.writeByte(attributemodifier.R_4764_Y().n_1700_B());
            }
        }
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public List<n_1700_B> R_4764_Y() {
        return this.J_1907_R;
    }

    public class n_1700_B {
        private final Attribute n_1700_B;
        private final double J_1907_R;
        private final Collection<U_1880_G> R_4764_Y;

        public n_1700_B(ClientboundUpdateAttributesPacket this$0, Attribute p_i232582_2_, double p_i232582_3_, Collection<U_1880_G> p_i232582_5_) {
            this.n_1700_B = p_i232582_2_;
            this.J_1907_R = p_i232582_3_;
            this.R_4764_Y = p_i232582_5_;
        }

        public Attribute n_1700_B() {
            return this.n_1700_B;
        }

        public double J_1907_R() {
            return this.J_1907_R;
        }

        public Collection<U_1880_G> R_4764_Y() {
            return this.R_4764_Y;
        }
    }
}



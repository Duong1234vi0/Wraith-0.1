/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import java.io.IOException;
import java.util.List;
import lightning.product.Z_1993_T;
import lightning.product.b_2585_i;
import lightning.product.e_1174_E;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;

public class ClientboundSetEquipmentPacket
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private final List<Pair<e_1174_E, Z_1993_T>> J_1907_R;

    public ClientboundSetEquipmentPacket() {
        this.J_1907_R = Lists.newArrayList();
    }

    public ClientboundSetEquipmentPacket(int p_i241270_1_, List<Pair<e_1174_E, Z_1993_T>> p_i241270_2_) {
        this.n_1700_B = p_i241270_1_;
        this.J_1907_R = p_i241270_2_;
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        byte i;
        this.n_1700_B = buf.u_1723_Y();
        e_1174_E[] aequipmentslottype = e_1174_E.values();
        do {
            i = buf.readByte();
            e_1174_E equipmentslottype = aequipmentslottype[i & 0x7F];
            Z_1993_T itemstack = buf.u_2550_I();
            this.J_1907_R.add((Pair<e_1174_E, Z_1993_T>)Pair.of((Object)((Object)equipmentslottype), (Object)itemstack));
        } while ((i & 0xFFFFFF80) != 0);
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.G_564_y(this.n_1700_B);
        int i = this.J_1907_R.size();
        for (int j = 0; j < i; ++j) {
            Pair<e_1174_E, Z_1993_T> pair = this.J_1907_R.get(j);
            e_1174_E equipmentslottype = (e_1174_E)((Object)pair.getFirst());
            boolean flag = j != i - 1;
            int k = equipmentslottype.ordinal();
            buf.writeByte(flag ? k | 0xFFFFFF80 : k);
            buf.n_1700_B((Z_1993_T)pair.getSecond());
        }
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public int J_1907_R() {
        return this.n_1700_B;
    }

    public List<Pair<e_1174_E, Z_1993_T>> R_4764_Y() {
        return this.J_1907_R;
    }
}



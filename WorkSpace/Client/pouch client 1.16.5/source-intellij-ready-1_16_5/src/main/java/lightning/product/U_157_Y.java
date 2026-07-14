/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.LongArrayTag;
import lightning.product.H_1748_a;
import lightning.product.P_3550_Z;
import lightning.product.U_2912_j;
import lightning.product.Y_1387_d;
import lightning.product.b_2585_i;
import lightning.product.c_1108_W;
import lightning.product.c_1514_x;
import lightning.product.i_2154_H;
import lightning.product.ClientGamePacketListener;
import lightning.product.Packet;
import lightning.product.z_2963_s;
import net.optifine.ChunkDataOF;
import net.optifine.ChunkOF;

public class U_157_Y
implements Packet<ClientGamePacketListener> {
    private int n_1700_B;
    private int J_1907_R;
    private int R_4764_Y;
    private U_2912_j G_564_y;
    @Nullable
    private int[] P_1922_E;
    private byte[] u_1723_Y;
    private List<U_2912_j> v_4262_N;
    private boolean w_1484_f;
    private Map<String, Object> t_148_a;

    public U_157_Y() {
    }

    public U_157_Y(H_1748_a p_i242081_1_, int p_i242081_2_) {
        Y_1387_d chunkpos = p_i242081_1_.getPos();
        this.n_1700_B = chunkpos.J_1907_R;
        this.J_1907_R = chunkpos.R_4764_Y;
        this.w_1484_f = p_i242081_2_ == 65535;
        this.G_564_y = new U_2912_j();
        for (Map.Entry<z_2963_s.n_1700_B, z_2963_s> entry : p_i242081_1_.getHeightmaps()) {
            if (!entry.getKey().R_4764_Y()) continue;
            this.G_564_y.n_1700_B(entry.getKey().J_1907_R(), new LongArrayTag(entry.getValue().n_1700_B()));
        }
        if (this.w_1484_f) {
            this.P_1922_E = p_i242081_1_.getBiomes().n_1700_B();
        }
        this.u_1723_Y = new byte[this.n_1700_B(p_i242081_1_, p_i242081_2_)];
        this.R_4764_Y = this.n_1700_B(new b_2585_i(this.s_956_w()), p_i242081_1_, p_i242081_2_);
        this.v_4262_N = Lists.newArrayList();
        for (Map.Entry<Object, Object> entry : p_i242081_1_.getTileEntityMap().entrySet()) {
            c_1514_x blockpos = (c_1514_x)entry.getKey();
            i_2154_H tileentity = (i_2154_H)entry.getValue();
            int i = blockpos.getY() >> 4;
            if (!this.u_1723_Y() && (p_i242081_2_ & 1 << i) == 0) continue;
            U_2912_j compoundnbt = tileentity.H_();
            this.v_4262_N.add(compoundnbt);
        }
        this.t_148_a = new HashMap<String, Object>();
        ChunkDataOF chunkdataof = ChunkOF.makeChunkDataOF(p_i242081_1_);
        this.t_148_a.put("ChunkDataOF", chunkdataof);
    }

    @Override
    public void n_1700_B(b_2585_i buf) throws IOException {
        int i;
        this.n_1700_B = buf.readInt();
        this.J_1907_R = buf.readInt();
        this.w_1484_f = buf.readBoolean();
        this.R_4764_Y = buf.u_1723_Y();
        this.G_564_y = buf.t_148_a();
        if (this.w_1484_f) {
            this.P_1922_E = buf.R_4764_Y(c_1108_W.n_1700_B);
        }
        if ((i = buf.u_1723_Y()) > 0x200000) {
            throw new RuntimeException("Chunk Packet trying to allocate too much memory on read.");
        }
        this.u_1723_Y = new byte[i];
        buf.readBytes(this.u_1723_Y);
        int j = buf.u_1723_Y();
        this.v_4262_N = Lists.newArrayList();
        for (int k = 0; k < j; ++k) {
            this.v_4262_N.add(buf.t_148_a());
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) throws IOException {
        buf.writeInt(this.n_1700_B);
        buf.writeInt(this.J_1907_R);
        buf.writeBoolean(this.w_1484_f);
        buf.G_564_y(this.R_4764_Y);
        buf.n_1700_B(this.G_564_y);
        if (this.P_1922_E != null) {
            buf.n_1700_B(this.P_1922_E);
        }
        buf.G_564_y(this.u_1723_Y.length);
        buf.writeBytes(this.u_1723_Y);
        buf.G_564_y(this.v_4262_N.size());
        for (U_2912_j compoundnbt : this.v_4262_N) {
            buf.n_1700_B(compoundnbt);
        }
    }

    @Override
    public void n_1700_B(ClientGamePacketListener handler) {
        handler.n_1700_B(this);
    }

    public b_2585_i J_1907_R() {
        return new b_2585_i(Unpooled.wrappedBuffer((byte[])this.u_1723_Y), this.t_148_a);
    }

    private ByteBuf s_956_w() {
        ByteBuf bytebuf = Unpooled.wrappedBuffer((byte[])this.u_1723_Y);
        bytebuf.writerIndex(0);
        return bytebuf;
    }

    public int n_1700_B(b_2585_i buf, H_1748_a chunkIn, int writeSkylight) {
        int i = 0;
        P_3550_Z[] achunksection = chunkIn.getSections();
        int k = achunksection.length;
        for (int j = 0; j < k; ++j) {
            P_3550_Z chunksection = achunksection[j];
            if (chunksection == H_1748_a.EMPTY_SECTION || this.u_1723_Y() && chunksection.R_4764_Y() || (writeSkylight & 1 << j) == 0) continue;
            i |= 1 << j;
            chunksection.J_1907_R(buf);
        }
        return i;
    }

    protected int n_1700_B(H_1748_a chunkIn, int changedSectionsIn) {
        int i = 0;
        P_3550_Z[] achunksection = chunkIn.getSections();
        int k = achunksection.length;
        for (int j = 0; j < k; ++j) {
            P_3550_Z chunksection = achunksection[j];
            if (chunksection == H_1748_a.EMPTY_SECTION || this.u_1723_Y() && chunksection.R_4764_Y() || (changedSectionsIn & 1 << j) == 0) continue;
            i += chunksection.s_956_w();
        }
        return i;
    }

    public int R_4764_Y() {
        return this.n_1700_B;
    }

    public int G_564_y() {
        return this.J_1907_R;
    }

    public int P_1922_E() {
        return this.R_4764_Y;
    }

    public boolean u_1723_Y() {
        return this.w_1484_f;
    }

    public U_2912_j v_4262_N() {
        return this.G_564_y;
    }

    public List<U_2912_j> w_1484_f() {
        return this.v_4262_N;
    }

    @Nullable
    public int[] t_148_a() {
        return this.P_1922_E;
    }
}



/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.H_1748_a;
import lightning.product.K_4074_S;
import lightning.product.M_4466_T;
import lightning.product.T_2915_h;
import lightning.product.GlobalPalette;
import lightning.product.a_3742_W;
import lightning.product.b_2585_i;
import lightning.product.FluidState;
import lightning.product.n_3832_I;
import lightning.product.Palette;
import net.optifine.ChunkDataOF;
import net.optifine.ChunkSectionDataOF;

public class P_3550_Z {
    private static final Palette<K_4074_S> J_1907_R = new GlobalPalette<K_4074_S>(T_2915_h.t_4043_B, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider());
    private final int R_4764_Y;
    private short G_564_y;
    private short P_1922_E;
    private short u_1723_Y;
    private final M_4466_T<K_4074_S> v_4262_N;
    public static final ThreadLocal<ChunkDataOF> n_1700_B = new ThreadLocal();

    public P_3550_Z(int yBaseIn) {
        this(yBaseIn, 0, 0, 0);
    }

    public P_3550_Z(int yBaseIn, short blockRefCountIn, short blockTickRefCountIn, short fluidRefCountIn) {
        this.R_4764_Y = yBaseIn;
        this.G_564_y = blockRefCountIn;
        this.P_1922_E = blockTickRefCountIn;
        this.u_1723_Y = fluidRefCountIn;
        this.v_4262_N = new M_4466_T<K_4074_S>(J_1907_R, T_2915_h.t_4043_B, n_3832_I::R_4764_Y, n_3832_I::n_1700_B, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider());
    }

    public K_4074_S n_1700_B(int x, int y, int z) {
        return this.v_4262_N.n_1700_B(x, y, z);
    }

    public FluidState J_1907_R(int x, int y, int z) {
        return this.v_4262_N.n_1700_B(x, y, z).P_4830_p();
    }

    public void n_1700_B() {
        this.v_4262_N.n_1700_B();
    }

    public void J_1907_R() {
        this.v_4262_N.J_1907_R();
    }

    public K_4074_S n_1700_B(int x, int y, int z, K_4074_S blockStateIn) {
        return this.n_1700_B(x, y, z, blockStateIn, true);
    }

    public K_4074_S n_1700_B(int x, int y, int z, K_4074_S state, boolean useLocks) {
        K_4074_S blockstate = useLocks ? this.v_4262_N.n_1700_B(x, y, z, state) : this.v_4262_N.J_1907_R(x, y, z, state);
        FluidState fluidstate = blockstate.P_4830_p();
        FluidState fluidstate1 = state.P_4830_p();
        if (!blockstate.v_4262_N()) {
            this.G_564_y = (short)(this.G_564_y - 1);
            if (blockstate.h_1847_R()) {
                this.P_1922_E = (short)(this.P_1922_E - 1);
            }
        }
        if (!fluidstate.R_4764_Y()) {
            this.u_1723_Y = (short)(this.u_1723_Y - 1);
        }
        if (!state.v_4262_N()) {
            this.G_564_y = (short)(this.G_564_y + 1);
            if (state.h_1847_R()) {
                this.P_1922_E = (short)(this.P_1922_E + 1);
            }
        }
        if (!fluidstate1.R_4764_Y()) {
            this.u_1723_Y = (short)(this.u_1723_Y + 1);
        }
        return blockstate;
    }

    public boolean R_4764_Y() {
        return this.G_564_y == 0;
    }

    public static boolean n_1700_B(@Nullable P_3550_Z section) {
        return section == H_1748_a.EMPTY_SECTION || section.R_4764_Y();
    }

    public boolean G_564_y() {
        return this.P_1922_E() || this.u_1723_Y();
    }

    public boolean P_1922_E() {
        return this.P_1922_E > 0;
    }

    public boolean u_1723_Y() {
        return this.u_1723_Y > 0;
    }

    public int v_4262_N() {
        return this.R_4764_Y;
    }

    public void w_1484_f() {
        ChunkSectionDataOF chunksectiondataof;
        int i;
        ChunkSectionDataOF[] achunksectiondataof;
        ChunkDataOF chunkdataof = n_1700_B.get();
        if (chunkdataof != null && (achunksectiondataof = chunkdataof.getChunkSectionDatas()) != null && (i = this.R_4764_Y >> 4) >= 0 && i < achunksectiondataof.length && (chunksectiondataof = achunksectiondataof[i]) != null) {
            this.G_564_y = chunksectiondataof.getBlockRefCount();
            this.P_1922_E = chunksectiondataof.getTickRefCount();
            this.u_1723_Y = chunksectiondataof.getFluidRefCount();
            achunksectiondataof[i] = null;
            return;
        }
        this.G_564_y = 0;
        this.P_1922_E = 0;
        this.u_1723_Y = 0;
        this.v_4262_N.n_1700_B((T p_lambda$recalculateRefCounts$0_1_, int p_lambda$recalculateRefCounts$0_2_) -> {
            FluidState fluidstate = p_lambda$recalculateRefCounts$0_1_.P_4830_p();
            if (!p_lambda$recalculateRefCounts$0_1_.v_4262_N()) {
                this.G_564_y = (short)(this.G_564_y + p_lambda$recalculateRefCounts$0_2_);
                if (p_lambda$recalculateRefCounts$0_1_.h_1847_R()) {
                    this.P_1922_E = (short)(this.P_1922_E + p_lambda$recalculateRefCounts$0_2_);
                }
            }
            if (!fluidstate.R_4764_Y()) {
                this.G_564_y = (short)(this.G_564_y + p_lambda$recalculateRefCounts$0_2_);
                if (fluidstate.u_1723_Y()) {
                    this.u_1723_Y = (short)(this.u_1723_Y + p_lambda$recalculateRefCounts$0_2_);
                }
            }
        });
    }

    public M_4466_T<K_4074_S> t_148_a() {
        return this.v_4262_N;
    }

    public void n_1700_B(b_2585_i packetBufferIn) {
        this.G_564_y = packetBufferIn.readShort();
        this.v_4262_N.n_1700_B(packetBufferIn);
    }

    public void J_1907_R(b_2585_i packetBufferIn) {
        packetBufferIn.writeShort(this.G_564_y);
        this.v_4262_N.J_1907_R(packetBufferIn);
    }

    public int s_956_w() {
        return 2 + this.v_4262_N.R_4764_Y();
    }

    public boolean n_1700_B(Predicate<K_4074_S> predicate) {
        return this.v_4262_N.n_1700_B(predicate);
    }

    public short u_2550_I() {
        return this.G_564_y;
    }

    public short M_588_G() {
        return this.P_1922_E;
    }

    public short P_4830_p() {
        return this.u_1723_Y;
    }
}



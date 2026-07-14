/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.mutable.MutableInt
 */
package lightning.product;

import java.util.Arrays;
import javax.annotation.Nullable;
import lightning.product.B_1887_u;
import lightning.product.LayerLightEventListener;
import lightning.product.DataLayerStorageMap;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.K_4719_o;
import lightning.product.DataLayer;
import lightning.product.Y_1387_d;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1289_c;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.s_1395_c;
import lightning.product.LightChunkGetter;
import lightning.product.x_268_Y;
import org.apache.commons.lang3.mutable.MutableInt;

public abstract class i_4702_v<M extends DataLayerStorageMap<M>, S extends B_1887_u<M>>
extends c_1289_c
implements LayerLightEventListener {
    private static final b_257_Y[] P_1922_E = b_257_Y.values();
    protected final LightChunkGetter n_1700_B;
    protected final K_4719_o J_1907_R;
    protected final S R_4764_Y;
    private boolean u_1723_Y;
    protected final c_1514_x.n_1700_B G_564_y = new c_1514_x.n_1700_B();
    private final long[] v_4262_N = new long[2];
    private final BlockGetter[] w_1484_f = new BlockGetter[2];

    public i_4702_v(LightChunkGetter chunkLightProvider, K_4719_o lightTypeIn, S storageIn) {
        super(16, 256, 8192);
        this.n_1700_B = chunkLightProvider;
        this.J_1907_R = lightTypeIn;
        this.R_4764_Y = storageIn;
        this.P_1922_E();
    }

    @Override
    protected void u_1723_Y(long worldPos) {
        ((B_1887_u)this.R_4764_Y).P_1922_E();
        if (((B_1887_u)this.R_4764_Y).v_4262_N(SectionPos.P_1922_E(worldPos))) {
            super.u_1723_Y(worldPos);
        }
    }

    @Nullable
    private BlockGetter n_1700_B(int chunkX, int chunkZ) {
        long i = Y_1387_d.n_1700_B(chunkX, chunkZ);
        for (int j = 0; j < 2; ++j) {
            if (i != this.v_4262_N[j]) continue;
            return this.w_1484_f[j];
        }
        BlockGetter iblockreader = this.n_1700_B.G_564_y(chunkX, chunkZ);
        for (int k = 1; k > 0; --k) {
            this.v_4262_N[k] = this.v_4262_N[k - 1];
            this.w_1484_f[k] = this.w_1484_f[k - 1];
        }
        this.v_4262_N[0] = i;
        this.w_1484_f[0] = iblockreader;
        return iblockreader;
    }

    private void P_1922_E() {
        Arrays.fill(this.v_4262_N, Y_1387_d.n_1700_B);
        Arrays.fill(this.w_1484_f, null);
    }

    protected K_4074_S n_1700_B(long pos, @Nullable MutableInt opacityOut) {
        boolean flag;
        int j;
        if (pos == Long.MAX_VALUE) {
            if (opacityOut != null) {
                opacityOut.setValue(0);
            }
            return a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
        }
        int i = SectionPos.n_1700_B(c_1514_x.unpackX(pos));
        BlockGetter iblockreader = this.n_1700_B(i, j = SectionPos.n_1700_B(c_1514_x.unpackZ(pos)));
        if (iblockreader == null) {
            if (opacityOut != null) {
                opacityOut.setValue(16);
            }
            return a_3742_W.Z_875_P.multiplayerClientSuggestionProvider();
        }
        this.G_564_y.n_1700_B(pos);
        K_4074_S blockstate = iblockreader.getBlockState(this.G_564_y);
        boolean bl = flag = blockstate.M_588_G() && blockstate.P_1922_E();
        if (opacityOut != null) {
            opacityOut.setValue(blockstate.J_1907_R(this.n_1700_B.n_1700_B(), (c_1514_x)this.G_564_y));
        }
        return flag ? blockstate : a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
    }

    protected s_1395_c n_1700_B(K_4074_S blockStateIn, long worldPos, b_257_Y directionIn) {
        return blockStateIn.M_588_G() ? blockStateIn.n_1700_B(this.n_1700_B.n_1700_B(), (c_1514_x)this.G_564_y.n_1700_B(worldPos), directionIn) : x_268_Y.n_1700_B();
    }

    public static int n_1700_B(BlockGetter p_215613_0_, K_4074_S p_215613_1_, c_1514_x p_215613_2_, K_4074_S p_215613_3_, c_1514_x p_215613_4_, b_257_Y p_215613_5_, int p_215613_6_) {
        boolean flag1;
        boolean flag = p_215613_1_.M_588_G() && p_215613_1_.P_1922_E();
        boolean bl = flag1 = p_215613_3_.M_588_G() && p_215613_3_.P_1922_E();
        if (!flag && !flag1) {
            return p_215613_6_;
        }
        s_1395_c voxelshape = flag ? p_215613_1_.R_4764_Y(p_215613_0_, p_215613_2_) : x_268_Y.n_1700_B();
        s_1395_c voxelshape1 = flag1 ? p_215613_3_.R_4764_Y(p_215613_0_, p_215613_4_) : x_268_Y.n_1700_B();
        return x_268_Y.J_1907_R(voxelshape, voxelshape1, p_215613_5_) ? 16 : p_215613_6_;
    }

    @Override
    protected boolean n_1700_B(long pos) {
        return pos == Long.MAX_VALUE;
    }

    @Override
    protected int n_1700_B(long pos, long excludedSourcePos, int level) {
        return 0;
    }

    @Override
    protected int R_4764_Y(long sectionPosIn) {
        return sectionPosIn == Long.MAX_VALUE ? 0 : 15 - ((B_1887_u)this.R_4764_Y).t_148_a(sectionPosIn);
    }

    protected int n_1700_B(DataLayer array, long worldPos) {
        return 15 - array.n_1700_B(SectionPos.J_1907_R(c_1514_x.unpackX(worldPos)), SectionPos.J_1907_R(c_1514_x.unpackY(worldPos)), SectionPos.J_1907_R(c_1514_x.unpackZ(worldPos)));
    }

    @Override
    protected void n_1700_B(long sectionPosIn, int level) {
        ((B_1887_u)this.R_4764_Y).J_1907_R(sectionPosIn, Math.min(15, 15 - level));
    }

    @Override
    protected int J_1907_R(long startPos, long endPos, int startLevel) {
        return 0;
    }

    public boolean n_1700_B() {
        return this.J_1907_R() || ((c_1289_c)this.R_4764_Y).J_1907_R() || ((B_1887_u)this.R_4764_Y).n_1700_B();
    }

    public int n_1700_B(int toUpdateCount, boolean updateSkyLight, boolean updateBlockLight) {
        if (!this.u_1723_Y) {
            if (((c_1289_c)this.R_4764_Y).J_1907_R() && (toUpdateCount = ((c_1289_c)this.R_4764_Y).n_1700_B(toUpdateCount)) == 0) {
                return toUpdateCount;
            }
            ((B_1887_u)this.R_4764_Y).n_1700_B(this, updateSkyLight, updateBlockLight);
        }
        this.u_1723_Y = true;
        if (this.J_1907_R()) {
            toUpdateCount = this.n_1700_B(toUpdateCount);
            this.P_1922_E();
            if (toUpdateCount == 0) {
                return toUpdateCount;
            }
        }
        this.u_1723_Y = false;
        ((B_1887_u)this.R_4764_Y).u_1723_Y();
        return toUpdateCount;
    }

    protected void n_1700_B(long sectionPosIn, @Nullable DataLayer array, boolean p_215621_4_) {
        ((B_1887_u)this.R_4764_Y).n_1700_B(sectionPosIn, array, p_215621_4_);
    }

    @Override
    @Nullable
    public DataLayer n_1700_B(SectionPos p_215612_1_) {
        return ((B_1887_u)this.R_4764_Y).w_1484_f(p_215612_1_.P_4830_p());
    }

    @Override
    public int n_1700_B(c_1514_x worldPos) {
        return ((B_1887_u)this.R_4764_Y).G_564_y(worldPos.toLong());
    }

    public String J_1907_R(long sectionPosIn) {
        return "" + ((B_1887_u)this.R_4764_Y).R_4764_Y(sectionPosIn);
    }

    public void J_1907_R(c_1514_x worldPos) {
        long i = worldPos.toLong();
        this.u_1723_Y(i);
        for (b_257_Y direction : P_1922_E) {
            this.u_1723_Y(c_1514_x.offset(i, direction));
        }
    }

    public void n_1700_B(c_1514_x p_215623_1_, int p_215623_2_) {
    }

    @Override
    public void n_1700_B(SectionPos pos, boolean isEmpty) {
        ((B_1887_u)this.R_4764_Y).G_564_y(pos.P_4830_p(), isEmpty);
    }

    public void n_1700_B(Y_1387_d chunkPos, boolean p_215620_2_) {
        long i = SectionPos.u_1723_Y(SectionPos.J_1907_R(chunkPos.J_1907_R, 0, chunkPos.R_4764_Y));
        ((B_1887_u)this.R_4764_Y).J_1907_R(i, p_215620_2_);
    }

    public void J_1907_R(Y_1387_d pos, boolean retain) {
        long i = SectionPos.u_1723_Y(SectionPos.J_1907_R(pos.J_1907_R, 0, pos.R_4764_Y));
        ((B_1887_u)this.R_4764_Y).R_4764_Y(i, retain);
    }
}



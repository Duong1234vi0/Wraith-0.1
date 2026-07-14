/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.C_2693_g;
import lightning.product.LayerLightEventListener;
import lightning.product.K_4719_o;
import lightning.product.L_4831_e;
import lightning.product.DataLayer;
import lightning.product.LightEventListener;
import lightning.product.Y_1387_d;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.i_4702_v;
import lightning.product.LightChunkGetter;

public class R_1900_x
implements LightEventListener {
    @Nullable
    private final i_4702_v<?, ?> n_1700_B;
    @Nullable
    private final i_4702_v<?, ?> J_1907_R;

    public R_1900_x(LightChunkGetter provider, boolean hasBlockLight, boolean hasSkyLight) {
        this.n_1700_B = hasBlockLight ? new C_2693_g(provider) : null;
        this.J_1907_R = hasSkyLight ? new L_4831_e(provider) : null;
    }

    public void n_1700_B(c_1514_x blockPosIn) {
        if (this.n_1700_B != null) {
            this.n_1700_B.J_1907_R(blockPosIn);
        }
        if (this.J_1907_R != null) {
            this.J_1907_R.J_1907_R(blockPosIn);
        }
    }

    public void n_1700_B(c_1514_x blockPosIn, int p_215573_2_) {
        if (this.n_1700_B != null) {
            this.n_1700_B.n_1700_B(blockPosIn, p_215573_2_);
        }
    }

    public boolean n_1700_B() {
        if (this.J_1907_R != null && this.J_1907_R.n_1700_B()) {
            return true;
        }
        return this.n_1700_B != null && this.n_1700_B.n_1700_B();
    }

    public int n_1700_B(int toUpdateCount, boolean updateSkyLight, boolean updateBlockLight) {
        if (this.n_1700_B != null && this.J_1907_R != null) {
            int i = toUpdateCount / 2;
            int j = this.n_1700_B.n_1700_B(i, updateSkyLight, updateBlockLight);
            int k = toUpdateCount - i + j;
            int l = this.J_1907_R.n_1700_B(k, updateSkyLight, updateBlockLight);
            return j == 0 && l > 0 ? this.n_1700_B.n_1700_B(l, updateSkyLight, updateBlockLight) : l;
        }
        if (this.n_1700_B != null) {
            return this.n_1700_B.n_1700_B(toUpdateCount, updateSkyLight, updateBlockLight);
        }
        return this.J_1907_R != null ? this.J_1907_R.n_1700_B(toUpdateCount, updateSkyLight, updateBlockLight) : toUpdateCount;
    }

    @Override
    public void n_1700_B(SectionPos pos, boolean isEmpty) {
        if (this.n_1700_B != null) {
            this.n_1700_B.n_1700_B(pos, isEmpty);
        }
        if (this.J_1907_R != null) {
            this.J_1907_R.n_1700_B(pos, isEmpty);
        }
    }

    public void n_1700_B(Y_1387_d p_215571_1_, boolean p_215571_2_) {
        if (this.n_1700_B != null) {
            this.n_1700_B.n_1700_B(p_215571_1_, p_215571_2_);
        }
        if (this.J_1907_R != null) {
            this.J_1907_R.n_1700_B(p_215571_1_, p_215571_2_);
        }
    }

    public LayerLightEventListener n_1700_B(K_4719_o type) {
        if (type == K_4719_o.J_1907_R) {
            return this.n_1700_B == null ? LayerLightEventListener.n_1700_B.n_1700_B : this.n_1700_B;
        }
        return this.J_1907_R == null ? LayerLightEventListener.n_1700_B.n_1700_B : this.J_1907_R;
    }

    public String n_1700_B(K_4719_o p_215572_1_, SectionPos p_215572_2_) {
        if (p_215572_1_ == K_4719_o.J_1907_R) {
            if (this.n_1700_B != null) {
                return this.n_1700_B.J_1907_R(p_215572_2_.P_4830_p());
            }
        } else if (this.J_1907_R != null) {
            return this.J_1907_R.J_1907_R(p_215572_2_.P_4830_p());
        }
        return "n/a";
    }

    public void n_1700_B(K_4719_o type, SectionPos pos, @Nullable DataLayer array, boolean p_215574_4_) {
        if (type == K_4719_o.J_1907_R) {
            if (this.n_1700_B != null) {
                this.n_1700_B.n_1700_B(pos.P_4830_p(), array, p_215574_4_);
            }
        } else if (this.J_1907_R != null) {
            this.J_1907_R.n_1700_B(pos.P_4830_p(), array, p_215574_4_);
        }
    }

    public void J_1907_R(Y_1387_d pos, boolean retain) {
        if (this.n_1700_B != null) {
            this.n_1700_B.J_1907_R(pos, retain);
        }
        if (this.J_1907_R != null) {
            this.J_1907_R.J_1907_R(pos, retain);
        }
    }

    public int J_1907_R(c_1514_x blockPosIn, int amount) {
        int i = this.J_1907_R == null ? 0 : this.J_1907_R.n_1700_B(blockPosIn) - amount;
        int j = this.n_1700_B == null ? 0 : this.n_1700_B.n_1700_B(blockPosIn);
        return Math.max(j, i);
    }
}



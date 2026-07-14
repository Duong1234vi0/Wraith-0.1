/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.d_2427_y;
import lightning.product.x_607_J;
import lightning.product.z_4547_I;
import lombok.Generated;

public class Z_2812_M
extends d_2427_y
implements x_607_J {
    private z_4547_I.n_1700_B n_1700_B;

    @Generated
    public z_4547_I.n_1700_B J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public void n_1700_B(z_4547_I.n_1700_B chunkRender) {
        this.n_1700_B = chunkRender;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Z_2812_M)) {
            return false;
        }
        Z_2812_M other = (Z_2812_M)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        z_4547_I.n_1700_B this$chunkRender = this.J_1907_R();
        z_4547_I.n_1700_B other$chunkRender = other.J_1907_R();
        return !(this$chunkRender == null ? other$chunkRender != null : !this$chunkRender.equals(other$chunkRender));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof Z_2812_M;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        z_4547_I.n_1700_B $chunkRender = this.J_1907_R();
        result = result * 59 + ($chunkRender == null ? 43 : $chunkRender.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventRenderChunk(chunkRender=" + String.valueOf(this.J_1907_R()) + ")";
    }

    @Generated
    public Z_2812_M(z_4547_I.n_1700_B chunkRender) {
        this.n_1700_B = chunkRender;
    }
}


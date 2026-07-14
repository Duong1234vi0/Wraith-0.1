/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.BlockHitResult;
import lightning.product.V_772_m;
import lightning.product.d_2427_y;
import lightning.product.k_4690_i;
import lightning.product.x_1688_C;
import lightning.product.x_607_J;
import lombok.Generated;

public class o_1800_r
extends d_2427_y
implements x_607_J {
    private final V_772_m n_1700_B;
    private final k_4690_i J_1907_R;
    private final x_1688_C R_4764_Y;
    private final BlockHitResult G_564_y;

    @Generated
    public V_772_m J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public k_4690_i R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public x_1688_C G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public BlockHitResult P_1922_E() {
        return this.G_564_y;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof o_1800_r)) {
            return false;
        }
        o_1800_r other = (o_1800_r)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        V_772_m this$player = this.J_1907_R();
        V_772_m other$player = other.J_1907_R();
        if (this$player == null ? other$player != null : !((Object)this$player).equals(other$player)) {
            return false;
        }
        k_4690_i this$world = this.R_4764_Y();
        k_4690_i other$world = other.R_4764_Y();
        if (this$world == null ? other$world != null : !this$world.equals(other$world)) {
            return false;
        }
        x_1688_C this$hand = this.G_564_y();
        x_1688_C other$hand = other.G_564_y();
        if (this$hand == null ? other$hand != null : !((Object)((Object)this$hand)).equals((Object)other$hand)) {
            return false;
        }
        BlockHitResult this$result = this.P_1922_E();
        BlockHitResult other$result = other.P_1922_E();
        return !(this$result == null ? other$result != null : !this$result.equals(other$result));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof o_1800_r;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        V_772_m $player = this.J_1907_R();
        result = result * 59 + ($player == null ? 43 : ((Object)$player).hashCode());
        k_4690_i $world = this.R_4764_Y();
        result = result * 59 + ($world == null ? 43 : $world.hashCode());
        x_1688_C $hand = this.G_564_y();
        result = result * 59 + ($hand == null ? 43 : ((Object)((Object)$hand)).hashCode());
        BlockHitResult $result = this.P_1922_E();
        result = result * 59 + ($result == null ? 43 : $result.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventClickBlockRight(player=" + String.valueOf(this.J_1907_R()) + ", world=" + String.valueOf(this.R_4764_Y()) + ", hand=" + String.valueOf((Object)this.G_564_y()) + ", result=" + String.valueOf(this.P_1922_E()) + ")";
    }

    @Generated
    public o_1800_r(V_772_m player, k_4690_i world, x_1688_C hand, BlockHitResult result) {
        this.n_1700_B = player;
        this.J_1907_R = world;
        this.R_4764_Y = hand;
        this.G_564_y = result;
    }
}



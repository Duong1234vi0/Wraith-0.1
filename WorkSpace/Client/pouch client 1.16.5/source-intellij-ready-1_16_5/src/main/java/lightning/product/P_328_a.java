/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.EntityModel;
import lightning.product.d_2427_y;
import lightning.product.g_221_o;
import lightning.product.r_4811_B;
import lightning.product.x_607_J;
import lombok.Generated;

public class P_328_a
extends d_2427_y
implements x_607_J {
    private r_4811_B n_1700_B;
    private g_221_o J_1907_R;
    private EntityModel<?> R_4764_Y;

    @Generated
    public r_4811_B J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public g_221_o R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public EntityModel<?> G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public void n_1700_B(r_4811_B entity) {
        this.n_1700_B = entity;
    }

    @Generated
    public void n_1700_B(g_221_o matrix) {
        this.J_1907_R = matrix;
    }

    @Generated
    public void n_1700_B(EntityModel<?> model) {
        this.R_4764_Y = model;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof P_328_a)) {
            return false;
        }
        P_328_a other = (P_328_a)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        r_4811_B this$entity = this.J_1907_R();
        r_4811_B other$entity = other.J_1907_R();
        if (this$entity == null ? other$entity != null : !((Object)this$entity).equals(other$entity)) {
            return false;
        }
        g_221_o this$matrix = this.R_4764_Y();
        g_221_o other$matrix = other.R_4764_Y();
        if (this$matrix == null ? other$matrix != null : !this$matrix.equals(other$matrix)) {
            return false;
        }
        EntityModel<?> this$model = this.G_564_y();
        EntityModel<?> other$model = other.G_564_y();
        return !(this$model == null ? other$model != null : !this$model.equals(other$model));
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof P_328_a;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        r_4811_B $entity = this.J_1907_R();
        result = result * 59 + ($entity == null ? 43 : ((Object)$entity).hashCode());
        g_221_o $matrix = this.R_4764_Y();
        result = result * 59 + ($matrix == null ? 43 : $matrix.hashCode());
        EntityModel<?> $model = this.G_564_y();
        result = result * 59 + ($model == null ? 43 : $model.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "EventLayerHead(entity=" + String.valueOf(this.J_1907_R()) + ", matrix=" + String.valueOf(this.R_4764_Y()) + ", model=" + String.valueOf(this.G_564_y()) + ")";
    }

    @Generated
    public P_328_a(r_4811_B entity, g_221_o matrix, EntityModel<?> model) {
        this.n_1700_B = entity;
        this.J_1907_R = matrix;
        this.R_4764_Y = model;
    }
}



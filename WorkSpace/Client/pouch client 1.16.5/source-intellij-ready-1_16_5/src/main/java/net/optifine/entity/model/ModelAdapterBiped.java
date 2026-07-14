/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.e_4189_z;
import lightning.product.n_1658_l;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import net.optifine.entity.model.ModelAdapter;

public abstract class ModelAdapterBiped
extends ModelAdapter {
    public ModelAdapterBiped(t_5_h type, String name, float shadowSize) {
        super(type, name, shadowSize);
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof n_1658_l)) {
            return null;
        }
        n_1658_l bipedmodel = (n_1658_l)model;
        if (modelPart.equals("head")) {
            return bipedmodel.n_1700_B;
        }
        if (modelPart.equals("headwear")) {
            return bipedmodel.J_1907_R;
        }
        if (modelPart.equals("body")) {
            return bipedmodel.R_4764_Y;
        }
        if (modelPart.equals("left_arm")) {
            return bipedmodel.P_1922_E;
        }
        if (modelPart.equals("right_arm")) {
            return bipedmodel.G_564_y;
        }
        if (modelPart.equals("left_leg")) {
            return bipedmodel.v_4262_N;
        }
        return modelPart.equals("right_leg") ? bipedmodel.u_1723_Y : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "headwear", "body", "left_arm", "right_arm", "left_leg", "right_leg"};
    }
}


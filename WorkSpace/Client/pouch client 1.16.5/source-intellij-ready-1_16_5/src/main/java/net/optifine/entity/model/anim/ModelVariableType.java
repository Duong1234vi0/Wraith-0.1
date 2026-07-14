/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model.anim;

import lightning.product.e_4189_z;
import net.optifine.Config;

public enum ModelVariableType {
    POS_X("tx"),
    POS_Y("ty"),
    POS_Z("tz"),
    ANGLE_X("rx"),
    ANGLE_Y("ry"),
    ANGLE_Z("rz"),
    SCALE_X("sx"),
    SCALE_Y("sy"),
    SCALE_Z("sz");

    private String name;
    public static ModelVariableType[] VALUES;

    private ModelVariableType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public float getFloat(e_4189_z mr) {
        switch (this.ordinal()) {
            case 0: {
                return mr.R_4764_Y;
            }
            case 1: {
                return mr.G_564_y;
            }
            case 2: {
                return mr.P_1922_E;
            }
            case 3: {
                return mr.u_1723_Y;
            }
            case 4: {
                return mr.v_4262_N;
            }
            case 5: {
                return mr.w_1484_f;
            }
            case 6: {
                return mr.Q_4569_t;
            }
            case 7: {
                return mr.M_182_A;
            }
            case 8: {
                return mr.t_1786_h;
            }
        }
        Config.warn("GetFloat not supported for: " + String.valueOf((Object)this));
        return 0.0f;
    }

    public void setFloat(e_4189_z mr, float val) {
        switch (this.ordinal()) {
            case 0: {
                mr.R_4764_Y = val;
                return;
            }
            case 1: {
                mr.G_564_y = val;
                return;
            }
            case 2: {
                mr.P_1922_E = val;
                return;
            }
            case 3: {
                mr.u_1723_Y = val;
                return;
            }
            case 4: {
                mr.v_4262_N = val;
                return;
            }
            case 5: {
                mr.w_1484_f = val;
                return;
            }
            case 6: {
                mr.Q_4569_t = val;
                return;
            }
            case 7: {
                mr.M_182_A = val;
                return;
            }
            case 8: {
                mr.t_1786_h = val;
                return;
            }
        }
        Config.warn("SetFloat not supported for: " + String.valueOf((Object)this));
    }

    public static ModelVariableType parse(String str) {
        for (int i = 0; i < VALUES.length; ++i) {
            ModelVariableType modelvariabletype = VALUES[i];
            if (!modelvariabletype.getName().equals(str)) continue;
            return modelvariabletype;
        }
        return null;
    }

    static {
        VALUES = ModelVariableType.values();
    }
}


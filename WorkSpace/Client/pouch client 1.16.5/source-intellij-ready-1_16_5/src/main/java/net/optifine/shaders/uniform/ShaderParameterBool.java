/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders.uniform;

import lightning.product.N_4263_v;
import lightning.product.MinecraftClient;
import lightning.product.r_4811_B;
import lightning.product.w_2040_b;
import net.optifine.expr.IExpressionBool;

public enum ShaderParameterBool implements IExpressionBool
{
    IS_ALIVE("is_alive"),
    IS_BURNING("is_burning"),
    IS_CHILD("is_child"),
    IS_GLOWING("is_glowing"),
    IS_HURT("is_hurt"),
    IS_IN_LAVA("is_in_lava"),
    IS_IN_WATER("is_in_water"),
    IS_INVISIBLE("is_invisible"),
    IS_ON_GROUND("is_on_ground"),
    IS_RIDDEN("is_ridden"),
    IS_RIDING("is_riding"),
    IS_SNEAKING("is_sneaking"),
    IS_SPRINTING("is_sprinting"),
    IS_WET("is_wet");

    private String name;
    private w_2040_b renderManager;
    private static final ShaderParameterBool[] VALUES;

    private ShaderParameterBool(String name) {
        this.name = name;
        this.renderManager = MinecraftClient.A_4115_X().O_508_d();
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean eval() {
        N_4263_v entity = MinecraftClient.A_4115_X().g_2268_R();
        if (entity instanceof r_4811_B) {
            r_4811_B livingentity = (r_4811_B)entity;
            switch (this.ordinal()) {
                case 0: {
                    return livingentity.RealmsLongRunningMcoTaskScreen();
                }
                case 1: {
                    return livingentity.RealmsPersistence();
                }
                case 2: {
                    return livingentity.d_();
                }
                case 3: {
                    return livingentity.j_306_t();
                }
                case 4: {
                    return livingentity.RealmsLongRunningMcoTaskScreen > 0;
                }
                case 5: {
                    return livingentity.W_3464_O();
                }
                case 6: {
                    return livingentity.RowButton();
                }
                case 7: {
                    return livingentity.F_3572_x();
                }
                case 8: {
                    return livingentity.M_1641_O();
                }
                case 9: {
                    return livingentity.H_1883_T();
                }
                case 10: {
                    return livingentity.y_2772_m();
                }
                case 11: {
                    return livingentity.Z_875_P();
                }
                case 12: {
                    return livingentity.o_2341_D();
                }
                case 13: {
                    return livingentity.LongRunningTask();
                }
            }
        }
        return false;
    }

    public static ShaderParameterBool parse(String str) {
        if (str == null) {
            return null;
        }
        for (int i = 0; i < VALUES.length; ++i) {
            ShaderParameterBool shaderparameterbool = VALUES[i];
            if (!shaderparameterbool.getName().equals(str)) continue;
            return shaderparameterbool;
        }
        return null;
    }

    static {
        VALUES = ShaderParameterBool.values();
    }
}




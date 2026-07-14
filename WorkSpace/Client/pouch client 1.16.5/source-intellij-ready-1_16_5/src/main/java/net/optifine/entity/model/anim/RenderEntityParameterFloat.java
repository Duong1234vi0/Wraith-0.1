/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model.anim;

import lightning.product.Z_2049_e;
import lightning.product.MinecraftClient;
import lightning.product.o_4479_Q;
import lightning.product.r_4811_B;
import lightning.product.w_2040_b;
import net.optifine.expr.IExpressionFloat;

public enum RenderEntityParameterFloat implements IExpressionFloat
{
    LIMB_SWING("limb_swing"),
    LIMB_SWING_SPEED("limb_speed"),
    AGE("age"),
    HEAD_YAW("head_yaw"),
    HEAD_PITCH("head_pitch"),
    HEALTH("health"),
    HURT_TIME("hurt_time"),
    IDLE_TIME("idle_time"),
    MAX_HEALTH("max_health"),
    MOVE_FORWARD("move_forward"),
    MOVE_STRAFING("move_strafing"),
    PARTIAL_TICKS("partial_ticks"),
    POS_X("pos_x"),
    POS_Y("pos_y"),
    POS_Z("pos_z"),
    REVENGE_TIME("revenge_time"),
    SWING_PROGRESS("swing_progress");

    private String name;
    private w_2040_b renderManager;
    private static final RenderEntityParameterFloat[] VALUES;

    private RenderEntityParameterFloat(String name) {
        this.name = name;
        this.renderManager = MinecraftClient.A_4115_X().O_508_d();
    }

    public String getName() {
        return this.name;
    }

    @Override
    public float eval() {
        Z_2049_e entityrenderer = this.renderManager.P_1922_E;
        if (entityrenderer == null) {
            return 0.0f;
        }
        if (entityrenderer instanceof o_4479_Q) {
            o_4479_Q livingrenderer = (o_4479_Q)entityrenderer;
            switch (this.ordinal()) {
                case 0: {
                    return livingrenderer.s_956_w;
                }
                case 1: {
                    return livingrenderer.u_2550_I;
                }
                case 2: {
                    return livingrenderer.M_588_G;
                }
                case 3: {
                    return livingrenderer.P_4830_p;
                }
                case 4: {
                    return livingrenderer.h_1847_R;
                }
            }
            r_4811_B livingentity = livingrenderer.t_148_a;
            if (livingentity == null) {
                return 0.0f;
            }
            switch (this.ordinal()) {
                case 5: {
                    return livingentity.g_46_E();
                }
                case 6: {
                    return livingentity.RealmsLongRunningMcoTaskScreen;
                }
                case 7: {
                    return livingentity.g_4560_H();
                }
                case 8: {
                    return livingentity.L_1733_J();
                }
                case 9: {
                    return livingentity.L_4248_u;
                }
                case 10: {
                    return livingentity.L_1362_X;
                }
                case 12: {
                    return (float)livingentity.O_3598_v();
                }
                case 13: {
                    return (float)livingentity.X_2960_b();
                }
                case 14: {
                    return (float)livingentity.l_2647_k();
                }
                case 15: {
                    return livingentity.r_260_T();
                }
                case 16: {
                    return livingentity.Y_601_j(livingrenderer.Q_4569_t);
                }
            }
        }
        return 0.0f;
    }

    public static RenderEntityParameterFloat parse(String str) {
        if (str == null) {
            return null;
        }
        for (int i = 0; i < VALUES.length; ++i) {
            RenderEntityParameterFloat renderentityparameterfloat = VALUES[i];
            if (!renderentityparameterfloat.getName().equals(str)) continue;
            return renderentityparameterfloat;
        }
        return null;
    }

    static {
        VALUES = RenderEntityParameterFloat.values();
    }
}




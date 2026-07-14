/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.E_4918_z;
import lightning.product.I_4817_s;
import lightning.product.b_4440_Q;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.m_1605_o;
import lightning.product.r_1334_c;
import lightning.product.ShulkerModel;
import lightning.product.w_2040_b;
import lightning.product.y_3560_r;

public class D_434_g
extends r_1334_c<m_1605_o, ShulkerModel<m_1605_o>> {
    public static final g_2336_b n_1700_B = new g_2336_b("textures/" + b_4440_Q.v_4262_N.J_1907_R().J_1907_R() + ".png");
    public static final g_2336_b[] t_1786_h = (g_2336_b[])b_4440_Q.w_1484_f.stream().map(p_229125_0_ -> new g_2336_b("textures/" + p_229125_0_.J_1907_R().J_1907_R() + ".png")).toArray(g_2336_b[]::new);

    public D_434_g(w_2040_b renderManagerIn) {
        super(renderManagerIn, new ShulkerModel(), 0.0f);
        this.n_1700_B(new y_3560_r(this));
    }

    @Override
    public e_2866_D n_1700_B(m_1605_o entityIn, float partialTicks) {
        int i = entityIn.J_3635_s();
        if (i > 0 && entityIn.h_973_D()) {
            c_1514_x blockpos = entityIn.V_1176_p();
            c_1514_x blockpos1 = entityIn.o_82_k();
            double d0 = (double)((float)i - partialTicks) / 6.0;
            d0 *= d0;
            double d1 = (double)(blockpos.getX() - blockpos1.getX()) * d0;
            double d2 = (double)(blockpos.getY() - blockpos1.getY()) * d0;
            double d3 = (double)(blockpos.getZ() - blockpos1.getZ()) * d0;
            return new e_2866_D(-d1, -d2, -d3);
        }
        return super.n_1700_B(entityIn, partialTicks);
    }

    @Override
    public boolean n_1700_B(m_1605_o livingEntityIn, E_4918_z camera, double camX, double camY, double camZ) {
        if (super.n_1700_B(livingEntityIn, camera, camX, camY, camZ)) {
            return true;
        }
        if (livingEntityIn.J_3635_s() > 0 && livingEntityIn.h_973_D()) {
            e_2866_D vector3d = e_2866_D.J_1907_R(livingEntityIn.V_1176_p());
            e_2866_D vector3d1 = e_2866_D.J_1907_R(livingEntityIn.o_82_k());
            if (camera.isBoundingBoxInFrustum(new I_4817_s(vector3d1.J_1907_R, vector3d1.R_4764_Y, vector3d1.G_564_y, vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public g_2336_b n_1700_B(m_1605_o entity) {
        return entity.f_2787_O() == null ? n_1700_B : t_1786_h[entity.f_2787_O().J_1907_R()];
    }

    @Override
    protected void n_1700_B(m_1605_o entityLiving, g_221_o matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.n_1700_B(entityLiving, matrixStackIn, ageInTicks, rotationYaw + 180.0f, partialTicks);
        matrixStackIn.n_1700_B(0.0, 0.5, 0.0);
        matrixStackIn.n_1700_B(entityLiving.h_1640_b().u_1723_Y().J_1907_R());
        matrixStackIn.n_1700_B(0.0, -0.5, 0.0);
    }
}



/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_3240_x;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.L_3848_p;
import lightning.product.X_933_l;
import lightning.product.c_4037_x;
import lightning.product.l_3747_P;

public interface ParticleRenderType {
    public static final ParticleRenderType n_1700_B = new ParticleRenderType(){

        @Override
        public void n_1700_B(D_3318_r bufferBuilder, C_3240_x textureManager) {
            c_4037_x.Y_601_j();
            c_4037_x.s_2632_s();
            c_4037_x.J_1907_R(true);
            textureManager.n_1700_B(L_3848_p.n_1700_B);
            bufferBuilder.n_1700_B(7, E_688_b.multiplayerClientSuggestionProvider);
        }

        @Override
        public void n_1700_B(l_3747_P tesselator) {
            tesselator.J_1907_R();
        }

        public String toString() {
            return "TERRAIN_SHEET";
        }
    };
    public static final ParticleRenderType J_1907_R = new ParticleRenderType(){

        @Override
        public void n_1700_B(D_3318_r bufferBuilder, C_3240_x textureManager) {
            c_4037_x.Y_259_p();
            c_4037_x.J_1907_R(true);
            textureManager.n_1700_B(L_3848_p.J_1907_R);
            bufferBuilder.n_1700_B(7, E_688_b.multiplayerClientSuggestionProvider);
        }

        @Override
        public void n_1700_B(l_3747_P tesselator) {
            tesselator.J_1907_R();
        }

        public String toString() {
            return "PARTICLE_SHEET_OPAQUE";
        }
    };
    public static final ParticleRenderType R_4764_Y = new ParticleRenderType(){

        @Override
        public void n_1700_B(D_3318_r bufferBuilder, C_3240_x textureManager) {
            c_4037_x.J_1907_R(true);
            textureManager.n_1700_B(L_3848_p.J_1907_R);
            c_4037_x.Y_601_j();
            c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w, X_933_l.t_1786_h.P_1922_E, X_933_l.s_956_w.s_956_w);
            c_4037_x.n_1700_B(516, 0.003921569f);
            bufferBuilder.n_1700_B(7, E_688_b.multiplayerClientSuggestionProvider);
        }

        @Override
        public void n_1700_B(l_3747_P tesselator) {
            tesselator.J_1907_R();
        }

        public String toString() {
            return "PARTICLE_SHEET_TRANSLUCENT";
        }
    };
    public static final ParticleRenderType G_564_y = new ParticleRenderType(){

        @Override
        public void n_1700_B(D_3318_r bufferBuilder, C_3240_x textureManager) {
            c_4037_x.Y_259_p();
            c_4037_x.J_1907_R(true);
            textureManager.n_1700_B(L_3848_p.J_1907_R);
            bufferBuilder.n_1700_B(7, E_688_b.multiplayerClientSuggestionProvider);
        }

        @Override
        public void n_1700_B(l_3747_P tesselator) {
            tesselator.J_1907_R();
        }

        public String toString() {
            return "PARTICLE_SHEET_LIT";
        }
    };
    public static final ParticleRenderType P_1922_E = new ParticleRenderType(){

        @Override
        public void n_1700_B(D_3318_r bufferBuilder, C_3240_x textureManager) {
            c_4037_x.J_1907_R(true);
            c_4037_x.Y_259_p();
        }

        @Override
        public void n_1700_B(l_3747_P tesselator) {
        }

        public String toString() {
            return "CUSTOM";
        }
    };
    public static final ParticleRenderType u_1723_Y = new ParticleRenderType(){

        @Override
        public void n_1700_B(D_3318_r bufferBuilder, C_3240_x textureManager) {
        }

        @Override
        public void n_1700_B(l_3747_P tesselator) {
        }

        public String toString() {
            return "NO_RENDER";
        }
    };

    public void n_1700_B(D_3318_r var1, C_3240_x var2);

    public void n_1700_B(l_3747_P var1);
}



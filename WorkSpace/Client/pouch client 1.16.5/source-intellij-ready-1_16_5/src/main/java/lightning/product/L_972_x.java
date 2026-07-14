/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.B_3871_I;
import lightning.product.D_1098_v;
import lightning.product.E_688_b;
import lightning.product.BlockElementFace;
import lightning.product.M_1336_P;
import lightning.product.BlockFaceUV;
import lightning.product.Z_2491_A;
import lightning.product.b_257_Y;
import lightning.product.c_932_S;
import lightning.product.g_2336_b;
import lightning.product.k_2679_r;
import lightning.product.m_2240_s;
import lightning.product.Transformation;
import lightning.product.o_1290_k;
import lightning.product.BlockElementRotation;
import lightning.product.u_530_F;
import lightning.product.w_3785_E;
import lightning.product.ModelState;
import lightning.product.z_3539_x;
import net.optifine.Config;
import net.optifine.model.BlockModelUtils;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;

public class L_972_x {
    private static final float n_1700_B = 1.0f / (float)Math.cos(0.3926991f) - 1.0f;
    private static final float J_1907_R = 1.0f / (float)Math.cos(0.7853981852531433) - 1.0f;

    public c_932_S n_1700_B(M_1336_P posFrom, M_1336_P posTo, BlockElementFace face, B_3871_I sprite, b_257_Y facing, ModelState transformIn, @Nullable BlockElementRotation partRotation, boolean shade, g_2336_b modelLocationIn) {
        BlockFaceUV blockfaceuv = face.G_564_y;
        if (transformIn.J_1907_R()) {
            blockfaceuv = L_972_x.n_1700_B(face.G_564_y, facing, transformIn.n_1700_B(), modelLocationIn);
        }
        float[] afloat = new float[blockfaceuv.n_1700_B.length];
        System.arraycopy(blockfaceuv.n_1700_B, 0, afloat, 0, afloat.length);
        float f = sprite.h_1847_R();
        float f1 = (blockfaceuv.n_1700_B[0] + blockfaceuv.n_1700_B[0] + blockfaceuv.n_1700_B[2] + blockfaceuv.n_1700_B[2]) / 4.0f;
        float f2 = (blockfaceuv.n_1700_B[1] + blockfaceuv.n_1700_B[1] + blockfaceuv.n_1700_B[3] + blockfaceuv.n_1700_B[3]) / 4.0f;
        blockfaceuv.n_1700_B[0] = u_530_F.v_4262_N(f, blockfaceuv.n_1700_B[0], f1);
        blockfaceuv.n_1700_B[2] = u_530_F.v_4262_N(f, blockfaceuv.n_1700_B[2], f1);
        blockfaceuv.n_1700_B[1] = u_530_F.v_4262_N(f, blockfaceuv.n_1700_B[1], f2);
        blockfaceuv.n_1700_B[3] = u_530_F.v_4262_N(f, blockfaceuv.n_1700_B[3], f2);
        boolean flag = Reflector.ForgeHooksClient_fillNormal.exists() ? false : shade;
        int[] aint = this.n_1700_B(blockfaceuv, sprite, facing, this.n_1700_B(posFrom, posTo), transformIn.n_1700_B(), partRotation, flag);
        b_257_Y direction = L_972_x.n_1700_B(aint);
        System.arraycopy(afloat, 0, blockfaceuv.n_1700_B, 0, afloat.length);
        if (partRotation == null) {
            this.n_1700_B(aint, direction);
        }
        if (Reflector.ForgeHooksClient_fillNormal.exists()) {
            ReflectorForge.fillNormal(aint, direction);
            return new c_932_S(aint, face.J_1907_R, direction, sprite, shade);
        }
        return new c_932_S(aint, face.J_1907_R, direction, sprite, shade);
    }

    public static BlockFaceUV n_1700_B(BlockFaceUV blockFaceUVIn, b_257_Y facing, Transformation modelRotationIn, g_2336_b modelLocationIn) {
        float f11;
        float f10;
        float f9;
        float f8;
        D_1098_v matrix4f = k_2679_r.n_1700_B(modelRotationIn, facing, () -> "Unable to resolve UVLock for model: " + String.valueOf(modelLocationIn)).R_4764_Y();
        float f = blockFaceUVIn.n_1700_B(blockFaceUVIn.R_4764_Y(0));
        float f1 = blockFaceUVIn.J_1907_R(blockFaceUVIn.R_4764_Y(0));
        Z_2491_A vector4f = new Z_2491_A(f / 16.0f, f1 / 16.0f, 0.0f, 1.0f);
        vector4f.n_1700_B(matrix4f);
        float f2 = 16.0f * vector4f.n_1700_B();
        float f3 = 16.0f * vector4f.J_1907_R();
        float f4 = blockFaceUVIn.n_1700_B(blockFaceUVIn.R_4764_Y(2));
        float f5 = blockFaceUVIn.J_1907_R(blockFaceUVIn.R_4764_Y(2));
        Z_2491_A vector4f1 = new Z_2491_A(f4 / 16.0f, f5 / 16.0f, 0.0f, 1.0f);
        vector4f1.n_1700_B(matrix4f);
        float f6 = 16.0f * vector4f1.n_1700_B();
        float f7 = 16.0f * vector4f1.J_1907_R();
        if (Math.signum(f4 - f) == Math.signum(f6 - f2)) {
            f8 = f2;
            f9 = f6;
        } else {
            f8 = f6;
            f9 = f2;
        }
        if (Math.signum(f5 - f1) == Math.signum(f7 - f3)) {
            f10 = f3;
            f11 = f7;
        } else {
            f10 = f7;
            f11 = f3;
        }
        float f12 = (float)Math.toRadians(blockFaceUVIn.J_1907_R);
        M_1336_P vector3f = new M_1336_P(u_530_F.J_1907_R(f12), u_530_F.n_1700_B(f12), 0.0f);
        o_1290_k matrix3f = new o_1290_k(matrix4f);
        vector3f.n_1700_B(matrix3f);
        int i = Math.floorMod(-((int)Math.round(Math.toDegrees(Math.atan2(vector3f.J_1907_R(), vector3f.n_1700_B())) / 90.0)) * 90, 360);
        return new BlockFaceUV(new float[]{f8, f10, f9, f11}, i);
    }

    private int[] n_1700_B(BlockFaceUV uvs, B_3871_I sprite, b_257_Y orientation, float[] posDiv16, Transformation rotationIn, @Nullable BlockElementRotation partRotation, boolean shade) {
        int i = Config.isShaders() ? E_688_b.P_4830_p : E_688_b.M_588_G;
        int[] aint = new int[i];
        for (int j = 0; j < 4; ++j) {
            this.n_1700_B(aint, j, orientation, uvs, posDiv16, sprite, rotationIn, partRotation, shade);
        }
        return aint;
    }

    private float[] n_1700_B(M_1336_P pos1, M_1336_P pos2) {
        float[] afloat = new float[b_257_Y.values().length];
        afloat[m_2240_s.n_1700_B.u_1723_Y] = pos1.n_1700_B() / 16.0f;
        afloat[m_2240_s.n_1700_B.P_1922_E] = pos1.J_1907_R() / 16.0f;
        afloat[m_2240_s.n_1700_B.G_564_y] = pos1.R_4764_Y() / 16.0f;
        afloat[m_2240_s.n_1700_B.R_4764_Y] = pos2.n_1700_B() / 16.0f;
        afloat[m_2240_s.n_1700_B.J_1907_R] = pos2.J_1907_R() / 16.0f;
        afloat[m_2240_s.n_1700_B.n_1700_B] = pos2.R_4764_Y() / 16.0f;
        return afloat;
    }

    private void n_1700_B(int[] vertexData, int vertexIndex, b_257_Y facing, BlockFaceUV blockFaceUVIn, float[] posDiv16, B_3871_I sprite, Transformation rotationIn, @Nullable BlockElementRotation partRotation, boolean shade) {
        m_2240_s.J_1907_R facedirection$vertexinformation = m_2240_s.n_1700_B(facing).n_1700_B(vertexIndex);
        M_1336_P vector3f = new M_1336_P(posDiv16[facedirection$vertexinformation.n_1700_B], posDiv16[facedirection$vertexinformation.J_1907_R], posDiv16[facedirection$vertexinformation.R_4764_Y]);
        this.n_1700_B(vector3f, partRotation);
        this.n_1700_B(vector3f, rotationIn);
        BlockModelUtils.snapVertexPosition(vector3f);
        this.n_1700_B(vertexData, vertexIndex, vector3f, sprite, blockFaceUVIn);
    }

    private void n_1700_B(int[] vertexData, int vertexIndex, M_1336_P vector, B_3871_I sprite, BlockFaceUV blockFaceUV) {
        int i = vertexData.length / 4;
        int j = vertexIndex * i;
        vertexData[j] = Float.floatToRawIntBits(vector.n_1700_B());
        vertexData[j + 1] = Float.floatToRawIntBits(vector.J_1907_R());
        vertexData[j + 2] = Float.floatToRawIntBits(vector.R_4764_Y());
        vertexData[j + 3] = -1;
        vertexData[j + 4] = Float.floatToRawIntBits(sprite.n_1700_B((double)blockFaceUV.n_1700_B(vertexIndex)));
        vertexData[j + 4 + 1] = Float.floatToRawIntBits(sprite.J_1907_R((double)blockFaceUV.J_1907_R(vertexIndex)));
    }

    private void n_1700_B(M_1336_P vec, @Nullable BlockElementRotation partRotation) {
        if (partRotation != null) {
            M_1336_P vector3f;
            M_1336_P vector3f1 = switch (partRotation.J_1907_R) {
                case b_257_Y.n_1700_B.n_1700_B -> {
                    vector3f = new M_1336_P(1.0f, 0.0f, 0.0f);
                    yield new M_1336_P(0.0f, 1.0f, 1.0f);
                }
                case b_257_Y.n_1700_B.J_1907_R -> {
                    vector3f = new M_1336_P(0.0f, 1.0f, 0.0f);
                    yield new M_1336_P(1.0f, 0.0f, 1.0f);
                }
                case b_257_Y.n_1700_B.R_4764_Y -> {
                    vector3f = new M_1336_P(0.0f, 0.0f, 1.0f);
                    yield new M_1336_P(1.0f, 1.0f, 0.0f);
                }
                default -> throw new IllegalArgumentException("There are only 3 axes");
            };
            w_3785_E quaternion = new w_3785_E(vector3f, partRotation.R_4764_Y, true);
            if (partRotation.G_564_y) {
                if (Math.abs(partRotation.R_4764_Y) == 22.5f) {
                    vector3f1.n_1700_B(n_1700_B);
                } else {
                    vector3f1.n_1700_B(J_1907_R);
                }
                vector3f1.R_4764_Y(1.0f, 1.0f, 1.0f);
            } else {
                vector3f1.J_1907_R(1.0f, 1.0f, 1.0f);
            }
            this.n_1700_B(vec, partRotation.n_1700_B.P_1922_E(), new D_1098_v(quaternion), vector3f1);
        }
    }

    public void n_1700_B(M_1336_P posIn, Transformation transformIn) {
        if (transformIn != Transformation.n_1700_B()) {
            this.n_1700_B(posIn, new M_1336_P(0.5f, 0.5f, 0.5f), transformIn.R_4764_Y(), new M_1336_P(1.0f, 1.0f, 1.0f));
        }
    }

    private void n_1700_B(M_1336_P posIn, M_1336_P originIn, D_1098_v transformIn, M_1336_P scaleIn) {
        Z_2491_A vector4f = new Z_2491_A(posIn.n_1700_B() - originIn.n_1700_B(), posIn.J_1907_R() - originIn.J_1907_R(), posIn.R_4764_Y() - originIn.R_4764_Y(), 1.0f);
        vector4f.n_1700_B(transformIn);
        vector4f.n_1700_B(scaleIn);
        posIn.J_1907_R(vector4f.n_1700_B() + originIn.n_1700_B(), vector4f.J_1907_R() + originIn.J_1907_R(), vector4f.R_4764_Y() + originIn.R_4764_Y());
    }

    public static b_257_Y n_1700_B(int[] faceData) {
        int i = faceData.length / 4;
        int j = i * 2;
        M_1336_P vector3f = new M_1336_P(Float.intBitsToFloat(faceData[0]), Float.intBitsToFloat(faceData[1]), Float.intBitsToFloat(faceData[2]));
        M_1336_P vector3f1 = new M_1336_P(Float.intBitsToFloat(faceData[i]), Float.intBitsToFloat(faceData[i + 1]), Float.intBitsToFloat(faceData[i + 2]));
        M_1336_P vector3f2 = new M_1336_P(Float.intBitsToFloat(faceData[j]), Float.intBitsToFloat(faceData[j + 1]), Float.intBitsToFloat(faceData[j + 2]));
        M_1336_P vector3f3 = vector3f.P_1922_E();
        vector3f3.J_1907_R(vector3f1);
        M_1336_P vector3f4 = vector3f2.P_1922_E();
        vector3f4.J_1907_R(vector3f1);
        M_1336_P vector3f5 = vector3f4.P_1922_E();
        vector3f5.G_564_y(vector3f3);
        vector3f5.G_564_y();
        b_257_Y direction = null;
        float f = 0.0f;
        for (b_257_Y direction1 : b_257_Y.values()) {
            z_3539_x vector3i = direction1.M_182_A();
            M_1336_P vector3f6 = new M_1336_P(vector3i.getX(), vector3i.getY(), vector3i.getZ());
            float f1 = vector3f5.R_4764_Y(vector3f6);
            if (!(f1 >= 0.0f) || !(f1 > f)) continue;
            f = f1;
            direction = direction1;
        }
        return direction == null ? b_257_Y.J_1907_R : direction;
    }

    private void n_1700_B(int[] vertexData, b_257_Y directionIn) {
        int[] aint = new int[vertexData.length];
        System.arraycopy(vertexData, 0, aint, 0, vertexData.length);
        float[] afloat = new float[b_257_Y.values().length];
        afloat[m_2240_s.n_1700_B.u_1723_Y] = 999.0f;
        afloat[m_2240_s.n_1700_B.P_1922_E] = 999.0f;
        afloat[m_2240_s.n_1700_B.G_564_y] = 999.0f;
        afloat[m_2240_s.n_1700_B.R_4764_Y] = -999.0f;
        afloat[m_2240_s.n_1700_B.J_1907_R] = -999.0f;
        afloat[m_2240_s.n_1700_B.n_1700_B] = -999.0f;
        int i = vertexData.length / 4;
        for (int j = 0; j < 4; ++j) {
            int k = i * j;
            float f = Float.intBitsToFloat(aint[k]);
            float f1 = Float.intBitsToFloat(aint[k + 1]);
            float f2 = Float.intBitsToFloat(aint[k + 2]);
            if (f < afloat[m_2240_s.n_1700_B.u_1723_Y]) {
                afloat[m_2240_s.n_1700_B.u_1723_Y] = f;
            }
            if (f1 < afloat[m_2240_s.n_1700_B.P_1922_E]) {
                afloat[m_2240_s.n_1700_B.P_1922_E] = f1;
            }
            if (f2 < afloat[m_2240_s.n_1700_B.G_564_y]) {
                afloat[m_2240_s.n_1700_B.G_564_y] = f2;
            }
            if (f > afloat[m_2240_s.n_1700_B.R_4764_Y]) {
                afloat[m_2240_s.n_1700_B.R_4764_Y] = f;
            }
            if (f1 > afloat[m_2240_s.n_1700_B.J_1907_R]) {
                afloat[m_2240_s.n_1700_B.J_1907_R] = f1;
            }
            if (!(f2 > afloat[m_2240_s.n_1700_B.n_1700_B])) continue;
            afloat[m_2240_s.n_1700_B.n_1700_B] = f2;
        }
        m_2240_s facedirection = m_2240_s.n_1700_B(directionIn);
        for (int j1 = 0; j1 < 4; ++j1) {
            int k1 = i * j1;
            m_2240_s.J_1907_R facedirection$vertexinformation = facedirection.n_1700_B(j1);
            float f8 = afloat[facedirection$vertexinformation.n_1700_B];
            float f3 = afloat[facedirection$vertexinformation.J_1907_R];
            float f4 = afloat[facedirection$vertexinformation.R_4764_Y];
            vertexData[k1] = Float.floatToRawIntBits(f8);
            vertexData[k1 + 1] = Float.floatToRawIntBits(f3);
            vertexData[k1 + 2] = Float.floatToRawIntBits(f4);
            for (int l = 0; l < 4; ++l) {
                int i1 = i * l;
                float f5 = Float.intBitsToFloat(aint[i1]);
                float f6 = Float.intBitsToFloat(aint[i1 + 1]);
                float f7 = Float.intBitsToFloat(aint[i1 + 2]);
                if (!u_530_F.n_1700_B(f8, f5) || !u_530_F.n_1700_B(f3, f6) || !u_530_F.n_1700_B(f4, f7)) continue;
                vertexData[k1 + 4] = aint[i1 + 4];
                vertexData[k1 + 4 + 1] = aint[i1 + 4 + 1];
            }
        }
    }
}



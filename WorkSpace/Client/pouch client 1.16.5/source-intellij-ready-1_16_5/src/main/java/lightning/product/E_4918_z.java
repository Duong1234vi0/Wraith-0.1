/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1098_v;
import lightning.product.I_4817_s;
import lightning.product.Z_2491_A;
import net.optifine.render.ICamera;

public class E_4918_z
implements ICamera {
    private final Z_2491_A[] frustum = new Z_2491_A[6];
    private double cameraX;
    private double cameraY;
    private double cameraZ;
    public boolean disabled = false;

    public E_4918_z(D_1098_v matrix4f, D_1098_v projection) {
        this.calculateFrustum(matrix4f, projection);
    }

    @Override
    public void setCameraPosition(double camX, double camY, double camZ) {
        this.cameraX = camX;
        this.cameraY = camY;
        this.cameraZ = camZ;
    }

    private void calculateFrustum(D_1098_v projection, D_1098_v frustrumMatrix) {
        D_1098_v matrix4f = frustrumMatrix.u_1723_Y();
        matrix4f.n_1700_B(projection);
        matrix4f.G_564_y();
        this.setFrustumPlane(matrix4f, -1, 0, 0, 0);
        this.setFrustumPlane(matrix4f, 1, 0, 0, 1);
        this.setFrustumPlane(matrix4f, 0, -1, 0, 2);
        this.setFrustumPlane(matrix4f, 0, 1, 0, 3);
        this.setFrustumPlane(matrix4f, 0, 0, -1, 4);
        this.setFrustumPlane(matrix4f, 0, 0, 1, 5);
    }

    private void setFrustumPlane(D_1098_v frustrumMatrix, int x, int y, int z, int id) {
        Z_2491_A vector4f = new Z_2491_A(x, y, z, 1.0f);
        vector4f.n_1700_B(frustrumMatrix);
        vector4f.P_1922_E();
        this.frustum[id] = vector4f;
    }

    @Override
    public boolean isBoundingBoxInFrustum(I_4817_s aabbIn) {
        return this.isBoxInFrustum(aabbIn.minX, aabbIn.minY, aabbIn.minZ, aabbIn.maxX, aabbIn.maxY, aabbIn.maxZ);
    }

    private boolean isBoxInFrustum(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        if (this.disabled) {
            return true;
        }
        float f = (float)(minX - this.cameraX);
        float f1 = (float)(minY - this.cameraY);
        float f2 = (float)(minZ - this.cameraZ);
        float f3 = (float)(maxX - this.cameraX);
        float f4 = (float)(maxY - this.cameraY);
        float f5 = (float)(maxZ - this.cameraZ);
        return this.isBoxInFrustumRaw(f, f1, f2, f3, f4, f5);
    }

    private boolean isBoxInFrustumRaw(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        for (int i = 0; i < 6; ++i) {
            float f3;
            float f2;
            float f1;
            Z_2491_A vector4f = this.frustum[i];
            float f = vector4f.n_1700_B();
            if (!(f * minX + (f1 = vector4f.J_1907_R()) * minY + (f2 = vector4f.R_4764_Y()) * minZ + (f3 = vector4f.G_564_y()) <= 0.0f) || !(f * maxX + f1 * minY + f2 * minZ + f3 <= 0.0f) || !(f * minX + f1 * maxY + f2 * minZ + f3 <= 0.0f) || !(f * maxX + f1 * maxY + f2 * minZ + f3 <= 0.0f) || !(f * minX + f1 * minY + f2 * maxZ + f3 <= 0.0f) || !(f * maxX + f1 * minY + f2 * maxZ + f3 <= 0.0f) || !(f * minX + f1 * maxY + f2 * maxZ + f3 <= 0.0f) || !(f * maxX + f1 * maxY + f2 * maxZ + f3 <= 0.0f)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean isBoxInFrustumFully(double p_isBoxInFrustumFully_1_, double p_isBoxInFrustumFully_3_, double p_isBoxInFrustumFully_5_, double p_isBoxInFrustumFully_7_, double p_isBoxInFrustumFully_9_, double p_isBoxInFrustumFully_11_) {
        if (this.disabled) {
            return true;
        }
        float f = (float)p_isBoxInFrustumFully_1_;
        float f1 = (float)p_isBoxInFrustumFully_3_;
        float f2 = (float)p_isBoxInFrustumFully_5_;
        float f3 = (float)p_isBoxInFrustumFully_7_;
        float f4 = (float)p_isBoxInFrustumFully_9_;
        float f5 = (float)p_isBoxInFrustumFully_11_;
        for (int i = 0; i < 6; ++i) {
            Z_2491_A vector4f = this.frustum[i];
            float f6 = vector4f.n_1700_B();
            float f7 = vector4f.J_1907_R();
            float f8 = vector4f.R_4764_Y();
            float f9 = vector4f.G_564_y();
            if (!(i < 4 ? f6 * f + f7 * f1 + f8 * f2 + f9 <= 0.0f || f6 * f3 + f7 * f1 + f8 * f2 + f9 <= 0.0f || f6 * f + f7 * f4 + f8 * f2 + f9 <= 0.0f || f6 * f3 + f7 * f4 + f8 * f2 + f9 <= 0.0f || f6 * f + f7 * f1 + f8 * f5 + f9 <= 0.0f || f6 * f3 + f7 * f1 + f8 * f5 + f9 <= 0.0f || f6 * f + f7 * f4 + f8 * f5 + f9 <= 0.0f || f6 * f3 + f7 * f4 + f8 * f5 + f9 <= 0.0f : f6 * f + f7 * f1 + f8 * f2 + f9 <= 0.0f && f6 * f3 + f7 * f1 + f8 * f2 + f9 <= 0.0f && f6 * f + f7 * f4 + f8 * f2 + f9 <= 0.0f && f6 * f3 + f7 * f4 + f8 * f2 + f9 <= 0.0f && f6 * f + f7 * f1 + f8 * f5 + f9 <= 0.0f && f6 * f3 + f7 * f1 + f8 * f5 + f9 <= 0.0f && f6 * f + f7 * f4 + f8 * f5 + f9 <= 0.0f && f6 * f3 + f7 * f4 + f8 * f5 + f9 <= 0.0f)) continue;
            return false;
        }
        return true;
    }

    public Z_2491_A[] getFrustum() {
        return this.frustum;
    }
}


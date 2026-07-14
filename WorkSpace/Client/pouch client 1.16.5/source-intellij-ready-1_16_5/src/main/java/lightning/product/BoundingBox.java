/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 */
package lightning.product;

import com.google.common.base.MoreObjects;
import lightning.product.IntArrayTag;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.z_3539_x;

public class BoundingBox {
    public int n_1700_B;
    public int J_1907_R;
    public int R_4764_Y;
    public int G_564_y;
    public int P_1922_E;
    public int u_1723_Y;

    public BoundingBox() {
    }

    public BoundingBox(int[] coords) {
        if (coords.length == 6) {
            this.n_1700_B = coords[0];
            this.J_1907_R = coords[1];
            this.R_4764_Y = coords[2];
            this.G_564_y = coords[3];
            this.P_1922_E = coords[4];
            this.u_1723_Y = coords[5];
        }
    }

    public static BoundingBox n_1700_B() {
        return new BoundingBox(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public static BoundingBox J_1907_R() {
        return new BoundingBox(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public static BoundingBox n_1700_B(int structureMinX, int structureMinY, int structureMinZ, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, b_257_Y facing) {
        switch (facing) {
            case R_4764_Y: {
                return new BoundingBox(structureMinX + xMin, structureMinY + yMin, structureMinZ - zMax + 1 + zMin, structureMinX + xMax - 1 + xMin, structureMinY + yMax - 1 + yMin, structureMinZ + zMin);
            }
            case G_564_y: {
                return new BoundingBox(structureMinX + xMin, structureMinY + yMin, structureMinZ + zMin, structureMinX + xMax - 1 + xMin, structureMinY + yMax - 1 + yMin, structureMinZ + zMax - 1 + zMin);
            }
            case P_1922_E: {
                return new BoundingBox(structureMinX - zMax + 1 + zMin, structureMinY + yMin, structureMinZ + xMin, structureMinX + zMin, structureMinY + yMax - 1 + yMin, structureMinZ + xMax - 1 + xMin);
            }
            case u_1723_Y: {
                return new BoundingBox(structureMinX + zMin, structureMinY + yMin, structureMinZ + xMin, structureMinX + zMax - 1 + zMin, structureMinY + yMax - 1 + yMin, structureMinZ + xMax - 1 + xMin);
            }
        }
        return new BoundingBox(structureMinX + xMin, structureMinY + yMin, structureMinZ + zMin, structureMinX + xMax - 1 + xMin, structureMinY + yMax - 1 + yMin, structureMinZ + zMax - 1 + zMin);
    }

    public static BoundingBox n_1700_B(int x1, int y1, int z1, int x2, int y2, int z2) {
        return new BoundingBox(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2), Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2));
    }

    public BoundingBox(BoundingBox structurebb) {
        this.n_1700_B = structurebb.n_1700_B;
        this.J_1907_R = structurebb.J_1907_R;
        this.R_4764_Y = structurebb.R_4764_Y;
        this.G_564_y = structurebb.G_564_y;
        this.P_1922_E = structurebb.P_1922_E;
        this.u_1723_Y = structurebb.u_1723_Y;
    }

    public BoundingBox(int xMin, int yMin, int zMin, int xMax, int yMax, int zMax) {
        this.n_1700_B = xMin;
        this.J_1907_R = yMin;
        this.R_4764_Y = zMin;
        this.G_564_y = xMax;
        this.P_1922_E = yMax;
        this.u_1723_Y = zMax;
    }

    public BoundingBox(z_3539_x vec1, z_3539_x vec2) {
        this.n_1700_B = Math.min(vec1.getX(), vec2.getX());
        this.J_1907_R = Math.min(vec1.getY(), vec2.getY());
        this.R_4764_Y = Math.min(vec1.getZ(), vec2.getZ());
        this.G_564_y = Math.max(vec1.getX(), vec2.getX());
        this.P_1922_E = Math.max(vec1.getY(), vec2.getY());
        this.u_1723_Y = Math.max(vec1.getZ(), vec2.getZ());
    }

    public BoundingBox(int xMin, int zMin, int xMax, int zMax) {
        this.n_1700_B = xMin;
        this.R_4764_Y = zMin;
        this.G_564_y = xMax;
        this.u_1723_Y = zMax;
        this.J_1907_R = 1;
        this.P_1922_E = 512;
    }

    public boolean n_1700_B(BoundingBox structurebb) {
        return this.G_564_y >= structurebb.n_1700_B && this.n_1700_B <= structurebb.G_564_y && this.u_1723_Y >= structurebb.R_4764_Y && this.R_4764_Y <= structurebb.u_1723_Y && this.P_1922_E >= structurebb.J_1907_R && this.J_1907_R <= structurebb.P_1922_E;
    }

    public boolean n_1700_B(int minXIn, int minZIn, int maxXIn, int maxZIn) {
        return this.G_564_y >= minXIn && this.n_1700_B <= maxXIn && this.u_1723_Y >= minZIn && this.R_4764_Y <= maxZIn;
    }

    public void J_1907_R(BoundingBox sbb) {
        this.n_1700_B = Math.min(this.n_1700_B, sbb.n_1700_B);
        this.J_1907_R = Math.min(this.J_1907_R, sbb.J_1907_R);
        this.R_4764_Y = Math.min(this.R_4764_Y, sbb.R_4764_Y);
        this.G_564_y = Math.max(this.G_564_y, sbb.G_564_y);
        this.P_1922_E = Math.max(this.P_1922_E, sbb.P_1922_E);
        this.u_1723_Y = Math.max(this.u_1723_Y, sbb.u_1723_Y);
    }

    public void n_1700_B(int x, int y, int z) {
        this.n_1700_B += x;
        this.J_1907_R += y;
        this.R_4764_Y += z;
        this.G_564_y += x;
        this.P_1922_E += y;
        this.u_1723_Y += z;
    }

    public BoundingBox J_1907_R(int p_215127_1_, int p_215127_2_, int p_215127_3_) {
        return new BoundingBox(this.n_1700_B + p_215127_1_, this.J_1907_R + p_215127_2_, this.R_4764_Y + p_215127_3_, this.G_564_y + p_215127_1_, this.P_1922_E + p_215127_2_, this.u_1723_Y + p_215127_3_);
    }

    public void n_1700_B(z_3539_x p_236989_1_) {
        this.n_1700_B(p_236989_1_.getX(), p_236989_1_.getY(), p_236989_1_.getZ());
    }

    public boolean J_1907_R(z_3539_x vec) {
        return vec.getX() >= this.n_1700_B && vec.getX() <= this.G_564_y && vec.getZ() >= this.R_4764_Y && vec.getZ() <= this.u_1723_Y && vec.getY() >= this.J_1907_R && vec.getY() <= this.P_1922_E;
    }

    public z_3539_x R_4764_Y() {
        return new z_3539_x(this.G_564_y - this.n_1700_B, this.P_1922_E - this.J_1907_R, this.u_1723_Y - this.R_4764_Y);
    }

    public int G_564_y() {
        return this.G_564_y - this.n_1700_B + 1;
    }

    public int P_1922_E() {
        return this.P_1922_E - this.J_1907_R + 1;
    }

    public int u_1723_Y() {
        return this.u_1723_Y - this.R_4764_Y + 1;
    }

    public z_3539_x v_4262_N() {
        return new c_1514_x(this.n_1700_B + (this.G_564_y - this.n_1700_B + 1) / 2, this.J_1907_R + (this.P_1922_E - this.J_1907_R + 1) / 2, this.R_4764_Y + (this.u_1723_Y - this.R_4764_Y + 1) / 2);
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("x0", this.n_1700_B).add("y0", this.J_1907_R).add("z0", this.R_4764_Y).add("x1", this.G_564_y).add("y1", this.P_1922_E).add("z1", this.u_1723_Y).toString();
    }

    public IntArrayTag w_1484_f() {
        return new IntArrayTag(new int[]{this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y});
    }
}



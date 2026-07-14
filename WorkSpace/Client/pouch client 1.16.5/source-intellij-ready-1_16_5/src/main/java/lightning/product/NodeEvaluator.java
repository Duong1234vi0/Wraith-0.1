/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lightning.product.D_1436_R;
import lightning.product.BlockGetter;
import lightning.product.I_1869_h;
import lightning.product.Target;
import lightning.product.PathNavigationRegion;
import lightning.product.Z_530_i;
import lightning.product.c_1514_x;
import lightning.product.u_530_F;

public abstract class NodeEvaluator {
    protected PathNavigationRegion n_1700_B;
    protected Z_530_i J_1907_R;
    protected final Int2ObjectMap<D_1436_R> R_4764_Y = new Int2ObjectOpenHashMap();
    protected int G_564_y;
    protected int P_1922_E;
    protected int u_1723_Y;
    protected boolean v_4262_N;
    protected boolean w_1484_f;
    protected boolean t_148_a;

    public void n_1700_B(PathNavigationRegion p_225578_1_, Z_530_i p_225578_2_) {
        this.n_1700_B = p_225578_1_;
        this.J_1907_R = p_225578_2_;
        this.R_4764_Y.clear();
        this.G_564_y = u_530_F.G_564_y(p_225578_2_.C_415_h() + 1.0f);
        this.P_1922_E = u_530_F.G_564_y(p_225578_2_.v_165_F() + 1.0f);
        this.u_1723_Y = u_530_F.G_564_y(p_225578_2_.C_415_h() + 1.0f);
    }

    public void n_1700_B() {
        this.n_1700_B = null;
        this.J_1907_R = null;
    }

    protected D_1436_R n_1700_B(c_1514_x p_237223_1_) {
        return this.n_1700_B(p_237223_1_.getX(), p_237223_1_.getY(), p_237223_1_.getZ());
    }

    protected D_1436_R n_1700_B(int x, int y, int z) {
        return (D_1436_R)this.R_4764_Y.computeIfAbsent(D_1436_R.J_1907_R(x, y, z), p_215743_3_ -> new D_1436_R(x, y, z));
    }

    public abstract D_1436_R J_1907_R();

    public abstract Target n_1700_B(double var1, double var3, double var5);

    public abstract int n_1700_B(D_1436_R[] var1, D_1436_R var2);

    public abstract I_1869_h n_1700_B(BlockGetter var1, int var2, int var3, int var4, Z_530_i var5, int var6, int var7, int var8, boolean var9, boolean var10);

    public abstract I_1869_h n_1700_B(BlockGetter var1, int var2, int var3, int var4);

    public void n_1700_B(boolean canEnterDoorsIn) {
        this.v_4262_N = canEnterDoorsIn;
    }

    public void J_1907_R(boolean canOpenDoorsIn) {
        this.w_1484_f = canOpenDoorsIn;
    }

    public void R_4764_Y(boolean canSwimIn) {
        this.t_148_a = canSwimIn;
    }

    public boolean R_4764_Y() {
        return this.v_4262_N;
    }

    public boolean G_564_y() {
        return this.w_1484_f;
    }

    public boolean P_1922_E() {
        return this.t_148_a;
    }
}



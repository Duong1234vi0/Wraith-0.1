/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import lightning.product.B_3871_I;
import lightning.product.j_1909_L;
import lightning.product.u_530_F;
import net.optifine.util.MathUtils;

public class i_2224_y {
    private static final Comparator<n_1700_B> n_1700_B = Comparator.comparing(p_lambda$static$0_0_ -> -p_lambda$static$0_0_.R_4764_Y).thenComparing(p_lambda$static$1_0_ -> -p_lambda$static$1_0_.J_1907_R).thenComparing(p_lambda$static$2_0_ -> p_lambda$static$2_0_.n_1700_B.n_1700_B());
    private final int J_1907_R;
    private final Set<n_1700_B> R_4764_Y = Sets.newHashSetWithExpectedSize((int)256);
    private final List<R_4764_Y> G_564_y = Lists.newArrayListWithCapacity((int)256);
    private int P_1922_E;
    private int u_1723_Y;
    private final int v_4262_N;
    private final int w_1484_f;

    public i_2224_y(int mipmapLevelIn, int maxWidthIn, int maxHeightIn) {
        this.J_1907_R = maxHeightIn;
        this.v_4262_N = mipmapLevelIn;
        this.w_1484_f = maxWidthIn;
    }

    public int n_1700_B() {
        return this.P_1922_E;
    }

    public int J_1907_R() {
        return this.u_1723_Y;
    }

    public void n_1700_B(B_3871_I.n_1700_B spriteInfoIn) {
        n_1700_B stitcher$holder = new n_1700_B(spriteInfoIn, this.J_1907_R);
        this.R_4764_Y.add(stitcher$holder);
    }

    public void R_4764_Y() {
        ArrayList list = Lists.newArrayList(this.R_4764_Y);
        list.sort(n_1700_B);
        for (n_1700_B stitcher$holder : list) {
            if (this.n_1700_B(stitcher$holder)) continue;
            throw new j_1909_L(stitcher$holder.n_1700_B, (Collection)list.stream().map(p_lambda$doStitch$3_0_ -> p_lambda$doStitch$3_0_.n_1700_B).collect(ImmutableList.toImmutableList()), this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f);
        }
        this.P_1922_E = u_530_F.R_4764_Y(this.P_1922_E);
        this.u_1723_Y = u_530_F.R_4764_Y(this.u_1723_Y);
    }

    public void n_1700_B(J_1907_R spriteLoaderIn) {
        for (R_4764_Y stitcher$slot : this.G_564_y) {
            stitcher$slot.n_1700_B((R_4764_Y p_lambda$getStitchSlots$4_2_) -> {
                n_1700_B stitcher$holder = p_lambda$getStitchSlots$4_2_.n_1700_B();
                B_3871_I.n_1700_B textureatlassprite$info = stitcher$holder.n_1700_B;
                spriteLoaderIn.load(textureatlassprite$info, this.P_1922_E, this.u_1723_Y, p_lambda$getStitchSlots$4_2_.J_1907_R(), p_lambda$getStitchSlots$4_2_.R_4764_Y());
            });
        }
    }

    private static int n_1700_B(int dimensionIn, int mipmapLevelIn) {
        return (dimensionIn >> mipmapLevelIn) + ((dimensionIn & (1 << mipmapLevelIn) - 1) == 0 ? 0 : 1) << mipmapLevelIn;
    }

    private boolean n_1700_B(n_1700_B holderIn) {
        for (R_4764_Y stitcher$slot : this.G_564_y) {
            if (!stitcher$slot.n_1700_B(holderIn)) continue;
            return true;
        }
        return this.J_1907_R(holderIn);
    }

    private boolean J_1907_R(n_1700_B holderIn) {
        R_4764_Y stitcher$slot;
        boolean flag2;
        boolean flag1;
        int i = u_530_F.R_4764_Y(this.P_1922_E);
        int j = u_530_F.R_4764_Y(this.u_1723_Y);
        int k = u_530_F.R_4764_Y(this.P_1922_E + holderIn.J_1907_R);
        int l = u_530_F.R_4764_Y(this.u_1723_Y + holderIn.R_4764_Y);
        boolean flag = k <= this.v_4262_N;
        boolean bl = flag1 = l <= this.w_1484_f;
        if (!flag && !flag1) {
            return false;
        }
        int i1 = MathUtils.roundDownToPowerOfTwo(this.u_1723_Y);
        boolean bl2 = flag2 = flag && k <= 2 * i1;
        if (this.P_1922_E == 0 && this.u_1723_Y == 0) {
            flag2 = true;
        }
        if (flag2) {
            if (this.u_1723_Y == 0) {
                this.u_1723_Y = holderIn.R_4764_Y;
            }
            stitcher$slot = new R_4764_Y(this.P_1922_E, 0, holderIn.J_1907_R, this.u_1723_Y);
            this.P_1922_E += holderIn.J_1907_R;
        } else {
            stitcher$slot = new R_4764_Y(0, this.u_1723_Y, this.P_1922_E, holderIn.R_4764_Y);
            this.u_1723_Y += holderIn.R_4764_Y;
        }
        stitcher$slot.n_1700_B(holderIn);
        this.G_564_y.add(stitcher$slot);
        return true;
    }

    static class n_1700_B {
        public final B_3871_I.n_1700_B n_1700_B;
        public final int J_1907_R;
        public final int R_4764_Y;

        public n_1700_B(B_3871_I.n_1700_B spriteInfoIn, int mipmapLevelIn) {
            this.n_1700_B = spriteInfoIn;
            this.J_1907_R = i_2224_y.n_1700_B(spriteInfoIn.J_1907_R(), mipmapLevelIn);
            this.R_4764_Y = i_2224_y.n_1700_B(spriteInfoIn.R_4764_Y(), mipmapLevelIn);
        }

        public String toString() {
            return "Holder{width=" + this.J_1907_R + ", height=" + this.R_4764_Y + ", name=" + String.valueOf(this.n_1700_B.n_1700_B()) + "}";
        }
    }

    public static class R_4764_Y {
        private final int n_1700_B;
        private final int J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;
        private List<R_4764_Y> P_1922_E;
        private n_1700_B u_1723_Y;

        public R_4764_Y(int originXIn, int originYIn, int widthIn, int heightIn) {
            this.n_1700_B = originXIn;
            this.J_1907_R = originYIn;
            this.R_4764_Y = widthIn;
            this.G_564_y = heightIn;
        }

        public n_1700_B n_1700_B() {
            return this.u_1723_Y;
        }

        public int J_1907_R() {
            return this.n_1700_B;
        }

        public int R_4764_Y() {
            return this.J_1907_R;
        }

        public boolean n_1700_B(n_1700_B holderIn) {
            if (this.u_1723_Y != null) {
                return false;
            }
            int i = holderIn.J_1907_R;
            int j = holderIn.R_4764_Y;
            if (i <= this.R_4764_Y && j <= this.G_564_y) {
                if (i == this.R_4764_Y && j == this.G_564_y) {
                    this.u_1723_Y = holderIn;
                    return true;
                }
                if (this.P_1922_E == null) {
                    this.P_1922_E = Lists.newArrayListWithCapacity((int)1);
                    this.P_1922_E.add(new R_4764_Y(this.n_1700_B, this.J_1907_R, i, j));
                    int k = this.R_4764_Y - i;
                    int l = this.G_564_y - j;
                    if (l > 0 && k > 0) {
                        int j1;
                        int i1 = Math.max(this.G_564_y, k);
                        if (i1 >= (j1 = Math.max(this.R_4764_Y, l))) {
                            this.P_1922_E.add(new R_4764_Y(this.n_1700_B, this.J_1907_R + j, i, l));
                            this.P_1922_E.add(new R_4764_Y(this.n_1700_B + i, this.J_1907_R, k, this.G_564_y));
                        } else {
                            this.P_1922_E.add(new R_4764_Y(this.n_1700_B + i, this.J_1907_R, k, j));
                            this.P_1922_E.add(new R_4764_Y(this.n_1700_B, this.J_1907_R + j, this.R_4764_Y, l));
                        }
                    } else if (k == 0) {
                        this.P_1922_E.add(new R_4764_Y(this.n_1700_B, this.J_1907_R + j, i, l));
                    } else if (l == 0) {
                        this.P_1922_E.add(new R_4764_Y(this.n_1700_B + i, this.J_1907_R, k, j));
                    }
                }
                for (R_4764_Y stitcher$slot : this.P_1922_E) {
                    if (!stitcher$slot.n_1700_B(holderIn)) continue;
                    return true;
                }
                return false;
            }
            return false;
        }

        public void n_1700_B(Consumer<R_4764_Y> slots) {
            if (this.u_1723_Y != null) {
                slots.accept(this);
            } else if (this.P_1922_E != null) {
                for (R_4764_Y stitcher$slot : this.P_1922_E) {
                    stitcher$slot.n_1700_B(slots);
                }
            }
        }

        public String toString() {
            return "Slot{originX=" + this.n_1700_B + ", originY=" + this.J_1907_R + ", width=" + this.R_4764_Y + ", height=" + this.G_564_y + ", texture=" + String.valueOf(this.u_1723_Y) + ", subSlots=" + String.valueOf(this.P_1922_E) + "}";
        }
    }

    public static interface J_1907_R {
        public void load(B_3871_I.n_1700_B var1, int var2, int var3, int var4, int var5);
    }
}


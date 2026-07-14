/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lightning.product.AnimationFrame;
import lightning.product.e_2501_q;

public class B_4830_U {
    public static final e_2501_q n_1700_B = new e_2501_q();
    public static final B_4830_U J_1907_R = new B_4830_U((List)Lists.newArrayList(), -1, -1, 1, false){

        @Override
        public Pair<Integer, Integer> n_1700_B(int widthIn, int heightIn) {
            return Pair.of((Object)widthIn, (Object)heightIn);
        }
    };
    private final List<AnimationFrame> R_4764_Y;
    private final int G_564_y;
    private final int P_1922_E;
    private final int u_1723_Y;
    private final boolean v_4262_N;

    public B_4830_U(List<AnimationFrame> animationFramesIn, int frameWidthIn, int frameHeightIn, int frameTimeIn, boolean interpolateIn) {
        this.R_4764_Y = animationFramesIn;
        this.G_564_y = frameWidthIn;
        this.P_1922_E = frameHeightIn;
        this.u_1723_Y = frameTimeIn;
        this.v_4262_N = interpolateIn;
    }

    private static boolean J_1907_R(int valMul, int val) {
        return valMul / val * val == valMul;
    }

    public Pair<Integer, Integer> n_1700_B(int widthIn, int heightIn) {
        Pair<Integer, Integer> pair = this.R_4764_Y(widthIn, heightIn);
        int i = (Integer)pair.getFirst();
        int j = (Integer)pair.getSecond();
        if (B_4830_U.J_1907_R(widthIn, i) && B_4830_U.J_1907_R(heightIn, j)) {
            return pair;
        }
        throw new IllegalArgumentException(String.format("Image size %s,%s is not multiply of frame size %s,%s", widthIn, heightIn, i, j));
    }

    private Pair<Integer, Integer> R_4764_Y(int defWidthIn, int defHeightIn) {
        if (this.G_564_y != -1) {
            return this.P_1922_E != -1 ? Pair.of((Object)this.G_564_y, (Object)this.P_1922_E) : Pair.of((Object)this.G_564_y, (Object)defHeightIn);
        }
        if (this.P_1922_E != -1) {
            return Pair.of((Object)defWidthIn, (Object)this.P_1922_E);
        }
        int i = Math.min(defWidthIn, defHeightIn);
        return Pair.of((Object)i, (Object)i);
    }

    public int n_1700_B(int defHeightIn) {
        return this.P_1922_E == -1 ? defHeightIn : this.P_1922_E;
    }

    public int J_1907_R(int defWidthIn) {
        return this.G_564_y == -1 ? defWidthIn : this.G_564_y;
    }

    public int n_1700_B() {
        return this.R_4764_Y.size();
    }

    public int J_1907_R() {
        return this.u_1723_Y;
    }

    public boolean R_4764_Y() {
        return this.v_4262_N;
    }

    private AnimationFrame P_1922_E(int frame) {
        return this.R_4764_Y.get(frame);
    }

    public int R_4764_Y(int frame) {
        AnimationFrame animationframe = this.P_1922_E(frame);
        return animationframe.n_1700_B() ? this.u_1723_Y : animationframe.J_1907_R();
    }

    public int G_564_y(int frame) {
        return this.R_4764_Y.get(frame).R_4764_Y();
    }

    public Set<Integer> G_564_y() {
        HashSet set = Sets.newHashSet();
        for (AnimationFrame animationframe : this.R_4764_Y) {
            set.add(animationframe.R_4764_Y());
        }
        return set;
    }
}



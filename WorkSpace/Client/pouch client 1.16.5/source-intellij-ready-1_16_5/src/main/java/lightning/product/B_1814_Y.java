/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.util.Either
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Either;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import lightning.product.B_3871_I;
import lightning.product.BlockElement;
import lightning.product.BlockElementFace;
import lightning.product.M_1336_P;
import lightning.product.T_2910_P;
import lightning.product.BlockFaceUV;
import lightning.product.b_257_Y;
import lightning.product.o_3047_I;

public class B_1814_Y {
    public static final List<String> n_1700_B = Lists.newArrayList((Object[])new String[]{"layer0", "layer1", "layer2", "layer3", "layer4"});

    public o_3047_I n_1700_B(Function<T_2910_P, B_3871_I> textureGetter, o_3047_I blockModelIn) {
        String s;
        HashMap map = Maps.newHashMap();
        ArrayList list = Lists.newArrayList();
        for (int i = 0; i < n_1700_B.size() && blockModelIn.J_1907_R(s = n_1700_B.get(i)); ++i) {
            T_2910_P rendermaterial = blockModelIn.R_4764_Y(s);
            map.put(s, Either.left((Object)rendermaterial));
            B_3871_I textureatlassprite = textureGetter.apply(rendermaterial);
            list.addAll(this.n_1700_B(i, s, textureatlassprite));
        }
        map.put("particle", blockModelIn.J_1907_R("particle") ? Either.left((Object)blockModelIn.R_4764_Y("particle")) : (Either)map.get("layer0"));
        o_3047_I blockmodel = new o_3047_I(null, list, map, false, blockModelIn.R_4764_Y(), blockModelIn.v_4262_N(), blockModelIn.G_564_y());
        blockmodel.J_1907_R = blockModelIn.J_1907_R;
        return blockmodel;
    }

    private List<BlockElement> n_1700_B(int tintIndex, String textureIn, B_3871_I spriteIn) {
        HashMap map = Maps.newHashMap();
        map.put(b_257_Y.G_564_y, new BlockElementFace(null, tintIndex, textureIn, new BlockFaceUV(new float[]{0.0f, 0.0f, 16.0f, 16.0f}, 0)));
        map.put(b_257_Y.R_4764_Y, new BlockElementFace(null, tintIndex, textureIn, new BlockFaceUV(new float[]{16.0f, 0.0f, 0.0f, 16.0f}, 0)));
        ArrayList list = Lists.newArrayList();
        list.add(new BlockElement(new M_1336_P(0.0f, 0.0f, 7.5f), new M_1336_P(16.0f, 16.0f, 8.5f), map, null, true));
        list.addAll(this.n_1700_B(spriteIn, textureIn, tintIndex));
        return list;
    }

    private List<BlockElement> n_1700_B(B_3871_I spriteIn, String textureIn, int tintIndexIn) {
        float f = spriteIn.G_564_y();
        float f1 = spriteIn.P_1922_E();
        ArrayList list = Lists.newArrayList();
        for (n_1700_B itemmodelgenerator$span : this.n_1700_B(spriteIn)) {
            float f2 = 0.0f;
            float f3 = 0.0f;
            float f4 = 0.0f;
            float f5 = 0.0f;
            float f6 = 0.0f;
            float f7 = 0.0f;
            float f8 = 0.0f;
            float f9 = 0.0f;
            float f10 = 16.0f / f;
            float f11 = 16.0f / f1;
            float f12 = itemmodelgenerator$span.J_1907_R();
            float f13 = itemmodelgenerator$span.R_4764_Y();
            float f14 = itemmodelgenerator$span.G_564_y();
            J_1907_R itemmodelgenerator$spanfacing = itemmodelgenerator$span.n_1700_B();
            switch (itemmodelgenerator$spanfacing.ordinal()) {
                case 0: {
                    f6 = f12;
                    f2 = f12;
                    f4 = f7 = f13 + 1.0f;
                    f8 = f14;
                    f3 = f14;
                    f5 = f14;
                    f9 = f14 + 1.0f;
                    break;
                }
                case 1: {
                    f8 = f14;
                    f9 = f14 + 1.0f;
                    f6 = f12;
                    f2 = f12;
                    f4 = f7 = f13 + 1.0f;
                    f3 = f14 + 1.0f;
                    f5 = f14 + 1.0f;
                    break;
                }
                case 2: {
                    f6 = f14;
                    f2 = f14;
                    f4 = f14;
                    f7 = f14 + 1.0f;
                    f9 = f12;
                    f3 = f12;
                    f5 = f8 = f13 + 1.0f;
                    break;
                }
                case 3: {
                    f6 = f14;
                    f7 = f14 + 1.0f;
                    f2 = f14 + 1.0f;
                    f4 = f14 + 1.0f;
                    f9 = f12;
                    f3 = f12;
                    f5 = f8 = f13 + 1.0f;
                }
            }
            f2 *= f10;
            f4 *= f10;
            f3 *= f11;
            f5 *= f11;
            f3 = 16.0f - f3;
            f5 = 16.0f - f5;
            HashMap map = Maps.newHashMap();
            map.put(itemmodelgenerator$spanfacing.n_1700_B(), new BlockElementFace(null, tintIndexIn, textureIn, new BlockFaceUV(new float[]{f6 *= f10, f8 *= f11, f7 *= f10, f9 *= f11}, 0)));
            switch (itemmodelgenerator$spanfacing.ordinal()) {
                case 0: {
                    list.add(new BlockElement(new M_1336_P(f2, f3, 7.5f), new M_1336_P(f4, f3, 8.5f), map, null, true));
                    break;
                }
                case 1: {
                    list.add(new BlockElement(new M_1336_P(f2, f5, 7.5f), new M_1336_P(f4, f5, 8.5f), map, null, true));
                    break;
                }
                case 2: {
                    list.add(new BlockElement(new M_1336_P(f2, f3, 7.5f), new M_1336_P(f2, f5, 8.5f), map, null, true));
                    break;
                }
                case 3: {
                    list.add(new BlockElement(new M_1336_P(f4, f3, 7.5f), new M_1336_P(f4, f5, 8.5f), map, null, true));
                }
            }
        }
        B_1814_Y.n_1700_B(list);
        return list;
    }

    private static void n_1700_B(List<BlockElement> parts) {
        float inc = (float)B_1814_Y.n_1700_B();
        float inc2 = (float)B_1814_Y.J_1907_R();
        for (BlockElement e : parts) {
            M_1336_P from = e.n_1700_B;
            M_1336_P to = e.J_1907_R;
            Set<b_257_Y> set = e.R_4764_Y.keySet();
            if (set.size() != 1) continue;
            b_257_Y dir = set.iterator().next();
            switch (dir) {
                case J_1907_R: {
                    from.J_1907_R(from.n_1700_B() - inc2, from.J_1907_R() - inc, from.R_4764_Y() - inc2);
                    to.J_1907_R(to.n_1700_B() + inc2, to.J_1907_R() - inc, to.R_4764_Y() + inc2);
                    break;
                }
                case n_1700_B: {
                    from.J_1907_R(from.n_1700_B() - inc2, from.J_1907_R() + inc, from.R_4764_Y() - inc2);
                    to.J_1907_R(to.n_1700_B() + inc2, to.J_1907_R() + inc, to.R_4764_Y() + inc2);
                    break;
                }
                case P_1922_E: {
                    from.J_1907_R(from.n_1700_B() - inc, from.J_1907_R() + inc2, from.R_4764_Y() - inc2);
                    to.J_1907_R(to.n_1700_B() - inc, to.J_1907_R() - inc2, to.R_4764_Y() + inc2);
                    break;
                }
                case u_1723_Y: {
                    from.J_1907_R(from.n_1700_B() + inc, from.J_1907_R() + inc2, from.R_4764_Y() - inc2);
                    to.J_1907_R(to.n_1700_B() + inc, to.J_1907_R() - inc2, to.R_4764_Y() + inc2);
                    break;
                }
            }
        }
    }

    public static double n_1700_B() {
        return 0.0;
    }

    public static double J_1907_R() {
        return 0.0;
    }

    private List<n_1700_B> n_1700_B(B_3871_I spriteIn) {
        int i = spriteIn.G_564_y();
        int j = spriteIn.P_1922_E();
        ArrayList list = Lists.newArrayList();
        for (int k = 0; k < spriteIn.M_588_G(); ++k) {
            for (int l = 0; l < j; ++l) {
                for (int i1 = 0; i1 < i; ++i1) {
                    boolean flag = !this.n_1700_B(spriteIn, k, i1, l, i, j);
                    this.n_1700_B(J_1907_R.n_1700_B, list, spriteIn, k, i1, l, i, j, flag);
                    this.n_1700_B(J_1907_R.J_1907_R, list, spriteIn, k, i1, l, i, j, flag);
                    this.n_1700_B(J_1907_R.R_4764_Y, list, spriteIn, k, i1, l, i, j, flag);
                    this.n_1700_B(J_1907_R.G_564_y, list, spriteIn, k, i1, l, i, j, flag);
                }
            }
        }
        return list;
    }

    private void n_1700_B(J_1907_R spanFacingIn, List<n_1700_B> listSpansIn, B_3871_I spriteIn, int frameIndex, int pixelX, int pixelY, int spiteWidth, int spriteHeight, boolean transparent) {
        boolean flag;
        boolean bl = flag = this.n_1700_B(spriteIn, frameIndex, pixelX + spanFacingIn.J_1907_R(), pixelY + spanFacingIn.R_4764_Y(), spiteWidth, spriteHeight) && transparent;
        if (flag) {
            B_1814_Y.n_1700_B(listSpansIn, spanFacingIn, pixelX, pixelY);
        }
    }

    public static void n_1700_B(List<n_1700_B> listSpans, J_1907_R spanFacing, int pixelX, int pixelY) {
        int length;
        n_1700_B existingSpan = null;
        for (n_1700_B span2 : listSpans) {
            int i;
            if (span2.n_1700_B() != spanFacing) continue;
            int n = i = spanFacing.G_564_y() ? pixelY : pixelX;
            if (span2.G_564_y() != i || B_1814_Y.J_1907_R() != 0.0 && span2.R_4764_Y() != (!spanFacing.G_564_y() ? pixelY : pixelX) - 1) continue;
            existingSpan = span2;
            break;
        }
        int n = length = spanFacing.G_564_y() ? pixelX : pixelY;
        if (existingSpan == null) {
            int newStart = spanFacing.G_564_y() ? pixelY : pixelX;
            listSpans.add(new n_1700_B(spanFacing, length, newStart));
        } else {
            existingSpan.n_1700_B(length);
        }
    }

    private boolean n_1700_B(B_3871_I spriteIn, int frameIndex, int pixelX, int pixelY, int spiteWidth, int spriteHeight) {
        return pixelX >= 0 && pixelY >= 0 && pixelX < spiteWidth && pixelY < spriteHeight ? spriteIn.n_1700_B(frameIndex, pixelX, pixelY) : true;
    }

    static class n_1700_B {
        private final J_1907_R n_1700_B;
        private int J_1907_R;
        private int R_4764_Y;
        private final int G_564_y;

        public n_1700_B(J_1907_R spanFacingIn, int minIn, int maxIn) {
            this.n_1700_B = spanFacingIn;
            this.J_1907_R = minIn;
            this.R_4764_Y = minIn;
            this.G_564_y = maxIn;
        }

        public void n_1700_B(int posIn) {
            if (posIn < this.J_1907_R) {
                this.J_1907_R = posIn;
            } else if (posIn > this.R_4764_Y) {
                this.R_4764_Y = posIn;
            }
        }

        public J_1907_R n_1700_B() {
            return this.n_1700_B;
        }

        public int J_1907_R() {
            return this.J_1907_R;
        }

        public int R_4764_Y() {
            return this.R_4764_Y;
        }

        public int G_564_y() {
            return this.G_564_y;
        }
    }

    static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R(b_257_Y.J_1907_R, 0, -1);
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R(b_257_Y.n_1700_B, 0, 1);
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R(b_257_Y.u_1723_Y, -1, 0);
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R(b_257_Y.P_1922_E, 1, 0);
        private final b_257_Y P_1922_E;
        private final int u_1723_Y;
        private final int v_4262_N;
        private static final /* synthetic */ J_1907_R[] w_1484_f;

        public static J_1907_R[] values() {
            return (J_1907_R[])w_1484_f.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(b_257_Y facing, int xOffsetIn, int yOffsetIn) {
            this.P_1922_E = facing;
            this.u_1723_Y = xOffsetIn;
            this.v_4262_N = yOffsetIn;
        }

        public b_257_Y n_1700_B() {
            return this.P_1922_E;
        }

        public int J_1907_R() {
            return this.u_1723_Y;
        }

        public int R_4764_Y() {
            return this.v_4262_N;
        }

        private boolean G_564_y() {
            return this == J_1907_R || this == n_1700_B;
        }

        private static /* synthetic */ J_1907_R[] P_1922_E() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            w_1484_f = lightning.product.B_1814_Y$J_1907_R.P_1922_E();
        }
    }
}



/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.T_335_n;
import lightning.product.PackResources;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import lightning.product.i_4221_J;
import lightning.product.j_3341_s;
import lightning.product.p_1429_o;

public class j_1086_C
implements PackResources {
    private static final Map<String, Pair<p_1429_o, g_2336_b>> G_564_y = j_3341_s.n_1700_B(Maps.newHashMap(), p_229288_0_ -> {
        p_229288_0_.put("textures/entity/chest/normal_left.png", new Pair((Object)p_1429_o.J_1907_R, (Object)new g_2336_b("textures/entity/chest/normal_double.png")));
        p_229288_0_.put("textures/entity/chest/normal_right.png", new Pair((Object)p_1429_o.R_4764_Y, (Object)new g_2336_b("textures/entity/chest/normal_double.png")));
        p_229288_0_.put("textures/entity/chest/normal.png", new Pair((Object)p_1429_o.n_1700_B, (Object)new g_2336_b("textures/entity/chest/normal.png")));
        p_229288_0_.put("textures/entity/chest/trapped_left.png", new Pair((Object)p_1429_o.J_1907_R, (Object)new g_2336_b("textures/entity/chest/trapped_double.png")));
        p_229288_0_.put("textures/entity/chest/trapped_right.png", new Pair((Object)p_1429_o.R_4764_Y, (Object)new g_2336_b("textures/entity/chest/trapped_double.png")));
        p_229288_0_.put("textures/entity/chest/trapped.png", new Pair((Object)p_1429_o.n_1700_B, (Object)new g_2336_b("textures/entity/chest/trapped.png")));
        p_229288_0_.put("textures/entity/chest/christmas_left.png", new Pair((Object)p_1429_o.J_1907_R, (Object)new g_2336_b("textures/entity/chest/christmas_double.png")));
        p_229288_0_.put("textures/entity/chest/christmas_right.png", new Pair((Object)p_1429_o.R_4764_Y, (Object)new g_2336_b("textures/entity/chest/christmas_double.png")));
        p_229288_0_.put("textures/entity/chest/christmas.png", new Pair((Object)p_1429_o.n_1700_B, (Object)new g_2336_b("textures/entity/chest/christmas.png")));
        p_229288_0_.put("textures/entity/chest/ender.png", new Pair((Object)p_1429_o.n_1700_B, (Object)new g_2336_b("textures/entity/chest/ender.png")));
    });
    private static final List<String> P_1922_E = Lists.newArrayList((Object[])new String[]{"base", "border", "bricks", "circle", "creeper", "cross", "curly_border", "diagonal_left", "diagonal_right", "diagonal_up_left", "diagonal_up_right", "flower", "globe", "gradient", "gradient_up", "half_horizontal", "half_horizontal_bottom", "half_vertical", "half_vertical_right", "mojang", "rhombus", "skull", "small_stripes", "square_bottom_left", "square_bottom_right", "square_top_left", "square_top_right", "straight_cross", "stripe_bottom", "stripe_center", "stripe_downleft", "stripe_downright", "stripe_left", "stripe_middle", "stripe_right", "stripe_top", "triangle_bottom", "triangle_top", "triangles_bottom", "triangles_top"});
    private static final Set<String> u_1723_Y = P_1922_E.stream().map(p_229291_0_ -> "textures/entity/shield/" + p_229291_0_ + ".png").collect(Collectors.toSet());
    private static final Set<String> v_4262_N = P_1922_E.stream().map(p_229287_0_ -> "textures/entity/banner/" + p_229287_0_ + ".png").collect(Collectors.toSet());
    public static final g_2336_b n_1700_B = new g_2336_b("textures/entity/shield_base.png");
    public static final g_2336_b J_1907_R = new g_2336_b("textures/entity/banner_base.png");
    public static final g_2336_b R_4764_Y = new g_2336_b("textures/entity/iron_golem.png");
    private final PackResources w_1484_f;

    public j_1086_C(PackResources p_i226053_1_) {
        this.w_1484_f = p_i226053_1_;
    }

    @Override
    public InputStream getRootResourceStream(String fileName) throws IOException {
        return this.w_1484_f.getRootResourceStream(fileName);
    }

    @Override
    public boolean resourceExists(i_4221_J type, g_2336_b location) {
        if (!"minecraft".equals(location.R_4764_Y())) {
            return this.w_1484_f.resourceExists(type, location);
        }
        String s = location.J_1907_R();
        if ("textures/misc/enchanted_item_glint.png".equals(s)) {
            return false;
        }
        if ("textures/entity/iron_golem/iron_golem.png".equals(s)) {
            return this.w_1484_f.resourceExists(type, R_4764_Y);
        }
        if (!"textures/entity/conduit/wind.png".equals(s) && !"textures/entity/conduit/wind_vertical.png".equals(s)) {
            if (u_1723_Y.contains(s)) {
                return this.w_1484_f.resourceExists(type, n_1700_B) && this.w_1484_f.resourceExists(type, location);
            }
            if (!v_4262_N.contains(s)) {
                Pair<p_1429_o, g_2336_b> pair = G_564_y.get(s);
                return pair != null && this.w_1484_f.resourceExists(type, (g_2336_b)pair.getSecond()) ? true : this.w_1484_f.resourceExists(type, location);
            }
            return this.w_1484_f.resourceExists(type, J_1907_R) && this.w_1484_f.resourceExists(type, location);
        }
        return false;
    }

    @Override
    public InputStream getResourceStream(i_4221_J type, g_2336_b location) throws IOException {
        if (!"minecraft".equals(location.R_4764_Y())) {
            return this.w_1484_f.getResourceStream(type, location);
        }
        String s = location.J_1907_R();
        if ("textures/entity/iron_golem/iron_golem.png".equals(s)) {
            return this.w_1484_f.getResourceStream(type, R_4764_Y);
        }
        if (u_1723_Y.contains(s)) {
            InputStream inputstream2 = j_1086_C.n_1700_B(this.w_1484_f.getResourceStream(type, n_1700_B), this.w_1484_f.getResourceStream(type, location), 64, 2, 2, 12, 22);
            if (inputstream2 != null) {
                return inputstream2;
            }
        } else if (v_4262_N.contains(s)) {
            InputStream inputstream1 = j_1086_C.n_1700_B(this.w_1484_f.getResourceStream(type, J_1907_R), this.w_1484_f.getResourceStream(type, location), 64, 0, 0, 42, 41);
            if (inputstream1 != null) {
                return inputstream1;
            }
        } else {
            if ("textures/entity/enderdragon/dragon.png".equals(s) || "textures/entity/enderdragon/dragon_exploding.png".equals(s)) {
                ByteArrayInputStream bytearrayinputstream;
                try (i_2518_W nativeimage = i_2518_W.n_1700_B(this.w_1484_f.getResourceStream(type, location));){
                    int k = nativeimage.n_1700_B() / 256;
                    for (int i = 88 * k; i < 200 * k; ++i) {
                        for (int j = 56 * k; j < 112 * k; ++j) {
                            nativeimage.n_1700_B(j, i, 0);
                        }
                    }
                    bytearrayinputstream = new ByteArrayInputStream(nativeimage.P_1922_E());
                }
                return bytearrayinputstream;
            }
            if ("textures/entity/conduit/closed_eye.png".equals(s) || "textures/entity/conduit/open_eye.png".equals(s)) {
                return j_1086_C.n_1700_B(this.w_1484_f.getResourceStream(type, location));
            }
            Pair<p_1429_o, g_2336_b> pair = G_564_y.get(s);
            if (pair != null) {
                p_1429_o chesttype = (p_1429_o)pair.getFirst();
                InputStream inputstream = this.w_1484_f.getResourceStream(type, (g_2336_b)pair.getSecond());
                if (chesttype == p_1429_o.n_1700_B) {
                    return j_1086_C.G_564_y(inputstream);
                }
                if (chesttype == p_1429_o.J_1907_R) {
                    return j_1086_C.J_1907_R(inputstream);
                }
                if (chesttype == p_1429_o.R_4764_Y) {
                    return j_1086_C.R_4764_Y(inputstream);
                }
            }
        }
        return this.w_1484_f.getResourceStream(type, location);
    }

    @Nullable
    public static InputStream n_1700_B(InputStream p_229286_0_, InputStream p_229286_1_, int p_229286_2_, int p_229286_3_, int p_229286_4_, int p_229286_5_, int p_229286_6_) throws IOException {
        ByteArrayInputStream bytearrayinputstream;
        try (i_2518_W nativeimage1 = i_2518_W.n_1700_B(p_229286_1_);
             i_2518_W nativeimage = i_2518_W.n_1700_B(p_229286_0_);){
            int i = nativeimage.n_1700_B();
            int j = nativeimage.J_1907_R();
            if (i != nativeimage1.n_1700_B() || j != nativeimage1.J_1907_R()) {
                InputStream inputStream = null;
                return inputStream;
            }
            try (i_2518_W nativeimage2 = new i_2518_W(i, j, true);){
                int k = i / p_229286_2_;
                for (int l = p_229286_4_ * k; l < p_229286_6_ * k; ++l) {
                    for (int i1 = p_229286_3_ * k; i1 < p_229286_5_ * k; ++i1) {
                        int j1 = i_2518_W.J_1907_R(nativeimage1.n_1700_B(i1, l));
                        int k1 = nativeimage.n_1700_B(i1, l);
                        nativeimage2.n_1700_B(i1, l, i_2518_W.n_1700_B(j1, i_2518_W.G_564_y(k1), i_2518_W.R_4764_Y(k1), i_2518_W.J_1907_R(k1)));
                    }
                }
                bytearrayinputstream = new ByteArrayInputStream(nativeimage2.P_1922_E());
            }
        }
        return bytearrayinputstream;
    }

    public static InputStream n_1700_B(InputStream p_229285_0_) throws IOException {
        ByteArrayInputStream bytearrayinputstream;
        try (i_2518_W nativeimage = i_2518_W.n_1700_B(p_229285_0_);){
            int i = nativeimage.n_1700_B();
            int j = nativeimage.J_1907_R();
            try (i_2518_W nativeimage1 = new i_2518_W(2 * i, 2 * j, true);){
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 0, 0, 0, 0, i, j, 1, false, false);
                bytearrayinputstream = new ByteArrayInputStream(nativeimage1.P_1922_E());
            }
        }
        return bytearrayinputstream;
    }

    public static InputStream J_1907_R(InputStream p_229289_0_) throws IOException {
        ByteArrayInputStream bytearrayinputstream;
        try (i_2518_W nativeimage = i_2518_W.n_1700_B(p_229289_0_);){
            int i = nativeimage.n_1700_B();
            int j = nativeimage.J_1907_R();
            try (i_2518_W nativeimage1 = new i_2518_W(i / 2, j, true);){
                int k = j / 64;
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 29, 0, 29, 0, 15, 14, k, false, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 59, 0, 14, 0, 15, 14, k, false, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 29, 14, 43, 14, 15, 5, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 44, 14, 29, 14, 14, 5, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 58, 14, 14, 14, 15, 5, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 29, 19, 29, 19, 15, 14, k, false, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 59, 19, 14, 19, 15, 14, k, false, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 29, 33, 43, 33, 15, 10, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 44, 33, 29, 33, 14, 10, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 58, 33, 14, 33, 15, 10, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 2, 0, 2, 0, 1, 1, k, false, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 4, 0, 1, 0, 1, 1, k, false, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 2, 1, 3, 1, 1, 4, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 3, 1, 2, 1, 1, 4, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 4, 1, 1, 1, 1, 4, k, true, true);
                bytearrayinputstream = new ByteArrayInputStream(nativeimage1.P_1922_E());
            }
        }
        return bytearrayinputstream;
    }

    public static InputStream R_4764_Y(InputStream p_229290_0_) throws IOException {
        ByteArrayInputStream bytearrayinputstream;
        try (i_2518_W nativeimage = i_2518_W.n_1700_B(p_229290_0_);){
            int i = nativeimage.n_1700_B();
            int j = nativeimage.J_1907_R();
            try (i_2518_W nativeimage1 = new i_2518_W(i / 2, j, true);){
                int k = j / 64;
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 14, 0, 29, 0, 15, 14, k, false, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 44, 0, 14, 0, 15, 14, k, false, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 0, 14, 0, 14, 14, 5, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 14, 14, 43, 14, 15, 5, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 73, 14, 14, 14, 15, 5, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 14, 19, 29, 19, 15, 14, k, false, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 44, 19, 14, 19, 15, 14, k, false, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 0, 33, 0, 33, 14, 10, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 14, 33, 43, 33, 15, 10, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 73, 33, 14, 33, 15, 10, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 1, 0, 2, 0, 1, 1, k, false, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 3, 0, 1, 0, 1, 1, k, false, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 0, 1, 0, 1, 1, 4, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 1, 1, 3, 1, 1, 4, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 5, 1, 1, 1, 1, 4, k, true, true);
                bytearrayinputstream = new ByteArrayInputStream(nativeimage1.P_1922_E());
            }
        }
        return bytearrayinputstream;
    }

    public static InputStream G_564_y(InputStream p_229292_0_) throws IOException {
        ByteArrayInputStream bytearrayinputstream;
        try (i_2518_W nativeimage = i_2518_W.n_1700_B(p_229292_0_);){
            int i = nativeimage.n_1700_B();
            int j = nativeimage.J_1907_R();
            try (i_2518_W nativeimage1 = new i_2518_W(i, j, true);){
                int k = j / 64;
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 14, 0, 28, 0, 14, 14, k, false, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 28, 0, 14, 0, 14, 14, k, false, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 0, 14, 0, 14, 14, 5, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 14, 14, 42, 14, 14, 5, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 28, 14, 28, 14, 14, 5, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 42, 14, 14, 14, 14, 5, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 14, 19, 28, 19, 14, 14, k, false, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 28, 19, 14, 19, 14, 14, k, false, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 0, 33, 0, 33, 14, 10, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 14, 33, 42, 33, 14, 10, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 28, 33, 28, 33, 14, 10, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 42, 33, 14, 33, 14, 10, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 1, 0, 3, 0, 2, 1, k, false, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 3, 0, 1, 0, 2, 1, k, false, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 0, 1, 0, 1, 1, 4, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 1, 1, 4, 1, 2, 4, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 3, 1, 3, 1, 1, 4, k, true, true);
                j_1086_C.n_1700_B(nativeimage, nativeimage1, 4, 1, 1, 1, 2, 4, k, true, true);
                bytearrayinputstream = new ByteArrayInputStream(nativeimage1.P_1922_E());
            }
        }
        return bytearrayinputstream;
    }

    @Override
    public Collection<g_2336_b> getAllResourceLocations(i_4221_J type, String namespaceIn, String pathIn, int maxDepthIn, Predicate<String> filterIn) {
        return this.w_1484_f.getAllResourceLocations(type, namespaceIn, pathIn, maxDepthIn, filterIn);
    }

    @Override
    public Set<String> getResourceNamespaces(i_4221_J type) {
        return this.w_1484_f.getResourceNamespaces(type);
    }

    @Override
    @Nullable
    public <T> T getMetadata(T_335_n<T> deserializer) throws IOException {
        return this.w_1484_f.getMetadata(deserializer);
    }

    @Override
    public String getName() {
        return this.w_1484_f.getName();
    }

    @Override
    public void close() {
        this.w_1484_f.close();
    }

    private static void n_1700_B(i_2518_W p_229284_0_, i_2518_W p_229284_1_, int p_229284_2_, int p_229284_3_, int p_229284_4_, int p_229284_5_, int p_229284_6_, int p_229284_7_, int p_229284_8_, boolean p_229284_9_, boolean p_229284_10_) {
        p_229284_7_ *= p_229284_8_;
        p_229284_6_ *= p_229284_8_;
        p_229284_4_ *= p_229284_8_;
        p_229284_5_ *= p_229284_8_;
        p_229284_2_ *= p_229284_8_;
        p_229284_3_ *= p_229284_8_;
        for (int i = 0; i < p_229284_7_; ++i) {
            for (int j = 0; j < p_229284_6_; ++j) {
                p_229284_1_.n_1700_B(p_229284_4_ + j, p_229284_5_ + i, p_229284_0_.n_1700_B(p_229284_2_ + (p_229284_9_ ? p_229284_6_ - 1 - j : j), p_229284_3_ + (p_229284_10_ ? p_229284_7_ - 1 - i : i)));
            }
        }
    }
}



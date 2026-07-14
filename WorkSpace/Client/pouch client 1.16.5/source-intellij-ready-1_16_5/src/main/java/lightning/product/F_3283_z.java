/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.ints.IntArrayList
 *  it.unimi.dsi.fastutil.ints.IntCollection
 *  it.unimi.dsi.fastutil.ints.IntList
 *  it.unimi.dsi.fastutil.ints.IntOpenHashSet
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import lightning.product.C_3240_x;
import lightning.product.J_2133_L;
import lightning.product.c_4447_Z;
import lightning.product.e_3495_r;
import lightning.product.FontTexture;
import lightning.product.g_2336_b;
import lightning.product.r_2343_c;
import lightning.product.s_3940_w;
import lightning.product.EmptyGlyph;
import lightning.product.u_530_F;
import lightning.product.y_3462_t;

public class F_3283_z
implements AutoCloseable {
    private static final EmptyGlyph n_1700_B = new EmptyGlyph();
    private static final y_3462_t J_1907_R = () -> 4.0f;
    private static final Random R_4764_Y = new Random();
    private final C_3240_x G_564_y;
    private final g_2336_b P_1922_E;
    private J_2133_L u_1723_Y;
    private J_2133_L v_4262_N;
    private final List<e_3495_r> w_1484_f = Lists.newArrayList();
    private final Int2ObjectMap<J_2133_L> t_148_a = new Int2ObjectOpenHashMap();
    private final Int2ObjectMap<y_3462_t> s_956_w = new Int2ObjectOpenHashMap();
    private final Int2ObjectMap<IntList> u_2550_I = new Int2ObjectOpenHashMap();
    private final List<FontTexture> M_588_G = Lists.newArrayList();

    public F_3283_z(C_3240_x textureManagerIn, g_2336_b resourceLocationIn) {
        this.G_564_y = textureManagerIn;
        this.P_1922_E = resourceLocationIn;
    }

    public void n_1700_B(List<e_3495_r> glyphProvidersIn) {
        this.J_1907_R();
        this.R_4764_Y();
        this.t_148_a.clear();
        this.s_956_w.clear();
        this.u_2550_I.clear();
        this.u_1723_Y = this.n_1700_B(c_4447_Z.n_1700_B);
        this.v_4262_N = this.n_1700_B(r_2343_c.n_1700_B);
        IntOpenHashSet intset = new IntOpenHashSet();
        for (e_3495_r iglyphprovider : glyphProvidersIn) {
            intset.addAll((IntCollection)iglyphprovider.n_1700_B());
        }
        HashSet set = Sets.newHashSet();
        intset.forEach(p_lambda$setGlyphProviders$2_3_ -> {
            for (e_3495_r iglyphprovider1 : glyphProvidersIn) {
                y_3462_t iglyph = p_lambda$setGlyphProviders$2_3_ == 32 ? J_1907_R : iglyphprovider1.n_1700_B(p_lambda$setGlyphProviders$2_3_);
                if (iglyph == null) continue;
                set.add(iglyphprovider1);
                if (iglyph == c_4447_Z.n_1700_B) break;
                ((IntList)this.u_2550_I.computeIfAbsent(u_530_F.u_1723_Y(iglyph.n_1700_B(false)), p_lambda$null$1_0_ -> new IntArrayList())).add(p_lambda$setGlyphProviders$2_3_);
                break;
            }
        });
        glyphProvidersIn.stream().filter(set::contains).forEach(this.w_1484_f::add);
    }

    @Override
    public void close() {
        this.J_1907_R();
        this.R_4764_Y();
    }

    private void J_1907_R() {
        for (e_3495_r iglyphprovider : this.w_1484_f) {
            iglyphprovider.close();
        }
        this.w_1484_f.clear();
    }

    private void R_4764_Y() {
        for (FontTexture fonttexture : this.M_588_G) {
            fonttexture.close();
        }
        this.M_588_G.clear();
    }

    public y_3462_t n_1700_B(int p_238557_1_) {
        y_3462_t iglyph = (y_3462_t)this.s_956_w.get(p_238557_1_);
        if (iglyph == null) {
            iglyph = p_238557_1_ == 32 ? J_1907_R : this.R_4764_Y(p_238557_1_);
            this.s_956_w.put(p_238557_1_, (Object)iglyph);
        }
        return iglyph;
    }

    private s_3940_w R_4764_Y(int p_212455_1_) {
        for (e_3495_r iglyphprovider : this.w_1484_f) {
            s_3940_w iglyphinfo = iglyphprovider.n_1700_B(p_212455_1_);
            if (iglyphinfo == null) continue;
            return iglyphinfo;
        }
        return c_4447_Z.n_1700_B;
    }

    public J_2133_L J_1907_R(int p_238559_1_) {
        J_2133_L texturedglyph = (J_2133_L)this.t_148_a.get(p_238559_1_);
        if (texturedglyph == null) {
            texturedglyph = p_238559_1_ == 32 ? n_1700_B : this.n_1700_B(this.R_4764_Y(p_238559_1_));
            this.t_148_a.put(p_238559_1_, (Object)texturedglyph);
        }
        return texturedglyph;
    }

    private J_2133_L n_1700_B(s_3940_w glyphInfoIn) {
        for (FontTexture fonttexture : this.M_588_G) {
            J_2133_L texturedglyph = fonttexture.n_1700_B(glyphInfoIn);
            if (texturedglyph == null) continue;
            return texturedglyph;
        }
        FontTexture fonttexture1 = new FontTexture(new g_2336_b(this.P_1922_E.R_4764_Y(), this.P_1922_E.J_1907_R() + "/" + this.M_588_G.size()), glyphInfoIn.G_564_y());
        this.M_588_G.add(fonttexture1);
        this.G_564_y.n_1700_B(fonttexture1.n_1700_B(), fonttexture1);
        J_2133_L texturedglyph1 = fonttexture1.n_1700_B(glyphInfoIn);
        return texturedglyph1 == null ? this.u_1723_Y : texturedglyph1;
    }

    public J_2133_L n_1700_B(y_3462_t glyph) {
        IntList intlist = (IntList)this.u_2550_I.get(u_530_F.u_1723_Y(glyph.n_1700_B(false)));
        return intlist != null && !intlist.isEmpty() ? this.J_1907_R(intlist.getInt(R_4764_Y.nextInt(intlist.size()))) : this.u_1723_Y;
    }

    public J_2133_L n_1700_B() {
        return this.v_4262_N;
    }
}



/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.ints.IntSet
 *  it.unimi.dsi.fastutil.ints.IntSets
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.ints.IntSets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.annotation.Nullable;
import lightning.product.Resource;
import lightning.product.ResourceManager;
import lightning.product.e_3495_r;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import lightning.product.i_4431_W;
import lightning.product.n_4006_Y;
import lightning.product.s_3940_w;
import net.optifine.util.FontUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class o_525_o
implements e_3495_r {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final i_2518_W J_1907_R;
    private final Int2ObjectMap<J_1907_R> R_4764_Y;
    private boolean G_564_y = false;
    private float P_1922_E = -1.0f;

    private o_525_o(i_2518_W p_i232266_1_, Int2ObjectMap<J_1907_R> p_i232266_2_) {
        this.J_1907_R = p_i232266_1_;
        this.R_4764_Y = p_i232266_2_;
    }

    @Override
    public void close() {
        this.J_1907_R.close();
    }

    @Override
    @Nullable
    public s_3940_w n_1700_B(int character) {
        return (s_3940_w)this.R_4764_Y.get(character);
    }

    @Override
    public IntSet n_1700_B() {
        return IntSets.unmodifiable((IntSet)this.R_4764_Y.keySet());
    }

    public boolean J_1907_R() {
        return this.G_564_y;
    }

    public float R_4764_Y() {
        return this.P_1922_E;
    }

    static final class J_1907_R
    implements s_3940_w {
        private final float n_1700_B;
        private final i_2518_W J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;
        private final int P_1922_E;
        private final int u_1723_Y;
        private final int v_4262_N;
        private final int w_1484_f;
        private float t_148_a = 1.0f;

        private J_1907_R(float p_i49748_1_, i_2518_W p_i49748_2_, int p_i49748_3_, int p_i49748_4_, int p_i49748_5_, int p_i49748_6_, int p_i49748_7_, int p_i49748_8_) {
            this.n_1700_B = p_i49748_1_;
            this.J_1907_R = p_i49748_2_;
            this.R_4764_Y = p_i49748_3_;
            this.G_564_y = p_i49748_4_;
            this.P_1922_E = p_i49748_5_;
            this.u_1723_Y = p_i49748_6_;
            this.v_4262_N = p_i49748_7_;
            this.w_1484_f = p_i49748_8_;
        }

        @Override
        public float R_4764_Y() {
            return 1.0f / this.n_1700_B;
        }

        @Override
        public int n_1700_B() {
            return this.P_1922_E;
        }

        @Override
        public int J_1907_R() {
            return this.u_1723_Y;
        }

        @Override
        public float getAdvance() {
            return this.v_4262_N;
        }

        @Override
        public float M_588_G() {
            return s_3940_w.super.M_588_G() + 7.0f - (float)this.w_1484_f;
        }

        @Override
        public void n_1700_B(int xOffset, int yOffset) {
            this.J_1907_R.n_1700_B(0, xOffset, yOffset, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, false, false);
        }

        @Override
        public boolean G_564_y() {
            return this.J_1907_R.R_4764_Y().n_1700_B() > 1;
        }

        @Override
        public float u_1723_Y() {
            return this.t_148_a;
        }
    }

    public static class n_1700_B
    implements n_4006_Y {
        private g_2336_b n_1700_B;
        private final List<int[]> J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;

        public n_1700_B(g_2336_b textureLocationIn, int heightIn, int ascentIn, List<int[]> listCharRowsIn) {
            this.n_1700_B = new g_2336_b(textureLocationIn.R_4764_Y(), "textures/" + textureLocationIn.J_1907_R());
            this.n_1700_B = FontUtils.getHdFontLocation(this.n_1700_B);
            this.J_1907_R = listCharRowsIn;
            this.R_4764_Y = heightIn;
            this.G_564_y = ascentIn;
        }

        public static n_1700_B n_1700_B(JsonObject jsonIn) {
            int i = i_4431_W.n_1700_B(jsonIn, "height", 8);
            int j = i_4431_W.u_2550_I(jsonIn, "ascent");
            if (j > i) {
                throw new JsonParseException("Ascent " + j + " higher than height " + i);
            }
            ArrayList list = Lists.newArrayList();
            JsonArray jsonarray = i_4431_W.P_4830_p(jsonIn, "chars");
            for (int k = 0; k < jsonarray.size(); ++k) {
                int l;
                String s = i_4431_W.n_1700_B(jsonarray.get(k), "chars[" + k + "]");
                int[] aint = s.codePoints().toArray();
                if (k > 0 && aint.length != (l = ((int[])list.get(0)).length)) {
                    throw new JsonParseException("Elements of chars have to be the same length (found: " + aint.length + ", expected: " + l + "), pad with space or \\u0000");
                }
                list.add(aint);
            }
            if (!list.isEmpty() && ((int[])list.get(0)).length != 0) {
                return new n_1700_B(new g_2336_b(i_4431_W.u_1723_Y(jsonIn, "file")), i, j, list);
            }
            throw new JsonParseException("Expected to find data in chars, found none.");
        }

        @Override
        @Nullable
        public e_3495_r n_1700_B(ResourceManager resourceManagerIn) {
            o_525_o o_525_o2;
            block14: {
                Resource iresource = resourceManagerIn.n_1700_B(this.n_1700_B);
                try {
                    i_2518_W nativeimage = i_2518_W.n_1700_B(i_2518_W.n_1700_B.n_1700_B, iresource.J_1907_R());
                    int i = nativeimage.n_1700_B();
                    int j = nativeimage.J_1907_R();
                    int k = i / this.J_1907_R.get(0).length;
                    int l = j / this.J_1907_R.size();
                    float f = (float)this.R_4764_Y / (float)l;
                    Int2ObjectOpenHashMap int2objectmap = new Int2ObjectOpenHashMap();
                    Properties properties = FontUtils.readFontProperties(this.n_1700_B);
                    Int2ObjectMap<Float> int2objectmap1 = FontUtils.readCustomCharWidths(properties);
                    Float f1 = (Float)int2objectmap1.get(32);
                    boolean flag = FontUtils.readBoolean(properties, "blend", false);
                    float f2 = FontUtils.readFloat(properties, "offsetBold", -1.0f);
                    if (f2 < 0.0f) {
                        f2 = k > 8 ? 0.5f : 1.0f;
                    }
                    for (int i1 = 0; i1 < this.J_1907_R.size(); ++i1) {
                        int j1 = 0;
                        for (int k1 : this.J_1907_R.get(i1)) {
                            J_1907_R textureglyphprovider$glyphinfo;
                            int l1 = j1++;
                            if (k1 == 0 || k1 == 32) continue;
                            float f3 = this.n_1700_B(nativeimage, k, l, l1, i1);
                            Float f4 = (Float)int2objectmap1.get(k1);
                            if (f4 != null) {
                                f3 = f4.floatValue() * ((float)k / 8.0f);
                            }
                            if ((textureglyphprovider$glyphinfo = (J_1907_R)int2objectmap.put(k1, (Object)new J_1907_R(f, nativeimage, l1 * k, i1 * l, k, l, (int)(0.5 + (double)(f3 * f)) + 1, this.G_564_y))) != null) {
                                n_1700_B.warn("Codepoint '{}' declared multiple times in {}", (Object)Integer.toHexString(k1), (Object)this.n_1700_B);
                            }
                            J_1907_R textureglyphprovider$glyphinfo1 = (J_1907_R)int2objectmap.get(k1);
                            textureglyphprovider$glyphinfo1.t_148_a = f2;
                        }
                    }
                    o_525_o textureglyphprovider = new o_525_o(nativeimage, (Int2ObjectMap<J_1907_R>)int2objectmap);
                    textureglyphprovider.G_564_y = flag;
                    if (f1 != null) {
                        textureglyphprovider.P_1922_E = f1.floatValue();
                    }
                    o_525_o2 = textureglyphprovider;
                    if (iresource == null) break block14;
                }
                catch (Throwable throwable) {
                    try {
                        if (iresource != null) {
                            try {
                                iresource.close();
                            }
                            catch (Throwable throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                        }
                        throw throwable;
                    }
                    catch (IOException ioexception1) {
                        throw new RuntimeException(ioexception1.getMessage());
                    }
                }
                iresource.close();
            }
            return o_525_o2;
        }

        private int n_1700_B(i_2518_W nativeImageIn, int charWidthIn, int charHeightInsp, int columnIn, int rowIn) {
            int i;
            for (i = charWidthIn - 1; i >= 0; --i) {
                int j = columnIn * charWidthIn + i;
                for (int k = 0; k < charHeightInsp; ++k) {
                    int l = rowIn * charHeightInsp + k;
                    if ((nativeImageIn.J_1907_R(j, l) & 0xFF) <= 16) continue;
                    return i + 1;
                }
            }
            return i + 1;
        }
    }
}



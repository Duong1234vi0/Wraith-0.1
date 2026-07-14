/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonObject
 *  it.unimi.dsi.fastutil.ints.IntOpenHashSet
 *  it.unimi.dsi.fastutil.ints.IntSet
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.Resource;
import lightning.product.ResourceManager;
import lightning.product.MinecraftClient;
import lightning.product.e_3495_r;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import lightning.product.i_4431_W;
import lightning.product.n_4006_Y;
import lightning.product.s_3940_w;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class K_1311_s
implements e_3495_r {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final ResourceManager J_1907_R;
    private final byte[] R_4764_Y;
    private final String G_564_y;
    private final Map<g_2336_b, i_2518_W> P_1922_E = Maps.newHashMap();

    public K_1311_s(ResourceManager p_i49737_1_, byte[] p_i49737_2_, String p_i49737_3_) {
        this.J_1907_R = p_i49737_1_;
        this.R_4764_Y = p_i49737_2_;
        this.G_564_y = p_i49737_3_;
        for (int i = 0; i < 256; ++i) {
            int j = i * 256;
            g_2336_b resourcelocation = this.J_1907_R(j);
            try (Resource iresource = this.J_1907_R.n_1700_B(resourcelocation);
                 i_2518_W nativeimage = i_2518_W.n_1700_B(i_2518_W.n_1700_B.n_1700_B, iresource.J_1907_R());){
                if (nativeimage.n_1700_B() == 256 && nativeimage.J_1907_R() == 256) {
                    for (int k = 0; k < 256; ++k) {
                        byte b0 = p_i49737_2_[j + k];
                        if (b0 == 0 || K_1311_s.n_1700_B(b0) <= K_1311_s.J_1907_R(b0)) continue;
                        p_i49737_2_[j + k] = 0;
                    }
                    continue;
                }
            }
            catch (IOException iOException) {
                // empty catch block
            }
            Arrays.fill(p_i49737_2_, j, j + 256, (byte)0);
        }
    }

    @Override
    public void close() {
        this.P_1922_E.values().forEach(i_2518_W::close);
    }

    private g_2336_b J_1907_R(int p_238591_1_) {
        g_2336_b resourcelocation = new g_2336_b(String.format(this.G_564_y, String.format("%02x", p_238591_1_ / 256)));
        return new g_2336_b(resourcelocation.R_4764_Y(), "textures/" + resourcelocation.J_1907_R());
    }

    @Override
    @Nullable
    public s_3940_w n_1700_B(int character) {
        if (character >= 0 && character <= 65535) {
            i_2518_W nativeimage;
            byte b0 = this.R_4764_Y[character];
            if (b0 != 0 && (nativeimage = this.P_1922_E.computeIfAbsent(this.J_1907_R(character), this::n_1700_B)) != null) {
                int i = K_1311_s.n_1700_B(b0);
                return new J_1907_R(character % 16 * 16 + i, (character & 0xFF) / 16 * 16, K_1311_s.J_1907_R(b0) - i, 16, nativeimage);
            }
            return null;
        }
        return null;
    }

    @Override
    public IntSet n_1700_B() {
        IntOpenHashSet intset = new IntOpenHashSet();
        for (int i = 0; i < 65535; ++i) {
            if (this.R_4764_Y[i] == 0) continue;
            intset.add(i);
        }
        return intset;
    }

    @Nullable
    private i_2518_W n_1700_B(g_2336_b p_211255_1_) {
        i_2518_W i_2518_W2;
        block8: {
            Resource iresource = this.J_1907_R.n_1700_B(p_211255_1_);
            try {
                i_2518_W2 = i_2518_W.n_1700_B(i_2518_W.n_1700_B.n_1700_B, iresource.J_1907_R());
                if (iresource == null) break block8;
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
                catch (IOException ioexception) {
                    n_1700_B.error("Couldn't load texture {}", (Object)p_211255_1_, (Object)ioexception);
                    return null;
                }
            }
            iresource.close();
        }
        return i_2518_W2;
    }

    private static int n_1700_B(byte p_212453_0_) {
        return p_212453_0_ >> 4 & 0xF;
    }

    private static int J_1907_R(byte p_212454_0_) {
        return (p_212454_0_ & 0xF) + 1;
    }

    static class J_1907_R
    implements s_3940_w {
        private final int n_1700_B;
        private final int J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;
        private final i_2518_W P_1922_E;

        private J_1907_R(int p_i49758_1_, int p_i49758_2_, int p_i49758_3_, int p_i49758_4_, i_2518_W p_i49758_5_) {
            this.n_1700_B = p_i49758_3_;
            this.J_1907_R = p_i49758_4_;
            this.R_4764_Y = p_i49758_1_;
            this.G_564_y = p_i49758_2_;
            this.P_1922_E = p_i49758_5_;
        }

        @Override
        public float R_4764_Y() {
            return 2.0f;
        }

        @Override
        public int n_1700_B() {
            return this.n_1700_B;
        }

        @Override
        public int J_1907_R() {
            return this.J_1907_R;
        }

        @Override
        public float getAdvance() {
            return this.n_1700_B / 2 + 1;
        }

        @Override
        public void n_1700_B(int xOffset, int yOffset) {
            this.P_1922_E.n_1700_B(0, xOffset, yOffset, this.R_4764_Y, this.G_564_y, this.n_1700_B, this.J_1907_R, false, false);
        }

        @Override
        public boolean G_564_y() {
            return this.P_1922_E.R_4764_Y().n_1700_B() > 1;
        }

        @Override
        public float v_4262_N() {
            return 0.5f;
        }

        @Override
        public float u_1723_Y() {
            return 0.5f;
        }
    }

    public static class n_1700_B
    implements n_4006_Y {
        private final g_2336_b n_1700_B;
        private final String J_1907_R;

        public n_1700_B(g_2336_b p_i49760_1_, String p_i49760_2_) {
            this.n_1700_B = p_i49760_1_;
            this.J_1907_R = p_i49760_2_;
        }

        public static n_4006_Y n_1700_B(JsonObject p_211629_0_) {
            return new n_1700_B(new g_2336_b(i_4431_W.u_1723_Y(p_211629_0_, "sizes")), i_4431_W.u_1723_Y(p_211629_0_, "template"));
        }

        @Override
        @Nullable
        public e_3495_r n_1700_B(ResourceManager resourceManagerIn) {
            K_1311_s k_1311_s;
            block8: {
                Resource iresource = MinecraftClient.A_4115_X().T_2506_i().n_1700_B(this.n_1700_B);
                try {
                    byte[] abyte = new byte[65536];
                    iresource.J_1907_R().read(abyte);
                    k_1311_s = new K_1311_s(resourceManagerIn, abyte, this.J_1907_R);
                    if (iresource == null) break block8;
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
                    catch (IOException ioexception) {
                        n_1700_B.error("Cannot load {}, unicode glyphs will not render correctly", (Object)this.n_1700_B);
                        return null;
                    }
                }
                iresource.close();
            }
            return k_1311_s;
        }
    }
}




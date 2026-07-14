/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.google.common.primitives.Floats
 *  com.mojang.datafixers.util.Pair
 *  it.unimi.dsi.fastutil.ints.IntArrays
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.primitives.Floats;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.IntArrays;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.BitSet;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.A_1726_L;
import lightning.product.B_3871_I;
import lightning.product.D_4792_h;
import lightning.product.E_688_b;
import lightning.product.K_4074_S;
import lightning.product.M_1336_P;
import lightning.product.T_3594_S;
import lightning.product.b_1213_w;
import lightning.product.c_1514_x;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.q_383_x;
import lightning.product.s_2632_s;
import net.optifine.Config;
import net.optifine.SmartAnimations;
import net.optifine.render.MultiTextureBuilder;
import net.optifine.render.MultiTextureData;
import net.optifine.render.RenderEnv;
import net.optifine.render.VertexPosition;
import net.optifine.shaders.SVertexBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class D_3318_r
extends T_3594_S
implements s_2632_s {
    private static final Logger P_4830_p = LogManager.getLogger();
    private ByteBuffer h_1847_R;
    private final List<n_1700_B> Q_4569_t = Lists.newArrayList();
    private int M_182_A = 0;
    private int t_1786_h = 0;
    private int multiplayerClientSuggestionProvider = 0;
    private int w_1457_N = 0;
    private int Y_601_j;
    @Nullable
    private A_1726_L Y_259_p;
    private int Q_2552_b;
    private int C_2741_M;
    private b_1213_w k_2293_S;
    private boolean q_2307_F;
    private boolean Z_875_P;
    private boolean c_3005_b;
    private o_2576_A H_2857_Y;
    private boolean A_4115_X;
    private B_3871_I[] Y_1740_V = null;
    private B_3871_I[] t_4043_B = null;
    private B_3871_I x_607_J = null;
    private MultiTextureBuilder e_4240_b = new MultiTextureBuilder();
    public SVertexBuilder t_148_a;
    public RenderEnv s_956_w = null;
    public BitSet u_2550_I = null;
    public BitSet M_588_G = new BitSet();
    private ByteBuffer n_3318_d;
    private M_1336_P d_2427_y = new M_1336_P();
    private float[] z_1737_N = new float[4];
    private int[] v_4276_D = new int[4];
    private IntBuffer d_2461_k;
    private FloatBuffer G_624_v;
    private o_3091_w.n_1700_B T_2506_i;
    private FloatBuffer q_4610_l;
    private VertexPosition[] z_4693_k;
    private M_1336_P g_221_o = new M_1336_P();

    public D_3318_r(int bufferSizeIn) {
        this.h_1847_R = q_383_x.n_1700_B(bufferSizeIn * 4);
        this.d_2461_k = this.h_1847_R.asIntBuffer();
        this.G_624_v = this.h_1847_R.asFloatBuffer();
        SVertexBuilder.initVertexBuilder(this);
    }

    protected void G_564_y() {
        this.P_1922_E(this.k_2293_S.J_1907_R());
    }

    private void P_1922_E(int increaseAmount) {
        if (this.multiplayerClientSuggestionProvider + increaseAmount > this.h_1847_R.capacity()) {
            int i = this.h_1847_R.capacity();
            int j = i + D_3318_r.u_1723_Y(increaseAmount);
            P_4830_p.debug("Needed to grow BufferBuilder buffer: Old size {} bytes, new size {} bytes.", (Object)i, (Object)j);
            ByteBuffer bytebuffer = q_383_x.n_1700_B(j);
            ((Buffer)this.h_1847_R).position(0);
            bytebuffer.put(this.h_1847_R);
            ((Buffer)bytebuffer).rewind();
            this.h_1847_R = bytebuffer;
            this.d_2461_k = this.h_1847_R.asIntBuffer();
            this.G_624_v = this.h_1847_R.asFloatBuffer();
            if (this.Y_1740_V != null) {
                B_3871_I[] atextureatlassprite = this.Y_1740_V;
                int k = this.k_2293_S();
                this.Y_1740_V = new B_3871_I[k];
                System.arraycopy(atextureatlassprite, 0, this.Y_1740_V, 0, Math.min(atextureatlassprite.length, this.Y_1740_V.length));
                this.t_4043_B = null;
            }
        }
    }

    private static int u_1723_Y(int xIn) {
        int j;
        int i = 0x200000;
        if (xIn == 0) {
            return i;
        }
        if (xIn < 0) {
            i *= -1;
        }
        return (j = xIn % i) == 0 ? xIn : xIn + i - j;
    }

    public void n_1700_B(float cameraX, float cameraY, float cameraZ) {
        ((Buffer)this.h_1847_R).clear();
        FloatBuffer floatbuffer = this.h_1847_R.asFloatBuffer();
        FloatBuffer floatbuffer1 = floatbuffer.slice();
        int i = this.Y_601_j / 4;
        float[] afloat = new float[i];
        for (int j = 0; j < i; ++j) {
            afloat[j] = D_3318_r.n_1700_B(floatbuffer, cameraX, cameraY, cameraZ, this.k_2293_S.n_1700_B(), this.t_1786_h / 4 + j * this.k_2293_S.J_1907_R());
        }
        int[] aint = new int[i];
        int k = 0;
        while (k < aint.length) {
            aint[k] = k++;
        }
        IntArrays.mergeSort((int[])aint, (p_lambda$sortVertexData$0_1_, p_lambda$sortVertexData$0_2_) -> Floats.compare((float)afloat[p_lambda$sortVertexData$0_2_], (float)afloat[p_lambda$sortVertexData$0_1_]));
        BitSet bitset = new BitSet();
        FloatBuffer floatbuffer2 = this.v_4262_N(this.k_2293_S.n_1700_B() * 4);
        int l = bitset.nextClearBit(0);
        while (l < aint.length) {
            int i1 = aint[l];
            if (i1 != l) {
                this.n_1700_B(floatbuffer, i1);
                ((Buffer)floatbuffer2).clear();
                floatbuffer2.put(floatbuffer);
                int j1 = i1;
                int k1 = aint[i1];
                while (j1 != l) {
                    this.n_1700_B(floatbuffer, k1);
                    ((Buffer)floatbuffer1).clear();
                    ((Buffer)floatbuffer1).position(floatbuffer.position());
                    ((Buffer)floatbuffer1).limit(floatbuffer.limit());
                    this.n_1700_B(floatbuffer, j1);
                    floatbuffer.put(floatbuffer1);
                    bitset.set(j1);
                    j1 = k1;
                    k1 = aint[k1];
                }
                this.n_1700_B(floatbuffer, l);
                ((Buffer)floatbuffer2).flip();
                floatbuffer.put(floatbuffer2);
            }
            bitset.set(l);
            l = bitset.nextClearBit(l + 1);
        }
        if (this.Y_1740_V != null) {
            B_3871_I[] atextureatlassprite = new B_3871_I[this.Y_601_j / 4];
            int l1 = this.k_2293_S.J_1907_R() / 4 * 4;
            for (int i2 = 0; i2 < aint.length; ++i2) {
                int j2 = aint[i2];
                atextureatlassprite[i2] = this.Y_1740_V[j2];
            }
            System.arraycopy(atextureatlassprite, 0, this.Y_1740_V, 0, atextureatlassprite.length);
        }
    }

    private void n_1700_B(FloatBuffer floatBufferIn, int indexIn) {
        int i = this.k_2293_S.n_1700_B() * 4;
        ((Buffer)floatBufferIn).limit(this.t_1786_h / 4 + (indexIn + 1) * i);
        ((Buffer)floatBufferIn).position(this.t_1786_h / 4 + indexIn * i);
    }

    public J_1907_R P_1922_E() {
        ((Buffer)this.h_1847_R).limit(this.multiplayerClientSuggestionProvider);
        ((Buffer)this.h_1847_R).position(this.t_1786_h);
        ByteBuffer bytebuffer = ByteBuffer.allocate(this.Y_601_j * this.k_2293_S.J_1907_R());
        bytebuffer.put(this.h_1847_R);
        ((Buffer)this.h_1847_R).clear();
        B_3871_I[] atextureatlassprite = this.Q_2552_b();
        return new J_1907_R(bytebuffer, this.k_2293_S, atextureatlassprite);
    }

    private B_3871_I[] Q_2552_b() {
        if (this.Y_1740_V == null) {
            return null;
        }
        int i = this.Y_601_j / 4;
        B_3871_I[] atextureatlassprite = new B_3871_I[i];
        System.arraycopy(this.Y_1740_V, 0, atextureatlassprite, 0, i);
        return atextureatlassprite;
    }

    private static float n_1700_B(FloatBuffer floatBufferIn, float x, float y, float z, int integerSize, int offset) {
        float f = floatBufferIn.get(offset + integerSize * 0 + 0);
        float f1 = floatBufferIn.get(offset + integerSize * 0 + 1);
        float f2 = floatBufferIn.get(offset + integerSize * 0 + 2);
        float f3 = floatBufferIn.get(offset + integerSize * 1 + 0);
        float f4 = floatBufferIn.get(offset + integerSize * 1 + 1);
        float f5 = floatBufferIn.get(offset + integerSize * 1 + 2);
        float f6 = floatBufferIn.get(offset + integerSize * 2 + 0);
        float f7 = floatBufferIn.get(offset + integerSize * 2 + 1);
        float f8 = floatBufferIn.get(offset + integerSize * 2 + 2);
        float f9 = floatBufferIn.get(offset + integerSize * 3 + 0);
        float f10 = floatBufferIn.get(offset + integerSize * 3 + 1);
        float f11 = floatBufferIn.get(offset + integerSize * 3 + 2);
        float f12 = (f + f3 + f6 + f9) * 0.25f - x;
        float f13 = (f1 + f4 + f7 + f10) * 0.25f - y;
        float f14 = (f2 + f5 + f8 + f11) * 0.25f - z;
        return f12 * f12 + f13 * f13 + f14 * f14;
    }

    public void n_1700_B(J_1907_R state) {
        ((Buffer)state.n_1700_B).clear();
        int i = state.n_1700_B.capacity();
        this.P_1922_E(i);
        ((Buffer)this.h_1847_R).limit(this.h_1847_R.capacity());
        ((Buffer)this.h_1847_R).position(this.t_1786_h);
        this.h_1847_R.put(state.n_1700_B);
        ((Buffer)this.h_1847_R).clear();
        b_1213_w vertexformat = state.J_1907_R;
        this.n_1700_B(vertexformat);
        this.Y_601_j = i / vertexformat.J_1907_R();
        this.multiplayerClientSuggestionProvider = this.t_1786_h + this.Y_601_j * vertexformat.J_1907_R();
        if (state.R_4764_Y != null) {
            if (this.Y_1740_V == null) {
                this.Y_1740_V = this.t_4043_B;
            }
            if (this.Y_1740_V == null || this.Y_1740_V.length < this.k_2293_S()) {
                this.Y_1740_V = new B_3871_I[this.k_2293_S()];
            }
            B_3871_I[] atextureatlassprite = state.R_4764_Y;
            System.arraycopy(atextureatlassprite, 0, this.Y_1740_V, 0, atextureatlassprite.length);
        } else {
            if (this.Y_1740_V != null) {
                this.t_4043_B = this.Y_1740_V;
            }
            this.Y_1740_V = null;
        }
    }

    public void n_1700_B(int glMode, b_1213_w format) {
        if (this.c_3005_b) {
            throw new IllegalStateException("Already building!");
        }
        this.c_3005_b = true;
        this.C_2741_M = glMode;
        this.n_1700_B(format);
        this.Y_259_p = (A_1726_L)format.R_4764_Y().get(0);
        this.Q_2552_b = 0;
        ((Buffer)this.h_1847_R).clear();
        if (Config.isShaders()) {
            SVertexBuilder.endSetVertexFormat(this);
        }
        if (Config.isMultiTexture()) {
            this.C_2741_M();
        }
        if (SmartAnimations.isActive()) {
            if (this.u_2550_I == null) {
                this.u_2550_I = this.M_588_G;
            }
            this.u_2550_I.clear();
        } else if (this.u_2550_I != null) {
            this.u_2550_I = null;
        }
    }

    @Override
    public D_4792_h tex(float u, float v) {
        if (this.x_607_J != null && this.Y_1740_V != null) {
            u = this.x_607_J.R_4764_Y(u);
            v = this.x_607_J.G_564_y(v);
            this.Y_1740_V[this.Y_601_j / 4] = this.x_607_J;
        }
        return s_2632_s.super.tex(u, v);
    }

    private void n_1700_B(b_1213_w vertexFormatIn) {
        if (this.k_2293_S != vertexFormatIn) {
            this.k_2293_S = vertexFormatIn;
            boolean flag = vertexFormatIn == E_688_b.t_148_a;
            boolean flag1 = vertexFormatIn == E_688_b.w_1484_f;
            this.q_2307_F = flag || flag1;
            this.Z_875_P = flag;
        }
    }

    public void u_1723_Y() {
        if (!this.c_3005_b) {
            throw new IllegalStateException("Not building!");
        }
        this.c_3005_b = false;
        MultiTextureData multitexturedata = this.e_4240_b.build(this.Y_601_j, this.H_2857_Y, this.Y_1740_V);
        this.Q_4569_t.add(new n_1700_B(this.k_2293_S, this.Y_601_j, this.C_2741_M, multitexturedata));
        this.H_2857_Y = null;
        this.A_4115_X = false;
        if (this.Y_1740_V != null) {
            this.t_4043_B = this.Y_1740_V;
        }
        this.Y_1740_V = null;
        this.x_607_J = null;
        this.t_1786_h += this.Y_601_j * this.k_2293_S.J_1907_R();
        this.Y_601_j = 0;
        this.Y_259_p = null;
        this.Q_2552_b = 0;
    }

    @Override
    public void n_1700_B(int indexIn, byte byteIn) {
        this.h_1847_R.put(this.multiplayerClientSuggestionProvider + indexIn, byteIn);
    }

    @Override
    public void n_1700_B(int indexIn, short shortIn) {
        this.h_1847_R.putShort(this.multiplayerClientSuggestionProvider + indexIn, shortIn);
    }

    @Override
    public void n_1700_B(int indexIn, float floatIn) {
        this.h_1847_R.putFloat(this.multiplayerClientSuggestionProvider + indexIn, floatIn);
    }

    @Override
    public void endVertex() {
        if (this.Q_2552_b != 0) {
            throw new IllegalStateException("Not filled all elements of the vertex");
        }
        ++this.Y_601_j;
        this.G_564_y();
        if (Config.isShaders()) {
            SVertexBuilder.endAddVertex(this);
        }
    }

    @Override
    public void R_4764_Y() {
        A_1726_L vertexformatelement;
        ImmutableList<A_1726_L> immutablelist = this.k_2293_S.R_4764_Y();
        this.Q_2552_b = (this.Q_2552_b + 1) % immutablelist.size();
        this.multiplayerClientSuggestionProvider += this.Y_259_p.G_564_y();
        this.Y_259_p = vertexformatelement = (A_1726_L)immutablelist.get(this.Q_2552_b);
        if (vertexformatelement.J_1907_R() == A_1726_L.J_1907_R.P_1922_E) {
            this.R_4764_Y();
        }
        if (this.n_1700_B && this.Y_259_p.J_1907_R() == A_1726_L.J_1907_R.R_4764_Y) {
            s_2632_s.super.color(this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E);
        }
    }

    @Override
    public D_4792_h color(int red, int green, int blue, int alpha) {
        if (this.n_1700_B) {
            throw new IllegalStateException();
        }
        return s_2632_s.super.color(red, green, blue, alpha);
    }

    @Override
    public void n_1700_B(float x, float y, float z, float red, float green, float blue, float alpha, float texU, float texV, int overlayUV, int lightmapUV, float normalX, float normalY, float normalZ) {
        if (this.n_1700_B) {
            throw new IllegalStateException();
        }
        if (this.q_2307_F) {
            int i;
            this.n_1700_B(0, x);
            this.n_1700_B(4, y);
            this.n_1700_B(8, z);
            this.n_1700_B(12, (byte)(red * 255.0f));
            this.n_1700_B(13, (byte)(green * 255.0f));
            this.n_1700_B(14, (byte)(blue * 255.0f));
            this.n_1700_B(15, (byte)(alpha * 255.0f));
            this.n_1700_B(16, texU);
            this.n_1700_B(20, texV);
            if (this.Z_875_P) {
                this.n_1700_B(24, (short)(overlayUV & 0xFFFF));
                this.n_1700_B(26, (short)(overlayUV >> 16 & 0xFFFF));
                i = 28;
            } else {
                i = 24;
            }
            this.n_1700_B(i + 0, (short)(lightmapUV & 0xFFFF));
            this.n_1700_B(i + 2, (short)(lightmapUV >> 16 & 0xFFFF));
            this.n_1700_B(i + 4, s_2632_s.n_1700_B(normalX));
            this.n_1700_B(i + 5, s_2632_s.n_1700_B(normalY));
            this.n_1700_B(i + 6, s_2632_s.n_1700_B(normalZ));
            this.multiplayerClientSuggestionProvider += this.k_2293_S.J_1907_R();
            this.endVertex();
        } else {
            super.n_1700_B(x, y, z, red, green, blue, alpha, texU, texV, overlayUV, lightmapUV, normalX, normalY, normalZ);
        }
    }

    public Pair<n_1700_B, ByteBuffer> v_4262_N() {
        n_1700_B bufferbuilder$drawstate = this.Q_4569_t.get(this.M_182_A++);
        ((Buffer)this.h_1847_R).position(this.w_1457_N);
        this.w_1457_N += bufferbuilder$drawstate.R_4764_Y() * bufferbuilder$drawstate.J_1907_R().J_1907_R();
        ((Buffer)this.h_1847_R).limit(this.w_1457_N);
        if (this.M_182_A == this.Q_4569_t.size() && this.Y_601_j == 0) {
            this.w_1484_f();
        }
        ByteBuffer bytebuffer = this.h_1847_R.slice();
        bytebuffer.order(this.h_1847_R.order());
        ((Buffer)this.h_1847_R).clear();
        if (bufferbuilder$drawstate.R_4764_Y == 7 && Config.isQuadsToTriangles()) {
            if (this.n_3318_d == null) {
                this.n_3318_d = q_383_x.n_1700_B(this.h_1847_R.capacity() * 2);
            }
            if (this.n_3318_d.capacity() < this.h_1847_R.capacity() * 2) {
                this.n_3318_d = q_383_x.n_1700_B(this.h_1847_R.capacity() * 2);
            }
            b_1213_w vertexformat = bufferbuilder$drawstate.J_1907_R();
            int i = bufferbuilder$drawstate.R_4764_Y();
            D_3318_r.n_1700_B(bytebuffer, vertexformat, i, this.n_3318_d);
            int j = i * 6 / 4;
            n_1700_B bufferbuilder$drawstate1 = new n_1700_B(vertexformat, j, 4);
            return Pair.of((Object)bufferbuilder$drawstate1, (Object)this.n_3318_d);
        }
        return Pair.of((Object)bufferbuilder$drawstate, (Object)bytebuffer);
    }

    public void w_1484_f() {
        if (this.t_1786_h != this.w_1457_N) {
            P_4830_p.warn("Bytes mismatch " + this.t_1786_h + " " + this.w_1457_N);
        }
        this.t_148_a();
    }

    public void t_148_a() {
        this.t_1786_h = 0;
        this.w_1457_N = 0;
        this.multiplayerClientSuggestionProvider = 0;
        this.Q_4569_t.clear();
        this.M_182_A = 0;
        this.x_607_J = null;
    }

    @Override
    public A_1726_L J_1907_R() {
        if (this.Y_259_p == null) {
            throw new IllegalStateException("BufferBuilder not started");
        }
        return this.Y_259_p;
    }

    public boolean s_956_w() {
        return this.c_3005_b;
    }

    @Override
    public void putSprite(B_3871_I p_putSprite_1_) {
        if (this.u_2550_I != null && p_putSprite_1_ != null && p_putSprite_1_.c_3005_b() && p_putSprite_1_.multiplayerClientSuggestionProvider() >= 0) {
            this.u_2550_I.set(p_putSprite_1_.multiplayerClientSuggestionProvider());
        }
        if (this.Y_1740_V != null) {
            int i = this.Y_601_j / 4;
            this.Y_1740_V[i] = p_putSprite_1_;
        }
    }

    @Override
    public void setSprite(B_3871_I p_setSprite_1_) {
        if (this.u_2550_I != null && p_setSprite_1_ != null && p_setSprite_1_.c_3005_b() && p_setSprite_1_.multiplayerClientSuggestionProvider() >= 0) {
            this.u_2550_I.set(p_setSprite_1_.multiplayerClientSuggestionProvider());
        }
        if (this.Y_1740_V != null) {
            this.x_607_J = p_setSprite_1_;
        }
    }

    @Override
    public boolean isMultiTexture() {
        return this.Y_1740_V != null;
    }

    @Override
    public void setRenderType(o_2576_A p_setRenderType_1_) {
        this.H_2857_Y = p_setRenderType_1_;
    }

    @Override
    public o_2576_A getRenderType() {
        return this.H_2857_Y;
    }

    @Override
    public void setRenderBlocks(boolean p_setRenderBlocks_1_) {
        this.A_4115_X = p_setRenderBlocks_1_;
        if (Config.isMultiTexture()) {
            this.C_2741_M();
        }
    }

    public void n_1700_B(o_2576_A p_setBlockLayer_1_) {
        this.H_2857_Y = p_setBlockLayer_1_;
        this.A_4115_X = true;
    }

    private void C_2741_M() {
        if (this.A_4115_X && this.H_2857_Y != null && this.Y_1740_V == null && this.c_3005_b) {
            if (this.Y_601_j > 0) {
                int i = this.C_2741_M;
                b_1213_w vertexformat = this.k_2293_S;
                o_2576_A rendertype = this.H_2857_Y;
                boolean flag = this.A_4115_X;
                this.H_2857_Y.n_1700_B(this, 0, 0, 0);
                this.n_1700_B(i, vertexformat);
                this.H_2857_Y = rendertype;
                this.A_4115_X = flag;
            }
            this.Y_1740_V = this.t_4043_B;
            if (this.Y_1740_V == null || this.Y_1740_V.length < this.k_2293_S()) {
                this.Y_1740_V = new B_3871_I[this.k_2293_S()];
            }
        }
    }

    private int k_2293_S() {
        return this.h_1847_R.capacity() / this.k_2293_S.J_1907_R();
    }

    @Override
    public RenderEnv n_1700_B(K_4074_S p_getRenderEnv_1_, c_1514_x p_getRenderEnv_2_) {
        if (this.s_956_w == null) {
            this.s_956_w = new RenderEnv(p_getRenderEnv_1_, p_getRenderEnv_2_);
            return this.s_956_w;
        }
        this.s_956_w.reset(p_getRenderEnv_1_, p_getRenderEnv_2_);
        return this.s_956_w;
    }

    private static void n_1700_B(ByteBuffer p_quadsToTriangles_0_, b_1213_w p_quadsToTriangles_1_, int p_quadsToTriangles_2_, ByteBuffer p_quadsToTriangles_3_) {
        int i = p_quadsToTriangles_1_.J_1907_R();
        int j = p_quadsToTriangles_0_.limit();
        ((Buffer)p_quadsToTriangles_0_).rewind();
        ((Buffer)p_quadsToTriangles_3_).clear();
        for (int k = 0; k < p_quadsToTriangles_2_; k += 4) {
            ((Buffer)p_quadsToTriangles_0_).limit((k + 3) * i);
            ((Buffer)p_quadsToTriangles_0_).position(k * i);
            p_quadsToTriangles_3_.put(p_quadsToTriangles_0_);
            ((Buffer)p_quadsToTriangles_0_).limit((k + 1) * i);
            ((Buffer)p_quadsToTriangles_0_).position(k * i);
            p_quadsToTriangles_3_.put(p_quadsToTriangles_0_);
            ((Buffer)p_quadsToTriangles_0_).limit((k + 2 + 2) * i);
            ((Buffer)p_quadsToTriangles_0_).position((k + 2) * i);
            p_quadsToTriangles_3_.put(p_quadsToTriangles_0_);
        }
        ((Buffer)p_quadsToTriangles_0_).limit(j);
        ((Buffer)p_quadsToTriangles_0_).rewind();
        ((Buffer)p_quadsToTriangles_3_).flip();
    }

    public int u_2550_I() {
        return this.C_2741_M;
    }

    public int M_588_G() {
        return this.Y_601_j;
    }

    @Override
    public M_1336_P getTempVec3f(M_1336_P p_getTempVec3f_1_) {
        this.d_2427_y.J_1907_R(p_getTempVec3f_1_.n_1700_B(), p_getTempVec3f_1_.J_1907_R(), p_getTempVec3f_1_.R_4764_Y());
        return this.d_2427_y;
    }

    @Override
    public M_1336_P getTempVec3f(float p_getTempVec3f_1_, float p_getTempVec3f_2_, float p_getTempVec3f_3_) {
        this.d_2427_y.J_1907_R(p_getTempVec3f_1_, p_getTempVec3f_2_, p_getTempVec3f_3_);
        return this.d_2427_y;
    }

    @Override
    public float[] getTempFloat4(float p_getTempFloat4_1_, float p_getTempFloat4_2_, float p_getTempFloat4_3_, float p_getTempFloat4_4_) {
        this.z_1737_N[0] = p_getTempFloat4_1_;
        this.z_1737_N[1] = p_getTempFloat4_2_;
        this.z_1737_N[2] = p_getTempFloat4_3_;
        this.z_1737_N[3] = p_getTempFloat4_4_;
        return this.z_1737_N;
    }

    @Override
    public int[] getTempInt4(int p_getTempInt4_1_, int p_getTempInt4_2_, int p_getTempInt4_3_, int p_getTempInt4_4_) {
        this.v_4276_D[0] = p_getTempInt4_1_;
        this.v_4276_D[1] = p_getTempInt4_2_;
        this.v_4276_D[2] = p_getTempInt4_3_;
        this.v_4276_D[3] = p_getTempInt4_4_;
        return this.v_4276_D;
    }

    public ByteBuffer P_4830_p() {
        return this.h_1847_R;
    }

    public FloatBuffer h_1847_R() {
        return this.G_624_v;
    }

    public IntBuffer Q_4569_t() {
        return this.d_2461_k;
    }

    public int M_182_A() {
        return this.Y_601_j * this.k_2293_S.n_1700_B();
    }

    private FloatBuffer v_4262_N(int p_getFloatBufferSort_1_) {
        if (this.q_4610_l == null || this.q_4610_l.capacity() < p_getFloatBufferSort_1_) {
            this.q_4610_l = q_383_x.J_1907_R(p_getFloatBufferSort_1_);
        }
        return this.q_4610_l;
    }

    @Override
    public o_3091_w.n_1700_B getRenderTypeBuffer() {
        return this.T_2506_i;
    }

    public void n_1700_B(o_3091_w.n_1700_B p_setRenderTypeBuffer_1_) {
        this.T_2506_i = p_setRenderTypeBuffer_1_;
    }

    public void n_1700_B(float p_addVertexText_1_, float p_addVertexText_2_, float p_addVertexText_3_, int p_addVertexText_4_, int p_addVertexText_5_, int p_addVertexText_6_, int p_addVertexText_7_, float p_addVertexText_8_, float p_addVertexText_9_, int p_addVertexText_10_, int p_addVertexText_11_) {
        if (this.k_2293_S.J_1907_R() != E_688_b.q_2307_F.J_1907_R()) {
            throw new IllegalStateException("Invalid text vertex format: " + String.valueOf(this.k_2293_S));
        }
        this.n_1700_B(0, p_addVertexText_1_);
        this.n_1700_B(4, p_addVertexText_2_);
        this.n_1700_B(8, p_addVertexText_3_);
        this.n_1700_B(12, (byte)p_addVertexText_4_);
        this.n_1700_B(13, (byte)p_addVertexText_5_);
        this.n_1700_B(14, (byte)p_addVertexText_6_);
        this.n_1700_B(15, (byte)p_addVertexText_7_);
        this.n_1700_B(16, p_addVertexText_8_);
        this.n_1700_B(20, p_addVertexText_9_);
        this.n_1700_B(24, (short)p_addVertexText_10_);
        this.n_1700_B(26, (short)p_addVertexText_11_);
        this.multiplayerClientSuggestionProvider += this.k_2293_S.J_1907_R();
        this.endVertex();
    }

    @Override
    public void setQuadVertexPositions(VertexPosition[] p_setQuadVertexPositions_1_) {
        this.z_4693_k = p_setQuadVertexPositions_1_;
    }

    public VertexPosition[] t_1786_h() {
        return this.z_4693_k;
    }

    @Override
    public void setMidBlock(float p_setMidBlock_1_, float p_setMidBlock_2_, float p_setMidBlock_3_) {
        this.g_221_o.J_1907_R(p_setMidBlock_1_, p_setMidBlock_2_, p_setMidBlock_3_);
    }

    public M_1336_P multiplayerClientSuggestionProvider() {
        return this.g_221_o;
    }

    public void n_1700_B(ByteBuffer p_putBulkData_1_) {
        if (Config.isShaders()) {
            SVertexBuilder.beginAddVertexData(this, p_putBulkData_1_);
        }
        this.P_1922_E(p_putBulkData_1_.limit() + this.k_2293_S.J_1907_R());
        ((Buffer)this.h_1847_R).position(this.Y_601_j * this.k_2293_S.J_1907_R());
        this.h_1847_R.put(p_putBulkData_1_);
        this.Y_601_j += p_putBulkData_1_.limit() / this.k_2293_S.J_1907_R();
        this.multiplayerClientSuggestionProvider += p_putBulkData_1_.limit();
        if (Config.isShaders()) {
            SVertexBuilder.endAddVertexData(this);
        }
    }

    public b_1213_w w_1457_N() {
        return this.k_2293_S;
    }

    public int Y_601_j() {
        return this.t_1786_h;
    }

    public int Y_259_p() {
        return this.t_1786_h / 4;
    }

    public static class J_1907_R {
        private final ByteBuffer n_1700_B;
        private final b_1213_w J_1907_R;
        private B_3871_I[] R_4764_Y;

        public J_1907_R(ByteBuffer p_i242121_1_, b_1213_w p_i242121_2_, B_3871_I[] p_i242121_3_) {
            this(p_i242121_1_, p_i242121_2_);
            this.R_4764_Y = p_i242121_3_;
        }

        private J_1907_R(ByteBuffer byteBufferIn, b_1213_w vertexFormatIn) {
            this.n_1700_B = byteBufferIn;
            this.J_1907_R = vertexFormatIn;
        }
    }

    public static final class n_1700_B {
        private final b_1213_w n_1700_B;
        private final int J_1907_R;
        private final int R_4764_Y;
        private MultiTextureData G_564_y;

        private n_1700_B(b_1213_w p_i242109_1_, int p_i242109_2_, int p_i242109_3_, MultiTextureData p_i242109_4_) {
            this(p_i242109_1_, p_i242109_2_, p_i242109_3_);
            this.G_564_y = p_i242109_4_;
        }

        public MultiTextureData n_1700_B() {
            return this.G_564_y;
        }

        private n_1700_B(b_1213_w formatIn, int vertexCountIn, int drawModeIn) {
            this.n_1700_B = formatIn;
            this.J_1907_R = vertexCountIn;
            this.R_4764_Y = drawModeIn;
        }

        public b_1213_w J_1907_R() {
            return this.n_1700_B;
        }

        public int R_4764_Y() {
            return this.J_1907_R;
        }

        public int G_564_y() {
            return this.R_4764_Y;
        }
    }
}



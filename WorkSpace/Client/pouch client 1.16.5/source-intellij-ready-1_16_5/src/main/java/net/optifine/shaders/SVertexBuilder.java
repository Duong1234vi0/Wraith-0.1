/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL20
 */
package net.optifine.shaders;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import lightning.product.D_3318_r;
import lightning.product.D_4792_h;
import lightning.product.E_688_b;
import lightning.product.K_4074_S;
import lightning.product.M_1336_P;
import lightning.product.b_1213_w;
import net.optifine.Config;
import net.optifine.render.VertexPosition;
import net.optifine.shaders.BlockAliases;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL20;

public class SVertexBuilder {
    int vertexSize;
    int offsetNormal;
    int offsetUV;
    int offsetUVCenter;
    boolean hasNormal;
    boolean hasTangent;
    boolean hasUV;
    boolean hasUVCenter;
    long[] entityData = new long[10];
    int entityDataIndex = 0;

    public SVertexBuilder() {
        this.entityData[this.entityDataIndex] = 0L;
    }

    public static void initVertexBuilder(D_3318_r wrr) {
        wrr.t_148_a = new SVertexBuilder();
    }

    public void pushEntity(long data) {
        ++this.entityDataIndex;
        this.entityData[this.entityDataIndex] = data;
    }

    public void popEntity() {
        this.entityData[this.entityDataIndex] = 0L;
        --this.entityDataIndex;
    }

    public static void pushEntity(K_4074_S blockState, D_4792_h ivb) {
        if (ivb instanceof D_3318_r) {
            D_3318_r bufferbuilder = (D_3318_r)ivb;
            int i = BlockAliases.getAliasBlockId(blockState);
            int j = BlockAliases.getAliasMetadata(blockState);
            int k = BlockAliases.getRenderType(blockState);
            int l = ((k & 0xFFFF) << 16) + (i & 0xFFFF);
            int i1 = j & 0xFFFF;
            bufferbuilder.t_148_a.pushEntity(((long)i1 << 32) + (long)l);
        }
    }

    public static void popEntity(D_4792_h ivb) {
        if (ivb instanceof D_3318_r) {
            D_3318_r bufferbuilder = (D_3318_r)ivb;
            bufferbuilder.t_148_a.popEntity();
        }
    }

    public static boolean popEntity(boolean value, D_3318_r wrr) {
        wrr.t_148_a.popEntity();
        return value;
    }

    public static void endSetVertexFormat(D_3318_r wrr) {
        SVertexBuilder svertexbuilder = wrr.t_148_a;
        b_1213_w vertexformat = wrr.w_1457_N();
        svertexbuilder.vertexSize = vertexformat.J_1907_R() / 4;
        svertexbuilder.hasTangent = svertexbuilder.hasNormal = vertexformat.v_4262_N();
        svertexbuilder.hasUV = vertexformat.J_1907_R(0);
        svertexbuilder.offsetNormal = svertexbuilder.hasNormal ? vertexformat.w_1484_f() / 4 : 0;
        svertexbuilder.offsetUV = svertexbuilder.hasUV ? vertexformat.R_4764_Y(0) / 4 : 0;
        svertexbuilder.offsetUVCenter = 8;
    }

    public static void beginAddVertex(D_3318_r wrr) {
        if (wrr.M_588_G() == 0) {
            SVertexBuilder.endSetVertexFormat(wrr);
        }
    }

    public static void endAddVertex(D_3318_r wrr) {
        SVertexBuilder svertexbuilder = wrr.t_148_a;
        if (svertexbuilder.vertexSize == 18) {
            if (wrr.u_2550_I() == 7 && wrr.M_588_G() % 4 == 0) {
                svertexbuilder.calcNormal(wrr, wrr.M_182_A() - 4 * svertexbuilder.vertexSize);
            }
            long i = svertexbuilder.entityData[svertexbuilder.entityDataIndex];
            int j = wrr.M_182_A() - 18 + 13;
            wrr.Q_4569_t().put(j += wrr.Y_259_p(), (int)i);
            wrr.Q_4569_t().put(j + 1, (int)(i >> 32));
        }
    }

    public static void beginAddVertexData(D_3318_r wrr, int[] data) {
        if (wrr.M_588_G() == 0) {
            SVertexBuilder.endSetVertexFormat(wrr);
        }
        SVertexBuilder svertexbuilder = wrr.t_148_a;
        if (svertexbuilder.vertexSize == 18) {
            long i = svertexbuilder.entityData[svertexbuilder.entityDataIndex];
            int j = 13;
            while (j + 1 < data.length) {
                data[j] = (int)i;
                data[j + 1] = (int)(i >> 32);
                j += 18;
            }
        }
    }

    public static void beginAddVertexData(D_3318_r wrr, ByteBuffer byteBuffer) {
        if (wrr.M_588_G() == 0) {
            SVertexBuilder.endSetVertexFormat(wrr);
        }
        SVertexBuilder svertexbuilder = wrr.t_148_a;
        if (svertexbuilder.vertexSize == 18) {
            long i = svertexbuilder.entityData[svertexbuilder.entityDataIndex];
            int j = byteBuffer.limit() / 4;
            int k = 13;
            while (k + 1 < j) {
                int l = (int)i;
                int i1 = (int)(i >> 32);
                byteBuffer.putInt(k * 4, l);
                byteBuffer.putInt((k + 1) * 4, i1);
                k += 18;
            }
        }
    }

    public static void endAddVertexData(D_3318_r wrr) {
        SVertexBuilder svertexbuilder = wrr.t_148_a;
        if (svertexbuilder.vertexSize == 18 && wrr.u_2550_I() == 7 && wrr.M_588_G() % 4 == 0) {
            svertexbuilder.calcNormal(wrr, wrr.M_182_A() - 4 * svertexbuilder.vertexSize);
        }
    }

    public void calcNormal(D_3318_r wrr, int baseIndex) {
        FloatBuffer floatbuffer = wrr.h_1847_R();
        IntBuffer intbuffer = wrr.Q_4569_t();
        float f = floatbuffer.get((baseIndex += wrr.Y_259_p()) + 0 * this.vertexSize);
        float f1 = floatbuffer.get(baseIndex + 0 * this.vertexSize + 1);
        float f2 = floatbuffer.get(baseIndex + 0 * this.vertexSize + 2);
        float f3 = floatbuffer.get(baseIndex + 0 * this.vertexSize + this.offsetUV);
        float f4 = floatbuffer.get(baseIndex + 0 * this.vertexSize + this.offsetUV + 1);
        float f5 = floatbuffer.get(baseIndex + 1 * this.vertexSize);
        float f6 = floatbuffer.get(baseIndex + 1 * this.vertexSize + 1);
        float f7 = floatbuffer.get(baseIndex + 1 * this.vertexSize + 2);
        float f8 = floatbuffer.get(baseIndex + 1 * this.vertexSize + this.offsetUV);
        float f9 = floatbuffer.get(baseIndex + 1 * this.vertexSize + this.offsetUV + 1);
        float f10 = floatbuffer.get(baseIndex + 2 * this.vertexSize);
        float f11 = floatbuffer.get(baseIndex + 2 * this.vertexSize + 1);
        float f12 = floatbuffer.get(baseIndex + 2 * this.vertexSize + 2);
        float f13 = floatbuffer.get(baseIndex + 2 * this.vertexSize + this.offsetUV);
        float f14 = floatbuffer.get(baseIndex + 2 * this.vertexSize + this.offsetUV + 1);
        float f15 = floatbuffer.get(baseIndex + 3 * this.vertexSize);
        float f16 = floatbuffer.get(baseIndex + 3 * this.vertexSize + 1);
        float f17 = floatbuffer.get(baseIndex + 3 * this.vertexSize + 2);
        float f18 = floatbuffer.get(baseIndex + 3 * this.vertexSize + this.offsetUV);
        float f19 = floatbuffer.get(baseIndex + 3 * this.vertexSize + this.offsetUV + 1);
        float f21 = f11 - f1;
        float f25 = f17 - f7;
        float f24 = f16 - f6;
        float f22 = f12 - f2;
        float f30 = f21 * f25 - f24 * f22;
        float f23 = f15 - f5;
        float f20 = f10 - f;
        float f31 = f22 * f23 - f25 * f20;
        float f32 = f20 * f24 - f23 * f21;
        float f33 = f30 * f30 + f31 * f31 + f32 * f32;
        float f34 = (double)f33 != 0.0 ? (float)(1.0 / Math.sqrt(f33)) : 1.0f;
        f30 *= f34;
        f31 *= f34;
        f32 *= f34;
        f20 = f5 - f;
        f21 = f6 - f1;
        f22 = f7 - f2;
        float f26 = f8 - f3;
        float f27 = f9 - f4;
        f23 = f10 - f;
        f24 = f11 - f1;
        f25 = f12 - f2;
        float f28 = f13 - f3;
        float f29 = f14 - f4;
        float f35 = f26 * f29 - f28 * f27;
        float f36 = f35 != 0.0f ? 1.0f / f35 : 1.0f;
        float f37 = (f29 * f20 - f27 * f23) * f36;
        float f38 = (f29 * f21 - f27 * f24) * f36;
        float f39 = (f29 * f22 - f27 * f25) * f36;
        float f40 = (f26 * f23 - f28 * f20) * f36;
        float f41 = (f26 * f24 - f28 * f21) * f36;
        float f42 = (f26 * f25 - f28 * f22) * f36;
        f33 = f37 * f37 + f38 * f38 + f39 * f39;
        f34 = (double)f33 != 0.0 ? (float)(1.0 / Math.sqrt(f33)) : 1.0f;
        f37 *= f34;
        f38 *= f34;
        f39 *= f34;
        f33 = f40 * f40 + f41 * f41 + f42 * f42;
        f34 = (double)f33 != 0.0 ? (float)(1.0 / Math.sqrt(f33)) : 1.0f;
        float f43 = f32 * f38 - f31 * f39;
        float f44 = f30 * f39 - f32 * f37;
        float f45 = f31 * f37 - f30 * f38;
        float f46 = (f40 *= f34) * f43 + (f41 *= f34) * f44 + (f42 *= f34) * f45 < 0.0f ? -1.0f : 1.0f;
        int i = (int)(f30 * 127.0f) & 0xFF;
        int j = (int)(f31 * 127.0f) & 0xFF;
        int k = (int)(f32 * 127.0f) & 0xFF;
        int l = (k << 16) + (j << 8) + i;
        intbuffer.put(baseIndex + 0 * this.vertexSize + this.offsetNormal, l);
        intbuffer.put(baseIndex + 1 * this.vertexSize + this.offsetNormal, l);
        intbuffer.put(baseIndex + 2 * this.vertexSize + this.offsetNormal, l);
        intbuffer.put(baseIndex + 3 * this.vertexSize + this.offsetNormal, l);
        int i1 = ((int)(f37 * 32767.0f) & 0xFFFF) + (((int)(f38 * 32767.0f) & 0xFFFF) << 16);
        int j1 = ((int)(f39 * 32767.0f) & 0xFFFF) + (((int)(f46 * 32767.0f) & 0xFFFF) << 16);
        intbuffer.put(baseIndex + 0 * this.vertexSize + 11, i1);
        intbuffer.put(baseIndex + 0 * this.vertexSize + 11 + 1, j1);
        intbuffer.put(baseIndex + 1 * this.vertexSize + 11, i1);
        intbuffer.put(baseIndex + 1 * this.vertexSize + 11 + 1, j1);
        intbuffer.put(baseIndex + 2 * this.vertexSize + 11, i1);
        intbuffer.put(baseIndex + 2 * this.vertexSize + 11 + 1, j1);
        intbuffer.put(baseIndex + 3 * this.vertexSize + 11, i1);
        intbuffer.put(baseIndex + 3 * this.vertexSize + 11 + 1, j1);
        float f47 = (f3 + f8 + f13 + f18) / 4.0f;
        float f48 = (f4 + f9 + f14 + f19) / 4.0f;
        floatbuffer.put(baseIndex + 0 * this.vertexSize + 9, f47);
        floatbuffer.put(baseIndex + 0 * this.vertexSize + 9 + 1, f48);
        floatbuffer.put(baseIndex + 1 * this.vertexSize + 9, f47);
        floatbuffer.put(baseIndex + 1 * this.vertexSize + 9 + 1, f48);
        floatbuffer.put(baseIndex + 2 * this.vertexSize + 9, f47);
        floatbuffer.put(baseIndex + 2 * this.vertexSize + 9 + 1, f48);
        floatbuffer.put(baseIndex + 3 * this.vertexSize + 9, f47);
        floatbuffer.put(baseIndex + 3 * this.vertexSize + 9 + 1, f48);
        if (Shaders.useVelocityAttrib) {
            VertexPosition[] avertexposition = wrr.t_1786_h();
            int k1 = Config.getWorldRenderer().q_2307_F();
            this.setVelocity(floatbuffer, baseIndex, 0, avertexposition, k1, f, f1, f2);
            this.setVelocity(floatbuffer, baseIndex, 1, avertexposition, k1, f5, f6, f7);
            this.setVelocity(floatbuffer, baseIndex, 2, avertexposition, k1, f10, f11, f12);
            this.setVelocity(floatbuffer, baseIndex, 3, avertexposition, k1, f15, f16, f17);
            wrr.setQuadVertexPositions(null);
        }
        if (wrr.w_1457_N() == E_688_b.w_1484_f) {
            M_1336_P vector3f = wrr.multiplayerClientSuggestionProvider();
            float f51 = vector3f.n_1700_B();
            float f49 = vector3f.J_1907_R();
            float f50 = vector3f.R_4764_Y();
            this.setMidBlock(intbuffer, baseIndex, 0, f51 - f, f49 - f1, f50 - f2);
            this.setMidBlock(intbuffer, baseIndex, 1, f51 - f5, f49 - f6, f50 - f7);
            this.setMidBlock(intbuffer, baseIndex, 2, f51 - f10, f49 - f11, f50 - f12);
            this.setMidBlock(intbuffer, baseIndex, 3, f51 - f15, f49 - f16, f50 - f17);
        }
    }

    public void setMidBlock(IntBuffer intBuffer, int baseIndex, int vertex, float mbx, float mby, float mbz) {
        int i = (int)(mbx * 64.0f) & 0xFF;
        int j = (int)(mby * 64.0f) & 0xFF;
        int k = (int)(mbz * 64.0f) & 0xFF;
        int l = (k << 16) + (j << 8) + i;
        intBuffer.put(baseIndex + vertex * this.vertexSize + 8, l);
    }

    public void setVelocity(FloatBuffer floatBuffer, int baseIndex, int vertex, VertexPosition[] vps, int frameId, float x, float y, float z) {
        float f = 0.0f;
        float f1 = 0.0f;
        float f2 = 0.0f;
        if (vps != null && vps.length == 4) {
            VertexPosition vertexposition = vps[vertex];
            vertexposition.setPosition(frameId, x, y, z);
            if (vertexposition.isVelocityValid()) {
                f = vertexposition.getVelocityX();
                f1 = vertexposition.getVelocityY();
                f2 = vertexposition.getVelocityZ();
            }
        }
        int i = baseIndex + vertex * this.vertexSize + 15;
        floatBuffer.put(i + 0, f);
        floatBuffer.put(i + 1, f1);
        floatBuffer.put(i + 2, f2);
    }

    public static void calcNormalChunkLayer(D_3318_r wrr) {
        if (wrr.w_1457_N().v_4262_N() && wrr.u_2550_I() == 7 && wrr.M_588_G() % 4 == 0) {
            SVertexBuilder svertexbuilder = wrr.t_148_a;
            SVertexBuilder.endSetVertexFormat(wrr);
            int i = wrr.M_588_G() * svertexbuilder.vertexSize;
            for (int j = 0; j < i; j += svertexbuilder.vertexSize * 4) {
                svertexbuilder.calcNormal(wrr, j);
            }
        }
    }

    public static boolean preDrawArrays(b_1213_w vf, ByteBuffer bb) {
        int i = vf.J_1907_R();
        if (i != 72) {
            return false;
        }
        ((Buffer)bb).position(36);
        GL20.glVertexAttribPointer((int)Shaders.midTexCoordAttrib, (int)2, (int)5126, (boolean)false, (int)i, (ByteBuffer)bb);
        ((Buffer)bb).position(44);
        GL20.glVertexAttribPointer((int)Shaders.tangentAttrib, (int)4, (int)5122, (boolean)false, (int)i, (ByteBuffer)bb);
        ((Buffer)bb).position(52);
        GL20.glVertexAttribPointer((int)Shaders.entityAttrib, (int)3, (int)5122, (boolean)false, (int)i, (ByteBuffer)bb);
        ((Buffer)bb).position(60);
        GL20.glVertexAttribPointer((int)Shaders.velocityAttrib, (int)3, (int)5126, (boolean)false, (int)i, (ByteBuffer)bb);
        ((Buffer)bb).position(0);
        GL20.glEnableVertexAttribArray((int)Shaders.midTexCoordAttrib);
        GL20.glEnableVertexAttribArray((int)Shaders.tangentAttrib);
        GL20.glEnableVertexAttribArray((int)Shaders.entityAttrib);
        GL20.glEnableVertexAttribArray((int)Shaders.velocityAttrib);
        return true;
    }

    public static void postDrawArrays() {
        GL20.glDisableVertexAttribArray((int)Shaders.midTexCoordAttrib);
        GL20.glDisableVertexAttribArray((int)Shaders.tangentAttrib);
        GL20.glDisableVertexAttribArray((int)Shaders.entityAttrib);
        GL20.glDisableVertexAttribArray((int)Shaders.velocityAttrib);
    }
}



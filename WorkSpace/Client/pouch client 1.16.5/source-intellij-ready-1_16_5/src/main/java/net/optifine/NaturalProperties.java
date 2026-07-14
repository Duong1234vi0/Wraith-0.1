/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.util.IdentityHashMap;
import java.util.Map;
import lightning.product.B_3871_I;
import lightning.product.b_257_Y;
import lightning.product.c_932_S;
import lightning.product.u_530_F;
import net.optifine.Config;

public class NaturalProperties {
    public int rotation = 1;
    public boolean flip = false;
    private Map[] quadMaps = new Map[8];

    public NaturalProperties(String type) {
        if (type.equals("4")) {
            this.rotation = 4;
        } else if (type.equals("2")) {
            this.rotation = 2;
        } else if (type.equals("F")) {
            this.flip = true;
        } else if (type.equals("4F")) {
            this.rotation = 4;
            this.flip = true;
        } else if (type.equals("2F")) {
            this.rotation = 2;
            this.flip = true;
        } else {
            Config.warn("NaturalTextures: Unknown type: " + type);
        }
    }

    public boolean isValid() {
        if (this.rotation != 2 && this.rotation != 4) {
            return this.flip;
        }
        return true;
    }

    public synchronized c_932_S getQuad(c_932_S quadIn, int rotate, boolean flipU) {
        int i = rotate;
        if (flipU) {
            i = rotate | 4;
        }
        if (i > 0 && i < this.quadMaps.length) {
            c_932_S bakedquad;
            IdentityHashMap<c_932_S, c_932_S> map = this.quadMaps[i];
            if (map == null) {
                this.quadMaps[i] = map = new IdentityHashMap<c_932_S, c_932_S>(1);
            }
            if ((bakedquad = (c_932_S)map.get(quadIn)) == null) {
                bakedquad = this.makeQuad(quadIn, rotate, flipU);
                map.put(quadIn, bakedquad);
            }
            return bakedquad;
        }
        return quadIn;
    }

    private c_932_S makeQuad(c_932_S quad, int rotate, boolean flipU) {
        int[] aint = quad.getVertexData();
        int i = quad.getTintIndex();
        b_257_Y direction = quad.getFace();
        B_3871_I textureatlassprite = quad.getSprite();
        boolean flag = quad.applyDiffuseLighting();
        if (!this.isFullSprite(quad)) {
            rotate = 0;
        }
        aint = this.transformVertexData(aint, rotate, flipU);
        return new c_932_S(aint, i, direction, textureatlassprite, flag);
    }

    private int[] transformVertexData(int[] vertexData, int rotate, boolean flipU) {
        int[] aint = (int[])vertexData.clone();
        int i = 4 - rotate;
        if (flipU) {
            i += 3;
        }
        i %= 4;
        int j = aint.length / 4;
        for (int k = 0; k < 4; ++k) {
            int l = k * j;
            int i1 = i * j;
            aint[i1 + 4] = vertexData[l + 4];
            aint[i1 + 4 + 1] = vertexData[l + 4 + 1];
            if (flipU) {
                if (--i >= 0) continue;
                i = 3;
                continue;
            }
            if (++i <= 3) continue;
            i = 0;
        }
        return aint;
    }

    private boolean isFullSprite(c_932_S quad) {
        B_3871_I textureatlassprite = quad.getSprite();
        float f = textureatlassprite.u_1723_Y();
        float f1 = textureatlassprite.v_4262_N();
        float f2 = f1 - f;
        float f3 = f2 / 256.0f;
        float f4 = textureatlassprite.w_1484_f();
        float f5 = textureatlassprite.t_148_a();
        float f6 = f5 - f4;
        float f7 = f6 / 256.0f;
        int[] aint = quad.getVertexData();
        int i = aint.length / 4;
        for (int j = 0; j < 4; ++j) {
            int k = j * i;
            float f8 = Float.intBitsToFloat(aint[k + 4]);
            float f9 = Float.intBitsToFloat(aint[k + 4 + 1]);
            if (!this.equalsDelta(f8, f, f3) && !this.equalsDelta(f8, f1, f3)) {
                return false;
            }
            if (this.equalsDelta(f9, f4, f7) || this.equalsDelta(f9, f5, f7)) continue;
            return false;
        }
        return true;
    }

    private boolean equalsDelta(float x1, float x2, float deltaMax) {
        float f = u_530_F.P_1922_E(x1 - x2);
        return f < deltaMax;
    }
}


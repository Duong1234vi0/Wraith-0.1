/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import lightning.product.i_2518_W;
import lightning.product.k_4690_i;
import net.optifine.LightMap;
import net.optifine.util.WorldUtils;

public class LightMapPack {
    private LightMap lightMap;
    private LightMap lightMapRain;
    private LightMap lightMapThunder;
    private int[] colorBuffer1 = new int[0];
    private int[] colorBuffer2 = new int[0];
    private int[] lmColorsBuffer = new int[0];

    public LightMapPack(LightMap lightMap, LightMap lightMapRain, LightMap lightMapThunder) {
        if (lightMapRain != null || lightMapThunder != null) {
            if (lightMapRain == null) {
                lightMapRain = lightMap;
            }
            if (lightMapThunder == null) {
                lightMapThunder = lightMapRain;
            }
        }
        this.lightMap = lightMap;
        this.lightMapRain = lightMapRain;
        this.lightMapThunder = lightMapThunder;
    }

    public boolean updateLightmap(k_4690_i world, float torchFlickerX, i_2518_W lmColorsImage, boolean nightvision, float partialTicks) {
        int i = lmColorsImage.n_1700_B() * lmColorsImage.J_1907_R();
        if (this.lmColorsBuffer.length != i) {
            this.lmColorsBuffer = new int[i];
        }
        lmColorsImage.w_1484_f().get(this.lmColorsBuffer);
        boolean flag = this.updateLightmap(world, torchFlickerX, this.lmColorsBuffer, nightvision, partialTicks);
        if (flag) {
            lmColorsImage.w_1484_f().put(this.lmColorsBuffer);
        }
        return flag;
    }

    public boolean updateLightmap(k_4690_i world, float torchFlickerX, int[] lmColors, boolean nightvision, float partialTicks) {
        if (this.lightMapRain == null && this.lightMapThunder == null) {
            return this.lightMap.updateLightmap(world, torchFlickerX, lmColors, nightvision);
        }
        if (!WorldUtils.isEnd(world) && !WorldUtils.isNether(world)) {
            boolean flag1;
            float f = world.w_1484_f(partialTicks);
            float f1 = world.u_1723_Y(partialTicks);
            float f2 = 1.0E-4f;
            boolean flag = f > f2;
            boolean bl = flag1 = f1 > f2;
            if (!flag && !flag1) {
                return this.lightMap.updateLightmap(world, torchFlickerX, lmColors, nightvision);
            }
            if (f > 0.0f) {
                f1 /= f;
            }
            float f3 = 1.0f - f;
            float f4 = f - f1;
            if (this.colorBuffer1.length != lmColors.length) {
                this.colorBuffer1 = new int[lmColors.length];
                this.colorBuffer2 = new int[lmColors.length];
            }
            int i = 0;
            int[][] aint = new int[][]{lmColors, this.colorBuffer1, this.colorBuffer2};
            float[] afloat = new float[3];
            if (f3 > f2 && this.lightMap.updateLightmap(world, torchFlickerX, aint[i], nightvision)) {
                afloat[i] = f3;
                ++i;
            }
            if (f4 > f2 && this.lightMapRain != null && this.lightMapRain.updateLightmap(world, torchFlickerX, aint[i], nightvision)) {
                afloat[i] = f4;
                ++i;
            }
            if (f1 > f2 && this.lightMapThunder != null && this.lightMapThunder.updateLightmap(world, torchFlickerX, aint[i], nightvision)) {
                afloat[i] = f1;
                ++i;
            }
            if (i == 2) {
                return this.blend(aint[0], afloat[0], aint[1], afloat[1]);
            }
            return i == 3 ? this.blend(aint[0], afloat[0], aint[1], afloat[1], aint[2], afloat[2]) : true;
        }
        return this.lightMap.updateLightmap(world, torchFlickerX, lmColors, nightvision);
    }

    private boolean blend(int[] cols0, float br0, int[] cols1, float br1) {
        if (cols1.length != cols0.length) {
            return false;
        }
        for (int i = 0; i < cols0.length; ++i) {
            int j = cols0[i];
            int k = j >> 16 & 0xFF;
            int l = j >> 8 & 0xFF;
            int i1 = j & 0xFF;
            int j1 = cols1[i];
            int k1 = j1 >> 16 & 0xFF;
            int l1 = j1 >> 8 & 0xFF;
            int i2 = j1 & 0xFF;
            int j2 = (int)((float)k * br0 + (float)k1 * br1);
            int k2 = (int)((float)l * br0 + (float)l1 * br1);
            int l2 = (int)((float)i1 * br0 + (float)i2 * br1);
            cols0[i] = 0xFF000000 | j2 << 16 | k2 << 8 | l2;
        }
        return true;
    }

    private boolean blend(int[] cols0, float br0, int[] cols1, float br1, int[] cols2, float br2) {
        if (cols1.length == cols0.length && cols2.length == cols0.length) {
            for (int i = 0; i < cols0.length; ++i) {
                int j = cols0[i];
                int k = j >> 16 & 0xFF;
                int l = j >> 8 & 0xFF;
                int i1 = j & 0xFF;
                int j1 = cols1[i];
                int k1 = j1 >> 16 & 0xFF;
                int l1 = j1 >> 8 & 0xFF;
                int i2 = j1 & 0xFF;
                int j2 = cols2[i];
                int k2 = j2 >> 16 & 0xFF;
                int l2 = j2 >> 8 & 0xFF;
                int i3 = j2 & 0xFF;
                int j3 = (int)((float)k * br0 + (float)k1 * br1 + (float)k2 * br2);
                int k3 = (int)((float)l * br0 + (float)l1 * br1 + (float)l2 * br2);
                int l3 = (int)((float)i1 * br0 + (float)i2 * br1 + (float)i3 * br2);
                cols0[i] = 0xFF000000 | j3 << 16 | k3 << 8 | l3;
            }
            return true;
        }
        return false;
    }
}


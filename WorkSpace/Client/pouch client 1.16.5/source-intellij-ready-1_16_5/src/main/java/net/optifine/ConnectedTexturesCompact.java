/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.util.IdentityHashMap;
import java.util.Map;
import lightning.product.B_3871_I;
import lightning.product.c_932_S;
import net.optifine.ConnectedProperties;
import net.optifine.ConnectedTextures;
import net.optifine.render.RenderEnv;

public class ConnectedTexturesCompact {
    private static final int COMPACT_NONE = 0;
    private static final int COMPACT_ALL = 1;
    private static final int COMPACT_V = 2;
    private static final int COMPACT_H = 3;
    private static final int COMPACT_HV = 4;

    public static c_932_S[] getConnectedTextureCtmCompact(int ctmIndex, ConnectedProperties cp, int side, c_932_S quad, RenderEnv renderEnv) {
        int i;
        if (cp.ctmTileIndexes != null && ctmIndex >= 0 && ctmIndex < cp.ctmTileIndexes.length && (i = cp.ctmTileIndexes[ctmIndex]) >= 0 && i <= cp.tileIcons.length) {
            return ConnectedTexturesCompact.getQuadsCompact(i, cp.tileIcons, quad, renderEnv);
        }
        switch (ctmIndex) {
            case 1: {
                return ConnectedTexturesCompact.getQuadsCompactH(0, 3, cp.tileIcons, side, quad, renderEnv);
            }
            case 2: {
                return ConnectedTexturesCompact.getQuadsCompact(3, cp.tileIcons, quad, renderEnv);
            }
            case 3: {
                return ConnectedTexturesCompact.getQuadsCompactH(3, 0, cp.tileIcons, side, quad, renderEnv);
            }
            case 4: {
                return ConnectedTexturesCompact.getQuadsCompact4(0, 3, 2, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 5: {
                return ConnectedTexturesCompact.getQuadsCompact4(3, 0, 4, 2, cp.tileIcons, side, quad, renderEnv);
            }
            case 6: {
                return ConnectedTexturesCompact.getQuadsCompact4(2, 4, 2, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 7: {
                return ConnectedTexturesCompact.getQuadsCompact4(3, 3, 4, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 8: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 1, 4, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 9: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 4, 4, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 10: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 4, 1, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 11: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 1, 4, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 12: {
                return ConnectedTexturesCompact.getQuadsCompactV(0, 2, cp.tileIcons, side, quad, renderEnv);
            }
            case 13: {
                return ConnectedTexturesCompact.getQuadsCompact4(0, 3, 2, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 14: {
                return ConnectedTexturesCompact.getQuadsCompactV(3, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 15: {
                return ConnectedTexturesCompact.getQuadsCompact4(3, 0, 1, 2, cp.tileIcons, side, quad, renderEnv);
            }
            case 16: {
                return ConnectedTexturesCompact.getQuadsCompact4(2, 4, 0, 3, cp.tileIcons, side, quad, renderEnv);
            }
            case 17: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 2, 3, 0, cp.tileIcons, side, quad, renderEnv);
            }
            case 18: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 4, 3, 3, cp.tileIcons, side, quad, renderEnv);
            }
            case 19: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 2, 4, 2, cp.tileIcons, side, quad, renderEnv);
            }
            case 20: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 4, 4, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 21: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 4, 1, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 22: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 4, 1, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 23: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 1, 4, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 24: {
                return ConnectedTexturesCompact.getQuadsCompact(2, cp.tileIcons, quad, renderEnv);
            }
            case 25: {
                return ConnectedTexturesCompact.getQuadsCompactH(2, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 26: {
                return ConnectedTexturesCompact.getQuadsCompact(1, cp.tileIcons, quad, renderEnv);
            }
            case 27: {
                return ConnectedTexturesCompact.getQuadsCompactH(1, 2, cp.tileIcons, side, quad, renderEnv);
            }
            case 28: {
                return ConnectedTexturesCompact.getQuadsCompact4(2, 4, 2, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 29: {
                return ConnectedTexturesCompact.getQuadsCompact4(3, 3, 1, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 30: {
                return ConnectedTexturesCompact.getQuadsCompact4(2, 1, 2, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 31: {
                return ConnectedTexturesCompact.getQuadsCompact4(3, 3, 4, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 32: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 1, 1, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 33: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 1, 4, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 34: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 1, 1, 4, cp.tileIcons, side, quad, renderEnv);
            }
            case 35: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 4, 4, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 36: {
                return ConnectedTexturesCompact.getQuadsCompactV(2, 0, cp.tileIcons, side, quad, renderEnv);
            }
            case 37: {
                return ConnectedTexturesCompact.getQuadsCompact4(2, 1, 0, 3, cp.tileIcons, side, quad, renderEnv);
            }
            case 38: {
                return ConnectedTexturesCompact.getQuadsCompactV(1, 3, cp.tileIcons, side, quad, renderEnv);
            }
            case 39: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 2, 3, 0, cp.tileIcons, side, quad, renderEnv);
            }
            case 40: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 1, 3, 3, cp.tileIcons, side, quad, renderEnv);
            }
            case 41: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 2, 4, 2, cp.tileIcons, side, quad, renderEnv);
            }
            case 42: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 4, 3, 3, cp.tileIcons, side, quad, renderEnv);
            }
            case 43: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 2, 1, 2, cp.tileIcons, side, quad, renderEnv);
            }
            case 44: {
                return ConnectedTexturesCompact.getQuadsCompact4(1, 4, 1, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 45: {
                return ConnectedTexturesCompact.getQuadsCompact4(4, 1, 1, 1, cp.tileIcons, side, quad, renderEnv);
            }
            case 46: {
                return ConnectedTexturesCompact.getQuadsCompact(4, cp.tileIcons, quad, renderEnv);
            }
        }
        return ConnectedTexturesCompact.getQuadsCompact(0, cp.tileIcons, quad, renderEnv);
    }

    private static c_932_S[] getQuadsCompactH(int indexLeft, int indexRight, B_3871_I[] sprites, int side, c_932_S quad, RenderEnv renderEnv) {
        return ConnectedTexturesCompact.getQuadsCompact(Dir.LEFT, indexLeft, Dir.RIGHT, indexRight, sprites, side, quad, renderEnv);
    }

    private static c_932_S[] getQuadsCompactV(int indexUp, int indexDown, B_3871_I[] sprites, int side, c_932_S quad, RenderEnv renderEnv) {
        return ConnectedTexturesCompact.getQuadsCompact(Dir.UP, indexUp, Dir.DOWN, indexDown, sprites, side, quad, renderEnv);
    }

    private static c_932_S[] getQuadsCompact4(int upLeft, int upRight, int downLeft, int downRight, B_3871_I[] sprites, int side, c_932_S quad, RenderEnv renderEnv) {
        if (upLeft == upRight) {
            return downLeft == downRight ? ConnectedTexturesCompact.getQuadsCompact(Dir.UP, upLeft, Dir.DOWN, downLeft, sprites, side, quad, renderEnv) : ConnectedTexturesCompact.getQuadsCompact(Dir.UP, upLeft, Dir.DOWN_LEFT, downLeft, Dir.DOWN_RIGHT, downRight, sprites, side, quad, renderEnv);
        }
        if (downLeft == downRight) {
            return ConnectedTexturesCompact.getQuadsCompact(Dir.UP_LEFT, upLeft, Dir.UP_RIGHT, upRight, Dir.DOWN, downLeft, sprites, side, quad, renderEnv);
        }
        if (upLeft == downLeft) {
            return upRight == downRight ? ConnectedTexturesCompact.getQuadsCompact(Dir.LEFT, upLeft, Dir.RIGHT, upRight, sprites, side, quad, renderEnv) : ConnectedTexturesCompact.getQuadsCompact(Dir.LEFT, upLeft, Dir.UP_RIGHT, upRight, Dir.DOWN_RIGHT, downRight, sprites, side, quad, renderEnv);
        }
        return upRight == downRight ? ConnectedTexturesCompact.getQuadsCompact(Dir.UP_LEFT, upLeft, Dir.DOWN_LEFT, downLeft, Dir.RIGHT, upRight, sprites, side, quad, renderEnv) : ConnectedTexturesCompact.getQuadsCompact(Dir.UP_LEFT, upLeft, Dir.UP_RIGHT, upRight, Dir.DOWN_LEFT, downLeft, Dir.DOWN_RIGHT, downRight, sprites, side, quad, renderEnv);
    }

    private static c_932_S[] getQuadsCompact(int index, B_3871_I[] sprites, c_932_S quad, RenderEnv renderEnv) {
        B_3871_I textureatlassprite = sprites[index];
        return ConnectedTextures.getQuads(textureatlassprite, quad, renderEnv);
    }

    private static c_932_S[] getQuadsCompact(Dir dir1, int index1, Dir dir2, int index2, B_3871_I[] sprites, int side, c_932_S quad, RenderEnv renderEnv) {
        c_932_S bakedquad = ConnectedTexturesCompact.getQuadCompact(sprites[index1], dir1, side, quad, renderEnv);
        c_932_S bakedquad1 = ConnectedTexturesCompact.getQuadCompact(sprites[index2], dir2, side, quad, renderEnv);
        return renderEnv.getArrayQuadsCtm(bakedquad, bakedquad1);
    }

    private static c_932_S[] getQuadsCompact(Dir dir1, int index1, Dir dir2, int index2, Dir dir3, int index3, B_3871_I[] sprites, int side, c_932_S quad, RenderEnv renderEnv) {
        c_932_S bakedquad = ConnectedTexturesCompact.getQuadCompact(sprites[index1], dir1, side, quad, renderEnv);
        c_932_S bakedquad1 = ConnectedTexturesCompact.getQuadCompact(sprites[index2], dir2, side, quad, renderEnv);
        c_932_S bakedquad2 = ConnectedTexturesCompact.getQuadCompact(sprites[index3], dir3, side, quad, renderEnv);
        return renderEnv.getArrayQuadsCtm(bakedquad, bakedquad1, bakedquad2);
    }

    private static c_932_S[] getQuadsCompact(Dir dir1, int index1, Dir dir2, int index2, Dir dir3, int index3, Dir dir4, int index4, B_3871_I[] sprites, int side, c_932_S quad, RenderEnv renderEnv) {
        c_932_S bakedquad = ConnectedTexturesCompact.getQuadCompact(sprites[index1], dir1, side, quad, renderEnv);
        c_932_S bakedquad1 = ConnectedTexturesCompact.getQuadCompact(sprites[index2], dir2, side, quad, renderEnv);
        c_932_S bakedquad2 = ConnectedTexturesCompact.getQuadCompact(sprites[index3], dir3, side, quad, renderEnv);
        c_932_S bakedquad3 = ConnectedTexturesCompact.getQuadCompact(sprites[index4], dir4, side, quad, renderEnv);
        return renderEnv.getArrayQuadsCtm(bakedquad, bakedquad1, bakedquad2, bakedquad3);
    }

    private static c_932_S getQuadCompact(B_3871_I sprite, Dir dir, int side, c_932_S quad, RenderEnv renderEnv) {
        switch (dir.ordinal()) {
            case 0: {
                return ConnectedTexturesCompact.getQuadCompact(sprite, dir, 0, 0, 16, 8, side, quad, renderEnv);
            }
            case 1: {
                return ConnectedTexturesCompact.getQuadCompact(sprite, dir, 8, 0, 16, 8, side, quad, renderEnv);
            }
            case 2: {
                return ConnectedTexturesCompact.getQuadCompact(sprite, dir, 8, 0, 16, 16, side, quad, renderEnv);
            }
            case 3: {
                return ConnectedTexturesCompact.getQuadCompact(sprite, dir, 8, 8, 16, 16, side, quad, renderEnv);
            }
            case 4: {
                return ConnectedTexturesCompact.getQuadCompact(sprite, dir, 0, 8, 16, 16, side, quad, renderEnv);
            }
            case 5: {
                return ConnectedTexturesCompact.getQuadCompact(sprite, dir, 0, 8, 8, 16, side, quad, renderEnv);
            }
            case 6: {
                return ConnectedTexturesCompact.getQuadCompact(sprite, dir, 0, 0, 8, 16, side, quad, renderEnv);
            }
            case 7: {
                return ConnectedTexturesCompact.getQuadCompact(sprite, dir, 0, 0, 8, 8, side, quad, renderEnv);
            }
        }
        return quad;
    }

    private static c_932_S getQuadCompact(B_3871_I sprite, Dir dir, int x1, int y1, int x2, int y2, int side, c_932_S quadIn, RenderEnv renderEnv) {
        Map[][] amap = ConnectedTextures.getSpriteQuadCompactMaps();
        if (amap == null) {
            return quadIn;
        }
        int i = sprite.t_1786_h();
        if (i >= 0 && i < amap.length) {
            c_932_S bakedquad;
            IdentityHashMap<c_932_S, c_932_S> map;
            Map[] amap1 = amap[i];
            if (amap1 == null) {
                amap1 = new Map[Dir.VALUES.length];
                amap[i] = amap1;
            }
            if ((map = amap1[dir.ordinal()]) == null) {
                amap1[dir.ordinal()] = map = new IdentityHashMap<c_932_S, c_932_S>(1);
            }
            if ((bakedquad = (c_932_S)map.get(quadIn)) == null) {
                bakedquad = ConnectedTexturesCompact.makeSpriteQuadCompact(quadIn, sprite, side, x1, y1, x2, y2);
                map.put(quadIn, bakedquad);
            }
            return bakedquad;
        }
        return quadIn;
    }

    private static c_932_S makeSpriteQuadCompact(c_932_S quad, B_3871_I sprite, int side, int x1, int y1, int x2, int y2) {
        int[] aint = (int[])quad.getVertexData().clone();
        B_3871_I textureatlassprite = quad.getSprite();
        for (int i = 0; i < 4; ++i) {
            ConnectedTexturesCompact.fixVertexCompact(aint, i, textureatlassprite, sprite, side, x1, y1, x2, y2);
        }
        return new c_932_S(aint, quad.getTintIndex(), quad.getFace(), sprite, quad.applyDiffuseLighting());
    }

    private static void fixVertexCompact(int[] data, int vertex, B_3871_I spriteFrom, B_3871_I spriteTo, int side, int x1, int y1, int x2, int y2) {
        float f6;
        float f5;
        int i = data.length / 4;
        int j = i * vertex;
        float f = Float.intBitsToFloat(data[j + 4]);
        float f1 = Float.intBitsToFloat(data[j + 4 + 1]);
        double d0 = spriteFrom.n_1700_B(f);
        double d1 = spriteFrom.J_1907_R(f1);
        float f2 = Float.intBitsToFloat(data[j + 0]);
        float f3 = Float.intBitsToFloat(data[j + 1]);
        float f4 = Float.intBitsToFloat(data[j + 2]);
        switch (side) {
            case 0: {
                f5 = f2;
                f6 = 1.0f - f4;
                break;
            }
            case 1: {
                f5 = f2;
                f6 = f4;
                break;
            }
            case 2: {
                f5 = 1.0f - f2;
                f6 = 1.0f - f3;
                break;
            }
            case 3: {
                f5 = f2;
                f6 = 1.0f - f3;
                break;
            }
            case 4: {
                f5 = f4;
                f6 = 1.0f - f3;
                break;
            }
            case 5: {
                f5 = 1.0f - f4;
                f6 = 1.0f - f3;
                break;
            }
            default: {
                return;
            }
        }
        float f7 = (float)spriteFrom.G_564_y() / (spriteFrom.v_4262_N() - spriteFrom.u_1723_Y());
        float f8 = (float)spriteFrom.P_1922_E() / (spriteFrom.t_148_a() - spriteFrom.w_1484_f());
        float f9 = 4.0f / Math.max(f8, f7);
        float f10 = 16.0f * (1.0f - f9);
        float f11 = 16.0f * (1.0f - f9);
        if (d0 < (double)x1) {
            f5 = (float)((double)f5 + ((double)x1 - d0) / (double)f10);
            d0 = x1;
        }
        if (d0 > (double)x2) {
            f5 = (float)((double)f5 - (d0 - (double)x2) / (double)f10);
            d0 = x2;
        }
        if (d1 < (double)y1) {
            f6 = (float)((double)f6 + ((double)y1 - d1) / (double)f11);
            d1 = y1;
        }
        if (d1 > (double)y2) {
            f6 = (float)((double)f6 - (d1 - (double)y2) / (double)f11);
            d1 = y2;
        }
        switch (side) {
            case 0: {
                f2 = f5;
                f4 = 1.0f - f6;
                break;
            }
            case 1: {
                f2 = f5;
                f4 = f6;
                break;
            }
            case 2: {
                f2 = 1.0f - f5;
                f3 = 1.0f - f6;
                break;
            }
            case 3: {
                f2 = f5;
                f3 = 1.0f - f6;
                break;
            }
            case 4: {
                f4 = f5;
                f3 = 1.0f - f6;
                break;
            }
            case 5: {
                f4 = 1.0f - f5;
                f3 = 1.0f - f6;
                break;
            }
            default: {
                return;
            }
        }
        data[j + 4] = Float.floatToRawIntBits(spriteTo.n_1700_B(d0));
        data[j + 4 + 1] = Float.floatToRawIntBits(spriteTo.J_1907_R(d1));
        data[j + 0] = Float.floatToRawIntBits(f2);
        data[j + 1] = Float.floatToRawIntBits(f3);
        data[j + 2] = Float.floatToRawIntBits(f4);
    }

    private static enum Dir {
        UP,
        UP_RIGHT,
        RIGHT,
        DOWN_RIGHT,
        DOWN,
        DOWN_LEFT,
        LEFT,
        UP_LEFT;

        public static final Dir[] VALUES;

        static {
            VALUES = Dir.values();
        }
    }
}


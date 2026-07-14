/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import lightning.product.B_3871_I;
import lightning.product.ItemTransforms;
import lightning.product.BlockElementFace;
import lightning.product.I_4817_s;
import lightning.product.L_3848_p;
import lightning.product.L_4237_Q;
import lightning.product.L_972_x;
import lightning.product.M_1336_P;
import lightning.product.S_3779_r;
import lightning.product.S_3826_o;
import lightning.product.BlockFaceUV;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.c_932_S;
import lightning.product.d_1062_x;
import lightning.product.g_2336_b;
import lightning.product.ModelManager;
import lightning.product.q_4293_E;
import lightning.product.BlockElementRotation;
import lightning.product.SimpleBakedModel;
import net.optifine.Config;
import net.optifine.model.BakedQuadRetextured;
import net.optifine.model.ModelUtils;

public class BlockModelUtils {
    private static final float VERTEX_COORD_ACCURACY = 1.0E-6f;
    private static final Random RANDOM = new Random(0L);

    public static S_3826_o makeModelCube(String spriteName, int tintIndex) {
        B_3871_I textureatlassprite = Config.getTextureMap().J_1907_R(spriteName);
        return BlockModelUtils.makeModelCube(textureatlassprite, tintIndex);
    }

    public static S_3826_o makeModelCube(B_3871_I sprite, int tintIndex) {
        ArrayList<c_932_S> list = new ArrayList<c_932_S>();
        b_257_Y[] adirection = b_257_Y.v_4262_N;
        HashMap<b_257_Y, List<c_932_S>> map = new HashMap<b_257_Y, List<c_932_S>>();
        for (int i = 0; i < adirection.length; ++i) {
            b_257_Y direction = adirection[i];
            ArrayList<c_932_S> list1 = new ArrayList<c_932_S>();
            list1.add(BlockModelUtils.makeBakedQuad(direction, sprite, tintIndex));
            map.put(direction, list1);
        }
        L_4237_Q itemoverridelist = L_4237_Q.n_1700_B;
        SimpleBakedModel ibakedmodel = new SimpleBakedModel(list, map, true, true, true, sprite, ItemTransforms.n_1700_B, itemoverridelist);
        return ibakedmodel;
    }

    public static S_3826_o joinModelsCube(S_3826_o modelBase, S_3826_o modelAdd) {
        ArrayList<c_932_S> list = new ArrayList<c_932_S>();
        list.addAll(modelBase.n_1700_B(null, null, RANDOM));
        list.addAll(modelAdd.n_1700_B(null, null, RANDOM));
        b_257_Y[] adirection = b_257_Y.v_4262_N;
        HashMap<b_257_Y, List<c_932_S>> map = new HashMap<b_257_Y, List<c_932_S>>();
        for (int i = 0; i < adirection.length; ++i) {
            b_257_Y direction = adirection[i];
            ArrayList<c_932_S> list1 = new ArrayList<c_932_S>();
            list1.addAll(modelBase.n_1700_B(null, direction, RANDOM));
            list1.addAll(modelAdd.n_1700_B(null, direction, RANDOM));
            map.put(direction, list1);
        }
        boolean flag = modelBase.n_1700_B();
        boolean flag1 = modelBase.G_564_y();
        B_3871_I textureatlassprite = modelBase.P_1922_E();
        ItemTransforms itemcameratransforms = modelBase.u_1723_Y();
        L_4237_Q itemoverridelist = modelBase.v_4262_N();
        SimpleBakedModel ibakedmodel = new SimpleBakedModel(list, map, flag, flag1, true, textureatlassprite, itemcameratransforms, itemoverridelist);
        return ibakedmodel;
    }

    public static c_932_S makeBakedQuad(b_257_Y facing, B_3871_I sprite, int tintIndex) {
        M_1336_P vector3f = new M_1336_P(0.0f, 0.0f, 0.0f);
        M_1336_P vector3f1 = new M_1336_P(16.0f, 16.0f, 16.0f);
        BlockFaceUV blockfaceuv = new BlockFaceUV(new float[]{0.0f, 0.0f, 16.0f, 16.0f}, 0);
        BlockElementFace blockpartface = new BlockElementFace(facing, tintIndex, "#" + facing.n_1700_B(), blockfaceuv);
        S_3779_r modelrotation = S_3779_r.n_1700_B;
        BlockElementRotation blockpartrotation = null;
        boolean flag = true;
        g_2336_b resourcelocation = sprite.s_956_w();
        L_972_x facebakery = new L_972_x();
        return facebakery.n_1700_B(vector3f, vector3f1, blockpartface, sprite, facing, modelrotation, blockpartrotation, flag, resourcelocation);
    }

    public static S_3826_o makeModel(String modelName, String spriteOldName, String spriteNewName) {
        L_3848_p atlastexture = Config.getTextureMap();
        B_3871_I textureatlassprite = atlastexture.J_1907_R(spriteOldName);
        B_3871_I textureatlassprite1 = atlastexture.J_1907_R(spriteNewName);
        return BlockModelUtils.makeModel(modelName, textureatlassprite, textureatlassprite1);
    }

    public static S_3826_o makeModel(String modelName, B_3871_I spriteOld, B_3871_I spriteNew) {
        if (spriteOld != null && spriteNew != null) {
            ModelManager modelmanager = Config.getModelManager();
            if (modelmanager == null) {
                return null;
            }
            d_1062_x modelresourcelocation = new d_1062_x(modelName, "");
            S_3826_o ibakedmodel = modelmanager.n_1700_B(modelresourcelocation);
            if (ibakedmodel != null && ibakedmodel != modelmanager.J_1907_R()) {
                S_3826_o ibakedmodel1 = ModelUtils.duplicateModel(ibakedmodel);
                b_257_Y[] adirection = b_257_Y.v_4262_N;
                for (int i = 0; i < adirection.length; ++i) {
                    b_257_Y direction = adirection[i];
                    List<c_932_S> list = ibakedmodel1.n_1700_B(null, direction, RANDOM);
                    BlockModelUtils.replaceTexture(list, spriteOld, spriteNew);
                }
                List<c_932_S> list1 = ibakedmodel1.n_1700_B(null, null, RANDOM);
                BlockModelUtils.replaceTexture(list1, spriteOld, spriteNew);
                return ibakedmodel1;
            }
            return null;
        }
        return null;
    }

    private static void replaceTexture(List<c_932_S> quads, B_3871_I spriteOld, B_3871_I spriteNew) {
        ArrayList<c_932_S> list = new ArrayList<c_932_S>();
        for (c_932_S bakedquad : quads) {
            if (bakedquad.getSprite() == spriteOld) {
                bakedquad = new BakedQuadRetextured(bakedquad, spriteNew);
            }
            list.add(bakedquad);
        }
        quads.clear();
        quads.addAll(list);
    }

    public static void snapVertexPosition(M_1336_P pos) {
        pos.J_1907_R(BlockModelUtils.snapVertexCoord(pos.n_1700_B()), BlockModelUtils.snapVertexCoord(pos.J_1907_R()), BlockModelUtils.snapVertexCoord(pos.R_4764_Y()));
    }

    private static float snapVertexCoord(float x) {
        if (x > -1.0E-6f && x < 1.0E-6f) {
            return 0.0f;
        }
        return x > 0.999999f && x < 1.000001f ? 1.0f : x;
    }

    public static I_4817_s getOffsetBoundingBox(I_4817_s aabb, q_4293_E.G_564_y offsetType, c_1514_x pos) {
        int i = pos.getX();
        int j = pos.getZ();
        long k = (long)(i * 3129871) ^ (long)j * 116129781L;
        k = k * k * 42317861L + k * 11L;
        double d0 = ((double)((float)(k >> 16 & 0xFL) / 15.0f) - 0.5) * 0.5;
        double d1 = ((double)((float)(k >> 24 & 0xFL) / 15.0f) - 0.5) * 0.5;
        double d2 = 0.0;
        if (offsetType == q_4293_E.G_564_y.R_4764_Y) {
            d2 = ((double)((float)(k >> 20 & 0xFL) / 15.0f) - 1.0) * 0.2;
        }
        return aabb.offset(d0, d2, d1);
    }
}



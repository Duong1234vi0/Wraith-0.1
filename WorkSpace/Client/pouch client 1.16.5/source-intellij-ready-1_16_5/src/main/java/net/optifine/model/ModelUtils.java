/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import lightning.product.S_3826_o;
import lightning.product.b_257_Y;
import lightning.product.c_932_S;
import lightning.product.SimpleBakedModel;
import net.optifine.Config;

public class ModelUtils {
    private static final Random RANDOM = new Random(0L);

    public static void dbgModel(S_3826_o model) {
        if (model != null) {
            Config.dbg("Model: " + String.valueOf(model) + ", ao: " + model.n_1700_B() + ", gui3d: " + model.J_1907_R() + ", builtIn: " + model.G_564_y() + ", particle: " + String.valueOf(model.P_1922_E()));
            b_257_Y[] adirection = b_257_Y.v_4262_N;
            for (int i = 0; i < adirection.length; ++i) {
                b_257_Y direction = adirection[i];
                List<c_932_S> list = model.n_1700_B(null, direction, RANDOM);
                ModelUtils.dbgQuads(direction.n_1700_B(), list, "  ");
            }
            List<c_932_S> list1 = model.n_1700_B(null, null, RANDOM);
            ModelUtils.dbgQuads("General", list1, "  ");
        }
    }

    private static void dbgQuads(String name, List<c_932_S> quads, String prefix) {
        for (c_932_S bakedquad : quads) {
            ModelUtils.dbgQuad(name, bakedquad, prefix);
        }
    }

    public static void dbgQuad(String name, c_932_S quad, String prefix) {
        Config.dbg(prefix + "Quad: " + quad.getClass().getName() + ", type: " + name + ", face: " + String.valueOf(quad.getFace()) + ", tint: " + quad.getTintIndex() + ", sprite: " + String.valueOf(quad.getSprite()));
        ModelUtils.dbgVertexData(quad.getVertexData(), "  " + prefix);
    }

    public static void dbgVertexData(int[] vd, String prefix) {
        int i = vd.length / 4;
        Config.dbg(prefix + "Length: " + vd.length + ", step: " + i);
        for (int j = 0; j < 4; ++j) {
            int k = j * i;
            float f = Float.intBitsToFloat(vd[k + 0]);
            float f1 = Float.intBitsToFloat(vd[k + 1]);
            float f2 = Float.intBitsToFloat(vd[k + 2]);
            int l = vd[k + 3];
            float f3 = Float.intBitsToFloat(vd[k + 4]);
            float f4 = Float.intBitsToFloat(vd[k + 5]);
            Config.dbg(prefix + j + " xyz: " + f + "," + f1 + "," + f2 + " col: " + l + " u,v: " + f3 + "," + f4);
        }
    }

    public static S_3826_o duplicateModel(S_3826_o model) {
        List list = ModelUtils.duplicateQuadList(model.n_1700_B(null, null, RANDOM));
        b_257_Y[] adirection = b_257_Y.v_4262_N;
        HashMap<b_257_Y, List<c_932_S>> map = new HashMap<b_257_Y, List<c_932_S>>();
        for (int i = 0; i < adirection.length; ++i) {
            b_257_Y direction = adirection[i];
            List<c_932_S> list1 = model.n_1700_B(null, direction, RANDOM);
            List list2 = ModelUtils.duplicateQuadList(list1);
            map.put(direction, list2);
        }
        return new SimpleBakedModel(list, map, model.n_1700_B(), model.J_1907_R(), true, model.P_1922_E(), model.u_1723_Y(), model.v_4262_N());
    }

    public static List duplicateQuadList(List<c_932_S> list) {
        ArrayList<c_932_S> list2 = new ArrayList<c_932_S>();
        for (c_932_S bakedquad : list) {
            c_932_S bakedquad1 = ModelUtils.duplicateQuad(bakedquad);
            list2.add(bakedquad1);
        }
        return list2;
    }

    public static c_932_S duplicateQuad(c_932_S quad) {
        return new c_932_S((int[])quad.getVertexData().clone(), quad.getTintIndex(), quad.getFace(), quad.getSprite(), quad.applyDiffuseLighting());
    }
}



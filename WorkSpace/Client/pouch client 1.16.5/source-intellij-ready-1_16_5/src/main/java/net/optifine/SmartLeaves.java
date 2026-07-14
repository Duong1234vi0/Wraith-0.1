/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lightning.product.K_4074_S;
import lightning.product.S_3826_o;
import lightning.product.T_2915_h;
import lightning.product.b_257_Y;
import lightning.product.c_932_S;
import lightning.product.d_1062_x;
import lightning.product.g_2336_b;
import lightning.product.ModelManager;
import net.optifine.Config;
import net.optifine.model.ModelUtils;

public class SmartLeaves {
    private static S_3826_o modelLeavesCullAcacia = null;
    private static S_3826_o modelLeavesCullBirch = null;
    private static S_3826_o modelLeavesCullDarkOak = null;
    private static S_3826_o modelLeavesCullJungle = null;
    private static S_3826_o modelLeavesCullOak = null;
    private static S_3826_o modelLeavesCullSpruce = null;
    private static List generalQuadsCullAcacia = null;
    private static List generalQuadsCullBirch = null;
    private static List generalQuadsCullDarkOak = null;
    private static List generalQuadsCullJungle = null;
    private static List generalQuadsCullOak = null;
    private static List generalQuadsCullSpruce = null;
    private static S_3826_o modelLeavesDoubleAcacia = null;
    private static S_3826_o modelLeavesDoubleBirch = null;
    private static S_3826_o modelLeavesDoubleDarkOak = null;
    private static S_3826_o modelLeavesDoubleJungle = null;
    private static S_3826_o modelLeavesDoubleOak = null;
    private static S_3826_o modelLeavesDoubleSpruce = null;
    private static final Random RANDOM = new Random();

    public static S_3826_o getLeavesModel(S_3826_o model, K_4074_S stateIn) {
        if (!Config.isTreesSmart()) {
            return model;
        }
        List<c_932_S> list = model.n_1700_B(stateIn, null, RANDOM);
        if (list == generalQuadsCullAcacia) {
            return modelLeavesDoubleAcacia;
        }
        if (list == generalQuadsCullBirch) {
            return modelLeavesDoubleBirch;
        }
        if (list == generalQuadsCullDarkOak) {
            return modelLeavesDoubleDarkOak;
        }
        if (list == generalQuadsCullJungle) {
            return modelLeavesDoubleJungle;
        }
        if (list == generalQuadsCullOak) {
            return modelLeavesDoubleOak;
        }
        return list == generalQuadsCullSpruce ? modelLeavesDoubleSpruce : model;
    }

    public static boolean isSameLeaves(K_4074_S state1, K_4074_S state2) {
        T_2915_h block1;
        if (state1 == state2) {
            return true;
        }
        T_2915_h block = state1.J_1907_R();
        return block == (block1 = state2.J_1907_R());
    }

    public static void updateLeavesModels() {
        ArrayList list = new ArrayList();
        modelLeavesCullAcacia = SmartLeaves.getModelCull("acacia", list);
        modelLeavesCullBirch = SmartLeaves.getModelCull("birch", list);
        modelLeavesCullDarkOak = SmartLeaves.getModelCull("dark_oak", list);
        modelLeavesCullJungle = SmartLeaves.getModelCull("jungle", list);
        modelLeavesCullOak = SmartLeaves.getModelCull("oak", list);
        modelLeavesCullSpruce = SmartLeaves.getModelCull("spruce", list);
        generalQuadsCullAcacia = SmartLeaves.getGeneralQuadsSafe(modelLeavesCullAcacia);
        generalQuadsCullBirch = SmartLeaves.getGeneralQuadsSafe(modelLeavesCullBirch);
        generalQuadsCullDarkOak = SmartLeaves.getGeneralQuadsSafe(modelLeavesCullDarkOak);
        generalQuadsCullJungle = SmartLeaves.getGeneralQuadsSafe(modelLeavesCullJungle);
        generalQuadsCullOak = SmartLeaves.getGeneralQuadsSafe(modelLeavesCullOak);
        generalQuadsCullSpruce = SmartLeaves.getGeneralQuadsSafe(modelLeavesCullSpruce);
        modelLeavesDoubleAcacia = SmartLeaves.getModelDoubleFace(modelLeavesCullAcacia);
        modelLeavesDoubleBirch = SmartLeaves.getModelDoubleFace(modelLeavesCullBirch);
        modelLeavesDoubleDarkOak = SmartLeaves.getModelDoubleFace(modelLeavesCullDarkOak);
        modelLeavesDoubleJungle = SmartLeaves.getModelDoubleFace(modelLeavesCullJungle);
        modelLeavesDoubleOak = SmartLeaves.getModelDoubleFace(modelLeavesCullOak);
        modelLeavesDoubleSpruce = SmartLeaves.getModelDoubleFace(modelLeavesCullSpruce);
        if (list.size() > 0) {
            Config.dbg("Enable face culling: " + Config.arrayToString(list.toArray()));
        }
    }

    private static List getGeneralQuadsSafe(S_3826_o model) {
        return model == null ? null : model.n_1700_B(null, null, RANDOM);
    }

    static S_3826_o getModelCull(String type, List updatedTypes) {
        ModelManager modelmanager = Config.getModelManager();
        if (modelmanager == null) {
            return null;
        }
        g_2336_b resourcelocation = new g_2336_b("blockstates/" + type + "_leaves.json");
        if (!Config.isFromDefaultResourcePack(resourcelocation)) {
            return null;
        }
        g_2336_b resourcelocation1 = new g_2336_b("models/block/" + type + "_leaves.json");
        if (!Config.isFromDefaultResourcePack(resourcelocation1)) {
            return null;
        }
        d_1062_x modelresourcelocation = new d_1062_x(type + "_leaves", "normal");
        S_3826_o ibakedmodel = modelmanager.n_1700_B(modelresourcelocation);
        if (ibakedmodel != null && ibakedmodel != modelmanager.J_1907_R()) {
            List<c_932_S> list = ibakedmodel.n_1700_B(null, null, RANDOM);
            if (list.size() == 0) {
                return ibakedmodel;
            }
            if (list.size() != 6) {
                return null;
            }
            for (c_932_S bakedquad : list) {
                List<c_932_S> list1 = ibakedmodel.n_1700_B(null, bakedquad.getFace(), RANDOM);
                if (list1.size() > 0) {
                    return null;
                }
                list1.add(bakedquad);
            }
            list.clear();
            updatedTypes.add(type + "_leaves");
            return ibakedmodel;
        }
        return null;
    }

    private static S_3826_o getModelDoubleFace(S_3826_o model) {
        if (model == null) {
            return null;
        }
        if (model.n_1700_B(null, null, RANDOM).size() > 0) {
            Config.warn("SmartLeaves: Model is not cube, general quads: " + model.n_1700_B(null, null, RANDOM).size() + ", model: " + String.valueOf(model));
            return model;
        }
        b_257_Y[] adirection = b_257_Y.v_4262_N;
        for (int i = 0; i < adirection.length; ++i) {
            b_257_Y direction = adirection[i];
            List<c_932_S> list = model.n_1700_B(null, direction, RANDOM);
            if (list.size() == 1) continue;
            Config.warn("SmartLeaves: Model is not cube, side: " + String.valueOf(direction) + ", quads: " + list.size() + ", model: " + String.valueOf(model));
            return model;
        }
        S_3826_o ibakedmodel = ModelUtils.duplicateModel(model);
        List[] alist = new List[adirection.length];
        for (int k = 0; k < adirection.length; ++k) {
            b_257_Y direction1 = adirection[k];
            List<c_932_S> list1 = ibakedmodel.n_1700_B(null, direction1, RANDOM);
            c_932_S bakedquad = list1.get(0);
            c_932_S bakedquad1 = new c_932_S((int[])bakedquad.getVertexData().clone(), bakedquad.getTintIndex(), bakedquad.getFace(), bakedquad.getSprite(), bakedquad.applyDiffuseLighting());
            int[] aint = bakedquad1.getVertexData();
            int[] aint1 = (int[])aint.clone();
            int j = aint.length / 4;
            System.arraycopy(aint, 0 * j, aint1, 3 * j, j);
            System.arraycopy(aint, 1 * j, aint1, 2 * j, j);
            System.arraycopy(aint, 2 * j, aint1, 1 * j, j);
            System.arraycopy(aint, 3 * j, aint1, 0 * j, j);
            System.arraycopy(aint1, 0, aint, 0, aint1.length);
            list1.add(bakedquad1);
        }
        return ibakedmodel;
    }
}



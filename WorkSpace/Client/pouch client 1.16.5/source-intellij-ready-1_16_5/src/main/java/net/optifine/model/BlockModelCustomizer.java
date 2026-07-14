/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package net.optifine.model;

import com.google.common.collect.ImmutableList;
import java.util.List;
import lightning.product.K_4074_S;
import lightning.product.S_3826_o;
import lightning.product.BlockAndTintGetter;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.c_932_S;
import lightning.product.o_2576_A;
import net.optifine.BetterGrass;
import net.optifine.Config;
import net.optifine.ConnectedTextures;
import net.optifine.NaturalTextures;
import net.optifine.SmartLeaves;
import net.optifine.render.RenderEnv;
import net.optifine.render.RenderTypes;

public class BlockModelCustomizer {
    private static final List<c_932_S> NO_QUADS = ImmutableList.of();

    public static S_3826_o getRenderModel(S_3826_o modelIn, K_4074_S stateIn, RenderEnv renderEnv) {
        if (renderEnv.isSmartLeaves()) {
            modelIn = SmartLeaves.getLeavesModel(modelIn, stateIn);
        }
        return modelIn;
    }

    public static List<c_932_S> getRenderQuads(List<c_932_S> quads, BlockAndTintGetter worldIn, K_4074_S stateIn, c_1514_x posIn, b_257_Y enumfacing, o_2576_A layer, long rand, RenderEnv renderEnv) {
        if (enumfacing != null) {
            if (renderEnv.isSmartLeaves() && SmartLeaves.isSameLeaves(worldIn.getBlockState(posIn.offset(enumfacing)), stateIn)) {
                return NO_QUADS;
            }
            if (!renderEnv.isBreakingAnimation(quads) && Config.isBetterGrass()) {
                quads = BetterGrass.getFaceQuads(worldIn, stateIn, posIn, enumfacing, quads);
            }
        }
        List<c_932_S> list = renderEnv.getListQuadsCustomizer();
        list.clear();
        for (int i = 0; i < quads.size(); ++i) {
            c_932_S bakedquad = quads.get(i);
            c_932_S[] abakedquad = BlockModelCustomizer.getRenderQuads(bakedquad, worldIn, stateIn, posIn, enumfacing, rand, renderEnv);
            if (i == 0 && quads.size() == 1 && abakedquad.length == 1 && abakedquad[0] == bakedquad && bakedquad.getQuadEmissive() == null) {
                return quads;
            }
            for (int j = 0; j < abakedquad.length; ++j) {
                c_932_S bakedquad1 = abakedquad[j];
                list.add(bakedquad1);
                if (bakedquad1.getQuadEmissive() == null) continue;
                renderEnv.getListQuadsOverlay(BlockModelCustomizer.getEmissiveLayer(layer)).addQuad(bakedquad1.getQuadEmissive(), stateIn);
                renderEnv.setOverlaysRendered(true);
            }
        }
        return list;
    }

    private static o_2576_A getEmissiveLayer(o_2576_A layer) {
        return layer != null && layer != RenderTypes.SOLID ? layer : RenderTypes.CUTOUT_MIPPED;
    }

    private static c_932_S[] getRenderQuads(c_932_S quad, BlockAndTintGetter worldIn, K_4074_S stateIn, c_1514_x posIn, b_257_Y enumfacing, long rand, RenderEnv renderEnv) {
        c_932_S[] abakedquad;
        if (renderEnv.isBreakingAnimation(quad)) {
            return renderEnv.getArrayQuadsCtm(quad);
        }
        c_932_S bakedquad = quad;
        if (Config.isConnectedTextures() && ((abakedquad = ConnectedTextures.getConnectedTexture(worldIn, stateIn, posIn, quad, renderEnv)).length != 1 || abakedquad[0] != quad)) {
            return abakedquad;
        }
        if (Config.isNaturalTextures() && (quad = NaturalTextures.getNaturalTexture(posIn, quad)) != bakedquad) {
            return renderEnv.getArrayQuadsCtm(quad);
        }
        return renderEnv.getArrayQuadsCtm(quad);
    }
}



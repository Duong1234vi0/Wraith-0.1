/*
 * Decompiled with CFR 0.152.
 */
package net.minecraftforge.client.extensions;

import java.util.List;
import java.util.Random;
import lightning.product.B_3871_I;
import lightning.product.ItemTransforms;
import lightning.product.K_4074_S;
import lightning.product.S_3826_o;
import lightning.product.BlockAndTintGetter;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.c_932_S;
import lightning.product.g_221_o;
import net.minecraftforge.client.model.data.IModelData;
import net.optifine.reflect.Reflector;

public interface IForgeBakedModel {
    default public S_3826_o getBakedModel() {
        return (S_3826_o)this;
    }

    default public List<c_932_S> getQuads(K_4074_S state, b_257_Y side, Random rand, IModelData extraData) {
        return this.getBakedModel().n_1700_B(state, side, rand);
    }

    default public boolean isAmbientOcclusion(K_4074_S state) {
        return this.getBakedModel().n_1700_B();
    }

    default public S_3826_o handlePerspective(ItemTransforms.J_1907_R cameraTransformType, g_221_o mat) {
        return (S_3826_o)Reflector.ForgeHooksClient_handlePerspective.call(new Object[]{this.getBakedModel(), cameraTransformType, mat});
    }

    default public IModelData getModelData(BlockAndTintGetter world, c_1514_x pos, K_4074_S state, IModelData tileData) {
        return tileData;
    }

    default public B_3871_I getParticleTexture(IModelData data) {
        return this.getBakedModel().P_1922_E();
    }

    default public boolean isLayered() {
        return false;
    }
}



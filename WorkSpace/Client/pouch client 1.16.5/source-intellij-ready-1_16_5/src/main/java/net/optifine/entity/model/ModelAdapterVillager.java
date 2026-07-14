/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.ReloadableResourceManager;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.VillagerModel;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import lightning.product.VillagerRenderer;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterVillager
extends ModelAdapter {
    public ModelAdapterVillager() {
        super(t_5_h.RealmsDefaultUncaughtExceptionHandler, "villager", 0.5f);
    }

    protected ModelAdapterVillager(t_5_h type, String name, float shadowSize) {
        super(type, name, shadowSize);
    }

    @Override
    public v_3569_v makeModel() {
        return new VillagerModel(0.0f);
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof VillagerModel)) {
            return null;
        }
        VillagerModel villagermodel = (VillagerModel)model;
        if (modelPart.equals("head")) {
            return (e_4189_z)Reflector.ModelVillager_ModelRenderers.getValue(villagermodel, 0);
        }
        if (modelPart.equals("headwear")) {
            return (e_4189_z)Reflector.ModelVillager_ModelRenderers.getValue(villagermodel, 1);
        }
        if (modelPart.equals("headwear2")) {
            return (e_4189_z)Reflector.ModelVillager_ModelRenderers.getValue(villagermodel, 2);
        }
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.ModelVillager_ModelRenderers.getValue(villagermodel, 3);
        }
        if (modelPart.equals("bodywear")) {
            return (e_4189_z)Reflector.ModelVillager_ModelRenderers.getValue(villagermodel, 4);
        }
        if (modelPart.equals("arms")) {
            return (e_4189_z)Reflector.ModelVillager_ModelRenderers.getValue(villagermodel, 5);
        }
        if (modelPart.equals("right_leg")) {
            return (e_4189_z)Reflector.ModelVillager_ModelRenderers.getValue(villagermodel, 6);
        }
        if (modelPart.equals("left_leg")) {
            return (e_4189_z)Reflector.ModelVillager_ModelRenderers.getValue(villagermodel, 7);
        }
        return modelPart.equals("nose") ? (e_4189_z)Reflector.ModelVillager_ModelRenderers.getValue(villagermodel, 8) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "headwear", "headwear2", "body", "bodywear", "arms", "right_leg", "left_leg", "nose"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        ReloadableResourceManager ireloadableresourcemanager = (ReloadableResourceManager)MinecraftClient.A_4115_X().T_2506_i();
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        VillagerRenderer villagerrenderer = new VillagerRenderer(entityrenderermanager, ireloadableresourcemanager);
        villagerrenderer.v_4262_N = (VillagerModel)modelBase;
        villagerrenderer.R_4764_Y = shadowSize;
        return villagerrenderer;
    }
}




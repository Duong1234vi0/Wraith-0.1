/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.IllagerModel;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public abstract class ModelAdapterIllager
extends ModelAdapter {
    public ModelAdapterIllager(t_5_h type, String name, float shadowSize) {
        super(type, name, shadowSize);
    }

    public ModelAdapterIllager(t_5_h type, String name, float shadowSize, String[] aliases) {
        super(type, name, shadowSize, aliases);
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        e_4189_z modelrenderer;
        if (!(model instanceof IllagerModel)) {
            return null;
        }
        IllagerModel illagermodel = (IllagerModel)model;
        if (modelPart.equals("head")) {
            return (e_4189_z)Reflector.ModelIllager_ModelRenderers.getValue(illagermodel, 0);
        }
        if (modelPart.equals("hat")) {
            return (e_4189_z)Reflector.ModelIllager_ModelRenderers.getValue(illagermodel, 1);
        }
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.ModelIllager_ModelRenderers.getValue(illagermodel, 2);
        }
        if (modelPart.equals("arms")) {
            return (e_4189_z)Reflector.ModelIllager_ModelRenderers.getValue(illagermodel, 3);
        }
        if (modelPart.equals("right_leg")) {
            return (e_4189_z)Reflector.ModelIllager_ModelRenderers.getValue(illagermodel, 4);
        }
        if (modelPart.equals("left_leg")) {
            return (e_4189_z)Reflector.ModelIllager_ModelRenderers.getValue(illagermodel, 5);
        }
        if (modelPart.equals("nose") && (modelrenderer = (e_4189_z)Reflector.ModelIllager_ModelRenderers.getValue(illagermodel, 0)) != null) {
            return modelrenderer.n_1700_B(0);
        }
        if (modelPart.equals("right_arm")) {
            return (e_4189_z)Reflector.ModelIllager_ModelRenderers.getValue(illagermodel, 6);
        }
        return modelPart.equals("left_arm") ? (e_4189_z)Reflector.ModelIllager_ModelRenderers.getValue(illagermodel, 7) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "hat", "body", "arms", "right_leg", "left_leg", "nose", "right_arm", "left_arm"};
    }
}



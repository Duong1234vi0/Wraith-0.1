/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.QuadrupedModel;
import lightning.product.e_4189_z;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public abstract class ModelAdapterQuadruped
extends ModelAdapter {
    public ModelAdapterQuadruped(t_5_h type, String name, float shadowSize) {
        super(type, name, shadowSize);
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof QuadrupedModel)) {
            return null;
        }
        QuadrupedModel quadrupedmodel = (QuadrupedModel)model;
        if (modelPart.equals("head")) {
            return (e_4189_z)Reflector.ModelQuadruped_ModelRenderers.getValue(quadrupedmodel, 0);
        }
        if (modelPart.equals("body")) {
            return (e_4189_z)Reflector.ModelQuadruped_ModelRenderers.getValue(quadrupedmodel, 1);
        }
        if (modelPart.equals("leg1")) {
            return (e_4189_z)Reflector.ModelQuadruped_ModelRenderers.getValue(quadrupedmodel, 2);
        }
        if (modelPart.equals("leg2")) {
            return (e_4189_z)Reflector.ModelQuadruped_ModelRenderers.getValue(quadrupedmodel, 3);
        }
        if (modelPart.equals("leg3")) {
            return (e_4189_z)Reflector.ModelQuadruped_ModelRenderers.getValue(quadrupedmodel, 4);
        }
        return modelPart.equals("leg4") ? (e_4189_z)Reflector.ModelQuadruped_ModelRenderers.getValue(quadrupedmodel, 5) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "body", "leg1", "leg2", "leg3", "leg4"};
    }
}



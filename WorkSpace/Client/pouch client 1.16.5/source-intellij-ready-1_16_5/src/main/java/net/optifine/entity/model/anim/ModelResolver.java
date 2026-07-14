/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model.anim;

import lightning.product.e_4189_z;
import lightning.product.BlockEntityType;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import net.optifine.Config;
import net.optifine.entity.model.CustomModelRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.entity.model.anim.IModelResolver;
import net.optifine.entity.model.anim.IRenderResolver;
import net.optifine.entity.model.anim.ModelVariableFloat;
import net.optifine.entity.model.anim.ModelVariableType;
import net.optifine.entity.model.anim.RenderResolverEntity;
import net.optifine.entity.model.anim.RenderResolverTileEntity;
import net.optifine.expr.IExpression;
import net.optifine.util.Either;

public class ModelResolver
implements IModelResolver {
    private ModelAdapter modelAdapter;
    private v_3569_v model;
    private CustomModelRenderer[] customModelRenderers;
    private e_4189_z thisModelRenderer;
    private e_4189_z partModelRenderer;
    private IRenderResolver renderResolver;

    public ModelResolver(ModelAdapter modelAdapter, v_3569_v model, CustomModelRenderer[] customModelRenderers) {
        this.modelAdapter = modelAdapter;
        this.model = model;
        this.customModelRenderers = customModelRenderers;
        Either<t_5_h, BlockEntityType> either = modelAdapter.getType();
        this.renderResolver = either.getRight().isPresent() ? new RenderResolverTileEntity() : new RenderResolverEntity();
    }

    @Override
    public IExpression getExpression(String name) {
        ModelVariableFloat iexpression = this.getModelVariable(name);
        if (iexpression != null) {
            return iexpression;
        }
        IExpression iexpression1 = this.renderResolver.getParameter(name);
        return iexpression1 != null ? iexpression1 : null;
    }

    @Override
    public e_4189_z getModelRenderer(String name) {
        if (name == null) {
            return null;
        }
        if (name.indexOf(":") >= 0) {
            String[] astring = Config.tokenize(name, ":");
            e_4189_z modelrenderer3 = this.getModelRenderer(astring[0]);
            for (int j = 1; j < astring.length; ++j) {
                String s = astring[j];
                e_4189_z modelrenderer4 = modelrenderer3.R_4764_Y(s);
                if (modelrenderer4 == null) {
                    return null;
                }
                modelrenderer3 = modelrenderer4;
            }
            return modelrenderer3;
        }
        if (this.thisModelRenderer != null && name.equals("this")) {
            return this.thisModelRenderer;
        }
        if (this.partModelRenderer != null && name.equals("part")) {
            return this.partModelRenderer;
        }
        e_4189_z modelrenderer = this.modelAdapter.getModelRenderer(this.model, name);
        if (modelrenderer != null) {
            return modelrenderer;
        }
        for (int i = 0; i < this.customModelRenderers.length; ++i) {
            CustomModelRenderer custommodelrenderer = this.customModelRenderers[i];
            e_4189_z modelrenderer1 = custommodelrenderer.getModelRenderer();
            if (name.equals(modelrenderer1.R_4764_Y())) {
                return modelrenderer1;
            }
            e_4189_z modelrenderer2 = modelrenderer1.R_4764_Y(name);
            if (modelrenderer2 == null) continue;
            return modelrenderer2;
        }
        return null;
    }

    @Override
    public ModelVariableFloat getModelVariable(String name) {
        String[] astring = Config.tokenize(name, ".");
        if (astring.length != 2) {
            return null;
        }
        String s = astring[0];
        String s1 = astring[1];
        e_4189_z modelrenderer = this.getModelRenderer(s);
        if (modelrenderer == null) {
            return null;
        }
        ModelVariableType modelvariabletype = ModelVariableType.parse(s1);
        return modelvariabletype == null ? null : new ModelVariableFloat(name, modelrenderer, modelvariabletype);
    }

    public void setPartModelRenderer(e_4189_z partModelRenderer) {
        this.partModelRenderer = partModelRenderer;
    }

    public void setThisModelRenderer(e_4189_z thisModelRenderer) {
        this.thisModelRenderer = thisModelRenderer;
    }
}



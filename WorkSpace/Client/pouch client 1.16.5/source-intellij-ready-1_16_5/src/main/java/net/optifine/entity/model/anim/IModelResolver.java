/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model.anim;

import lightning.product.e_4189_z;
import net.optifine.entity.model.anim.ModelVariableFloat;
import net.optifine.expr.IExpressionResolver;

public interface IModelResolver
extends IExpressionResolver {
    public e_4189_z getModelRenderer(String var1);

    public ModelVariableFloat getModelVariable(String var1);
}


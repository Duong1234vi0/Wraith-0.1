/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model.anim;

import lightning.product.e_4189_z;
import net.optifine.entity.model.anim.ModelVariableType;
import net.optifine.expr.IExpressionFloat;

public class ModelVariableFloat
implements IExpressionFloat {
    private String name;
    private e_4189_z modelRenderer;
    private ModelVariableType enumModelVariable;

    public ModelVariableFloat(String name, e_4189_z modelRenderer, ModelVariableType enumModelVariable) {
        this.name = name;
        this.modelRenderer = modelRenderer;
        this.enumModelVariable = enumModelVariable;
    }

    @Override
    public float eval() {
        return this.getValue();
    }

    public float getValue() {
        return this.enumModelVariable.getFloat(this.modelRenderer);
    }

    public void setValue(float value) {
        this.enumModelVariable.setFloat(this.modelRenderer, value);
    }

    public String toString() {
        return this.name;
    }
}


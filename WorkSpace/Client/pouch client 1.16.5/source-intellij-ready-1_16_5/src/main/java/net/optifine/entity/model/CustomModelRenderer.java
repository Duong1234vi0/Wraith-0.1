/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.e_4189_z;
import net.optifine.entity.model.anim.ModelUpdater;

public class CustomModelRenderer {
    private String modelPart;
    private boolean attach;
    private e_4189_z modelRenderer;
    private ModelUpdater modelUpdater;

    public CustomModelRenderer(String modelPart, boolean attach, e_4189_z modelRenderer, ModelUpdater modelUpdater) {
        this.modelPart = modelPart;
        this.attach = attach;
        this.modelRenderer = modelRenderer;
        this.modelUpdater = modelUpdater;
    }

    public e_4189_z getModelRenderer() {
        return this.modelRenderer;
    }

    public String getModelPart() {
        return this.modelPart;
    }

    public boolean isAttach() {
        return this.attach;
    }

    public ModelUpdater getModelUpdater() {
        return this.modelUpdater;
    }
}


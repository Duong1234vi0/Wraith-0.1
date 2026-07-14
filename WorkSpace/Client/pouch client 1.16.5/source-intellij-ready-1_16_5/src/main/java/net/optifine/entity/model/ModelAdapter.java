/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import java.util.ArrayList;
import lightning.product.e_4189_z;
import lightning.product.BlockEntityType;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.util.Either;

public abstract class ModelAdapter {
    private Either<t_5_h, BlockEntityType> type;
    private String name;
    private float shadowSize;
    private String[] aliases;

    public ModelAdapter(t_5_h entityType, String name, float shadowSize) {
        this(Either.makeLeft(entityType), name, shadowSize, (String[])null);
    }

    public ModelAdapter(t_5_h entityType, String name, float shadowSize, String[] aliases) {
        this(Either.makeLeft(entityType), name, shadowSize, aliases);
    }

    public ModelAdapter(BlockEntityType tileEntityType, String name, float shadowSize) {
        this(Either.makeRight(tileEntityType), name, shadowSize, (String[])null);
    }

    public ModelAdapter(BlockEntityType tileEntityType, String name, float shadowSize, String[] aliases) {
        this(Either.makeRight(tileEntityType), name, shadowSize, aliases);
    }

    public ModelAdapter(Either<t_5_h, BlockEntityType> type, String name, float shadowSize, String[] aliases) {
        this.type = type;
        this.name = name;
        this.shadowSize = shadowSize;
        this.aliases = aliases;
    }

    public Either<t_5_h, BlockEntityType> getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String[] getAliases() {
        return this.aliases;
    }

    public float getShadowSize() {
        return this.shadowSize;
    }

    public abstract v_3569_v makeModel();

    public abstract e_4189_z getModelRenderer(v_3569_v var1, String var2);

    public abstract String[] getModelRendererNames();

    public abstract IEntityRenderer makeEntityRender(v_3569_v var1, float var2);

    public e_4189_z[] getModelRenderers(v_3569_v model) {
        String[] astring = this.getModelRendererNames();
        ArrayList<e_4189_z> list = new ArrayList<e_4189_z>();
        for (int i = 0; i < astring.length; ++i) {
            String s = astring[i];
            e_4189_z modelrenderer = this.getModelRenderer(model, s);
            if (modelrenderer == null) continue;
            list.add(modelrenderer);
        }
        return list.toArray(new e_4189_z[list.size()]);
    }
}



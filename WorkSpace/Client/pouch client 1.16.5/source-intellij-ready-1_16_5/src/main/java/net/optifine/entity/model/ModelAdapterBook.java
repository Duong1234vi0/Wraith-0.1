/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model;

import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.BookModel;
import lightning.product.l_1802_R;
import lightning.product.BlockEntityType;
import lightning.product.EnchantTableRenderer;
import lightning.product.v_3569_v;
import net.optifine.Config;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterBook
extends ModelAdapter {
    public ModelAdapterBook() {
        super(BlockEntityType.M_588_G, "enchanting_book", 0.0f, new String[]{"book"});
    }

    @Override
    public v_3569_v makeModel() {
        return new BookModel();
    }

    @Override
    public e_4189_z getModelRenderer(v_3569_v model, String modelPart) {
        if (!(model instanceof BookModel)) {
            return null;
        }
        BookModel bookmodel = (BookModel)model;
        if (modelPart.equals("cover_right")) {
            return (e_4189_z)Reflector.ModelBook_ModelRenderers.getValue(bookmodel, 0);
        }
        if (modelPart.equals("cover_left")) {
            return (e_4189_z)Reflector.ModelBook_ModelRenderers.getValue(bookmodel, 1);
        }
        if (modelPart.equals("pages_right")) {
            return (e_4189_z)Reflector.ModelBook_ModelRenderers.getValue(bookmodel, 2);
        }
        if (modelPart.equals("pages_left")) {
            return (e_4189_z)Reflector.ModelBook_ModelRenderers.getValue(bookmodel, 3);
        }
        if (modelPart.equals("flipping_page_right")) {
            return (e_4189_z)Reflector.ModelBook_ModelRenderers.getValue(bookmodel, 4);
        }
        if (modelPart.equals("flipping_page_left")) {
            return (e_4189_z)Reflector.ModelBook_ModelRenderers.getValue(bookmodel, 5);
        }
        return modelPart.equals("book_spine") ? (e_4189_z)Reflector.ModelBook_ModelRenderers.getValue(bookmodel, 6) : null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"cover_right", "cover_left", "pages_right", "pages_left", "flipping_page_right", "flipping_page_left", "book_spine"};
    }

    @Override
    public IEntityRenderer makeEntityRender(v_3569_v modelBase, float shadowSize) {
        f_2689_h tileentityrendererdispatcher = f_2689_h.J_1907_R;
        l_1802_R tileentityrenderer = tileentityrendererdispatcher.n_1700_B(BlockEntityType.M_588_G);
        if (!(tileentityrenderer instanceof EnchantTableRenderer)) {
            return null;
        }
        if (tileentityrenderer.getType() == null) {
            tileentityrenderer = new EnchantTableRenderer(tileentityrendererdispatcher);
        }
        if (!Reflector.TileEntityEnchantmentTableRenderer_modelBook.exists()) {
            Config.warn("Field not found: TileEntityEnchantmentTableRenderer.modelBook");
            return null;
        }
        Reflector.setFieldValue(tileentityrenderer, Reflector.TileEntityEnchantmentTableRenderer_modelBook, modelBase);
        return tileentityrenderer;
    }
}



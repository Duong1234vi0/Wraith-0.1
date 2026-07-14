/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  it.unimi.dsi.fastutil.objects.ObjectList
 */
package net.optifine.entity.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import it.unimi.dsi.fastutil.objects.ObjectList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.W_3959_H;
import lightning.product.SkullBlock;
import lightning.product.ThrownTridentRenderer;
import lightning.product.Z_2049_e;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.f_2689_h;
import lightning.product.g_2336_b;
import lightning.product.BookModel;
import lightning.product.l_1802_R;
import lightning.product.o_4479_Q;
import lightning.product.TridentModel;
import lightning.product.BlockEntityType;
import lightning.product.EnchantTableRenderer;
import lightning.product.t_5_h;
import lightning.product.v_3569_v;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.entity.model.CustomEntityModelParser;
import net.optifine.entity.model.CustomEntityRenderer;
import net.optifine.entity.model.CustomModelRegistry;
import net.optifine.entity.model.CustomModelRenderer;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.entity.model.anim.ModelResolver;
import net.optifine.entity.model.anim.ModelUpdater;
import net.optifine.reflect.Reflector;
import net.optifine.util.Either;

public class CustomEntityModels {
    private static boolean active = false;
    private static Map<t_5_h, Z_2049_e> originalEntityRenderMap = null;
    private static Map<BlockEntityType, l_1802_R> originalTileEntityRenderMap = null;
    private static Map<SkullBlock.n_1700_B, v_3569_v> originalSkullModelMap = null;
    private static List<BlockEntityType> customTileEntityTypes = new ArrayList<BlockEntityType>();

    public static void update() {
        Map<t_5_h, Z_2049_e> map = CustomEntityModels.getEntityRenderMap();
        Map<BlockEntityType, l_1802_R> map1 = CustomEntityModels.getTileEntityRenderMap();
        Map<SkullBlock.n_1700_B, v_3569_v> map2 = CustomEntityModels.getSkullModelMap();
        if (map == null) {
            Config.warn("Entity render map not found, custom entity models are DISABLED.");
        } else if (map1 == null) {
            Config.warn("Tile entity render map not found, custom entity models are DISABLED.");
        } else {
            active = false;
            map.clear();
            map1.clear();
            map2.clear();
            customTileEntityTypes.clear();
            map.putAll(originalEntityRenderMap);
            map1.putAll(originalTileEntityRenderMap);
            map2.putAll(originalSkullModelMap);
            W_3959_H.n_1700_B.J_1907_R = new TridentModel();
            CustomEntityModels.setEnchantmentScreenBookModel(new BookModel());
            if (Config.isCustomEntityModels()) {
                g_2336_b[] aresourcelocation = CustomEntityModels.getModelLocations();
                for (int i = 0; i < aresourcelocation.length; ++i) {
                    g_2336_b resourcelocation = aresourcelocation[i];
                    Config.dbg("CustomEntityModel: " + resourcelocation.J_1907_R());
                    IEntityRenderer ientityrenderer = CustomEntityModels.parseEntityRender(resourcelocation);
                    if (ientityrenderer == null) continue;
                    Either<t_5_h, BlockEntityType> either = ientityrenderer.getType();
                    if (ientityrenderer instanceof Z_2049_e) {
                        ThrownTridentRenderer tridentrenderer;
                        TridentModel tridentmodel;
                        map.put(either.getLeft().get(), (Z_2049_e)ientityrenderer);
                        if (ientityrenderer instanceof ThrownTridentRenderer && (tridentmodel = (TridentModel)Reflector.getFieldValue(tridentrenderer = (ThrownTridentRenderer)ientityrenderer, Reflector.RenderTrident_modelTrident)) != null) {
                            W_3959_H.n_1700_B.J_1907_R = tridentmodel;
                        }
                    } else if (ientityrenderer instanceof l_1802_R) {
                        map1.put(either.getRight().get(), (l_1802_R)ientityrenderer);
                        if (ientityrenderer instanceof EnchantTableRenderer) {
                            EnchantTableRenderer enchantmenttabletileentityrenderer = (EnchantTableRenderer)ientityrenderer;
                            BookModel bookmodel = (BookModel)Reflector.getFieldValue(enchantmenttabletileentityrenderer, Reflector.TileEntityEnchantmentTableRenderer_modelBook);
                            CustomEntityModels.setEnchantmentScreenBookModel(bookmodel);
                        }
                        customTileEntityTypes.add(either.getRight().get());
                    } else {
                        Config.warn("Unknown renderer type: " + ientityrenderer.getClass().getName());
                    }
                    active = true;
                }
            }
        }
    }

    private static void setEnchantmentScreenBookModel(BookModel bookModel) {
        BookModel bookmodel = (BookModel)Reflector.GuiEnchantment_bookModel.getValue();
        if (bookmodel != null && bookModel != null) {
            if (!Reflector.ModelBook_ModelRenderers.exists()) {
                return;
            }
            if (!Reflector.ModelBook_bookParts.exists()) {
                return;
            }
            int i = Reflector.ModelBook_ModelRenderers.getFieldCount();
            for (int j = 0; j < i; ++j) {
                e_4189_z modelrenderer = (e_4189_z)Reflector.ModelBook_ModelRenderers.getValue(bookModel, j);
                Reflector.ModelBook_ModelRenderers.setValue(bookmodel, j, modelrenderer);
            }
            List list = (List)Reflector.ModelBook_bookParts.getValue(bookModel);
            Reflector.ModelBook_bookParts.setValue(bookmodel, list);
            bookmodel.textureWidth = bookModel.textureWidth;
            bookmodel.textureHeight = bookModel.textureHeight;
        }
    }

    private static Map<t_5_h, Z_2049_e> getEntityRenderMap() {
        w_2040_b entityrenderermanager = MinecraftClient.A_4115_X().O_508_d();
        Map<t_5_h, Z_2049_e> map = entityrenderermanager.P_1922_E();
        if (map == null) {
            return null;
        }
        if (originalEntityRenderMap == null) {
            originalEntityRenderMap = new HashMap<t_5_h, Z_2049_e>(map);
        }
        return map;
    }

    private static Map<BlockEntityType, l_1802_R> getTileEntityRenderMap() {
        Map<BlockEntityType, l_1802_R> map = f_2689_h.J_1907_R.n_1700_B;
        if (originalTileEntityRenderMap == null) {
            originalTileEntityRenderMap = new HashMap<BlockEntityType, l_1802_R>(map);
        }
        return map;
    }

    private static Map<SkullBlock.n_1700_B, v_3569_v> getSkullModelMap() {
        HashMap map = (HashMap)Reflector.TileEntitySkullRenderer_MODELS.getValue();
        if (map == null) {
            Config.warn("Field not found: TileEntitySkullRenderer.MODELS");
            map = new HashMap();
        }
        if (originalSkullModelMap == null) {
            originalSkullModelMap = new HashMap<SkullBlock.n_1700_B, v_3569_v>(map);
        }
        return map;
    }

    private static g_2336_b[] getModelLocations() {
        String s = "optifine/cem/";
        String s1 = ".jem";
        ArrayList<g_2336_b> list = new ArrayList<g_2336_b>();
        String[] astring = CustomModelRegistry.getModelNames();
        for (int i = 0; i < astring.length; ++i) {
            String s2 = astring[i];
            String s3 = s + s2 + s1;
            g_2336_b resourcelocation = new g_2336_b(s3);
            if (!Config.hasResource(resourcelocation)) continue;
            list.add(resourcelocation);
        }
        return list.toArray(new g_2336_b[list.size()]);
    }

    private static IEntityRenderer parseEntityRender(g_2336_b location) {
        try {
            JsonObject jsonobject = CustomEntityModelParser.loadJson(location);
            return CustomEntityModels.parseEntityRender(jsonobject, location.J_1907_R());
        }
        catch (IOException ioexception) {
            Config.error(ioexception.getClass().getName() + ": " + ioexception.getMessage());
            return null;
        }
        catch (JsonParseException jsonparseexception) {
            Config.error(((Object)((Object)jsonparseexception)).getClass().getName() + ": " + jsonparseexception.getMessage());
            return null;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private static IEntityRenderer parseEntityRender(JsonObject obj, String path) {
        CustomEntityRenderer customentityrenderer = CustomEntityModelParser.parseEntityRender(obj, path);
        String s = customentityrenderer.getName();
        ModelAdapter modeladapter = CustomModelRegistry.getModelAdapter(s);
        CustomEntityModels.checkNull(modeladapter, "Entity not found: " + s);
        Either<t_5_h, BlockEntityType> either = modeladapter.getType();
        CustomEntityModels.checkNull(either, "Entity type not found: " + s);
        IEntityRenderer ientityrenderer = CustomEntityModels.makeEntityRender(modeladapter, customentityrenderer);
        if (ientityrenderer == null) {
            return null;
        }
        ientityrenderer.setType(either);
        return ientityrenderer;
    }

    private static IEntityRenderer makeEntityRender(ModelAdapter modelAdapter, CustomEntityRenderer cer) {
        v_3569_v model;
        g_2336_b resourcelocation = cer.getTextureLocation();
        CustomModelRenderer[] acustommodelrenderer = cer.getCustomModelRenderers();
        float f = cer.getShadowSize();
        if (f < 0.0f) {
            f = modelAdapter.getShadowSize();
        }
        if ((model = modelAdapter.makeModel()) == null) {
            return null;
        }
        ModelResolver modelresolver = new ModelResolver(modelAdapter, model, acustommodelrenderer);
        if (!CustomEntityModels.modifyModel(modelAdapter, model, acustommodelrenderer, modelresolver)) {
            return null;
        }
        IEntityRenderer ientityrenderer = modelAdapter.makeEntityRender(model, f);
        if (ientityrenderer == null) {
            throw new JsonParseException("Entity renderer is null, model: " + modelAdapter.getName() + ", adapter: " + modelAdapter.getClass().getName());
        }
        if (resourcelocation != null) {
            CustomEntityModels.setTextureLocation(modelAdapter, model, ientityrenderer, resourcelocation);
        }
        return ientityrenderer;
    }

    private static void setTextureLocation(ModelAdapter modelAdapter, v_3569_v model, IEntityRenderer er, g_2336_b textureLocation) {
        if (er instanceof o_4479_Q) {
            er.setLocationTextureCustom(textureLocation);
        } else {
            String[] astring = modelAdapter.getModelRendererNames();
            for (int i = 0; i < astring.length; ++i) {
                String s = astring[i];
                e_4189_z modelrenderer = modelAdapter.getModelRenderer(model, s);
                if (modelrenderer == null || modelrenderer.J_1907_R() != null) continue;
                modelrenderer.n_1700_B(textureLocation);
            }
        }
    }

    private static boolean modifyModel(ModelAdapter modelAdapter, v_3569_v model, CustomModelRenderer[] modelRenderers, ModelResolver mr) {
        for (int i = 0; i < modelRenderers.length; ++i) {
            CustomModelRenderer custommodelrenderer = modelRenderers[i];
            if (CustomEntityModels.modifyModel(modelAdapter, model, custommodelrenderer, mr)) continue;
            return false;
        }
        return true;
    }

    private static boolean modifyModel(ModelAdapter modelAdapter, v_3569_v model, CustomModelRenderer customModelRenderer, ModelResolver modelResolver) {
        String s = customModelRenderer.getModelPart();
        e_4189_z modelrenderer = modelAdapter.getModelRenderer(model, s);
        if (modelrenderer == null) {
            Config.warn("Model part not found: " + s + ", model: " + String.valueOf(model));
            return false;
        }
        if (!customModelRenderer.isAttach()) {
            if (modelrenderer.u_2550_I != null) {
                modelrenderer.u_2550_I.clear();
            }
            if (modelrenderer.P_4830_p != null) {
                modelrenderer.P_4830_p.clear();
            }
            if (modelrenderer.M_588_G != null) {
                e_4189_z[] amodelrenderer = modelAdapter.getModelRenderers(model);
                Set set = Collections.newSetFromMap(new IdentityHashMap());
                set.addAll(Arrays.asList(amodelrenderer));
                ObjectList<e_4189_z> list = modelrenderer.M_588_G;
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    e_4189_z modelrenderer1 = (e_4189_z)iterator.next();
                    if (set.contains(modelrenderer1)) continue;
                    iterator.remove();
                }
            }
        }
        modelrenderer.J_1907_R(customModelRenderer.getModelRenderer());
        ModelUpdater modelupdater = customModelRenderer.getModelUpdater();
        if (modelupdater != null) {
            modelResolver.setThisModelRenderer(customModelRenderer.getModelRenderer());
            modelResolver.setPartModelRenderer(modelrenderer);
            if (!modelupdater.initialize(modelResolver)) {
                return false;
            }
            customModelRenderer.getModelRenderer().n_1700_B(modelupdater);
        }
        return true;
    }

    private static void checkNull(Object obj, String msg) {
        if (obj == null) {
            throw new JsonParseException(msg);
        }
    }

    public static boolean isActive() {
        return active;
    }

    public static boolean isCustomModel(K_4074_S blockStateIn) {
        T_2915_h block = blockStateIn.J_1907_R();
        for (int i = 0; i < customTileEntityTypes.size(); ++i) {
            BlockEntityType tileentitytype = customTileEntityTypes.get(i);
            if (!tileentitytype.n_1700_B(block)) continue;
            return true;
        }
        return false;
    }
}




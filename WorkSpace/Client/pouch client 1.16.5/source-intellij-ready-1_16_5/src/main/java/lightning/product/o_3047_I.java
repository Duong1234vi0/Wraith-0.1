/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.annotations.VisibleForTesting
 *  com.google.common.base.Joiner
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.mojang.datafixers.util.Either
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.B_1814_Y;
import lightning.product.B_3871_I;
import lightning.product.BlockElement;
import lightning.product.F_3565_Q;
import lightning.product.ItemTransforms;
import lightning.product.BlockElementFace;
import lightning.product.ItemTransform;
import lightning.product.L_3848_p;
import lightning.product.L_4237_Q;
import lightning.product.L_972_x;
import lightning.product.S_3826_o;
import lightning.product.T_2910_P;
import lightning.product.BlockFaceUV;
import lightning.product.b_257_Y;
import lightning.product.c_932_S;
import lightning.product.g_2336_b;
import lightning.product.g_2561_p;
import lightning.product.BuiltInModel;
import lightning.product.i_4431_W;
import lightning.product.ItemOverride;
import lightning.product.SimpleBakedModel;
import lightning.product.UnbakedModel;
import lightning.product.ModelState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class o_3047_I
implements UnbakedModel {
    private static final Logger u_1723_Y = LogManager.getLogger();
    private static final L_972_x v_4262_N = new L_972_x();
    @VisibleForTesting
    static final Gson n_1700_B = new GsonBuilder().registerTypeAdapter(o_3047_I.class, (Object)new n_1700_B()).registerTypeAdapter(BlockElement.class, (Object)new BlockElement.n_1700_B()).registerTypeAdapter(BlockElementFace.class, (Object)new BlockElementFace.n_1700_B()).registerTypeAdapter(BlockFaceUV.class, (Object)new BlockFaceUV.n_1700_B()).registerTypeAdapter(ItemTransform.class, (Object)new ItemTransform.n_1700_B()).registerTypeAdapter(ItemTransforms.class, (Object)new ItemTransforms.n_1700_B()).registerTypeAdapter(ItemOverride.class, (Object)new ItemOverride.n_1700_B()).create();
    private final List<BlockElement> w_1484_f;
    @Nullable
    private final J_1907_R t_148_a;
    private final boolean s_956_w;
    private final ItemTransforms u_2550_I;
    private final List<ItemOverride> M_588_G;
    public String J_1907_R = "";
    @VisibleForTesting
    protected final Map<String, Either<T_2910_P, String>> R_4764_Y;
    @Nullable
    protected o_3047_I G_564_y;
    @Nullable
    protected g_2336_b P_1922_E;

    public static o_3047_I n_1700_B(Reader readerIn) {
        return i_4431_W.n_1700_B(n_1700_B, readerIn, o_3047_I.class);
    }

    public static o_3047_I n_1700_B(String jsonString) {
        return o_3047_I.n_1700_B(new StringReader(jsonString));
    }

    public o_3047_I(@Nullable g_2336_b parentLocation, List<BlockElement> elements, Map<String, Either<T_2910_P, String>> textures, boolean ambientOcclusion, @Nullable J_1907_R guiLight3d, ItemTransforms cameraTransforms, List<ItemOverride> overrides) {
        this.w_1484_f = elements;
        this.s_956_w = ambientOcclusion;
        this.t_148_a = guiLight3d;
        this.R_4764_Y = textures;
        this.P_1922_E = parentLocation;
        this.u_2550_I = cameraTransforms;
        this.M_588_G = overrides;
    }

    public List<BlockElement> n_1700_B() {
        return this.w_1484_f.isEmpty() && this.G_564_y != null ? this.G_564_y.n_1700_B() : this.w_1484_f;
    }

    public boolean J_1907_R() {
        return this.G_564_y != null ? this.G_564_y.J_1907_R() : this.s_956_w;
    }

    public J_1907_R R_4764_Y() {
        if (this.t_148_a != null) {
            return this.t_148_a;
        }
        return this.G_564_y != null ? this.G_564_y.R_4764_Y() : lightning.product.o_3047_I$J_1907_R.J_1907_R;
    }

    public List<ItemOverride> G_564_y() {
        return this.M_588_G;
    }

    private L_4237_Q n_1700_B(g_2561_p modelBakeryIn, o_3047_I modelIn) {
        return this.M_588_G.isEmpty() ? L_4237_Q.n_1700_B : new L_4237_Q(modelBakeryIn, modelIn, modelBakeryIn::n_1700_B, this.M_588_G);
    }

    @Override
    public Collection<g_2336_b> P_1922_E() {
        HashSet set = Sets.newHashSet();
        for (ItemOverride itemoverride : this.M_588_G) {
            set.add(itemoverride.n_1700_B());
        }
        if (this.P_1922_E != null) {
            set.add(this.P_1922_E);
        }
        return set;
    }

    @Override
    public Collection<T_2910_P> n_1700_B(Function<g_2336_b, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        LinkedHashSet set = Sets.newLinkedHashSet();
        o_3047_I blockmodel = this;
        while (blockmodel.P_1922_E != null && blockmodel.G_564_y == null) {
            set.add(blockmodel);
            UnbakedModel iunbakedmodel = modelGetter.apply(blockmodel.P_1922_E);
            if (iunbakedmodel == null) {
                u_1723_Y.warn("No parent '{}' while loading model '{}'", (Object)this.P_1922_E, (Object)blockmodel);
            }
            if (set.contains(iunbakedmodel)) {
                u_1723_Y.warn("Found 'parent' loop while loading model '{}' in chain: {} -> {}", (Object)blockmodel, (Object)set.stream().map(Object::toString).collect(Collectors.joining(" -> ")), (Object)this.P_1922_E);
                iunbakedmodel = null;
            }
            if (iunbakedmodel == null) {
                blockmodel.P_1922_E = g_2561_p.M_588_G;
                iunbakedmodel = modelGetter.apply(blockmodel.P_1922_E);
            }
            if (!(iunbakedmodel instanceof o_3047_I)) {
                throw new IllegalStateException("BlockModel parent has to be a block model.");
            }
            blockmodel = blockmodel.G_564_y = (o_3047_I)iunbakedmodel;
        }
        HashSet set1 = Sets.newHashSet((Object[])new T_2910_P[]{this.R_4764_Y("particle")});
        for (BlockElement blockpart : this.n_1700_B()) {
            for (BlockElementFace blockpartface : blockpart.R_4764_Y.values()) {
                T_2910_P rendermaterial = this.R_4764_Y(blockpartface.R_4764_Y);
                if (Objects.equals(rendermaterial.J_1907_R(), F_3565_Q.n_1700_B())) {
                    missingTextureErrors.add((Pair<String, String>)Pair.of((Object)blockpartface.R_4764_Y, (Object)this.J_1907_R));
                }
                set1.add(rendermaterial);
            }
        }
        this.M_588_G.forEach(override -> {
            UnbakedModel iunbakedmodel1 = (UnbakedModel)modelGetter.apply(override.n_1700_B());
            if (!Objects.equals(iunbakedmodel1, this)) {
                set1.addAll(iunbakedmodel1.n_1700_B(modelGetter, missingTextureErrors));
            }
        });
        if (this.u_1723_Y() == g_2561_p.h_1847_R) {
            B_1814_Y.n_1700_B.forEach(layerName -> set1.add(this.R_4764_Y((String)layerName)));
        }
        return set1;
    }

    @Override
    public S_3826_o n_1700_B(g_2561_p modelBakeryIn, Function<T_2910_P, B_3871_I> spriteGetterIn, ModelState transformIn, g_2336_b locationIn) {
        return this.n_1700_B(modelBakeryIn, this, spriteGetterIn, transformIn, locationIn, true);
    }

    public S_3826_o n_1700_B(g_2561_p modelBakeryIn, o_3047_I modelIn, Function<T_2910_P, B_3871_I> spriteGetterIn, ModelState transformIn, g_2336_b locationIn, boolean guiLight3d) {
        B_3871_I textureatlassprite = spriteGetterIn.apply(this.R_4764_Y("particle"));
        if (this.u_1723_Y() == g_2561_p.Q_4569_t) {
            return new BuiltInModel(this.v_4262_N(), this.n_1700_B(modelBakeryIn, modelIn), textureatlassprite, this.R_4764_Y().n_1700_B());
        }
        SimpleBakedModel.n_1700_B simplebakedmodel$builder = new SimpleBakedModel.n_1700_B(this, this.n_1700_B(modelBakeryIn, modelIn), guiLight3d).n_1700_B(textureatlassprite);
        for (BlockElement blockpart : this.n_1700_B()) {
            for (b_257_Y direction : blockpart.R_4764_Y.keySet()) {
                BlockElementFace blockpartface = blockpart.R_4764_Y.get(direction);
                B_3871_I textureatlassprite1 = spriteGetterIn.apply(this.R_4764_Y(blockpartface.R_4764_Y));
                if (blockpartface.n_1700_B == null) {
                    simplebakedmodel$builder.n_1700_B(o_3047_I.n_1700_B(blockpart, blockpartface, textureatlassprite1, direction, transformIn, locationIn));
                    continue;
                }
                simplebakedmodel$builder.n_1700_B(b_257_Y.n_1700_B(transformIn.n_1700_B().R_4764_Y(), blockpartface.n_1700_B), o_3047_I.n_1700_B(blockpart, blockpartface, textureatlassprite1, direction, transformIn, locationIn));
            }
        }
        return simplebakedmodel$builder.n_1700_B();
    }

    private static c_932_S n_1700_B(BlockElement partIn, BlockElementFace partFaceIn, B_3871_I spriteIn, b_257_Y directionIn, ModelState transformIn, g_2336_b locationIn) {
        return v_4262_N.n_1700_B(partIn.n_1700_B, partIn.J_1907_R, partFaceIn, spriteIn, directionIn, transformIn, partIn.G_564_y, partIn.P_1922_E, locationIn);
    }

    public boolean J_1907_R(String textureName) {
        return !F_3565_Q.n_1700_B().equals(this.R_4764_Y(textureName).J_1907_R());
    }

    public T_2910_P R_4764_Y(String nameIn) {
        if (o_3047_I.P_1922_E(nameIn)) {
            nameIn = nameIn.substring(1);
        }
        ArrayList list = Lists.newArrayList();
        Either<T_2910_P, String> either;
        Optional optional;
        while (!(optional = (either = this.G_564_y(nameIn)).left()).isPresent()) {
            nameIn = (String)either.right().get();
            if (list.contains(nameIn)) {
                u_1723_Y.warn("Unable to resolve texture due to reference chain {}->{} in {}", (Object)Joiner.on((String)"->").join((Iterable)list), (Object)nameIn, (Object)this.J_1907_R);
                return new T_2910_P(L_3848_p.n_1700_B, F_3565_Q.n_1700_B());
            }
            list.add(nameIn);
        }
        return (T_2910_P)optional.get();
    }

    private Either<T_2910_P, String> G_564_y(String nameIn) {
        o_3047_I blockmodel = this;
        while (blockmodel != null) {
            Either<T_2910_P, String> either = blockmodel.R_4764_Y.get(nameIn);
            if (either != null) {
                return either;
            }
            blockmodel = blockmodel.G_564_y;
        }
        return Either.left((Object)new T_2910_P(L_3848_p.n_1700_B, F_3565_Q.n_1700_B()));
    }

    private static boolean P_1922_E(String strIn) {
        return strIn.charAt(0) == '#';
    }

    public o_3047_I u_1723_Y() {
        return this.G_564_y == null ? this : this.G_564_y.u_1723_Y();
    }

    public ItemTransforms v_4262_N() {
        ItemTransform itemtransformvec3f = this.n_1700_B(ItemTransforms.J_1907_R.J_1907_R);
        ItemTransform itemtransformvec3f1 = this.n_1700_B(ItemTransforms.J_1907_R.R_4764_Y);
        ItemTransform itemtransformvec3f2 = this.n_1700_B(ItemTransforms.J_1907_R.G_564_y);
        ItemTransform itemtransformvec3f3 = this.n_1700_B(ItemTransforms.J_1907_R.P_1922_E);
        ItemTransform itemtransformvec3f4 = this.n_1700_B(ItemTransforms.J_1907_R.u_1723_Y);
        ItemTransform itemtransformvec3f5 = this.n_1700_B(ItemTransforms.J_1907_R.v_4262_N);
        ItemTransform itemtransformvec3f6 = this.n_1700_B(ItemTransforms.J_1907_R.w_1484_f);
        ItemTransform itemtransformvec3f7 = this.n_1700_B(ItemTransforms.J_1907_R.t_148_a);
        return new ItemTransforms(itemtransformvec3f, itemtransformvec3f1, itemtransformvec3f2, itemtransformvec3f3, itemtransformvec3f4, itemtransformvec3f5, itemtransformvec3f6, itemtransformvec3f7);
    }

    private ItemTransform n_1700_B(ItemTransforms.J_1907_R type) {
        return this.G_564_y != null && !this.u_2550_I.J_1907_R(type) ? this.G_564_y.n_1700_B(type) : this.u_2550_I.n_1700_B(type);
    }

    public String toString() {
        return this.J_1907_R;
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R("front");
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R("side");
        private final String R_4764_Y;
        private static final /* synthetic */ J_1907_R[] G_564_y;

        public static J_1907_R[] values() {
            return (J_1907_R[])G_564_y.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(String name) {
            this.R_4764_Y = name;
        }

        public static J_1907_R n_1700_B(String name) {
            for (J_1907_R blockmodel$guilight : lightning.product.o_3047_I$J_1907_R.values()) {
                if (!blockmodel$guilight.R_4764_Y.equals(name)) continue;
                return blockmodel$guilight;
            }
            throw new IllegalArgumentException("Invalid gui light: " + name);
        }

        public boolean n_1700_B() {
            return this == J_1907_R;
        }

        private static /* synthetic */ J_1907_R[] J_1907_R() {
            return new J_1907_R[]{n_1700_B, J_1907_R};
        }

        static {
            G_564_y = lightning.product.o_3047_I$J_1907_R.J_1907_R();
        }
    }

    public static class n_1700_B
    implements JsonDeserializer<o_3047_I> {
        public o_3047_I n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
            List<BlockElement> list = this.J_1907_R(p_deserialize_3_, jsonobject);
            String s = this.R_4764_Y(jsonobject);
            Map<String, Either<T_2910_P, String>> map = this.J_1907_R(jsonobject);
            boolean flag = this.n_1700_B(jsonobject);
            ItemTransforms itemcameratransforms = ItemTransforms.n_1700_B;
            if (jsonobject.has("display")) {
                JsonObject jsonobject1 = i_4431_W.M_588_G(jsonobject, "display");
                itemcameratransforms = (ItemTransforms)p_deserialize_3_.deserialize((JsonElement)jsonobject1, ItemTransforms.class);
            }
            List<ItemOverride> list1 = this.n_1700_B(p_deserialize_3_, jsonobject);
            J_1907_R blockmodel$guilight = null;
            if (jsonobject.has("gui_light")) {
                blockmodel$guilight = lightning.product.o_3047_I$J_1907_R.n_1700_B(i_4431_W.u_1723_Y(jsonobject, "gui_light"));
            }
            g_2336_b resourcelocation = s.isEmpty() ? null : new g_2336_b(s);
            return new o_3047_I(resourcelocation, list, map, flag, blockmodel$guilight, itemcameratransforms, list1);
        }

        protected List<ItemOverride> n_1700_B(JsonDeserializationContext deserializationContext, JsonObject object) {
            ArrayList list = Lists.newArrayList();
            if (object.has("overrides")) {
                for (JsonElement jsonelement : i_4431_W.P_4830_p(object, "overrides")) {
                    list.add((ItemOverride)deserializationContext.deserialize(jsonelement, ItemOverride.class));
                }
            }
            return list;
        }

        private Map<String, Either<T_2910_P, String>> J_1907_R(JsonObject object) {
            g_2336_b resourcelocation = L_3848_p.n_1700_B;
            HashMap map = Maps.newHashMap();
            if (object.has("textures")) {
                JsonObject jsonobject = i_4431_W.M_588_G(object, "textures");
                for (Map.Entry entry : jsonobject.entrySet()) {
                    map.put((String)entry.getKey(), lightning.product.o_3047_I$n_1700_B.n_1700_B(resourcelocation, ((JsonElement)entry.getValue()).getAsString()));
                }
            }
            return map;
        }

        private static Either<T_2910_P, String> n_1700_B(g_2336_b locationIn, String nameIn) {
            if (o_3047_I.P_1922_E(nameIn)) {
                return Either.right((Object)nameIn.substring(1));
            }
            g_2336_b resourcelocation = g_2336_b.J_1907_R(nameIn);
            if (resourcelocation == null) {
                throw new JsonParseException(nameIn + " is not valid resource location");
            }
            return Either.left((Object)new T_2910_P(locationIn, resourcelocation));
        }

        private String R_4764_Y(JsonObject object) {
            return i_4431_W.n_1700_B(object, "parent", "");
        }

        protected boolean n_1700_B(JsonObject object) {
            return i_4431_W.n_1700_B(object, "ambientocclusion", true);
        }

        protected List<BlockElement> J_1907_R(JsonDeserializationContext deserializationContext, JsonObject object) {
            ArrayList list = Lists.newArrayList();
            if (object.has("elements")) {
                for (JsonElement jsonelement : i_4431_W.P_4830_p(object, "elements")) {
                    list.add((BlockElement)deserializationContext.deserialize(jsonelement, BlockElement.class));
                }
            }
            return list;
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }
    }
}



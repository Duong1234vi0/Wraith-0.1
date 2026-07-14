/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 */
package lightning.product;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import lightning.product.ItemTransform;

public class ItemTransforms {
    public static final ItemTransforms n_1700_B = new ItemTransforms();
    public final ItemTransform J_1907_R;
    public final ItemTransform R_4764_Y;
    public final ItemTransform G_564_y;
    public final ItemTransform P_1922_E;
    public final ItemTransform u_1723_Y;
    public final ItemTransform v_4262_N;
    public final ItemTransform w_1484_f;
    public final ItemTransform t_148_a;

    private ItemTransforms() {
        this(ItemTransform.n_1700_B, ItemTransform.n_1700_B, ItemTransform.n_1700_B, ItemTransform.n_1700_B, ItemTransform.n_1700_B, ItemTransform.n_1700_B, ItemTransform.n_1700_B, ItemTransform.n_1700_B);
    }

    public ItemTransforms(ItemTransforms transforms) {
        this.J_1907_R = transforms.J_1907_R;
        this.R_4764_Y = transforms.R_4764_Y;
        this.G_564_y = transforms.G_564_y;
        this.P_1922_E = transforms.P_1922_E;
        this.u_1723_Y = transforms.u_1723_Y;
        this.v_4262_N = transforms.v_4262_N;
        this.w_1484_f = transforms.w_1484_f;
        this.t_148_a = transforms.t_148_a;
    }

    public ItemTransforms(ItemTransform thirdperson_leftIn, ItemTransform thirdperson_rightIn, ItemTransform firstperson_leftIn, ItemTransform firstperson_rightIn, ItemTransform headIn, ItemTransform guiIn, ItemTransform groundIn, ItemTransform fixedIn) {
        this.J_1907_R = thirdperson_leftIn;
        this.R_4764_Y = thirdperson_rightIn;
        this.G_564_y = firstperson_leftIn;
        this.P_1922_E = firstperson_rightIn;
        this.u_1723_Y = headIn;
        this.v_4262_N = guiIn;
        this.w_1484_f = groundIn;
        this.t_148_a = fixedIn;
    }

    public ItemTransform n_1700_B(J_1907_R type) {
        switch (type.ordinal()) {
            case 1: {
                return this.J_1907_R;
            }
            case 2: {
                return this.R_4764_Y;
            }
            case 3: {
                return this.G_564_y;
            }
            case 4: {
                return this.P_1922_E;
            }
            case 5: {
                return this.u_1723_Y;
            }
            case 6: {
                return this.v_4262_N;
            }
            case 7: {
                return this.w_1484_f;
            }
            case 8: {
                return this.t_148_a;
            }
        }
        return ItemTransform.n_1700_B;
    }

    public boolean J_1907_R(J_1907_R type) {
        return this.n_1700_B(type) != ItemTransform.n_1700_B;
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R();
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R();
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R();
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R();
        public static final /* enum */ J_1907_R P_1922_E = new J_1907_R();
        public static final /* enum */ J_1907_R u_1723_Y = new J_1907_R();
        public static final /* enum */ J_1907_R v_4262_N = new J_1907_R();
        public static final /* enum */ J_1907_R w_1484_f = new J_1907_R();
        public static final /* enum */ J_1907_R t_148_a = new J_1907_R();
        private static final /* synthetic */ J_1907_R[] s_956_w;

        public static J_1907_R[] values() {
            return (J_1907_R[])s_956_w.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        public boolean n_1700_B() {
            return this == G_564_y || this == P_1922_E;
        }

        private static /* synthetic */ J_1907_R[] J_1907_R() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a};
        }

        static {
            s_956_w = lightning.product.ItemTransforms$J_1907_R.J_1907_R();
        }
    }

    public static class n_1700_B
    implements JsonDeserializer<ItemTransforms> {
        protected n_1700_B() {
        }

        public ItemTransforms n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
            ItemTransform itemtransformvec3f = this.n_1700_B(p_deserialize_3_, jsonobject, "thirdperson_righthand");
            ItemTransform itemtransformvec3f1 = this.n_1700_B(p_deserialize_3_, jsonobject, "thirdperson_lefthand");
            if (itemtransformvec3f1 == ItemTransform.n_1700_B) {
                itemtransformvec3f1 = itemtransformvec3f;
            }
            ItemTransform itemtransformvec3f2 = this.n_1700_B(p_deserialize_3_, jsonobject, "firstperson_righthand");
            ItemTransform itemtransformvec3f3 = this.n_1700_B(p_deserialize_3_, jsonobject, "firstperson_lefthand");
            if (itemtransformvec3f3 == ItemTransform.n_1700_B) {
                itemtransformvec3f3 = itemtransformvec3f2;
            }
            ItemTransform itemtransformvec3f4 = this.n_1700_B(p_deserialize_3_, jsonobject, "head");
            ItemTransform itemtransformvec3f5 = this.n_1700_B(p_deserialize_3_, jsonobject, "gui");
            ItemTransform itemtransformvec3f6 = this.n_1700_B(p_deserialize_3_, jsonobject, "ground");
            ItemTransform itemtransformvec3f7 = this.n_1700_B(p_deserialize_3_, jsonobject, "fixed");
            return new ItemTransforms(itemtransformvec3f1, itemtransformvec3f, itemtransformvec3f3, itemtransformvec3f2, itemtransformvec3f4, itemtransformvec3f5, itemtransformvec3f6, itemtransformvec3f7);
        }

        private ItemTransform n_1700_B(JsonDeserializationContext context, JsonObject json, String name) {
            return json.has(name) ? (ItemTransform)context.deserialize(json.get(name), ItemTransform.class) : ItemTransform.n_1700_B;
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }
    }
}



/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 */
package lightning.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import lightning.product.M_1336_P;
import lightning.product.g_221_o;
import lightning.product.i_4431_W;
import lightning.product.w_3785_E;

public class ItemTransform {
    public static final ItemTransform n_1700_B = new ItemTransform(new M_1336_P(), new M_1336_P(), new M_1336_P(1.0f, 1.0f, 1.0f));
    public final M_1336_P J_1907_R;
    public final M_1336_P R_4764_Y;
    public final M_1336_P G_564_y;

    public ItemTransform(M_1336_P rotationIn, M_1336_P translationIn, M_1336_P scaleIn) {
        this.J_1907_R = rotationIn.P_1922_E();
        this.R_4764_Y = translationIn.P_1922_E();
        this.G_564_y = scaleIn.P_1922_E();
    }

    public void n_1700_B(boolean leftHand, g_221_o matrixStackIn) {
        if (this != n_1700_B) {
            float f = this.J_1907_R.n_1700_B();
            float f1 = this.J_1907_R.J_1907_R();
            float f2 = this.J_1907_R.R_4764_Y();
            if (leftHand) {
                f1 = -f1;
                f2 = -f2;
            }
            int i = leftHand ? -1 : 1;
            matrixStackIn.n_1700_B((double)((float)i * this.R_4764_Y.n_1700_B()), (double)this.R_4764_Y.J_1907_R(), (double)this.R_4764_Y.R_4764_Y());
            matrixStackIn.n_1700_B(new w_3785_E(f, f1, f2, true));
            matrixStackIn.n_1700_B(this.G_564_y.n_1700_B(), this.G_564_y.J_1907_R(), this.G_564_y.R_4764_Y());
        }
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (this.getClass() != p_equals_1_.getClass()) {
            return false;
        }
        ItemTransform itemtransformvec3f = (ItemTransform)p_equals_1_;
        return this.J_1907_R.equals(itemtransformvec3f.J_1907_R) && this.G_564_y.equals(itemtransformvec3f.G_564_y) && this.R_4764_Y.equals(itemtransformvec3f.R_4764_Y);
    }

    public int hashCode() {
        int i = this.J_1907_R.hashCode();
        i = 31 * i + this.R_4764_Y.hashCode();
        return 31 * i + this.G_564_y.hashCode();
    }

    public static class n_1700_B
    implements JsonDeserializer<ItemTransform> {
        private static final M_1336_P n_1700_B = new M_1336_P(0.0f, 0.0f, 0.0f);
        private static final M_1336_P J_1907_R = new M_1336_P(0.0f, 0.0f, 0.0f);
        private static final M_1336_P R_4764_Y = new M_1336_P(1.0f, 1.0f, 1.0f);

        protected n_1700_B() {
        }

        public ItemTransform n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
            M_1336_P vector3f = this.n_1700_B(jsonobject, "rotation", n_1700_B);
            M_1336_P vector3f1 = this.n_1700_B(jsonobject, "translation", J_1907_R);
            vector3f1.n_1700_B(0.0625f);
            vector3f1.n_1700_B(-5.0f, 5.0f);
            M_1336_P vector3f2 = this.n_1700_B(jsonobject, "scale", R_4764_Y);
            vector3f2.n_1700_B(-4.0f, 4.0f);
            return new ItemTransform(vector3f, vector3f1, vector3f2);
        }

        private M_1336_P n_1700_B(JsonObject json, String key, M_1336_P fallback) {
            if (!json.has(key)) {
                return fallback;
            }
            JsonArray jsonarray = i_4431_W.P_4830_p(json, key);
            if (jsonarray.size() != 3) {
                throw new JsonParseException("Expected 3 " + key + " values, found: " + jsonarray.size());
            }
            float[] afloat = new float[3];
            for (int i = 0; i < afloat.length; ++i) {
                afloat[i] = i_4431_W.G_564_y(jsonarray.get(i), key + "[" + i + "]");
            }
            return new M_1336_P(afloat[0], afloat[1], afloat[2]);
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }
    }
}



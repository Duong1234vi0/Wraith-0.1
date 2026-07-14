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
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import lightning.product.i_4431_W;

public class BlockFaceUV {
    public float[] n_1700_B;
    public final int J_1907_R;

    public BlockFaceUV(@Nullable float[] uvsIn, int rotationIn) {
        this.n_1700_B = uvsIn;
        this.J_1907_R = rotationIn;
    }

    public float n_1700_B(int indexIn) {
        if (this.n_1700_B == null) {
            throw new NullPointerException("uvs");
        }
        int i = this.G_564_y(indexIn);
        return this.n_1700_B[i != 0 && i != 1 ? 2 : 0];
    }

    public float J_1907_R(int indexIn) {
        if (this.n_1700_B == null) {
            throw new NullPointerException("uvs");
        }
        int i = this.G_564_y(indexIn);
        return this.n_1700_B[i != 0 && i != 3 ? 3 : 1];
    }

    private int G_564_y(int indexIn) {
        return (indexIn + this.J_1907_R / 90) % 4;
    }

    public int R_4764_Y(int indexIn) {
        return (indexIn + 4 - this.J_1907_R / 90) % 4;
    }

    public void n_1700_B(float[] uvsIn) {
        if (this.n_1700_B == null) {
            this.n_1700_B = uvsIn;
        }
    }

    public static class n_1700_B
    implements JsonDeserializer<BlockFaceUV> {
        protected n_1700_B() {
        }

        public BlockFaceUV n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
            float[] afloat = this.J_1907_R(jsonobject);
            int i = this.n_1700_B(jsonobject);
            return new BlockFaceUV(afloat, i);
        }

        protected int n_1700_B(JsonObject object) {
            int i = i_4431_W.n_1700_B(object, "rotation", 0);
            if (i >= 0 && i % 90 == 0 && i / 90 <= 3) {
                return i;
            }
            throw new JsonParseException("Invalid rotation " + i + " found, only 0/90/180/270 allowed");
        }

        @Nullable
        private float[] J_1907_R(JsonObject object) {
            if (!object.has("uv")) {
                return null;
            }
            JsonArray jsonarray = i_4431_W.P_4830_p(object, "uv");
            if (jsonarray.size() != 4) {
                throw new JsonParseException("Expected 4 uv values, found: " + jsonarray.size());
            }
            float[] afloat = new float[4];
            for (int i = 0; i < afloat.length; ++i) {
                afloat[i] = i_4431_W.G_564_y(jsonarray.get(i), "uv[" + i + "]");
            }
            return afloat;
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }
    }
}



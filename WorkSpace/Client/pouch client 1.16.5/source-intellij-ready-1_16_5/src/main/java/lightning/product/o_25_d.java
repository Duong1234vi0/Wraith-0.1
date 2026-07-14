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
import java.util.Objects;
import lightning.product.S_3779_r;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.Transformation;
import lightning.product.ModelState;

public class o_25_d
implements ModelState {
    private final g_2336_b n_1700_B;
    private final Transformation J_1907_R;
    private final boolean R_4764_Y;
    private final int G_564_y;

    public o_25_d(g_2336_b modelLocationIn, Transformation rotationIn, boolean uvLockIn, int weightIn) {
        this.n_1700_B = modelLocationIn;
        this.J_1907_R = rotationIn;
        this.R_4764_Y = uvLockIn;
        this.G_564_y = weightIn;
    }

    public g_2336_b R_4764_Y() {
        return this.n_1700_B;
    }

    @Override
    public Transformation n_1700_B() {
        return this.J_1907_R;
    }

    @Override
    public boolean J_1907_R() {
        return this.R_4764_Y;
    }

    public int G_564_y() {
        return this.G_564_y;
    }

    public String toString() {
        return "Variant{modelLocation=" + String.valueOf(this.n_1700_B) + ", rotation=" + String.valueOf(this.J_1907_R) + ", uvLock=" + this.R_4764_Y + ", weight=" + this.G_564_y + "}";
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof o_25_d)) {
            return false;
        }
        o_25_d variant = (o_25_d)p_equals_1_;
        return this.n_1700_B.equals(variant.n_1700_B) && Objects.equals(this.J_1907_R, variant.J_1907_R) && this.R_4764_Y == variant.R_4764_Y && this.G_564_y == variant.G_564_y;
    }

    public int hashCode() {
        int i = this.n_1700_B.hashCode();
        i = 31 * i + this.J_1907_R.hashCode();
        i = 31 * i + Boolean.valueOf(this.R_4764_Y).hashCode();
        return 31 * i + this.G_564_y;
    }

    public static class n_1700_B
    implements JsonDeserializer<o_25_d> {
        public o_25_d n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
            g_2336_b resourcelocation = this.J_1907_R(jsonobject);
            S_3779_r modelrotation = this.n_1700_B(jsonobject);
            boolean flag = this.G_564_y(jsonobject);
            int i = this.R_4764_Y(jsonobject);
            return new o_25_d(resourcelocation, modelrotation.n_1700_B(), flag, i);
        }

        private boolean G_564_y(JsonObject json) {
            return i_4431_W.n_1700_B(json, "uvlock", false);
        }

        protected S_3779_r n_1700_B(JsonObject json) {
            int j;
            int i = i_4431_W.n_1700_B(json, "x", 0);
            S_3779_r modelrotation = S_3779_r.n_1700_B(i, j = i_4431_W.n_1700_B(json, "y", 0));
            if (modelrotation == null) {
                throw new JsonParseException("Invalid BlockModelRotation x: " + i + ", y: " + j);
            }
            return modelrotation;
        }

        protected g_2336_b J_1907_R(JsonObject json) {
            return new g_2336_b(i_4431_W.u_1723_Y(json, "model"));
        }

        protected int R_4764_Y(JsonObject json) {
            int i = i_4431_W.n_1700_B(json, "weight", 1);
            if (i < 1) {
                throw new JsonParseException("Invalid weight " + i + " found, expected integer >= 1");
            }
            return i;
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }
    }
}



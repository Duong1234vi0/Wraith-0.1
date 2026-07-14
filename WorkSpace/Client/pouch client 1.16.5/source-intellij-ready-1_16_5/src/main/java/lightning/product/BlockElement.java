/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.BlockElementFace;
import lightning.product.M_1336_P;
import lightning.product.b_257_Y;
import lightning.product.i_4431_W;
import lightning.product.BlockElementRotation;
import lightning.product.u_530_F;

public class BlockElement {
    public final M_1336_P n_1700_B;
    public final M_1336_P J_1907_R;
    public final Map<b_257_Y, BlockElementFace> R_4764_Y;
    public final BlockElementRotation G_564_y;
    public final boolean P_1922_E;

    public BlockElement(M_1336_P positionFrom, M_1336_P positionTo, Map<b_257_Y, BlockElementFace> mapFaces, @Nullable BlockElementRotation partRotation, boolean shade) {
        this.n_1700_B = positionFrom;
        this.J_1907_R = positionTo;
        this.R_4764_Y = mapFaces;
        this.G_564_y = partRotation;
        this.P_1922_E = shade;
        this.n_1700_B();
    }

    private void n_1700_B() {
        for (Map.Entry<b_257_Y, BlockElementFace> entry : this.R_4764_Y.entrySet()) {
            float[] afloat = this.n_1700_B(entry.getKey());
            entry.getValue().G_564_y.n_1700_B(afloat);
        }
    }

    private float[] n_1700_B(b_257_Y facing) {
        switch (facing) {
            case n_1700_B: {
                return new float[]{this.n_1700_B.n_1700_B(), 16.0f - this.J_1907_R.R_4764_Y(), this.J_1907_R.n_1700_B(), 16.0f - this.n_1700_B.R_4764_Y()};
            }
            case J_1907_R: {
                return new float[]{this.n_1700_B.n_1700_B(), this.n_1700_B.R_4764_Y(), this.J_1907_R.n_1700_B(), this.J_1907_R.R_4764_Y()};
            }
            default: {
                return new float[]{16.0f - this.J_1907_R.n_1700_B(), 16.0f - this.J_1907_R.J_1907_R(), 16.0f - this.n_1700_B.n_1700_B(), 16.0f - this.n_1700_B.J_1907_R()};
            }
            case G_564_y: {
                return new float[]{this.n_1700_B.n_1700_B(), 16.0f - this.J_1907_R.J_1907_R(), this.J_1907_R.n_1700_B(), 16.0f - this.n_1700_B.J_1907_R()};
            }
            case P_1922_E: {
                return new float[]{this.n_1700_B.R_4764_Y(), 16.0f - this.J_1907_R.J_1907_R(), this.J_1907_R.R_4764_Y(), 16.0f - this.n_1700_B.J_1907_R()};
            }
            case u_1723_Y: 
        }
        return new float[]{16.0f - this.J_1907_R.R_4764_Y(), 16.0f - this.J_1907_R.J_1907_R(), 16.0f - this.n_1700_B.R_4764_Y(), 16.0f - this.n_1700_B.J_1907_R()};
    }

    public static class n_1700_B
    implements JsonDeserializer<BlockElement> {
        protected n_1700_B() {
        }

        public BlockElement n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
            M_1336_P vector3f = this.P_1922_E(jsonobject);
            M_1336_P vector3f1 = this.G_564_y(jsonobject);
            BlockElementRotation blockpartrotation = this.n_1700_B(jsonobject);
            Map<b_257_Y, BlockElementFace> map = this.n_1700_B(p_deserialize_3_, jsonobject);
            if (jsonobject.has("shade") && !i_4431_W.J_1907_R(jsonobject, "shade")) {
                throw new JsonParseException("Expected shade to be a Boolean");
            }
            boolean flag = i_4431_W.n_1700_B(jsonobject, "shade", true);
            return new BlockElement(vector3f, vector3f1, map, blockpartrotation, flag);
        }

        @Nullable
        private BlockElementRotation n_1700_B(JsonObject object) {
            BlockElementRotation blockpartrotation = null;
            if (object.has("rotation")) {
                JsonObject jsonobject = i_4431_W.M_588_G(object, "rotation");
                M_1336_P vector3f = this.n_1700_B(jsonobject, "origin");
                vector3f.n_1700_B(0.0625f);
                b_257_Y.n_1700_B direction$axis = this.R_4764_Y(jsonobject);
                float f = this.J_1907_R(jsonobject);
                boolean flag = i_4431_W.n_1700_B(jsonobject, "rescale", false);
                blockpartrotation = new BlockElementRotation(vector3f, direction$axis, f, flag);
            }
            return blockpartrotation;
        }

        private float J_1907_R(JsonObject object) {
            float f = i_4431_W.t_148_a(object, "angle");
            if (f != 0.0f && u_530_F.P_1922_E(f) != 22.5f && u_530_F.P_1922_E(f) != 45.0f) {
                throw new JsonParseException("Invalid rotation " + f + " found, only -45/-22.5/0/22.5/45 allowed");
            }
            return f;
        }

        private b_257_Y.n_1700_B R_4764_Y(JsonObject object) {
            String s = i_4431_W.u_1723_Y(object, "axis");
            b_257_Y.n_1700_B direction$axis = b_257_Y.n_1700_B.n_1700_B(s.toLowerCase(Locale.ROOT));
            if (direction$axis == null) {
                throw new JsonParseException("Invalid rotation axis: " + s);
            }
            return direction$axis;
        }

        private Map<b_257_Y, BlockElementFace> n_1700_B(JsonDeserializationContext deserializationContext, JsonObject object) {
            Map<b_257_Y, BlockElementFace> map = this.J_1907_R(deserializationContext, object);
            if (map.isEmpty()) {
                throw new JsonParseException("Expected between 1 and 6 unique faces, got 0");
            }
            return map;
        }

        private Map<b_257_Y, BlockElementFace> J_1907_R(JsonDeserializationContext deserializationContext, JsonObject object) {
            EnumMap map = Maps.newEnumMap(b_257_Y.class);
            JsonObject jsonobject = i_4431_W.M_588_G(object, "faces");
            for (Map.Entry entry : jsonobject.entrySet()) {
                b_257_Y direction = this.n_1700_B((String)entry.getKey());
                map.put(direction, (BlockElementFace)deserializationContext.deserialize((JsonElement)entry.getValue(), BlockElementFace.class));
            }
            return map;
        }

        private b_257_Y n_1700_B(String name) {
            b_257_Y direction = b_257_Y.n_1700_B(name);
            if (direction == null) {
                throw new JsonParseException("Unknown facing: " + name);
            }
            return direction;
        }

        private M_1336_P G_564_y(JsonObject json) {
            M_1336_P vector3f = this.n_1700_B(json, "to");
            if (!(vector3f.n_1700_B() < -16.0f || vector3f.J_1907_R() < -16.0f || vector3f.R_4764_Y() < -16.0f || vector3f.n_1700_B() > 32.0f || vector3f.J_1907_R() > 32.0f || vector3f.R_4764_Y() > 32.0f)) {
                return vector3f;
            }
            throw new JsonParseException("'to' specifier exceeds the allowed boundaries: " + String.valueOf(vector3f));
        }

        private M_1336_P P_1922_E(JsonObject json) {
            M_1336_P vector3f = this.n_1700_B(json, "from");
            if (!(vector3f.n_1700_B() < -16.0f || vector3f.J_1907_R() < -16.0f || vector3f.R_4764_Y() < -16.0f || vector3f.n_1700_B() > 32.0f || vector3f.J_1907_R() > 32.0f || vector3f.R_4764_Y() > 32.0f)) {
                return vector3f;
            }
            throw new JsonParseException("'from' specifier exceeds the allowed boundaries: " + String.valueOf(vector3f));
        }

        private M_1336_P n_1700_B(JsonObject json, String name) {
            JsonArray jsonarray = i_4431_W.P_4830_p(json, name);
            if (jsonarray.size() != 3) {
                throw new JsonParseException("Expected 3 " + name + " values, found: " + jsonarray.size());
            }
            float[] afloat = new float[3];
            for (int i = 0; i < afloat.length; ++i) {
                afloat[i] = i_4431_W.G_564_y(jsonarray.get(i), name + "[" + i + "]");
            }
            return new M_1336_P(afloat[0], afloat[1], afloat[2]);
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }
    }
}



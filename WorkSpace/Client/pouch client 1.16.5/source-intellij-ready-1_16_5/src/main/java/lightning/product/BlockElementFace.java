/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import lightning.product.BlockFaceUV;
import lightning.product.b_257_Y;
import lightning.product.i_4431_W;

public class BlockElementFace {
    public final b_257_Y n_1700_B;
    public final int J_1907_R;
    public final String R_4764_Y;
    public final BlockFaceUV G_564_y;

    public BlockElementFace(@Nullable b_257_Y cullFaceIn, int tintIndexIn, String textureIn, BlockFaceUV blockFaceUVIn) {
        this.n_1700_B = cullFaceIn;
        this.J_1907_R = tintIndexIn;
        this.R_4764_Y = textureIn;
        this.G_564_y = blockFaceUVIn;
    }

    public static class n_1700_B
    implements JsonDeserializer<BlockElementFace> {
        protected n_1700_B() {
        }

        public BlockElementFace n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
            b_257_Y direction = this.R_4764_Y(jsonobject);
            int i = this.n_1700_B(jsonobject);
            String s = this.J_1907_R(jsonobject);
            BlockFaceUV blockfaceuv = (BlockFaceUV)p_deserialize_3_.deserialize((JsonElement)jsonobject, BlockFaceUV.class);
            return new BlockElementFace(direction, i, s, blockfaceuv);
        }

        protected int n_1700_B(JsonObject object) {
            return i_4431_W.n_1700_B(object, "tintindex", -1);
        }

        private String J_1907_R(JsonObject object) {
            return i_4431_W.u_1723_Y(object, "texture");
        }

        @Nullable
        private b_257_Y R_4764_Y(JsonObject object) {
            String s = i_4431_W.n_1700_B(object, "cullface", "");
            return b_257_Y.n_1700_B(s);
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }
    }
}



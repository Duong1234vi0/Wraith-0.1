/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  org.apache.commons.lang3.Validate
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import lightning.product.O_728_b;
import lightning.product.soundsSoundEventRegistration;
import lightning.product.i_4431_W;
import org.apache.commons.lang3.Validate;

public class SoundEventRegistrationSerializer
implements JsonDeserializer<soundsSoundEventRegistration> {
    public soundsSoundEventRegistration n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
        JsonObject jsonobject = i_4431_W.w_1484_f(p_deserialize_1_, "entry");
        boolean flag = i_4431_W.n_1700_B(jsonobject, "replace", false);
        String s = i_4431_W.n_1700_B(jsonobject, "subtitle", (String)null);
        List<O_728_b> list = this.n_1700_B(jsonobject);
        return new soundsSoundEventRegistration(list, flag, s);
    }

    private List<O_728_b> n_1700_B(JsonObject object) {
        ArrayList list = Lists.newArrayList();
        if (object.has("sounds")) {
            JsonArray jsonarray = i_4431_W.P_4830_p(object, "sounds");
            for (int i = 0; i < jsonarray.size(); ++i) {
                JsonElement jsonelement = jsonarray.get(i);
                if (i_4431_W.n_1700_B(jsonelement)) {
                    String s = i_4431_W.n_1700_B(jsonelement, "sound");
                    list.add(new O_728_b(s, 1.0f, 1.0f, 1, O_728_b.n_1700_B.n_1700_B, false, false, 16));
                    continue;
                }
                list.add(this.J_1907_R(i_4431_W.w_1484_f(jsonelement, "sound")));
            }
        }
        return list;
    }

    private O_728_b J_1907_R(JsonObject object) {
        String s = i_4431_W.u_1723_Y(object, "name");
        O_728_b.n_1700_B sound$type = this.n_1700_B(object, O_728_b.n_1700_B.n_1700_B);
        float f = i_4431_W.n_1700_B(object, "volume", 1.0f);
        Validate.isTrue((f > 0.0f ? 1 : 0) != 0, (String)"Invalid volume", (Object[])new Object[0]);
        float f1 = i_4431_W.n_1700_B(object, "pitch", 1.0f);
        Validate.isTrue((f1 > 0.0f ? 1 : 0) != 0, (String)"Invalid pitch", (Object[])new Object[0]);
        int i = i_4431_W.n_1700_B(object, "weight", 1);
        Validate.isTrue((i > 0 ? 1 : 0) != 0, (String)"Invalid weight", (Object[])new Object[0]);
        boolean flag = i_4431_W.n_1700_B(object, "preload", false);
        boolean flag1 = i_4431_W.n_1700_B(object, "stream", false);
        int j = i_4431_W.n_1700_B(object, "attenuation_distance", 16);
        return new O_728_b(s, f, f1, i, sound$type, flag1, flag, j);
    }

    private O_728_b.n_1700_B n_1700_B(JsonObject object, O_728_b.n_1700_B defaultValue) {
        O_728_b.n_1700_B sound$type = defaultValue;
        if (object.has("type")) {
            sound$type = O_728_b.n_1700_B.n_1700_B(i_4431_W.u_1723_Y(object, "type"));
            Validate.notNull((Object)((Object)sound$type), (String)"Invalid type", (Object[])new Object[0]);
        }
        return sound$type;
    }

    public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
    }
}



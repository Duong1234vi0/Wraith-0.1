/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  org.apache.commons.lang3.Validate
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.util.ArrayList;
import lightning.product.B_4830_U;
import lightning.product.AnimationFrame;
import lightning.product.T_335_n;
import lightning.product.i_4431_W;
import org.apache.commons.lang3.Validate;

public class e_2501_q
implements T_335_n<B_4830_U> {
    public B_4830_U n_1700_B(JsonObject json) {
        ArrayList list = Lists.newArrayList();
        int i = i_4431_W.n_1700_B(json, "frametime", 1);
        if (i != 1) {
            Validate.inclusiveBetween((long)1L, (long)Integer.MAX_VALUE, (long)i, (String)"Invalid default frame time");
        }
        if (json.has("frames")) {
            try {
                JsonArray jsonarray = i_4431_W.P_4830_p(json, "frames");
                for (int j = 0; j < jsonarray.size(); ++j) {
                    JsonElement jsonelement = jsonarray.get(j);
                    AnimationFrame animationframe = this.n_1700_B(j, jsonelement);
                    if (animationframe == null) continue;
                    list.add(animationframe);
                }
            }
            catch (ClassCastException classcastexception) {
                throw new JsonParseException("Invalid animation->frames: expected array, was " + String.valueOf(json.get("frames")), (Throwable)classcastexception);
            }
        }
        int k = i_4431_W.n_1700_B(json, "width", -1);
        int l = i_4431_W.n_1700_B(json, "height", -1);
        if (k != -1) {
            Validate.inclusiveBetween((long)1L, (long)Integer.MAX_VALUE, (long)k, (String)"Invalid width");
        }
        if (l != -1) {
            Validate.inclusiveBetween((long)1L, (long)Integer.MAX_VALUE, (long)l, (String)"Invalid height");
        }
        boolean flag = i_4431_W.n_1700_B(json, "interpolate", false);
        return new B_4830_U(list, k, l, i, flag);
    }

    private AnimationFrame n_1700_B(int frame, JsonElement element) {
        if (element.isJsonPrimitive()) {
            return new AnimationFrame(i_4431_W.u_1723_Y(element, "frames[" + frame + "]"));
        }
        if (element.isJsonObject()) {
            JsonObject jsonobject = i_4431_W.w_1484_f(element, "frames[" + frame + "]");
            int i = i_4431_W.n_1700_B(jsonobject, "time", -1);
            if (jsonobject.has("time")) {
                Validate.inclusiveBetween((long)1L, (long)Integer.MAX_VALUE, (long)i, (String)"Invalid frame time");
            }
            int j = i_4431_W.u_2550_I(jsonobject, "index");
            Validate.inclusiveBetween((long)0L, (long)Integer.MAX_VALUE, (long)j, (String)"Invalid frame index");
            return new AnimationFrame(j, i);
        }
        return null;
    }

    @Override
    public String n_1700_B() {
        return "animation";
    }

    @Override
    public /* synthetic */ Object J_1907_R(JsonObject jsonObject) {
        return this.n_1700_B(jsonObject);
    }
}



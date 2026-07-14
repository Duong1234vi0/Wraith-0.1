/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import javax.annotation.Nullable;
import lightning.product.SerializationTags;
import lightning.product.V_3137_a;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_3591_l;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.r_109_r;
import lightning.product.r_2687_x;
import lightning.product.Fluid;

public class l_14_c {
    public static final l_14_c n_1700_B = new l_14_c(null, null, r_2687_x.n_1700_B);
    @Nullable
    private final r_109_r<Fluid> J_1907_R;
    @Nullable
    private final Fluid R_4764_Y;
    private final r_2687_x G_564_y;

    public l_14_c(@Nullable r_109_r<Fluid> fluidTag, @Nullable Fluid fluid, r_2687_x stateCondition) {
        this.J_1907_R = fluidTag;
        this.R_4764_Y = fluid;
        this.G_564_y = stateCondition;
    }

    public boolean n_1700_B(e_3591_l world, c_1514_x pos) {
        if (this == n_1700_B) {
            return true;
        }
        if (!world.multiplayerClientSuggestionProvider(pos)) {
            return false;
        }
        FluidState fluidstate = world.getFluidState(pos);
        Fluid fluid = fluidstate.n_1700_B();
        if (this.J_1907_R != null && !this.J_1907_R.n_1700_B(fluid)) {
            return false;
        }
        if (this.R_4764_Y != null && fluid != this.R_4764_Y) {
            return false;
        }
        return this.G_564_y.n_1700_B(fluidstate);
    }

    public static l_14_c n_1700_B(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonObject jsonobject = i_4431_W.w_1484_f(element, "fluid");
            Fluid fluid = null;
            if (jsonobject.has("fluid")) {
                g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(jsonobject, "fluid"));
                fluid = V_3137_a.G_624_v.n_1700_B(resourcelocation);
            }
            r_109_r<Fluid> itag = null;
            if (jsonobject.has("tag")) {
                g_2336_b resourcelocation1 = new g_2336_b(i_4431_W.u_1723_Y(jsonobject, "tag"));
                itag = SerializationTags.n_1700_B().R_4764_Y().n_1700_B(resourcelocation1);
                if (itag == null) {
                    throw new JsonSyntaxException("Unknown fluid tag '" + String.valueOf(resourcelocation1) + "'");
                }
            }
            r_2687_x statepropertiespredicate = r_2687_x.n_1700_B(jsonobject.get("state"));
            return new l_14_c(itag, fluid, statepropertiespredicate);
        }
        return n_1700_B;
    }

    public JsonElement n_1700_B() {
        if (this == n_1700_B) {
            return JsonNull.INSTANCE;
        }
        JsonObject jsonobject = new JsonObject();
        if (this.R_4764_Y != null) {
            jsonobject.addProperty("fluid", V_3137_a.G_624_v.J_1907_R(this.R_4764_Y).toString());
        }
        if (this.J_1907_R != null) {
            jsonobject.addProperty("tag", SerializationTags.n_1700_B().R_4764_Y().J_1907_R(this.J_1907_R).toString());
        }
        jsonobject.add("state", this.G_564_y.n_1700_B());
        return jsonobject;
    }
}



/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import javax.annotation.Nullable;
import lightning.product.i_4431_W;
import lightning.product.MinMaxBounds;
import lightning.product.u_530_F;

public class o_3456_E {
    public static final o_3456_E n_1700_B = new o_3456_E(MinMaxBounds.n_1700_B.P_1922_E, MinMaxBounds.n_1700_B.P_1922_E, MinMaxBounds.n_1700_B.P_1922_E, MinMaxBounds.n_1700_B.P_1922_E, MinMaxBounds.n_1700_B.P_1922_E);
    private final MinMaxBounds.n_1700_B J_1907_R;
    private final MinMaxBounds.n_1700_B R_4764_Y;
    private final MinMaxBounds.n_1700_B G_564_y;
    private final MinMaxBounds.n_1700_B P_1922_E;
    private final MinMaxBounds.n_1700_B u_1723_Y;

    public o_3456_E(MinMaxBounds.n_1700_B x, MinMaxBounds.n_1700_B y, MinMaxBounds.n_1700_B z, MinMaxBounds.n_1700_B horizontal, MinMaxBounds.n_1700_B absolute) {
        this.J_1907_R = x;
        this.R_4764_Y = y;
        this.G_564_y = z;
        this.P_1922_E = horizontal;
        this.u_1723_Y = absolute;
    }

    public static o_3456_E n_1700_B(MinMaxBounds.n_1700_B distance) {
        return new o_3456_E(MinMaxBounds.n_1700_B.P_1922_E, MinMaxBounds.n_1700_B.P_1922_E, MinMaxBounds.n_1700_B.P_1922_E, distance, MinMaxBounds.n_1700_B.P_1922_E);
    }

    public static o_3456_E J_1907_R(MinMaxBounds.n_1700_B distance) {
        return new o_3456_E(MinMaxBounds.n_1700_B.P_1922_E, distance, MinMaxBounds.n_1700_B.P_1922_E, MinMaxBounds.n_1700_B.P_1922_E, MinMaxBounds.n_1700_B.P_1922_E);
    }

    public boolean n_1700_B(double x1, double y1, double z1, double x2, double y2, double z2) {
        float f = (float)(x1 - x2);
        float f1 = (float)(y1 - y2);
        float f2 = (float)(z1 - z2);
        if (this.J_1907_R.J_1907_R(u_530_F.P_1922_E(f)) && this.R_4764_Y.J_1907_R(u_530_F.P_1922_E(f1)) && this.G_564_y.J_1907_R(u_530_F.P_1922_E(f2))) {
            if (!this.P_1922_E.n_1700_B(f * f + f2 * f2)) {
                return false;
            }
            return this.u_1723_Y.n_1700_B(f * f + f1 * f1 + f2 * f2);
        }
        return false;
    }

    public static o_3456_E n_1700_B(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonObject jsonobject = i_4431_W.w_1484_f(element, "distance");
            MinMaxBounds.n_1700_B minmaxbounds$floatbound = MinMaxBounds.n_1700_B.n_1700_B(jsonobject.get("x"));
            MinMaxBounds.n_1700_B minmaxbounds$floatbound1 = MinMaxBounds.n_1700_B.n_1700_B(jsonobject.get("y"));
            MinMaxBounds.n_1700_B minmaxbounds$floatbound2 = MinMaxBounds.n_1700_B.n_1700_B(jsonobject.get("z"));
            MinMaxBounds.n_1700_B minmaxbounds$floatbound3 = MinMaxBounds.n_1700_B.n_1700_B(jsonobject.get("horizontal"));
            MinMaxBounds.n_1700_B minmaxbounds$floatbound4 = MinMaxBounds.n_1700_B.n_1700_B(jsonobject.get("absolute"));
            return new o_3456_E(minmaxbounds$floatbound, minmaxbounds$floatbound1, minmaxbounds$floatbound2, minmaxbounds$floatbound3, minmaxbounds$floatbound4);
        }
        return n_1700_B;
    }

    public JsonElement n_1700_B() {
        if (this == n_1700_B) {
            return JsonNull.INSTANCE;
        }
        JsonObject jsonobject = new JsonObject();
        jsonobject.add("x", this.J_1907_R.G_564_y());
        jsonobject.add("y", this.R_4764_Y.G_564_y());
        jsonobject.add("z", this.G_564_y.G_564_y());
        jsonobject.add("horizontal", this.P_1922_E.G_564_y());
        jsonobject.add("absolute", this.u_1723_Y.G_564_y());
        return jsonobject;
    }
}



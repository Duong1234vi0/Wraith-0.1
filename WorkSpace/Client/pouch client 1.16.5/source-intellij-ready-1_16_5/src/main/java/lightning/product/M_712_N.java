/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSyntaxException
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import javax.annotation.Nullable;
import lightning.product.MutableComponent;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.W_4813_f;
import lightning.product.Z_1993_T;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.q_1613_l;
import lightning.product.r_4318_c;
import lightning.product.x_282_a;

public class M_712_N {
    private final x_282_a n_1700_B;
    private final x_282_a J_1907_R;
    private final Z_1993_T R_4764_Y;
    private final g_2336_b G_564_y;
    private final W_4813_f P_1922_E;
    private final boolean u_1723_Y;
    private final boolean v_4262_N;
    private final boolean w_1484_f;
    private float t_148_a;
    private float s_956_w;

    public M_712_N(Z_1993_T icon, x_282_a title, x_282_a description, @Nullable g_2336_b background, W_4813_f frame, boolean showToast, boolean announceToChat, boolean hidden) {
        this.n_1700_B = title;
        this.J_1907_R = description;
        this.R_4764_Y = icon;
        this.G_564_y = background;
        this.P_1922_E = frame;
        this.u_1723_Y = showToast;
        this.v_4262_N = announceToChat;
        this.w_1484_f = hidden;
    }

    public void n_1700_B(float x, float y) {
        this.t_148_a = x;
        this.s_956_w = y;
    }

    public x_282_a n_1700_B() {
        return this.n_1700_B;
    }

    public x_282_a J_1907_R() {
        return this.J_1907_R;
    }

    public Z_1993_T R_4764_Y() {
        return this.R_4764_Y;
    }

    @Nullable
    public g_2336_b G_564_y() {
        return this.G_564_y;
    }

    public W_4813_f P_1922_E() {
        return this.P_1922_E;
    }

    public float u_1723_Y() {
        return this.t_148_a;
    }

    public float v_4262_N() {
        return this.s_956_w;
    }

    public boolean w_1484_f() {
        return this.u_1723_Y;
    }

    public boolean t_148_a() {
        return this.v_4262_N;
    }

    public boolean s_956_w() {
        return this.w_1484_f;
    }

    public static M_712_N n_1700_B(JsonObject object) {
        MutableComponent itextcomponent = x_282_a.n_1700_B.n_1700_B(object.get("title"));
        MutableComponent itextcomponent1 = x_282_a.n_1700_B.n_1700_B(object.get("description"));
        if (itextcomponent != null && itextcomponent1 != null) {
            Z_1993_T itemstack = M_712_N.J_1907_R(i_4431_W.M_588_G(object, "icon"));
            g_2336_b resourcelocation = object.has("background") ? new g_2336_b(i_4431_W.u_1723_Y(object, "background")) : null;
            W_4813_f frametype = object.has("frame") ? W_4813_f.n_1700_B(i_4431_W.u_1723_Y(object, "frame")) : W_4813_f.n_1700_B;
            boolean flag = i_4431_W.n_1700_B(object, "show_toast", true);
            boolean flag1 = i_4431_W.n_1700_B(object, "announce_to_chat", true);
            boolean flag2 = i_4431_W.n_1700_B(object, "hidden", false);
            return new M_712_N(itemstack, itextcomponent, itextcomponent1, resourcelocation, frametype, flag, flag1, flag2);
        }
        throw new JsonSyntaxException("Both title and description must be set");
    }

    private static Z_1993_T J_1907_R(JsonObject object) {
        if (!object.has("item")) {
            throw new JsonSyntaxException("Unsupported icon type, currently only items are supported (add 'item' key)");
        }
        q_1613_l item = i_4431_W.v_4262_N(object, "item");
        if (object.has("data")) {
            throw new JsonParseException("Disallowed data tag found");
        }
        Z_1993_T itemstack = new Z_1993_T(item);
        if (object.has("nbt")) {
            try {
                U_2912_j compoundnbt = r_4318_c.n_1700_B(i_4431_W.n_1700_B(object.get("nbt"), "nbt"));
                itemstack.R_4764_Y(compoundnbt);
            }
            catch (CommandSyntaxException commandsyntaxexception) {
                throw new JsonSyntaxException("Invalid nbt tag: " + commandsyntaxexception.getMessage());
            }
        }
        return itemstack;
    }

    public void n_1700_B(b_2585_i buf) {
        buf.n_1700_B(this.n_1700_B);
        buf.n_1700_B(this.J_1907_R);
        buf.n_1700_B(this.R_4764_Y);
        buf.n_1700_B(this.P_1922_E);
        int i = 0;
        if (this.G_564_y != null) {
            i |= 1;
        }
        if (this.u_1723_Y) {
            i |= 2;
        }
        if (this.w_1484_f) {
            i |= 4;
        }
        buf.writeInt(i);
        if (this.G_564_y != null) {
            buf.n_1700_B(this.G_564_y);
        }
        buf.writeFloat(this.t_148_a);
        buf.writeFloat(this.s_956_w);
    }

    public static M_712_N J_1907_R(b_2585_i buf) {
        x_282_a itextcomponent = buf.P_1922_E();
        x_282_a itextcomponent1 = buf.P_1922_E();
        Z_1993_T itemstack = buf.u_2550_I();
        W_4813_f frametype = buf.n_1700_B(W_4813_f.class);
        int i = buf.readInt();
        g_2336_b resourcelocation = (i & 1) != 0 ? buf.P_4830_p() : null;
        boolean flag = (i & 2) != 0;
        boolean flag1 = (i & 4) != 0;
        M_712_N displayinfo = new M_712_N(itemstack, itextcomponent, itextcomponent1, resourcelocation, frametype, flag, false, flag1);
        displayinfo.n_1700_B(buf.readFloat(), buf.readFloat());
        return displayinfo;
    }

    public JsonElement u_2550_I() {
        JsonObject jsonobject = new JsonObject();
        jsonobject.add("icon", (JsonElement)this.M_588_G());
        jsonobject.add("title", x_282_a.n_1700_B.J_1907_R(this.n_1700_B));
        jsonobject.add("description", x_282_a.n_1700_B.J_1907_R(this.J_1907_R));
        jsonobject.addProperty("frame", this.P_1922_E.n_1700_B());
        jsonobject.addProperty("show_toast", Boolean.valueOf(this.u_1723_Y));
        jsonobject.addProperty("announce_to_chat", Boolean.valueOf(this.v_4262_N));
        jsonobject.addProperty("hidden", Boolean.valueOf(this.w_1484_f));
        if (this.G_564_y != null) {
            jsonobject.addProperty("background", this.G_564_y.toString());
        }
        return jsonobject;
    }

    private JsonObject M_588_G() {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("item", V_3137_a.e_2887_G.J_1907_R(this.R_4764_Y.J_1907_R()).toString());
        if (this.R_4764_Y.h_1847_R()) {
            jsonobject.addProperty("nbt", this.R_4764_Y.Q_4569_t().toString());
        }
        return jsonobject;
    }
}



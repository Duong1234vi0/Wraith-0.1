/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonPrimitive
 *  com.google.gson.JsonSyntaxException
 */
package lightning.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lightning.product.b_2585_i;

public class B_3068_A {
    private static final SimpleDateFormat n_1700_B = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
    private Date J_1907_R;

    public boolean n_1700_B() {
        return this.J_1907_R != null;
    }

    public void J_1907_R() {
        this.J_1907_R = new Date();
    }

    public void R_4764_Y() {
        this.J_1907_R = null;
    }

    public Date G_564_y() {
        return this.J_1907_R;
    }

    public String toString() {
        return "CriterionProgress{obtained=" + String.valueOf(this.J_1907_R == null ? "false" : this.J_1907_R) + "}";
    }

    public void n_1700_B(b_2585_i buf) {
        buf.writeBoolean(this.J_1907_R != null);
        if (this.J_1907_R != null) {
            buf.n_1700_B(this.J_1907_R);
        }
    }

    public JsonElement P_1922_E() {
        return this.J_1907_R != null ? new JsonPrimitive(n_1700_B.format(this.J_1907_R)) : JsonNull.INSTANCE;
    }

    public static B_3068_A J_1907_R(b_2585_i buf) {
        B_3068_A criterionprogress = new B_3068_A();
        if (buf.readBoolean()) {
            criterionprogress.J_1907_R = buf.h_1847_R();
        }
        return criterionprogress;
    }

    public static B_3068_A n_1700_B(String dateTime) {
        B_3068_A criterionprogress = new B_3068_A();
        try {
            criterionprogress.J_1907_R = n_1700_B.parse(dateTime);
            return criterionprogress;
        }
        catch (ParseException parseexception) {
            throw new JsonSyntaxException("Invalid datetime: " + dateTime, (Throwable)parseexception);
        }
    }
}


/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonObject;
import java.util.Objects;
import lightning.product.ValueObject;
import lightning.product.K_1289_S;
import lightning.product.JsonUtils;

public class RealmsWorldOptions
extends ValueObject {
    public Boolean n_1700_B;
    public Boolean J_1907_R;
    public Boolean R_4764_Y;
    public Boolean G_564_y;
    public Integer P_1922_E;
    public Boolean u_1723_Y;
    public Boolean v_4262_N;
    public Integer w_1484_f;
    public Integer t_148_a;
    public String s_956_w;
    public long u_2550_I;
    public String M_588_G;
    public boolean P_4830_p;
    public boolean h_1847_R;
    private static final String Q_4569_t = null;

    public RealmsWorldOptions(Boolean atlasTexturesIn, Boolean p_i51651_2_, Boolean p_i51651_3_, Boolean p_i51651_4_, Integer p_i51651_5_, Boolean p_i51651_6_, Integer p_i51651_7_, Integer p_i51651_8_, Boolean p_i51651_9_, String p_i51651_10_) {
        this.n_1700_B = atlasTexturesIn;
        this.J_1907_R = p_i51651_2_;
        this.R_4764_Y = p_i51651_3_;
        this.G_564_y = p_i51651_4_;
        this.P_1922_E = p_i51651_5_;
        this.u_1723_Y = p_i51651_6_;
        this.w_1484_f = p_i51651_7_;
        this.t_148_a = p_i51651_8_;
        this.v_4262_N = p_i51651_9_;
        this.s_956_w = p_i51651_10_;
    }

    public static RealmsWorldOptions n_1700_B() {
        return new RealmsWorldOptions(true, true, true, true, 0, false, 2, 0, false, "");
    }

    public static RealmsWorldOptions J_1907_R() {
        RealmsWorldOptions realmsworldoptions = RealmsWorldOptions.n_1700_B();
        realmsworldoptions.n_1700_B(true);
        return realmsworldoptions;
    }

    public void n_1700_B(boolean p_230789_1_) {
        this.h_1847_R = p_230789_1_;
    }

    public static RealmsWorldOptions n_1700_B(JsonObject p_230788_0_) {
        RealmsWorldOptions realmsworldoptions = new RealmsWorldOptions(JsonUtils.n_1700_B("pvp", p_230788_0_, true), JsonUtils.n_1700_B("spawnAnimals", p_230788_0_, true), JsonUtils.n_1700_B("spawnMonsters", p_230788_0_, true), JsonUtils.n_1700_B("spawnNPCs", p_230788_0_, true), JsonUtils.n_1700_B("spawnProtection", p_230788_0_, 0), JsonUtils.n_1700_B("commandBlocks", p_230788_0_, false), JsonUtils.n_1700_B("difficulty", p_230788_0_, 2), JsonUtils.n_1700_B("gameMode", p_230788_0_, 0), JsonUtils.n_1700_B("forceGameMode", p_230788_0_, false), JsonUtils.n_1700_B("slotName", p_230788_0_, ""));
        realmsworldoptions.u_2550_I = JsonUtils.n_1700_B("worldTemplateId", p_230788_0_, -1L);
        realmsworldoptions.M_588_G = JsonUtils.n_1700_B("worldTemplateImage", p_230788_0_, Q_4569_t);
        realmsworldoptions.P_4830_p = JsonUtils.n_1700_B("adventureMap", p_230788_0_, false);
        return realmsworldoptions;
    }

    public String n_1700_B(int p_230787_1_) {
        if (this.s_956_w != null && !this.s_956_w.isEmpty()) {
            return this.s_956_w;
        }
        return this.h_1847_R ? K_1289_S.n_1700_B("mco.configure.world.slot.empty", new Object[0]) : this.J_1907_R(p_230787_1_);
    }

    public String J_1907_R(int p_230790_1_) {
        return K_1289_S.n_1700_B("mco.configure.world.slot", p_230790_1_);
    }

    public String R_4764_Y() {
        JsonObject jsonobject = new JsonObject();
        if (!this.n_1700_B.booleanValue()) {
            jsonobject.addProperty("pvp", this.n_1700_B);
        }
        if (!this.J_1907_R.booleanValue()) {
            jsonobject.addProperty("spawnAnimals", this.J_1907_R);
        }
        if (!this.R_4764_Y.booleanValue()) {
            jsonobject.addProperty("spawnMonsters", this.R_4764_Y);
        }
        if (!this.G_564_y.booleanValue()) {
            jsonobject.addProperty("spawnNPCs", this.G_564_y);
        }
        if (this.P_1922_E != 0) {
            jsonobject.addProperty("spawnProtection", (Number)this.P_1922_E);
        }
        if (this.u_1723_Y.booleanValue()) {
            jsonobject.addProperty("commandBlocks", this.u_1723_Y);
        }
        if (this.w_1484_f != 2) {
            jsonobject.addProperty("difficulty", (Number)this.w_1484_f);
        }
        if (this.t_148_a != 0) {
            jsonobject.addProperty("gameMode", (Number)this.t_148_a);
        }
        if (this.v_4262_N.booleanValue()) {
            jsonobject.addProperty("forceGameMode", this.v_4262_N);
        }
        if (!Objects.equals(this.s_956_w, "")) {
            jsonobject.addProperty("slotName", this.s_956_w);
        }
        return jsonobject.toString();
    }

    public RealmsWorldOptions G_564_y() {
        return new RealmsWorldOptions(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.w_1484_f, this.t_148_a, this.v_4262_N, this.s_956_w);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return this.G_564_y();
    }
}



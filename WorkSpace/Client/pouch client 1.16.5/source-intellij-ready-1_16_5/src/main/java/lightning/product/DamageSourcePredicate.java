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
import lightning.product.B_4088_l;
import lightning.product.P_11_z;
import lightning.product.b_1430_k;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.i_4431_W;

public class DamageSourcePredicate {
    public static final DamageSourcePredicate n_1700_B = lightning.product.DamageSourcePredicate$n_1700_B.n_1700_B().J_1907_R();
    private final Boolean J_1907_R;
    private final Boolean R_4764_Y;
    private final Boolean G_564_y;
    private final Boolean P_1922_E;
    private final Boolean u_1723_Y;
    private final Boolean v_4262_N;
    private final Boolean w_1484_f;
    private final Boolean t_148_a;
    private final b_1430_k s_956_w;
    private final b_1430_k u_2550_I;

    public DamageSourcePredicate(@Nullable Boolean isProjectile, @Nullable Boolean isExplosion, @Nullable Boolean bypassesArmor, @Nullable Boolean bypassesInvulnerability, @Nullable Boolean bypassesMagic, @Nullable Boolean isFire, @Nullable Boolean isMagic, @Nullable Boolean isLightning, b_1430_k directEntity, b_1430_k sourceEntity) {
        this.J_1907_R = isProjectile;
        this.R_4764_Y = isExplosion;
        this.G_564_y = bypassesArmor;
        this.P_1922_E = bypassesInvulnerability;
        this.u_1723_Y = bypassesMagic;
        this.v_4262_N = isFire;
        this.w_1484_f = isMagic;
        this.t_148_a = isLightning;
        this.s_956_w = directEntity;
        this.u_2550_I = sourceEntity;
    }

    public boolean n_1700_B(B_4088_l player, P_11_z source) {
        return this.n_1700_B(player.c_3005_b(), player.s_4990_V(), source);
    }

    public boolean n_1700_B(e_3591_l world, e_2866_D vector, P_11_z source) {
        if (this == n_1700_B) {
            return true;
        }
        if (this.J_1907_R != null && this.J_1907_R.booleanValue() != source.J_1907_R()) {
            return false;
        }
        if (this.R_4764_Y != null && this.R_4764_Y.booleanValue() != source.G_564_y()) {
            return false;
        }
        if (this.G_564_y != null && this.G_564_y.booleanValue() != source.u_1723_Y()) {
            return false;
        }
        if (this.P_1922_E != null && this.P_1922_E.booleanValue() != source.w_1484_f()) {
            return false;
        }
        if (this.u_1723_Y != null && this.u_1723_Y.booleanValue() != source.t_148_a()) {
            return false;
        }
        if (this.v_4262_N != null && this.v_4262_N.booleanValue() != source.M_182_A()) {
            return false;
        }
        if (this.w_1484_f != null && this.w_1484_f.booleanValue() != source.Y_601_j()) {
            return false;
        }
        if (this.t_148_a != null && this.t_148_a != (source == P_11_z.J_1907_R)) {
            return false;
        }
        if (!this.s_956_w.n_1700_B(world, vector, source.s_956_w())) {
            return false;
        }
        return this.u_2550_I.n_1700_B(world, vector, source.u_2550_I());
    }

    public static DamageSourcePredicate n_1700_B(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonObject jsonobject = i_4431_W.w_1484_f(element, "damage type");
            Boolean obool = DamageSourcePredicate.n_1700_B(jsonobject, "is_projectile");
            Boolean obool1 = DamageSourcePredicate.n_1700_B(jsonobject, "is_explosion");
            Boolean obool2 = DamageSourcePredicate.n_1700_B(jsonobject, "bypasses_armor");
            Boolean obool3 = DamageSourcePredicate.n_1700_B(jsonobject, "bypasses_invulnerability");
            Boolean obool4 = DamageSourcePredicate.n_1700_B(jsonobject, "bypasses_magic");
            Boolean obool5 = DamageSourcePredicate.n_1700_B(jsonobject, "is_fire");
            Boolean obool6 = DamageSourcePredicate.n_1700_B(jsonobject, "is_magic");
            Boolean obool7 = DamageSourcePredicate.n_1700_B(jsonobject, "is_lightning");
            b_1430_k entitypredicate = b_1430_k.n_1700_B(jsonobject.get("direct_entity"));
            b_1430_k entitypredicate1 = b_1430_k.n_1700_B(jsonobject.get("source_entity"));
            return new DamageSourcePredicate(obool, obool1, obool2, obool3, obool4, obool5, obool6, obool7, entitypredicate, entitypredicate1);
        }
        return n_1700_B;
    }

    @Nullable
    private static Boolean n_1700_B(JsonObject object, String memberName) {
        return object.has(memberName) ? Boolean.valueOf(i_4431_W.w_1484_f(object, memberName)) : null;
    }

    public JsonElement n_1700_B() {
        if (this == n_1700_B) {
            return JsonNull.INSTANCE;
        }
        JsonObject jsonobject = new JsonObject();
        this.n_1700_B(jsonobject, "is_projectile", this.J_1907_R);
        this.n_1700_B(jsonobject, "is_explosion", this.R_4764_Y);
        this.n_1700_B(jsonobject, "bypasses_armor", this.G_564_y);
        this.n_1700_B(jsonobject, "bypasses_invulnerability", this.P_1922_E);
        this.n_1700_B(jsonobject, "bypasses_magic", this.u_1723_Y);
        this.n_1700_B(jsonobject, "is_fire", this.v_4262_N);
        this.n_1700_B(jsonobject, "is_magic", this.w_1484_f);
        this.n_1700_B(jsonobject, "is_lightning", this.t_148_a);
        jsonobject.add("direct_entity", this.s_956_w.n_1700_B());
        jsonobject.add("source_entity", this.u_2550_I.n_1700_B());
        return jsonobject;
    }

    private void n_1700_B(JsonObject obj, String key, @Nullable Boolean value) {
        if (value != null) {
            obj.addProperty(key, value);
        }
    }

    public static class n_1700_B {
        private Boolean n_1700_B;
        private Boolean J_1907_R;
        private Boolean R_4764_Y;
        private Boolean G_564_y;
        private Boolean P_1922_E;
        private Boolean u_1723_Y;
        private Boolean v_4262_N;
        private Boolean w_1484_f;
        private b_1430_k t_148_a = b_1430_k.n_1700_B;
        private b_1430_k s_956_w = b_1430_k.n_1700_B;

        public static n_1700_B n_1700_B() {
            return new n_1700_B();
        }

        public n_1700_B n_1700_B(Boolean isProjectile) {
            this.n_1700_B = isProjectile;
            return this;
        }

        public n_1700_B J_1907_R(Boolean isLightning) {
            this.w_1484_f = isLightning;
            return this;
        }

        public n_1700_B n_1700_B(b_1430_k.J_1907_R directEntity) {
            this.t_148_a = directEntity.J_1907_R();
            return this;
        }

        public DamageSourcePredicate J_1907_R() {
            return new DamageSourcePredicate(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w);
        }
    }
}



/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.F_1241_B;
import lightning.product.F_2904_S;
import lightning.product.J_3992_v;
import lightning.product.N_4263_v;
import lightning.product.WitherSkull;
import lightning.product.a_3913_L;
import lightning.product.e_2866_D;
import lightning.product.f_2785_f;
import lightning.product.Fireball;
import lightning.product.h_384_L;
import lightning.product.BadRespawnPointDamage;
import lightning.product.r_4811_B;
import lightning.product.x_282_a;
import lightning.product.y_4711_y;

public class P_11_z {
    public static final P_11_z n_1700_B = new P_11_z("inFire").M_588_G().Q_4569_t();
    public static final P_11_z J_1907_R = new P_11_z("lightningBolt");
    public static final P_11_z R_4764_Y = new P_11_z("onFire").M_588_G().Q_4569_t();
    public static final P_11_z G_564_y = new P_11_z("lava").Q_4569_t();
    public static final P_11_z P_1922_E = new P_11_z("hotFloor").Q_4569_t();
    public static final P_11_z u_1723_Y = new P_11_z("inWall").M_588_G();
    public static final P_11_z v_4262_N = new P_11_z("cramming").M_588_G();
    public static final P_11_z w_1484_f = new P_11_z("drown").M_588_G();
    public static final P_11_z t_148_a = new P_11_z("starve").M_588_G().h_1847_R();
    public static final P_11_z s_956_w = new P_11_z("cactus");
    public static final P_11_z u_2550_I = new P_11_z("fall").M_588_G();
    public static final P_11_z M_588_G = new P_11_z("flyIntoWall").M_588_G();
    public static final P_11_z P_4830_p = new P_11_z("outOfWorld").M_588_G().P_4830_p();
    public static final P_11_z h_1847_R = new P_11_z("generic").M_588_G();
    public static final P_11_z Q_4569_t = new P_11_z("magic").M_588_G().Y_259_p();
    public static final P_11_z M_182_A = new P_11_z("wither").M_588_G();
    public static final P_11_z t_1786_h = new P_11_z("anvil");
    public static final P_11_z multiplayerClientSuggestionProvider = new P_11_z("fallingBlock");
    public static final P_11_z w_1457_N = new P_11_z("dragonBreath").M_588_G();
    public static final P_11_z Y_601_j = new P_11_z("dryout");
    public static final P_11_z Y_259_p = new P_11_z("sweetBerryBush");
    private boolean C_2741_M;
    private boolean k_2293_S;
    private boolean q_2307_F;
    private float Z_875_P = 0.1f;
    private boolean c_3005_b;
    private boolean H_2857_Y;
    private boolean A_4115_X;
    private boolean Y_1740_V;
    private boolean t_4043_B;
    public final String Q_2552_b;

    public static P_11_z J_1907_R(r_4811_B bee) {
        return new f_2785_f("sting", bee);
    }

    public static P_11_z R_4764_Y(r_4811_B mob) {
        return new f_2785_f("mob", mob);
    }

    public static P_11_z n_1700_B(N_4263_v source, r_4811_B indirectEntityIn) {
        return new y_4711_y("mob", source, indirectEntityIn);
    }

    public static P_11_z n_1700_B(a_3913_L player) {
        return new f_2785_f("player", player);
    }

    public static P_11_z n_1700_B(h_384_L arrow, @Nullable N_4263_v indirectEntityIn) {
        return new y_4711_y("arrow", arrow, indirectEntityIn).R_4764_Y();
    }

    public static P_11_z n_1700_B(N_4263_v source, @Nullable N_4263_v indirectEntityIn) {
        return new y_4711_y("trident", source, indirectEntityIn).R_4764_Y();
    }

    public static P_11_z n_1700_B(J_3992_v p_233548_0_, @Nullable N_4263_v p_233548_1_) {
        return new y_4711_y("fireworks", p_233548_0_, p_233548_1_).P_1922_E();
    }

    public static P_11_z n_1700_B(Fireball p_233547_0_, @Nullable N_4263_v p_233547_1_) {
        return p_233547_1_ == null ? new y_4711_y("onFire", p_233547_0_, p_233547_0_).Q_4569_t().R_4764_Y() : new y_4711_y("fireball", p_233547_0_, p_233547_1_).Q_4569_t().R_4764_Y();
    }

    public static P_11_z n_1700_B(WitherSkull p_233549_0_, N_4263_v p_233549_1_) {
        return new y_4711_y("witherSkull", p_233549_0_, p_233549_1_).R_4764_Y();
    }

    public static P_11_z J_1907_R(N_4263_v source, @Nullable N_4263_v indirectEntityIn) {
        return new y_4711_y("thrown", source, indirectEntityIn).R_4764_Y();
    }

    public static P_11_z R_4764_Y(N_4263_v source, @Nullable N_4263_v indirectEntityIn) {
        return new y_4711_y("indirectMagic", source, indirectEntityIn).M_588_G().Y_259_p();
    }

    public static P_11_z n_1700_B(N_4263_v source) {
        return new f_2785_f("thorns", source).k_2293_S().Y_259_p();
    }

    public static P_11_z n_1700_B(@Nullable F_1241_B explosionIn) {
        return P_11_z.G_564_y(explosionIn != null ? explosionIn.G_564_y() : null);
    }

    public static P_11_z G_564_y(@Nullable r_4811_B entityLivingBaseIn) {
        return entityLivingBaseIn != null ? new f_2785_f("explosion.player", entityLivingBaseIn).multiplayerClientSuggestionProvider().P_1922_E() : new P_11_z("explosion").multiplayerClientSuggestionProvider().P_1922_E();
    }

    public static P_11_z n_1700_B() {
        return new BadRespawnPointDamage();
    }

    public String toString() {
        return "DamageSource (" + this.Q_2552_b + ")";
    }

    public boolean J_1907_R() {
        return this.H_2857_Y;
    }

    public P_11_z R_4764_Y() {
        this.H_2857_Y = true;
        return this;
    }

    public boolean G_564_y() {
        return this.t_4043_B;
    }

    public P_11_z P_1922_E() {
        this.t_4043_B = true;
        return this;
    }

    public boolean u_1723_Y() {
        return this.C_2741_M;
    }

    public float v_4262_N() {
        return this.Z_875_P;
    }

    public boolean w_1484_f() {
        return this.k_2293_S;
    }

    public boolean t_148_a() {
        return this.q_2307_F;
    }

    protected P_11_z(String damageTypeIn) {
        this.Q_2552_b = damageTypeIn;
    }

    @Nullable
    public N_4263_v s_956_w() {
        return this.u_2550_I();
    }

    @Nullable
    public N_4263_v u_2550_I() {
        return null;
    }

    protected P_11_z M_588_G() {
        this.C_2741_M = true;
        this.Z_875_P = 0.0f;
        return this;
    }

    protected P_11_z P_4830_p() {
        this.k_2293_S = true;
        return this;
    }

    protected P_11_z h_1847_R() {
        this.q_2307_F = true;
        this.Z_875_P = 0.0f;
        return this;
    }

    protected P_11_z Q_4569_t() {
        this.c_3005_b = true;
        return this;
    }

    public x_282_a n_1700_B(r_4811_B entityLivingBaseIn) {
        r_4811_B livingentity = entityLivingBaseIn.J_2061_p();
        String s = "death.attack." + this.Q_2552_b;
        String s1 = s + ".player";
        return livingentity != null ? new F_2904_S(s1, entityLivingBaseIn.c_(), livingentity.c_()) : new F_2904_S(s, entityLivingBaseIn.c_());
    }

    public boolean M_182_A() {
        return this.c_3005_b;
    }

    public String t_1786_h() {
        return this.Q_2552_b;
    }

    public P_11_z multiplayerClientSuggestionProvider() {
        this.A_4115_X = true;
        return this;
    }

    public boolean w_1457_N() {
        return this.A_4115_X;
    }

    public boolean Y_601_j() {
        return this.Y_1740_V;
    }

    public P_11_z Y_259_p() {
        this.Y_1740_V = true;
        return this;
    }

    public boolean Q_2552_b() {
        N_4263_v entity = this.u_2550_I();
        return entity instanceof a_3913_L && ((a_3913_L)entity).C_415_h.G_564_y;
    }

    @Nullable
    public e_2866_D C_2741_M() {
        return null;
    }
}



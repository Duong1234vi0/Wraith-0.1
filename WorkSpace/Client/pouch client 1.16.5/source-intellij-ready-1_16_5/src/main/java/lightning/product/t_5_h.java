/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.A_268_Q;
import lightning.product.A_4390_i;
import lightning.product.A_69_b;
import lightning.product.B_1132_Q;
import lightning.product.B_4271_P;
import lightning.product.LargeFireball;
import lightning.product.C_4816_K;
import lightning.product.C_4998_y;
import lightning.product.Giant;
import lightning.product.D_2364_U;
import lightning.product.D_3833_N;
import lightning.product.D_4381_C;
import lightning.product.D_686_b;
import lightning.product.Pillager;
import lightning.product.E_4925_L;
import lightning.product.F_2904_S;
import lightning.product.F_4355_q;
import lightning.product.F_666_T;
import lightning.product.G_1455_B;
import lightning.product.G_2149_k;
import lightning.product.G_4536_S;
import lightning.product.Arrow;
import lightning.product.ThrownExperienceBottle;
import lightning.product.I_3700_V;
import lightning.product.LeashFenceKnotEntity;
import lightning.product.I_4817_s;
import lightning.product.Squid;
import lightning.product.J_3992_v;
import lightning.product.Snowball;
import lightning.product.K_4074_S;
import lightning.product.K_550_M;
import lightning.product.L_2225_p;
import lightning.product.L_2837_o;
import lightning.product.L_3233_K;
import lightning.product.M_2433_H;
import lightning.product.M_914_T;
import lightning.product.N_1077_C;
import lightning.product.References;
import lightning.product.N_4263_v;
import lightning.product.Painting;
import lightning.product.MinecartSpawner;
import lightning.product.monsterSpider;
import lightning.product.Q_3816_H;
import lightning.product.Hoglin;
import lightning.product.R_1299_M;
import lightning.product.R_137_s;
import lightning.product.R_1815_U;
import lightning.product.S_3014_o;
import lightning.product.S_3848_S;
import lightning.product.S_922_s;
import lightning.product.WitherSkull;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.T_426_Y;
import lightning.product.U_2912_j;
import lightning.product.Ghast;
import lightning.product.V_3137_a;
import lightning.product.V_3157_k;
import lightning.product.V_3354_l;
import lightning.product.CaveSpider;
import lightning.product.W_1247_f;
import lightning.product.W_4464_I;
import lightning.product.X_1275_n;
import lightning.product.X_4861_v;
import lightning.product.Cow;
import lightning.product.Y_559_r;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.Z_749_F;
import lightning.product.ElderGuardian;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.Mule;
import lightning.product.b_1913_J;
import lightning.product.b_257_Y;
import lightning.product.b_2971_b;
import lightning.product.b_3485_j;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.Bat;
import lightning.product.SpectralArrow;
import lightning.product.Donkey;
import lightning.product.d_3786_K;
import lightning.product.MinecartChest;
import lightning.product.e_3591_l;
import lightning.product.e_3714_r;
import lightning.product.Silverfish;
import lightning.product.g_1253_u;
import lightning.product.g_1462_f;
import lightning.product.g_2336_b;
import lightning.product.g_4407_j;
import lightning.product.Salmon;
import lightning.product.MinecartHopper;
import lightning.product.i_1663_p;
import lightning.product.ZombifiedPiglin;
import lightning.product.j_3013_R;
import lightning.product.j_3341_s;
import lightning.product.MinecartFurnace;
import lightning.product.monsterSkeleton;
import lightning.product.l_3090_i;
import lightning.product.l_4140_i;
import lightning.product.m_1605_o;
import lightning.product.TraderLlama;
import lightning.product.m_3937_C;
import lightning.product.Cod;
import lightning.product.n_1494_c;
import lightning.product.n_4637_L;
import lightning.product.Stray;
import lightning.product.BlockTags;
import lightning.product.q_2335_j;
import lightning.product.q_2896_o;
import lightning.product.r_109_r;
import lightning.product.r_1637_F;
import lightning.product.r_214_x;
import lightning.product.r_4811_B;
import lightning.product.Minecart;
import lightning.product.s_1395_c;
import lightning.product.s_4023_U;
import lightning.product.s_4438_s;
import lightning.product.LightningBolt;
import lightning.product.Horse;
import lightning.product.t_4149_i;
import lightning.product.PrimedTnt;
import lightning.product.t_950_g;
import lightning.product.u_530_F;
import lightning.product.ZombieHorse;
import lightning.product.w_2989_N;
import lightning.product.w_3611_Y;
import lightning.product.DragonFireball;
import lightning.product.ThrownEgg;
import lightning.product.x_268_Y;
import lightning.product.x_282_a;
import lightning.product.x_742_i;
import lightning.product.y_2798_W;
import lightning.product.Endermite;
import lightning.product.y_740_d;
import lightning.product.WitherSkeleton;
import lightning.product.z_2326_J;
import net.minecraft.server.G_564_y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class t_5_h<T extends N_4263_v> {
    private static final Logger W_3464_O = LogManager.getLogger();
    public static final t_5_h<B_1132_Q> n_1700_B = t_5_h.n_1700_B("area_effect_cloud", lightning.product.t_5_h$n_1700_B.n_1700_B(B_1132_Q::new, Z_749_F.u_1723_Y).R_4764_Y().n_1700_B(6.0f, 0.5f).n_1700_B(10).J_1907_R(Integer.MAX_VALUE));
    public static final t_5_h<D_686_b> J_1907_R = t_5_h.n_1700_B("armor_stand", lightning.product.t_5_h$n_1700_B.n_1700_B(D_686_b::new, Z_749_F.u_1723_Y).n_1700_B(0.5f, 1.975f).n_1700_B(10));
    public static final t_5_h<Arrow> R_4764_Y = t_5_h.n_1700_B("arrow", lightning.product.t_5_h$n_1700_B.n_1700_B(Arrow::new, Z_749_F.u_1723_Y).n_1700_B(0.5f, 0.5f).n_1700_B(4).J_1907_R(20));
    public static final t_5_h<Bat> G_564_y = t_5_h.n_1700_B("bat", lightning.product.t_5_h$n_1700_B.n_1700_B(Bat::new, Z_749_F.R_4764_Y).n_1700_B(0.5f, 0.9f).n_1700_B(5));
    public static final t_5_h<b_1913_J> P_1922_E = t_5_h.n_1700_B("bee", lightning.product.t_5_h$n_1700_B.n_1700_B(b_1913_J::new, Z_749_F.J_1907_R).n_1700_B(0.7f, 0.6f).n_1700_B(8));
    public static final t_5_h<G_2149_k> u_1723_Y = t_5_h.n_1700_B("blaze", lightning.product.t_5_h$n_1700_B.n_1700_B(G_2149_k::new, Z_749_F.n_1700_B).R_4764_Y().n_1700_B(0.6f, 1.8f).n_1700_B(8));
    public static final t_5_h<g_1462_f> v_4262_N = t_5_h.n_1700_B("boat", lightning.product.t_5_h$n_1700_B.n_1700_B(g_1462_f::new, Z_749_F.u_1723_Y).n_1700_B(1.375f, 0.5625f).n_1700_B(10));
    public static final t_5_h<K_550_M> w_1484_f = t_5_h.n_1700_B("cat", lightning.product.t_5_h$n_1700_B.n_1700_B(K_550_M::new, Z_749_F.J_1907_R).n_1700_B(0.6f, 0.7f).n_1700_B(8));
    public static final t_5_h<CaveSpider> t_148_a = t_5_h.n_1700_B("cave_spider", lightning.product.t_5_h$n_1700_B.n_1700_B(CaveSpider::new, Z_749_F.n_1700_B).n_1700_B(0.7f, 0.5f).n_1700_B(8));
    public static final t_5_h<X_4861_v> s_956_w = t_5_h.n_1700_B("chicken", lightning.product.t_5_h$n_1700_B.n_1700_B(X_4861_v::new, Z_749_F.J_1907_R).n_1700_B(0.4f, 0.7f).n_1700_B(10));
    public static final t_5_h<Cod> u_2550_I = t_5_h.n_1700_B("cod", lightning.product.t_5_h$n_1700_B.n_1700_B(Cod::new, Z_749_F.P_1922_E).n_1700_B(0.5f, 0.3f).n_1700_B(4));
    public static final t_5_h<Cow> M_588_G = t_5_h.n_1700_B("cow", lightning.product.t_5_h$n_1700_B.n_1700_B(Cow::new, Z_749_F.J_1907_R).n_1700_B(0.9f, 1.4f).n_1700_B(10));
    public static final t_5_h<b_3485_j> P_4830_p = t_5_h.n_1700_B("creeper", lightning.product.t_5_h$n_1700_B.n_1700_B(b_3485_j::new, Z_749_F.n_1700_B).n_1700_B(0.6f, 1.7f).n_1700_B(8));
    public static final t_5_h<Y_559_r> h_1847_R = t_5_h.n_1700_B("dolphin", lightning.product.t_5_h$n_1700_B.n_1700_B(Y_559_r::new, Z_749_F.G_564_y).n_1700_B(0.9f, 0.6f));
    public static final t_5_h<Donkey> Q_4569_t = t_5_h.n_1700_B("donkey", lightning.product.t_5_h$n_1700_B.n_1700_B(Donkey::new, Z_749_F.J_1907_R).n_1700_B(1.3964844f, 1.5f).n_1700_B(10));
    public static final t_5_h<DragonFireball> M_182_A = t_5_h.n_1700_B("dragon_fireball", lightning.product.t_5_h$n_1700_B.n_1700_B(DragonFireball::new, Z_749_F.u_1723_Y).n_1700_B(1.0f, 1.0f).n_1700_B(4).J_1907_R(10));
    public static final t_5_h<S_3848_S> t_1786_h = t_5_h.n_1700_B("drowned", lightning.product.t_5_h$n_1700_B.n_1700_B(S_3848_S::new, Z_749_F.n_1700_B).n_1700_B(0.6f, 1.95f).n_1700_B(8));
    public static final t_5_h<ElderGuardian> multiplayerClientSuggestionProvider = t_5_h.n_1700_B("elder_guardian", lightning.product.t_5_h$n_1700_B.n_1700_B(ElderGuardian::new, Z_749_F.n_1700_B).n_1700_B(1.9975f, 1.9975f).n_1700_B(10));
    public static final t_5_h<V_3354_l> w_1457_N = t_5_h.n_1700_B("end_crystal", lightning.product.t_5_h$n_1700_B.n_1700_B(V_3354_l::new, Z_749_F.u_1723_Y).n_1700_B(2.0f, 2.0f).n_1700_B(16).J_1907_R(Integer.MAX_VALUE));
    public static final t_5_h<b_2971_b> Y_601_j = t_5_h.n_1700_B("ender_dragon", lightning.product.t_5_h$n_1700_B.n_1700_B(b_2971_b::new, Z_749_F.n_1700_B).R_4764_Y().n_1700_B(16.0f, 8.0f).n_1700_B(10));
    public static final t_5_h<M_914_T> Y_259_p = t_5_h.n_1700_B("enderman", lightning.product.t_5_h$n_1700_B.n_1700_B(M_914_T::new, Z_749_F.n_1700_B).n_1700_B(0.6f, 2.9f).n_1700_B(8));
    public static final t_5_h<Endermite> Q_2552_b = t_5_h.n_1700_B("endermite", lightning.product.t_5_h$n_1700_B.n_1700_B(Endermite::new, Z_749_F.n_1700_B).n_1700_B(0.4f, 0.3f).n_1700_B(8));
    public static final t_5_h<e_3714_r> C_2741_M = t_5_h.n_1700_B("evoker", lightning.product.t_5_h$n_1700_B.n_1700_B(e_3714_r::new, Z_749_F.n_1700_B).n_1700_B(0.6f, 1.95f).n_1700_B(8));
    public static final t_5_h<t_950_g> k_2293_S = t_5_h.n_1700_B("evoker_fangs", lightning.product.t_5_h$n_1700_B.n_1700_B(t_950_g::new, Z_749_F.u_1723_Y).n_1700_B(0.5f, 0.8f).n_1700_B(6).J_1907_R(2));
    public static final t_5_h<n_4637_L> q_2307_F = t_5_h.n_1700_B("experience_orb", lightning.product.t_5_h$n_1700_B.n_1700_B(n_4637_L::new, Z_749_F.u_1723_Y).n_1700_B(0.5f, 0.5f).n_1700_B(6).J_1907_R(20));
    public static final t_5_h<R_137_s> Z_875_P = t_5_h.n_1700_B("eye_of_ender", lightning.product.t_5_h$n_1700_B.n_1700_B(R_137_s::new, Z_749_F.u_1723_Y).n_1700_B(0.25f, 0.25f).n_1700_B(4).J_1907_R(4));
    public static final t_5_h<W_4464_I> c_3005_b = t_5_h.n_1700_B("falling_block", lightning.product.t_5_h$n_1700_B.n_1700_B(W_4464_I::new, Z_749_F.u_1723_Y).n_1700_B(0.98f, 0.98f).n_1700_B(10).J_1907_R(20));
    public static final t_5_h<J_3992_v> H_2857_Y = t_5_h.n_1700_B("firework_rocket", lightning.product.t_5_h$n_1700_B.n_1700_B(J_3992_v::new, Z_749_F.u_1723_Y).n_1700_B(0.25f, 0.25f).n_1700_B(4).J_1907_R(10));
    public static final t_5_h<g_1253_u> A_4115_X = t_5_h.n_1700_B("fox", lightning.product.t_5_h$n_1700_B.n_1700_B(g_1253_u::new, Z_749_F.J_1907_R).n_1700_B(0.6f, 0.7f).n_1700_B(8).n_1700_B(a_3742_W.s_4405_m));
    public static final t_5_h<Ghast> Y_1740_V = t_5_h.n_1700_B("ghast", lightning.product.t_5_h$n_1700_B.n_1700_B(Ghast::new, Z_749_F.n_1700_B).R_4764_Y().n_1700_B(4.0f, 4.0f).n_1700_B(10));
    public static final t_5_h<Giant> t_4043_B = t_5_h.n_1700_B("giant", lightning.product.t_5_h$n_1700_B.n_1700_B(Giant::new, Z_749_F.n_1700_B).n_1700_B(3.6f, 12.0f).n_1700_B(10));
    public static final t_5_h<G_1455_B> x_607_J = t_5_h.n_1700_B("guardian", lightning.product.t_5_h$n_1700_B.n_1700_B(G_1455_B::new, Z_749_F.n_1700_B).n_1700_B(0.85f, 0.85f).n_1700_B(8));
    public static final t_5_h<Hoglin> e_4240_b = t_5_h.n_1700_B("hoglin", lightning.product.t_5_h$n_1700_B.n_1700_B(Hoglin::new, Z_749_F.n_1700_B).n_1700_B(1.3964844f, 1.4f).n_1700_B(8));
    public static final t_5_h<Horse> n_3318_d = t_5_h.n_1700_B("horse", lightning.product.t_5_h$n_1700_B.n_1700_B(Horse::new, Z_749_F.J_1907_R).n_1700_B(1.3964844f, 1.6f).n_1700_B(10));
    public static final t_5_h<d_3786_K> d_2427_y = t_5_h.n_1700_B("husk", lightning.product.t_5_h$n_1700_B.n_1700_B(d_3786_K::new, Z_749_F.n_1700_B).n_1700_B(0.6f, 1.95f).n_1700_B(8));
    public static final t_5_h<y_2798_W> z_1737_N = t_5_h.n_1700_B("illusioner", lightning.product.t_5_h$n_1700_B.n_1700_B(y_2798_W::new, Z_749_F.n_1700_B).n_1700_B(0.6f, 1.95f).n_1700_B(8));
    public static final t_5_h<D_2364_U> v_4276_D = t_5_h.n_1700_B("iron_golem", lightning.product.t_5_h$n_1700_B.n_1700_B(D_2364_U::new, Z_749_F.u_1723_Y).n_1700_B(1.4f, 2.7f).n_1700_B(10));
    public static final t_5_h<n_1494_c> d_2461_k = t_5_h.n_1700_B("item", lightning.product.t_5_h$n_1700_B.n_1700_B(n_1494_c::new, Z_749_F.u_1723_Y).n_1700_B(0.25f, 0.25f).n_1700_B(6).J_1907_R(20));
    public static final t_5_h<y_740_d> G_624_v = t_5_h.n_1700_B("item_frame", lightning.product.t_5_h$n_1700_B.n_1700_B(y_740_d::new, Z_749_F.u_1723_Y).n_1700_B(0.5f, 0.5f).n_1700_B(10).J_1907_R(Integer.MAX_VALUE));
    public static final t_5_h<LargeFireball> T_2506_i = t_5_h.n_1700_B("fireball", lightning.product.t_5_h$n_1700_B.n_1700_B(LargeFireball::new, Z_749_F.u_1723_Y).n_1700_B(1.0f, 1.0f).n_1700_B(4).J_1907_R(10));
    public static final t_5_h<LeashFenceKnotEntity> q_4610_l = t_5_h.n_1700_B("leash_knot", lightning.product.t_5_h$n_1700_B.n_1700_B(LeashFenceKnotEntity::new, Z_749_F.u_1723_Y).J_1907_R().n_1700_B(0.5f, 0.5f).n_1700_B(10).J_1907_R(Integer.MAX_VALUE));
    public static final t_5_h<LightningBolt> z_4693_k = t_5_h.n_1700_B("lightning_bolt", lightning.product.t_5_h$n_1700_B.n_1700_B(LightningBolt::new, Z_749_F.u_1723_Y).J_1907_R().n_1700_B(0.0f, 0.0f).n_1700_B(16).J_1907_R(Integer.MAX_VALUE));
    public static final t_5_h<g_4407_j> g_221_o = t_5_h.n_1700_B("llama", lightning.product.t_5_h$n_1700_B.n_1700_B(g_4407_j::new, Z_749_F.J_1907_R).n_1700_B(0.9f, 1.87f).n_1700_B(10));
    public static final t_5_h<r_214_x> e_2887_G = t_5_h.n_1700_B("llama_spit", lightning.product.t_5_h$n_1700_B.n_1700_B(r_214_x::new, Z_749_F.u_1723_Y).n_1700_B(0.25f, 0.25f).n_1700_B(4).J_1907_R(10));
    public static final t_5_h<S_922_s> B_1668_F = t_5_h.n_1700_B("magma_cube", lightning.product.t_5_h$n_1700_B.n_1700_B(S_922_s::new, Z_749_F.n_1700_B).R_4764_Y().n_1700_B(2.04f, 2.04f).n_1700_B(8));
    public static final t_5_h<Minecart> g_164_R = t_5_h.n_1700_B("minecart", lightning.product.t_5_h$n_1700_B.n_1700_B(Minecart::new, Z_749_F.u_1723_Y).n_1700_B(0.98f, 0.7f).n_1700_B(8));
    public static final t_5_h<MinecartChest> X_933_l = t_5_h.n_1700_B("chest_minecart", lightning.product.t_5_h$n_1700_B.n_1700_B(MinecartChest::new, Z_749_F.u_1723_Y).n_1700_B(0.98f, 0.7f).n_1700_B(8));
    public static final t_5_h<z_2326_J> Z_976_R = t_5_h.n_1700_B("command_block_minecart", lightning.product.t_5_h$n_1700_B.n_1700_B(z_2326_J::new, Z_749_F.u_1723_Y).n_1700_B(0.98f, 0.7f).n_1700_B(8));
    public static final t_5_h<MinecartFurnace> H_1990_U = t_5_h.n_1700_B("furnace_minecart", lightning.product.t_5_h$n_1700_B.n_1700_B(MinecartFurnace::new, Z_749_F.u_1723_Y).n_1700_B(0.98f, 0.7f).n_1700_B(8));
    public static final t_5_h<MinecartHopper> N_2525_X = t_5_h.n_1700_B("hopper_minecart", lightning.product.t_5_h$n_1700_B.n_1700_B(MinecartHopper::new, Z_749_F.u_1723_Y).n_1700_B(0.98f, 0.7f).n_1700_B(8));
    public static final t_5_h<MinecartSpawner> c_4037_x = t_5_h.n_1700_B("spawner_minecart", lightning.product.t_5_h$n_1700_B.n_1700_B(MinecartSpawner::new, Z_749_F.u_1723_Y).n_1700_B(0.98f, 0.7f).n_1700_B(8));
    public static final t_5_h<r_1637_F> g_2268_R = t_5_h.n_1700_B("tnt_minecart", lightning.product.t_5_h$n_1700_B.n_1700_B(r_1637_F::new, Z_749_F.u_1723_Y).n_1700_B(0.98f, 0.7f).n_1700_B(8));
    public static final t_5_h<Mule> T_3594_S = t_5_h.n_1700_B("mule", lightning.product.t_5_h$n_1700_B.n_1700_B(Mule::new, Z_749_F.J_1907_R).n_1700_B(1.3964844f, 1.6f).n_1700_B(8));
    public static final t_5_h<s_4023_U> D_4792_h = t_5_h.n_1700_B("mooshroom", lightning.product.t_5_h$n_1700_B.n_1700_B(s_4023_U::new, Z_749_F.J_1907_R).n_1700_B(0.9f, 1.4f).n_1700_B(10));
    public static final t_5_h<l_3090_i> s_2632_s = t_5_h.n_1700_B("ocelot", lightning.product.t_5_h$n_1700_B.n_1700_B(l_3090_i::new, Z_749_F.J_1907_R).n_1700_B(0.6f, 0.7f).n_1700_B(10));
    public static final t_5_h<Painting> l_1233_K = t_5_h.n_1700_B("painting", lightning.product.t_5_h$n_1700_B.n_1700_B(Painting::new, Z_749_F.u_1723_Y).n_1700_B(0.5f, 0.5f).n_1700_B(10).J_1907_R(Integer.MAX_VALUE));
    public static final t_5_h<j_3013_R> z_1333_t = t_5_h.n_1700_B("panda", lightning.product.t_5_h$n_1700_B.n_1700_B(j_3013_R::new, Z_749_F.J_1907_R).n_1700_B(1.3f, 1.25f).n_1700_B(10));
    public static final t_5_h<R_1299_M> O_508_d = t_5_h.n_1700_B("parrot", lightning.product.t_5_h$n_1700_B.n_1700_B(R_1299_M::new, Z_749_F.J_1907_R).n_1700_B(0.5f, 0.9f).n_1700_B(8));
    public static final t_5_h<m_3937_C> r_715_M = t_5_h.n_1700_B("phantom", lightning.product.t_5_h$n_1700_B.n_1700_B(m_3937_C::new, Z_749_F.n_1700_B).n_1700_B(0.9f, 0.5f).n_1700_B(8));
    public static final t_5_h<B_4271_P> A_1038_p = t_5_h.n_1700_B("pig", lightning.product.t_5_h$n_1700_B.n_1700_B(B_4271_P::new, Z_749_F.J_1907_R).n_1700_B(0.9f, 0.9f).n_1700_B(10));
    public static final t_5_h<A_69_b> i_1637_u = t_5_h.n_1700_B("piglin", lightning.product.t_5_h$n_1700_B.n_1700_B(A_69_b::new, Z_749_F.n_1700_B).n_1700_B(0.6f, 1.95f).n_1700_B(8));
    public static final t_5_h<S_3014_o> Ping = t_5_h.n_1700_B("piglin_brute", lightning.product.t_5_h$n_1700_B.n_1700_B(S_3014_o::new, Z_749_F.n_1700_B).n_1700_B(0.6f, 1.95f).n_1700_B(8));
    public static final t_5_h<Pillager> p_178_J = t_5_h.n_1700_B("pillager", lightning.product.t_5_h$n_1700_B.n_1700_B(Pillager::new, Z_749_F.n_1700_B).G_564_y().n_1700_B(0.6f, 1.95f).n_1700_B(8));
    public static final t_5_h<Q_3816_H> RealmsClientConfig = t_5_h.n_1700_B("polar_bear", lightning.product.t_5_h$n_1700_B.n_1700_B(Q_3816_H::new, Z_749_F.J_1907_R).n_1700_B(1.4f, 1.4f).n_1700_B(10));
    public static final t_5_h<PrimedTnt> f_4016_n = t_5_h.n_1700_B("tnt", lightning.product.t_5_h$n_1700_B.n_1700_B(PrimedTnt::new, Z_749_F.u_1723_Y).R_4764_Y().n_1700_B(0.98f, 0.98f).n_1700_B(10).J_1907_R(10));
    public static final t_5_h<x_742_i> j_276_v = t_5_h.n_1700_B("pufferfish", lightning.product.t_5_h$n_1700_B.n_1700_B(x_742_i::new, Z_749_F.P_1922_E).n_1700_B(0.7f, 0.7f).n_1700_B(4));
    public static final t_5_h<M_2433_H> UploadStatus = t_5_h.n_1700_B("rabbit", lightning.product.t_5_h$n_1700_B.n_1700_B(M_2433_H::new, Z_749_F.J_1907_R).n_1700_B(0.4f, 0.5f).n_1700_B(8));
    public static final t_5_h<X_1275_n> e_1992_r = t_5_h.n_1700_B("ravager", lightning.product.t_5_h$n_1700_B.n_1700_B(X_1275_n::new, Z_749_F.n_1700_B).n_1700_B(1.95f, 2.2f).n_1700_B(10));
    public static final t_5_h<Salmon> D_60_a = t_5_h.n_1700_B("salmon", lightning.product.t_5_h$n_1700_B.n_1700_B(Salmon::new, Z_749_F.P_1922_E).n_1700_B(0.7f, 0.4f).n_1700_B(4));
    public static final t_5_h<G_4536_S> k_3961_g = t_5_h.n_1700_B("sheep", lightning.product.t_5_h$n_1700_B.n_1700_B(G_4536_S::new, Z_749_F.J_1907_R).n_1700_B(0.9f, 1.3f).n_1700_B(10));
    public static final t_5_h<m_1605_o> Ops = t_5_h.n_1700_B("shulker", lightning.product.t_5_h$n_1700_B.n_1700_B(m_1605_o::new, Z_749_F.n_1700_B).R_4764_Y().G_564_y().n_1700_B(1.0f, 1.0f).n_1700_B(10));
    public static final t_5_h<L_2837_o> h_4320_q = t_5_h.n_1700_B("shulker_bullet", lightning.product.t_5_h$n_1700_B.n_1700_B(L_2837_o::new, Z_749_F.u_1723_Y).n_1700_B(0.3125f, 0.3125f).n_1700_B(8));
    public static final t_5_h<Silverfish> t_4219_U = t_5_h.n_1700_B("silverfish", lightning.product.t_5_h$n_1700_B.n_1700_B(Silverfish::new, Z_749_F.n_1700_B).n_1700_B(0.4f, 0.3f).n_1700_B(8));
    public static final t_5_h<monsterSkeleton> V_1446_Y = t_5_h.n_1700_B("skeleton", lightning.product.t_5_h$n_1700_B.n_1700_B(monsterSkeleton::new, Z_749_F.n_1700_B).n_1700_B(0.6f, 1.99f).n_1700_B(8));
    public static final t_5_h<D_4381_C> PlayerInfo = t_5_h.n_1700_B("skeleton_horse", lightning.product.t_5_h$n_1700_B.n_1700_B(D_4381_C::new, Z_749_F.J_1907_R).n_1700_B(1.3964844f, 1.6f).n_1700_B(10));
    public static final t_5_h<A_268_Q> V_1225_t = t_5_h.n_1700_B("slime", lightning.product.t_5_h$n_1700_B.n_1700_B(A_268_Q::new, Z_749_F.n_1700_B).n_1700_B(2.04f, 2.04f).n_1700_B(10));
    public static final t_5_h<s_4438_s> U_1241_n = t_5_h.n_1700_B("small_fireball", lightning.product.t_5_h$n_1700_B.n_1700_B(s_4438_s::new, Z_749_F.u_1723_Y).n_1700_B(0.3125f, 0.3125f).n_1700_B(4).J_1907_R(10));
    public static final t_5_h<N_1077_C> q_1982_R = t_5_h.n_1700_B("snow_golem", lightning.product.t_5_h$n_1700_B.n_1700_B(N_1077_C::new, Z_749_F.u_1723_Y).n_1700_B(0.7f, 1.9f).n_1700_B(8));
    public static final t_5_h<Snowball> dtoRealmsServerAddress = t_5_h.n_1700_B("snowball", lightning.product.t_5_h$n_1700_B.n_1700_B(Snowball::new, Z_749_F.u_1723_Y).n_1700_B(0.25f, 0.25f).n_1700_B(4).J_1907_R(10));
    public static final t_5_h<SpectralArrow> w_612_n = t_5_h.n_1700_B("spectral_arrow", lightning.product.t_5_h$n_1700_B.n_1700_B(SpectralArrow::new, Z_749_F.u_1723_Y).n_1700_B(0.5f, 0.5f).n_1700_B(4).J_1907_R(20));
    public static final t_5_h<monsterSpider> RealmsServerPing = t_5_h.n_1700_B("spider", lightning.product.t_5_h$n_1700_B.n_1700_B(monsterSpider::new, Z_749_F.n_1700_B).n_1700_B(1.4f, 0.9f).n_1700_B(8));
    public static final t_5_h<Squid> j_1564_a = t_5_h.n_1700_B("squid", lightning.product.t_5_h$n_1700_B.n_1700_B(Squid::new, Z_749_F.G_564_y).n_1700_B(0.8f, 0.8f).n_1700_B(8));
    public static final t_5_h<Stray> M_1641_O = t_5_h.n_1700_B("stray", lightning.product.t_5_h$n_1700_B.n_1700_B(Stray::new, Z_749_F.n_1700_B).n_1700_B(0.6f, 1.99f).n_1700_B(8));
    public static final t_5_h<L_3233_K> RealmsWorldOptions = t_5_h.n_1700_B("strider", lightning.product.t_5_h$n_1700_B.n_1700_B(L_3233_K::new, Z_749_F.J_1907_R).R_4764_Y().n_1700_B(0.9f, 1.7f).n_1700_B(10));
    public static final t_5_h<ThrownEgg> RealmsWorldResetDto = t_5_h.n_1700_B("egg", lightning.product.t_5_h$n_1700_B.n_1700_B(ThrownEgg::new, Z_749_F.u_1723_Y).n_1700_B(0.25f, 0.25f).n_1700_B(4).J_1907_R(10));
    public static final t_5_h<w_2989_N> RegionPingResult = t_5_h.n_1700_B("ender_pearl", lightning.product.t_5_h$n_1700_B.n_1700_B(w_2989_N::new, Z_749_F.u_1723_Y).n_1700_B(0.25f, 0.25f).n_1700_B(4).J_1907_R(10));
    public static final t_5_h<ThrownExperienceBottle> H_1083_k = t_5_h.n_1700_B("experience_bottle", lightning.product.t_5_h$n_1700_B.n_1700_B(ThrownExperienceBottle::new, Z_749_F.u_1723_Y).n_1700_B(0.25f, 0.25f).n_1700_B(4).J_1907_R(10));
    public static final t_5_h<F_666_T> R_3908_n = t_5_h.n_1700_B("potion", lightning.product.t_5_h$n_1700_B.n_1700_B(F_666_T::new, Z_749_F.u_1723_Y).n_1700_B(0.25f, 0.25f).n_1700_B(4).J_1907_R(10));
    public static final t_5_h<E_4925_L> ValueObject = t_5_h.n_1700_B("trident", lightning.product.t_5_h$n_1700_B.n_1700_B(E_4925_L::new, Z_749_F.u_1723_Y).n_1700_B(0.5f, 0.5f).n_1700_B(4).J_1907_R(20));
    public static final t_5_h<TraderLlama> F_1410_V = t_5_h.n_1700_B("trader_llama", lightning.product.t_5_h$n_1700_B.n_1700_B(TraderLlama::new, Z_749_F.J_1907_R).n_1700_B(0.9f, 1.87f).n_1700_B(10));
    public static final t_5_h<A_4390_i> S_4022_R = t_5_h.n_1700_B("tropical_fish", lightning.product.t_5_h$n_1700_B.n_1700_B(A_4390_i::new, Z_749_F.P_1922_E).n_1700_B(0.5f, 0.4f).n_1700_B(4));
    public static final t_5_h<t_4149_i> l_4537_E = t_5_h.n_1700_B("turtle", lightning.product.t_5_h$n_1700_B.n_1700_B(t_4149_i::new, Z_749_F.J_1907_R).n_1700_B(1.2f, 0.4f).n_1700_B(10));
    public static final t_5_h<D_3833_N> F_2624_D = t_5_h.n_1700_B("vex", lightning.product.t_5_h$n_1700_B.n_1700_B(D_3833_N::new, Z_749_F.n_1700_B).R_4764_Y().n_1700_B(0.4f, 0.8f).n_1700_B(8));
    public static final t_5_h<L_2225_p> RealmsDefaultUncaughtExceptionHandler = t_5_h.n_1700_B("villager", lightning.product.t_5_h$n_1700_B.n_1700_B(L_2225_p::new, Z_749_F.u_1723_Y).n_1700_B(0.6f, 1.95f).n_1700_B(10));
    public static final t_5_h<i_1663_p> y_1700_S = t_5_h.n_1700_B("vindicator", lightning.product.t_5_h$n_1700_B.n_1700_B(i_1663_p::new, Z_749_F.n_1700_B).n_1700_B(0.6f, 1.95f).n_1700_B(8));
    public static final t_5_h<T_426_Y> u_744_e = t_5_h.n_1700_B("wandering_trader", lightning.product.t_5_h$n_1700_B.n_1700_B(T_426_Y::new, Z_749_F.J_1907_R).n_1700_B(0.6f, 1.95f).n_1700_B(10));
    public static final t_5_h<w_3611_Y> RetryCallException = t_5_h.n_1700_B("witch", lightning.product.t_5_h$n_1700_B.n_1700_B(w_3611_Y::new, Z_749_F.n_1700_B).n_1700_B(0.6f, 1.95f).n_1700_B(8));
    public static final t_5_h<I_3700_V> r_3651_U = t_5_h.n_1700_B("wither", lightning.product.t_5_h$n_1700_B.n_1700_B(I_3700_V::new, Z_749_F.n_1700_B).R_4764_Y().n_1700_B(a_3742_W.f_3449_S).n_1700_B(0.9f, 3.5f).n_1700_B(10));
    public static final t_5_h<WitherSkeleton> RowButton = t_5_h.n_1700_B("wither_skeleton", lightning.product.t_5_h$n_1700_B.n_1700_B(WitherSkeleton::new, Z_749_F.n_1700_B).R_4764_Y().n_1700_B(a_3742_W.f_3449_S).n_1700_B(0.7f, 2.4f).n_1700_B(8));
    public static final t_5_h<WitherSkull> LongRunningTask = t_5_h.n_1700_B("wither_skull", lightning.product.t_5_h$n_1700_B.n_1700_B(WitherSkull::new, Z_749_F.u_1723_Y).n_1700_B(0.3125f, 0.3125f).n_1700_B(4).J_1907_R(10));
    public static final t_5_h<q_2335_j> j_2266_I = t_5_h.n_1700_B("wolf", lightning.product.t_5_h$n_1700_B.n_1700_B(q_2335_j::new, Z_749_F.J_1907_R).n_1700_B(0.6f, 0.85f).n_1700_B(10));
    public static final t_5_h<C_4816_K> S_980_j = t_5_h.n_1700_B("zoglin", lightning.product.t_5_h$n_1700_B.n_1700_B(C_4816_K::new, Z_749_F.n_1700_B).R_4764_Y().n_1700_B(1.3964844f, 1.4f).n_1700_B(8));
    public static final t_5_h<F_4355_q> R_3077_Z = t_5_h.n_1700_B("zombie", lightning.product.t_5_h$n_1700_B.n_1700_B(F_4355_q::new, Z_749_F.n_1700_B).n_1700_B(0.6f, 1.95f).n_1700_B(8));
    public static final t_5_h<ZombieHorse> RealmsScreenWithCallback = t_5_h.n_1700_B("zombie_horse", lightning.product.t_5_h$n_1700_B.n_1700_B(ZombieHorse::new, Z_749_F.J_1907_R).n_1700_B(1.3964844f, 1.6f).n_1700_B(10));
    public static final t_5_h<l_4140_i> M_2677_i = t_5_h.n_1700_B("zombie_villager", lightning.product.t_5_h$n_1700_B.n_1700_B(l_4140_i::new, Z_749_F.n_1700_B).n_1700_B(0.6f, 1.95f).n_1700_B(8));
    public static final t_5_h<ZombifiedPiglin> c_132_F = t_5_h.n_1700_B("zombified_piglin", lightning.product.t_5_h$n_1700_B.n_1700_B(ZombifiedPiglin::new, Z_749_F.n_1700_B).R_4764_Y().n_1700_B(0.6f, 1.95f).n_1700_B(8));
    public static final t_5_h<a_3913_L> g_4106_L = t_5_h.n_1700_B("player", lightning.product.t_5_h$n_1700_B.n_1700_B(Z_749_F.u_1723_Y).J_1907_R().n_1700_B().n_1700_B(0.6f, 1.8f).n_1700_B(32).J_1907_R(2));
    public static final t_5_h<W_1247_f> RealmsClientOutdatedScreen = t_5_h.n_1700_B("fishing_bobber", lightning.product.t_5_h$n_1700_B.n_1700_B(Z_749_F.u_1723_Y).J_1907_R().n_1700_B().n_1700_B(0.25f, 0.25f).n_1700_B(4).J_1907_R(5));
    private final J_1907_R<T> RealmsConfirmScreen;
    private final Z_749_F RealmsCreateRealmScreen;
    private final ImmutableSet<T_2915_h> C_290_v;
    private final boolean w_728_N;
    private final boolean J_4256_G;
    private final boolean RealmsLongConfirmationScreen;
    private final boolean RealmsLongRunningMcoTaskScreen;
    private final int i_2993_w;
    private final int RealmsParentalConsentScreen;
    @Nullable
    private String O_2151_c;
    @Nullable
    private x_282_a s_1671_u;
    @Nullable
    private g_2336_b RealmsResetNormalWorldScreen;
    private final R_1815_U C_3538_G;

    private static <T extends N_4263_v> t_5_h<T> n_1700_B(String key, n_1700_B<T> builder) {
        return V_3137_a.n_1700_B(V_3137_a.g_221_o, key, builder.n_1700_B(key));
    }

    public static g_2336_b n_1700_B(t_5_h<?> entityTypeIn) {
        return V_3137_a.g_221_o.J_1907_R(entityTypeIn);
    }

    public static Optional<t_5_h<?>> n_1700_B(String key) {
        return V_3137_a.g_221_o.J_1907_R(g_2336_b.J_1907_R(key));
    }

    public t_5_h(J_1907_R<T> p_i231489_1_, Z_749_F p_i231489_2_, boolean p_i231489_3_, boolean p_i231489_4_, boolean p_i231489_5_, boolean p_i231489_6_, ImmutableSet<T_2915_h> p_i231489_7_, R_1815_U p_i231489_8_, int trackingRange, int updateInterval) {
        this.RealmsConfirmScreen = p_i231489_1_;
        this.RealmsCreateRealmScreen = p_i231489_2_;
        this.RealmsLongRunningMcoTaskScreen = p_i231489_6_;
        this.w_728_N = p_i231489_3_;
        this.J_4256_G = p_i231489_4_;
        this.RealmsLongConfirmationScreen = p_i231489_5_;
        this.C_290_v = p_i231489_7_;
        this.C_3538_G = p_i231489_8_;
        this.i_2993_w = trackingRange;
        this.RealmsParentalConsentScreen = updateInterval;
    }

    @Nullable
    public N_4263_v n_1700_B(e_3591_l worldIn, @Nullable Z_1993_T stack, @Nullable a_3913_L playerIn, c_1514_x pos, a_3160_D reason, boolean p_220331_6_, boolean p_220331_7_) {
        return this.n_1700_B(worldIn, stack == null ? null : stack.Q_4569_t(), stack != null && stack.Y_601_j() ? stack.multiplayerClientSuggestionProvider() : null, playerIn, pos, reason, p_220331_6_, p_220331_7_);
    }

    @Nullable
    public T n_1700_B(e_3591_l worldIn, @Nullable U_2912_j compound, @Nullable x_282_a customName, @Nullable a_3913_L playerIn, c_1514_x pos, a_3160_D reason, boolean p_220342_7_, boolean p_220342_8_) {
        T t = this.J_1907_R(worldIn, compound, customName, playerIn, pos, reason, p_220342_7_, p_220342_8_);
        if (t != null) {
            worldIn.n_1700_B((N_4263_v)t);
        }
        return t;
    }

    @Nullable
    public T J_1907_R(e_3591_l worldIn, @Nullable U_2912_j compound, @Nullable x_282_a customName, @Nullable a_3913_L playerIn, c_1514_x pos, a_3160_D reason, boolean p_220349_7_, boolean p_220349_8_) {
        double d0;
        T t = this.n_1700_B(worldIn);
        if (t == null) {
            return (T)((N_4263_v)null);
        }
        if (p_220349_7_) {
            ((N_4263_v)t).J_1907_R((double)pos.getX() + 0.5, pos.getY() + 1, (double)pos.getZ() + 0.5);
            d0 = t_5_h.n_1700_B((T_1316_M)worldIn, pos, p_220349_8_, ((N_4263_v)t).i_601_W());
        } else {
            d0 = 0.0;
        }
        ((N_4263_v)t).J_1907_R((double)pos.getX() + 0.5, (double)pos.getY() + d0, (double)pos.getZ() + 0.5, u_530_F.v_4262_N(worldIn.w_1457_N.nextFloat() * 360.0f), 0.0f);
        if (t instanceof Z_530_i) {
            Z_530_i mobentity = (Z_530_i)t;
            mobentity.f_3449_S = mobentity.p_178_J;
            mobentity.C_1162_e = mobentity.p_178_J;
            mobentity.n_1700_B(worldIn, worldIn.J_1907_R(mobentity.b_2312_j()), reason, (V_3157_k)null, compound);
            mobentity.G_624_v();
        }
        if (customName != null && t instanceof r_4811_B) {
            ((N_4263_v)t).n_1700_B(customName);
        }
        t_5_h.n_1700_B(worldIn, playerIn, t, compound);
        return t;
    }

    protected static double n_1700_B(T_1316_M worldReader, c_1514_x pos, boolean p_208051_2_, I_4817_s p_208051_3_) {
        I_4817_s axisalignedbb = new I_4817_s(pos);
        if (p_208051_2_) {
            axisalignedbb = axisalignedbb.expand(0.0, -1.0, 0.0);
        }
        Stream<s_1395_c> stream = worldReader.R_4764_Y(null, axisalignedbb, entity -> true);
        return 1.0 + x_268_Y.n_1700_B(b_257_Y.n_1700_B.J_1907_R, p_208051_3_, stream, p_208051_2_ ? -2.0 : -1.0);
    }

    public static void n_1700_B(b_4507_u worldIn, @Nullable a_3913_L player, @Nullable N_4263_v spawnedEntity, @Nullable U_2912_j itemNBT) {
        G_564_y minecraftserver;
        if (itemNBT != null && itemNBT.R_4764_Y("EntityTag", 10) && (minecraftserver = worldIn.T_2506_i()) != null && spawnedEntity != null && (worldIn.Y_259_p || !spawnedEntity.J_303_C() || player != null && minecraftserver.p_178_J().u_1723_Y(player.y_4642_Y()))) {
            U_2912_j compoundnbt = spawnedEntity.P_1922_E(new U_2912_j());
            UUID uuid = spawnedEntity.w_2705_t();
            compoundnbt.n_1700_B(itemNBT.M_182_A("EntityTag"));
            spawnedEntity.a_(uuid);
            spawnedEntity.u_1723_Y(compoundnbt);
        }
    }

    public boolean n_1700_B() {
        return this.w_728_N;
    }

    public boolean J_1907_R() {
        return this.J_4256_G;
    }

    public boolean R_4764_Y() {
        return this.RealmsLongConfirmationScreen;
    }

    public boolean G_564_y() {
        return this.RealmsLongRunningMcoTaskScreen;
    }

    public Z_749_F P_1922_E() {
        return this.RealmsCreateRealmScreen;
    }

    public String u_1723_Y() {
        if (this.O_2151_c == null) {
            this.O_2151_c = j_3341_s.n_1700_B("entity", V_3137_a.g_221_o.J_1907_R(this));
        }
        return this.O_2151_c;
    }

    public x_282_a v_4262_N() {
        if (this.s_1671_u == null) {
            this.s_1671_u = new F_2904_S(this.u_1723_Y());
        }
        return this.s_1671_u;
    }

    public String toString() {
        return this.u_1723_Y();
    }

    public g_2336_b w_1484_f() {
        if (this.RealmsResetNormalWorldScreen == null) {
            g_2336_b resourcelocation = V_3137_a.g_221_o.J_1907_R(this);
            this.RealmsResetNormalWorldScreen = new g_2336_b(resourcelocation.R_4764_Y(), "entities/" + resourcelocation.J_1907_R());
        }
        return this.RealmsResetNormalWorldScreen;
    }

    public float t_148_a() {
        return this.C_3538_G.n_1700_B;
    }

    public float s_956_w() {
        return this.C_3538_G.J_1907_R;
    }

    @Nullable
    public T n_1700_B(b_4507_u worldIn) {
        return this.RealmsConfirmScreen.create(this, worldIn);
    }

    @Nullable
    public static N_4263_v n_1700_B(int id, b_4507_u worldIn) {
        return t_5_h.n_1700_B(worldIn, V_3137_a.g_221_o.n_1700_B(id));
    }

    public static Optional<N_4263_v> n_1700_B(U_2912_j compound, b_4507_u worldIn) {
        return j_3341_s.n_1700_B(t_5_h.n_1700_B(compound).map(entityType -> entityType.n_1700_B(worldIn)), (T entity) -> entity.u_1723_Y(compound), () -> W_3464_O.warn("Skipping Entity with id {}", (Object)compound.M_588_G("id")));
    }

    @Nullable
    private static N_4263_v n_1700_B(b_4507_u worldIn, @Nullable t_5_h<?> type) {
        return type == null ? null : (N_4263_v)type.n_1700_B(worldIn);
    }

    public I_4817_s n_1700_B(double p_220328_1_, double p_220328_3_, double p_220328_5_) {
        float f = this.t_148_a() / 2.0f;
        return new I_4817_s(p_220328_1_ - (double)f, p_220328_3_, p_220328_5_ - (double)f, p_220328_1_ + (double)f, p_220328_3_ + (double)this.s_956_w(), p_220328_5_ + (double)f);
    }

    public boolean n_1700_B(K_4074_S p_233597_1_) {
        if (this.C_290_v.contains((Object)p_233597_1_.J_1907_R())) {
            return false;
        }
        if (this.RealmsLongConfirmationScreen || !p_233597_1_.n_1700_B(BlockTags.j_276_v) && !p_233597_1_.n_1700_B(a_3742_W.LevitationControl) && !C_4998_y.w_1484_f(p_233597_1_) && !p_233597_1_.n_1700_B(a_3742_W.H_2857_Y)) {
            return p_233597_1_.n_1700_B(a_3742_W.f_3449_S) || p_233597_1_.n_1700_B(a_3742_W.s_4405_m) || p_233597_1_.n_1700_B(a_3742_W.d_3244_b);
        }
        return true;
    }

    public R_1815_U u_2550_I() {
        return this.C_3538_G;
    }

    public static Optional<t_5_h<?>> n_1700_B(U_2912_j compound) {
        return V_3137_a.g_221_o.J_1907_R(new g_2336_b(compound.M_588_G("id")));
    }

    @Nullable
    public static N_4263_v n_1700_B(U_2912_j compound, b_4507_u worldIn, Function<N_4263_v, N_4263_v> p_220335_2_) {
        return t_5_h.J_1907_R(compound, worldIn).map(p_220335_2_).map(p_220346_3_ -> {
            if (compound.R_4764_Y("Passengers", 9)) {
                q_2896_o listnbt = compound.G_564_y("Passengers", 10);
                for (int i = 0; i < listnbt.size(); ++i) {
                    N_4263_v entity = t_5_h.n_1700_B(listnbt.n_1700_B(i), worldIn, p_220335_2_);
                    if (entity == null) continue;
                    entity.n_1700_B((N_4263_v)p_220346_3_, true);
                }
            }
            return p_220346_3_;
        }).orElse(null);
    }

    private static Optional<N_4263_v> J_1907_R(U_2912_j compound, b_4507_u worldIn) {
        try {
            return t_5_h.n_1700_B(compound, worldIn);
        }
        catch (RuntimeException runtimeexception) {
            W_3464_O.warn("Exception loading entity: ", (Throwable)runtimeexception);
            return Optional.empty();
        }
    }

    public int M_588_G() {
        return this.i_2993_w;
    }

    public int P_4830_p() {
        return this.RealmsParentalConsentScreen;
    }

    public boolean h_1847_R() {
        return this != g_4106_L && this != e_2887_G && this != r_3651_U && this != G_564_y && this != G_624_v && this != q_4610_l && this != l_1233_K && this != w_1457_N && this != k_2293_S;
    }

    public boolean n_1700_B(r_109_r<t_5_h<?>> tagIn) {
        return tagIn.n_1700_B(this);
    }

    public static class n_1700_B<T extends N_4263_v> {
        private final J_1907_R<T> n_1700_B;
        private final Z_749_F J_1907_R;
        private ImmutableSet<T_2915_h> R_4764_Y = ImmutableSet.of();
        private boolean G_564_y = true;
        private boolean P_1922_E = true;
        private boolean u_1723_Y;
        private boolean v_4262_N;
        private int w_1484_f = 5;
        private int t_148_a = 3;
        private R_1815_U s_956_w = R_1815_U.J_1907_R(0.6f, 1.8f);

        private n_1700_B(J_1907_R<T> factoryIn, Z_749_F classificationIn) {
            this.n_1700_B = factoryIn;
            this.J_1907_R = classificationIn;
            this.v_4262_N = classificationIn == Z_749_F.J_1907_R || classificationIn == Z_749_F.u_1723_Y;
        }

        public static <T extends N_4263_v> n_1700_B<T> n_1700_B(J_1907_R<T> factoryIn, Z_749_F classificationIn) {
            return new n_1700_B<T>(factoryIn, classificationIn);
        }

        public static <T extends N_4263_v> n_1700_B<T> n_1700_B(Z_749_F classificationIn) {
            return new n_1700_B<N_4263_v>((type, world) -> null, classificationIn);
        }

        public n_1700_B<T> n_1700_B(float width, float height) {
            this.s_956_w = R_1815_U.J_1907_R(width, height);
            return this;
        }

        public n_1700_B<T> n_1700_B() {
            this.P_1922_E = false;
            return this;
        }

        public n_1700_B<T> J_1907_R() {
            this.G_564_y = false;
            return this;
        }

        public n_1700_B<T> R_4764_Y() {
            this.u_1723_Y = true;
            return this;
        }

        public n_1700_B<T> n_1700_B(T_2915_h ... p_233607_1_) {
            this.R_4764_Y = ImmutableSet.copyOf((Object[])p_233607_1_);
            return this;
        }

        public n_1700_B<T> G_564_y() {
            this.v_4262_N = true;
            return this;
        }

        public n_1700_B<T> n_1700_B(int p_233606_1_) {
            this.w_1484_f = p_233606_1_;
            return this;
        }

        public n_1700_B<T> J_1907_R(int p_233608_1_) {
            this.t_148_a = p_233608_1_;
            return this;
        }

        public t_5_h<T> n_1700_B(String id) {
            if (this.G_564_y) {
                j_3341_s.n_1700_B(References.Q_4569_t, id);
            }
            return new t_5_h<T>(this.n_1700_B, this.J_1907_R, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.R_4764_Y, this.s_956_w, this.w_1484_f, this.t_148_a);
        }
    }

    public static interface J_1907_R<T extends N_4263_v> {
        public T create(t_5_h<T> var1, b_4507_u var2);
    }
}




/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.function.Function;
import lightning.product.N_3869_i;
import lightning.product.ParticleOptions;
import lightning.product.V_3137_a;
import lightning.product.X_426_i;
import lightning.product.SimpleParticleType;
import lightning.product.DustParticleOptions;
import lightning.product.ParticleType;

public class ParticleTypes {
    public static final SimpleParticleType n_1700_B = ParticleTypes.n_1700_B("ambient_entity_effect", false);
    public static final SimpleParticleType J_1907_R = ParticleTypes.n_1700_B("angry_villager", false);
    public static final SimpleParticleType R_4764_Y = ParticleTypes.n_1700_B("barrier", false);
    public static final ParticleType<X_426_i> G_564_y = ParticleTypes.n_1700_B("block", X_426_i.n_1700_B, X_426_i::n_1700_B);
    public static final SimpleParticleType P_1922_E = ParticleTypes.n_1700_B("bubble", false);
    public static final SimpleParticleType u_1723_Y = ParticleTypes.n_1700_B("cloud", false);
    public static final SimpleParticleType v_4262_N = ParticleTypes.n_1700_B("crit", false);
    public static final SimpleParticleType w_1484_f = ParticleTypes.n_1700_B("damage_indicator", true);
    public static final SimpleParticleType t_148_a = ParticleTypes.n_1700_B("dragon_breath", false);
    public static final SimpleParticleType s_956_w = ParticleTypes.n_1700_B("dripping_lava", false);
    public static final SimpleParticleType u_2550_I = ParticleTypes.n_1700_B("falling_lava", false);
    public static final SimpleParticleType M_588_G = ParticleTypes.n_1700_B("landing_lava", false);
    public static final SimpleParticleType P_4830_p = ParticleTypes.n_1700_B("dripping_water", false);
    public static final SimpleParticleType h_1847_R = ParticleTypes.n_1700_B("falling_water", false);
    public static final ParticleType<DustParticleOptions> Q_4569_t = ParticleTypes.n_1700_B("dust", DustParticleOptions.R_4764_Y, p_239822_0_ -> DustParticleOptions.J_1907_R);
    public static final SimpleParticleType M_182_A = ParticleTypes.n_1700_B("effect", false);
    public static final SimpleParticleType t_1786_h = ParticleTypes.n_1700_B("elder_guardian", true);
    public static final SimpleParticleType multiplayerClientSuggestionProvider = ParticleTypes.n_1700_B("enchanted_hit", false);
    public static final SimpleParticleType w_1457_N = ParticleTypes.n_1700_B("enchant", false);
    public static final SimpleParticleType Y_601_j = ParticleTypes.n_1700_B("end_rod", false);
    public static final SimpleParticleType Y_259_p = ParticleTypes.n_1700_B("entity_effect", false);
    public static final SimpleParticleType Q_2552_b = ParticleTypes.n_1700_B("explosion_emitter", true);
    public static final SimpleParticleType C_2741_M = ParticleTypes.n_1700_B("explosion", true);
    public static final ParticleType<X_426_i> k_2293_S = ParticleTypes.n_1700_B("falling_dust", X_426_i.n_1700_B, X_426_i::n_1700_B);
    public static final SimpleParticleType q_2307_F = ParticleTypes.n_1700_B("firework", false);
    public static final SimpleParticleType Z_875_P = ParticleTypes.n_1700_B("fishing", false);
    public static final SimpleParticleType c_3005_b = ParticleTypes.n_1700_B("flame", false);
    public static final SimpleParticleType H_2857_Y = ParticleTypes.n_1700_B("soul_fire_flame", false);
    public static final SimpleParticleType A_4115_X = ParticleTypes.n_1700_B("soul", false);
    public static final SimpleParticleType Y_1740_V = ParticleTypes.n_1700_B("flash", false);
    public static final SimpleParticleType t_4043_B = ParticleTypes.n_1700_B("happy_villager", false);
    public static final SimpleParticleType x_607_J = ParticleTypes.n_1700_B("composter", false);
    public static final SimpleParticleType e_4240_b = ParticleTypes.n_1700_B("heart", false);
    public static final SimpleParticleType n_3318_d = ParticleTypes.n_1700_B("instant_effect", false);
    public static final ParticleType<N_3869_i> d_2427_y = ParticleTypes.n_1700_B("item", N_3869_i.n_1700_B, N_3869_i::n_1700_B);
    public static final SimpleParticleType z_1737_N = ParticleTypes.n_1700_B("item_slime", false);
    public static final SimpleParticleType v_4276_D = ParticleTypes.n_1700_B("item_snowball", false);
    public static final SimpleParticleType d_2461_k = ParticleTypes.n_1700_B("large_smoke", false);
    public static final SimpleParticleType G_624_v = ParticleTypes.n_1700_B("lava", false);
    public static final SimpleParticleType T_2506_i = ParticleTypes.n_1700_B("mycelium", false);
    public static final SimpleParticleType q_4610_l = ParticleTypes.n_1700_B("note", false);
    public static final SimpleParticleType z_4693_k = ParticleTypes.n_1700_B("poof", true);
    public static final SimpleParticleType g_221_o = ParticleTypes.n_1700_B("portal", false);
    public static final SimpleParticleType e_2887_G = ParticleTypes.n_1700_B("rain", false);
    public static final SimpleParticleType B_1668_F = ParticleTypes.n_1700_B("smoke", false);
    public static final SimpleParticleType g_164_R = ParticleTypes.n_1700_B("sneeze", false);
    public static final SimpleParticleType X_933_l = ParticleTypes.n_1700_B("spit", true);
    public static final SimpleParticleType Z_976_R = ParticleTypes.n_1700_B("squid_ink", true);
    public static final SimpleParticleType H_1990_U = ParticleTypes.n_1700_B("sweep_attack", true);
    public static final SimpleParticleType N_2525_X = ParticleTypes.n_1700_B("totem_of_undying", false);
    public static final SimpleParticleType c_4037_x = ParticleTypes.n_1700_B("underwater", false);
    public static final SimpleParticleType g_2268_R = ParticleTypes.n_1700_B("splash", false);
    public static final SimpleParticleType T_3594_S = ParticleTypes.n_1700_B("witch", false);
    public static final SimpleParticleType D_4792_h = ParticleTypes.n_1700_B("bubble_pop", false);
    public static final SimpleParticleType s_2632_s = ParticleTypes.n_1700_B("current_down", false);
    public static final SimpleParticleType l_1233_K = ParticleTypes.n_1700_B("bubble_column_up", false);
    public static final SimpleParticleType z_1333_t = ParticleTypes.n_1700_B("nautilus", false);
    public static final SimpleParticleType O_508_d = ParticleTypes.n_1700_B("dolphin", false);
    public static final SimpleParticleType r_715_M = ParticleTypes.n_1700_B("campfire_cosy_smoke", true);
    public static final SimpleParticleType A_1038_p = ParticleTypes.n_1700_B("campfire_signal_smoke", true);
    public static final SimpleParticleType i_1637_u = ParticleTypes.n_1700_B("dripping_honey", false);
    public static final SimpleParticleType Ping = ParticleTypes.n_1700_B("falling_honey", false);
    public static final SimpleParticleType p_178_J = ParticleTypes.n_1700_B("landing_honey", false);
    public static final SimpleParticleType RealmsClientConfig = ParticleTypes.n_1700_B("falling_nectar", false);
    public static final SimpleParticleType f_4016_n = ParticleTypes.n_1700_B("ash", false);
    public static final SimpleParticleType j_276_v = ParticleTypes.n_1700_B("crimson_spore", false);
    public static final SimpleParticleType UploadStatus = ParticleTypes.n_1700_B("warped_spore", false);
    public static final SimpleParticleType e_1992_r = ParticleTypes.n_1700_B("dripping_obsidian_tear", false);
    public static final SimpleParticleType D_60_a = ParticleTypes.n_1700_B("falling_obsidian_tear", false);
    public static final SimpleParticleType k_3961_g = ParticleTypes.n_1700_B("landing_obsidian_tear", false);
    public static final SimpleParticleType Ops = ParticleTypes.n_1700_B("reverse_portal", false);
    public static final SimpleParticleType h_4320_q = ParticleTypes.n_1700_B("white_ash", false);
    public static final Codec<ParticleOptions> t_4219_U = V_3137_a.g_164_R.dispatch("type", ParticleOptions::G_564_y, ParticleType::J_1907_R);

    private static SimpleParticleType n_1700_B(String key, boolean alwaysShow) {
        return V_3137_a.n_1700_B(V_3137_a.g_164_R, key, new SimpleParticleType(alwaysShow));
    }

    private static <T extends ParticleOptions> ParticleType<T> n_1700_B(String key, ParticleOptions.n_1700_B<T> deserializer, final Function<ParticleType<T>, Codec<T>> p_218416_2_) {
        return V_3137_a.n_1700_B(V_3137_a.g_164_R, key, new ParticleType<T>(false, deserializer){

            @Override
            public Codec<T> J_1907_R() {
                return (Codec)p_218416_2_.apply(this);
            }
        });
    }
}



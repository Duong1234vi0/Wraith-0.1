/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.data;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.HashSet;
import java.util.Objects;
import java.util.function.Consumer;
import lightning.product.A_2629_w;
import lightning.product.ItemTags;
import lightning.product.P_2068_y;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.V_3982_O;
import lightning.product.a_3742_W;
import lightning.product.b_1430_k;
import lightning.product.b_3278_X;
import lightning.product.e_2674_c;
import lightning.product.RecipeSerializer;
import lightning.product.q_1613_l;
import lightning.product.q_1803_e;
import lightning.product.q_2034_t;
import lightning.product.Items;
import lightning.product.r_109_r;
import lightning.product.r_2687_x;
import lightning.product.MinMaxBounds;
import lightning.product.w_4866_k;
import net.minecraft.data.C_2741_M;
import net.minecraft.data.M_182_A;
import net.minecraft.data.P_4830_p;
import net.minecraft.data.Q_4569_t;
import net.minecraft.data.Y_259_p;
import net.minecraft.data.d_2427_y;
import net.minecraft.data.d_2461_k;
import net.minecraft.data.h_1847_R;
import net.minecraft.data.v_4276_D;
import net.minecraft.data.z_1737_N;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class x_607_J
implements Y_259_p {
    private static final Logger J_1907_R = LogManager.getLogger();
    private static final Gson R_4764_Y = new GsonBuilder().setPrettyPrinting().create();
    private final Q_4569_t G_564_y;

    public x_607_J(Q_4569_t generatorIn) {
        this.G_564_y = generatorIn;
    }

    @Override
    public void n_1700_B(M_182_A cache) throws IOException {
        Path path = this.G_564_y.J_1907_R();
        HashSet set = Sets.newHashSet();
        x_607_J.n_1700_B((C_2741_M recipe) -> {
            if (!set.add(recipe.J_1907_R())) {
                throw new IllegalStateException("Duplicate recipe " + String.valueOf(recipe.J_1907_R()));
            }
            x_607_J.n_1700_B(cache, recipe.P_1922_E(), path.resolve("data/" + recipe.J_1907_R().R_4764_Y() + "/recipes/" + recipe.J_1907_R().J_1907_R() + ".json"));
            JsonObject jsonobject = recipe.R_4764_Y();
            if (jsonobject != null) {
                x_607_J.J_1907_R(cache, jsonobject, path.resolve("data/" + recipe.J_1907_R().R_4764_Y() + "/advancements/" + recipe.G_564_y().J_1907_R() + ".json"));
            }
        });
        x_607_J.J_1907_R(cache, A_2629_w.n_1700_B.n_1700_B().n_1700_B("impossible", new q_2034_t.n_1700_B()).J_1907_R(), path.resolve("data/minecraft/advancements/recipes/root.json"));
    }

    private static void n_1700_B(M_182_A cache, JsonObject cache2, Path recipeJson) {
        try {
            String s = R_4764_Y.toJson((JsonElement)cache2);
            String s1 = n_1700_B.hashUnencodedChars((CharSequence)s).toString();
            if (!Objects.equals(cache.n_1700_B(recipeJson), s1) || !Files.exists(recipeJson, new LinkOption[0])) {
                Files.createDirectories(recipeJson.getParent(), new FileAttribute[0]);
                try (BufferedWriter bufferedwriter = Files.newBufferedWriter(recipeJson, new OpenOption[0]);){
                    bufferedwriter.write(s);
                }
            }
            cache.n_1700_B(recipeJson, s1);
        }
        catch (IOException ioexception) {
            J_1907_R.error("Couldn't save recipe {}", (Object)recipeJson, (Object)ioexception);
        }
    }

    private static void J_1907_R(M_182_A cache, JsonObject cache2, Path advancementJson) {
        try {
            String s = R_4764_Y.toJson((JsonElement)cache2);
            String s1 = n_1700_B.hashUnencodedChars((CharSequence)s).toString();
            if (!Objects.equals(cache.n_1700_B(advancementJson), s1) || !Files.exists(advancementJson, new LinkOption[0])) {
                Files.createDirectories(advancementJson.getParent(), new FileAttribute[0]);
                try (BufferedWriter bufferedwriter = Files.newBufferedWriter(advancementJson, new OpenOption[0]);){
                    bufferedwriter.write(s);
                }
            }
            cache.n_1700_B(advancementJson, s1);
        }
        catch (IOException ioexception) {
            J_1907_R.error("Couldn't save recipe advancement {}", (Object)advancementJson, (Object)ioexception);
        }
    }

    private static void n_1700_B(Consumer<C_2741_M> consumer) {
        x_607_J.n_1700_B(consumer, (q_1803_e)a_3742_W.multiplayerClientSuggestionProvider, ItemTags.Y_259_p);
        x_607_J.J_1907_R(consumer, (q_1803_e)a_3742_W.M_182_A, ItemTags.Y_601_j);
        x_607_J.J_1907_R(consumer, (q_1803_e)a_3742_W.d_3769_f, ItemTags.k_2293_S);
        x_607_J.n_1700_B(consumer, (q_1803_e)a_3742_W.w_1457_N, ItemTags.multiplayerClientSuggestionProvider);
        x_607_J.J_1907_R(consumer, (q_1803_e)a_3742_W.t_1786_h, ItemTags.Q_2552_b);
        x_607_J.J_1907_R(consumer, (q_1803_e)a_3742_W.h_1847_R, ItemTags.w_1457_N);
        x_607_J.J_1907_R(consumer, (q_1803_e)a_3742_W.Q_4569_t, ItemTags.C_2741_M);
        x_607_J.J_1907_R(consumer, (q_1803_e)a_3742_W.n_4560_z, ItemTags.q_2307_F);
        x_607_J.n_1700_B(consumer, (q_1803_e)a_3742_W.g_2268_R, a_3742_W.T_2506_i);
        x_607_J.n_1700_B(consumer, (q_1803_e)a_3742_W.N_2525_X, a_3742_W.d_2461_k);
        x_607_J.n_1700_B(consumer, (q_1803_e)a_3742_W.T_3594_S, a_3742_W.q_4610_l);
        x_607_J.n_1700_B(consumer, (q_1803_e)a_3742_W.c_4037_x, a_3742_W.G_624_v);
        x_607_J.n_1700_B(consumer, (q_1803_e)a_3742_W.Z_976_R, a_3742_W.z_1737_N);
        x_607_J.n_1700_B(consumer, (q_1803_e)a_3742_W.H_1990_U, a_3742_W.v_4276_D);
        x_607_J.n_1700_B(consumer, (q_1803_e)a_3742_W.M_712_N, a_3742_W.T_4001_f);
        x_607_J.n_1700_B(consumer, (q_1803_e)a_3742_W.A_2629_w, a_3742_W.X_1303_p);
        x_607_J.n_1700_B(consumer, (q_1803_e)a_3742_W.O_508_d, a_3742_W.B_1668_F);
        x_607_J.n_1700_B(consumer, (q_1803_e)a_3742_W.l_1233_K, a_3742_W.g_221_o);
        x_607_J.n_1700_B(consumer, (q_1803_e)a_3742_W.r_715_M, a_3742_W.g_164_R);
        x_607_J.n_1700_B(consumer, (q_1803_e)a_3742_W.z_1333_t, a_3742_W.e_2887_G);
        x_607_J.n_1700_B(consumer, (q_1803_e)a_3742_W.D_4792_h, a_3742_W.X_933_l);
        x_607_J.n_1700_B(consumer, (q_1803_e)a_3742_W.s_2632_s, a_3742_W.z_4693_k);
        x_607_J.n_1700_B(consumer, (q_1803_e)a_3742_W.W_4813_f, a_3742_W.B_3068_A);
        x_607_J.n_1700_B(consumer, (q_1803_e)a_3742_W.AdvancementList, a_3742_W.w_2223_C);
        x_607_J.J_1907_R(consumer, (q_1803_e)Items.h_4152_b, a_3742_W.multiplayerClientSuggestionProvider);
        x_607_J.J_1907_R(consumer, (q_1803_e)Items.OreBlock, a_3742_W.M_182_A);
        x_607_J.J_1907_R(consumer, (q_1803_e)Items.M_1398_d, a_3742_W.w_1457_N);
        x_607_J.J_1907_R(consumer, (q_1803_e)Items.IronBarsBlock, a_3742_W.t_1786_h);
        x_607_J.J_1907_R(consumer, (q_1803_e)Items.m_1628_s, a_3742_W.h_1847_R);
        x_607_J.J_1907_R(consumer, (q_1803_e)Items.ObserverBlock, a_3742_W.Q_4569_t);
        x_607_J.R_4764_Y(consumer, a_3742_W.y_3417_N, a_3742_W.multiplayerClientSuggestionProvider);
        x_607_J.G_564_y(consumer, a_3742_W.AutoTool, a_3742_W.multiplayerClientSuggestionProvider);
        x_607_J.P_1922_E(consumer, a_3742_W.AutoLes, a_3742_W.multiplayerClientSuggestionProvider);
        x_607_J.u_1723_Y(consumer, a_3742_W.AutoEat, a_3742_W.multiplayerClientSuggestionProvider);
        x_607_J.v_4262_N(consumer, a_3742_W.l_1268_F, a_3742_W.multiplayerClientSuggestionProvider);
        x_607_J.w_1484_f(consumer, a_3742_W.GuiMove, a_3742_W.multiplayerClientSuggestionProvider);
        x_607_J.t_148_a(consumer, a_3742_W.I_4683_a, a_3742_W.multiplayerClientSuggestionProvider);
        x_607_J.s_956_w(consumer, a_3742_W.g_4560_H, a_3742_W.multiplayerClientSuggestionProvider);
        x_607_J.u_2550_I(consumer, a_3742_W.P_2947_S, a_3742_W.multiplayerClientSuggestionProvider);
        x_607_J.R_4764_Y(consumer, a_3742_W.o_4117_e, a_3742_W.M_182_A);
        x_607_J.G_564_y(consumer, a_3742_W.AutoRespawn, a_3742_W.M_182_A);
        x_607_J.P_1922_E(consumer, a_3742_W.AutoJoiner, a_3742_W.M_182_A);
        x_607_J.u_1723_Y(consumer, a_3742_W.AutoBuy, a_3742_W.M_182_A);
        x_607_J.v_4262_N(consumer, a_3742_W.Z_759_W, a_3742_W.M_182_A);
        x_607_J.w_1484_f(consumer, a_3742_W.Flight, a_3742_W.M_182_A);
        x_607_J.t_148_a(consumer, a_3742_W.g_1031_K, a_3742_W.M_182_A);
        x_607_J.s_956_w(consumer, a_3742_W.Q_2753_H, a_3742_W.M_182_A);
        x_607_J.u_2550_I(consumer, a_3742_W.n_3197_X, a_3742_W.M_182_A);
        x_607_J.R_4764_Y(consumer, a_3742_W.V_3982_O, a_3742_W.d_3769_f);
        x_607_J.G_564_y(consumer, a_3742_W.D_3640_k, a_3742_W.d_3769_f);
        x_607_J.P_1922_E(consumer, a_3742_W.P_1965_C, a_3742_W.d_3769_f);
        x_607_J.u_1723_Y(consumer, a_3742_W.o_3456_E, a_3742_W.d_3769_f);
        x_607_J.v_4262_N(consumer, a_3742_W.q_608_V, a_3742_W.d_3769_f);
        x_607_J.w_1484_f(consumer, a_3742_W.z_936_s, a_3742_W.d_3769_f);
        x_607_J.t_148_a(consumer, a_3742_W.m_3168_q, a_3742_W.d_3769_f);
        x_607_J.s_956_w(consumer, a_3742_W.r_1970_q, a_3742_W.d_3769_f);
        x_607_J.u_2550_I(consumer, a_3742_W.b_1430_k, a_3742_W.d_3769_f);
        x_607_J.R_4764_Y(consumer, a_3742_W.A_1306_N, a_3742_W.w_1457_N);
        x_607_J.G_564_y(consumer, a_3742_W.AutoTrade, a_3742_W.w_1457_N);
        x_607_J.P_1922_E(consumer, a_3742_W.AutoPilot, a_3742_W.w_1457_N);
        x_607_J.u_1723_Y(consumer, a_3742_W.AutoFarm, a_3742_W.w_1457_N);
        x_607_J.v_4262_N(consumer, a_3742_W.J_303_C, a_3742_W.w_1457_N);
        x_607_J.w_1484_f(consumer, a_3742_W.HighJump, a_3742_W.w_1457_N);
        x_607_J.t_148_a(consumer, a_3742_W.n_2689_l, a_3742_W.w_1457_N);
        x_607_J.s_956_w(consumer, a_3742_W.z_2025_Z, a_3742_W.w_1457_N);
        x_607_J.u_2550_I(consumer, a_3742_W.w_2705_t, a_3742_W.w_1457_N);
        x_607_J.R_4764_Y(consumer, a_3742_W.U_3758_B, a_3742_W.t_1786_h);
        x_607_J.G_564_y(consumer, a_3742_W.AutoSoup, a_3742_W.t_1786_h);
        x_607_J.P_1922_E(consumer, a_3742_W.AutoLeave, a_3742_W.t_1786_h);
        x_607_J.u_1723_Y(consumer, a_3742_W.AutoDupe, a_3742_W.t_1786_h);
        x_607_J.v_4262_N(consumer, a_3742_W.f_1574_f, a_3742_W.t_1786_h);
        x_607_J.w_1484_f(consumer, a_3742_W.c_892_d, a_3742_W.t_1786_h);
        x_607_J.t_148_a(consumer, a_3742_W.g_134_G, a_3742_W.t_1786_h);
        x_607_J.s_956_w(consumer, a_3742_W.Y_2080_q, a_3742_W.t_1786_h);
        x_607_J.u_2550_I(consumer, a_3742_W.O_4761_U, a_3742_W.t_1786_h);
        x_607_J.R_4764_Y(consumer, a_3742_W.V_537_k, a_3742_W.h_1847_R);
        x_607_J.G_564_y(consumer, a_3742_W.F_518_D, a_3742_W.h_1847_R);
        x_607_J.P_1922_E(consumer, a_3742_W.h_2848_I, a_3742_W.h_1847_R);
        x_607_J.u_1723_Y(consumer, a_3742_W.k_3129_Y, a_3742_W.h_1847_R);
        x_607_J.v_4262_N(consumer, a_3742_W.X_1313_W, a_3742_W.h_1847_R);
        x_607_J.w_1484_f(consumer, a_3742_W.ElytraMotion, a_3742_W.h_1847_R);
        x_607_J.t_148_a(consumer, a_3742_W.F_3572_x, a_3742_W.h_1847_R);
        x_607_J.s_956_w(consumer, a_3742_W.q_817_e, a_3742_W.h_1847_R);
        x_607_J.u_2550_I(consumer, a_3742_W.X_4895_T, a_3742_W.h_1847_R);
        x_607_J.R_4764_Y(consumer, a_3742_W.c_2086_l, a_3742_W.Q_4569_t);
        x_607_J.G_564_y(consumer, a_3742_W.AutoPotion, a_3742_W.Q_4569_t);
        x_607_J.P_1922_E(consumer, a_3742_W.AutoFish, a_3742_W.Q_4569_t);
        x_607_J.u_1723_Y(consumer, a_3742_W.AutoArmor, a_3742_W.Q_4569_t);
        x_607_J.v_4262_N(consumer, a_3742_W.x_4991_F, a_3742_W.Q_4569_t);
        x_607_J.w_1484_f(consumer, a_3742_W.F_3698_k, a_3742_W.Q_4569_t);
        x_607_J.t_148_a(consumer, a_3742_W.U_144_f, a_3742_W.Q_4569_t);
        x_607_J.s_956_w(consumer, a_3742_W.r_260_T, a_3742_W.Q_4569_t);
        x_607_J.u_2550_I(consumer, a_3742_W.L_103_L, a_3742_W.Q_4569_t);
        x_607_J.R_4764_Y(consumer, a_3742_W.k_200_a, a_3742_W.n_4560_z);
        x_607_J.G_564_y(consumer, a_3742_W.U_4107_W, a_3742_W.n_4560_z);
        x_607_J.P_1922_E(consumer, a_3742_W.K_4866_h, a_3742_W.n_4560_z);
        x_607_J.u_1723_Y(consumer, a_3742_W.I_3736_z, a_3742_W.n_4560_z);
        x_607_J.v_4262_N(consumer, a_3742_W.Z_2021_u, a_3742_W.n_4560_z);
        x_607_J.w_1484_f(consumer, a_3742_W.I_4421_I, a_3742_W.n_4560_z);
        x_607_J.t_148_a(consumer, a_3742_W.A_1604_A, a_3742_W.n_4560_z);
        x_607_J.s_956_w(consumer, a_3742_W.DamageSourcePredicate, a_3742_W.n_4560_z);
        x_607_J.u_2550_I(consumer, a_3742_W.n_3115_n, a_3742_W.n_4560_z);
        x_607_J.M_588_G(consumer, a_3742_W.RealmsParentalConsentScreen, Items.K_4866_h);
        x_607_J.P_4830_p(consumer, a_3742_W.Globals, a_3742_W.RealmsParentalConsentScreen);
        x_607_J.h_1847_R(consumer, a_3742_W.Globals, Items.K_4866_h);
        x_607_J.Q_4569_t(consumer, Items.t_4057_p, a_3742_W.RealmsParentalConsentScreen);
        x_607_J.M_182_A(consumer, Items.t_4057_p, Items.K_4866_h);
        x_607_J.t_1786_h(consumer, Items.WaterlilyBlock, a_3742_W.RealmsParentalConsentScreen);
        x_607_J.M_588_G(consumer, a_3742_W.J_4256_G, Items.I_4421_I);
        x_607_J.P_4830_p(consumer, a_3742_W.DiscordRPC, a_3742_W.J_4256_G);
        x_607_J.h_1847_R(consumer, a_3742_W.DiscordRPC, Items.I_4421_I);
        x_607_J.Q_4569_t(consumer, Items.q_2034_t, a_3742_W.J_4256_G);
        x_607_J.M_182_A(consumer, Items.q_2034_t, Items.I_4421_I);
        x_607_J.t_1786_h(consumer, Items.R_2215_C, a_3742_W.J_4256_G);
        x_607_J.M_588_G(consumer, a_3742_W.RealmsLongConfirmationScreen, Items.q_608_V);
        x_607_J.P_4830_p(consumer, a_3742_W.EcSaver, a_3742_W.RealmsLongConfirmationScreen);
        x_607_J.h_1847_R(consumer, a_3742_W.EcSaver, Items.q_608_V);
        x_607_J.Q_4569_t(consumer, Items.P_2068_y, a_3742_W.RealmsLongConfirmationScreen);
        x_607_J.M_182_A(consumer, Items.P_2068_y, Items.q_608_V);
        x_607_J.t_1786_h(consumer, Items.LeavesBlock, a_3742_W.RealmsLongConfirmationScreen);
        x_607_J.M_588_G(consumer, a_3742_W.C_290_v, Items.n_4560_z);
        x_607_J.P_4830_p(consumer, a_3742_W.ClientSpoof, a_3742_W.C_290_v);
        x_607_J.h_1847_R(consumer, a_3742_W.ClientSpoof, Items.n_4560_z);
        x_607_J.Q_4569_t(consumer, Items.E_4068_x, a_3742_W.C_290_v);
        x_607_J.M_182_A(consumer, Items.E_4068_x, Items.n_4560_z);
        x_607_J.t_1786_h(consumer, Items.V_1395_p, a_3742_W.C_290_v);
        x_607_J.M_588_G(consumer, a_3742_W.RealmsConfirmScreen, Items.D_4237_z);
        x_607_J.P_4830_p(consumer, a_3742_W.Bots, a_3742_W.RealmsConfirmScreen);
        x_607_J.h_1847_R(consumer, a_3742_W.Bots, Items.D_4237_z);
        x_607_J.Q_4569_t(consumer, Items.n_3115_n, a_3742_W.RealmsConfirmScreen);
        x_607_J.M_182_A(consumer, Items.n_3115_n, Items.D_4237_z);
        x_607_J.t_1786_h(consumer, Items.JukeboxBlock, a_3742_W.RealmsConfirmScreen);
        x_607_J.M_588_G(consumer, a_3742_W.RealmsLongRunningMcoTaskScreen, Items.Z_2021_u);
        x_607_J.P_4830_p(consumer, a_3742_W.ElytraHelper, a_3742_W.RealmsLongRunningMcoTaskScreen);
        x_607_J.h_1847_R(consumer, a_3742_W.ElytraHelper, Items.Z_2021_u);
        x_607_J.Q_4569_t(consumer, Items.Y_2805_J, a_3742_W.RealmsLongRunningMcoTaskScreen);
        x_607_J.M_182_A(consumer, Items.Y_2805_J, Items.Z_2021_u);
        x_607_J.t_1786_h(consumer, Items.h_355_y, a_3742_W.RealmsLongRunningMcoTaskScreen);
        x_607_J.M_588_G(consumer, a_3742_W.c_132_F, Items.RequirementsStrategy);
        x_607_J.P_4830_p(consumer, a_3742_W.AutoDuel, a_3742_W.c_132_F);
        x_607_J.h_1847_R(consumer, a_3742_W.AutoDuel, Items.RequirementsStrategy);
        x_607_J.Q_4569_t(consumer, Items.k_200_a, a_3742_W.c_132_F);
        x_607_J.M_182_A(consumer, Items.k_200_a, Items.RequirementsStrategy);
        x_607_J.t_1786_h(consumer, Items.k_2789_z, a_3742_W.c_132_F);
        x_607_J.M_588_G(consumer, a_3742_W.RealmsCreateRealmScreen, Items.d_3769_f);
        x_607_J.P_4830_p(consumer, a_3742_W.ClickFriend, a_3742_W.RealmsCreateRealmScreen);
        x_607_J.h_1847_R(consumer, a_3742_W.ClickFriend, Items.d_3769_f);
        x_607_J.Q_4569_t(consumer, Items.b_4067_I, a_3742_W.RealmsCreateRealmScreen);
        x_607_J.M_182_A(consumer, Items.b_4067_I, Items.d_3769_f);
        x_607_J.t_1786_h(consumer, Items.KelpPlantBlock, a_3742_W.RealmsCreateRealmScreen);
        x_607_J.M_588_G(consumer, a_3742_W.RealmsClientOutdatedScreen, Items.SimpleCriterionTrigger);
        x_607_J.P_4830_p(consumer, a_3742_W.BetterMinecraft, a_3742_W.RealmsClientOutdatedScreen);
        x_607_J.h_1847_R(consumer, a_3742_W.BetterMinecraft, Items.SimpleCriterionTrigger);
        x_607_J.Q_4569_t(consumer, Items.U_4107_W, a_3742_W.RealmsClientOutdatedScreen);
        x_607_J.M_182_A(consumer, Items.U_4107_W, Items.SimpleCriterionTrigger);
        x_607_J.t_1786_h(consumer, Items.IceBlock, a_3742_W.RealmsClientOutdatedScreen);
        x_607_J.M_588_G(consumer, a_3742_W.M_2677_i, Items.CriterionTrigger);
        x_607_J.P_4830_p(consumer, a_3742_W.AutoContract, a_3742_W.M_2677_i);
        x_607_J.h_1847_R(consumer, a_3742_W.AutoContract, Items.CriterionTrigger);
        x_607_J.Q_4569_t(consumer, Items.V_3982_O, a_3742_W.M_2677_i);
        x_607_J.M_182_A(consumer, Items.V_3982_O, Items.CriterionTrigger);
        x_607_J.t_1786_h(consumer, Items.LiquidBlockContainer, a_3742_W.M_2677_i);
        x_607_J.M_588_G(consumer, a_3742_W.RealmsScreenWithCallback, Items.h_1723_G);
        x_607_J.P_4830_p(consumer, a_3742_W.AutoAccept, a_3742_W.RealmsScreenWithCallback);
        x_607_J.h_1847_R(consumer, a_3742_W.AutoAccept, Items.h_1723_G);
        x_607_J.Q_4569_t(consumer, Items.A_1604_A, a_3742_W.RealmsScreenWithCallback);
        x_607_J.M_182_A(consumer, Items.A_1604_A, Items.h_1723_G);
        x_607_J.t_1786_h(consumer, Items.BonemealableBlock, a_3742_W.RealmsScreenWithCallback);
        x_607_J.M_588_G(consumer, a_3742_W.W_3464_O, Items.T_2391_T);
        x_607_J.P_4830_p(consumer, a_3742_W.BotAutoCollector, a_3742_W.W_3464_O);
        x_607_J.h_1847_R(consumer, a_3742_W.BotAutoCollector, Items.T_2391_T);
        x_607_J.Q_4569_t(consumer, Items.b_1430_k, a_3742_W.W_3464_O);
        x_607_J.M_182_A(consumer, Items.b_1430_k, Items.T_2391_T);
        x_607_J.t_1786_h(consumer, Items.JigsawBlock, a_3742_W.W_3464_O);
        x_607_J.M_588_G(consumer, a_3742_W.w_728_N, Items.z_936_s);
        x_607_J.P_4830_p(consumer, a_3742_W.DeathCoords, a_3742_W.w_728_N);
        x_607_J.h_1847_R(consumer, a_3742_W.DeathCoords, Items.z_936_s);
        x_607_J.Q_4569_t(consumer, Items.l_14_c, a_3742_W.w_728_N);
        x_607_J.M_182_A(consumer, Items.l_14_c, Items.z_936_s);
        x_607_J.t_1786_h(consumer, Items.C_1985_D, a_3742_W.w_728_N);
        x_607_J.M_588_G(consumer, a_3742_W.i_2993_w, Items.P_1965_C);
        x_607_J.P_4830_p(consumer, a_3742_W.FlagDetector, a_3742_W.i_2993_w);
        x_607_J.h_1847_R(consumer, a_3742_W.FlagDetector, Items.P_1965_C);
        x_607_J.Q_4569_t(consumer, Items.w_4866_k, a_3742_W.i_2993_w);
        x_607_J.M_182_A(consumer, Items.w_4866_k, Items.P_1965_C);
        x_607_J.t_1786_h(consumer, Items.K_3256_W, a_3742_W.i_2993_w);
        x_607_J.P_4830_p(consumer, a_3742_W.AuctionHelper, a_3742_W.R_3077_Z);
        x_607_J.Q_4569_t(consumer, Items.m_3168_q, a_3742_W.R_3077_Z);
        x_607_J.t_1786_h(consumer, Items.o_3946_o, a_3742_W.R_3077_Z);
        x_607_J.M_588_G(consumer, a_3742_W.g_4106_L, Items.S_4998_h);
        x_607_J.P_4830_p(consumer, a_3742_W.BedrockProxy, a_3742_W.g_4106_L);
        x_607_J.h_1847_R(consumer, a_3742_W.BedrockProxy, Items.S_4998_h);
        x_607_J.Q_4569_t(consumer, Items.D_3640_k, a_3742_W.g_4106_L);
        x_607_J.M_182_A(consumer, Items.D_3640_k, Items.S_4998_h);
        x_607_J.t_1786_h(consumer, Items.SimpleWaterloggedBlock, a_3742_W.g_4106_L);
        x_607_J.multiplayerClientSuggestionProvider(consumer, a_3742_W.M_3508_C, Items.K_4866_h);
        x_607_J.w_1457_N(consumer, a_3742_W.W_1707_M, a_3742_W.M_3508_C);
        x_607_J.Y_601_j(consumer, a_3742_W.W_1707_M, Items.K_4866_h);
        x_607_J.multiplayerClientSuggestionProvider(consumer, a_3742_W.T_2971_J, Items.I_4421_I);
        x_607_J.w_1457_N(consumer, a_3742_W.TriggerBot, a_3742_W.T_2971_J);
        x_607_J.Y_601_j(consumer, a_3742_W.TriggerBot, Items.I_4421_I);
        x_607_J.multiplayerClientSuggestionProvider(consumer, a_3742_W.Q_3581_n, Items.q_608_V);
        x_607_J.w_1457_N(consumer, a_3742_W.r_4601_j, a_3742_W.Q_3581_n);
        x_607_J.Y_601_j(consumer, a_3742_W.r_4601_j, Items.q_608_V);
        x_607_J.multiplayerClientSuggestionProvider(consumer, a_3742_W.S_4258_d, Items.n_4560_z);
        x_607_J.w_1457_N(consumer, a_3742_W.TargetPearl, a_3742_W.S_4258_d);
        x_607_J.Y_601_j(consumer, a_3742_W.TargetPearl, Items.n_4560_z);
        x_607_J.multiplayerClientSuggestionProvider(consumer, a_3742_W.Q_2467_v, Items.D_4237_z);
        x_607_J.w_1457_N(consumer, a_3742_W.PacketCriticals, a_3742_W.Q_2467_v);
        x_607_J.Y_601_j(consumer, a_3742_W.PacketCriticals, Items.D_4237_z);
        x_607_J.multiplayerClientSuggestionProvider(consumer, a_3742_W.I_685_r, Items.Z_2021_u);
        x_607_J.w_1457_N(consumer, a_3742_W.O_726_g, a_3742_W.I_685_r);
        x_607_J.Y_601_j(consumer, a_3742_W.O_726_g, Items.Z_2021_u);
        x_607_J.multiplayerClientSuggestionProvider(consumer, a_3742_W.i_4434_b, Items.RequirementsStrategy);
        x_607_J.w_1457_N(consumer, a_3742_W.NoFriendDamage, a_3742_W.i_4434_b);
        x_607_J.Y_601_j(consumer, a_3742_W.NoFriendDamage, Items.RequirementsStrategy);
        x_607_J.multiplayerClientSuggestionProvider(consumer, a_3742_W.m_2262_U, Items.d_3769_f);
        x_607_J.w_1457_N(consumer, a_3742_W.Surround, a_3742_W.m_2262_U);
        x_607_J.Y_601_j(consumer, a_3742_W.Surround, Items.d_3769_f);
        x_607_J.multiplayerClientSuggestionProvider(consumer, a_3742_W.l_4627_h, Items.SimpleCriterionTrigger);
        x_607_J.w_1457_N(consumer, a_3742_W.r_4217_P, a_3742_W.l_4627_h);
        x_607_J.Y_601_j(consumer, a_3742_W.r_4217_P, Items.SimpleCriterionTrigger);
        x_607_J.multiplayerClientSuggestionProvider(consumer, a_3742_W.P_4639_N, Items.CriterionTrigger);
        x_607_J.w_1457_N(consumer, a_3742_W.NoEntityTrace, a_3742_W.P_4639_N);
        x_607_J.Y_601_j(consumer, a_3742_W.NoEntityTrace, Items.CriterionTrigger);
        x_607_J.multiplayerClientSuggestionProvider(consumer, a_3742_W.U_532_X, Items.h_1723_G);
        x_607_J.w_1457_N(consumer, a_3742_W.KBDisplacement, a_3742_W.U_532_X);
        x_607_J.Y_601_j(consumer, a_3742_W.KBDisplacement, Items.h_1723_G);
        x_607_J.multiplayerClientSuggestionProvider(consumer, a_3742_W.K_3372_t, Items.T_2391_T);
        x_607_J.w_1457_N(consumer, a_3742_W.Velocity, a_3742_W.K_3372_t);
        x_607_J.Y_601_j(consumer, a_3742_W.Velocity, Items.T_2391_T);
        x_607_J.multiplayerClientSuggestionProvider(consumer, a_3742_W.m_891_U, Items.z_936_s);
        x_607_J.w_1457_N(consumer, a_3742_W.TargetStrafe, a_3742_W.m_891_U);
        x_607_J.Y_601_j(consumer, a_3742_W.TargetStrafe, Items.z_936_s);
        x_607_J.multiplayerClientSuggestionProvider(consumer, a_3742_W.h_3270_j, Items.P_1965_C);
        x_607_J.w_1457_N(consumer, a_3742_W.E_2115_e, a_3742_W.h_3270_j);
        x_607_J.Y_601_j(consumer, a_3742_W.E_2115_e, Items.P_1965_C);
        x_607_J.multiplayerClientSuggestionProvider(consumer, a_3742_W.y_1945_D, Items.ServerFunctionManager);
        x_607_J.w_1457_N(consumer, a_3742_W.HoleFill, a_3742_W.y_1945_D);
        x_607_J.Y_601_j(consumer, a_3742_W.HoleFill, Items.ServerFunctionManager);
        x_607_J.multiplayerClientSuggestionProvider(consumer, a_3742_W.P_328_a, Items.S_4998_h);
        x_607_J.w_1457_N(consumer, a_3742_W.NoServerDesync, a_3742_W.P_328_a);
        x_607_J.Y_601_j(consumer, a_3742_W.NoServerDesync, Items.S_4998_h);
        x_607_J.Y_259_p(consumer, a_3742_W.s_4054_j, Items.K_4866_h);
        x_607_J.Y_259_p(consumer, a_3742_W.AutoExplosion, Items.I_4421_I);
        x_607_J.Y_259_p(consumer, a_3742_W.AutoSwap, Items.q_608_V);
        x_607_J.Y_259_p(consumer, a_3742_W.AutoAnchor, Items.n_4560_z);
        x_607_J.Y_259_p(consumer, a_3742_W.s_4447_V, Items.D_4237_z);
        x_607_J.Y_259_p(consumer, a_3742_W.AutoTotem, Items.Z_2021_u);
        x_607_J.Y_259_p(consumer, a_3742_W.t_4433_T, Items.RequirementsStrategy);
        x_607_J.Y_259_p(consumer, a_3742_W.AttackAura, Items.d_3769_f);
        x_607_J.Y_259_p(consumer, a_3742_W.AntiBot, Items.SimpleCriterionTrigger);
        x_607_J.Y_259_p(consumer, a_3742_W.l_4397_i, Items.CriterionTrigger);
        x_607_J.Y_259_p(consumer, a_3742_W.h_3858_e, Items.h_1723_G);
        x_607_J.Y_259_p(consumer, a_3742_W.AntiSurround, Items.T_2391_T);
        x_607_J.Y_259_p(consumer, a_3742_W.AutoCrystal, Items.z_936_s);
        x_607_J.Y_259_p(consumer, a_3742_W.AutoTrap, Items.P_1965_C);
        x_607_J.Y_259_p(consumer, a_3742_W.I_2209_R, Items.ServerFunctionManager);
        x_607_J.Y_259_p(consumer, a_3742_W.AimAssist, Items.S_4998_h);
        x_607_J.Q_2552_b(consumer, a_3742_W.E_738_L, Items.K_4866_h);
        x_607_J.Q_2552_b(consumer, a_3742_W.R_4912_F, Items.I_4421_I);
        x_607_J.Q_2552_b(consumer, a_3742_W.S_315_z, Items.q_608_V);
        x_607_J.Q_2552_b(consumer, a_3742_W.f_4340_D, Items.n_4560_z);
        x_607_J.Q_2552_b(consumer, a_3742_W.E_170_p, Items.D_4237_z);
        x_607_J.Q_2552_b(consumer, a_3742_W.o_977_F, Items.Z_2021_u);
        x_607_J.Q_2552_b(consumer, a_3742_W.n_421_x, Items.RequirementsStrategy);
        x_607_J.Q_2552_b(consumer, a_3742_W.m_229_F, Items.d_3769_f);
        x_607_J.Q_2552_b(consumer, a_3742_W.A_4252_m, Items.SimpleCriterionTrigger);
        x_607_J.Q_2552_b(consumer, a_3742_W.K_2336_H, Items.CriterionTrigger);
        x_607_J.Q_2552_b(consumer, a_3742_W.e_87_p, Items.h_1723_G);
        x_607_J.Q_2552_b(consumer, a_3742_W.a_794_m, Items.T_2391_T);
        x_607_J.Q_2552_b(consumer, a_3742_W.A_2204_Z, Items.z_936_s);
        x_607_J.Q_2552_b(consumer, a_3742_W.S_1165_y, Items.P_1965_C);
        x_607_J.Q_2552_b(consumer, a_3742_W.CavityFinder, Items.ServerFunctionManager);
        x_607_J.Q_2552_b(consumer, a_3742_W.p_1976_q, Items.S_4998_h);
        d_2427_y.n_1700_B(a_3742_W.H_1475_K, 6).n_1700_B(Character.valueOf('#'), a_3742_W.H_1873_g).n_1700_B(Character.valueOf('S'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.D_1621_L).n_1700_B("XSX").n_1700_B("X#X").n_1700_B("XSX").n_1700_B("has_rail", x_607_J.n_1700_B((q_1803_e)a_3742_W.Y_776_s)).n_1700_B(consumer);
        z_1737_N.n_1700_B(a_3742_W.v_4262_N, 2).J_1907_R(a_3742_W.P_1922_E).J_1907_R(a_3742_W.P_4830_p).n_1700_B("has_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_1922_E)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.c_1608_O).n_1700_B(Character.valueOf('I'), a_3742_W.H_1883_T).n_1700_B(Character.valueOf('i'), Items.D_1621_L).n_1700_B("III").n_1700_B(" i ").n_1700_B("iii").n_1700_B("has_iron_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.H_1883_T)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.p_1168_n).n_1700_B(Character.valueOf('/'), Items.A_4514_U).n_1700_B(Character.valueOf('_'), a_3742_W.MoveHelper).n_1700_B("///").n_1700_B(" / ").n_1700_B("/_/").n_1700_B("has_stone_slab", x_607_J.n_1700_B((q_1803_e)a_3742_W.MoveHelper)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.g_24_p, 4).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.W_1488_x).n_1700_B(Character.valueOf('Y'), Items.H_274_C).n_1700_B("X").n_1700_B("#").n_1700_B("Y").n_1700_B("has_feather", x_607_J.n_1700_B(Items.H_274_C)).n_1700_B("has_flint", x_607_J.n_1700_B(Items.W_1488_x)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.y_254_d, 1).n_1700_B(Character.valueOf('P'), ItemTags.R_4764_Y).n_1700_B(Character.valueOf('S'), ItemTags.s_956_w).n_1700_B("PSP").n_1700_B("P P").n_1700_B("PSP").n_1700_B("has_planks", x_607_J.n_1700_B(ItemTags.R_4764_Y)).n_1700_B("has_wood_slab", x_607_J.n_1700_B(ItemTags.s_956_w)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.k_578_l).n_1700_B(Character.valueOf('S'), Items.FallingBlock).n_1700_B(Character.valueOf('G'), a_3742_W.e_1992_r).n_1700_B(Character.valueOf('O'), a_3742_W.ClientBootstrap).n_1700_B("GGG").n_1700_B("GSG").n_1700_B("OOO").n_1700_B("has_nether_star", x_607_J.n_1700_B(Items.FallingBlock)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.t_4057_p).n_1700_B(Character.valueOf('P'), ItemTags.R_4764_Y).n_1700_B(Character.valueOf('H'), Items.StemGrownBlock).n_1700_B("PPP").n_1700_B("HHH").n_1700_B("PPP").n_1700_B("has_honeycomb", x_607_J.n_1700_B(Items.StemGrownBlock)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.MyceliumBlock).J_1907_R(Items.S_4088_D).J_1907_R(Items.s_3401_U, 6).n_1700_B("has_beetroot", x_607_J.n_1700_B(Items.s_3401_U)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.K_4866_h).J_1907_R(Items.B_3068_A).n_1700_B("black_dye").n_1700_B("has_ink_sac", x_607_J.n_1700_B(Items.B_3068_A)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.K_4866_h).J_1907_R(a_3742_W.f_3449_S).n_1700_B("black_dye").n_1700_B("has_black_flower", x_607_J.n_1700_B((q_1803_e)a_3742_W.f_3449_S)).n_1700_B(consumer, "black_dye_from_wither_rose");
        z_1737_N.n_1700_B(Items.C_3528_u, 2).J_1907_R(Items.A_2487_t).n_1700_B("has_blaze_rod", x_607_J.n_1700_B(Items.A_2487_t)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.I_4421_I).J_1907_R(Items.W_4813_f).n_1700_B("blue_dye").n_1700_B("has_lapis_lazuli", x_607_J.n_1700_B(Items.W_4813_f)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.I_4421_I).J_1907_R(a_3742_W.D_4361_a).n_1700_B("blue_dye").n_1700_B("has_blue_flower", x_607_J.n_1700_B((q_1803_e)a_3742_W.D_4361_a)).n_1700_B(consumer, "blue_dye_from_cornflower");
        d_2427_y.n_1700_B(a_3742_W.G_4691_Q).n_1700_B(Character.valueOf('#'), a_3742_W.ServerHelper).n_1700_B("###").n_1700_B("###").n_1700_B("###").n_1700_B("has_packed_ice", x_607_J.n_1700_B((q_1803_e)a_3742_W.ServerHelper)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.Nuker).n_1700_B(Character.valueOf('X'), Items.r_1970_q).n_1700_B("XXX").n_1700_B("XXX").n_1700_B("XXX").n_1700_B("has_bonemeal", x_607_J.n_1700_B(Items.r_1970_q)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.r_1970_q, 3).J_1907_R(Items.DamageSourcePredicate).n_1700_B("bonemeal").n_1700_B("has_bone", x_607_J.n_1700_B(Items.DamageSourcePredicate)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.r_1970_q, 9).J_1907_R(a_3742_W.Nuker).n_1700_B("bonemeal").n_1700_B("has_bone_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.Nuker)).n_1700_B(consumer, "bone_meal_from_bone_block");
        z_1737_N.n_1700_B(Items.K_4237_u).J_1907_R(Items.l_3370_o, 3).J_1907_R(Items.y_254_d).n_1700_B("has_paper", x_607_J.n_1700_B(Items.l_3370_o)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.UploadTokenCache).n_1700_B(Character.valueOf('#'), ItemTags.R_4764_Y).n_1700_B(Character.valueOf('X'), Items.K_4237_u).n_1700_B("###").n_1700_B("XXX").n_1700_B("###").n_1700_B("has_book", x_607_J.n_1700_B(Items.K_4237_u)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.R_1796_s).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.Animation).n_1700_B(" #X").n_1700_B("# X").n_1700_B(" #X").n_1700_B("has_string", x_607_J.n_1700_B(Items.Animation)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.S_4088_D, 4).n_1700_B(Character.valueOf('#'), ItemTags.R_4764_Y).n_1700_B("# #").n_1700_B(" # ").n_1700_B("has_brown_mushroom", x_607_J.n_1700_B((q_1803_e)a_3742_W.JsonUtils)).n_1700_B("has_red_mushroom", x_607_J.n_1700_B((q_1803_e)a_3742_W.RealmsPersistence)).n_1700_B("has_mushroom_stew", x_607_J.n_1700_B(Items.MinecraftAccess)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.m_3828_C).n_1700_B(Character.valueOf('#'), Items.V_3441_j).n_1700_B("###").n_1700_B("has_wheat", x_607_J.n_1700_B(Items.V_3441_j)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.e_837_t).n_1700_B(Character.valueOf('B'), Items.A_2487_t).n_1700_B(Character.valueOf('#'), ItemTags.s_2632_s).n_1700_B(" B ").n_1700_B("###").n_1700_B("has_blaze_rod", x_607_J.n_1700_B(Items.A_2487_t)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.d_4007_L).n_1700_B(Character.valueOf('#'), Items.F_489_x).n_1700_B("##").n_1700_B("##").n_1700_B("has_brick", x_607_J.n_1700_B(Items.F_489_x)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.NoWeb, 6).n_1700_B(Character.valueOf('#'), a_3742_W.d_4007_L).n_1700_B("###").n_1700_B("has_brick_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.d_4007_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.B_1146_q, 4).n_1700_B(Character.valueOf('#'), a_3742_W.d_4007_L).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_brick_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.d_4007_L)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.q_608_V).J_1907_R(Items.M_712_N).n_1700_B("brown_dye").n_1700_B("has_cocoa_beans", x_607_J.n_1700_B(Items.M_712_N)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.G_1539_D).n_1700_B(Character.valueOf('#'), Items.D_1621_L).n_1700_B("# #").n_1700_B(" # ").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.a_178_J).n_1700_B(Character.valueOf('A'), Items.H_2506_c).n_1700_B(Character.valueOf('B'), Items.o_3456_E).n_1700_B(Character.valueOf('C'), Items.V_3441_j).n_1700_B(Character.valueOf('E'), Items.s_4405_m).n_1700_B("AAA").n_1700_B("BEB").n_1700_B("CCC").n_1700_B("has_egg", x_607_J.n_1700_B(Items.s_4405_m)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.k_1366_K).n_1700_B(Character.valueOf('L'), ItemTags.t_1786_h).n_1700_B(Character.valueOf('S'), Items.A_4514_U).n_1700_B(Character.valueOf('C'), ItemTags.N_2525_X).n_1700_B(" S ").n_1700_B("SCS").n_1700_B("LLL").n_1700_B("has_stick", x_607_J.n_1700_B(Items.A_4514_U)).n_1700_B("has_coal", x_607_J.n_1700_B(ItemTags.N_2525_X)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.EndRodBlock).n_1700_B(Character.valueOf('#'), Items.w_2223_C).n_1700_B(Character.valueOf('X'), Items.BaseCoralWallFanBlock).n_1700_B("# ").n_1700_B(" X").n_1700_B("has_carrot", x_607_J.n_1700_B(Items.BaseCoralWallFanBlock)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.j_3599_p).n_1700_B(Character.valueOf('#'), Items.w_2223_C).n_1700_B(Character.valueOf('X'), Items.J_739_q).n_1700_B("# ").n_1700_B(" X").n_1700_B("has_warped_fungus", x_607_J.n_1700_B(Items.J_739_q)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.m_1621_v).n_1700_B(Character.valueOf('#'), Items.D_1621_L).n_1700_B("# #").n_1700_B("# #").n_1700_B("###").n_1700_B("has_water_bucket", x_607_J.n_1700_B(Items.W_2770_z)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.P_2068_y).n_1700_B(Character.valueOf('#'), ItemTags.s_956_w).n_1700_B("# #").n_1700_B("# #").n_1700_B("###").n_1700_B("has_wood_slab", x_607_J.n_1700_B(ItemTags.s_956_w)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.L_1362_X).n_1700_B(Character.valueOf('#'), ItemTags.R_4764_Y).n_1700_B("###").n_1700_B("# #").n_1700_B("###").n_1700_B("has_lots_of_items", new P_2068_y.n_1700_B(b_1430_k.n_1700_B.n_1700_B, MinMaxBounds.G_564_y.J_1907_R(10), MinMaxBounds.G_564_y.P_1922_E, MinMaxBounds.G_564_y.P_1922_E, new w_4866_k[0])).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.k_1366_K).n_1700_B(Character.valueOf('A'), a_3742_W.L_1362_X).n_1700_B(Character.valueOf('B'), Items.u_925_K).n_1700_B("A").n_1700_B("B").n_1700_B("has_minecart", x_607_J.n_1700_B(Items.u_925_K)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.e_2973_e).n_1700_B(Character.valueOf('#'), a_3742_W.Speed).n_1700_B("#").n_1700_B("#").n_1700_B("has_nether_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_1015_G)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.Q_4222_k).n_1700_B(Character.valueOf('#'), a_3742_W.Spider).n_1700_B("#").n_1700_B("#").n_1700_B("has_chiseled_quartz_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.Q_4222_k)).n_1700_B("has_quartz_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_3676_m)).n_1700_B("has_quartz_pillar", x_607_J.n_1700_B((q_1803_e)a_3742_W.f_887_Z)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.I_3457_f).n_1700_B(Character.valueOf('#'), a_3742_W.Phase).n_1700_B("#").n_1700_B("#").n_1700_B("has_stone_bricks", x_607_J.n_1700_B(ItemTags.G_564_y)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.v_887_r).n_1700_B(Character.valueOf('#'), Items.i_4833_u).n_1700_B("##").n_1700_B("##").n_1700_B("has_clay_ball", x_607_J.n_1700_B(Items.i_4833_u)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.A_2629_w).n_1700_B(Character.valueOf('#'), Items.ServerHandshakePacketListener).n_1700_B(Character.valueOf('X'), Items.v_570_f).n_1700_B(" # ").n_1700_B("#X#").n_1700_B(" # ").n_1700_B("has_redstone", x_607_J.n_1700_B(Items.v_570_f)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.T_797_O, 9).J_1907_R(a_3742_W.ItemHelper).n_1700_B("has_coal_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.ItemHelper)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.ItemHelper).n_1700_B(Character.valueOf('#'), Items.T_797_O).n_1700_B("###").n_1700_B("###").n_1700_B("###").n_1700_B("has_coal", x_607_J.n_1700_B(Items.T_797_O)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.u_2550_I, 4).n_1700_B(Character.valueOf('D'), a_3742_W.s_956_w).n_1700_B(Character.valueOf('G'), a_3742_W.t_4043_B).n_1700_B("DG").n_1700_B("GD").n_1700_B("has_gravel", x_607_J.n_1700_B((q_1803_e)a_3742_W.t_4043_B)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.NoSlow, 6).n_1700_B(Character.valueOf('#'), a_3742_W.P_4830_p).n_1700_B("###").n_1700_B("has_cobblestone", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_4830_p)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.h_3859_C, 6).n_1700_B(Character.valueOf('#'), a_3742_W.P_4830_p).n_1700_B("###").n_1700_B("###").n_1700_B("has_cobblestone", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_4830_p)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.N_4006_T).n_1700_B(Character.valueOf('#'), a_3742_W.H_1873_g).n_1700_B(Character.valueOf('X'), Items.FlowerBlock).n_1700_B(Character.valueOf('I'), a_3742_W.J_1907_R).n_1700_B(" # ").n_1700_B("#X#").n_1700_B("III").n_1700_B("has_quartz", x_607_J.n_1700_B(Items.FlowerBlock)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.X_1303_p).n_1700_B(Character.valueOf('#'), Items.D_1621_L).n_1700_B(Character.valueOf('X'), Items.v_570_f).n_1700_B(" # ").n_1700_B("#X#").n_1700_B(" # ").n_1700_B("has_redstone", x_607_J.n_1700_B(Items.v_570_f)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.B_1335_M, 8).n_1700_B(Character.valueOf('#'), Items.V_3441_j).n_1700_B(Character.valueOf('X'), Items.M_712_N).n_1700_B("#X#").n_1700_B("has_cocoa", x_607_J.n_1700_B(Items.M_712_N)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.O_2934_T).n_1700_B(Character.valueOf('#'), ItemTags.R_4764_Y).n_1700_B("##").n_1700_B("##").n_1700_B("has_planks", x_607_J.n_1700_B(ItemTags.R_4764_Y)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.V_2454_J).n_1700_B(Character.valueOf('~'), Items.Animation).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('&'), Items.D_1621_L).n_1700_B(Character.valueOf('$'), a_3742_W.d_2169_p).n_1700_B("#&#").n_1700_B("~$~").n_1700_B(" # ").n_1700_B("has_string", x_607_J.n_1700_B(Items.Animation)).n_1700_B("has_stick", x_607_J.n_1700_B(Items.A_4514_U)).n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B("has_tripwire_hook", x_607_J.n_1700_B((q_1803_e)a_3742_W.d_2169_p)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.m_1628_s).n_1700_B(Character.valueOf('#'), ItemTags.R_4764_Y).n_1700_B(Character.valueOf('@'), Items.Animation).n_1700_B("@@").n_1700_B("##").n_1700_B("has_string", x_607_J.n_1700_B(Items.Animation)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.BoatNoClip).n_1700_B(Character.valueOf('#'), a_3742_W.Sprint).n_1700_B("#").n_1700_B("#").n_1700_B("has_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.BlockFly)).n_1700_B("has_chiseled_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.BoatNoClip)).n_1700_B("has_cut_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.ElytraResolver)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.t_4219_U).n_1700_B(Character.valueOf('#'), a_3742_W.NoFall).n_1700_B("#").n_1700_B("#").n_1700_B("has_stone_slab", x_607_J.n_1700_B((q_1803_e)a_3742_W.NoFall)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.n_4560_z, 2).J_1907_R(Items.I_4421_I).J_1907_R(Items.Z_2021_u).n_1700_B("has_green_dye", x_607_J.n_1700_B(Items.Z_2021_u)).n_1700_B("has_blue_dye", x_607_J.n_1700_B(Items.I_4421_I)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.G_3540_E).n_1700_B(Character.valueOf('S'), Items.v_4620_e).n_1700_B(Character.valueOf('I'), Items.K_4866_h).n_1700_B("SSS").n_1700_B("SIS").n_1700_B("SSS").n_1700_B("has_prismarine_shard", x_607_J.n_1700_B(Items.v_4620_e)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.e_4654_Y, 4).n_1700_B(Character.valueOf('#'), a_3742_W.z_2311_U).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_prismarine", x_607_J.n_1700_B((q_1803_e)a_3742_W.z_2311_U)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.b_967_P, 4).n_1700_B(Character.valueOf('#'), a_3742_W.Q_1082_O).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_prismarine_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.Q_1082_O)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.P_459_I, 4).n_1700_B(Character.valueOf('#'), a_3742_W.G_3540_E).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_dark_prismarine", x_607_J.n_1700_B((q_1803_e)a_3742_W.G_3540_E)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.k_1608_N).n_1700_B(Character.valueOf('Q'), Items.FlowerBlock).n_1700_B(Character.valueOf('G'), a_3742_W.e_1992_r).n_1700_B(Character.valueOf('W'), b_3278_X.n_1700_B(ItemTags.s_956_w)).n_1700_B("GGG").n_1700_B("QQQ").n_1700_B("WWW").n_1700_B("has_quartz", x_607_J.n_1700_B(Items.FlowerBlock)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.F_2624_D, 6).n_1700_B(Character.valueOf('R'), Items.v_570_f).n_1700_B(Character.valueOf('#'), a_3742_W.i_601_W).n_1700_B(Character.valueOf('X'), Items.D_1621_L).n_1700_B("X X").n_1700_B("X#X").n_1700_B("XRX").n_1700_B("has_rail", x_607_J.n_1700_B((q_1803_e)a_3742_W.Y_776_s)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.k_2273_q, 9).J_1907_R(a_3742_W.O_1309_Q).n_1700_B("has_diamond_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.O_1309_Q)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.M_2029_A).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.k_2273_q).n_1700_B("XX").n_1700_B("X#").n_1700_B(" #").n_1700_B("has_diamond", x_607_J.n_1700_B(Items.k_2273_q)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.O_1309_Q).n_1700_B(Character.valueOf('#'), Items.k_2273_q).n_1700_B("###").n_1700_B("###").n_1700_B("###").n_1700_B("has_diamond", x_607_J.n_1700_B(Items.k_2273_q)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.V_4557_X).n_1700_B(Character.valueOf('X'), Items.k_2273_q).n_1700_B("X X").n_1700_B("X X").n_1700_B("has_diamond", x_607_J.n_1700_B(Items.k_2273_q)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.f_508_U).n_1700_B(Character.valueOf('X'), Items.k_2273_q).n_1700_B("X X").n_1700_B("XXX").n_1700_B("XXX").n_1700_B("has_diamond", x_607_J.n_1700_B(Items.k_2273_q)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.q_4361_M).n_1700_B(Character.valueOf('X'), Items.k_2273_q).n_1700_B("XXX").n_1700_B("X X").n_1700_B("has_diamond", x_607_J.n_1700_B(Items.k_2273_q)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.q_3148_R).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.k_2273_q).n_1700_B("XX").n_1700_B(" #").n_1700_B(" #").n_1700_B("has_diamond", x_607_J.n_1700_B(Items.k_2273_q)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.A_1603_w).n_1700_B(Character.valueOf('X'), Items.k_2273_q).n_1700_B("XXX").n_1700_B("X X").n_1700_B("X X").n_1700_B("has_diamond", x_607_J.n_1700_B(Items.k_2273_q)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.C_1577_A).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.k_2273_q).n_1700_B("XXX").n_1700_B(" # ").n_1700_B(" # ").n_1700_B("has_diamond", x_607_J.n_1700_B(Items.k_2273_q)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.s_1124_y).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.k_2273_q).n_1700_B("X").n_1700_B("#").n_1700_B("#").n_1700_B("has_diamond", x_607_J.n_1700_B(Items.k_2273_q)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.N_2592_G).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.k_2273_q).n_1700_B("X").n_1700_B("X").n_1700_B("#").n_1700_B("has_diamond", x_607_J.n_1700_B(Items.k_2273_q)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.P_1922_E, 2).n_1700_B(Character.valueOf('Q'), Items.FlowerBlock).n_1700_B(Character.valueOf('C'), a_3742_W.P_4830_p).n_1700_B("CQ").n_1700_B("QC").n_1700_B("has_quartz", x_607_J.n_1700_B(Items.FlowerBlock)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.Ops).n_1700_B(Character.valueOf('R'), Items.v_570_f).n_1700_B(Character.valueOf('#'), a_3742_W.P_4830_p).n_1700_B(Character.valueOf('X'), Items.R_1796_s).n_1700_B("###").n_1700_B("#X#").n_1700_B("#R#").n_1700_B("has_bow", x_607_J.n_1700_B(Items.R_1796_s)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.c_1732_c).n_1700_B(Character.valueOf('R'), Items.v_570_f).n_1700_B(Character.valueOf('#'), a_3742_W.P_4830_p).n_1700_B("###").n_1700_B("# #").n_1700_B("#R#").n_1700_B("has_redstone", x_607_J.n_1700_B(Items.v_570_f)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.Y_2905_A, 9).J_1907_R(a_3742_W.B_2580_P).n_1700_B("has_emerald_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.B_2580_P)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.B_2580_P).n_1700_B(Character.valueOf('#'), Items.Y_2905_A).n_1700_B("###").n_1700_B("###").n_1700_B("###").n_1700_B("has_emerald", x_607_J.n_1700_B(Items.Y_2905_A)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.E_453_w).n_1700_B(Character.valueOf('B'), Items.K_4237_u).n_1700_B(Character.valueOf('#'), a_3742_W.ClientBootstrap).n_1700_B(Character.valueOf('D'), Items.k_2273_q).n_1700_B(" B ").n_1700_B("D#D").n_1700_B("###").n_1700_B("has_obsidian", x_607_J.n_1700_B((q_1803_e)a_3742_W.ClientBootstrap)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.k_2348_i).n_1700_B(Character.valueOf('#'), a_3742_W.ClientBootstrap).n_1700_B(Character.valueOf('E'), Items.V_1824_v).n_1700_B("###").n_1700_B("#E#").n_1700_B("###").n_1700_B("has_ender_eye", x_607_J.n_1700_B(Items.V_1824_v)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.V_1824_v).J_1907_R(Items.v_2746_S).J_1907_R(Items.C_3528_u).n_1700_B("has_blaze_powder", x_607_J.n_1700_B(Items.C_3528_u)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.FastPlace, 4).n_1700_B(Character.valueOf('#'), a_3742_W.e_1231_S).n_1700_B("##").n_1700_B("##").n_1700_B("has_end_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.e_1231_S)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.LoomBlock).n_1700_B(Character.valueOf('T'), Items.b_3334_n).n_1700_B(Character.valueOf('E'), Items.V_1824_v).n_1700_B(Character.valueOf('G'), a_3742_W.e_1992_r).n_1700_B("GGG").n_1700_B("GEG").n_1700_B("GTG").n_1700_B("has_ender_eye", x_607_J.n_1700_B(Items.V_1824_v)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.BaritoneSettings, 4).n_1700_B(Character.valueOf('#'), Items.MelonBlock).n_1700_B(Character.valueOf('/'), Items.A_2487_t).n_1700_B("/").n_1700_B("#").n_1700_B("has_chorus_fruit_popped", x_607_J.n_1700_B(Items.MelonBlock)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.Y_3066_B).J_1907_R(Items.r_2687_x).J_1907_R(a_3742_W.JsonUtils).J_1907_R(Items.o_3456_E).n_1700_B("has_spider_eye", x_607_J.n_1700_B(Items.r_2687_x)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.CraftingTableBlock, 3).J_1907_R(Items.Easing).J_1907_R(Items.C_3528_u).n_1700_B(b_3278_X.n_1700_B(Items.T_797_O, Items.d_560_A)).n_1700_B("has_blaze_powder", x_607_J.n_1700_B(Items.C_3528_u)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.w_2223_C).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.Animation).n_1700_B("  #").n_1700_B(" #X").n_1700_B("# X").n_1700_B("has_string", x_607_J.n_1700_B(Items.Animation)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.S_1165_y).J_1907_R(Items.D_1621_L).J_1907_R(Items.W_1488_x).n_1700_B("has_flint", x_607_J.n_1700_B(Items.W_1488_x)).n_1700_B("has_obsidian", x_607_J.n_1700_B((q_1803_e)a_3742_W.ClientBootstrap)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.r_4790_y).n_1700_B(Character.valueOf('#'), Items.F_489_x).n_1700_B("# #").n_1700_B(" # ").n_1700_B("has_brick", x_607_J.n_1700_B(Items.F_489_x)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.P_925_e).n_1700_B(Character.valueOf('#'), ItemTags.s_2632_s).n_1700_B("###").n_1700_B("# #").n_1700_B("###").n_1700_B("has_cobblestone", x_607_J.n_1700_B(ItemTags.s_2632_s)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.y_4842_Z).n_1700_B(Character.valueOf('A'), a_3742_W.P_925_e).n_1700_B(Character.valueOf('B'), Items.u_925_K).n_1700_B("A").n_1700_B("B").n_1700_B("has_minecart", x_607_J.n_1700_B(Items.u_925_K)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.Y_3588_g, 3).n_1700_B(Character.valueOf('#'), a_3742_W.e_1992_r).n_1700_B("# #").n_1700_B(" # ").n_1700_B("has_glass", x_607_J.n_1700_B((q_1803_e)a_3742_W.e_1992_r)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.q_839_y, 16).n_1700_B(Character.valueOf('#'), a_3742_W.e_1992_r).n_1700_B("###").n_1700_B("###").n_1700_B("has_glass", x_607_J.n_1700_B((q_1803_e)a_3742_W.e_1992_r)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.X_2960_b).n_1700_B(Character.valueOf('#'), Items.AdvancementList).n_1700_B("##").n_1700_B("##").n_1700_B("has_glowstone_dust", x_607_J.n_1700_B(Items.AdvancementList)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.p_863_D).n_1700_B(Character.valueOf('#'), Items.ServerHandshakePacketListener).n_1700_B(Character.valueOf('X'), Items.E_738_L).n_1700_B("###").n_1700_B("#X#").n_1700_B("###").n_1700_B("has_gold_ingot", x_607_J.n_1700_B(Items.ServerHandshakePacketListener)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.u_4724_w).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.ServerHandshakePacketListener).n_1700_B("XX").n_1700_B("X#").n_1700_B(" #").n_1700_B("has_gold_ingot", x_607_J.n_1700_B(Items.ServerHandshakePacketListener)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.m_4644_u).n_1700_B(Character.valueOf('X'), Items.ServerHandshakePacketListener).n_1700_B("X X").n_1700_B("X X").n_1700_B("has_gold_ingot", x_607_J.n_1700_B(Items.ServerHandshakePacketListener)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.DoublePlantBlock).n_1700_B(Character.valueOf('#'), Items.u_3578_p).n_1700_B(Character.valueOf('X'), Items.BaseCoralWallFanBlock).n_1700_B("###").n_1700_B("#X#").n_1700_B("###").n_1700_B("has_gold_nugget", x_607_J.n_1700_B(Items.u_3578_p)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.m_38_G).n_1700_B(Character.valueOf('X'), Items.ServerHandshakePacketListener).n_1700_B("X X").n_1700_B("XXX").n_1700_B("XXX").n_1700_B("has_gold_ingot", x_607_J.n_1700_B(Items.ServerHandshakePacketListener)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.h_3066_J).n_1700_B(Character.valueOf('X'), Items.ServerHandshakePacketListener).n_1700_B("XXX").n_1700_B("X X").n_1700_B("has_gold_ingot", x_607_J.n_1700_B(Items.ServerHandshakePacketListener)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.H_1952_g).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.ServerHandshakePacketListener).n_1700_B("XX").n_1700_B(" #").n_1700_B(" #").n_1700_B("has_gold_ingot", x_607_J.n_1700_B(Items.ServerHandshakePacketListener)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.k_4946_A).n_1700_B(Character.valueOf('X'), Items.ServerHandshakePacketListener).n_1700_B("XXX").n_1700_B("X X").n_1700_B("X X").n_1700_B("has_gold_ingot", x_607_J.n_1700_B(Items.ServerHandshakePacketListener)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.i_1894_C).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.ServerHandshakePacketListener).n_1700_B("XXX").n_1700_B(" # ").n_1700_B(" # ").n_1700_B("has_gold_ingot", x_607_J.n_1700_B(Items.ServerHandshakePacketListener)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.l_4537_E, 6).n_1700_B(Character.valueOf('R'), Items.v_570_f).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.ServerHandshakePacketListener).n_1700_B("X X").n_1700_B("X#X").n_1700_B("XRX").n_1700_B("has_rail", x_607_J.n_1700_B((q_1803_e)a_3742_W.Y_776_s)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.W_2756_H).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.ServerHandshakePacketListener).n_1700_B("X").n_1700_B("#").n_1700_B("#").n_1700_B("has_gold_ingot", x_607_J.n_1700_B(Items.ServerHandshakePacketListener)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.n_2412_y).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.ServerHandshakePacketListener).n_1700_B("X").n_1700_B("X").n_1700_B("#").n_1700_B("has_gold_ingot", x_607_J.n_1700_B(Items.ServerHandshakePacketListener)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.y_2772_m).n_1700_B(Character.valueOf('#'), Items.ServerHandshakePacketListener).n_1700_B("###").n_1700_B("###").n_1700_B("###").n_1700_B("has_gold_ingot", x_607_J.n_1700_B(Items.ServerHandshakePacketListener)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.ServerHandshakePacketListener, 9).J_1907_R(a_3742_W.y_2772_m).n_1700_B("gold_ingot").n_1700_B("has_gold_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.y_2772_m)).n_1700_B(consumer, "gold_ingot_from_gold_block");
        d_2427_y.n_1700_B(Items.ServerHandshakePacketListener).n_1700_B(Character.valueOf('#'), Items.u_3578_p).n_1700_B("###").n_1700_B("###").n_1700_B("###").J_1907_R("gold_ingot").n_1700_B("has_gold_nugget", x_607_J.n_1700_B(Items.u_3578_p)).n_1700_B(consumer, "gold_ingot_from_nuggets");
        z_1737_N.n_1700_B(Items.u_3578_p, 9).J_1907_R(Items.ServerHandshakePacketListener).n_1700_B("has_gold_ingot", x_607_J.n_1700_B(Items.ServerHandshakePacketListener)).n_1700_B(consumer);
        z_1737_N.n_1700_B(a_3742_W.R_4764_Y).J_1907_R(a_3742_W.P_1922_E).J_1907_R(Items.FlowerBlock).n_1700_B("has_quartz", x_607_J.n_1700_B(Items.FlowerBlock)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.D_4237_z, 2).J_1907_R(Items.K_4866_h).J_1907_R(Items.ServerFunctionManager).n_1700_B("has_white_dye", x_607_J.n_1700_B(Items.ServerFunctionManager)).n_1700_B("has_black_dye", x_607_J.n_1700_B(Items.K_4866_h)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.M_4609_z).n_1700_B(Character.valueOf('#'), Items.V_3441_j).n_1700_B("###").n_1700_B("###").n_1700_B("###").n_1700_B("has_wheat", x_607_J.n_1700_B(Items.V_3441_j)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.b_2037_V).n_1700_B(Character.valueOf('#'), Items.D_1621_L).n_1700_B("##").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.StructureBlock, 4).J_1907_R(Items.StructureVoidBlock).J_1907_R(Items.Y_3588_g, 4).n_1700_B("has_honey_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.B_1335_M)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.B_1335_M, 1).n_1700_B(Character.valueOf('S'), Items.StructureBlock).n_1700_B("SS").n_1700_B("SS").n_1700_B("has_honey_bottle", x_607_J.n_1700_B(Items.StructureBlock)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.K_4518_s).n_1700_B(Character.valueOf('H'), Items.StemGrownBlock).n_1700_B("HH").n_1700_B("HH").n_1700_B("has_honeycomb", x_607_J.n_1700_B(Items.StemGrownBlock)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.p_3749_n).n_1700_B(Character.valueOf('C'), a_3742_W.L_1362_X).n_1700_B(Character.valueOf('I'), Items.D_1621_L).n_1700_B("I I").n_1700_B("ICI").n_1700_B(" I ").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.s_3834_w).n_1700_B(Character.valueOf('A'), a_3742_W.p_3749_n).n_1700_B(Character.valueOf('B'), Items.u_925_K).n_1700_B("A").n_1700_B("B").n_1700_B("has_minecart", x_607_J.n_1700_B(Items.u_925_K)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.E_390_U).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.D_1621_L).n_1700_B("XX").n_1700_B("X#").n_1700_B(" #").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.Z_4720_K, 16).n_1700_B(Character.valueOf('#'), Items.D_1621_L).n_1700_B("###").n_1700_B("###").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.H_1883_T).n_1700_B(Character.valueOf('#'), Items.D_1621_L).n_1700_B("###").n_1700_B("###").n_1700_B("###").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.j_2302_z).n_1700_B(Character.valueOf('X'), Items.D_1621_L).n_1700_B("X X").n_1700_B("X X").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.k_2282_P).n_1700_B(Character.valueOf('X'), Items.D_1621_L).n_1700_B("X X").n_1700_B("XXX").n_1700_B("XXX").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.h_2739_B, 3).n_1700_B(Character.valueOf('#'), Items.D_1621_L).n_1700_B("##").n_1700_B("##").n_1700_B("##").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.T_1170_t).n_1700_B(Character.valueOf('X'), Items.D_1621_L).n_1700_B("XXX").n_1700_B("X X").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.Z_256_c).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.D_1621_L).n_1700_B("XX").n_1700_B(" #").n_1700_B(" #").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.D_1621_L, 9).J_1907_R(a_3742_W.H_1883_T).n_1700_B("iron_ingot").n_1700_B("has_iron_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.H_1883_T)).n_1700_B(consumer, "iron_ingot_from_iron_block");
        d_2427_y.n_1700_B(Items.D_1621_L).n_1700_B(Character.valueOf('#'), Items.f_1186_l).n_1700_B("###").n_1700_B("###").n_1700_B("###").J_1907_R("iron_ingot").n_1700_B("has_iron_nugget", x_607_J.n_1700_B(Items.f_1186_l)).n_1700_B(consumer, "iron_ingot_from_nuggets");
        d_2427_y.n_1700_B(Items.U_2474_c).n_1700_B(Character.valueOf('X'), Items.D_1621_L).n_1700_B("XXX").n_1700_B("X X").n_1700_B("X X").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.f_1186_l, 9).J_1907_R(Items.D_1621_L).n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.r_976_u).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.D_1621_L).n_1700_B("XXX").n_1700_B(" # ").n_1700_B(" # ").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.Z_1243_X).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.D_1621_L).n_1700_B("X").n_1700_B("#").n_1700_B("#").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.w_2152_d).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.D_1621_L).n_1700_B("X").n_1700_B("X").n_1700_B("#").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.q_2475_j).n_1700_B(Character.valueOf('#'), Items.D_1621_L).n_1700_B("##").n_1700_B("##").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.DeadBushBlock).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.y_254_d).n_1700_B("###").n_1700_B("#X#").n_1700_B("###").n_1700_B("has_leather", x_607_J.n_1700_B(Items.y_254_d)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.r_2478_U).n_1700_B(Character.valueOf('#'), ItemTags.R_4764_Y).n_1700_B(Character.valueOf('X'), Items.k_2273_q).n_1700_B("###").n_1700_B("#X#").n_1700_B("###").n_1700_B("has_diamond", x_607_J.n_1700_B(Items.k_2273_q)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.L_3570_A, 3).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B("# #").n_1700_B("###").n_1700_B("# #").n_1700_B("has_stick", x_607_J.n_1700_B(Items.A_4514_U)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.k_3961_g).n_1700_B(Character.valueOf('#'), Items.W_4813_f).n_1700_B("###").n_1700_B("###").n_1700_B("###").n_1700_B("has_lapis", x_607_J.n_1700_B(Items.W_4813_f)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.W_4813_f, 9).J_1907_R(a_3742_W.k_3961_g).n_1700_B("has_lapis_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.k_3961_g)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.c_1788_D, 2).n_1700_B(Character.valueOf('~'), Items.Animation).n_1700_B(Character.valueOf('O'), Items.Z_3822_q).n_1700_B("~~ ").n_1700_B("~O ").n_1700_B("  ~").n_1700_B("has_slime_ball", x_607_J.n_1700_B(Items.Z_3822_q)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.y_254_d).n_1700_B(Character.valueOf('#'), Items.GrassBlock).n_1700_B("##").n_1700_B("##").n_1700_B("has_rabbit_hide", x_607_J.n_1700_B(Items.GrassBlock)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.a_1344_X).n_1700_B(Character.valueOf('X'), Items.y_254_d).n_1700_B("X X").n_1700_B("X X").n_1700_B("has_leather", x_607_J.n_1700_B(Items.y_254_d)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.r_2090_h).n_1700_B(Character.valueOf('X'), Items.y_254_d).n_1700_B("X X").n_1700_B("XXX").n_1700_B("XXX").n_1700_B("has_leather", x_607_J.n_1700_B(Items.y_254_d)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.t_1509_b).n_1700_B(Character.valueOf('X'), Items.y_254_d).n_1700_B("XXX").n_1700_B("X X").n_1700_B("has_leather", x_607_J.n_1700_B(Items.y_254_d)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.z_2759_Q).n_1700_B(Character.valueOf('X'), Items.y_254_d).n_1700_B("XXX").n_1700_B("X X").n_1700_B("X X").n_1700_B("has_leather", x_607_J.n_1700_B(Items.y_254_d)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.HoneyBlock).n_1700_B(Character.valueOf('X'), Items.y_254_d).n_1700_B("X X").n_1700_B("XXX").n_1700_B("X X").n_1700_B("has_leather", x_607_J.n_1700_B(Items.y_254_d)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.F_489_x).n_1700_B(Character.valueOf('S'), ItemTags.s_956_w).n_1700_B(Character.valueOf('B'), a_3742_W.UploadTokenCache).n_1700_B("SSS").n_1700_B(" B ").n_1700_B(" S ").n_1700_B("has_book", x_607_J.n_1700_B(Items.K_4237_u)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.x_92_N).n_1700_B(Character.valueOf('#'), a_3742_W.P_4830_p).n_1700_B(Character.valueOf('X'), Items.A_4514_U).n_1700_B("X").n_1700_B("#").n_1700_B("has_cobblestone", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_4830_p)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.RequirementsStrategy).J_1907_R(a_3742_W.C_3538_G).n_1700_B("light_blue_dye").n_1700_B("has_red_flower", x_607_J.n_1700_B((q_1803_e)a_3742_W.C_3538_G)).n_1700_B(consumer, "light_blue_dye_from_blue_orchid");
        z_1737_N.n_1700_B(Items.RequirementsStrategy, 2).J_1907_R(Items.I_4421_I).J_1907_R(Items.ServerFunctionManager).n_1700_B("light_blue_dye").n_1700_B("has_blue_dye", x_607_J.n_1700_B(Items.I_4421_I)).n_1700_B("has_white_dye", x_607_J.n_1700_B(Items.ServerFunctionManager)).n_1700_B(consumer, "light_blue_dye_from_blue_white_dye");
        z_1737_N.n_1700_B(Items.d_3769_f).J_1907_R(a_3742_W.G_424_k).n_1700_B("light_gray_dye").n_1700_B("has_red_flower", x_607_J.n_1700_B((q_1803_e)a_3742_W.G_424_k)).n_1700_B(consumer, "light_gray_dye_from_azure_bluet");
        z_1737_N.n_1700_B(Items.d_3769_f, 2).J_1907_R(Items.D_4237_z).J_1907_R(Items.ServerFunctionManager).n_1700_B("light_gray_dye").n_1700_B("has_gray_dye", x_607_J.n_1700_B(Items.D_4237_z)).n_1700_B("has_white_dye", x_607_J.n_1700_B(Items.ServerFunctionManager)).n_1700_B(consumer, "light_gray_dye_from_gray_white_dye");
        z_1737_N.n_1700_B(Items.d_3769_f, 3).J_1907_R(Items.K_4866_h).J_1907_R(Items.ServerFunctionManager, 2).n_1700_B("light_gray_dye").n_1700_B("has_white_dye", x_607_J.n_1700_B(Items.ServerFunctionManager)).n_1700_B("has_black_dye", x_607_J.n_1700_B(Items.K_4866_h)).n_1700_B(consumer, "light_gray_dye_from_black_white_dye");
        z_1737_N.n_1700_B(Items.d_3769_f).J_1907_R(a_3742_W.C_1162_e).n_1700_B("light_gray_dye").n_1700_B("has_red_flower", x_607_J.n_1700_B((q_1803_e)a_3742_W.C_1162_e)).n_1700_B(consumer, "light_gray_dye_from_oxeye_daisy");
        z_1737_N.n_1700_B(Items.d_3769_f).J_1907_R(a_3742_W.F_4247_a).n_1700_B("light_gray_dye").n_1700_B("has_red_flower", x_607_J.n_1700_B((q_1803_e)a_3742_W.F_4247_a)).n_1700_B(consumer, "light_gray_dye_from_white_tulip");
        d_2427_y.n_1700_B(a_3742_W.O_3016_i).n_1700_B(Character.valueOf('#'), Items.ServerHandshakePacketListener).n_1700_B("##").n_1700_B("has_gold_ingot", x_607_J.n_1700_B(Items.ServerHandshakePacketListener)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.SimpleCriterionTrigger, 2).J_1907_R(Items.Z_2021_u).J_1907_R(Items.ServerFunctionManager).n_1700_B("has_green_dye", x_607_J.n_1700_B(Items.Z_2021_u)).n_1700_B("has_white_dye", x_607_J.n_1700_B(Items.ServerFunctionManager)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.l_2647_k).n_1700_B(Character.valueOf('A'), a_3742_W.X_2048_Y).n_1700_B(Character.valueOf('B'), a_3742_W.o_2341_D).n_1700_B("A").n_1700_B("B").n_1700_B("has_carved_pumpkin", x_607_J.n_1700_B((q_1803_e)a_3742_W.X_2048_Y)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.CriterionTrigger).J_1907_R(a_3742_W.A_3959_N).n_1700_B("magenta_dye").n_1700_B("has_red_flower", x_607_J.n_1700_B((q_1803_e)a_3742_W.A_3959_N)).n_1700_B(consumer, "magenta_dye_from_allium");
        z_1737_N.n_1700_B(Items.CriterionTrigger, 4).J_1907_R(Items.I_4421_I).J_1907_R(Items.P_1965_C, 2).J_1907_R(Items.ServerFunctionManager).n_1700_B("magenta_dye").n_1700_B("has_blue_dye", x_607_J.n_1700_B(Items.I_4421_I)).n_1700_B("has_rose_red", x_607_J.n_1700_B(Items.P_1965_C)).n_1700_B("has_white_dye", x_607_J.n_1700_B(Items.ServerFunctionManager)).n_1700_B(consumer, "magenta_dye_from_blue_red_white_dye");
        z_1737_N.n_1700_B(Items.CriterionTrigger, 3).J_1907_R(Items.I_4421_I).J_1907_R(Items.P_1965_C).J_1907_R(Items.T_2391_T).n_1700_B("magenta_dye").n_1700_B("has_pink_dye", x_607_J.n_1700_B(Items.T_2391_T)).n_1700_B("has_blue_dye", x_607_J.n_1700_B(Items.I_4421_I)).n_1700_B("has_red_dye", x_607_J.n_1700_B(Items.P_1965_C)).n_1700_B(consumer, "magenta_dye_from_blue_red_pink");
        z_1737_N.n_1700_B(Items.CriterionTrigger, 2).J_1907_R(a_3742_W.X_812_G).n_1700_B("magenta_dye").n_1700_B("has_double_plant", x_607_J.n_1700_B((q_1803_e)a_3742_W.X_812_G)).n_1700_B(consumer, "magenta_dye_from_lilac");
        z_1737_N.n_1700_B(Items.CriterionTrigger, 2).J_1907_R(Items.z_936_s).J_1907_R(Items.T_2391_T).n_1700_B("magenta_dye").n_1700_B("has_pink_dye", x_607_J.n_1700_B(Items.T_2391_T)).n_1700_B("has_purple_dye", x_607_J.n_1700_B(Items.z_936_s)).n_1700_B(consumer, "magenta_dye_from_purple_and_pink");
        d_2427_y.n_1700_B(a_3742_W.LevitationControl).n_1700_B(Character.valueOf('#'), Items.S_3844_E).n_1700_B("##").n_1700_B("##").n_1700_B("has_magma_cream", x_607_J.n_1700_B(Items.S_3844_E)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.S_3844_E).J_1907_R(Items.C_3528_u).J_1907_R(Items.Z_3822_q).n_1700_B("has_blaze_powder", x_607_J.n_1700_B(Items.C_3528_u)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.S_1431_H).n_1700_B(Character.valueOf('#'), Items.l_3370_o).n_1700_B(Character.valueOf('X'), Items.X_1303_p).n_1700_B("###").n_1700_B("#X#").n_1700_B("###").n_1700_B("has_compass", x_607_J.n_1700_B(Items.X_1303_p)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.E_3343_g).n_1700_B(Character.valueOf('M'), Items.B_368_w).n_1700_B("MMM").n_1700_B("MMM").n_1700_B("MMM").n_1700_B("has_melon", x_607_J.n_1700_B(Items.B_368_w)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.y_2836_h).J_1907_R(Items.B_368_w).n_1700_B("has_melon", x_607_J.n_1700_B(Items.B_368_w)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.u_925_K).n_1700_B(Character.valueOf('#'), Items.D_1621_L).n_1700_B("# #").n_1700_B("###").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        z_1737_N.n_1700_B(a_3742_W.U_1341_G).J_1907_R(a_3742_W.P_4830_p).J_1907_R(a_3742_W.U_4087_m).n_1700_B("has_vine", x_607_J.n_1700_B((q_1803_e)a_3742_W.U_4087_m)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.F_1446_q, 6).n_1700_B(Character.valueOf('#'), a_3742_W.U_1341_G).n_1700_B("###").n_1700_B("###").n_1700_B("has_mossy_cobblestone", x_607_J.n_1700_B((q_1803_e)a_3742_W.U_1341_G)).n_1700_B(consumer);
        z_1737_N.n_1700_B(a_3742_W.I_4481_g).J_1907_R(a_3742_W.f_691_R).J_1907_R(a_3742_W.U_4087_m).n_1700_B("has_mossy_cobblestone", x_607_J.n_1700_B((q_1803_e)a_3742_W.U_1341_G)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.MinecraftAccess).J_1907_R(a_3742_W.JsonUtils).J_1907_R(a_3742_W.RealmsPersistence).J_1907_R(Items.S_4088_D).n_1700_B("has_mushroom_stew", x_607_J.n_1700_B(Items.MinecraftAccess)).n_1700_B("has_bowl", x_607_J.n_1700_B(Items.S_4088_D)).n_1700_B("has_brown_mushroom", x_607_J.n_1700_B((q_1803_e)a_3742_W.JsonUtils)).n_1700_B("has_red_mushroom", x_607_J.n_1700_B((q_1803_e)a_3742_W.RealmsPersistence)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.h_1015_G).n_1700_B(Character.valueOf('N'), Items.FletchingTableBlock).n_1700_B("NN").n_1700_B("NN").n_1700_B("has_netherbrick", x_607_J.n_1700_B(Items.FletchingTableBlock)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.d_4500_Q, 6).n_1700_B(Character.valueOf('#'), a_3742_W.h_1015_G).n_1700_B(Character.valueOf('-'), Items.FletchingTableBlock).n_1700_B("#-#").n_1700_B("#-#").n_1700_B("has_nether_brick", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_1015_G)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.Speed, 6).n_1700_B(Character.valueOf('#'), a_3742_W.h_1015_G).n_1700_B("###").n_1700_B("has_nether_brick", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_1015_G)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.O_2761_o, 4).n_1700_B(Character.valueOf('#'), a_3742_W.h_1015_G).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_nether_brick", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_1015_G)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.LockSlot).n_1700_B(Character.valueOf('#'), Items.g_1096_r).n_1700_B("###").n_1700_B("###").n_1700_B("###").n_1700_B("has_nether_wart", x_607_J.n_1700_B(Items.g_1096_r)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.PlayerInfo).n_1700_B(Character.valueOf('#'), ItemTags.R_4764_Y).n_1700_B(Character.valueOf('X'), Items.v_570_f).n_1700_B("###").n_1700_B("#X#").n_1700_B("###").n_1700_B("has_redstone", x_607_J.n_1700_B(Items.v_570_f)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.RegionExploit).n_1700_B(Character.valueOf('Q'), Items.FlowerBlock).n_1700_B(Character.valueOf('R'), Items.v_570_f).n_1700_B(Character.valueOf('#'), a_3742_W.P_4830_p).n_1700_B("###").n_1700_B("RRQ").n_1700_B("###").n_1700_B("has_quartz", x_607_J.n_1700_B(Items.FlowerBlock)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.h_1723_G).J_1907_R(a_3742_W.f_1043_S).n_1700_B("orange_dye").n_1700_B("has_red_flower", x_607_J.n_1700_B((q_1803_e)a_3742_W.f_1043_S)).n_1700_B(consumer, "orange_dye_from_orange_tulip");
        z_1737_N.n_1700_B(Items.h_1723_G, 2).J_1907_R(Items.P_1965_C).J_1907_R(Items.S_4998_h).n_1700_B("orange_dye").n_1700_B("has_red_dye", x_607_J.n_1700_B(Items.P_1965_C)).n_1700_B("has_yellow_dye", x_607_J.n_1700_B(Items.S_4998_h)).n_1700_B(consumer, "orange_dye_from_red_yellow");
        d_2427_y.n_1700_B(Items.q_3115_L).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), b_3278_X.n_1700_B(ItemTags.J_1907_R)).n_1700_B("###").n_1700_B("#X#").n_1700_B("###").n_1700_B("has_wool", x_607_J.n_1700_B(ItemTags.J_1907_R)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.l_3370_o, 3).n_1700_B(Character.valueOf('#'), a_3742_W.l_3609_d).n_1700_B("###").n_1700_B("has_reeds", x_607_J.n_1700_B((q_1803_e)a_3742_W.l_3609_d)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.f_887_Z, 2).n_1700_B(Character.valueOf('#'), a_3742_W.P_3676_m).n_1700_B("#").n_1700_B("#").n_1700_B("has_chiseled_quartz_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.Q_4222_k)).n_1700_B("has_quartz_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_3676_m)).n_1700_B("has_quartz_pillar", x_607_J.n_1700_B((q_1803_e)a_3742_W.f_887_Z)).n_1700_B(consumer);
        z_1737_N.n_1700_B(a_3742_W.ServerHelper).J_1907_R(a_3742_W.O_1795_e, 9).n_1700_B("has_ice", x_607_J.n_1700_B((q_1803_e)a_3742_W.O_1795_e)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.T_2391_T, 2).J_1907_R(a_3742_W.OpenWalls).n_1700_B("pink_dye").n_1700_B("has_double_plant", x_607_J.n_1700_B((q_1803_e)a_3742_W.OpenWalls)).n_1700_B(consumer, "pink_dye_from_peony");
        z_1737_N.n_1700_B(Items.T_2391_T).J_1907_R(a_3742_W.J_739_q).n_1700_B("pink_dye").n_1700_B("has_red_flower", x_607_J.n_1700_B((q_1803_e)a_3742_W.J_739_q)).n_1700_B(consumer, "pink_dye_from_pink_tulip");
        z_1737_N.n_1700_B(Items.T_2391_T, 2).J_1907_R(Items.P_1965_C).J_1907_R(Items.ServerFunctionManager).n_1700_B("pink_dye").n_1700_B("has_white_dye", x_607_J.n_1700_B(Items.ServerFunctionManager)).n_1700_B("has_red_dye", x_607_J.n_1700_B(Items.P_1965_C)).n_1700_B(consumer, "pink_dye_from_red_white_dye");
        d_2427_y.n_1700_B(a_3742_W.j_2266_I).n_1700_B(Character.valueOf('R'), Items.v_570_f).n_1700_B(Character.valueOf('#'), a_3742_W.P_4830_p).n_1700_B(Character.valueOf('T'), ItemTags.R_4764_Y).n_1700_B(Character.valueOf('X'), Items.D_1621_L).n_1700_B("TTT").n_1700_B("#X#").n_1700_B("#R#").n_1700_B("has_redstone", x_607_J.n_1700_B(Items.v_570_f)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.b_2312_j, 4).n_1700_B(Character.valueOf('S'), a_3742_W.s_4990_V).n_1700_B("SS").n_1700_B("SS").n_1700_B("has_basalt", x_607_J.n_1700_B((q_1803_e)a_3742_W.s_4990_V)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.G_564_y, 4).n_1700_B(Character.valueOf('S'), a_3742_W.R_4764_Y).n_1700_B("SS").n_1700_B("SS").n_1700_B("has_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.R_4764_Y)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.u_1723_Y, 4).n_1700_B(Character.valueOf('S'), a_3742_W.P_1922_E).n_1700_B("SS").n_1700_B("SS").n_1700_B("has_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_1922_E)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.w_1484_f, 4).n_1700_B(Character.valueOf('S'), a_3742_W.v_4262_N).n_1700_B("SS").n_1700_B("SS").n_1700_B("has_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.v_4262_N)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.z_2311_U).n_1700_B(Character.valueOf('S'), Items.v_4620_e).n_1700_B("SS").n_1700_B("SS").n_1700_B("has_prismarine_shard", x_607_J.n_1700_B(Items.v_4620_e)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.Q_1082_O).n_1700_B(Character.valueOf('S'), Items.v_4620_e).n_1700_B("SSS").n_1700_B("SSS").n_1700_B("SSS").n_1700_B("has_prismarine_shard", x_607_J.n_1700_B(Items.v_4620_e)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.l_1757_S, 6).n_1700_B(Character.valueOf('#'), a_3742_W.z_2311_U).n_1700_B("###").n_1700_B("has_prismarine", x_607_J.n_1700_B((q_1803_e)a_3742_W.z_2311_U)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.t_4864_b, 6).n_1700_B(Character.valueOf('#'), a_3742_W.Q_1082_O).n_1700_B("###").n_1700_B("has_prismarine_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.Q_1082_O)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.u_1980_X, 6).n_1700_B(Character.valueOf('#'), a_3742_W.G_3540_E).n_1700_B("###").n_1700_B("has_dark_prismarine", x_607_J.n_1700_B((q_1803_e)a_3742_W.G_3540_E)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.y_2012_u).J_1907_R(a_3742_W.A_3244_K).J_1907_R(Items.o_3456_E).J_1907_R(Items.s_4405_m).n_1700_B("has_carved_pumpkin", x_607_J.n_1700_B((q_1803_e)a_3742_W.X_2048_Y)).n_1700_B("has_pumpkin", x_607_J.n_1700_B((q_1803_e)a_3742_W.A_3244_K)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.WrappedMinMaxBounds, 4).J_1907_R(a_3742_W.A_3244_K).n_1700_B("has_pumpkin", x_607_J.n_1700_B((q_1803_e)a_3742_W.A_3244_K)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.z_936_s, 2).J_1907_R(Items.I_4421_I).J_1907_R(Items.P_1965_C).n_1700_B("has_blue_dye", x_607_J.n_1700_B(Items.I_4421_I)).n_1700_B("has_red_dye", x_607_J.n_1700_B(Items.P_1965_C)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.k_1052_R).n_1700_B(Character.valueOf('#'), a_3742_W.L_1362_X).n_1700_B(Character.valueOf('-'), Items.NetherVines).n_1700_B("-").n_1700_B("#").n_1700_B("-").n_1700_B("has_shulker_shell", x_607_J.n_1700_B(Items.NetherVines)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.ClickPearl, 4).n_1700_B(Character.valueOf('F'), Items.MelonBlock).n_1700_B("FF").n_1700_B("FF").n_1700_B("has_chorus_fruit_popped", x_607_J.n_1700_B(Items.MelonBlock)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.CrystalOptimizer).n_1700_B(Character.valueOf('#'), a_3742_W.Strafe).n_1700_B("#").n_1700_B("#").n_1700_B("has_purpur_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.ClickPearl)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.Strafe, 6).n_1700_B(Character.valueOf('#'), b_3278_X.n_1700_B(a_3742_W.ClickPearl, a_3742_W.CrystalOptimizer)).n_1700_B("###").n_1700_B("has_purpur_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.ClickPearl)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.FastBreak, 4).n_1700_B(Character.valueOf('#'), b_3278_X.n_1700_B(a_3742_W.ClickPearl, a_3742_W.CrystalOptimizer)).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_purpur_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.ClickPearl)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.P_3676_m).n_1700_B(Character.valueOf('#'), Items.FlowerBlock).n_1700_B("##").n_1700_B("##").n_1700_B("has_quartz", x_607_J.n_1700_B(Items.FlowerBlock)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.AbstractBannerBlock, 4).n_1700_B(Character.valueOf('#'), a_3742_W.P_3676_m).n_1700_B("##").n_1700_B("##").n_1700_B("has_quartz_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_3676_m)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.Spider, 6).n_1700_B(Character.valueOf('#'), b_3278_X.n_1700_B(a_3742_W.Q_4222_k, a_3742_W.P_3676_m, a_3742_W.f_887_Z)).n_1700_B("###").n_1700_B("has_chiseled_quartz_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.Q_4222_k)).n_1700_B("has_quartz_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_3676_m)).n_1700_B("has_quartz_pillar", x_607_J.n_1700_B((q_1803_e)a_3742_W.f_887_Z)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.R_3213_X, 4).n_1700_B(Character.valueOf('#'), b_3278_X.n_1700_B(a_3742_W.Q_4222_k, a_3742_W.P_3676_m, a_3742_W.f_887_Z)).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_chiseled_quartz_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.Q_4222_k)).n_1700_B("has_quartz_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_3676_m)).n_1700_B("has_quartz_pillar", x_607_J.n_1700_B((q_1803_e)a_3742_W.f_887_Z)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.N_3347_G).J_1907_R(Items.DirectionalBlock).J_1907_R(Items.A_3138_X).J_1907_R(Items.S_4088_D).J_1907_R(Items.BaseCoralWallFanBlock).J_1907_R(a_3742_W.JsonUtils).n_1700_B("rabbit_stew").n_1700_B("has_cooked_rabbit", x_607_J.n_1700_B(Items.A_3138_X)).n_1700_B(consumer, "rabbit_stew_from_brown_mushroom");
        z_1737_N.n_1700_B(Items.N_3347_G).J_1907_R(Items.DirectionalBlock).J_1907_R(Items.A_3138_X).J_1907_R(Items.S_4088_D).J_1907_R(Items.BaseCoralWallFanBlock).J_1907_R(a_3742_W.RealmsPersistence).n_1700_B("rabbit_stew").n_1700_B("has_cooked_rabbit", x_607_J.n_1700_B(Items.A_3138_X)).n_1700_B(consumer, "rabbit_stew_from_red_mushroom");
        d_2427_y.n_1700_B(a_3742_W.Y_776_s, 16).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.D_1621_L).n_1700_B("X X").n_1700_B("X#X").n_1700_B("X X").n_1700_B("has_minecart", x_607_J.n_1700_B(Items.u_925_K)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.v_570_f, 9).J_1907_R(a_3742_W.s_3815_K).n_1700_B("has_redstone_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.s_3815_K)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.s_3815_K).n_1700_B(Character.valueOf('#'), Items.v_570_f).n_1700_B("###").n_1700_B("###").n_1700_B("###").n_1700_B("has_redstone", x_607_J.n_1700_B(Items.v_570_f)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.B_3040_x).n_1700_B(Character.valueOf('R'), Items.v_570_f).n_1700_B(Character.valueOf('G'), a_3742_W.X_2960_b).n_1700_B(" R ").n_1700_B("RGR").n_1700_B(" R ").n_1700_B("has_glowstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.X_2960_b)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.H_1873_g).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), Items.v_570_f).n_1700_B("X").n_1700_B("#").n_1700_B("has_redstone", x_607_J.n_1700_B(Items.v_570_f)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.P_1965_C).J_1907_R(Items.s_3401_U).n_1700_B("red_dye").n_1700_B("has_beetroot", x_607_J.n_1700_B(Items.s_3401_U)).n_1700_B(consumer, "red_dye_from_beetroot");
        z_1737_N.n_1700_B(Items.P_1965_C).J_1907_R(a_3742_W.RealmsResetNormalWorldScreen).n_1700_B("red_dye").n_1700_B("has_red_flower", x_607_J.n_1700_B((q_1803_e)a_3742_W.RealmsResetNormalWorldScreen)).n_1700_B(consumer, "red_dye_from_poppy");
        z_1737_N.n_1700_B(Items.P_1965_C, 2).J_1907_R(a_3742_W.NameProtect).n_1700_B("red_dye").n_1700_B("has_double_plant", x_607_J.n_1700_B((q_1803_e)a_3742_W.NameProtect)).n_1700_B(consumer, "red_dye_from_rose_bush");
        z_1737_N.n_1700_B(Items.P_1965_C).J_1907_R(a_3742_W.RealmsSettingsScreen).n_1700_B("red_dye").n_1700_B("has_red_flower", x_607_J.n_1700_B((q_1803_e)a_3742_W.RealmsSettingsScreen)).n_1700_B(consumer, "red_dye_from_tulip");
        d_2427_y.n_1700_B(a_3742_W.NoInteract).n_1700_B(Character.valueOf('W'), Items.g_1096_r).n_1700_B(Character.valueOf('N'), Items.FletchingTableBlock).n_1700_B("NW").n_1700_B("WN").n_1700_B("has_nether_wart", x_607_J.n_1700_B(Items.g_1096_r)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.BlockFly).n_1700_B(Character.valueOf('#'), a_3742_W.Y_1740_V).n_1700_B("##").n_1700_B("##").n_1700_B("has_sand", x_607_J.n_1700_B((q_1803_e)a_3742_W.Y_1740_V)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.Sprint, 6).n_1700_B(Character.valueOf('#'), b_3278_X.n_1700_B(a_3742_W.BlockFly, a_3742_W.BoatNoClip)).n_1700_B("###").n_1700_B("has_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.BlockFly)).n_1700_B("has_chiseled_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.BoatNoClip)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.Step, 6).n_1700_B(Character.valueOf('#'), a_3742_W.ElytraResolver).n_1700_B("###").n_1700_B("has_cut_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.ElytraResolver)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.ElytraJump, 4).n_1700_B(Character.valueOf('#'), b_3278_X.n_1700_B(a_3742_W.BlockFly, a_3742_W.BoatNoClip, a_3742_W.ElytraResolver)).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.BlockFly)).n_1700_B("has_chiseled_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.BoatNoClip)).n_1700_B("has_cut_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.ElytraResolver)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.T_437_o).n_1700_B(Character.valueOf('#'), a_3742_W.H_1873_g).n_1700_B(Character.valueOf('X'), Items.v_570_f).n_1700_B(Character.valueOf('I'), a_3742_W.J_1907_R).n_1700_B("#X#").n_1700_B("III").n_1700_B("has_redstone_torch", x_607_J.n_1700_B((q_1803_e)a_3742_W.H_1873_g)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.h_4320_q).n_1700_B(Character.valueOf('#'), a_3742_W.A_4115_X).n_1700_B("##").n_1700_B("##").n_1700_B("has_sand", x_607_J.n_1700_B((q_1803_e)a_3742_W.A_4115_X)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.NoFall, 6).n_1700_B(Character.valueOf('#'), b_3278_X.n_1700_B(a_3742_W.h_4320_q, a_3742_W.t_4219_U)).n_1700_B("###").n_1700_B("has_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_4320_q)).n_1700_B("has_chiseled_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.t_4219_U)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.NoJumpDelay, 6).n_1700_B(Character.valueOf('#'), a_3742_W.V_1446_Y).n_1700_B("###").n_1700_B("has_cut_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.V_1446_Y)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.E_4256_w, 4).n_1700_B(Character.valueOf('#'), b_3278_X.n_1700_B(a_3742_W.h_4320_q, a_3742_W.t_4219_U, a_3742_W.V_1446_Y)).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_4320_q)).n_1700_B("has_chiseled_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.t_4219_U)).n_1700_B("has_cut_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.V_1446_Y)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.T_33_Q).n_1700_B(Character.valueOf('S'), Items.v_4620_e).n_1700_B(Character.valueOf('C'), Items.FrostedIceBlock).n_1700_B("SCS").n_1700_B("CCC").n_1700_B("SCS").n_1700_B("has_prismarine_crystals", x_607_J.n_1700_B(Items.FrostedIceBlock)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.LightPredicate).n_1700_B(Character.valueOf('#'), Items.D_1621_L).n_1700_B(" #").n_1700_B("# ").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.NoteBlock).n_1700_B(Character.valueOf('W'), ItemTags.R_4764_Y).n_1700_B(Character.valueOf('o'), Items.D_1621_L).n_1700_B("WoW").n_1700_B("WWW").n_1700_B(" W ").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.g_4841_c).n_1700_B(Character.valueOf('#'), Items.Z_3822_q).n_1700_B("###").n_1700_B("###").n_1700_B("###").n_1700_B("has_slime_ball", x_607_J.n_1700_B(Items.Z_3822_q)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.Z_3822_q, 9).J_1907_R(a_3742_W.g_4841_c).n_1700_B("has_slime", x_607_J.n_1700_B((q_1803_e)a_3742_W.g_4841_c)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.ElytraResolver, 4).n_1700_B(Character.valueOf('#'), a_3742_W.BlockFly).n_1700_B("##").n_1700_B("##").n_1700_B("has_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.BlockFly)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.V_1446_Y, 4).n_1700_B(Character.valueOf('#'), a_3742_W.h_4320_q).n_1700_B("##").n_1700_B("##").n_1700_B("has_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_4320_q)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.l_697_B).n_1700_B(Character.valueOf('#'), Items.i_770_g).n_1700_B("##").n_1700_B("##").n_1700_B("has_snowball", x_607_J.n_1700_B(Items.i_770_g)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.X_290_I, 6).n_1700_B(Character.valueOf('#'), a_3742_W.l_697_B).n_1700_B("###").n_1700_B("has_snowball", x_607_J.n_1700_B(Items.i_770_g)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.y_4842_Z).n_1700_B(Character.valueOf('L'), ItemTags.t_1786_h).n_1700_B(Character.valueOf('S'), Items.A_4514_U).n_1700_B(Character.valueOf('#'), ItemTags.e_2887_G).n_1700_B(" S ").n_1700_B("S#S").n_1700_B("LLL").n_1700_B("has_stick", x_607_J.n_1700_B(Items.A_4514_U)).n_1700_B("has_soul_sand", x_607_J.n_1700_B(ItemTags.e_2887_G)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.e_1503_j).n_1700_B(Character.valueOf('#'), Items.u_3578_p).n_1700_B(Character.valueOf('X'), Items.B_368_w).n_1700_B("###").n_1700_B("#X#").n_1700_B("###").n_1700_B("has_melon", x_607_J.n_1700_B(Items.B_368_w)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.g_2783_J, 2).n_1700_B(Character.valueOf('#'), Items.AdvancementList).n_1700_B(Character.valueOf('X'), Items.g_24_p).n_1700_B(" # ").n_1700_B("#X#").n_1700_B(" # ").n_1700_B("has_glowstone_dust", x_607_J.n_1700_B(Items.AdvancementList)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.A_4514_U, 4).n_1700_B(Character.valueOf('#'), ItemTags.R_4764_Y).n_1700_B("#").n_1700_B("#").J_1907_R("sticks").n_1700_B("has_planks", x_607_J.n_1700_B(ItemTags.R_4764_Y)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.A_4514_U, 1).n_1700_B(Character.valueOf('#'), a_3742_W.t_1509_b).n_1700_B("#").n_1700_B("#").J_1907_R("sticks").n_1700_B("has_bamboo", x_607_J.n_1700_B((q_1803_e)a_3742_W.t_1509_b)).n_1700_B(consumer, "stick_from_bamboo_item");
        d_2427_y.n_1700_B(a_3742_W.RealmsDefaultUncaughtExceptionHandler).n_1700_B(Character.valueOf('P'), a_3742_W.j_2266_I).n_1700_B(Character.valueOf('S'), Items.Z_3822_q).n_1700_B("S").n_1700_B("P").n_1700_B("has_slime_ball", x_607_J.n_1700_B(Items.Z_3822_q)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.f_691_R, 4).n_1700_B(Character.valueOf('#'), a_3742_W.J_1907_R).n_1700_B("##").n_1700_B("##").n_1700_B("has_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.J_1907_R)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.z_283_n).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), ItemTags.D_4792_h).n_1700_B("XX").n_1700_B("X#").n_1700_B(" #").n_1700_B("has_cobblestone", x_607_J.n_1700_B(ItemTags.D_4792_h)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.Phase, 6).n_1700_B(Character.valueOf('#'), a_3742_W.f_691_R).n_1700_B("###").n_1700_B("has_stone_bricks", x_607_J.n_1700_B(ItemTags.G_564_y)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.F_2860_q, 4).n_1700_B(Character.valueOf('#'), a_3742_W.f_691_R).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_stone_bricks", x_607_J.n_1700_B(ItemTags.G_564_y)).n_1700_B(consumer);
        z_1737_N.n_1700_B(a_3742_W.o_3599_Z).J_1907_R(a_3742_W.J_1907_R).n_1700_B("has_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.J_1907_R)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.a_1887_j).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), ItemTags.D_4792_h).n_1700_B("XX").n_1700_B(" #").n_1700_B(" #").n_1700_B("has_cobblestone", x_607_J.n_1700_B(ItemTags.D_4792_h)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.Y_4293_u).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), ItemTags.D_4792_h).n_1700_B("XXX").n_1700_B(" # ").n_1700_B(" # ").n_1700_B("has_cobblestone", x_607_J.n_1700_B(ItemTags.D_4792_h)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.i_601_W).n_1700_B(Character.valueOf('#'), a_3742_W.J_1907_R).n_1700_B("##").n_1700_B("has_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.J_1907_R)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.z_4066_l).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), ItemTags.D_4792_h).n_1700_B("X").n_1700_B("#").n_1700_B("#").n_1700_B("has_cobblestone", x_607_J.n_1700_B(ItemTags.D_4792_h)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.Jesus, 6).n_1700_B(Character.valueOf('#'), a_3742_W.J_1907_R).n_1700_B("###").n_1700_B("has_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.J_1907_R)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.MoveHelper, 6).n_1700_B(Character.valueOf('#'), a_3742_W.SuperFirework).n_1700_B("###").n_1700_B("has_smooth_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.SuperFirework)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.S_3139_t, 4).n_1700_B(Character.valueOf('#'), a_3742_W.P_4830_p).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_cobblestone", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_4830_p)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.Y_3623_f).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), ItemTags.D_4792_h).n_1700_B("X").n_1700_B("X").n_1700_B("#").n_1700_B("has_cobblestone", x_607_J.n_1700_B(ItemTags.D_4792_h)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.R_3077_Z).n_1700_B(Character.valueOf('#'), Items.Animation).n_1700_B("##").n_1700_B("##").n_1700_B("has_string", x_607_J.n_1700_B(Items.Animation)).n_1700_B(consumer, "white_wool_from_string");
        z_1737_N.n_1700_B(Items.o_3456_E).J_1907_R(a_3742_W.l_3609_d).n_1700_B("sugar").n_1700_B("has_reeds", x_607_J.n_1700_B((q_1803_e)a_3742_W.l_3609_d)).n_1700_B(consumer, "sugar_from_sugar_cane");
        z_1737_N.n_1700_B(Items.o_3456_E, 3).J_1907_R(Items.StructureBlock).n_1700_B("sugar").n_1700_B("has_honey_bottle", x_607_J.n_1700_B(Items.StructureBlock)).n_1700_B(consumer, "sugar_from_honey_bottle");
        d_2427_y.n_1700_B(a_3742_W.Y_2805_J).n_1700_B(Character.valueOf('H'), Items.AntiSurround).n_1700_B(Character.valueOf('R'), Items.v_570_f).n_1700_B(" R ").n_1700_B("RHR").n_1700_B(" R ").n_1700_B("has_redstone", x_607_J.n_1700_B(Items.v_570_f)).n_1700_B("has_hay_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.M_4609_z)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.TextRenderingUtils).n_1700_B(Character.valueOf('#'), b_3278_X.n_1700_B(a_3742_W.A_4115_X, a_3742_W.Y_1740_V)).n_1700_B(Character.valueOf('X'), Items.Easing).n_1700_B("X#X").n_1700_B("#X#").n_1700_B("X#X").n_1700_B("has_gunpowder", x_607_J.n_1700_B(Items.Easing)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.h_935_G).n_1700_B(Character.valueOf('A'), a_3742_W.TextRenderingUtils).n_1700_B(Character.valueOf('B'), Items.u_925_K).n_1700_B("A").n_1700_B("B").n_1700_B("has_minecart", x_607_J.n_1700_B(Items.u_925_K)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.o_2341_D, 4).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), b_3278_X.n_1700_B(Items.T_797_O, Items.d_560_A)).n_1700_B("X").n_1700_B("#").n_1700_B("has_stone_pickaxe", x_607_J.n_1700_B(Items.Y_4293_u)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.I_4348_c, 4).n_1700_B(Character.valueOf('X'), b_3278_X.n_1700_B(Items.T_797_O, Items.d_560_A)).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('S'), ItemTags.e_2887_G).n_1700_B("X").n_1700_B("#").n_1700_B("S").n_1700_B("has_soul_sand", x_607_J.n_1700_B(ItemTags.e_2887_G)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.K_4237_u).n_1700_B(Character.valueOf('#'), Items.x_92_N).n_1700_B(Character.valueOf('X'), Items.f_1186_l).n_1700_B("XXX").n_1700_B("X#X").n_1700_B("XXX").n_1700_B("has_iron_nugget", x_607_J.n_1700_B(Items.f_1186_l)).n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.Z_3822_q).n_1700_B(Character.valueOf('#'), Items.r_260_T).n_1700_B(Character.valueOf('X'), Items.f_1186_l).n_1700_B("XXX").n_1700_B("X#X").n_1700_B("XXX").n_1700_B("has_soul_torch", x_607_J.n_1700_B(Items.r_260_T)).n_1700_B(consumer);
        z_1737_N.n_1700_B(a_3742_W.NumberSetting).J_1907_R(a_3742_W.L_1362_X).J_1907_R(a_3742_W.d_2169_p).n_1700_B("has_tripwire_hook", x_607_J.n_1700_B((q_1803_e)a_3742_W.d_2169_p)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.d_2169_p, 2).n_1700_B(Character.valueOf('#'), ItemTags.R_4764_Y).n_1700_B(Character.valueOf('S'), Items.A_4514_U).n_1700_B(Character.valueOf('I'), Items.D_1621_L).n_1700_B("I").n_1700_B("S").n_1700_B("#").n_1700_B("has_string", x_607_J.n_1700_B(Items.Animation)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.S_315_z).n_1700_B(Character.valueOf('X'), Items.o_977_F).n_1700_B("XXX").n_1700_B("X X").n_1700_B("has_scute", x_607_J.n_1700_B(Items.o_977_F)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.V_3441_j, 9).J_1907_R(a_3742_W.M_4609_z).n_1700_B("has_hay_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.M_4609_z)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.ServerFunctionManager).J_1907_R(Items.r_1970_q).n_1700_B("white_dye").n_1700_B("has_bone_meal", x_607_J.n_1700_B(Items.r_1970_q)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.ServerFunctionManager).J_1907_R(a_3742_W.u_55_V).n_1700_B("white_dye").n_1700_B("has_white_flower", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_55_V)).n_1700_B(consumer, "white_dye_from_lily_of_the_valley");
        d_2427_y.n_1700_B(Items.a_2727_J).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), ItemTags.R_4764_Y).n_1700_B("XX").n_1700_B("X#").n_1700_B(" #").n_1700_B("has_stick", x_607_J.n_1700_B(Items.A_4514_U)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.D_1410_T).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), ItemTags.R_4764_Y).n_1700_B("XX").n_1700_B(" #").n_1700_B(" #").n_1700_B("has_stick", x_607_J.n_1700_B(Items.A_4514_U)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.N_1833_W).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), ItemTags.R_4764_Y).n_1700_B("XXX").n_1700_B(" # ").n_1700_B(" # ").n_1700_B("has_stick", x_607_J.n_1700_B(Items.A_4514_U)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.B_707_U).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), ItemTags.R_4764_Y).n_1700_B("X").n_1700_B("#").n_1700_B("#").n_1700_B("has_stick", x_607_J.n_1700_B(Items.A_4514_U)).n_1700_B(consumer);
        d_2427_y.n_1700_B(Items.S_234_U).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('X'), ItemTags.R_4764_Y).n_1700_B("X").n_1700_B("X").n_1700_B("#").n_1700_B("has_stick", x_607_J.n_1700_B(Items.A_4514_U)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.CropBlock).J_1907_R(Items.K_4237_u).J_1907_R(Items.B_3068_A).J_1907_R(Items.H_274_C).n_1700_B("has_book", x_607_J.n_1700_B(Items.K_4237_u)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.S_4998_h).J_1907_R(a_3742_W.s_1671_u).n_1700_B("yellow_dye").n_1700_B("has_yellow_flower", x_607_J.n_1700_B((q_1803_e)a_3742_W.s_1671_u)).n_1700_B(consumer, "yellow_dye_from_dandelion");
        z_1737_N.n_1700_B(Items.S_4998_h, 2).J_1907_R(a_3742_W.V_983_n).n_1700_B("yellow_dye").n_1700_B("has_double_plant", x_607_J.n_1700_B((q_1803_e)a_3742_W.V_983_n)).n_1700_B(consumer, "yellow_dye_from_sunflower");
        z_1737_N.n_1700_B(Items.MinMaxBounds, 9).J_1907_R(a_3742_W.T_797_O).n_1700_B("has_dried_kelp_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.T_797_O)).n_1700_B(consumer);
        z_1737_N.n_1700_B(a_3742_W.T_797_O).J_1907_R(Items.MinMaxBounds, 9).n_1700_B("has_dried_kelp", x_607_J.n_1700_B(Items.MinMaxBounds)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.V_3441_j).n_1700_B(Character.valueOf('#'), Items.SandBlock).n_1700_B(Character.valueOf('X'), Items.SaplingBlock).n_1700_B("###").n_1700_B("#X#").n_1700_B("###").n_1700_B("has_nautilus_core", x_607_J.n_1700_B(Items.SaplingBlock)).n_1700_B("has_nautilus_shell", x_607_J.n_1700_B(Items.SandBlock)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.f_800_j, 4).n_1700_B(Character.valueOf('#'), a_3742_W.G_564_y).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_polished_granite", x_607_J.n_1700_B((q_1803_e)a_3742_W.G_564_y)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.R_2329_T, 4).n_1700_B(Character.valueOf('#'), a_3742_W.AntiAFK).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_smooth_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.AntiAFK)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.F_747_P, 4).n_1700_B(Character.valueOf('#'), a_3742_W.I_4481_g).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_mossy_stone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.I_4481_g)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.T_1170_t, 4).n_1700_B(Character.valueOf('#'), a_3742_W.u_1723_Y).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_polished_diorite", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_1723_Y)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.k_2282_P, 4).n_1700_B(Character.valueOf('#'), a_3742_W.U_1341_G).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_mossy_cobblestone", x_607_J.n_1700_B((q_1803_e)a_3742_W.U_1341_G)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.U_2474_c, 4).n_1700_B(Character.valueOf('#'), a_3742_W.FastPlace).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_end_stone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.FastPlace)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.j_2302_z, 4).n_1700_B(Character.valueOf('#'), a_3742_W.J_1907_R).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.J_1907_R)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.q_4361_M, 4).n_1700_B(Character.valueOf('#'), a_3742_W.Timer).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_smooth_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.Timer)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.f_508_U, 4).n_1700_B(Character.valueOf('#'), a_3742_W.WaterSpeed).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_smooth_quartz", x_607_J.n_1700_B((q_1803_e)a_3742_W.WaterSpeed)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.A_1603_w, 4).n_1700_B(Character.valueOf('#'), a_3742_W.R_4764_Y).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_granite", x_607_J.n_1700_B((q_1803_e)a_3742_W.R_4764_Y)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.V_4557_X, 4).n_1700_B(Character.valueOf('#'), a_3742_W.v_4262_N).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_andesite", x_607_J.n_1700_B((q_1803_e)a_3742_W.v_4262_N)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.h_3066_J, 4).n_1700_B(Character.valueOf('#'), a_3742_W.NoInteract).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_red_nether_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.NoInteract)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.m_38_G, 4).n_1700_B(Character.valueOf('#'), a_3742_W.w_1484_f).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_polished_andesite", x_607_J.n_1700_B((q_1803_e)a_3742_W.w_1484_f)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.k_4946_A, 4).n_1700_B(Character.valueOf('#'), a_3742_W.P_1922_E).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_diorite", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_1922_E)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.m_4644_u, 6).n_1700_B(Character.valueOf('#'), a_3742_W.G_564_y).n_1700_B("###").n_1700_B("has_polished_granite", x_607_J.n_1700_B((q_1803_e)a_3742_W.G_564_y)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.u_488_m, 6).n_1700_B(Character.valueOf('#'), a_3742_W.AntiAFK).n_1700_B("###").n_1700_B("has_smooth_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.AntiAFK)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.O_1043_U, 6).n_1700_B(Character.valueOf('#'), a_3742_W.I_4481_g).n_1700_B("###").n_1700_B("has_mossy_stone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.I_4481_g)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.v_1900_v, 6).n_1700_B(Character.valueOf('#'), a_3742_W.u_1723_Y).n_1700_B("###").n_1700_B("has_polished_diorite", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_1723_Y)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.j_2129_E, 6).n_1700_B(Character.valueOf('#'), a_3742_W.U_1341_G).n_1700_B("###").n_1700_B("has_mossy_cobblestone", x_607_J.n_1700_B((q_1803_e)a_3742_W.U_1341_G)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.W_1488_x, 6).n_1700_B(Character.valueOf('#'), a_3742_W.FastPlace).n_1700_B("###").n_1700_B("has_end_stone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.FastPlace)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.j_1654_T, 6).n_1700_B(Character.valueOf('#'), a_3742_W.Timer).n_1700_B("###").n_1700_B("has_smooth_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.Timer)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.l_3729_r, 6).n_1700_B(Character.valueOf('#'), a_3742_W.WaterSpeed).n_1700_B("###").n_1700_B("has_smooth_quartz", x_607_J.n_1700_B((q_1803_e)a_3742_W.WaterSpeed)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.q_3115_L, 6).n_1700_B(Character.valueOf('#'), a_3742_W.R_4764_Y).n_1700_B("###").n_1700_B("has_granite", x_607_J.n_1700_B((q_1803_e)a_3742_W.R_4764_Y)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.p_863_D, 6).n_1700_B(Character.valueOf('#'), a_3742_W.v_4262_N).n_1700_B("###").n_1700_B("has_andesite", x_607_J.n_1700_B((q_1803_e)a_3742_W.v_4262_N)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.E_4612_l, 6).n_1700_B(Character.valueOf('#'), a_3742_W.NoInteract).n_1700_B("###").n_1700_B("has_red_nether_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.NoInteract)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.v_143_j, 6).n_1700_B(Character.valueOf('#'), a_3742_W.w_1484_f).n_1700_B("###").n_1700_B("has_polished_andesite", x_607_J.n_1700_B((q_1803_e)a_3742_W.w_1484_f)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.U_3443_A, 6).n_1700_B(Character.valueOf('#'), a_3742_W.P_1922_E).n_1700_B("###").n_1700_B("has_diorite", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_1922_E)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.Y_2143_L, 6).n_1700_B(Character.valueOf('#'), a_3742_W.d_4007_L).n_1700_B("###").n_1700_B("###").n_1700_B("has_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.d_4007_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.U_567_E, 6).n_1700_B(Character.valueOf('#'), a_3742_W.z_2311_U).n_1700_B("###").n_1700_B("###").n_1700_B("has_prismarine", x_607_J.n_1700_B((q_1803_e)a_3742_W.z_2311_U)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.i_1479_B, 6).n_1700_B(Character.valueOf('#'), a_3742_W.BlockFly).n_1700_B("###").n_1700_B("###").n_1700_B("has_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.BlockFly)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.t_4562_T, 6).n_1700_B(Character.valueOf('#'), a_3742_W.I_4481_g).n_1700_B("###").n_1700_B("###").n_1700_B("has_mossy_stone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.I_4481_g)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.H_3529_d, 6).n_1700_B(Character.valueOf('#'), a_3742_W.R_4764_Y).n_1700_B("###").n_1700_B("###").n_1700_B("has_granite", x_607_J.n_1700_B((q_1803_e)a_3742_W.R_4764_Y)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.U_3005_m, 6).n_1700_B(Character.valueOf('#'), a_3742_W.f_691_R).n_1700_B("###").n_1700_B("###").n_1700_B("has_stone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.f_691_R)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.G_1539_D, 6).n_1700_B(Character.valueOf('#'), a_3742_W.h_1015_G).n_1700_B("###").n_1700_B("###").n_1700_B("has_nether_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_1015_G)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.W_2770_z, 6).n_1700_B(Character.valueOf('#'), a_3742_W.v_4262_N).n_1700_B("###").n_1700_B("###").n_1700_B("has_andesite", x_607_J.n_1700_B((q_1803_e)a_3742_W.v_4262_N)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.u_1934_K, 6).n_1700_B(Character.valueOf('#'), a_3742_W.NoInteract).n_1700_B("###").n_1700_B("###").n_1700_B("has_red_nether_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.NoInteract)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.u_925_K, 6).n_1700_B(Character.valueOf('#'), a_3742_W.h_4320_q).n_1700_B("###").n_1700_B("###").n_1700_B("has_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_4320_q)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.Z_361_l, 6).n_1700_B(Character.valueOf('#'), a_3742_W.FastPlace).n_1700_B("###").n_1700_B("###").n_1700_B("has_end_stone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.FastPlace)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.v_570_f, 6).n_1700_B(Character.valueOf('#'), a_3742_W.P_1922_E).n_1700_B("###").n_1700_B("###").n_1700_B("has_diorite", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_1922_E)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.InfestedBlock).J_1907_R(Items.l_3370_o).J_1907_R(Items.EndPortalBlock).n_1700_B("has_creeper_head", x_607_J.n_1700_B(Items.EndPortalBlock)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.PipeBlock).J_1907_R(Items.l_3370_o).J_1907_R(Items.DropperBlock).n_1700_B("has_wither_skeleton_skull", x_607_J.n_1700_B(Items.DropperBlock)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.Y_3462_U).J_1907_R(Items.l_3370_o).J_1907_R(a_3742_W.C_1162_e).n_1700_B("has_oxeye_daisy", x_607_J.n_1700_B((q_1803_e)a_3742_W.C_1162_e)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.SkullBlock).J_1907_R(Items.l_3370_o).J_1907_R(Items.E_4612_l).n_1700_B("has_enchanted_golden_apple", x_607_J.n_1700_B(Items.E_4612_l)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.i_770_g, 6).n_1700_B(Character.valueOf('~'), Items.Animation).n_1700_B(Character.valueOf('I'), a_3742_W.t_1509_b).n_1700_B("I~I").n_1700_B("I I").n_1700_B("I I").n_1700_B("has_bamboo", x_607_J.n_1700_B((q_1803_e)a_3742_W.t_1509_b)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.v_2826_q).n_1700_B(Character.valueOf('I'), Items.A_4514_U).n_1700_B(Character.valueOf('-'), a_3742_W.Jesus).n_1700_B(Character.valueOf('#'), ItemTags.R_4764_Y).n_1700_B("I-I").n_1700_B("# #").n_1700_B("has_stone_slab", x_607_J.n_1700_B((q_1803_e)a_3742_W.Jesus)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.F_2052_z).n_1700_B(Character.valueOf('#'), a_3742_W.SuperFirework).n_1700_B(Character.valueOf('X'), a_3742_W.P_925_e).n_1700_B(Character.valueOf('I'), Items.D_1621_L).n_1700_B("III").n_1700_B("IXI").n_1700_B("###").n_1700_B("has_smooth_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.SuperFirework)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.H_2506_c).n_1700_B(Character.valueOf('#'), ItemTags.t_1786_h).n_1700_B(Character.valueOf('X'), a_3742_W.P_925_e).n_1700_B(" # ").n_1700_B("#X#").n_1700_B(" # ").n_1700_B("has_furnace", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_925_e)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.j_1376_w).n_1700_B(Character.valueOf('#'), ItemTags.R_4764_Y).n_1700_B(Character.valueOf('@'), Items.l_3370_o).n_1700_B("@@").n_1700_B("##").n_1700_B("##").n_1700_B("has_paper", x_607_J.n_1700_B(Items.l_3370_o)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.i_4833_u).n_1700_B(Character.valueOf('#'), ItemTags.R_4764_Y).n_1700_B(Character.valueOf('@'), Items.D_1621_L).n_1700_B("@@").n_1700_B("##").n_1700_B("##").n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.W_3801_h).n_1700_B(Character.valueOf('#'), ItemTags.R_4764_Y).n_1700_B(Character.valueOf('@'), Items.W_1488_x).n_1700_B("@@").n_1700_B("##").n_1700_B("##").n_1700_B("has_flint", x_607_J.n_1700_B(Items.W_1488_x)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.f_4705_f).n_1700_B(Character.valueOf('I'), Items.D_1621_L).n_1700_B(Character.valueOf('#'), a_3742_W.J_1907_R).n_1700_B(" I ").n_1700_B("###").n_1700_B("has_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.J_1907_R)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.m_3052_r).n_1700_B(Character.valueOf('S'), Items.q_839_y).n_1700_B(Character.valueOf('#'), Items.q_4124_m).n_1700_B("SSS").n_1700_B("S#S").n_1700_B("SSS").n_1700_B("has_netherite_ingot", x_607_J.n_1700_B(Items.q_4124_m)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.LightPredicate).n_1700_B(Character.valueOf('#'), Items.q_4124_m).n_1700_B("###").n_1700_B("###").n_1700_B("###").n_1700_B("has_netherite_ingot", x_607_J.n_1700_B(Items.q_4124_m)).n_1700_B(consumer);
        z_1737_N.n_1700_B(Items.q_4124_m, 9).J_1907_R(a_3742_W.LightPredicate).n_1700_B("netherite_ingot").n_1700_B("has_netherite_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.LightPredicate)).n_1700_B(consumer, "netherite_ingot_from_netherite_block");
        z_1737_N.n_1700_B(Items.q_4124_m).J_1907_R(Items.m_396_H, 4).J_1907_R(Items.ServerHandshakePacketListener, 4).n_1700_B("netherite_ingot").n_1700_B("has_netherite_scrap", x_607_J.n_1700_B(Items.m_396_H)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.WrappedMinMaxBounds).n_1700_B(Character.valueOf('O'), a_3742_W.MinMaxBounds).n_1700_B(Character.valueOf('G'), a_3742_W.X_2960_b).n_1700_B("OOO").n_1700_B("GGG").n_1700_B("OOO").n_1700_B("has_obsidian", x_607_J.n_1700_B((q_1803_e)a_3742_W.MinMaxBounds)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.v_2746_S, 4).n_1700_B(Character.valueOf('#'), a_3742_W.m_1964_F).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.m_1964_F)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.Q_1036_Q, 4).n_1700_B(Character.valueOf('#'), a_3742_W.u_3578_p).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_polished_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_3578_p)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.Y_3066_B, 4).n_1700_B(Character.valueOf('#'), a_3742_W.g_1096_r).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").n_1700_B("has_polished_blackstone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.g_1096_r)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.b_3334_n, 6).n_1700_B(Character.valueOf('#'), a_3742_W.m_1964_F).n_1700_B("###").n_1700_B("has_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.m_1964_F)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.TickTrigger, 6).n_1700_B(Character.valueOf('#'), a_3742_W.u_3578_p).n_1700_B("###").n_1700_B("has_polished_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_3578_p)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.r_2687_x, 6).n_1700_B(Character.valueOf('#'), a_3742_W.g_1096_r).n_1700_B("###").n_1700_B("has_polished_blackstone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.g_1096_r)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.u_3578_p, 4).n_1700_B(Character.valueOf('S'), a_3742_W.m_1964_F).n_1700_B("SS").n_1700_B("SS").n_1700_B("has_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.m_1964_F)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.g_1096_r, 4).n_1700_B(Character.valueOf('#'), a_3742_W.u_3578_p).n_1700_B("##").n_1700_B("##").n_1700_B("has_polished_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_3578_p)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.Y_3588_g).n_1700_B(Character.valueOf('#'), a_3742_W.TickTrigger).n_1700_B("#").n_1700_B("#").n_1700_B("has_polished_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_3578_p)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.A_2487_t, 6).n_1700_B(Character.valueOf('#'), a_3742_W.m_1964_F).n_1700_B("###").n_1700_B("###").n_1700_B("has_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.m_1964_F)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.z_1100_b, 6).n_1700_B(Character.valueOf('#'), a_3742_W.u_3578_p).n_1700_B("###").n_1700_B("###").n_1700_B("has_polished_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_3578_p)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.C_3528_u, 6).n_1700_B(Character.valueOf('#'), a_3742_W.g_1096_r).n_1700_B("###").n_1700_B("###").n_1700_B("has_polished_blackstone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.g_1096_r)).n_1700_B(consumer);
        z_1737_N.n_1700_B(a_3742_W.e_1503_j).J_1907_R(a_3742_W.u_3578_p).n_1700_B("has_polished_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_3578_p)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.V_1824_v).n_1700_B(Character.valueOf('#'), a_3742_W.u_3578_p).n_1700_B("##").n_1700_B("has_polished_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_3578_p)).n_1700_B(consumer);
        d_2427_y.n_1700_B(a_3742_W.r_4879_Z).n_1700_B(Character.valueOf('I'), Items.D_1621_L).n_1700_B(Character.valueOf('N'), Items.f_1186_l).n_1700_B("N").n_1700_B("I").n_1700_B("N").n_1700_B("has_iron_nugget", x_607_J.n_1700_B(Items.f_1186_l)).n_1700_B("has_iron_ingot", x_607_J.n_1700_B(Items.D_1621_L)).n_1700_B(consumer);
        h_1847_R.n_1700_B(RecipeSerializer.R_4764_Y).n_1700_B(consumer, "armor_dye");
        h_1847_R.n_1700_B(RecipeSerializer.u_2550_I).n_1700_B(consumer, "banner_duplicate");
        h_1847_R.n_1700_B(RecipeSerializer.G_564_y).n_1700_B(consumer, "book_cloning");
        h_1847_R.n_1700_B(RecipeSerializer.v_4262_N).n_1700_B(consumer, "firework_rocket");
        h_1847_R.n_1700_B(RecipeSerializer.w_1484_f).n_1700_B(consumer, "firework_star");
        h_1847_R.n_1700_B(RecipeSerializer.t_148_a).n_1700_B(consumer, "firework_star_fade");
        h_1847_R.n_1700_B(RecipeSerializer.P_1922_E).n_1700_B(consumer, "map_cloning");
        h_1847_R.n_1700_B(RecipeSerializer.u_1723_Y).n_1700_B(consumer, "map_extending");
        h_1847_R.n_1700_B(RecipeSerializer.Q_4569_t).n_1700_B(consumer, "repair_item");
        h_1847_R.n_1700_B(RecipeSerializer.M_588_G).n_1700_B(consumer, "shield_decoration");
        h_1847_R.n_1700_B(RecipeSerializer.P_4830_p).n_1700_B(consumer, "shulker_box_coloring");
        h_1847_R.n_1700_B(RecipeSerializer.s_956_w).n_1700_B(consumer, "tipped_arrow");
        h_1847_R.n_1700_B(RecipeSerializer.h_1847_R).n_1700_B(consumer, "suspicious_stew");
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(Items.l_683_e), Items.DirectionalBlock, 0.35f, 200).n_1700_B("has_potato", x_607_J.n_1700_B(Items.l_683_e)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(Items.i_4833_u), Items.F_489_x, 0.3f, 200).n_1700_B("has_clay_ball", x_607_J.n_1700_B(Items.i_4833_u)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(ItemTags.M_182_A), Items.d_560_A, 0.15f, 200).n_1700_B("has_log", x_607_J.n_1700_B(ItemTags.M_182_A)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(Items.MagmaBlock), Items.MelonBlock, 0.1f, 200).n_1700_B("has_chorus_fruit", x_607_J.n_1700_B(Items.MagmaBlock)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.n_3318_d.u_1723_Y()), Items.T_797_O, 0.1f, 200).n_1700_B("has_coal_ore", x_607_J.n_1700_B((q_1803_e)a_3742_W.n_3318_d)).n_1700_B(consumer, "coal_from_smelting");
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(Items.h_2396_v), Items.x_2711_Y, 0.35f, 200).n_1700_B("has_beef", x_607_J.n_1700_B(Items.h_2396_v)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(Items.w_2892_f), Items.m_3052_r, 0.35f, 200).n_1700_B("has_chicken", x_607_J.n_1700_B(Items.w_2892_f)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(Items.ServerAdvancementManager), Items.U_3554_Q, 0.35f, 200).n_1700_B("has_cod", x_607_J.n_1700_B(Items.ServerAdvancementManager)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.R_1796_s), Items.MinMaxBounds, 0.1f, 200).n_1700_B("has_kelp", x_607_J.n_1700_B((q_1803_e)a_3742_W.R_1796_s)).n_1700_B(consumer, "dried_kelp_from_smelting");
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(Items.C_3304_p), Items.T_4001_f, 0.35f, 200).n_1700_B("has_salmon", x_607_J.n_1700_B(Items.C_3304_p)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(Items.s_3698_N), Items.o_869_X, 0.35f, 200).n_1700_B("has_mutton", x_607_J.n_1700_B(Items.s_3698_N)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(Items.j_1654_T), Items.l_3729_r, 0.35f, 200).n_1700_B("has_porkchop", x_607_J.n_1700_B(Items.j_1654_T)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(Items.FungusBlock), Items.A_3138_X, 0.35f, 200).n_1700_B("has_rabbit", x_607_J.n_1700_B(Items.FungusBlock)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.L_4248_u.u_1723_Y()), Items.k_2273_q, 1.0f, 200).n_1700_B("has_diamond_ore", x_607_J.n_1700_B((q_1803_e)a_3742_W.L_4248_u)).n_1700_B(consumer, "diamond_from_smelting");
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.D_60_a.u_1723_Y()), Items.W_4813_f, 0.2f, 200).n_1700_B("has_lapis_ore", x_607_J.n_1700_B((q_1803_e)a_3742_W.D_60_a)).n_1700_B(consumer, "lapis_from_smelting");
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.V_1665_T.u_1723_Y()), Items.Y_2905_A, 1.0f, 200).n_1700_B("has_emerald_ore", x_607_J.n_1700_B((q_1803_e)a_3742_W.V_1665_T)).n_1700_B(consumer, "emerald_from_smelting");
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(ItemTags.c_3005_b), a_3742_W.e_1992_r.u_1723_Y(), 0.1f, 200).n_1700_B("has_sand", x_607_J.n_1700_B(ItemTags.c_3005_b)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(ItemTags.z_4693_k), Items.ServerHandshakePacketListener, 1.0f, 200).n_1700_B("has_gold_ore", x_607_J.n_1700_B(ItemTags.z_4693_k)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.Easing.u_1723_Y()), Items.SimpleCriterionTrigger, 0.1f, 200).n_1700_B("has_sea_pickle", x_607_J.n_1700_B((q_1803_e)a_3742_W.Easing)).n_1700_B(consumer, "lime_dye_from_smelting");
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.d_3244_b.u_1723_Y()), Items.Z_2021_u, 1.0f, 200).n_1700_B("has_cactus", x_607_J.n_1700_B((q_1803_e)a_3742_W.d_3244_b)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(Items.i_1894_C, Items.W_2756_H, Items.u_4724_w, Items.H_1952_g, Items.n_2412_y, Items.h_3066_J, Items.m_38_G, Items.k_4946_A, Items.m_4644_u, Items.GrindstoneBlock), Items.u_3578_p, 0.1f, 200).n_1700_B("has_golden_pickaxe", x_607_J.n_1700_B(Items.i_1894_C)).n_1700_B("has_golden_shovel", x_607_J.n_1700_B(Items.W_2756_H)).n_1700_B("has_golden_axe", x_607_J.n_1700_B(Items.u_4724_w)).n_1700_B("has_golden_hoe", x_607_J.n_1700_B(Items.H_1952_g)).n_1700_B("has_golden_sword", x_607_J.n_1700_B(Items.n_2412_y)).n_1700_B("has_golden_helmet", x_607_J.n_1700_B(Items.h_3066_J)).n_1700_B("has_golden_chestplate", x_607_J.n_1700_B(Items.m_38_G)).n_1700_B("has_golden_leggings", x_607_J.n_1700_B(Items.k_4946_A)).n_1700_B("has_golden_boots", x_607_J.n_1700_B(Items.m_4644_u)).n_1700_B("has_golden_horse_armor", x_607_J.n_1700_B(Items.GrindstoneBlock)).n_1700_B(consumer, "gold_nugget_from_smelting");
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(Items.r_976_u, Items.Z_1243_X, Items.E_390_U, Items.Z_256_c, Items.w_2152_d, Items.T_1170_t, Items.k_2282_P, Items.U_2474_c, Items.j_2302_z, Items.GravelBlock, Items.S_4325_V, Items.f_800_j, Items.R_2329_T, Items.F_747_P), Items.f_1186_l, 0.1f, 200).n_1700_B("has_iron_pickaxe", x_607_J.n_1700_B(Items.r_976_u)).n_1700_B("has_iron_shovel", x_607_J.n_1700_B(Items.Z_1243_X)).n_1700_B("has_iron_axe", x_607_J.n_1700_B(Items.E_390_U)).n_1700_B("has_iron_hoe", x_607_J.n_1700_B(Items.Z_256_c)).n_1700_B("has_iron_sword", x_607_J.n_1700_B(Items.w_2152_d)).n_1700_B("has_iron_helmet", x_607_J.n_1700_B(Items.T_1170_t)).n_1700_B("has_iron_chestplate", x_607_J.n_1700_B(Items.k_2282_P)).n_1700_B("has_iron_leggings", x_607_J.n_1700_B(Items.U_2474_c)).n_1700_B("has_iron_boots", x_607_J.n_1700_B(Items.j_2302_z)).n_1700_B("has_iron_horse_armor", x_607_J.n_1700_B(Items.GravelBlock)).n_1700_B("has_chainmail_helmet", x_607_J.n_1700_B(Items.S_4325_V)).n_1700_B("has_chainmail_chestplate", x_607_J.n_1700_B(Items.f_800_j)).n_1700_B("has_chainmail_leggings", x_607_J.n_1700_B(Items.R_2329_T)).n_1700_B("has_chainmail_boots", x_607_J.n_1700_B(Items.F_747_P)).n_1700_B(consumer, "iron_nugget_from_smelting");
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.e_4240_b.u_1723_Y()), Items.D_1621_L, 0.7f, 200).n_1700_B("has_iron_ore", x_607_J.n_1700_B(a_3742_W.e_4240_b.u_1723_Y())).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.v_887_r), a_3742_W.InventoryPlus.u_1723_Y(), 0.35f, 200).n_1700_B("has_clay_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.v_887_r)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.i_3196_G), Items.FletchingTableBlock, 0.1f, 200).n_1700_B("has_netherrack", x_607_J.n_1700_B((q_1803_e)a_3742_W.i_3196_G)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.N_2266_w), Items.FlowerBlock, 0.2f, 200).n_1700_B("has_nether_quartz_ore", x_607_J.n_1700_B((q_1803_e)a_3742_W.N_2266_w)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.o_1800_r), Items.v_570_f, 0.7f, 200).n_1700_B("has_redstone_ore", x_607_J.n_1700_B((q_1803_e)a_3742_W.o_1800_r)).n_1700_B(consumer, "redstone_from_smelting");
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.UploadStatus), a_3742_W.j_276_v.u_1723_Y(), 0.15f, 200).n_1700_B("has_wet_sponge", x_607_J.n_1700_B((q_1803_e)a_3742_W.UploadStatus)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.P_4830_p), a_3742_W.J_1907_R.u_1723_Y(), 0.1f, 200).n_1700_B("has_cobblestone", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_4830_p)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.J_1907_R), a_3742_W.SuperFirework.u_1723_Y(), 0.1f, 200).n_1700_B("has_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.J_1907_R)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.h_4320_q), a_3742_W.Timer.u_1723_Y(), 0.1f, 200).n_1700_B("has_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_4320_q)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.BlockFly), a_3742_W.AntiAFK.u_1723_Y(), 0.1f, 200).n_1700_B("has_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.BlockFly)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.P_3676_m), a_3742_W.WaterSpeed.u_1723_Y(), 0.1f, 200).n_1700_B("has_quartz_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_3676_m)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.f_691_R), a_3742_W.g_1734_y.u_1723_Y(), 0.1f, 200).n_1700_B("has_stone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.f_691_R)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.s_4054_j), a_3742_W.Particles.u_1723_Y(), 0.1f, 200).n_1700_B("has_black_terracotta", x_607_J.n_1700_B((q_1803_e)a_3742_W.s_4054_j)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.AutoExplosion), a_3742_W.LogoutSpots.u_1723_Y(), 0.1f, 200).n_1700_B("has_blue_terracotta", x_607_J.n_1700_B((q_1803_e)a_3742_W.AutoExplosion)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.AutoSwap), a_3742_W.w_2099_r.u_1723_Y(), 0.1f, 200).n_1700_B("has_brown_terracotta", x_607_J.n_1700_B((q_1803_e)a_3742_W.AutoSwap)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.AutoAnchor), a_3742_W.JumpCircle.u_1723_Y(), 0.1f, 200).n_1700_B("has_cyan_terracotta", x_607_J.n_1700_B((q_1803_e)a_3742_W.AutoAnchor)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.s_4447_V), a_3742_W.ItemPhysics.u_1723_Y(), 0.1f, 200).n_1700_B("has_gray_terracotta", x_607_J.n_1700_B((q_1803_e)a_3742_W.s_4447_V)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.AutoTotem), a_3742_W.R_4688_l.u_1723_Y(), 0.1f, 200).n_1700_B("has_green_terracotta", x_607_J.n_1700_B((q_1803_e)a_3742_W.AutoTotem)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.t_4433_T), a_3742_W.Glint.u_1723_Y(), 0.1f, 200).n_1700_B("has_light_blue_terracotta", x_607_J.n_1700_B((q_1803_e)a_3742_W.t_4433_T)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.AttackAura), a_3742_W.ItemRadius.u_1723_Y(), 0.1f, 200).n_1700_B("has_light_gray_terracotta", x_607_J.n_1700_B((q_1803_e)a_3742_W.AttackAura)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.AntiBot), a_3742_W.HitEffect.u_1723_Y(), 0.1f, 200).n_1700_B("has_lime_terracotta", x_607_J.n_1700_B((q_1803_e)a_3742_W.AntiBot)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.l_4397_i), a_3742_W.FullBright.u_1723_Y(), 0.1f, 200).n_1700_B("has_magenta_terracotta", x_607_J.n_1700_B((q_1803_e)a_3742_W.l_4397_i)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.h_3858_e), a_3742_W.FireworkESP.u_1723_Y(), 0.1f, 200).n_1700_B("has_orange_terracotta", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_3858_e)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.AntiSurround), a_3742_W.Interface.u_1723_Y(), 0.1f, 200).n_1700_B("has_pink_terracotta", x_607_J.n_1700_B((q_1803_e)a_3742_W.AntiSurround)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.AutoCrystal), a_3742_W.KillEffect.u_1723_Y(), 0.1f, 200).n_1700_B("has_purple_terracotta", x_607_J.n_1700_B((q_1803_e)a_3742_W.AutoCrystal)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.AutoTrap), a_3742_W.ObjectInfo.u_1723_Y(), 0.1f, 200).n_1700_B("has_red_terracotta", x_607_J.n_1700_B((q_1803_e)a_3742_W.AutoTrap)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.I_2209_R), a_3742_W.ExtendedTab.u_1723_Y(), 0.1f, 200).n_1700_B("has_white_terracotta", x_607_J.n_1700_B((q_1803_e)a_3742_W.I_2209_R)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.AimAssist), a_3742_W.f_2247_K.u_1723_Y(), 0.1f, 200).n_1700_B("has_yellow_terracotta", x_607_J.n_1700_B((q_1803_e)a_3742_W.AimAssist)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.B_368_w), Items.m_396_H, 2.0f, 200).n_1700_B("has_ancient_debris", x_607_J.n_1700_B((q_1803_e)a_3742_W.B_368_w)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.g_1096_r), a_3742_W.j_2461_G.u_1723_Y(), 0.1f, 200).n_1700_B("has_blackstone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.g_1096_r)).n_1700_B(consumer);
        P_4830_p.J_1907_R(b_3278_X.n_1700_B(a_3742_W.h_1015_G), a_3742_W.b_1557_h.u_1723_Y(), 0.1f, 200).n_1700_B("has_nether_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_1015_G)).n_1700_B(consumer);
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(a_3742_W.e_4240_b.u_1723_Y()), Items.D_1621_L, 0.7f, 100).n_1700_B("has_iron_ore", x_607_J.n_1700_B(a_3742_W.e_4240_b.u_1723_Y())).n_1700_B(consumer, "iron_ingot_from_blasting");
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(ItemTags.z_4693_k), Items.ServerHandshakePacketListener, 1.0f, 100).n_1700_B("has_gold_ore", x_607_J.n_1700_B(ItemTags.z_4693_k)).n_1700_B(consumer, "gold_ingot_from_blasting");
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(a_3742_W.L_4248_u.u_1723_Y()), Items.k_2273_q, 1.0f, 100).n_1700_B("has_diamond_ore", x_607_J.n_1700_B((q_1803_e)a_3742_W.L_4248_u)).n_1700_B(consumer, "diamond_from_blasting");
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(a_3742_W.D_60_a.u_1723_Y()), Items.W_4813_f, 0.2f, 100).n_1700_B("has_lapis_ore", x_607_J.n_1700_B((q_1803_e)a_3742_W.D_60_a)).n_1700_B(consumer, "lapis_from_blasting");
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(a_3742_W.o_1800_r), Items.v_570_f, 0.7f, 100).n_1700_B("has_redstone_ore", x_607_J.n_1700_B((q_1803_e)a_3742_W.o_1800_r)).n_1700_B(consumer, "redstone_from_blasting");
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(a_3742_W.n_3318_d.u_1723_Y()), Items.T_797_O, 0.1f, 100).n_1700_B("has_coal_ore", x_607_J.n_1700_B((q_1803_e)a_3742_W.n_3318_d)).n_1700_B(consumer, "coal_from_blasting");
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(a_3742_W.V_1665_T.u_1723_Y()), Items.Y_2905_A, 1.0f, 100).n_1700_B("has_emerald_ore", x_607_J.n_1700_B((q_1803_e)a_3742_W.V_1665_T)).n_1700_B(consumer, "emerald_from_blasting");
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(a_3742_W.N_2266_w), Items.FlowerBlock, 0.2f, 100).n_1700_B("has_nether_quartz_ore", x_607_J.n_1700_B((q_1803_e)a_3742_W.N_2266_w)).n_1700_B(consumer, "quartz_from_blasting");
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(Items.i_1894_C, Items.W_2756_H, Items.u_4724_w, Items.H_1952_g, Items.n_2412_y, Items.h_3066_J, Items.m_38_G, Items.k_4946_A, Items.m_4644_u, Items.GrindstoneBlock), Items.u_3578_p, 0.1f, 100).n_1700_B("has_golden_pickaxe", x_607_J.n_1700_B(Items.i_1894_C)).n_1700_B("has_golden_shovel", x_607_J.n_1700_B(Items.W_2756_H)).n_1700_B("has_golden_axe", x_607_J.n_1700_B(Items.u_4724_w)).n_1700_B("has_golden_hoe", x_607_J.n_1700_B(Items.H_1952_g)).n_1700_B("has_golden_sword", x_607_J.n_1700_B(Items.n_2412_y)).n_1700_B("has_golden_helmet", x_607_J.n_1700_B(Items.h_3066_J)).n_1700_B("has_golden_chestplate", x_607_J.n_1700_B(Items.m_38_G)).n_1700_B("has_golden_leggings", x_607_J.n_1700_B(Items.k_4946_A)).n_1700_B("has_golden_boots", x_607_J.n_1700_B(Items.m_4644_u)).n_1700_B("has_golden_horse_armor", x_607_J.n_1700_B(Items.GrindstoneBlock)).n_1700_B(consumer, "gold_nugget_from_blasting");
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(Items.r_976_u, Items.Z_1243_X, Items.E_390_U, Items.Z_256_c, Items.w_2152_d, Items.T_1170_t, Items.k_2282_P, Items.U_2474_c, Items.j_2302_z, Items.GravelBlock, Items.S_4325_V, Items.f_800_j, Items.R_2329_T, Items.F_747_P), Items.f_1186_l, 0.1f, 100).n_1700_B("has_iron_pickaxe", x_607_J.n_1700_B(Items.r_976_u)).n_1700_B("has_iron_shovel", x_607_J.n_1700_B(Items.Z_1243_X)).n_1700_B("has_iron_axe", x_607_J.n_1700_B(Items.E_390_U)).n_1700_B("has_iron_hoe", x_607_J.n_1700_B(Items.Z_256_c)).n_1700_B("has_iron_sword", x_607_J.n_1700_B(Items.w_2152_d)).n_1700_B("has_iron_helmet", x_607_J.n_1700_B(Items.T_1170_t)).n_1700_B("has_iron_chestplate", x_607_J.n_1700_B(Items.k_2282_P)).n_1700_B("has_iron_leggings", x_607_J.n_1700_B(Items.U_2474_c)).n_1700_B("has_iron_boots", x_607_J.n_1700_B(Items.j_2302_z)).n_1700_B("has_iron_horse_armor", x_607_J.n_1700_B(Items.GravelBlock)).n_1700_B("has_chainmail_helmet", x_607_J.n_1700_B(Items.S_4325_V)).n_1700_B("has_chainmail_chestplate", x_607_J.n_1700_B(Items.f_800_j)).n_1700_B("has_chainmail_leggings", x_607_J.n_1700_B(Items.R_2329_T)).n_1700_B("has_chainmail_boots", x_607_J.n_1700_B(Items.F_747_P)).n_1700_B(consumer, "iron_nugget_from_blasting");
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(a_3742_W.B_368_w), Items.m_396_H, 2.0f, 100).n_1700_B("has_ancient_debris", x_607_J.n_1700_B((q_1803_e)a_3742_W.B_368_w)).n_1700_B(consumer, "netherite_scrap_from_blasting");
        x_607_J.n_1700_B(consumer, "smoking", RecipeSerializer.multiplayerClientSuggestionProvider, 100);
        x_607_J.n_1700_B(consumer, "campfire_cooking", RecipeSerializer.w_1457_N, 600);
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.J_1907_R), a_3742_W.Jesus, 2).n_1700_B("has_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.J_1907_R)).n_1700_B(consumer, "stone_slab_from_stone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.J_1907_R), a_3742_W.j_2302_z).n_1700_B("has_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.J_1907_R)).n_1700_B(consumer, "stone_stairs_from_stone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.J_1907_R), a_3742_W.f_691_R).n_1700_B("has_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.J_1907_R)).n_1700_B(consumer, "stone_bricks_from_stone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.J_1907_R), a_3742_W.Phase, 2).n_1700_B("has_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.J_1907_R)).n_1700_B(consumer, "stone_brick_slab_from_stone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.J_1907_R), a_3742_W.F_2860_q).n_1700_B("has_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.J_1907_R)).n_1700_B(consumer, "stone_brick_stairs_from_stone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.J_1907_R), a_3742_W.I_3457_f).n_1700_B("has_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.J_1907_R)).n_1700_B(consumer, "chiseled_stone_bricks_stone_from_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.J_1907_R), a_3742_W.U_3005_m).n_1700_B("has_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.J_1907_R)).n_1700_B(consumer, "stone_brick_walls_from_stone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.h_4320_q), a_3742_W.V_1446_Y).n_1700_B("has_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_4320_q)).n_1700_B(consumer, "cut_sandstone_from_sandstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.h_4320_q), a_3742_W.NoFall, 2).n_1700_B("has_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_4320_q)).n_1700_B(consumer, "sandstone_slab_from_sandstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.h_4320_q), a_3742_W.NoJumpDelay, 2).n_1700_B("has_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_4320_q)).n_1700_B(consumer, "cut_sandstone_slab_from_sandstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.V_1446_Y), a_3742_W.NoJumpDelay, 2).n_1700_B("has_cut_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_4320_q)).n_1700_B(consumer, "cut_sandstone_slab_from_cut_sandstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.h_4320_q), a_3742_W.E_4256_w).n_1700_B("has_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_4320_q)).n_1700_B(consumer, "sandstone_stairs_from_sandstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.h_4320_q), a_3742_W.u_925_K).n_1700_B("has_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_4320_q)).n_1700_B(consumer, "sandstone_wall_from_sandstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.h_4320_q), a_3742_W.t_4219_U).n_1700_B("has_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_4320_q)).n_1700_B(consumer, "chiseled_sandstone_from_sandstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.BlockFly), a_3742_W.ElytraResolver).n_1700_B("has_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.BlockFly)).n_1700_B(consumer, "cut_red_sandstone_from_red_sandstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.BlockFly), a_3742_W.Sprint, 2).n_1700_B("has_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.BlockFly)).n_1700_B(consumer, "red_sandstone_slab_from_red_sandstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.BlockFly), a_3742_W.Step, 2).n_1700_B("has_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.BlockFly)).n_1700_B(consumer, "cut_red_sandstone_slab_from_red_sandstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.ElytraResolver), a_3742_W.Step, 2).n_1700_B("has_cut_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.BlockFly)).n_1700_B(consumer, "cut_red_sandstone_slab_from_cut_red_sandstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.BlockFly), a_3742_W.ElytraJump).n_1700_B("has_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.BlockFly)).n_1700_B(consumer, "red_sandstone_stairs_from_red_sandstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.BlockFly), a_3742_W.i_1479_B).n_1700_B("has_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.BlockFly)).n_1700_B(consumer, "red_sandstone_wall_from_red_sandstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.BlockFly), a_3742_W.BoatNoClip).n_1700_B("has_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.BlockFly)).n_1700_B(consumer, "chiseled_red_sandstone_from_red_sandstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.P_3676_m), a_3742_W.Spider, 2).n_1700_B("has_quartz_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_3676_m)).n_1700_B(consumer, "quartz_slab_from_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.P_3676_m), a_3742_W.R_3213_X).n_1700_B("has_quartz_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_3676_m)).n_1700_B(consumer, "quartz_stairs_from_quartz_block_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.P_3676_m), a_3742_W.f_887_Z).n_1700_B("has_quartz_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_3676_m)).n_1700_B(consumer, "quartz_pillar_from_quartz_block_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.P_3676_m), a_3742_W.Q_4222_k).n_1700_B("has_quartz_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_3676_m)).n_1700_B(consumer, "chiseled_quartz_block_from_quartz_block_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.P_3676_m), a_3742_W.AbstractBannerBlock).n_1700_B("has_quartz_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_3676_m)).n_1700_B(consumer, "quartz_bricks_from_quartz_block_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.P_4830_p), a_3742_W.S_3139_t).n_1700_B("has_cobblestone", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_4830_p)).n_1700_B(consumer, "cobblestone_stairs_from_cobblestone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.P_4830_p), a_3742_W.NoSlow, 2).n_1700_B("has_cobblestone", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_4830_p)).n_1700_B(consumer, "cobblestone_slab_from_cobblestone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.P_4830_p), a_3742_W.h_3859_C).n_1700_B("has_cobblestone", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_4830_p)).n_1700_B(consumer, "cobblestone_wall_from_cobblestone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.f_691_R), a_3742_W.Phase, 2).n_1700_B("has_stone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.f_691_R)).n_1700_B(consumer, "stone_brick_slab_from_stone_bricks_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.f_691_R), a_3742_W.F_2860_q).n_1700_B("has_stone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.f_691_R)).n_1700_B(consumer, "stone_brick_stairs_from_stone_bricks_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.f_691_R), a_3742_W.U_3005_m).n_1700_B("has_stone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.f_691_R)).n_1700_B(consumer, "stone_brick_wall_from_stone_bricks_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.f_691_R), a_3742_W.I_3457_f).n_1700_B("has_stone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.f_691_R)).n_1700_B(consumer, "chiseled_stone_bricks_from_stone_bricks_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.d_4007_L), a_3742_W.NoWeb, 2).n_1700_B("has_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.d_4007_L)).n_1700_B(consumer, "brick_slab_from_bricks_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.d_4007_L), a_3742_W.B_1146_q).n_1700_B("has_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.d_4007_L)).n_1700_B(consumer, "brick_stairs_from_bricks_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.d_4007_L), a_3742_W.Y_2143_L).n_1700_B("has_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.d_4007_L)).n_1700_B(consumer, "brick_wall_from_bricks_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.h_1015_G), a_3742_W.Speed, 2).n_1700_B("has_nether_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_1015_G)).n_1700_B(consumer, "nether_brick_slab_from_nether_bricks_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.h_1015_G), a_3742_W.O_2761_o).n_1700_B("has_nether_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_1015_G)).n_1700_B(consumer, "nether_brick_stairs_from_nether_bricks_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.h_1015_G), a_3742_W.G_1539_D).n_1700_B("has_nether_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_1015_G)).n_1700_B(consumer, "nether_brick_wall_from_nether_bricks_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.h_1015_G), a_3742_W.e_2973_e).n_1700_B("has_nether_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.h_1015_G)).n_1700_B(consumer, "chiseled_nether_bricks_from_nether_bricks_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.NoInteract), a_3742_W.E_4612_l, 2).n_1700_B("has_nether_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.NoInteract)).n_1700_B(consumer, "red_nether_brick_slab_from_red_nether_bricks_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.NoInteract), a_3742_W.h_3066_J).n_1700_B("has_nether_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.NoInteract)).n_1700_B(consumer, "red_nether_brick_stairs_from_red_nether_bricks_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.NoInteract), a_3742_W.u_1934_K).n_1700_B("has_nether_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.NoInteract)).n_1700_B(consumer, "red_nether_brick_wall_from_red_nether_bricks_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.ClickPearl), a_3742_W.Strafe, 2).n_1700_B("has_purpur_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.ClickPearl)).n_1700_B(consumer, "purpur_slab_from_purpur_block_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.ClickPearl), a_3742_W.FastBreak).n_1700_B("has_purpur_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.ClickPearl)).n_1700_B(consumer, "purpur_stairs_from_purpur_block_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.ClickPearl), a_3742_W.CrystalOptimizer).n_1700_B("has_purpur_block", x_607_J.n_1700_B((q_1803_e)a_3742_W.ClickPearl)).n_1700_B(consumer, "purpur_pillar_from_purpur_block_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.z_2311_U), a_3742_W.l_1757_S, 2).n_1700_B("has_prismarine", x_607_J.n_1700_B((q_1803_e)a_3742_W.z_2311_U)).n_1700_B(consumer, "prismarine_slab_from_prismarine_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.z_2311_U), a_3742_W.e_4654_Y).n_1700_B("has_prismarine", x_607_J.n_1700_B((q_1803_e)a_3742_W.z_2311_U)).n_1700_B(consumer, "prismarine_stairs_from_prismarine_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.z_2311_U), a_3742_W.U_567_E).n_1700_B("has_prismarine", x_607_J.n_1700_B((q_1803_e)a_3742_W.z_2311_U)).n_1700_B(consumer, "prismarine_wall_from_prismarine_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.Q_1082_O), a_3742_W.t_4864_b, 2).n_1700_B("has_prismarine_brick", x_607_J.n_1700_B((q_1803_e)a_3742_W.Q_1082_O)).n_1700_B(consumer, "prismarine_brick_slab_from_prismarine_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.Q_1082_O), a_3742_W.b_967_P).n_1700_B("has_prismarine_brick", x_607_J.n_1700_B((q_1803_e)a_3742_W.Q_1082_O)).n_1700_B(consumer, "prismarine_brick_stairs_from_prismarine_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.G_3540_E), a_3742_W.u_1980_X, 2).n_1700_B("has_dark_prismarine", x_607_J.n_1700_B((q_1803_e)a_3742_W.G_3540_E)).n_1700_B(consumer, "dark_prismarine_slab_from_dark_prismarine_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.G_3540_E), a_3742_W.P_459_I).n_1700_B("has_dark_prismarine", x_607_J.n_1700_B((q_1803_e)a_3742_W.G_3540_E)).n_1700_B(consumer, "dark_prismarine_stairs_from_dark_prismarine_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.v_4262_N), a_3742_W.p_863_D, 2).n_1700_B("has_andesite", x_607_J.n_1700_B((q_1803_e)a_3742_W.v_4262_N)).n_1700_B(consumer, "andesite_slab_from_andesite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.v_4262_N), a_3742_W.V_4557_X).n_1700_B("has_andesite", x_607_J.n_1700_B((q_1803_e)a_3742_W.v_4262_N)).n_1700_B(consumer, "andesite_stairs_from_andesite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.v_4262_N), a_3742_W.W_2770_z).n_1700_B("has_andesite", x_607_J.n_1700_B((q_1803_e)a_3742_W.v_4262_N)).n_1700_B(consumer, "andesite_wall_from_andesite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.v_4262_N), a_3742_W.w_1484_f).n_1700_B("has_andesite", x_607_J.n_1700_B((q_1803_e)a_3742_W.v_4262_N)).n_1700_B(consumer, "polished_andesite_from_andesite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.v_4262_N), a_3742_W.v_143_j, 2).n_1700_B("has_andesite", x_607_J.n_1700_B((q_1803_e)a_3742_W.v_4262_N)).n_1700_B(consumer, "polished_andesite_slab_from_andesite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.v_4262_N), a_3742_W.m_38_G).n_1700_B("has_andesite", x_607_J.n_1700_B((q_1803_e)a_3742_W.v_4262_N)).n_1700_B(consumer, "polished_andesite_stairs_from_andesite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.w_1484_f), a_3742_W.v_143_j, 2).n_1700_B("has_polished_andesite", x_607_J.n_1700_B((q_1803_e)a_3742_W.w_1484_f)).n_1700_B(consumer, "polished_andesite_slab_from_polished_andesite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.w_1484_f), a_3742_W.m_38_G).n_1700_B("has_polished_andesite", x_607_J.n_1700_B((q_1803_e)a_3742_W.w_1484_f)).n_1700_B(consumer, "polished_andesite_stairs_from_polished_andesite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.s_4990_V), a_3742_W.b_2312_j).n_1700_B("has_basalt", x_607_J.n_1700_B((q_1803_e)a_3742_W.s_4990_V)).n_1700_B(consumer, "polished_basalt_from_basalt_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.R_4764_Y), a_3742_W.q_3115_L, 2).n_1700_B("has_granite", x_607_J.n_1700_B((q_1803_e)a_3742_W.R_4764_Y)).n_1700_B(consumer, "granite_slab_from_granite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.R_4764_Y), a_3742_W.A_1603_w).n_1700_B("has_granite", x_607_J.n_1700_B((q_1803_e)a_3742_W.R_4764_Y)).n_1700_B(consumer, "granite_stairs_from_granite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.R_4764_Y), a_3742_W.H_3529_d).n_1700_B("has_granite", x_607_J.n_1700_B((q_1803_e)a_3742_W.R_4764_Y)).n_1700_B(consumer, "granite_wall_from_granite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.R_4764_Y), a_3742_W.G_564_y).n_1700_B("has_granite", x_607_J.n_1700_B((q_1803_e)a_3742_W.R_4764_Y)).n_1700_B(consumer, "polished_granite_from_granite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.R_4764_Y), a_3742_W.m_4644_u, 2).n_1700_B("has_granite", x_607_J.n_1700_B((q_1803_e)a_3742_W.R_4764_Y)).n_1700_B(consumer, "polished_granite_slab_from_granite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.R_4764_Y), a_3742_W.f_800_j).n_1700_B("has_granite", x_607_J.n_1700_B((q_1803_e)a_3742_W.R_4764_Y)).n_1700_B(consumer, "polished_granite_stairs_from_granite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.G_564_y), a_3742_W.m_4644_u, 2).n_1700_B("has_polished_granite", x_607_J.n_1700_B((q_1803_e)a_3742_W.G_564_y)).n_1700_B(consumer, "polished_granite_slab_from_polished_granite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.G_564_y), a_3742_W.f_800_j).n_1700_B("has_polished_granite", x_607_J.n_1700_B((q_1803_e)a_3742_W.G_564_y)).n_1700_B(consumer, "polished_granite_stairs_from_polished_granite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.P_1922_E), a_3742_W.U_3443_A, 2).n_1700_B("has_diorite", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_1922_E)).n_1700_B(consumer, "diorite_slab_from_diorite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.P_1922_E), a_3742_W.k_4946_A).n_1700_B("has_diorite", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_1922_E)).n_1700_B(consumer, "diorite_stairs_from_diorite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.P_1922_E), a_3742_W.v_570_f).n_1700_B("has_diorite", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_1922_E)).n_1700_B(consumer, "diorite_wall_from_diorite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.P_1922_E), a_3742_W.u_1723_Y).n_1700_B("has_diorite", x_607_J.n_1700_B((q_1803_e)a_3742_W.P_1922_E)).n_1700_B(consumer, "polished_diorite_from_diorite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.P_1922_E), a_3742_W.v_1900_v, 2).n_1700_B("has_diorite", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_1723_Y)).n_1700_B(consumer, "polished_diorite_slab_from_diorite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.P_1922_E), a_3742_W.T_1170_t).n_1700_B("has_diorite", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_1723_Y)).n_1700_B(consumer, "polished_diorite_stairs_from_diorite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.u_1723_Y), a_3742_W.v_1900_v, 2).n_1700_B("has_polished_diorite", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_1723_Y)).n_1700_B(consumer, "polished_diorite_slab_from_polished_diorite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.u_1723_Y), a_3742_W.T_1170_t).n_1700_B("has_polished_diorite", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_1723_Y)).n_1700_B(consumer, "polished_diorite_stairs_from_polished_diorite_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.I_4481_g), a_3742_W.O_1043_U, 2).n_1700_B("has_mossy_stone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.I_4481_g)).n_1700_B(consumer, "mossy_stone_brick_slab_from_mossy_stone_brick_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.I_4481_g), a_3742_W.F_747_P).n_1700_B("has_mossy_stone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.I_4481_g)).n_1700_B(consumer, "mossy_stone_brick_stairs_from_mossy_stone_brick_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.I_4481_g), a_3742_W.t_4562_T).n_1700_B("has_mossy_stone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.I_4481_g)).n_1700_B(consumer, "mossy_stone_brick_wall_from_mossy_stone_brick_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.U_1341_G), a_3742_W.j_2129_E, 2).n_1700_B("has_mossy_cobblestone", x_607_J.n_1700_B((q_1803_e)a_3742_W.U_1341_G)).n_1700_B(consumer, "mossy_cobblestone_slab_from_mossy_cobblestone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.U_1341_G), a_3742_W.k_2282_P).n_1700_B("has_mossy_cobblestone", x_607_J.n_1700_B((q_1803_e)a_3742_W.U_1341_G)).n_1700_B(consumer, "mossy_cobblestone_stairs_from_mossy_cobblestone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.U_1341_G), a_3742_W.F_1446_q).n_1700_B("has_mossy_cobblestone", x_607_J.n_1700_B((q_1803_e)a_3742_W.U_1341_G)).n_1700_B(consumer, "mossy_cobblestone_wall_from_mossy_cobblestone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.Timer), a_3742_W.j_1654_T, 2).n_1700_B("has_smooth_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.Timer)).n_1700_B(consumer, "smooth_sandstone_slab_from_smooth_sandstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.Timer), a_3742_W.q_4361_M).n_1700_B("has_mossy_cobblestone", x_607_J.n_1700_B((q_1803_e)a_3742_W.Timer)).n_1700_B(consumer, "smooth_sandstone_stairs_from_smooth_sandstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.AntiAFK), a_3742_W.u_488_m, 2).n_1700_B("has_smooth_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.AntiAFK)).n_1700_B(consumer, "smooth_red_sandstone_slab_from_smooth_red_sandstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.AntiAFK), a_3742_W.R_2329_T).n_1700_B("has_smooth_red_sandstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.AntiAFK)).n_1700_B(consumer, "smooth_red_sandstone_stairs_from_smooth_red_sandstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.WaterSpeed), a_3742_W.l_3729_r, 2).n_1700_B("has_smooth_quartz", x_607_J.n_1700_B((q_1803_e)a_3742_W.WaterSpeed)).n_1700_B(consumer, "smooth_quartz_slab_from_smooth_quartz_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.WaterSpeed), a_3742_W.f_508_U).n_1700_B("has_smooth_quartz", x_607_J.n_1700_B((q_1803_e)a_3742_W.WaterSpeed)).n_1700_B(consumer, "smooth_quartz_stairs_from_smooth_quartz_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.FastPlace), a_3742_W.W_1488_x, 2).n_1700_B("has_end_stone_brick", x_607_J.n_1700_B((q_1803_e)a_3742_W.FastPlace)).n_1700_B(consumer, "end_stone_brick_slab_from_end_stone_brick_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.FastPlace), a_3742_W.U_2474_c).n_1700_B("has_end_stone_brick", x_607_J.n_1700_B((q_1803_e)a_3742_W.FastPlace)).n_1700_B(consumer, "end_stone_brick_stairs_from_end_stone_brick_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.FastPlace), a_3742_W.Z_361_l).n_1700_B("has_end_stone_brick", x_607_J.n_1700_B((q_1803_e)a_3742_W.FastPlace)).n_1700_B(consumer, "end_stone_brick_wall_from_end_stone_brick_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.e_1231_S), a_3742_W.FastPlace).n_1700_B("has_end_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.e_1231_S)).n_1700_B(consumer, "end_stone_bricks_from_end_stone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.e_1231_S), a_3742_W.W_1488_x, 2).n_1700_B("has_end_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.e_1231_S)).n_1700_B(consumer, "end_stone_brick_slab_from_end_stone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.e_1231_S), a_3742_W.U_2474_c).n_1700_B("has_end_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.e_1231_S)).n_1700_B(consumer, "end_stone_brick_stairs_from_end_stone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.e_1231_S), a_3742_W.Z_361_l).n_1700_B("has_end_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.e_1231_S)).n_1700_B(consumer, "end_stone_brick_wall_from_end_stone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.SuperFirework), a_3742_W.MoveHelper, 2).n_1700_B("has_smooth_stone", x_607_J.n_1700_B((q_1803_e)a_3742_W.SuperFirework)).n_1700_B(consumer, "smooth_stone_slab_from_smooth_stone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.m_1964_F), a_3742_W.b_3334_n, 2).n_1700_B("has_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.m_1964_F)).n_1700_B(consumer, "blackstone_slab_from_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.m_1964_F), a_3742_W.v_2746_S).n_1700_B("has_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.m_1964_F)).n_1700_B(consumer, "blackstone_stairs_from_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.m_1964_F), a_3742_W.A_2487_t).n_1700_B("has_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.m_1964_F)).n_1700_B(consumer, "blackstone_wall_from_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.m_1964_F), a_3742_W.u_3578_p).n_1700_B("has_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.m_1964_F)).n_1700_B(consumer, "polished_blackstone_from_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.m_1964_F), a_3742_W.z_1100_b).n_1700_B("has_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.m_1964_F)).n_1700_B(consumer, "polished_blackstone_wall_from_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.m_1964_F), a_3742_W.TickTrigger, 2).n_1700_B("has_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.m_1964_F)).n_1700_B(consumer, "polished_blackstone_slab_from_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.m_1964_F), a_3742_W.Q_1036_Q).n_1700_B("has_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.m_1964_F)).n_1700_B(consumer, "polished_blackstone_stairs_from_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.m_1964_F), a_3742_W.Y_3588_g).n_1700_B("has_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.m_1964_F)).n_1700_B(consumer, "chiseled_polished_blackstone_from_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.m_1964_F), a_3742_W.g_1096_r).n_1700_B("has_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.m_1964_F)).n_1700_B(consumer, "polished_blackstone_bricks_from_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.m_1964_F), a_3742_W.r_2687_x, 2).n_1700_B("has_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.m_1964_F)).n_1700_B(consumer, "polished_blackstone_brick_slab_from_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.m_1964_F), a_3742_W.Y_3066_B).n_1700_B("has_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.m_1964_F)).n_1700_B(consumer, "polished_blackstone_brick_stairs_from_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.m_1964_F), a_3742_W.C_3528_u).n_1700_B("has_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.m_1964_F)).n_1700_B(consumer, "polished_blackstone_brick_wall_from_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.u_3578_p), a_3742_W.TickTrigger, 2).n_1700_B("has_polished_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_3578_p)).n_1700_B(consumer, "polished_blackstone_slab_from_polished_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.u_3578_p), a_3742_W.Q_1036_Q).n_1700_B("has_polished_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_3578_p)).n_1700_B(consumer, "polished_blackstone_stairs_from_polished_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.u_3578_p), a_3742_W.g_1096_r).n_1700_B("has_polished_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_3578_p)).n_1700_B(consumer, "polished_blackstone_bricks_from_polished_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.u_3578_p), a_3742_W.z_1100_b).n_1700_B("has_polished_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_3578_p)).n_1700_B(consumer, "polished_blackstone_wall_from_polished_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.u_3578_p), a_3742_W.r_2687_x, 2).n_1700_B("has_polished_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_3578_p)).n_1700_B(consumer, "polished_blackstone_brick_slab_from_polished_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.u_3578_p), a_3742_W.Y_3066_B).n_1700_B("has_polished_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_3578_p)).n_1700_B(consumer, "polished_blackstone_brick_stairs_from_polished_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.u_3578_p), a_3742_W.C_3528_u).n_1700_B("has_polished_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_3578_p)).n_1700_B(consumer, "polished_blackstone_brick_wall_from_polished_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.u_3578_p), a_3742_W.Y_3588_g).n_1700_B("has_polished_blackstone", x_607_J.n_1700_B((q_1803_e)a_3742_W.u_3578_p)).n_1700_B(consumer, "chiseled_polished_blackstone_from_polished_blackstone_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.g_1096_r), a_3742_W.r_2687_x, 2).n_1700_B("has_polished_blackstone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.g_1096_r)).n_1700_B(consumer, "polished_blackstone_brick_slab_from_polished_blackstone_bricks_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.g_1096_r), a_3742_W.Y_3066_B).n_1700_B("has_polished_blackstone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.g_1096_r)).n_1700_B(consumer, "polished_blackstone_brick_stairs_from_polished_blackstone_bricks_stonecutting");
        v_4276_D.n_1700_B(b_3278_X.n_1700_B(a_3742_W.g_1096_r), a_3742_W.C_3528_u).n_1700_B("has_polished_blackstone_bricks", x_607_J.n_1700_B((q_1803_e)a_3742_W.g_1096_r)).n_1700_B(consumer, "polished_blackstone_brick_wall_from_polished_blackstone_bricks_stonecutting");
        x_607_J.n_1700_B(consumer, Items.f_508_U, Items.O_1043_U);
        x_607_J.n_1700_B(consumer, Items.A_1603_w, Items.v_1900_v);
        x_607_J.n_1700_B(consumer, Items.q_4361_M, Items.u_488_m);
        x_607_J.n_1700_B(consumer, Items.V_4557_X, Items.j_2129_E);
        x_607_J.n_1700_B(consumer, Items.N_2592_G, Items.K_1200_E);
        x_607_J.n_1700_B(consumer, Items.M_2029_A, Items.O_922_L);
        x_607_J.n_1700_B(consumer, Items.C_1577_A, Items.K_1964_I);
        x_607_J.n_1700_B(consumer, Items.q_3148_R, Items.D_940_S);
        x_607_J.n_1700_B(consumer, Items.s_1124_y, Items.D_563_q);
    }

    private static void n_1700_B(Consumer<C_2741_M> recipeConsumer, q_1613_l itemToReinforce, q_1613_l output) {
        d_2461_k.n_1700_B(b_3278_X.n_1700_B(itemToReinforce), b_3278_X.n_1700_B(Items.q_4124_m), output).n_1700_B("has_netherite_ingot", x_607_J.n_1700_B(Items.q_4124_m)).n_1700_B(recipeConsumer, V_3137_a.e_2887_G.J_1907_R(output.u_1723_Y()).J_1907_R() + "_smithing");
    }

    private static void n_1700_B(Consumer<C_2741_M> recipeConsumer, q_1803_e planks, r_109_r<q_1613_l> input) {
        z_1737_N.n_1700_B(planks, 4).n_1700_B(input).n_1700_B("planks").n_1700_B("has_log", x_607_J.n_1700_B(input)).n_1700_B(recipeConsumer);
    }

    private static void J_1907_R(Consumer<C_2741_M> recipeConsumer, q_1803_e planks, r_109_r<q_1613_l> input) {
        z_1737_N.n_1700_B(planks, 4).n_1700_B(input).n_1700_B("planks").n_1700_B("has_logs", x_607_J.n_1700_B(input)).n_1700_B(recipeConsumer);
    }

    private static void n_1700_B(Consumer<C_2741_M> recipeConsumer, q_1803_e stripped, q_1803_e input) {
        d_2427_y.n_1700_B(stripped, 3).n_1700_B(Character.valueOf('#'), input).n_1700_B("##").n_1700_B("##").J_1907_R("bark").n_1700_B("has_log", x_607_J.n_1700_B(input)).n_1700_B(recipeConsumer);
    }

    private static void J_1907_R(Consumer<C_2741_M> recipeConsumer, q_1803_e boat, q_1803_e input) {
        d_2427_y.n_1700_B(boat).n_1700_B(Character.valueOf('#'), input).n_1700_B("# #").n_1700_B("###").J_1907_R("boat").n_1700_B("in_water", x_607_J.n_1700_B(a_3742_W.c_3005_b)).n_1700_B(recipeConsumer);
    }

    private static void R_4764_Y(Consumer<C_2741_M> recipeConsumer, q_1803_e button, q_1803_e input) {
        z_1737_N.n_1700_B(button).J_1907_R(input).n_1700_B("wooden_button").n_1700_B("has_planks", x_607_J.n_1700_B(input)).n_1700_B(recipeConsumer);
    }

    private static void G_564_y(Consumer<C_2741_M> recipeConsumer, q_1803_e door, q_1803_e input) {
        d_2427_y.n_1700_B(door, 3).n_1700_B(Character.valueOf('#'), input).n_1700_B("##").n_1700_B("##").n_1700_B("##").J_1907_R("wooden_door").n_1700_B("has_planks", x_607_J.n_1700_B(input)).n_1700_B(recipeConsumer);
    }

    private static void P_1922_E(Consumer<C_2741_M> recipeConsumer, q_1803_e fence, q_1803_e input) {
        d_2427_y.n_1700_B(fence, 3).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('W'), input).n_1700_B("W#W").n_1700_B("W#W").J_1907_R("wooden_fence").n_1700_B("has_planks", x_607_J.n_1700_B(input)).n_1700_B(recipeConsumer);
    }

    private static void u_1723_Y(Consumer<C_2741_M> recipeConsumer, q_1803_e fenceGate, q_1803_e input) {
        d_2427_y.n_1700_B(fenceGate).n_1700_B(Character.valueOf('#'), Items.A_4514_U).n_1700_B(Character.valueOf('W'), input).n_1700_B("#W#").n_1700_B("#W#").J_1907_R("wooden_fence_gate").n_1700_B("has_planks", x_607_J.n_1700_B(input)).n_1700_B(recipeConsumer);
    }

    private static void v_4262_N(Consumer<C_2741_M> recipeConsumer, q_1803_e pressurePlate, q_1803_e input) {
        d_2427_y.n_1700_B(pressurePlate).n_1700_B(Character.valueOf('#'), input).n_1700_B("##").J_1907_R("wooden_pressure_plate").n_1700_B("has_planks", x_607_J.n_1700_B(input)).n_1700_B(recipeConsumer);
    }

    private static void w_1484_f(Consumer<C_2741_M> recipeConsumer, q_1803_e slab, q_1803_e input) {
        d_2427_y.n_1700_B(slab, 6).n_1700_B(Character.valueOf('#'), input).n_1700_B("###").J_1907_R("wooden_slab").n_1700_B("has_planks", x_607_J.n_1700_B(input)).n_1700_B(recipeConsumer);
    }

    private static void t_148_a(Consumer<C_2741_M> recipeConsumer, q_1803_e stairs, q_1803_e input) {
        d_2427_y.n_1700_B(stairs, 4).n_1700_B(Character.valueOf('#'), input).n_1700_B("#  ").n_1700_B("## ").n_1700_B("###").J_1907_R("wooden_stairs").n_1700_B("has_planks", x_607_J.n_1700_B(input)).n_1700_B(recipeConsumer);
    }

    private static void s_956_w(Consumer<C_2741_M> recipeConsumer, q_1803_e trapdoor, q_1803_e input) {
        d_2427_y.n_1700_B(trapdoor, 2).n_1700_B(Character.valueOf('#'), input).n_1700_B("###").n_1700_B("###").J_1907_R("wooden_trapdoor").n_1700_B("has_planks", x_607_J.n_1700_B(input)).n_1700_B(recipeConsumer);
    }

    private static void u_2550_I(Consumer<C_2741_M> recipeConsumer, q_1803_e sign, q_1803_e input) {
        String s = V_3137_a.e_2887_G.J_1907_R(input.u_1723_Y()).J_1907_R();
        d_2427_y.n_1700_B(sign, 3).J_1907_R("sign").n_1700_B(Character.valueOf('#'), input).n_1700_B(Character.valueOf('X'), Items.A_4514_U).n_1700_B("###").n_1700_B("###").n_1700_B(" X ").n_1700_B("has_" + s, x_607_J.n_1700_B(input)).n_1700_B(recipeConsumer);
    }

    private static void M_588_G(Consumer<C_2741_M> recipeConsumer, q_1803_e coloredWool, q_1803_e dye) {
        z_1737_N.n_1700_B(coloredWool).J_1907_R(dye).J_1907_R(a_3742_W.R_3077_Z).n_1700_B("wool").n_1700_B("has_white_wool", x_607_J.n_1700_B((q_1803_e)a_3742_W.R_3077_Z)).n_1700_B(recipeConsumer);
    }

    private static void P_4830_p(Consumer<C_2741_M> recipeConsumer, q_1803_e carpet, q_1803_e input) {
        String s = V_3137_a.e_2887_G.J_1907_R(input.u_1723_Y()).J_1907_R();
        d_2427_y.n_1700_B(carpet, 3).n_1700_B(Character.valueOf('#'), input).n_1700_B("##").J_1907_R("carpet").n_1700_B("has_" + s, x_607_J.n_1700_B(input)).n_1700_B(recipeConsumer);
    }

    private static void h_1847_R(Consumer<C_2741_M> recipeConsumer, q_1803_e coloredCarpet, q_1803_e dye) {
        String s = V_3137_a.e_2887_G.J_1907_R(coloredCarpet.u_1723_Y()).J_1907_R();
        String s1 = V_3137_a.e_2887_G.J_1907_R(dye.u_1723_Y()).J_1907_R();
        d_2427_y.n_1700_B(coloredCarpet, 8).n_1700_B(Character.valueOf('#'), a_3742_W.AuctionHelper).n_1700_B(Character.valueOf('$'), dye).n_1700_B("###").n_1700_B("#$#").n_1700_B("###").J_1907_R("carpet").n_1700_B("has_white_carpet", x_607_J.n_1700_B((q_1803_e)a_3742_W.AuctionHelper)).n_1700_B("has_" + s1, x_607_J.n_1700_B(dye)).n_1700_B(recipeConsumer, s + "_from_white_carpet");
    }

    private static void Q_4569_t(Consumer<C_2741_M> recipeConsumer, q_1803_e bed, q_1803_e wool) {
        String s = V_3137_a.e_2887_G.J_1907_R(wool.u_1723_Y()).J_1907_R();
        d_2427_y.n_1700_B(bed).n_1700_B(Character.valueOf('#'), wool).n_1700_B(Character.valueOf('X'), ItemTags.R_4764_Y).n_1700_B("###").n_1700_B("XXX").J_1907_R("bed").n_1700_B("has_" + s, x_607_J.n_1700_B(wool)).n_1700_B(recipeConsumer);
    }

    private static void M_182_A(Consumer<C_2741_M> recipeConsumer, q_1803_e coloredBed, q_1803_e dye) {
        String s = V_3137_a.e_2887_G.J_1907_R(coloredBed.u_1723_Y()).J_1907_R();
        z_1737_N.n_1700_B(coloredBed).J_1907_R(Items.m_3168_q).J_1907_R(dye).n_1700_B("dyed_bed").n_1700_B("has_bed", x_607_J.n_1700_B(Items.m_3168_q)).n_1700_B(recipeConsumer, s + "_from_white_bed");
    }

    private static void t_1786_h(Consumer<C_2741_M> recipeConsumer, q_1803_e banner, q_1803_e input) {
        String s = V_3137_a.e_2887_G.J_1907_R(input.u_1723_Y()).J_1907_R();
        d_2427_y.n_1700_B(banner).n_1700_B(Character.valueOf('#'), input).n_1700_B(Character.valueOf('|'), Items.A_4514_U).n_1700_B("###").n_1700_B("###").n_1700_B(" | ").J_1907_R("banner").n_1700_B("has_" + s, x_607_J.n_1700_B(input)).n_1700_B(recipeConsumer);
    }

    private static void multiplayerClientSuggestionProvider(Consumer<C_2741_M> recipeConsumer, q_1803_e coloredGlass, q_1803_e dye) {
        d_2427_y.n_1700_B(coloredGlass, 8).n_1700_B(Character.valueOf('#'), a_3742_W.e_1992_r).n_1700_B(Character.valueOf('X'), dye).n_1700_B("###").n_1700_B("#X#").n_1700_B("###").J_1907_R("stained_glass").n_1700_B("has_glass", x_607_J.n_1700_B((q_1803_e)a_3742_W.e_1992_r)).n_1700_B(recipeConsumer);
    }

    private static void w_1457_N(Consumer<C_2741_M> recipeConsumer, q_1803_e pane, q_1803_e glass) {
        d_2427_y.n_1700_B(pane, 16).n_1700_B(Character.valueOf('#'), glass).n_1700_B("###").n_1700_B("###").J_1907_R("stained_glass_pane").n_1700_B("has_glass", x_607_J.n_1700_B(glass)).n_1700_B(recipeConsumer);
    }

    private static void Y_601_j(Consumer<C_2741_M> recipeConsumer, q_1803_e coloredPane, q_1803_e dye) {
        String s = V_3137_a.e_2887_G.J_1907_R(coloredPane.u_1723_Y()).J_1907_R();
        String s1 = V_3137_a.e_2887_G.J_1907_R(dye.u_1723_Y()).J_1907_R();
        d_2427_y.n_1700_B(coloredPane, 8).n_1700_B(Character.valueOf('#'), a_3742_W.q_839_y).n_1700_B(Character.valueOf('$'), dye).n_1700_B("###").n_1700_B("#$#").n_1700_B("###").J_1907_R("stained_glass_pane").n_1700_B("has_glass_pane", x_607_J.n_1700_B((q_1803_e)a_3742_W.q_839_y)).n_1700_B("has_" + s1, x_607_J.n_1700_B(dye)).n_1700_B(recipeConsumer, s + "_from_glass_pane");
    }

    private static void Y_259_p(Consumer<C_2741_M> recipeConsumer, q_1803_e coloredTerracotta, q_1803_e dye) {
        d_2427_y.n_1700_B(coloredTerracotta, 8).n_1700_B(Character.valueOf('#'), a_3742_W.InventoryPlus).n_1700_B(Character.valueOf('X'), dye).n_1700_B("###").n_1700_B("#X#").n_1700_B("###").J_1907_R("stained_terracotta").n_1700_B("has_terracotta", x_607_J.n_1700_B((q_1803_e)a_3742_W.InventoryPlus)).n_1700_B(recipeConsumer);
    }

    private static void Q_2552_b(Consumer<C_2741_M> recipeConsumer, q_1803_e coloredConcretePowder, q_1803_e dye) {
        z_1737_N.n_1700_B(coloredConcretePowder, 8).J_1907_R(dye).J_1907_R(a_3742_W.A_4115_X, 4).J_1907_R(a_3742_W.t_4043_B, 4).n_1700_B("concrete_powder").n_1700_B("has_sand", x_607_J.n_1700_B((q_1803_e)a_3742_W.A_4115_X)).n_1700_B("has_gravel", x_607_J.n_1700_B((q_1803_e)a_3742_W.t_4043_B)).n_1700_B(recipeConsumer);
    }

    private static void n_1700_B(Consumer<C_2741_M> recipeConsumer, String recipeConsumerIn, e_2674_c<?> cookingMethod, int serializerIn) {
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(Items.h_2396_v), Items.x_2711_Y, 0.35f, serializerIn, cookingMethod).n_1700_B("has_beef", x_607_J.n_1700_B(Items.h_2396_v)).n_1700_B(recipeConsumer, "cooked_beef_from_" + recipeConsumerIn);
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(Items.w_2892_f), Items.m_3052_r, 0.35f, serializerIn, cookingMethod).n_1700_B("has_chicken", x_607_J.n_1700_B(Items.w_2892_f)).n_1700_B(recipeConsumer, "cooked_chicken_from_" + recipeConsumerIn);
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(Items.ServerAdvancementManager), Items.U_3554_Q, 0.35f, serializerIn, cookingMethod).n_1700_B("has_cod", x_607_J.n_1700_B(Items.ServerAdvancementManager)).n_1700_B(recipeConsumer, "cooked_cod_from_" + recipeConsumerIn);
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(a_3742_W.R_1796_s), Items.MinMaxBounds, 0.1f, serializerIn, cookingMethod).n_1700_B("has_kelp", x_607_J.n_1700_B((q_1803_e)a_3742_W.R_1796_s)).n_1700_B(recipeConsumer, "dried_kelp_from_" + recipeConsumerIn);
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(Items.C_3304_p), Items.T_4001_f, 0.35f, serializerIn, cookingMethod).n_1700_B("has_salmon", x_607_J.n_1700_B(Items.C_3304_p)).n_1700_B(recipeConsumer, "cooked_salmon_from_" + recipeConsumerIn);
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(Items.s_3698_N), Items.o_869_X, 0.35f, serializerIn, cookingMethod).n_1700_B("has_mutton", x_607_J.n_1700_B(Items.s_3698_N)).n_1700_B(recipeConsumer, "cooked_mutton_from_" + recipeConsumerIn);
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(Items.j_1654_T), Items.l_3729_r, 0.35f, serializerIn, cookingMethod).n_1700_B("has_porkchop", x_607_J.n_1700_B(Items.j_1654_T)).n_1700_B(recipeConsumer, "cooked_porkchop_from_" + recipeConsumerIn);
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(Items.l_683_e), Items.DirectionalBlock, 0.35f, serializerIn, cookingMethod).n_1700_B("has_potato", x_607_J.n_1700_B(Items.l_683_e)).n_1700_B(recipeConsumer, "baked_potato_from_" + recipeConsumerIn);
        P_4830_p.n_1700_B(b_3278_X.n_1700_B(Items.FungusBlock), Items.A_3138_X, 0.35f, serializerIn, cookingMethod).n_1700_B("has_rabbit", x_607_J.n_1700_B(Items.FungusBlock)).n_1700_B(recipeConsumer, "cooked_rabbit_from_" + recipeConsumerIn);
    }

    private static V_3982_O.n_1700_B n_1700_B(T_2915_h block) {
        return new V_3982_O.n_1700_B(b_1430_k.n_1700_B.n_1700_B, block, r_2687_x.n_1700_B);
    }

    private static P_2068_y.n_1700_B n_1700_B(q_1803_e item) {
        return x_607_J.n_1700_B(w_4866_k.n_1700_B.n_1700_B().n_1700_B(item).J_1907_R());
    }

    private static P_2068_y.n_1700_B n_1700_B(r_109_r<q_1613_l> tag) {
        return x_607_J.n_1700_B(w_4866_k.n_1700_B.n_1700_B().n_1700_B(tag).J_1907_R());
    }

    private static P_2068_y.n_1700_B n_1700_B(w_4866_k ... predicate) {
        return new P_2068_y.n_1700_B(b_1430_k.n_1700_B.n_1700_B, MinMaxBounds.G_564_y.P_1922_E, MinMaxBounds.G_564_y.P_1922_E, MinMaxBounds.G_564_y.P_1922_E, predicate);
    }

    @Override
    public String n_1700_B() {
        return "Recipes";
    }
}




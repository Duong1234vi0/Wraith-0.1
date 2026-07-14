/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.primitives.Doubles
 *  com.google.common.primitives.Floats
 *  com.mojang.brigadier.ParseResults
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.suggestion.Suggestions
 *  io.netty.util.concurrent.Future
 *  io.netty.util.concurrent.GenericFutureListener
 *  it.unimi.dsi.fastutil.ints.Int2ShortMap
 *  it.unimi.dsi.fastutil.ints.Int2ShortOpenHashMap
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.StringUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.suggestion.Suggestions;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import it.unimi.dsi.fastutil.ints.Int2ShortMap;
import it.unimi.dsi.fastutil.ints.Int2ShortOpenHashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.A_2629_w;
import lightning.product.A_4313_D;
import lightning.product.ServerboundClientInformationPacket;
import lightning.product.B_3217_H;
import lightning.product.B_4088_l;
import lightning.product.D_4024_W;
import lightning.product.D_4338_T;
import lightning.product.ServerboundInteractPacket;
import lightning.product.F_1464_b;
import lightning.product.F_2904_S;
import lightning.product.F_551_J;
import lightning.product.ServerboundResourcePackPacket;
import lightning.product.BlockHitResult;
import lightning.product.ServerGamePacketListener;
import lightning.product.StringTag;
import lightning.product.H_1420_X;
import lightning.product.H_1468_N;
import lightning.product.H_2543_D;
import lightning.product.MutableComponent;
import lightning.product.H_4075_o;
import lightning.product.I_14_v;
import lightning.product.I_4817_s;
import lightning.product.I_4838_g;
import lightning.product.ServerboundTeleportToEntityPacket;
import lightning.product.SharedConstants;
import lightning.product.ServerboundSetJigsawBlockPacket;
import lightning.product.MobEffects;
import lightning.product.ServerboundSeenAdvancementsPacket;
import lightning.product.K_3710_b;
import lightning.product.K_4074_S;
import lightning.product.ServerboundSetStructureBlockPacket;
import lightning.product.L_4122_s;
import lightning.product.L_461_d;
import lightning.product.ClientboundCommandSuggestionsPacket;
import lightning.product.N_3268_u;
import lightning.product.N_4263_v;
import lightning.product.ServerboundRecipeBookChangeSettingsPacket;
import lightning.product.ClientboundBlockUpdatePacket;
import lightning.product.O_3036_q;
import lightning.product.ClientboundTagQueryPacket;
import lightning.product.P_1520_s;
import lightning.product.ServerboundContainerClickPacket;
import lightning.product.P_4526_H;
import lightning.product.NonNullList;
import lightning.product.T_1316_M;
import lightning.product.T_1368_k;
import lightning.product.T_3558_p;
import lightning.product.T_3952_j;
import lightning.product.T_4830_s;
import lightning.product.U_2534_D;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.V_182_a;
import lightning.product.PlayerRideableJumping;
import lightning.product.V_674_I;
import lightning.product.W_3491_f;
import lightning.product.W_4328_U;
import lightning.product.Y_408_h;
import lightning.product.Z_1993_T;
import lightning.product.Z_3504_M;
import lightning.product.AnvilMenu;
import lightning.product.Z_390_O;
import lightning.product.ServerboundPaddleBoatPacket;
import lightning.product.ServerboundPlayerAbilitiesPacket;
import lightning.product.ServerboundSetCommandMinecartPacket;
import lightning.product.a_2900_S;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.a_4764_N;
import lightning.product.a_9_q;
import lightning.product.ClientboundPlayerPositionPacket;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.c_1633_k;
import lightning.product.d_1428_k;
import lightning.product.TextFilter;
import lightning.product.d_742_e;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.BeaconMenu;
import lightning.product.g_1462_f;
import lightning.product.g_2336_b;
import lightning.product.g_4418_P;
import lightning.product.CommandBlock;
import lightning.product.h_384_L;
import lightning.product.h_516_K;
import lightning.product.i_2154_H;
import lightning.product.i_2572_h;
import lightning.product.Recipe;
import lightning.product.j_2644_e;
import lightning.product.j_3341_s;
import lightning.product.ServerboundPlayerActionPacket;
import lightning.product.ClientboundChatPacket;
import lightning.product.ServerboundLockDifficultyPacket;
import lightning.product.m_3054_I;
import lightning.product.n_1494_c;
import lightning.product.n_2740_g;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.n_4637_L;
import lightning.product.p_1183_T;
import lightning.product.p_4692_E;
import lightning.product.q_1613_l;
import lightning.product.JigsawBlockEntity;
import lightning.product.q_2896_o;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.r_1873_a;
import lightning.product.WritableBookItem;
import lightning.product.CrashReportCategory;
import lightning.product.r_586_S;
import lightning.product.s_1395_c;
import lightning.product.ServerboundSignUpdatePacket;
import lightning.product.t_1786_h;
import lightning.product.Packet;
import lightning.product.v_1669_V;
import lightning.product.v_1937_d;
import lightning.product.w_690_m;
import lightning.product.ServerboundSetCommandBlockPacket;
import lightning.product.x_1688_C;
import lightning.product.x_2401_v;
import lightning.product.x_268_Y;
import lightning.product.x_282_a;
import lightning.product.ServerboundPlayerInputPacket;
import lightning.product.BooleanOp;
import lightning.product.z_1181_o;
import lightning.product.z_1886_T;
import lightning.product.RecipeBookMenu;
import mods.voicechat.eventforge.ForgeNetworkEvents;
import net.minecraft.server.G_564_y;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class s_4922_C
implements ServerGamePacketListener {
    private static final Logger R_4764_Y = LogManager.getLogger();
    public final c_1633_k n_1700_B;
    private final G_564_y G_564_y;
    public B_4088_l J_1907_R;
    private int P_1922_E;
    private long u_1723_Y;
    private boolean v_4262_N;
    private long w_1484_f;
    private int t_148_a;
    private int s_956_w;
    private final Int2ShortMap u_2550_I = new Int2ShortOpenHashMap();
    private double M_588_G;
    private double P_4830_p;
    private double h_1847_R;
    private double Q_4569_t;
    private double M_182_A;
    private double t_1786_h;
    private N_4263_v multiplayerClientSuggestionProvider;
    private double w_1457_N;
    private double Y_601_j;
    private double Y_259_p;
    private double Q_2552_b;
    private double C_2741_M;
    private double k_2293_S;
    private e_2866_D q_2307_F;
    private int Z_875_P;
    private int c_3005_b;
    private boolean H_2857_Y;
    private int A_4115_X;
    private boolean Y_1740_V;
    private int t_4043_B;
    private int x_607_J;
    private int e_4240_b;

    public s_4922_C(G_564_y server, c_1633_k networkManagerIn, B_4088_l playerIn) {
        this.G_564_y = server;
        this.n_1700_B = networkManagerIn;
        networkManagerIn.n_1700_B(this);
        this.J_1907_R = playerIn;
        playerIn.n_1700_B = this;
        TextFilter ichatfilter = playerIn.g_2268_R();
        if (ichatfilter != null) {
            ichatfilter.n_1700_B();
        }
    }

    public void n_1700_B() {
        this.J_1907_R();
        this.J_1907_R.r_715_M = this.J_1907_R.O_3598_v();
        this.J_1907_R.A_1038_p = this.J_1907_R.X_2960_b();
        this.J_1907_R.i_1637_u = this.J_1907_R.l_2647_k();
        this.J_1907_R.h_1847_R();
        this.J_1907_R.n_1700_B(this.M_588_G, this.P_4830_p, this.h_1847_R, this.J_1907_R.p_178_J, this.J_1907_R.f_4016_n);
        ++this.P_1922_E;
        this.e_4240_b = this.x_607_J;
        if (this.H_2857_Y && !this.J_1907_R.z_2372_L()) {
            if (++this.A_4115_X > 80) {
                R_4764_Y.warn("{} was kicked for floating too long!", (Object)this.J_1907_R.O_1309_Q().getString());
                this.n_1700_B(new F_2904_S("multiplayer.disconnect.flying"));
                return;
            }
        } else {
            this.H_2857_Y = false;
            this.A_4115_X = 0;
        }
        this.multiplayerClientSuggestionProvider = this.J_1907_R.d_3244_b();
        if (this.multiplayerClientSuggestionProvider != this.J_1907_R && this.multiplayerClientSuggestionProvider.n_3864_h() == this.J_1907_R) {
            this.w_1457_N = this.multiplayerClientSuggestionProvider.O_3598_v();
            this.Y_601_j = this.multiplayerClientSuggestionProvider.X_2960_b();
            this.Y_259_p = this.multiplayerClientSuggestionProvider.l_2647_k();
            this.Q_2552_b = this.multiplayerClientSuggestionProvider.O_3598_v();
            this.C_2741_M = this.multiplayerClientSuggestionProvider.X_2960_b();
            this.k_2293_S = this.multiplayerClientSuggestionProvider.l_2647_k();
            if (this.Y_1740_V && this.J_1907_R.d_3244_b().n_3864_h() == this.J_1907_R) {
                if (++this.t_4043_B > 80) {
                    R_4764_Y.warn("{} was kicked for floating a vehicle too long!", (Object)this.J_1907_R.O_1309_Q().getString());
                    this.n_1700_B(new F_2904_S("multiplayer.disconnect.flying"));
                    return;
                }
            } else {
                this.Y_1740_V = false;
                this.t_4043_B = 0;
            }
        } else {
            this.multiplayerClientSuggestionProvider = null;
            this.Y_1740_V = false;
            this.t_4043_B = 0;
        }
        this.G_564_y.LongRunningTask().n_1700_B("keepAlive");
        long i = j_3341_s.J_1907_R();
        if (i - this.u_1723_Y >= 15000L) {
            if (this.v_4262_N) {
                this.n_1700_B(new F_2904_S("disconnect.timeout"));
            } else {
                this.v_4262_N = true;
                this.u_1723_Y = i;
                this.w_1484_f = i;
                this.n_1700_B(new r_1873_a(this.w_1484_f));
            }
        }
        this.G_564_y.LongRunningTask().R_4764_Y();
        if (this.t_148_a > 0) {
            --this.t_148_a;
        }
        if (this.s_956_w > 0) {
            --this.s_956_w;
        }
        if (this.J_1907_R.q_4610_l() > 0L && this.G_564_y.t_4219_U() > 0 && j_3341_s.J_1907_R() - this.J_1907_R.q_4610_l() > (long)(this.G_564_y.t_4219_U() * 1000 * 60)) {
            this.n_1700_B(new F_2904_S("multiplayer.disconnect.idling"));
        }
    }

    public void J_1907_R() {
        this.M_588_G = this.J_1907_R.O_3598_v();
        this.P_4830_p = this.J_1907_R.X_2960_b();
        this.h_1847_R = this.J_1907_R.l_2647_k();
        this.Q_4569_t = this.J_1907_R.O_3598_v();
        this.M_182_A = this.J_1907_R.X_2960_b();
        this.t_1786_h = this.J_1907_R.l_2647_k();
    }

    @Override
    public c_1633_k getNetworkManager() {
        return this.n_1700_B;
    }

    @Override
    public t_1786_h getBotNetwork() {
        return null;
    }

    private boolean R_4764_Y() {
        return this.G_564_y.J_1907_R(this.J_1907_R.y_4642_Y());
    }

    public void n_1700_B(x_282_a textComponent) {
        this.n_1700_B.n_1700_B(new w_690_m(textComponent), (GenericFutureListener<? extends Future<? super Void>>)((GenericFutureListener)p_210161_2_ -> this.n_1700_B.n_1700_B(textComponent)));
        this.n_1700_B.s_956_w();
        this.G_564_y.v_4262_N(this.n_1700_B::u_2550_I);
    }

    private <T> void n_1700_B(T p_244533_1_, Consumer<T> p_244533_2_, BiFunction<TextFilter, T, CompletableFuture<Optional<T>>> p_244533_3_) {
        G_564_y threadtaskexecutor = this.J_1907_R.c_3005_b().T_2506_i();
        Consumer<Object> consumer = p_244545_2_ -> {
            if (this.getNetworkManager().u_1723_Y()) {
                p_244533_2_.accept(p_244545_2_);
            } else {
                R_4764_Y.debug("Ignoring packet due to disconnection");
            }
        };
        TextFilter ichatfilter = this.J_1907_R.g_2268_R();
        if (ichatfilter != null) {
            p_244533_3_.apply(ichatfilter, (TextFilter)p_244533_1_).thenAcceptAsync(p_244539_1_ -> p_244539_1_.ifPresent(consumer), (Executor)threadtaskexecutor);
        } else {
            threadtaskexecutor.execute(() -> consumer.accept(p_244533_1_));
        }
    }

    private void n_1700_B(String p_244535_1_, Consumer<String> p_244535_2_) {
        this.n_1700_B(p_244535_1_, p_244535_2_, TextFilter::n_1700_B);
    }

    private void n_1700_B(List<String> p_244537_1_, Consumer<List<String>> p_244537_2_) {
        this.n_1700_B(p_244537_1_, p_244537_2_, TextFilter::n_1700_B);
    }

    @Override
    public void n_1700_B(ServerboundPlayerInputPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        this.J_1907_R.n_1700_B(packetIn.J_1907_R(), packetIn.R_4764_Y(), packetIn.G_564_y(), packetIn.P_1922_E());
    }

    private static boolean J_1907_R(N_3268_u packetIn) {
        if (Doubles.isFinite((double)packetIn.n_1700_B(0.0)) && Doubles.isFinite((double)packetIn.J_1907_R(0.0)) && Doubles.isFinite((double)packetIn.R_4764_Y(0.0)) && Floats.isFinite((float)packetIn.J_1907_R(0.0f)) && Floats.isFinite((float)packetIn.n_1700_B(0.0f))) {
            return Math.abs(packetIn.n_1700_B(0.0)) > 3.0E7 || Math.abs(packetIn.J_1907_R(0.0)) > 3.0E7 || Math.abs(packetIn.R_4764_Y(0.0)) > 3.0E7;
        }
        return true;
    }

    private static boolean J_1907_R(L_4122_s packetIn) {
        return !Doubles.isFinite((double)packetIn.J_1907_R()) || !Doubles.isFinite((double)packetIn.R_4764_Y()) || !Doubles.isFinite((double)packetIn.G_564_y()) || !Floats.isFinite((float)packetIn.u_1723_Y()) || !Floats.isFinite((float)packetIn.P_1922_E());
    }

    @Override
    public void n_1700_B(L_4122_s packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        if (s_4922_C.J_1907_R(packetIn)) {
            this.n_1700_B(new F_2904_S("multiplayer.disconnect.invalid_vehicle_movement"));
        } else {
            N_4263_v entity = this.J_1907_R.d_3244_b();
            if (entity != this.J_1907_R && entity.n_3864_h() == this.J_1907_R && entity == this.multiplayerClientSuggestionProvider) {
                e_3591_l serverworld = this.J_1907_R.c_3005_b();
                double d0 = entity.O_3598_v();
                double d1 = entity.X_2960_b();
                double d2 = entity.l_2647_k();
                double d3 = packetIn.J_1907_R();
                double d4 = packetIn.R_4764_Y();
                double d5 = packetIn.G_564_y();
                float f = packetIn.P_1922_E();
                float f1 = packetIn.u_1723_Y();
                double d6 = d3 - this.w_1457_N;
                double d7 = d4 - this.Y_601_j;
                double d8 = d5 - this.Y_259_p;
                double d10 = d6 * d6 + d7 * d7 + d8 * d8;
                double d9 = entity.I_4348_c().v_4262_N();
                if (d10 - d9 > 100.0 && !this.R_4764_Y()) {
                    R_4764_Y.warn("{} (vehicle of {}) moved too quickly! {},{},{}", (Object)entity.O_1309_Q().getString(), (Object)this.J_1907_R.O_1309_Q().getString(), (Object)d6, (Object)d7, (Object)d8);
                    this.n_1700_B.n_1700_B(new F_551_J(entity));
                    return;
                }
                boolean flag = serverworld.a_(entity, entity.i_601_W().shrink(0.0625));
                d6 = d3 - this.Q_2552_b;
                d7 = d4 - this.C_2741_M - 1.0E-6;
                d8 = d5 - this.k_2293_S;
                entity.n_1700_B(L_461_d.J_1907_R, new e_2866_D(d6, d7, d8));
                d6 = d3 - entity.O_3598_v();
                d7 = d4 - entity.X_2960_b();
                if (d7 > -0.5 || d7 < 0.5) {
                    d7 = 0.0;
                }
                d8 = d5 - entity.l_2647_k();
                d10 = d6 * d6 + d7 * d7 + d8 * d8;
                boolean flag1 = false;
                if (d10 > 0.0625) {
                    flag1 = true;
                    R_4764_Y.warn("{} (vehicle of {}) moved wrongly! {}", (Object)entity.O_1309_Q().getString(), (Object)this.J_1907_R.O_1309_Q().getString(), (Object)Math.sqrt(d10));
                }
                entity.n_1700_B(d3, d4, d5, f, f1);
                boolean flag2 = serverworld.a_(entity, entity.i_601_W().shrink(0.0625));
                if (flag && (flag1 || !flag2)) {
                    entity.n_1700_B(d0, d1, d2, f, f1);
                    this.n_1700_B.n_1700_B(new F_551_J(entity));
                    return;
                }
                this.J_1907_R.c_3005_b().Y_259_p().n_1700_B(this.J_1907_R);
                this.J_1907_R.M_182_A(this.J_1907_R.O_3598_v() - d0, this.J_1907_R.X_2960_b() - d1, this.J_1907_R.l_2647_k() - d2);
                this.Y_1740_V = d7 >= -0.03125 && !this.G_564_y.D_4792_h() && this.n_1700_B(entity);
                this.Q_2552_b = entity.O_3598_v();
                this.C_2741_M = entity.X_2960_b();
                this.k_2293_S = entity.l_2647_k();
            }
        }
    }

    private boolean n_1700_B(N_4263_v p_241162_1_) {
        return p_241162_1_.O_508_d.n_1700_B(p_241162_1_.i_601_W().grow(0.0625).expand(0.0, -0.55, 0.0)).allMatch(q_4293_E.n_1700_B::v_4262_N);
    }

    @Override
    public void n_1700_B(x_2401_v packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        if (packetIn.J_1907_R() == this.Z_875_P) {
            this.J_1907_R.n_1700_B(this.q_2307_F.J_1907_R, this.q_2307_F.R_4764_Y, this.q_2307_F.G_564_y, this.J_1907_R.p_178_J, this.J_1907_R.f_4016_n);
            this.Q_4569_t = this.q_2307_F.J_1907_R;
            this.M_182_A = this.q_2307_F.R_4764_Y;
            this.t_1786_h = this.q_2307_F.G_564_y;
            if (this.J_1907_R.g_221_o()) {
                this.J_1907_R.e_2887_G();
            }
            this.q_2307_F = null;
        }
    }

    @Override
    public void n_1700_B(z_1181_o packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        this.G_564_y.ValueObject().n_1700_B(packetIn.J_1907_R()).ifPresent(this.J_1907_R.d_2427_y()::P_1922_E);
    }

    @Override
    public void n_1700_B(ServerboundRecipeBookChangeSettingsPacket p_241831_1_) {
        v_1937_d.n_1700_B(p_241831_1_, this, this.J_1907_R.c_3005_b());
        this.J_1907_R.d_2427_y().n_1700_B(p_241831_1_.J_1907_R(), p_241831_1_.R_4764_Y(), p_241831_1_.G_564_y());
    }

    @Override
    public void n_1700_B(ServerboundSeenAdvancementsPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        if (packetIn.R_4764_Y() == ServerboundSeenAdvancementsPacket.n_1700_B.n_1700_B) {
            g_2336_b resourcelocation = packetIn.G_564_y();
            A_2629_w advancement = this.G_564_y.RealmsWorldOptions().n_1700_B(resourcelocation);
            if (advancement != null) {
                this.J_1907_R.g_164_R().n_1700_B(advancement);
            }
        }
    }

    @Override
    public void n_1700_B(I_4838_g packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        StringReader stringreader = new StringReader(packetIn.R_4764_Y());
        if (stringreader.canRead() && stringreader.peek() == '/') {
            stringreader.skip();
        }
        ParseResults parseresults = this.G_564_y.H_1083_k().n_1700_B().parse(stringreader, (Object)this.J_1907_R.A_3244_K());
        this.G_564_y.H_1083_k().n_1700_B().getCompletionSuggestions(parseresults).thenAccept(p_195519_2_ -> this.n_1700_B.n_1700_B(new ClientboundCommandSuggestionsPacket(packetIn.J_1907_R(), (Suggestions)p_195519_2_)));
    }

    @Override
    public void n_1700_B(ServerboundSetCommandBlockPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        if (!this.G_564_y.s_2632_s()) {
            this.J_1907_R.n_1700_B((x_282_a)new F_2904_S("advMode.notEnabled"), j_3341_s.J_1907_R);
        } else if (!this.J_1907_R.ModuleManager()) {
            this.J_1907_R.n_1700_B((x_282_a)new F_2904_S("advMode.notAllowed"), j_3341_s.J_1907_R);
        } else {
            d_742_e commandblocklogic = null;
            T_1368_k commandblocktileentity = null;
            c_1514_x blockpos = packetIn.J_1907_R();
            i_2154_H tileentity = this.J_1907_R.O_508_d.getTileEntity(blockpos);
            if (tileentity instanceof T_1368_k) {
                commandblocktileentity = (T_1368_k)tileentity;
                commandblocklogic = commandblocktileentity.P_1922_E();
            }
            String s = packetIn.R_4764_Y();
            boolean flag = packetIn.G_564_y();
            if (commandblocklogic != null) {
                T_1368_k.n_1700_B commandblocktileentity$mode = commandblocktileentity.h_1847_R();
                b_257_Y direction = this.J_1907_R.O_508_d.getBlockState(blockpos).R_4764_Y(CommandBlock.P_4830_p);
                switch (packetIn.v_4262_N()) {
                    case n_1700_B: {
                        K_4074_S blockstate1 = a_3742_W.ItemsCooldown.multiplayerClientSuggestionProvider();
                        this.J_1907_R.O_508_d.n_1700_B(blockpos, (K_4074_S)((K_4074_S)blockstate1.n_1700_B(CommandBlock.P_4830_p, direction)).n_1700_B(CommandBlock.h_1847_R, packetIn.P_1922_E()), 2);
                        break;
                    }
                    case J_1907_R: {
                        K_4074_S blockstate = a_3742_W.ItemScroller.multiplayerClientSuggestionProvider();
                        this.J_1907_R.O_508_d.n_1700_B(blockpos, (K_4074_S)((K_4074_S)blockstate.n_1700_B(CommandBlock.P_4830_p, direction)).n_1700_B(CommandBlock.h_1847_R, packetIn.P_1922_E()), 2);
                        break;
                    }
                    default: {
                        K_4074_S blockstate2 = a_3742_W.N_260_m.multiplayerClientSuggestionProvider();
                        this.J_1907_R.O_508_d.n_1700_B(blockpos, (K_4074_S)((K_4074_S)blockstate2.n_1700_B(CommandBlock.P_4830_p, direction)).n_1700_B(CommandBlock.h_1847_R, packetIn.P_1922_E()), 2);
                    }
                }
                tileentity.M_182_A();
                this.J_1907_R.O_508_d.n_1700_B(blockpos, tileentity);
                commandblocklogic.n_1700_B(s);
                commandblocklogic.n_1700_B(flag);
                if (!flag) {
                    commandblocklogic.J_1907_R((x_282_a)null);
                }
                commandblocktileentity.J_1907_R(packetIn.u_1723_Y());
                if (commandblocktileentity$mode != packetIn.v_4262_N()) {
                    commandblocktileentity.s_956_w();
                }
                commandblocklogic.J_1907_R();
                if (!H_1468_N.J_1907_R(s)) {
                    this.J_1907_R.n_1700_B((x_282_a)new F_2904_S("advMode.setCommand.success", s), j_3341_s.J_1907_R);
                }
            }
        }
    }

    @Override
    public void n_1700_B(ServerboundSetCommandMinecartPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        if (!this.G_564_y.s_2632_s()) {
            this.J_1907_R.n_1700_B((x_282_a)new F_2904_S("advMode.notEnabled"), j_3341_s.J_1907_R);
        } else if (!this.J_1907_R.ModuleManager()) {
            this.J_1907_R.n_1700_B((x_282_a)new F_2904_S("advMode.notAllowed"), j_3341_s.J_1907_R);
        } else {
            d_742_e commandblocklogic = packetIn.n_1700_B(this.J_1907_R.O_508_d);
            if (commandblocklogic != null) {
                commandblocklogic.n_1700_B(packetIn.J_1907_R());
                commandblocklogic.n_1700_B(packetIn.R_4764_Y());
                if (!packetIn.R_4764_Y()) {
                    commandblocklogic.J_1907_R((x_282_a)null);
                }
                commandblocklogic.J_1907_R();
                this.J_1907_R.n_1700_B((x_282_a)new F_2904_S("advMode.setCommand.success", packetIn.J_1907_R()), j_3341_s.J_1907_R);
            }
        }
    }

    @Override
    public void n_1700_B(i_2572_h packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        this.J_1907_R.l_1268_F.n_1700_B(packetIn.J_1907_R());
        this.J_1907_R.n_1700_B.n_1700_B(new a_4764_N(-2, this.J_1907_R.l_1268_F.G_564_y, this.J_1907_R.l_1268_F.s_956_w(this.J_1907_R.l_1268_F.G_564_y)));
        this.J_1907_R.n_1700_B.n_1700_B(new a_4764_N(-2, packetIn.J_1907_R(), this.J_1907_R.l_1268_F.s_956_w(packetIn.J_1907_R())));
        this.J_1907_R.n_1700_B.n_1700_B(new Z_390_O(this.J_1907_R.l_1268_F.G_564_y));
    }

    @Override
    public void n_1700_B(P_1520_s packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        if (this.J_1907_R.H_1873_g instanceof AnvilMenu) {
            AnvilMenu repaircontainer = (AnvilMenu)this.J_1907_R.H_1873_g;
            String s = SharedConstants.n_1700_B(packetIn.J_1907_R());
            if (s.length() <= 35) {
                repaircontainer.n_1700_B(s);
            }
        }
    }

    @Override
    public void n_1700_B(d_1428_k packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        if (this.J_1907_R.H_1873_g instanceof BeaconMenu) {
            ((BeaconMenu)this.J_1907_R.H_1873_g).J_1907_R(packetIn.J_1907_R(), packetIn.R_4764_Y());
        }
    }

    @Override
    public void n_1700_B(ServerboundSetStructureBlockPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        if (this.J_1907_R.ModuleManager()) {
            c_1514_x blockpos = packetIn.J_1907_R();
            K_4074_S blockstate = this.J_1907_R.O_508_d.getBlockState(blockpos);
            i_2154_H tileentity = this.J_1907_R.O_508_d.getTileEntity(blockpos);
            if (tileentity instanceof j_2644_e) {
                j_2644_e structureblocktileentity = (j_2644_e)tileentity;
                structureblocktileentity.n_1700_B(packetIn.G_564_y());
                structureblocktileentity.n_1700_B(packetIn.P_1922_E());
                structureblocktileentity.n_1700_B(packetIn.u_1723_Y());
                structureblocktileentity.J_1907_R(packetIn.v_4262_N());
                structureblocktileentity.n_1700_B(packetIn.w_1484_f());
                structureblocktileentity.n_1700_B(packetIn.t_148_a());
                structureblocktileentity.J_1907_R(packetIn.s_956_w());
                structureblocktileentity.n_1700_B(packetIn.u_2550_I());
                structureblocktileentity.G_564_y(packetIn.M_588_G());
                structureblocktileentity.P_1922_E(packetIn.P_4830_p());
                structureblocktileentity.n_1700_B(packetIn.h_1847_R());
                structureblocktileentity.n_1700_B(packetIn.Q_4569_t());
                if (structureblocktileentity.w_1484_f()) {
                    String s = structureblocktileentity.P_1922_E();
                    if (packetIn.R_4764_Y() == j_2644_e.n_1700_B.J_1907_R) {
                        if (structureblocktileentity.Q_2552_b()) {
                            this.J_1907_R.n_1700_B((x_282_a)new F_2904_S("structure_block.save_success", s), false);
                        } else {
                            this.J_1907_R.n_1700_B((x_282_a)new F_2904_S("structure_block.save_failure", s), false);
                        }
                    } else if (packetIn.R_4764_Y() == j_2644_e.n_1700_B.R_4764_Y) {
                        if (!structureblocktileentity.k_2293_S()) {
                            this.J_1907_R.n_1700_B((x_282_a)new F_2904_S("structure_block.load_not_found", s), false);
                        } else if (structureblocktileentity.n_1700_B(this.J_1907_R.c_3005_b())) {
                            this.J_1907_R.n_1700_B((x_282_a)new F_2904_S("structure_block.load_success", s), false);
                        } else {
                            this.J_1907_R.n_1700_B((x_282_a)new F_2904_S("structure_block.load_prepare", s), false);
                        }
                    } else if (packetIn.R_4764_Y() == j_2644_e.n_1700_B.G_564_y) {
                        if (structureblocktileentity.Y_259_p()) {
                            this.J_1907_R.n_1700_B((x_282_a)new F_2904_S("structure_block.size_success", s), false);
                        } else {
                            this.J_1907_R.n_1700_B((x_282_a)new F_2904_S("structure_block.size_failure"), false);
                        }
                    }
                } else {
                    this.J_1907_R.n_1700_B((x_282_a)new F_2904_S("structure_block.invalid_structure_name", packetIn.P_1922_E()), false);
                }
                structureblocktileentity.J_1907_R();
                this.J_1907_R.O_508_d.n_1700_B(blockpos, blockstate, blockstate, 3);
            }
        }
    }

    @Override
    public void n_1700_B(ServerboundSetJigsawBlockPacket p_217262_1_) {
        v_1937_d.n_1700_B(p_217262_1_, this, this.J_1907_R.c_3005_b());
        if (this.J_1907_R.ModuleManager()) {
            c_1514_x blockpos = p_217262_1_.J_1907_R();
            K_4074_S blockstate = this.J_1907_R.O_508_d.getBlockState(blockpos);
            i_2154_H tileentity = this.J_1907_R.O_508_d.getTileEntity(blockpos);
            if (tileentity instanceof JigsawBlockEntity) {
                JigsawBlockEntity jigsawtileentity = (JigsawBlockEntity)tileentity;
                jigsawtileentity.n_1700_B(p_217262_1_.R_4764_Y());
                jigsawtileentity.J_1907_R(p_217262_1_.G_564_y());
                jigsawtileentity.R_4764_Y(p_217262_1_.P_1922_E());
                jigsawtileentity.n_1700_B(p_217262_1_.u_1723_Y());
                jigsawtileentity.n_1700_B(p_217262_1_.v_4262_N());
                jigsawtileentity.J_1907_R();
                this.J_1907_R.O_508_d.n_1700_B(blockpos, blockstate, blockstate, 3);
            }
        }
    }

    @Override
    public void n_1700_B(V_182_a p_230549_1_) {
        c_1514_x blockpos;
        i_2154_H tileentity;
        v_1937_d.n_1700_B(p_230549_1_, this, this.J_1907_R.c_3005_b());
        if (this.J_1907_R.ModuleManager() && (tileentity = this.J_1907_R.O_508_d.getTileEntity(blockpos = p_230549_1_.J_1907_R())) instanceof JigsawBlockEntity) {
            JigsawBlockEntity jigsawtileentity = (JigsawBlockEntity)tileentity;
            jigsawtileentity.n_1700_B(this.J_1907_R.c_3005_b(), p_230549_1_.R_4764_Y(), p_230549_1_.G_564_y());
        }
    }

    @Override
    public void n_1700_B(a_9_q packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        int i = packetIn.J_1907_R();
        a_2900_S container = this.J_1907_R.H_1873_g;
        if (container instanceof K_3710_b) {
            K_3710_b merchantcontainer = (K_3710_b)container;
            merchantcontainer.G_564_y(i);
            merchantcontainer.v_4262_N(i);
        }
    }

    @Override
    public void n_1700_B(D_4338_T packetIn) {
        U_2912_j compoundnbt;
        Z_1993_T itemstack = packetIn.J_1907_R();
        if (itemstack.J_1907_R() == Items.CropBlock && WritableBookItem.n_1700_B(compoundnbt = itemstack.Q_4569_t())) {
            ArrayList list = Lists.newArrayList();
            boolean flag = packetIn.R_4764_Y();
            if (flag) {
                list.add(compoundnbt.M_588_G("title"));
            }
            q_2896_o listnbt = compoundnbt.G_564_y("pages", 8);
            for (int i = 0; i < listnbt.size(); ++i) {
                list.add(listnbt.t_148_a(i));
            }
            int j = packetIn.G_564_y();
            if (W_3491_f.J_1907_R(j) || j == 40) {
                this.n_1700_B((List<String>)list, flag ? p_244543_2_ -> this.n_1700_B((String)p_244543_2_.get(0), p_244543_2_.subList(1, p_244543_2_.size()), j) : p_244531_2_ -> this.n_1700_B((List<String>)p_244531_2_, j));
            }
        }
    }

    private void n_1700_B(List<String> p_244536_1_, int p_244536_2_) {
        Z_1993_T itemstack = this.J_1907_R.l_1268_F.s_956_w(p_244536_2_);
        if (itemstack.J_1907_R() == Items.CropBlock) {
            q_2896_o listnbt = new q_2896_o();
            p_244536_1_.stream().map(StringTag::n_1700_B).forEach(listnbt::add);
            itemstack.n_1700_B("pages", listnbt);
        }
    }

    private void n_1700_B(String p_244534_1_, List<String> p_244534_2_, int p_244534_3_) {
        Z_1993_T itemstack = this.J_1907_R.l_1268_F.s_956_w(p_244534_3_);
        if (itemstack.J_1907_R() == Items.CropBlock) {
            Z_1993_T itemstack1 = new Z_1993_T(Items.CryingObsidianBlock);
            U_2912_j compoundnbt = itemstack.Q_4569_t();
            if (compoundnbt != null) {
                itemstack1.R_4764_Y(compoundnbt.v_4262_N());
            }
            itemstack1.n_1700_B("author", StringTag.n_1700_B(this.J_1907_R.O_1309_Q().getString()));
            itemstack1.n_1700_B("title", StringTag.n_1700_B(p_244534_1_));
            q_2896_o listnbt = new q_2896_o();
            for (String s : p_244534_2_) {
                U_2871_b itextcomponent = new U_2871_b(s);
                String s1 = x_282_a.n_1700_B.n_1700_B(itextcomponent);
                listnbt.add(StringTag.n_1700_B(s1));
            }
            itemstack1.n_1700_B("pages", listnbt);
            this.J_1907_R.l_1268_F.J_1907_R(p_244534_3_, itemstack1);
        }
    }

    @Override
    public void n_1700_B(T_4830_s packetIn) {
        N_4263_v entity;
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        if (this.J_1907_R.t_148_a(2) && (entity = this.J_1907_R.c_3005_b().J_1907_R(packetIn.R_4764_Y())) != null) {
            U_2912_j compoundnbt = entity.P_1922_E(new U_2912_j());
            this.J_1907_R.n_1700_B.n_1700_B(new ClientboundTagQueryPacket(packetIn.J_1907_R(), compoundnbt));
        }
    }

    @Override
    public void n_1700_B(H_1420_X packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        if (this.J_1907_R.t_148_a(2)) {
            i_2154_H tileentity = this.J_1907_R.c_3005_b().getTileEntity(packetIn.R_4764_Y());
            U_2912_j compoundnbt = tileentity != null ? tileentity.n_1700_B(new U_2912_j()) : null;
            this.J_1907_R.n_1700_B.n_1700_B(new ClientboundTagQueryPacket(packetIn.J_1907_R(), compoundnbt));
        }
    }

    @Override
    public void n_1700_B(N_3268_u packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        if (s_4922_C.J_1907_R(packetIn)) {
            this.n_1700_B(new F_2904_S("multiplayer.disconnect.invalid_player_movement"));
        } else {
            e_3591_l serverworld = this.J_1907_R.c_3005_b();
            if (!this.J_1907_R.u_1723_Y) {
                if (this.P_1922_E == 0) {
                    this.J_1907_R();
                }
                if (this.q_2307_F != null) {
                    if (this.P_1922_E - this.c_3005_b > 20) {
                        this.c_3005_b = this.P_1922_E;
                        this.n_1700_B(this.q_2307_F.J_1907_R, this.q_2307_F.R_4764_Y, this.q_2307_F.G_564_y, this.J_1907_R.p_178_J, this.J_1907_R.f_4016_n);
                    }
                } else {
                    this.c_3005_b = this.P_1922_E;
                    if (this.J_1907_R.y_2772_m()) {
                        this.J_1907_R.n_1700_B(this.J_1907_R.O_3598_v(), this.J_1907_R.X_2960_b(), this.J_1907_R.l_2647_k(), packetIn.n_1700_B(this.J_1907_R.p_178_J), packetIn.J_1907_R(this.J_1907_R.f_4016_n));
                        this.J_1907_R.c_3005_b().Y_259_p().n_1700_B(this.J_1907_R);
                    } else {
                        double d0 = this.J_1907_R.O_3598_v();
                        double d1 = this.J_1907_R.X_2960_b();
                        double d2 = this.J_1907_R.l_2647_k();
                        double d3 = this.J_1907_R.X_2960_b();
                        double d4 = packetIn.n_1700_B(this.J_1907_R.O_3598_v());
                        double d5 = packetIn.J_1907_R(this.J_1907_R.X_2960_b());
                        double d6 = packetIn.R_4764_Y(this.J_1907_R.l_2647_k());
                        float f = packetIn.n_1700_B(this.J_1907_R.p_178_J);
                        float f1 = packetIn.J_1907_R(this.J_1907_R.f_4016_n);
                        double d7 = d4 - this.M_588_G;
                        double d8 = d5 - this.P_4830_p;
                        double d9 = d6 - this.h_1847_R;
                        double d10 = this.J_1907_R.I_4348_c().v_4262_N();
                        double d11 = d7 * d7 + d8 * d8 + d9 * d9;
                        if (this.J_1907_R.z_2372_L()) {
                            if (d11 > 1.0) {
                                this.n_1700_B(this.J_1907_R.O_3598_v(), this.J_1907_R.X_2960_b(), this.J_1907_R.l_2647_k(), packetIn.n_1700_B(this.J_1907_R.p_178_J), packetIn.J_1907_R(this.J_1907_R.f_4016_n));
                            }
                        } else {
                            boolean flag;
                            ++this.x_607_J;
                            int i = this.x_607_J - this.e_4240_b;
                            if (i > 5) {
                                R_4764_Y.debug("{} is sending move packets too frequently ({} packets since last tick)", (Object)this.J_1907_R.O_1309_Q().getString(), (Object)i);
                                i = 1;
                            }
                            if (!(this.J_1907_R.g_221_o() || this.J_1907_R.c_3005_b().H_1990_U().J_1907_R(A_2352_Z.multiplayerClientSuggestionProvider) && this.J_1907_R.k_578_l())) {
                                float f2;
                                float f3 = f2 = this.J_1907_R.k_578_l() ? 300.0f : 100.0f;
                                if (d11 - d10 > (double)(f2 * (float)i) && !this.R_4764_Y()) {
                                    R_4764_Y.warn("{} moved too quickly! {},{},{}", (Object)this.J_1907_R.O_1309_Q().getString(), (Object)d7, (Object)d8, (Object)d9);
                                    this.n_1700_B(this.J_1907_R.O_3598_v(), this.J_1907_R.X_2960_b(), this.J_1907_R.l_2647_k(), this.J_1907_R.p_178_J, this.J_1907_R.f_4016_n);
                                    return;
                                }
                            }
                            I_4817_s axisalignedbb = this.J_1907_R.i_601_W();
                            d7 = d4 - this.Q_4569_t;
                            d8 = d5 - this.M_182_A;
                            d9 = d6 - this.t_1786_h;
                            boolean bl = flag = d8 > 0.0;
                            if (this.J_1907_R.M_1641_O() && !packetIn.J_1907_R() && flag) {
                                this.J_1907_R.e_837_t();
                            }
                            this.J_1907_R.n_1700_B(L_461_d.J_1907_R, new e_2866_D(d7, d8, d9));
                            d7 = d4 - this.J_1907_R.O_3598_v();
                            d8 = d5 - this.J_1907_R.X_2960_b();
                            if (d8 > -0.5 || d8 < 0.5) {
                                d8 = 0.0;
                            }
                            d9 = d6 - this.J_1907_R.l_2647_k();
                            d11 = d7 * d7 + d8 * d8 + d9 * d9;
                            boolean flag1 = false;
                            if (!this.J_1907_R.g_221_o() && d11 > 0.0625 && !this.J_1907_R.z_2372_L() && !this.J_1907_R.R_4764_Y.P_1922_E() && this.J_1907_R.R_4764_Y.J_1907_R() != I_14_v.P_1922_E) {
                                flag1 = true;
                                R_4764_Y.warn("{} moved wrongly!", (Object)this.J_1907_R.O_1309_Q().getString());
                            }
                            this.J_1907_R.n_1700_B(d4, d5, d6, f, f1);
                            if (this.J_1907_R.j_1564_a || this.J_1907_R.z_2372_L() || (!flag1 || !serverworld.a_(this.J_1907_R, axisalignedbb)) && !this.n_1700_B(serverworld, axisalignedbb)) {
                                this.H_2857_Y = d8 >= -0.03125 && this.J_1907_R.R_4764_Y.J_1907_R() != I_14_v.P_1922_E && !this.G_564_y.D_4792_h() && !this.J_1907_R.C_415_h.R_4764_Y && !this.J_1907_R.J_1907_R(MobEffects.q_2307_F) && !this.J_1907_R.k_578_l() && this.n_1700_B(this.J_1907_R);
                                this.J_1907_R.c_3005_b().Y_259_p().n_1700_B(this.J_1907_R);
                                this.J_1907_R.n_1700_B(this.J_1907_R.X_2960_b() - d3, packetIn.J_1907_R());
                                this.J_1907_R.u_1723_Y(packetIn.J_1907_R());
                                if (flag) {
                                    this.J_1907_R.U_1241_n = 0.0f;
                                }
                                this.J_1907_R.M_182_A(this.J_1907_R.O_3598_v() - d0, this.J_1907_R.X_2960_b() - d1, this.J_1907_R.l_2647_k() - d2);
                                this.Q_4569_t = this.J_1907_R.O_3598_v();
                                this.M_182_A = this.J_1907_R.X_2960_b();
                                this.t_1786_h = this.J_1907_R.l_2647_k();
                            } else {
                                this.n_1700_B(d0, d1, d2, f, f1);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean n_1700_B(T_1316_M p_241163_1_, I_4817_s p_241163_2_) {
        Stream<s_1395_c> stream = p_241163_1_.R_4764_Y(this.J_1907_R, this.J_1907_R.i_601_W().shrink(1.0E-5f), p_241167_0_ -> true);
        s_1395_c voxelshape = x_268_Y.n_1700_B(p_241163_2_.shrink(1.0E-5f));
        return stream.anyMatch(p_241164_1_ -> !x_268_Y.R_4764_Y(p_241164_1_, voxelshape, BooleanOp.t_148_a));
    }

    public void n_1700_B(double x, double y, double z, float yaw, float pitch) {
        this.n_1700_B(x, y, z, yaw, pitch, Collections.emptySet());
    }

    public void n_1700_B(double x, double y, double z, float yaw, float pitch, Set<ClientboundPlayerPositionPacket.n_1700_B> relativeSet) {
        double d0 = relativeSet.contains((Object)ClientboundPlayerPositionPacket.n_1700_B.n_1700_B) ? this.J_1907_R.O_3598_v() : 0.0;
        double d1 = relativeSet.contains((Object)ClientboundPlayerPositionPacket.n_1700_B.J_1907_R) ? this.J_1907_R.X_2960_b() : 0.0;
        double d2 = relativeSet.contains((Object)ClientboundPlayerPositionPacket.n_1700_B.R_4764_Y) ? this.J_1907_R.l_2647_k() : 0.0;
        float f = relativeSet.contains((Object)ClientboundPlayerPositionPacket.n_1700_B.G_564_y) ? this.J_1907_R.p_178_J : 0.0f;
        float f1 = relativeSet.contains((Object)ClientboundPlayerPositionPacket.n_1700_B.P_1922_E) ? this.J_1907_R.f_4016_n : 0.0f;
        this.q_2307_F = new e_2866_D(x, y, z);
        if (++this.Z_875_P == Integer.MAX_VALUE) {
            this.Z_875_P = 0;
        }
        this.c_3005_b = this.P_1922_E;
        this.J_1907_R.n_1700_B(x, y, z, yaw, pitch);
        this.J_1907_R.n_1700_B.n_1700_B(new ClientboundPlayerPositionPacket(x - d0, y - d1, z - d2, yaw - f, pitch - f1, relativeSet, this.Z_875_P));
    }

    @Override
    public void n_1700_B(ServerboundPlayerActionPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        c_1514_x blockpos = packetIn.J_1907_R();
        this.J_1907_R.e_4240_b();
        ServerboundPlayerActionPacket.n_1700_B cplayerdiggingpacket$action = packetIn.G_564_y();
        switch (cplayerdiggingpacket$action) {
            case v_4262_N: {
                if (!this.J_1907_R.d_2461_k()) {
                    Z_1993_T itemstack = this.J_1907_R.R_4764_Y(x_1688_C.J_1907_R);
                    this.J_1907_R.n_1700_B(x_1688_C.J_1907_R, this.J_1907_R.R_4764_Y(x_1688_C.n_1700_B));
                    this.J_1907_R.n_1700_B(x_1688_C.n_1700_B, itemstack);
                    this.J_1907_R.Y_259_p();
                }
                return;
            }
            case P_1922_E: {
                if (!this.J_1907_R.d_2461_k()) {
                    this.J_1907_R.n_1700_B(false);
                }
                return;
            }
            case G_564_y: {
                if (!this.J_1907_R.d_2461_k()) {
                    this.J_1907_R.n_1700_B(true);
                }
                return;
            }
            case u_1723_Y: {
                this.J_1907_R.g_134_G();
                return;
            }
            case n_1700_B: 
            case J_1907_R: 
            case R_4764_Y: {
                this.J_1907_R.R_4764_Y.n_1700_B(blockpos, cplayerdiggingpacket$action, packetIn.R_4764_Y(), this.G_564_y.i_1637_u());
                return;
            }
        }
        throw new IllegalArgumentException("Invalid player action");
    }

    private static boolean n_1700_B(B_4088_l p_241166_0_, Z_1993_T p_241166_1_) {
        if (p_241166_1_.n_1700_B()) {
            return false;
        }
        q_1613_l item = p_241166_1_.J_1907_R();
        return (item instanceof v_1669_V || item instanceof B_3217_H) && !p_241166_0_.p_1458_L().n_1700_B(item);
    }

    @Override
    public void n_1700_B(F_1464_b packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        e_3591_l serverworld = this.J_1907_R.c_3005_b();
        x_1688_C hand = packetIn.J_1907_R();
        Z_1993_T itemstack = this.J_1907_R.R_4764_Y(hand);
        BlockHitResult blockraytraceresult = packetIn.R_4764_Y();
        c_1514_x blockpos = blockraytraceresult.n_1700_B();
        b_257_Y direction = blockraytraceresult.J_1907_R();
        this.J_1907_R.e_4240_b();
        if (blockpos.getY() < this.G_564_y.i_1637_u()) {
            if (this.q_2307_F == null && this.J_1907_R.v_4262_N((double)blockpos.getX() + 0.5, (double)blockpos.getY() + 0.5, (double)blockpos.getZ() + 0.5) < 64.0 && serverworld.n_1700_B(this.J_1907_R, blockpos)) {
                m_3054_I actionresulttype = this.J_1907_R.R_4764_Y.n_1700_B(this.J_1907_R, serverworld, itemstack, hand, blockraytraceresult);
                if (direction == b_257_Y.J_1907_R && !actionresulttype.n_1700_B() && blockpos.getY() >= this.G_564_y.i_1637_u() - 1 && s_4922_C.n_1700_B(this.J_1907_R, itemstack)) {
                    MutableComponent itextcomponent = new F_2904_S("build.tooHigh", this.G_564_y.i_1637_u()).n_1700_B(D_4024_W.P_4830_p);
                    this.J_1907_R.n_1700_B.n_1700_B(new ClientboundChatPacket(itextcomponent, Y_408_h.R_4764_Y, j_3341_s.J_1907_R));
                } else if (actionresulttype.J_1907_R()) {
                    this.J_1907_R.n_1700_B(hand, true);
                }
            }
        } else {
            MutableComponent itextcomponent1 = new F_2904_S("build.tooHigh", this.G_564_y.i_1637_u()).n_1700_B(D_4024_W.P_4830_p);
            this.J_1907_R.n_1700_B.n_1700_B(new ClientboundChatPacket(itextcomponent1, Y_408_h.R_4764_Y, j_3341_s.J_1907_R));
        }
        this.J_1907_R.n_1700_B.n_1700_B(new ClientboundBlockUpdatePacket(serverworld, blockpos));
        this.J_1907_R.n_1700_B.n_1700_B(new ClientboundBlockUpdatePacket(serverworld, blockpos.offset(direction)));
    }

    @Override
    public void n_1700_B(Z_3504_M packetIn) {
        m_3054_I actionresulttype;
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        e_3591_l serverworld = this.J_1907_R.c_3005_b();
        x_1688_C hand = packetIn.J_1907_R();
        Z_1993_T itemstack = this.J_1907_R.R_4764_Y(hand);
        this.J_1907_R.e_4240_b();
        if (!itemstack.n_1700_B() && (actionresulttype = this.J_1907_R.R_4764_Y.n_1700_B(this.J_1907_R, serverworld, itemstack, hand)).J_1907_R()) {
            this.J_1907_R.n_1700_B(hand, true);
        }
    }

    @Override
    public void n_1700_B(ServerboundTeleportToEntityPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        if (this.J_1907_R.d_2461_k()) {
            for (e_3591_l serverworld : this.G_564_y.n_3318_d()) {
                N_4263_v entity = packetIn.n_1700_B(serverworld);
                if (entity == null) continue;
                this.J_1907_R.n_1700_B(serverworld, entity.O_3598_v(), entity.X_2960_b(), entity.l_2647_k(), entity.p_178_J, entity.f_4016_n);
                return;
            }
        }
    }

    @Override
    public void n_1700_B(ServerboundResourcePackPacket packetIn) {
    }

    @Override
    public void n_1700_B(ServerboundPaddleBoatPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        N_4263_v entity = this.J_1907_R.l_3609_d();
        if (entity instanceof g_1462_f) {
            ((g_1462_f)entity).n_1700_B(packetIn.J_1907_R(), packetIn.R_4764_Y());
        }
    }

    @Override
    public void onDisconnect(x_282_a reason) {
        R_4764_Y.info("{} lost connection: {}", (Object)this.J_1907_R.O_1309_Q().getString(), (Object)reason.getString());
        this.G_564_y.q_1982_R();
        this.G_564_y.p_178_J().n_1700_B(new F_2904_S("multiplayer.player.left", this.J_1907_R.c_()).n_1700_B(D_4024_W.Q_4569_t), Y_408_h.J_1907_R, j_3341_s.J_1907_R);
        this.J_1907_R.multiplayerClientSuggestionProvider();
        this.G_564_y.p_178_J().R_4764_Y(this.J_1907_R);
        TextFilter ichatfilter = this.J_1907_R.g_2268_R();
        if (ichatfilter != null) {
            ichatfilter.J_1907_R();
        }
        if (this.R_4764_Y()) {
            R_4764_Y.info("Stopping singleplayer server as player logged out");
            this.G_564_y.n_1700_B(false);
        }
    }

    public void n_1700_B(Packet<?> packetIn) {
        this.n_1700_B(packetIn, (GenericFutureListener<? extends Future<? super Void>>)((GenericFutureListener)null));
    }

    public void n_1700_B(Packet<?> packetIn, @Nullable GenericFutureListener<? extends Future<? super Void>> futureListeners) {
        if (packetIn instanceof ClientboundChatPacket) {
            ClientboundChatPacket schatpacket = (ClientboundChatPacket)packetIn;
            g_4418_P chatvisibility = this.J_1907_R.t_4043_B();
            if (chatvisibility == g_4418_P.R_4764_Y && schatpacket.G_564_y() != Y_408_h.R_4764_Y) {
                return;
            }
            if (chatvisibility == g_4418_P.J_1907_R && !schatpacket.R_4764_Y()) {
                return;
            }
        }
        try {
            this.n_1700_B.n_1700_B(packetIn, futureListeners);
        }
        catch (Throwable throwable) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Sending packet");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Packet being sent");
            crashreportcategory.n_1700_B("Packet class", () -> packetIn.getClass().getCanonicalName());
            throw new ReportedException(crashreport);
        }
    }

    @Override
    public void n_1700_B(p_1183_T packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        if (packetIn.J_1907_R() >= 0 && packetIn.J_1907_R() < W_3491_f.G_564_y()) {
            if (this.J_1907_R.l_1268_F.G_564_y != packetIn.J_1907_R() && this.J_1907_R.Q_2552_b() == x_1688_C.n_1700_B) {
                this.J_1907_R.Y_259_p();
            }
            this.J_1907_R.l_1268_F.G_564_y = packetIn.J_1907_R();
            this.J_1907_R.e_4240_b();
        } else {
            R_4764_Y.warn("{} tried to set an invalid carried item", (Object)this.J_1907_R.O_1309_Q().getString());
        }
    }

    @Override
    public void n_1700_B(W_4328_U packetIn) {
        String s = StringUtils.normalizeSpace((String)packetIn.J_1907_R());
        if (s.startsWith("/")) {
            v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
            this.n_1700_B(s);
        } else {
            this.n_1700_B(s, this::n_1700_B);
        }
    }

    private void n_1700_B(String p_244548_1_) {
        this.J_1907_R.e_4240_b();
        for (int i = 0; i < p_244548_1_.length(); ++i) {
            if (SharedConstants.n_1700_B(p_244548_1_.charAt(i))) continue;
            this.n_1700_B(new F_2904_S("multiplayer.disconnect.illegal_characters"));
            return;
        }
        if (p_244548_1_.startsWith("/")) {
            this.J_1907_R(p_244548_1_);
        } else {
            F_2904_S itextcomponent = new F_2904_S("chat.type.text", this.J_1907_R.c_(), p_244548_1_);
            this.G_564_y.p_178_J().n_1700_B(itextcomponent, Y_408_h.n_1700_B, this.J_1907_R.w_2705_t());
        }
        this.t_148_a += 20;
        if (this.t_148_a > 200 && !this.G_564_y.p_178_J().u_1723_Y(this.J_1907_R.y_4642_Y())) {
            this.n_1700_B(new F_2904_S("disconnect.spam"));
        }
    }

    private void J_1907_R(String command) {
        this.G_564_y.H_1083_k().n_1700_B(this.J_1907_R.A_3244_K(), command);
    }

    @Override
    public void n_1700_B(T_3558_p packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        this.J_1907_R.e_4240_b();
        this.J_1907_R.n_1700_B(packetIn.J_1907_R());
    }

    @Override
    public void n_1700_B(T_3952_j packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        this.J_1907_R.e_4240_b();
        switch (packetIn.J_1907_R()) {
            case n_1700_B: {
                this.J_1907_R.t_148_a(true);
                break;
            }
            case J_1907_R: {
                this.J_1907_R.t_148_a(false);
                break;
            }
            case G_564_y: {
                this.J_1907_R.b_(true);
                break;
            }
            case P_1922_E: {
                this.J_1907_R.b_(false);
                break;
            }
            case R_4764_Y: {
                if (!this.J_1907_R.z_2372_L()) break;
                this.J_1907_R.n_1700_B(false, true);
                this.q_2307_F = this.J_1907_R.s_4990_V();
                break;
            }
            case u_1723_Y: {
                if (!(this.J_1907_R.l_3609_d() instanceof PlayerRideableJumping)) break;
                PlayerRideableJumping ijumpingmount1 = (PlayerRideableJumping)((Object)this.J_1907_R.l_3609_d());
                int i = packetIn.R_4764_Y();
                if (!ijumpingmount1.u_1723_Y() || i <= 0) break;
                ijumpingmount1.J_1907_R(i);
                break;
            }
            case v_4262_N: {
                if (!(this.J_1907_R.l_3609_d() instanceof PlayerRideableJumping)) break;
                PlayerRideableJumping ijumpingmount = (PlayerRideableJumping)((Object)this.J_1907_R.l_3609_d());
                ijumpingmount.v_4262_N();
                break;
            }
            case w_1484_f: {
                if (!(this.J_1907_R.l_3609_d() instanceof U_2534_D)) break;
                ((U_2534_D)this.J_1907_R.l_3609_d()).u_1723_Y(this.J_1907_R);
                break;
            }
            case t_148_a: {
                if (this.J_1907_R.y_2447_C()) break;
                this.J_1907_R.o_82_k();
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid client command!");
            }
        }
    }

    @Override
    public void n_1700_B(ServerboundInteractPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        e_3591_l serverworld = this.J_1907_R.c_3005_b();
        N_4263_v entity = packetIn.n_1700_B(serverworld);
        this.J_1907_R.e_4240_b();
        this.J_1907_R.t_148_a(packetIn.P_1922_E());
        if (entity != null) {
            double d0 = 36.0;
            if (this.J_1907_R.G_564_y(entity) < 36.0) {
                x_1688_C hand = packetIn.R_4764_Y();
                Z_1993_T itemstack = hand != null ? this.J_1907_R.R_4764_Y(hand).t_148_a() : Z_1993_T.J_1907_R;
                Optional<Object> optional = Optional.empty();
                if (packetIn.J_1907_R() == ServerboundInteractPacket.n_1700_B.n_1700_B) {
                    optional = Optional.of(this.J_1907_R.n_1700_B(entity, hand));
                } else if (packetIn.J_1907_R() == ServerboundInteractPacket.n_1700_B.R_4764_Y) {
                    optional = Optional.of(entity.n_1700_B(this.J_1907_R, packetIn.G_564_y(), hand));
                } else if (packetIn.J_1907_R() == ServerboundInteractPacket.n_1700_B.J_1907_R) {
                    if (entity instanceof n_1494_c || entity instanceof n_4637_L || entity instanceof h_384_L || entity == this.J_1907_R) {
                        this.n_1700_B(new F_2904_S("multiplayer.disconnect.invalid_entity_attacked"));
                        R_4764_Y.warn("Player {} tried to attack an invalid entity", (Object)this.J_1907_R.O_1309_Q().getString());
                        return;
                    }
                    this.J_1907_R.H_2857_Y(entity);
                }
                if (optional.isPresent() && ((m_3054_I)((Object)optional.get())).n_1700_B()) {
                    U_3554_Q.z_4693_k.n_1700_B(this.J_1907_R, itemstack, entity);
                    if (((m_3054_I)((Object)optional.get())).J_1907_R()) {
                        this.J_1907_R.n_1700_B(hand, true);
                    }
                }
            }
        }
    }

    @Override
    public void n_1700_B(H_2543_D packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        this.J_1907_R.e_4240_b();
        H_2543_D.n_1700_B cclientstatuspacket$state = packetIn.J_1907_R();
        switch (cclientstatuspacket$state) {
            case n_1700_B: {
                if (this.J_1907_R.u_1723_Y) {
                    this.J_1907_R.u_1723_Y = false;
                    this.J_1907_R = this.G_564_y.p_178_J().n_1700_B(this.J_1907_R, true);
                    U_3554_Q.Q_2552_b.n_1700_B(this.J_1907_R, b_4507_u.w_1484_f, b_4507_u.u_1723_Y);
                    break;
                }
                if (this.J_1907_R.g_46_E() > 0.0f) {
                    return;
                }
                this.J_1907_R = this.G_564_y.p_178_J().n_1700_B(this.J_1907_R, false);
                if (!this.G_564_y.M_182_A()) break;
                this.J_1907_R.n_1700_B(I_14_v.P_1922_E);
                this.J_1907_R.c_3005_b().H_1990_U().n_1700_B(A_2352_Z.M_182_A).n_1700_B(false, this.G_564_y);
                break;
            }
            case J_1907_R: {
                this.J_1907_R.n_3318_d().n_1700_B(this.J_1907_R);
            }
        }
    }

    @Override
    public void n_1700_B(P_4526_H packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        this.J_1907_R.M_182_A();
    }

    @Override
    public void n_1700_B(ServerboundContainerClickPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        this.J_1907_R.e_4240_b();
        if (this.J_1907_R.H_1873_g.u_1723_Y == packetIn.J_1907_R() && this.J_1907_R.H_1873_g.R_4764_Y(this.J_1907_R)) {
            if (this.J_1907_R.d_2461_k()) {
                NonNullList<Z_1993_T> nonnulllist = NonNullList.n_1700_B();
                for (int i = 0; i < this.J_1907_R.H_1873_g.P_1922_E.size(); ++i) {
                    nonnulllist.add(this.J_1907_R.H_1873_g.P_1922_E.get(i).n_1700_B());
                }
                this.J_1907_R.n_1700_B(this.J_1907_R.H_1873_g, nonnulllist);
            } else {
                Z_1993_T itemstack1 = this.J_1907_R.H_1873_g.n_1700_B(packetIn.R_4764_Y(), packetIn.G_564_y(), packetIn.v_4262_N(), this.J_1907_R);
                if (Z_1993_T.J_1907_R(packetIn.u_1723_Y(), itemstack1)) {
                    this.J_1907_R.n_1700_B.n_1700_B(new n_2740_g(packetIn.J_1907_R(), packetIn.P_1922_E(), true));
                    this.J_1907_R.G_564_y = true;
                    this.J_1907_R.H_1873_g.M_588_G();
                    this.J_1907_R.Q_4569_t();
                    this.J_1907_R.G_564_y = false;
                } else {
                    this.u_2550_I.put(this.J_1907_R.H_1873_g.u_1723_Y, packetIn.P_1922_E());
                    this.J_1907_R.n_1700_B.n_1700_B(new n_2740_g(packetIn.J_1907_R(), packetIn.P_1922_E(), false));
                    this.J_1907_R.H_1873_g.J_1907_R((a_3913_L)this.J_1907_R, false);
                    NonNullList<Z_1993_T> nonnulllist1 = NonNullList.n_1700_B();
                    for (int j = 0; j < this.J_1907_R.H_1873_g.P_1922_E.size(); ++j) {
                        Z_1993_T itemstack = this.J_1907_R.H_1873_g.P_1922_E.get(j).n_1700_B();
                        nonnulllist1.add(itemstack.n_1700_B() ? Z_1993_T.J_1907_R : itemstack);
                    }
                    this.J_1907_R.n_1700_B(this.J_1907_R.H_1873_g, nonnulllist1);
                }
            }
        }
    }

    @Override
    public void n_1700_B(H_4075_o packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        this.J_1907_R.e_4240_b();
        if (!this.J_1907_R.d_2461_k() && this.J_1907_R.H_1873_g.u_1723_Y == packetIn.J_1907_R() && this.J_1907_R.H_1873_g.R_4764_Y(this.J_1907_R) && this.J_1907_R.H_1873_g instanceof RecipeBookMenu) {
            this.G_564_y.ValueObject().n_1700_B(packetIn.R_4764_Y()).ifPresent(p_241165_2_ -> ((RecipeBookMenu)this.J_1907_R.H_1873_g).n_1700_B(packetIn.G_564_y(), (Recipe<?>)p_241165_2_, this.J_1907_R));
        }
    }

    @Override
    public void n_1700_B(h_516_K packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        this.J_1907_R.e_4240_b();
        if (this.J_1907_R.H_1873_g.u_1723_Y == packetIn.J_1907_R() && this.J_1907_R.H_1873_g.R_4764_Y(this.J_1907_R) && !this.J_1907_R.d_2461_k()) {
            this.J_1907_R.H_1873_g.J_1907_R((a_3913_L)this.J_1907_R, packetIn.R_4764_Y());
            this.J_1907_R.H_1873_g.M_588_G();
        }
    }

    @Override
    public void n_1700_B(r_586_S packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        if (this.J_1907_R.R_4764_Y.P_1922_E()) {
            boolean flag2;
            c_1514_x blockpos;
            i_2154_H tileentity;
            boolean flag = packetIn.J_1907_R() < 0;
            Z_1993_T itemstack = packetIn.R_4764_Y();
            U_2912_j compoundnbt = itemstack.J_1907_R("BlockEntityTag");
            if (!itemstack.n_1700_B() && compoundnbt != null && compoundnbt.P_1922_E("x") && compoundnbt.P_1922_E("y") && compoundnbt.P_1922_E("z") && (tileentity = this.J_1907_R.O_508_d.getTileEntity(blockpos = new c_1514_x(compoundnbt.w_1484_f("x"), compoundnbt.w_1484_f("y"), compoundnbt.w_1484_f("z")))) != null) {
                U_2912_j compoundnbt1 = tileentity.n_1700_B(new U_2912_j());
                compoundnbt1.multiplayerClientSuggestionProvider("x");
                compoundnbt1.multiplayerClientSuggestionProvider("y");
                compoundnbt1.multiplayerClientSuggestionProvider("z");
                itemstack.n_1700_B("BlockEntityTag", compoundnbt1);
            }
            boolean flag1 = packetIn.J_1907_R() >= 1 && packetIn.J_1907_R() <= 45;
            boolean bl = flag2 = itemstack.n_1700_B() || itemstack.v_4262_N() >= 0 && itemstack.t_4043_B() <= 64 && !itemstack.n_1700_B();
            if (flag1 && flag2) {
                if (itemstack.n_1700_B()) {
                    this.J_1907_R.o_1800_r.n_1700_B(packetIn.J_1907_R(), Z_1993_T.J_1907_R);
                } else {
                    this.J_1907_R.o_1800_r.n_1700_B(packetIn.J_1907_R(), itemstack);
                }
                this.J_1907_R.o_1800_r.J_1907_R((a_3913_L)this.J_1907_R, true);
                this.J_1907_R.o_1800_r.M_588_G();
            } else if (flag && flag2 && this.s_956_w < 200) {
                this.s_956_w += 20;
                this.J_1907_R.n_1700_B(itemstack, true);
            }
        }
    }

    @Override
    public void n_1700_B(V_674_I packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        int i = this.J_1907_R.H_1873_g.u_1723_Y;
        if (i == packetIn.J_1907_R() && this.u_2550_I.getOrDefault(i, (short)(packetIn.R_4764_Y() + 1)) == packetIn.R_4764_Y() && !this.J_1907_R.H_1873_g.R_4764_Y(this.J_1907_R) && !this.J_1907_R.d_2461_k()) {
            this.J_1907_R.H_1873_g.J_1907_R((a_3913_L)this.J_1907_R, true);
        }
    }

    @Override
    public void n_1700_B(ServerboundSignUpdatePacket packetIn) {
        List<String> list = Stream.of(packetIn.R_4764_Y()).map(D_4024_W::n_1700_B).collect(Collectors.toList());
        this.n_1700_B(list, (List<String> p_244547_2_) -> this.n_1700_B(packetIn, (List<String>)p_244547_2_));
    }

    private void n_1700_B(ServerboundSignUpdatePacket p_244542_1_, List<String> p_244542_2_) {
        this.J_1907_R.e_4240_b();
        e_3591_l serverworld = this.J_1907_R.c_3005_b();
        c_1514_x blockpos = p_244542_1_.J_1907_R();
        if (serverworld.M_588_G(blockpos)) {
            K_4074_S blockstate = serverworld.getBlockState(blockpos);
            i_2154_H tileentity = serverworld.getTileEntity(blockpos);
            if (!(tileentity instanceof A_4313_D)) {
                return;
            }
            A_4313_D signtileentity = (A_4313_D)tileentity;
            if (!signtileentity.P_1922_E() || signtileentity.v_4262_N() != this.J_1907_R) {
                R_4764_Y.warn("Player {} just tried to change non-editable sign", (Object)this.J_1907_R.O_1309_Q().getString());
                return;
            }
            for (int i = 0; i < p_244542_2_.size(); ++i) {
                signtileentity.n_1700_B(i, new U_2871_b(p_244542_2_.get(i)));
            }
            signtileentity.J_1907_R();
            serverworld.n_1700_B(blockpos, blockstate, blockstate, 3);
        }
    }

    @Override
    public void n_1700_B(p_4692_E packetIn) {
        if (this.v_4262_N && packetIn.J_1907_R() == this.w_1484_f) {
            int i = (int)(j_3341_s.J_1907_R() - this.u_1723_Y);
            this.J_1907_R.P_1922_E = (this.J_1907_R.P_1922_E * 3 + i) / 4;
            this.v_4262_N = false;
        } else if (!this.R_4764_Y()) {
            this.n_1700_B(new F_2904_S("disconnect.timeout"));
        }
    }

    @Override
    public void n_1700_B(ServerboundPlayerAbilitiesPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        this.J_1907_R.C_415_h.J_1907_R = packetIn.J_1907_R() && this.J_1907_R.C_415_h.R_4764_Y;
    }

    @Override
    public void n_1700_B(ServerboundClientInformationPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.J_1907_R.c_3005_b());
        this.J_1907_R.n_1700_B(packetIn);
    }

    @Override
    public void n_1700_B(O_3036_q packetIn) {
        ForgeNetworkEvents.onCustomPayloadServer(packetIn, this.J_1907_R);
    }

    @Override
    public void n_1700_B(z_1886_T p_217263_1_) {
        v_1937_d.n_1700_B(p_217263_1_, this, this.J_1907_R.c_3005_b());
        if (this.J_1907_R.t_148_a(2) || this.R_4764_Y()) {
            this.G_564_y.n_1700_B(p_217263_1_.J_1907_R(), false);
        }
    }

    @Override
    public void n_1700_B(ServerboundLockDifficultyPacket p_217261_1_) {
        v_1937_d.n_1700_B(p_217261_1_, this, this.J_1907_R.c_3005_b());
        if (this.J_1907_R.t_148_a(2) || this.R_4764_Y()) {
            this.G_564_y.J_1907_R(p_217261_1_.J_1907_R());
        }
    }

    static {
        try {
            Class.forName("lightning.product.Globals");
        }
        catch (ClassNotFoundException classNotFoundException) {
            // empty catch block
        }
    }
}




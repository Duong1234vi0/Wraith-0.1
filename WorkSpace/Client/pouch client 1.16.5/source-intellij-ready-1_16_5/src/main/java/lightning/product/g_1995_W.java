/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.mojang.authlib.GameProfile
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  io.netty.buffer.Unpooled
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.A_1557_z;
import lightning.product.A_2352_Z;
import lightning.product.A_4115_X;
import lightning.product.B_4088_l;
import lightning.product.C_1375_J;
import lightning.product.D_38_f;
import lightning.product.D_4024_W;
import lightning.product.E_389_s;
import lightning.product.F_2904_S;
import lightning.product.G_4584_Z;
import lightning.product.BiomeManager;
import lightning.product.ClientboundChangeDifficultyPacket;
import lightning.product.H_4757_Q;
import lightning.product.I_14_v;
import lightning.product.ServerOpList;
import lightning.product.I_1965_o;
import lightning.product.I_4656_k;
import lightning.product.ClientboundLoginPacket;
import lightning.product.ClientboundGameEventPacket;
import lightning.product.K_4074_S;
import lightning.product.ClientboundRespawnPacket;
import lightning.product.N_4263_v;
import lightning.product.ClientboundPlayerAbilitiesPacket;
import lightning.product.BorderChangeListener;
import lightning.product.UserBanListEntry;
import lightning.product.R_831_p;
import lightning.product.ClientboundSetDefaultSpawnPositionPacket;
import lightning.product.LevelData;
import lightning.product.S_4998_h;
import lightning.product.Stats;
import lightning.product.T_603_v;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.W_1689_V;
import lightning.product.Objective;
import lightning.product.ClientboundSoundPacket;
import lightning.product.Y_408_h;
import lightning.product.Z_3903_F;
import lightning.product.Z_390_O;
import lightning.product.Z_4308_L;
import lightning.product.ClientboundSetExperiencePacket;
import lightning.product.IpBanList;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_2585_i;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.c_1633_k;
import lightning.product.PlayerTeam;
import lightning.product.d_338_B;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.f_2392_k;
import lightning.product.ClientboundCustomPayloadPacket;
import lightning.product.ClientboundSetPlayerTeamPacket;
import lightning.product.ServerScoreboard;
import lightning.product.j_3341_s;
import lightning.product.ClientboundChatPacket;
import lightning.product.k_2610_C;
import lightning.product.k_2895_h;
import lightning.product.l_4118_l;
import lightning.product.n_4563_y;
import lightning.product.o_3050_h;
import lightning.product.BlockTags;
import lightning.product.q_1829_g;
import lightning.product.ClientboundSetTimePacket;
import lightning.product.ClientboundUpdateMobEffectPacket;
import lightning.product.r_4097_j;
import lightning.product.s_4922_C;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.v_174_f;
import lightning.product.x_282_a;
import lightning.product.IpBanListEntry;
import mods.voicechat.eventforge.PlayerEvent;
import net.minecraft.server.G_564_y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class g_1995_W {
    public static final File n_1700_B = new File("banned-players.json");
    public static final File J_1907_R = new File("banned-ips.json");
    public static final File R_4764_Y = new File("ops.json");
    public static final File G_564_y = new File("whitelist.json");
    private static final Logger u_1723_Y = LogManager.getLogger();
    private static final SimpleDateFormat v_4262_N = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    private final G_564_y w_1484_f;
    private final List<B_4088_l> t_148_a = Lists.newArrayList();
    private final Map<UUID, B_4088_l> s_956_w = Maps.newHashMap();
    private final q_1829_g u_2550_I = new q_1829_g(n_1700_B);
    private final IpBanList M_588_G = new IpBanList(J_1907_R);
    private final ServerOpList P_4830_p = new ServerOpList(R_4764_Y);
    private final G_4584_Z h_1847_R = new G_4584_Z(G_564_y);
    private final Map<UUID, k_2895_h> Q_4569_t = Maps.newHashMap();
    private final Map<UUID, S_4998_h> M_182_A = Maps.newHashMap();
    private final Z_4308_L t_1786_h;
    private boolean multiplayerClientSuggestionProvider;
    private final r_4097_j.J_1907_R w_1457_N;
    protected final int P_1922_E;
    private int Y_601_j;
    private I_14_v Y_259_p;
    private boolean Q_2552_b;
    private int C_2741_M;

    public g_1995_W(G_564_y p_i231425_1_, r_4097_j.J_1907_R p_i231425_2_, Z_4308_L p_i231425_3_, int p_i231425_4_) {
        this.w_1484_f = p_i231425_1_;
        this.w_1457_N = p_i231425_2_;
        this.P_1922_E = p_i231425_4_;
        this.t_1786_h = p_i231425_3_;
    }

    public void n_1700_B(c_1633_k netManager, B_4088_l playerIn) {
        U_2912_j compoundnbt1;
        N_4263_v entity1;
        e_3591_l serverworld1;
        GameProfile gameprofile = playerIn.y_4642_Y();
        W_1689_V playerprofilecache = this.w_1484_f.V_1225_t();
        GameProfile gameprofile1 = playerprofilecache.n_1700_B(gameprofile.getId());
        String s = gameprofile1 == null ? gameprofile.getName() : gameprofile1.getName();
        playerprofilecache.n_1700_B(gameprofile);
        U_2912_j compoundnbt = this.J_1907_R(playerIn);
        f_2392_k<b_4507_u> registrykey = compoundnbt != null ? Z_3903_F.n_1700_B(new Dynamic((DynamicOps)l_4118_l.n_1700_B, (Object)compoundnbt.R_4764_Y("Dimension"))).resultOrPartial(arg_0 -> ((Logger)u_1723_Y).error(arg_0)).orElse(b_4507_u.u_1723_Y) : b_4507_u.u_1723_Y;
        e_3591_l serverworld = this.w_1484_f.n_1700_B(registrykey);
        if (serverworld == null) {
            u_1723_Y.warn("Unknown respawn dimension {}, defaulting to overworld", registrykey);
            serverworld1 = this.w_1484_f.x_607_J();
        } else {
            serverworld1 = serverworld;
        }
        playerIn.n_1700_B((b_4507_u)serverworld1);
        playerIn.R_4764_Y.n_1700_B((e_3591_l)playerIn.O_508_d);
        String s1 = "local";
        if (netManager.R_4764_Y() != null) {
            s1 = netManager.R_4764_Y().toString();
        }
        u_1723_Y.info("{}[{}] logged in with entity id {} at ({}, {}, {})", (Object)playerIn.O_1309_Q().getString(), (Object)s1, (Object)playerIn.j_276_v(), (Object)playerIn.O_3598_v(), (Object)playerIn.X_2960_b(), (Object)playerIn.l_2647_k());
        LevelData iworldinfo = serverworld1.k_2293_S();
        this.n_1700_B(playerIn, (B_4088_l)null, serverworld1);
        s_4922_C serverplaynethandler = new s_4922_C(this.w_1484_f, netManager, playerIn);
        A_2352_Z gamerules = serverworld1.H_1990_U();
        boolean flag = gamerules.J_1907_R(A_2352_Z.Z_875_P);
        boolean flag1 = gamerules.J_1907_R(A_2352_Z.Q_4569_t);
        serverplaynethandler.n_1700_B(new ClientboundLoginPacket(playerIn.j_276_v(), playerIn.R_4764_Y.J_1907_R(), playerIn.R_4764_Y.R_4764_Y(), BiomeManager.n_1700_B(serverworld1.n_1700_B()), iworldinfo.n_1700_B(), this.w_1484_f.e_4240_b(), this.w_1457_N, serverworld1.G_624_v(), serverworld1.g_2268_R(), this.Q_4569_t(), this.Y_601_j, flag1, !flag, serverworld1.l_1233_K(), serverworld1.j_276_v()));
        serverplaynethandler.n_1700_B(new ClientboundCustomPayloadPacket(ClientboundCustomPayloadPacket.n_1700_B, new b_2585_i(Unpooled.buffer()).n_1700_B(this.R_4764_Y().d_2427_y())));
        serverplaynethandler.n_1700_B(new ClientboundChangeDifficultyPacket(iworldinfo.u_2550_I(), iworldinfo.M_588_G()));
        serverplaynethandler.n_1700_B(new ClientboundPlayerAbilitiesPacket(playerIn.C_415_h));
        serverplaynethandler.n_1700_B(new Z_390_O(playerIn.l_1268_F.G_564_y));
        serverplaynethandler.n_1700_B(new A_1557_z(this.w_1484_f.ValueObject().J_1907_R()));
        serverplaynethandler.n_1700_B(new I_4656_k(this.w_1484_f.F_1410_V()));
        this.G_564_y(playerIn);
        playerIn.n_3318_d().R_4764_Y();
        playerIn.d_2427_y().n_1700_B(playerIn);
        this.n_1700_B(serverworld1.v_4262_N(), playerIn);
        this.w_1484_f.q_1982_R();
        F_2904_S iformattabletextcomponent = playerIn.y_4642_Y().getName().equalsIgnoreCase(s) ? new F_2904_S("multiplayer.player.joined", playerIn.c_()) : new F_2904_S("multiplayer.player.joined.renamed", playerIn.c_(), s);
        this.n_1700_B(iformattabletextcomponent.n_1700_B(D_4024_W.Q_4569_t), Y_408_h.J_1907_R, j_3341_s.J_1907_R);
        serverplaynethandler.n_1700_B(playerIn.O_3598_v(), playerIn.X_2960_b(), playerIn.l_2647_k(), playerIn.p_178_J, playerIn.f_4016_n);
        this.t_148_a.add(playerIn);
        this.s_956_w.put(playerIn.w_2705_t(), playerIn);
        this.n_1700_B(new d_338_B(d_338_B.n_1700_B.n_1700_B, playerIn));
        for (int i = 0; i < this.t_148_a.size(); ++i) {
            playerIn.n_1700_B.n_1700_B(new d_338_B(d_338_B.n_1700_B.n_1700_B, this.t_148_a.get(i)));
        }
        serverworld1.R_4764_Y(playerIn);
        this.w_1484_f.u_744_e().n_1700_B(playerIn);
        this.n_1700_B(playerIn, serverworld1);
        if (!this.w_1484_f.e_2887_G().isEmpty()) {
            playerIn.n_1700_B(this.w_1484_f.e_2887_G(), this.w_1484_f.B_1668_F());
        }
        for (k_2610_C effectinstance : playerIn.I_3457_f()) {
            serverplaynethandler.n_1700_B(new ClientboundUpdateMobEffectPacket(playerIn.j_276_v(), effectinstance));
        }
        if (compoundnbt != null && compoundnbt.R_4764_Y("RootVehicle", 10) && (entity1 = t_5_h.n_1700_B((compoundnbt1 = compoundnbt.M_182_A("RootVehicle")).M_182_A("Entity"), serverworld1, p_217885_1_ -> !serverworld1.G_564_y((N_4263_v)p_217885_1_) ? null : p_217885_1_)) != null) {
            UUID uuid = compoundnbt1.J_1907_R("Attach") ? compoundnbt1.n_1700_B("Attach") : null;
            if (entity1.w_2705_t().equals(uuid)) {
                playerIn.n_1700_B(entity1, true);
            } else {
                for (N_4263_v entity : entity1.X_290_I()) {
                    if (!entity.w_2705_t().equals(uuid)) continue;
                    playerIn.n_1700_B(entity, true);
                    break;
                }
            }
            if (!playerIn.y_2772_m()) {
                u_1723_Y.warn("Couldn't reattach entity to player");
                serverworld1.u_2550_I(entity1);
                for (N_4263_v entity2 : entity1.X_290_I()) {
                    serverworld1.u_2550_I(entity2);
                }
            }
        }
        playerIn.u_1723_Y();
        A_4115_X.n_1700_B(new PlayerEvent.PlayerLoggedInEvent(playerIn));
    }

    protected void n_1700_B(ServerScoreboard scoreboardIn, B_4088_l playerIn) {
        HashSet set = Sets.newHashSet();
        for (PlayerTeam scoreplayerteam : scoreboardIn.P_1922_E()) {
            playerIn.n_1700_B.n_1700_B(new ClientboundSetPlayerTeamPacket(scoreplayerteam, 0));
        }
        for (int i = 0; i < 19; ++i) {
            Objective scoreobjective = scoreboardIn.n_1700_B(i);
            if (scoreobjective == null || set.contains(scoreobjective)) continue;
            for (Packet<?> ipacket : scoreboardIn.u_1723_Y(scoreobjective)) {
                playerIn.n_1700_B.n_1700_B(ipacket);
            }
            set.add(scoreobjective);
        }
    }

    public void n_1700_B(e_3591_l p_212504_1_) {
        p_212504_1_.H_2857_Y().n_1700_B(new BorderChangeListener(){

            @Override
            public void n_1700_B(T_603_v border, double newSize) {
                g_1995_W.this.n_1700_B(new R_831_p(border, R_831_p.n_1700_B.n_1700_B));
            }

            @Override
            public void n_1700_B(T_603_v border, double oldSize, double newSize, long time) {
                g_1995_W.this.n_1700_B(new R_831_p(border, R_831_p.n_1700_B.J_1907_R));
            }

            @Override
            public void n_1700_B(T_603_v border, double x, double z) {
                g_1995_W.this.n_1700_B(new R_831_p(border, R_831_p.n_1700_B.R_4764_Y));
            }

            @Override
            public void n_1700_B(T_603_v border, int newTime) {
                g_1995_W.this.n_1700_B(new R_831_p(border, R_831_p.n_1700_B.P_1922_E));
            }

            @Override
            public void J_1907_R(T_603_v border, int newDistance) {
                g_1995_W.this.n_1700_B(new R_831_p(border, R_831_p.n_1700_B.u_1723_Y));
            }

            @Override
            public void J_1907_R(T_603_v border, double newAmount) {
            }

            @Override
            public void R_4764_Y(T_603_v border, double newSize) {
            }
        });
    }

    @Nullable
    public U_2912_j J_1907_R(B_4088_l playerIn) {
        U_2912_j compoundnbt1;
        U_2912_j compoundnbt = this.w_1484_f.c_132_F().t_4043_B();
        if (playerIn.O_1309_Q().getString().equals(this.w_1484_f.G_624_v()) && compoundnbt != null) {
            compoundnbt1 = compoundnbt;
            playerIn.u_1723_Y(compoundnbt);
            u_1723_Y.debug("loading single player");
        } else {
            compoundnbt1 = this.t_1786_h.J_1907_R(playerIn);
        }
        return compoundnbt1;
    }

    protected void n_1700_B(B_4088_l playerIn) {
        S_4998_h playeradvancements;
        this.t_1786_h.n_1700_B(playerIn);
        k_2895_h serverstatisticsmanager = this.Q_4569_t.get(playerIn.w_2705_t());
        if (serverstatisticsmanager != null) {
            serverstatisticsmanager.n_1700_B();
        }
        if ((playeradvancements = this.M_182_A.get(playerIn.w_2705_t())) != null) {
            playeradvancements.J_1907_R();
        }
    }

    public void R_4764_Y(B_4088_l playerIn) {
        N_4263_v entity;
        A_4115_X.n_1700_B(new PlayerEvent.PlayerLoggedOutEvent(playerIn));
        e_3591_l serverworld = playerIn.c_3005_b();
        playerIn.J_1907_R(Stats.s_956_w);
        this.n_1700_B(playerIn);
        if (playerIn.y_2772_m() && (entity = playerIn.d_3244_b()).l_697_B()) {
            u_1723_Y.debug("Removing player mount");
            playerIn.A_3959_N();
            serverworld.u_2550_I(entity);
            entity.t_4219_U = true;
            for (N_4263_v entity1 : entity.X_290_I()) {
                serverworld.u_2550_I(entity1);
                entity1.t_4219_U = true;
            }
            serverworld.u_1723_Y(playerIn.u_744_e, playerIn.r_3651_U).markDirty();
        }
        playerIn.Ping();
        serverworld.P_1922_E(playerIn);
        playerIn.g_164_R().n_1700_B();
        this.t_148_a.remove(playerIn);
        this.w_1484_f.u_744_e().J_1907_R(playerIn);
        UUID uuid = playerIn.w_2705_t();
        B_4088_l serverplayerentity = this.s_956_w.get(uuid);
        if (serverplayerentity == playerIn) {
            this.s_956_w.remove(uuid);
            this.Q_4569_t.remove(uuid);
            this.M_182_A.remove(uuid);
        }
        this.n_1700_B(new d_338_B(d_338_B.n_1700_B.P_1922_E, playerIn));
    }

    @Nullable
    public x_282_a n_1700_B(SocketAddress p_206258_1_, GameProfile p_206258_2_) {
        if (this.u_2550_I.n_1700_B(p_206258_2_)) {
            UserBanListEntry profilebanentry = (UserBanListEntry)this.u_2550_I.J_1907_R(p_206258_2_);
            F_2904_S iformattabletextcomponent1 = new F_2904_S("multiplayer.disconnect.banned.reason", profilebanentry.R_4764_Y());
            if (profilebanentry.J_1907_R() != null) {
                iformattabletextcomponent1.n_1700_B(new F_2904_S("multiplayer.disconnect.banned.expiration", v_4262_N.format(profilebanentry.J_1907_R())));
            }
            return iformattabletextcomponent1;
        }
        if (!this.R_4764_Y(p_206258_2_)) {
            return new F_2904_S("multiplayer.disconnect.not_whitelisted");
        }
        if (this.M_588_G.n_1700_B(p_206258_1_)) {
            IpBanListEntry ipbanentry = this.M_588_G.J_1907_R(p_206258_1_);
            F_2904_S iformattabletextcomponent = new F_2904_S("multiplayer.disconnect.banned_ip.reason", ipbanentry.R_4764_Y());
            if (ipbanentry.J_1907_R() != null) {
                iformattabletextcomponent.n_1700_B(new F_2904_S("multiplayer.disconnect.banned_ip.expiration", v_4262_N.format(ipbanentry.J_1907_R())));
            }
            return iformattabletextcomponent;
        }
        return this.t_148_a.size() >= this.P_1922_E && !this.G_564_y(p_206258_2_) ? new F_2904_S("multiplayer.disconnect.server_full") : null;
    }

    public B_4088_l P_1922_E(GameProfile profile) {
        UUID uuid = a_3913_L.n_1700_B(profile);
        ArrayList list = Lists.newArrayList();
        for (int i = 0; i < this.t_148_a.size(); ++i) {
            B_4088_l serverplayerentity = this.t_148_a.get(i);
            if (!serverplayerentity.w_2705_t().equals(uuid)) continue;
            list.add(serverplayerentity);
        }
        B_4088_l serverplayerentity2 = this.s_956_w.get(profile.getId());
        if (serverplayerentity2 != null && !list.contains(serverplayerentity2)) {
            list.add(serverplayerentity2);
        }
        for (B_4088_l serverplayerentity1 : list) {
            serverplayerentity1.n_1700_B.n_1700_B(new F_2904_S("multiplayer.disconnect.duplicate_login"));
        }
        e_3591_l serverworld = this.w_1484_f.x_607_J();
        v_174_f playerinteractionmanager = this.w_1484_f.g_221_o() ? new E_389_s(serverworld) : new v_174_f(serverworld);
        return new B_4088_l(this.w_1484_f, serverworld, profile, playerinteractionmanager);
    }

    public B_4088_l n_1700_B(B_4088_l p_232644_1_, boolean p_232644_2_) {
        this.t_148_a.remove(p_232644_1_);
        p_232644_1_.c_3005_b().P_1922_E(p_232644_1_);
        c_1514_x blockpos = p_232644_1_.X_933_l();
        float f = p_232644_1_.Z_976_R();
        boolean flag = p_232644_1_.N_2525_X();
        e_3591_l serverworld = this.w_1484_f.n_1700_B(p_232644_1_.H_1990_U());
        Optional<Object> optional = serverworld != null && blockpos != null ? a_3913_L.n_1700_B(serverworld, blockpos, f, flag, p_232644_2_) : Optional.empty();
        e_3591_l serverworld1 = serverworld != null && optional.isPresent() ? serverworld : this.w_1484_f.x_607_J();
        v_174_f playerinteractionmanager = this.w_1484_f.g_221_o() ? new E_389_s(serverworld1) : new v_174_f(serverworld1);
        B_4088_l serverplayerentity = new B_4088_l(this.w_1484_f, serverworld1, p_232644_1_.y_4642_Y(), playerinteractionmanager);
        serverplayerentity.n_1700_B = p_232644_1_.n_1700_B;
        serverplayerentity.n_1700_B(p_232644_1_, p_232644_2_);
        serverplayerentity.G_564_y(p_232644_1_.j_276_v());
        serverplayerentity.n_1700_B(p_232644_1_.d_2169_p());
        for (String s : p_232644_1_.UploadStatus()) {
            serverplayerentity.G_564_y(s);
        }
        this.n_1700_B(serverplayerentity, p_232644_1_, serverworld1);
        boolean flag2 = false;
        if (optional.isPresent()) {
            float f1;
            K_4074_S blockstate = serverworld1.getBlockState(blockpos);
            boolean flag1 = blockstate.n_1700_B(a_3742_W.WrappedMinMaxBounds);
            e_2866_D vector3d = (e_2866_D)optional.get();
            if (!blockstate.n_1700_B(BlockTags.d_2461_k) && !flag1) {
                f1 = f;
            } else {
                e_2866_D vector3d1 = e_2866_D.R_4764_Y(blockpos).G_564_y(vector3d).G_564_y();
                f1 = (float)u_530_F.u_1723_Y(u_530_F.G_564_y(vector3d1.G_564_y, vector3d1.J_1907_R) * 57.2957763671875 - 90.0);
            }
            serverplayerentity.J_1907_R(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y, f1, 0.0f);
            serverplayerentity.n_1700_B(serverworld1.g_2268_R(), blockpos, f, flag, false);
            flag2 = !p_232644_2_ && flag1;
        } else if (blockpos != null) {
            serverplayerentity.n_1700_B.n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.n_1700_B, 0.0f));
        }
        while (!serverworld1.u_1723_Y((N_4263_v)serverplayerentity) && serverplayerentity.X_2960_b() < 256.0) {
            serverplayerentity.J_1907_R(serverplayerentity.O_3598_v(), serverplayerentity.X_2960_b() + 1.0, serverplayerentity.l_2647_k());
        }
        LevelData iworldinfo = serverplayerentity.O_508_d.k_2293_S();
        serverplayerentity.n_1700_B.n_1700_B(new ClientboundRespawnPacket(serverplayerentity.O_508_d.G_624_v(), serverplayerentity.O_508_d.g_2268_R(), BiomeManager.n_1700_B(serverplayerentity.c_3005_b().n_1700_B()), serverplayerentity.R_4764_Y.J_1907_R(), serverplayerentity.R_4764_Y.R_4764_Y(), serverplayerentity.c_3005_b().l_1233_K(), serverplayerentity.c_3005_b().j_276_v(), p_232644_2_));
        serverplayerentity.n_1700_B.n_1700_B(serverplayerentity.O_3598_v(), serverplayerentity.X_2960_b(), serverplayerentity.l_2647_k(), serverplayerentity.p_178_J, serverplayerentity.f_4016_n);
        serverplayerentity.n_1700_B.n_1700_B(new ClientboundSetDefaultSpawnPositionPacket(serverworld1.A_1038_p(), serverworld1.i_1637_u()));
        serverplayerentity.n_1700_B.n_1700_B(new ClientboundChangeDifficultyPacket(iworldinfo.u_2550_I(), iworldinfo.M_588_G()));
        serverplayerentity.n_1700_B.n_1700_B(new ClientboundSetExperiencePacket(serverplayerentity.b_2312_j, serverplayerentity.s_4990_V, serverplayerentity.v_165_F));
        this.n_1700_B(serverplayerentity, serverworld1);
        this.G_564_y(serverplayerentity);
        serverworld1.G_564_y(serverplayerentity);
        this.t_148_a.add(serverplayerentity);
        this.s_956_w.put(serverplayerentity.w_2705_t(), serverplayerentity);
        serverplayerentity.u_1723_Y();
        serverplayerentity.t_1786_h(serverplayerentity.g_46_E());
        if (flag2) {
            serverplayerentity.n_1700_B.n_1700_B(new ClientboundSoundPacket(SoundEvents.SimpleCriterionTrigger, D_38_f.P_1922_E, blockpos.getX(), blockpos.getY(), blockpos.getZ(), 1.0f, 1.0f));
        }
        return serverplayerentity;
    }

    public void G_564_y(B_4088_l player) {
        GameProfile gameprofile = player.y_4642_Y();
        int i = this.w_1484_f.n_1700_B(gameprofile);
        this.n_1700_B(player, i);
    }

    public void P_1922_E() {
        if (++this.C_2741_M > 600) {
            this.n_1700_B(new d_338_B(d_338_B.n_1700_B.R_4764_Y, this.t_148_a));
            this.C_2741_M = 0;
        }
    }

    public void n_1700_B(Packet<?> packetIn) {
        for (int i = 0; i < this.t_148_a.size(); ++i) {
            this.t_148_a.get((int)i).n_1700_B.n_1700_B(packetIn);
        }
    }

    public void n_1700_B(Packet<?> p_232642_1_, f_2392_k<b_4507_u> p_232642_2_) {
        for (int i = 0; i < this.t_148_a.size(); ++i) {
            B_4088_l serverplayerentity = this.t_148_a.get(i);
            if (serverplayerentity.O_508_d.g_2268_R() != p_232642_2_) continue;
            serverplayerentity.n_1700_B.n_1700_B(p_232642_1_);
        }
    }

    public void n_1700_B(a_3913_L player, x_282_a message) {
        o_3050_h team = player.L_1362_X();
        if (team != null) {
            for (String s : team.u_1723_Y()) {
                B_4088_l serverplayerentity = this.n_1700_B(s);
                if (serverplayerentity == null || serverplayerentity == player) continue;
                serverplayerentity.n_1700_B(message, player.w_2705_t());
            }
        }
    }

    public void J_1907_R(a_3913_L player, x_282_a message) {
        o_3050_h team = player.L_1362_X();
        if (team == null) {
            this.n_1700_B(message, Y_408_h.J_1907_R, player.w_2705_t());
        } else {
            for (int i = 0; i < this.t_148_a.size(); ++i) {
                B_4088_l serverplayerentity = this.t_148_a.get(i);
                if (serverplayerentity.L_1362_X() == team) continue;
                serverplayerentity.n_1700_B(message, player.w_2705_t());
            }
        }
    }

    public String[] u_1723_Y() {
        String[] astring = new String[this.t_148_a.size()];
        for (int i = 0; i < this.t_148_a.size(); ++i) {
            astring[i] = this.t_148_a.get(i).y_4642_Y().getName();
        }
        return astring;
    }

    public q_1829_g v_4262_N() {
        return this.u_2550_I;
    }

    public IpBanList w_1484_f() {
        return this.M_588_G;
    }

    public void n_1700_B(GameProfile profile) {
        this.P_4830_p.n_1700_B(new I_1965_o(profile, this.w_1484_f.t_1786_h(), this.P_4830_p.n_1700_B(profile)));
        B_4088_l serverplayerentity = this.n_1700_B(profile.getId());
        if (serverplayerentity != null) {
            this.G_564_y(serverplayerentity);
        }
    }

    public void J_1907_R(GameProfile profile) {
        this.P_4830_p.R_4764_Y(profile);
        B_4088_l serverplayerentity = this.n_1700_B(profile.getId());
        if (serverplayerentity != null) {
            this.G_564_y(serverplayerentity);
        }
    }

    private void n_1700_B(B_4088_l player, int permLevel) {
        if (player.n_1700_B != null) {
            byte b0 = permLevel <= 0 ? (byte)24 : (permLevel >= 4 ? (byte)28 : (byte)((byte)(24 + permLevel)));
            player.n_1700_B.n_1700_B(new C_1375_J(player, b0));
        }
        this.w_1484_f.H_1083_k().n_1700_B(player);
    }

    public boolean R_4764_Y(GameProfile profile) {
        return !this.multiplayerClientSuggestionProvider || this.P_4830_p.G_564_y(profile) || this.h_1847_R.G_564_y(profile);
    }

    public boolean u_1723_Y(GameProfile profile) {
        return this.P_4830_p.G_564_y(profile) || this.w_1484_f.J_1907_R(profile) && this.w_1484_f.c_132_F().multiplayerClientSuggestionProvider() || this.Q_2552_b;
    }

    @Nullable
    public B_4088_l n_1700_B(String username) {
        for (B_4088_l serverplayerentity : this.t_148_a) {
            if (!serverplayerentity.y_4642_Y().getName().equalsIgnoreCase(username)) continue;
            return serverplayerentity;
        }
        return null;
    }

    public void n_1700_B(@Nullable a_3913_L except, double x, double y, double z, double radius, f_2392_k<b_4507_u> dimension, Packet<?> packetIn) {
        for (int i = 0; i < this.t_148_a.size(); ++i) {
            double d2;
            double d1;
            double d0;
            B_4088_l serverplayerentity = this.t_148_a.get(i);
            if (serverplayerentity == except || serverplayerentity.O_508_d.g_2268_R() != dimension || !((d0 = x - serverplayerentity.O_3598_v()) * d0 + (d1 = y - serverplayerentity.X_2960_b()) * d1 + (d2 = z - serverplayerentity.l_2647_k()) * d2 < radius * radius)) continue;
            serverplayerentity.n_1700_B.n_1700_B(packetIn);
        }
    }

    public void t_148_a() {
        for (int i = 0; i < this.t_148_a.size(); ++i) {
            this.n_1700_B(this.t_148_a.get(i));
        }
    }

    public G_4584_Z s_956_w() {
        return this.h_1847_R;
    }

    public String[] u_2550_I() {
        return this.h_1847_R.n_1700_B();
    }

    public ServerOpList M_588_G() {
        return this.P_4830_p;
    }

    public String[] P_4830_p() {
        return this.P_4830_p.n_1700_B();
    }

    public void n_1700_B() {
    }

    public void n_1700_B(B_4088_l playerIn, e_3591_l worldIn) {
        T_603_v worldborder = this.w_1484_f.x_607_J().H_2857_Y();
        playerIn.n_1700_B.n_1700_B(new R_831_p(worldborder, R_831_p.n_1700_B.G_564_y));
        playerIn.n_1700_B.n_1700_B(new ClientboundSetTimePacket(worldIn.X_933_l(), worldIn.Z_976_R(), worldIn.H_1990_U().J_1907_R(A_2352_Z.s_956_w)));
        playerIn.n_1700_B.n_1700_B(new ClientboundSetDefaultSpawnPositionPacket(worldIn.A_1038_p(), worldIn.i_1637_u()));
        if (worldIn.c_4037_x()) {
            playerIn.n_1700_B.n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.J_1907_R, 0.0f));
            playerIn.n_1700_B.n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.w_1484_f, worldIn.w_1484_f(1.0f)));
            playerIn.n_1700_B.n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.t_148_a, worldIn.u_1723_Y(1.0f)));
        }
    }

    public void P_1922_E(B_4088_l playerIn) {
        playerIn.n_1700_B(playerIn.o_1800_r);
        playerIn.k_2293_S();
        playerIn.n_1700_B.n_1700_B(new Z_390_O(playerIn.l_1268_F.G_564_y));
    }

    public int h_1847_R() {
        return this.t_148_a.size();
    }

    public int Q_4569_t() {
        return this.P_1922_E;
    }

    public boolean M_182_A() {
        return this.multiplayerClientSuggestionProvider;
    }

    public void n_1700_B(boolean whitelistEnabled) {
        this.multiplayerClientSuggestionProvider = whitelistEnabled;
    }

    public List<B_4088_l> J_1907_R(String address) {
        ArrayList list = Lists.newArrayList();
        for (B_4088_l serverplayerentity : this.t_148_a) {
            if (!serverplayerentity.A_4115_X().equals(address)) continue;
            list.add(serverplayerentity);
        }
        return list;
    }

    public int t_1786_h() {
        return this.Y_601_j;
    }

    public G_564_y R_4764_Y() {
        return this.w_1484_f;
    }

    public U_2912_j G_564_y() {
        return null;
    }

    public void n_1700_B(I_14_v gameModeIn) {
        this.Y_259_p = gameModeIn;
    }

    private void n_1700_B(B_4088_l target, @Nullable B_4088_l source, e_3591_l worldIn) {
        if (source != null) {
            target.R_4764_Y.n_1700_B(source.R_4764_Y.J_1907_R(), source.R_4764_Y.R_4764_Y());
        } else if (this.Y_259_p != null) {
            target.R_4764_Y.n_1700_B(this.Y_259_p, I_14_v.n_1700_B);
        }
        target.R_4764_Y.J_1907_R(worldIn.T_2506_i().c_132_F().t_1786_h());
    }

    public void J_1907_R(boolean p_72387_1_) {
        this.Q_2552_b = p_72387_1_;
    }

    public void multiplayerClientSuggestionProvider() {
        for (int i = 0; i < this.t_148_a.size(); ++i) {
            this.t_148_a.get((int)i).n_1700_B.n_1700_B(new F_2904_S("multiplayer.disconnect.server_shutdown"));
        }
    }

    public void n_1700_B(x_282_a p_232641_1_, Y_408_h p_232641_2_, UUID p_232641_3_) {
        this.w_1484_f.n_1700_B(p_232641_1_, p_232641_3_);
        this.n_1700_B(new ClientboundChatPacket(p_232641_1_, p_232641_2_, p_232641_3_));
    }

    public k_2895_h n_1700_B(a_3913_L playerIn) {
        k_2895_h serverstatisticsmanager;
        UUID uuid = playerIn.w_2705_t();
        k_2895_h k_2895_h2 = serverstatisticsmanager = uuid == null ? null : this.Q_4569_t.get(uuid);
        if (serverstatisticsmanager == null) {
            File file3;
            File file1 = this.w_1484_f.n_1700_B(H_4757_Q.J_1907_R).toFile();
            File file2 = new File(file1, String.valueOf(uuid) + ".json");
            if (!file2.exists() && (file3 = new File(file1, playerIn.O_1309_Q().getString() + ".json")).exists() && file3.isFile()) {
                file3.renameTo(file2);
            }
            serverstatisticsmanager = new k_2895_h(this.w_1484_f, file2);
            this.Q_4569_t.put(uuid, serverstatisticsmanager);
        }
        return serverstatisticsmanager;
    }

    public S_4998_h u_1723_Y(B_4088_l p_192054_1_) {
        UUID uuid = p_192054_1_.w_2705_t();
        S_4998_h playeradvancements = this.M_182_A.get(uuid);
        if (playeradvancements == null) {
            File file1 = this.w_1484_f.n_1700_B(H_4757_Q.n_1700_B).toFile();
            File file2 = new File(file1, String.valueOf(uuid) + ".json");
            playeradvancements = new S_4998_h(this.w_1484_f.M_1641_O(), this, this.w_1484_f.RealmsWorldOptions(), file2, p_192054_1_);
            this.M_182_A.put(uuid, playeradvancements);
        }
        playeradvancements.n_1700_B(p_192054_1_);
        return playeradvancements;
    }

    public void n_1700_B(int viewDistanceIn) {
        this.Y_601_j = viewDistanceIn;
        this.n_1700_B(new n_4563_y(viewDistanceIn));
        for (e_3591_l serverworld : this.w_1484_f.n_3318_d()) {
            if (serverworld == null) continue;
            serverworld.Y_259_p().n_1700_B(viewDistanceIn);
        }
    }

    public List<B_4088_l> w_1457_N() {
        return this.t_148_a;
    }

    @Nullable
    public B_4088_l n_1700_B(UUID playerUUID) {
        return this.s_956_w.get(playerUUID);
    }

    public boolean G_564_y(GameProfile profile) {
        return false;
    }

    public void Y_601_j() {
        for (S_4998_h playeradvancements : this.M_182_A.values()) {
            playeradvancements.n_1700_B(this.w_1484_f.RealmsWorldOptions());
        }
        this.n_1700_B(new I_4656_k(this.w_1484_f.F_1410_V()));
        A_1557_z supdaterecipespacket = new A_1557_z(this.w_1484_f.ValueObject().J_1907_R());
        for (B_4088_l serverplayerentity : this.t_148_a) {
            serverplayerentity.n_1700_B.n_1700_B(supdaterecipespacket);
            serverplayerentity.d_2427_y().n_1700_B(serverplayerentity);
        }
    }

    public boolean Y_259_p() {
        return this.Q_2552_b;
    }
}



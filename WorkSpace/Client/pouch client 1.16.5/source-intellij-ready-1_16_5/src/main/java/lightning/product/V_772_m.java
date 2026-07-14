/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 *  lombok.Generated
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.A_4313_D;
import lightning.product.A_4746_z;
import lightning.product.FluidTags;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.H_2372_h;
import lightning.product.H_2543_D;
import lightning.product.I_1170_F;
import lightning.product.I_4817_s;
import lightning.product.I_685_r;
import lightning.product.MobEffects;
import lightning.product.K_4074_S;
import lightning.product.L_4122_s;
import lightning.product.L_461_d;
import lightning.product.N_3268_u;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.P_3504_Q;
import lightning.product.P_4526_H;
import lightning.product.UnderwaterAmbientSoundHandler;
import lightning.product.T_1368_k;
import lightning.product.T_3558_p;
import lightning.product.T_3952_j;
import lightning.product.SimpleSoundInstance;
import lightning.product.PlayerRideableJumping;
import lightning.product.SoundEvents;
import lightning.product.V_4964_s;
import lightning.product.W_2853_p;
import lightning.product.W_4328_U;
import lightning.product.SoundEvent;
import lightning.product.CollisionContext;
import lightning.product.X_4340_E;
import lightning.product.Slot;
import lightning.product.SoundInstance;
import lightning.product.Z_1993_T;
import lightning.product.ServerboundPlayerAbilitiesPacket;
import lightning.product.b_257_Y;
import lightning.product.c_1070_s;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.d_2169_p;
import lightning.product.d_3514_r;
import lightning.product.d_4500_Q;
import lightning.product.d_742_e;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.StatsCounter;
import lightning.product.g_1462_f;
import lightning.product.g_1734_y;
import lightning.product.g_422_i;
import lightning.product.ElytraItem;
import lightning.product.h_1015_G;
import lightning.product.h_256_u;
import lightning.product.h_3270_j;
import lightning.product.h_3538_s;
import lightning.product.Recipe;
import lightning.product.j_2644_e;
import lightning.product.MinecartCommandBlockEditScreen;
import lightning.product.Sprint;
import lightning.product.ServerboundPlayerActionPacket;
import lightning.product.k_2603_m;
import lightning.product.k_2610_C;
import lightning.product.k_4231_L;
import lightning.product.k_4690_i;
import lightning.product.m_2262_U;
import lightning.product.n_2873_k;
import lightning.product.ClientBootstrap;
import lightning.product.o_3599_Z;
import lightning.product.p_2870_k;
import lightning.product.ElytraOnPlayerSoundInstance;
import lightning.product.q_1613_l;
import lightning.product.JigsawBlockEntity;
import lightning.product.Items;
import lightning.product.q_817_e;
import lightning.product.q_839_y;
import lightning.product.s_1395_c;
import lightning.product.ParticleTypes;
import lightning.product.t_2037_T;
import lightning.product.u_4724_w;
import lightning.product.u_530_F;
import lightning.product.BiomeAmbientSoundsHandler;
import lightning.product.Input;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;
import lightning.product.ServerboundPlayerInputPacket;
import lightning.product.y_1945_D;
import lightning.product.CommandBlockEditScreen;
import lightning.product.y_4319_k;
import lightning.product.z_1181_o;
import lightning.product.z_3427_G;
import lombok.Generated;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.event.events.ChatEvent;
import mods.baritone.api.api.java.baritone.api.event.events.PlayerUpdateEvent;
import mods.baritone.api.api.java.baritone.api.event.events.SprintStateEvent;
import mods.baritone.api.api.java.baritone.api.event.events.type.EventState;

public class V_772_m
extends X_4340_E {
    public final W_2853_p n_1700_B;
    private final StatsCounter h_1847_R;
    private final c_1070_s Q_4569_t;
    private final List<V_4964_s> M_182_A = Lists.newArrayList();
    private int t_1786_h = 0;
    private double multiplayerClientSuggestionProvider;
    private double w_1457_N;
    private double Y_601_j;
    private float Y_259_p;
    public float J_1907_R;
    private boolean Q_2552_b;
    private boolean C_2741_M;
    private boolean k_2293_S;
    public boolean R_4764_Y;
    private int q_2307_F;
    private boolean Z_875_P;
    private String c_3005_b;
    public Input G_564_y;
    protected final MinecraftClient P_1922_E;
    protected int u_1723_Y;
    public int v_4262_N;
    public float w_1484_f;
    public float t_148_a;
    public float s_956_w;
    public float u_2550_I;
    private int H_2857_Y;
    private float A_4115_X;
    public float M_588_G;
    public float P_4830_p;
    private boolean Y_1740_V;
    private x_1688_C t_4043_B;
    private boolean x_607_J;
    private boolean e_4240_b = true;
    private int n_3318_d;
    private boolean d_2427_y;
    private int z_1737_N;
    private boolean v_4276_D = true;
    private long d_2461_k = System.nanoTime();
    private final d_4500_Q M_766_z = new d_4500_Q();

    public V_772_m(MinecraftClient mc, k_4690_i world, W_2853_p connection, StatsCounter stats, c_1070_s recipeBook, boolean clientSneakState, boolean clientSprintState) {
        super(world, connection.v_4262_N());
        this.P_1922_E = mc;
        this.n_1700_B = connection;
        this.h_1847_R = stats;
        this.Q_4569_t = recipeBook;
        this.k_2293_S = clientSneakState;
        this.R_4764_Y = clientSprintState;
        this.M_182_A.add(new UnderwaterAmbientSoundHandler(this, mc.Z_976_R()));
        this.M_182_A.add(new p_2870_k(this));
        this.M_182_A.add(new BiomeAmbientSoundsHandler(this, mc.Z_976_R(), world.z_1737_N()));
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        return false;
    }

    @Override
    public void n_1700_B(float healAmount) {
    }

    @Override
    public boolean n_1700_B(N_4263_v entityIn, boolean force) {
        if (!super.n_1700_B(entityIn, force)) {
            return false;
        }
        if (entityIn instanceof y_4319_k) {
            this.P_1922_E.Z_976_R().n_1700_B((SoundInstance)new H_2372_h(this, (y_4319_k)entityIn));
        }
        if (entityIn instanceof g_1462_f) {
            this.j_276_v = entityIn.p_178_J;
            this.p_178_J = entityIn.p_178_J;
            this.h_1847_R(entityIn.p_178_J);
        }
        return true;
    }

    @Override
    public void t_() {
        super.t_();
        this.x_607_J = false;
    }

    @Override
    public float J_1907_R(float partialTicks) {
        return this.f_4016_n;
    }

    @Override
    public float R_4764_Y(float partialTicks) {
        return this.y_2772_m() ? super.R_4764_Y(partialTicks) : this.p_178_J;
    }

    @Override
    public void v_() {
        if (this.O_508_d.M_588_G(new c_1514_x(this.O_3598_v(), 0.0, this.l_2647_k()))) {
            long currentTime = System.nanoTime();
            long interval240Hz = 4166666L;
            for (long timeSinceLast = currentTime - this.d_2461_k; timeSinceLast >= interval240Hz; timeSinceLast -= interval240Hz) {
                lightning.product.A_4115_X.n_1700_B(this.M_766_z);
                this.d_2461_k += interval240Hz;
            }
            lightning.product.A_4115_X.n_1700_B(new h_1015_G());
            super.v_();
            if (this.H_1873_g != null) {
                ArrayList<Z_1993_T> list = new ArrayList<Z_1993_T>();
                for (Slot s : this.H_1873_g.P_1922_E) {
                    list.add(s.n_1700_B().t_148_a());
                }
                lightning.product.A_4115_X.n_1700_B(new o_3599_Z(this.H_1873_g.u_1723_Y, list));
            }
            if (this.y_2772_m()) {
                this.n_1700_B.n_1700_B(new N_3268_u.R_4764_Y(this.p_178_J, this.f_4016_n, this.e_1992_r));
                this.n_1700_B.n_1700_B(new ServerboundPlayerInputPacket(this.L_1362_X, this.L_4248_u, this.G_564_y.jump, this.G_564_y.sneaking));
                N_4263_v entity = this.d_3244_b();
                if (entity != this && entity.v_887_r()) {
                    this.n_1700_B.n_1700_B(new L_4122_s(entity));
                }
            } else {
                IBaritone baritone = BaritoneAPI.getProvider().getBaritoneForPlayer(this);
                if (baritone != null) {
                    baritone.getGameEventHandler().onPlayerUpdate(new PlayerUpdateEvent(EventState.PRE));
                }
                this.BooleanSetting();
                if (baritone != null) {
                    baritone.getGameEventHandler().onPlayerUpdate(new PlayerUpdateEvent(EventState.POST));
                }
            }
            for (V_4964_s iambientsoundhandler : this.M_182_A) {
                iambientsoundhandler.J_1907_R();
            }
            lightning.product.A_4115_X.n_1700_B(new g_1734_y());
        }
    }

    public float R_4764_Y() {
        for (V_4964_s iambientsoundhandler : this.M_182_A) {
            if (!(iambientsoundhandler instanceof BiomeAmbientSoundsHandler)) continue;
            return ((BiomeAmbientSoundsHandler)iambientsoundhandler).n_1700_B();
        }
        return 0.0f;
    }

    private void BooleanSetting() {
        boolean flag3;
        double posX = this.O_3598_v();
        double posY = this.X_2960_b();
        double posZ = this.l_2647_k();
        float yaw = this.p_178_J;
        float pitch = this.f_4016_n;
        boolean onGround = this.e_1992_r;
        boolean isSneaking = this.q_2307_F();
        boolean isSprinting = this.o_2341_D();
        m_2262_U event = new m_2262_U(posX, posY, posZ, yaw, pitch, onGround, isSneaking, isSprinting);
        lightning.product.A_4115_X.n_1700_B(event);
        if (event.t_148_a() != this.R_4764_Y) {
            T_3952_j.n_1700_B centityactionpacket$action = event.t_148_a() ? T_3952_j.n_1700_B.G_564_y : T_3952_j.n_1700_B.P_1922_E;
            this.n_1700_B.n_1700_B(new T_3952_j(this, centityactionpacket$action));
            this.R_4764_Y = event.t_148_a();
        }
        if ((flag3 = event.w_1484_f()) != this.k_2293_S) {
            T_3952_j.n_1700_B centityactionpacket$action1 = flag3 ? T_3952_j.n_1700_B.n_1700_B : T_3952_j.n_1700_B.J_1907_R;
            this.n_1700_B.n_1700_B(new T_3952_j(this, centityactionpacket$action1));
            this.k_2293_S = flag3;
        }
        if (this.A_4115_X()) {
            boolean flag2;
            double d4 = event.J_1907_R() - this.multiplayerClientSuggestionProvider;
            double d0 = event.R_4764_Y() - this.w_1457_N;
            double d1 = event.G_564_y() - this.Y_601_j;
            double d2 = event.P_1922_E() - this.Y_259_p;
            double d3 = event.u_1723_Y() - this.J_1907_R;
            ++this.q_2307_F;
            boolean flag1 = d4 * d4 + d0 * d0 + d1 * d1 > 9.0E-4 || this.q_2307_F >= 20;
            boolean bl = flag2 = d2 != 0.0 || d3 != 0.0;
            if (this.y_2772_m()) {
                e_2866_D vector3d = this.I_4348_c();
                this.n_1700_B.n_1700_B(new N_3268_u.J_1907_R(vector3d.J_1907_R, -999.0, vector3d.G_564_y, event.P_1922_E(), event.u_1723_Y(), event.v_4262_N()));
                flag1 = false;
            } else if (flag1 && flag2) {
                this.n_1700_B.n_1700_B(new N_3268_u.J_1907_R(event.J_1907_R(), event.R_4764_Y(), event.G_564_y(), event.P_1922_E(), event.u_1723_Y(), event.v_4262_N()));
            } else if (flag1) {
                this.n_1700_B.n_1700_B(new N_3268_u.n_1700_B(event.J_1907_R(), event.R_4764_Y(), event.G_564_y(), event.v_4262_N()));
            } else if (flag2) {
                this.n_1700_B.n_1700_B(new N_3268_u.R_4764_Y(event.P_1922_E(), event.u_1723_Y(), event.v_4262_N()));
            } else if (this.Q_2552_b != event.v_4262_N()) {
                this.n_1700_B.n_1700_B(new N_3268_u(event.v_4262_N()));
            }
            if (flag1) {
                this.multiplayerClientSuggestionProvider = event.J_1907_R();
                this.w_1457_N = event.R_4764_Y();
                this.Y_601_j = event.G_564_y();
                this.q_2307_F = 0;
            }
            if (flag2) {
                this.Y_259_p = event.P_1922_E();
                this.J_1907_R = event.u_1723_Y();
            }
            this.Q_2552_b = event.v_4262_N();
            this.e_4240_b = this.P_1922_E.P_4830_p.z_1737_N;
        }
    }

    @Override
    public boolean n_1700_B(boolean p_225609_1_) {
        ServerboundPlayerActionPacket.n_1700_B cplayerdiggingpacket$action = p_225609_1_ ? ServerboundPlayerActionPacket.n_1700_B.G_564_y : ServerboundPlayerActionPacket.n_1700_B.P_1922_E;
        this.n_1700_B.n_1700_B(new ServerboundPlayerActionPacket(cplayerdiggingpacket$action, c_1514_x.ZERO, b_257_Y.n_1700_B));
        return this.l_1268_F.n_1700_B(this.l_1268_F.G_564_y, p_225609_1_ && !this.l_1268_F.R_4764_Y().n_1700_B() ? this.l_1268_F.R_4764_Y().t_4043_B() : 1) != Z_1993_T.J_1907_R;
    }

    public void n_1700_B(String message) {
        if (((String)message).trim().equalsIgnoreCase("@self")) {
            String prefix = lightning.product.ClientBootstrap.Y_601_j().Q_4569_t().n_1700_B();
            message = prefix + "self";
        }
        if (((String)message).startsWith(lightning.product.ClientBootstrap.Y_601_j().Q_4569_t().n_1700_B())) {
            String command = ((String)message).substring(lightning.product.ClientBootstrap.Y_601_j().Q_4569_t().n_1700_B().length());
            if (!lightning.product.ClientBootstrap.Y_601_j().Q_4569_t().R_4764_Y(command)) {
                return;
            }
            if (lightning.product.ClientBootstrap.Y_601_j().Q_4569_t().J_1907_R(command)) {
                return;
            }
            try {
                lightning.product.ClientBootstrap.Y_601_j().Q_4569_t().J_1907_R().execute(command, (Object)lightning.product.ClientBootstrap.Y_601_j().Q_4569_t().G_564_y());
            }
            catch (CommandSyntaxException commandSyntaxException) {
                // empty catch block
            }
            return;
        }
        ChatEvent event = new ChatEvent((String)message);
        IBaritone baritone = BaritoneAPI.getProvider().getBaritoneForPlayer(this);
        if (baritone == null) {
            return;
        }
        baritone.getGameEventHandler().onSendChatMessage(event);
        if (event.isCancelled()) {
            return;
        }
        this.n_1700_B.n_1700_B(new W_4328_U((String)message));
    }

    @Override
    public void n_1700_B(x_1688_C hand) {
        super.n_1700_B(hand);
        this.n_1700_B.n_1700_B(new T_3558_p(hand));
    }

    @Override
    public void G_564_y() {
        this.n_1700_B.n_1700_B(new H_2543_D(H_2543_D.n_1700_B.n_1700_B));
    }

    @Override
    protected void J_1907_R(P_11_z damageSrc, float damageAmount) {
        if (!this.n_1700_B(damageSrc)) {
            this.t_1786_h(this.g_46_E() - damageAmount);
        }
    }

    @Override
    public void P_1922_E() {
        y_1945_D event = new y_1945_D(this.H_1873_g.u_1723_Y);
        lightning.product.A_4115_X.n_1700_B(event);
        if (!event.n_1700_B()) {
            this.n_1700_B.n_1700_B(new P_4526_H(this.H_1873_g.u_1723_Y));
        }
        this.u_1723_Y();
    }

    public void u_1723_Y() {
        this.l_1268_F.v_4262_N(Z_1993_T.J_1907_R);
        super.P_1922_E();
        if (!(this.P_1922_E.Y_1740_V instanceof u_4724_w)) {
            this.P_1922_E.n_1700_B((k_2603_m)null);
        }
    }

    public void G_564_y(float health) {
        if (this.Z_875_P) {
            float f = this.g_46_E() - health;
            if (f <= 0.0f) {
                this.t_1786_h(health);
                if (f < 0.0f) {
                    this.F_1410_V = 10;
                }
            } else {
                this.j_306_t = f;
                this.t_1786_h(this.g_46_E());
                this.F_1410_V = 20;
                this.J_1907_R(P_11_z.h_1847_R, f);
                this.RealmsLongRunningMcoTaskScreen = this.i_2993_w = 10;
            }
        } else {
            this.t_1786_h(health);
            this.Z_875_P = true;
        }
    }

    @Override
    public void v_4262_N() {
        this.n_1700_B.n_1700_B(new ServerboundPlayerAbilitiesPacket(this.C_415_h));
    }

    @Override
    public boolean w_1484_f() {
        return true;
    }

    @Override
    public boolean q_() {
        return !this.C_415_h.J_1907_R && super.q_();
    }

    @Override
    public boolean s_956_w() {
        return !this.C_415_h.J_1907_R && super.s_956_w();
    }

    @Override
    public boolean u_2550_I() {
        return !this.C_415_h.J_1907_R && super.u_2550_I();
    }

    protected void M_588_G() {
        this.n_1700_B.n_1700_B(new T_3952_j(this, T_3952_j.n_1700_B.u_1723_Y, u_530_F.G_564_y(this.k_2293_S() * 100.0f)));
    }

    public void P_4830_p() {
        this.n_1700_B.n_1700_B(new T_3952_j(this, T_3952_j.n_1700_B.w_1484_f));
    }

    public void J_1907_R(String brand) {
        this.c_3005_b = brand;
    }

    public String h_1847_R() {
        return this.c_3005_b;
    }

    public StatsCounter Q_4569_t() {
        return this.h_1847_R;
    }

    public c_1070_s M_182_A() {
        return this.Q_4569_t;
    }

    public void n_1700_B(Recipe<?> recipe) {
        if (this.Q_4569_t.G_564_y(recipe)) {
            this.Q_4569_t.P_1922_E(recipe);
            this.n_1700_B.n_1700_B(new z_1181_o(recipe));
        }
    }

    @Override
    protected int t_1786_h() {
        return this.t_1786_h;
    }

    public void n_1700_B(int permissionLevel) {
        this.t_1786_h = permissionLevel;
    }

    @Override
    public void n_1700_B(x_282_a chatComponent, boolean actionBar) {
        if (actionBar) {
            this.P_1922_E.M_588_G.n_1700_B(chatComponent, false);
        } else {
            this.P_1922_E.M_588_G.R_4764_Y().n_1700_B(chatComponent);
        }
    }

    private void J_1907_R(double x, double z) {
        c_1514_x blockpos = new c_1514_x(x, this.X_2960_b(), z);
        if (this.v_4262_N(blockpos)) {
            b_257_Y[] adirection;
            double d0 = x - (double)blockpos.getX();
            double d1 = z - (double)blockpos.getZ();
            b_257_Y direction = null;
            double d2 = Double.MAX_VALUE;
            for (b_257_Y direction1 : adirection = new b_257_Y[]{b_257_Y.P_1922_E, b_257_Y.u_1723_Y, b_257_Y.R_4764_Y, b_257_Y.G_564_y}) {
                double d4;
                double d3 = direction1.h_1847_R().n_1700_B(d0, 0.0, d1);
                double d = d4 = direction1.P_1922_E() == b_257_Y.J_1907_R.n_1700_B ? 1.0 - d3 : d3;
                if (!(d4 < d2) || this.v_4262_N(blockpos.offset(direction1))) continue;
                d2 = d4;
                direction = direction1;
            }
            if (direction != null) {
                e_2866_D vector3d = this.I_4348_c();
                if (direction.h_1847_R() == b_257_Y.n_1700_B.n_1700_B) {
                    this.h_1847_R(0.1 * (double)direction.t_148_a(), vector3d.R_4764_Y, vector3d.G_564_y);
                } else {
                    this.h_1847_R(vector3d.J_1907_R, vector3d.R_4764_Y, 0.1 * (double)direction.u_2550_I());
                }
            }
        }
    }

    private boolean v_4262_N(c_1514_x pos) {
        I_685_r eventNoPush = new I_685_r(I_685_r.n_1700_B.n_1700_B);
        lightning.product.A_4115_X.n_1700_B(eventNoPush);
        if (eventNoPush.n_1700_B()) {
            return false;
        }
        I_4817_s axisalignedbb = this.i_601_W();
        I_4817_s axisalignedbb1 = new I_4817_s(pos.getX(), axisalignedbb.minY, pos.getZ(), (double)pos.getX() + 1.0, axisalignedbb.maxY, (double)pos.getZ() + 1.0).shrink(1.0E-7);
        return !this.O_508_d.n_1700_B((N_4263_v)this, axisalignedbb1, (K_4074_S state, c_1514_x pos2) -> state.Q_4569_t(this.O_508_d, (c_1514_x)pos2));
    }

    @Override
    public void b_(boolean sprinting) {
        super.b_(sprinting);
        this.v_4262_N = 0;
    }

    public void n_1700_B(float currentXP, int maxXP, int level) {
        this.b_2312_j = currentXP;
        this.s_4990_V = maxXP;
        this.v_165_F = level;
    }

    @Override
    public void n_1700_B(x_282_a component, UUID senderUUID) {
        this.P_1922_E.M_588_G.R_4764_Y().n_1700_B(component);
    }

    @Override
    public void n_1700_B(byte id) {
        if (id >= 24 && id <= 28) {
            this.n_1700_B(id - 24);
        } else {
            super.n_1700_B(id);
        }
    }

    public void R_4764_Y(boolean show) {
        this.v_4276_D = show;
    }

    public boolean multiplayerClientSuggestionProvider() {
        return this.v_4276_D;
    }

    @Override
    public void n_1700_B(SoundEvent soundIn, float volume, float pitch) {
        this.O_508_d.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), soundIn, this.r_2478_U(), volume, pitch, false);
    }

    @Override
    public void n_1700_B(SoundEvent p_213823_1_, D_38_f p_213823_2_, float p_213823_3_, float p_213823_4_) {
        this.O_508_d.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), p_213823_1_, p_213823_2_, p_213823_3_, p_213823_4_, false);
    }

    @Override
    public boolean w_1457_N() {
        return true;
    }

    @Override
    public void J_1907_R(x_1688_C hand) {
        Z_1993_T itemstack = this.R_4764_Y(hand);
        if (!itemstack.n_1700_B() && !this.Y_601_j()) {
            super.J_1907_R(hand);
            this.Y_1740_V = true;
            this.t_4043_B = hand;
        }
    }

    @Override
    public boolean Y_601_j() {
        return this.Y_1740_V;
    }

    @Override
    public void Y_259_p() {
        super.Y_259_p();
        this.Y_1740_V = false;
    }

    @Override
    public x_1688_C Q_2552_b() {
        return this.t_4043_B;
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        super.n_1700_B(key);
        if (W_3464_O.equals(key)) {
            x_1688_C hand;
            boolean flag = ((Byte)this.l_4537_E.n_1700_B(W_3464_O) & 1) > 0;
            x_1688_C x_1688_C2 = hand = ((Byte)this.l_4537_E.n_1700_B(W_3464_O) & 2) > 0 ? x_1688_C.J_1907_R : x_1688_C.n_1700_B;
            if (flag && !this.Y_1740_V) {
                this.J_1907_R(hand);
            } else if (!flag && this.Y_1740_V) {
                this.Y_259_p();
            }
        }
        if (F_2624_D.equals(key) && this.k_578_l() && !this.d_2427_y) {
            this.P_1922_E.Z_976_R().n_1700_B((SoundInstance)new ElytraOnPlayerSoundInstance(this));
        }
    }

    public boolean C_2741_M() {
        N_4263_v entity = this.l_3609_d();
        return this.y_2772_m() && entity instanceof PlayerRideableJumping && ((PlayerRideableJumping)((Object)entity)).u_1723_Y();
    }

    public float k_2293_S() {
        return this.A_4115_X;
    }

    @Override
    public void n_1700_B(A_4313_D signTile) {
        this.P_1922_E.n_1700_B(new t_2037_T(signTile));
    }

    @Override
    public void n_1700_B(d_742_e commandBlock) {
        this.P_1922_E.n_1700_B(new MinecartCommandBlockEditScreen(commandBlock));
    }

    @Override
    public void n_1700_B(T_1368_k commandBlock) {
        this.P_1922_E.n_1700_B(new CommandBlockEditScreen(commandBlock));
    }

    @Override
    public void n_1700_B(j_2644_e structure) {
        this.P_1922_E.n_1700_B(new h_3538_s(structure));
    }

    @Override
    public void n_1700_B(JigsawBlockEntity p_213826_1_) {
        this.P_1922_E.n_1700_B(new d_3514_r(p_213826_1_));
    }

    @Override
    public void n_1700_B(Z_1993_T stack, x_1688_C hand) {
        q_1613_l item = stack.J_1907_R();
        if (item == Items.CropBlock) {
            this.P_1922_E.n_1700_B(new A_4746_z(this, stack, hand));
        }
    }

    @Override
    public void n_1700_B(N_4263_v entityHit) {
        this.P_1922_E.v_4262_N.n_1700_B(entityHit, ParticleTypes.v_4262_N);
    }

    @Override
    public void J_1907_R(N_4263_v entityHit) {
        this.P_1922_E.v_4262_N.n_1700_B(entityHit, ParticleTypes.multiplayerClientSuggestionProvider);
    }

    @Override
    public boolean q_2307_F() {
        return this.G_564_y != null && this.G_564_y.sneaking;
    }

    @Override
    public boolean Z_875_P() {
        return this.C_2741_M;
    }

    public boolean c_3005_b() {
        return this.Z_875_P() || this.t_1446_I();
    }

    @Override
    public void H_2857_Y() {
        super.H_2857_Y();
        if (this.A_4115_X()) {
            this.L_1362_X = this.G_564_y.moveStrafe;
            this.L_4248_u = this.G_564_y.moveForward;
            this.F_3572_x = this.G_564_y.jump;
            this.s_956_w = this.w_1484_f;
            this.u_2550_I = this.t_148_a;
            this.t_148_a = (float)((double)this.t_148_a + (double)(d_2169_p.R_4764_Y() - this.t_148_a) * 0.5);
            this.w_1484_f = (float)((double)this.w_1484_f + (double)(d_2169_p.J_1907_R() - this.w_1484_f) * 0.5);
        }
    }

    protected boolean A_4115_X() {
        return this.P_1922_E.g_2268_R() == this;
    }

    @Override
    public void Y_1740_V() {
        Z_1993_T itemstack;
        boolean flag4;
        ++this.v_4262_N;
        if (this.u_1723_Y > 0) {
            --this.u_1723_Y;
        }
        this.SoundEventRegistration();
        boolean flag = this.G_564_y.jump;
        boolean flag1 = this.G_564_y.sneaking;
        boolean flag2 = this.c_1608_O();
        this.C_2741_M = !this.C_415_h.J_1907_R && !this.C_1269_X() && this.R_4764_Y(I_1170_F.u_1723_Y) && (this.q_2307_F() || !this.z_2372_L() && !this.R_4764_Y(I_1170_F.n_1700_B));
        this.G_564_y.tickMovement(this.c_3005_b());
        this.P_1922_E.D_60_a().n_1700_B(this.G_564_y);
        if (this.Y_601_j() && !this.y_2772_m()) {
            q_817_e event = new q_817_e();
            lightning.product.A_4115_X.n_1700_B(event);
            if (!event.n_1700_B()) {
                this.G_564_y.moveStrafe *= 0.2f;
                this.G_564_y.moveForward *= 0.2f;
            }
            this.u_1723_Y = 0;
        }
        boolean flag3 = false;
        if (this.n_3318_d > 0) {
            --this.n_3318_d;
            flag3 = true;
            this.G_564_y.jump = true;
        }
        if (!this.j_1564_a) {
            this.J_1907_R(this.O_3598_v() - (double)this.C_415_h() * 0.35, this.l_2647_k() + (double)this.C_415_h() * 0.35);
            this.J_1907_R(this.O_3598_v() - (double)this.C_415_h() * 0.35, this.l_2647_k() - (double)this.C_415_h() * 0.35);
            this.J_1907_R(this.O_3598_v() + (double)this.C_415_h() * 0.35, this.l_2647_k() - (double)this.C_415_h() * 0.35);
            this.J_1907_R(this.O_3598_v() + (double)this.C_415_h() * 0.35, this.l_2647_k() + (double)this.C_415_h() * 0.35);
        }
        if (flag1) {
            this.u_1723_Y = 0;
        }
        boolean allowFlying = this.C_415_h.R_4764_Y;
        IBaritone baritone = BaritoneAPI.getProvider().getBaritoneForPlayer(this);
        if (baritone != null) {
            allowFlying = !baritone.getPathingBehavior().isPathing() && this.C_415_h.R_4764_Y;
        }
        Sprint sprint = (Sprint)lightning.product.ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Sprint.class);
        boolean ignoreHunger = false;
        boolean ignoreStun = false;
        boolean bl = flag4 = ignoreHunger || (float)this.P_2295_B().n_1700_B() > 6.0f || allowFlying;
        if (!(!this.e_1992_r && !this.z_1737_N() || flag1 || flag2 || !this.c_1608_O() || this.o_2341_D() || !flag4 || this.Y_601_j() || !ignoreStun && this.J_1907_R(MobEffects.Q_4569_t))) {
            if (this.u_1723_Y <= 0 && !this.P_1922_E.P_4830_p.RealmsClientConfig.G_564_y()) {
                this.u_1723_Y = 7;
            } else {
                this.b_(true);
            }
        }
        if (!(this.o_2341_D() || this.RowButton() && !this.z_1737_N() || !this.c_1608_O() || !flag4 || this.Y_601_j() || !ignoreStun && this.J_1907_R(MobEffects.Q_4569_t))) {
            boolean isSprintKeyDown = this.P_1922_E.P_4830_p.RealmsClientConfig.G_564_y();
            if (baritone != null) {
                SprintStateEvent event = new SprintStateEvent();
                baritone.getGameEventHandler().onPlayerSprintState(event);
                if (event.getState() != null) {
                    isSprintKeyDown = event.getState();
                }
                if (baritone != BaritoneAPI.getProvider().getPrimaryBaritone()) {
                    isSprintKeyDown = false;
                }
            }
            if (isSprintKeyDown) {
                this.b_(true);
            }
        }
        if (!(sprint == null || !sprint.w_1484_f() || this.o_2341_D() || this.RowButton() && !this.z_1737_N() || !this.c_1608_O() || !flag4 || this.Y_601_j() || !ignoreStun && this.J_1907_R(MobEffects.Q_4569_t))) {
            this.P_1922_E.Y_259_p.b_(this.P_1922_E.Y_259_p.G_564_y.forwardKeyDown);
        }
        if (this.o_2341_D()) {
            boolean flag6;
            boolean flag5 = !this.G_564_y.isMovingForward() || !flag4;
            boolean bl2 = flag6 = flag5 || this.D_60_a || this.RowButton() && !this.z_1737_N();
            if (this.C_1269_X()) {
                if (!this.e_1992_r && !this.G_564_y.sneaking && flag5 || !this.RowButton()) {
                    this.b_(false);
                }
            } else if (flag6) {
                this.b_(false);
            }
        }
        boolean flag7 = false;
        if (allowFlying) {
            if (this.P_1922_E.w_1457_N.isSpectatorMode()) {
                if (!this.C_415_h.J_1907_R) {
                    this.C_415_h.J_1907_R = true;
                    flag7 = true;
                    this.v_4262_N();
                }
            } else if (!flag && this.G_564_y.jump && !flag3) {
                if (this.o_3599_Z == 0) {
                    this.o_3599_Z = 7;
                } else if (!this.C_1269_X()) {
                    this.C_415_h.J_1907_R = !this.C_415_h.J_1907_R;
                    flag7 = true;
                    this.v_4262_N();
                    this.o_3599_Z = 0;
                }
            }
        }
        if (this.G_564_y.jump && !flag7 && !flag && !this.C_415_h.J_1907_R && !this.y_2772_m() && !this.e_() && (itemstack = this.J_1907_R(e_1174_E.P_1922_E)).J_1907_R() == Items.NyliumBlock && ElytraItem.G_564_y(itemstack) && this.y_2447_C()) {
            q_839_y event = new q_839_y();
            lightning.product.A_4115_X.n_1700_B(event);
            if (!event.n_1700_B()) {
                this.n_1700_B.n_1700_B(new T_3952_j(this, T_3952_j.n_1700_B.t_148_a));
            }
        }
        this.d_2427_y = this.k_578_l();
        if (this.RowButton() && this.G_564_y.sneaking && this.m_891_U()) {
            this.m_1621_v();
        }
        if (((N_4263_v)this).n_1700_B(FluidTags.J_1907_R)) {
            int i = this.d_2461_k() ? 10 : 1;
            this.z_1737_N = u_530_F.n_1700_B(this.z_1737_N + i, 0, 600);
        } else if (this.z_1737_N > 0) {
            ((N_4263_v)this).n_1700_B(FluidTags.J_1907_R);
            this.z_1737_N = u_530_F.n_1700_B(this.z_1737_N - 10, 0, 600);
        }
        if (this.C_415_h.J_1907_R && this.A_4115_X()) {
            int j = 0;
            if (this.G_564_y.sneaking) {
                --j;
            }
            if (this.G_564_y.jump) {
                ++j;
            }
            if (j != 0) {
                this.v_4262_N(this.I_4348_c().J_1907_R(0.0, (float)j * this.C_415_h.n_1700_B() * 3.0f, 0.0));
            }
        }
        if (this.C_2741_M()) {
            PlayerRideableJumping ijumpingmount = (PlayerRideableJumping)((Object)this.l_3609_d());
            if (this.H_2857_Y < 0) {
                ++this.H_2857_Y;
                if (this.H_2857_Y == 0) {
                    this.A_4115_X = 0.0f;
                }
            }
            if (flag && !this.G_564_y.jump) {
                this.H_2857_Y = -10;
                ijumpingmount.n_1700_B(u_530_F.G_564_y(this.k_2293_S() * 100.0f));
                this.M_588_G();
            } else if (!flag && this.G_564_y.jump) {
                this.H_2857_Y = 0;
                this.A_4115_X = 0.0f;
            } else if (flag) {
                ++this.H_2857_Y;
                this.A_4115_X = this.H_2857_Y < 10 ? (float)this.H_2857_Y * 0.1f : 0.8f + 2.0f / (float)(this.H_2857_Y - 9) * 0.1f;
            }
        } else {
            this.A_4115_X = 0.0f;
        }
        super.Y_1740_V();
        if (this.e_1992_r && this.C_415_h.J_1907_R && !this.P_1922_E.w_1457_N.isSpectatorMode()) {
            this.C_415_h.J_1907_R = false;
            this.v_4262_N();
        }
    }

    private void SoundEventRegistration() {
        this.P_4830_p = this.M_588_G;
        if (this.j_2266_I) {
            if (this.P_1922_E.Y_1740_V != null && !this.P_1922_E.Y_1740_V.isPauseScreen()) {
                if (this.P_1922_E.Y_1740_V instanceof z_3427_G) {
                    this.P_1922_E();
                }
                this.P_1922_E.n_1700_B((k_2603_m)null);
            }
            if (this.M_588_G == 0.0f) {
                this.P_1922_E.Z_976_R().n_1700_B(SimpleSoundInstance.J_1907_R(SoundEvents.v_570_f, this.RealmsWorldOptions.nextFloat() * 0.4f + 0.8f, 0.25f));
            }
            this.M_588_G += 0.0125f;
            if (this.M_588_G >= 1.0f) {
                this.M_588_G = 1.0f;
            }
            this.j_2266_I = false;
        } else if (this.J_1907_R(MobEffects.t_148_a) && this.R_4764_Y(MobEffects.t_148_a).J_1907_R() > 60) {
            h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.multiplayerClientSuggestionProvider);
            lightning.product.A_4115_X.n_1700_B(event);
            if (!event.n_1700_B()) {
                this.M_588_G += 0.006666667f;
                if (this.M_588_G > 1.0f) {
                    this.M_588_G = 1.0f;
                }
            }
        } else {
            if (this.M_588_G > 0.0f) {
                this.M_588_G -= 0.05f;
            }
            if (this.M_588_G < 0.0f) {
                this.M_588_G = 0.0f;
            }
        }
        this.U_1241_n();
    }

    @Override
    public void x_607_J() {
        super.x_607_J();
        this.x_607_J = false;
        if (this.l_3609_d() instanceof g_1462_f) {
            g_1462_f boatentity = (g_1462_f)this.l_3609_d();
            boatentity.n_1700_B(this.G_564_y.leftKeyDown, this.G_564_y.rightKeyDown, this.G_564_y.forwardKeyDown, this.G_564_y.backKeyDown);
            this.x_607_J |= this.G_564_y.leftKeyDown || this.G_564_y.rightKeyDown || this.G_564_y.forwardKeyDown || this.G_564_y.backKeyDown;
        }
    }

    @Override
    public boolean e_4240_b() {
        return this.x_607_J;
    }

    @Override
    @Nullable
    public k_2610_C n_1700_B(@Nullable g_422_i potioneffectin) {
        if (potioneffectin == MobEffects.t_148_a) {
            this.P_4830_p = 0.0f;
            this.M_588_G = 0.0f;
        }
        return super.n_1700_B(potioneffectin);
    }

    @Override
    public void n_1700_B(L_461_d typeIn, e_2866_D pos) {
        double d0 = this.O_3598_v();
        double d1 = this.l_2647_k();
        super.n_1700_B(typeIn, pos);
        this.n_1700_B((float)(this.O_3598_v() - d0), (float)(this.l_2647_k() - d1));
    }

    public boolean t_4043_B() {
        return this.e_4240_b;
    }

    protected void n_1700_B(float movementX, float movementZ) {
        if (this.H_1491_c()) {
            e_2866_D vector3d = this.s_4990_V();
            e_2866_D vector3d1 = vector3d.J_1907_R(movementX, 0.0, movementZ);
            e_2866_D vector3d2 = new e_2866_D(movementX, 0.0, movementZ);
            float f = this.l_2995_s();
            float f1 = (float)vector3d2.v_4262_N();
            if (f1 <= 0.001f) {
                P_3504_Q vector2f = this.G_564_y.getMoveVector();
                float f2 = f * vector2f.t_148_a;
                float f3 = f * vector2f.s_956_w;
                float f4 = u_530_F.n_1700_B(this.p_178_J * ((float)Math.PI / 180));
                float f5 = u_530_F.J_1907_R(this.p_178_J * ((float)Math.PI / 180));
                vector3d2 = new e_2866_D(f2 * f5 - f3 * f4, vector3d2.R_4764_Y, f3 * f5 + f2 * f4);
                f1 = (float)vector3d2.v_4262_N();
                if (f1 <= 0.001f) {
                    return;
                }
            }
            float f12 = u_530_F.t_148_a(f1);
            e_2866_D vector3d12 = vector3d2.n_1700_B((double)f12);
            e_2866_D vector3d13 = this.F_4247_a();
            float f13 = (float)(vector3d13.J_1907_R * vector3d12.J_1907_R + vector3d13.G_564_y * vector3d12.G_564_y);
            if (!(f13 < -0.15f)) {
                K_4074_S blockstate1;
                CollisionContext iselectioncontext = CollisionContext.n_1700_B(this);
                c_1514_x blockpos = new c_1514_x(this.O_3598_v(), this.i_601_W().maxY, this.l_2647_k());
                K_4074_S blockstate = this.O_508_d.getBlockState(blockpos);
                if (blockstate.R_4764_Y((BlockGetter)this.O_508_d, blockpos, iselectioncontext).J_1907_R() && (blockstate1 = this.O_508_d.getBlockState(blockpos = blockpos.up())).R_4764_Y((BlockGetter)this.O_508_d, blockpos, iselectioncontext).J_1907_R()) {
                    float f14;
                    float f6 = 7.0f;
                    float f7 = 1.2f;
                    if (this.J_1907_R(MobEffects.w_1484_f)) {
                        f7 += (float)(this.R_4764_Y(MobEffects.w_1484_f).R_4764_Y() + 1) * 0.75f;
                    }
                    float f8 = Math.max(f * 7.0f, 1.0f / f12);
                    e_2866_D vector3d4 = vector3d1.P_1922_E(vector3d12.n_1700_B((double)f8));
                    float f9 = this.C_415_h();
                    float f10 = this.v_165_F();
                    I_4817_s axisalignedbb = new I_4817_s(vector3d, vector3d4.J_1907_R(0.0, f10, 0.0)).grow(f9, 0.0, f9);
                    e_2866_D lvt_19_1_ = vector3d.J_1907_R(0.0, 0.51f, 0.0);
                    vector3d4 = vector3d4.J_1907_R(0.0, 0.51f, 0.0);
                    e_2866_D vector3d5 = vector3d12.R_4764_Y(new e_2866_D(0.0, 1.0, 0.0));
                    e_2866_D vector3d6 = vector3d5.n_1700_B((double)(f9 * 0.5f));
                    e_2866_D vector3d7 = lvt_19_1_.G_564_y(vector3d6);
                    e_2866_D vector3d8 = vector3d4.G_564_y(vector3d6);
                    e_2866_D vector3d9 = lvt_19_1_.P_1922_E(vector3d6);
                    e_2866_D vector3d10 = vector3d4.P_1922_E(vector3d6);
                    Iterator iterator = this.O_508_d.R_4764_Y(this, axisalignedbb, entity -> true).flatMap(shape -> shape.G_564_y().stream()).iterator();
                    float f11 = Float.MIN_VALUE;
                    while (iterator.hasNext()) {
                        I_4817_s axisalignedbb1 = (I_4817_s)iterator.next();
                        if (!axisalignedbb1.intersects(vector3d7, vector3d8) && !axisalignedbb1.intersects(vector3d9, vector3d10)) continue;
                        f11 = (float)axisalignedbb1.maxY;
                        e_2866_D vector3d11 = axisalignedbb1.getCenter();
                        c_1514_x blockpos1 = new c_1514_x(vector3d11);
                        int i = 1;
                        while ((float)i < f7) {
                            K_4074_S blockstate3;
                            c_1514_x blockpos2 = blockpos1.up(i);
                            K_4074_S blockstate2 = this.O_508_d.getBlockState(blockpos2);
                            s_1395_c voxelshape = blockstate2.R_4764_Y((BlockGetter)this.O_508_d, blockpos2, iselectioncontext);
                            if (!voxelshape.J_1907_R() && (double)(f11 = (float)voxelshape.R_4764_Y(b_257_Y.n_1700_B.J_1907_R) + (float)blockpos2.getY()) - this.X_2960_b() > (double)f7) {
                                return;
                            }
                            if (i > 1 && !(blockstate3 = this.O_508_d.getBlockState(blockpos = blockpos.up())).R_4764_Y((BlockGetter)this.O_508_d, blockpos, iselectioncontext).J_1907_R()) {
                                return;
                            }
                            ++i;
                        }
                        break block0;
                    }
                    if (f11 != Float.MIN_VALUE && !((f14 = (float)((double)f11 - this.X_2960_b())) <= 0.5f) && !(f14 > f7)) {
                        this.n_3318_d = 1;
                    }
                }
            }
        }
    }

    private boolean H_1491_c() {
        return this.t_4043_B() && this.n_3318_d <= 0 && this.e_1992_r && !this.y_2622_c() && !this.y_2772_m() && this.h_2367_h() && (double)this.RealmsWorldResetDto() >= 1.0;
    }

    private boolean h_2367_h() {
        P_3504_Q vector2f = this.G_564_y.getMoveVector();
        return vector2f.t_148_a != 0.0f || vector2f.s_956_w != 0.0f;
    }

    private boolean c_1608_O() {
        double d0 = 0.8;
        return this.z_1737_N() ? this.G_564_y.isMovingForward() : (double)this.G_564_y.moveForward >= 0.8;
    }

    public float n_3318_d() {
        if (!((N_4263_v)this).n_1700_B(FluidTags.J_1907_R)) {
            return 0.0f;
        }
        float f = 600.0f;
        float f1 = 100.0f;
        if ((float)this.z_1737_N >= 600.0f) {
            return 1.0f;
        }
        float f2 = u_530_F.n_1700_B((float)this.z_1737_N / 100.0f, 0.0f, 1.0f);
        float f3 = (float)this.z_1737_N < 100.0f ? 0.0f : u_530_F.n_1700_B(((float)this.z_1737_N - 100.0f) / 500.0f, 0.0f, 1.0f);
        return f2 * 0.6f + f3 * 0.39999998f;
    }

    @Override
    public boolean z_1737_N() {
        return this.i_3196_G;
    }

    @Override
    protected boolean v_4276_D() {
        boolean flag = this.i_3196_G;
        boolean flag1 = super.v_4276_D();
        if (this.d_2461_k()) {
            return this.i_3196_G;
        }
        if (!flag && flag1) {
            this.O_508_d.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.t_1786_h, D_38_f.t_148_a, 1.0f, 1.0f, false);
            this.P_1922_E.Z_976_R().n_1700_B((SoundInstance)new n_2873_k.J_1907_R(this));
        }
        if (flag && !flag1) {
            this.O_508_d.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.multiplayerClientSuggestionProvider, D_38_f.t_148_a, 1.0f, 1.0f, false);
        }
        return this.i_3196_G;
    }

    @Override
    public e_2866_D P_1922_E(float partialTicks) {
        if (this.P_1922_E.P_4830_p.P_4830_p().n_1700_B()) {
            float f = u_530_F.v_4262_N(partialTicks * 0.5f, this.p_178_J, this.j_276_v) * ((float)Math.PI / 180);
            float f1 = u_530_F.v_4262_N(partialTicks * 0.5f, this.f_4016_n, this.UploadStatus) * ((float)Math.PI / 180);
            double d0 = this.d_2169_p() == k_4231_L.J_1907_R ? -1.0 : 1.0;
            e_2866_D vector3d = new e_2866_D(0.39 * d0, -0.6, 0.3);
            return vector3d.n_1700_B(-f1).J_1907_R(-f).P_1922_E(this.u_2550_I(partialTicks));
        }
        return super.P_1922_E(partialTicks);
    }

    @Generated
    public float d_2427_y() {
        return this.Y_259_p;
    }

    @Generated
    public float l_1233_K() {
        return this.J_1907_R;
    }

    @Generated
    public void P_1922_E(boolean serverSprintState) {
        this.R_4764_Y = serverSprintState;
    }

    @Generated
    public boolean z_1333_t() {
        return this.R_4764_Y;
    }
}




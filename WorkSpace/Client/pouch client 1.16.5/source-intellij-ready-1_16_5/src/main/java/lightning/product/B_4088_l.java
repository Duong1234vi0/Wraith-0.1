/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.authlib.GameProfile
 *  com.mojang.datafixers.util.Either
 *  com.mojang.serialization.DynamicOps
 *  io.netty.util.concurrent.Future
 *  io.netty.util.concurrent.GenericFutureListener
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.DynamicOps;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.ComplexItem;
import lightning.product.A_4313_D;
import lightning.product.ServerItemCooldowns;
import lightning.product.ServerboundClientInformationPacket;
import lightning.product.ClientboundContainerSetContentPacket;
import lightning.product.B_477_D;
import lightning.product.C_1375_J;
import lightning.product.D_38_f;
import lightning.product.D_4024_W;
import lightning.product.ClientboundSetCameraPacket;
import lightning.product.E_3520_U;
import lightning.product.F_2904_S;
import lightning.product.G_3246_f;
import lightning.product.BiomeManager;
import lightning.product.MutableComponent;
import lightning.product.ClientboundChangeDifficultyPacket;
import lightning.product.I_14_v;
import lightning.product.I_4817_s;
import lightning.product.ClientboundGameEventPacket;
import lightning.product.MobEffects;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.ServerRecipeBook;
import lightning.product.M_1462_J;
import lightning.product.Container;
import lightning.product.ClientboundRespawnPacket;
import lightning.product.N_4263_v;
import lightning.product.ClientboundPlayerAbilitiesPacket;
import lightning.product.O_3671_t;
import lightning.product.P_11_z;
import lightning.product.ClientboundOpenScreenPacket;
import lightning.product.NonNullList;
import lightning.product.R_3940_n;
import lightning.product.LevelData;
import lightning.product.S_4998_h;
import lightning.product.T_1368_k;
import lightning.product.Stats;
import lightning.product.U_2534_D;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.ClientboundPlayerLookAtPacket;
import lightning.product.ClientboundSetHealthPacket;
import lightning.product.ClientboundAddPlayerPacket;
import lightning.product.W_4148_E;
import lightning.product.SoundEvent;
import lightning.product.X_1446_C;
import lightning.product.PlayerRespawnLogic;
import lightning.product.ClientboundSoundPacket;
import lightning.product.X_821_u;
import lightning.product.Y_1387_d;
import lightning.product.ResultSlot;
import lightning.product.Y_408_h;
import lightning.product.BlockUtil;
import lightning.product.Tag;
import lightning.product.Z_1993_T;
import lightning.product.ClientboundRemoveMobEffectPacket;
import lightning.product.ClientboundSetExperiencePacket;
import lightning.product.ClientboundMerchantOffersPacket;
import lightning.product.Z_530_i;
import lightning.product.a_2900_S;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.a_4764_N;
import lightning.product.ContainerListener;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.PlayerTeam;
import lightning.product.SectionPos;
import lightning.product.c_973_a;
import lightning.product.TextFilter;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.f_1186_l;
import lightning.product.f_2392_k;
import lightning.product.f_2785_f;
import lightning.product.g_1995_W;
import lightning.product.g_2336_b;
import lightning.product.g_4418_P;
import lightning.product.h_384_L;
import lightning.product.i_2154_H;
import lightning.product.Recipe;
import lightning.product.MerchantOffers;
import lightning.product.j_3341_s;
import lightning.product.Monster;
import lightning.product.ClientboundChatPacket;
import lightning.product.k_2610_C;
import lightning.product.k_2895_h;
import lightning.product.k_4231_L;
import lightning.product.l_4118_l;
import lightning.product.ClientboundBlockEntityDataPacket;
import lightning.product.n_1494_c;
import lightning.product.ClientboundPlayerCombatPacket;
import lightning.product.n_3236_c;
import lightning.product.ClientboundRemoveEntitiesPacket;
import lightning.product.ReportedException;
import lightning.product.o_3050_h;
import lightning.product.o_98_P;
import lightning.product.q_1613_l;
import lightning.product.q_3092_O;
import lightning.product.Items;
import lightning.product.ClientboundUpdateMobEffectPacket;
import lightning.product.CrashReportCategory;
import lightning.product.r_4811_B;
import lightning.product.ClientboundLevelEventPacket;
import lightning.product.s_4922_C;
import lightning.product.Packet;
import lightning.product.t_3286_u;
import lightning.product.t_3906_J;
import lightning.product.u_530_F;
import lightning.product.v_174_f;
import lightning.product.v_4727_z;
import lightning.product.v_4839_y;
import lightning.product.v_576_m;
import lightning.product.w_3005_z;
import lightning.product.x_1688_C;
import lightning.product.x_2680_y;
import lightning.product.x_282_a;
import lightning.product.EntityAnchorArgument;
import net.minecraft.server.G_564_y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class B_4088_l
extends a_3913_L
implements ContainerListener {
    private static final Logger v_4262_N = LogManager.getLogger();
    public s_4922_C n_1700_B;
    public final G_564_y J_1907_R;
    public final v_174_f R_4764_Y;
    private final List<Integer> w_1484_f = Lists.newLinkedList();
    private final S_4998_h t_148_a;
    private final k_2895_h s_956_w;
    private float u_2550_I = Float.MIN_VALUE;
    private int M_588_G = Integer.MIN_VALUE;
    private int P_4830_p = Integer.MIN_VALUE;
    private int h_1847_R = Integer.MIN_VALUE;
    private int Q_4569_t = Integer.MIN_VALUE;
    private int M_182_A = Integer.MIN_VALUE;
    private float t_1786_h = -1.0E8f;
    private int multiplayerClientSuggestionProvider = -99999999;
    private boolean w_1457_N = true;
    private int Y_601_j = -99999999;
    private int Y_259_p = 60;
    private g_4418_P Q_2552_b;
    private boolean C_2741_M = true;
    private long k_2293_S = j_3341_s.J_1907_R();
    private N_4263_v q_2307_F;
    private boolean Z_875_P;
    private boolean c_3005_b;
    private final ServerRecipeBook H_2857_Y = new ServerRecipeBook();
    private e_2866_D A_4115_X;
    private int Y_1740_V;
    private boolean t_4043_B;
    @Nullable
    private e_2866_D x_607_J;
    private SectionPos e_4240_b = SectionPos.n_1700_B(0, 0, 0);
    private f_2392_k<b_4507_u> n_3318_d = b_4507_u.u_1723_Y;
    @Nullable
    private c_1514_x d_2427_y;
    private boolean z_1737_N;
    private float v_4276_D;
    @Nullable
    private final TextFilter d_2461_k;
    private int G_624_v;
    public boolean G_564_y;
    public int P_1922_E;
    public boolean u_1723_Y;

    public B_4088_l(G_564_y server, e_3591_l worldIn, GameProfile profile, v_174_f interactionManagerIn) {
        super(worldIn, worldIn.A_1038_p(), worldIn.i_1637_u(), profile);
        interactionManagerIn.J_1907_R = this;
        this.R_4764_Y = interactionManagerIn;
        this.J_1907_R = server;
        this.s_956_w = server.p_178_J().n_1700_B((a_3913_L)this);
        this.t_148_a = server.p_178_J().u_1723_Y(this);
        this.RealmsServerPing = 1.0f;
        this.R_4764_Y(worldIn);
        this.d_2461_k = server.n_1700_B(this);
    }

    private void R_4764_Y(e_3591_l worldIn) {
        c_1514_x blockpos = worldIn.A_1038_p();
        if (worldIn.G_624_v().J_1907_R() && worldIn.T_2506_i().c_132_F().t_1786_h() != I_14_v.G_564_y) {
            long k;
            long l;
            int i = Math.max(0, this.J_1907_R.n_1700_B(worldIn));
            int j = u_530_F.R_4764_Y(worldIn.H_2857_Y().n_1700_B(blockpos.getX(), blockpos.getZ()));
            if (j < i) {
                i = j;
            }
            if (j <= 1) {
                i = 1;
            }
            int i1 = (l = (k = (long)(i * 2 + 1)) * k) > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)l;
            int j1 = this.Y_259_p(i1);
            int k1 = new Random().nextInt(i1);
            for (int l1 = 0; l1 < i1; ++l1) {
                int i2 = (k1 + j1 * l1) % i1;
                int j2 = i2 % (i * 2 + 1);
                int k2 = i2 / (i * 2 + 1);
                c_1514_x blockpos1 = PlayerRespawnLogic.n_1700_B(worldIn, blockpos.getX() + j2 - i, blockpos.getZ() + k2 - i, false);
                if (blockpos1 == null) continue;
                this.n_1700_B(blockpos1, 0.0f, 0.0f);
                if (!worldIn.u_1723_Y((N_4263_v)this)) {
                    continue;
                }
                break;
            }
        } else {
            this.n_1700_B(blockpos, 0.0f, 0.0f);
            while (!worldIn.u_1723_Y((N_4263_v)this) && this.X_2960_b() < 255.0) {
                this.J_1907_R(this.O_3598_v(), this.X_2960_b() + 1.0, this.l_2647_k());
            }
        }
    }

    private int Y_259_p(int p_205735_1_) {
        return p_205735_1_ <= 16 ? p_205735_1_ - 1 : 17;
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.R_4764_Y("playerGameType", 99)) {
            if (this.f_1574_f().Ops()) {
                this.R_4764_Y.n_1700_B(this.f_1574_f().Q_4569_t(), I_14_v.n_1700_B);
            } else {
                this.R_4764_Y.n_1700_B(I_14_v.n_1700_B(compound.w_1484_f("playerGameType")), compound.R_4764_Y("previousPlayerGameType", 3) ? I_14_v.n_1700_B(compound.w_1484_f("previousPlayerGameType")) : I_14_v.n_1700_B);
            }
        }
        if (compound.R_4764_Y("enteredNetherPosition", 10)) {
            U_2912_j compoundnbt = compound.M_182_A("enteredNetherPosition");
            this.x_607_J = new e_2866_D(compoundnbt.u_2550_I("x"), compoundnbt.u_2550_I("y"), compoundnbt.u_2550_I("z"));
        }
        this.c_3005_b = compound.t_1786_h("seenCredits");
        if (compound.R_4764_Y("recipeBook", 10)) {
            this.H_2857_Y.n_1700_B(compound.M_182_A("recipeBook"), this.J_1907_R.ValueObject());
        }
        if (this.z_2372_L()) {
            this.t_2932_z();
        }
        if (compound.R_4764_Y("SpawnX", 99) && compound.R_4764_Y("SpawnY", 99) && compound.R_4764_Y("SpawnZ", 99)) {
            this.d_2427_y = new c_1514_x(compound.w_1484_f("SpawnX"), compound.w_1484_f("SpawnY"), compound.w_1484_f("SpawnZ"));
            this.z_1737_N = compound.t_1786_h("SpawnForced");
            this.v_4276_D = compound.s_956_w("SpawnAngle");
            if (compound.P_1922_E("SpawnDimension")) {
                this.n_3318_d = b_4507_u.P_1922_E.parse((DynamicOps)l_4118_l.n_1700_B, (Object)compound.R_4764_Y("SpawnDimension")).resultOrPartial(arg_0 -> ((Logger)v_4262_N).error(arg_0)).orElse(b_4507_u.u_1723_Y);
            }
        }
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("playerGameType", this.R_4764_Y.J_1907_R().n_1700_B());
        compound.J_1907_R("previousPlayerGameType", this.R_4764_Y.R_4764_Y().n_1700_B());
        compound.n_1700_B("seenCredits", this.c_3005_b);
        if (this.x_607_J != null) {
            U_2912_j compoundnbt = new U_2912_j();
            compoundnbt.n_1700_B("x", this.x_607_J.J_1907_R);
            compoundnbt.n_1700_B("y", this.x_607_J.R_4764_Y);
            compoundnbt.n_1700_B("z", this.x_607_J.G_564_y);
            compound.n_1700_B("enteredNetherPosition", compoundnbt);
        }
        N_4263_v entity1 = this.d_3244_b();
        N_4263_v entity = this.l_3609_d();
        if (entity != null && entity1 != this && entity1.l_697_B()) {
            U_2912_j compoundnbt1 = new U_2912_j();
            U_2912_j compoundnbt2 = new U_2912_j();
            entity1.G_564_y(compoundnbt2);
            compoundnbt1.n_1700_B("Attach", entity.w_2705_t());
            compoundnbt1.n_1700_B("Entity", compoundnbt2);
            compound.n_1700_B("RootVehicle", compoundnbt1);
        }
        compound.n_1700_B("recipeBook", this.H_2857_Y.n_1700_B());
        compound.n_1700_B("Dimension", this.O_508_d.g_2268_R().n_1700_B().toString());
        if (this.d_2427_y != null) {
            compound.J_1907_R("SpawnX", this.d_2427_y.getX());
            compound.J_1907_R("SpawnY", this.d_2427_y.getY());
            compound.J_1907_R("SpawnZ", this.d_2427_y.getZ());
            compound.n_1700_B("SpawnForced", this.z_1737_N);
            compound.n_1700_B("SpawnAngle", this.v_4276_D);
            g_2336_b.n_1700_B.encodeStart((DynamicOps)l_4118_l.n_1700_B, (Object)this.n_3318_d.n_1700_B()).resultOrPartial(arg_0 -> ((Logger)v_4262_N).error(arg_0)).ifPresent(p_241148_1_ -> compound.n_1700_B("SpawnDimension", (Tag)p_241148_1_));
        }
    }

    public void n_1700_B(int p_195394_1_) {
        float f = this.f_2787_O();
        float f1 = (f - 1.0f) / f;
        this.b_2312_j = u_530_F.n_1700_B((float)p_195394_1_ / f, 0.0f, f1);
        this.Y_601_j = -1;
    }

    public void Y_601_j(int level) {
        this.v_165_F = level;
        this.Y_601_j = -1;
    }

    @Override
    public void w_1457_N(int levels) {
        super.w_1457_N(levels);
        this.Y_601_j = -1;
    }

    @Override
    public void J_1907_R(Z_1993_T enchantedItem, int cost) {
        super.J_1907_R(enchantedItem, cost);
        this.Y_601_j = -1;
    }

    public void u_1723_Y() {
        this.H_1873_g.n_1700_B(this);
    }

    @Override
    public void E_4256_w() {
        super.E_4256_w();
        this.n_1700_B.n_1700_B(new ClientboundPlayerCombatPacket(this.i_789_Q(), ClientboundPlayerCombatPacket.n_1700_B.n_1700_B));
    }

    @Override
    public void V_1665_T() {
        super.V_1665_T();
        this.n_1700_B.n_1700_B(new ClientboundPlayerCombatPacket(this.i_789_Q(), ClientboundPlayerCombatPacket.n_1700_B.J_1907_R));
    }

    @Override
    protected void n_1700_B(K_4074_S state) {
        U_3554_Q.G_564_y.n_1700_B(this, state);
    }

    @Override
    protected v_576_m n_473_l() {
        return new ServerItemCooldowns(this);
    }

    @Override
    public void v_() {
        this.R_4764_Y.n_1700_B();
        --this.Y_259_p;
        if (this.F_1410_V > 0) {
            --this.F_1410_V;
        }
        this.H_1873_g.M_588_G();
        if (!this.O_508_d.Y_259_p && !this.H_1873_g.n_1700_B(this)) {
            this.P_1922_E();
            this.H_1873_g = this.o_1800_r;
        }
        while (!this.w_1484_f.isEmpty()) {
            int i = Math.min(this.w_1484_f.size(), Integer.MAX_VALUE);
            int[] aint = new int[i];
            Iterator<Integer> iterator = this.w_1484_f.iterator();
            int j = 0;
            while (iterator.hasNext() && j < i) {
                aint[j++] = iterator.next();
                iterator.remove();
            }
            this.n_1700_B.n_1700_B(new ClientboundRemoveEntitiesPacket(aint));
        }
        N_4263_v entity = this.T_2506_i();
        if (entity != this) {
            if (entity.RealmsLongRunningMcoTaskScreen()) {
                this.n_1700_B(entity.O_3598_v(), entity.X_2960_b(), entity.l_2647_k(), entity.p_178_J, entity.f_4016_n);
                this.c_3005_b().Y_259_p().n_1700_B(this);
                if (this.n_4915_F()) {
                    this.t_4043_B(this);
                }
            } else {
                this.t_4043_B(this);
            }
        }
        U_3554_Q.C_2741_M.n_1700_B(this);
        if (this.A_4115_X != null) {
            U_3554_Q.Y_259_p.n_1700_B(this, this.A_4115_X, this.RealmsWorldResetDto - this.Y_1740_V);
        }
        this.t_148_a.J_1907_R(this);
    }

    public void h_1847_R() {
        try {
            if (!this.d_2461_k() || this.O_508_d.M_588_G(this.b_2312_j())) {
                super.v_();
            }
            for (int i = 0; i < this.l_1268_F.Y_259_p(); ++i) {
                Packet<?> ipacket;
                Z_1993_T itemstack = this.l_1268_F.s_956_w(i);
                if (!itemstack.J_1907_R().n_1700_B() || (ipacket = ((ComplexItem)itemstack.J_1907_R()).n_1700_B(itemstack, this.O_508_d, this)) == null) continue;
                this.n_1700_B.n_1700_B(ipacket);
            }
            if (this.g_46_E() != this.t_1786_h || this.multiplayerClientSuggestionProvider != this.n_3864_h.n_1700_B() || this.n_3864_h.R_4764_Y() == 0.0f != this.w_1457_N) {
                this.n_1700_B.n_1700_B(new ClientboundSetHealthPacket(this.g_46_E(), this.n_3864_h.n_1700_B(), this.n_3864_h.R_4764_Y()));
                this.t_1786_h = this.g_46_E();
                this.multiplayerClientSuggestionProvider = this.n_3864_h.n_1700_B();
                boolean bl = this.w_1457_N = this.n_3864_h.R_4764_Y() == 0.0f;
            }
            if (this.g_46_E() + this.U_3823_u() != this.u_2550_I) {
                this.u_2550_I = this.g_46_E() + this.U_3823_u();
                this.n_1700_B(M_1462_J.v_4262_N, u_530_F.u_1723_Y(this.u_2550_I));
            }
            if (this.n_3864_h.n_1700_B() != this.M_588_G) {
                this.M_588_G = this.n_3864_h.n_1700_B();
                this.n_1700_B(M_1462_J.w_1484_f, u_530_F.u_1723_Y((float)this.M_588_G));
            }
            if (this.L_4248_u() != this.P_4830_p) {
                this.P_4830_p = this.L_4248_u();
                this.n_1700_B(M_1462_J.t_148_a, u_530_F.u_1723_Y((float)this.P_4830_p));
            }
            if (this.E_3343_g() != this.h_1847_R) {
                this.h_1847_R = this.E_3343_g();
                this.n_1700_B(M_1462_J.s_956_w, u_530_F.u_1723_Y((float)this.h_1847_R));
            }
            if (this.s_4990_V != this.M_182_A) {
                this.M_182_A = this.s_4990_V;
                this.n_1700_B(M_1462_J.u_2550_I, u_530_F.u_1723_Y((float)this.M_182_A));
            }
            if (this.v_165_F != this.Q_4569_t) {
                this.Q_4569_t = this.v_165_F;
                this.n_1700_B(M_1462_J.M_588_G, u_530_F.u_1723_Y((float)this.Q_4569_t));
            }
            if (this.s_4990_V != this.Y_601_j) {
                this.Y_601_j = this.s_4990_V;
                this.n_1700_B.n_1700_B(new ClientboundSetExperiencePacket(this.b_2312_j, this.s_4990_V, this.v_165_F));
            }
            if (this.RealmsWorldResetDto % 20 == 0) {
                U_3554_Q.M_182_A.n_1700_B(this);
            }
        }
        catch (Throwable throwable) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Ticking player");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Player being ticked");
            this.n_1700_B(crashreportcategory);
            throw new ReportedException(crashreport);
        }
    }

    private void n_1700_B(M_1462_J criteria, int points) {
        this.U_3758_B().n_1700_B(criteria, this.L_3570_A(), (v_4839_y p_195397_1_) -> p_195397_1_.J_1907_R(points));
    }

    @Override
    public void R_4764_Y(P_11_z cause) {
        boolean flag = this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.M_588_G);
        if (flag) {
            x_282_a itextcomponent = this.i_789_Q().J_1907_R();
            this.n_1700_B.n_1700_B(new ClientboundPlayerCombatPacket(this.i_789_Q(), ClientboundPlayerCombatPacket.n_1700_B.R_4764_Y, itextcomponent), (GenericFutureListener<? extends Future<? super Void>>)((GenericFutureListener)p_212356_2_ -> {
                if (!p_212356_2_.isSuccess()) {
                    int i = 256;
                    String s = itextcomponent.n_1700_B(256);
                    F_2904_S itextcomponent1 = new F_2904_S("death.attack.message_too_long", new U_2871_b(s).n_1700_B(D_4024_W.Q_4569_t));
                    MutableComponent itextcomponent2 = new F_2904_S("death.attack.even_more_magic", this.c_()).n_1700_B(p_212357_1_ -> p_212357_1_.n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, itextcomponent1)));
                    this.n_1700_B.n_1700_B(new ClientboundPlayerCombatPacket(this.i_789_Q(), ClientboundPlayerCombatPacket.n_1700_B.R_4764_Y, itextcomponent2));
                }
            }));
            o_3050_h team = this.L_1362_X();
            if (team != null && team.s_956_w() != o_3050_h.J_1907_R.n_1700_B) {
                if (team.s_956_w() == o_3050_h.J_1907_R.R_4764_Y) {
                    this.J_1907_R.p_178_J().n_1700_B((a_3913_L)this, itextcomponent);
                } else if (team.s_956_w() == o_3050_h.J_1907_R.G_564_y) {
                    this.J_1907_R.p_178_J().J_1907_R(this, itextcomponent);
                }
            } else {
                this.J_1907_R.p_178_J().n_1700_B(itextcomponent, Y_408_h.J_1907_R, j_3341_s.J_1907_R);
            }
        } else {
            this.n_1700_B.n_1700_B(new ClientboundPlayerCombatPacket(this.i_789_Q(), ClientboundPlayerCombatPacket.n_1700_B.R_4764_Y));
        }
        this.o_4117_e();
        if (this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.x_607_J)) {
            this.T_3594_S();
        }
        if (!this.d_2461_k()) {
            this.G_564_y(cause);
        }
        this.U_3758_B().n_1700_B(M_1462_J.G_564_y, this.L_3570_A(), v_4839_y::n_1700_B);
        r_4811_B livingentity = this.J_2061_p();
        if (livingentity != null) {
            this.n_1700_B(Stats.w_1484_f.J_1907_R(livingentity.f_4016_n()));
            livingentity.n_1700_B((N_4263_v)this, this.t_1446_I, cause);
            this.u_1723_Y(livingentity);
        }
        this.O_508_d.n_1700_B((N_4263_v)this, (byte)3);
        this.J_1907_R(Stats.G_624_v);
        this.J_1907_R(Stats.t_148_a.J_1907_R(Stats.M_588_G));
        this.J_1907_R(Stats.t_148_a.J_1907_R(Stats.P_4830_p));
        this.RealmsServerPing();
        this.J_1907_R(0, false);
        this.i_789_Q().P_1922_E();
    }

    private void T_3594_S() {
        I_4817_s axisalignedbb = new I_4817_s(this.b_2312_j()).grow(32.0, 10.0, 32.0);
        this.O_508_d.J_1907_R(Z_530_i.class, axisalignedbb).stream().filter(p_241155_0_ -> p_241155_0_ instanceof G_3246_f).forEach(p_241145_1_ -> ((G_3246_f)((Object)p_241145_1_)).n_1700_B(this));
    }

    @Override
    public void n_1700_B(N_4263_v killed, int scoreValue, P_11_z damageSource) {
        if (killed != this) {
            super.n_1700_B(killed, scoreValue, damageSource);
            this.t_1786_h(scoreValue);
            String s = this.L_3570_A();
            String s1 = killed.L_3570_A();
            this.U_3758_B().n_1700_B(M_1462_J.u_1723_Y, s, v_4839_y::n_1700_B);
            if (killed instanceof a_3913_L) {
                this.J_1907_R(Stats.z_4693_k);
                this.U_3758_B().n_1700_B(M_1462_J.P_1922_E, s, v_4839_y::n_1700_B);
            } else {
                this.J_1907_R(Stats.T_2506_i);
            }
            this.n_1700_B(s, s1, M_1462_J.P_4830_p);
            this.n_1700_B(s1, s, M_1462_J.h_1847_R);
            U_3554_Q.J_1907_R.n_1700_B(this, killed, damageSource);
        }
    }

    private void n_1700_B(String p_195398_1_, String p_195398_2_, M_1462_J[] p_195398_3_) {
        int i;
        PlayerTeam scoreplayerteam = this.U_3758_B().w_1484_f(p_195398_2_);
        if (scoreplayerteam != null && (i = scoreplayerteam.P_4830_p().n_1700_B()) >= 0 && i < p_195398_3_.length) {
            this.U_3758_B().n_1700_B(p_195398_3_[i], p_195398_1_, v_4839_y::n_1700_B);
        }
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        boolean flag;
        if (this.n_1700_B(source)) {
            return false;
        }
        boolean bl = flag = this.J_1907_R.g_164_R() && this.D_4792_h() && "fall".equals(source.Q_2552_b);
        if (!flag && this.Y_259_p > 0 && source != P_11_z.P_4830_p) {
            return false;
        }
        if (source instanceof f_2785_f) {
            h_384_L abstractarrowentity;
            N_4263_v entity1;
            N_4263_v entity = source.u_2550_I();
            if (entity instanceof a_3913_L && !this.G_564_y((a_3913_L)entity)) {
                return false;
            }
            if (entity instanceof h_384_L && (entity1 = (abstractarrowentity = (h_384_L)entity).Y_601_j()) instanceof a_3913_L && !this.G_564_y((a_3913_L)entity1)) {
                return false;
            }
        }
        return super.n_1700_B(source, amount);
    }

    @Override
    public boolean G_564_y(a_3913_L other) {
        return !this.D_4792_h() ? false : super.G_564_y(other);
    }

    private boolean D_4792_h() {
        return this.J_1907_R.T_3594_S();
    }

    @Override
    @Nullable
    protected f_1186_l J_1907_R(e_3591_l p_241829_1_) {
        f_1186_l portalinfo = super.J_1907_R(p_241829_1_);
        if (portalinfo != null && this.O_508_d.g_2268_R() == b_4507_u.u_1723_Y && p_241829_1_.g_2268_R() == b_4507_u.w_1484_f) {
            e_2866_D vector3d = portalinfo.n_1700_B.J_1907_R(0.0, -1.0, 0.0);
            return new f_1186_l(vector3d, e_2866_D.n_1700_B, 90.0f, 0.0f);
        }
        return portalinfo;
    }

    @Override
    @Nullable
    public N_4263_v n_1700_B(e_3591_l server) {
        this.Z_875_P = true;
        e_3591_l serverworld = this.c_3005_b();
        f_2392_k<b_4507_u> registrykey = serverworld.g_2268_R();
        if (registrykey == b_4507_u.w_1484_f && server.g_2268_R() == b_4507_u.u_1723_Y) {
            this.Ping();
            this.c_3005_b().P_1922_E(this);
            if (!this.u_1723_Y) {
                this.u_1723_Y = true;
                this.n_1700_B.n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.P_1922_E, this.c_3005_b ? 0.0f : 1.0f));
                this.c_3005_b = true;
            }
            return this;
        }
        LevelData iworldinfo = server.k_2293_S();
        this.n_1700_B.n_1700_B(new ClientboundRespawnPacket(server.G_624_v(), server.g_2268_R(), BiomeManager.n_1700_B(server.n_1700_B()), this.R_4764_Y.J_1907_R(), this.R_4764_Y.R_4764_Y(), server.l_1233_K(), server.j_276_v(), true));
        this.n_1700_B.n_1700_B(new ClientboundChangeDifficultyPacket(iworldinfo.u_2550_I(), iworldinfo.M_588_G()));
        g_1995_W playerlist = this.J_1907_R.p_178_J();
        playerlist.G_564_y(this);
        serverworld.P_1922_E(this);
        this.t_4219_U = false;
        f_1186_l portalinfo = this.J_1907_R(server);
        if (portalinfo != null) {
            serverworld.D_4792_h().n_1700_B("moving");
            if (registrykey == b_4507_u.u_1723_Y && server.g_2268_R() == b_4507_u.v_4262_N) {
                this.x_607_J = this.s_4990_V();
            } else if (server.g_2268_R() == b_4507_u.w_1484_f) {
                this.n_1700_B(server, new c_1514_x(portalinfo.n_1700_B));
            }
            serverworld.D_4792_h().R_4764_Y();
            serverworld.D_4792_h().n_1700_B("placing");
            this.n_1700_B((b_4507_u)server);
            server.J_1907_R(this);
            this.J_1907_R(portalinfo.R_4764_Y, portalinfo.G_564_y);
            this.P_1922_E(portalinfo.n_1700_B.J_1907_R, portalinfo.n_1700_B.R_4764_Y, portalinfo.n_1700_B.G_564_y);
            serverworld.D_4792_h().R_4764_Y();
            this.G_564_y(serverworld);
            this.R_4764_Y.n_1700_B(server);
            this.n_1700_B.n_1700_B(new ClientboundPlayerAbilitiesPacket(this.C_415_h));
            playerlist.n_1700_B(this, server);
            playerlist.P_1922_E(this);
            for (k_2610_C effectinstance : this.I_3457_f()) {
                this.n_1700_B.n_1700_B(new ClientboundUpdateMobEffectPacket(this.j_276_v(), effectinstance));
            }
            this.n_1700_B.n_1700_B(new ClientboundLevelEventPacket(1032, c_1514_x.ZERO, 0, false));
            this.Y_601_j = -1;
            this.t_1786_h = -1.0f;
            this.multiplayerClientSuggestionProvider = -1;
        }
        return this;
    }

    private void n_1700_B(e_3591_l p_242110_1_, c_1514_x p_242110_2_) {
        c_1514_x.n_1700_B blockpos$mutable = p_242110_2_.toMutable();
        for (int i = -2; i <= 2; ++i) {
            for (int j = -2; j <= 2; ++j) {
                for (int k = -1; k < 3; ++k) {
                    K_4074_S blockstate = k == -1 ? a_3742_W.ClientBootstrap.multiplayerClientSuggestionProvider() : a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
                    p_242110_1_.J_1907_R((c_1514_x)blockpos$mutable.n_1700_B(p_242110_2_).J_1907_R(j, k, i), blockstate);
                }
            }
        }
    }

    @Override
    protected Optional<BlockUtil.J_1907_R> n_1700_B(e_3591_l p_241830_1_, c_1514_x p_241830_2_, boolean p_241830_3_) {
        Optional<BlockUtil.J_1907_R> optional = super.n_1700_B(p_241830_1_, p_241830_2_, p_241830_3_);
        if (optional.isPresent()) {
            return optional;
        }
        b_257_Y.n_1700_B direction$axis = this.O_508_d.getBlockState(this.R_3077_Z).G_564_y(O_3671_t.P_4830_p).orElse(b_257_Y.n_1700_B.n_1700_B);
        Optional<BlockUtil.J_1907_R> optional1 = p_241830_1_.z_1333_t().n_1700_B(p_241830_2_, direction$axis);
        if (!optional1.isPresent()) {
            v_4262_N.error("Unable to create a portal, likely target out of worldborder");
        }
        return optional1;
    }

    private void G_564_y(e_3591_l p_213846_1_) {
        f_2392_k<b_4507_u> registrykey = p_213846_1_.g_2268_R();
        f_2392_k<b_4507_u> registrykey1 = this.O_508_d.g_2268_R();
        U_3554_Q.Q_2552_b.n_1700_B(this, registrykey, registrykey1);
        if (registrykey == b_4507_u.v_4262_N && registrykey1 == b_4507_u.u_1723_Y && this.x_607_J != null) {
            U_3554_Q.A_4115_X.n_1700_B(this, this.x_607_J);
        }
        if (registrykey1 != b_4507_u.v_4262_N) {
            this.x_607_J = null;
        }
    }

    @Override
    public boolean n_1700_B(B_4088_l player) {
        if (player.d_2461_k()) {
            return this.T_2506_i() == this;
        }
        return this.d_2461_k() ? false : super.n_1700_B(player);
    }

    private void n_1700_B(i_2154_H p_147097_1_) {
        ClientboundBlockEntityDataPacket supdatetileentitypacket;
        if (p_147097_1_ != null && (supdatetileentitypacket = p_147097_1_.G_()) != null) {
            this.n_1700_B.n_1700_B(supdatetileentitypacket);
        }
    }

    @Override
    public void n_1700_B(N_4263_v entityIn, int quantity) {
        super.n_1700_B(entityIn, quantity);
        this.H_1873_g.M_588_G();
    }

    @Override
    public Either<a_3913_L.n_1700_B, X_1446_C> n_1700_B(c_1514_x at) {
        b_257_Y direction = this.O_508_d.getBlockState(at).R_4764_Y(HorizontalDirectionalBlock.w_612_n);
        if (!this.z_2372_L() && this.RealmsLongRunningMcoTaskScreen()) {
            if (!this.O_508_d.G_624_v().P_1922_E()) {
                return Either.left((Object)((Object)a_3913_L.n_1700_B.n_1700_B));
            }
            if (!this.n_1700_B(at, direction)) {
                return Either.left((Object)((Object)a_3913_L.n_1700_B.R_4764_Y));
            }
            if (this.J_1907_R(at, direction)) {
                return Either.left((Object)((Object)a_3913_L.n_1700_B.G_564_y));
            }
            this.n_1700_B(this.O_508_d.g_2268_R(), at, this.p_178_J, false, true);
            if (this.O_508_d.q_4610_l()) {
                return Either.left((Object)((Object)a_3913_L.n_1700_B.J_1907_R));
            }
            if (!this.G_624_v()) {
                double d0 = 8.0;
                double d1 = 5.0;
                e_2866_D vector3d = e_2866_D.R_4764_Y(at);
                List<Monster> list = this.O_508_d.n_1700_B(Monster.class, new I_4817_s(vector3d.n_1700_B() - 8.0, vector3d.J_1907_R() - 5.0, vector3d.R_4764_Y() - 8.0, vector3d.n_1700_B() + 8.0, vector3d.J_1907_R() + 5.0, vector3d.R_4764_Y() + 8.0), (? super T p_241146_1_) -> p_241146_1_.P_1922_E(this));
                if (!list.isEmpty()) {
                    return Either.left((Object)((Object)a_3913_L.n_1700_B.u_1723_Y));
                }
            }
            Either either = super.n_1700_B(at).ifRight(p_241144_1_ -> {
                this.J_1907_R(Stats.UploadStatus);
                U_3554_Q.t_1786_h.n_1700_B(this);
            });
            ((e_3591_l)this.O_508_d).u_1723_Y();
            return either;
        }
        return Either.left((Object)((Object)a_3913_L.n_1700_B.P_1922_E));
    }

    @Override
    public void P_1922_E(c_1514_x pos) {
        this.J_1907_R(Stats.t_148_a.J_1907_R(Stats.P_4830_p));
        super.P_1922_E(pos);
    }

    private boolean n_1700_B(c_1514_x p_241147_1_, b_257_Y p_241147_2_) {
        return this.v_4262_N(p_241147_1_) || this.v_4262_N(p_241147_1_.offset(p_241147_2_.u_1723_Y()));
    }

    private boolean v_4262_N(c_1514_x p_241158_1_) {
        e_2866_D vector3d = e_2866_D.R_4764_Y(p_241158_1_);
        return Math.abs(this.O_3598_v() - vector3d.n_1700_B()) <= 3.0 && Math.abs(this.X_2960_b() - vector3d.J_1907_R()) <= 2.0 && Math.abs(this.l_2647_k() - vector3d.R_4764_Y()) <= 3.0;
    }

    private boolean J_1907_R(c_1514_x p_241156_1_, b_257_Y p_241156_2_) {
        c_1514_x blockpos = p_241156_1_.up();
        return !this.u_1723_Y(blockpos) || !this.u_1723_Y(blockpos.offset(p_241156_2_.u_1723_Y()));
    }

    @Override
    public void n_1700_B(boolean p_225652_1_, boolean p_225652_2_) {
        if (this.z_2372_L()) {
            this.c_3005_b().Y_259_p().n_1700_B(this, new q_3092_O(this, 2));
        }
        super.n_1700_B(p_225652_1_, p_225652_2_);
        if (this.n_1700_B != null) {
            this.n_1700_B.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.p_178_J, this.f_4016_n);
        }
    }

    @Override
    public boolean n_1700_B(N_4263_v entityIn, boolean force) {
        N_4263_v entity = this.l_3609_d();
        if (!super.n_1700_B(entityIn, force)) {
            return false;
        }
        N_4263_v entity1 = this.l_3609_d();
        if (entity1 != entity && this.n_1700_B != null) {
            this.n_1700_B.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.p_178_J, this.f_4016_n);
        }
        return true;
    }

    @Override
    public void A_3959_N() {
        N_4263_v entity = this.l_3609_d();
        super.A_3959_N();
        N_4263_v entity1 = this.l_3609_d();
        if (entity1 != entity && this.n_1700_B != null) {
            this.n_1700_B.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.p_178_J, this.f_4016_n);
        }
    }

    @Override
    public boolean n_1700_B(P_11_z source) {
        return super.n_1700_B(source) || this.g_221_o() || this.C_415_h.n_1700_B && source == P_11_z.M_182_A;
    }

    @Override
    protected void n_1700_B(double y, boolean onGroundIn, K_4074_S state, c_1514_x pos) {
    }

    @Override
    protected void R_4764_Y(c_1514_x pos) {
        if (!this.d_2461_k()) {
            super.R_4764_Y(pos);
        }
    }

    public void n_1700_B(double y, boolean onGroundIn) {
        c_1514_x blockpos = this.RealmsWorldOptions();
        if (this.O_508_d.M_588_G(blockpos)) {
            super.n_1700_B(y, onGroundIn, this.O_508_d.getBlockState(blockpos), blockpos);
        }
    }

    @Override
    public void n_1700_B(A_4313_D signTile) {
        signTile.n_1700_B((a_3913_L)this);
        this.n_1700_B.n_1700_B(new w_3005_z(signTile.x_607_J()));
    }

    private void s_2632_s() {
        this.G_624_v = this.G_624_v % 100 + 1;
    }

    @Override
    public OptionalInt n_1700_B(@Nullable t_3286_u p_213829_1_) {
        if (p_213829_1_ == null) {
            return OptionalInt.empty();
        }
        if (this.H_1873_g != this.o_1800_r) {
            this.P_1922_E();
        }
        this.s_2632_s();
        a_2900_S container = p_213829_1_.createMenu(this.G_624_v, this.l_1268_F, this);
        if (container == null) {
            if (this.d_2461_k()) {
                this.n_1700_B((x_282_a)new F_2904_S("container.spectatorCantOpen").n_1700_B(D_4024_W.P_4830_p), true);
            }
            return OptionalInt.empty();
        }
        this.n_1700_B.n_1700_B(new ClientboundOpenScreenPacket(container.u_1723_Y, container.s_956_w(), p_213829_1_.c_()));
        container.n_1700_B(this);
        this.H_1873_g = container;
        return OptionalInt.of(this.G_624_v);
    }

    @Override
    public void n_1700_B(int containerId, MerchantOffers offers, int level, int xp, boolean p_213818_5_, boolean p_213818_6_) {
        this.n_1700_B.n_1700_B(new ClientboundMerchantOffersPacket(containerId, offers, level, xp, p_213818_5_, p_213818_6_));
    }

    @Override
    public void n_1700_B(U_2534_D horse, Container inventoryIn) {
        if (this.H_1873_g != this.o_1800_r) {
            this.P_1922_E();
        }
        this.s_2632_s();
        this.n_1700_B.n_1700_B(new t_3906_J(this.G_624_v, inventoryIn.Y_259_p(), horse.j_276_v()));
        this.H_1873_g = new R_3940_n(this.G_624_v, this.l_1268_F, inventoryIn, horse);
        this.H_1873_g.n_1700_B(this);
    }

    @Override
    public void n_1700_B(Z_1993_T stack, x_1688_C hand) {
        q_1613_l item = stack.J_1907_R();
        if (item == Items.CryingObsidianBlock) {
            if (B_477_D.n_1700_B(stack, this.A_3244_K(), this)) {
                this.H_1873_g.M_588_G();
            }
            this.n_1700_B.n_1700_B(new x_2680_y(hand));
        }
    }

    @Override
    public void n_1700_B(T_1368_k commandBlock) {
        commandBlock.R_4764_Y(true);
        this.n_1700_B((i_2154_H)commandBlock);
    }

    @Override
    public void n_1700_B(a_2900_S containerToSend, int slotInd, Z_1993_T stack) {
        if (!(containerToSend.n_1700_B(slotInd) instanceof ResultSlot)) {
            if (containerToSend == this.o_1800_r) {
                U_3554_Q.P_1922_E.n_1700_B(this, this.l_1268_F, stack);
            }
            if (!this.G_564_y) {
                this.n_1700_B.n_1700_B(new a_4764_N(containerToSend.u_1723_Y, slotInd, stack));
            }
        }
    }

    public void n_1700_B(a_2900_S containerIn) {
        this.n_1700_B(containerIn, containerIn.u_2550_I());
    }

    @Override
    public void n_1700_B(a_2900_S containerToSend, NonNullList<Z_1993_T> itemsList) {
        this.n_1700_B.n_1700_B(new ClientboundContainerSetContentPacket(containerToSend.u_1723_Y, itemsList));
        this.n_1700_B.n_1700_B(new a_4764_N(-1, -1, this.l_1268_F.s_956_w()));
    }

    @Override
    public void n_1700_B(a_2900_S containerIn, int varToUpdate, int newValue) {
        this.n_1700_B.n_1700_B(new X_821_u(containerIn.u_1723_Y, varToUpdate, newValue));
    }

    @Override
    public void P_1922_E() {
        this.n_1700_B.n_1700_B(new v_4727_z(this.H_1873_g.u_1723_Y));
        this.M_182_A();
    }

    public void Q_4569_t() {
        if (!this.G_564_y) {
            this.n_1700_B.n_1700_B(new a_4764_N(-1, -1, this.l_1268_F.s_956_w()));
        }
    }

    public void M_182_A() {
        this.H_1873_g.J_1907_R(this);
        this.H_1873_g = this.o_1800_r;
    }

    public void n_1700_B(float strafe, float forward, boolean jumping, boolean sneaking) {
        if (this.y_2772_m()) {
            if (strafe >= -1.0f && strafe <= 1.0f) {
                this.L_1362_X = strafe;
            }
            if (forward >= -1.0f && forward <= 1.0f) {
                this.L_4248_u = forward;
            }
            this.F_3572_x = jumping;
            this.t_148_a(sneaking);
        }
    }

    @Override
    public void n_1700_B(o_98_P<?> stat, int amount) {
        this.s_956_w.J_1907_R(this, stat, amount);
        this.U_3758_B().n_1700_B(stat, this.L_3570_A(), (v_4839_y p_195396_1_) -> p_195396_1_.n_1700_B(amount));
    }

    @Override
    public void J_1907_R(o_98_P<?> stat) {
        this.s_956_w.n_1700_B(this, stat, 0);
        this.U_3758_B().n_1700_B(stat, this.L_3570_A(), v_4839_y::R_4764_Y);
    }

    @Override
    public int J_1907_R(Collection<Recipe<?>> p_195065_1_) {
        return this.H_2857_Y.n_1700_B(p_195065_1_, this);
    }

    @Override
    public void n_1700_B(g_2336_b[] p_193102_1_) {
        ArrayList list = Lists.newArrayList();
        for (g_2336_b resourcelocation : p_193102_1_) {
            this.J_1907_R.ValueObject().n_1700_B(resourcelocation).ifPresent(list::add);
        }
        this.J_1907_R(list);
    }

    @Override
    public int R_4764_Y(Collection<Recipe<?>> p_195069_1_) {
        return this.H_2857_Y.J_1907_R(p_195069_1_, this);
    }

    @Override
    public void multiplayerClientSuggestionProvider(int p_195068_1_) {
        super.multiplayerClientSuggestionProvider(p_195068_1_);
        this.Y_601_j = -1;
    }

    public void multiplayerClientSuggestionProvider() {
        this.t_4043_B = true;
        this.C_3538_G();
        if (this.z_2372_L()) {
            this.n_1700_B(true, false);
        }
    }

    public boolean C_2741_M() {
        return this.t_4043_B;
    }

    public void k_2293_S() {
        this.t_1786_h = -1.0E8f;
    }

    @Override
    public void n_1700_B(x_282_a chatComponent, boolean actionBar) {
        this.n_1700_B.n_1700_B(new ClientboundChatPacket(chatComponent, actionBar ? Y_408_h.R_4764_Y : Y_408_h.n_1700_B, j_3341_s.J_1907_R));
    }

    @Override
    protected void I_3637_j() {
        if (!this.O_4761_U.n_1700_B() && this.Y_601_j()) {
            this.n_1700_B.n_1700_B(new C_1375_J(this, 9));
            super.I_3637_j();
        }
    }

    @Override
    public void n_1700_B(EntityAnchorArgument.n_1700_B anchor, e_2866_D target) {
        super.n_1700_B(anchor, target);
        this.n_1700_B.n_1700_B(new ClientboundPlayerLookAtPacket(anchor, target.J_1907_R, target.R_4764_Y, target.G_564_y));
    }

    public void n_1700_B(EntityAnchorArgument.n_1700_B p_200618_1_, N_4263_v p_200618_2_, EntityAnchorArgument.n_1700_B p_200618_3_) {
        e_2866_D vector3d = p_200618_3_.n_1700_B(p_200618_2_);
        super.n_1700_B(p_200618_1_, vector3d);
        this.n_1700_B.n_1700_B(new ClientboundPlayerLookAtPacket(p_200618_1_, p_200618_2_, p_200618_3_));
    }

    public void n_1700_B(B_4088_l that, boolean keepEverything) {
        if (keepEverything) {
            this.l_1268_F.n_1700_B(that.l_1268_F);
            this.t_1786_h(that.g_46_E());
            this.n_3864_h = that.n_3864_h;
            this.v_165_F = that.v_165_F;
            this.s_4990_V = that.s_4990_V;
            this.b_2312_j = that.b_2312_j;
            this.J_1907_R(that.r_4414_L());
            this.R_3077_Z = that.R_3077_Z;
        } else if (this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.R_4764_Y) || that.d_2461_k()) {
            this.l_1268_F.n_1700_B(that.l_1268_F);
            this.v_165_F = that.v_165_F;
            this.s_4990_V = that.s_4990_V;
            this.b_2312_j = that.b_2312_j;
            this.J_1907_R(that.r_4414_L());
        }
        this.I_4348_c = that.I_4348_c;
        this.J_303_C = that.J_303_C;
        this.D_60_a().J_1907_R(X_1313_W, (Byte)that.D_60_a().n_1700_B(X_1313_W));
        this.Y_601_j = -1;
        this.t_1786_h = -1.0f;
        this.multiplayerClientSuggestionProvider = -1;
        this.H_2857_Y.n_1700_B(that.H_2857_Y);
        this.w_1484_f.addAll(that.w_1484_f);
        this.c_3005_b = that.c_3005_b;
        this.x_607_J = that.x_607_J;
        this.w_1484_f(that.A_1306_N());
        this.t_148_a(that.D_3612_q());
    }

    @Override
    protected void G_564_y(k_2610_C id) {
        super.G_564_y(id);
        this.n_1700_B.n_1700_B(new ClientboundUpdateMobEffectPacket(this.j_276_v(), id));
        if (id.n_1700_B() == MobEffects.q_2307_F) {
            this.Y_1740_V = this.RealmsWorldResetDto;
            this.A_4115_X = this.s_4990_V();
        }
        U_3554_Q.c_3005_b.n_1700_B(this);
    }

    @Override
    protected void n_1700_B(k_2610_C id, boolean reapply) {
        super.n_1700_B(id, reapply);
        this.n_1700_B.n_1700_B(new ClientboundUpdateMobEffectPacket(this.j_276_v(), id));
        U_3554_Q.c_3005_b.n_1700_B(this);
    }

    @Override
    protected void P_1922_E(k_2610_C effect) {
        super.P_1922_E(effect);
        this.n_1700_B.n_1700_B(new ClientboundRemoveMobEffectPacket(this.j_276_v(), effect.n_1700_B()));
        if (effect.n_1700_B() == MobEffects.q_2307_F) {
            this.A_4115_X = null;
        }
        U_3554_Q.c_3005_b.n_1700_B(this);
    }

    @Override
    public void P_4830_p(double x, double y, double z) {
        this.n_1700_B.n_1700_B(x, y, z, this.p_178_J, this.f_4016_n);
    }

    @Override
    public void P_1922_E(double x, double y, double z) {
        this.P_4830_p(x, y, z);
        this.n_1700_B.J_1907_R();
    }

    @Override
    public void n_1700_B(N_4263_v entityHit) {
        this.c_3005_b().Y_259_p().n_1700_B(this, new q_3092_O(entityHit, 4));
    }

    @Override
    public void J_1907_R(N_4263_v entityHit) {
        this.c_3005_b().Y_259_p().n_1700_B(this, new q_3092_O(entityHit, 5));
    }

    @Override
    public void v_4262_N() {
        if (this.n_1700_B != null) {
            this.n_1700_B.n_1700_B(new ClientboundPlayerAbilitiesPacket(this.C_415_h));
            this.f_691_R();
        }
    }

    public e_3591_l c_3005_b() {
        return (e_3591_l)this.O_508_d;
    }

    @Override
    public void n_1700_B(I_14_v gameType) {
        this.R_4764_Y.n_1700_B(gameType);
        this.n_1700_B.n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.G_564_y, gameType.n_1700_B()));
        if (gameType == I_14_v.P_1922_E) {
            this.o_4117_e();
            this.A_3959_N();
        } else {
            this.t_4043_B(this);
        }
        this.v_4262_N();
        this.k_2348_i();
    }

    @Override
    public boolean d_2461_k() {
        return this.R_4764_Y.J_1907_R() == I_14_v.P_1922_E;
    }

    @Override
    public boolean G_624_v() {
        return this.R_4764_Y.J_1907_R() == I_14_v.R_4764_Y;
    }

    @Override
    public void n_1700_B(x_282_a component, UUID senderUUID) {
        this.n_1700_B(component, Y_408_h.J_1907_R, senderUUID);
    }

    public void n_1700_B(x_282_a p_241151_1_, Y_408_h p_241151_2_, UUID p_241151_3_) {
        this.n_1700_B.n_1700_B(new ClientboundChatPacket(p_241151_1_, p_241151_2_, p_241151_3_), (GenericFutureListener<? extends Future<? super Void>>)((GenericFutureListener)p_241149_4_ -> {
            if (!(p_241149_4_.isSuccess() || p_241151_2_ != Y_408_h.R_4764_Y && p_241151_2_ != Y_408_h.J_1907_R)) {
                int i = 256;
                String s = p_241151_1_.n_1700_B(256);
                MutableComponent itextcomponent = new U_2871_b(s).n_1700_B(D_4024_W.Q_4569_t);
                this.n_1700_B.n_1700_B(new ClientboundChatPacket(new F_2904_S("multiplayer.message_not_delivered", itextcomponent).n_1700_B(D_4024_W.P_4830_p), Y_408_h.J_1907_R, p_241151_3_));
            }
        }));
    }

    public String A_4115_X() {
        String s = this.n_1700_B.n_1700_B.R_4764_Y().toString();
        s = s.substring(s.indexOf("/") + 1);
        return s.substring(0, s.indexOf(":"));
    }

    public void n_1700_B(ServerboundClientInformationPacket packetIn) {
        this.Q_2552_b = packetIn.J_1907_R();
        this.C_2741_M = packetIn.R_4764_Y();
        this.D_60_a().J_1907_R(X_1313_W, (byte)packetIn.G_564_y());
        this.D_60_a().J_1907_R(x_4991_F, (byte)(packetIn.P_1922_E() != k_4231_L.n_1700_B ? 1 : 0));
    }

    public g_4418_P t_4043_B() {
        return this.Q_2552_b;
    }

    public void n_1700_B(String url, String hash) {
        this.n_1700_B.n_1700_B(new E_3520_U(url, hash));
    }

    @Override
    protected int t_1786_h() {
        return this.J_1907_R.n_1700_B(this.y_4642_Y());
    }

    public void e_4240_b() {
        this.k_2293_S = j_3341_s.J_1907_R();
    }

    public k_2895_h n_3318_d() {
        return this.s_956_w;
    }

    public ServerRecipeBook d_2427_y() {
        return this.H_2857_Y;
    }

    public void A_4115_X(N_4263_v entityIn) {
        if (entityIn instanceof a_3913_L) {
            this.n_1700_B.n_1700_B(new ClientboundRemoveEntitiesPacket(entityIn.j_276_v()));
        } else {
            this.w_1484_f.add(entityIn.j_276_v());
        }
    }

    public void Y_1740_V(N_4263_v entityIn) {
        this.w_1484_f.remove((Object)entityIn.j_276_v());
    }

    @Override
    protected void f_691_R() {
        if (this.d_2461_k()) {
            this.I_4481_g();
            this.M_588_G(true);
        } else {
            super.f_691_R();
        }
    }

    public N_4263_v T_2506_i() {
        return this.q_2307_F == null ? this : this.q_2307_F;
    }

    public void t_4043_B(N_4263_v entityToSpectate) {
        N_4263_v entity = this.T_2506_i();
        N_4263_v n_4263_v = this.q_2307_F = entityToSpectate == null ? this : entityToSpectate;
        if (entity != this.q_2307_F) {
            this.n_1700_B.n_1700_B(new ClientboundSetCameraPacket(this.q_2307_F));
            this.P_4830_p(this.q_2307_F.O_3598_v(), this.q_2307_F.X_2960_b(), this.q_2307_F.l_2647_k());
        }
    }

    @Override
    protected void U_1241_n() {
        if (!this.Z_875_P) {
            super.U_1241_n();
        }
    }

    @Override
    public void H_2857_Y(N_4263_v targetEntity) {
        if (this.R_4764_Y.J_1907_R() == I_14_v.P_1922_E) {
            this.t_4043_B(targetEntity);
        } else {
            super.H_2857_Y(targetEntity);
        }
    }

    public long q_4610_l() {
        return this.k_2293_S;
    }

    @Nullable
    public x_282_a z_4693_k() {
        return null;
    }

    @Override
    public void n_1700_B(x_1688_C hand) {
        super.n_1700_B(hand);
        this.ModuleCategory();
    }

    public boolean g_221_o() {
        return this.Z_875_P;
    }

    public void e_2887_G() {
        this.Z_875_P = false;
    }

    public S_4998_h g_164_R() {
        return this.t_148_a;
    }

    public void n_1700_B(e_3591_l newWorld, double x, double y, double z, float yaw, float pitch) {
        this.t_4043_B(this);
        this.A_3959_N();
        if (newWorld == this.O_508_d) {
            this.n_1700_B.n_1700_B(x, y, z, yaw, pitch);
        } else {
            e_3591_l serverworld = this.c_3005_b();
            LevelData iworldinfo = newWorld.k_2293_S();
            this.n_1700_B.n_1700_B(new ClientboundRespawnPacket(newWorld.G_624_v(), newWorld.g_2268_R(), BiomeManager.n_1700_B(newWorld.n_1700_B()), this.R_4764_Y.J_1907_R(), this.R_4764_Y.R_4764_Y(), newWorld.l_1233_K(), newWorld.j_276_v(), true));
            this.n_1700_B.n_1700_B(new ClientboundChangeDifficultyPacket(iworldinfo.u_2550_I(), iworldinfo.M_588_G()));
            this.J_1907_R.p_178_J().G_564_y(this);
            serverworld.P_1922_E(this);
            this.t_4219_U = false;
            this.J_1907_R(x, y, z, yaw, pitch);
            this.n_1700_B((b_4507_u)newWorld);
            newWorld.n_1700_B(this);
            this.G_564_y(serverworld);
            this.n_1700_B.n_1700_B(x, y, z, yaw, pitch);
            this.R_4764_Y.n_1700_B(newWorld);
            this.J_1907_R.p_178_J().n_1700_B(this, newWorld);
            this.J_1907_R.p_178_J().P_1922_E(this);
        }
    }

    @Nullable
    public c_1514_x X_933_l() {
        return this.d_2427_y;
    }

    public float Z_976_R() {
        return this.v_4276_D;
    }

    public f_2392_k<b_4507_u> H_1990_U() {
        return this.n_3318_d;
    }

    public boolean N_2525_X() {
        return this.z_1737_N;
    }

    public void n_1700_B(f_2392_k<b_4507_u> p_242111_1_, @Nullable c_1514_x p_242111_2_, float p_242111_3_, boolean p_242111_4_, boolean p_242111_5_) {
        if (p_242111_2_ != null) {
            boolean flag;
            boolean bl = flag = p_242111_2_.equals(this.d_2427_y) && p_242111_1_.equals(this.n_3318_d);
            if (p_242111_5_ && !flag) {
                this.n_1700_B((x_282_a)new F_2904_S("block.minecraft.set_spawn"), j_3341_s.J_1907_R);
            }
            this.d_2427_y = p_242111_2_;
            this.n_3318_d = p_242111_1_;
            this.v_4276_D = p_242111_3_;
            this.z_1737_N = p_242111_4_;
        } else {
            this.d_2427_y = null;
            this.n_3318_d = b_4507_u.u_1723_Y;
            this.v_4276_D = 0.0f;
            this.z_1737_N = false;
        }
    }

    public void n_1700_B(Y_1387_d p_213844_1_, Packet<?> p_213844_2_, Packet<?> p_213844_3_) {
        this.n_1700_B.n_1700_B(p_213844_3_);
        this.n_1700_B.n_1700_B(p_213844_2_);
    }

    public void n_1700_B(Y_1387_d p_213845_1_) {
        if (this.RealmsLongRunningMcoTaskScreen()) {
            this.n_1700_B.n_1700_B(new W_4148_E(p_213845_1_.J_1907_R, p_213845_1_.R_4764_Y));
        }
    }

    public SectionPos c_4037_x() {
        return this.e_4240_b;
    }

    public void n_1700_B(SectionPos sectionPosIn) {
        this.e_4240_b = sectionPosIn;
    }

    @Override
    public void n_1700_B(SoundEvent p_213823_1_, D_38_f p_213823_2_, float p_213823_3_, float p_213823_4_) {
        this.n_1700_B.n_1700_B(new ClientboundSoundPacket(p_213823_1_, p_213823_2_, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), p_213823_3_, p_213823_4_));
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddPlayerPacket(this);
    }

    @Override
    public n_1494_c n_1700_B(Z_1993_T droppedItem, boolean dropAround, boolean traceItem) {
        n_1494_c itementity = super.n_1700_B(droppedItem, dropAround, traceItem);
        if (itementity == null) {
            return null;
        }
        this.O_508_d.a_(itementity);
        Z_1993_T itemstack = itementity.P_1922_E();
        if (traceItem) {
            if (!itemstack.n_1700_B()) {
                this.n_1700_B(Stats.u_1723_Y.J_1907_R(itemstack.J_1907_R()), droppedItem.t_4043_B());
            }
            this.J_1907_R(Stats.t_4043_B);
        }
        return itementity;
    }

    @Nullable
    public TextFilter g_2268_R() {
        return this.d_2461_k;
    }
}




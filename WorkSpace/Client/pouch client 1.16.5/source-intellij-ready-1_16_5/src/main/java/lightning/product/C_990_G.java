/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ContiguousSet
 *  com.google.common.collect.DiscreteDomain
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Range
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.ChunkStatus;
import lightning.product.BlockPredicate;
import lightning.product.F_2904_S;
import lightning.product.F_4543_b;
import lightning.product.H_1748_a;
import lightning.product.I_408_V;
import lightning.product.EndPodiumFeature;
import lightning.product.I_4817_s;
import lightning.product.BlockPattern;
import lightning.product.TheEndPortalBlockEntity;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.Features;
import lightning.product.V_3354_l;
import lightning.product.X_1446_C;
import lightning.product.Y_1387_d;
import lightning.product.Z_1164_j;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_2971_b;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.e_3591_l;
import lightning.product.e_91_Z;
import lightning.product.BossEvent;
import lightning.product.i_2154_H;
import lightning.product.TicketType;
import lightning.product.ServerBossEvent;
import lightning.product.n_3832_I;
import lightning.product.BlockInWorld;
import lightning.product.q_2896_o;
import lightning.product.SpikeFeature;
import lightning.product.FeatureConfiguration;
import lightning.product.IntTag;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.y_3683_b;
import lightning.product.z_2963_s;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class C_990_G {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final Predicate<N_4263_v> J_1907_R = I_408_V.n_1700_B.and(I_408_V.n_1700_B(0.0, 128.0, 0.0, 192.0));
    private final ServerBossEvent R_4764_Y = (ServerBossEvent)new ServerBossEvent(new F_2904_S("entity.minecraft.ender_dragon"), BossEvent.n_1700_B.n_1700_B, BossEvent.J_1907_R.n_1700_B).J_1907_R(true).R_4764_Y(true);
    private final e_3591_l G_564_y;
    private final List<Integer> P_1922_E = Lists.newArrayList();
    private final BlockPattern u_1723_Y;
    private int v_4262_N;
    private int w_1484_f;
    private int t_148_a;
    private int s_956_w;
    private boolean u_2550_I;
    private boolean M_588_G;
    private UUID P_4830_p;
    private boolean h_1847_R = true;
    private c_1514_x Q_4569_t;
    private F_4543_b M_182_A;
    private int t_1786_h;
    private List<V_3354_l> multiplayerClientSuggestionProvider;

    public C_990_G(e_3591_l world, long seed, U_2912_j dragonFightNBT) {
        this.G_564_y = world;
        if (dragonFightNBT.R_4764_Y("DragonKilled", 99)) {
            if (dragonFightNBT.J_1907_R("Dragon")) {
                this.P_4830_p = dragonFightNBT.n_1700_B("Dragon");
            }
            this.u_2550_I = dragonFightNBT.t_1786_h("DragonKilled");
            this.M_588_G = dragonFightNBT.t_1786_h("PreviouslyKilled");
            if (dragonFightNBT.t_1786_h("IsRespawning")) {
                this.M_182_A = F_4543_b.n_1700_B;
            }
            if (dragonFightNBT.R_4764_Y("ExitPortalLocation", 10)) {
                this.Q_4569_t = n_3832_I.J_1907_R(dragonFightNBT.M_182_A("ExitPortalLocation"));
            }
        } else {
            this.u_2550_I = true;
            this.M_588_G = true;
        }
        if (dragonFightNBT.R_4764_Y("Gateways", 9)) {
            q_2896_o listnbt = dragonFightNBT.G_564_y("Gateways", 3);
            for (int i = 0; i < listnbt.size(); ++i) {
                this.P_1922_E.add(listnbt.P_1922_E(i));
            }
        } else {
            this.P_1922_E.addAll((Collection<Integer>)ContiguousSet.create((Range)Range.closedOpen((Comparable)Integer.valueOf(0), (Comparable)Integer.valueOf(20)), (DiscreteDomain)DiscreteDomain.integers()));
            Collections.shuffle(this.P_1922_E, new Random(seed));
        }
        this.u_1723_Y = e_91_Z.n_1700_B().n_1700_B("       ", "       ", "       ", "   #   ", "       ", "       ", "       ").n_1700_B("       ", "       ", "       ", "   #   ", "       ", "       ", "       ").n_1700_B("       ", "       ", "       ", "   #   ", "       ", "       ", "       ").n_1700_B("  ###  ", " #   # ", "#     #", "#  #  #", "#     #", " #   # ", "  ###  ").n_1700_B("       ", "  ###  ", " ##### ", " ##### ", " ##### ", "  ###  ", "       ").n_1700_B('#', BlockInWorld.n_1700_B(BlockPredicate.n_1700_B(a_3742_W.Z_875_P))).J_1907_R();
    }

    public U_2912_j n_1700_B() {
        U_2912_j compoundnbt = new U_2912_j();
        if (this.P_4830_p != null) {
            compoundnbt.n_1700_B("Dragon", this.P_4830_p);
        }
        compoundnbt.n_1700_B("DragonKilled", this.u_2550_I);
        compoundnbt.n_1700_B("PreviouslyKilled", this.M_588_G);
        if (this.Q_4569_t != null) {
            compoundnbt.n_1700_B("ExitPortalLocation", n_3832_I.n_1700_B(this.Q_4569_t));
        }
        q_2896_o listnbt = new q_2896_o();
        for (int i : this.P_1922_E) {
            listnbt.add(IntTag.n_1700_B(i));
        }
        compoundnbt.n_1700_B("Gateways", listnbt);
        return compoundnbt;
    }

    public void J_1907_R() {
        this.R_4764_Y.G_564_y(!this.u_2550_I);
        if (++this.s_956_w >= 20) {
            this.M_588_G();
            this.s_956_w = 0;
        }
        if (!this.R_4764_Y.M_182_A().isEmpty()) {
            this.G_564_y.Y_259_p().n_1700_B(TicketType.J_1907_R, new Y_1387_d(0, 0), 9, X_1446_C.n_1700_B);
            boolean flag = this.u_2550_I();
            if (this.h_1847_R && flag) {
                this.v_4262_N();
                this.h_1847_R = false;
            }
            if (this.M_182_A != null) {
                if (this.multiplayerClientSuggestionProvider == null && flag) {
                    this.M_182_A = null;
                    this.P_1922_E();
                }
                this.M_182_A.n_1700_B(this.G_564_y, this, this.multiplayerClientSuggestionProvider, this.t_1786_h++, this.Q_4569_t);
            }
            if (!this.u_2550_I) {
                if ((this.P_4830_p == null || ++this.v_4262_N >= 1200) && flag) {
                    this.w_1484_f();
                    this.v_4262_N = 0;
                }
                if (++this.t_148_a >= 100 && flag) {
                    this.P_4830_p();
                    this.t_148_a = 0;
                }
            }
        } else {
            this.G_564_y.Y_259_p().J_1907_R(TicketType.J_1907_R, new Y_1387_d(0, 0), 9, X_1446_C.n_1700_B);
        }
    }

    private void v_4262_N() {
        n_1700_B.info("Scanning for legacy world dragon fight...");
        boolean flag = this.t_148_a();
        if (flag) {
            n_1700_B.info("Found that the dragon has been killed in this world already.");
            this.M_588_G = true;
        } else {
            n_1700_B.info("Found that the dragon has not yet been killed in this world.");
            this.M_588_G = false;
            if (this.s_956_w() == null) {
                this.n_1700_B(false);
            }
        }
        List<b_2971_b> list = this.G_564_y.P_4830_p();
        if (list.isEmpty()) {
            this.u_2550_I = true;
        } else {
            b_2971_b enderdragonentity = list.get(0);
            this.P_4830_p = enderdragonentity.w_2705_t();
            n_1700_B.info("Found that there's a dragon still alive ({})", (Object)enderdragonentity);
            this.u_2550_I = false;
            if (!flag) {
                n_1700_B.info("But we didn't have a portal, let's remove it.");
                enderdragonentity.Ops();
                this.P_4830_p = null;
            }
        }
        if (!this.M_588_G && this.u_2550_I) {
            this.u_2550_I = false;
        }
    }

    private void w_1484_f() {
        List<b_2971_b> list = this.G_564_y.P_4830_p();
        if (list.isEmpty()) {
            n_1700_B.debug("Haven't seen the dragon, respawning it");
            this.Q_4569_t();
        } else {
            n_1700_B.debug("Haven't seen our dragon, but found another one to use.");
            this.P_4830_p = list.get(0).w_2705_t();
        }
    }

    protected void n_1700_B(F_4543_b state) {
        if (this.M_182_A == null) {
            throw new IllegalStateException("Dragon respawn isn't in progress, can't skip ahead in the animation.");
        }
        this.t_1786_h = 0;
        if (state == F_4543_b.P_1922_E) {
            this.M_182_A = null;
            this.u_2550_I = false;
            b_2971_b enderdragonentity = this.Q_4569_t();
            for (B_4088_l serverplayerentity : this.R_4764_Y.M_182_A()) {
                U_3554_Q.h_1847_R.n_1700_B(serverplayerentity, enderdragonentity);
            }
        } else {
            this.M_182_A = state;
        }
    }

    private boolean t_148_a() {
        for (int i = -8; i <= 8; ++i) {
            for (int j = -8; j <= 8; ++j) {
                H_1748_a chunk = this.G_564_y.u_1723_Y(i, j);
                for (i_2154_H tileentity : chunk.getTileEntityMap().values()) {
                    if (!(tileentity instanceof TheEndPortalBlockEntity)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    @Nullable
    private BlockPattern.J_1907_R s_956_w() {
        int k;
        for (int i = -8; i <= 8; ++i) {
            for (int j = -8; j <= 8; ++j) {
                H_1748_a chunk = this.G_564_y.u_1723_Y(i, j);
                for (i_2154_H tileentity : chunk.getTileEntityMap().values()) {
                    BlockPattern.J_1907_R blockpattern$patternhelper;
                    if (!(tileentity instanceof TheEndPortalBlockEntity) || (blockpattern$patternhelper = this.u_1723_Y.n_1700_B(this.G_564_y, tileentity.x_607_J())) == null) continue;
                    c_1514_x blockpos = blockpattern$patternhelper.n_1700_B(3, 3, 3).G_564_y();
                    if (this.Q_4569_t == null && blockpos.getX() == 0 && blockpos.getZ() == 0) {
                        this.Q_4569_t = blockpos;
                    }
                    return blockpattern$patternhelper;
                }
            }
        }
        for (int l = k = this.G_564_y.n_1700_B(z_2963_s.n_1700_B.P_1922_E, EndPodiumFeature.n_1700_B).getY(); l >= 0; --l) {
            BlockPattern.J_1907_R blockpattern$patternhelper1 = this.u_1723_Y.n_1700_B(this.G_564_y, new c_1514_x(EndPodiumFeature.n_1700_B.getX(), l, EndPodiumFeature.n_1700_B.getZ()));
            if (blockpattern$patternhelper1 == null) continue;
            if (this.Q_4569_t == null) {
                this.Q_4569_t = blockpattern$patternhelper1.n_1700_B(3, 3, 3).G_564_y();
            }
            return blockpattern$patternhelper1;
        }
        return null;
    }

    private boolean u_2550_I() {
        for (int i = -8; i <= 8; ++i) {
            for (int j = 8; j <= 8; ++j) {
                ChunkAccess ichunk = this.G_564_y.n_1700_B(i, j, ChunkStatus.P_4830_p, false);
                if (!(ichunk instanceof H_1748_a)) {
                    return false;
                }
                y_3683_b.G_564_y chunkholder$locationtype = ((H_1748_a)ichunk).getLocationType();
                if (chunkholder$locationtype.n_1700_B(y_3683_b.G_564_y.R_4764_Y)) continue;
                return false;
            }
        }
        return true;
    }

    private void M_588_G() {
        HashSet set = Sets.newHashSet();
        for (B_4088_l serverplayerentity : this.G_564_y.n_1700_B(J_1907_R)) {
            this.R_4764_Y.n_1700_B(serverplayerentity);
            set.add(serverplayerentity);
        }
        HashSet set1 = Sets.newHashSet(this.R_4764_Y.M_182_A());
        set1.removeAll(set);
        for (B_4088_l serverplayerentity1 : set1) {
            this.R_4764_Y.J_1907_R(serverplayerentity1);
        }
    }

    private void P_4830_p() {
        this.t_148_a = 0;
        this.w_1484_f = 0;
        for (SpikeFeature.n_1700_B endspikefeature$endspike : SpikeFeature.n_1700_B(this.G_564_y)) {
            this.w_1484_f += this.G_564_y.n_1700_B(V_3354_l.class, endspikefeature$endspike.u_1723_Y()).size();
        }
        n_1700_B.debug("Found {} end crystals still alive", (Object)this.w_1484_f);
    }

    public void n_1700_B(b_2971_b dragon) {
        if (dragon.w_2705_t().equals(this.P_4830_p)) {
            this.R_4764_Y.n_1700_B(0.0f);
            this.R_4764_Y.G_564_y(false);
            this.n_1700_B(true);
            this.h_1847_R();
            if (!this.M_588_G) {
                this.G_564_y.J_1907_R(this.G_564_y.n_1700_B(z_2963_s.n_1700_B.P_1922_E, EndPodiumFeature.n_1700_B), a_3742_W.F_391_H.multiplayerClientSuggestionProvider());
            }
            this.M_588_G = true;
            this.u_2550_I = true;
        }
    }

    private void h_1847_R() {
        if (!this.P_1922_E.isEmpty()) {
            int i = this.P_1922_E.remove(this.P_1922_E.size() - 1);
            int j = u_530_F.R_4764_Y(96.0 * Math.cos(2.0 * (-Math.PI + 0.15707963267948966 * (double)i)));
            int k = u_530_F.R_4764_Y(96.0 * Math.sin(2.0 * (-Math.PI + 0.15707963267948966 * (double)i)));
            this.n_1700_B(new c_1514_x(j, 75, k));
        }
    }

    private void n_1700_B(c_1514_x pos) {
        this.G_564_y.R_4764_Y(3000, pos, 0);
        Features.R_4764_Y.n_1700_B(this.G_564_y, this.G_564_y.Y_259_p().t_148_a(), new Random(), pos);
    }

    private void n_1700_B(boolean active) {
        EndPodiumFeature endpodiumfeature = new EndPodiumFeature(active);
        if (this.Q_4569_t == null) {
            this.Q_4569_t = this.G_564_y.n_1700_B(z_2963_s.n_1700_B.u_1723_Y, EndPodiumFeature.n_1700_B).down();
            while (this.G_564_y.getBlockState(this.Q_4569_t).n_1700_B(a_3742_W.Z_875_P) && this.Q_4569_t.getY() > this.G_564_y.d_2461_k()) {
                this.Q_4569_t = this.Q_4569_t.down();
            }
        }
        endpodiumfeature.J_1907_R(FeatureConfiguration.P_4830_p).n_1700_B(this.G_564_y, this.G_564_y.Y_259_p().t_148_a(), new Random(), this.Q_4569_t);
    }

    private b_2971_b Q_4569_t() {
        this.G_564_y.M_182_A(new c_1514_x(0, 128, 0));
        b_2971_b enderdragonentity = t_5_h.Y_601_j.n_1700_B(this.G_564_y);
        enderdragonentity.y_4642_Y().n_1700_B(Z_1164_j.n_1700_B);
        enderdragonentity.J_1907_R(0.0, 128.0, 0.0, this.G_564_y.w_1457_N.nextFloat() * 360.0f, 0.0f);
        this.G_564_y.a_(enderdragonentity);
        this.P_4830_p = enderdragonentity.w_2705_t();
        return enderdragonentity;
    }

    public void J_1907_R(b_2971_b dragonIn) {
        if (dragonIn.w_2705_t().equals(this.P_4830_p)) {
            this.R_4764_Y.n_1700_B(dragonIn.g_46_E() / dragonIn.L_1733_J());
            this.v_4262_N = 0;
            if (dragonIn.t_3452_g()) {
                this.R_4764_Y.n_1700_B(dragonIn.c_());
            }
        }
    }

    public int R_4764_Y() {
        return this.w_1484_f;
    }

    public void n_1700_B(V_3354_l crystal, P_11_z dmgSrc) {
        if (this.M_182_A != null && this.multiplayerClientSuggestionProvider.contains(crystal)) {
            n_1700_B.debug("Aborting respawn sequence");
            this.M_182_A = null;
            this.t_1786_h = 0;
            this.u_1723_Y();
            this.n_1700_B(true);
        } else {
            this.P_4830_p();
            N_4263_v entity = this.G_564_y.J_1907_R(this.P_4830_p);
            if (entity instanceof b_2971_b) {
                ((b_2971_b)entity).n_1700_B(crystal, crystal.b_2312_j(), dmgSrc);
            }
        }
    }

    public boolean G_564_y() {
        return this.M_588_G;
    }

    public void P_1922_E() {
        if (this.u_2550_I && this.M_182_A == null) {
            c_1514_x blockpos = this.Q_4569_t;
            if (blockpos == null) {
                n_1700_B.debug("Tried to respawn, but need to find the portal first.");
                BlockPattern.J_1907_R blockpattern$patternhelper = this.s_956_w();
                if (blockpattern$patternhelper == null) {
                    n_1700_B.debug("Couldn't find a portal, so we made one.");
                    this.n_1700_B(true);
                } else {
                    n_1700_B.debug("Found the exit portal & temporarily using it.");
                }
                blockpos = this.Q_4569_t;
            }
            ArrayList list1 = Lists.newArrayList();
            c_1514_x blockpos1 = blockpos.up(1);
            for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
                List<V_3354_l> list = this.G_564_y.n_1700_B(V_3354_l.class, new I_4817_s(blockpos1.offset(direction, 2)));
                if (list.isEmpty()) {
                    return;
                }
                list1.addAll(list);
            }
            n_1700_B.debug("Found all crystals, respawning dragon.");
            this.n_1700_B(list1);
        }
    }

    private void n_1700_B(List<V_3354_l> crystalsIn) {
        if (this.u_2550_I && this.M_182_A == null) {
            BlockPattern.J_1907_R blockpattern$patternhelper = this.s_956_w();
            while (blockpattern$patternhelper != null) {
                for (int i = 0; i < this.u_1723_Y.R_4764_Y(); ++i) {
                    for (int j = 0; j < this.u_1723_Y.J_1907_R(); ++j) {
                        for (int k = 0; k < this.u_1723_Y.n_1700_B(); ++k) {
                            BlockInWorld cachedblockinfo = blockpattern$patternhelper.n_1700_B(i, j, k);
                            if (!cachedblockinfo.n_1700_B().n_1700_B(a_3742_W.Z_875_P) && !cachedblockinfo.n_1700_B().n_1700_B(a_3742_W.M_2562_s)) continue;
                            this.G_564_y.J_1907_R(cachedblockinfo.G_564_y(), a_3742_W.e_1231_S.multiplayerClientSuggestionProvider());
                        }
                    }
                }
                blockpattern$patternhelper = this.s_956_w();
            }
            this.M_182_A = F_4543_b.n_1700_B;
            this.t_1786_h = 0;
            this.n_1700_B(false);
            this.multiplayerClientSuggestionProvider = crystalsIn;
        }
    }

    public void u_1723_Y() {
        for (SpikeFeature.n_1700_B endspikefeature$endspike : SpikeFeature.n_1700_B(this.G_564_y)) {
            for (V_3354_l endercrystalentity : this.G_564_y.n_1700_B(V_3354_l.class, endspikefeature$endspike.u_1723_Y())) {
                endercrystalentity.Q_4569_t(false);
                endercrystalentity.n_1700_B((c_1514_x)null);
            }
        }
    }
}



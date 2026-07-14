/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.ClientboundSetObjectivePacket;
import lightning.product.Objective;
import lightning.product.PlayerTeam;
import lightning.product.ClientboundSetPlayerTeamPacket;
import lightning.product.i_4895_l;
import lightning.product.ClientboundSetDisplayObjectivePacket;
import lightning.product.Packet;
import lightning.product.v_4839_y;
import lightning.product.ClientboundSetScorePacket;
import net.minecraft.server.G_564_y;

public class ServerScoreboard
extends i_4895_l {
    private final G_564_y n_1700_B;
    private final Set<Objective> J_1907_R = Sets.newHashSet();
    private Runnable[] R_4764_Y = new Runnable[0];

    public ServerScoreboard(G_564_y mcServer) {
        this.n_1700_B = mcServer;
    }

    @Override
    public void n_1700_B(v_4839_y scoreIn) {
        super.n_1700_B(scoreIn);
        if (this.J_1907_R.contains(scoreIn.G_564_y())) {
            this.n_1700_B.p_178_J().n_1700_B(new ClientboundSetScorePacket(lightning.product.ServerScoreboard$n_1700_B.n_1700_B, scoreIn.G_564_y().J_1907_R(), scoreIn.P_1922_E(), scoreIn.J_1907_R()));
        }
        this.w_1484_f();
    }

    @Override
    public void t_148_a(String scoreName) {
        super.t_148_a(scoreName);
        this.n_1700_B.p_178_J().n_1700_B(new ClientboundSetScorePacket(lightning.product.ServerScoreboard$n_1700_B.J_1907_R, null, scoreName, 0));
        this.w_1484_f();
    }

    @Override
    public void G_564_y(String scoreName, Objective objective) {
        super.G_564_y(scoreName, objective);
        if (this.J_1907_R.contains(objective)) {
            this.n_1700_B.p_178_J().n_1700_B(new ClientboundSetScorePacket(lightning.product.ServerScoreboard$n_1700_B.J_1907_R, objective.J_1907_R(), scoreName, 0));
        }
        this.w_1484_f();
    }

    @Override
    public void n_1700_B(int objectiveSlot, @Nullable Objective objective) {
        Objective scoreobjective = this.n_1700_B(objectiveSlot);
        super.n_1700_B(objectiveSlot, objective);
        if (scoreobjective != objective && scoreobjective != null) {
            if (this.s_956_w(scoreobjective) > 0) {
                this.n_1700_B.p_178_J().n_1700_B(new ClientboundSetDisplayObjectivePacket(objectiveSlot, objective));
            } else {
                this.t_148_a(scoreobjective);
            }
        }
        if (objective != null) {
            if (this.J_1907_R.contains(objective)) {
                this.n_1700_B.p_178_J().n_1700_B(new ClientboundSetDisplayObjectivePacket(objectiveSlot, objective));
            } else {
                this.v_4262_N(objective);
            }
        }
        this.w_1484_f();
    }

    @Override
    public boolean n_1700_B(String p_197901_1_, PlayerTeam p_197901_2_) {
        if (super.n_1700_B(p_197901_1_, p_197901_2_)) {
            this.n_1700_B.p_178_J().n_1700_B(new ClientboundSetPlayerTeamPacket(p_197901_2_, Arrays.asList(p_197901_1_), 3));
            this.w_1484_f();
            return true;
        }
        return false;
    }

    @Override
    public void J_1907_R(String username, PlayerTeam playerTeam) {
        super.J_1907_R(username, playerTeam);
        this.n_1700_B.p_178_J().n_1700_B(new ClientboundSetPlayerTeamPacket(playerTeam, Arrays.asList(username), 4));
        this.w_1484_f();
    }

    @Override
    public void R_4764_Y(Objective objective) {
        super.R_4764_Y(objective);
        this.w_1484_f();
    }

    @Override
    public void G_564_y(Objective objective) {
        super.G_564_y(objective);
        if (this.J_1907_R.contains(objective)) {
            this.n_1700_B.p_178_J().n_1700_B(new ClientboundSetObjectivePacket(objective, 2));
        }
        this.w_1484_f();
    }

    @Override
    public void P_1922_E(Objective objective) {
        super.P_1922_E(objective);
        if (this.J_1907_R.contains(objective)) {
            this.t_148_a(objective);
        }
        this.w_1484_f();
    }

    @Override
    public void J_1907_R(PlayerTeam playerTeam) {
        super.J_1907_R(playerTeam);
        this.n_1700_B.p_178_J().n_1700_B(new ClientboundSetPlayerTeamPacket(playerTeam, 0));
        this.w_1484_f();
    }

    @Override
    public void R_4764_Y(PlayerTeam playerTeam) {
        super.R_4764_Y(playerTeam);
        this.n_1700_B.p_178_J().n_1700_B(new ClientboundSetPlayerTeamPacket(playerTeam, 2));
        this.w_1484_f();
    }

    @Override
    public void G_564_y(PlayerTeam playerTeam) {
        super.G_564_y(playerTeam);
        this.n_1700_B.p_178_J().n_1700_B(new ClientboundSetPlayerTeamPacket(playerTeam, 1));
        this.w_1484_f();
    }

    public void n_1700_B(Runnable runnable) {
        this.R_4764_Y = Arrays.copyOf(this.R_4764_Y, this.R_4764_Y.length + 1);
        this.R_4764_Y[this.R_4764_Y.length - 1] = runnable;
    }

    protected void w_1484_f() {
        for (Runnable runnable : this.R_4764_Y) {
            runnable.run();
        }
    }

    public List<Packet<?>> u_1723_Y(Objective objective) {
        ArrayList list = Lists.newArrayList();
        list.add(new ClientboundSetObjectivePacket(objective, 0));
        for (int i = 0; i < 19; ++i) {
            if (this.n_1700_B(i) != objective) continue;
            list.add(new ClientboundSetDisplayObjectivePacket(i, objective));
        }
        for (v_4839_y score : this.n_1700_B(objective)) {
            list.add(new ClientboundSetScorePacket(lightning.product.ServerScoreboard$n_1700_B.n_1700_B, score.G_564_y().J_1907_R(), score.P_1922_E(), score.J_1907_R()));
        }
        return list;
    }

    public void v_4262_N(Objective objective) {
        List<Packet<?>> list = this.u_1723_Y(objective);
        for (B_4088_l serverplayerentity : this.n_1700_B.p_178_J().w_1457_N()) {
            for (Packet<?> ipacket : list) {
                serverplayerentity.n_1700_B.n_1700_B(ipacket);
            }
        }
        this.J_1907_R.add(objective);
    }

    public List<Packet<?>> w_1484_f(Objective p_96548_1_) {
        ArrayList list = Lists.newArrayList();
        list.add(new ClientboundSetObjectivePacket(p_96548_1_, 1));
        for (int i = 0; i < 19; ++i) {
            if (this.n_1700_B(i) != p_96548_1_) continue;
            list.add(new ClientboundSetDisplayObjectivePacket(i, p_96548_1_));
        }
        return list;
    }

    public void t_148_a(Objective p_96546_1_) {
        List<Packet<?>> list = this.w_1484_f(p_96546_1_);
        for (B_4088_l serverplayerentity : this.n_1700_B.p_178_J().w_1457_N()) {
            for (Packet<?> ipacket : list) {
                serverplayerentity.n_1700_B.n_1700_B(ipacket);
            }
        }
        this.J_1907_R.remove(p_96546_1_);
    }

    public int s_956_w(Objective p_96552_1_) {
        int i = 0;
        for (int j = 0; j < 19; ++j) {
            if (this.n_1700_B(j) != p_96552_1_) continue;
            ++i;
        }
        return i;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] R_4764_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])R_4764_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.ServerScoreboard$n_1700_B.n_1700_B();
        }
    }
}



/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.M_1462_J;
import lightning.product.N_4263_v;
import lightning.product.U_2912_j;
import lightning.product.Objective;
import lightning.product.a_3913_L;
import lightning.product.PlayerTeam;
import lightning.product.q_2896_o;
import lightning.product.v_4839_y;
import lightning.product.x_282_a;

public class i_4895_l {
    private final Map<String, Objective> n_1700_B = Maps.newHashMap();
    private final Map<M_1462_J, List<Objective>> J_1907_R = Maps.newHashMap();
    private final Map<String, Map<Objective, v_4839_y>> R_4764_Y = Maps.newHashMap();
    private final Objective[] G_564_y = new Objective[19];
    private final Map<String, PlayerTeam> P_1922_E = Maps.newHashMap();
    private final Map<String, PlayerTeam> u_1723_Y = Maps.newHashMap();
    private static String[] v_4262_N;

    public boolean n_1700_B(String p_197900_1_) {
        return this.n_1700_B.containsKey(p_197900_1_);
    }

    public Objective J_1907_R(String p_197899_1_) {
        return this.n_1700_B.get(p_197899_1_);
    }

    @Nullable
    public Objective R_4764_Y(@Nullable String name) {
        return this.n_1700_B.get(name);
    }

    public Objective n_1700_B(String p_199868_1_, M_1462_J p_199868_2_, x_282_a p_199868_3_, M_1462_J.n_1700_B p_199868_4_) {
        if (p_199868_1_.length() > 16) {
            throw new IllegalArgumentException("The objective name '" + p_199868_1_ + "' is too long!");
        }
        if (this.n_1700_B.containsKey(p_199868_1_)) {
            throw new IllegalArgumentException("An objective with the name '" + p_199868_1_ + "' already exists!");
        }
        Objective scoreobjective = new Objective(this, p_199868_1_, p_199868_2_, p_199868_3_, p_199868_4_);
        this.J_1907_R.computeIfAbsent(p_199868_2_, p_197903_0_ -> Lists.newArrayList()).add(scoreobjective);
        this.n_1700_B.put(p_199868_1_, scoreobjective);
        this.R_4764_Y(scoreobjective);
        return scoreobjective;
    }

    public final void n_1700_B(M_1462_J p_197893_1_, String p_197893_2_, Consumer<v_4839_y> p_197893_3_) {
        this.J_1907_R.getOrDefault(p_197893_1_, Collections.emptyList()).forEach(p_197906_3_ -> p_197893_3_.accept(this.J_1907_R(p_197893_2_, (Objective)p_197906_3_)));
    }

    public boolean n_1700_B(String name, Objective objective) {
        Map<Objective, v_4839_y> map = this.R_4764_Y.get(name);
        if (map == null) {
            return false;
        }
        v_4839_y score = map.get(objective);
        return score != null;
    }

    public v_4839_y J_1907_R(String username, Objective objective) {
        if (username.length() > 40) {
            throw new IllegalArgumentException("The player name '" + username + "' is too long!");
        }
        Map map = this.R_4764_Y.computeIfAbsent(username, p_197898_0_ -> Maps.newHashMap());
        return map.computeIfAbsent(objective, p_197904_2_ -> {
            v_4839_y score = new v_4839_y(this, (Objective)p_197904_2_, username);
            score.J_1907_R(0);
            return score;
        });
    }

    public Collection<v_4839_y> n_1700_B(Objective objective) {
        ArrayList list = Lists.newArrayList();
        for (Map<Objective, v_4839_y> map : this.R_4764_Y.values()) {
            v_4839_y score = map.get(objective);
            if (score == null) continue;
            list.add(score);
        }
        list.sort(v_4839_y.n_1700_B);
        return list;
    }

    public Collection<Objective> n_1700_B() {
        return this.n_1700_B.values();
    }

    public Collection<String> J_1907_R() {
        return this.n_1700_B.keySet();
    }

    public Collection<String> R_4764_Y() {
        return Lists.newArrayList(this.R_4764_Y.keySet());
    }

    public void R_4764_Y(String name, @Nullable Objective objective) {
        if (objective == null) {
            Map<Objective, v_4839_y> map = this.R_4764_Y.remove(name);
            if (map != null) {
                this.t_148_a(name);
            }
        } else {
            Map<Objective, v_4839_y> map2 = this.R_4764_Y.get(name);
            if (map2 != null) {
                v_4839_y score = map2.remove(objective);
                if (map2.size() < 1) {
                    Map<Objective, v_4839_y> map1 = this.R_4764_Y.remove(name);
                    if (map1 != null) {
                        this.t_148_a(name);
                    }
                } else if (score != null) {
                    this.G_564_y(name, objective);
                }
            }
        }
    }

    public Map<Objective, v_4839_y> G_564_y(String name) {
        HashMap map = this.R_4764_Y.get(name);
        if (map == null) {
            map = Maps.newHashMap();
        }
        return map;
    }

    public void J_1907_R(Objective objective) {
        this.n_1700_B.remove(objective.J_1907_R());
        for (int i = 0; i < 19; ++i) {
            if (this.n_1700_B(i) != objective) continue;
            this.n_1700_B(i, (Objective)null);
        }
        List<Objective> list = this.J_1907_R.get(objective.R_4764_Y());
        if (list != null) {
            list.remove(objective);
        }
        for (Map<Objective, v_4839_y> map : this.R_4764_Y.values()) {
            map.remove(objective);
        }
        this.P_1922_E(objective);
    }

    public void n_1700_B(int objectiveSlot, @Nullable Objective objective) {
        this.G_564_y[objectiveSlot] = objective;
    }

    @Nullable
    public Objective n_1700_B(int slotIn) {
        return this.G_564_y[slotIn];
    }

    public PlayerTeam P_1922_E(String teamName) {
        return this.P_1922_E.get(teamName);
    }

    public PlayerTeam u_1723_Y(String name) {
        if (name.length() > 16) {
            throw new IllegalArgumentException("The team name '" + name + "' is too long!");
        }
        PlayerTeam scoreplayerteam = this.P_1922_E(name);
        if (scoreplayerteam != null) {
            throw new IllegalArgumentException("A team with the name '" + name + "' already exists!");
        }
        scoreplayerteam = new PlayerTeam(this, name);
        this.P_1922_E.put(name, scoreplayerteam);
        this.J_1907_R(scoreplayerteam);
        return scoreplayerteam;
    }

    public void n_1700_B(PlayerTeam playerTeam) {
        this.P_1922_E.remove(playerTeam.n_1700_B());
        for (String s : playerTeam.u_1723_Y()) {
            this.u_1723_Y.remove(s);
        }
        this.G_564_y(playerTeam);
    }

    public boolean n_1700_B(String p_197901_1_, PlayerTeam p_197901_2_) {
        if (p_197901_1_.length() > 40) {
            throw new IllegalArgumentException("The player name '" + p_197901_1_ + "' is too long!");
        }
        if (this.w_1484_f(p_197901_1_) != null) {
            this.v_4262_N(p_197901_1_);
        }
        this.u_1723_Y.put(p_197901_1_, p_197901_2_);
        return p_197901_2_.u_1723_Y().add(p_197901_1_);
    }

    public boolean v_4262_N(String playerName) {
        PlayerTeam scoreplayerteam = this.w_1484_f(playerName);
        if (scoreplayerteam != null) {
            this.J_1907_R(playerName, scoreplayerteam);
            return true;
        }
        return false;
    }

    public void J_1907_R(String username, PlayerTeam playerTeam) {
        if (this.w_1484_f(username) == playerTeam) {
            this.u_1723_Y.remove(username);
            playerTeam.u_1723_Y().remove(username);
        }
    }

    public Collection<String> G_564_y() {
        return this.P_1922_E.keySet();
    }

    public Collection<PlayerTeam> P_1922_E() {
        return this.P_1922_E.values();
    }

    @Nullable
    public PlayerTeam w_1484_f(String username) {
        return this.u_1723_Y.get(username);
    }

    public void R_4764_Y(Objective objective) {
    }

    public void G_564_y(Objective objective) {
    }

    public void P_1922_E(Objective objective) {
    }

    public void n_1700_B(v_4839_y scoreIn) {
    }

    public void t_148_a(String scoreName) {
    }

    public void G_564_y(String scoreName, Objective objective) {
    }

    public void J_1907_R(PlayerTeam playerTeam) {
    }

    public void R_4764_Y(PlayerTeam playerTeam) {
    }

    public void G_564_y(PlayerTeam playerTeam) {
    }

    public static String J_1907_R(int id) {
        D_4024_W textformatting;
        switch (id) {
            case 0: {
                return "list";
            }
            case 1: {
                return "sidebar";
            }
            case 2: {
                return "belowName";
            }
        }
        if (id >= 3 && id <= 18 && (textformatting = D_4024_W.n_1700_B(id - 3)) != null && textformatting != D_4024_W.Q_2552_b) {
            return "sidebar.team." + textformatting.P_1922_E();
        }
        return null;
    }

    public static int s_956_w(String name) {
        String s;
        D_4024_W textformatting;
        if ("list".equalsIgnoreCase(name)) {
            return 0;
        }
        if ("sidebar".equalsIgnoreCase(name)) {
            return 1;
        }
        if ("belowName".equalsIgnoreCase(name)) {
            return 2;
        }
        if (name.startsWith("sidebar.team.") && (textformatting = D_4024_W.J_1907_R(s = name.substring("sidebar.team.".length()))) != null && textformatting.n_1700_B() >= 0) {
            return textformatting.n_1700_B() + 3;
        }
        return -1;
    }

    public static String[] u_1723_Y() {
        if (v_4262_N == null) {
            v_4262_N = new String[19];
            for (int i = 0; i < 19; ++i) {
                i_4895_l.v_4262_N[i] = i_4895_l.J_1907_R(i);
            }
        }
        return v_4262_N;
    }

    public void n_1700_B(N_4263_v entityIn) {
        if (entityIn != null && !(entityIn instanceof a_3913_L) && !entityIn.RealmsLongRunningMcoTaskScreen()) {
            String s = entityIn.F_518_D();
            this.R_4764_Y(s, null);
            this.v_4262_N(s);
        }
    }

    protected q_2896_o v_4262_N() {
        q_2896_o listnbt = new q_2896_o();
        this.R_4764_Y.values().stream().map(Map::values).forEach(p_197894_1_ -> p_197894_1_.stream().filter(p_209546_0_ -> p_209546_0_.G_564_y() != null).forEach(p_197896_1_ -> {
            U_2912_j compoundnbt = new U_2912_j();
            compoundnbt.n_1700_B("Name", p_197896_1_.P_1922_E());
            compoundnbt.n_1700_B("Objective", p_197896_1_.G_564_y().J_1907_R());
            compoundnbt.J_1907_R("Score", p_197896_1_.J_1907_R());
            compoundnbt.n_1700_B("Locked", p_197896_1_.v_4262_N());
            listnbt.add(compoundnbt);
        }));
        return listnbt;
    }

    protected void n_1700_B(q_2896_o p_197905_1_) {
        for (int i = 0; i < p_197905_1_.size(); ++i) {
            U_2912_j compoundnbt = p_197905_1_.n_1700_B(i);
            Objective scoreobjective = this.J_1907_R(compoundnbt.M_588_G("Objective"));
            String s = compoundnbt.M_588_G("Name");
            if (s.length() > 40) {
                s = s.substring(0, 40);
            }
            v_4839_y score = this.J_1907_R(s, scoreobjective);
            score.J_1907_R(compoundnbt.w_1484_f("Score"));
            if (!compoundnbt.P_1922_E("Locked")) continue;
            score.n_1700_B(compoundnbt.t_1786_h("Locked"));
        }
    }
}



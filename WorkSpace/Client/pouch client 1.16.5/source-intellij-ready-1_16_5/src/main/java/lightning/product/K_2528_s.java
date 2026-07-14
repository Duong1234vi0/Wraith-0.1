/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import lightning.product.D_4024_W;
import lightning.product.StringTag;
import lightning.product.MutableComponent;
import lightning.product.M_1462_J;
import lightning.product.U_2912_j;
import lightning.product.Objective;
import lightning.product.PlayerTeam;
import lightning.product.i_4895_l;
import lightning.product.o_3050_h;
import lightning.product.q_2896_o;
import lightning.product.x_282_a;
import lightning.product.SavedData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class K_2528_s
extends SavedData {
    private static final Logger n_1700_B = LogManager.getLogger();
    private i_4895_l J_1907_R;
    private U_2912_j R_4764_Y;

    public K_2528_s() {
        super("scoreboard");
    }

    public void n_1700_B(i_4895_l scoreboardIn) {
        this.J_1907_R = scoreboardIn;
        if (this.R_4764_Y != null) {
            this.n_1700_B(this.R_4764_Y);
        }
    }

    @Override
    public void n_1700_B(U_2912_j nbt) {
        if (this.J_1907_R == null) {
            this.R_4764_Y = nbt;
        } else {
            this.J_1907_R(nbt.G_564_y("Objectives", 10));
            this.J_1907_R.n_1700_B(nbt.G_564_y("PlayerScores", 10));
            if (nbt.R_4764_Y("DisplaySlots", 10)) {
                this.J_1907_R(nbt.M_182_A("DisplaySlots"));
            }
            if (nbt.R_4764_Y("Teams", 9)) {
                this.n_1700_B(nbt.G_564_y("Teams", 10));
            }
        }
    }

    protected void n_1700_B(q_2896_o tagList) {
        for (int i = 0; i < tagList.size(); ++i) {
            o_3050_h.n_1700_B team$collisionrule;
            o_3050_h.J_1907_R team$visible1;
            o_3050_h.J_1907_R team$visible;
            MutableComponent itextcomponent2;
            MutableComponent itextcomponent1;
            U_2912_j compoundnbt = tagList.n_1700_B(i);
            String s = compoundnbt.M_588_G("Name");
            if (s.length() > 16) {
                s = s.substring(0, 16);
            }
            PlayerTeam scoreplayerteam = this.J_1907_R.u_1723_Y(s);
            MutableComponent itextcomponent = x_282_a.n_1700_B.n_1700_B(compoundnbt.M_588_G("DisplayName"));
            if (itextcomponent != null) {
                scoreplayerteam.n_1700_B(itextcomponent);
            }
            if (compoundnbt.R_4764_Y("TeamColor", 8)) {
                scoreplayerteam.n_1700_B(D_4024_W.J_1907_R(compoundnbt.M_588_G("TeamColor")));
            }
            if (compoundnbt.R_4764_Y("AllowFriendlyFire", 99)) {
                scoreplayerteam.n_1700_B(compoundnbt.t_1786_h("AllowFriendlyFire"));
            }
            if (compoundnbt.R_4764_Y("SeeFriendlyInvisibles", 99)) {
                scoreplayerteam.J_1907_R(compoundnbt.t_1786_h("SeeFriendlyInvisibles"));
            }
            if (compoundnbt.R_4764_Y("MemberNamePrefix", 8) && (itextcomponent1 = x_282_a.n_1700_B.n_1700_B(compoundnbt.M_588_G("MemberNamePrefix"))) != null) {
                scoreplayerteam.J_1907_R(itextcomponent1);
            }
            if (compoundnbt.R_4764_Y("MemberNameSuffix", 8) && (itextcomponent2 = x_282_a.n_1700_B.n_1700_B(compoundnbt.M_588_G("MemberNameSuffix"))) != null) {
                scoreplayerteam.R_4764_Y(itextcomponent2);
            }
            if (compoundnbt.R_4764_Y("NameTagVisibility", 8) && (team$visible = o_3050_h.J_1907_R.n_1700_B(compoundnbt.M_588_G("NameTagVisibility"))) != null) {
                scoreplayerteam.n_1700_B(team$visible);
            }
            if (compoundnbt.R_4764_Y("DeathMessageVisibility", 8) && (team$visible1 = o_3050_h.J_1907_R.n_1700_B(compoundnbt.M_588_G("DeathMessageVisibility"))) != null) {
                scoreplayerteam.J_1907_R(team$visible1);
            }
            if (compoundnbt.R_4764_Y("CollisionRule", 8) && (team$collisionrule = o_3050_h.n_1700_B.n_1700_B(compoundnbt.M_588_G("CollisionRule"))) != null) {
                scoreplayerteam.n_1700_B(team$collisionrule);
            }
            this.n_1700_B(scoreplayerteam, compoundnbt.G_564_y("Players", 8));
        }
    }

    protected void n_1700_B(PlayerTeam playerTeam, q_2896_o tagList) {
        for (int i = 0; i < tagList.size(); ++i) {
            this.J_1907_R.n_1700_B(tagList.t_148_a(i), playerTeam);
        }
    }

    protected void J_1907_R(U_2912_j compound) {
        for (int i = 0; i < 19; ++i) {
            if (!compound.R_4764_Y("slot_" + i, 8)) continue;
            String s = compound.M_588_G("slot_" + i);
            Objective scoreobjective = this.J_1907_R.R_4764_Y(s);
            this.J_1907_R.n_1700_B(i, scoreobjective);
        }
    }

    protected void J_1907_R(q_2896_o nbt) {
        for (int i = 0; i < nbt.size(); ++i) {
            U_2912_j compoundnbt = nbt.n_1700_B(i);
            M_1462_J.n_1700_B(compoundnbt.M_588_G("CriteriaName")).ifPresent(p_215164_2_ -> {
                String s = compoundnbt.M_588_G("Name");
                if (s.length() > 16) {
                    s = s.substring(0, 16);
                }
                MutableComponent itextcomponent = x_282_a.n_1700_B.n_1700_B(compoundnbt.M_588_G("DisplayName"));
                M_1462_J.n_1700_B scorecriteria$rendertype = M_1462_J.n_1700_B.n_1700_B(compoundnbt.M_588_G("RenderType"));
                this.J_1907_R.n_1700_B(s, (M_1462_J)p_215164_2_, itextcomponent, scorecriteria$rendertype);
            });
        }
    }

    @Override
    public U_2912_j R_4764_Y(U_2912_j compound) {
        if (this.J_1907_R == null) {
            n_1700_B.warn("Tried to save scoreboard without having a scoreboard...");
            return compound;
        }
        compound.n_1700_B("Objectives", this.J_1907_R());
        compound.n_1700_B("PlayerScores", this.J_1907_R.v_4262_N());
        compound.n_1700_B("Teams", this.n_1700_B());
        this.G_564_y(compound);
        return compound;
    }

    protected q_2896_o n_1700_B() {
        q_2896_o listnbt = new q_2896_o();
        for (PlayerTeam scoreplayerteam : this.J_1907_R.P_1922_E()) {
            U_2912_j compoundnbt = new U_2912_j();
            compoundnbt.n_1700_B("Name", scoreplayerteam.n_1700_B());
            compoundnbt.n_1700_B("DisplayName", x_282_a.n_1700_B.n_1700_B(scoreplayerteam.J_1907_R()));
            if (scoreplayerteam.P_4830_p().n_1700_B() >= 0) {
                compoundnbt.n_1700_B("TeamColor", scoreplayerteam.P_4830_p().P_1922_E());
            }
            compoundnbt.n_1700_B("AllowFriendlyFire", scoreplayerteam.v_4262_N());
            compoundnbt.n_1700_B("SeeFriendlyInvisibles", scoreplayerteam.w_1484_f());
            compoundnbt.n_1700_B("MemberNamePrefix", x_282_a.n_1700_B.n_1700_B(scoreplayerteam.G_564_y()));
            compoundnbt.n_1700_B("MemberNameSuffix", x_282_a.n_1700_B.n_1700_B(scoreplayerteam.P_1922_E()));
            compoundnbt.n_1700_B("NameTagVisibility", scoreplayerteam.t_148_a().P_1922_E);
            compoundnbt.n_1700_B("DeathMessageVisibility", scoreplayerteam.s_956_w().P_1922_E);
            compoundnbt.n_1700_B("CollisionRule", scoreplayerteam.u_2550_I().P_1922_E);
            q_2896_o listnbt1 = new q_2896_o();
            for (String s : scoreplayerteam.u_1723_Y()) {
                listnbt1.add(StringTag.n_1700_B(s));
            }
            compoundnbt.n_1700_B("Players", listnbt1);
            listnbt.add(compoundnbt);
        }
        return listnbt;
    }

    protected void G_564_y(U_2912_j compound) {
        U_2912_j compoundnbt = new U_2912_j();
        boolean flag = false;
        for (int i = 0; i < 19; ++i) {
            Objective scoreobjective = this.J_1907_R.n_1700_B(i);
            if (scoreobjective == null) continue;
            compoundnbt.n_1700_B("slot_" + i, scoreobjective.J_1907_R());
            flag = true;
        }
        if (flag) {
            compound.n_1700_B("DisplaySlots", compoundnbt);
        }
    }

    protected q_2896_o J_1907_R() {
        q_2896_o listnbt = new q_2896_o();
        for (Objective scoreobjective : this.J_1907_R.n_1700_B()) {
            if (scoreobjective.R_4764_Y() == null) continue;
            U_2912_j compoundnbt = new U_2912_j();
            compoundnbt.n_1700_B("Name", scoreobjective.J_1907_R());
            compoundnbt.n_1700_B("CriteriaName", scoreobjective.R_4764_Y().n_1700_B());
            compoundnbt.n_1700_B("DisplayName", x_282_a.n_1700_B.n_1700_B(scoreobjective.G_564_y()));
            compoundnbt.n_1700_B("RenderType", scoreobjective.u_1723_Y().n_1700_B());
            listnbt.add(compoundnbt);
        }
        return listnbt;
    }
}



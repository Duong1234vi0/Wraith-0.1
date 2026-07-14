/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Sets;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;
import lightning.product.ProfilerFiller;
import lightning.product.b_3075_S;
import lightning.product.Goal;
import net.optifine.util.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class E_194_H {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final b_3075_S J_1907_R = new b_3075_S(Integer.MAX_VALUE, new Goal(){

        @Override
        public boolean n_1700_B() {
            return false;
        }
    }){

        @Override
        public boolean v_4262_N() {
            return false;
        }
    };
    private final Map<Goal.n_1700_B, b_3075_S> R_4764_Y = new EnumMap<Goal.n_1700_B, b_3075_S>(Goal.n_1700_B.class);
    private final Set<b_3075_S> G_564_y = Sets.newLinkedHashSet();
    private final Supplier<ProfilerFiller> P_1922_E;
    private final EnumSet<Goal.n_1700_B> u_1723_Y = EnumSet.noneOf(Goal.n_1700_B.class);
    private int v_4262_N = 3;

    public E_194_H(Supplier<ProfilerFiller> profiler) {
        this.P_1922_E = profiler;
    }

    public void n_1700_B(int priority, Goal task) {
        this.G_564_y.add(new b_3075_S(priority, task));
    }

    public void n_1700_B(Goal task) {
        this.G_564_y.stream().filter(p_lambda$removeGoal$0_1_ -> p_lambda$removeGoal$0_1_.s_956_w() == task).filter(b_3075_S::v_4262_N).forEach(b_3075_S::G_564_y);
        this.G_564_y.removeIf(p_lambda$removeGoal$1_1_ -> p_lambda$removeGoal$1_1_.s_956_w() == task);
    }

    public void n_1700_B() {
        ProfilerFiller iprofiler = this.P_1922_E.get();
        iprofiler.n_1700_B("goalCleanup");
        if (this.G_564_y.size() > 0) {
            for (b_3075_S prioritizedgoal : this.G_564_y) {
                if (!prioritizedgoal.v_4262_N() || prioritizedgoal.v_4262_N() && !CollectionUtils.anyMatch(prioritizedgoal.t_148_a(), this.u_1723_Y) && prioritizedgoal.J_1907_R()) continue;
                prioritizedgoal.G_564_y();
            }
        }
        if (this.R_4764_Y.size() > 0) {
            this.R_4764_Y.forEach((p_lambda$tick$2_1_, p_lambda$tick$2_2_) -> {
                if (!p_lambda$tick$2_2_.v_4262_N()) {
                    this.R_4764_Y.remove(p_lambda$tick$2_1_);
                }
            });
        }
        iprofiler.R_4764_Y();
        iprofiler.n_1700_B("goalUpdate");
        if (this.G_564_y.size() > 0) {
            for (b_3075_S prioritizedgoal1 : this.G_564_y) {
                if (prioritizedgoal1.v_4262_N() || !CollectionUtils.noneMatch(prioritizedgoal1.t_148_a(), this.u_1723_Y) || !E_194_H.n_1700_B(prioritizedgoal1, prioritizedgoal1.t_148_a(), this.R_4764_Y) || !prioritizedgoal1.n_1700_B()) continue;
                E_194_H.J_1907_R(prioritizedgoal1, prioritizedgoal1.t_148_a(), this.R_4764_Y);
                prioritizedgoal1.R_4764_Y();
            }
        }
        iprofiler.R_4764_Y();
        iprofiler.n_1700_B("goalTick");
        if (this.G_564_y.size() > 0) {
            for (b_3075_S prioritizedgoal2 : this.G_564_y) {
                if (!prioritizedgoal2.v_4262_N()) continue;
                prioritizedgoal2.P_1922_E();
            }
        }
        iprofiler.R_4764_Y();
    }

    private static boolean n_1700_B(b_3075_S p_allPreemptedBy_0_, EnumSet<Goal.n_1700_B> p_allPreemptedBy_1_, Map<Goal.n_1700_B, b_3075_S> p_allPreemptedBy_2_) {
        if (p_allPreemptedBy_1_.isEmpty()) {
            return true;
        }
        for (Goal.n_1700_B goal$flag : p_allPreemptedBy_1_) {
            b_3075_S prioritizedgoal = p_allPreemptedBy_2_.getOrDefault((Object)goal$flag, J_1907_R);
            if (prioritizedgoal.n_1700_B(p_allPreemptedBy_0_)) continue;
            return false;
        }
        return true;
    }

    private static void J_1907_R(b_3075_S p_resetTasks_0_, EnumSet<Goal.n_1700_B> p_resetTasks_1_, Map<Goal.n_1700_B, b_3075_S> p_resetTasks_2_) {
        if (!p_resetTasks_1_.isEmpty()) {
            for (Goal.n_1700_B goal$flag : p_resetTasks_1_) {
                b_3075_S prioritizedgoal = p_resetTasks_2_.getOrDefault((Object)goal$flag, J_1907_R);
                prioritizedgoal.G_564_y();
                p_resetTasks_2_.put(goal$flag, p_resetTasks_0_);
            }
        }
    }

    public Stream<b_3075_S> J_1907_R() {
        return this.G_564_y.stream().filter(b_3075_S::v_4262_N);
    }

    public void n_1700_B(Goal.n_1700_B flag) {
        this.u_1723_Y.add(flag);
    }

    public void J_1907_R(Goal.n_1700_B flag) {
        this.u_1723_Y.remove((Object)flag);
    }

    public void n_1700_B(Goal.n_1700_B flag, boolean p_220878_2_) {
        if (p_220878_2_) {
            this.J_1907_R(flag);
        } else {
            this.n_1700_B(flag);
        }
    }
}



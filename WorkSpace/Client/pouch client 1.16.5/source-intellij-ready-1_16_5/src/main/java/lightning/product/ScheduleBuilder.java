/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;
import lightning.product.Activity;
import lightning.product.Schedule;

public class ScheduleBuilder {
    private final Schedule n_1700_B;
    private final List<n_1700_B> J_1907_R = Lists.newArrayList();

    public ScheduleBuilder(Schedule schedule) {
        this.n_1700_B = schedule;
    }

    public ScheduleBuilder n_1700_B(int duration, Activity activityIn) {
        this.J_1907_R.add(new n_1700_B(duration, activityIn));
        return this;
    }

    public Schedule n_1700_B() {
        this.J_1907_R.stream().map(n_1700_B::J_1907_R).collect(Collectors.toSet()).forEach(this.n_1700_B::n_1700_B);
        this.J_1907_R.forEach(activityEntry -> {
            Activity activity = activityEntry.J_1907_R();
            this.n_1700_B.R_4764_Y(activity).forEach(scheduleDuties -> scheduleDuties.n_1700_B(activityEntry.n_1700_B(), 0.0f));
            this.n_1700_B.J_1907_R(activity).n_1700_B(activityEntry.n_1700_B(), 1.0f);
        });
        return this.n_1700_B;
    }

    static class n_1700_B {
        private final int n_1700_B;
        private final Activity J_1907_R;

        public n_1700_B(int durationIn, Activity activityIn) {
            this.n_1700_B = durationIn;
            this.J_1907_R = activityIn;
        }

        public int n_1700_B() {
            return this.n_1700_B;
        }

        public Activity J_1907_R() {
            return this.J_1907_R;
        }
    }
}



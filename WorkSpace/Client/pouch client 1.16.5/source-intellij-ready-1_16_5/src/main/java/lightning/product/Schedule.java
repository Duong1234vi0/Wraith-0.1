/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lightning.product.V_3137_a;
import lightning.product.Activity;
import lightning.product.Timeline;
import lightning.product.ScheduleBuilder;

public class Schedule {
    public static final Schedule n_1700_B = Schedule.n_1700_B("empty").n_1700_B(0, Activity.J_1907_R).n_1700_B();
    public static final Schedule J_1907_R = Schedule.n_1700_B("simple").n_1700_B(5000, Activity.R_4764_Y).n_1700_B(11000, Activity.P_1922_E).n_1700_B();
    public static final Schedule R_4764_Y = Schedule.n_1700_B("villager_baby").n_1700_B(10, Activity.J_1907_R).n_1700_B(3000, Activity.G_564_y).n_1700_B(6000, Activity.J_1907_R).n_1700_B(10000, Activity.G_564_y).n_1700_B(12000, Activity.P_1922_E).n_1700_B();
    public static final Schedule G_564_y = Schedule.n_1700_B("villager_default").n_1700_B(10, Activity.J_1907_R).n_1700_B(2000, Activity.R_4764_Y).n_1700_B(9000, Activity.u_1723_Y).n_1700_B(11000, Activity.J_1907_R).n_1700_B(12000, Activity.P_1922_E).n_1700_B();
    private final Map<Activity, Timeline> P_1922_E = Maps.newHashMap();

    protected static ScheduleBuilder n_1700_B(String key) {
        Schedule schedule = V_3137_a.n_1700_B(V_3137_a.p_178_J, key, new Schedule());
        return new ScheduleBuilder(schedule);
    }

    protected void n_1700_B(Activity activityIn) {
        if (!this.P_1922_E.containsKey(activityIn)) {
            this.P_1922_E.put(activityIn, new Timeline());
        }
    }

    protected Timeline J_1907_R(Activity activityIn) {
        return this.P_1922_E.get(activityIn);
    }

    protected List<Timeline> R_4764_Y(Activity activityIn) {
        return this.P_1922_E.entrySet().stream().filter(entry -> entry.getKey() != activityIn).map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public Activity n_1700_B(int dayTime) {
        return this.P_1922_E.entrySet().stream().max(Comparator.comparingDouble(entry -> ((Timeline)entry.getValue()).n_1700_B(dayTime))).map(Map.Entry::getKey).orElse(Activity.J_1907_R);
    }
}



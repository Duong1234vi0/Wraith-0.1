/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  it.unimi.dsi.fastutil.ints.Int2ObjectAVLTreeMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectSortedMap
 */
package lightning.product;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.Int2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectSortedMap;
import java.util.Collection;
import java.util.List;
import lightning.product.Keyframe;

public class Timeline {
    private final List<Keyframe> n_1700_B = Lists.newArrayList();
    private int J_1907_R;

    public Timeline n_1700_B(int duration, float active) {
        this.n_1700_B.add(new Keyframe(duration, active));
        this.n_1700_B();
        return this;
    }

    private void n_1700_B() {
        Int2ObjectAVLTreeMap int2objectsortedmap = new Int2ObjectAVLTreeMap();
        this.n_1700_B.forEach(arg_0 -> Timeline.n_1700_B((Int2ObjectSortedMap)int2objectsortedmap, arg_0));
        this.n_1700_B.clear();
        this.n_1700_B.addAll((Collection<Keyframe>)int2objectsortedmap.values());
        this.J_1907_R = 0;
    }

    public float n_1700_B(int dayTime) {
        Keyframe dutytime2;
        if (this.n_1700_B.size() <= 0) {
            return 0.0f;
        }
        Keyframe dutytime = this.n_1700_B.get(this.J_1907_R);
        Keyframe dutytime1 = this.n_1700_B.get(this.n_1700_B.size() - 1);
        boolean flag = dayTime < dutytime.n_1700_B();
        int i = flag ? 0 : this.J_1907_R;
        float f = flag ? dutytime1.J_1907_R() : dutytime.J_1907_R();
        int j = i;
        while (j < this.n_1700_B.size() && (dutytime2 = this.n_1700_B.get(j)).n_1700_B() <= dayTime) {
            this.J_1907_R = j++;
            f = dutytime2.J_1907_R();
        }
        return f;
    }

    private static /* synthetic */ void n_1700_B(Int2ObjectSortedMap int2objectsortedmap, Keyframe dutyTime) {
        Keyframe dutytime = (Keyframe)int2objectsortedmap.put(dutyTime.n_1700_B(), (Object)dutyTime);
    }
}



/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import lightning.product.A_4115_X;
import lightning.product.O_1795_e;
import lightning.product.X_290_I;
import lightning.product.q_1613_l;
import lightning.product.u_530_F;

public class v_576_m {
    private final Map<q_1613_l, n_1700_B> n_1700_B = Maps.newHashMap();
    private int J_1907_R;

    public boolean n_1700_B(q_1613_l itemIn) {
        return this.n_1700_B(itemIn, 0.0f) > 0.0f;
    }

    public float n_1700_B(q_1613_l itemIn, float partialTicks) {
        n_1700_B cooldowntracker$cooldown = this.n_1700_B.get(itemIn);
        O_1795_e event = new O_1795_e(itemIn, 0.0f);
        A_4115_X.n_1700_B(event);
        if (cooldowntracker$cooldown != null) {
            float f = cooldowntracker$cooldown.J_1907_R - cooldowntracker$cooldown.n_1700_B;
            float f1 = (float)cooldowntracker$cooldown.J_1907_R - ((float)this.J_1907_R + partialTicks);
            return u_530_F.n_1700_B(f1 / f, 0.0f, 1.0f);
        }
        return event.R_4764_Y();
    }

    public void n_1700_B() {
        ++this.J_1907_R;
        if (!this.n_1700_B.isEmpty()) {
            Iterator<Map.Entry<q_1613_l, n_1700_B>> iterator = this.n_1700_B.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<q_1613_l, n_1700_B> entry = iterator.next();
                if (entry.getValue().J_1907_R > this.J_1907_R) continue;
                iterator.remove();
                this.R_4764_Y(entry.getKey());
            }
        }
    }

    public void n_1700_B(q_1613_l itemIn, int ticksIn) {
        X_290_I event = new X_290_I(itemIn, ticksIn);
        A_4115_X.n_1700_B(event);
        this.n_1700_B.put(itemIn, new n_1700_B(this, this.J_1907_R, this.J_1907_R + ticksIn));
        this.J_1907_R(itemIn, ticksIn);
    }

    public void J_1907_R(q_1613_l itemIn) {
        this.n_1700_B.remove(itemIn);
        this.R_4764_Y(itemIn);
    }

    protected void J_1907_R(q_1613_l itemIn, int ticksIn) {
    }

    protected void R_4764_Y(q_1613_l itemIn) {
    }

    public float J_1907_R(q_1613_l itemIn, float partialTicks) {
        n_1700_B cooldown = this.n_1700_B.get(itemIn);
        if (cooldown != null) {
            float remainingTicks = (float)cooldown.J_1907_R - ((float)this.J_1907_R + partialTicks);
            return Math.max(remainingTicks / 20.0f, 0.0f);
        }
        return 0.0f;
    }

    class n_1700_B {
        private final int n_1700_B;
        private final int J_1907_R;

        private n_1700_B(v_576_m this$0, int createTicksIn, int expireTicksIn) {
            this.n_1700_B = createTicksIn;
            this.J_1907_R = expireTicksIn;
        }
    }
}


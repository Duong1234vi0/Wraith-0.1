/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.stream.Stream;
import lightning.product.U_2912_j;
import lightning.product.g_2336_b;
import lightning.product.s_4380_l;
import lightning.product.SavedData;

public class J_4848_t {
    private final Map<String, n_1700_B> n_1700_B = Maps.newHashMap();
    private final s_4380_l J_1907_R;

    public J_4848_t(s_4380_l manager) {
        this.J_1907_R = manager;
    }

    private n_1700_B n_1700_B(String namespace, String name) {
        n_1700_B commandstorage$container = new n_1700_B(name);
        this.n_1700_B.put(namespace, commandstorage$container);
        return commandstorage$container;
    }

    public U_2912_j n_1700_B(g_2336_b id) {
        String s1;
        String s = id.R_4764_Y();
        n_1700_B commandstorage$container = this.J_1907_R.J_1907_R(() -> this.R_4764_Y(s, s1 = J_4848_t.n_1700_B(s)), s1);
        return commandstorage$container != null ? commandstorage$container.n_1700_B(id.J_1907_R()) : new U_2912_j();
    }

    public void n_1700_B(g_2336_b id, U_2912_j nbt) {
        String s = id.R_4764_Y();
        String s1 = J_4848_t.n_1700_B(s);
        this.J_1907_R.n_1700_B(() -> this.n_1700_B(s, s1), s1).n_1700_B(id.J_1907_R(), nbt);
    }

    public Stream<g_2336_b> n_1700_B() {
        return this.n_1700_B.entrySet().stream().flatMap(entry -> ((n_1700_B)entry.getValue()).J_1907_R((String)entry.getKey()));
    }

    private static String n_1700_B(String namespace) {
        return "command_storage_" + namespace;
    }

    private /* synthetic */ n_1700_B R_4764_Y(String s, String s1) {
        return this.n_1700_B(s, s1);
    }

    static class n_1700_B
    extends SavedData {
        private final Map<String, U_2912_j> n_1700_B = Maps.newHashMap();

        public n_1700_B(String name) {
            super(name);
        }

        @Override
        public void n_1700_B(U_2912_j nbt) {
            U_2912_j compoundnbt = nbt.M_182_A("contents");
            for (String s : compoundnbt.G_564_y()) {
                this.n_1700_B.put(s, compoundnbt.M_182_A(s));
            }
        }

        @Override
        public U_2912_j R_4764_Y(U_2912_j compound) {
            U_2912_j compoundnbt = new U_2912_j();
            this.n_1700_B.forEach((id, nbt) -> compoundnbt.n_1700_B((String)id, nbt.v_4262_N()));
            compound.n_1700_B("contents", compoundnbt);
            return compound;
        }

        public U_2912_j n_1700_B(String id) {
            U_2912_j compoundnbt = this.n_1700_B.get(id);
            return compoundnbt != null ? compoundnbt : new U_2912_j();
        }

        public void n_1700_B(String id, U_2912_j nbt) {
            if (nbt.u_1723_Y()) {
                this.n_1700_B.remove(id);
            } else {
                this.n_1700_B.put(id, nbt);
            }
            this.R_4764_Y();
        }

        public Stream<g_2336_b> J_1907_R(String namespace) {
            return this.n_1700_B.keySet().stream().map(id -> new g_2336_b(namespace, (String)id));
        }
    }
}



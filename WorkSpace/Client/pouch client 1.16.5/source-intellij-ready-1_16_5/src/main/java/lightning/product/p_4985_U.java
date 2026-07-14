/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 *  org.apache.commons.lang3.ArrayUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import lightning.product.A_2178_U;
import lightning.product.I_2176_d;
import lightning.product.LootItemFunctions;
import lightning.product.Container;
import lightning.product.Z_1993_T;
import lightning.product.Z_3128_E;
import lightning.product.f_1402_I;
import lightning.product.g_1866_m;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.n_2967_p;
import lightning.product.q_1704_m;
import lightning.product.u_530_F;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class p_4985_U {
    private static final Logger R_4764_Y = LogManager.getLogger();
    public static final p_4985_U n_1700_B = new p_4985_U(f_1402_I.n_1700_B, new n_2967_p[0], new A_2178_U[0]);
    public static final I_2176_d J_1907_R = f_1402_I.u_2550_I;
    private final I_2176_d G_564_y;
    private final n_2967_p[] P_1922_E;
    private final A_2178_U[] u_1723_Y;
    private final BiFunction<Z_1993_T, q_1704_m, Z_1993_T> v_4262_N;

    private p_4985_U(I_2176_d parameterSet, n_2967_p[] pools, A_2178_U[] functions) {
        this.G_564_y = parameterSet;
        this.P_1922_E = pools;
        this.u_1723_Y = functions;
        this.v_4262_N = LootItemFunctions.n_1700_B(functions);
    }

    public static Consumer<Z_1993_T> n_1700_B(Consumer<Z_1993_T> stackConsumer) {
        return stack -> {
            if (stack.t_4043_B() < stack.R_4764_Y()) {
                stackConsumer.accept((Z_1993_T)stack);
            } else {
                Z_1993_T itemstack;
                for (int i = stack.t_4043_B(); i > 0; i -= itemstack.t_4043_B()) {
                    itemstack = stack.t_148_a();
                    itemstack.P_1922_E(Math.min(stack.R_4764_Y(), i));
                    stackConsumer.accept(itemstack);
                }
            }
        };
    }

    public void n_1700_B(q_1704_m context, Consumer<Z_1993_T> stacksOut) {
        if (context.n_1700_B(this)) {
            Consumer<Z_1993_T> consumer = A_2178_U.n_1700_B(this.v_4262_N, stacksOut, context);
            for (n_2967_p lootpool : this.P_1922_E) {
                lootpool.n_1700_B(consumer, context);
            }
            context.J_1907_R(this);
        } else {
            R_4764_Y.warn("Detected infinite loop in loot tables");
        }
    }

    public void J_1907_R(q_1704_m contextData, Consumer<Z_1993_T> stacksOut) {
        this.n_1700_B(contextData, p_4985_U.n_1700_B(stacksOut));
    }

    public List<Z_1993_T> n_1700_B(q_1704_m context) {
        ArrayList list = Lists.newArrayList();
        this.J_1907_R(context, list::add);
        return list;
    }

    public I_2176_d n_1700_B() {
        return this.G_564_y;
    }

    public void n_1700_B(g_1866_m validator) {
        for (int i = 0; i < this.P_1922_E.length; ++i) {
            this.P_1922_E[i].n_1700_B(validator.J_1907_R(".pools[" + i + "]"));
        }
        for (int j = 0; j < this.u_1723_Y.length; ++j) {
            this.u_1723_Y[j].n_1700_B(validator.J_1907_R(".functions[" + j + "]"));
        }
    }

    public void n_1700_B(Container p_216118_1_, q_1704_m context) {
        List<Z_1993_T> list = this.n_1700_B(context);
        Random random = context.n_1700_B();
        List<Integer> list1 = this.n_1700_B(p_216118_1_, random);
        this.n_1700_B(list, list1.size(), random);
        for (Z_1993_T itemstack : list) {
            if (list1.isEmpty()) {
                R_4764_Y.warn("Tried to over-fill a container");
                return;
            }
            if (itemstack.n_1700_B()) {
                p_216118_1_.J_1907_R(list1.remove(list1.size() - 1), Z_1993_T.J_1907_R);
                continue;
            }
            p_216118_1_.J_1907_R(list1.remove(list1.size() - 1), itemstack);
        }
    }

    private void n_1700_B(List<Z_1993_T> stacks, int emptySlotsCount, Random rand) {
        ArrayList list = Lists.newArrayList();
        Iterator<Z_1993_T> iterator = stacks.iterator();
        while (iterator.hasNext()) {
            Z_1993_T itemstack = iterator.next();
            if (itemstack.n_1700_B()) {
                iterator.remove();
                continue;
            }
            if (itemstack.t_4043_B() <= 1) continue;
            list.add(itemstack);
            iterator.remove();
        }
        while (emptySlotsCount - stacks.size() - list.size() > 0 && !list.isEmpty()) {
            Z_1993_T itemstack2 = (Z_1993_T)list.remove(u_530_F.n_1700_B(rand, 0, list.size() - 1));
            int i = u_530_F.n_1700_B(rand, 1, itemstack2.t_4043_B() / 2);
            Z_1993_T itemstack1 = itemstack2.n_1700_B(i);
            if (itemstack2.t_4043_B() > 1 && rand.nextBoolean()) {
                list.add(itemstack2);
            } else {
                stacks.add(itemstack2);
            }
            if (itemstack1.t_4043_B() > 1 && rand.nextBoolean()) {
                list.add(itemstack1);
                continue;
            }
            stacks.add(itemstack1);
        }
        stacks.addAll(list);
        Collections.shuffle(stacks, rand);
    }

    private List<Integer> n_1700_B(Container inventory, Random rand) {
        ArrayList list = Lists.newArrayList();
        for (int i = 0; i < inventory.Y_259_p(); ++i) {
            if (!inventory.s_956_w(i).n_1700_B()) continue;
            list.add(i);
        }
        Collections.shuffle(list, rand);
        return list;
    }

    public static n_1700_B J_1907_R() {
        return new n_1700_B();
    }

    public static class n_1700_B
    implements Z_3128_E<n_1700_B> {
        private final List<n_2967_p> n_1700_B = Lists.newArrayList();
        private final List<A_2178_U> J_1907_R = Lists.newArrayList();
        private I_2176_d R_4764_Y = J_1907_R;

        public n_1700_B n_1700_B(n_2967_p.n_1700_B lootPoolIn) {
            this.n_1700_B.add(lootPoolIn.J_1907_R());
            return this;
        }

        public n_1700_B n_1700_B(I_2176_d parameterSet) {
            this.R_4764_Y = parameterSet;
            return this;
        }

        public n_1700_B J_1907_R(A_2178_U.n_1700_B functionBuilder) {
            this.J_1907_R.add(functionBuilder.u_1723_Y());
            return this;
        }

        public n_1700_B n_1700_B() {
            return this;
        }

        public p_4985_U J_1907_R() {
            return new p_4985_U(this.R_4764_Y, this.n_1700_B.toArray(new n_2967_p[0]), this.J_1907_R.toArray(new A_2178_U[0]));
        }

        @Override
        public /* synthetic */ Object G_564_y() {
            return this.n_1700_B();
        }

        @Override
        public /* synthetic */ Object n_1700_B(A_2178_U.n_1700_B n_1700_B2) {
            return this.J_1907_R(n_1700_B2);
        }
    }

    public static class J_1907_R
    implements JsonDeserializer<p_4985_U>,
    JsonSerializer<p_4985_U> {
        public p_4985_U n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = i_4431_W.w_1484_f(p_deserialize_1_, "loot table");
            n_2967_p[] alootpool = i_4431_W.n_1700_B(jsonobject, "pools", new n_2967_p[0], p_deserialize_3_, n_2967_p[].class);
            I_2176_d lootparameterset = null;
            if (jsonobject.has("type")) {
                String s = i_4431_W.u_1723_Y(jsonobject, "type");
                lootparameterset = f_1402_I.n_1700_B(new g_2336_b(s));
            }
            A_2178_U[] ailootfunction = i_4431_W.n_1700_B(jsonobject, "functions", new A_2178_U[0], p_deserialize_3_, A_2178_U[].class);
            return new p_4985_U(lootparameterset != null ? lootparameterset : f_1402_I.u_2550_I, alootpool, ailootfunction);
        }

        public JsonElement n_1700_B(p_4985_U p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
            JsonObject jsonobject = new JsonObject();
            if (p_serialize_1_.G_564_y != J_1907_R) {
                g_2336_b resourcelocation = f_1402_I.n_1700_B(p_serialize_1_.G_564_y);
                if (resourcelocation != null) {
                    jsonobject.addProperty("type", resourcelocation.toString());
                } else {
                    R_4764_Y.warn("Failed to find id for param set " + String.valueOf(p_serialize_1_.G_564_y));
                }
            }
            if (p_serialize_1_.P_1922_E.length > 0) {
                jsonobject.add("pools", p_serialize_3_.serialize((Object)p_serialize_1_.P_1922_E));
            }
            if (!ArrayUtils.isEmpty((Object[])p_serialize_1_.u_1723_Y)) {
                jsonobject.add("functions", p_serialize_3_.serialize((Object)p_serialize_1_.u_1723_Y));
            }
            return jsonobject;
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }

        public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.n_1700_B((p_4985_U)object, type, jsonSerializationContext);
        }
    }
}



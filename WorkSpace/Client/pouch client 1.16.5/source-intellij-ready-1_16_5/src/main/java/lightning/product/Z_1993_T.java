/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Multimap
 *  com.google.gson.JsonParseException
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.gson.JsonParseException;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.B_4088_l;
import lightning.product.TagContainer;
import lightning.product.D_4024_W;
import lightning.product.BlockPredicateArgument;
import lightning.product.F_1573_j;
import lightning.product.F_2904_S;
import lightning.product.G_3165_y;
import lightning.product.MutableComponent;
import lightning.product.Attributes;
import lightning.product.K_1310_v;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.Attribute;
import lightning.product.N_4263_v;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.U_1880_G;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.V_3137_a;
import lightning.product.W_3729_Q;
import lightning.product.SoundEvent;
import lightning.product.UseOnContext;
import lightning.product.Tag;
import lightning.product.Z_1567_W;
import lightning.product.InteractionResultHolder;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.c_973_a;
import lightning.product.Enchantments;
import lightning.product.e_1174_E;
import lightning.product.f_71_T;
import lightning.product.g_2336_b;
import lightning.product.MobType;
import lightning.product.g_3316_o;
import lightning.product.j_3341_s;
import lightning.product.m_3054_I;
import lightning.product.BlockInWorld;
import lightning.product.BlockTags;
import lightning.product.q_1613_l;
import lightning.product.q_1803_e;
import lightning.product.q_1874_T;
import lightning.product.q_2896_o;
import lightning.product.q_3334_C;
import lightning.product.Items;
import lightning.product.r_109_r;
import lightning.product.r_4811_B;
import lightning.product.v_143_j;
import lightning.product.ComponentUtils;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;
import lightning.product.y_740_d;
import mods.baritone.api.api.java.baritone.api.utils.accessor.IItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Z_1993_T
implements IItemStack {
    public static final Codec<Z_1993_T> n_1700_B = RecordCodecBuilder.create(p_234698_0_ -> p_234698_0_.group((App)V_3137_a.e_2887_G.fieldOf("id").forGetter(p_234706_0_ -> p_234706_0_.w_1484_f), (App)Codec.INT.fieldOf("Count").forGetter(p_234705_0_ -> p_234705_0_.u_1723_Y), (App)U_2912_j.n_1700_B.optionalFieldOf("tag").forGetter(p_234704_0_ -> Optional.ofNullable(p_234704_0_.t_148_a))).apply((Applicative)p_234698_0_, Z_1993_T::new));
    private static final Logger G_564_y = LogManager.getLogger();
    public static final Z_1993_T J_1907_R = new Z_1993_T((q_1803_e)null);
    public static final DecimalFormat R_4764_Y = j_3341_s.n_1700_B(new DecimalFormat("#.##"), (T p_234699_0_) -> p_234699_0_.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT)));
    private static final Z_1567_W P_1922_E = Z_1567_W.n_1700_B.n_1700_B(D_4024_W.u_1723_Y).J_1907_R(true);
    private int u_1723_Y;
    private int v_4262_N;
    @Deprecated
    private final q_1613_l w_1484_f;
    private U_2912_j t_148_a;
    private boolean s_956_w;
    private N_4263_v u_2550_I;
    private BlockInWorld M_588_G;
    private boolean P_4830_p;
    private BlockInWorld h_1847_R;
    private boolean Q_4569_t;
    private int M_182_A;

    public Z_1993_T(q_1803_e itemIn) {
        this(itemIn, 1);
    }

    private Z_1993_T(q_1803_e item, int count, Optional<U_2912_j> nbt) {
        this(item, count);
        nbt.ifPresent(this::R_4764_Y);
    }

    public Z_1993_T(q_1803_e itemIn, int count) {
        this.w_1484_f = itemIn == null ? null : itemIn.u_1723_Y();
        this.u_1723_Y = count;
        if (this.w_1484_f != null && this.w_1484_f.P_4830_p()) {
            this.J_1907_R(this.v_4262_N());
        }
        this.d_2427_y();
    }

    private void d_2427_y() {
        this.s_956_w = false;
        this.s_956_w = this.n_1700_B();
    }

    private Z_1993_T(U_2912_j compound) {
        this.w_1484_f = V_3137_a.e_2887_G.n_1700_B(new g_2336_b(compound.M_588_G("id")));
        this.u_1723_Y = compound.u_1723_Y("Count");
        if (compound.R_4764_Y("tag", 10)) {
            this.t_148_a = compound.M_182_A("tag");
            this.J_1907_R().J_1907_R(compound);
        }
        if (this.J_1907_R().P_4830_p()) {
            this.J_1907_R(this.v_4262_N());
        }
        this.d_2427_y();
        this.v_4276_D();
    }

    public static Z_1993_T n_1700_B(U_2912_j compound) {
        try {
            return new Z_1993_T(compound);
        }
        catch (RuntimeException runtimeexception) {
            G_564_y.debug("Tried to load invalid item: {}", (Object)compound, (Object)runtimeexception);
            return J_1907_R;
        }
    }

    public boolean n_1700_B() {
        if (this == J_1907_R) {
            return true;
        }
        if (this.J_1907_R() != null && this.J_1907_R() != Items.n_1700_B) {
            return this.u_1723_Y <= 0;
        }
        return true;
    }

    public Z_1993_T n_1700_B(int amount) {
        int i = Math.min(amount, this.u_1723_Y);
        Z_1993_T itemstack = this.t_148_a();
        itemstack.P_1922_E(i);
        this.v_4262_N(i);
        return itemstack;
    }

    public q_1613_l J_1907_R() {
        return this.s_956_w ? Items.n_1700_B : this.w_1484_f;
    }

    public m_3054_I n_1700_B(UseOnContext context) {
        a_3913_L playerentity = context.getPlayer();
        c_1514_x blockpos = context.getPos();
        BlockInWorld cachedblockinfo = new BlockInWorld(context.getWorld(), blockpos, false);
        if (playerentity != null && !playerentity.C_415_h.P_1922_E && !this.J_1907_R(context.getWorld().M_182_A(), cachedblockinfo)) {
            return m_3054_I.R_4764_Y;
        }
        q_1613_l item = this.J_1907_R();
        m_3054_I actionresulttype = item.n_1700_B(context);
        if (playerentity != null && actionresulttype.n_1700_B()) {
            playerentity.n_1700_B(Stats.R_4764_Y.J_1907_R(item));
        }
        return actionresulttype;
    }

    public float n_1700_B(K_4074_S blockIn) {
        return this.J_1907_R().n_1700_B(this, blockIn);
    }

    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C hand) {
        return this.J_1907_R().n_1700_B(worldIn, playerIn, hand);
    }

    public Z_1993_T n_1700_B(b_4507_u worldIn, r_4811_B entityLiving) {
        A_4115_X.n_1700_B(new W_3729_Q(this, worldIn, entityLiving));
        return this.J_1907_R().n_1700_B(this, worldIn, entityLiving);
    }

    public U_2912_j J_1907_R(U_2912_j nbt) {
        g_2336_b resourcelocation = V_3137_a.e_2887_G.J_1907_R(this.J_1907_R());
        nbt.n_1700_B("id", resourcelocation == null ? "minecraft:air" : resourcelocation.toString());
        nbt.n_1700_B("Count", (byte)this.u_1723_Y);
        if (this.t_148_a != null) {
            nbt.n_1700_B("tag", this.t_148_a.v_4262_N());
        }
        return nbt;
    }

    public int R_4764_Y() {
        return this.J_1907_R().u_2550_I();
    }

    public boolean G_564_y() {
        return this.R_4764_Y() > 1 && (!this.P_1922_E() || !this.u_1723_Y());
    }

    public boolean P_1922_E() {
        if (!this.s_956_w && this.J_1907_R().M_588_G() > 0) {
            U_2912_j compoundnbt = this.Q_4569_t();
            return compoundnbt == null || !compoundnbt.t_1786_h("Unbreakable");
        }
        return false;
    }

    public boolean u_1723_Y() {
        return this.P_1922_E() && this.v_4262_N() > 0;
    }

    public int v_4262_N() {
        return this.t_148_a == null ? 0 : this.t_148_a.w_1484_f("Damage");
    }

    public void J_1907_R(int damage) {
        this.M_182_A().J_1907_R("Damage", Math.max(0, damage));
        this.v_4276_D();
    }

    public int w_1484_f() {
        return this.J_1907_R().M_588_G();
    }

    public boolean n_1700_B(int amount, Random rand, @Nullable B_4088_l damager) {
        if (!this.P_1922_E()) {
            return false;
        }
        if (amount > 0) {
            int i = K_4096_w.n_1700_B(Enchantments.Q_2552_b, this);
            int j = 0;
            for (int k = 0; i > 0 && k < amount; ++k) {
                if (!q_3334_C.n_1700_B(this, i, rand)) continue;
                ++j;
            }
            if ((amount -= j) <= 0) {
                return false;
            }
        }
        if (damager != null && amount != 0) {
            U_3554_Q.Y_601_j.n_1700_B(damager, this, this.v_4262_N() + amount);
        }
        int l = this.v_4262_N() + amount;
        this.J_1907_R(l);
        return l >= this.w_1484_f();
    }

    public <T extends r_4811_B> void n_1700_B(int amount, T entityIn, Consumer<T> onBroken) {
        if (!(entityIn.O_508_d.Y_259_p || entityIn instanceof a_3913_L && ((a_3913_L)entityIn).C_415_h.G_564_y || !this.P_1922_E() || !this.n_1700_B(amount, entityIn.M_3508_C(), entityIn instanceof B_4088_l ? (B_4088_l)entityIn : null))) {
            onBroken.accept(entityIn);
            q_1613_l item = this.J_1907_R();
            this.v_4262_N(1);
            if (entityIn instanceof a_3913_L) {
                ((a_3913_L)entityIn).n_1700_B(Stats.G_564_y.J_1907_R(item));
            }
            this.J_1907_R(0);
        }
    }

    public void n_1700_B(r_4811_B entityIn, a_3913_L playerIn) {
        q_1613_l item = this.J_1907_R();
        if (item.n_1700_B(this, entityIn, (r_4811_B)playerIn)) {
            playerIn.n_1700_B(Stats.R_4764_Y.J_1907_R(item));
        }
    }

    public void n_1700_B(b_4507_u worldIn, K_4074_S blockIn, c_1514_x pos, a_3913_L playerIn) {
        q_1613_l item = this.J_1907_R();
        if (item.n_1700_B(this, worldIn, blockIn, pos, playerIn)) {
            playerIn.n_1700_B(Stats.R_4764_Y.J_1907_R(item));
        }
    }

    public boolean J_1907_R(K_4074_S blockIn) {
        return this.J_1907_R().J_1907_R(blockIn);
    }

    public m_3054_I n_1700_B(a_3913_L playerIn, r_4811_B entityIn, x_1688_C hand) {
        return this.J_1907_R().n_1700_B(this, playerIn, entityIn, hand);
    }

    public Z_1993_T t_148_a() {
        if (this.n_1700_B()) {
            return J_1907_R;
        }
        Z_1993_T itemstack = new Z_1993_T(this.J_1907_R(), this.u_1723_Y);
        itemstack.G_564_y(this.Y_1740_V());
        if (this.t_148_a != null) {
            itemstack.t_148_a = this.t_148_a.v_4262_N();
        }
        return itemstack;
    }

    public static boolean n_1700_B(Z_1993_T stackA, Z_1993_T stackB) {
        if (stackA.n_1700_B() && stackB.n_1700_B()) {
            return true;
        }
        if (!stackA.n_1700_B() && !stackB.n_1700_B()) {
            if (stackA.t_148_a == null && stackB.t_148_a != null) {
                return false;
            }
            return stackA.t_148_a == null || stackA.t_148_a.equals(stackB.t_148_a);
        }
        return false;
    }

    public static boolean J_1907_R(Z_1993_T stackA, Z_1993_T stackB) {
        if (stackA.n_1700_B() && stackB.n_1700_B()) {
            return true;
        }
        return !stackA.n_1700_B() && !stackB.n_1700_B() ? stackA.R_4764_Y(stackB) : false;
    }

    private boolean R_4764_Y(Z_1993_T other) {
        if (this.u_1723_Y != other.u_1723_Y) {
            return false;
        }
        if (this.J_1907_R() != other.J_1907_R()) {
            return false;
        }
        if (this.t_148_a == null && other.t_148_a != null) {
            return false;
        }
        return this.t_148_a == null || this.t_148_a.equals(other.t_148_a);
    }

    public static boolean R_4764_Y(Z_1993_T stackA, Z_1993_T stackB) {
        if (stackA == stackB) {
            return true;
        }
        return !stackA.n_1700_B() && !stackB.n_1700_B() ? stackA.n_1700_B(stackB) : false;
    }

    public static boolean G_564_y(Z_1993_T stackA, Z_1993_T stackB) {
        if (stackA == stackB) {
            return true;
        }
        return !stackA.n_1700_B() && !stackB.n_1700_B() ? stackA.J_1907_R(stackB) : false;
    }

    public boolean n_1700_B(Z_1993_T other) {
        return !other.n_1700_B() && this.J_1907_R() == other.J_1907_R();
    }

    public boolean J_1907_R(Z_1993_T stack) {
        if (!this.P_1922_E()) {
            return this.n_1700_B(stack);
        }
        return !stack.n_1700_B() && this.J_1907_R() == stack.J_1907_R();
    }

    public String s_956_w() {
        return this.J_1907_R().u_1723_Y(this);
    }

    public String toString() {
        return this.u_1723_Y + " " + String.valueOf(this.J_1907_R());
    }

    public void n_1700_B(b_4507_u worldIn, N_4263_v entityIn, int inventorySlot, boolean isCurrentItem) {
        if (this.v_4262_N > 0) {
            --this.v_4262_N;
        }
        if (this.J_1907_R() != null) {
            this.J_1907_R().n_1700_B(this, worldIn, entityIn, inventorySlot, isCurrentItem);
        }
    }

    public void n_1700_B(b_4507_u worldIn, a_3913_L playerIn, int amount) {
        playerIn.n_1700_B(Stats.J_1907_R.J_1907_R(this.J_1907_R()), amount);
        this.J_1907_R().J_1907_R(this, worldIn, playerIn);
    }

    public int u_2550_I() {
        return this.J_1907_R().J_1907_R(this);
    }

    public F_1573_j M_588_G() {
        return this.J_1907_R().R_4764_Y(this);
    }

    public void n_1700_B(b_4507_u worldIn, r_4811_B entityLiving, int timeLeft) {
        this.J_1907_R().n_1700_B(this, worldIn, entityLiving, timeLeft);
    }

    public boolean P_4830_p() {
        return this.J_1907_R().s_956_w(this);
    }

    public boolean h_1847_R() {
        return !this.s_956_w && this.t_148_a != null && !this.t_148_a.u_1723_Y();
    }

    @Nullable
    public U_2912_j Q_4569_t() {
        return this.t_148_a;
    }

    public U_2912_j M_182_A() {
        if (this.t_148_a == null) {
            this.R_4764_Y(new U_2912_j());
        }
        return this.t_148_a;
    }

    public U_2912_j n_1700_B(String key) {
        if (this.t_148_a != null && this.t_148_a.R_4764_Y(key, 10)) {
            return this.t_148_a.M_182_A(key);
        }
        U_2912_j compoundnbt = new U_2912_j();
        this.n_1700_B(key, compoundnbt);
        return compoundnbt;
    }

    @Nullable
    public U_2912_j J_1907_R(String key) {
        return this.t_148_a != null && this.t_148_a.R_4764_Y(key, 10) ? this.t_148_a.M_182_A(key) : null;
    }

    public void R_4764_Y(String p_196083_1_) {
        if (this.t_148_a != null && this.t_148_a.P_1922_E(p_196083_1_)) {
            this.t_148_a.multiplayerClientSuggestionProvider(p_196083_1_);
            if (this.t_148_a.u_1723_Y()) {
                this.t_148_a = null;
            }
        }
    }

    public q_2896_o t_1786_h() {
        return this.t_148_a != null ? this.t_148_a.G_564_y("Enchantments", 10) : new q_2896_o();
    }

    public void R_4764_Y(@Nullable U_2912_j nbt) {
        this.t_148_a = nbt;
        if (this.J_1907_R().P_4830_p()) {
            this.J_1907_R(this.v_4262_N());
        }
    }

    public x_282_a multiplayerClientSuggestionProvider() {
        U_2912_j compoundnbt = this.J_1907_R("display");
        if (compoundnbt != null && compoundnbt.R_4764_Y("Name", 8)) {
            try {
                MutableComponent itextcomponent = x_282_a.n_1700_B.n_1700_B(compoundnbt.M_588_G("Name"));
                if (itextcomponent != null) {
                    String fixed = v_143_j.n_1700_B(itextcomponent.getString());
                    if (!fixed.equals(itextcomponent.getString())) {
                        return new U_2871_b(fixed);
                    }
                    return itextcomponent;
                }
                compoundnbt.multiplayerClientSuggestionProvider("Name");
            }
            catch (JsonParseException jsonparseexception) {
                compoundnbt.multiplayerClientSuggestionProvider("Name");
            }
        }
        return this.J_1907_R().w_1484_f(this);
    }

    public Z_1993_T n_1700_B(@Nullable x_282_a name) {
        U_2912_j compoundnbt = this.n_1700_B("display");
        if (name != null) {
            compoundnbt.n_1700_B("Name", x_282_a.n_1700_B.n_1700_B(name));
        } else {
            compoundnbt.multiplayerClientSuggestionProvider("Name");
        }
        return this;
    }

    public void w_1457_N() {
        U_2912_j compoundnbt = this.J_1907_R("display");
        if (compoundnbt != null) {
            compoundnbt.multiplayerClientSuggestionProvider("Name");
            if (compoundnbt.u_1723_Y()) {
                this.R_4764_Y("display");
            }
        }
        if (this.t_148_a != null && this.t_148_a.u_1723_Y()) {
            this.t_148_a = null;
        }
    }

    public boolean Y_601_j() {
        U_2912_j compoundnbt = this.J_1907_R("display");
        return compoundnbt != null && compoundnbt.R_4764_Y("Name", 8);
    }

    public List<x_282_a> n_1700_B(@Nullable a_3913_L playerIn, g_3316_o advanced) {
        int i;
        ArrayList list = Lists.newArrayList();
        MutableComponent iformattabletextcomponent = new U_2871_b("").n_1700_B(this.multiplayerClientSuggestionProvider()).n_1700_B(this.Q_2552_b().P_1922_E);
        if (this.Y_601_j()) {
            iformattabletextcomponent.n_1700_B(D_4024_W.Y_259_p);
        }
        list.add(iformattabletextcomponent);
        if (!advanced.n_1700_B() && !this.Y_601_j() && this.J_1907_R() == Items.K_4518_s) {
            list.add(new U_2871_b("#" + G_3165_y.G_564_y(this)).n_1700_B(D_4024_W.w_1484_f));
        }
        if (Z_1993_T.n_1700_B(i = this.z_1737_N(), lightning.product.Z_1993_T$n_1700_B.u_1723_Y)) {
            this.J_1907_R().n_1700_B(this, playerIn == null ? null : playerIn.O_508_d, list, advanced);
        }
        if (this.h_1847_R()) {
            if (Z_1993_T.n_1700_B(i, lightning.product.Z_1993_T$n_1700_B.n_1700_B)) {
                Z_1993_T.n_1700_B(list, this.t_1786_h());
            }
            if (this.t_148_a.R_4764_Y("display", 10)) {
                U_2912_j compoundnbt = this.t_148_a.M_182_A("display");
                if (Z_1993_T.n_1700_B(i, lightning.product.Z_1993_T$n_1700_B.v_4262_N) && compoundnbt.R_4764_Y("color", 99)) {
                    if (advanced.n_1700_B()) {
                        list.add(new F_2904_S("item.color", String.format("#%06X", compoundnbt.w_1484_f("color"))).n_1700_B(D_4024_W.w_1484_f));
                    } else {
                        list.add(new F_2904_S("item.dyed").n_1700_B(D_4024_W.w_1484_f, D_4024_W.Y_259_p));
                    }
                }
                if (compoundnbt.G_564_y("Lore") == 9) {
                    q_2896_o listnbt = compoundnbt.G_564_y("Lore", 8);
                    for (int j = 0; j < listnbt.size(); ++j) {
                        String s = listnbt.t_148_a(j);
                        try {
                            MutableComponent iformattabletextcomponent1 = x_282_a.n_1700_B.n_1700_B(s);
                            if (iformattabletextcomponent1 == null) continue;
                            list.add(ComponentUtils.n_1700_B(iformattabletextcomponent1, P_1922_E));
                            continue;
                        }
                        catch (JsonParseException jsonparseexception) {
                            compoundnbt.multiplayerClientSuggestionProvider("Lore");
                        }
                    }
                }
            }
        }
        if (Z_1993_T.n_1700_B(i, lightning.product.Z_1993_T$n_1700_B.J_1907_R)) {
            for (e_1174_E equipmentslottype : e_1174_E.values()) {
                Multimap<Attribute, U_1880_G> multimap = this.n_1700_B(equipmentslottype);
                if (multimap.isEmpty()) continue;
                list.add(U_2871_b.R_4764_Y);
                list.add(new F_2904_S("item.modifiers." + equipmentslottype.G_564_y()).n_1700_B(D_4024_W.w_1484_f));
                for (Map.Entry entry : multimap.entries()) {
                    U_1880_G attributemodifier = (U_1880_G)entry.getValue();
                    double d0 = attributemodifier.G_564_y();
                    boolean flag = false;
                    if (playerIn != null) {
                        if (attributemodifier.n_1700_B() == q_1613_l.u_1723_Y) {
                            d0 += playerIn.R_4764_Y(Attributes.u_1723_Y);
                            d0 += (double)K_4096_w.n_1700_B(this, MobType.n_1700_B);
                            flag = true;
                        } else if (attributemodifier.n_1700_B() == q_1613_l.v_4262_N) {
                            d0 += playerIn.R_4764_Y(Attributes.w_1484_f);
                            flag = true;
                        }
                    }
                    double d1 = attributemodifier.R_4764_Y() != U_1880_G.n_1700_B.J_1907_R && attributemodifier.R_4764_Y() != U_1880_G.n_1700_B.R_4764_Y ? (((Attribute)entry.getKey()).equals(Attributes.R_4764_Y) ? d0 * 10.0 : d0) : d0 * 100.0;
                    if (flag) {
                        list.add(new U_2871_b(" ").n_1700_B(new F_2904_S("attribute.modifier.equals." + attributemodifier.R_4764_Y().n_1700_B(), R_4764_Y.format(d1), new F_2904_S(((Attribute)entry.getKey()).R_4764_Y()))).n_1700_B(D_4024_W.R_4764_Y));
                        continue;
                    }
                    if (d0 > 0.0) {
                        list.add(new F_2904_S("attribute.modifier.plus." + attributemodifier.R_4764_Y().n_1700_B(), R_4764_Y.format(d1), new F_2904_S(((Attribute)entry.getKey()).R_4764_Y())).n_1700_B(D_4024_W.s_956_w));
                        continue;
                    }
                    if (!(d0 < 0.0)) continue;
                    list.add(new F_2904_S("attribute.modifier.take." + attributemodifier.R_4764_Y().n_1700_B(), R_4764_Y.format(d1 *= -1.0), new F_2904_S(((Attribute)entry.getKey()).R_4764_Y())).n_1700_B(D_4024_W.P_4830_p));
                }
            }
        }
        if (this.h_1847_R()) {
            q_2896_o listnbt2;
            q_2896_o listnbt1;
            if (Z_1993_T.n_1700_B(i, lightning.product.Z_1993_T$n_1700_B.R_4764_Y) && this.t_148_a.t_1786_h("Unbreakable")) {
                list.add(new F_2904_S("item.unbreakable").n_1700_B(D_4024_W.s_956_w));
            }
            if (Z_1993_T.n_1700_B(i, lightning.product.Z_1993_T$n_1700_B.G_564_y) && this.t_148_a.R_4764_Y("CanDestroy", 9) && !(listnbt1 = this.t_148_a.G_564_y("CanDestroy", 8)).isEmpty()) {
                list.add(U_2871_b.R_4764_Y);
                list.add(new F_2904_S("item.canBreak").n_1700_B(D_4024_W.w_1484_f));
                for (int k = 0; k < listnbt1.size(); ++k) {
                    list.addAll(Z_1993_T.G_564_y(listnbt1.t_148_a(k)));
                }
            }
            if (Z_1993_T.n_1700_B(i, lightning.product.Z_1993_T$n_1700_B.P_1922_E) && this.t_148_a.R_4764_Y("CanPlaceOn", 9) && !(listnbt2 = this.t_148_a.G_564_y("CanPlaceOn", 8)).isEmpty()) {
                list.add(U_2871_b.R_4764_Y);
                list.add(new F_2904_S("item.canPlace").n_1700_B(D_4024_W.w_1484_f));
                for (int l = 0; l < listnbt2.size(); ++l) {
                    list.addAll(Z_1993_T.G_564_y(listnbt2.t_148_a(l)));
                }
            }
        }
        if (advanced.n_1700_B()) {
            if (this.u_1723_Y()) {
                list.add(new F_2904_S("item.durability", this.w_1484_f() - this.v_4262_N(), this.w_1484_f()));
            }
            list.add(new U_2871_b(V_3137_a.e_2887_G.J_1907_R(this.J_1907_R()).toString()).n_1700_B(D_4024_W.t_148_a));
            if (this.h_1847_R()) {
                list.add(new F_2904_S("item.nbt_tags", this.t_148_a.G_564_y().size()).n_1700_B(D_4024_W.t_148_a));
            }
        }
        return list;
    }

    private static boolean n_1700_B(int p_242394_0_, n_1700_B p_242394_1_) {
        return (p_242394_0_ & p_242394_1_.n_1700_B()) == 0;
    }

    private int z_1737_N() {
        return this.h_1847_R() && this.t_148_a.R_4764_Y("HideFlags", 99) ? this.t_148_a.w_1484_f("HideFlags") : 0;
    }

    public void n_1700_B(n_1700_B p_242395_1_) {
        U_2912_j compoundnbt = this.M_182_A();
        compoundnbt.J_1907_R("HideFlags", compoundnbt.w_1484_f("HideFlags") | p_242395_1_.n_1700_B());
    }

    public static void n_1700_B(List<x_282_a> p_222120_0_, q_2896_o p_222120_1_) {
        for (int i = 0; i < p_222120_1_.size(); ++i) {
            U_2912_j compoundnbt = p_222120_1_.n_1700_B(i);
            V_3137_a.z_4693_k.J_1907_R(g_2336_b.J_1907_R(compoundnbt.M_588_G("id"))).ifPresent(p_222123_2_ -> p_222120_0_.add(p_222123_2_.G_564_y(compoundnbt.w_1484_f("lvl"))));
        }
    }

    private static Collection<x_282_a> G_564_y(String stateString) {
        try {
            boolean flag1;
            f_71_T blockstateparser = new f_71_T(new StringReader(stateString), true).n_1700_B(true);
            K_4074_S blockstate = blockstateparser.J_1907_R();
            g_2336_b resourcelocation = blockstateparser.G_564_y();
            boolean flag = blockstate != null;
            boolean bl = flag1 = resourcelocation != null;
            if (flag || flag1) {
                List<T_2915_h> collection;
                if (flag) {
                    return Lists.newArrayList((Object[])new x_282_a[]{blockstate.J_1907_R().M_588_G().n_1700_B(D_4024_W.t_148_a)});
                }
                r_109_r<T_2915_h> itag = BlockTags.n_1700_B().n_1700_B(resourcelocation);
                if (itag != null && !(collection = itag.n_1700_B()).isEmpty()) {
                    return collection.stream().map(T_2915_h::M_588_G).map(p_222119_0_ -> p_222119_0_.n_1700_B(D_4024_W.t_148_a)).collect(Collectors.toList());
                }
            }
        }
        catch (CommandSyntaxException commandSyntaxException) {
            // empty catch block
        }
        return Lists.newArrayList((Object[])new x_282_a[]{new U_2871_b("missingno").n_1700_B(D_4024_W.t_148_a)});
    }

    public boolean Y_259_p() {
        return this.J_1907_R().P_1922_E(this);
    }

    public q_1874_T Q_2552_b() {
        return this.J_1907_R().t_148_a(this);
    }

    public boolean C_2741_M() {
        if (!this.J_1907_R().n_1700_B(this)) {
            return false;
        }
        return !this.k_2293_S();
    }

    public void n_1700_B(K_1310_v ench, int level) {
        this.M_182_A();
        if (!this.t_148_a.R_4764_Y("Enchantments", 9)) {
            this.t_148_a.n_1700_B("Enchantments", new q_2896_o());
        }
        q_2896_o listnbt = this.t_148_a.G_564_y("Enchantments", 10);
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.n_1700_B("id", String.valueOf(V_3137_a.z_4693_k.J_1907_R(ench)));
        compoundnbt.n_1700_B("lvl", (short)((byte)level));
        listnbt.add(compoundnbt);
    }

    public boolean k_2293_S() {
        if (this.t_148_a != null && this.t_148_a.R_4764_Y("Enchantments", 9)) {
            return !this.t_148_a.G_564_y("Enchantments", 10).isEmpty();
        }
        return false;
    }

    public void n_1700_B(String key, Tag value) {
        this.M_182_A().n_1700_B(key, value);
    }

    public boolean q_2307_F() {
        return this.u_2550_I instanceof y_740_d;
    }

    public void n_1700_B(@Nullable N_4263_v entity) {
        this.u_2550_I = entity;
    }

    @Nullable
    public y_740_d Z_875_P() {
        return this.u_2550_I instanceof y_740_d ? (y_740_d)this.c_3005_b() : null;
    }

    @Nullable
    public N_4263_v c_3005_b() {
        return !this.s_956_w ? this.u_2550_I : null;
    }

    public int H_2857_Y() {
        return this.h_1847_R() && this.t_148_a.R_4764_Y("RepairCost", 3) ? this.t_148_a.w_1484_f("RepairCost") : 0;
    }

    public void R_4764_Y(int cost) {
        this.M_182_A().J_1907_R("RepairCost", cost);
    }

    public Multimap<Attribute, U_1880_G> n_1700_B(e_1174_E equipmentSlot) {
        HashMultimap multimap;
        if (this.h_1847_R() && this.t_148_a.R_4764_Y("AttributeModifiers", 9)) {
            multimap = HashMultimap.create();
            q_2896_o listnbt = this.t_148_a.G_564_y("AttributeModifiers", 10);
            for (int i = 0; i < listnbt.size(); ++i) {
                U_1880_G attributemodifier;
                Optional<Attribute> optional;
                U_2912_j compoundnbt = listnbt.n_1700_B(i);
                if (compoundnbt.R_4764_Y("Slot", 8) && !compoundnbt.M_588_G("Slot").equals(equipmentSlot.G_564_y()) || !(optional = V_3137_a.l_1233_K.J_1907_R(g_2336_b.J_1907_R(compoundnbt.M_588_G("AttributeName")))).isPresent() || (attributemodifier = U_1880_G.n_1700_B(compoundnbt)) == null || attributemodifier.n_1700_B().getLeastSignificantBits() == 0L || attributemodifier.n_1700_B().getMostSignificantBits() == 0L) continue;
                multimap.put((Object)optional.get(), (Object)attributemodifier);
            }
        } else {
            multimap = this.J_1907_R().n_1700_B(equipmentSlot);
        }
        return multimap;
    }

    public void n_1700_B(Attribute attributeName, U_1880_G modifier, @Nullable e_1174_E equipmentSlot) {
        this.M_182_A();
        if (!this.t_148_a.R_4764_Y("AttributeModifiers", 9)) {
            this.t_148_a.n_1700_B("AttributeModifiers", new q_2896_o());
        }
        q_2896_o listnbt = this.t_148_a.G_564_y("AttributeModifiers", 10);
        U_2912_j compoundnbt = modifier.P_1922_E();
        compoundnbt.n_1700_B("AttributeName", V_3137_a.l_1233_K.J_1907_R(attributeName).toString());
        if (equipmentSlot != null) {
            compoundnbt.n_1700_B("Slot", equipmentSlot.G_564_y());
        }
        listnbt.add(compoundnbt);
    }

    public x_282_a A_4115_X() {
        MutableComponent iformattabletextcomponent = new U_2871_b("").n_1700_B(this.multiplayerClientSuggestionProvider());
        if (this.Y_601_j()) {
            iformattabletextcomponent.n_1700_B(D_4024_W.Y_259_p);
        }
        MutableComponent iformattabletextcomponent1 = ComponentUtils.n_1700_B(iformattabletextcomponent);
        if (!this.s_956_w) {
            iformattabletextcomponent1.n_1700_B(this.Q_2552_b().P_1922_E).n_1700_B(p_234702_1_ -> p_234702_1_.n_1700_B(new c_973_a(c_973_a.n_1700_B.J_1907_R, new c_973_a.R_4764_Y(this))));
        }
        return iformattabletextcomponent1;
    }

    private static boolean n_1700_B(BlockInWorld p_206846_0_, @Nullable BlockInWorld p_206846_1_) {
        if (p_206846_1_ != null && p_206846_0_.n_1700_B() == p_206846_1_.n_1700_B()) {
            if (p_206846_0_.J_1907_R() == null && p_206846_1_.J_1907_R() == null) {
                return true;
            }
            return p_206846_0_.J_1907_R() != null && p_206846_1_.J_1907_R() != null ? Objects.equals(p_206846_0_.J_1907_R().n_1700_B(new U_2912_j()), p_206846_1_.J_1907_R().n_1700_B(new U_2912_j())) : false;
        }
        return false;
    }

    public boolean n_1700_B(TagContainer p_206848_1_, BlockInWorld p_206848_2_) {
        if (Z_1993_T.n_1700_B(p_206848_2_, this.M_588_G)) {
            return this.P_4830_p;
        }
        this.M_588_G = p_206848_2_;
        if (this.h_1847_R() && this.t_148_a.R_4764_Y("CanDestroy", 9)) {
            q_2896_o listnbt = this.t_148_a.G_564_y("CanDestroy", 8);
            for (int i = 0; i < listnbt.size(); ++i) {
                String s = listnbt.t_148_a(i);
                try {
                    Predicate<BlockInWorld> predicate = BlockPredicateArgument.n_1700_B().n_1700_B(new StringReader(s)).create(p_206848_1_);
                    if (predicate.test(p_206848_2_)) {
                        this.P_4830_p = true;
                        return true;
                    }
                    continue;
                }
                catch (CommandSyntaxException commandSyntaxException) {
                    // empty catch block
                }
            }
        }
        this.P_4830_p = false;
        return false;
    }

    public boolean J_1907_R(TagContainer p_206847_1_, BlockInWorld p_206847_2_) {
        if (Z_1993_T.n_1700_B(p_206847_2_, this.h_1847_R)) {
            return this.Q_4569_t;
        }
        this.h_1847_R = p_206847_2_;
        if (this.h_1847_R() && this.t_148_a.R_4764_Y("CanPlaceOn", 9)) {
            q_2896_o listnbt = this.t_148_a.G_564_y("CanPlaceOn", 8);
            for (int i = 0; i < listnbt.size(); ++i) {
                String s = listnbt.t_148_a(i);
                try {
                    Predicate<BlockInWorld> predicate = BlockPredicateArgument.n_1700_B().n_1700_B(new StringReader(s)).create(p_206847_1_);
                    if (predicate.test(p_206847_2_)) {
                        this.Q_4569_t = true;
                        return true;
                    }
                    continue;
                }
                catch (CommandSyntaxException commandSyntaxException) {
                    // empty catch block
                }
            }
        }
        this.Q_4569_t = false;
        return false;
    }

    public int Y_1740_V() {
        return this.v_4262_N;
    }

    public void G_564_y(int animations) {
        this.v_4262_N = animations;
    }

    public int t_4043_B() {
        return this.s_956_w ? 0 : this.u_1723_Y;
    }

    public void P_1922_E(int count) {
        this.u_1723_Y = count;
        this.d_2427_y();
    }

    public void u_1723_Y(int count) {
        this.P_1922_E(this.u_1723_Y + count);
    }

    public void v_4262_N(int count) {
        this.u_1723_Y(-count);
    }

    public void J_1907_R(b_4507_u worldIn, r_4811_B livingEntityIn, int countIn) {
        this.J_1907_R().n_1700_B(worldIn, livingEntityIn, this, countIn);
    }

    public boolean x_607_J() {
        return this.J_1907_R().Y_259_p();
    }

    public SoundEvent e_4240_b() {
        return this.J_1907_R().C_();
    }

    public SoundEvent n_3318_d() {
        return this.J_1907_R().D_();
    }

    private void v_4276_D() {
        this.M_182_A = this.w_1484_f == null ? -1 : this.w_1484_f.hashCode() + this.v_4262_N();
    }

    @Override
    public int getBaritoneHash() {
        return this.M_182_A;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B();
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B();
        public static final /* enum */ n_1700_B v_4262_N = new n_1700_B();
        private int w_1484_f = 1 << this.ordinal();
        private static final /* synthetic */ n_1700_B[] t_148_a;

        public static n_1700_B[] values() {
            return (n_1700_B[])t_148_a.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        public int n_1700_B() {
            return this.w_1484_f;
        }

        private static /* synthetic */ n_1700_B[] J_1907_R() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N};
        }

        static {
            t_148_a = lightning.product.Z_1993_T$n_1700_B.J_1907_R();
        }
    }
}



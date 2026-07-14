/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Iterables
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.DoubleArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.ArgumentBuilder
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import lightning.product.F_2904_S;
import lightning.product.StringTag;
import lightning.product.EntityDataAccessor;
import lightning.product.K_1178_t;
import lightning.product.DataAccessor;
import lightning.product.Q_2241_p;
import lightning.product.S_4888_k;
import lightning.product.NumericTag;
import lightning.product.U_2912_j;
import lightning.product.CompoundTagArgument;
import lightning.product.Tag;
import lightning.product.CollectionTag;
import lightning.product.NbtTagArgument;
import lightning.product.q_2896_o;
import lightning.product.u_530_F;
import lightning.product.u_693_p;
import lightning.product.y_2498_m;

public class z_1856_e {
    private static final SimpleCommandExceptionType G_564_y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.data.merge.failed"));
    private static final DynamicCommandExceptionType P_1922_E = new DynamicCommandExceptionType(p_208922_0_ -> new F_2904_S("commands.data.get.invalid", p_208922_0_));
    private static final DynamicCommandExceptionType u_1723_Y = new DynamicCommandExceptionType(p_208919_0_ -> new F_2904_S("commands.data.get.unknown", p_208919_0_));
    private static final SimpleCommandExceptionType v_4262_N = new SimpleCommandExceptionType((Message)new F_2904_S("commands.data.get.multiple"));
    private static final DynamicCommandExceptionType w_1484_f = new DynamicCommandExceptionType(p_218931_0_ -> new F_2904_S("commands.data.modify.expected_list", p_218931_0_));
    private static final DynamicCommandExceptionType t_148_a = new DynamicCommandExceptionType(p_218948_0_ -> new F_2904_S("commands.data.modify.expected_object", p_218948_0_));
    private static final DynamicCommandExceptionType s_956_w = new DynamicCommandExceptionType(p_218943_0_ -> new F_2904_S("commands.data.modify.invalid_index", p_218943_0_));
    public static final List<Function<String, n_1700_B>> n_1700_B = ImmutableList.of(EntityDataAccessor.n_1700_B, u_693_p.n_1700_B, S_4888_k.n_1700_B);
    public static final List<n_1700_B> J_1907_R = (List)n_1700_B.stream().map(p_218925_0_ -> (n_1700_B)p_218925_0_.apply("target")).collect(ImmutableList.toImmutableList());
    public static final List<n_1700_B> R_4764_Y = (List)n_1700_B.stream().map(p_218947_0_ -> (n_1700_B)p_218947_0_.apply("source")).collect(ImmutableList.toImmutableList());

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        LiteralArgumentBuilder literalargumentbuilder = (LiteralArgumentBuilder)Q_2241_p.n_1700_B("data").requires(p_198939_0_ -> p_198939_0_.n_1700_B(2));
        for (n_1700_B datacommand$idataprovider : J_1907_R) {
            ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)literalargumentbuilder.then(datacommand$idataprovider.n_1700_B((ArgumentBuilder<y_2498_m, ?>)Q_2241_p.n_1700_B("merge"), p_198943_1_ -> p_198943_1_.then(Q_2241_p.n_1700_B("nbt", CompoundTagArgument.n_1700_B()).executes(p_198936_1_ -> z_1856_e.n_1700_B((y_2498_m)p_198936_1_.getSource(), datacommand$idataprovider.n_1700_B((CommandContext<y_2498_m>)p_198936_1_), CompoundTagArgument.n_1700_B(p_198936_1_, "nbt"))))))).then(datacommand$idataprovider.n_1700_B((ArgumentBuilder<y_2498_m, ?>)Q_2241_p.n_1700_B("get"), p_198940_1_ -> p_198940_1_.executes(p_198944_1_ -> z_1856_e.n_1700_B((y_2498_m)p_198944_1_.getSource(), datacommand$idataprovider.n_1700_B((CommandContext<y_2498_m>)p_198944_1_))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("path", K_1178_t.n_1700_B()).executes(p_198945_1_ -> z_1856_e.J_1907_R((y_2498_m)p_198945_1_.getSource(), datacommand$idataprovider.n_1700_B((CommandContext<y_2498_m>)p_198945_1_), K_1178_t.n_1700_B((CommandContext<y_2498_m>)p_198945_1_, "path")))).then(Q_2241_p.n_1700_B("scale", DoubleArgumentType.doubleArg()).executes(p_198935_1_ -> z_1856_e.n_1700_B((y_2498_m)p_198935_1_.getSource(), datacommand$idataprovider.n_1700_B((CommandContext<y_2498_m>)p_198935_1_), K_1178_t.n_1700_B((CommandContext<y_2498_m>)p_198935_1_, "path"), DoubleArgumentType.getDouble((CommandContext)p_198935_1_, (String)"scale")))))))).then(datacommand$idataprovider.n_1700_B((ArgumentBuilder<y_2498_m, ?>)Q_2241_p.n_1700_B("remove"), p_198934_1_ -> p_198934_1_.then(Q_2241_p.n_1700_B("path", K_1178_t.n_1700_B()).executes(p_198941_1_ -> z_1856_e.n_1700_B((y_2498_m)p_198941_1_.getSource(), datacommand$idataprovider.n_1700_B((CommandContext<y_2498_m>)p_198941_1_), K_1178_t.n_1700_B((CommandContext<y_2498_m>)p_198941_1_, "path"))))))).then(z_1856_e.n_1700_B((ArgumentBuilder<y_2498_m, ?> p_218924_0_, J_1907_R p_218924_1_) -> p_218924_0_.then(Q_2241_p.n_1700_B("insert").then(Q_2241_p.n_1700_B("index", IntegerArgumentType.integer()).then(p_218924_1_.create((p_218930_0_, p_218930_1_, p_218930_2_, p_218930_3_) -> {
                int i = IntegerArgumentType.getInteger((CommandContext)p_218930_0_, (String)"index");
                return z_1856_e.n_1700_B(i, p_218930_1_, p_218930_2_, (List<Tag>)p_218930_3_);
            })))).then(Q_2241_p.n_1700_B("prepend").then(p_218924_1_.create((p_218932_0_, p_218932_1_, p_218932_2_, p_218932_3_) -> z_1856_e.n_1700_B(0, p_218932_1_, p_218932_2_, (List<Tag>)p_218932_3_)))).then(Q_2241_p.n_1700_B("append").then(p_218924_1_.create((p_218941_0_, p_218941_1_, p_218941_2_, p_218941_3_) -> z_1856_e.n_1700_B(-1, p_218941_1_, p_218941_2_, (List<Tag>)p_218941_3_)))).then(Q_2241_p.n_1700_B("set").then(p_218924_1_.create((p_218954_0_, p_218954_1_, p_218954_2_, p_218954_3_) -> p_218954_2_.J_1907_R(p_218954_1_, ((Tag)Iterables.getLast((Iterable)p_218954_3_))::R_4764_Y)))).then(Q_2241_p.n_1700_B("merge").then(p_218924_1_.create((p_218927_0_, p_218927_1_, p_218927_2_, p_218927_3_) -> {
                List<Tag> collection = p_218927_2_.n_1700_B(p_218927_1_, U_2912_j::new);
                int i = 0;
                for (Tag inbt : collection) {
                    if (!(inbt instanceof U_2912_j)) {
                        throw t_148_a.create((Object)inbt);
                    }
                    U_2912_j compoundnbt = (U_2912_j)inbt;
                    U_2912_j compoundnbt1 = compoundnbt.v_4262_N();
                    for (Tag inbt1 : p_218927_3_) {
                        if (!(inbt1 instanceof U_2912_j)) {
                            throw t_148_a.create((Object)inbt1);
                        }
                        compoundnbt.n_1700_B((U_2912_j)inbt1);
                    }
                    i += compoundnbt1.equals(compoundnbt) ? 0 : 1;
                }
                return i;
            })))));
        }
        dispatcher.register(literalargumentbuilder);
    }

    private static int n_1700_B(int p_218944_0_, U_2912_j p_218944_1_, K_1178_t.v_4262_N p_218944_2_, List<Tag> p_218944_3_) throws CommandSyntaxException {
        List<Tag> collection = p_218944_2_.n_1700_B(p_218944_1_, q_2896_o::new);
        int i = 0;
        for (Tag inbt : collection) {
            if (!(inbt instanceof CollectionTag)) {
                throw w_1484_f.create((Object)inbt);
            }
            boolean flag = false;
            CollectionTag collectionnbt = (CollectionTag)inbt;
            int j = p_218944_0_ < 0 ? collectionnbt.size() + p_218944_0_ + 1 : p_218944_0_;
            for (Tag inbt1 : p_218944_3_) {
                try {
                    if (!collectionnbt.J_1907_R(j, inbt1.R_4764_Y())) continue;
                    ++j;
                    flag = true;
                }
                catch (IndexOutOfBoundsException indexoutofboundsexception) {
                    throw s_956_w.create((Object)j);
                }
            }
            i += flag ? 1 : 0;
        }
        return i;
    }

    private static ArgumentBuilder<y_2498_m, ?> n_1700_B(BiConsumer<ArgumentBuilder<y_2498_m, ?>, J_1907_R> p_218935_0_) {
        LiteralArgumentBuilder<y_2498_m> literalargumentbuilder = Q_2241_p.n_1700_B("modify");
        for (n_1700_B datacommand$idataprovider : J_1907_R) {
            datacommand$idataprovider.n_1700_B((ArgumentBuilder<y_2498_m, ?>)literalargumentbuilder, p_218940_2_ -> {
                RequiredArgumentBuilder<y_2498_m, K_1178_t.v_4262_N> argumentbuilder = Q_2241_p.n_1700_B("targetPath", K_1178_t.n_1700_B());
                for (n_1700_B datacommand$idataprovider1 : R_4764_Y) {
                    p_218935_0_.accept((ArgumentBuilder<y_2498_m, ?>)argumentbuilder, p_218934_2_ -> datacommand$idataprovider1.n_1700_B((ArgumentBuilder<y_2498_m, ?>)Q_2241_p.n_1700_B("from"), p_218929_3_ -> p_218929_3_.executes(p_218937_3_ -> {
                        List<Tag> list = Collections.singletonList(datacommand$idataprovider1.n_1700_B((CommandContext<y_2498_m>)p_218937_3_).n_1700_B());
                        return z_1856_e.n_1700_B((CommandContext<y_2498_m>)p_218937_3_, datacommand$idataprovider, p_218934_2_, list);
                    }).then(Q_2241_p.n_1700_B("sourcePath", K_1178_t.n_1700_B()).executes(p_218936_3_ -> {
                        DataAccessor idataaccessor = datacommand$idataprovider1.n_1700_B((CommandContext<y_2498_m>)p_218936_3_);
                        K_1178_t.v_4262_N nbtpathargument$nbtpath = K_1178_t.n_1700_B((CommandContext<y_2498_m>)p_218936_3_, "sourcePath");
                        List<Tag> list = nbtpathargument$nbtpath.n_1700_B(idataaccessor.n_1700_B());
                        return z_1856_e.n_1700_B((CommandContext<y_2498_m>)p_218936_3_, datacommand$idataprovider, p_218934_2_, list);
                    }))));
                }
                p_218935_0_.accept((ArgumentBuilder<y_2498_m, ?>)argumentbuilder, p_218949_1_ -> Q_2241_p.n_1700_B("value").then(Q_2241_p.n_1700_B("value", NbtTagArgument.n_1700_B()).executes(p_218952_2_ -> {
                    List<Tag> list = Collections.singletonList(NbtTagArgument.n_1700_B(p_218952_2_, "value"));
                    return z_1856_e.n_1700_B((CommandContext<y_2498_m>)p_218952_2_, datacommand$idataprovider, p_218949_1_, list);
                })));
                return p_218940_2_.then(argumentbuilder);
            });
        }
        return literalargumentbuilder;
    }

    private static int n_1700_B(CommandContext<y_2498_m> p_218933_0_, n_1700_B p_218933_1_, R_4764_Y p_218933_2_, List<Tag> p_218933_3_) throws CommandSyntaxException {
        DataAccessor idataaccessor = p_218933_1_.n_1700_B(p_218933_0_);
        K_1178_t.v_4262_N nbtpathargument$nbtpath = K_1178_t.n_1700_B(p_218933_0_, "targetPath");
        U_2912_j compoundnbt = idataaccessor.n_1700_B();
        int i = p_218933_2_.modify(p_218933_0_, compoundnbt, nbtpathargument$nbtpath, p_218933_3_);
        if (i == 0) {
            throw G_564_y.create();
        }
        idataaccessor.n_1700_B(compoundnbt);
        ((y_2498_m)p_218933_0_.getSource()).n_1700_B(idataaccessor.J_1907_R(), true);
        return i;
    }

    private static int n_1700_B(y_2498_m source, DataAccessor accessor, K_1178_t.v_4262_N pathIn) throws CommandSyntaxException {
        U_2912_j compoundnbt = accessor.n_1700_B();
        int i = pathIn.R_4764_Y(compoundnbt);
        if (i == 0) {
            throw G_564_y.create();
        }
        accessor.n_1700_B(compoundnbt);
        source.n_1700_B(accessor.J_1907_R(), true);
        return i;
    }

    private static Tag n_1700_B(K_1178_t.v_4262_N p_218928_0_, DataAccessor p_218928_1_) throws CommandSyntaxException {
        List<Tag> collection = p_218928_0_.n_1700_B(p_218928_1_.n_1700_B());
        Iterator iterator = collection.iterator();
        Tag inbt = (Tag)iterator.next();
        if (iterator.hasNext()) {
            throw v_4262_N.create();
        }
        return inbt;
    }

    private static int J_1907_R(y_2498_m source, DataAccessor accessor, K_1178_t.v_4262_N pathIn) throws CommandSyntaxException {
        int i;
        Tag inbt = z_1856_e.n_1700_B(pathIn, accessor);
        if (inbt instanceof NumericTag) {
            i = u_530_F.R_4764_Y(((NumericTag)inbt).t_148_a());
        } else if (inbt instanceof CollectionTag) {
            i = ((CollectionTag)inbt).size();
        } else if (inbt instanceof U_2912_j) {
            i = ((U_2912_j)inbt).P_1922_E();
        } else {
            if (!(inbt instanceof StringTag)) {
                throw u_1723_Y.create((Object)pathIn.toString());
            }
            i = inbt.M_588_G().length();
        }
        source.n_1700_B(accessor.n_1700_B(inbt), false);
        return i;
    }

    private static int n_1700_B(y_2498_m source, DataAccessor accessor, K_1178_t.v_4262_N pathIn, double scale) throws CommandSyntaxException {
        Tag inbt = z_1856_e.n_1700_B(pathIn, accessor);
        if (!(inbt instanceof NumericTag)) {
            throw P_1922_E.create((Object)pathIn.toString());
        }
        int i = u_530_F.R_4764_Y(((NumericTag)inbt).t_148_a() * scale);
        source.n_1700_B(accessor.n_1700_B(pathIn, scale, i), false);
        return i;
    }

    private static int n_1700_B(y_2498_m source, DataAccessor accessor) throws CommandSyntaxException {
        source.n_1700_B(accessor.n_1700_B((Tag)accessor.n_1700_B()), false);
        return 1;
    }

    private static int n_1700_B(y_2498_m source, DataAccessor accessor, U_2912_j nbt) throws CommandSyntaxException {
        U_2912_j compoundnbt1;
        U_2912_j compoundnbt = accessor.n_1700_B();
        if (compoundnbt.equals(compoundnbt1 = compoundnbt.v_4262_N().n_1700_B(nbt))) {
            throw G_564_y.create();
        }
        accessor.n_1700_B(compoundnbt1);
        source.n_1700_B(accessor.J_1907_R(), true);
        return 1;
    }

    public static interface n_1700_B {
        public DataAccessor n_1700_B(CommandContext<y_2498_m> var1) throws CommandSyntaxException;

        public ArgumentBuilder<y_2498_m, ?> n_1700_B(ArgumentBuilder<y_2498_m, ?> var1, Function<ArgumentBuilder<y_2498_m, ?>, ArgumentBuilder<y_2498_m, ?>> var2);
    }

    static interface R_4764_Y {
        public int modify(CommandContext<y_2498_m> var1, U_2912_j var2, K_1178_t.v_4262_N var3, List<Tag> var4) throws CommandSyntaxException;
    }

    static interface J_1907_R {
        public ArgumentBuilder<y_2498_m, ?> create(R_4764_Y var1);
    }
}



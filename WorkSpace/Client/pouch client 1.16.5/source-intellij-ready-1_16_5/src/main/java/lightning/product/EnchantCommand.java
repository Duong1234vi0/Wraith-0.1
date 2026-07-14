/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Collection;
import lightning.product.F_2904_S;
import lightning.product.K_1310_v;
import lightning.product.K_4096_w;
import lightning.product.N_4263_v;
import lightning.product.Q_2241_p;
import lightning.product.Z_1993_T;
import lightning.product.ItemEnchantmentArgument;
import lightning.product.i_4556_r;
import lightning.product.r_4811_B;
import lightning.product.y_2498_m;

public class EnchantCommand {
    private static final DynamicCommandExceptionType n_1700_B = new DynamicCommandExceptionType(p_208839_0_ -> new F_2904_S("commands.enchant.failed.entity", p_208839_0_));
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_208835_0_ -> new F_2904_S("commands.enchant.failed.itemless", p_208835_0_));
    private static final DynamicCommandExceptionType R_4764_Y = new DynamicCommandExceptionType(p_208837_0_ -> new F_2904_S("commands.enchant.failed.incompatible", p_208837_0_));
    private static final Dynamic2CommandExceptionType G_564_y = new Dynamic2CommandExceptionType((p_208840_0_, p_208840_1_) -> new F_2904_S("commands.enchant.failed.level", p_208840_0_, p_208840_1_));
    private static final SimpleCommandExceptionType P_1922_E = new SimpleCommandExceptionType((Message)new F_2904_S("commands.enchant.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("enchant").requires(p_203630_0_ -> p_203630_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("targets", i_4556_r.J_1907_R()).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("enchantment", ItemEnchantmentArgument.n_1700_B()).executes(p_202648_0_ -> EnchantCommand.n_1700_B((y_2498_m)p_202648_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_202648_0_, "targets"), ItemEnchantmentArgument.n_1700_B((CommandContext<y_2498_m>)p_202648_0_, "enchantment"), 1))).then(Q_2241_p.n_1700_B("level", IntegerArgumentType.integer((int)0)).executes(p_202650_0_ -> EnchantCommand.n_1700_B((y_2498_m)p_202650_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_202650_0_, "targets"), ItemEnchantmentArgument.n_1700_B((CommandContext<y_2498_m>)p_202650_0_, "enchantment"), IntegerArgumentType.getInteger((CommandContext)p_202650_0_, (String)"level")))))));
    }

    private static int n_1700_B(y_2498_m source, Collection<? extends N_4263_v> targets, K_1310_v enchantmentIn, int level) throws CommandSyntaxException {
        if (level > enchantmentIn.n_1700_B()) {
            throw G_564_y.create((Object)level, (Object)enchantmentIn.n_1700_B());
        }
        int i = 0;
        for (N_4263_v n_4263_v : targets) {
            if (n_4263_v instanceof r_4811_B) {
                r_4811_B livingentity = (r_4811_B)n_4263_v;
                Z_1993_T itemstack = livingentity.A_2714_y();
                if (!itemstack.n_1700_B()) {
                    if (enchantmentIn.n_1700_B(itemstack) && K_4096_w.n_1700_B(K_4096_w.n_1700_B(itemstack).keySet(), enchantmentIn)) {
                        itemstack.n_1700_B(enchantmentIn, level);
                        ++i;
                        continue;
                    }
                    if (targets.size() != 1) continue;
                    throw R_4764_Y.create((Object)itemstack.J_1907_R().w_1484_f(itemstack).getString());
                }
                if (targets.size() != 1) continue;
                throw J_1907_R.create((Object)livingentity.O_1309_Q().getString());
            }
            if (targets.size() != 1) continue;
            throw n_1700_B.create((Object)n_4263_v.O_1309_Q().getString());
        }
        if (i == 0) {
            throw P_1922_E.create();
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.enchant.success.single", enchantmentIn.G_564_y(level), targets.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.enchant.success.multiple", enchantmentIn.G_564_y(level), targets.size()), true);
        }
        return i;
    }
}



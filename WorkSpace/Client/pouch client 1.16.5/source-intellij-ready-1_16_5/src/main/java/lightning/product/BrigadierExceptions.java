/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.exceptions.BuiltInExceptionProvider
 *  com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.exceptions.BuiltInExceptionProvider;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import lightning.product.F_2904_S;

public class BrigadierExceptions
implements BuiltInExceptionProvider {
    private static final Dynamic2CommandExceptionType n_1700_B = new Dynamic2CommandExceptionType((p_208631_0_, p_208631_1_) -> new F_2904_S("argument.double.low", p_208631_1_, p_208631_0_));
    private static final Dynamic2CommandExceptionType J_1907_R = new Dynamic2CommandExceptionType((p_208627_0_, p_208627_1_) -> new F_2904_S("argument.double.big", p_208627_1_, p_208627_0_));
    private static final Dynamic2CommandExceptionType R_4764_Y = new Dynamic2CommandExceptionType((p_208624_0_, p_208624_1_) -> new F_2904_S("argument.float.low", p_208624_1_, p_208624_0_));
    private static final Dynamic2CommandExceptionType G_564_y = new Dynamic2CommandExceptionType((p_208622_0_, p_208622_1_) -> new F_2904_S("argument.float.big", p_208622_1_, p_208622_0_));
    private static final Dynamic2CommandExceptionType P_1922_E = new Dynamic2CommandExceptionType((p_208634_0_, p_208634_1_) -> new F_2904_S("argument.integer.low", p_208634_1_, p_208634_0_));
    private static final Dynamic2CommandExceptionType u_1723_Y = new Dynamic2CommandExceptionType((p_208630_0_, p_208630_1_) -> new F_2904_S("argument.integer.big", p_208630_1_, p_208630_0_));
    private static final Dynamic2CommandExceptionType v_4262_N = new Dynamic2CommandExceptionType((p_218034_0_, p_218034_1_) -> new F_2904_S("argument.long.low", p_218034_1_, p_218034_0_));
    private static final Dynamic2CommandExceptionType w_1484_f = new Dynamic2CommandExceptionType((p_218032_0_, p_218032_1_) -> new F_2904_S("argument.long.big", p_218032_1_, p_218032_0_));
    private static final DynamicCommandExceptionType t_148_a = new DynamicCommandExceptionType(p_208633_0_ -> new F_2904_S("argument.literal.incorrect", p_208633_0_));
    private static final SimpleCommandExceptionType s_956_w = new SimpleCommandExceptionType((Message)new F_2904_S("parsing.quote.expected.start"));
    private static final SimpleCommandExceptionType u_2550_I = new SimpleCommandExceptionType((Message)new F_2904_S("parsing.quote.expected.end"));
    private static final DynamicCommandExceptionType M_588_G = new DynamicCommandExceptionType(p_208635_0_ -> new F_2904_S("parsing.quote.escape", p_208635_0_));
    private static final DynamicCommandExceptionType P_4830_p = new DynamicCommandExceptionType(p_208629_0_ -> new F_2904_S("parsing.bool.invalid", p_208629_0_));
    private static final DynamicCommandExceptionType h_1847_R = new DynamicCommandExceptionType(p_208625_0_ -> new F_2904_S("parsing.int.invalid", p_208625_0_));
    private static final SimpleCommandExceptionType Q_4569_t = new SimpleCommandExceptionType((Message)new F_2904_S("parsing.int.expected"));
    private static final DynamicCommandExceptionType M_182_A = new DynamicCommandExceptionType(p_218855_0_ -> new F_2904_S("parsing.long.invalid", p_218855_0_));
    private static final SimpleCommandExceptionType t_1786_h = new SimpleCommandExceptionType((Message)new F_2904_S("parsing.long.expected"));
    private static final DynamicCommandExceptionType multiplayerClientSuggestionProvider = new DynamicCommandExceptionType(p_208626_0_ -> new F_2904_S("parsing.double.invalid", p_208626_0_));
    private static final SimpleCommandExceptionType w_1457_N = new SimpleCommandExceptionType((Message)new F_2904_S("parsing.double.expected"));
    private static final DynamicCommandExceptionType Y_601_j = new DynamicCommandExceptionType(p_208623_0_ -> new F_2904_S("parsing.float.invalid", p_208623_0_));
    private static final SimpleCommandExceptionType Y_259_p = new SimpleCommandExceptionType((Message)new F_2904_S("parsing.float.expected"));
    private static final SimpleCommandExceptionType Q_2552_b = new SimpleCommandExceptionType((Message)new F_2904_S("parsing.bool.expected"));
    private static final DynamicCommandExceptionType C_2741_M = new DynamicCommandExceptionType(p_208632_0_ -> new F_2904_S("parsing.expected", p_208632_0_));
    private static final SimpleCommandExceptionType k_2293_S = new SimpleCommandExceptionType((Message)new F_2904_S("command.unknown.command"));
    private static final SimpleCommandExceptionType q_2307_F = new SimpleCommandExceptionType((Message)new F_2904_S("command.unknown.argument"));
    private static final SimpleCommandExceptionType Z_875_P = new SimpleCommandExceptionType((Message)new F_2904_S("command.expected.separator"));
    private static final DynamicCommandExceptionType c_3005_b = new DynamicCommandExceptionType(p_208628_0_ -> new F_2904_S("command.exception", p_208628_0_));

    public Dynamic2CommandExceptionType doubleTooLow() {
        return n_1700_B;
    }

    public Dynamic2CommandExceptionType doubleTooHigh() {
        return J_1907_R;
    }

    public Dynamic2CommandExceptionType floatTooLow() {
        return R_4764_Y;
    }

    public Dynamic2CommandExceptionType floatTooHigh() {
        return G_564_y;
    }

    public Dynamic2CommandExceptionType integerTooLow() {
        return P_1922_E;
    }

    public Dynamic2CommandExceptionType integerTooHigh() {
        return u_1723_Y;
    }

    public Dynamic2CommandExceptionType longTooLow() {
        return v_4262_N;
    }

    public Dynamic2CommandExceptionType longTooHigh() {
        return w_1484_f;
    }

    public DynamicCommandExceptionType literalIncorrect() {
        return t_148_a;
    }

    public SimpleCommandExceptionType readerExpectedStartOfQuote() {
        return s_956_w;
    }

    public SimpleCommandExceptionType readerExpectedEndOfQuote() {
        return u_2550_I;
    }

    public DynamicCommandExceptionType readerInvalidEscape() {
        return M_588_G;
    }

    public DynamicCommandExceptionType readerInvalidBool() {
        return P_4830_p;
    }

    public DynamicCommandExceptionType readerInvalidInt() {
        return h_1847_R;
    }

    public SimpleCommandExceptionType readerExpectedInt() {
        return Q_4569_t;
    }

    public DynamicCommandExceptionType readerInvalidLong() {
        return M_182_A;
    }

    public SimpleCommandExceptionType readerExpectedLong() {
        return t_1786_h;
    }

    public DynamicCommandExceptionType readerInvalidDouble() {
        return multiplayerClientSuggestionProvider;
    }

    public SimpleCommandExceptionType readerExpectedDouble() {
        return w_1457_N;
    }

    public DynamicCommandExceptionType readerInvalidFloat() {
        return Y_601_j;
    }

    public SimpleCommandExceptionType readerExpectedFloat() {
        return Y_259_p;
    }

    public SimpleCommandExceptionType readerExpectedBool() {
        return Q_2552_b;
    }

    public DynamicCommandExceptionType readerExpectedSymbol() {
        return C_2741_M;
    }

    public SimpleCommandExceptionType dispatcherUnknownCommand() {
        return k_2293_S;
    }

    public SimpleCommandExceptionType dispatcherUnknownArgument() {
        return q_2307_F;
    }

    public SimpleCommandExceptionType dispatcherExpectedArgumentSeparator() {
        return Z_875_P;
    }

    public DynamicCommandExceptionType dispatcherParseException() {
        return c_3005_b;
    }
}



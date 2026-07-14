/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.ImmutableStringReader
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.ImmutableStringReader;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Arrays;
import java.util.Collection;
import lightning.product.F_2904_S;
import lightning.product.WorldCoordinate;
import lightning.product.u_530_F;
import lightning.product.y_2498_m;

public class d_4673_Y
implements ArgumentType<n_1700_B> {
    private static final Collection<String> J_1907_R = Arrays.asList("0", "~", "~-5");
    public static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("argument.angle.incomplete"));

    public static d_4673_Y n_1700_B() {
        return new d_4673_Y();
    }

    public static float n_1700_B(CommandContext<y_2498_m> p_242992_0_, String p_242992_1_) {
        return ((n_1700_B)p_242992_0_.getArgument(p_242992_1_, n_1700_B.class)).n_1700_B((y_2498_m)p_242992_0_.getSource());
    }

    public n_1700_B n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        if (!p_parse_1_.canRead()) {
            throw n_1700_B.createWithContext((ImmutableStringReader)p_parse_1_);
        }
        boolean flag = WorldCoordinate.J_1907_R(p_parse_1_);
        float f = p_parse_1_.canRead() && p_parse_1_.peek() != ' ' ? p_parse_1_.readFloat() : 0.0f;
        return new n_1700_B(f, flag);
    }

    public Collection<String> getExamples() {
        return J_1907_R;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }

    public static final class n_1700_B {
        private final float n_1700_B;
        private final boolean J_1907_R;

        private n_1700_B(float p_i242044_1_, boolean p_i242044_2_) {
            this.n_1700_B = p_i242044_1_;
            this.J_1907_R = p_i242044_2_;
        }

        public float n_1700_B(y_2498_m p_242995_1_) {
            return u_530_F.v_4262_N(this.J_1907_R ? this.n_1700_B + p_242995_1_.multiplayerClientSuggestionProvider().s_956_w : this.n_1700_B);
        }
    }
}



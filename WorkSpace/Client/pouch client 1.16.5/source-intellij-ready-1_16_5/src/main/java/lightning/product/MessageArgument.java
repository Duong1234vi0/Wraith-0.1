/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.annotation.Nullable;
import lightning.product.J_2545_z;
import lightning.product.U_2871_b;
import lightning.product.Y_995_C;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;

public class MessageArgument
implements ArgumentType<n_1700_B> {
    private static final Collection<String> n_1700_B = Arrays.asList("Hello world!", "foo", "@e", "Hello @p :)");

    public static MessageArgument n_1700_B() {
        return new MessageArgument();
    }

    public static x_282_a n_1700_B(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        return ((n_1700_B)context.getArgument(name, n_1700_B.class)).n_1700_B((y_2498_m)context.getSource(), ((y_2498_m)context.getSource()).n_1700_B(2));
    }

    public n_1700_B n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        return lightning.product.MessageArgument$n_1700_B.n_1700_B(p_parse_1_, true);
    }

    public Collection<String> getExamples() {
        return n_1700_B;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }

    public static class n_1700_B {
        private final String n_1700_B;
        private final J_1907_R[] J_1907_R;

        public n_1700_B(String textIn, J_1907_R[] selectorsIn) {
            this.n_1700_B = textIn;
            this.J_1907_R = selectorsIn;
        }

        public x_282_a n_1700_B(y_2498_m source, boolean allowSelectors) throws CommandSyntaxException {
            if (this.J_1907_R.length != 0 && allowSelectors) {
                U_2871_b iformattabletextcomponent = new U_2871_b(this.n_1700_B.substring(0, this.J_1907_R[0].n_1700_B()));
                int i = this.J_1907_R[0].n_1700_B();
                for (J_1907_R messageargument$part : this.J_1907_R) {
                    x_282_a itextcomponent = messageargument$part.n_1700_B(source);
                    if (i < messageargument$part.n_1700_B()) {
                        iformattabletextcomponent.n_1700_B(this.n_1700_B.substring(i, messageargument$part.n_1700_B()));
                    }
                    if (itextcomponent != null) {
                        iformattabletextcomponent.n_1700_B(itextcomponent);
                    }
                    i = messageargument$part.J_1907_R();
                }
                if (i < this.n_1700_B.length()) {
                    iformattabletextcomponent.n_1700_B(this.n_1700_B.substring(i, this.n_1700_B.length()));
                }
                return iformattabletextcomponent;
            }
            return new U_2871_b(this.n_1700_B);
        }

        public static n_1700_B n_1700_B(StringReader reader, boolean allowSelectors) throws CommandSyntaxException {
            String s = reader.getString().substring(reader.getCursor(), reader.getTotalLength());
            if (!allowSelectors) {
                reader.setCursor(reader.getTotalLength());
                return new n_1700_B(s, new J_1907_R[0]);
            }
            ArrayList list = Lists.newArrayList();
            int i = reader.getCursor();
            while (true) {
                Y_995_C entityselector;
                int j;
                block7: {
                    if (!reader.canRead()) {
                        return new n_1700_B(s, list.toArray(new J_1907_R[list.size()]));
                    }
                    if (reader.peek() == '@') {
                        j = reader.getCursor();
                        try {
                            J_2545_z entityselectorparser = new J_2545_z(reader);
                            entityselector = entityselectorparser.w_1457_N();
                            break block7;
                        }
                        catch (CommandSyntaxException commandsyntaxexception) {
                            if (commandsyntaxexception.getType() != J_2545_z.G_564_y && commandsyntaxexception.getType() != J_2545_z.J_1907_R) {
                                throw commandsyntaxexception;
                            }
                            reader.setCursor(j + 1);
                            continue;
                        }
                    }
                    reader.skip();
                    continue;
                }
                list.add(new J_1907_R(j - i, reader.getCursor() - i, entityselector));
            }
        }
    }

    public static class J_1907_R {
        private final int n_1700_B;
        private final int J_1907_R;
        private final Y_995_C R_4764_Y;

        public J_1907_R(int startIn, int endIn, Y_995_C selectorIn) {
            this.n_1700_B = startIn;
            this.J_1907_R = endIn;
            this.R_4764_Y = selectorIn;
        }

        public int n_1700_B() {
            return this.n_1700_B;
        }

        public int J_1907_R() {
            return this.J_1907_R;
        }

        @Nullable
        public x_282_a n_1700_B(y_2498_m source) throws CommandSyntaxException {
            return Y_995_C.n_1700_B(this.R_4764_Y.J_1907_R(source));
        }
    }
}



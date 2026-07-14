/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import lightning.product.U_2871_b;
import lightning.product.Tag;
import lightning.product.TagType;
import lightning.product.o_926_S;
import lightning.product.x_282_a;

public class EndTag
implements Tag {
    public static final TagType<EndTag> n_1700_B = new TagType<EndTag>(){

        public EndTag n_1700_B(DataInput input, int depth, o_926_S accounter) {
            accounter.n_1700_B(64L);
            return J_1907_R;
        }

        @Override
        public String n_1700_B() {
            return "END";
        }

        @Override
        public String J_1907_R() {
            return "TAG_End";
        }

        @Override
        public boolean R_4764_Y() {
            return true;
        }

        @Override
        public /* synthetic */ Tag J_1907_R(DataInput dataInput, int n, o_926_S o_926_S2) throws IOException {
            return this.n_1700_B(dataInput, n, o_926_S2);
        }
    };
    public static final EndTag J_1907_R = new EndTag();

    private EndTag() {
    }

    @Override
    public void n_1700_B(DataOutput output) throws IOException {
    }

    @Override
    public byte n_1700_B() {
        return 0;
    }

    public TagType<EndTag> J_1907_R() {
        return n_1700_B;
    }

    @Override
    public String toString() {
        return "END";
    }

    public EndTag G_564_y() {
        return this;
    }

    @Override
    public x_282_a n_1700_B(String indentation, int indentDepth) {
        return U_2871_b.R_4764_Y;
    }

    @Override
    public /* synthetic */ Tag R_4764_Y() {
        return this.G_564_y();
    }
}



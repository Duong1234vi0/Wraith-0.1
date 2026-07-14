/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.DataInput;
import java.io.IOException;
import lightning.product.Tag;
import lightning.product.o_926_S;
import lightning.product.EndTag;

public interface TagType<T extends Tag> {
    public T J_1907_R(DataInput var1, int var2, o_926_S var3) throws IOException;

    default public boolean R_4764_Y() {
        return false;
    }

    public String n_1700_B();

    public String J_1907_R();

    public static TagType<EndTag> n_1700_B(final int id) {
        return new TagType<EndTag>(){

            public EndTag n_1700_B(DataInput input, int depth, o_926_S accounter) throws IOException {
                throw new IllegalArgumentException("Invalid tag id: " + id);
            }

            @Override
            public String n_1700_B() {
                return "INVALID[" + id + "]";
            }

            @Override
            public String J_1907_R() {
                return "UNKNOWN_" + id;
            }

            @Override
            public /* synthetic */ Tag J_1907_R(DataInput dataInput, int n, o_926_S o_926_S2) throws IOException {
                return this.n_1700_B(dataInput, n, o_926_S2);
            }
        };
    }
}



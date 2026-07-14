/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main_1<T> {
    private final List<T> n_1700_B = Lists.newArrayList();
    private final Spliterator<T> J_1907_R;

    public Main_1(Stream<T> stream) {
        this.J_1907_R = stream.spliterator();
    }

    public Stream<T> n_1700_B() {
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<T>(Long.MAX_VALUE, 0){
            private int J_1907_R;

            @Override
            public boolean tryAdvance(Consumer<? super T> p_tryAdvance_1_) {
                while (this.J_1907_R >= Main_1.this.n_1700_B.size()) {
                    if (Main_1.this.J_1907_R.tryAdvance(Main_1.this.n_1700_B::add)) continue;
                    return false;
                }
                p_tryAdvance_1_.accept(Main_1.this.n_1700_B.get(this.J_1907_R++));
                return true;
            }
        }, false);
    }
}



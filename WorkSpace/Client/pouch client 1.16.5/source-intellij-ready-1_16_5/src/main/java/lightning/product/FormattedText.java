/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Optional;
import lightning.product.X_1446_C;
import lightning.product.Z_1567_W;

public interface FormattedText {
    public static final Optional<X_1446_C> n_1700_B = Optional.of(X_1446_C.n_1700_B);
    public static final FormattedText J_1907_R = new FormattedText(){

        @Override
        public <T> Optional<T> n_1700_B(J_1907_R<T> acceptor) {
            return Optional.empty();
        }

        @Override
        public <T> Optional<T> n_1700_B(n_1700_B<T> acceptor, Z_1567_W styleIn) {
            return Optional.empty();
        }
    };

    public <T> Optional<T> n_1700_B(J_1907_R<T> var1);

    public <T> Optional<T> n_1700_B(n_1700_B<T> var1, Z_1567_W var2);

    public static FormattedText R_4764_Y(final String p_240652_0_) {
        return new FormattedText(){

            @Override
            public <T> Optional<T> n_1700_B(J_1907_R<T> acceptor) {
                return acceptor.accept(p_240652_0_);
            }

            @Override
            public <T> Optional<T> n_1700_B(n_1700_B<T> acceptor, Z_1567_W styleIn) {
                return acceptor.accept(styleIn, p_240652_0_);
            }
        };
    }

    public static FormattedText n_1700_B(final String p_240653_0_, final Z_1567_W p_240653_1_) {
        return new FormattedText(){

            @Override
            public <T> Optional<T> n_1700_B(J_1907_R<T> acceptor) {
                return acceptor.accept(p_240653_0_);
            }

            @Override
            public <T> Optional<T> n_1700_B(n_1700_B<T> acceptor, Z_1567_W styleIn) {
                return acceptor.accept(p_240653_1_.n_1700_B(styleIn), p_240653_0_);
            }
        };
    }

    public static FormattedText n_1700_B(FormattedText ... p_240655_0_) {
        return FormattedText.n_1700_B((List<FormattedText>)ImmutableList.copyOf((Object[])p_240655_0_));
    }

    public static FormattedText n_1700_B(final List<FormattedText> p_240654_0_) {
        return new FormattedText(){

            @Override
            public <T> Optional<T> n_1700_B(J_1907_R<T> acceptor) {
                for (FormattedText itextproperties : p_240654_0_) {
                    Optional<T> optional = itextproperties.n_1700_B(acceptor);
                    if (!optional.isPresent()) continue;
                    return optional;
                }
                return Optional.empty();
            }

            @Override
            public <T> Optional<T> n_1700_B(n_1700_B<T> acceptor, Z_1567_W styleIn) {
                for (FormattedText itextproperties : p_240654_0_) {
                    Optional<T> optional = itextproperties.n_1700_B(acceptor, styleIn);
                    if (!optional.isPresent()) continue;
                    return optional;
                }
                return Optional.empty();
            }
        };
    }

    default public String getString() {
        StringBuilder stringbuilder = new StringBuilder();
        this.n_1700_B(p_241754_1_ -> {
            stringbuilder.append(p_241754_1_);
            return Optional.empty();
        });
        return stringbuilder.toString();
    }

    public static interface J_1907_R<T> {
        public Optional<T> accept(String var1);
    }

    public static interface n_1700_B<T> {
        public Optional<T> accept(Z_1567_W var1, String var2);
    }
}



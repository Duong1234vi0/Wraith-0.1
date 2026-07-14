/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Optional;
import lightning.product.D_4024_W;
import lightning.product.FormattedText;
import lightning.product.FormattedCharSink;
import lightning.product.X_1446_C;
import lightning.product.Z_1567_W;

public class StringDecomposer {
    private static final Optional<Object> n_1700_B = Optional.of(X_1446_C.n_1700_B);

    private static boolean n_1700_B(Z_1567_W p_238344_0_, FormattedCharSink p_238344_1_, int p_238344_2_, char p_238344_3_) {
        return Character.isSurrogate(p_238344_3_) ? p_238344_1_.accept(p_238344_2_, p_238344_0_, 65533) : p_238344_1_.accept(p_238344_2_, p_238344_0_, p_238344_3_);
    }

    public static boolean n_1700_B(String p_238341_0_, Z_1567_W p_238341_1_, FormattedCharSink p_238341_2_) {
        int i = p_238341_0_.length();
        for (int j = 0; j < i; ++j) {
            char c0 = p_238341_0_.charAt(j);
            if (Character.isHighSurrogate(c0)) {
                if (j + 1 >= i) {
                    if (p_238341_2_.accept(j, p_238341_1_, 65533)) break;
                    return false;
                }
                char c1 = p_238341_0_.charAt(j + 1);
                if (Character.isLowSurrogate(c1)) {
                    if (!p_238341_2_.accept(j, p_238341_1_, Character.toCodePoint(c0, c1))) {
                        return false;
                    }
                    ++j;
                    continue;
                }
                if (p_238341_2_.accept(j, p_238341_1_, 65533)) continue;
                return false;
            }
            if (StringDecomposer.n_1700_B(p_238341_1_, p_238341_2_, j, c0)) continue;
            return false;
        }
        return true;
    }

    public static boolean J_1907_R(String p_238345_0_, Z_1567_W p_238345_1_, FormattedCharSink p_238345_2_) {
        int i = p_238345_0_.length();
        for (int j = i - 1; j >= 0; --j) {
            char c0 = p_238345_0_.charAt(j);
            if (Character.isLowSurrogate(c0)) {
                if (j - 1 < 0) {
                    if (p_238345_2_.accept(0, p_238345_1_, 65533)) break;
                    return false;
                }
                char c1 = p_238345_0_.charAt(j - 1);
                if (!(Character.isHighSurrogate(c1) ? !p_238345_2_.accept(--j, p_238345_1_, Character.toCodePoint(c1, c0)) : !p_238345_2_.accept(j, p_238345_1_, 65533))) continue;
                return false;
            }
            if (StringDecomposer.n_1700_B(p_238345_1_, p_238345_2_, j, c0)) continue;
            return false;
        }
        return true;
    }

    public static boolean R_4764_Y(String p_238346_0_, Z_1567_W p_238346_1_, FormattedCharSink p_238346_2_) {
        return StringDecomposer.n_1700_B(p_238346_0_, 0, p_238346_1_, p_238346_2_);
    }

    public static boolean n_1700_B(String p_238339_0_, int p_238339_1_, Z_1567_W p_238339_2_, FormattedCharSink p_238339_3_) {
        return StringDecomposer.n_1700_B(p_238339_0_, p_238339_1_, p_238339_2_, p_238339_2_, p_238339_3_);
    }

    public static boolean n_1700_B(String p_238340_0_, int p_238340_1_, Z_1567_W p_238340_2_, Z_1567_W p_238340_3_, FormattedCharSink p_238340_4_) {
        int i = p_238340_0_.length();
        Z_1567_W style = p_238340_2_;
        for (int j = p_238340_1_; j < i; ++j) {
            char c0 = p_238340_0_.charAt(j);
            if (c0 == '\u00a7') {
                if (j + 1 >= i) break;
                char c1 = p_238340_0_.charAt(j + 1);
                D_4024_W textformatting = D_4024_W.n_1700_B(c1);
                if (textformatting != null) {
                    style = textformatting == D_4024_W.Q_2552_b ? p_238340_3_ : style.R_4764_Y(textformatting);
                }
                ++j;
                continue;
            }
            if (Character.isHighSurrogate(c0)) {
                if (j + 1 >= i) {
                    if (p_238340_4_.accept(j, style, 65533)) break;
                    return false;
                }
                char c2 = p_238340_0_.charAt(j + 1);
                if (Character.isLowSurrogate(c2)) {
                    if (!p_238340_4_.accept(j, style, Character.toCodePoint(c0, c2))) {
                        return false;
                    }
                    ++j;
                    continue;
                }
                if (p_238340_4_.accept(j, style, 65533)) continue;
                return false;
            }
            if (StringDecomposer.n_1700_B(style, p_238340_4_, j, c0)) continue;
            return false;
        }
        return true;
    }

    public static boolean n_1700_B(FormattedText p_238343_0_, Z_1567_W p_238343_1_, FormattedCharSink p_238343_2_) {
        return !p_238343_0_.n_1700_B((p_238337_1_, p_238337_2_) -> StringDecomposer.n_1700_B(p_238337_2_, 0, p_238337_1_, p_238343_2_) ? Optional.empty() : n_1700_B, p_238343_1_).isPresent();
    }

    public static String n_1700_B(String p_238338_0_) {
        StringBuilder stringbuilder = new StringBuilder();
        StringDecomposer.n_1700_B(p_238338_0_, Z_1567_W.n_1700_B, (int p_238342_1_, Z_1567_W p_238342_2_, int p_238342_3_) -> {
            stringbuilder.appendCodePoint(p_238342_3_);
            return true;
        });
        return stringbuilder.toString();
    }

    public static String n_1700_B(FormattedText p_244782_0_) {
        StringBuilder stringbuilder = new StringBuilder();
        StringDecomposer.n_1700_B(p_244782_0_, Z_1567_W.n_1700_B, (int p_244781_1_, Z_1567_W p_244781_2_, int p_244781_3_) -> {
            stringbuilder.appendCodePoint(p_244781_3_);
            return true;
        });
        return stringbuilder.toString();
    }
}



/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.mutable.MutableFloat
 *  org.apache.commons.lang3.mutable.MutableInt
 *  org.apache.commons.lang3.mutable.MutableObject
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.StringDecomposer;
import lightning.product.FormattedText;
import lightning.product.FormattedCharSink;
import lightning.product.Z_1567_W;
import lightning.product.MinecraftAccess;
import lightning.product.FormattedCharSequence;
import lightning.product.NameProtect;
import lightning.product.ClientBootstrap;
import lightning.product.ComponentCollector;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.mutable.MutableObject;

public class f_1703_u {
    private final n_1700_B n_1700_B;

    public f_1703_u(n_1700_B p_i232243_1_) {
        this.n_1700_B = p_i232243_1_;
    }

    public float n_1700_B(@Nullable String p_238350_1_) {
        if (p_238350_1_ == null) {
            return 0.0f;
        }
        try {
            NameProtect p = (NameProtect)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(NameProtect.class);
            if (p != null && p.w_1484_f() && MinecraftAccess.c_3005_b.k_2293_S() != null) {
                p_238350_1_ = NameProtect.G_564_y(p_238350_1_);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        MutableFloat mutablefloat = new MutableFloat();
        StringDecomposer.R_4764_Y(p_238350_1_, Z_1567_W.n_1700_B, (p_238363_2_, p_238363_3_, p_238363_4_) -> {
            mutablefloat.add(this.n_1700_B.getWidth(p_238363_4_, p_238363_3_));
            return true;
        });
        return mutablefloat.floatValue();
    }

    public float n_1700_B(FormattedText p_238356_1_) {
        MutableFloat mutablefloat = new MutableFloat();
        StringDecomposer.n_1700_B(p_238356_1_, Z_1567_W.n_1700_B, (int p_238359_2_, Z_1567_W p_238359_3_, int p_238359_4_) -> {
            mutablefloat.add(this.n_1700_B.getWidth(p_238359_4_, p_238359_3_));
            return true;
        });
        return mutablefloat.floatValue();
    }

    public float n_1700_B(FormattedCharSequence p_243238_1_) {
        MutableFloat mutablefloat = new MutableFloat();
        p_243238_1_.accept((p_243243_2_, p_243243_3_, p_243243_4_) -> {
            mutablefloat.add(this.n_1700_B.getWidth(p_243243_4_, p_243243_3_));
            return true;
        });
        return mutablefloat.floatValue();
    }

    public int n_1700_B(String p_238352_1_, int p_238352_2_, Z_1567_W p_238352_3_) {
        G_564_y charactermanager$stringwidthprocessor = new G_564_y(p_238352_2_);
        StringDecomposer.n_1700_B(p_238352_1_, p_238352_3_, (FormattedCharSink)charactermanager$stringwidthprocessor);
        return charactermanager$stringwidthprocessor.n_1700_B();
    }

    public String J_1907_R(String p_238361_1_, int p_238361_2_, Z_1567_W p_238361_3_) {
        return p_238361_1_.substring(0, this.n_1700_B(p_238361_1_, p_238361_2_, p_238361_3_));
    }

    public String R_4764_Y(String p_238364_1_, int p_238364_2_, Z_1567_W p_238364_3_) {
        MutableFloat mutablefloat = new MutableFloat();
        MutableInt mutableint = new MutableInt(p_238364_1_.length());
        StringDecomposer.J_1907_R(p_238364_1_, p_238364_3_, (p_238360_4_, p_238360_5_, p_238360_6_) -> {
            float f = mutablefloat.addAndGet(this.n_1700_B.getWidth(p_238360_6_, p_238360_5_));
            if (f > (float)p_238364_2_) {
                return false;
            }
            mutableint.setValue(p_238360_4_);
            return true;
        });
        return p_238364_1_.substring(mutableint.intValue());
    }

    @Nullable
    public Z_1567_W n_1700_B(FormattedText p_238357_1_, int p_238357_2_) {
        G_564_y charactermanager$stringwidthprocessor = new G_564_y(p_238357_2_);
        return p_238357_1_.n_1700_B((Z_1567_W p_238348_1_, String p_238348_2_) -> StringDecomposer.R_4764_Y(p_238348_2_, p_238348_1_, charactermanager$stringwidthprocessor) ? Optional.empty() : Optional.of(p_238348_1_), Z_1567_W.n_1700_B).orElse(null);
    }

    @Nullable
    public Z_1567_W n_1700_B(FormattedCharSequence p_243239_1_, int p_243239_2_) {
        G_564_y charactermanager$stringwidthprocessor = new G_564_y(p_243239_2_);
        MutableObject mutableobject = new MutableObject();
        p_243239_1_.accept((p_243240_2_, p_243240_3_, p_243240_4_) -> {
            if (!charactermanager$stringwidthprocessor.accept(p_243240_2_, p_243240_3_, p_243240_4_)) {
                mutableobject.setValue((Object)p_243240_3_);
                return false;
            }
            return true;
        });
        return (Z_1567_W)mutableobject.getValue();
    }

    public FormattedText n_1700_B(FormattedText p_238358_1_, int p_238358_2_, Z_1567_W p_238358_3_) {
        final G_564_y charactermanager$stringwidthprocessor = new G_564_y(p_238358_2_);
        return p_238358_1_.n_1700_B(new FormattedText.n_1700_B<FormattedText>(){
            private final ComponentCollector J_1907_R = new ComponentCollector();

            @Override
            public Optional<FormattedText> accept(Z_1567_W p_accept_1_, String p_accept_2_) {
                charactermanager$stringwidthprocessor.J_1907_R();
                if (!StringDecomposer.R_4764_Y(p_accept_2_, p_accept_1_, charactermanager$stringwidthprocessor)) {
                    String s = p_accept_2_.substring(0, charactermanager$stringwidthprocessor.n_1700_B());
                    if (!s.isEmpty()) {
                        this.J_1907_R.n_1700_B(FormattedText.n_1700_B(s, p_accept_1_));
                    }
                    return Optional.of(this.J_1907_R.J_1907_R());
                }
                if (!p_accept_2_.isEmpty()) {
                    this.J_1907_R.n_1700_B(FormattedText.n_1700_B(p_accept_2_, p_accept_1_));
                }
                return Optional.empty();
            }
        }, p_238358_3_).orElse(p_238358_1_);
    }

    public static int n_1700_B(String p_238351_0_, int p_238351_1_, int p_238351_2_, boolean p_238351_3_) {
        int i = p_238351_2_;
        boolean flag = p_238351_1_ < 0;
        int j = Math.abs(p_238351_1_);
        for (int k = 0; k < j; ++k) {
            if (flag) {
                while (p_238351_3_ && i > 0 && (p_238351_0_.charAt(i - 1) == ' ' || p_238351_0_.charAt(i - 1) == '\n')) {
                    --i;
                }
                while (i > 0 && p_238351_0_.charAt(i - 1) != ' ' && p_238351_0_.charAt(i - 1) != '\n') {
                    --i;
                }
                continue;
            }
            int l = p_238351_0_.length();
            int i1 = p_238351_0_.indexOf(32, i);
            int j1 = p_238351_0_.indexOf(10, i);
            i = i1 == -1 && j1 == -1 ? -1 : (i1 != -1 && j1 != -1 ? Math.min(i1, j1) : (i1 != -1 ? i1 : j1));
            if (i == -1) {
                i = l;
                continue;
            }
            while (p_238351_3_ && i < l && (p_238351_0_.charAt(i) == ' ' || p_238351_0_.charAt(i) == '\n')) {
                ++i;
            }
        }
        return i;
    }

    public void n_1700_B(String p_238353_1_, int p_238353_2_, Z_1567_W p_238353_3_, boolean p_238353_4_, J_1907_R p_238353_5_) {
        int i = 0;
        int j = p_238353_1_.length();
        Z_1567_W style = p_238353_3_;
        while (i < j) {
            R_4764_Y charactermanager$multilineprocessor = new R_4764_Y(p_238353_2_);
            boolean flag = StringDecomposer.n_1700_B(p_238353_1_, i, style, p_238353_3_, charactermanager$multilineprocessor);
            if (flag) {
                p_238353_5_.accept(style, i, j);
                break;
            }
            int k = charactermanager$multilineprocessor.n_1700_B();
            char c0 = p_238353_1_.charAt(k);
            int l = c0 != '\n' && c0 != ' ' ? k : k + 1;
            p_238353_5_.accept(style, i, p_238353_4_ ? l : k);
            i = l;
            style = charactermanager$multilineprocessor.J_1907_R();
        }
    }

    public List<FormattedText> G_564_y(String p_238365_1_, int p_238365_2_, Z_1567_W p_238365_3_) {
        ArrayList list = Lists.newArrayList();
        this.n_1700_B(p_238365_1_, p_238365_2_, p_238365_3_, false, (p_238354_2_, p_238354_3_, p_238354_4_) -> list.add(FormattedText.n_1700_B(p_238365_1_.substring(p_238354_3_, p_238354_4_), p_238354_2_)));
        return list;
    }

    public List<FormattedText> J_1907_R(FormattedText p_238362_1_, int p_238362_2_, Z_1567_W p_238362_3_) {
        ArrayList list = Lists.newArrayList();
        this.n_1700_B(p_238362_1_, p_238362_2_, p_238362_3_, (p_243241_1_, p_243241_2_) -> list.add(p_243241_1_));
        return list;
    }

    public void n_1700_B(FormattedText p_243242_1_, int p_243242_2_, Z_1567_W p_243242_3_, BiConsumer<FormattedText, Boolean> p_243242_4_) {
        ArrayList list = Lists.newArrayList();
        p_243242_1_.n_1700_B((Z_1567_W p_238355_1_, String p_238355_2_) -> {
            if (!p_238355_2_.isEmpty()) {
                list.add(new P_1922_E(p_238355_2_, p_238355_1_));
            }
            return Optional.empty();
        }, p_243242_3_);
        u_1723_Y charactermanager$substyledtext = new u_1723_Y(list);
        boolean flag = true;
        boolean flag1 = false;
        boolean flag2 = false;
        block0: while (flag) {
            flag = false;
            R_4764_Y charactermanager$multilineprocessor = new R_4764_Y(p_243242_2_);
            for (P_1922_E charactermanager$styleoverridingtextcomponent : charactermanager$substyledtext.n_1700_B) {
                boolean flag3 = StringDecomposer.n_1700_B(charactermanager$styleoverridingtextcomponent.R_4764_Y, 0, charactermanager$styleoverridingtextcomponent.G_564_y, p_243242_3_, charactermanager$multilineprocessor);
                if (!flag3) {
                    int i = charactermanager$multilineprocessor.n_1700_B();
                    Z_1567_W style = charactermanager$multilineprocessor.J_1907_R();
                    char c0 = charactermanager$substyledtext.n_1700_B(i);
                    boolean flag4 = c0 == '\n';
                    boolean flag5 = flag4 || c0 == ' ';
                    flag1 = flag4;
                    FormattedText itextproperties = charactermanager$substyledtext.n_1700_B(i, flag5 ? 1 : 0, style);
                    p_243242_4_.accept(itextproperties, flag2);
                    flag2 = !flag4;
                    flag = true;
                    continue block0;
                }
                charactermanager$multilineprocessor.n_1700_B(charactermanager$styleoverridingtextcomponent.R_4764_Y.length());
            }
        }
        FormattedText itextproperties1 = charactermanager$substyledtext.n_1700_B();
        if (itextproperties1 != null) {
            p_243242_4_.accept(itextproperties1, flag2);
        } else if (flag1) {
            p_243242_4_.accept(FormattedText.J_1907_R, false);
        }
    }

    @FunctionalInterface
    public static interface n_1700_B {
        public float getWidth(int var1, Z_1567_W var2);
    }

    class G_564_y
    implements FormattedCharSink {
        private float J_1907_R;
        private int R_4764_Y;

        public G_564_y(float p_i232248_2_) {
            this.J_1907_R = p_i232248_2_;
        }

        @Override
        public boolean accept(int p_accept_1_, Z_1567_W p_accept_2_, int p_accept_3_) {
            this.J_1907_R -= f_1703_u.this.n_1700_B.getWidth(p_accept_3_, p_accept_2_);
            if (this.J_1907_R >= 0.0f) {
                this.R_4764_Y = p_accept_1_ + Character.charCount(p_accept_3_);
                return true;
            }
            return false;
        }

        public int n_1700_B() {
            return this.R_4764_Y;
        }

        public void J_1907_R() {
            this.R_4764_Y = 0;
        }
    }

    class R_4764_Y
    implements FormattedCharSink {
        private final float J_1907_R;
        private int R_4764_Y = -1;
        private Z_1567_W G_564_y = Z_1567_W.n_1700_B;
        private boolean P_1922_E;
        private float u_1723_Y;
        private int v_4262_N = -1;
        private Z_1567_W w_1484_f = Z_1567_W.n_1700_B;
        private int t_148_a;
        private int s_956_w;

        public R_4764_Y(float p_i232246_2_) {
            this.J_1907_R = Math.max(p_i232246_2_, 1.0f);
        }

        @Override
        public boolean accept(int p_accept_1_, Z_1567_W p_accept_2_, int p_accept_3_) {
            int i = p_accept_1_ + this.s_956_w;
            switch (p_accept_3_) {
                case 10: {
                    return this.n_1700_B(i, p_accept_2_);
                }
                case 32: {
                    this.v_4262_N = i;
                    this.w_1484_f = p_accept_2_;
                }
            }
            float f = f_1703_u.this.n_1700_B.getWidth(p_accept_3_, p_accept_2_);
            this.u_1723_Y += f;
            if (this.P_1922_E && this.u_1723_Y > this.J_1907_R) {
                return this.v_4262_N != -1 ? this.n_1700_B(this.v_4262_N, this.w_1484_f) : this.n_1700_B(i, p_accept_2_);
            }
            this.P_1922_E |= f != 0.0f;
            this.t_148_a = i + Character.charCount(p_accept_3_);
            return true;
        }

        private boolean n_1700_B(int p_238388_1_, Z_1567_W p_238388_2_) {
            this.R_4764_Y = p_238388_1_;
            this.G_564_y = p_238388_2_;
            return false;
        }

        private boolean R_4764_Y() {
            return this.R_4764_Y != -1;
        }

        public int n_1700_B() {
            return this.R_4764_Y() ? this.R_4764_Y : this.t_148_a;
        }

        public Z_1567_W J_1907_R() {
            return this.G_564_y;
        }

        public void n_1700_B(int p_238387_1_) {
            this.s_956_w += p_238387_1_;
        }
    }

    @FunctionalInterface
    public static interface J_1907_R {
        public void accept(Z_1567_W var1, int var2, int var3);
    }

    static class u_1723_Y {
        private final List<P_1922_E> n_1700_B;
        private String J_1907_R;

        public u_1723_Y(List<P_1922_E> p_i232245_1_) {
            this.n_1700_B = p_i232245_1_;
            this.J_1907_R = p_i232245_1_.stream().map(p_238375_0_ -> p_238375_0_.R_4764_Y).collect(Collectors.joining());
        }

        public char n_1700_B(int p_238372_1_) {
            return this.J_1907_R.charAt(p_238372_1_);
        }

        public FormattedText n_1700_B(int p_238373_1_, int p_238373_2_, Z_1567_W p_238373_3_) {
            ComponentCollector textpropertiesmanager = new ComponentCollector();
            ListIterator<P_1922_E> listiterator = this.n_1700_B.listIterator();
            int i = p_238373_1_;
            boolean flag = false;
            while (listiterator.hasNext()) {
                P_1922_E charactermanager$styleoverridingtextcomponent = listiterator.next();
                String s = charactermanager$styleoverridingtextcomponent.R_4764_Y;
                int j = s.length();
                if (!flag) {
                    if (i > j) {
                        textpropertiesmanager.n_1700_B(charactermanager$styleoverridingtextcomponent);
                        listiterator.remove();
                        i -= j;
                    } else {
                        String s1 = s.substring(0, i);
                        if (!s1.isEmpty()) {
                            textpropertiesmanager.n_1700_B(FormattedText.n_1700_B(s1, charactermanager$styleoverridingtextcomponent.G_564_y));
                        }
                        i += p_238373_2_;
                        flag = true;
                    }
                }
                if (!flag) continue;
                if (i <= j) {
                    String s2 = s.substring(i);
                    if (s2.isEmpty()) {
                        listiterator.remove();
                        break;
                    }
                    listiterator.set(new P_1922_E(s2, p_238373_3_));
                    break;
                }
                listiterator.remove();
                i -= j;
            }
            this.J_1907_R = this.J_1907_R.substring(p_238373_1_ + p_238373_2_);
            return textpropertiesmanager.J_1907_R();
        }

        @Nullable
        public FormattedText n_1700_B() {
            ComponentCollector textpropertiesmanager = new ComponentCollector();
            this.n_1700_B.forEach(textpropertiesmanager::n_1700_B);
            this.n_1700_B.clear();
            return textpropertiesmanager.n_1700_B();
        }
    }

    static class P_1922_E
    implements FormattedText {
        private final String R_4764_Y;
        private final Z_1567_W G_564_y;

        public P_1922_E(String p_i232247_1_, Z_1567_W p_i232247_2_) {
            this.R_4764_Y = p_i232247_1_;
            this.G_564_y = p_i232247_2_;
        }

        @Override
        public <T> Optional<T> n_1700_B(FormattedText.J_1907_R<T> acceptor) {
            return acceptor.accept(this.R_4764_Y);
        }

        @Override
        public <T> Optional<T> n_1700_B(FormattedText.n_1700_B<T> acceptor, Z_1567_W styleIn) {
            return acceptor.accept(this.G_564_y.n_1700_B(styleIn), this.R_4764_Y);
        }
    }
}




/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import lightning.product.ContextAwareComponent;
import lightning.product.FormattedText;
import lightning.product.MutableComponent;
import lightning.product.L_3144_D;
import lightning.product.N_4263_v;
import lightning.product.Z_1567_W;
import lightning.product.l_4033_W;
import lightning.product.TranslatableFormatException;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;

public class F_2904_S
extends L_3144_D
implements ContextAwareComponent {
    private static final Object[] R_4764_Y = new Object[0];
    private static final FormattedText G_564_y = FormattedText.R_4764_Y("%");
    private static final FormattedText P_1922_E = FormattedText.R_4764_Y("null");
    private final String v_4262_N;
    private final Object[] w_1484_f;
    @Nullable
    private l_4033_W t_148_a;
    private final List<FormattedText> s_956_w = Lists.newArrayList();
    private static final Pattern u_2550_I = Pattern.compile("%(?:(\\d+)\\$)?([A-Za-z%]|$)");

    public F_2904_S(String translationKey) {
        this.v_4262_N = translationKey;
        this.w_1484_f = R_4764_Y;
    }

    public F_2904_S(String translationKey, Object ... args) {
        this.v_4262_N = translationKey;
        this.w_1484_f = args;
    }

    private void u_2550_I() {
        l_4033_W languagemap = l_4033_W.R_4764_Y();
        if (languagemap != this.t_148_a) {
            this.t_148_a = languagemap;
            this.s_956_w.clear();
            String s = languagemap.n_1700_B(this.v_4262_N);
            try {
                this.G_564_y(s);
            }
            catch (TranslatableFormatException translationtextcomponentformatexception) {
                this.s_956_w.clear();
                this.s_956_w.add(FormattedText.R_4764_Y(s));
            }
        }
    }

    private void G_564_y(String p_240758_1_) {
        Matcher matcher = u_2550_I.matcher(p_240758_1_);
        try {
            int i = 0;
            int j = 0;
            while (matcher.find(j)) {
                int k = matcher.start();
                int l = matcher.end();
                if (k > j) {
                    String s = p_240758_1_.substring(j, k);
                    if (s.indexOf(37) != -1) {
                        throw new IllegalArgumentException();
                    }
                    this.s_956_w.add(FormattedText.R_4764_Y(s));
                }
                String s4 = matcher.group(2);
                String s1 = p_240758_1_.substring(k, l);
                if ("%".equals(s4) && "%%".equals(s1)) {
                    this.s_956_w.add(G_564_y);
                } else {
                    int i1;
                    if (!"s".equals(s4)) {
                        throw new TranslatableFormatException(this, "Unsupported format: '" + s1 + "'");
                    }
                    String s2 = matcher.group(1);
                    int n = i1 = s2 != null ? Integer.parseInt(s2) - 1 : i++;
                    if (i1 < this.w_1484_f.length) {
                        this.s_956_w.add(this.J_1907_R(i1));
                    }
                }
                j = l;
            }
            if (j < p_240758_1_.length()) {
                String s3 = p_240758_1_.substring(j);
                if (s3.indexOf(37) != -1) {
                    throw new IllegalArgumentException();
                }
                this.s_956_w.add(FormattedText.R_4764_Y(s3));
            }
        }
        catch (IllegalArgumentException illegalargumentexception) {
            throw new TranslatableFormatException(this, (Throwable)illegalargumentexception);
        }
    }

    private FormattedText J_1907_R(int p_240757_1_) {
        if (p_240757_1_ >= this.w_1484_f.length) {
            throw new TranslatableFormatException(this, p_240757_1_);
        }
        Object object = this.w_1484_f[p_240757_1_];
        if (object instanceof x_282_a) {
            return (x_282_a)object;
        }
        return object == null ? P_1922_E : FormattedText.R_4764_Y(object.toString());
    }

    public F_2904_S v_4262_N() {
        return new F_2904_S(this.v_4262_N, this.w_1484_f);
    }

    @Override
    public <T> Optional<T> J_1907_R(FormattedText.n_1700_B<T> acceptor, Z_1567_W style) {
        this.u_2550_I();
        for (FormattedText itextproperties : this.s_956_w) {
            Optional<T> optional = itextproperties.n_1700_B(acceptor, style);
            if (!optional.isPresent()) continue;
            return optional;
        }
        return Optional.empty();
    }

    @Override
    public <T> Optional<T> J_1907_R(FormattedText.J_1907_R<T> acceptor) {
        this.u_2550_I();
        for (FormattedText itextproperties : this.s_956_w) {
            Optional<T> optional = itextproperties.n_1700_B(acceptor);
            if (!optional.isPresent()) continue;
            return optional;
        }
        return Optional.empty();
    }

    @Override
    public MutableComponent n_1700_B(@Nullable y_2498_m p_230535_1_, @Nullable N_4263_v p_230535_2_, int p_230535_3_) throws CommandSyntaxException {
        Object[] aobject = new Object[this.w_1484_f.length];
        for (int i = 0; i < aobject.length; ++i) {
            Object object = this.w_1484_f[i];
            aobject[i] = object instanceof x_282_a ? ComponentUtils.n_1700_B(p_230535_1_, (x_282_a)object, p_230535_2_, p_230535_3_) : object;
        }
        return new F_2904_S(this.v_4262_N, aobject);
    }

    @Override
    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof F_2904_S)) {
            return false;
        }
        F_2904_S translationtextcomponent = (F_2904_S)p_equals_1_;
        return Arrays.equals(this.w_1484_f, translationtextcomponent.w_1484_f) && this.v_4262_N.equals(translationtextcomponent.v_4262_N) && super.equals(p_equals_1_);
    }

    @Override
    public int hashCode() {
        int i = super.hashCode();
        i = 31 * i + this.v_4262_N.hashCode();
        return 31 * i + Arrays.hashCode(this.w_1484_f);
    }

    @Override
    public String toString() {
        return "TranslatableComponent{key='" + this.v_4262_N + "', args=" + Arrays.toString(this.w_1484_f) + ", siblings=" + String.valueOf(this.u_1723_Y) + ", style=" + String.valueOf(this.n_1700_B()) + "}";
    }

    public String w_1484_f() {
        return this.v_4262_N;
    }

    public Object[] s_956_w() {
        return this.w_1484_f;
    }

    @Override
    public /* synthetic */ L_3144_D t_148_a() {
        return this.v_4262_N();
    }

    @Override
    public /* synthetic */ MutableComponent G_564_y() {
        return this.v_4262_N();
    }
}



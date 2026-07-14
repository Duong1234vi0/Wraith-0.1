/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.FormattedText;

public class ComponentCollector {
    private final List<FormattedText> n_1700_B = Lists.newArrayList();

    public void n_1700_B(FormattedText p_238155_1_) {
        this.n_1700_B.add(p_238155_1_);
    }

    @Nullable
    public FormattedText n_1700_B() {
        if (this.n_1700_B.isEmpty()) {
            return null;
        }
        return this.n_1700_B.size() == 1 ? this.n_1700_B.get(0) : FormattedText.n_1700_B(this.n_1700_B);
    }

    public FormattedText J_1907_R() {
        FormattedText itextproperties = this.n_1700_B();
        return itextproperties != null ? itextproperties : FormattedText.J_1907_R;
    }
}



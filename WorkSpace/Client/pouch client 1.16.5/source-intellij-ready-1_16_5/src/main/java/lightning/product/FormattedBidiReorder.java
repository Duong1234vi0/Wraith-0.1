/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.ibm.icu.lang.UCharacter
 *  com.ibm.icu.text.ArabicShaping
 *  com.ibm.icu.text.Bidi
 *  com.ibm.icu.text.BidiRun
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.Bidi;
import com.ibm.icu.text.BidiRun;
import java.util.ArrayList;
import lightning.product.FormattedText;
import lightning.product.FormattedCharSequence;
import lightning.product.SubStringSource;

public class FormattedBidiReorder {
    public static FormattedCharSequence n_1700_B(FormattedText p_243508_0_, boolean p_243508_1_) {
        SubStringSource bidireorder = SubStringSource.n_1700_B(p_243508_0_, UCharacter::getMirror, FormattedBidiReorder::n_1700_B);
        Bidi bidi = new Bidi(bidireorder.n_1700_B(), p_243508_1_ ? 127 : 126);
        bidi.setReorderingMode(0);
        ArrayList list = Lists.newArrayList();
        int i = bidi.countRuns();
        for (int j = 0; j < i; ++j) {
            BidiRun bidirun = bidi.getVisualRun(j);
            list.addAll(bidireorder.n_1700_B(bidirun.getStart(), bidirun.getLength(), bidirun.isOddRun()));
        }
        return FormattedCharSequence.n_1700_B(list);
    }

    private static String n_1700_B(String p_243507_0_) {
        try {
            return new ArabicShaping(8).shape(p_243507_0_);
        }
        catch (Exception exception) {
            return p_243507_0_;
        }
    }
}



/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lightning.product.FormattedText;
import lightning.product.Resource;
import lightning.product.ResourceManager;
import lightning.product.b_164_E;
import lightning.product.FormattedCharSequence;
import lightning.product.g_2336_b;
import lightning.product.l_4033_W;
import lightning.product.FormattedBidiReorder;
import net.optifine.Lang;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class h_1858_T
extends l_4033_W {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final Map<String, String> J_1907_R;
    private final boolean R_4764_Y;

    private h_1858_T(Map<String, String> p_i232487_1_, boolean p_i232487_2_) {
        this.J_1907_R = p_i232487_1_;
        this.R_4764_Y = p_i232487_2_;
    }

    public static h_1858_T n_1700_B(ResourceManager p_239497_0_, List<b_164_E> p_239497_1_) {
        HashMap map = Maps.newHashMap();
        boolean flag = false;
        for (b_164_E language : p_239497_1_) {
            flag |= language.n_1700_B();
            String s = String.format("lang/%s.json", language.getCode());
            for (String s1 : p_239497_0_.n_1700_B()) {
                try {
                    g_2336_b resourcelocation = new g_2336_b(s1, s);
                    h_1858_T.n_1700_B(p_239497_0_.R_4764_Y(resourcelocation), map);
                    Lang.loadResources(p_239497_0_, language.getCode(), (Map<String, String>)map);
                }
                catch (FileNotFoundException resourcelocation) {
                }
                catch (Exception exception1) {
                    n_1700_B.warn("Skipped language file: {}:{} ({})", (Object)s1, (Object)s, (Object)exception1.toString());
                }
            }
        }
        for (b_164_E language : p_239497_1_) {
            String langCode = language.getCode();
            try {
                g_2336_b voiceChatLang = new g_2336_b("voicechat/lang/" + langCode + ".json");
                h_1858_T.n_1700_B(p_239497_0_.R_4764_Y(voiceChatLang), map);
            }
            catch (FileNotFoundException voiceChatLang) {
            }
            catch (Exception exception) {
                n_1700_B.warn("Skipped voicechat language file: voicechat:lang/{}.json ({})", (Object)langCode, (Object)exception.toString());
            }
        }
        return new h_1858_T((Map<String, String>)ImmutableMap.copyOf((Map)map), flag);
    }

    private static void n_1700_B(List<Resource> p_239498_0_, Map<String, String> p_239498_1_) {
        for (Resource iresource : p_239498_0_) {
            try {
                InputStream inputstream = iresource.J_1907_R();
                try {
                    l_4033_W.n_1700_B(inputstream, p_239498_1_::put);
                }
                finally {
                    if (inputstream == null) continue;
                    inputstream.close();
                }
            }
            catch (IOException ioexception1) {
                n_1700_B.warn("Failed to load translations from {}", (Object)iresource, (Object)ioexception1);
            }
        }
    }

    @Override
    public String n_1700_B(String p_230503_1_) {
        return this.J_1907_R.getOrDefault(p_230503_1_, p_230503_1_);
    }

    @Override
    public boolean J_1907_R(String p_230506_1_) {
        return this.J_1907_R.containsKey(p_230506_1_);
    }

    @Override
    public boolean n_1700_B() {
        return this.R_4764_Y;
    }

    @Override
    public FormattedCharSequence n_1700_B(FormattedText p_241870_1_) {
        return FormattedBidiReorder.n_1700_B(p_241870_1_, this.R_4764_Y);
    }

    public Map<String, String> J_1907_R() {
        return this.J_1907_R;
    }
}



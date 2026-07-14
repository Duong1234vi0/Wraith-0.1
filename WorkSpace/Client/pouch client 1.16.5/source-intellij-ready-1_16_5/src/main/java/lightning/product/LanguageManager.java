/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.stream.Stream;
import lightning.product.ResourceManagerReloadListener;
import lightning.product.K_1289_S;
import lightning.product.ResourceManager;
import lightning.product.PackResources;
import lightning.product.b_164_E;
import lightning.product.b_236_t;
import lightning.product.h_1858_T;
import lightning.product.l_4033_W;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LanguageManager
implements ResourceManagerReloadListener {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final b_164_E J_1907_R = new b_164_E("en_us", "US", "English", false);
    private Map<String, b_164_E> R_4764_Y = ImmutableMap.of((Object)"en_us", (Object)J_1907_R);
    private String G_564_y;
    private b_164_E P_1922_E = J_1907_R;

    public LanguageManager(String p_i48112_1_) {
        this.G_564_y = p_i48112_1_;
    }

    private static Map<String, b_164_E> n_1700_B(Stream<PackResources> p_239506_0_) {
        HashMap map = Maps.newHashMap();
        p_239506_0_.forEach(p_239505_1_ -> {
            try {
                b_236_t languagemetadatasection = p_239505_1_.getMetadata(b_236_t.n_1700_B);
                if (languagemetadatasection != null) {
                    for (b_164_E language : languagemetadatasection.n_1700_B()) {
                        map.putIfAbsent(language.getCode(), language);
                    }
                }
            }
            catch (IOException | RuntimeException runtimeexception) {
                n_1700_B.warn("Unable to parse language metadata section of resourcepack: {}", (Object)p_239505_1_.getName(), (Object)runtimeexception);
            }
        });
        return ImmutableMap.copyOf((Map)map);
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        this.R_4764_Y = LanguageManager.n_1700_B(resourceManager.J_1907_R());
        b_164_E language = this.R_4764_Y.getOrDefault("en_us", J_1907_R);
        this.P_1922_E = this.R_4764_Y.getOrDefault(this.G_564_y, language);
        ArrayList list = Lists.newArrayList((Object[])new b_164_E[]{language});
        if (this.P_1922_E != language) {
            list.add(this.P_1922_E);
        }
        h_1858_T clientlanguagemap = h_1858_T.n_1700_B(resourceManager, list);
        K_1289_S.n_1700_B(clientlanguagemap);
        l_4033_W.n_1700_B(clientlanguagemap);
    }

    public void n_1700_B(b_164_E currentLanguageIn) {
        this.G_564_y = currentLanguageIn.getCode();
        this.P_1922_E = currentLanguageIn;
    }

    public b_164_E J_1907_R() {
        return this.P_1922_E;
    }

    public SortedSet<b_164_E> R_4764_Y() {
        return Sets.newTreeSet(this.R_4764_Y.values());
    }

    public b_164_E n_1700_B(String p_191960_1_) {
        return this.R_4764_Y.get(p_191960_1_);
    }
}



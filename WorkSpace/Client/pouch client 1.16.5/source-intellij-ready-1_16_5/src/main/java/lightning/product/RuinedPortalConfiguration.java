/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.I_892_V;
import lightning.product.FeatureConfiguration;

public class RuinedPortalConfiguration
implements FeatureConfiguration {
    public static final Codec<RuinedPortalConfiguration> n_1700_B = I_892_V.n_1700_B.w_1484_f.fieldOf("portal_type").xmap(RuinedPortalConfiguration::new, p_236629_0_ -> p_236629_0_.J_1907_R).codec();
    public final I_892_V.n_1700_B J_1907_R;

    public RuinedPortalConfiguration(I_892_V.n_1700_B p_i232016_1_) {
        this.J_1907_R = p_i232016_1_;
    }
}



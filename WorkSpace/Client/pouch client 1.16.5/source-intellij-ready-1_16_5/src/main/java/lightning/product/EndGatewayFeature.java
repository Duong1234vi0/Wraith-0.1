/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.A_4605_O;
import lightning.product.WorldGenLevel;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.i_2154_H;
import lightning.product.z_1753_f;
import lightning.product.EndGatewayConfiguration;

public class EndGatewayFeature
extends Feature<EndGatewayConfiguration> {
    public EndGatewayFeature(Codec<EndGatewayConfiguration> p_i231951_1_) {
        super(p_i231951_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, EndGatewayConfiguration p_241855_5_) {
        for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(p_241855_4_.add(-1, -2, -1), p_241855_4_.add(1, 2, 1))) {
            boolean flag3;
            boolean flag = blockpos.getX() == p_241855_4_.getX();
            boolean flag1 = blockpos.getY() == p_241855_4_.getY();
            boolean flag2 = blockpos.getZ() == p_241855_4_.getZ();
            boolean bl = flag3 = Math.abs(blockpos.getY() - p_241855_4_.getY()) == 2;
            if (flag && flag1 && flag2) {
                c_1514_x blockpos1 = blockpos.toImmutable();
                this.n_1700_B(p_241855_1_, blockpos1, a_3742_W.ItemRelease.multiplayerClientSuggestionProvider());
                p_241855_5_.R_4764_Y().ifPresent(p_236280_3_ -> {
                    i_2154_H tileentity = p_241855_1_.getTileEntity(blockpos1);
                    if (tileentity instanceof A_4605_O) {
                        A_4605_O endgatewaytileentity = (A_4605_O)tileentity;
                        endgatewaytileentity.n_1700_B((c_1514_x)p_236280_3_, p_241855_5_.G_564_y());
                        tileentity.J_1907_R();
                    }
                });
                continue;
            }
            if (flag1) {
                this.n_1700_B(p_241855_1_, blockpos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider());
                continue;
            }
            if (flag3 && flag && flag2) {
                this.n_1700_B(p_241855_1_, blockpos, a_3742_W.Z_875_P.multiplayerClientSuggestionProvider());
                continue;
            }
            if ((flag || flag2) && !flag3) {
                this.n_1700_B(p_241855_1_, blockpos, a_3742_W.Z_875_P.multiplayerClientSuggestionProvider());
                continue;
            }
            this.n_1700_B(p_241855_1_, blockpos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider());
        }
        return true;
    }
}




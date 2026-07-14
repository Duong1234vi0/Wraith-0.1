/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.P_11_z;
import lightning.product.U_2871_b;
import lightning.product.c_973_a;
import lightning.product.i_2909_p;
import lightning.product.r_4811_B;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;

public class BadRespawnPointDamage
extends P_11_z {
    protected BadRespawnPointDamage() {
        super("badRespawnPoint");
        this.multiplayerClientSuggestionProvider();
        this.P_1922_E();
    }

    @Override
    public x_282_a n_1700_B(r_4811_B entityLivingBaseIn) {
        MutableComponent itextcomponent = ComponentUtils.n_1700_B(new F_2904_S("death.attack.badRespawnPoint.link")).n_1700_B(p_233545_0_ -> p_233545_0_.n_1700_B(new i_2909_p(i_2909_p.n_1700_B.n_1700_B, "https://bugs.mojang.com/browse/MCPE-28723")).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b("MCPE-28723"))));
        return new F_2904_S("death.attack.badRespawnPoint.message", entityLivingBaseIn.c_(), itextcomponent);
    }
}



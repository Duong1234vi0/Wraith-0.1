/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4024_W;
import lightning.product.RabbitModel;
import lightning.product.M_2433_H;
import lightning.product.g_2336_b;
import lightning.product.r_1334_c;
import lightning.product.w_2040_b;

public class RabbitRenderer
extends r_1334_c<M_2433_H, RabbitModel<M_2433_H>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/rabbit/brown.png");
    private static final g_2336_b t_1786_h = new g_2336_b("textures/entity/rabbit/white.png");
    private static final g_2336_b multiplayerClientSuggestionProvider = new g_2336_b("textures/entity/rabbit/black.png");
    private static final g_2336_b w_1457_N = new g_2336_b("textures/entity/rabbit/gold.png");
    private static final g_2336_b Y_601_j = new g_2336_b("textures/entity/rabbit/salt.png");
    private static final g_2336_b Y_259_p = new g_2336_b("textures/entity/rabbit/white_splotched.png");
    private static final g_2336_b Q_2552_b = new g_2336_b("textures/entity/rabbit/toast.png");
    private static final g_2336_b C_2741_M = new g_2336_b("textures/entity/rabbit/caerbannog.png");

    public RabbitRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn, new RabbitModel(), 0.3f);
    }

    @Override
    public g_2336_b n_1700_B(M_2433_H entity) {
        String s = D_4024_W.n_1700_B(entity.O_1309_Q().getString());
        if (s != null && "Toast".equals(s)) {
            return Q_2552_b;
        }
        switch (entity.y_2447_C()) {
            default: {
                return n_1700_B;
            }
            case 1: {
                return t_1786_h;
            }
            case 2: {
                return multiplayerClientSuggestionProvider;
            }
            case 3: {
                return Y_259_p;
            }
            case 4: {
                return w_1457_N;
            }
            case 5: {
                return Y_601_j;
            }
            case 99: 
        }
        return C_2741_M;
    }
}



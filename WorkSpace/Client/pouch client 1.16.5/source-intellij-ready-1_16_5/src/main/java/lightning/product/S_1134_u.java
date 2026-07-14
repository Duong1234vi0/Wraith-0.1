/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.L_1875_m;
import lightning.product.NonNullList;
import lightning.product.Potions;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.j_123_i;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.x_282_a;

public abstract class S_1134_u {
    public static final S_1134_u[] n_1700_B = new S_1134_u[12];
    public static final S_1134_u J_1907_R = new S_1134_u(0, "buildingBlocks"){

        @Override
        public Z_1993_T P_1922_E() {
            return new Z_1993_T(a_3742_W.d_4007_L);
        }
    }.J_1907_R("building_blocks");
    public static final S_1134_u R_4764_Y = new S_1134_u(1, "decorations"){

        @Override
        public Z_1993_T P_1922_E() {
            return new Z_1993_T(a_3742_W.OpenWalls);
        }
    };
    public static final S_1134_u G_564_y = new S_1134_u(2, "redstone"){

        @Override
        public Z_1993_T P_1922_E() {
            return new Z_1993_T(Items.v_570_f);
        }
    };
    public static final S_1134_u P_1922_E = new S_1134_u(3, "transportation"){

        @Override
        public Z_1993_T P_1922_E() {
            return new Z_1993_T(a_3742_W.l_4537_E);
        }
    };
    public static final S_1134_u u_1723_Y = new S_1134_u(6, "misc"){

        @Override
        public Z_1993_T P_1922_E() {
            return new Z_1993_T(Items.u_1934_K);
        }
    };
    public static final S_1134_u v_4262_N = new S_1134_u(5, "search"){

        @Override
        public Z_1993_T P_1922_E() {
            return new Z_1993_T(Items.X_1303_p);
        }
    }.n_1700_B("item_search.png");
    public static final S_1134_u w_1484_f = new S_1134_u(7, "food"){

        @Override
        public Z_1993_T P_1922_E() {
            return new Z_1993_T(Items.E_738_L);
        }
    };
    public static final S_1134_u t_148_a = new S_1134_u(8, "tools"){

        @Override
        public Z_1993_T P_1922_E() {
            return new Z_1993_T(Items.E_390_U);
        }
    }.n_1700_B(j_123_i.h_1847_R, j_123_i.v_4262_N, j_123_i.w_1484_f, j_123_i.s_956_w);
    public static final S_1134_u s_956_w = new S_1134_u(9, "combat"){

        @Override
        public Z_1993_T P_1922_E() {
            return new Z_1993_T(Items.n_2412_y);
        }
    }.n_1700_B(j_123_i.h_1847_R, j_123_i.n_1700_B, j_123_i.J_1907_R, j_123_i.P_1922_E, j_123_i.R_4764_Y, j_123_i.G_564_y, j_123_i.u_2550_I, j_123_i.u_1723_Y, j_123_i.M_588_G, j_123_i.s_956_w, j_123_i.t_148_a, j_123_i.P_4830_p);
    public static final S_1134_u u_2550_I = new S_1134_u(10, "brewing"){

        @Override
        public Z_1993_T P_1922_E() {
            return L_1875_m.n_1700_B(new Z_1993_T(Items.j_2461_G), Potions.J_1907_R);
        }
    };
    public static final S_1134_u M_588_G = u_1723_Y;
    public static final S_1134_u P_4830_p = new S_1134_u(4, "hotbar"){

        @Override
        public Z_1993_T P_1922_E() {
            return new Z_1993_T(a_3742_W.UploadTokenCache);
        }

        @Override
        public void n_1700_B(NonNullList<Z_1993_T> items) {
            throw new RuntimeException("Implement exception client-side.");
        }

        @Override
        public boolean P_4830_p() {
            return true;
        }
    };
    public static final S_1134_u h_1847_R = new S_1134_u(11, "inventory"){

        @Override
        public Z_1993_T P_1922_E() {
            return new Z_1993_T(a_3742_W.L_1362_X);
        }
    }.n_1700_B("inventory.png").s_956_w().w_1484_f();
    private final int Q_4569_t;
    private final String M_182_A;
    private final x_282_a t_1786_h;
    private String multiplayerClientSuggestionProvider;
    private String w_1457_N = "items.png";
    private boolean Y_601_j = true;
    private boolean Y_259_p = true;
    private j_123_i[] Q_2552_b = new j_123_i[0];
    private Z_1993_T C_2741_M;

    public S_1134_u(int index, String label) {
        this.Q_4569_t = index;
        this.M_182_A = label;
        this.t_1786_h = new F_2904_S("itemGroup." + label);
        this.C_2741_M = Z_1993_T.J_1907_R;
        S_1134_u.n_1700_B[index] = this;
    }

    public int n_1700_B() {
        return this.Q_4569_t;
    }

    public String J_1907_R() {
        return this.multiplayerClientSuggestionProvider == null ? this.M_182_A : this.multiplayerClientSuggestionProvider;
    }

    public x_282_a R_4764_Y() {
        return this.t_1786_h;
    }

    public Z_1993_T G_564_y() {
        if (this.C_2741_M.n_1700_B()) {
            this.C_2741_M = this.P_1922_E();
        }
        return this.C_2741_M;
    }

    public abstract Z_1993_T P_1922_E();

    public String u_1723_Y() {
        return this.w_1457_N;
    }

    public S_1134_u n_1700_B(String texture) {
        this.w_1457_N = texture;
        return this;
    }

    public S_1134_u J_1907_R(String pathIn) {
        this.multiplayerClientSuggestionProvider = pathIn;
        return this;
    }

    public boolean v_4262_N() {
        return this.Y_259_p;
    }

    public S_1134_u w_1484_f() {
        this.Y_259_p = false;
        return this;
    }

    public boolean t_148_a() {
        return this.Y_601_j;
    }

    public S_1134_u s_956_w() {
        this.Y_601_j = false;
        return this;
    }

    public int u_2550_I() {
        return this.Q_4569_t % 6;
    }

    public boolean M_588_G() {
        return this.Q_4569_t < 6;
    }

    public boolean P_4830_p() {
        return this.u_2550_I() == 5;
    }

    public j_123_i[] h_1847_R() {
        return this.Q_2552_b;
    }

    public S_1134_u n_1700_B(j_123_i ... types) {
        this.Q_2552_b = types;
        return this;
    }

    public boolean n_1700_B(@Nullable j_123_i enchantmentType) {
        if (enchantmentType != null) {
            for (j_123_i enchantmenttype : this.Q_2552_b) {
                if (enchantmenttype != enchantmentType) continue;
                return true;
            }
        }
        return false;
    }

    public void n_1700_B(NonNullList<Z_1993_T> items) {
        for (q_1613_l item : V_3137_a.e_2887_G) {
            item.n_1700_B(this, items);
        }
    }
}




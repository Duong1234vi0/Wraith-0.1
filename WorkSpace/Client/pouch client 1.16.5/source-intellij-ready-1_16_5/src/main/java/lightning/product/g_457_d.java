/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Comparator;
import java.util.Optional;
import lightning.product.BlockStateProperties;
import lightning.product.K_4074_S;
import lightning.product.O_3671_t;
import lightning.product.T_603_v;
import lightning.product.Y_1387_d;
import lightning.product.BlockUtil;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4946_z;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.TicketType;
import lightning.product.q_2232_A;
import lightning.product.u_530_F;
import lightning.product.y_2339_p;
import lightning.product.z_2963_s;

public class g_457_d {
    private final e_3591_l n_1700_B;

    public g_457_d(e_3591_l worldIn) {
        this.n_1700_B = worldIn;
    }

    public Optional<BlockUtil.J_1907_R> n_1700_B(c_1514_x pos, boolean isNether) {
        b_4946_z pointofinterestmanager = this.n_1700_B.p_178_J();
        int i = isNether ? 16 : 128;
        pointofinterestmanager.n_1700_B(this.n_1700_B, pos, i);
        Optional<y_2339_p> optional = pointofinterestmanager.J_1907_R(poiType -> poiType == q_2232_A.Q_2552_b, pos, i, b_4946_z.J_1907_R.R_4764_Y).sorted(Comparator.comparingDouble(poi -> poi.P_1922_E().distanceSq(pos)).thenComparingInt(poi -> poi.P_1922_E().getY())).filter(poi -> this.n_1700_B.getBlockState(poi.P_1922_E()).J_1907_R(BlockStateProperties.t_4043_B)).findFirst();
        return optional.map(poi -> {
            c_1514_x blockpos = poi.P_1922_E();
            this.n_1700_B.Y_259_p().n_1700_B(TicketType.u_1723_Y, new Y_1387_d(blockpos), 3, blockpos);
            K_4074_S blockstate = this.n_1700_B.getBlockState(blockpos);
            return BlockUtil.n_1700_B(blockpos, blockstate.R_4764_Y(BlockStateProperties.t_4043_B), 21, b_257_Y.n_1700_B.J_1907_R, 21, posIn -> this.n_1700_B.getBlockState((c_1514_x)posIn) == blockstate);
        });
    }

    public Optional<BlockUtil.J_1907_R> n_1700_B(c_1514_x pos, b_257_Y.n_1700_B axis) {
        b_257_Y direction = b_257_Y.n_1700_B(b_257_Y.J_1907_R.n_1700_B, axis);
        double d0 = -1.0;
        c_1514_x blockpos = null;
        double d1 = -1.0;
        c_1514_x blockpos1 = null;
        T_603_v worldborder = this.n_1700_B.H_2857_Y();
        int i = this.n_1700_B.n_3318_d() - 1;
        c_1514_x.n_1700_B blockpos$mutable = pos.toMutable();
        for (c_1514_x.n_1700_B blockpos$mutable1 : c_1514_x.func_243514_a(pos, 16, b_257_Y.u_1723_Y, b_257_Y.G_564_y)) {
            int j = Math.min(i, this.n_1700_B.n_1700_B(z_2963_s.n_1700_B.P_1922_E, blockpos$mutable1.getX(), blockpos$mutable1.getZ()));
            boolean k = true;
            if (!worldborder.n_1700_B(blockpos$mutable1) || !worldborder.n_1700_B(blockpos$mutable1.n_1700_B(direction, 1))) continue;
            blockpos$mutable1.n_1700_B(direction.u_1723_Y(), 1);
            for (int l = j; l >= 0; --l) {
                int j1;
                blockpos$mutable1.setY(l);
                if (!this.n_1700_B.u_1723_Y(blockpos$mutable1)) continue;
                int i1 = l;
                while (l > 0 && this.n_1700_B.u_1723_Y(blockpos$mutable1.n_1700_B(b_257_Y.n_1700_B))) {
                    --l;
                }
                if (l + 4 > i || (j1 = i1 - l) > 0 && j1 < 3) continue;
                blockpos$mutable1.setY(l);
                if (!this.n_1700_B(blockpos$mutable1, blockpos$mutable, direction, 0)) continue;
                double d2 = pos.distanceSq(blockpos$mutable1);
                if (this.n_1700_B(blockpos$mutable1, blockpos$mutable, direction, -1) && this.n_1700_B(blockpos$mutable1, blockpos$mutable, direction, 1) && (d0 == -1.0 || d0 > d2)) {
                    d0 = d2;
                    blockpos = blockpos$mutable1.toImmutable();
                }
                if (d0 != -1.0 || d1 != -1.0 && !(d1 > d2)) continue;
                d1 = d2;
                blockpos1 = blockpos$mutable1.toImmutable();
            }
        }
        if (d0 == -1.0 && d1 != -1.0) {
            blockpos = blockpos1;
            d0 = d1;
        }
        if (d0 == -1.0) {
            blockpos = new c_1514_x(pos.getX(), u_530_F.n_1700_B(pos.getY(), 70, this.n_1700_B.n_3318_d() - 10), pos.getZ()).toImmutable();
            b_257_Y direction1 = direction.v_4262_N();
            if (!worldborder.n_1700_B(blockpos)) {
                return Optional.empty();
            }
            for (int l1 = -1; l1 < 2; ++l1) {
                for (int k2 = 0; k2 < 2; ++k2) {
                    for (int i3 = -1; i3 < 3; ++i3) {
                        K_4074_S blockstate1 = i3 < 0 ? a_3742_W.ClientBootstrap.multiplayerClientSuggestionProvider() : a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
                        blockpos$mutable.n_1700_B(blockpos, k2 * direction.t_148_a() + l1 * direction1.t_148_a(), i3, k2 * direction.u_2550_I() + l1 * direction1.u_2550_I());
                        this.n_1700_B.J_1907_R((c_1514_x)blockpos$mutable, blockstate1);
                    }
                }
            }
        }
        for (int k1 = -1; k1 < 3; ++k1) {
            for (int i2 = -1; i2 < 4; ++i2) {
                if (k1 != -1 && k1 != 2 && i2 != -1 && i2 != 3) continue;
                blockpos$mutable.n_1700_B(blockpos, k1 * direction.t_148_a(), i2, k1 * direction.u_2550_I());
                this.n_1700_B.n_1700_B((c_1514_x)blockpos$mutable, a_3742_W.ClientBootstrap.multiplayerClientSuggestionProvider(), 3);
            }
        }
        K_4074_S blockstate = (K_4074_S)a_3742_W.M_766_z.multiplayerClientSuggestionProvider().n_1700_B(O_3671_t.P_4830_p, axis);
        for (int j2 = 0; j2 < 2; ++j2) {
            for (int l2 = 0; l2 < 3; ++l2) {
                blockpos$mutable.n_1700_B(blockpos, j2 * direction.t_148_a(), l2, j2 * direction.u_2550_I());
                this.n_1700_B.n_1700_B((c_1514_x)blockpos$mutable, blockstate, 18);
            }
        }
        return Optional.of(new BlockUtil.J_1907_R(blockpos.toImmutable(), 2, 3));
    }

    private boolean n_1700_B(c_1514_x originalPos, c_1514_x.n_1700_B offsetPos, b_257_Y directionIn, int offsetScale) {
        b_257_Y direction = directionIn.v_4262_N();
        for (int i = -1; i < 3; ++i) {
            for (int j = -1; j < 4; ++j) {
                offsetPos.n_1700_B(originalPos, directionIn.t_148_a() * i + direction.t_148_a() * offsetScale, j, directionIn.u_2550_I() * i + direction.u_2550_I() * offsetScale);
                if (j < 0 && !this.n_1700_B.getBlockState(offsetPos).R_4764_Y().J_1907_R()) {
                    return false;
                }
                if (j < 0 || this.n_1700_B.u_1723_Y(offsetPos)) continue;
                return false;
            }
        }
        return true;
    }
}




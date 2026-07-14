/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.Q_584_o;
import lightning.product.SpawnData;
import lightning.product.U_2912_j;
import lightning.product.X_1924_A;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.i_2154_H;
import lightning.product.ClientboundBlockEntityDataPacket;
import lightning.product.BlockEntityType;

public class SpawnerBlockEntity
extends i_2154_H
implements X_1924_A {
    private final Q_584_o n_1700_B = new Q_584_o(){

        @Override
        public void n_1700_B(int id) {
            SpawnerBlockEntity.this.u_2550_I.n_1700_B(SpawnerBlockEntity.this.M_588_G, a_3742_W.j_306_t, id, 0);
        }

        @Override
        public b_4507_u n_1700_B() {
            return SpawnerBlockEntity.this.u_2550_I;
        }

        @Override
        public c_1514_x J_1907_R() {
            return SpawnerBlockEntity.this.M_588_G;
        }

        @Override
        public void n_1700_B(SpawnData nextSpawnData) {
            super.n_1700_B(nextSpawnData);
            if (this.n_1700_B() != null) {
                K_4074_S blockstate = this.n_1700_B().getBlockState(this.J_1907_R());
                this.n_1700_B().n_1700_B(SpawnerBlockEntity.this.M_588_G, blockstate, blockstate, 4);
            }
        }
    };

    public SpawnerBlockEntity() {
        super(BlockEntityType.t_148_a);
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.n_1700_B.n_1700_B(nbt);
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        this.n_1700_B.J_1907_R(compound);
        return compound;
    }

    @Override
    public void P_1922_E() {
        this.n_1700_B.R_4764_Y();
    }

    @Override
    @Nullable
    public ClientboundBlockEntityDataPacket G_() {
        return new ClientboundBlockEntityDataPacket(this.M_588_G, 1, this.H_());
    }

    @Override
    public U_2912_j H_() {
        U_2912_j compoundnbt = this.n_1700_B(new U_2912_j());
        compoundnbt.multiplayerClientSuggestionProvider("SpawnPotentials");
        return compoundnbt;
    }

    @Override
    public boolean a_(int id, int type) {
        return this.n_1700_B.J_1907_R(id) ? true : super.a_(id, type);
    }

    @Override
    public boolean K_() {
        return true;
    }

    public Q_584_o v_4262_N() {
        return this.n_1700_B;
    }
}



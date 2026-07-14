/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Optional;
import lightning.product.DebugPackets;
import lightning.product.AgableMob;
import lightning.product.E_4668_a;
import lightning.product.F_427_K;
import lightning.product.L_2225_p;
import lightning.product.N_4263_v;
import lightning.product.S_50_d;
import lightning.product.a_3236_r;
import lightning.product.b_1722_e;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.q_2232_A;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;

public class VillagerMakeLove
extends Behavior<L_2225_p> {
    private long n_1700_B;

    public VillagerMakeLove() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.multiplayerClientSuggestionProvider, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.w_1484_f, (Object)((Object)S_50_d.n_1700_B)), 350, 350);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p owner) {
        return this.n_1700_B(owner);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        return gameTimeIn <= this.n_1700_B && this.n_1700_B(entityIn);
    }

    @Override
    protected void J_1907_R(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        AgableMob ageableentity = entityIn.y_1945_D().R_4764_Y(MemoryModuleType.multiplayerClientSuggestionProvider).get();
        a_3236_r.n_1700_B((r_4811_B)entityIn, (r_4811_B)ageableentity, 0.5f);
        worldIn.n_1700_B((N_4263_v)ageableentity, (byte)18);
        worldIn.n_1700_B((N_4263_v)entityIn, (byte)18);
        int i = 275 + entityIn.M_3508_C().nextInt(50);
        this.n_1700_B = gameTimeIn + (long)i;
    }

    @Override
    protected void R_4764_Y(e_3591_l worldIn, L_2225_p owner, long gameTime) {
        L_2225_p villagerentity = (L_2225_p)owner.y_1945_D().R_4764_Y(MemoryModuleType.multiplayerClientSuggestionProvider).get();
        if (!(owner.G_564_y((N_4263_v)villagerentity) > 5.0)) {
            a_3236_r.n_1700_B((r_4811_B)owner, (r_4811_B)villagerentity, 0.5f);
            if (gameTime >= this.n_1700_B) {
                owner.o_4117_e();
                villagerentity.o_4117_e();
                this.n_1700_B(worldIn, owner, villagerentity);
            } else if (owner.M_3508_C().nextInt(35) == 0) {
                worldIn.n_1700_B((N_4263_v)villagerentity, (byte)12);
                worldIn.n_1700_B((N_4263_v)owner, (byte)12);
            }
        }
    }

    private void n_1700_B(e_3591_l world, L_2225_p parent, L_2225_p partner) {
        Optional<c_1514_x> optional = this.J_1907_R(world, parent);
        if (!optional.isPresent()) {
            world.n_1700_B((N_4263_v)partner, (byte)13);
            world.n_1700_B((N_4263_v)parent, (byte)13);
        } else {
            Optional<L_2225_p> optional1 = this.J_1907_R(world, parent, partner);
            if (optional1.isPresent()) {
                this.n_1700_B(world, optional1.get(), optional.get());
            } else {
                world.p_178_J().J_1907_R(optional.get());
                DebugPackets.R_4764_Y(world, optional.get());
            }
        }
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        entityIn.y_1945_D().J_1907_R(MemoryModuleType.multiplayerClientSuggestionProvider);
    }

    private boolean n_1700_B(L_2225_p villager) {
        E_4668_a<L_2225_p> brain = villager.y_1945_D();
        Optional<AgableMob> optional = brain.R_4764_Y(MemoryModuleType.multiplayerClientSuggestionProvider).filter(breedTarget -> breedTarget.f_4016_n() == t_5_h.RealmsDefaultUncaughtExceptionHandler);
        if (!optional.isPresent()) {
            return false;
        }
        return a_3236_r.n_1700_B(brain, MemoryModuleType.multiplayerClientSuggestionProvider, t_5_h.RealmsDefaultUncaughtExceptionHandler) && villager.w_() && optional.get().w_();
    }

    private Optional<c_1514_x> J_1907_R(e_3591_l world, L_2225_p villager) {
        return world.p_178_J().n_1700_B(q_2232_A.multiplayerClientSuggestionProvider.J_1907_R(), pos -> this.n_1700_B(villager, (c_1514_x)pos), villager.b_2312_j(), 48);
    }

    private boolean n_1700_B(L_2225_p villager, c_1514_x pos) {
        b_1722_e path = villager.e_4240_b().n_1700_B(pos, q_2232_A.multiplayerClientSuggestionProvider.R_4764_Y());
        return path != null && path.s_956_w();
    }

    private Optional<L_2225_p> J_1907_R(e_3591_l world, L_2225_p parent, L_2225_p partner) {
        L_2225_p villagerentity = parent.J_1907_R(world, partner);
        if (villagerentity == null) {
            return Optional.empty();
        }
        parent.b_(6000);
        partner.b_(6000);
        villagerentity.b_(-24000);
        villagerentity.J_1907_R(parent.O_3598_v(), parent.X_2960_b(), parent.l_2647_k(), 0.0f, 0.0f);
        world.n_1700_B((N_4263_v)villagerentity);
        world.n_1700_B((N_4263_v)villagerentity, (byte)12);
        return Optional.of(villagerentity);
    }

    private void n_1700_B(e_3591_l world, L_2225_p villager, c_1514_x pos) {
        F_427_K globalpos = F_427_K.n_1700_B(world.g_2268_R(), pos);
        villager.y_1945_D().n_1700_B(MemoryModuleType.J_1907_R, globalpos);
    }

    @Override
    protected /* synthetic */ void J_1907_R(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.G_564_y(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.J_1907_R(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }
}



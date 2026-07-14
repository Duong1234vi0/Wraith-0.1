/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  it.unimi.dsi.fastutil.longs.Long2LongMap
 *  it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.longs.Long2LongMap;
import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lightning.product.DebugPackets;
import lightning.product.WalkTarget;
import lightning.product.S_50_d;
import lightning.product.b_1722_e;
import lightning.product.b_4946_z;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.PathfinderMob;
import lightning.product.q_2232_A;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class SetClosestHomeAsWalkTarget
extends Behavior<r_4811_B> {
    private final float n_1700_B;
    private final Long2LongMap R_4764_Y = new Long2LongOpenHashMap();
    private int G_564_y;
    private long P_1922_E;

    public SetClosestHomeAsWalkTarget(float p_i50353_1_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.J_1907_R, (Object)((Object)S_50_d.J_1907_R)));
        this.n_1700_B = p_i50353_1_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, r_4811_B owner) {
        if (worldIn.X_933_l() - this.P_1922_E < 20L) {
            return false;
        }
        PathfinderMob creatureentity = (PathfinderMob)owner;
        b_4946_z pointofinterestmanager = worldIn.p_178_J();
        Optional<c_1514_x> optional = pointofinterestmanager.G_564_y(q_2232_A.multiplayerClientSuggestionProvider.J_1907_R(), owner.b_2312_j(), 48, b_4946_z.J_1907_R.R_4764_Y);
        return optional.isPresent() && !(optional.get().distanceSq(creatureentity.b_2312_j()) <= 4.0);
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        this.G_564_y = 0;
        this.P_1922_E = worldIn.X_933_l() + (long)worldIn.e_4240_b().nextInt(20);
        PathfinderMob creatureentity = (PathfinderMob)entityIn;
        b_4946_z pointofinterestmanager = worldIn.p_178_J();
        Predicate<c_1514_x> predicate = p_225453_1_ -> {
            long i = p_225453_1_.toLong();
            if (this.R_4764_Y.containsKey(i)) {
                return false;
            }
            if (++this.G_564_y >= 5) {
                return false;
            }
            this.R_4764_Y.put(i, this.P_1922_E + 40L);
            return true;
        };
        Stream<c_1514_x> stream = pointofinterestmanager.n_1700_B(q_2232_A.multiplayerClientSuggestionProvider.J_1907_R(), predicate, entityIn.b_2312_j(), 48, b_4946_z.J_1907_R.R_4764_Y);
        b_1722_e path = creatureentity.e_4240_b().n_1700_B(stream, q_2232_A.multiplayerClientSuggestionProvider.R_4764_Y());
        if (path != null && path.s_956_w()) {
            c_1514_x blockpos = path.P_4830_p();
            Optional<q_2232_A> optional = pointofinterestmanager.R_4764_Y(blockpos);
            if (optional.isPresent()) {
                entityIn.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget(blockpos, this.n_1700_B, 1));
                DebugPackets.R_4764_Y(worldIn, blockpos);
            }
        } else if (this.G_564_y < 5) {
            this.R_4764_Y.long2LongEntrySet().removeIf(p_225454_1_ -> p_225454_1_.getLongValue() < this.P_1922_E);
        }
    }
}



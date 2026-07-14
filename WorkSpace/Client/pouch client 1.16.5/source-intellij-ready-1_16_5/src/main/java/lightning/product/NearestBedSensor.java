/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  it.unimi.dsi.fastutil.longs.Long2LongMap
 *  it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.longs.Long2LongMap;
import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lightning.product.Z_530_i;
import lightning.product.b_1722_e;
import lightning.product.Sensor;
import lightning.product.b_4946_z;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.q_2232_A;
import lightning.product.MemoryModuleType;

public class NearestBedSensor
extends Sensor<Z_530_i> {
    private final Long2LongMap n_1700_B = new Long2LongOpenHashMap();
    private int J_1907_R;
    private long R_4764_Y;

    public NearestBedSensor() {
        super(20);
    }

    @Override
    public Set<MemoryModuleType<?>> n_1700_B() {
        return ImmutableSet.of(MemoryModuleType.C_2741_M);
    }

    @Override
    protected void n_1700_B(e_3591_l worldIn, Z_530_i entityIn) {
        if (entityIn.d_()) {
            this.J_1907_R = 0;
            this.R_4764_Y = worldIn.X_933_l() + (long)worldIn.e_4240_b().nextInt(20);
            b_4946_z pointofinterestmanager = worldIn.p_178_J();
            Predicate<c_1514_x> predicate = pos -> {
                long i = pos.toLong();
                if (this.n_1700_B.containsKey(i)) {
                    return false;
                }
                if (++this.J_1907_R >= 5) {
                    return false;
                }
                this.n_1700_B.put(i, this.R_4764_Y + 40L);
                return true;
            };
            Stream<c_1514_x> stream = pointofinterestmanager.n_1700_B(q_2232_A.multiplayerClientSuggestionProvider.J_1907_R(), predicate, entityIn.b_2312_j(), 48, b_4946_z.J_1907_R.R_4764_Y);
            b_1722_e path = entityIn.e_4240_b().n_1700_B(stream, q_2232_A.multiplayerClientSuggestionProvider.R_4764_Y());
            if (path != null && path.s_956_w()) {
                c_1514_x blockpos = path.P_4830_p();
                Optional<q_2232_A> optional = pointofinterestmanager.R_4764_Y(blockpos);
                if (optional.isPresent()) {
                    entityIn.y_1945_D().n_1700_B(MemoryModuleType.C_2741_M, blockpos);
                }
            } else if (this.J_1907_R < 5) {
                this.n_1700_B.long2LongEntrySet().removeIf(bedLocatedTime -> bedLocatedTime.getLongValue() < this.R_4764_Y);
            }
        }
    }
}



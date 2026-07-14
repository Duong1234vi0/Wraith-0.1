/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.WalkTarget;
import lightning.product.S_50_d;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;
import lightning.product.z_2963_s;

public class MoveToSkySeeingSpot
extends Behavior<r_4811_B> {
    private final float n_1700_B;

    public MoveToSkySeeingSpot(float speed) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.J_1907_R)));
        this.n_1700_B = speed;
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        Optional<e_2866_D> optional = Optional.ofNullable(this.J_1907_R(worldIn, entityIn));
        if (optional.isPresent()) {
            entityIn.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p, optional.map(p_220492_1_ -> new WalkTarget((e_2866_D)p_220492_1_, this.n_1700_B, 0)));
        }
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, r_4811_B owner) {
        return !worldIn.canSeeSky(owner.b_2312_j());
    }

    @Nullable
    private e_2866_D J_1907_R(e_3591_l world, r_4811_B walker) {
        Random random = walker.M_3508_C();
        c_1514_x blockpos = walker.b_2312_j();
        for (int i = 0; i < 10; ++i) {
            c_1514_x blockpos1 = blockpos.add(random.nextInt(20) - 10, random.nextInt(6) - 3, random.nextInt(20) - 10);
            if (!MoveToSkySeeingSpot.n_1700_B(world, walker, blockpos1)) continue;
            return e_2866_D.R_4764_Y(blockpos1);
        }
        return null;
    }

    public static boolean n_1700_B(e_3591_l world, r_4811_B walker, c_1514_x p_226306_2_) {
        return world.canSeeSky(p_226306_2_) && (double)world.n_1700_B(z_2963_s.n_1700_B.P_1922_E, p_226306_2_).getY() <= walker.X_2960_b();
    }
}



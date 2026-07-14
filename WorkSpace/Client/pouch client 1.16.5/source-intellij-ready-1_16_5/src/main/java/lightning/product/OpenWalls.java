/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.Set;
import lightning.product.A_3138_X;
import lightning.product.C_4998_y;
import lightning.product.BaseEntityBlock;
import lightning.product.K_3256_W;
import lightning.product.SmokerBlock;
import lightning.product.StonecutterBlock;
import lightning.product.Module;
import lightning.product.Y_3462_U;
import lightning.product.CartographyTableBlock;
import lightning.product.BlastFurnaceBlock;
import lightning.product.CraftingTableBlock;
import lightning.product.h_355_y;
import lightning.product.AbstractChestBlock;
import lightning.product.LoomBlock;
import lightning.product.BeaconBlock;
import lightning.product.GrindstoneBlock;
import lightning.product.t_2321_d;
import lightning.product.BrewingStandBlock;
import lightning.product.ModuleCategory;
import lombok.Generated;

public class OpenWalls
extends Module {
    private final Set<Class<?>> v_4262_N = Set.of(AbstractChestBlock.class, A_3138_X.class, CraftingTableBlock.class, Y_3462_U.class, BaseEntityBlock.class, t_2321_d.class, BeaconBlock.class, BlastFurnaceBlock.class, BrewingStandBlock.class, C_4998_y.class, CartographyTableBlock.class, GrindstoneBlock.class, h_355_y.class, LoomBlock.class, SmokerBlock.class, StonecutterBlock.class, K_3256_W.class);

    public OpenWalls() {
        super("OpenWalls", ModuleCategory.P_1922_E);
    }

    @Generated
    public Set<Class<?>> h_1847_R() {
        return this.v_4262_N;
    }
}




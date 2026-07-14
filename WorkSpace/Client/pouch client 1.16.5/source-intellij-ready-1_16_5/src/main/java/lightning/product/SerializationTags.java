/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.stream.Collectors;
import lightning.product.FluidTags;
import lightning.product.TagContainer;
import lightning.product.E_2561_m;
import lightning.product.ItemTags;
import lightning.product.BlockTags;
import lightning.product.EntityTypeTags;
import lightning.product.r_109_r;

public class SerializationTags {
    private static volatile TagContainer n_1700_B = TagContainer.n_1700_B(E_2561_m.n_1700_B(BlockTags.J_1907_R().stream().collect(Collectors.toMap(r_109_r.J_1907_R::J_1907_R, blockTag -> blockTag))), E_2561_m.n_1700_B(ItemTags.J_1907_R().stream().collect(Collectors.toMap(r_109_r.J_1907_R::J_1907_R, itemTag -> itemTag))), E_2561_m.n_1700_B(FluidTags.n_1700_B().stream().collect(Collectors.toMap(r_109_r.J_1907_R::J_1907_R, fluidTag -> fluidTag))), E_2561_m.n_1700_B(EntityTypeTags.J_1907_R().stream().collect(Collectors.toMap(r_109_r.J_1907_R::J_1907_R, entityTypeTag -> entityTypeTag))));

    public static TagContainer n_1700_B() {
        return n_1700_B;
    }

    public static void n_1700_B(TagContainer managerIn) {
        n_1700_B = managerIn;
    }
}



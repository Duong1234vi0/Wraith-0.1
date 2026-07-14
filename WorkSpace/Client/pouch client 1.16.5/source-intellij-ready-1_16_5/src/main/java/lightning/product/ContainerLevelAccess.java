/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;

public interface ContainerLevelAccess {
    public static final ContainerLevelAccess n_1700_B = new ContainerLevelAccess(){

        @Override
        public <T> Optional<T> n_1700_B(BiFunction<b_4507_u, c_1514_x, T> worldPosConsumer) {
            return Optional.empty();
        }
    };

    public static ContainerLevelAccess n_1700_B(final b_4507_u world, final c_1514_x pos) {
        return new ContainerLevelAccess(){

            @Override
            public <T> Optional<T> n_1700_B(BiFunction<b_4507_u, c_1514_x, T> worldPosConsumer) {
                return Optional.of(worldPosConsumer.apply(world, pos));
            }
        };
    }

    public <T> Optional<T> n_1700_B(BiFunction<b_4507_u, c_1514_x, T> var1);

    default public <T> T n_1700_B(BiFunction<b_4507_u, c_1514_x, T> worldPosConsumer, T defaultValue) {
        return this.n_1700_B(worldPosConsumer).orElse(defaultValue);
    }

    default public void n_1700_B(BiConsumer<b_4507_u, c_1514_x> worldPosConsumer) {
        this.n_1700_B((b_4507_u world, c_1514_x pos) -> {
            worldPosConsumer.accept((b_4507_u)world, (c_1514_x)pos);
            return Optional.empty();
        });
    }
}



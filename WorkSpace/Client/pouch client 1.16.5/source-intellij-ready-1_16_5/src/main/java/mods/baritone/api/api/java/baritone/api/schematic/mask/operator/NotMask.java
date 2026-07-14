/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.schematic.mask.operator;

import lightning.product.K_4074_S;
import mods.baritone.api.api.java.baritone.api.schematic.mask.AbstractMask;
import mods.baritone.api.api.java.baritone.api.schematic.mask.Mask;
import mods.baritone.api.api.java.baritone.api.schematic.mask.StaticMask;

public final class NotMask
extends AbstractMask {
    private final Mask source;

    public NotMask(Mask source) {
        super(source.widthX(), source.heightY(), source.lengthZ());
        this.source = source;
    }

    @Override
    public boolean partOfMask(int x, int y, int z, K_4074_S currentState) {
        return !this.source.partOfMask(x, y, z, currentState);
    }

    public static final class Static
    extends AbstractMask
    implements StaticMask {
        private final StaticMask source;

        public Static(StaticMask source) {
            super(source.widthX(), source.heightY(), source.lengthZ());
            this.source = source;
        }

        @Override
        public boolean partOfMask(int x, int y, int z) {
            return !this.source.partOfMask(x, y, z);
        }
    }
}


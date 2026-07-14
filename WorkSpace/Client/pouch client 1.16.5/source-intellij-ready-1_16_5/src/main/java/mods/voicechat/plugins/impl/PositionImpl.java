/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl;

import java.util.Objects;
import lightning.product.e_2866_D;
import mods.voicechat.api.Position;

public class PositionImpl
implements Position {
    private final e_2866_D position;

    public PositionImpl(e_2866_D position) {
        this.position = position;
    }

    public PositionImpl(double x, double y, double z) {
        this.position = new e_2866_D(x, y, z);
    }

    @Override
    public double getX() {
        return this.position.J_1907_R;
    }

    @Override
    public double getY() {
        return this.position.R_4764_Y;
    }

    @Override
    public double getZ() {
        return this.position.G_564_y;
    }

    public e_2866_D getPosition() {
        return this.position;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        PositionImpl position1 = (PositionImpl)object;
        return Objects.equals(this.position, position1.position);
    }

    public int hashCode() {
        return this.position != null ? this.position.hashCode() : 0;
    }
}


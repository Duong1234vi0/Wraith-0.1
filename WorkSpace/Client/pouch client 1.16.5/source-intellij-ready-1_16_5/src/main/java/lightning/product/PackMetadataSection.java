/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.PackMetadataSectionSerializer;
import lightning.product.x_282_a;

public class PackMetadataSection {
    public static final PackMetadataSectionSerializer n_1700_B = new PackMetadataSectionSerializer();
    private final x_282_a J_1907_R;
    private final int R_4764_Y;

    public PackMetadataSection(x_282_a packDescriptionIn, int packFormatIn) {
        this.J_1907_R = packDescriptionIn;
        this.R_4764_Y = packFormatIn;
    }

    public x_282_a n_1700_B() {
        return this.J_1907_R;
    }

    public int J_1907_R() {
        return this.R_4764_Y;
    }
}



/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.eventforge;

import lightning.product.N_4263_v;
import lightning.product.Z_2049_e;
import lightning.product.g_221_o;
import lightning.product.o_3091_w;
import lightning.product.x_282_a;
import mods.voicechat.eventforge.EntityEvent;

public class RenderNameplateEvent
extends EntityEvent {
    private x_282_a nameplateContent;
    private final x_282_a originalContent;
    private final Z_2049_e<?> entityRenderer;
    private final g_221_o matrixStack;
    private final o_3091_w renderTypeBuffer;
    private final int packedLight;
    private final float partialTicks;

    public RenderNameplateEvent(N_4263_v entity, x_282_a content, Z_2049_e<?> entityRenderer, g_221_o matrixStack, o_3091_w renderTypeBuffer, int packedLight, float partialTicks) {
        super(entity);
        this.originalContent = content;
        this.setContent(this.originalContent);
        this.entityRenderer = entityRenderer;
        this.matrixStack = matrixStack;
        this.renderTypeBuffer = renderTypeBuffer;
        this.packedLight = packedLight;
        this.partialTicks = partialTicks;
    }

    public void setContent(x_282_a contents) {
        this.nameplateContent = contents;
    }

    public x_282_a getContent() {
        return this.nameplateContent;
    }

    public x_282_a getOriginalContent() {
        return this.originalContent;
    }

    public Z_2049_e<?> getEntityRenderer() {
        return this.entityRenderer;
    }

    public g_221_o getMatrixStack() {
        return this.matrixStack;
    }

    public o_3091_w getRenderTypeBuffer() {
        return this.renderTypeBuffer;
    }

    public int getPackedLight() {
        return this.packedLight;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }
}


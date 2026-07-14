package ua.babka.core;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;
import ua.babka.util.player.PetBrain;

@Environment(value=EnvType.CLIENT)
public class RenderDog {
    private Identifier texture = Identifier.of((String)"mre", (String)"images/dog.png");
    private static final float TH = 36.0f;
    private static final float S = 0.0625f;
    private float headYaw;
    private float headPitch;
    private float bodyYaw;
    private float frontLeftLegX;
    private float frontRightLegX;
    private float backLeftLegX;
    private float backRightLegX;
    private float frontLeftLegY;
    private float frontRightLegY;
    private float backLeftLegY;
    private float backRightLegY;
    private float tailX;
    private float tailZ;
    private boolean lay;

    public void setTextureFileName(String name) {
        this.texture = Identifier.of((String)"mre", (String)("images/" + name));
    }

    public void setRotationAngles(float ageInTicks, PetBrain brain) {
        this.headYaw = -brain.getYaw() * ((float)Math.PI / 180);
        this.headPitch = brain.getPitch() * ((float)Math.PI / 180);
        this.bodyYaw = brain.getBody();
        this.lay = brain.isLay();
        float swing = brain.limbSwing;
        float amount = brain.limbSwingAmount;
        this.frontLeftLegX = MathHelper.cos((float)(swing * 0.6662f)) * 1.4f * amount;
        this.frontRightLegX = MathHelper.cos((float)(swing * 0.6662f + (float)Math.PI)) * 1.4f * amount;
        this.backLeftLegX = MathHelper.cos((float)(swing * 0.6662f + (float)Math.PI)) * 1.4f * amount;
        this.backRightLegX = MathHelper.cos((float)(swing * 0.6662f)) * 1.4f * amount;
        if (this.lay) {
            this.frontLeftLegX = (float)Math.toRadians(-90.0);
            this.frontRightLegX = (float)Math.toRadians(-90.0);
            this.backLeftLegX = (float)Math.toRadians(90.0);
            this.backRightLegX = (float)Math.toRadians(90.0);
            this.frontLeftLegY = (float)Math.toRadians(-22.0);
            this.frontRightLegY = (float)Math.toRadians(22.0);
            this.backLeftLegY = (float)Math.toRadians(22.0);
            this.backRightLegY = (float)Math.toRadians(-22.0);
        } else {
            this.backRightLegY = 0.0f;
            this.backLeftLegY = 0.0f;
            this.frontRightLegY = 0.0f;
            this.frontLeftLegY = 0.0f;
        }
        this.tailX = (float)Math.toRadians(this.lay ? 45.0 : 22.0);
        this.tailZ = (float)Math.toRadians(-22.5) + (float)Math.toRadians(22.5) + (float)Math.cos(ageInTicks * 0.15f) * 0.3f;
    }

    public void render(MatrixStack ms, VertexConsumerProvider vcp, PetBrain brain, int light) {
        VertexConsumer vc = vcp.getBuffer(RenderLayer.getEntityTranslucent((Identifier)this.texture));
        int overlay = OverlayTexture.DEFAULT_UV;
        ms.push();
        ms.translate(0.0, (double)(1.4f - (this.lay ? 0.3f : 0.0f)), 0.0);
        ms.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0f));
        ms.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(this.bodyYaw));
        this.renderHead(ms, vc, light, overlay);
        this.renderNeck(ms, vc, light, overlay);
        this.renderBody(ms, vc, light, overlay);
        this.renderLegs(ms, vc, light, overlay);
        this.renderTail(ms, vc, light, overlay);
        ms.pop();
    }

    private void renderHead(MatrixStack ms, VertexConsumer vc, int light, int overlay) {
        ms.push();
        ms.translate(0.0f, 0.65625f, -0.425f);
        ms.multiply(RotationAxis.POSITIVE_Y.rotation(this.headYaw));
        ms.multiply(RotationAxis.POSITIVE_X.rotation(this.headPitch));
        this.cube(ms, vc, light, overlay, -3.0f, -3.0f, -4.0f, 6.0f, 6.0f, 4.0f, 0, 0, false);
        this.cube(ms, vc, light, overlay, -1.5f, 0.0f, -7.0f, 3.0f, 3.0f, 3.0f, 21, 0, false);
        ms.push();
        ms.translate(0.1875f, 0.1875f, -0.125f);
        this.cube(ms, vc, light, overlay, 0.0f, -5.0f, -1.5f, 1.0f, 3.0f, 3.0f, 32, 4, false);
        this.cube(ms, vc, light, overlay, 0.0f, -5.5f, -0.75f, 1.0f, 1.0f, 1.5f, 34, 1, false);
        ms.pop();
        ms.push();
        ms.translate(-0.1875f, 0.1875f, -0.125f);
        this.cube(ms, vc, light, overlay, -1.0f, -5.0f, -1.5f, 1.0f, 3.0f, 3.0f, 32, 4, true);
        this.cube(ms, vc, light, overlay, -1.0f, -5.5f, -0.75f, 1.0f, 1.0f, 1.5f, 34, 1, true);
        ms.pop();
        ms.pop();
    }

    private void renderNeck(MatrixStack ms, VertexConsumer vc, int light, int overlay) {
        ms.push();
        ms.translate(0.0f, 0.65625f, -0.3125f);
        ms.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-25.0f));
        this.cube(ms, vc, light, overlay, -2.95f, -1.0f, -4.0f, 5.9f, 5.0f, 6.0f, 15, 7, false);
        ms.pop();
    }

    private void renderBody(MatrixStack ms, VertexConsumer vc, int light, int overlay) {
        ms.push();
        ms.translate(0.0f, 0.84375f, -0.3125f);
        ms.push();
        ms.translate(0.0f, 0.0f, 0.1875f);
        this.cube(ms, vc, light, overlay, -4.0f, -3.5f, -3.0f, 8.0f, 7.0f, 6.0f, 32, 13, false);
        ms.pop();
        ms.push();
        ms.translate(0.0f, -0.03125f, 0.34375f);
        this.cube(ms, vc, light, overlay, -3.0f, -3.0f, -0.5f, 6.0f, 6.0f, 11.0f, 3, 19, false);
        ms.pop();
        ms.pop();
    }

    private void renderLegs(MatrixStack ms, VertexConsumer vc, int light, int overlay) {
        ms.push();
        ms.translate(0.09375f, 1.0f, -0.1875f);
        ms.multiply(RotationAxis.POSITIVE_Y.rotation(this.frontLeftLegY));
        ms.multiply(RotationAxis.POSITIVE_X.rotation(this.frontLeftLegX));
        this.cube(ms, vc, light, overlay, -1.0f, 0.0f, -1.0f, 2.0f, 5.0f, 2.0f, 42, 0, false);
        ms.pop();
        ms.push();
        ms.translate(-0.09375f, 1.0f, -0.1875f);
        ms.multiply(RotationAxis.POSITIVE_Y.rotation(this.frontRightLegY));
        ms.multiply(RotationAxis.POSITIVE_X.rotation(this.frontRightLegX));
        this.cube(ms, vc, light, overlay, -1.0f, 0.0f, -1.0f, 2.0f, 5.0f, 2.0f, 42, 0, true);
        ms.pop();
        ms.push();
        ms.translate(0.09375f, 1.0f, 0.5625f);
        ms.multiply(RotationAxis.POSITIVE_Y.rotation(this.backLeftLegY));
        ms.multiply(RotationAxis.POSITIVE_X.rotation(this.backLeftLegX));
        this.cube(ms, vc, light, overlay, -1.0f, 0.0f, -1.0f, 2.0f, 5.0f, 2.0f, 52, 0, false);
        ms.pop();
        ms.push();
        ms.translate(-0.09375f, 1.0f, 0.5625f);
        ms.multiply(RotationAxis.POSITIVE_Y.rotation(this.backRightLegY));
        ms.multiply(RotationAxis.POSITIVE_X.rotation(this.backRightLegX));
        this.cube(ms, vc, light, overlay, -1.0f, 0.0f, -1.0f, 2.0f, 5.0f, 2.0f, 52, 0, true);
        ms.pop();
    }

    private void renderTail(MatrixStack ms, VertexConsumer vc, int light, int overlay) {
        ms.push();
        ms.translate(0.0f, 0.5625f, 0.625f);
        ms.multiply(RotationAxis.POSITIVE_X.rotation(this.tailX));
        ms.multiply(RotationAxis.POSITIVE_Z.rotation(this.tailZ));
        this.cube(ms, vc, light, overlay, -1.0f, 2.0f, -1.0f, 2.0f, 8.0f, 2.0f, 2, 12, false);
        ms.pop();
    }

    private void cube(MatrixStack ms, VertexConsumer vc, int light, int overlay, float ox, float oy, float oz, float w2, float h2, float d2, int u2, int v2, boolean mirror) {
        float x0 = ox * 0.0625f;
        float x1 = (ox + w2) * 0.0625f;
        float y0 = oy * 0.0625f;
        float y1 = (oy + h2) * 0.0625f;
        float z0 = oz * 0.0625f;
        float z1 = (oz + d2) * 0.0625f;
        if (mirror) {
            float t2 = x0;
            x0 = x1;
            x1 = t2;
        }
        Matrix4f m4 = ms.peek().getPositionMatrix();
        MatrixStack.Entry entry = ms.peek();
        this.quad(vc, m4, entry, light, overlay, x1, y0, z1, ((float)u2 + d2) / 60.0f, ((float)v2 + d2) / 36.0f, x1, y0, z0, (float)u2 / 60.0f, ((float)v2 + d2) / 36.0f, x1, y1, z0, (float)u2 / 60.0f, ((float)v2 + d2 + h2) / 36.0f, x1, y1, z1, ((float)u2 + d2) / 60.0f, ((float)v2 + d2 + h2) / 36.0f, 1.0f, 0.0f, 0.0f);
        this.quad(vc, m4, entry, light, overlay, x0, y0, z0, ((float)u2 + 2.0f * d2 + w2) / 60.0f, ((float)v2 + d2) / 36.0f, x0, y0, z1, ((float)u2 + d2 + w2) / 60.0f, ((float)v2 + d2) / 36.0f, x0, y1, z1, ((float)u2 + d2 + w2) / 60.0f, ((float)v2 + d2 + h2) / 36.0f, x0, y1, z0, ((float)u2 + 2.0f * d2 + w2) / 60.0f, ((float)v2 + d2 + h2) / 36.0f, -1.0f, 0.0f, 0.0f);
        this.quad(vc, m4, entry, light, overlay, x1, y0, z1, ((float)u2 + d2 + w2) / 60.0f, (float)v2 / 36.0f, x0, y0, z1, ((float)u2 + d2) / 60.0f, (float)v2 / 36.0f, x0, y0, z0, ((float)u2 + d2) / 60.0f, ((float)v2 + d2) / 36.0f, x1, y0, z0, ((float)u2 + d2 + w2) / 60.0f, ((float)v2 + d2) / 36.0f, 0.0f, -1.0f, 0.0f);
        this.quad(vc, m4, entry, light, overlay, x1, y1, z0, ((float)u2 + d2 + 2.0f * w2) / 60.0f, (float)v2 / 36.0f, x0, y1, z0, ((float)u2 + d2 + w2) / 60.0f, (float)v2 / 36.0f, x0, y1, z1, ((float)u2 + d2 + w2) / 60.0f, ((float)v2 + d2) / 36.0f, x1, y1, z1, ((float)u2 + d2 + 2.0f * w2) / 60.0f, ((float)v2 + d2) / 36.0f, 0.0f, 1.0f, 0.0f);
        this.quad(vc, m4, entry, light, overlay, x1, y0, z0, ((float)u2 + d2 + w2) / 60.0f, ((float)v2 + d2) / 36.0f, x0, y0, z0, ((float)u2 + d2) / 60.0f, ((float)v2 + d2) / 36.0f, x0, y1, z0, ((float)u2 + d2) / 60.0f, ((float)v2 + d2 + h2) / 36.0f, x1, y1, z0, ((float)u2 + d2 + w2) / 60.0f, ((float)v2 + d2 + h2) / 36.0f, 0.0f, 0.0f, -1.0f);
        this.quad(vc, m4, entry, light, overlay, x0, y0, z1, ((float)u2 + 2.0f * d2 + 2.0f * w2) / 60.0f, ((float)v2 + d2) / 36.0f, x1, y0, z1, ((float)u2 + 2.0f * d2 + w2) / 60.0f, ((float)v2 + d2) / 36.0f, x1, y1, z1, ((float)u2 + 2.0f * d2 + w2) / 60.0f, ((float)v2 + d2 + h2) / 36.0f, x0, y1, z1, ((float)u2 + 2.0f * d2 + 2.0f * w2) / 60.0f, ((float)v2 + d2 + h2) / 36.0f, 0.0f, 0.0f, 1.0f);
    }

    private void quad(VertexConsumer vc, Matrix4f m4, MatrixStack.Entry entry, int light, int overlay, float x0, float y0, float z0, float u0, float v0, float x1, float y1, float z1, float u1, float v1, float x2, float y2, float z2, float u2, float v2, float x3, float y3, float z3, float u3, float v3, float nx, float ny, float nz) {
        vc.vertex(m4, x0, y0, z0).texture(u0, v0).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(overlay).light(light).normal(entry, nx, ny, nz);
        vc.vertex(m4, x1, y1, z1).texture(u1, v1).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(overlay).light(light).normal(entry, nx, ny, nz);
        vc.vertex(m4, x2, y2, z2).texture(u2, v2).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(overlay).light(light).normal(entry, nx, ny, nz);
        vc.vertex(m4, x3, y3, z3).texture(u3, v3).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(overlay).light(light).normal(entry, nx, ny, nz);
    }
}


package ua.babka.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.Vector2f;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public class ProjectionUtil {
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static Vector2f project(double x2, double y2, double z2) {
        Entity entity;
        Camera camera = ProjectionUtil.mc.getEntityRenderDispatcher().camera;
        Vec3d cameraPos = camera.getPos();
        Quaternionf cameraRotation = mc.getEntityRenderDispatcher().getRotation();
        cameraRotation = cameraRotation.conjugate(new Quaternionf());
        Vector3f result3f = new Vector3f((float)(x2 - cameraPos.x), (float)(y2 - cameraPos.y), (float)(z2 - cameraPos.z));
        result3f.rotate((Quaternionfc)cameraRotation);
        if (((Boolean)ProjectionUtil.mc.options.getBobView().getValue()).booleanValue() && (entity = mc.getCameraEntity()) instanceof AbstractClientPlayerEntity) {
            AbstractClientPlayerEntity playerentity = (AbstractClientPlayerEntity)entity;
            ProjectionUtil.calculateViewBobbing(playerentity, result3f);
        }
        double fov = ProjectionUtil.mc.gameRenderer.getFov(camera, mc.getRenderTickCounter().getTickDelta(true), true);
        return ProjectionUtil.calculateScreenPosition(result3f, fov);
    }

    public static Vector2f project(Vec3d vec3d) {
        Entity entity;
        Camera camera = ProjectionUtil.mc.getEntityRenderDispatcher().camera;
        Vec3d cameraPos = camera.getPos();
        Quaternionf cameraRotation = mc.getEntityRenderDispatcher().getRotation();
        cameraRotation = cameraRotation.conjugate(new Quaternionf());
        Vector3f result3f = new Vector3f((float)(vec3d.x - cameraPos.x), (float)(vec3d.y - cameraPos.y), (float)(vec3d.z - cameraPos.z));
        result3f.rotate((Quaternionfc)cameraRotation);
        if (((Boolean)ProjectionUtil.mc.options.getBobView().getValue()).booleanValue() && (entity = mc.getCameraEntity()) instanceof AbstractClientPlayerEntity) {
            AbstractClientPlayerEntity playerentity = (AbstractClientPlayerEntity)entity;
            ProjectionUtil.calculateViewBobbing(playerentity, result3f);
        }
        double fov = ProjectionUtil.mc.gameRenderer.getFov(camera, mc.getRenderTickCounter().getTickDelta(true), true);
        return ProjectionUtil.calculateScreenPosition(result3f, fov);
    }

    private static void calculateViewBobbing(AbstractClientPlayerEntity playerEntity, Vector3f result3f) {
        float f2 = playerEntity.distanceMoved - playerEntity.lastDistanceMoved;
        float g2 = -(playerEntity.distanceMoved + f2 * mc.getRenderTickCounter().getTickDelta(true));
        float h2 = MathHelper.lerp((float)mc.getRenderTickCounter().getTickDelta(true), (float)playerEntity.prevStrideDistance, (float)playerEntity.strideDistance);
        float angle = Math.abs(MathHelper.cos((float)(g2 * (float)Math.PI - 0.2f)) * h2) * 5.0f;
        Quaternionf quaternion = new Quaternionf().setAngleAxis(angle *= (float)Math.PI / 180, 1.0f, 0.0f, 0.0f);
        quaternion.conjugate();
        result3f.rotate((Quaternionfc)quaternion);
        float angle1 = MathHelper.sin((float)(g2 * (float)Math.PI)) * h2 * 3.0f;
        Quaternionf quaternion1 = new Quaternionf().setAngleAxis(angle1 *= (float)Math.PI / 180, 0.0f, 0.0f, 1.0f);
        quaternion1.conjugate();
        result3f.rotate((Quaternionfc)quaternion1);
        Vector3f bobTranslation = new Vector3f(MathHelper.sin((float)(g2 * (float)Math.PI)) * h2 * 0.5f, 0.0f, 0.0f);
        bobTranslation.y = -bobTranslation.y;
        result3f.add((Vector3fc)bobTranslation);
    }

    private static Vector2f calculateScreenPosition(Vector3f result3f, double fov) {
        Window window = mc.getWindow();
        float width = (float)window.getScaledWidth() / 2.0f;
        float height = (float)window.getScaledHeight() / 2.0f;
        float x2 = result3f.x;
        float y2 = result3f.y;
        float z2 = result3f.z;
        float scaleFactor = height / (z2 * (float)Math.tan(Math.toRadians(fov / 2.0)));
        if (z2 < 0.0f) {
            float screenX = -x2 * scaleFactor + width;
            float screenY = y2 * scaleFactor + height;
            return new Vector2f(screenX, screenY);
        }
        return new Vector2f(Float.MAX_VALUE, Float.MAX_VALUE);
    }
}


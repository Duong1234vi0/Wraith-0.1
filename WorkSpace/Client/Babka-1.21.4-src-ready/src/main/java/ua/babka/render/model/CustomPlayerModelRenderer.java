package ua.babka.render.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public final class CustomPlayerModelRenderer {
    private static final Map<String, Factory> FACTORIES = new HashMap<String, Factory>();
    private static final Map<String, CustomModelInstance> CACHE = new HashMap<String, CustomModelInstance>();

    public static boolean render(String modelName, PlayerEntityModel vanillaModel, PlayerEntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, int color) {
        CustomModelInstance instance = CustomPlayerModelRenderer.getInstance(modelName, state.id);
        if (instance == null) {
            return false;
        }
        instance.copyAngles(vanillaModel, state);
        matrices.push();
        instance.transform(matrices, state);
        RenderLayer layer = RenderLayer.getEntityTranslucent((Identifier)instance.texture);
        VertexConsumer consumer = vertexConsumers.getBuffer(layer);
        instance.root.render(matrices, consumer, light, overlay, color);
        matrices.pop();
        return true;
    }

    private static CustomModelInstance getInstance(String modelName, int entityId) {
        Factory factory = FACTORIES.get(modelName);
        if (factory == null) {
            return null;
        }
        if (CACHE.size() > 128) {
            CACHE.clear();
        }
        return CACHE.computeIfAbsent(modelName + "#" + entityId, k2 -> factory.create());
    }

    private static CustomModelInstance createRabbit() {
        ModelPart root = CustomPlayerModelRenderer.createModel(64, 64, data -> {
            ModelPartData rabbit = data.addChild("rabbit", CustomPlayerModelRenderer.box(28, 45, -5.0f, -13.0f, -5.0f, 10.0f, 11.0f, 8.0f), ModelTransform.pivot((float)0.0f, (float)24.0f, (float)0.0f));
            rabbit.addChild("right_leg", CustomPlayerModelRenderer.box(0, 32, -2.0f, 0.0f, -2.0f, 4.0f, 2.0f, 4.0f), ModelTransform.pivot((float)-3.0f, (float)-2.0f, (float)-1.0f));
            rabbit.addChild("left_leg", CustomPlayerModelRenderer.box(0, 32, -2.0f, 0.0f, -2.0f, 4.0f, 2.0f, 4.0f), ModelTransform.pivot((float)3.0f, (float)-2.0f, (float)-1.0f));
            rabbit.addChild("left_arm", CustomPlayerModelRenderer.box(24, 16, 0.0f, 0.0f, -2.0f, 2.0f, 8.0f, 4.0f), ModelTransform.of((float)5.0f, (float)-13.0f, (float)-1.0f, (float)0.0f, (float)0.0f, (float)-0.0873f));
            rabbit.addChild("right_arm", CustomPlayerModelRenderer.box(24, 16, -2.0f, 0.0f, -2.0f, 2.0f, 8.0f, 4.0f), ModelTransform.of((float)-5.0f, (float)-13.0f, (float)-1.0f, (float)0.0f, (float)0.0f, (float)0.0873f));
            ModelPartData head = rabbit.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0f, 0.0f, -4.0f, 6.0f, 1.0f, 6.0f).uv(0, 45).cuboid(-4.0f, -11.0f, -4.0f, 8.0f, 11.0f, 8.0f).uv(56, 0).cuboid(-5.0f, -9.0f, -5.0f, 2.0f, 3.0f, 2.0f).uv(56, 0).cuboid(3.0f, -9.0f, -5.0f, 2.0f, 3.0f, 2.0f).uv(46, 0).cuboid(1.0f, -20.0f, 0.0f, 3.0f, 9.0f, 1.0f).uv(46, 0).cuboid(-4.0f, -20.0f, 0.0f, 3.0f, 9.0f, 1.0f), ModelTransform.pivot((float)0.0f, (float)-14.0f, (float)-1.0f));
            head.addChild("nose", CustomPlayerModelRenderer.box(0, 7, -1.5f, -4.0f, -5.5f, 3.0f, 2.0f, 1.0f), ModelTransform.NONE);
        });
        ModelPart rabbit = root.getChild("rabbit");
        return new CustomModelInstance(Identifier.of((String)"mre", (String)"images/custommodel/rabbit.png"), root, rabbit, rabbit.getChild("head"), rabbit, rabbit.getChild("left_arm"), rabbit.getChild("right_arm"), rabbit.getChild("left_leg"), rabbit.getChild("right_leg")){

            @Override
            void transform(MatrixStack matrices, PlayerEntityRenderState state) {
                matrices.scale(1.25f, 1.25f, 1.25f);
                matrices.translate(0.0f, -0.3f, 0.0f);
            }

            @Override
            void copyAngles(PlayerEntityModel vanilla, PlayerEntityRenderState state) {
                this.copyHeadAngles(vanilla);
                this.clearAngles(this.bodyRoot);
                this.copyLimbAngles(vanilla);
                this.leftArm.roll -= 0.0873f;
                this.rightArm.roll += 0.0873f;
            }
        };
    }

    private static ModelPart createModel(int textureWidth, int textureHeight, Consumer<ModelPartData> builder) {
        ModelData data = new ModelData();
        builder.accept(data.getRoot());
        return TexturedModelData.of((ModelData)data, (int)textureWidth, (int)textureHeight).createModel();
    }

    private static ModelPartBuilder box(int u2, int v2, float x2, float y2, float z2, float w2, float h2, float d2) {
        return ModelPartBuilder.create().uv(u2, v2).cuboid(x2, y2, z2, w2, h2, d2);
    }

    private static ModelPartBuilder box(int u2, int v2, float x2, float y2, float z2, float w2, float h2, float d2, float dil) {
        return ModelPartBuilder.create().uv(u2, v2).cuboid(x2, y2, z2, w2, h2, d2, new Dilation(dil));
    }

    private CustomPlayerModelRenderer() {
    }

    static {
        FACTORIES.put("Crazy Rabbit", CustomPlayerModelRenderer::createRabbit);
    }

    static class CustomModelInstance {
        final Identifier texture;
        final ModelPart root;
        final ModelPart bodyRoot;
        final ModelPart head;
        final ModelPart body;
        final ModelPart leftArm;
        final ModelPart rightArm;
        final ModelPart leftLeg;
        final ModelPart rightLeg;

        CustomModelInstance(Identifier texture, ModelPart root, ModelPart bodyRoot, ModelPart head, ModelPart body, ModelPart leftArm, ModelPart rightArm, ModelPart leftLeg, ModelPart rightLeg) {
            this.texture = texture;
            this.root = root;
            this.bodyRoot = bodyRoot;
            this.head = head;
            this.body = body;
            this.leftArm = leftArm;
            this.rightArm = rightArm;
            this.leftLeg = leftLeg;
            this.rightLeg = rightLeg;
        }

        void transform(MatrixStack matrices, PlayerEntityRenderState state) {
        }

        void copyAngles(PlayerEntityModel vanilla, PlayerEntityRenderState state) {
            this.copyHeadAngles(vanilla);
            this.copyBodyAngles(vanilla);
            this.copyLimbAngles(vanilla);
        }

        void copyHeadAngles(PlayerEntityModel vanilla) {
            this.head.pitch = vanilla.head.pitch;
            this.head.yaw = vanilla.head.yaw;
            this.head.roll = vanilla.head.roll;
        }

        void copyBodyAngles(PlayerEntityModel vanilla) {
            this.body.pitch = vanilla.body.pitch;
            this.body.yaw = vanilla.body.yaw;
            this.body.roll = vanilla.body.roll;
        }

        void copyLimbAngles(PlayerEntityModel vanilla) {
            this.leftArm.pitch = vanilla.leftArm.pitch;
            this.leftArm.yaw = vanilla.leftArm.yaw;
            this.leftArm.roll = vanilla.leftArm.roll;
            this.rightArm.pitch = vanilla.rightArm.pitch;
            this.rightArm.yaw = vanilla.rightArm.yaw;
            this.rightArm.roll = vanilla.rightArm.roll;
            this.leftLeg.pitch = vanilla.leftLeg.pitch;
            this.leftLeg.yaw = vanilla.leftLeg.yaw;
            this.leftLeg.roll = vanilla.leftLeg.roll;
            this.rightLeg.pitch = vanilla.rightLeg.pitch;
            this.rightLeg.yaw = vanilla.rightLeg.yaw;
            this.rightLeg.roll = vanilla.rightLeg.roll;
        }

        void clearAngles(ModelPart part) {
            part.pitch = 0.0f;
            part.yaw = 0.0f;
            part.roll = 0.0f;
        }
    }

    private static interface Factory {
        public CustomModelInstance create();
    }
}


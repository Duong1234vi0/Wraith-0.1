/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10055
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1921
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_3883
 *  net.minecraft.class_3887
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_4608
 *  net.minecraft.class_591
 *  net.minecraft.class_742
 */
package code.alphadlc.features.impl.render.custommodels;

import code.alphadlc.features.impl.render.CustomModelModule;
import code.alphadlc.features.impl.render.custommodels.models.AmogusModel;
import code.alphadlc.features.impl.render.custommodels.models.ChinchillaModel;
import code.alphadlc.features.impl.render.custommodels.models.CrabModel;
import code.alphadlc.features.impl.render.custommodels.models.DemonModel;
import code.alphadlc.features.impl.render.custommodels.models.FredBearModel;
import code.alphadlc.features.impl.render.custommodels.models.JeffKillerModel;
import code.alphadlc.features.impl.render.custommodels.models.RabbitModel;
import code.alphadlc.features.impl.render.custommodels.models.SonicModel;
import net.minecraft.class_10055;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_591;
import net.minecraft.class_742;

public class CustomModelFeatureRenderer
extends class_3887<class_10055, class_591> {
    private static final class_2960 RABBIT_TEXTURE = class_2960.method_60655((String)"minecraft", (String)"models/rabbit.png");
    private static final class_2960 FREDDY_TEXTURE = class_2960.method_60655((String)"minecraft", (String)"models/freddy.png");
    private static final class_2960 DEMON_TEXTURE = class_2960.method_60655((String)"minecraft", (String)"models/demon.png");
    private static final class_2960 WHITE_DEMON_TEXTURE = class_2960.method_60655((String)"minecraft", (String)"models/whitedemon.png");
    private static final class_2960 SONIC_TEXTURE = class_2960.method_60655((String)"minecraft", (String)"models/sonic.png");
    private static final class_2960 AMOGUS_TEXTURE = class_2960.method_60655((String)"minecraft", (String)"models/amogus.png");
    private static final class_2960 JEFF_TEXTURE = class_2960.method_60655((String)"minecraft", (String)"models/jeff.png");
    private static final class_2960 CRAB_TEXTURE = class_2960.method_60655((String)"minecraft", (String)"models/crab.png");
    private static final class_2960 CHINCHILLA_TEXTURE = class_2960.method_60655((String)"minecraft", (String)"models/chinchilla.png");
    private static final class_2960 DEFAULT_TEXTURE = class_2960.method_60655((String)"minecraft", (String)"textures/entity/player/wide/steve.png");
    private final RabbitModel rabbitModel = new RabbitModel();
    private final DemonModel demonModel = new DemonModel();
    private final FredBearModel fredBearModel = new FredBearModel();
    private final SonicModel sonicModel = new SonicModel();
    private final AmogusModel amogusModel = new AmogusModel();
    private final JeffKillerModel jeffKillerModel = new JeffKillerModel();
    private final CrabModel crabModel = new CrabModel();
    private final ChinchillaModel chinchillaModel = new ChinchillaModel();

    public CustomModelFeatureRenderer(class_3883<class_10055, class_591> context) {
        super(context);
    }

    public void render(class_4587 matrices, class_4597 vertexConsumers, int light, class_10055 state, float limbAngle, float limbDistance) {
        String modelType;
        class_742 player = this.getPlayer(state);
        String string = modelType = player == null ? null : CustomModelModule.getModelType((class_1657)player);
        if (modelType == null || modelType.isEmpty()) {
            this.restoreDefaultModel();
            return;
        }
        this.hideDefaultModel();
        this.renderCustomModel(matrices, vertexConsumers, light, state, modelType);
    }

    private void hideDefaultModel() {
        class_591 model = (class_591)this.method_17165();
        model.field_3398.field_3665 = false;
        model.field_3394.field_3665 = false;
        model.field_3391.field_3665 = false;
        model.field_3401.field_3665 = false;
        model.field_27433.field_3665 = false;
        model.field_3392.field_3665 = false;
        model.field_3397.field_3665 = false;
        model.field_3483.field_3665 = false;
        model.field_3484.field_3665 = false;
        model.field_3486.field_3665 = false;
        model.field_3482.field_3665 = false;
        model.field_3479.field_3665 = false;
    }

    private void restoreDefaultModel() {
        class_591 model = (class_591)this.method_17165();
        model.field_3398.field_3665 = true;
        model.field_3394.field_3665 = true;
        model.field_3391.field_3665 = true;
        model.field_3401.field_3665 = true;
        model.field_27433.field_3665 = true;
        model.field_3392.field_3665 = true;
        model.field_3397.field_3665 = true;
        model.field_3483.field_3665 = true;
        model.field_3484.field_3665 = true;
        model.field_3486.field_3665 = true;
        model.field_3482.field_3665 = true;
        model.field_3479.field_3665 = true;
    }

    private void renderCustomModel(class_4587 matrices, class_4597 vertexConsumers, int light, class_10055 state, String modelType) {
        matrices.method_22903();
        class_4588 vertexConsumer = vertexConsumers.getBuffer(class_1921.method_23578((class_2960)this.getTextureForModel(modelType)));
        class_591 playerModel = (class_591)this.method_17165();
        switch (modelType) {
            case "Crazy Rabbit": {
                matrices.method_22905(0.825f, 0.825f, 0.825f);
                matrices.method_46416(0.0f, 0.3f, 0.0f);
                this.rabbitModel.setAngles(state, playerModel);
                this.rabbitModel.render(matrices, vertexConsumer, light, class_4608.field_21444);
                break;
            }
            case "White Demon": 
            case "Red Demon": {
                this.demonModel.setAngles(state, playerModel);
                this.demonModel.render(matrices, vertexConsumer, light, class_4608.field_21444);
                break;
            }
            case "Freddy Bear": {
                matrices.method_22905(0.75f, 0.65f, 0.75f);
                matrices.method_46416(0.0f, 0.85f, 0.0f);
                this.fredBearModel.setAngles(state, playerModel);
                this.fredBearModel.render(matrices, vertexConsumer, light, class_4608.field_21444);
                break;
            }
            case "Sonic": {
                matrices.method_22905(1.3f, 1.3f, 1.3f);
                matrices.method_22904(0.0, -0.35, 0.0);
                this.sonicModel.setAngles(state, playerModel);
                this.sonicModel.render(matrices, vertexConsumer, light, class_4608.field_21444);
                break;
            }
            case "Amogus": {
                this.amogusModel.setAngles(state, playerModel);
                this.amogusModel.render(matrices, vertexConsumer, light, class_4608.field_21444);
                break;
            }
            case "Jeff Killer": {
                this.jeffKillerModel.setAngles(state, playerModel);
                this.jeffKillerModel.render(matrices, vertexConsumer, light, class_4608.field_21444);
                break;
            }
            case "Crab": {
                this.crabModel.setAngles(state, playerModel);
                this.crabModel.render(matrices, vertexConsumer, light, class_4608.field_21444);
                break;
            }
            case "Chinchilla": {
                matrices.method_22905(1.4f, 1.4f, 1.4f);
                matrices.method_46416(0.0f, -0.45f, 0.0f);
                this.chinchillaModel.setAngles(state, playerModel);
                this.chinchillaModel.render(matrices, vertexConsumer, light, class_4608.field_21444);
                break;
            }
        }
        matrices.method_22909();
    }

    private class_2960 getTextureForModel(String modelType) {
        return switch (modelType) {
            case "Crazy Rabbit" -> RABBIT_TEXTURE;
            case "Freddy Bear" -> FREDDY_TEXTURE;
            case "Red Demon" -> DEMON_TEXTURE;
            case "White Demon" -> WHITE_DEMON_TEXTURE;
            case "Sonic" -> SONIC_TEXTURE;
            case "Amogus" -> AMOGUS_TEXTURE;
            case "Jeff Killer" -> JEFF_TEXTURE;
            case "Crab" -> CRAB_TEXTURE;
            case "Chinchilla" -> CHINCHILLA_TEXTURE;
            default -> DEFAULT_TEXTURE;
        };
    }

    private class_742 getPlayer(class_10055 state) {
        class_742 player;
        class_310 client = class_310.method_1551();
        if (client.field_1687 == null) {
            return null;
        }
        class_1297 class_12972 = client.field_1687.method_8469(state.field_53528);
        return class_12972 instanceof class_742 ? (player = (class_742)class_12972) : null;
    }
}

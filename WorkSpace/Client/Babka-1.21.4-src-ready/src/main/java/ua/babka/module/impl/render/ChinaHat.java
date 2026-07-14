package ua.babka.module.impl.render;

import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.entity.EntityType;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.render.model.ChinaHatRenderer;

@ModuleInformation(moduleName="China Hat", moduleDesc="Китайская шляпа над головой", moduleCategory=ModuleCategory.RENDER)
public class ChinaHat
extends Module {
    private static ChinaHat instance;

    public ChinaHat() {
        instance = this;
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityType == EntityType.PLAYER) {
                registrationHelper.register((FeatureRenderer)new ChinaHatRenderer((FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel>)entityRenderer));
            }
        });
    }

    public static ChinaHat getInstance() {
        return instance;
    }
}


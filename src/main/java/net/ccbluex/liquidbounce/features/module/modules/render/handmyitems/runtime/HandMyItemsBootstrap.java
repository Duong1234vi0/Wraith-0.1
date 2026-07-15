package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime;

import net.fabricmc.api.ClientModInitializer;

/** Registers the HMI built-in pack before Minecraft creates its initial resource-pack repository. */
public final class HandMyItemsBootstrap implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandMyItemsRuntime.initialize();
    }
}

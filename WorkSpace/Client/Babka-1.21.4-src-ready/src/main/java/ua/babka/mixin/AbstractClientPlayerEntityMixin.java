package ua.babka.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ua.babka.module.impl.render.Cape;
import ua.babka.render.model.AnimatedCape;

@Mixin(value={AbstractClientPlayerEntity.class})
public class AbstractClientPlayerEntityMixin {
    private static final Identifier CUSTOM_CAPE = Identifier.of((String)"mre", (String)"images/cape/cape.gif");

    @Inject(method={"getSkinTextures"}, at={@At(value="RETURN")}, cancellable=true)
    private void onGetSkinTextures(CallbackInfoReturnable<SkinTextures> cir) {
        AbstractClientPlayerEntity player = (AbstractClientPlayerEntity)this;
        Cape capeModule = Cape.getInstance();
        if (capeModule != null && capeModule.shouldRenderCape(player)) {
            boolean hasElytra = player.getInventory().getArmorStack(2).isOf(Items.ELYTRA);
            if (hasElytra) {
                return;
            }
            AnimatedCape.getInstance().setCape(CUSTOM_CAPE);
            AnimatedCape.getInstance().tick();
            SkinTextures original = (SkinTextures)cir.getReturnValue();
            cir.setReturnValue((Object)new SkinTextures(original.comp_1626(), original.comp_1911(), CUSTOM_CAPE, CUSTOM_CAPE, original.comp_1629(), original.comp_1630()));
        }
    }
}


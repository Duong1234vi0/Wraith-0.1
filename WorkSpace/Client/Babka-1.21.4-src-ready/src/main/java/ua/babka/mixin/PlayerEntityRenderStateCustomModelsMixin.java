package ua.babka.mixin;

import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import ua.babka.render.model.ICustomPlayerModelState;

@Mixin(value={PlayerEntityRenderState.class})
public class PlayerEntityRenderStateCustomModelsMixin
implements ICustomPlayerModelState {
    @Unique
    private boolean mre$hasCustomModel;
    @Unique
    private String mre$customModel = "";
    @Unique
    private int mre$entityId;

    @Override
    public boolean mre$hasCustomModel() {
        return this.mre$hasCustomModel;
    }

    @Override
    public String mre$getCustomModel() {
        return this.mre$customModel;
    }

    @Override
    public int mre$getEntityId() {
        return this.mre$entityId;
    }

    @Override
    public void mre$setCustomModel(boolean enabled, String model, int entityId) {
        this.mre$hasCustomModel = enabled;
        this.mre$customModel = model == null ? "" : model;
        this.mre$entityId = entityId;
    }
}


/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_310
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.features.impl.render.custommodels.CustomModelSyncManager;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.utils.client.Instance;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_310;
import org.jetbrains.annotations.Nullable;

public class CustomModelModule
extends Module {
    private static CustomModelModule instance;
    public final SelectSetting models = new SelectSetting("Model", "Select custom player model").value("White Demon", "Red Demon", "Crazy Rabbit", "Freddy Bear", "Sonic", "Amogus", "Jeff Killer", "Crab", "Chinchilla").selected("White Demon");
    public final BooleanSetting friends = new BooleanSetting("Apply to Friends", "Apply custom models to friends").setValue(true);

    public CustomModelModule() {
        super("CustomModel", "Custom Models", ModuleCategory.RENDER);
        this.setup(this.models, this.friends);
        instance = this;
    }

    public static CustomModelModule getInstance() {
        return instance;
    }

    @Nullable
    public static String getModelType(@Nullable class_1657 player) {
        CustomModelSyncManager.RemoteModelState remoteState;
        CustomModelModule module = Instance.get(CustomModelModule.class);
        if (module == null || !module.isState()) {
            return null;
        }
        if (player == null) {
            return null;
        }
        if (player == class_310.method_1551().field_1724) {
            return module.models.getSelected();
        }
        CustomModelSyncManager syncManager = CustomModelSyncManager.getInstance();
        if (syncManager != null && (remoteState = syncManager.getRemoteState(player.method_5667())) != null) {
            return remoteState.enabled() ? remoteState.modelType() : null;
        }
        if (module.friends.isValue() && FriendUtils.isFriend((class_1297)player)) {
            return module.models.getSelected();
        }
        return null;
    }
}

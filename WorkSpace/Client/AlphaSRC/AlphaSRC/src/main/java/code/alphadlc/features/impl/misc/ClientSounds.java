/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;

public class ClientSounds
extends Module {
    public SliderSettings volumeSetting = new SliderSettings("\u0413\u0440\u043e\u043c\u043a\u043e\u0441\u0442\u044c", "\u0413\u0440\u043e\u043c\u043a\u043e\u0441\u0442\u044c \u0437\u0432\u0443\u043a\u043e\u0432 \u043a\u043b\u0438\u0435\u043d\u0442\u0430").range(0.0f, 1.0f).setValue(1.0f);
    public SliderSettings pitchSetting = new SliderSettings("\u0422\u043e\u043d", "\u0422\u043e\u043d\u0430\u043b\u044c\u043d\u043e\u0441\u0442\u044c (pitch) \u0437\u0432\u0443\u043a\u043e\u0432 \u043a\u043b\u0438\u0435\u043d\u0442\u0430").range(0.5f, 2.0f).setValue(1.0f);

    public static ClientSounds getInstance() {
        return Instance.get(ClientSounds.class);
    }

    public ClientSounds() {
        super("ClientSounds", "ClientSounds", ModuleCategory.MISC);
        this.setup(this.volumeSetting, this.pitchSetting);
    }

    public float getVolume() {
        return this.volumeSetting.getValue();
    }

    public float getPitch() {
        return this.pitchSetting.getValue();
    }
}

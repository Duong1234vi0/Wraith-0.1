/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_310
 *  org.apache.logging.log4j.LogManager
 */
package code.alphadlc.features.module;

import code.alphadlc.AlphaDLC;
import code.alphadlc.common.animation.Animation;
import code.alphadlc.common.animation.Direction;
import code.alphadlc.common.animation.implement.Decelerate;
import code.alphadlc.display.hud.DynamicIsland;
import code.alphadlc.features.impl.render.Hud;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.SettingRepository;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.utils.client.managers.event.EventManager;
import code.alphadlc.utils.client.sound.SoundManager;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_124;
import net.minecraft.class_310;
import org.apache.logging.log4j.LogManager;

public class Module
extends SettingRepository
implements QuickImports {
    private final String name;
    private final String visibleName;
    private final ModuleCategory category;
    private final Animation animation = new Decelerate().setMs(175).setValue(1.0);
    private int key = -1;
    private int type = 1;
    private int secondKey = -1;
    public boolean state;
    private boolean hidden;

    public Module(String name, ModuleCategory category) {
        this.name = name;
        this.category = category;
        this.visibleName = name;
    }

    public Module(String name, String visibleName, ModuleCategory category) {
        this.name = name;
        this.visibleName = visibleName;
        this.category = category;
    }

    public BindSetting getBind() {
        BindSetting bind = new BindSetting("\u0411\u0438\u043d\u0434", "\u041a\u043b\u0430\u0432\u0438\u0448\u0430 \u0430\u043a\u0442\u0438\u0432\u0430\u0446\u0438\u0438");
        bind.setKey(this.key);
        bind.setSecondKey(this.secondKey);
        return bind;
    }

    public void setBindFromSetting(BindSetting bind) {
        if (bind != null) {
            this.key = bind.getKey();
            this.secondKey = bind.getSecondKey();
        }
    }

    public void switchState() {
        this.setState(!this.state);
    }

    public void setState(boolean state) {
        this.animation.setDirection(state ? Direction.FORWARDS : Direction.BACKWARDS);
        if (state != this.state) {
            this.state = state;
            this.handleStateChange();
        }
    }

    private void handleStateChange() {
        try {
            class_310 mc = class_310.method_1551();
            Hud hud = Hud.getInstance();
            if (hud == null) {
                if (this.state) {
                    this.activate();
                } else {
                    this.deactivate();
                }
                this.toggleSilent(this.state);
                return;
            }
            if (mc.field_1724 != null && mc.field_1687 != null) {
                String cleanName;
                String string = cleanName = class_124.method_539((String)this.visibleName) == null ? this.visibleName : class_124.method_539((String)this.visibleName);
                if (this.state) {
                    DynamicIsland.notifyModuleToggle(cleanName, true);
                    SoundManager.playSound(SoundManager.ENABLE_MODULE);
                    this.activate();
                } else {
                    DynamicIsland.notifyModuleToggle(cleanName, false);
                    SoundManager.playSound(SoundManager.DISABLE_MODULE);
                    this.deactivate();
                }
            }
            this.toggleSilent(this.state);
        }
        catch (Throwable error) {
            this.state = false;
            LogManager.getLogger((String)"AlphaDLC").error("Failed to change state for module {}", (Object)this.name, (Object)error);
        }
    }

    private void toggleSilent(boolean activate) {
        EventManager eventManager = AlphaDLC.getInstance().getEventManager();
        if (eventManager == null) {
            return;
        }
        try {
            if (activate) {
                if (!EventManager.canInspectEventHandlers(this)) {
                    this.state = false;
                    return;
                }
                EventManager.register(this);
            } else {
                eventManager.unregister(this);
            }
        }
        catch (Throwable error) {
            this.state = false;
            LogManager.getLogger((String)"AlphaDLC").error("Failed to {} event handlers for module {}", (Object)(activate ? "register" : "unregister"), (Object)this.name, (Object)error);
        }
    }

    public void activate() {
    }

    public void deactivate() {
    }

    public String getName() {
        return this.name;
    }

    public String getVisibleName() {
        return this.visibleName;
    }

    public ModuleCategory getCategory() {
        return this.category;
    }

    public Animation getAnimation() {
        return this.animation;
    }

    public int getKey() {
        return this.key;
    }

    public int getType() {
        return this.type;
    }

    public int getSecondKey() {
        return this.secondKey;
    }

    public boolean isState() {
        return this.state;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setSecondKey(int secondKey) {
        this.secondKey = secondKey;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}

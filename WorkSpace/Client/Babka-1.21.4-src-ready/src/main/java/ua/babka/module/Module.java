package ua.babka.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.client.MinecraftClient;
import ua.babka.Babka;
import ua.babka.core.ConfigManager;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.impl.render.ClientSounds;
import ua.babka.module.impl.render.Interface;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.Setting;
import ua.babka.util.IMinecraft;
import ua.babka.util.Instance;
import ua.babka.util.QuickLogger;
import ua.babka.util.animation.Animation;
import ua.babka.util.animation.Easing;

public class Module
implements IMinecraft,
QuickLogger {
    private final String name;
    private final String desc;
    private final ModuleCategory category;
    private int key;
    private boolean enabled;
    private final Animation animation = new Animation(Easing.BACK_OUT, 450L);
    public final MinecraftClient mc = MinecraftClient.getInstance();
    private final List<Setting> settings = new ArrayList<Setting>();

    public Module() {
        ModuleInformation information = this.getClass().getAnnotation(ModuleInformation.class);
        this.name = information.moduleName();
        this.desc = information.moduleDesc();
        this.category = information.moduleCategory();
        this.key = information.moduleKeybind();
    }

    public List<Setting> getSettings() {
        return Arrays.stream(this.getClass().getDeclaredFields()).map(field -> {
            try {
                field.setAccessible(true);
                return field.get(this);
            }
            catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return null;
            }
        }).filter(field -> field instanceof Setting).map(field -> (Setting)field).collect(Collectors.toList());
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled != enabled) {
            this.enabled = enabled;
            if (enabled) {
                this.onEnable();
            } else {
                this.onDisable();
            }
            Interface iface = Instance.get(Interface.class);
            if (iface != null) {
                iface.notifications.post(this.name, enabled);
            }
        }
        if (this.name.equals("ClickGui")) {
            return;
        }
        ClientSounds soundsModule = Instance.get(ClientSounds.class);
        if (soundsModule != null && (soundsModule.isEnabled() || this instanceof ClientSounds)) {
            ClientSounds.play(this.isEnabled());
        }
        ConfigManager.save("autocfg");
    }

    protected ModeSetting modeCreate() {
        return new ModeSetting("Мод", "Vanilla", "Vanilla", "Grim", "Polar");
    }

    public void onEnable() {
        Babka.getInstance().getEventBus().register((Object)this);
    }

    public void onDisable() {
        Babka.getInstance().getEventBus().unregister((Object)this);
    }

    public void toggle() {
        this.setEnabled(!this.isEnabled());
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public ModuleCategory getCategory() {
        return this.category;
    }

    public int getKey() {
        return this.key;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public Animation getAnimation() {
        return this.animation;
    }

    public MinecraftClient getMc() {
        return this.mc;
    }

    public void setKey(int key) {
        this.key = key;
    }
}


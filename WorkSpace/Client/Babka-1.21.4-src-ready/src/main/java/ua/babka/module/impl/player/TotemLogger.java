package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import java.util.concurrent.ConcurrentHashMap;
import ua.babka.event.EventPopTotem;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.util.ChatUtil;

@ModuleInformation(moduleName="TotemLogger", moduleDesc="Логирует сколько  тотемов потерял игрок ", moduleCategory=ModuleCategory.MISC)
public class TotemLogger
extends Module {
    private final ConcurrentHashMap<String, Integer> totemCounts = new ConcurrentHashMap();

    @Subscribe
    private void onPopTotem(EventPopTotem event) {
        if (this.mc.player == null) {
            return;
        }
        if (event.getPlayer() == this.mc.player) {
            return;
        }
        String name = event.getPlayer().getName().getString();
        int count = this.totemCounts.merge(name, 1, Integer::sum);
        ChatUtil.send((Object)(name + " потерял " + count + " " + this.pluralize(count)));
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.totemCounts.clear();
    }

    private String pluralize(int n2) {
        int mod10 = n2 % 10;
        int mod100 = n2 % 100;
        if (mod100 >= 11 && mod100 <= 14) {
            return "тотемов";
        }
        if (mod10 == 1) {
            return "тотем";
        }
        if (mod10 >= 2 && mod10 <= 4) {
            return "тотема";
        }
        return "тотемов";
    }
}


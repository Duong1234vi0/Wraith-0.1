/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.utils.client.managers.api.draggable;

import code.alphadlc.display.hud.ArmorBar;
import code.alphadlc.display.hud.CoolDowns;
import code.alphadlc.display.hud.CustomHotbar;
import code.alphadlc.display.hud.DynamicIsland;
import code.alphadlc.display.hud.Info;
import code.alphadlc.display.hud.InventoryHud;
import code.alphadlc.display.hud.Keybinds;
import code.alphadlc.display.hud.Notifications;
import code.alphadlc.display.hud.Potions;
import code.alphadlc.display.hud.StaffList;
import code.alphadlc.display.hud.TargetHud;
import code.alphadlc.display.hud.TotemBar;
import code.alphadlc.utils.client.managers.api.draggable.AbstractDraggable;
import java.util.ArrayList;
import java.util.List;

public class DraggableRepository {
    private final List<AbstractDraggable> draggable = new ArrayList<AbstractDraggable>();

    public void setup() {
        this.register(new DynamicIsland(), new TargetHud(), Notifications.getInstance(), new CoolDowns(), new Keybinds(), new Potions(), new StaffList(), Info.getInstance(), new ArmorBar(), new TotemBar(), new CustomHotbar(), InventoryHud.getInstance());
    }

    public void register(AbstractDraggable ... module) {
        this.draggable.addAll(List.of(module));
    }

    public List<AbstractDraggable> draggable() {
        return this.draggable;
    }

    public <T extends AbstractDraggable> T get(String name) {
        return (T)((AbstractDraggable)this.draggable.stream().filter(module -> module.getName().equalsIgnoreCase(name)).map(module -> module).findFirst().orElse(null));
    }

    public <T extends AbstractDraggable> T get(Class<T> clazz) {
        return (T)((AbstractDraggable)this.draggable.stream().filter(module -> clazz.isAssignableFrom(module.getClass())).map(clazz::cast).findFirst().orElse(null));
    }
}

package ua.babka.ui.component;

import java.util.HashMap;
import ua.babka.Babka;
import ua.babka.ui.component.ComponentHelper;
import ua.babka.ui.component.FreeLookComponent;
import ua.babka.ui.component.RotationComponent;

public final class ComponentManager
extends HashMap<Class<? extends ComponentHelper>, ComponentHelper> {
    public void init() {
        this.add(new RotationComponent(), new FreeLookComponent());
        this.values().forEach(component -> Babka.getInstance().getEventBus().register(component));
    }

    public void add(ComponentHelper ... components) {
        for (ComponentHelper component : components) {
            this.put(component.getClass(), component);
        }
    }

    public <T extends ComponentHelper> T get(Class<T> clazz) {
        return (T)((ComponentHelper)this.values().stream().filter(component -> component.getClass() == clazz).map(clazz::cast).findFirst().orElse(null));
    }
}


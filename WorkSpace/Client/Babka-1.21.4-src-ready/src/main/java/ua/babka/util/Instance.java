package ua.babka.util;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;
import ua.babka.Babka;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.ui.component.ComponentHelper;

public final class Instance {
    private static final ConcurrentMap<Class<? extends Module>, Module> instances = new ConcurrentHashMap<Class<? extends Module>, Module>();
    private static final ConcurrentMap<Class<? extends ComponentHelper>, ComponentHelper> componentInstances = new ConcurrentHashMap<Class<? extends ComponentHelper>, ComponentHelper>();

    public static <T extends Module> T get(Class<T> clazz) {
        return (T)((Module)clazz.cast(instances.computeIfAbsent(clazz, instance -> Babka.getInstance().getModuleStorage().get(instance))));
    }

    public static <T extends ComponentHelper> T getComponent(Class<T> clazz) {
        return (T)((ComponentHelper)clazz.cast(componentInstances.computeIfAbsent(clazz, instance -> Babka.getInstance().getComponentManager().get(instance))));
    }

    public static <T extends Module> Supplier<T> getSupplier(Class<T> clazz) {
        return () -> clazz.cast(instances.computeIfAbsent(clazz, instance -> Babka.getInstance().getModuleStorage().get(instance)));
    }

    public static <T extends Module> T get(String module) {
        return Babka.getInstance().getModuleStorage().get(module);
    }

    public static List<Module> get(ModuleCategory category) {
        return Babka.getInstance().getModuleStorage().get(category);
    }

    private Instance() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}


/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package mods.voicechat.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.ServiceLoader;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Service {
    private static final Logger LOGGER = LogManager.getLogger((String)"voicechat");

    public static <T> T get(Class<T> serviceClass) {
        Iterator<T> iterator = ServiceLoader.load(serviceClass).iterator();
        if (!iterator.hasNext()) {
            LOGGER.warn("Failed to load service {} with ServiceLoader", (Object)serviceClass.getSimpleName());
            try {
                return Service.loadFallback(serviceClass);
            }
            catch (Exception e) {
                throw new IllegalStateException("Failed to load service " + serviceClass.getSimpleName(), e);
            }
        }
        return iterator.next();
    }

    private static <T> T loadFallback(Class<T> serviceClass) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> fallbackClass = Service.loadFallbackClass(serviceClass);
        if (!serviceClass.isAssignableFrom(fallbackClass)) {
            throw new ClassNotFoundException("Class " + fallbackClass.getSimpleName() + " is not an instance of " + serviceClass.getSimpleName());
        }
        return (T)fallbackClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
    }

    private static Class<?> loadFallbackClass(Class<?> serviceClass) throws ClassNotFoundException {
        Class<?> implClass = Service.loadClassWithPrefix(serviceClass, "Fabric");
        if (implClass != null) {
            return implClass;
        }
        implClass = Service.loadClassWithPrefix(serviceClass, "Forge");
        if (implClass != null) {
            return implClass;
        }
        implClass = Service.loadClassWithPrefix(serviceClass, "Quilt");
        if (implClass != null) {
            return implClass;
        }
        throw new ClassNotFoundException("Implementation of " + serviceClass.getSimpleName() + " not found in package " + serviceClass.getPackage().getName());
    }

    @Nullable
    private static Class<?> loadClassWithPrefix(Class<?> serviceClass, String prefix) {
        try {
            return Class.forName(serviceClass.getPackage().getName() + "." + prefix + serviceClass.getSimpleName());
        }
        catch (ClassNotFoundException e) {
            return null;
        }
    }
}


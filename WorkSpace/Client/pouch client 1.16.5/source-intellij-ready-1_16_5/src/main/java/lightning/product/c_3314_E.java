/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.lwjgl.system.Pointer
 */
package lightning.product;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.annotation.Nullable;
import lightning.product.g_164_R;
import org.lwjgl.system.Pointer;

public class c_3314_E {
    @Nullable
    private static final MethodHandle n_1700_B = g_164_R.n_1700_B(() -> {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            Class<?> oclass = Class.forName("org.lwjgl.system.MemoryManage$DebugAllocator");
            Method method = oclass.getDeclaredMethod("untrack", Long.TYPE);
            method.setAccessible(true);
            Field field = Class.forName("org.lwjgl.system.MemoryUtil$LazyInit").getDeclaredField("ALLOCATOR");
            field.setAccessible(true);
            Object object = field.get(null);
            return oclass.isInstance(object) ? lookup.unreflect(method) : null;
        }
        catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException classnotfoundexception) {
            throw new RuntimeException(classnotfoundexception);
        }
    });

    public static void n_1700_B(long memAddr) {
        if (n_1700_B != null) {
            try {
                n_1700_B.invoke(memAddr);
            }
            catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        }
    }

    public static void n_1700_B(Pointer pointer) {
        c_3314_E.n_1700_B(pointer.address());
    }
}


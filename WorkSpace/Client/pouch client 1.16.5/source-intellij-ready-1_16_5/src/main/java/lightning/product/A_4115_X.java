/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import lightning.product.Y_1740_V;
import lightning.product.d_2461_k;
import lightning.product.e_4240_b;
import lightning.product.x_607_J;

public final class A_4115_X {
    private static final Map<Class<? extends x_607_J>, List<n_1700_B>> n_1700_B = new HashMap<Class<? extends x_607_J>, List<n_1700_B>>();

    private A_4115_X() {
    }

    public static void n_1700_B(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (A_4115_X.n_1700_B(method)) continue;
            A_4115_X.n_1700_B(method, object);
        }
    }

    public static void n_1700_B(Object object, Class<? extends x_607_J> eventClass) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (A_4115_X.n_1700_B(method, eventClass)) continue;
            A_4115_X.n_1700_B(method, object);
        }
    }

    public static void J_1907_R(Object object) {
        for (List<n_1700_B> dataList : n_1700_B.values()) {
            for (n_1700_B data : dataList) {
                if (!data.n_1700_B().equals(object)) continue;
                dataList.remove(data);
            }
        }
        A_4115_X.n_1700_B(true);
    }

    public static void J_1907_R(Object object, Class<? extends x_607_J> eventClass) {
        if (n_1700_B.containsKey(eventClass)) {
            for (n_1700_B data : n_1700_B.get(eventClass)) {
                if (!data.n_1700_B().equals(object)) continue;
                n_1700_B.get(eventClass).remove(data);
            }
            A_4115_X.n_1700_B(true);
        }
    }

    private static void n_1700_B(Method method, Object object) {
        Class<?> indexClass = method.getParameterTypes()[0];
        final n_1700_B data = new n_1700_B(object, method, method.getAnnotation(Y_1740_V.class).n_1700_B());
        if (!data.J_1907_R().isAccessible()) {
            data.J_1907_R().setAccessible(true);
        }
        if (n_1700_B.containsKey(indexClass)) {
            if (!n_1700_B.get(indexClass).contains(data)) {
                n_1700_B.get(indexClass).add(data);
                A_4115_X.J_1907_R(indexClass);
            }
        } else {
            n_1700_B.put(indexClass, (List<n_1700_B>)new CopyOnWriteArrayList<n_1700_B>(){
                private static final long J_1907_R = 666L;
                {
                    this.add(data);
                }
            });
        }
    }

    public static void n_1700_B(Class<? extends x_607_J> indexClass) {
        Iterator<Map.Entry<Class<? extends x_607_J>, List<n_1700_B>>> mapIterator = n_1700_B.entrySet().iterator();
        while (mapIterator.hasNext()) {
            if (!mapIterator.next().getKey().equals(indexClass)) continue;
            mapIterator.remove();
            break;
        }
    }

    public static void n_1700_B(boolean onlyEmptyEntries) {
        Iterator<Map.Entry<Class<? extends x_607_J>, List<n_1700_B>>> mapIterator = n_1700_B.entrySet().iterator();
        while (mapIterator.hasNext()) {
            if (onlyEmptyEntries && !mapIterator.next().getValue().isEmpty()) continue;
            mapIterator.remove();
        }
    }

    private static void J_1907_R(Class<? extends x_607_J> indexClass) {
        CopyOnWriteArrayList<n_1700_B> sortedList = new CopyOnWriteArrayList<n_1700_B>();
        for (byte priority : d_2461_k.u_1723_Y) {
            for (n_1700_B data : n_1700_B.get(indexClass)) {
                if (data.R_4764_Y() != priority) continue;
                sortedList.add(data);
            }
        }
        n_1700_B.put(indexClass, sortedList);
    }

    private static boolean n_1700_B(Method method) {
        return method.getParameterTypes().length != 1 || !method.isAnnotationPresent(Y_1740_V.class);
    }

    private static boolean n_1700_B(Method method, Class<? extends x_607_J> eventClass) {
        return A_4115_X.n_1700_B(method) || !method.getParameterTypes()[0].equals(eventClass);
    }

    public static final x_607_J n_1700_B(x_607_J event) {
        block4: {
            List<n_1700_B> dataList = n_1700_B.get(event.getClass());
            if (dataList == null) break block4;
            if (event instanceof e_4240_b) {
                e_4240_b stoppable = (e_4240_b)event;
                for (n_1700_B data : dataList) {
                    A_4115_X.n_1700_B(data, event);
                    if (!stoppable.J_1907_R()) continue;
                    break;
                }
            } else {
                for (n_1700_B data : dataList) {
                    A_4115_X.n_1700_B(data, event);
                }
            }
        }
        return event;
    }

    private static void n_1700_B(n_1700_B data, x_607_J argument) {
        try {
            data.J_1907_R().invoke(data.n_1700_B(), argument);
        }
        catch (IllegalAccessException illegalAccessException) {
        }
        catch (IllegalArgumentException illegalArgumentException) {
        }
        catch (InvocationTargetException invocationTargetException) {
            // empty catch block
        }
    }

    private static final class n_1700_B {
        private final Object n_1700_B;
        private final Method J_1907_R;
        private final byte R_4764_Y;

        public n_1700_B(Object source, Method target, byte priority) {
            this.n_1700_B = source;
            this.J_1907_R = target;
            this.R_4764_Y = priority;
        }

        public Object n_1700_B() {
            return this.n_1700_B;
        }

        public Method J_1907_R() {
            return this.J_1907_R;
        }

        public byte R_4764_Y() {
            return this.R_4764_Y;
        }
    }
}


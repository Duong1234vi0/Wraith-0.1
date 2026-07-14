/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.concurrent;

import com.electronwill.nightconfig.core.AbstractCommentedConfig;
import com.electronwill.nightconfig.core.AbstractConfig;
import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.InMemoryCommentedFormat;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.concurrent.ConcurrentCommentedConfig;
import com.electronwill.nightconfig.core.concurrent.StampedConfig;
import com.electronwill.nightconfig.core.utils.TransformingMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class SynchronizedConfig
implements ConcurrentCommentedConfig {
    private DataHolder dataHolder;
    final Object rootMonitor;

    public static SynchronizedConfig convert(Config c) {
        return SynchronizedConfig.convert(c, null);
    }

    private static SynchronizedConfig convert(Config c, SynchronizedConfig parent) {
        if (c instanceof SynchronizedConfig) {
            return (SynchronizedConfig)c;
        }
        SynchronizedConfig result = new SynchronizedConfig(c.configFormat(), Config.getDefaultMapCreator(false), parent);
        CommentedConfig cc = CommentedConfig.fake(c);
        SynchronizedConfig.convertSubConfigs(cc, result);
        result.putAll(cc);
        result.putAllComments(cc);
        return result;
    }

    private static void convertSubConfigs(Config c, SynchronizedConfig parent) {
        if (c instanceof AbstractConfig) {
            AbstractConfig conf = (AbstractConfig)c;
            conf.valueMap().replaceAll((k, v) -> SynchronizedConfig.convertValue(v, parent));
        } else {
            for (Config.Entry entry : c.entrySet()) {
                Object converted;
                Object value = entry.getRawValue();
                if (value == (converted = SynchronizedConfig.convertValue(value, parent))) continue;
                entry.setValue(converted);
            }
        }
    }

    private static Object convertValue(Object v, SynchronizedConfig parent) {
        if (v instanceof Config) {
            SynchronizedConfig subConfig = SynchronizedConfig.convert((Config)v, parent);
            SynchronizedConfig.convertSubConfigs(subConfig, subConfig);
            return subConfig;
        }
        if (v instanceof List) {
            List l = (List)v;
            ArrayList newList = new ArrayList(l);
            newList.replaceAll(elem -> SynchronizedConfig.convertValue(elem, parent));
            return newList;
        }
        return v;
    }

    public SynchronizedConfig() {
        this(InMemoryCommentedFormat.defaultInstance(), Config.getDefaultMapCreator(false));
    }

    public SynchronizedConfig(ConfigFormat<?> configFormat, Supplier<Map<String, Object>> mapSupplier) {
        this.rootMonitor = new Object();
        this.dataHolder = new DataHolder(this, configFormat, mapSupplier);
    }

    public SynchronizedConfig(ConfigFormat<?> configFormat, Supplier<Map<String, Object>> mapSupplier, SynchronizedConfig parent) {
        this.rootMonitor = parent == null ? new Object() : parent.rootMonitor;
        this.dataHolder = new DataHolder(parent == null ? this : parent, configFormat, mapSupplier);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void replaceContentBy(SynchronizedConfig newContent) {
        Object object = this.rootMonitor;
        synchronized (object) {
            Object object2 = newContent.rootMonitor;
            synchronized (object2) {
                this.dataHolder = newContent.dataHolder;
                newContent.dataHolder = null;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void replaceContentBy(Config newContent) {
        if (newContent instanceof SynchronizedConfig) {
            this.replaceContentBy((SynchronizedConfig)newContent);
        } else {
            if (newContent instanceof StampedConfig) {
                throw new UnsupportedOperationException("SynchronizedConfig.replaceContentBy(StampedConfig) is illegal (and useless anyway).");
            }
            CommentedConfig cc = CommentedConfig.fake(newContent);
            Supplier<Map<String, Object>> mapSupplier = null;
            if (newContent instanceof StampedConfig.Accumulator) {
                mapSupplier = ((StampedConfig.Accumulator)newContent).mapSupplier();
            } else if (newContent instanceof AbstractConfig) {
                try {
                    Map<String, Object> map = ((AbstractConfig)newContent).valueMap();
                    if (map instanceof HashMap) {
                        mapSupplier = HashMap::new;
                    } else if (map instanceof LinkedHashMap) {
                        mapSupplier = LinkedHashMap::new;
                    }
                }
                catch (UnsupportedOperationException ex) {
                    mapSupplier = null;
                }
            }
            if (mapSupplier == null) {
                mapSupplier = Config.getDefaultMapCreator(false);
            }
            Object object = this.rootMonitor;
            synchronized (object) {
                DataHolder dataHolder = new DataHolder(this, newContent.configFormat(), mapSupplier);
                dataHolder.putAll(cc);
                dataHolder.putAllComments(cc);
                SynchronizedConfig.convertSubConfigs(dataHolder, this);
                this.dataHolder = dataHolder;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <R> R bulkCommentedRead(Function<? super UnmodifiableCommentedConfig, R> action) {
        Object object = this.rootMonitor;
        synchronized (object) {
            return action.apply(this.dataHolder);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <R> R bulkCommentedUpdate(Function<? super CommentedConfig, R> action) {
        Object object = this.rootMonitor;
        synchronized (object) {
            return action.apply(this.dataHolder);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean add(List<String> path, Object value) {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.add(path, value);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void clearComments() {
        Object object = this.rootMonitor;
        synchronized (object) {
            this.dataHolder.clearComments();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Map<String, String> commentMap() {
        Object object = this.rootMonitor;
        synchronized (object) {
            return new SynchronizedMap<String, String>(this.dataHolder.commentMap(), this.rootMonitor);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String removeComment(List<String> path) {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.removeComment(path);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String setComment(List<String> path, String comment) {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.setComment(path, comment);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean containsComment(List<String> path) {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.containsComment(path);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getComment(List<String> path) {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.getComment(path);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ConfigFormat<?> configFormat() {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.configFormat();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void clear() {
        Object object = this.rootMonitor;
        synchronized (object) {
            this.dataHolder.clear();
        }
    }

    @Override
    public SynchronizedConfig createSubConfig() {
        return this.dataHolder.createSubConfig();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Set<? extends CommentedConfig.Entry> entrySet() {
        Object object = this.rootMonitor;
        synchronized (object) {
            return new SynchronizedSet<CommentedConfig.Entry>(this.dataHolder.entrySet(), this.rootMonitor);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <T> T remove(List<String> path) {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.remove(path);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <T> T set(List<String> path, Object value) {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.set(path, value);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String toString() {
        Object object = this.rootMonitor;
        synchronized (object) {
            return "SynchronizedConfig{" + this.dataHolder.toString() + "}";
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean contains(List<String> path) {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.contains(path);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean equals(Object obj) {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.equals(obj);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <T> T getRaw(List<String> path) {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.getRaw(path);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int hashCode() {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.hashCode();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isEmpty() {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.isEmpty();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int size() {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.size();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public Map<String, Object> valueMap() {
        Object object = this.rootMonitor;
        synchronized (object) {
            TransformingMap<String, Object, Object> transformingMap = new TransformingMap<String, Object, Object>(this.dataHolder.valueMap(), o -> o, toWrite -> SynchronizedConfig.convertValue(toWrite, this), o -> o);
            return new SynchronizedMap<String, Object>(transformingMap, this.rootMonitor);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean add(String path, Object value) {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.add(path, value);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addAll(UnmodifiableConfig other) {
        Object object = this.rootMonitor;
        synchronized (object) {
            this.dataHolder.addAll(other);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void putAll(UnmodifiableConfig other) {
        Object object = this.rootMonitor;
        synchronized (object) {
            this.dataHolder.putAll(other);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <T> T remove(String path) {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.remove(path);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAll(UnmodifiableConfig toRemove) {
        Object object = this.rootMonitor;
        synchronized (object) {
            this.dataHolder.removeAll(toRemove);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <T> T set(String path, Object value) {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.set(path, value);
        }
    }

    @Override
    public UnmodifiableCommentedConfig unmodifiable() {
        return this.dataHolder.unmodifiable();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void update(List<String> path, Object value) {
        Object object = this.rootMonitor;
        synchronized (object) {
            this.dataHolder.update(path, value);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <T> T apply(List<String> path) {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.apply(path);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <T> T get(List<String> path) {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.get(path);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isNull(List<String> path) {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.isNull(path);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Map<String, UnmodifiableCommentedConfig.CommentNode> getComments() {
        Object object = this.rootMonitor;
        synchronized (object) {
            return this.dataHolder.getComments();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void getComments(Map<String, UnmodifiableCommentedConfig.CommentNode> destination) {
        Object object = this.rootMonitor;
        synchronized (object) {
            this.dataHolder.getComments(destination);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void putAllComments(Map<String, UnmodifiableCommentedConfig.CommentNode> comments) {
        Object object = this.rootMonitor;
        synchronized (object) {
            this.dataHolder.putAllComments(comments);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void putAllComments(UnmodifiableCommentedConfig commentedConfig) {
        Object object = this.rootMonitor;
        synchronized (object) {
            this.dataHolder.putAllComments(commentedConfig);
        }
    }

    private static final class DataHolder
    extends AbstractCommentedConfig {
        private SynchronizedConfig syncConfig;
        private final ConfigFormat<?> format;

        DataHolder(SynchronizedConfig parent) {
            super(parent.dataHolder.mapCreator);
            this.format = parent.configFormat();
            this.syncConfig = parent;
        }

        DataHolder(SynchronizedConfig syncConfig, ConfigFormat<?> configFormat, Supplier<Map<String, Object>> mapCreator) {
            super(mapCreator);
            this.format = configFormat;
            this.syncConfig = syncConfig;
        }

        @Override
        public AbstractCommentedConfig clone() {
            throw new UnsupportedOperationException();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public SynchronizedConfig createSubConfig() {
            Object object = this.syncConfig.rootMonitor;
            synchronized (object) {
                return new SynchronizedConfig(this.format, this.mapCreator, this.syncConfig);
            }
        }

        @Override
        public ConfigFormat<?> configFormat() {
            return this.format;
        }
    }

    private static final class SynchronizedMap<K, V>
    implements Map<K, V> {
        private final Map<K, V> map;
        private final Object rootMonitor;

        SynchronizedMap(Map<K, V> map, Object monitor) {
            this.map = map;
            this.rootMonitor = monitor;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean equals(Object obj) {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.map.equals(obj);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int hashCode() {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.map.hashCode();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public String toString() {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.map.toString();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void clear() {
            Object object = this.rootMonitor;
            synchronized (object) {
                this.map.clear();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean containsKey(Object key) {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.map.containsKey(key);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean containsValue(Object value) {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.map.containsValue(value);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Set<Map.Entry<K, V>> entrySet() {
            Object object = this.rootMonitor;
            synchronized (object) {
                return new SynchronizedSet<Map.Entry<K, V>>(this.map.entrySet(), this.rootMonitor);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public V get(Object key) {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.map.get(key);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isEmpty() {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.map.isEmpty();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Set<K> keySet() {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.map.keySet();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public V put(K key, V value) {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.map.put(key, value);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void putAll(Map<? extends K, ? extends V> m) {
            Object object = this.rootMonitor;
            synchronized (object) {
                this.map.putAll(m);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public V remove(Object key) {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.map.remove(key);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int size() {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.map.size();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Collection<V> values() {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.map.values();
            }
        }
    }

    private static final class SynchronizedSet<E>
    extends SynchronizedCollection<E>
    implements Set<E> {
        SynchronizedSet(Set<E> coll, Object rootMonitor) {
            super(coll, rootMonitor);
        }
    }

    private static final class SynchronizedIterator<E>
    implements Iterator<E> {
        private final Iterator<E> iter;
        private final Object rootMonitor;

        SynchronizedIterator(Iterator<E> iter, Object rootMonitor) {
            this.iter = iter;
            this.rootMonitor = rootMonitor;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Object object = this.rootMonitor;
            synchronized (object) {
                this.iter.forEachRemaining(action);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean hasNext() {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.iter.hasNext();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public E next() {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.iter.next();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void remove() {
            Object object = this.rootMonitor;
            synchronized (object) {
                this.iter.remove();
            }
        }
    }

    private static class SynchronizedCollection<E>
    implements Collection<E> {
        private final Collection<E> coll;
        private final Object rootMonitor;

        SynchronizedCollection(Collection<E> coll, Object rootMonitor) {
            this.coll = coll;
            this.rootMonitor = rootMonitor;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean add(E e) {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.coll.add(e);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean addAll(Collection<? extends E> c) {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.coll.addAll(c);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void clear() {
            Object object = this.rootMonitor;
            synchronized (object) {
                this.coll.clear();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean contains(Object o) {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.coll.contains(o);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean containsAll(Collection<?> c) {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.coll.containsAll(c);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isEmpty() {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.coll.isEmpty();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Iterator<E> iterator() {
            Object object = this.rootMonitor;
            synchronized (object) {
                return new SynchronizedIterator<E>(this.coll.iterator(), this.rootMonitor);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean remove(Object o) {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.coll.remove(o);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean removeAll(Collection<?> c) {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.coll.removeAll(c);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean retainAll(Collection<?> c) {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.coll.retainAll(c);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int size() {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.coll.size();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Object[] toArray() {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.coll.toArray();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public <T> T[] toArray(T[] a) {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.coll.toArray(a);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean removeIf(Predicate<? super E> filter) {
            Object object = this.rootMonitor;
            synchronized (object) {
                return this.coll.removeIf(filter);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void forEach(Consumer<? super E> action) {
            Object object = this.rootMonitor;
            synchronized (object) {
                this.coll.forEach(action);
            }
        }
    }
}


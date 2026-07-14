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
import com.electronwill.nightconfig.core.IncompatibleIntermediaryLevelException;
import com.electronwill.nightconfig.core.NullObject;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.concurrent.ConcurrentCommentedConfig;
import com.electronwill.nightconfig.core.utils.TransformingSet;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.StampedLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public final class StampedConfig
implements ConcurrentCommentedConfig {
    private final ConfigFormat<?> configFormat;
    private final Supplier<Map<String, Object>> mapSupplier;
    private Map<String, Object> values;
    private Map<String, String> comments;
    private final StampedLock lock = new StampedLock();
    private final ThreadLocal<ThreadConfigState> state = ThreadLocal.withInitial(() -> ThreadConfigState.NORMAL);

    public StampedConfig() {
        this(InMemoryCommentedFormat.defaultInstance(), Config.getDefaultMapCreator(false));
    }

    public StampedConfig(ConfigFormat<?> configFormat, Supplier<Map<String, Object>> mapSupplier) {
        this.configFormat = configFormat;
        this.mapSupplier = mapSupplier;
        this.values = mapSupplier.get();
        this.comments = mapSupplier.get();
    }

    StampedConfig(ConfigFormat<?> configFormat, Supplier<Map<String, Object>> mapSupplier, Map<String, Object> values, Map<String, String> comments) {
        this.configFormat = configFormat;
        this.mapSupplier = mapSupplier;
        this.values = values;
        this.comments = comments;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void replaceContentBy(StampedConfig newContent) {
        this.checkStateForNormalOp();
        long stamp = this.lock.writeLock();
        try {
            long otherVS = newContent.lock.writeLock();
            try {
                this.values = newContent.values;
                this.comments = newContent.comments;
                newContent.values = null;
                newContent.comments = null;
            }
            finally {
                newContent.lock.unlockWrite(otherVS);
            }
        }
        finally {
            this.lock.unlockWrite(stamp);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void replaceContentBy(Accumulator newContent) {
        this.checkStateForNormalOp();
        long stamp = this.lock.writeLock();
        try {
            newContent.prepareReplacement();
            this.values = newContent.values();
            this.comments = newContent.comments();
            newContent.invalidate();
        }
        finally {
            this.lock.unlockWrite(stamp);
        }
    }

    public Accumulator newAccumulator() {
        return new Accumulator(this.configFormat, this.mapSupplier);
    }

    public Accumulator newAccumulatorCopy() {
        Accumulator acc = (Accumulator)this.copyValueInAccumulator(this);
        return acc;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private Object copyValueInAccumulator(Object v) {
        if (v instanceof StampedConfig) {
            StampedConfig stamped = (StampedConfig)v;
            stamped.checkStateForNormalOp();
            long stamp = stamped.lock.readLock();
            try {
                Map<String, Object> valuesCopy = this.mapSupplier.get();
                valuesCopy.putAll(stamped.values);
                valuesCopy.replaceAll((k, w) -> this.copyValueInAccumulator(w));
                Map<String, Object> commentsCopy = this.mapSupplier.get();
                commentsCopy.putAll(stamped.comments);
                Accumulator accumulator = new Accumulator(valuesCopy, commentsCopy, this.mapSupplier, this.configFormat);
                return accumulator;
            }
            finally {
                stamped.lock.unlockRead(stamp);
            }
        }
        if (v instanceof List) {
            List l = (List)v;
            ArrayList copy = new ArrayList(l);
            copy.replaceAll(elem -> this.copyValueInAccumulator(elem));
            return copy;
        }
        return v;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private <V> V mapLockGet(Map<String, V> map, StampedLock lock, String key) {
        long stamp = lock.tryOptimisticRead();
        V value = map.get(key);
        if (!lock.validate(stamp)) {
            this.checkStateForNormalOp();
            stamp = lock.readLock();
            try {
                value = map.get(key);
            }
            finally {
                lock.unlockRead(stamp);
            }
        } else assert (this.state.get() == ThreadConfigState.NORMAL) : "invalid state " + String.valueOf((Object)this.state.get()) + " are you using bulk operations / iterators properly?";
        return value;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private <V> boolean mapLockContains(Map<String, V> map, StampedLock lock, String key) {
        long stamp = lock.tryOptimisticRead();
        boolean contains = map.containsKey(key);
        if (!lock.validate(stamp)) {
            this.checkStateForNormalOp();
            stamp = lock.readLock();
            try {
                contains = map.containsKey(key);
            }
            finally {
                lock.unlockRead(stamp);
            }
        }
        assert (this.state.get() == ThreadConfigState.NORMAL) : "invalid state " + String.valueOf((Object)this.state.get()) + " are you using bulk operations / iterators properly?";
        return contains;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private <V> V mapLockRemove(Map<String, V> map, StampedLock lock, String key) {
        long stamp = lock.tryWriteLock();
        if (stamp == 0L) {
            this.checkStateForNormalOp();
            stamp = lock.writeLock();
        }
        assert (this.state.get() == ThreadConfigState.NORMAL) : "invalid state " + String.valueOf((Object)this.state.get()) + " are you using bulk operations / iterators properly?";
        try {
            V v = map.remove(key);
            return v;
        }
        finally {
            lock.unlockWrite(stamp);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private <V> V mapLockPut(Map<String, V> map, StampedLock lock, String key, V value) {
        long stamp = lock.tryWriteLock();
        if (stamp == 0L) {
            this.checkStateForNormalOp();
            stamp = lock.writeLock();
        }
        assert (this.state.get() == ThreadConfigState.NORMAL) : "invalid state " + String.valueOf((Object)this.state.get()) + " are you using bulk operations / iterators properly?";
        try {
            V v = map.put(key, value);
            return v;
        }
        finally {
            lock.unlockWrite(stamp);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private <V> V mapLockPutIfAbsent(Map<String, V> map, StampedLock lock, String key, V value) {
        long stamp = lock.tryWriteLock();
        if (stamp == 0L) {
            this.checkStateForNormalOp();
            stamp = lock.writeLock();
        }
        assert (this.state.get() == ThreadConfigState.NORMAL) : "invalid state " + String.valueOf((Object)this.state.get()) + " are you using bulk operations / iterators properly?";
        try {
            V v = map.putIfAbsent(key, value);
            return v;
        }
        finally {
            lock.unlockWrite(stamp);
        }
    }

    private StampedConfig getExistingConfig(List<String> configPath, boolean failIfIncompatibleLevel) {
        StampedConfig current = this;
        for (String key : configPath) {
            Object level = this.mapLockGet(current.values, current.lock, key);
            if (level == null) {
                return null;
            }
            if (level instanceof StampedConfig) {
                current = (StampedConfig)level;
                continue;
            }
            if (failIfIncompatibleLevel) {
                throw new IncompatibleIntermediaryLevelException("Cannot get entry with parent path " + String.valueOf(configPath) + " because of an incompatible intermediary value of type: " + String.valueOf(level.getClass()));
            }
            return null;
        }
        return current;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private StampedConfig getOrCreateConfig(List<String> configPath) {
        assert (this.state.get() == ThreadConfigState.NORMAL) : "invalid state " + String.valueOf((Object)this.state.get()) + " are you using bulk operations / iterators properly?";
        StampedConfig current = this;
        for (String key : configPath) {
            StampedLock lock = current.lock;
            Map<String, Object> values = current.values;
            long stamp = lock.tryOptimisticRead();
            boolean isLock = false;
            try {
                Object level = values.get(key);
                if (!lock.validate(stamp)) {
                    this.checkStateForNormalOp();
                    stamp = lock.readLock();
                    isLock = true;
                    level = values.get(key);
                }
                if (level == null) {
                    if ((stamp = lock.tryConvertToWriteLock(stamp)) == 0L) {
                        this.checkStateForNormalOp();
                        stamp = lock.writeLock();
                    }
                    isLock = true;
                    current = this.createSubConfig();
                    values.put(key, current);
                    continue;
                }
                if (level instanceof StampedConfig) {
                    current = (StampedConfig)level;
                    continue;
                }
                throw new IncompatibleIntermediaryLevelException("Cannot get/create entry with parent path " + String.valueOf(configPath) + " because of an incompatible intermediary value of type: " + String.valueOf(level.getClass()));
            }
            finally {
                if (!isLock) continue;
                lock.unlock(stamp);
            }
        }
        return current;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int size() {
        long stamp = this.lock.tryOptimisticRead();
        int size = this.values.size();
        if (!this.lock.validate(stamp)) {
            this.checkStateForNormalOp();
            stamp = this.lock.readLock();
            try {
                size = this.values.size();
            }
            finally {
                this.lock.unlockRead(stamp);
            }
        }
        return size;
    }

    @Override
    public StampedConfig createSubConfig() {
        return new StampedConfig(this.configFormat, this.mapSupplier);
    }

    @Override
    public ConfigFormat<?> configFormat() {
        return this.configFormat;
    }

    @Override
    public Map<String, Object> valueMap() {
        return new ValueMap(this);
    }

    @Override
    public void clear() {
        long stamp = this.lock.tryWriteLock();
        if (stamp == 0L) {
            this.checkStateForNormalOp();
            stamp = this.lock.writeLock();
        }
        try {
            this.values.clear();
        }
        finally {
            this.lock.unlockWrite(stamp);
        }
    }

    @Override
    public <T> T getRaw(List<String> path) {
        switch (path.size()) {
            case 0: {
                throw new IllegalArgumentException("empty entry path");
            }
            case 1: {
                return (T)this.mapLockGet(this.values, this.lock, path.get(0));
            }
        }
        int lastIndex = path.size() - 1;
        List<String> parentPath = path.subList(0, lastIndex);
        StampedConfig parent = this.getExistingConfig(parentPath, false);
        if (parent == null) {
            return null;
        }
        return (T)this.mapLockGet(parent.values, parent.lock, path.get(lastIndex));
    }

    @Override
    public boolean contains(List<String> path) {
        switch (path.size()) {
            case 0: {
                throw new IllegalArgumentException("empty entry path");
            }
            case 1: {
                return this.mapLockContains(this.values, this.lock, path.get(0));
            }
        }
        int lastIndex = path.size() - 1;
        List<String> parentPath = path.subList(0, lastIndex);
        StampedConfig parent = this.getExistingConfig(parentPath, false);
        return parent != null && this.mapLockContains(parent.values, parent.lock, path.get(lastIndex));
    }

    @Override
    public boolean add(List<String> path, Object value) {
        Object nnValue = value == null ? NullObject.NULL_OBJECT : value;
        switch (path.size()) {
            case 0: {
                throw new IllegalArgumentException("empty entry path");
            }
            case 1: {
                return this.mapLockPutIfAbsent(this.values, this.lock, path.get(0), nnValue) == null;
            }
        }
        int lastIndex = path.size() - 1;
        List<String> parentPath = path.subList(0, lastIndex);
        StampedConfig parent = this.getOrCreateConfig(parentPath);
        Object prev = this.mapLockPutIfAbsent(parent.values, parent.lock, path.get(lastIndex), nnValue);
        return prev == null;
    }

    @Override
    public <T> T remove(List<String> path) {
        switch (path.size()) {
            case 0: {
                throw new IllegalArgumentException("empty entry path");
            }
            case 1: {
                return (T)this.mapLockRemove(this.values, this.lock, path.get(0));
            }
        }
        int lastIndex = path.size() - 1;
        List<String> parentPath = path.subList(0, lastIndex);
        StampedConfig parent = this.getExistingConfig(parentPath, false);
        if (parent == null) {
            return null;
        }
        return (T)this.mapLockRemove(parent.values, parent.lock, path.get(lastIndex));
    }

    @Override
    public <T> T set(List<String> path, Object value) {
        Object nnValue = value == null ? NullObject.NULL_OBJECT : value;
        switch (path.size()) {
            case 0: {
                throw new IllegalArgumentException("empty entry path");
            }
            case 1: {
                return (T)this.mapLockPut(this.values, this.lock, path.get(0), nnValue);
            }
        }
        int lastIndex = path.size() - 1;
        List<String> parentPath = path.subList(0, lastIndex);
        StampedConfig parent = this.getOrCreateConfig(parentPath);
        return (T)this.mapLockPut(parent.values, parent.lock, path.get(lastIndex), nnValue);
    }

    private void convertSubConfigs(Config c) {
        if (c instanceof AbstractConfig) {
            AbstractConfig conf = (AbstractConfig)c;
            try {
                conf.valueMap().replaceAll((k, v) -> this.convertValue(v));
            }
            catch (UnsupportedOperationException unsupportedOperationException) {
                conf.entrySet().forEach(entry -> entry.setValue(this.convertValue(entry.getRawValue())));
            }
        } else {
            for (Config.Entry entry2 : c.entrySet()) {
                Object converted;
                Object value = entry2.getRawValue();
                if (value == (converted = this.convertValue(value))) continue;
                entry2.setValue(converted);
            }
        }
    }

    private Object convertValue(Object v) {
        if (v instanceof StampedConfig) {
            return v;
        }
        if (v instanceof Config) {
            Config c = (Config)v;
            StampedConfig converted = this.createSubConfig();
            this.convertSubConfigs(c);
            converted.putAll(c);
            if (c instanceof CommentedConfig) {
                converted.putAllComments((CommentedConfig)c);
            }
            return converted;
        }
        if (v instanceof List) {
            List l = (List)v;
            l.replaceAll(elem -> this.convertValue(elem));
            return l;
        }
        return v;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void putAll(UnmodifiableConfig other) {
        long stamp = this.lock.tryWriteLock();
        if (stamp == 0L) {
            this.checkStateForNormalOp();
            stamp = this.lock.writeLock();
        }
        try {
            this.unsafePutAll(other);
        }
        finally {
            this.lock.unlockWrite(stamp);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void unsafePutAll(UnmodifiableConfig other) {
        if (other == this) {
            throw new IllegalArgumentException("I cannot putAll() into myself.");
        }
        if (other instanceof StampedConfig) {
            StampedConfig stamped = (StampedConfig)other;
            long stamp = stamped.lock.tryReadLock();
            if (stamp == 0L) {
                stamped.checkStateForNormalOp();
                stamp = stamped.lock.readLock();
            }
            try {
                this.values.putAll(stamped.values);
            }
            finally {
                stamped.lock.unlockRead(stamp);
            }
        } else {
            this.convertSubConfigs((Config)other);
            other.entrySet().forEach(entry -> this.values.put(entry.getKey(), entry.getRawValue()));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void unsafeRemoveAll(UnmodifiableConfig other) {
        if (other == this) {
            throw new IllegalArgumentException("I cannot removeAll() from myself.");
        }
        if (other instanceof StampedConfig) {
            StampedConfig stamped = (StampedConfig)other;
            long stamp = stamped.lock.tryReadLock();
            if (stamp == 0L) {
                stamped.checkStateForNormalOp();
                stamp = stamped.lock.readLock();
            }
            try {
                this.values.keySet().removeAll(stamped.values.keySet());
            }
            finally {
                stamped.lock.unlockRead(stamp);
            }
        }
        try {
            Set<String> values = other.valueMap().keySet();
            this.values.keySet().removeAll(values);
        }
        catch (UnsupportedOperationException ex) {
            other.entrySet().forEach(entry -> this.values.remove(entry.getKey()));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAll(UnmodifiableConfig other) {
        long stamp = this.lock.tryWriteLock();
        if (stamp == 0L) {
            this.checkStateForNormalOp();
            stamp = this.lock.writeLock();
        }
        try {
            this.unsafeRemoveAll(other);
        }
        finally {
            this.lock.unlockWrite(stamp);
        }
    }

    @Override
    public void clearComments() {
        this.checkStateForNormalOp();
        this.bulkCommentedUpdate((? super CommentedConfig view) -> view.clearComments());
    }

    @Override
    public String removeComment(List<String> path) {
        switch (path.size()) {
            case 0: {
                throw new IllegalArgumentException("empty entry path");
            }
            case 1: {
                return this.mapLockRemove(this.comments, this.lock, path.get(0));
            }
        }
        int lastIndex = path.size() - 1;
        List<String> parentPath = path.subList(0, lastIndex);
        StampedConfig parent = this.getExistingConfig(parentPath, false);
        if (parent == null) {
            return null;
        }
        return this.mapLockRemove(parent.comments, parent.lock, path.get(lastIndex));
    }

    @Override
    public String setComment(List<String> path, String value) {
        switch (path.size()) {
            case 0: {
                throw new IllegalArgumentException("empty entry path");
            }
            case 1: {
                return this.mapLockPut(this.comments, this.lock, path.get(0), value);
            }
        }
        int lastIndex = path.size() - 1;
        List<String> parentPath = path.subList(0, lastIndex);
        StampedConfig parent = this.getOrCreateConfig(parentPath);
        return this.mapLockPut(parent.comments, parent.lock, path.get(lastIndex), value);
    }

    @Override
    public boolean containsComment(List<String> path) {
        switch (path.size()) {
            case 0: {
                throw new IllegalArgumentException("empty entry path");
            }
            case 1: {
                return this.mapLockContains(this.comments, this.lock, path.get(0));
            }
        }
        int lastIndex = path.size() - 1;
        List<String> parentPath = path.subList(0, lastIndex);
        StampedConfig parent = this.getExistingConfig(parentPath, false);
        return parent != null && this.mapLockContains(parent.comments, parent.lock, path.get(lastIndex));
    }

    @Override
    public String getComment(List<String> path) {
        switch (path.size()) {
            case 0: {
                throw new IllegalArgumentException("empty entry path");
            }
            case 1: {
                return this.mapLockGet(this.comments, this.lock, path.get(0));
            }
        }
        int lastIndex = path.size() - 1;
        List<String> parentPath = path.subList(0, lastIndex);
        StampedConfig parent = this.getExistingConfig(parentPath, false);
        if (parent == null) {
            return null;
        }
        return this.mapLockGet(parent.comments, parent.lock, path.get(lastIndex));
    }

    @Override
    public Map<String, String> commentMap() {
        throw new UnsupportedOperationException("StampedConfig does not support commentMap() yet, please use entrySet() instead.");
    }

    @Override
    public void putAllComments(UnmodifiableCommentedConfig other) {
        if (other == this) {
            throw new IllegalArgumentException("I cannot putAllComments() into myself.");
        }
        this.bulkUpdate((? super Config view) -> {
            if (other instanceof StampedConfig) {
                StampedConfig otherStamped = (StampedConfig)other;
                long otherStamp = otherStamped.lock.tryReadLock();
                if (otherStamp == 0L) {
                    otherStamped.checkStateForNormalOp();
                    otherStamp = otherStamped.lock.readLock();
                }
                try {
                    this.comments.putAll(otherStamped.comments);
                    for (CommentedConfig.Entry entry2 : otherStamped.entrySet()) {
                        Object config;
                        Object value = entry2.getRawValue();
                        if (!(value instanceof StampedConfig) || !((config = this.values.get(entry2.getKey())) instanceof StampedConfig) || config == value) continue;
                        ((StampedConfig)config).putAllComments((StampedConfig)value);
                    }
                }
                finally {
                    otherStamped.lock.unlockRead(otherStamp);
                }
            }
            try {
                Map<String, String> comments = other.commentMap();
                this.comments.putAll(comments);
                for (UnmodifiableCommentedConfig.Entry entry3 : other.entrySet()) {
                    Object object;
                    Object value = entry3.getRawValue();
                    if (!(value instanceof UnmodifiableCommentedConfig) || !((object = this.values.get(entry3.getKey())) instanceof StampedConfig) || object == value) continue;
                    ((StampedConfig)object).putAllComments((UnmodifiableCommentedConfig)value);
                }
            }
            catch (UnsupportedOperationException ex) {
                other.entrySet().forEach(entry -> {
                    Object config;
                    this.comments.put(entry.getKey(), entry.getComment());
                    Object value = entry.getRawValue();
                    if (value instanceof UnmodifiableCommentedConfig && (config = this.values.get(entry.getKey())) instanceof StampedConfig) {
                        ((StampedConfig)config).putAllComments((UnmodifiableCommentedConfig)value);
                    }
                });
            }
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void putAllComments(Map<String, UnmodifiableCommentedConfig.CommentNode> comments) {
        long stamp = this.lock.tryWriteLock();
        if (stamp == 0L) {
            this.checkStateForNormalOp();
            stamp = this.lock.writeLock();
        }
        try {
            comments.forEach((key, node) -> {
                Object config;
                this.comments.put((String)key, node.getComment());
                Map<String, UnmodifiableCommentedConfig.CommentNode> children = node.getChildren();
                if (children != null && (config = this.values.get(key)) instanceof StampedConfig) {
                    ((StampedConfig)config).putAllComments(children);
                }
            });
        }
        finally {
            this.lock.unlockWrite(stamp);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StampedConfig) {
            return this.bulkCommentedRead((? super UnmodifiableCommentedConfig view) -> ((StampedConfig)obj).bulkCommentedRead((? super UnmodifiableCommentedConfig objView) -> view.equals(objView)));
        }
        if (obj instanceof UnmodifiableConfig) {
            return this.bulkRead((? super UnmodifiableConfig view) -> view.equals(obj));
        }
        return false;
    }

    public String toString() {
        return this.bulkRead((? super UnmodifiableConfig view) -> {
            StringBuilder builder = new StringBuilder();
            builder.append("StampedConfig{");
            for (UnmodifiableConfig.Entry entry : view.entrySet()) {
                builder.append(entry.getKey());
                builder.append('=');
                builder.append(String.valueOf(entry.getRawValue()));
                builder.append(", ");
            }
            builder.append('}');
            return builder.toString();
        });
    }

    @Override
    public Set<? extends CommentedConfig.Entry> entrySet() {
        return new EntrySet();
    }

    private void checkStateForBulkOp() {
        switch (this.state.get().ordinal()) {
            case 1: {
                throw new IllegalStateException("StampedConfig.{bulkRead, bulkUpdate, bulkCommentedRead, bulkCommentedUpdate} cannot be nested.");
            }
            case 2: {
                throw new IllegalStateException("Entries provided by StampedConfig.entrySet() cannot be used during another operation on the config nor on its entrySet, for thread-safety reasons (and to avoid deadlocks).");
            }
            case 3: {
                throw new IllegalStateException("This StampedConfig has been given to otherConfig.replaceContentBy() and cannot be used anymore.");
            }
        }
    }

    private void checkStateForNormalOp() {
        switch (this.state.get().ordinal()) {
            case 1: {
                throw new IllegalStateException("StampedConfig cannot be used inside of bulk operations, you must use the argument provided to your function by bulk, for example: bulkUpdate(bulkedConf -> {/* use bulkedConf here*/}).");
            }
            case 2: {
                throw new IllegalStateException("Entries provided by StampedConfig.entrySet() cannot be used during another operation on the config nor on its entrySet, for thread-safety reasons (and to avoid deadlocks).");
            }
            case 3: {
                throw new IllegalStateException("This StampedConfig has been given to otherConfig.replaceContentBy() and cannot be used anymore.");
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <R> R bulkRead(Function<? super UnmodifiableConfig, R> action) {
        long stamp = this.lock.tryReadLock();
        if (stamp == 0L) {
            this.checkStateForBulkOp();
            stamp = this.lock.readLock();
        }
        try {
            this.checkStateForBulkOp();
        }
        catch (IllegalStateException ex) {
            this.lock.unlockRead(stamp);
            throw ex;
        }
        this.state.set(ThreadConfigState.IN_BULK_OP);
        ReadOnlyLockedView view = new ReadOnlyLockedView();
        try {
            R r = action.apply(view);
            return r;
        }
        finally {
            view.invalidate();
            this.state.set(ThreadConfigState.NORMAL);
            this.lock.unlockRead(stamp);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <R> R bulkUpdate(Function<? super Config, R> action) {
        long stamp = this.lock.tryWriteLock();
        if (stamp == 0L) {
            this.checkStateForBulkOp();
            stamp = this.lock.writeLock();
        }
        try {
            this.checkStateForBulkOp();
        }
        catch (IllegalStateException ex) {
            this.lock.unlockWrite(stamp);
            throw ex;
        }
        this.state.set(ThreadConfigState.IN_BULK_OP);
        WritableLockedView view = new WritableLockedView();
        try {
            R r = action.apply(view);
            return r;
        }
        finally {
            view.invalidate();
            this.state.set(ThreadConfigState.NORMAL);
            this.lock.unlockWrite(stamp);
        }
    }

    @Override
    public <R> R bulkCommentedRead(Function<? super UnmodifiableCommentedConfig, R> action) {
        return this.bulkRead(action);
    }

    @Override
    public <R> R bulkCommentedUpdate(Function<? super CommentedConfig, R> action) {
        return this.bulkUpdate(action);
    }

    public static final class Accumulator
    extends AbstractCommentedConfig {
        private final StampedConfig mirror;
        private boolean valid = true;

        Accumulator(Map<String, Object> values, Map<String, String> comments, Supplier<Map<String, Object>> mapSupplier, ConfigFormat<?> configFormat) {
            super(values, comments);
            this.mirror = new StampedConfig(configFormat, mapSupplier, values, comments);
        }

        Accumulator(ConfigFormat<?> configFormat, Supplier<Map<String, Object>> mapSupplier) {
            super(mapSupplier);
            this.mirror = new StampedConfig(configFormat, mapSupplier, this.map, this.commentMap);
        }

        private void checkValid() {
            if (!this.valid) {
                throw new IllegalStateException("This StampedConfig.Accumulator is no longer valid after a call to replaceContentBy().");
            }
        }

        void invalidate() {
            this.valid = false;
        }

        Map<String, Object> values() {
            return this.map;
        }

        Map<String, String> comments() {
            return this.commentMap;
        }

        Supplier<Map<String, Object>> mapSupplier() {
            return this.mapCreator;
        }

        void prepareReplacement() {
            this.checkValid();
            this.map.replaceAll((k, v) -> this.replaceValue(v));
        }

        private Object replaceValue(Object v) {
            if (v instanceof Accumulator) {
                Accumulator acc = (Accumulator)v;
                acc.prepareReplacement();
                return acc.mirror;
            }
            if (v instanceof UnmodifiableConfig) {
                throw new IllegalStateException("Invalid sub-configuration of type " + v.getClass().getSimpleName() + " in the Accumulator. Sub-configurations must always be created with createSubConfig().");
            }
            if (v instanceof List) {
                List l = (List)v;
                ArrayList newList = new ArrayList(l);
                newList.replaceAll(elem -> this.replaceValue(elem));
                return newList;
            }
            return v;
        }

        @Override
        public AbstractCommentedConfig clone() {
            Accumulator copy = new Accumulator(this.configFormat(), (Supplier<Map<String, Object>>)this.mapCreator);
            copy.map.putAll(this.map);
            copy.commentMap.putAll(this.commentMap);
            return copy;
        }

        @Override
        public CommentedConfig createSubConfig() {
            return new Accumulator(this.configFormat(), (Supplier<Map<String, Object>>)this.mapCreator);
        }

        @Override
        public ConfigFormat<?> configFormat() {
            this.checkValid();
            return this.mirror.configFormat();
        }
    }

    private static enum ThreadConfigState {
        NORMAL,
        IN_BULK_OP,
        IN_ITER_OP,
        CONSUMED;

    }

    private static final class ValueMap
    implements Map<String, Object> {
        private final CommentedConfig config;

        ValueMap(CommentedConfig config) {
            this.config = config;
        }

        @Override
        public void clear() {
            this.config.clear();
        }

        @Override
        public boolean containsKey(Object key) {
            if (!(key instanceof String)) {
                return false;
            }
            return this.config.contains(Collections.singletonList((String)key));
        }

        @Override
        public boolean containsValue(Object value) {
            return this.config.entrySet().stream().anyMatch(e -> Objects.equals(value, e.getRawValue()));
        }

        @Override
        public Set<Map.Entry<String, Object>> entrySet() {
            return new Set<Map.Entry<String, Object>>(){

                @Override
                public int size() {
                    return config.size();
                }

                @Override
                public boolean isEmpty() {
                    return config.isEmpty();
                }

                @Override
                public boolean contains(Object o) {
                    if (!(o instanceof Map.Entry)) {
                        return false;
                    }
                    final Map.Entry search = (Map.Entry)o;
                    if (!(search.getKey() instanceof String)) {
                        return false;
                    }
                    return config.entrySet().contains(new UnmodifiableConfig.Entry(){

                        @Override
                        public String getKey() {
                            return (String)search.getKey();
                        }

                        @Override
                        public <T> T getRawValue() {
                            return (T)search.getValue();
                        }
                    });
                }

                @Override
                public Iterator<Map.Entry<String, Object>> iterator() {
                    final Iterator<? extends CommentedConfig.Entry> it = config.entrySet().iterator();
                    return new Iterator<Map.Entry<String, Object>>(){

                        @Override
                        public boolean hasNext() {
                            return it.hasNext();
                        }

                        @Override
                        public Map.Entry<String, Object> next() {
                            final Config.Entry entry = (Config.Entry)it.next();
                            return new Map.Entry<String, Object>(){

                                @Override
                                public String getKey() {
                                    return entry.getKey();
                                }

                                @Override
                                public Object getValue() {
                                    return entry.getRawValue();
                                }

                                @Override
                                public Object setValue(Object value) {
                                    return entry.setValue(value);
                                }
                            };
                        }

                        @Override
                        public void remove() {
                            it.remove();
                        }
                    };
                }

                @Override
                public Object[] toArray() {
                    throw new UnsupportedOperationException("Unimplemented method 'toArray'");
                }

                @Override
                public <T> T[] toArray(T[] a) {
                    throw new UnsupportedOperationException("Unimplemented method 'toArray'");
                }

                @Override
                public boolean add(Map.Entry<String, Object> e) {
                    return config.add(Collections.singletonList(e.getKey()), e.getValue());
                }

                @Override
                public boolean remove(Object o) {
                    if (!(o instanceof Map.Entry)) {
                        return false;
                    }
                    Map.Entry entry = (Map.Entry)o;
                    Object key = entry.getKey();
                    if (!(key instanceof String)) {
                        return false;
                    }
                    return config.remove(Collections.singletonList((String)key)) != null;
                }

                @Override
                public boolean containsAll(Collection<?> c) {
                    for (Object o : c) {
                        if (this.contains(o)) continue;
                        return false;
                    }
                    return true;
                }

                @Override
                public boolean addAll(Collection<? extends Map.Entry<String, Object>> c) {
                    boolean changed = false;
                    for (Map.Entry<String, Object> entry : c) {
                        changed |= this.add(entry);
                    }
                    return changed;
                }

                @Override
                public boolean retainAll(Collection<?> c) {
                    boolean changed = false;
                    Iterator<Map.Entry<String, Object>> it = this.iterator();
                    while (it.hasNext()) {
                        if (c.contains(it.next())) continue;
                        it.remove();
                        changed = true;
                    }
                    return changed;
                }

                @Override
                public boolean removeAll(Collection<?> c) {
                    boolean changed = false;
                    for (Object o : c) {
                        changed |= this.remove(o);
                    }
                    return changed;
                }

                @Override
                public void clear() {
                    config.clear();
                }
            };
        }

        @Override
        public Object get(Object key) {
            if (!(key instanceof String)) {
                return false;
            }
            return this.config.get(Collections.singletonList((String)key));
        }

        @Override
        public boolean isEmpty() {
            return this.config.isEmpty();
        }

        @Override
        public Set<String> keySet() {
            return this.config.entrySet().stream().map(e -> e.getKey()).collect(Collectors.toSet());
        }

        @Override
        public Collection<Object> values() {
            return this.config.entrySet().stream().map(e -> e.getRawValue()).collect(Collectors.toList());
        }

        @Override
        public Object put(String key, Object value) {
            return this.config.set(Collections.singletonList(key), value);
        }

        @Override
        public void putAll(Map<? extends String, ? extends Object> m) {
            for (Map.Entry<? extends String, ? extends Object> entry : m.entrySet()) {
                this.config.set(Collections.singletonList(entry.getKey()), entry.getValue());
            }
        }

        @Override
        public Object remove(Object key) {
            if (!(key instanceof String)) {
                return null;
            }
            return this.config.remove(Collections.singletonList((String)key));
        }

        @Override
        public int size() {
            return this.config.size();
        }
    }

    private class EntrySet
    extends AbstractCollection<LazyEntry>
    implements Set<LazyEntry> {
        private EntrySet() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Iterator<LazyEntry> iterator() {
            StampedConfig.this.checkStateForNormalOp();
            long stamp = StampedConfig.this.lock.readLock();
            try {
                StampedConfig.this.state.set(ThreadConfigState.IN_ITER_OP);
                LazyEntry[] snapshot = new LazyEntry[StampedConfig.this.values.size()];
                int i = 0;
                for (Map.Entry<String, Object> entry : StampedConfig.this.values.entrySet()) {
                    snapshot[i++] = new LockingLazyEntry(entry.getKey(), this);
                }
                EntryIterator entryIterator = new EntryIterator(snapshot);
                return entryIterator;
            }
            finally {
                StampedConfig.this.state.set(ThreadConfigState.NORMAL);
                StampedConfig.this.lock.unlockRead(stamp);
            }
        }

        @Override
        public int size() {
            return StampedConfig.this.size();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void forEach(Consumer<? super LazyEntry> action) {
            long stamp = StampedConfig.this.lock.tryWriteLock();
            if (stamp == 0L) {
                StampedConfig.this.checkStateForNormalOp();
                stamp = StampedConfig.this.lock.writeLock();
            }
            try {
                StampedConfig.this.state.set(ThreadConfigState.IN_ITER_OP);
                StampedConfig.this.values.forEach((? super K key, ? super V value) -> {
                    InLockLazyEntry entry = new InLockLazyEntry((String)key);
                    try {
                        action.accept(entry);
                    }
                    finally {
                        entry.invalidate();
                    }
                });
            }
            finally {
                StampedConfig.this.state.set(ThreadConfigState.NORMAL);
                StampedConfig.this.lock.unlockWrite(stamp);
            }
        }

        @Override
        public boolean add(LazyEntry e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            StampedConfig.this.bulkCommentedUpdate(view -> {
                view.clear();
                view.clearComments();
            });
        }

        @Override
        public boolean contains(Object o) {
            if (o instanceof UnmodifiableConfig.Entry) {
                UnmodifiableConfig.Entry entry = (UnmodifiableConfig.Entry)o;
                Object entryValue = entry.getRawValue();
                Object value = StampedConfig.this.getRaw(Collections.singletonList(entry.getKey()));
                return entryValue == null ? value == null : entryValue.equals(value);
            }
            return false;
        }

        @Override
        public boolean isEmpty() {
            return StampedConfig.this.isEmpty();
        }

        @Override
        public boolean remove(Object o) {
            throw new UnsupportedOperationException();
        }
    }

    private class ReadOnlyLockedView
    implements UnmodifiableCommentedConfig {
        private final AtomicBoolean valid;

        ReadOnlyLockedView() {
            this(new AtomicBoolean(true));
        }

        ReadOnlyLockedView(AtomicBoolean valid) {
            this.valid = valid;
        }

        void invalidate() {
            this.valid.set(false);
        }

        protected void checkValid() {
            if (!this.valid.get()) {
                throw new IllegalStateException("View provided by bulk operations are only valid in the scope of the bulkRead or bulkWrite method.To use the config elsewhere, use the actual config variable (not the one provided to your bulk action).");
            }
        }

        @Override
        public Map<String, String> commentMap() {
            throw new UnsupportedOperationException("The view provided by bulk operations on StampedConfig does not support commentMap(), please use entrySet() instead.");
        }

        @Override
        public Set<? extends UnmodifiableCommentedConfig.Entry> entrySet() {
            this.checkValid();
            TransformingSet<Map.Entry, Entry> set = new TransformingSet<Map.Entry, Entry>(StampedConfig.this.values.entrySet(), r -> {
                this.checkValid();
                return new Entry((Map.Entry<String, Object>)r);
            }, w -> {
                this.checkValid();
                return null;
            }, s -> {
                this.checkValid();
                return s instanceof Map.Entry ? new Entry((Map.Entry)s) : s;
            });
            return Collections.unmodifiableSet(set);
        }

        @Override
        public boolean containsComment(List<String> path) {
            this.checkValid();
            switch (path.size()) {
                case 0: {
                    throw new IllegalArgumentException("empty entry path");
                }
                case 1: {
                    String key = path.get(0);
                    return StampedConfig.this.comments.containsKey(key);
                }
            }
            Object maybeParent = StampedConfig.this.values.get(path.get(0));
            if (maybeParent instanceof StampedConfig) {
                StampedConfig parent = (StampedConfig)maybeParent;
                return parent.containsComment(path.subList(1, path.size()));
            }
            return false;
        }

        @Override
        public String getComment(List<String> path) {
            this.checkValid();
            switch (path.size()) {
                case 0: {
                    throw new IllegalArgumentException("empty entry path");
                }
                case 1: {
                    String key = path.get(0);
                    return StampedConfig.this.comments.get(key);
                }
            }
            Object maybeParent = StampedConfig.this.values.get(path.get(0));
            if (maybeParent instanceof StampedConfig) {
                StampedConfig parent = (StampedConfig)maybeParent;
                return parent.getComment(path.subList(1, path.size()));
            }
            return null;
        }

        @Override
        public ConfigFormat<?> configFormat() {
            this.checkValid();
            return StampedConfig.this.configFormat;
        }

        @Override
        public boolean contains(List<String> path) {
            this.checkValid();
            switch (path.size()) {
                case 0: {
                    throw new IllegalArgumentException("empty entry path");
                }
                case 1: {
                    String key = path.get(0);
                    return StampedConfig.this.values.containsKey(key);
                }
            }
            Object maybeParent = StampedConfig.this.values.get(path.get(0));
            if (maybeParent instanceof StampedConfig) {
                StampedConfig parent = (StampedConfig)maybeParent;
                return parent.contains(path.subList(1, path.size()));
            }
            return false;
        }

        @Override
        public <T> T getRaw(List<String> path) {
            this.checkValid();
            switch (path.size()) {
                case 0: {
                    throw new IllegalArgumentException("empty entry path");
                }
                case 1: {
                    String key = path.get(0);
                    return (T)StampedConfig.this.values.get(key);
                }
            }
            Object maybeParent = StampedConfig.this.values.get(path.get(0));
            if (maybeParent instanceof StampedConfig) {
                StampedConfig parent = (StampedConfig)maybeParent;
                return parent.getRaw(path.subList(1, path.size()));
            }
            return null;
        }

        @Override
        public int size() {
            this.checkValid();
            return StampedConfig.this.values.size();
        }

        @Override
        public Map<String, Object> valueMap() {
            WritableLockedView writable = new WritableLockedView(this.valid);
            return Collections.unmodifiableMap(writable.valueMap());
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("StampedConfig#LockedView{");
            for (UnmodifiableCommentedConfig.Entry entry : this.entrySet()) {
                builder.append(entry.getKey());
                builder.append('=');
                builder.append(String.valueOf(entry.getRawValue()));
                builder.append(", ");
            }
            builder.append("}");
            return builder.toString();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof UnmodifiableConfig)) {
                return false;
            }
            UnmodifiableConfig conf = (UnmodifiableConfig)obj;
            if (conf.size() != this.size()) {
                return false;
            }
            for (UnmodifiableConfig.Entry entry : this.entrySet()) {
                Object value = entry.getValue();
                Object otherEntry = conf.get(Collections.singletonList(entry.getKey()));
                if (!(value == null ? otherEntry != null : !value.equals(otherEntry))) continue;
                return false;
            }
            return true;
        }

        protected class Entry
        implements UnmodifiableCommentedConfig.Entry {
            protected final Map.Entry<String, Object> mapEntry;

            Entry(Map.Entry<String, Object> entry) {
                this.mapEntry = entry;
                ReadOnlyLockedView.this.checkValid();
            }

            @Override
            public String getComment() {
                ReadOnlyLockedView.this.checkValid();
                return StampedConfig.this.comments.get(this.mapEntry.getKey());
            }

            @Override
            public String getKey() {
                ReadOnlyLockedView.this.checkValid();
                return this.mapEntry.getKey();
            }

            @Override
            public <T> T getRawValue() {
                ReadOnlyLockedView.this.checkValid();
                return (T)this.mapEntry.getValue();
            }
        }
    }

    private final class WritableLockedView
    extends ReadOnlyLockedView
    implements CommentedConfig {
        WritableLockedView() {
        }

        WritableLockedView(AtomicBoolean valid) {
            super(valid);
        }

        @Override
        public void clear() {
            this.checkValid();
            StampedConfig.this.values.clear();
        }

        @Override
        public void removeAll(UnmodifiableConfig config) {
            this.checkValid();
            StampedConfig.this.unsafeRemoveAll(config);
        }

        @Override
        public void putAll(UnmodifiableConfig other) {
            this.checkValid();
            StampedConfig.this.unsafePutAll(other);
        }

        @Override
        public void clearComments() {
            this.checkValid();
            StampedConfig.this.comments.clear();
            for (Object o : StampedConfig.this.values.values()) {
                if (!(o instanceof StampedConfig)) continue;
                ((StampedConfig)o).clearComments();
            }
        }

        @Override
        public StampedConfig createSubConfig() {
            this.checkValid();
            return new StampedConfig(StampedConfig.this.configFormat, StampedConfig.this.mapSupplier);
        }

        @Override
        public Map<String, Object> valueMap() {
            return new ValueMap(this);
        }

        @Override
        public Set<? extends CommentedConfig.Entry> entrySet() {
            this.checkValid();
            return new TransformingSet<Map.Entry, Entry>(StampedConfig.this.values.entrySet(), r -> {
                this.checkValid();
                return new Entry((Map.Entry<String, Object>)r);
            }, w -> {
                this.checkValid();
                return new Map.Entry<String, Object>(){

                    @Override
                    public String getKey() {
                        WritableLockedView.this.checkValid();
                        return w.getKey();
                    }

                    @Override
                    public Object getValue() {
                        WritableLockedView.this.checkValid();
                        return w.getRawValue();
                    }

                    @Override
                    public Object setValue(Object value) {
                        WritableLockedView.this.checkValid();
                        return w.setValue(value);
                    }
                };
            }, s -> {
                this.checkValid();
                return s instanceof Map.Entry ? new Entry((Map.Entry<String, Object>)((Map.Entry)s)) : s;
            });
        }

        @Override
        public <T> T remove(List<String> path) {
            this.checkValid();
            switch (path.size()) {
                case 0: {
                    throw new IllegalArgumentException("empty entry path");
                }
                case 1: {
                    String key = path.get(0);
                    return (T)StampedConfig.this.values.remove(key);
                }
            }
            int lastIndex = path.size() - 1;
            Object maybeParent = this.getRaw(path.subList(0, lastIndex));
            if (maybeParent instanceof StampedConfig) {
                StampedConfig parent = (StampedConfig)maybeParent;
                String key = path.get(lastIndex);
                return (T)parent.values.remove(key);
            }
            return null;
        }

        @Override
        public String removeComment(List<String> path) {
            this.checkValid();
            switch (path.size()) {
                case 0: {
                    throw new IllegalArgumentException("empty entry path");
                }
                case 1: {
                    String key = path.get(0);
                    return StampedConfig.this.comments.remove(key);
                }
            }
            int lastIndex = path.size() - 1;
            Object maybeParent = this.getRaw(path.subList(0, lastIndex));
            if (maybeParent instanceof StampedConfig) {
                StampedConfig parent = (StampedConfig)maybeParent;
                String key = path.get(lastIndex);
                return parent.comments.remove(key);
            }
            return null;
        }

        @Override
        public <T> T set(List<String> path, Object value) {
            this.checkValid();
            switch (path.size()) {
                case 0: {
                    throw new IllegalArgumentException("empty entry path");
                }
                case 1: {
                    String key = path.get(0);
                    Object nnValue = value == null ? NullObject.NULL_OBJECT : value;
                    return (T)StampedConfig.this.values.put(key, nnValue);
                }
            }
            String key = path.get(0);
            List<String> subPath = path.subList(1, path.size());
            Object currentParent = StampedConfig.this.values.get(key);
            if (currentParent == null) {
                StampedConfig subConfig = this.createSubConfig();
                StampedConfig.this.values.put(key, subConfig);
                return subConfig.set(subPath, value);
            }
            if (currentParent instanceof StampedConfig) {
                return ((StampedConfig)currentParent).set(subPath, value);
            }
            throw new IncompatibleIntermediaryLevelException("Cannot add an element to an intermediary value of type: " + String.valueOf(currentParent.getClass()));
        }

        @Override
        public String setComment(List<String> path, String value) {
            this.checkValid();
            switch (path.size()) {
                case 0: {
                    throw new IllegalArgumentException("empty entry path");
                }
                case 1: {
                    String key = path.get(0);
                    return StampedConfig.this.comments.put(key, value);
                }
            }
            String key = path.get(0);
            List<String> subPath = path.subList(1, path.size());
            Object currentParent = StampedConfig.this.values.get(key);
            if (currentParent == null) {
                StampedConfig subConfig = this.createSubConfig();
                StampedConfig.this.values.put(key, subConfig);
                return subConfig.setComment(subPath, value);
            }
            if (currentParent instanceof StampedConfig) {
                return ((StampedConfig)currentParent).setComment(subPath, value);
            }
            throw new IncompatibleIntermediaryLevelException("Cannot add a comment to an intermediary value of type: " + String.valueOf(currentParent.getClass()));
        }

        @Override
        public boolean add(List<String> path, Object value) {
            this.checkValid();
            switch (path.size()) {
                case 0: {
                    throw new IllegalArgumentException("empty entry path");
                }
                case 1: {
                    String key = path.get(0);
                    Object nnValue = value == null ? NullObject.NULL_OBJECT : value;
                    return StampedConfig.this.values.putIfAbsent(key, nnValue) == null;
                }
            }
            String key = path.get(0);
            List<String> subPath = path.subList(1, path.size());
            Object currentParent = StampedConfig.this.values.get(key);
            if (currentParent == null) {
                StampedConfig subConfig = this.createSubConfig();
                StampedConfig.this.values.put(key, subConfig);
                return subConfig.add(subPath, value);
            }
            if (currentParent instanceof StampedConfig) {
                return ((StampedConfig)currentParent).add(subPath, value);
            }
            throw new IncompatibleIntermediaryLevelException("Cannot add an element to an intermediary value of type: " + String.valueOf(currentParent.getClass()));
        }

        protected class Entry
        extends ReadOnlyLockedView.Entry
        implements CommentedConfig.Entry {
            Entry(Map.Entry<String, Object> entry) {
                super(entry);
                WritableLockedView.this.checkValid();
            }

            @Override
            public String removeComment() {
                WritableLockedView.this.checkValid();
                return StampedConfig.this.comments.remove(this.mapEntry.getKey());
            }

            @Override
            public String setComment(String comment) {
                WritableLockedView.this.checkValid();
                return StampedConfig.this.comments.put((String)this.mapEntry.getKey(), comment);
            }

            @Override
            public <T> T setValue(Object value) {
                WritableLockedView.this.checkValid();
                return (T)this.mapEntry.setValue(StampedConfig.this.convertValue(value));
            }
        }
    }

    private final class InLockLazyEntry
    extends LazyEntry {
        private volatile boolean valid;

        private void checkValid() {
            if (!this.valid) {
                throw new IllegalStateException("Entries provided by StampedConfig.entrySet().forEach() are only valid in the scope of the forEach call, for thread-safety reasons (and to avoid deadlocks).");
            }
        }

        void invalidate() {
            this.valid = false;
        }

        protected InLockLazyEntry(String key) {
            super(StampedConfig.this, key);
            this.valid = true;
        }

        @Override
        public String removeComment() {
            this.checkValid();
            return StampedConfig.this.comments.remove(this.key);
        }

        @Override
        public String setComment(String comment) {
            this.checkValid();
            return StampedConfig.this.comments.put(this.key, comment);
        }

        @Override
        public <T> T setValue(Object value) {
            this.checkValid();
            return (T)StampedConfig.this.values.put(this.key, value);
        }

        @Override
        public String getKey() {
            this.checkValid();
            return this.key;
        }

        @Override
        public <T> T getRawValue() {
            this.checkValid();
            return (T)StampedConfig.this.values.get(this.key);
        }

        @Override
        public String getComment() {
            this.checkValid();
            return StampedConfig.this.comments.get(this.key);
        }

        public String toString() {
            this.checkValid();
            return "StampedConfig.InLockLazyEntry{key=\"" + this.key + "\"}";
        }
    }

    private final class LockingLazyEntry
    extends LazyEntry {
        protected LockingLazyEntry(String key, EntrySet set) {
            super(StampedConfig.this, key);
        }

        @Override
        public String removeComment() {
            return StampedConfig.this.mapLockRemove(StampedConfig.this.comments, StampedConfig.this.lock, this.key);
        }

        @Override
        public String setComment(String comment) {
            return StampedConfig.this.mapLockPut(StampedConfig.this.comments, StampedConfig.this.lock, this.key, comment);
        }

        @Override
        public <T> T setValue(Object value) {
            return (T)StampedConfig.this.mapLockPut(StampedConfig.this.values, StampedConfig.this.lock, this.key, value);
        }

        @Override
        public String getKey() {
            StampedConfig.this.checkStateForNormalOp();
            return this.key;
        }

        @Override
        public <T> T getRawValue() {
            return (T)StampedConfig.this.mapLockGet(StampedConfig.this.values, StampedConfig.this.lock, this.key);
        }

        @Override
        public String getComment() {
            return StampedConfig.this.mapLockGet(StampedConfig.this.comments, StampedConfig.this.lock, this.key);
        }

        public String toString() {
            return "StampedConfig.LockingLazyEntry{key=\"" + this.key + "\"}";
        }
    }

    private abstract class LazyEntry
    implements CommentedConfig.Entry {
        protected final String key;

        protected LazyEntry(StampedConfig stampedConfig, String key) {
            this.key = key;
        }
    }

    private class EntryIterator
    implements Iterator<LazyEntry> {
        private final LazyEntry[] entries;
        private int nextPosition;
        private boolean removed;

        EntryIterator(LazyEntry[] entries) {
            this.entries = entries;
        }

        @Override
        public boolean hasNext() {
            return this.nextPosition < this.entries.length;
        }

        @Override
        public LazyEntry next() {
            this.removed = false;
            return this.entries[this.nextPosition++];
        }

        @Override
        public void remove() {
            if (this.removed) {
                throw new IllegalStateException("remove() can be called only once per call to next()");
            }
            if (this.nextPosition == 0) {
                throw new IllegalStateException("next() must be called before remove()");
            }
            if (this.nextPosition - 1 >= this.entries.length) {
                throw new IllegalStateException("No more elements in this iterator");
            }
            this.removed = true;
            LazyEntry entry = this.entries[this.nextPosition - 1];
            StampedConfig.this.remove(Collections.singletonList(entry.key));
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void forEachRemaining(Consumer<? super LazyEntry> action) {
            long stamp = StampedConfig.this.lock.tryWriteLock();
            if (stamp == 0L) {
                StampedConfig.this.checkStateForNormalOp();
                stamp = StampedConfig.this.lock.writeLock();
            }
            try {
                StampedConfig.this.state.set(ThreadConfigState.IN_ITER_OP);
                for (int i = this.nextPosition; i < this.entries.length; ++i) {
                    LazyEntry entry = this.entries[i];
                    InLockLazyEntry inLockEntry = entry instanceof InLockLazyEntry ? (InLockLazyEntry)entry : new InLockLazyEntry(entry.key);
                    try {
                        action.accept(inLockEntry);
                        continue;
                    }
                    finally {
                        inLockEntry.invalidate();
                    }
                }
            }
            finally {
                StampedConfig.this.state.set(ThreadConfigState.NORMAL);
                StampedConfig.this.lock.unlockWrite(stamp);
            }
        }
    }
}


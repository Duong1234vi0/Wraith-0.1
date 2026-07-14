/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core;

import com.electronwill.nightconfig.core.AbstractConfig;
import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.IncompatibleIntermediaryLevelException;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.utils.TransformingSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

public abstract class AbstractCommentedConfig
extends AbstractConfig
implements CommentedConfig {
    protected final Map<String, String> commentMap;

    @Deprecated
    public AbstractCommentedConfig(boolean concurrent) {
        super(concurrent);
        this.commentMap = AbstractCommentedConfig.getDefaultCommentMap(concurrent);
    }

    public AbstractCommentedConfig(Supplier<Map<String, Object>> mapCreator) {
        super(mapCreator);
        this.commentMap = AbstractConfig.getWildcardMapCreator(mapCreator).get();
    }

    public AbstractCommentedConfig(Map<String, Object> valuesMap) {
        super(valuesMap);
        this.commentMap = AbstractCommentedConfig.getDefaultCommentMap(valuesMap instanceof ConcurrentMap);
    }

    public AbstractCommentedConfig(Map<String, Object> valuesMap, Map<String, String> commentMap) {
        super(valuesMap);
        this.commentMap = commentMap;
    }

    @Deprecated
    public AbstractCommentedConfig(UnmodifiableConfig toCopy, boolean concurrent) {
        super(toCopy, concurrent);
        this.commentMap = AbstractCommentedConfig.getDefaultCommentMap(concurrent);
    }

    public AbstractCommentedConfig(UnmodifiableConfig toCopy, Supplier<Map<String, Object>> mapCreator) {
        super(toCopy, mapCreator);
        this.commentMap = AbstractConfig.getWildcardMapCreator(mapCreator).get();
    }

    @Deprecated
    public AbstractCommentedConfig(UnmodifiableCommentedConfig toCopy, boolean concurrent) {
        super((UnmodifiableConfig)toCopy, concurrent);
        this.commentMap = AbstractCommentedConfig.getDefaultCommentMap(concurrent);
        try {
            this.commentMap.putAll(toCopy.commentMap());
        }
        catch (UnsupportedOperationException ex) {
            for (UnmodifiableCommentedConfig.Entry entry : toCopy.entrySet()) {
                this.commentMap.put(entry.getKey(), entry.getComment());
            }
        }
    }

    public AbstractCommentedConfig(UnmodifiableCommentedConfig toCopy, Supplier<Map<String, Object>> mapCreator) {
        super((UnmodifiableConfig)toCopy, mapCreator);
        this.commentMap = AbstractConfig.getWildcardMapCreator(mapCreator).get();
    }

    @Deprecated
    protected static Map<String, String> getDefaultCommentMap(boolean concurrent) {
        return AbstractConfig.getDefaultMapCreator(concurrent).get();
    }

    @Override
    public String getComment(List<String> path) {
        int lastIndex = path.size() - 1;
        String lastKey = path.get(lastIndex);
        if (lastIndex == 0) {
            return this.commentMap.get(lastKey);
        }
        Object parent = this.getRaw(path.subList(0, lastIndex));
        if (parent instanceof UnmodifiableCommentedConfig) {
            List<String> lastPath = Collections.singletonList(lastKey);
            return ((UnmodifiableCommentedConfig)parent).getComment(lastPath);
        }
        return null;
    }

    @Override
    public String setComment(List<String> path, String comment) {
        int lastIndex = path.size() - 1;
        String lastKey = path.get(lastIndex);
        if (lastIndex == 0) {
            if (comment != null) {
                return this.commentMap.put(lastKey, comment);
            }
            return this.commentMap.remove(lastKey);
        }
        List<String> parentPath = path.subList(0, lastIndex);
        Object parent = this.getRaw(parentPath);
        List<String> lastPath = Collections.singletonList(lastKey);
        if (parent instanceof CommentedConfig) {
            return ((CommentedConfig)parent).setComment(lastPath, comment);
        }
        if (parent == null) {
            Config commentedParent = this.createSubConfig();
            this.set(parentPath, (Object)commentedParent);
            return commentedParent.setComment(lastPath, comment);
        }
        throw new IncompatibleIntermediaryLevelException("Cannot set a comment to path " + String.valueOf(path) + " because the parent entry is of incompatible type " + String.valueOf(parent.getClass()));
    }

    @Override
    public String removeComment(List<String> path) {
        int lastIndex = path.size() - 1;
        String lastKey = path.get(lastIndex);
        if (lastIndex == 0) {
            return this.commentMap.remove(lastKey);
        }
        Object parent = this.getRaw(path.subList(0, lastIndex));
        if (parent instanceof CommentedConfig) {
            List<String> lastPath = Collections.singletonList(lastKey);
            return ((CommentedConfig)parent).removeComment(lastPath);
        }
        return null;
    }

    @Override
    public boolean containsComment(List<String> path) {
        int lastIndex = path.size() - 1;
        String lastKey = path.get(lastIndex);
        if (lastIndex == 0) {
            return this.commentMap.containsKey(lastKey);
        }
        Object parent = this.getRaw(path.subList(0, lastIndex));
        if (parent instanceof CommentedConfig) {
            List<String> lastPath = Collections.singletonList(lastKey);
            return ((CommentedConfig)parent).containsComment(lastPath);
        }
        return false;
    }

    @Override
    public Map<String, String> commentMap() {
        return this.commentMap;
    }

    @Override
    public Set<? extends CommentedConfig.Entry> entrySet() {
        return new TransformingSet<Map.Entry, CommentedEntryWrapper>(this.map.entrySet(), x$0 -> new CommentedEntryWrapper((Map.Entry<String, Object>)x$0), o -> null, o -> o);
    }

    @Override
    public abstract AbstractCommentedConfig clone();

    @Override
    public void clear() {
        super.clear();
        this.clearComments();
    }

    @Override
    public void clearComments() {
        this.commentMap.clear();
        for (Object o : this.map.values()) {
            if (!(o instanceof CommentedConfig)) continue;
            ((CommentedConfig)o).clearComments();
        }
    }

    protected class CommentedEntryWrapper
    extends AbstractConfig.EntryWrapper
    implements CommentedConfig.Entry {
        private List<String> path;

        public CommentedEntryWrapper(Map.Entry<String, Object> mapEntry) {
            super(mapEntry);
            this.path = null;
        }

        protected List<String> getPath() {
            if (this.path == null) {
                this.path = Collections.singletonList(this.getKey());
            }
            return this.path;
        }

        @Override
        public String getComment() {
            return AbstractCommentedConfig.this.getComment(this.getPath());
        }

        @Override
        public String setComment(String comment) {
            return AbstractCommentedConfig.this.setComment(this.getPath(), comment);
        }

        @Override
        public String removeComment() {
            return AbstractCommentedConfig.this.removeComment(this.getPath());
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof CommentedEntryWrapper) {
                CommentedEntryWrapper other = (CommentedEntryWrapper)obj;
                return Objects.equals(this.getKey(), other.getKey()) && Objects.equals(this.getValue(), other.getValue()) && Objects.equals(this.getComment(), other.getComment());
            }
            return false;
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = 31 * result + Objects.hashCode(this.getKey());
            result = 31 * result + Objects.hashCode(this.getValue());
            result = 31 * result + Objects.hashCode(this.getComment());
            return result;
        }

        public String toString() {
            return "CommentedEntryWrapper(" + this.getKey() + "=" + String.valueOf(this.getValue()) + ")";
        }
    }
}


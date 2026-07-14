/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.utils;

import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.utils.TransformingSet;
import com.electronwill.nightconfig.core.utils.UnmodifiableConfigWrapper;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class FakeUnmodifiableCommentedConfig
extends UnmodifiableConfigWrapper<UnmodifiableConfig>
implements UnmodifiableCommentedConfig {
    public FakeUnmodifiableCommentedConfig(UnmodifiableConfig config) {
        super(config);
    }

    @Override
    public String getComment(List<String> path) {
        return null;
    }

    @Override
    public boolean containsComment(List<String> path) {
        return false;
    }

    @Override
    public Map<String, UnmodifiableCommentedConfig.CommentNode> getComments() {
        return Collections.emptyMap();
    }

    @Override
    public Map<String, String> commentMap() {
        return Collections.emptyMap();
    }

    @Override
    public Set<? extends UnmodifiableCommentedConfig.Entry> entrySet() {
        return new TransformingSet<UnmodifiableConfig.Entry, FakeCommentedEntry>(this.config.entrySet(), FakeCommentedEntry::new, o -> null, o -> o);
    }

    private static final class FakeCommentedEntry
    implements UnmodifiableCommentedConfig.Entry {
        private final UnmodifiableConfig.Entry entry;

        private FakeCommentedEntry(UnmodifiableConfig.Entry entry) {
            this.entry = entry;
        }

        @Override
        public String getComment() {
            return null;
        }

        @Override
        public String getKey() {
            return this.entry.getKey();
        }

        @Override
        public <T> T getRawValue() {
            return this.entry.getRawValue();
        }
    }
}


/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.serde;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.serde.AnnotationProcessor;
import com.electronwill.nightconfig.core.serde.ObjectSerializer;
import com.electronwill.nightconfig.core.serde.SerdeAssertException;
import com.electronwill.nightconfig.core.serde.SerdeException;
import com.electronwill.nightconfig.core.serde.ValueSerializer;
import com.electronwill.nightconfig.core.serde.annotations.SerdeAssert;
import com.electronwill.nightconfig.core.serde.annotations.SerdeComment;
import com.electronwill.nightconfig.core.serde.annotations.SerdeKey;
import com.electronwill.nightconfig.core.serde.annotations.SerdePhase;
import com.electronwill.nightconfig.core.serde.annotations.SerdeSkipSerializingIf;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class SerializerContext {
    final ObjectSerializer settings;
    final Supplier<? extends ConfigFormat<?>> formatSupplier;
    final Supplier<? extends Config> configSupplier;

    SerializerContext(ObjectSerializer settings, Supplier<? extends ConfigFormat<?>> formatSupplier, Supplier<? extends Config> configSupplier) {
        this.settings = settings;
        this.formatSupplier = formatSupplier;
        this.configSupplier = configSupplier;
    }

    public ConfigFormat<?> configFormat() {
        return this.formatSupplier.get();
    }

    public Config createConfig() {
        return this.configSupplier.get();
    }

    public CommentedConfig createCommentedConfig() {
        return CommentedConfig.fake(this.createConfig());
    }

    public Object serializeValue(Object value) {
        ValueSerializer serializer = this.settings.findValueSerializer(value, this);
        return serializer.serialize(value, this);
    }

    public void serializeFields(Object source, Config destination) {
        for (Class<?> cls = source.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            for (Field field : cls.getDeclaredFields()) {
                Object value;
                if (!this.preCheck(field)) continue;
                try {
                    value = field.get(source);
                }
                catch (Exception e) {
                    throw new SerdeException("Failed to read field `" + String.valueOf(field) + "`", e);
                }
                if (this.skipField(field, source, value)) continue;
                List<String> path = Collections.singletonList(this.configKey(field));
                String comment = this.configComment(field);
                Supplier<?> defaultValueSupplier = this.settings.findDefaultValueSupplier(value, field, source);
                if (defaultValueSupplier != null) {
                    try {
                        value = defaultValueSupplier.get();
                    }
                    catch (Exception e) {
                        throw new SerdeException("Error in default value provider for field " + String.valueOf(field));
                    }
                }
                if (!this.assertField(field, source, value)) {
                    throw new SerdeAssertException("Field `" + String.valueOf(field) + "` has an invalid value: " + String.valueOf(value));
                }
                ValueSerializer serializer = this.settings.findValueSerializer(value, this);
                try {
                    Object serialized = serializer.serialize(value, this);
                    destination.set(path, serialized);
                    if (comment == null || !(destination instanceof CommentedConfig)) continue;
                    ((CommentedConfig)destination).setComment(path, comment);
                }
                catch (Exception ex) {
                    throw new SerdeException("Error during serialization of field `" + String.valueOf(field) + "` with serializer " + String.valueOf(serializer), ex);
                }
            }
        }
    }

    private String configKey(Field field) {
        SerdeKey keyAnnot = field.getAnnotation(SerdeKey.class);
        return keyAnnot == null ? field.getName() : keyAnnot.value();
    }

    private String configComment(Field field) {
        SerdeComment[] commentAnnots = (SerdeComment[])field.getDeclaredAnnotationsByType(SerdeComment.class);
        if (commentAnnots.length == 0) {
            return null;
        }
        Object comment = commentAnnots[0].value();
        for (int i = 1; i < commentAnnots.length; ++i) {
            comment = (String)comment + "\n";
            comment = (String)comment + commentAnnots[i].value();
        }
        return comment;
    }

    private boolean skipField(Field field, Object fieldContainer, Object fieldValue) {
        SerdeSkipSerializingIf annot = field.getAnnotation(SerdeSkipSerializingIf.class);
        if (annot == null) {
            return false;
        }
        try {
            Predicate<?> skipPredicate = AnnotationProcessor.resolveSkipSerializingIfPredicate(annot, fieldContainer, field);
            return skipPredicate.test(fieldValue);
        }
        catch (Exception e) {
            String msg = "Failed to resolve or apply skip predicate for serialization of field " + String.valueOf(field);
            throw new SerdeException(msg, e);
        }
    }

    private boolean assertField(Field field, Object fieldContainer, Object fieldValue) {
        SerdeAssert[] annot = (SerdeAssert[])field.getAnnotationsByType(SerdeAssert.class);
        if (annot == null || annot.length == 0) {
            return true;
        }
        try {
            Predicate<?> assertPredicate = AnnotationProcessor.resolveAssertPredicate(annot, fieldContainer, SerdePhase.SERIALIZING, field);
            if (assertPredicate == null) {
                return true;
            }
            return assertPredicate.test(fieldValue);
        }
        catch (Exception e) {
            String msg = "Failed to resolve or apply assertion for serialization of field " + String.valueOf(field);
            throw new SerdeException(msg, e);
        }
    }

    private boolean preCheck(Field field) {
        int mods = field.getModifiers();
        if (Modifier.isStatic(mods) || field.isSynthetic()) {
            return false;
        }
        if (Modifier.isTransient(mods) && this.settings.applyTransientModifier) {
            return false;
        }
        if (Modifier.isFinal(mods) || !Modifier.isPublic(mods)) {
            field.setAccessible(true);
        }
        return true;
    }
}


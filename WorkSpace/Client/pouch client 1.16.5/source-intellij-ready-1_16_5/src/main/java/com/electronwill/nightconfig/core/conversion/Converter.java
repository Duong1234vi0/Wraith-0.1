/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.conversion;

@Deprecated
public interface Converter<FieldType, ConfigValueType> {
    public FieldType convertToField(ConfigValueType var1);

    public ConfigValueType convertFromField(FieldType var1);
}


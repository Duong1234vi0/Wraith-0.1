/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;

public class DataPackConfig {
    public static final DataPackConfig n_1700_B = new DataPackConfig((List<String>)ImmutableList.of((Object)"vanilla"), (List<String>)ImmutableList.of());
    public static final Codec<DataPackConfig> J_1907_R = RecordCodecBuilder.create(builder -> builder.group((App)Codec.STRING.listOf().fieldOf("Enabled").forGetter(datapackCodec -> datapackCodec.R_4764_Y), (App)Codec.STRING.listOf().fieldOf("Disabled").forGetter(datapackCodec -> datapackCodec.G_564_y)).apply((Applicative)builder, DataPackConfig::new));
    private final List<String> R_4764_Y;
    private final List<String> G_564_y;

    public DataPackConfig(List<String> enabled, List<String> disabled) {
        this.R_4764_Y = ImmutableList.copyOf(enabled);
        this.G_564_y = ImmutableList.copyOf(disabled);
    }

    public List<String> n_1700_B() {
        return this.R_4764_Y;
    }

    public List<String> J_1907_R() {
        return this.G_564_y;
    }
}



/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package mods.voicechat.resourcepacks;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.D_2103_L;
import lightning.product.L_3144_D;
import lightning.product.PackMetadataSection;
import lightning.product.g_2336_b;
import lightning.product.i_4221_J;
import lightning.product.PackSource;
import lightning.product.r_2139_P;
import lightning.product.u_4608_G;
import mods.voicechat.Voicechat;

public class VoiceChatResourcePack
extends r_2139_P {
    protected String path;
    protected L_3144_D name;

    public VoiceChatResourcePack(String path, L_3144_D name) {
        super(null);
        this.path = path;
        this.name = name;
    }

    @Nullable
    public D_2103_L toPack() {
        try {
            PackMetadataSection packMetadataSection = this.getMetadata(PackMetadataSection.n_1700_B);
            if (packMetadataSection == null) {
                return null;
            }
            return new D_2103_L(this.path, false, () -> this, this.name, packMetadataSection.n_1700_B(), u_4608_G.R_4764_Y, D_2103_L.J_1907_R.n_1700_B, false, PackSource.J_1907_R);
        }
        catch (IOException e) {
            return null;
        }
    }

    @Override
    public String getName() {
        return this.path;
    }

    private String getPath() {
        return "/packs/" + this.path + "/";
    }

    @Nullable
    private InputStream get(String name) {
        return Voicechat.class.getResourceAsStream(this.getPath() + name);
    }

    @Override
    protected InputStream getInputStream(String name) throws IOException {
        InputStream resourceAsStream = this.get(name);
        if (resourceAsStream == null) {
            throw new FileNotFoundException("Resource " + name + " does not exist");
        }
        return resourceAsStream;
    }

    @Override
    protected boolean resourceExists(String name) {
        try {
            return this.get(name) != null;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public Collection<g_2336_b> getAllResourceLocations(i_4221_J type, String namespace, String prefix, int maxDepth, Predicate<String> pathFilter) {
        ArrayList list = Lists.newArrayList();
        try {
            URL url = Voicechat.class.getResource(this.getPath());
            Path namespacePath = Paths.get(url.toURI()).resolve(type.n_1700_B()).resolve(namespace);
            Path resPath = namespacePath.resolve(prefix);
            if (!Files.exists(resPath, new LinkOption[0])) {
                return list;
            }
            try (Stream<Path> files = Files.walk(resPath, new FileVisitOption[0]);){
                files.filter(path -> !Files.isDirectory(path, new LinkOption[0])).forEach(path -> {
                    g_2336_b resourceLocation = new g_2336_b(namespace, VoiceChatResourcePack.convertPath(path).substring(VoiceChatResourcePack.convertPath(namespacePath).length() + 1));
                    list.add(resourceLocation);
                });
            }
        }
        catch (Exception e) {
            Voicechat.LOGGER.error("Failed to list builtin pack resources", e);
        }
        return list.stream().filter(resourceLocation -> pathFilter.test(resourceLocation.J_1907_R())).collect(Collectors.toList());
    }

    private static String convertPath(Path path) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < path.getNameCount(); ++i) {
            stringBuilder.append(path.getName(i));
            if (i >= path.getNameCount() - 1) continue;
            stringBuilder.append("/");
        }
        return stringBuilder.toString();
    }

    @Override
    public Set<String> getResourceNamespaces(i_4221_J packType) {
        if (packType == i_4221_J.n_1700_B) {
            return ImmutableSet.of((Object)"voicechat");
        }
        return ImmutableSet.of();
    }

    @Override
    public void close() {
    }
}



/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.data;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class M_182_A {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final Path J_1907_R;
    private final Path R_4764_Y;
    private int G_564_y;
    private final Map<Path, String> P_1922_E = Maps.newHashMap();
    private final Map<Path, String> u_1723_Y = Maps.newHashMap();
    private final Set<Path> v_4262_N = Sets.newHashSet();

    public M_182_A(Path folder, String fileName) throws IOException {
        this.J_1907_R = folder;
        Path path = folder.resolve(".cache");
        Files.createDirectories(path, new FileAttribute[0]);
        this.R_4764_Y = path.resolve(fileName);
        this.R_4764_Y().forEach(p_209395_1_ -> {
            String s = this.P_1922_E.put((Path)p_209395_1_, "");
        });
        if (Files.isReadable(this.R_4764_Y)) {
            IOUtils.readLines((InputStream)Files.newInputStream(this.R_4764_Y, new OpenOption[0]), (Charset)Charsets.UTF_8).forEach(p_208315_2_ -> {
                int i = p_208315_2_.indexOf(32);
                this.P_1922_E.put(folder.resolve(p_208315_2_.substring(i + 1)), p_208315_2_.substring(0, i));
            });
        }
    }

    public void n_1700_B() throws IOException {
        BufferedWriter writer;
        this.J_1907_R();
        try {
            writer = Files.newBufferedWriter(this.R_4764_Y, new OpenOption[0]);
        }
        catch (IOException ioexception) {
            n_1700_B.warn("Unable write cachefile {}: {}", (Object)this.R_4764_Y, (Object)ioexception.toString());
            return;
        }
        IOUtils.writeLines((Collection)this.u_1723_Y.entrySet().stream().map(p_208319_1_ -> (String)p_208319_1_.getValue() + " " + String.valueOf(this.J_1907_R.relativize((Path)p_208319_1_.getKey()))).collect(Collectors.toList()), (String)System.lineSeparator(), (Writer)writer);
        ((Writer)writer).close();
        n_1700_B.debug("Caching: cache hits: {}, created: {} removed: {}", (Object)this.G_564_y, (Object)(this.u_1723_Y.size() - this.G_564_y), (Object)this.P_1922_E.size());
    }

    @Nullable
    public String n_1700_B(Path fileIn) {
        return this.P_1922_E.get(fileIn);
    }

    public void n_1700_B(Path fileIn, String hash) {
        this.u_1723_Y.put(fileIn, hash);
        if (Objects.equals(this.P_1922_E.remove(fileIn), hash)) {
            ++this.G_564_y;
        }
    }

    public boolean J_1907_R(Path fileIn) {
        return this.P_1922_E.containsKey(fileIn);
    }

    public void R_4764_Y(Path p_218456_1_) {
        this.v_4262_N.add(p_218456_1_);
    }

    private void J_1907_R() throws IOException {
        this.R_4764_Y().forEach(p_208322_1_ -> {
            if (this.J_1907_R((Path)p_208322_1_) && !this.v_4262_N.contains(p_208322_1_)) {
                try {
                    Files.delete(p_208322_1_);
                }
                catch (IOException ioexception) {
                    n_1700_B.debug("Unable to delete: {} ({})", p_208322_1_, (Object)ioexception.toString());
                }
            }
        });
    }

    private Stream<Path> R_4764_Y() throws IOException {
        return Files.walk(this.J_1907_R, new FileVisitOption[0]).filter(p_209397_1_ -> !Objects.equals(this.R_4764_Y, p_209397_1_) && !Files.isDirectory(p_209397_1_, new LinkOption[0]));
    }
}


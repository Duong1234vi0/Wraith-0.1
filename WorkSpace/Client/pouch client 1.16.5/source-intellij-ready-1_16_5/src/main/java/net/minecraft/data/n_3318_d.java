/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 *  org.apache.commons.io.FileUtils
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.data;

import com.google.common.collect.Lists;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;
import lightning.product.U_2912_j;
import lightning.product.j_3341_s;
import lightning.product.r_1827_u;
import lightning.product.r_4318_c;
import net.minecraft.data.M_182_A;
import net.minecraft.data.Q_4569_t;
import net.minecraft.data.Y_259_p;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class n_3318_d
implements Y_259_p {
    @Nullable
    private static final Path J_1907_R = null;
    private static final Logger R_4764_Y = LogManager.getLogger();
    private final Q_4569_t G_564_y;
    private final List<n_1700_B> P_1922_E = Lists.newArrayList();

    public n_3318_d(Q_4569_t generatorIn) {
        this.G_564_y = generatorIn;
    }

    public n_3318_d n_1700_B(n_1700_B transformer) {
        this.P_1922_E.add(transformer);
        return this;
    }

    private U_2912_j n_1700_B(String fileName, U_2912_j nbt) {
        U_2912_j compoundnbt = nbt;
        for (n_1700_B snbttonbtconverter$itransformer : this.P_1922_E) {
            compoundnbt = snbttonbtconverter$itransformer.n_1700_B(fileName, compoundnbt);
        }
        return compoundnbt;
    }

    @Override
    public void n_1700_B(M_182_A cache) throws IOException {
        Path path = this.G_564_y.J_1907_R();
        ArrayList list = Lists.newArrayList();
        for (Path path1 : this.G_564_y.n_1700_B()) {
            Files.walk(path1, new FileVisitOption[0]).filter(snbtPath -> snbtPath.toString().endsWith(".snbt")).forEach(filePath -> list.add(CompletableFuture.supplyAsync(() -> this.n_1700_B((Path)filePath, this.n_1700_B(path1, (Path)filePath)), j_3341_s.u_1723_Y())));
        }
        j_3341_s.J_1907_R(list).join().stream().filter(Objects::nonNull).forEach(result -> this.n_1700_B(cache, (J_1907_R)result, path));
    }

    @Override
    public String n_1700_B() {
        return "SNBT -> NBT";
    }

    private String n_1700_B(Path inputFolder, Path fileIn) {
        String s = inputFolder.relativize(fileIn).toString().replaceAll("\\\\", "/");
        return s.substring(0, s.length() - ".snbt".length());
    }

    @Nullable
    private J_1907_R n_1700_B(Path filePath, String fileName) {
        block10: {
            J_1907_R j_1907_R;
            block9: {
                BufferedReader bufferedreader = Files.newBufferedReader(filePath);
                try {
                    String s = IOUtils.toString((Reader)bufferedreader);
                    U_2912_j compoundnbt = this.n_1700_B(fileName, r_4318_c.n_1700_B(s));
                    ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                    r_1827_u.n_1700_B(compoundnbt, bytearrayoutputstream);
                    byte[] abyte = bytearrayoutputstream.toByteArray();
                    String s1 = n_1700_B.hashBytes(abyte).toString();
                    String s2 = J_1907_R != null ? compoundnbt.n_1700_B("    ", 0).getString() + "\n" : null;
                    j_1907_R = new J_1907_R(fileName, abyte, s2, s1);
                    if (bufferedreader == null) break block9;
                }
                catch (Throwable throwable) {
                    try {
                        if (bufferedreader != null) {
                            try {
                                bufferedreader.close();
                            }
                            catch (Throwable throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                        }
                        throw throwable;
                    }
                    catch (CommandSyntaxException commandsyntaxexception) {
                        R_4764_Y.error("Couldn't convert {} from SNBT to NBT at {} as it's invalid SNBT", (Object)fileName, (Object)filePath, (Object)commandsyntaxexception);
                        break block10;
                    }
                    catch (IOException ioexception) {
                        R_4764_Y.error("Couldn't convert {} from SNBT to NBT at {}", (Object)fileName, (Object)filePath, (Object)ioexception);
                    }
                }
                bufferedreader.close();
            }
            return j_1907_R;
        }
        return null;
    }

    private void n_1700_B(M_182_A directory, J_1907_R taskResult, Path pathIn) {
        if (taskResult.R_4764_Y != null) {
            Path path = J_1907_R.resolve(taskResult.n_1700_B + ".snbt");
            try {
                FileUtils.write((File)path.toFile(), (CharSequence)taskResult.R_4764_Y, (Charset)StandardCharsets.UTF_8);
            }
            catch (IOException ioexception) {
                R_4764_Y.error("Couldn't write structure SNBT {} at {}", (Object)taskResult.n_1700_B, (Object)path, (Object)ioexception);
            }
        }
        Path path1 = pathIn.resolve(taskResult.n_1700_B + ".nbt");
        try {
            if (!Objects.equals(directory.n_1700_B(path1), taskResult.G_564_y) || !Files.exists(path1, new LinkOption[0])) {
                Files.createDirectories(path1.getParent(), new FileAttribute[0]);
                try (OutputStream outputstream = Files.newOutputStream(path1, new OpenOption[0]);){
                    outputstream.write(taskResult.J_1907_R);
                }
            }
            directory.n_1700_B(path1, taskResult.G_564_y);
        }
        catch (IOException ioexception1) {
            R_4764_Y.error("Couldn't write structure {} at {}", (Object)taskResult.n_1700_B, (Object)path1, (Object)ioexception1);
        }
    }

    @FunctionalInterface
    public static interface n_1700_B {
        public U_2912_j n_1700_B(String var1, U_2912_j var2);
    }

    static class J_1907_R {
        private final String n_1700_B;
        private final byte[] J_1907_R;
        @Nullable
        private final String R_4764_Y;
        private final String G_564_y;

        public J_1907_R(String fileName, byte[] p_i232551_2_, @Nullable String p_i232551_3_, String bytesHash) {
            this.n_1700_B = fileName;
            this.J_1907_R = p_i232551_2_;
            this.R_4764_Y = p_i232551_3_;
            this.G_564_y = bytesHash;
        }
    }
}


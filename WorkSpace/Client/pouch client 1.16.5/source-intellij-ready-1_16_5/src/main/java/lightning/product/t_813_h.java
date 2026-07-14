/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.g_2336_b;
import lightning.product.x_1356_s;

public class t_813_h
extends x_1356_s {
    private final File J_1907_R;

    public t_813_h(File folder) {
        this.J_1907_R = folder;
    }

    @Override
    public File n_1700_B(g_2336_b location) {
        return new File(this.J_1907_R, location.toString().replace(':', '/'));
    }

    @Override
    public File n_1700_B(String p_225638_1_) {
        return new File(this.J_1907_R, p_225638_1_);
    }

    @Override
    public Collection<g_2336_b> n_1700_B(String p_225639_1_, String p_225639_2_, int p_225639_3_, Predicate<String> p_225639_4_) {
        block10: {
            Collection collection;
            block9: {
                Path path = this.J_1907_R.toPath().resolve(p_225639_2_);
                Stream<Path> stream2 = Files.walk(path.resolve(p_225639_1_), p_225639_3_, new FileVisitOption[0]);
                try {
                    collection = stream2.filter(p_211686_0_ -> Files.isRegularFile(p_211686_0_, new LinkOption[0])).filter(p_211687_0_ -> !p_211687_0_.endsWith(".mcmeta")).filter(p_229275_1_ -> p_225639_4_.test(p_229275_1_.getFileName().toString())).map(p_229274_2_ -> new g_2336_b(p_225639_2_, path.relativize((Path)p_229274_2_).toString().replaceAll("\\\\", "/"))).collect(Collectors.toList());
                    if (stream2 == null) break block9;
                }
                catch (Throwable throwable) {
                    try {
                        if (stream2 != null) {
                            try {
                                stream2.close();
                            }
                            catch (Throwable throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                        }
                        throw throwable;
                    }
                    catch (NoSuchFileException stream2) {
                        break block10;
                    }
                    catch (IOException ioexception) {
                        n_1700_B.warn("Unable to getFiles on {}", (Object)p_225639_1_, (Object)ioexception);
                    }
                }
                stream2.close();
            }
            return collection;
        }
        return Collections.emptyList();
    }
}


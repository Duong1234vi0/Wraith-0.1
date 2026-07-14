/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.DataFixer
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.datafixers.DataFixer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.H_4757_Q;
import lightning.product.Resource;
import lightning.product.ResourceManager;
import lightning.product.U_2912_j;
import lightning.product.a_2886_t;
import lightning.product.b_2971_z;
import lightning.product.g_2336_b;
import lightning.product.n_3832_I;
import lightning.product.o_1967_f;
import lightning.product.r_146_S;
import lightning.product.r_1827_u;
import lightning.product.s_3109_F;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class b_2085_h {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final Map<g_2336_b, a_2886_t> J_1907_R = Maps.newHashMap();
    private final DataFixer R_4764_Y;
    private ResourceManager G_564_y;
    private final Path P_1922_E;

    public b_2085_h(ResourceManager p_i232119_1_, b_2971_z.n_1700_B p_i232119_2_, DataFixer p_i232119_3_) {
        this.G_564_y = p_i232119_1_;
        this.R_4764_Y = p_i232119_3_;
        this.P_1922_E = p_i232119_2_.n_1700_B(H_4757_Q.u_1723_Y).normalize();
    }

    public a_2886_t n_1700_B(g_2336_b p_200220_1_) {
        a_2886_t template = this.J_1907_R(p_200220_1_);
        if (template == null) {
            template = new a_2886_t();
            this.J_1907_R.put(p_200220_1_, template);
        }
        return template;
    }

    @Nullable
    public a_2886_t J_1907_R(g_2336_b p_200219_1_) {
        return this.J_1907_R.computeIfAbsent(p_200219_1_, p_209204_1_ -> {
            a_2886_t template = this.u_1723_Y((g_2336_b)p_209204_1_);
            return template != null ? template : this.P_1922_E((g_2336_b)p_209204_1_);
        });
    }

    public void n_1700_B(ResourceManager resourceManager) {
        this.G_564_y = resourceManager;
        this.J_1907_R.clear();
    }

    @Nullable
    private a_2886_t P_1922_E(g_2336_b p_209201_1_) {
        a_2886_t a_2886_t2;
        block9: {
            g_2336_b resourcelocation = new g_2336_b(p_209201_1_.R_4764_Y(), "structures/" + p_209201_1_.J_1907_R() + ".nbt");
            Resource iresource = this.G_564_y.n_1700_B(resourcelocation);
            try {
                a_2886_t2 = this.n_1700_B(iresource.J_1907_R());
                if (iresource == null) break block9;
            }
            catch (Throwable throwable) {
                try {
                    if (iresource != null) {
                        try {
                            iresource.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (FileNotFoundException filenotfoundexception) {
                    return null;
                }
                catch (Throwable throwable3) {
                    n_1700_B.error("Couldn't load structure {}: {}", (Object)p_209201_1_, (Object)throwable3.toString());
                    return null;
                }
            }
            iresource.close();
        }
        return a_2886_t2;
    }

    @Nullable
    private a_2886_t u_1723_Y(g_2336_b locationIn) {
        a_2886_t a_2886_t2;
        if (!this.P_1922_E.toFile().isDirectory()) {
            return null;
        }
        Path path = this.J_1907_R(locationIn, ".nbt");
        FileInputStream inputstream = new FileInputStream(path.toFile());
        try {
            a_2886_t2 = this.n_1700_B(inputstream);
        }
        catch (Throwable throwable) {
            try {
                try {
                    ((InputStream)inputstream).close();
                }
                catch (Throwable throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
            catch (FileNotFoundException filenotfoundexception) {
                return null;
            }
            catch (IOException ioexception) {
                n_1700_B.error("Couldn't load structure from {}", (Object)path, (Object)ioexception);
                return null;
            }
        }
        ((InputStream)inputstream).close();
        return a_2886_t2;
    }

    private a_2886_t n_1700_B(InputStream inputStreamIn) throws IOException {
        U_2912_j compoundnbt = r_1827_u.n_1700_B(inputStreamIn);
        return this.n_1700_B(compoundnbt);
    }

    public a_2886_t n_1700_B(U_2912_j p_227458_1_) {
        if (!p_227458_1_.R_4764_Y("DataVersion", 99)) {
            p_227458_1_.J_1907_R("DataVersion", 500);
        }
        a_2886_t template = new a_2886_t();
        template.J_1907_R(n_3832_I.n_1700_B(this.R_4764_Y, o_1967_f.u_1723_Y, p_227458_1_, p_227458_1_.w_1484_f("DataVersion")));
        return template;
    }

    public boolean R_4764_Y(g_2336_b templateName) {
        boolean bl;
        a_2886_t template = this.J_1907_R.get(templateName);
        if (template == null) {
            return false;
        }
        Path path = this.J_1907_R(templateName, ".nbt");
        Path path1 = path.getParent();
        if (path1 == null) {
            return false;
        }
        try {
            Files.createDirectories(Files.exists(path1, new LinkOption[0]) ? path1.toRealPath(new LinkOption[0]) : path1, new FileAttribute[0]);
        }
        catch (IOException ioexception) {
            n_1700_B.error("Failed to create parent directory: {}", (Object)path1);
            return false;
        }
        U_2912_j compoundnbt = template.n_1700_B(new U_2912_j());
        FileOutputStream outputstream = new FileOutputStream(path.toFile());
        try {
            r_1827_u.n_1700_B(compoundnbt, outputstream);
            bl = true;
        }
        catch (Throwable throwable) {
            try {
                try {
                    ((OutputStream)outputstream).close();
                }
                catch (Throwable throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
            catch (Throwable throwable3) {
                return false;
            }
        }
        ((OutputStream)outputstream).close();
        return bl;
    }

    public Path n_1700_B(g_2336_b locationIn, String extIn) {
        try {
            Path path = this.P_1922_E.resolve(locationIn.R_4764_Y());
            Path path1 = path.resolve("structures");
            return r_146_S.J_1907_R(path1, locationIn.J_1907_R(), extIn);
        }
        catch (InvalidPathException invalidpathexception) {
            throw new s_3109_F("Invalid resource path: " + String.valueOf(locationIn), invalidpathexception);
        }
    }

    private Path J_1907_R(g_2336_b locationIn, String extIn) {
        if (locationIn.J_1907_R().contains("//")) {
            throw new s_3109_F("Invalid resource path: " + String.valueOf(locationIn));
        }
        Path path = this.n_1700_B(locationIn, extIn);
        if (path.startsWith(this.P_1922_E) && r_146_S.n_1700_B(path) && r_146_S.J_1907_R(path)) {
            return path;
        }
        throw new s_3109_F("Invalid resource path: " + String.valueOf(path));
    }

    public void G_564_y(g_2336_b templatePath) {
        this.J_1907_R.remove(templatePath);
    }
}



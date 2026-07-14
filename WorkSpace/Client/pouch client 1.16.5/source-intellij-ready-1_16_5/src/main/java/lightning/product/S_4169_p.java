/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.A_885_K;
import lightning.product.T_335_n;
import lightning.product.PackResources;
import lightning.product.g_2336_b;
import lightning.product.i_4221_J;
import lightning.product.j_3341_s;
import lightning.product.r_2139_P;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class S_4169_p
implements PackResources {
    public static Path n_1700_B;
    private static final Logger G_564_y;
    public static Class<?> J_1907_R;
    private static final Map<i_4221_J, FileSystem> P_1922_E;
    public final Set<String> R_4764_Y;
    private static final boolean u_1723_Y;
    private static final boolean v_4262_N;

    public S_4169_p(String ... resourceNamespacesIn) {
        this.R_4764_Y = ImmutableSet.copyOf((Object[])resourceNamespacesIn);
    }

    @Override
    public InputStream getRootResourceStream(String fileName) throws IOException {
        if (!fileName.contains("/") && !fileName.contains("\\")) {
            Path path;
            if (n_1700_B != null && Files.exists(path = n_1700_B.resolve(fileName), new LinkOption[0])) {
                return Files.newInputStream(path, new OpenOption[0]);
            }
            return this.n_1700_B(fileName);
        }
        throw new IllegalArgumentException("Root resources can only be filenames, not paths (no / allowed!)");
    }

    @Override
    public InputStream getResourceStream(i_4221_J type, g_2336_b location) throws IOException {
        InputStream inputstream = this.n_1700_B(type, location);
        if (inputstream != null) {
            return inputstream;
        }
        throw new FileNotFoundException(location.J_1907_R());
    }

    @Override
    public Collection<g_2336_b> getAllResourceLocations(i_4221_J type, String namespaceIn, String pathIn, int maxDepthIn, Predicate<String> filterIn) {
        HashSet set = Sets.newHashSet();
        if (n_1700_B != null) {
            try {
                S_4169_p.n_1700_B(set, maxDepthIn, namespaceIn, n_1700_B.resolve(type.n_1700_B()), pathIn, filterIn);
            }
            catch (IOException iOException) {
                // empty catch block
            }
            if (type == i_4221_J.n_1700_B) {
                Enumeration<URL> enumeration = null;
                try {
                    enumeration = J_1907_R.getClassLoader().getResources(type.n_1700_B() + "/");
                }
                catch (IOException iOException) {
                    // empty catch block
                }
                while (enumeration != null && enumeration.hasMoreElements()) {
                    try {
                        URI uri = enumeration.nextElement().toURI();
                        if (!"file".equals(uri.getScheme())) continue;
                        S_4169_p.n_1700_B(set, maxDepthIn, namespaceIn, Paths.get(uri), pathIn, filterIn);
                    }
                    catch (IOException | URISyntaxException uri) {}
                }
            }
        }
        try {
            URL url1 = S_4169_p.class.getResource("/" + type.n_1700_B() + "/.mcassetsroot");
            if (url1 == null) {
                G_564_y.error("Couldn't find .mcassetsroot, cannot load vanilla resources");
                return set;
            }
            URI uri1 = url1.toURI();
            if ("file".equals(uri1.getScheme())) {
                URL url = new URL(url1.toString().substring(0, url1.toString().length() - ".mcassetsroot".length()));
                Path path = Paths.get(url.toURI());
                S_4169_p.n_1700_B(set, maxDepthIn, namespaceIn, path, pathIn, filterIn);
            } else if ("jar".equals(uri1.getScheme())) {
                Path path1 = P_1922_E.get((Object)type).getPath("/" + type.n_1700_B(), new String[0]);
                S_4169_p.n_1700_B(set, maxDepthIn, "minecraft", path1, pathIn, filterIn);
            } else {
                G_564_y.error("Unsupported scheme {} trying to list vanilla resources (NYI?)", (Object)uri1);
            }
        }
        catch (FileNotFoundException | NoSuchFileException url1) {
        }
        catch (IOException | URISyntaxException ioexception) {
            G_564_y.error("Couldn't get a list of all vanilla resources", (Throwable)ioexception);
        }
        return set;
    }

    private static void n_1700_B(Collection<g_2336_b> resourceLocationsIn, int maxDepthIn, String namespaceIn, Path pathIn, String pathNameIn, Predicate<String> filterIn) throws IOException {
        Path path = pathIn.resolve(namespaceIn);
        try (Stream<Path> stream = Files.walk(path.resolve(pathNameIn), maxDepthIn, new FileVisitOption[0]);){
            stream.filter(p_lambda$collectResources$1_1_ -> !p_lambda$collectResources$1_1_.endsWith(".mcmeta") && Files.isRegularFile(p_lambda$collectResources$1_1_, new LinkOption[0]) && filterIn.test(p_lambda$collectResources$1_1_.getFileName().toString())).map(p_lambda$collectResources$2_2_ -> new g_2336_b(namespaceIn, path.relativize((Path)p_lambda$collectResources$2_2_).toString().replaceAll("\\\\", "/"))).forEach(resourceLocationsIn::add);
        }
    }

    @Nullable
    protected InputStream n_1700_B(i_4221_J type, g_2336_b location) {
        Path path;
        String s = S_4169_p.J_1907_R(type, location);
        InputStream inputstream = ReflectorForge.getOptiFineResourceStream(s);
        if (inputstream != null) {
            return inputstream;
        }
        if (n_1700_B != null && Files.exists(path = n_1700_B.resolve(type.n_1700_B() + "/" + location.R_4764_Y() + "/" + location.J_1907_R()), new LinkOption[0])) {
            try {
                return Files.newInputStream(path, new OpenOption[0]);
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        try {
            URL url = S_4169_p.class.getResource(s);
            return S_4169_p.n_1700_B(s, url) ? (v_4262_N ? this.n_1700_B(type, s) : url.openStream()) : null;
        }
        catch (IOException ioexception1) {
            return S_4169_p.class.getResourceAsStream(s);
        }
    }

    private static String J_1907_R(i_4221_J packTypeIn, g_2336_b locationIn) {
        return "/" + packTypeIn.n_1700_B() + "/" + locationIn.R_4764_Y() + "/" + locationIn.J_1907_R();
    }

    private static boolean n_1700_B(String pathIn, @Nullable URL urlIn) throws IOException {
        return urlIn != null && (urlIn.getProtocol().equals("jar") || S_4169_p.n_1700_B(new File(urlIn.getFile()), pathIn));
    }

    @Nullable
    protected InputStream n_1700_B(String pathIn) {
        return v_4262_N ? this.n_1700_B(i_4221_J.J_1907_R, "/" + pathIn) : S_4169_p.class.getResourceAsStream("/" + pathIn);
    }

    @Override
    public boolean resourceExists(i_4221_J type, g_2336_b location) {
        Path path;
        String s = S_4169_p.J_1907_R(type, location);
        InputStream inputstream = ReflectorForge.getOptiFineResourceStream(s);
        if (inputstream != null) {
            return true;
        }
        if (n_1700_B != null && Files.exists(path = n_1700_B.resolve(type.n_1700_B() + "/" + location.R_4764_Y() + "/" + location.J_1907_R()), new LinkOption[0])) {
            return true;
        }
        try {
            URL url = S_4169_p.class.getResource(s);
            return S_4169_p.n_1700_B(s, url);
        }
        catch (IOException ioexception1) {
            return false;
        }
    }

    @Override
    public Set<String> getResourceNamespaces(i_4221_J type) {
        return this.R_4764_Y;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    @Nullable
    public <T> T getMetadata(T_335_n<T> deserializer) throws IOException {
        try (InputStream inputstream = this.getRootResourceStream("pack.mcmeta");){
            T t = r_2139_P.getResourceMetadata(deserializer, inputstream);
            return t;
        }
        catch (FileNotFoundException | RuntimeException filenotfoundexception) {
            return null;
        }
    }

    @Override
    public String getName() {
        return "Default";
    }

    @Override
    public void close() {
    }

    private static boolean n_1700_B(File p_validatePath_0_, String p_validatePath_1_) throws IOException {
        String s = p_validatePath_0_.getPath();
        if (s.startsWith("file:")) {
            if (u_1723_Y) {
                s = s.replace("\\", "/");
            }
            return s.endsWith(p_validatePath_1_);
        }
        return A_885_K.n_1700_B(p_validatePath_0_, p_validatePath_1_);
    }

    private InputStream n_1700_B(i_4221_J p_getExtraInputStream_1_, String p_getExtraInputStream_2_) {
        try {
            FileSystem filesystem = P_1922_E.get((Object)p_getExtraInputStream_1_);
            return filesystem != null ? Files.newInputStream(filesystem.getPath(p_getExtraInputStream_2_, new String[0]), new OpenOption[0]) : S_4169_p.class.getResourceAsStream(p_getExtraInputStream_2_);
        }
        catch (IOException ioexception) {
            return S_4169_p.class.getResourceAsStream(p_getExtraInputStream_2_);
        }
    }

    static {
        G_564_y = LogManager.getLogger();
        P_1922_E = j_3341_s.n_1700_B(Maps.newHashMap(), (T p_lambda$static$0_0_) -> {
            Class<S_4169_p> clazz = S_4169_p.class;
            synchronized (S_4169_p.class) {
                for (i_4221_J resourcepacktype : i_4221_J.values()) {
                    URL url = S_4169_p.class.getResource("/" + resourcepacktype.n_1700_B() + "/.mcassetsroot");
                    try {
                        FileSystem filesystem;
                        URI uri = url.toURI();
                        if (!"jar".equals(uri.getScheme())) continue;
                        try {
                            filesystem = FileSystems.getFileSystem(uri);
                        }
                        catch (FileSystemNotFoundException filesystemnotfoundexception) {
                            filesystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
                        }
                        p_lambda$static$0_0_.put(resourcepacktype, filesystem);
                    }
                    catch (IOException | URISyntaxException ioexception) {
                        G_564_y.error("Couldn't get a list of all vanilla resources", (Throwable)ioexception);
                    }
                }
                // ** MonitorExit[var1_1] (shouldn't be in output)
                return;
            }
        });
        u_1723_Y = j_3341_s.t_148_a() == j_3341_s.J_1907_R.R_4764_Y;
        v_4262_N = Reflector.ForgeHooksClient.exists();
    }
}



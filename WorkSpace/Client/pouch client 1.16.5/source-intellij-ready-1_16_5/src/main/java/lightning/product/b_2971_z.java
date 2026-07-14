/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.io.Files
 *  com.mojang.datafixers.DataFixer
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.Lifecycle
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Lifecycle;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.annotation.Nullable;
import lightning.product.B_4315_y;
import lightning.product.F_2904_S;
import lightning.product.F_491_v;
import lightning.product.H_1033_y;
import lightning.product.H_4757_Q;
import lightning.product.SharedConstants;
import lightning.product.J_2011_a;
import lightning.product.References;
import lightning.product.P_1103_o;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.Tag;
import lightning.product.Z_3903_F;
import lightning.product.Z_4308_L;
import lightning.product.b_4507_u;
import lightning.product.c_3102_J;
import lightning.product.f_2392_k;
import lightning.product.DataPackConfig;
import lightning.product.i_3199_H;
import lightning.product.ProgressListener;
import lightning.product.j_3341_s;
import lightning.product.j_419_j;
import lightning.product.l_4118_l;
import lightning.product.WorldData;
import lightning.product.o_1967_f;
import lightning.product.q_3631_C;
import lightning.product.r_146_S;
import lightning.product.r_1827_u;
import lightning.product.r_4097_j;
import net.minecraft.server.v_4262_N;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class b_2971_z {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final DateTimeFormatter J_1907_R = new DateTimeFormatterBuilder().appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral('-').appendValue(ChronoField.MONTH_OF_YEAR, 2).appendLiteral('-').appendValue(ChronoField.DAY_OF_MONTH, 2).appendLiteral('_').appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral('-').appendValue(ChronoField.MINUTE_OF_HOUR, 2).appendLiteral('-').appendValue(ChronoField.SECOND_OF_MINUTE, 2).toFormatter();
    private static final ImmutableList<String> R_4764_Y = ImmutableList.of((Object)"RandomSeed", (Object)"generatorName", (Object)"generatorOptions", (Object)"generatorVersion", (Object)"legacy_custom_options", (Object)"MapFeatures", (Object)"BonusChest");
    private final Path G_564_y;
    private final Path P_1922_E;
    private final DataFixer u_1723_Y;

    public b_2971_z(Path savesDir, Path backupsDir, DataFixer dataFixer) {
        this.u_1723_Y = dataFixer;
        try {
            Files.createDirectories(Files.exists(savesDir, new LinkOption[0]) ? savesDir.toRealPath(new LinkOption[0]) : savesDir, new FileAttribute[0]);
        }
        catch (IOException ioexception) {
            throw new RuntimeException(ioexception);
        }
        this.G_564_y = savesDir;
        this.P_1922_E = backupsDir;
    }

    public static b_2971_z n_1700_B(Path savesDir) {
        return new b_2971_z(savesDir, savesDir.resolve("../backups"), F_491_v.n_1700_B());
    }

    private static <T> Pair<j_419_j, Lifecycle> n_1700_B(Dynamic<T> nbt, DataFixer fixer, int version) {
        Dynamic dynamic = nbt.get("WorldGenSettings").orElseEmptyMap();
        for (String s : R_4764_Y) {
            Optional optional = nbt.get(s).result();
            if (!optional.isPresent()) continue;
            dynamic = dynamic.set(s, (Dynamic)optional.get());
        }
        Dynamic dynamic1 = fixer.update(References.q_2307_F, dynamic, version, SharedConstants.n_1700_B().getWorldVersion());
        DataResult dataresult = j_419_j.n_1700_B.parse(dynamic1);
        return Pair.of((Object)dataresult.resultOrPartial(j_3341_s.n_1700_B("WorldGenSettings: ", arg_0 -> ((Logger)n_1700_B).error(arg_0))).orElseGet(() -> {
            V_3137_a registry = (V_3137_a)P_1103_o.n_1700_B(V_3137_a.d_2427_y).codec().parse(dynamic1).resultOrPartial(j_3341_s.n_1700_B("Dimension type registry: ", arg_0 -> ((Logger)n_1700_B).error(arg_0))).orElseThrow(() -> new IllegalStateException("Failed to get dimension registry"));
            V_3137_a registry1 = (V_3137_a)P_1103_o.n_1700_B(V_3137_a.PlayerInfo).codec().parse(dynamic1).resultOrPartial(j_3341_s.n_1700_B("Biome registry: ", arg_0 -> ((Logger)n_1700_B).error(arg_0))).orElseThrow(() -> new IllegalStateException("Failed to get biome registry"));
            V_3137_a registry2 = (V_3137_a)P_1103_o.n_1700_B(V_3137_a.e_1992_r).codec().parse(dynamic1).resultOrPartial(j_3341_s.n_1700_B("Noise settings registry: ", arg_0 -> ((Logger)n_1700_B).error(arg_0))).orElseThrow(() -> new IllegalStateException("Failed to get noise settings registry"));
            return j_419_j.n_1700_B((V_3137_a<Z_3903_F>)registry, registry1, registry2);
        }), (Object)dataresult.lifecycle());
    }

    private static DataPackConfig n_1700_B(Dynamic<?> nbt) {
        return DataPackConfig.J_1907_R.parse(nbt).resultOrPartial(arg_0 -> ((Logger)n_1700_B).error(arg_0)).orElse(DataPackConfig.n_1700_B);
    }

    public List<J_2011_a> n_1700_B() throws i_3199_H {
        File[] afile;
        if (!Files.isDirectory(this.G_564_y, new LinkOption[0])) {
            throw new i_3199_H(new F_2904_S("selectWorld.load_folder_access").getString());
        }
        ArrayList list = Lists.newArrayList();
        for (File file1 : afile = this.G_564_y.toFile().listFiles()) {
            boolean flag;
            if (!file1.isDirectory()) continue;
            try {
                flag = v_4262_N.J_1907_R(file1.toPath());
            }
            catch (Exception exception) {
                n_1700_B.warn("Failed to read {} lock", (Object)file1, (Object)exception);
                continue;
            }
            J_2011_a worldsummary = this.n_1700_B(file1, this.n_1700_B(file1, flag));
            if (worldsummary == null) continue;
            list.add(worldsummary);
        }
        return list;
    }

    private int G_564_y() {
        return 19133;
    }

    @Nullable
    private <T> T n_1700_B(File saveDir, BiFunction<File, DataFixer, T> levelDatReader) {
        T t;
        if (!saveDir.exists()) {
            return null;
        }
        File file1 = new File(saveDir, "level.dat");
        if (file1.exists() && (t = levelDatReader.apply(file1, this.u_1723_Y)) != null) {
            return t;
        }
        file1 = new File(saveDir, "level.dat_old");
        return file1.exists() ? (T)levelDatReader.apply(file1, this.u_1723_Y) : null;
    }

    @Nullable
    private static DataPackConfig n_1700_B(File levelDat, DataFixer fixer) {
        try {
            U_2912_j compoundnbt = r_1827_u.n_1700_B(levelDat);
            U_2912_j compoundnbt1 = compoundnbt.M_182_A("Data");
            compoundnbt1.multiplayerClientSuggestionProvider("Player");
            int i = compoundnbt1.R_4764_Y("DataVersion", 99) ? compoundnbt1.w_1484_f("DataVersion") : -1;
            Dynamic dynamic = fixer.update(o_1967_f.n_1700_B.n_1700_B(), new Dynamic((DynamicOps)l_4118_l.n_1700_B, (Object)compoundnbt1), i, SharedConstants.n_1700_B().getWorldVersion());
            return dynamic.get("DataPacks").result().map(b_2971_z::n_1700_B).orElse(DataPackConfig.n_1700_B);
        }
        catch (Exception exception) {
            n_1700_B.error("Exception reading {}", (Object)levelDat, (Object)exception);
            return null;
        }
    }

    private static BiFunction<File, DataFixer, c_3102_J> n_1700_B(DynamicOps<Tag> nbt, DataPackConfig datapackCodec) {
        return (file, fixer) -> {
            try {
                U_2912_j compoundnbt = r_1827_u.n_1700_B(file);
                U_2912_j compoundnbt1 = compoundnbt.M_182_A("Data");
                U_2912_j compoundnbt2 = compoundnbt1.R_4764_Y("Player", 10) ? compoundnbt1.M_182_A("Player") : null;
                compoundnbt1.multiplayerClientSuggestionProvider("Player");
                int i = compoundnbt1.R_4764_Y("DataVersion", 99) ? compoundnbt1.w_1484_f("DataVersion") : -1;
                Dynamic dynamic = fixer.update(o_1967_f.n_1700_B.n_1700_B(), new Dynamic(nbt, (Object)compoundnbt1), i, SharedConstants.n_1700_B().getWorldVersion());
                Pair<j_419_j, Lifecycle> pair = b_2971_z.n_1700_B(dynamic, fixer, i);
                H_1033_y versiondata = H_1033_y.n_1700_B(dynamic);
                B_4315_y worldsettings = B_4315_y.n_1700_B(dynamic, datapackCodec);
                return c_3102_J.n_1700_B((Dynamic<Tag>)dynamic, fixer, i, compoundnbt2, worldsettings, versiondata, (j_419_j)pair.getFirst(), (Lifecycle)pair.getSecond());
            }
            catch (Exception exception) {
                n_1700_B.error("Exception reading {}", file, (Object)exception);
                return null;
            }
        };
    }

    private BiFunction<File, DataFixer, J_2011_a> n_1700_B(File saveDir, boolean locked) {
        return (file, fixer) -> {
            try {
                U_2912_j compoundnbt = r_1827_u.n_1700_B(file);
                U_2912_j compoundnbt1 = compoundnbt.M_182_A("Data");
                compoundnbt1.multiplayerClientSuggestionProvider("Player");
                int i = compoundnbt1.R_4764_Y("DataVersion", 99) ? compoundnbt1.w_1484_f("DataVersion") : -1;
                Dynamic dynamic = fixer.update(o_1967_f.n_1700_B.n_1700_B(), new Dynamic((DynamicOps)l_4118_l.n_1700_B, (Object)compoundnbt1), i, SharedConstants.n_1700_B().getWorldVersion());
                H_1033_y versiondata = H_1033_y.n_1700_B(dynamic);
                int j = versiondata.n_1700_B();
                if (j != 19132 && j != 19133) {
                    return null;
                }
                boolean flag = j != this.G_564_y();
                File file1 = new File(saveDir, "icon.png");
                DataPackConfig datapackcodec = dynamic.get("DataPacks").result().map(b_2971_z::n_1700_B).orElse(DataPackConfig.n_1700_B);
                B_4315_y worldsettings = B_4315_y.n_1700_B(dynamic, datapackcodec);
                return new J_2011_a(worldsettings, versiondata, saveDir.getName(), flag, locked, file1);
            }
            catch (Exception exception) {
                n_1700_B.error("Exception reading {}", file, (Object)exception);
                return null;
            }
        };
    }

    public boolean n_1700_B(String saveName) {
        try {
            Path path = this.G_564_y.resolve(saveName);
            Files.createDirectory(path, new FileAttribute[0]);
            Files.deleteIfExists(path);
            return true;
        }
        catch (IOException ioexception) {
            return false;
        }
    }

    public boolean J_1907_R(String saveName) {
        return Files.isDirectory(this.G_564_y.resolve(saveName), new LinkOption[0]);
    }

    public Path J_1907_R() {
        return this.G_564_y;
    }

    public Path R_4764_Y() {
        return this.P_1922_E;
    }

    public n_1700_B R_4764_Y(String saveName) throws IOException {
        return new n_1700_B(saveName);
    }

    public class n_1700_B
    implements AutoCloseable {
        private final v_4262_N J_1907_R;
        private final Path R_4764_Y;
        private final String G_564_y;
        private final Map<H_4757_Q, Path> P_1922_E = Maps.newHashMap();

        public n_1700_B(String saveName) throws IOException {
            this.G_564_y = saveName;
            this.R_4764_Y = b_2971_z.this.G_564_y.resolve(saveName);
            this.J_1907_R = v_4262_N.n_1700_B(this.R_4764_Y);
        }

        public String n_1700_B() {
            return this.G_564_y;
        }

        public Path n_1700_B(H_4757_Q folderName) {
            return this.P_1922_E.computeIfAbsent(folderName, folder -> this.R_4764_Y.resolve(folder.n_1700_B()));
        }

        public File n_1700_B(f_2392_k<b_4507_u> dimensionKey) {
            return Z_3903_F.n_1700_B(dimensionKey, this.R_4764_Y.toFile());
        }

        private void t_148_a() {
            if (!this.J_1907_R.n_1700_B()) {
                throw new IllegalStateException("Lock is no longer valid");
            }
        }

        public Z_4308_L J_1907_R() {
            this.t_148_a();
            return new Z_4308_L(this, b_2971_z.this.u_1723_Y);
        }

        public boolean R_4764_Y() {
            J_2011_a worldsummary = this.G_564_y();
            return worldsummary != null && worldsummary.s_956_w().n_1700_B() != b_2971_z.this.G_564_y();
        }

        public boolean n_1700_B(ProgressListener progress) {
            this.t_148_a();
            return q_3631_C.n_1700_B(this, progress);
        }

        @Nullable
        public J_2011_a G_564_y() {
            this.t_148_a();
            return b_2971_z.this.n_1700_B(this.R_4764_Y.toFile(), b_2971_z.this.n_1700_B(this.R_4764_Y.toFile(), false));
        }

        @Nullable
        public WorldData n_1700_B(DynamicOps<Tag> nbt, DataPackConfig datapackCodec) {
            this.t_148_a();
            return b_2971_z.this.n_1700_B(this.R_4764_Y.toFile(), b_2971_z.n_1700_B(nbt, datapackCodec));
        }

        @Nullable
        public DataPackConfig P_1922_E() {
            this.t_148_a();
            return b_2971_z.this.n_1700_B(this.R_4764_Y.toFile(), (File levelDatFile, DataFixer dataFixer) -> b_2971_z.n_1700_B(levelDatFile, dataFixer));
        }

        public void n_1700_B(r_4097_j registries, WorldData serverConfiguration) {
            this.n_1700_B(registries, serverConfiguration, null);
        }

        public void n_1700_B(r_4097_j registries, WorldData serverConfiguration, @Nullable U_2912_j hostPlayerNBT) {
            File file1 = this.R_4764_Y.toFile();
            U_2912_j compoundnbt = serverConfiguration.n_1700_B(registries, hostPlayerNBT);
            U_2912_j compoundnbt1 = new U_2912_j();
            compoundnbt1.n_1700_B("Data", compoundnbt);
            try {
                File file2 = File.createTempFile("level", ".dat", file1);
                r_1827_u.n_1700_B(compoundnbt1, file2);
                File file3 = new File(file1, "level.dat_old");
                File file4 = new File(file1, "level.dat");
                j_3341_s.n_1700_B(file4, file2, file3);
            }
            catch (Exception exception) {
                n_1700_B.error("Failed to save level {}", (Object)file1, (Object)exception);
            }
        }

        public File u_1723_Y() {
            this.t_148_a();
            return this.R_4764_Y.resolve("icon.png").toFile();
        }

        public void v_4262_N() throws IOException {
            this.t_148_a();
            final Path path = this.R_4764_Y.resolve("session.lock");
            for (int i = 1; i <= 5; ++i) {
                n_1700_B.info("Attempt {}...", (Object)i);
                try {
                    Files.walkFileTree(this.R_4764_Y, (FileVisitor<? super Path>)new SimpleFileVisitor<Path>(){

                        public FileVisitResult n_1700_B(Path p_visitFile_1_, BasicFileAttributes p_visitFile_2_) throws IOException {
                            if (!p_visitFile_1_.equals(path)) {
                                b_2971_z.n_1700_B.debug("Deleting {}", (Object)p_visitFile_1_);
                                Files.delete(p_visitFile_1_);
                            }
                            return FileVisitResult.CONTINUE;
                        }

                        public FileVisitResult n_1700_B(Path p_postVisitDirectory_1_, IOException p_postVisitDirectory_2_) throws IOException {
                            if (p_postVisitDirectory_2_ != null) {
                                throw p_postVisitDirectory_2_;
                            }
                            if (p_postVisitDirectory_1_.equals(n_1700_B.this.R_4764_Y)) {
                                n_1700_B.this.J_1907_R.close();
                                Files.deleteIfExists(path);
                            }
                            Files.delete(p_postVisitDirectory_1_);
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public /* synthetic */ FileVisitResult postVisitDirectory(Object object, IOException iOException) throws IOException {
                            return this.n_1700_B((Path)object, iOException);
                        }

                        @Override
                        public /* synthetic */ FileVisitResult visitFile(Object object, BasicFileAttributes basicFileAttributes) throws IOException {
                            return this.n_1700_B((Path)object, basicFileAttributes);
                        }
                    });
                    break;
                }
                catch (IOException ioexception) {
                    if (i >= 5) {
                        throw ioexception;
                    }
                    n_1700_B.warn("Failed to delete {}", (Object)this.R_4764_Y, (Object)ioexception);
                    try {
                        Thread.sleep(500L);
                    }
                    catch (InterruptedException interruptedException) {
                        // empty catch block
                    }
                    continue;
                }
            }
        }

        public void n_1700_B(String saveName) throws IOException {
            File file2;
            this.t_148_a();
            File file1 = new File(b_2971_z.this.G_564_y.toFile(), this.G_564_y);
            if (file1.exists() && (file2 = new File(file1, "level.dat")).exists()) {
                U_2912_j compoundnbt = r_1827_u.n_1700_B(file2);
                U_2912_j compoundnbt1 = compoundnbt.M_182_A("Data");
                compoundnbt1.n_1700_B("LevelName", saveName);
                r_1827_u.n_1700_B(compoundnbt, file2);
            }
        }

        public long w_1484_f() throws IOException {
            this.t_148_a();
            String s = LocalDateTime.now().format(J_1907_R) + "_" + this.G_564_y;
            Path path = b_2971_z.this.R_4764_Y();
            try {
                Files.createDirectories(Files.exists(path, new LinkOption[0]) ? path.toRealPath(new LinkOption[0]) : path, new FileAttribute[0]);
            }
            catch (IOException ioexception) {
                throw new RuntimeException(ioexception);
            }
            Path path1 = path.resolve(r_146_S.n_1700_B(path, s, ".zip"));
            try (final ZipOutputStream zipoutputstream = new ZipOutputStream(new BufferedOutputStream(Files.newOutputStream(path1, new OpenOption[0])));){
                final Path path2 = Paths.get(this.G_564_y, new String[0]);
                Files.walkFileTree(this.R_4764_Y, (FileVisitor<? super Path>)new SimpleFileVisitor<Path>(){

                    public FileVisitResult n_1700_B(Path p_visitFile_1_, BasicFileAttributes p_visitFile_2_) throws IOException {
                        if (p_visitFile_1_.endsWith("session.lock")) {
                            return FileVisitResult.CONTINUE;
                        }
                        String s1 = path2.resolve(n_1700_B.this.R_4764_Y.relativize(p_visitFile_1_)).toString().replace('\\', '/');
                        ZipEntry zipentry = new ZipEntry(s1);
                        zipoutputstream.putNextEntry(zipentry);
                        com.google.common.io.Files.asByteSource((File)p_visitFile_1_.toFile()).copyTo((OutputStream)zipoutputstream);
                        zipoutputstream.closeEntry();
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public /* synthetic */ FileVisitResult visitFile(Object object, BasicFileAttributes basicFileAttributes) throws IOException {
                        return this.n_1700_B((Path)object, basicFileAttributes);
                    }
                });
            }
            return Files.size(path1);
        }

        @Override
        public void close() throws IOException {
            this.J_1907_R.close();
        }
    }
}



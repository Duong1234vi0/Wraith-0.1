/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 *  org.apache.commons.codec.digest.DigestUtils
 *  org.apache.commons.io.FileUtils
 *  org.apache.commons.io.comparator.LastModifiedFileComparator
 *  org.apache.commons.io.filefilter.IOFileFilter
 *  org.apache.commons.io.filefilter.TrueFileFilter
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import lightning.product.A_885_K;
import lightning.product.D_2103_L;
import lightning.product.F_2904_S;
import lightning.product.H_3999_U;
import lightning.product.I_2946_k;
import lightning.product.SharedConstants;
import lightning.product.O_2332_X;
import lightning.product.S_4169_p;
import lightning.product.PackMetadataSection;
import lightning.product.PackResources;
import lightning.product.MinecraftClient;
import lightning.product.c_817_f;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.PackSource;
import lightning.product.u_4608_G;
import lightning.product.u_4650_L;
import lightning.product.x_1356_s;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class L_1105_I
implements I_2946_k {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final Pattern J_1907_R = Pattern.compile("^[a-fA-F0-9]{40}$");
    private final S_4169_p R_4764_Y;
    private final File G_564_y;
    private final ReentrantLock P_1922_E = new ReentrantLock();
    private final x_1356_s u_1723_Y;
    @Nullable
    private CompletableFuture<?> v_4262_N;
    @Nullable
    private D_2103_L w_1484_f;

    public L_1105_I(File serverPackDirIn, x_1356_s resourceIndexIn) {
        this.G_564_y = serverPackDirIn;
        this.u_1723_Y = resourceIndexIn;
        this.R_4764_Y = new c_817_f(resourceIndexIn);
    }

    @Override
    public void n_1700_B(Consumer<D_2103_L> infoConsumer, D_2103_L.n_1700_B infoFactory) {
        D_2103_L resourcepackinfo1;
        D_2103_L resourcepackinfo = D_2103_L.n_1700_B("vanilla", true, () -> this.R_4764_Y, infoFactory, D_2103_L.J_1907_R.J_1907_R, PackSource.J_1907_R);
        if (resourcepackinfo != null) {
            infoConsumer.accept(resourcepackinfo);
        }
        if (this.w_1484_f != null) {
            infoConsumer.accept(this.w_1484_f);
        }
        if ((resourcepackinfo1 = this.n_1700_B(infoFactory)) != null) {
            infoConsumer.accept(resourcepackinfo1);
        }
    }

    public S_4169_p n_1700_B() {
        return this.R_4764_Y;
    }

    private static Map<String, String> R_4764_Y() {
        HashMap map = Maps.newHashMap();
        map.put("X-Minecraft-Username", MinecraftClient.A_4115_X().z_1737_N().R_4764_Y());
        map.put("X-Minecraft-UUID", MinecraftClient.A_4115_X().z_1737_N().J_1907_R());
        map.put("X-Minecraft-Version", SharedConstants.n_1700_B().getName());
        map.put("X-Minecraft-Version-ID", SharedConstants.n_1700_B().getId());
        map.put("X-Minecraft-Pack-Format", String.valueOf(SharedConstants.n_1700_B().getPackVersion()));
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        map.put("Accept", "*/*");
        map.put("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        map.put("Accept-Encoding", "identity");
        return map;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CompletableFuture<?> n_1700_B(String url, String hash) {
        CompletableFuture<?> completablefuture1;
        String s = DigestUtils.sha1Hex((String)url);
        String s1 = J_1907_R.matcher(hash).matches() ? hash : "";
        this.P_1922_E.lock();
        try {
            CompletableFuture<String> completablefuture;
            this.J_1907_R();
            this.G_564_y();
            File file1 = new File(this.G_564_y, s);
            if (file1.exists()) {
                completablefuture = CompletableFuture.completedFuture("");
            } else {
                u_4650_L workingscreen = new u_4650_L();
                Map<String, String> map = L_1105_I.R_4764_Y();
                MinecraftClient minecraft = MinecraftClient.A_4115_X();
                minecraft.v_4262_N(() -> minecraft.n_1700_B(workingscreen));
                completablefuture = O_2332_X.n_1700_B(file1, url, map, 0x6400000, workingscreen, minecraft.d_2461_k());
            }
            completablefuture1 = this.v_4262_N = ((CompletableFuture)completablefuture.thenCompose(p_217812_3_ -> !this.n_1700_B(s1, file1) ? j_3341_s.n_1700_B((Throwable)new RuntimeException("Hash check failure for file " + String.valueOf(file1) + ", see log")) : this.n_1700_B(file1, PackSource.G_564_y))).whenComplete((p_217815_1_, p_217815_2_) -> {
                if (p_217815_2_ != null) {
                    n_1700_B.warn("Pack application failed: {}, deleting file {}", (Object)p_217815_2_.getMessage(), (Object)file1);
                    L_1105_I.n_1700_B(file1);
                }
            });
        }
        finally {
            this.P_1922_E.unlock();
        }
        return completablefuture1;
    }

    private static void n_1700_B(File fileIn) {
        try {
            Files.delete(fileIn.toPath());
        }
        catch (IOException ioexception) {
            n_1700_B.warn("Failed to delete file {}: {}", (Object)fileIn, (Object)ioexception.getMessage());
        }
    }

    public void J_1907_R() {
        this.P_1922_E.lock();
        try {
            if (this.v_4262_N != null) {
                this.v_4262_N.cancel(true);
            }
            this.v_4262_N = null;
            if (this.w_1484_f != null) {
                this.w_1484_f = null;
                MinecraftClient.A_4115_X().Y_1740_V();
            }
        }
        finally {
            this.P_1922_E.unlock();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean n_1700_B(String expectedHash, File fileIn) {
        try (FileInputStream fileinputstream = new FileInputStream(fileIn);){
            String s = DigestUtils.sha1Hex((InputStream)fileinputstream);
            if (expectedHash.isEmpty()) {
                n_1700_B.info("Found file {} without verification hash", (Object)fileIn);
                boolean bl = true;
                return bl;
            }
            if (s.toLowerCase(Locale.ROOT).equals(expectedHash.toLowerCase(Locale.ROOT))) {
                n_1700_B.info("Found file {} matching requested hash {}", (Object)fileIn, (Object)expectedHash);
                boolean bl = true;
                return bl;
            }
            n_1700_B.warn("File {} had wrong hash (expected {}, found {}).", (Object)fileIn, (Object)expectedHash, (Object)s);
            return false;
        }
        catch (IOException ioexception) {
            n_1700_B.warn("File {} couldn't be hashed.", (Object)fileIn, (Object)ioexception);
        }
        return false;
    }

    private void G_564_y() {
        try {
            ArrayList list = Lists.newArrayList((Iterable)FileUtils.listFiles((File)this.G_564_y, (IOFileFilter)TrueFileFilter.TRUE, (IOFileFilter)null));
            list.sort(LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            int i = 0;
            for (File file1 : list) {
                if (i++ < 10) continue;
                n_1700_B.info("Deleting old server resource pack {}", (Object)file1.getName());
                FileUtils.deleteQuietly((File)file1);
            }
        }
        catch (IllegalArgumentException illegalargumentexception) {
            n_1700_B.error("Error while deleting old server resource pack : {}", (Object)illegalargumentexception.getMessage());
        }
    }

    public CompletableFuture<Void> n_1700_B(File fileIn, PackSource p_217816_2_) {
        PackMetadataSection packmetadatasection;
        try (H_3999_U filepack = new H_3999_U(fileIn);){
            packmetadatasection = filepack.getMetadata(PackMetadataSection.n_1700_B);
        }
        catch (IOException ioexception) {
            return j_3341_s.n_1700_B((Throwable)new IOException(String.format("Invalid resourcepack at %s", fileIn), ioexception));
        }
        n_1700_B.info("Applying server pack {}", (Object)fileIn);
        this.w_1484_f = new D_2103_L("server", true, () -> new H_3999_U(fileIn), new F_2904_S("resourcePack.server.name"), packmetadatasection.n_1700_B(), u_4608_G.n_1700_B(packmetadatasection.J_1907_R()), D_2103_L.J_1907_R.n_1700_B, true, p_217816_2_);
        return MinecraftClient.A_4115_X().Y_1740_V();
    }

    @Nullable
    private D_2103_L n_1700_B(D_2103_L.n_1700_B p_239453_1_) {
        File file2;
        D_2103_L resourcepackinfo = null;
        File file1 = this.u_1723_Y.n_1700_B(new g_2336_b("resourcepacks/programmer_art.zip"));
        if (file1 != null && file1.isFile()) {
            resourcepackinfo = L_1105_I.n_1700_B(p_239453_1_, () -> L_1105_I.R_4764_Y(file1));
        }
        if (resourcepackinfo == null && SharedConstants.G_564_y && (file2 = this.u_1723_Y.n_1700_B("../resourcepacks/programmer_art")) != null && file2.isDirectory()) {
            resourcepackinfo = L_1105_I.n_1700_B(p_239453_1_, () -> L_1105_I.J_1907_R(file2));
        }
        return resourcepackinfo;
    }

    @Nullable
    private static D_2103_L n_1700_B(D_2103_L.n_1700_B p_239454_0_, Supplier<PackResources> p_239454_1_) {
        return D_2103_L.n_1700_B("programer_art", false, p_239454_1_, p_239454_0_, D_2103_L.J_1907_R.n_1700_B, PackSource.J_1907_R);
    }

    private static A_885_K J_1907_R(File p_239459_0_) {
        return new A_885_K(p_239459_0_){

            @Override
            public String getName() {
                return "Programmer Art";
            }
        };
    }

    private static PackResources R_4764_Y(File p_239460_0_) {
        return new H_3999_U(p_239460_0_){

            @Override
            public String getName() {
                return "Programmer Art";
            }
        };
    }
}




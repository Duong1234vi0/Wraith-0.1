/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.common.hash.Hashing
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.mutable.MutableBoolean
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.C_3240_x;
import lightning.product.D_2103_L;
import lightning.product.D_4024_W;
import lightning.product.PackRepository;
import lightning.product.F_2904_S;
import lightning.product.I_2861_N;
import lightning.product.N_1412_w;
import lightning.product.SystemToast;
import lightning.product.T_1114_L;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.PackResources;
import lightning.product.MinecraftClient;
import lightning.product.c_4477_a;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.q_3418_t;
import lightning.product.x_282_a;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class V_3049_B
extends k_2603_m {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final x_282_a J_1907_R = new F_2904_S("pack.dropInfo").n_1700_B(D_4024_W.w_1484_f);
    private static final x_282_a R_4764_Y = new F_2904_S("pack.folderInfo");
    private static final g_2336_b G_564_y = new g_2336_b("textures/misc/unknown_pack.png");
    private final I_2861_N P_1922_E;
    private final k_2603_m u_1723_Y;
    @Nullable
    private n_1700_B v_4262_N;
    private long w_1484_f;
    private N_1412_w t_148_a;
    private N_1412_w s_956_w;
    private final File u_2550_I;
    private Button M_588_G;
    private final Map<String, g_2336_b> P_4830_p = Maps.newHashMap();

    public V_3049_B(k_2603_m p_i242060_1_, PackRepository p_i242060_2_, Consumer<PackRepository> p_i242060_3_, File p_i242060_4_, x_282_a p_i242060_5_) {
        super(p_i242060_5_);
        this.u_1723_Y = p_i242060_1_;
        this.P_1922_E = new I_2861_N(this::J_1907_R, this::n_1700_B, p_i242060_2_, p_i242060_3_);
        this.u_2550_I = p_i242060_4_;
        this.v_4262_N = lightning.product.V_3049_B$n_1700_B.n_1700_B(p_i242060_4_);
    }

    @Override
    public void closeScreen() {
        this.P_1922_E.R_4764_Y();
        this.minecraft.n_1700_B(this.u_1723_Y);
        this.n_1700_B();
    }

    private void n_1700_B() {
        if (this.v_4262_N != null) {
            try {
                this.v_4262_N.close();
                this.v_4262_N = null;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    @Override
    protected void init() {
        this.M_588_G = this.addButton(new Button(this.width / 2 + 4, this.height - 48, 150, 20, CommonComponents.R_4764_Y, p_238903_1_ -> this.closeScreen()));
        this.addButton(new Button(this.width / 2 - 154, this.height - 48, 150, 20, new F_2904_S("pack.openFolder"), p_238896_1_ -> j_3341_s.t_148_a().n_1700_B(this.u_2550_I), (p_238897_1_, p_238897_2_, p_238897_3_, p_238897_4_) -> this.renderTooltip(p_238897_2_, R_4764_Y, p_238897_3_, p_238897_4_)));
        this.t_148_a = new N_1412_w(this.minecraft, 200, this.height, new F_2904_S("pack.available.title"));
        this.t_148_a.setLeftPos(this.width / 2 - 4 - 200);
        this.children.add(this.t_148_a);
        this.s_956_w = new N_1412_w(this.minecraft, 200, this.height, new F_2904_S("pack.selected.title"));
        this.s_956_w.setLeftPos(this.width / 2 + 4);
        this.children.add(this.s_956_w);
        this.R_4764_Y();
    }

    @Override
    public void tick() {
        if (this.v_4262_N != null) {
            try {
                if (this.v_4262_N.n_1700_B()) {
                    this.w_1484_f = 20L;
                }
            }
            catch (IOException ioexception) {
                n_1700_B.warn("Failed to poll for directory {} changes, stopping", (Object)this.u_2550_I);
                this.n_1700_B();
            }
        }
        if (this.w_1484_f > 0L && --this.w_1484_f == 0L) {
            this.R_4764_Y();
        }
    }

    private void J_1907_R() {
        this.n_1700_B(this.s_956_w, this.P_1922_E.J_1907_R());
        this.n_1700_B(this.t_148_a, this.P_1922_E.n_1700_B());
        this.M_588_G.active = !this.s_956_w.getEventListeners().isEmpty();
    }

    private void n_1700_B(N_1412_w p_238899_1_, Stream<I_2861_N.G_564_y> p_238899_2_) {
        p_238899_1_.getEventListeners().clear();
        p_238899_2_.forEach(p_238898_2_ -> p_238899_1_.getEventListeners().add(new N_1412_w.n_1700_B(this.minecraft, p_238899_1_, this, (I_2861_N.G_564_y)p_238898_2_)));
    }

    private void R_4764_Y() {
        this.P_1922_E.G_564_y();
        this.J_1907_R();
        this.w_1484_f = 0L;
        this.P_4830_p.clear();
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderDirtBackground(0);
        this.t_148_a.render(matrixStack, mouseX, mouseY, partialTicks);
        this.s_956_w.render(matrixStack, mouseX, mouseY, partialTicks);
        V_3049_B.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 8, 0xFFFFFF);
        V_3049_B.drawCenteredString(matrixStack, this.font, J_1907_R, this.width / 2, 20, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    protected static void n_1700_B(MinecraftClient p_238895_0_, List<Path> p_238895_1_, Path p_238895_2_) {
        MutableBoolean mutableboolean = new MutableBoolean();
        p_238895_1_.forEach(p_238901_2_ -> {
            try (Stream<Path> stream = Files.walk(p_238901_2_, new FileVisitOption[0]);){
                stream.forEach(p_238900_3_ -> {
                    try {
                        j_3341_s.J_1907_R(p_238901_2_.getParent(), p_238895_2_, p_238900_3_);
                    }
                    catch (IOException ioexception1) {
                        n_1700_B.warn("Failed to copy datapack file  from {} to {}", p_238900_3_, (Object)p_238895_2_, (Object)ioexception1);
                        mutableboolean.setTrue();
                    }
                });
            }
            catch (IOException ioexception) {
                n_1700_B.warn("Failed to copy datapack file from {} to {}", p_238901_2_, (Object)p_238895_2_);
                mutableboolean.setTrue();
            }
        });
        if (mutableboolean.isTrue()) {
            SystemToast.R_4764_Y(p_238895_0_, p_238895_2_.toString());
        }
    }

    @Override
    public void addPacks(List<Path> packs) {
        String s = packs.stream().map(Path::getFileName).map(Path::toString).collect(Collectors.joining(", "));
        this.minecraft.n_1700_B(new q_3418_t(p_238902_2_ -> {
            if (p_238902_2_) {
                V_3049_B.n_1700_B(this.minecraft, packs, this.u_2550_I.toPath());
                this.R_4764_Y();
            }
            this.minecraft.n_1700_B(this);
        }, new F_2904_S("pack.dropConfirm"), new U_2871_b(s)));
    }

    /*
     * Enabled aggressive exception aggregation
     */
    private g_2336_b n_1700_B(C_3240_x p_243397_1_, D_2103_L p_243397_2_) {
        try (PackResources iresourcepack2 = p_243397_2_.G_564_y();){
            g_2336_b g_2336_b2;
            block15: {
                InputStream inputstream = iresourcepack2.getRootResourceStream("pack.png");
                try {
                    String s = p_243397_2_.P_1922_E();
                    g_2336_b resourcelocation = new g_2336_b("minecraft", "pack/" + j_3341_s.n_1700_B(s, g_2336_b::J_1907_R) + "/" + String.valueOf(Hashing.sha1().hashUnencodedChars((CharSequence)s)) + "/icon");
                    i_2518_W nativeimage = i_2518_W.n_1700_B(inputstream);
                    p_243397_1_.n_1700_B(resourcelocation, (c_4477_a)new T_1114_L(nativeimage));
                    g_2336_b2 = resourcelocation;
                    if (inputstream == null) break block15;
                }
                catch (Throwable throwable) {
                    if (inputstream != null) {
                        try {
                            inputstream.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                inputstream.close();
            }
            return g_2336_b2;
        }
        catch (FileNotFoundException iresourcepack2) {
        }
        catch (Exception exception) {
            n_1700_B.warn("Failed to load icon from pack {}", (Object)p_243397_2_.P_1922_E(), (Object)exception);
        }
        return G_564_y;
    }

    private g_2336_b n_1700_B(D_2103_L p_243395_1_) {
        return this.P_4830_p.computeIfAbsent(p_243395_1_.P_1922_E(), p_243396_2_ -> this.n_1700_B(this.minecraft.G_624_v(), p_243395_1_));
    }

    static class n_1700_B
    implements AutoCloseable {
        private final WatchService n_1700_B;
        private final Path J_1907_R;

        public n_1700_B(File p_i242061_1_) throws IOException {
            this.J_1907_R = p_i242061_1_.toPath();
            this.n_1700_B = this.J_1907_R.getFileSystem().newWatchService();
            try {
                this.n_1700_B(this.J_1907_R);
                try (DirectoryStream<Path> directorystream = Files.newDirectoryStream(this.J_1907_R);){
                    for (Path path : directorystream) {
                        if (!Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) continue;
                        this.n_1700_B(path);
                    }
                }
            }
            catch (Exception exception) {
                this.n_1700_B.close();
                throw exception;
            }
        }

        @Nullable
        public static n_1700_B n_1700_B(File p_243403_0_) {
            try {
                return new n_1700_B(p_243403_0_);
            }
            catch (IOException ioexception) {
                n_1700_B.warn("Failed to initialize pack directory {} monitoring", (Object)p_243403_0_, (Object)ioexception);
                return null;
            }
        }

        private void n_1700_B(Path p_243404_1_) throws IOException {
            p_243404_1_.register(this.n_1700_B, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
        }

        public boolean n_1700_B() throws IOException {
            WatchKey watchkey;
            boolean flag = false;
            while ((watchkey = this.n_1700_B.poll()) != null) {
                for (WatchEvent<?> watchevent : watchkey.pollEvents()) {
                    Path path;
                    flag = true;
                    if (watchkey.watchable() != this.J_1907_R || watchevent.kind() != StandardWatchEventKinds.ENTRY_CREATE || !Files.isDirectory(path = this.J_1907_R.resolve((Path)watchevent.context()), LinkOption.NOFOLLOW_LINKS)) continue;
                    this.n_1700_B(path);
                }
                watchkey.reset();
            }
            return flag;
        }

        @Override
        public void close() throws IOException {
            this.n_1700_B.close();
        }
    }
}




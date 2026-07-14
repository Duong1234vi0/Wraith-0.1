/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.util.concurrent.RateLimiter
 *  org.apache.commons.compress.archivers.ArchiveEntry
 *  org.apache.commons.compress.archivers.tar.TarArchiveEntry
 *  org.apache.commons.compress.archivers.tar.TarArchiveOutputStream
 *  org.apache.commons.compress.utils.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;
import java.util.zip.GZIPOutputStream;
import lightning.product.C_3538_G;
import lightning.product.D_3318_r;
import lightning.product.D_4361_a;
import lightning.product.E_688_b;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.H_1974_E;
import lightning.product.SharedConstants;
import lightning.product.J_2011_a;
import lightning.product.UploadTokenCache;
import lightning.product.R_3908_n;
import lightning.product.Button;
import lightning.product.c_4037_x;
import lightning.product.RetryCallException;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.i_1637_u;
import lightning.product.j_3341_s;
import lightning.product.l_3747_P;
import lightning.product.p_178_J;
import lightning.product.CommonComponents;
import lightning.product.NarrationHelper;
import lightning.product.u_744_e;
import lightning.product.x_282_a;
import lightning.product.UploadStatus;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class C_1162_e
extends RealmsScreen {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final ReentrantLock J_1907_R = new ReentrantLock();
    private static final String[] R_4764_Y = new String[]{"", ".", ". .", ". . ."};
    private static final x_282_a G_564_y = new F_2904_S("mco.upload.verifying");
    private final C_3538_G P_1922_E;
    private final J_2011_a u_1723_Y;
    private final long v_4262_N;
    private final int w_1484_f;
    private final UploadStatus t_148_a;
    private final RateLimiter s_956_w;
    private volatile x_282_a[] u_2550_I;
    private volatile x_282_a M_588_G = new F_2904_S("mco.upload.preparing");
    private volatile String P_4830_p;
    private volatile boolean h_1847_R;
    private volatile boolean Q_4569_t;
    private volatile boolean M_182_A = true;
    private volatile boolean t_1786_h;
    private Button multiplayerClientSuggestionProvider;
    private Button w_1457_N;
    private int Y_601_j;
    private Long Y_259_p;
    private Long Q_2552_b;
    private long C_2741_M;
    private final Runnable k_2293_S;

    public C_1162_e(long p_i232226_1_, int p_i232226_3_, C_3538_G p_i232226_4_, J_2011_a p_i232226_5_, Runnable p_i232226_6_) {
        this.v_4262_N = p_i232226_1_;
        this.w_1484_f = p_i232226_3_;
        this.P_1922_E = p_i232226_4_;
        this.u_1723_Y = p_i232226_5_;
        this.t_148_a = new UploadStatus();
        this.s_956_w = RateLimiter.create((double)0.1f);
        this.k_2293_S = p_i232226_6_;
    }

    @Override
    public void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.multiplayerClientSuggestionProvider = this.addButton(new Button(this.width / 2 - 100, this.height - 42, 200, 20, CommonComponents.w_1484_f, p_238087_1_ -> this.n_1700_B()));
        this.multiplayerClientSuggestionProvider.visible = false;
        this.w_1457_N = this.addButton(new Button(this.width / 2 - 100, this.height - 42, 200, 20, CommonComponents.G_564_y, p_238084_1_ -> this.J_1907_R()));
        if (!this.t_1786_h) {
            if (this.P_1922_E.n_1700_B == -1) {
                this.R_4764_Y();
            } else {
                this.P_1922_E.n_1700_B(() -> {
                    if (!this.t_1786_h) {
                        this.t_1786_h = true;
                        this.minecraft.n_1700_B(this);
                        this.R_4764_Y();
                    }
                });
            }
        }
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    private void n_1700_B() {
        this.k_2293_S.run();
    }

    private void J_1907_R() {
        this.h_1847_R = true;
        this.minecraft.n_1700_B(this.P_1922_E);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            if (this.M_182_A) {
                this.J_1907_R();
            } else {
                this.n_1700_B();
            }
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        if (!this.Q_4569_t && this.t_148_a.n_1700_B != 0L && this.t_148_a.n_1700_B == this.t_148_a.J_1907_R) {
            this.M_588_G = G_564_y;
            this.w_1457_N.active = false;
        }
        C_1162_e.drawCenteredString(matrixStack, this.font, this.M_588_G, this.width / 2, 50, 0xFFFFFF);
        if (this.M_182_A) {
            this.n_1700_B(matrixStack);
        }
        if (this.t_148_a.n_1700_B != 0L && !this.h_1847_R) {
            this.J_1907_R(matrixStack);
            this.R_4764_Y(matrixStack);
        }
        if (this.u_2550_I != null) {
            for (int i = 0; i < this.u_2550_I.length; ++i) {
                C_1162_e.drawCenteredString(matrixStack, this.font, this.u_2550_I[i], this.width / 2, 110 + 12 * i, 0xFF0000);
            }
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void n_1700_B(g_221_o p_238086_1_) {
        int i = this.font.n_1700_B((FormattedText)this.M_588_G);
        this.font.J_1907_R(p_238086_1_, R_4764_Y[this.Y_601_j / 10 % R_4764_Y.length], (float)(this.width / 2 + i / 2 + 5), 50.0f, 0xFFFFFF);
    }

    private void J_1907_R(g_221_o p_238088_1_) {
        double d0 = Math.min((double)this.t_148_a.n_1700_B / (double)this.t_148_a.J_1907_R, 1.0);
        this.P_4830_p = String.format(Locale.ROOT, "%.1f", d0 * 100.0);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        c_4037_x.e_4240_b();
        double d1 = this.width / 2 - 100;
        double d2 = 0.5;
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        bufferbuilder.n_1700_B(7, E_688_b.Y_601_j);
        bufferbuilder.pos(d1 - 0.5, 95.5, 0.0).color(217, 210, 210, 255).endVertex();
        bufferbuilder.pos(d1 + 200.0 * d0 + 0.5, 95.5, 0.0).color(217, 210, 210, 255).endVertex();
        bufferbuilder.pos(d1 + 200.0 * d0 + 0.5, 79.5, 0.0).color(217, 210, 210, 255).endVertex();
        bufferbuilder.pos(d1 - 0.5, 79.5, 0.0).color(217, 210, 210, 255).endVertex();
        bufferbuilder.pos(d1, 95.0, 0.0).color(128, 128, 128, 255).endVertex();
        bufferbuilder.pos(d1 + 200.0 * d0, 95.0, 0.0).color(128, 128, 128, 255).endVertex();
        bufferbuilder.pos(d1 + 200.0 * d0, 80.0, 0.0).color(128, 128, 128, 255).endVertex();
        bufferbuilder.pos(d1, 80.0, 0.0).color(128, 128, 128, 255).endVertex();
        tessellator.J_1907_R();
        c_4037_x.x_607_J();
        C_1162_e.drawCenteredString(p_238088_1_, this.font, this.P_4830_p + " %", this.width / 2, 84, 0xFFFFFF);
    }

    private void R_4764_Y(g_221_o p_238089_1_) {
        if (this.Y_601_j % 20 == 0) {
            if (this.Y_259_p != null) {
                long i = j_3341_s.J_1907_R() - this.Q_2552_b;
                if (i == 0L) {
                    i = 1L;
                }
                this.C_2741_M = 1000L * (this.t_148_a.n_1700_B - this.Y_259_p) / i;
                this.n_1700_B(p_238089_1_, this.C_2741_M);
            }
            this.Y_259_p = this.t_148_a.n_1700_B;
            this.Q_2552_b = j_3341_s.J_1907_R();
        } else {
            this.n_1700_B(p_238089_1_, this.C_2741_M);
        }
    }

    private void n_1700_B(g_221_o p_238083_1_, long p_238083_2_) {
        if (p_238083_2_ > 0L) {
            int i = this.font.J_1907_R(this.P_4830_p);
            String s = "(" + H_1974_E.J_1907_R(p_238083_2_) + "/s)";
            this.font.J_1907_R(p_238083_1_, s, (float)(this.width / 2 + i / 2 + 15), 84.0f, 0xFFFFFF);
        }
    }

    @Override
    public void tick() {
        super.tick();
        ++this.Y_601_j;
        if (this.M_588_G != null && this.s_956_w.tryAcquire(1)) {
            ArrayList list = Lists.newArrayList();
            list.add(this.M_588_G.getString());
            if (this.P_4830_p != null) {
                list.add(this.P_4830_p + "%");
            }
            if (this.u_2550_I != null) {
                Stream.of(this.u_2550_I).map(x_282_a::getString).forEach(list::add);
            }
            NarrationHelper.n_1700_B(String.join((CharSequence)System.lineSeparator(), list));
        }
    }

    private void R_4764_Y() {
        this.t_1786_h = true;
        new Thread(() -> {
            File file1 = null;
            p_178_J realmsclient = p_178_J.n_1700_B();
            long i = this.v_4262_N;
            try {
                if (J_1907_R.tryLock(1L, TimeUnit.SECONDS)) {
                    R_3908_n uploadinfo = null;
                    for (int j = 0; j < 20; ++j) {
                        block35: {
                            if (!this.h_1847_R) break block35;
                            this.G_564_y();
                        }
                        try {
                            uploadinfo = realmsclient.w_1484_f(i, UploadTokenCache.n_1700_B(i));
                            if (uploadinfo == null) continue;
                            break;
                        }
                        catch (RetryCallException retrycallexception) {
                            Thread.sleep(retrycallexception.P_1922_E * 1000);
                        }
                    }
                    if (uploadinfo == null) {
                        this.M_588_G = new F_2904_S("mco.upload.close.failure");
                    }
                    UploadTokenCache.n_1700_B(i, uploadinfo.n_1700_B());
                    if (!uploadinfo.R_4764_Y()) {
                        this.M_588_G = new F_2904_S("mco.upload.close.failure");
                    }
                    if (this.h_1847_R) {
                        this.G_564_y();
                    }
                    File file2 = new File(this.minecraft.M_182_A.getAbsolutePath(), "saves");
                    file1 = this.J_1907_R(new File(file2, this.u_1723_Y.n_1700_B()));
                    if (this.h_1847_R) {
                        this.G_564_y();
                    }
                    if (this.n_1700_B(file1)) {
                        this.M_588_G = new F_2904_S("mco.upload.uploading", this.u_1723_Y.J_1907_R());
                        i_1637_u fileupload = new i_1637_u(file1, this.v_4262_N, this.w_1484_f, uploadinfo, this.minecraft.z_1737_N(), SharedConstants.n_1700_B().getName(), this.t_148_a);
                        fileupload.n_1700_B((D_4361_a p_238082_3_) -> {
                            if (p_238082_3_.n_1700_B >= 200 && p_238082_3_.n_1700_B < 300) {
                                this.Q_4569_t = true;
                                this.M_588_G = new F_2904_S("mco.upload.done");
                                this.multiplayerClientSuggestionProvider.setMessage(CommonComponents.R_4764_Y);
                                UploadTokenCache.J_1907_R(i);
                            } else if (p_238082_3_.n_1700_B == 400 && p_238082_3_.J_1907_R != null) {
                                this.n_1700_B(new F_2904_S("mco.upload.failed", p_238082_3_.J_1907_R));
                            } else {
                                this.n_1700_B(new F_2904_S("mco.upload.failed", p_238082_3_.n_1700_B));
                            }
                        });
                        while (!fileupload.J_1907_R()) {
                            if (this.h_1847_R) {
                                fileupload.n_1700_B();
                                this.G_564_y();
                            }
                            try {
                                Thread.sleep(500L);
                            }
                            catch (InterruptedException interruptedexception) {
                                n_1700_B.error("Failed to check Realms file upload status");
                            }
                        }
                    }
                    long k = file1.length();
                    H_1974_E uploadspeed = H_1974_E.n_1700_B(k);
                    H_1974_E uploadspeed1 = H_1974_E.n_1700_B(0x140000000L);
                    if (H_1974_E.J_1907_R(k, uploadspeed).equals(H_1974_E.J_1907_R(0x140000000L, uploadspeed1)) && uploadspeed != H_1974_E.n_1700_B) {
                        H_1974_E uploadspeed2 = H_1974_E.values()[uploadspeed.ordinal() - 1];
                        this.n_1700_B(new F_2904_S("mco.upload.size.failure.line1", this.u_1723_Y.J_1907_R()), new F_2904_S("mco.upload.size.failure.line2", H_1974_E.J_1907_R(k, uploadspeed2), H_1974_E.J_1907_R(0x140000000L, uploadspeed2)));
                    }
                    this.n_1700_B(new F_2904_S("mco.upload.size.failure.line1", this.u_1723_Y.J_1907_R()), new F_2904_S("mco.upload.size.failure.line2", H_1974_E.J_1907_R(k, uploadspeed), H_1974_E.J_1907_R(0x140000000L, uploadspeed1)));
                }
                this.M_588_G = new F_2904_S("mco.upload.close.failure");
            }
            catch (IOException ioexception) {
                this.n_1700_B(new F_2904_S("mco.upload.failed", ioexception.getMessage()));
            }
            catch (u_744_e realmsserviceexception) {
                this.n_1700_B(new F_2904_S("mco.upload.failed", realmsserviceexception.toString()));
            }
            catch (InterruptedException interruptedexception1) {
                n_1700_B.error("Could not acquire upload lock");
            }
            finally {
                this.Q_4569_t = true;
                if (J_1907_R.isHeldByCurrentThread()) {
                    J_1907_R.unlock();
                    this.M_182_A = false;
                    this.multiplayerClientSuggestionProvider.visible = true;
                    this.w_1457_N.visible = false;
                    if (file1 != null) {
                        n_1700_B.debug("Deleting file " + file1.getAbsolutePath());
                        file1.delete();
                    }
                }
                return;
            }
        }).start();
    }

    private void n_1700_B(x_282_a ... p_238085_1_) {
        this.u_2550_I = p_238085_1_;
    }

    private void G_564_y() {
        this.M_588_G = new F_2904_S("mco.upload.cancelled");
        n_1700_B.debug("Upload was cancelled");
    }

    private boolean n_1700_B(File p_224692_1_) {
        return p_224692_1_.length() < 0x140000000L;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private File J_1907_R(File p_224675_1_) throws IOException {
        File file2;
        try (TarArchiveOutputStream tararchiveoutputstream = null;){
            File file1 = File.createTempFile("realms-upload-file", ".tar.gz");
            tararchiveoutputstream = new TarArchiveOutputStream((OutputStream)new GZIPOutputStream(new FileOutputStream(file1)));
            tararchiveoutputstream.setLongFileMode(3);
            this.n_1700_B(tararchiveoutputstream, p_224675_1_.getAbsolutePath(), "world", true);
            tararchiveoutputstream.finish();
            file2 = file1;
        }
        return file2;
    }

    private void n_1700_B(TarArchiveOutputStream p_224669_1_, String p_224669_2_, String p_224669_3_, boolean p_224669_4_) throws IOException {
        if (!this.h_1847_R) {
            File file1 = new File(p_224669_2_);
            String s = p_224669_4_ ? p_224669_3_ : p_224669_3_ + file1.getName();
            TarArchiveEntry tararchiveentry = new TarArchiveEntry(file1, s);
            p_224669_1_.putArchiveEntry((ArchiveEntry)tararchiveentry);
            if (file1.isFile()) {
                IOUtils.copy((InputStream)new FileInputStream(file1), (OutputStream)p_224669_1_);
                p_224669_1_.closeArchiveEntry();
            } else {
                p_224669_1_.closeArchiveEntry();
                File[] afile = file1.listFiles();
                if (afile != null) {
                    for (File file2 : afile) {
                        this.n_1700_B(p_224669_1_, file2.getAbsolutePath(), s + "/", false);
                    }
                }
            }
        }
    }
}



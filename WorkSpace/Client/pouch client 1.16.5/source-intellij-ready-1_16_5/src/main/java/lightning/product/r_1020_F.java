/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.commons.io.FileUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import lightning.product.N_1972_P;
import lightning.product.P_4645_d;
import lightning.product.ResourceManager;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import lightning.product.j_3341_s;
import net.optifine.Config;
import net.optifine.http.HttpPipeline;
import net.optifine.http.HttpRequest;
import net.optifine.http.HttpResponse;
import net.optifine.player.CapeImageBuffer;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class r_1020_F
extends P_4645_d {
    private static final Logger u_1723_Y = LogManager.getLogger();
    @Nullable
    private final File v_4262_N;
    private final String w_1484_f;
    private final boolean t_148_a;
    @Nullable
    private final Runnable s_956_w;
    @Nullable
    private CompletableFuture<?> u_2550_I;
    private boolean M_588_G;
    public Boolean n_1700_B = null;
    public boolean J_1907_R = false;
    private boolean P_4830_p = false;

    public r_1020_F(@Nullable File cacheFileIn, String imageUrlIn, g_2336_b textureResourceLocation, boolean legacySkinIn, @Nullable Runnable processTaskIn) {
        super(textureResourceLocation);
        this.v_4262_N = cacheFileIn;
        this.w_1484_f = imageUrlIn;
        this.t_148_a = legacySkinIn;
        this.s_956_w = processTaskIn;
    }

    private void n_1700_B(i_2518_W nativeImageIn) {
        if (this.s_956_w instanceof CapeImageBuffer) {
            CapeImageBuffer capeimagebuffer = (CapeImageBuffer)this.s_956_w;
            nativeImageIn = capeimagebuffer.parseUserSkin(nativeImageIn);
            capeimagebuffer.skinAvailable();
        }
        this.J_1907_R(nativeImageIn);
    }

    private void J_1907_R(i_2518_W p_setImageImpl_1_) {
        if (this.s_956_w != null) {
            this.s_956_w.run();
        }
        MinecraftClient.A_4115_X().execute(() -> {
            this.M_588_G = true;
            if (!c_4037_x.J_1907_R()) {
                c_4037_x.n_1700_B(() -> this.R_4764_Y(p_setImageImpl_1_));
            } else {
                this.R_4764_Y(p_setImageImpl_1_);
            }
        });
    }

    private void R_4764_Y(i_2518_W imageIn) {
        N_1972_P.n_1700_B(this.getGlTextureId(), imageIn.n_1700_B(), imageIn.J_1907_R());
        imageIn.n_1700_B(0, 0, 0, true);
        this.n_1700_B = imageIn != null;
    }

    @Override
    public void loadTexture(ResourceManager manager) throws IOException {
        MinecraftClient.A_4115_X().execute(() -> {
            if (!this.M_588_G) {
                try {
                    super.loadTexture(manager);
                }
                catch (IOException ioexception) {
                    u_1723_Y.warn("Failed to load texture: {}", (Object)this.R_4764_Y, (Object)ioexception);
                }
                this.M_588_G = true;
            }
        });
        if (this.u_2550_I == null) {
            i_2518_W nativeimage;
            if (this.v_4262_N != null && this.v_4262_N.isFile()) {
                u_1723_Y.debug("Loading http texture from local cache ({})", (Object)this.v_4262_N);
                FileInputStream fileinputstream = new FileInputStream(this.v_4262_N);
                nativeimage = this.n_1700_B(fileinputstream);
            } else {
                nativeimage = null;
            }
            if (nativeimage != null) {
                this.n_1700_B(nativeimage);
                this.G_564_y();
            } else {
                this.u_2550_I = CompletableFuture.runAsync(() -> {
                    HttpURLConnection httpurlconnection = null;
                    u_1723_Y.debug("Downloading http texture from {} to {}", (Object)this.w_1484_f, (Object)this.v_4262_N);
                    if (this.J_1907_R()) {
                        this.R_4764_Y();
                    } else {
                        try {
                            InputStream inputstream;
                            httpurlconnection = (HttpURLConnection)new URL(this.w_1484_f).openConnection(MinecraftClient.A_4115_X().d_2461_k());
                            httpurlconnection.setDoInput(true);
                            httpurlconnection.setDoOutput(false);
                            httpurlconnection.connect();
                            if (httpurlconnection.getResponseCode() / 100 != 2) {
                                if (httpurlconnection.getErrorStream() != null) {
                                    Config.readAll(httpurlconnection.getErrorStream());
                                }
                                return;
                            }
                            if (this.v_4262_N != null) {
                                FileUtils.copyInputStreamToFile((InputStream)httpurlconnection.getInputStream(), (File)this.v_4262_N);
                                inputstream = new FileInputStream(this.v_4262_N);
                            } else {
                                inputstream = httpurlconnection.getInputStream();
                            }
                            MinecraftClient.A_4115_X().execute(() -> {
                                i_2518_W nativeimage1 = this.n_1700_B(inputstream);
                                if (nativeimage1 != null) {
                                    this.n_1700_B(nativeimage1);
                                    this.G_564_y();
                                }
                            });
                            this.P_4830_p = true;
                        }
                        catch (Exception exception1) {
                            u_1723_Y.error("Couldn't download http texture", (Throwable)exception1);
                            return;
                        }
                        finally {
                            if (httpurlconnection != null) {
                                httpurlconnection.disconnect();
                            }
                            this.G_564_y();
                        }
                    }
                }, this.P_1922_E());
            }
        }
    }

    @Nullable
    private i_2518_W n_1700_B(InputStream inputStreamIn) {
        i_2518_W nativeimage = null;
        try {
            nativeimage = i_2518_W.n_1700_B(inputStreamIn);
            if (this.t_148_a) {
                nativeimage = r_1020_F.G_564_y(nativeimage);
            }
        }
        catch (IOException ioexception) {
            u_1723_Y.warn("Error while loading the skin texture", (Throwable)ioexception);
        }
        return nativeimage;
    }

    private boolean J_1907_R() {
        if (!this.J_1907_R) {
            return false;
        }
        Proxy proxy = MinecraftClient.A_4115_X().d_2461_k();
        if (proxy.type() != Proxy.Type.DIRECT && proxy.type() != Proxy.Type.SOCKS) {
            return false;
        }
        return this.w_1484_f.startsWith("http://");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void R_4764_Y() {
        try {
            i_2518_W nativeimage;
            HttpRequest httprequest = HttpPipeline.makeRequest(this.w_1484_f, MinecraftClient.A_4115_X().d_2461_k());
            HttpResponse httpresponse = HttpPipeline.executeRequest(httprequest);
            if (httpresponse.getStatus() / 100 != 2) {
                return;
            }
            byte[] abyte = httpresponse.getBody();
            ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte);
            if (this.v_4262_N != null) {
                FileUtils.copyInputStreamToFile((InputStream)bytearrayinputstream, (File)this.v_4262_N);
                nativeimage = i_2518_W.n_1700_B(new FileInputStream(this.v_4262_N));
            } else {
                nativeimage = i_2518_W.n_1700_B(bytearrayinputstream);
            }
            this.n_1700_B(nativeimage);
            this.P_4830_p = true;
        }
        catch (Exception exception) {
            u_1723_Y.error("Couldn't download http texture: " + exception.getClass().getName() + ": " + exception.getMessage());
            return;
        }
        finally {
            this.G_564_y();
        }
    }

    private void G_564_y() {
        if (!this.P_4830_p && this.s_956_w instanceof CapeImageBuffer) {
            CapeImageBuffer capeimagebuffer = (CapeImageBuffer)this.s_956_w;
            capeimagebuffer.cleanup();
        }
    }

    public Runnable n_1700_B() {
        return this.s_956_w;
    }

    private Executor P_1922_E() {
        return this.w_1484_f.startsWith("http://s.optifine.net") ? j_3341_s.P_4830_p() : j_3341_s.u_1723_Y();
    }

    private static i_2518_W G_564_y(i_2518_W nativeImageIn) {
        boolean flag;
        boolean bl = flag = nativeImageIn.J_1907_R() == 32;
        if (flag) {
            i_2518_W nativeimage = new i_2518_W(64, 64, true);
            nativeimage.n_1700_B(nativeImageIn);
            nativeImageIn.close();
            nativeImageIn = nativeimage;
            nativeimage.n_1700_B(0, 32, 64, 32, 0);
            nativeimage.n_1700_B(4, 16, 16, 32, 4, 4, true, false);
            nativeimage.n_1700_B(8, 16, 16, 32, 4, 4, true, false);
            nativeimage.n_1700_B(0, 20, 24, 32, 4, 12, true, false);
            nativeimage.n_1700_B(4, 20, 16, 32, 4, 12, true, false);
            nativeimage.n_1700_B(8, 20, 8, 32, 4, 12, true, false);
            nativeimage.n_1700_B(12, 20, 16, 32, 4, 12, true, false);
            nativeimage.n_1700_B(44, 16, -8, 32, 4, 4, true, false);
            nativeimage.n_1700_B(48, 16, -8, 32, 4, 4, true, false);
            nativeimage.n_1700_B(40, 20, 0, 32, 4, 12, true, false);
            nativeimage.n_1700_B(44, 20, -8, 32, 4, 12, true, false);
            nativeimage.n_1700_B(48, 20, -16, 32, 4, 12, true, false);
            nativeimage.n_1700_B(52, 20, -8, 32, 4, 12, true, false);
        }
        r_1020_F.J_1907_R(nativeImageIn, 0, 0, 32, 16);
        if (flag) {
            r_1020_F.n_1700_B(nativeImageIn, 32, 0, 64, 32);
        }
        r_1020_F.J_1907_R(nativeImageIn, 0, 16, 64, 32);
        r_1020_F.J_1907_R(nativeImageIn, 16, 48, 48, 64);
        return nativeImageIn;
    }

    private static void n_1700_B(i_2518_W image, int x, int y, int width, int height) {
        for (int i = x; i < width; ++i) {
            for (int j = y; j < height; ++j) {
                int k = image.n_1700_B(i, j);
                if ((k >> 24 & 0xFF) >= 128) continue;
                return;
            }
        }
        for (int l = x; l < width; ++l) {
            for (int i1 = y; i1 < height; ++i1) {
                image.n_1700_B(l, i1, image.n_1700_B(l, i1) & 0xFFFFFF);
            }
        }
    }

    private static void J_1907_R(i_2518_W image, int x, int y, int width, int height) {
        for (int i = x; i < width; ++i) {
            for (int j = y; j < height; ++j) {
                image.n_1700_B(i, j, image.n_1700_B(i, j) | 0xFF000000);
            }
        }
    }
}




/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture$Type
 *  com.mojang.util.UUIDTypeAdapter
 *  javax.annotation.Nullable
 *  org.apache.commons.codec.binary.Base64
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.util.UUIDTypeAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import lightning.product.H_1883_T;
import lightning.product.N_1972_P;
import lightning.product.X_933_l;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.d_4007_L;
import lightning.product.g_2336_b;
import lightning.product.s_2614_w;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class y_2772_m {
    private static final Map<String, n_1700_B> n_1700_B = Maps.newHashMap();
    private static final Map<String, Boolean> J_1907_R = Maps.newHashMap();
    private static final Map<String, String> R_4764_Y = Maps.newHashMap();
    private static final Logger G_564_y = LogManager.getLogger();
    private static final g_2336_b P_1922_E = new g_2336_b("textures/gui/presets/isles.png");

    public static void n_1700_B(String p_225202_0_, @Nullable String p_225202_1_) {
        if (p_225202_1_ == null) {
            MinecraftClient.A_4115_X().G_624_v().n_1700_B(P_1922_E);
        } else {
            int i = y_2772_m.J_1907_R(p_225202_0_, p_225202_1_);
            c_4037_x.v_4262_N(i);
        }
    }

    public static void n_1700_B(String p_225205_0_, Runnable p_225205_1_) {
        c_4037_x.t_148_a();
        try {
            y_2772_m.n_1700_B(p_225205_0_);
            p_225205_1_.run();
        }
        finally {
            c_4037_x.s_956_w();
        }
    }

    private static void n_1700_B(UUID p_225204_0_) {
        MinecraftClient.A_4115_X().G_624_v().n_1700_B(s_2614_w.n_1700_B(p_225204_0_));
    }

    private static void n_1700_B(final String p_225200_0_) {
        UUID uuid = UUIDTypeAdapter.fromString((String)p_225200_0_);
        if (n_1700_B.containsKey(p_225200_0_)) {
            c_4037_x.v_4262_N(y_2772_m.n_1700_B.get((Object)p_225200_0_).J_1907_R);
        } else if (J_1907_R.containsKey(p_225200_0_)) {
            if (!J_1907_R.get(p_225200_0_).booleanValue()) {
                y_2772_m.n_1700_B(uuid);
            } else if (R_4764_Y.containsKey(p_225200_0_)) {
                int i = y_2772_m.J_1907_R(p_225200_0_, R_4764_Y.get(p_225200_0_));
                c_4037_x.v_4262_N(i);
            } else {
                y_2772_m.n_1700_B(uuid);
            }
        } else {
            J_1907_R.put(p_225200_0_, false);
            y_2772_m.n_1700_B(uuid);
            Thread thread = new Thread("Realms Texture Downloader"){

                /*
                 * WARNING - Removed try catching itself - possible behaviour change.
                 * Enabled aggressive block sorting
                 * Enabled unnecessary exception pruning
                 * Enabled aggressive exception aggregation
                 */
                @Override
                public void run() {
                    ByteArrayOutputStream bytearrayoutputstream;
                    BufferedImage bufferedimage;
                    Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = H_1883_T.J_1907_R(p_225200_0_);
                    if (!map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
                        J_1907_R.put(p_225200_0_, true);
                        return;
                    }
                    MinecraftProfileTexture minecraftprofiletexture = map.get(MinecraftProfileTexture.Type.SKIN);
                    String s = minecraftprofiletexture.getUrl();
                    HttpURLConnection httpurlconnection = null;
                    G_564_y.debug("Downloading http texture from {}", (Object)s);
                    try {
                        httpurlconnection = (HttpURLConnection)new URL(s).openConnection(MinecraftClient.A_4115_X().d_2461_k());
                        httpurlconnection.setDoInput(true);
                        httpurlconnection.setDoOutput(false);
                        httpurlconnection.connect();
                        if (httpurlconnection.getResponseCode() / 100 == 2) {
                            try {
                                bufferedimage = ImageIO.read(httpurlconnection.getInputStream());
                            }
                            catch (Exception exception) {
                                J_1907_R.remove(p_225200_0_);
                                if (httpurlconnection == null) return;
                                httpurlconnection.disconnect();
                                return;
                            }
                            finally {
                                IOUtils.closeQuietly((InputStream)httpurlconnection.getInputStream());
                            }
                        } else {
                            J_1907_R.remove(p_225200_0_);
                            return;
                        }
                        bufferedimage = new d_4007_L().n_1700_B(bufferedimage);
                        bytearrayoutputstream = new ByteArrayOutputStream();
                    }
                    catch (Exception exception1) {
                        G_564_y.error("Couldn't download http texture", (Throwable)exception1);
                        J_1907_R.remove(p_225200_0_);
                        return;
                    }
                    ImageIO.write((RenderedImage)bufferedimage, "png", bytearrayoutputstream);
                    R_4764_Y.put(p_225200_0_, new Base64().encodeToString(bytearrayoutputstream.toByteArray()));
                    J_1907_R.put(p_225200_0_, true);
                    return;
                    finally {
                        if (httpurlconnection != null) {
                            httpurlconnection.disconnect();
                        }
                    }
                }
            };
            thread.setDaemon(true);
            thread.start();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static int J_1907_R(String p_225203_0_, String p_225203_1_) {
        int i;
        if (n_1700_B.containsKey(p_225203_0_)) {
            n_1700_B realmstexturemanager$realmstexture = n_1700_B.get(p_225203_0_);
            if (realmstexturemanager$realmstexture.n_1700_B.equals(p_225203_1_)) {
                return realmstexturemanager$realmstexture.J_1907_R;
            }
            c_4037_x.u_1723_Y(realmstexturemanager$realmstexture.J_1907_R);
            i = realmstexturemanager$realmstexture.J_1907_R;
        } else {
            i = X_933_l.G_624_v();
        }
        IntBuffer intbuffer = null;
        int j = 0;
        int k = 0;
        try {
            BufferedImage bufferedimage;
            ByteArrayInputStream inputstream = new ByteArrayInputStream(new Base64().decode(p_225203_1_));
            try {
                bufferedimage = ImageIO.read(inputstream);
            }
            finally {
                IOUtils.closeQuietly((InputStream)inputstream);
            }
            j = bufferedimage.getWidth();
            k = bufferedimage.getHeight();
            int[] lvt_8_1_ = new int[j * k];
            bufferedimage.getRGB(0, 0, j, k, lvt_8_1_, 0, j);
            intbuffer = ByteBuffer.allocateDirect(4 * j * k).order(ByteOrder.nativeOrder()).asIntBuffer();
            intbuffer.put(lvt_8_1_);
            ((Buffer)intbuffer).flip();
        }
        catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
        c_4037_x.P_1922_E(33984);
        c_4037_x.v_4262_N(i);
        N_1972_P.n_1700_B(intbuffer, j, k);
        n_1700_B.put(p_225203_0_, new n_1700_B(p_225203_1_, i));
        return i;
    }

    public static class n_1700_B {
        private final String n_1700_B;
        private final int J_1907_R;

        public n_1700_B(String p_i51693_1_, int p_i51693_2_) {
            this.n_1700_B = p_i51693_1_;
            this.J_1907_R = p_i51693_2_;
        }
    }
}



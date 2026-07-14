/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import lightning.product.F_3565_Q;
import lightning.product.I_174_h;
import lightning.product.N_1972_P;
import lightning.product.PreparableReloadListener;
import lightning.product.P_4645_d;
import lightning.product.ResourceManager;
import lightning.product.T_1114_L;
import lightning.product.V_2511_L;
import lightning.product.ProfilerFiller;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.c_4477_a;
import lightning.product.g_2336_b;
import lightning.product.k_596_g;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.CrashReportCategory;
import lightning.product.r_715_M;
import lightning.product.PreloadedTexture;
import net.optifine.Config;
import net.optifine.CustomGuis;
import net.optifine.EmissiveTextures;
import net.optifine.shaders.ShadersTex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class C_3240_x
implements AutoCloseable,
I_174_h,
PreparableReloadListener {
    private static final Logger J_1907_R = LogManager.getLogger();
    public static final g_2336_b n_1700_B = new g_2336_b("");
    private final Map<g_2336_b, c_4477_a> R_4764_Y = Maps.newHashMap();
    private final Set<I_174_h> G_564_y = Sets.newHashSet();
    private final Map<String, Integer> P_1922_E = Maps.newHashMap();
    private final ResourceManager u_1723_Y;
    private c_4477_a v_4262_N;
    private g_2336_b w_1484_f;

    public C_3240_x(ResourceManager resourceManager) {
        this.u_1723_Y = resourceManager;
    }

    public void n_1700_B(g_2336_b resource) {
        if (!c_4037_x.J_1907_R()) {
            c_4037_x.n_1700_B(() -> this.G_564_y(resource));
        } else {
            this.G_564_y(resource);
        }
    }

    private void G_564_y(g_2336_b resource) {
        c_4477_a texture;
        if (Config.isCustomGuis()) {
            resource = CustomGuis.getTextureLocation(resource);
        }
        if ((texture = this.R_4764_Y.get(resource)) == null) {
            texture = new P_4645_d(resource);
            this.n_1700_B(resource, texture);
        }
        if (Config.isShaders()) {
            ShadersTex.bindTexture(texture);
        } else {
            texture.bindTexture();
        }
        this.v_4262_N = texture;
        this.w_1484_f = resource;
    }

    public void n_1700_B(g_2336_b textureLocation, c_4477_a textureObj) {
        c_4477_a texture = this.R_4764_Y.put(textureLocation, textureObj = this.R_4764_Y(textureLocation, textureObj));
        if (texture != textureObj) {
            if (texture != null && texture != F_3565_Q.R_4764_Y()) {
                this.G_564_y.remove(texture);
                this.J_1907_R(textureLocation, texture);
            }
            if (textureObj instanceof I_174_h) {
                this.G_564_y.add((I_174_h)((Object)textureObj));
            }
        }
    }

    private void J_1907_R(g_2336_b p_243505_1_, c_4477_a p_243505_2_) {
        if (p_243505_2_ != F_3565_Q.R_4764_Y()) {
            try {
                p_243505_2_.close();
            }
            catch (Exception exception) {
                J_1907_R.warn("Failed to close texture {}", (Object)p_243505_1_, (Object)exception);
            }
        }
        p_243505_2_.deleteGlTexture();
    }

    private c_4477_a R_4764_Y(g_2336_b p_230183_1_, c_4477_a p_230183_2_) {
        try {
            p_230183_2_.loadTexture(this.u_1723_Y);
            return p_230183_2_;
        }
        catch (IOException ioexception) {
            if (p_230183_1_ != n_1700_B) {
                J_1907_R.warn("Failed to load texture: {}", (Object)p_230183_1_, (Object)ioexception);
            }
            return F_3565_Q.R_4764_Y();
        }
        catch (Throwable throwable) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Registering texture");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Resource location being registered");
            crashreportcategory.n_1700_B("Resource location", p_230183_1_);
            crashreportcategory.n_1700_B("Texture object class", () -> p_230183_2_.getClass().getName());
            throw new ReportedException(crashreport);
        }
    }

    @Nullable
    public c_4477_a J_1907_R(g_2336_b textureLocation) {
        return this.R_4764_Y.get(textureLocation);
    }

    public g_2336_b n_1700_B(String name, T_1114_L texture) {
        Integer integer = this.P_1922_E.get(name);
        integer = integer == null ? Integer.valueOf(1) : Integer.valueOf(integer + 1);
        this.P_1922_E.put(name, integer);
        g_2336_b resourcelocation = new g_2336_b(String.format("dynamic/%s_%d", name, integer));
        this.n_1700_B(resourcelocation, (c_4477_a)texture);
        return resourcelocation;
    }

    public CompletableFuture<Void> n_1700_B(g_2336_b textureLocation, Executor executor) {
        if (!this.R_4764_Y.containsKey(textureLocation)) {
            PreloadedTexture preloadedtexture = new PreloadedTexture(this.u_1723_Y, textureLocation, executor);
            this.R_4764_Y.put(textureLocation, preloadedtexture);
            return preloadedtexture.n_1700_B().thenRunAsync(() -> this.n_1700_B(textureLocation, (c_4477_a)preloadedtexture), C_3240_x::n_1700_B);
        }
        return CompletableFuture.completedFuture(null);
    }

    private static void n_1700_B(Runnable runnableIn) {
        MinecraftClient.A_4115_X().execute(() -> c_4037_x.n_1700_B(runnableIn::run));
    }

    @Override
    public void tick() {
        for (I_174_h itickable : this.G_564_y) {
            itickable.tick();
        }
    }

    public void R_4764_Y(g_2336_b textureLocation) {
        c_4477_a texture = this.J_1907_R(textureLocation);
        if (texture != null) {
            this.R_4764_Y.remove(textureLocation);
            N_1972_P.n_1700_B(texture.getGlTextureId());
        }
    }

    @Override
    public void close() {
        this.R_4764_Y.forEach(this::J_1907_R);
        this.R_4764_Y.clear();
        this.G_564_y.clear();
        this.P_1922_E.clear();
    }

    @Override
    public CompletableFuture<Void> reload(PreparableReloadListener.n_1700_B stage, ResourceManager resourceManager, ProfilerFiller preparationsProfiler, ProfilerFiller reloadProfiler, Executor backgroundExecutor, Executor gameExecutor) {
        Config.dbg("*** Reloading textures ***");
        Config.log("Resource packs: " + Config.getResourcePackNames());
        Iterator<g_2336_b> iterator = this.R_4764_Y.keySet().iterator();
        while (iterator.hasNext()) {
            g_2336_b resourcelocation = iterator.next();
            String s = resourcelocation.J_1907_R();
            if (!s.startsWith("optifine/") && !EmissiveTextures.isEmissive(resourcelocation)) continue;
            c_4477_a texture = this.R_4764_Y.get(resourcelocation);
            if (texture instanceof c_4477_a) {
                texture.deleteGlTexture();
            }
            iterator.remove();
        }
        EmissiveTextures.update();
        return ((CompletableFuture)CompletableFuture.allOf(k_596_g.n_1700_B(this, backgroundExecutor), this.n_1700_B(V_2511_L.WIDGETS_LOCATION, backgroundExecutor)).thenCompose(stage::markCompleteAwaitingOthers)).thenAcceptAsync(p_lambda$reload$4_3_ -> {
            F_3565_Q.R_4764_Y();
            r_715_M.n_1700_B(this.u_1723_Y);
            HashSet<Map.Entry<g_2336_b, c_4477_a>> set = new HashSet<Map.Entry<g_2336_b, c_4477_a>>(this.R_4764_Y.entrySet());
            Iterator iterator1 = set.iterator();
            while (iterator1.hasNext()) {
                Map.Entry entry = (Map.Entry)iterator1.next();
                g_2336_b resourcelocation1 = (g_2336_b)entry.getKey();
                c_4477_a texture1 = (c_4477_a)entry.getValue();
                if (texture1 == F_3565_Q.R_4764_Y() && !resourcelocation1.equals(F_3565_Q.n_1700_B())) {
                    iterator1.remove();
                    continue;
                }
                texture1.loadTexture(this, resourceManager, resourcelocation1, gameExecutor);
            }
        }, p_lambda$reload$5_0_ -> c_4037_x.n_1700_B(p_lambda$reload$5_0_::run));
    }

    public c_4477_a J_1907_R() {
        return this.v_4262_N;
    }

    public g_2336_b R_4764_Y() {
        return this.w_1484_f;
    }
}




/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.B_3871_I;
import lightning.product.B_4830_U;
import lightning.product.F_3565_Q;
import lightning.product.I_174_h;
import lightning.product.N_1972_P;
import lightning.product.Resource;
import lightning.product.ResourceManager;
import lightning.product.ProfilerFiller;
import lightning.product.X_933_l;
import lightning.product.c_4037_x;
import lightning.product.c_4477_a;
import lightning.product.g_1477_d;
import lightning.product.g_2336_b;
import lightning.product.i_2224_y;
import lightning.product.i_2518_W;
import lightning.product.j_1909_L;
import lightning.product.j_3341_s;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.CrashReportCategory;
import lightning.product.u_530_F;
import lightning.product.y_6_Q;
import net.optifine.Config;
import net.optifine.EmissiveTextures;
import net.optifine.SmartAnimations;
import net.optifine.reflect.Reflector;
import net.optifine.shaders.ITextureFormat;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersTex;
import net.optifine.shaders.ShadersTextureType;
import net.optifine.texture.ColorBlenderLinear;
import net.optifine.texture.IColorBlender;
import net.optifine.util.CounterInt;
import net.optifine.util.TextureUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class L_3848_p
extends c_4477_a
implements I_174_h {
    private static final Logger u_1723_Y = LogManager.getLogger();
    @Deprecated
    public static final g_2336_b n_1700_B = y_6_Q.n_1700_B;
    @Deprecated
    public static final g_2336_b J_1907_R = new g_2336_b("textures/atlas/particles.png");
    private final List<B_3871_I> v_4262_N = Lists.newArrayList();
    private final Set<g_2336_b> w_1484_f = Sets.newHashSet();
    private final Map<g_2336_b, B_3871_I> t_148_a = Maps.newHashMap();
    private final g_2336_b s_956_w;
    private final int u_2550_I;
    private Map<g_2336_b, B_3871_I> M_588_G = new LinkedHashMap<g_2336_b, B_3871_I>();
    private Map<g_2336_b, B_3871_I> P_4830_p = new LinkedHashMap<g_2336_b, B_3871_I>();
    private B_3871_I[] h_1847_R = null;
    private int Q_4569_t = -1;
    private int M_182_A = -1;
    private int t_1786_h = -1;
    private double multiplayerClientSuggestionProvider = -1.0;
    private double w_1457_N = -1.0;
    private CounterInt Y_601_j = new CounterInt(0);
    public int R_4764_Y = 0;
    public int G_564_y = 0;
    public int P_1922_E = 0;
    private int Y_259_p;
    private int Q_2552_b;
    private boolean C_2741_M;
    private boolean k_2293_S;
    private boolean q_2307_F;
    private ITextureFormat Z_875_P;

    public L_3848_p(g_2336_b textureLocationIn) {
        this.s_956_w = textureLocationIn;
        this.u_2550_I = c_4037_x.B_1668_F();
        this.C_2741_M = textureLocationIn.equals(n_1700_B);
        this.k_2293_S = Config.isShaders();
        this.q_2307_F = Config.isMultiTexture();
        if (this.C_2741_M) {
            Config.setTextureMap(this);
        }
    }

    @Override
    public void loadTexture(ResourceManager manager) throws IOException {
    }

    public void n_1700_B(n_1700_B sheetDataIn) {
        this.w_1484_f.clear();
        this.w_1484_f.addAll(sheetDataIn.n_1700_B);
        u_1723_Y.info("Created: {}x{}x{} {}-atlas", (Object)sheetDataIn.J_1907_R, (Object)sheetDataIn.R_4764_Y, (Object)sheetDataIn.G_564_y, (Object)this.s_956_w);
        N_1972_P.n_1700_B(this.getGlTextureId(), sheetDataIn.G_564_y, sheetDataIn.J_1907_R, sheetDataIn.R_4764_Y);
        this.R_4764_Y = sheetDataIn.J_1907_R;
        this.G_564_y = sheetDataIn.R_4764_Y;
        this.P_1922_E = sheetDataIn.G_564_y;
        if (this.k_2293_S) {
            ShadersTex.allocateTextureMapNS(sheetDataIn.G_564_y, sheetDataIn.J_1907_R, sheetDataIn.R_4764_Y, this);
        }
        this.J_1907_R();
        for (B_3871_I textureatlassprite : sheetDataIn.P_1922_E) {
            this.t_148_a.put(textureatlassprite.s_956_w(), textureatlassprite);
            try {
                textureatlassprite.P_4830_p();
            }
            catch (Throwable throwable) {
                n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Stitching texture atlas");
                CrashReportCategory crashreportcategory = crashreport.n_1700_B("Texture being stitched together");
                crashreportcategory.n_1700_B("Atlas path", this.s_956_w);
                crashreportcategory.n_1700_B("Sprite", textureatlassprite);
                throw new ReportedException(crashreport);
            }
            if (!textureatlassprite.M_182_A()) continue;
            textureatlassprite.n_1700_B(this.v_4262_N.size());
            this.v_4262_N.add(textureatlassprite);
        }
        TextureUtils.refreshCustomSprites(this);
        Config.log("Animated sprites: " + this.v_4262_N.size());
        if (Config.isMultiTexture()) {
            for (B_3871_I textureatlassprite1 : sheetDataIn.P_1922_E) {
                L_3848_p.J_1907_R(textureatlassprite1);
                if (textureatlassprite1.u_2550_I != null) {
                    L_3848_p.J_1907_R(textureatlassprite1.u_2550_I);
                }
                if (textureatlassprite1.M_588_G == null) continue;
                L_3848_p.J_1907_R(textureatlassprite1.M_588_G);
            }
            X_933_l.w_1457_N(this.getGlTextureId());
        }
        if (Config.isShaders()) {
            List<B_3871_I> list = sheetDataIn.P_1922_E;
            if (Shaders.configNormalMap) {
                X_933_l.w_1457_N(this.getMultiTexID().norm);
                for (B_3871_I textureatlassprite2 : list) {
                    B_3871_I textureatlassprite4 = textureatlassprite2.u_2550_I;
                    if (textureatlassprite4 == null) continue;
                    textureatlassprite4.P_4830_p();
                }
            }
            if (Shaders.configSpecularMap) {
                X_933_l.w_1457_N(this.getMultiTexID().spec);
                for (B_3871_I textureatlassprite3 : list) {
                    B_3871_I textureatlassprite5 = textureatlassprite3.M_588_G;
                    if (textureatlassprite5 == null) continue;
                    textureatlassprite5.P_4830_p();
                }
            }
            X_933_l.w_1457_N(this.getGlTextureId());
        }
        Reflector.callVoid(Reflector.ForgeHooksClient_onTextureStitchedPost, this);
        this.n_1700_B(sheetDataIn.J_1907_R, sheetDataIn.R_4764_Y);
        if (Config.equals(System.getProperty("saveTextureMap"), "true")) {
            Config.dbg("Exporting texture map: " + String.valueOf(this.s_956_w));
            TextureUtils.saveGlTexture("debug/" + this.s_956_w.J_1907_R().replaceAll("/", "_"), this.getGlTextureId(), sheetDataIn.G_564_y, sheetDataIn.J_1907_R, sheetDataIn.R_4764_Y);
            if (this.k_2293_S) {
                if (Shaders.configNormalMap) {
                    TextureUtils.saveGlTexture("debug/" + this.s_956_w.J_1907_R().replaceAll("/", "_").replace(".png", "_n.png"), this.multiTex.norm, sheetDataIn.G_564_y, sheetDataIn.J_1907_R, sheetDataIn.R_4764_Y);
                }
                if (Shaders.configSpecularMap) {
                    TextureUtils.saveGlTexture("debug/" + this.s_956_w.J_1907_R().replaceAll("/", "_").replace(".png", "_s.png"), this.multiTex.spec, sheetDataIn.G_564_y, sheetDataIn.J_1907_R, sheetDataIn.R_4764_Y);
                }
                X_933_l.w_1457_N(this.getGlTextureId());
            }
        }
    }

    public n_1700_B n_1700_B(ResourceManager resourceManagerIn, Stream<g_2336_b> resourceLocationsIn, ProfilerFiller profilerIn, int maxMipmapLevelIn) {
        int l2;
        int l;
        this.C_2741_M = this.s_956_w.equals(n_1700_B);
        this.k_2293_S = Config.isShaders();
        this.q_2307_F = Config.isMultiTexture();
        this.Z_875_P = ITextureFormat.readConfiguration();
        int i = maxMipmapLevelIn;
        this.M_588_G.clear();
        this.P_4830_p.clear();
        this.Y_601_j.reset();
        profilerIn.n_1700_B("preparing");
        Set<g_2336_b> set = resourceLocationsIn.peek(p_lambda$stitch$0_0_ -> {
            if (p_lambda$stitch$0_0_ == null) {
                throw new IllegalArgumentException("Location cannot be null!");
            }
        }).collect(Collectors.toSet());
        Config.dbg("Multitexture: " + Config.isMultiTexture());
        TextureUtils.registerCustomSprites(this);
        set.addAll(this.M_588_G.keySet());
        Set<g_2336_b> set1 = L_3848_p.n_1700_B(set, this.M_588_G.keySet());
        EmissiveTextures.updateIcons(this, set1);
        set.addAll(this.M_588_G.keySet());
        if (maxMipmapLevelIn >= 4) {
            i = this.n_1700_B(set, resourceManagerIn);
            Config.log("Mipmap levels: " + i);
        }
        int j = TextureUtils.getGLMaximumTextureSize();
        i_2224_y stitcher = new i_2224_y(j, j, maxMipmapLevelIn);
        int k = Integer.MAX_VALUE;
        this.Q_4569_t = l = L_3848_p.n_1700_B(i);
        int i1 = 1 << maxMipmapLevelIn;
        profilerIn.J_1907_R("extracting_frames");
        Reflector.callVoid(Reflector.ForgeHooksClient_onTextureStitchedPre, this, set);
        for (B_3871_I.n_1700_B textureatlassprite$info : this.n_1700_B(resourceManagerIn, set)) {
            int j1 = textureatlassprite$info.J_1907_R();
            int k1 = textureatlassprite$info.R_4764_Y();
            if (j1 >= 1 && k1 >= 1) {
                if (j1 < l || i > 0) {
                    int l1;
                    int n = l1 = i > 0 ? TextureUtils.scaleToGrid(j1, l) : TextureUtils.scaleToMin(j1, l);
                    if (l1 != j1) {
                        if (!TextureUtils.isPowerOfTwo(j1)) {
                            Config.log("Scaled non power of 2: " + String.valueOf(textureatlassprite$info.n_1700_B()) + ", " + j1 + " -> " + l1);
                        } else {
                            Config.log("Scaled too small texture: " + String.valueOf(textureatlassprite$info.n_1700_B()) + ", " + j1 + " -> " + l1);
                        }
                        int i2 = k1 * l1 / j1;
                        textureatlassprite$info.n_1700_B(l1);
                        textureatlassprite$info.J_1907_R(i2);
                        textureatlassprite$info.n_1700_B((double)l1 * 1.0 / (double)j1);
                    }
                }
                k = Math.min(k, Math.min(textureatlassprite$info.J_1907_R(), textureatlassprite$info.R_4764_Y()));
                int i3 = Math.min(Integer.lowestOneBit(textureatlassprite$info.J_1907_R()), Integer.lowestOneBit(textureatlassprite$info.R_4764_Y()));
                if (i3 < i1) {
                    u_1723_Y.warn("Texture {} with size {}x{} limits mip level from {} to {}", (Object)textureatlassprite$info.n_1700_B(), (Object)textureatlassprite$info.J_1907_R(), (Object)textureatlassprite$info.R_4764_Y(), (Object)u_530_F.u_1723_Y(i1), (Object)u_530_F.u_1723_Y(i3));
                    i1 = i3;
                }
                stitcher.n_1700_B(textureatlassprite$info);
                continue;
            }
            Config.warn("Invalid sprite size: " + String.valueOf(textureatlassprite$info.n_1700_B()));
        }
        int j2 = Math.min(k, i1);
        int k2 = u_530_F.u_1723_Y(j2);
        if (k2 < 0) {
            k2 = 0;
        }
        if (k2 < maxMipmapLevelIn) {
            u_1723_Y.warn("{}: dropping miplevel from {} to {}, because of minimum power of two: {}", (Object)this.s_956_w, (Object)maxMipmapLevelIn, (Object)k2, (Object)j2);
            l2 = k2;
        } else {
            l2 = maxMipmapLevelIn;
        }
        profilerIn.J_1907_R("register");
        B_3871_I.n_1700_B textureatlassprite$info1 = L_3848_p.n_1700_B(F_3565_Q.J_1907_R(), l);
        stitcher.n_1700_B(textureatlassprite$info1);
        profilerIn.J_1907_R("stitching");
        try {
            stitcher.R_4764_Y();
        }
        catch (j_1909_L stitcherexception) {
            n_3236_c crashreport = n_3236_c.n_1700_B(stitcherexception, "Stitching");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Stitcher");
            crashreportcategory.n_1700_B("Sprites", stitcherexception.n_1700_B().stream().map(p_lambda$stitch$1_0_ -> String.format("%s[%dx%d]", p_lambda$stitch$1_0_.n_1700_B(), p_lambda$stitch$1_0_.J_1907_R(), p_lambda$stitch$1_0_.R_4764_Y())).collect(Collectors.joining(",")));
            crashreportcategory.n_1700_B("Max Texture Size", j);
            throw new ReportedException(crashreport);
        }
        profilerIn.J_1907_R("loading");
        List<B_3871_I> list = this.n_1700_B(resourceManagerIn, stitcher, l2);
        profilerIn.R_4764_Y();
        return new n_1700_B(set, stitcher.n_1700_B(), stitcher.J_1907_R(), l2, list);
    }

    private Collection<B_3871_I.n_1700_B> n_1700_B(ResourceManager resourceManagerIn, Set<g_2336_b> spriteLocationsIn) {
        ArrayList list = Lists.newArrayList();
        ConcurrentLinkedQueue<B_3871_I.n_1700_B> concurrentlinkedqueue = new ConcurrentLinkedQueue<B_3871_I.n_1700_B>();
        for (g_2336_b resourcelocation : spriteLocationsIn) {
            if (F_3565_Q.n_1700_B().equals(resourcelocation)) continue;
            list.add(CompletableFuture.runAsync(() -> {
                B_3871_I.n_1700_B textureatlassprite$info;
                g_2336_b resourcelocation1 = this.n_1700_B(resourcelocation);
                try (Resource iresource = resourceManagerIn.n_1700_B(resourcelocation1);){
                    g_1477_d pngsizeinfo = new g_1477_d(iresource.toString(), iresource.J_1907_R());
                    B_4830_U animationmetadatasection = iresource.n_1700_B(B_4830_U.n_1700_B);
                    if (animationmetadatasection == null) {
                        animationmetadatasection = B_4830_U.J_1907_R;
                    }
                    Pair<Integer, Integer> pair = animationmetadatasection.n_1700_B(pngsizeinfo.n_1700_B, pngsizeinfo.J_1907_R);
                    textureatlassprite$info = new B_3871_I.n_1700_B(resourcelocation, (Integer)pair.getFirst(), (Integer)pair.getSecond(), animationmetadatasection);
                }
                catch (RuntimeException runtimeexception) {
                    u_1723_Y.error("Unable to parse metadata from {} : {}", (Object)resourcelocation1, (Object)runtimeexception);
                    this.v_4262_N(resourcelocation);
                    return;
                }
                catch (IOException ioexception1) {
                    u_1723_Y.error("Using missing texture, unable to load {} : {}", (Object)resourcelocation1, (Object)ioexception1);
                    this.v_4262_N(resourcelocation);
                    return;
                }
                concurrentlinkedqueue.add(textureatlassprite$info);
            }, j_3341_s.u_1723_Y()));
        }
        CompletableFuture.allOf(list.toArray(new CompletableFuture[0])).join();
        return concurrentlinkedqueue;
    }

    private List<B_3871_I> n_1700_B(ResourceManager resourceManagerIn, i_2224_y stitcherIn, int mipmapLevelIn) {
        ConcurrentLinkedQueue concurrentlinkedqueue = new ConcurrentLinkedQueue();
        ArrayList list = Lists.newArrayList();
        stitcherIn.n_1700_B((B_3871_I.n_1700_B p_lambda$getStitchedSprites$4_5_, int p_lambda$getStitchedSprites$4_6_, int p_lambda$getStitchedSprites$4_7_, int p_lambda$getStitchedSprites$4_8_, int p_lambda$getStitchedSprites$4_9_) -> {
            if (p_lambda$getStitchedSprites$4_5_.n_1700_B().equals(F_3565_Q.J_1907_R().n_1700_B())) {
                F_3565_Q missingtexturesprite = new F_3565_Q(this, p_lambda$getStitchedSprites$4_5_, mipmapLevelIn, p_lambda$getStitchedSprites$4_6_, p_lambda$getStitchedSprites$4_7_, p_lambda$getStitchedSprites$4_8_, p_lambda$getStitchedSprites$4_9_);
                missingtexturesprite.n_1700_B(resourceManagerIn);
                concurrentlinkedqueue.add(missingtexturesprite);
            } else {
                list.add(CompletableFuture.runAsync(() -> {
                    B_3871_I textureatlassprite = this.n_1700_B(resourceManagerIn, p_lambda$getStitchedSprites$4_5_, p_lambda$getStitchedSprites$4_6_, p_lambda$getStitchedSprites$4_7_, mipmapLevelIn, p_lambda$getStitchedSprites$4_8_, p_lambda$getStitchedSprites$4_9_);
                    if (textureatlassprite != null) {
                        concurrentlinkedqueue.add(textureatlassprite);
                    }
                }, j_3341_s.u_1723_Y()));
            }
        });
        CompletableFuture.allOf(list.toArray(new CompletableFuture[0])).join();
        return Lists.newArrayList(concurrentlinkedqueue);
    }

    @Nullable
    private B_3871_I n_1700_B(ResourceManager resourceManagerIn, B_3871_I.n_1700_B spriteInfoIn, int widthIn, int heightIn, int mipmapLevelIn, int originX, int originY) {
        B_3871_I b_3871_I;
        block9: {
            g_2336_b resourcelocation = this.n_1700_B(spriteInfoIn.n_1700_B());
            Resource iresource = resourceManagerIn.n_1700_B(resourcelocation);
            try {
                i_2518_W nativeimage = i_2518_W.n_1700_B(iresource.J_1907_R());
                B_3871_I textureatlassprite = new B_3871_I(this, spriteInfoIn, mipmapLevelIn, widthIn, heightIn, originX, originY, nativeimage);
                textureatlassprite.n_1700_B(resourceManagerIn);
                b_3871_I = textureatlassprite;
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
                catch (RuntimeException runtimeexception) {
                    u_1723_Y.error("Unable to parse metadata from {}", (Object)resourcelocation, (Object)runtimeexception);
                    return null;
                }
                catch (IOException ioexception1) {
                    u_1723_Y.error("Using missing texture, unable to load {}", (Object)resourcelocation, (Object)ioexception1);
                    return null;
                }
            }
            iresource.close();
        }
        return b_3871_I;
    }

    public g_2336_b n_1700_B(g_2336_b location) {
        return this.u_1723_Y(location) ? new g_2336_b(location.R_4764_Y(), location.J_1907_R() + ".png") : new g_2336_b(location.R_4764_Y(), String.format("textures/%s%s", location.J_1907_R(), ".png"));
    }

    public void n_1700_B() {
        boolean flag = false;
        boolean flag1 = false;
        if (!this.v_4262_N.isEmpty()) {
            this.bindTexture();
        }
        int i = 0;
        for (B_3871_I textureatlassprite : this.v_4262_N) {
            if (!this.n_1700_B(textureatlassprite)) continue;
            textureatlassprite.Q_4569_t();
            if (textureatlassprite.w_1457_N()) {
                ++i;
            }
            if (textureatlassprite.u_2550_I != null) {
                flag = true;
            }
            if (textureatlassprite.M_588_G == null) continue;
            flag1 = true;
        }
        if (Config.isShaders()) {
            if (flag) {
                X_933_l.w_1457_N(this.getMultiTexID().norm);
                for (B_3871_I textureatlassprite1 : this.v_4262_N) {
                    if (textureatlassprite1.u_2550_I == null || !this.n_1700_B(textureatlassprite1) || !textureatlassprite1.w_1457_N()) continue;
                    textureatlassprite1.u_2550_I.Q_4569_t();
                    if (!textureatlassprite1.u_2550_I.w_1457_N()) continue;
                    ++i;
                }
            }
            if (flag1) {
                X_933_l.w_1457_N(this.getMultiTexID().spec);
                for (B_3871_I textureatlassprite2 : this.v_4262_N) {
                    if (textureatlassprite2.M_588_G == null || !this.n_1700_B(textureatlassprite2) || !textureatlassprite2.w_1457_N()) continue;
                    textureatlassprite2.M_588_G.Q_4569_t();
                    if (!textureatlassprite2.M_588_G.w_1457_N()) continue;
                    ++i;
                }
            }
            if (flag || flag1) {
                X_933_l.w_1457_N(this.getGlTextureId());
            }
        }
        if (Config.isMultiTexture()) {
            for (B_3871_I textureatlassprite3 : this.v_4262_N) {
                if (!this.n_1700_B(textureatlassprite3) || !textureatlassprite3.w_1457_N()) continue;
                i += L_3848_p.R_4764_Y(textureatlassprite3);
                if (textureatlassprite3.u_2550_I != null) {
                    i += L_3848_p.R_4764_Y(textureatlassprite3.u_2550_I);
                }
                if (textureatlassprite3.M_588_G == null) continue;
                i += L_3848_p.R_4764_Y(textureatlassprite3.M_588_G);
            }
            X_933_l.w_1457_N(this.getGlTextureId());
        }
        if (this.C_2741_M) {
            int j = Config.getMinecraft().u_1723_Y.q_2307_F();
            if (j != this.Q_2552_b) {
                this.Y_259_p = i;
                this.Q_2552_b = j;
            }
            if (SmartAnimations.isActive()) {
                SmartAnimations.resetSpritesRendered(this);
            }
        }
    }

    @Override
    public void tick() {
        if (!c_4037_x.J_1907_R()) {
            c_4037_x.n_1700_B(this::n_1700_B);
        } else {
            this.n_1700_B();
        }
    }

    public B_3871_I J_1907_R(g_2336_b location) {
        B_3871_I textureatlassprite = this.t_148_a.get(location);
        return textureatlassprite == null ? this.t_148_a.get(F_3565_Q.n_1700_B()) : textureatlassprite;
    }

    public void J_1907_R() {
        for (B_3871_I textureatlassprite : this.t_148_a.values()) {
            textureatlassprite.close();
        }
        if (this.q_2307_F) {
            for (B_3871_I textureatlassprite1 : this.t_148_a.values()) {
                textureatlassprite1.Y_259_p();
                if (textureatlassprite1.u_2550_I != null) {
                    textureatlassprite1.u_2550_I.Y_259_p();
                }
                if (textureatlassprite1.M_588_G == null) continue;
                textureatlassprite1.M_588_G.Y_259_p();
            }
        }
        this.t_148_a.clear();
        this.v_4262_N.clear();
    }

    public g_2336_b R_4764_Y() {
        return this.s_956_w;
    }

    public void J_1907_R(n_1700_B sheetDataIn) {
        this.setBlurMipmapDirect(false, sheetDataIn.G_564_y > 0);
    }

    private boolean u_1723_Y(g_2336_b p_isAbsoluteLocation_1_) {
        String s = p_isAbsoluteLocation_1_.J_1907_R();
        return this.R_4764_Y(s);
    }

    private boolean R_4764_Y(String p_isAbsoluteLocationPath_1_) {
        String s = p_isAbsoluteLocationPath_1_.toLowerCase();
        return s.startsWith("optifine/");
    }

    public B_3871_I n_1700_B(String p_getRegisteredSprite_1_) {
        g_2336_b resourcelocation = new g_2336_b(p_getRegisteredSprite_1_);
        return this.R_4764_Y(resourcelocation);
    }

    public B_3871_I R_4764_Y(g_2336_b p_getRegisteredSprite_1_) {
        return this.M_588_G.get(p_getRegisteredSprite_1_);
    }

    public B_3871_I J_1907_R(String p_getUploadedSprite_1_) {
        g_2336_b resourcelocation = new g_2336_b(p_getUploadedSprite_1_);
        return this.G_564_y(resourcelocation);
    }

    public B_3871_I G_564_y(g_2336_b p_getUploadedSprite_1_) {
        return this.t_148_a.get(p_getUploadedSprite_1_);
    }

    private boolean n_1700_B(B_3871_I p_isAnimationEnabled_1_) {
        if (!this.C_2741_M) {
            return true;
        }
        if (p_isAnimationEnabled_1_ != TextureUtils.iconWaterStill && p_isAnimationEnabled_1_ != TextureUtils.iconWaterFlow) {
            if (p_isAnimationEnabled_1_ != TextureUtils.iconLavaStill && p_isAnimationEnabled_1_ != TextureUtils.iconLavaFlow) {
                if (p_isAnimationEnabled_1_ != TextureUtils.iconFireLayer0 && p_isAnimationEnabled_1_ != TextureUtils.iconFireLayer1) {
                    if (p_isAnimationEnabled_1_ != TextureUtils.iconSoulFireLayer0 && p_isAnimationEnabled_1_ != TextureUtils.iconSoulFireLayer1) {
                        if (p_isAnimationEnabled_1_ != TextureUtils.iconCampFire && p_isAnimationEnabled_1_ != TextureUtils.iconCampFireLogLit) {
                            if (p_isAnimationEnabled_1_ != TextureUtils.iconSoulCampFire && p_isAnimationEnabled_1_ != TextureUtils.iconSoulCampFireLogLit) {
                                return p_isAnimationEnabled_1_ == TextureUtils.iconPortal ? Config.isAnimatedPortal() : Config.isAnimatedTerrain();
                            }
                            return Config.isAnimatedFire();
                        }
                        return Config.isAnimatedFire();
                    }
                    return Config.isAnimatedFire();
                }
                return Config.isAnimatedFire();
            }
            return Config.isAnimatedLava();
        }
        return Config.isAnimatedWater();
    }

    private static void J_1907_R(B_3871_I p_uploadMipmapsSingle_0_) {
        B_3871_I textureatlassprite = p_uploadMipmapsSingle_0_.v_4262_N;
        if (textureatlassprite != null) {
            textureatlassprite.n_1700_B(p_uploadMipmapsSingle_0_.multiplayerClientSuggestionProvider());
            p_uploadMipmapsSingle_0_.Y_601_j();
            try {
                textureatlassprite.P_4830_p();
            }
            catch (Exception exception) {
                Config.dbg("Error uploading sprite single: " + String.valueOf(textureatlassprite) + ", parent: " + String.valueOf(p_uploadMipmapsSingle_0_));
                exception.printStackTrace();
            }
        }
    }

    private static int R_4764_Y(B_3871_I p_updateAnimationSingle_0_) {
        B_3871_I textureatlassprite = p_updateAnimationSingle_0_.v_4262_N;
        if (textureatlassprite != null) {
            p_updateAnimationSingle_0_.Y_601_j();
            textureatlassprite.Q_4569_t();
            if (textureatlassprite.w_1457_N()) {
                return 1;
            }
        }
        return 0;
    }

    public int G_564_y() {
        return this.Y_601_j.getValue();
    }

    private int n_1700_B(Set<g_2336_b> p_detectMaxMipmapLevel_1_, ResourceManager p_detectMaxMipmapLevel_2_) {
        int j;
        int i = this.n_1700_B(p_detectMaxMipmapLevel_1_, p_detectMaxMipmapLevel_2_, 20);
        if (i < 16) {
            i = 16;
        }
        if ((i = u_530_F.R_4764_Y(i)) > 16) {
            Config.log("Sprite size: " + i);
        }
        if ((j = u_530_F.u_1723_Y(i)) < 4) {
            j = 4;
        }
        return j;
    }

    private int n_1700_B(Set<g_2336_b> p_detectMinimumSpriteSize_1_, ResourceManager p_detectMinimumSpriteSize_2_, int p_detectMinimumSpriteSize_3_) {
        Object iresource2;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (g_2336_b resourcelocation : p_detectMinimumSpriteSize_1_) {
            g_2336_b resourcelocation1 = this.n_1700_B(resourcelocation);
            try {
                InputStream inputstream;
                iresource2 = p_detectMinimumSpriteSize_2_.n_1700_B(resourcelocation1);
                if (iresource2 == null || (inputstream = iresource2.J_1907_R()) == null) continue;
                Dimension dimension = TextureUtils.getImageSize(inputstream, "png");
                inputstream.close();
                if (dimension == null) continue;
                int i = dimension.width;
                int j = u_530_F.R_4764_Y(i);
                if (!map.containsKey(j)) {
                    map.put(j, 1);
                    continue;
                }
                int k = (Integer)map.get(j);
                map.put(j, k + 1);
            }
            catch (Exception iresource2) {}
        }
        int l = 0;
        Set set = map.keySet();
        TreeSet set1 = new TreeSet(set);
        iresource2 = set1.iterator();
        while (iresource2.hasNext()) {
            int j1 = (Integer)iresource2.next();
            int l1 = (Integer)map.get(j1);
            l += l1;
        }
        int i1 = 16;
        int k1 = 0;
        int i2 = l * p_detectMinimumSpriteSize_3_ / 100;
        Iterator iterator = set1.iterator();
        while (iterator.hasNext()) {
            int j2 = (Integer)iterator.next();
            int k2 = (Integer)map.get(j2);
            k1 += k2;
            if (j2 > i1) {
                i1 = j2;
            }
            if (k1 <= i2) continue;
            return i1;
        }
        return i1;
    }

    private static int n_1700_B(int p_getMinSpriteSize_0_) {
        int i = 1 << p_getMinSpriteSize_0_;
        if (i < 8) {
            i = 8;
        }
        return i;
    }

    private static B_3871_I.n_1700_B n_1700_B(B_3871_I.n_1700_B p_fixSpriteSize_0_, int p_fixSpriteSize_1_) {
        if (p_fixSpriteSize_0_.J_1907_R() >= p_fixSpriteSize_1_ && p_fixSpriteSize_0_.R_4764_Y() >= p_fixSpriteSize_1_) {
            return p_fixSpriteSize_0_;
        }
        int i = Math.max(p_fixSpriteSize_0_.J_1907_R(), p_fixSpriteSize_1_);
        int j = Math.max(p_fixSpriteSize_0_.R_4764_Y(), p_fixSpriteSize_1_);
        return new B_3871_I.n_1700_B(p_fixSpriteSize_0_.n_1700_B(), i, j, p_fixSpriteSize_0_.G_564_y());
    }

    public boolean P_1922_E() {
        int j;
        int i = X_933_l.N_2525_X();
        return i == (j = this.getGlTextureId());
    }

    private void n_1700_B(int p_updateIconGrid_1_, int p_updateIconGrid_2_) {
        this.M_182_A = -1;
        this.t_1786_h = -1;
        this.h_1847_R = null;
        if (this.Q_4569_t > 0) {
            this.M_182_A = p_updateIconGrid_1_ / this.Q_4569_t;
            this.t_1786_h = p_updateIconGrid_2_ / this.Q_4569_t;
            this.h_1847_R = new B_3871_I[this.M_182_A * this.t_1786_h];
            this.multiplayerClientSuggestionProvider = 1.0 / (double)this.M_182_A;
            this.w_1457_N = 1.0 / (double)this.t_1786_h;
            for (B_3871_I textureatlassprite : this.t_148_a.values()) {
                double d0 = 0.5 / (double)p_updateIconGrid_1_;
                double d1 = 0.5 / (double)p_updateIconGrid_2_;
                double d2 = (double)Math.min(textureatlassprite.u_1723_Y(), textureatlassprite.v_4262_N()) + d0;
                double d3 = (double)Math.min(textureatlassprite.w_1484_f(), textureatlassprite.t_148_a()) + d1;
                double d4 = (double)Math.max(textureatlassprite.u_1723_Y(), textureatlassprite.v_4262_N()) - d0;
                double d5 = (double)Math.max(textureatlassprite.w_1484_f(), textureatlassprite.t_148_a()) - d1;
                int i = (int)(d2 / this.multiplayerClientSuggestionProvider);
                int j = (int)(d3 / this.w_1457_N);
                int k = (int)(d4 / this.multiplayerClientSuggestionProvider);
                int l = (int)(d5 / this.w_1457_N);
                for (int i1 = i; i1 <= k; ++i1) {
                    if (i1 >= 0 && i1 < this.M_182_A) {
                        for (int j1 = j; j1 <= l; ++j1) {
                            if (j1 >= 0 && j1 < this.M_182_A) {
                                int k1 = j1 * this.M_182_A + i1;
                                this.h_1847_R[k1] = textureatlassprite;
                                continue;
                            }
                            Config.warn("Invalid grid V: " + j1 + ", icon: " + String.valueOf(textureatlassprite.s_956_w()));
                        }
                        continue;
                    }
                    Config.warn("Invalid grid U: " + i1 + ", icon: " + String.valueOf(textureatlassprite.s_956_w()));
                }
            }
        }
    }

    public B_3871_I n_1700_B(double p_getIconByUV_1_, double p_getIconByUV_3_) {
        if (this.h_1847_R == null) {
            return null;
        }
        int j = (int)(p_getIconByUV_3_ / this.w_1457_N);
        int i = (int)(p_getIconByUV_1_ / this.multiplayerClientSuggestionProvider);
        int k = j * this.M_182_A + i;
        return k >= 0 && k <= this.h_1847_R.length ? this.h_1847_R[k] : null;
    }

    public int u_1723_Y() {
        return this.v_4262_N.size();
    }

    public int v_4262_N() {
        return this.Y_259_p;
    }

    public B_3871_I P_1922_E(g_2336_b p_registerSprite_1_) {
        if (p_registerSprite_1_ == null) {
            throw new IllegalArgumentException("Location cannot be null!");
        }
        B_3871_I textureatlassprite = this.M_588_G.get(p_registerSprite_1_);
        if (textureatlassprite != null) {
            return textureatlassprite;
        }
        this.w_1484_f.add(p_registerSprite_1_);
        textureatlassprite = new B_3871_I(p_registerSprite_1_);
        this.M_588_G.put(p_registerSprite_1_, textureatlassprite);
        textureatlassprite.n_1700_B(this.Y_601_j);
        return textureatlassprite;
    }

    public Collection<B_3871_I> w_1484_f() {
        return Collections.unmodifiableCollection(this.M_588_G.values());
    }

    public boolean t_148_a() {
        return this.C_2741_M;
    }

    public CounterInt s_956_w() {
        return this.Y_601_j;
    }

    private void v_4262_N(g_2336_b p_onSpriteMissing_1_) {
        B_3871_I textureatlassprite = this.M_588_G.get(p_onSpriteMissing_1_);
        if (textureatlassprite != null) {
            this.P_4830_p.put(p_onSpriteMissing_1_, textureatlassprite);
        }
    }

    private static <T> Set<T> n_1700_B(Set<T> p_newHashSet_0_, Set<T> p_newHashSet_1_) {
        HashSet<T> set = new HashSet<T>();
        set.addAll(p_newHashSet_0_);
        set.addAll(p_newHashSet_1_);
        return set;
    }

    public int u_2550_I() {
        return this.P_1922_E;
    }

    public boolean M_588_G() {
        return this.P_1922_E > 0;
    }

    public ITextureFormat P_4830_p() {
        return this.Z_875_P;
    }

    public IColorBlender n_1700_B(ShadersTextureType p_getShadersColorBlender_1_) {
        if (p_getShadersColorBlender_1_ == null) {
            return null;
        }
        return this.Z_875_P != null ? this.Z_875_P.getColorBlender(p_getShadersColorBlender_1_) : new ColorBlenderLinear();
    }

    public boolean J_1907_R(ShadersTextureType p_isTextureBlend_1_) {
        if (p_isTextureBlend_1_ == null) {
            return true;
        }
        return this.Z_875_P != null ? this.Z_875_P.isTextureBlend(p_isTextureBlend_1_) : true;
    }

    public boolean h_1847_R() {
        return this.J_1907_R(ShadersTextureType.NORMAL);
    }

    public boolean Q_4569_t() {
        return this.J_1907_R(ShadersTextureType.SPECULAR);
    }

    public String toString() {
        return String.valueOf(this.s_956_w);
    }

    public static class n_1700_B {
        final Set<g_2336_b> n_1700_B;
        final int J_1907_R;
        final int R_4764_Y;
        final int G_564_y;
        final List<B_3871_I> P_1922_E;

        public n_1700_B(Set<g_2336_b> spriteLocationsIn, int widthIn, int heightIn, int mipmapLevelIn, List<B_3871_I> spritesIn) {
            this.n_1700_B = spriteLocationsIn;
            this.J_1907_R = widthIn;
            this.R_4764_Y = heightIn;
            this.G_564_y = mipmapLevelIn;
            this.P_1922_E = spritesIn;
        }
    }
}



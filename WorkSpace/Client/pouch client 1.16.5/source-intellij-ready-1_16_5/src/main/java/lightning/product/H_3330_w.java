/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Sets;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.C_3240_x;
import lightning.product.D_265_n;
import lightning.product.D_3318_r;
import lightning.product.D_4792_h;
import lightning.product.E_688_b;
import lightning.product.ItemTransforms;
import lightning.product.ResourceManagerReloadListener;
import lightning.product.L_3848_p;
import lightning.product.L_4237_Q;
import lightning.product.N_1599_o;
import lightning.product.ItemPhysics;
import lightning.product.ItemModelShaper;
import lightning.product.ResourceManager;
import lightning.product.S_3826_o;
import lightning.product.T_2915_h;
import lightning.product.HalfTransparentBlock;
import lightning.product.V_3137_a;
import lightning.product.V_772_m;
import lightning.product.V_983_n;
import lightning.product.W_3265_k;
import lightning.product.W_3959_H;
import lightning.product.X_933_l;
import lightning.product.Y_4083_F;
import lightning.product.Z_1993_T;
import lightning.product.Z_3224_L;
import lightning.product.b_257_Y;
import lightning.product.b_4440_Q;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.c_932_S;
import lightning.product.d_1062_x;
import lightning.product.d_1620_j;
import lightning.product.e_1689_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.ModelManager;
import lightning.product.k_4690_i;
import lightning.product.l_1233_K;
import lightning.product.l_3747_P;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.ClientBootstrap;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.CrashReportCategory;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.v_1669_V;
import lightning.product.z_1333_t;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.VanillaResourceType;
import net.optifine.Config;
import net.optifine.CustomColors;
import net.optifine.CustomItems;
import net.optifine.EmissiveTextures;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.render.VertexBuilderWrapper;
import net.optifine.shaders.Shaders;

public class H_3330_w
implements ResourceManagerReloadListener {
    public static final g_2336_b n_1700_B = new g_2336_b("textures/misc/enchanted_item_glint.png");
    private static final Set<q_1613_l> G_564_y = Sets.newHashSet((Object[])new q_1613_l[]{Items.n_1700_B});
    public float J_1907_R;
    private final ItemModelShaper P_1922_E;
    private final C_3240_x u_1723_Y;
    private final N_1599_o v_4262_N;
    public ModelManager R_4764_Y = null;
    private static boolean w_1484_f = false;

    public H_3330_w(C_3240_x textureManagerIn, ModelManager modelManagerIn, N_1599_o itemColorsIn) {
        this.u_1723_Y = textureManagerIn;
        this.R_4764_Y = modelManagerIn;
        this.P_1922_E = Reflector.ItemModelMesherForge_Constructor.exists() ? (ItemModelShaper)Reflector.newInstance(Reflector.ItemModelMesherForge_Constructor, this.R_4764_Y) : new ItemModelShaper(modelManagerIn);
        for (q_1613_l item : V_3137_a.e_2887_G) {
            if (G_564_y.contains(item)) continue;
            this.P_1922_E.n_1700_B(item, new d_1062_x(V_3137_a.e_2887_G.J_1907_R(item), "inventory"));
        }
        this.v_4262_N = itemColorsIn;
    }

    public ItemModelShaper J_1907_R() {
        return this.P_1922_E;
    }

    public void n_1700_B(S_3826_o modelIn, Z_1993_T stack, int combinedLightIn, int combinedOverlayIn, g_221_o matrixStackIn, D_4792_h bufferIn) {
        if (Config.isMultiTexture()) {
            bufferIn.setRenderBlocks(true);
        }
        Random random = new Random();
        long i = 42L;
        for (b_257_Y direction : b_257_Y.v_4262_N) {
            random.setSeed(42L);
            this.n_1700_B(matrixStackIn, bufferIn, modelIn.n_1700_B(null, direction, random), stack, combinedLightIn, combinedOverlayIn);
        }
        random.setSeed(42L);
        this.n_1700_B(matrixStackIn, bufferIn, modelIn.n_1700_B(null, null, random), stack, combinedLightIn, combinedOverlayIn);
    }

    public void n_1700_B(Z_1993_T itemStackIn, ItemTransforms.J_1907_R transformTypeIn, boolean leftHand, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn, S_3826_o modelIn) {
        if (!itemStackIn.n_1700_B()) {
            boolean flag;
            boolean physicsEnabled;
            matrixStackIn.n_1700_B();
            boolean isGroundItem = transformTypeIn == ItemTransforms.J_1907_R.w_1484_f;
            boolean bl = physicsEnabled = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(ItemPhysics.class).w_1484_f() && ItemPhysics.v_4262_N.t_148_a() != false;
            if (isGroundItem && physicsEnabled) {
                matrixStackIn.n_1700_B(0.5f, 0.5f, 0.5f);
            }
            boolean bl2 = flag = transformTypeIn == ItemTransforms.J_1907_R.v_4262_N || transformTypeIn == ItemTransforms.J_1907_R.w_1484_f || transformTypeIn == ItemTransforms.J_1907_R.t_148_a;
            if (itemStackIn.J_1907_R() == Items.P_2605_j && flag) {
                modelIn = this.P_1922_E.n_1700_B().n_1700_B(new d_1062_x("minecraft:trident#inventory"));
            }
            if (V_983_n.n_1700_B(itemStackIn, transformTypeIn, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn)) {
                matrixStackIn.J_1907_R();
                return;
            }
            if (Reflector.ForgeHooksClient_handleCameraTransforms.exists()) {
                modelIn = (S_3826_o)Reflector.ForgeHooksClient_handleCameraTransforms.call(new Object[]{matrixStackIn, modelIn, transformTypeIn, leftHand});
            } else {
                modelIn.u_1723_Y().n_1700_B(transformTypeIn).n_1700_B(leftHand, matrixStackIn);
            }
            matrixStackIn.n_1700_B(-0.5, -0.5, -0.5);
            if (!modelIn.G_564_y() && (itemStackIn.J_1907_R() != Items.P_2605_j || flag)) {
                T_2915_h block;
                boolean flag1 = transformTypeIn != ItemTransforms.J_1907_R.v_4262_N && !transformTypeIn.n_1700_B() && itemStackIn.J_1907_R() instanceof v_1669_V ? !((block = ((v_1669_V)itemStackIn.J_1907_R()).v_4262_N()) instanceof HalfTransparentBlock) && !(block instanceof D_265_n) : true;
                if (modelIn.isLayered()) {
                    Reflector.ForgeHooksClient_drawItemLayered.call(this, modelIn, itemStackIn, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, flag1);
                } else {
                    D_4792_h ivertexbuilder;
                    o_2576_A rendertype = d_1620_j.n_1700_B(itemStackIn, flag1);
                    if (itemStackIn.J_1907_R() == Items.X_1303_p && itemStackIn.Y_259_p()) {
                        matrixStackIn.n_1700_B();
                        g_221_o.n_1700_B matrixstack$entry = matrixStackIn.R_4764_Y();
                        if (transformTypeIn == ItemTransforms.J_1907_R.v_4262_N) {
                            matrixstack$entry.n_1700_B().n_1700_B(0.5f);
                        } else if (transformTypeIn.n_1700_B()) {
                            matrixstack$entry.n_1700_B().n_1700_B(0.75f);
                        }
                        ivertexbuilder = flag1 ? H_3330_w.J_1907_R(bufferIn, rendertype, matrixstack$entry) : H_3330_w.n_1700_B(bufferIn, rendertype, matrixstack$entry);
                        matrixStackIn.J_1907_R();
                    } else {
                        ivertexbuilder = flag1 ? H_3330_w.R_4764_Y(bufferIn, rendertype, true, itemStackIn.Y_259_p()) : H_3330_w.J_1907_R(bufferIn, rendertype, true, itemStackIn.Y_259_p());
                    }
                    if (Config.isCustomItems()) {
                        modelIn = CustomItems.getCustomItemModel(itemStackIn, modelIn, L_4237_Q.J_1907_R, false);
                        L_4237_Q.J_1907_R = null;
                    }
                    if (EmissiveTextures.isActive()) {
                        EmissiveTextures.beginRender();
                    }
                    this.n_1700_B(modelIn, itemStackIn, combinedLightIn, combinedOverlayIn, matrixStackIn, ivertexbuilder);
                    if (EmissiveTextures.isActive()) {
                        if (EmissiveTextures.hasEmissive()) {
                            EmissiveTextures.beginRenderEmissive();
                            D_4792_h ivertexbuilder1 = ivertexbuilder instanceof VertexBuilderWrapper ? ((VertexBuilderWrapper)ivertexbuilder).getVertexBuilder() : ivertexbuilder;
                            this.n_1700_B(modelIn, itemStackIn, e_1689_x.n_1700_B, combinedOverlayIn, matrixStackIn, ivertexbuilder1);
                            EmissiveTextures.endRenderEmissive();
                        }
                        EmissiveTextures.endRender();
                    }
                }
            } else if (Reflector.IForgeItem_getItemStackTileEntityRenderer.exists()) {
                W_3959_H itemstacktileentityrenderer = (W_3959_H)Reflector.call(itemStackIn.J_1907_R(), Reflector.IForgeItem_getItemStackTileEntityRenderer, new Object[0]);
                itemstacktileentityrenderer.n_1700_B(itemStackIn, transformTypeIn, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
            } else {
                W_3959_H.n_1700_B.n_1700_B(itemStackIn, transformTypeIn, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
            }
            matrixStackIn.J_1907_R();
        }
    }

    public static D_4792_h n_1700_B(o_3091_w buffer, o_2576_A renderType, boolean noEntity, boolean withGlint) {
        if (Shaders.isShadowPass) {
            withGlint = false;
        }
        if (EmissiveTextures.isRenderEmissive()) {
            withGlint = false;
        }
        return withGlint ? z_1333_t.n_1700_B(buffer.getBuffer(noEntity ? o_2576_A.h_1847_R() : o_2576_A.Q_4569_t()), buffer.getBuffer(renderType)) : buffer.getBuffer(renderType);
    }

    public static D_4792_h n_1700_B(o_3091_w buffer, o_2576_A renderType, g_221_o.n_1700_B matrixEntry) {
        return z_1333_t.n_1700_B(new l_1233_K(buffer.getBuffer(o_2576_A.t_1786_h()), matrixEntry.n_1700_B(), matrixEntry.J_1907_R()), buffer.getBuffer(renderType));
    }

    public static D_4792_h J_1907_R(o_3091_w buffer, o_2576_A renderType, g_221_o.n_1700_B matrixEntry) {
        return z_1333_t.n_1700_B(new l_1233_K(buffer.getBuffer(o_2576_A.multiplayerClientSuggestionProvider()), matrixEntry.n_1700_B(), matrixEntry.J_1907_R()), buffer.getBuffer(renderType));
    }

    public static D_4792_h J_1907_R(o_3091_w bufferIn, o_2576_A renderTypeIn, boolean isItemIn, boolean glintIn) {
        if (Shaders.isShadowPass) {
            glintIn = false;
        }
        if (EmissiveTextures.isRenderEmissive()) {
            glintIn = false;
        }
        if (!glintIn) {
            return bufferIn.getBuffer(renderTypeIn);
        }
        return MinecraftClient.c_3005_b() && renderTypeIn == b_4440_Q.t_148_a() ? z_1333_t.n_1700_B(bufferIn.getBuffer(o_2576_A.M_182_A()), bufferIn.getBuffer(renderTypeIn)) : z_1333_t.n_1700_B(bufferIn.getBuffer(isItemIn ? o_2576_A.t_1786_h() : o_2576_A.w_1457_N()), bufferIn.getBuffer(renderTypeIn));
    }

    public static D_4792_h R_4764_Y(o_3091_w buffer, o_2576_A renderType, boolean noEntity, boolean withGlint) {
        if (Shaders.isShadowPass) {
            withGlint = false;
        }
        if (EmissiveTextures.isRenderEmissive()) {
            withGlint = false;
        }
        return withGlint ? z_1333_t.n_1700_B(buffer.getBuffer(noEntity ? o_2576_A.multiplayerClientSuggestionProvider() : o_2576_A.Y_601_j()), buffer.getBuffer(renderType)) : buffer.getBuffer(renderType);
    }

    private void n_1700_B(g_221_o matrixStackIn, D_4792_h bufferIn, List<c_932_S> quadsIn, Z_1993_T itemStackIn, int combinedLightIn, int combinedOverlayIn) {
        boolean flag = !itemStackIn.n_1700_B();
        g_221_o.n_1700_B matrixstack$entry = matrixStackIn.R_4764_Y();
        boolean flag1 = EmissiveTextures.isActive();
        int i = quadsIn.size();
        for (int j = 0; j < i; ++j) {
            c_932_S bakedquad = quadsIn.get(j);
            if (flag1 && (bakedquad = EmissiveTextures.getEmissiveQuad(bakedquad)) == null) continue;
            int k = -1;
            if (flag && bakedquad.hasTintIndex()) {
                k = this.v_4262_N.n_1700_B(itemStackIn, bakedquad.getTintIndex());
                if (Config.isCustomColors()) {
                    k = CustomColors.getColorFromItemStack(itemStackIn, bakedquad.getTintIndex(), k);
                }
            }
            float f = (float)(k >> 16 & 0xFF) / 255.0f;
            float f1 = (float)(k >> 8 & 0xFF) / 255.0f;
            float f2 = (float)(k & 0xFF) / 255.0f;
            if (Reflector.ForgeHooksClient.exists()) {
                bufferIn.addVertexData(matrixstack$entry, bakedquad, f, f1, f2, combinedLightIn, combinedOverlayIn, true);
                continue;
            }
            bufferIn.n_1700_B(matrixstack$entry, bakedquad, f, f1, f2, combinedLightIn, combinedOverlayIn);
        }
    }

    public S_3826_o n_1700_B(Z_1993_T stack, @Nullable b_4507_u worldIn, @Nullable r_4811_B entitylivingbaseIn) {
        q_1613_l item = stack.J_1907_R();
        S_3826_o ibakedmodel = item == Items.P_2605_j ? this.P_1922_E.n_1700_B().n_1700_B(new d_1062_x("minecraft:trident_in_hand#inventory")) : this.P_1922_E.J_1907_R(stack);
        k_4690_i clientworld = worldIn instanceof k_4690_i ? (k_4690_i)worldIn : null;
        L_4237_Q.J_1907_R = null;
        S_3826_o ibakedmodel1 = ibakedmodel.v_4262_N().n_1700_B(ibakedmodel, stack, clientworld, entitylivingbaseIn);
        if (Config.isCustomItems()) {
            ibakedmodel1 = CustomItems.getCustomItemModel(stack, ibakedmodel1, L_4237_Q.J_1907_R, true);
        }
        return ibakedmodel1 == null ? this.P_1922_E.n_1700_B().J_1907_R() : ibakedmodel1;
    }

    public void n_1700_B(Z_1993_T itemStackIn, ItemTransforms.J_1907_R transformTypeIn, int combinedLightIn, int combinedOverlayIn, g_221_o matrixStackIn, o_3091_w bufferIn) {
        this.n_1700_B((r_4811_B)null, itemStackIn, transformTypeIn, false, matrixStackIn, bufferIn, null, combinedLightIn, combinedOverlayIn);
    }

    public void n_1700_B(@Nullable r_4811_B livingEntityIn, Z_1993_T itemStackIn, ItemTransforms.J_1907_R transformTypeIn, boolean leftHand, g_221_o matrixStackIn, o_3091_w bufferIn, @Nullable b_4507_u worldIn, int combinedLightIn, int combinedOverlayIn) {
        if (!itemStackIn.n_1700_B()) {
            S_3826_o ibakedmodel = this.n_1700_B(itemStackIn, worldIn, livingEntityIn);
            this.n_1700_B(itemStackIn, transformTypeIn, leftHand, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
        }
    }

    public void n_1700_B(Z_1993_T stack, int x, int y) {
        this.n_1700_B(stack, x, y, this.n_1700_B(stack, null, null));
    }

    protected void n_1700_B(Z_1993_T stack, int x, int y, S_3826_o bakedmodel) {
        boolean flag;
        w_1484_f = true;
        c_4037_x.v_4276_D();
        this.u_1723_Y.n_1700_B(L_3848_p.n_1700_B);
        this.u_1723_Y.J_1907_R(L_3848_p.n_1700_B).setBlurMipmapDirect(false, false);
        c_4037_x.n_3318_d();
        c_4037_x.M_588_G();
        c_4037_x.l_1233_K();
        c_4037_x.Y_601_j();
        c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        c_4037_x.R_4764_Y((float)x, (float)y, 100.0f + this.J_1907_R);
        c_4037_x.R_4764_Y(8.0f, 8.0f, 0.0f);
        c_4037_x.J_1907_R(1.0f, -1.0f, 1.0f);
        c_4037_x.J_1907_R(16.0f, 16.0f, 16.0f);
        g_221_o matrixstack = new g_221_o();
        o_3091_w.n_1700_B irendertypebuffer$impl = MinecraftClient.A_4115_X().j_1564_a().J_1907_R();
        boolean bl = flag = !bakedmodel.R_4764_Y();
        if (flag) {
            W_3265_k.R_4764_Y();
        }
        this.n_1700_B(stack, ItemTransforms.J_1907_R.v_4262_N, false, matrixstack, irendertypebuffer$impl, 0xF000F0, Z_3224_L.n_1700_B, bakedmodel);
        irendertypebuffer$impl.J_1907_R();
        c_4037_x.multiplayerClientSuggestionProvider();
        if (flag) {
            W_3265_k.G_564_y();
        }
        c_4037_x.u_2550_I();
        c_4037_x.d_2427_y();
        c_4037_x.d_2461_k();
        w_1484_f = false;
    }

    public void J_1907_R(Z_1993_T stack, int xPosition, int yPosition) {
        this.J_1907_R(MinecraftClient.A_4115_X().Y_259_p, stack, xPosition, yPosition);
    }

    public void R_4764_Y(Z_1993_T stack, int x, int y) {
        this.J_1907_R(null, stack, x, y);
    }

    public void n_1700_B(r_4811_B entityIn, Z_1993_T itemIn, int x, int y) {
        this.J_1907_R(entityIn, itemIn, x, y);
    }

    private void J_1907_R(@Nullable r_4811_B livingEntity, Z_1993_T stack, int x, int y) {
        if (!stack.n_1700_B()) {
            this.J_1907_R += 50.0f;
            try {
                this.n_1700_B(stack, x, y, this.n_1700_B(stack, null, livingEntity));
            }
            catch (Throwable throwable) {
                n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Rendering item");
                CrashReportCategory crashreportcategory = crashreport.n_1700_B("Item being rendered");
                crashreportcategory.n_1700_B("Item Type", () -> String.valueOf(stack.J_1907_R()));
                crashreportcategory.n_1700_B("Registry Name", () -> String.valueOf(Reflector.call(stack.J_1907_R(), Reflector.ForgeRegistryEntry_getRegistryName, new Object[0])));
                crashreportcategory.n_1700_B("Item Damage", () -> String.valueOf(stack.v_4262_N()));
                crashreportcategory.n_1700_B("Item NBT", () -> String.valueOf(stack.Q_4569_t()));
                crashreportcategory.n_1700_B("Item Foil", () -> String.valueOf(stack.Y_259_p()));
                throw new ReportedException(crashreport);
            }
            this.J_1907_R -= 50.0f;
        }
    }

    public void n_1700_B(Y_4083_F fr, Z_1993_T stack, int xPosition, int yPosition) {
        this.n_1700_B(fr, stack, xPosition, yPosition, null);
    }

    public void n_1700_B(Y_4083_F fr, Z_1993_T stack, int xPosition, int yPosition, @Nullable String text) {
        if (!stack.n_1700_B()) {
            V_772_m clientplayerentity;
            float f3;
            g_221_o matrixstack = new g_221_o();
            if (stack.t_4043_B() != 1 || text != null) {
                String s = text == null ? String.valueOf(stack.t_4043_B()) : text;
                matrixstack.n_1700_B(0.0, 0.0, (double)(this.J_1907_R + 200.0f));
                o_3091_w.n_1700_B irendertypebuffer$impl = o_3091_w.n_1700_B(l_3747_P.n_1700_B().R_4764_Y());
                fr.n_1700_B(s, (float)(xPosition + 19 - 2 - fr.J_1907_R(s)), (float)(yPosition + 6 + 3), 0xFFFFFF, true, matrixstack.R_4764_Y().n_1700_B(), (o_3091_w)irendertypebuffer$impl, false, 0, 0xF000F0);
                irendertypebuffer$impl.J_1907_R();
            }
            if (ReflectorForge.isItemDamaged(stack)) {
                c_4037_x.t_1786_h();
                c_4037_x.e_4240_b();
                c_4037_x.u_2550_I();
                c_4037_x.Y_259_p();
                l_3747_P tessellator = l_3747_P.n_1700_B();
                D_3318_r bufferbuilder = tessellator.R_4764_Y();
                float f = stack.v_4262_N();
                float f1 = stack.w_1484_f();
                float f2 = Math.max(0.0f, (f1 - f) / f1);
                int i = Math.round(13.0f - f * 13.0f / f1);
                int j = u_530_F.u_1723_Y(f2 / 3.0f, 1.0f, 1.0f);
                if (Reflector.IForgeItem_getDurabilityForDisplay.exists() && Reflector.IForgeItem_getRGBDurabilityForDisplay.exists()) {
                    double d0 = Reflector.callDouble(stack.J_1907_R(), Reflector.IForgeItem_getDurabilityForDisplay, stack);
                    int k = Reflector.callInt(stack.J_1907_R(), Reflector.IForgeItem_getRGBDurabilityForDisplay, stack);
                    i = Math.round(13.0f - (float)d0 * 13.0f);
                    j = k;
                }
                if (Config.isCustomColors()) {
                    j = CustomColors.getDurabilityColor(f2, j);
                }
                this.n_1700_B(bufferbuilder, xPosition + 2, yPosition + 13, 13, 2, 0, 0, 0, 255);
                this.n_1700_B(bufferbuilder, xPosition + 2, yPosition + 13, i, 1, j >> 16 & 0xFF, j >> 8 & 0xFF, j & 0xFF, 255);
                c_4037_x.Y_601_j();
                c_4037_x.M_588_G();
                c_4037_x.x_607_J();
                c_4037_x.multiplayerClientSuggestionProvider();
            }
            float f = f3 = (clientplayerentity = MinecraftClient.A_4115_X().Y_259_p) == null ? 0.0f : clientplayerentity.p_1458_L().n_1700_B(stack.J_1907_R(), MinecraftClient.A_4115_X().RealmsClientConfig());
            if (f3 > 0.0f) {
                c_4037_x.t_1786_h();
                c_4037_x.e_4240_b();
                c_4037_x.Y_601_j();
                c_4037_x.s_2632_s();
                l_3747_P tessellator1 = l_3747_P.n_1700_B();
                D_3318_r bufferbuilder1 = tessellator1.R_4764_Y();
                this.n_1700_B(bufferbuilder1, xPosition, yPosition + u_530_F.G_564_y(16.0f * (1.0f - f3)), 16, u_530_F.u_1723_Y(16.0f * f3), 255, 255, 255, 127);
                c_4037_x.x_607_J();
                c_4037_x.multiplayerClientSuggestionProvider();
            }
        }
    }

    private void n_1700_B(D_3318_r renderer, int x, int y, int width, int height, int red, int green, int blue, int alpha) {
        renderer.n_1700_B(7, E_688_b.Y_601_j);
        renderer.pos(x + 0, y + 0, 0.0).color(red, green, blue, alpha).endVertex();
        renderer.pos(x + 0, y + height, 0.0).color(red, green, blue, alpha).endVertex();
        renderer.pos(x + width, y + height, 0.0).color(red, green, blue, alpha).endVertex();
        renderer.pos(x + width, y + 0, 0.0).color(red, green, blue, alpha).endVertex();
        l_3747_P.n_1700_B().J_1907_R();
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        this.P_1922_E.J_1907_R();
    }

    public IResourceType R_4764_Y() {
        return VanillaResourceType.MODELS;
    }
}




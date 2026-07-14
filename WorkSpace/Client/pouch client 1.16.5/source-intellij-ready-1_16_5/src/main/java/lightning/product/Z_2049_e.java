/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.A_4115_X;
import lightning.product.D_1098_v;
import lightning.product.D_686_b;
import lightning.product.E_4612_l;
import lightning.product.E_4918_z;
import lightning.product.FormattedText;
import lightning.product.I_4817_s;
import lightning.product.K_4719_o;
import lightning.product.N_4263_v;
import lightning.product.U_2871_b;
import lightning.product.Y_4083_F;
import lightning.product.a_3913_L;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.e_1689_x;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_3270_j;
import lightning.product.NameProtect;
import lightning.product.ClientBootstrap;
import lightning.product.o_3091_w;
import lightning.product.Tags;
import lightning.product.BlockEntityType;
import lightning.product.t_5_h;
import lightning.product.w_2040_b;
import lightning.product.x_282_a;
import lombok.Generated;
import mods.voicechat.eventforge.RenderNameplateEvent;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.reflect.Reflector;
import net.optifine.util.Either;

public abstract class Z_2049_e<T extends N_4263_v>
implements IEntityRenderer {
    protected final w_2040_b J_1907_R;
    public float R_4764_Y;
    protected float G_564_y = 1.0f;
    private t_5_h n_1700_B = null;
    private g_2336_b v_4262_N = null;
    protected boolean P_1922_E = true;
    protected boolean u_1723_Y = true;

    protected Z_2049_e(w_2040_b renderManager) {
        this.J_1907_R = renderManager;
    }

    public final int J_1907_R(T entityIn, float partialTicks) {
        c_1514_x blockpos = new c_1514_x(((N_4263_v)entityIn).M_588_G(partialTicks));
        return e_1689_x.n_1700_B(this.n_1700_B(entityIn, blockpos), this.J_1907_R(entityIn, blockpos));
    }

    protected int J_1907_R(T p_239381_1_, c_1514_x p_239381_2_) {
        return ((N_4263_v)p_239381_1_).O_508_d.getLightFor(K_4719_o.n_1700_B, p_239381_2_);
    }

    protected int n_1700_B(T entityIn, c_1514_x partialTicks) {
        return ((N_4263_v)entityIn).RealmsPersistence() ? 15 : ((N_4263_v)entityIn).O_508_d.getLightFor(K_4719_o.J_1907_R, partialTicks);
    }

    public boolean n_1700_B(T livingEntityIn, E_4918_z camera, double camX, double camY, double camZ) {
        if (!((N_4263_v)livingEntityIn).t_148_a(camX, camY, camZ)) {
            return false;
        }
        if (((N_4263_v)livingEntityIn).RowButton) {
            return true;
        }
        I_4817_s axisalignedbb = ((N_4263_v)livingEntityIn).h_2739_B().grow(0.5);
        if (axisalignedbb.hasNaN() || axisalignedbb.getAverageEdgeLength() == 0.0) {
            axisalignedbb = new I_4817_s(((N_4263_v)livingEntityIn).O_3598_v() - 2.0, ((N_4263_v)livingEntityIn).X_2960_b() - 2.0, ((N_4263_v)livingEntityIn).l_2647_k() - 2.0, ((N_4263_v)livingEntityIn).O_3598_v() + 2.0, ((N_4263_v)livingEntityIn).X_2960_b() + 2.0, ((N_4263_v)livingEntityIn).l_2647_k() + 2.0);
        }
        return camera.isBoundingBoxInFrustum(axisalignedbb);
    }

    public e_2866_D n_1700_B(T entityIn, float partialTicks) {
        return e_2866_D.n_1700_B;
    }

    public void n_1700_B(T entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        RenderNameplateEvent renderNameplateEvent = new RenderNameplateEvent((N_4263_v)entityIn, ((N_4263_v)entityIn).c_(), this, matrixStackIn, bufferIn, packedLightIn, partialTicks);
        A_4115_X.n_1700_B(renderNameplateEvent);
        if (this.J_1907_R(entityIn) && this.u_1723_Y) {
            if (entityIn instanceof D_686_b) {
                h_3270_j hologramEvent = new h_3270_j(h_3270_j.n_1700_B.h_1847_R);
                A_4115_X.n_1700_B(hologramEvent);
                if (!hologramEvent.n_1700_B()) {
                    this.n_1700_B(entityIn, ((N_4263_v)entityIn).c_(), matrixStackIn, bufferIn, packedLightIn);
                }
            } else {
                this.n_1700_B(entityIn, ((N_4263_v)entityIn).c_(), matrixStackIn, bufferIn, packedLightIn);
            }
        }
    }

    protected boolean J_1907_R(T entity) {
        return ((N_4263_v)entity).I_1407_m() && ((N_4263_v)entity).t_3452_g();
    }

    public abstract g_2336_b n_1700_B(T var1);

    public Y_4083_F p_() {
        return this.J_1907_R.G_564_y();
    }

    protected void n_1700_B(T entityIn, x_282_a displayNameIn, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        double d0;
        boolean flag;
        Tags tagsModule = (Tags)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Tags.class);
        if (tagsModule.w_1484_f() && E_4612_l.G_564_y(entityIn, tagsModule.v_4262_N)) {
            return;
        }
        String nameText = displayNameIn.getString();
        String profileName = null;
        boolean friendNametag = false;
        if (entityIn instanceof a_3913_L) {
            a_3913_L p = (a_3913_L)entityIn;
            profileName = p.y_4642_Y().getName();
            friendNametag = ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(profileName);
        }
        String cleanedName = NameProtect.n_1700_B(nameText, friendNametag);
        if (!nameText.equals(cleanedName = NameProtect.n_1700_B(cleanedName, profileName))) {
            displayNameIn = new U_2871_b(cleanedName).n_1700_B(displayNameIn.n_1700_B());
        }
        boolean bl = flag = !((d0 = this.J_1907_R.J_1907_R((N_4263_v)entityIn)) > 4096.0);
        if (Reflector.ForgeHooksClient_isNameplateInRenderDistance.exists()) {
            flag = Reflector.ForgeHooksClient_isNameplateInRenderDistance.callBoolean(entityIn, d0);
        }
        if (flag) {
            boolean flag1 = !((N_4263_v)entityIn).U_1341_G();
            float f = ((N_4263_v)entityIn).v_165_F() + 0.5f;
            int i = "deadmau5".equals(displayNameIn.getString()) ? -10 : 0;
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.0, (double)f, 0.0);
            matrixStackIn.n_1700_B(this.J_1907_R.R_4764_Y());
            matrixStackIn.n_1700_B(-0.025f, -0.025f, 0.025f);
            D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
            float f1 = MinecraftClient.A_4115_X().P_4830_p.n_1700_B(0.25f);
            int j = (int)(f1 * 255.0f) << 24;
            Y_4083_F fontrenderer = this.p_();
            float f2 = -fontrenderer.n_1700_B((FormattedText)displayNameIn) / 2;
            fontrenderer.n_1700_B(displayNameIn, f2, (float)i, 0x20FFFFFF, false, matrix4f, bufferIn, flag1, j, packedLightIn);
            if (flag1) {
                fontrenderer.n_1700_B(displayNameIn, f2, (float)i, -1, false, matrix4f, bufferIn, false, 0, packedLightIn);
            }
            matrixStackIn.J_1907_R();
        }
    }

    public w_2040_b J_1907_R() {
        return this.J_1907_R;
    }

    @Override
    public Either<t_5_h, BlockEntityType> getType() {
        return this.n_1700_B == null ? null : Either.makeLeft(this.n_1700_B);
    }

    @Override
    public void setType(Either<t_5_h, BlockEntityType> p_setType_1_) {
        this.n_1700_B = p_setType_1_.getLeft().get();
    }

    @Override
    public g_2336_b getLocationTextureCustom() {
        return this.v_4262_N;
    }

    @Override
    public void setLocationTextureCustom(g_2336_b p_setLocationTextureCustom_1_) {
        this.v_4262_N = p_setLocationTextureCustom_1_;
    }

    @Generated
    public boolean R_4764_Y() {
        return this.P_1922_E;
    }

    @Generated
    public void n_1700_B(boolean renderLayers) {
        this.P_1922_E = renderLayers;
    }

    @Generated
    public boolean G_564_y() {
        return this.u_1723_Y;
    }

    @Generated
    public void J_1907_R(boolean renderName) {
        this.u_1723_Y = renderName;
    }
}




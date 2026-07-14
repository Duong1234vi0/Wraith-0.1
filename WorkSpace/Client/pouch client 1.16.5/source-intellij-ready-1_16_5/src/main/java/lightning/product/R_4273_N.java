/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_4115_X;
import lightning.product.FluidTags;
import lightning.product.B_3871_I;
import lightning.product.D_4792_h;
import lightning.product.LeavesBlock;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.HalfTransparentBlock;
import lightning.product.BlockAndTintGetter;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_2866_D;
import lightning.product.g_2561_p;
import lightning.product.h_3270_j;
import lightning.product.n_1769_f;
import lightning.product.s_1395_c;
import lightning.product.Fluid;
import lightning.product.u_530_F;
import lightning.product.x_268_Y;
import lightning.product.y_3008_A;
import lightning.product.z_883_p;
import net.optifine.Config;
import net.optifine.CustomColors;
import net.optifine.reflect.Reflector;
import net.optifine.render.RenderEnv;
import net.optifine.shaders.SVertexBuilder;
import net.optifine.shaders.Shaders;

public class R_4273_N {
    private final B_3871_I[] n_1700_B = new B_3871_I[2];
    private final B_3871_I[] J_1907_R = new B_3871_I[2];
    private B_3871_I R_4764_Y;

    protected void n_1700_B() {
        this.n_1700_B[0] = MinecraftClient.A_4115_X().h_4320_q().R_4764_Y().J_1907_R(a_3742_W.H_2857_Y.multiplayerClientSuggestionProvider()).P_1922_E();
        this.n_1700_B[1] = g_2561_p.R_4764_Y.R_4764_Y();
        this.J_1907_R[0] = MinecraftClient.A_4115_X().h_4320_q().R_4764_Y().J_1907_R(a_3742_W.c_3005_b.multiplayerClientSuggestionProvider()).P_1922_E();
        this.J_1907_R[1] = g_2561_p.G_564_y.R_4764_Y();
        this.R_4764_Y = g_2561_p.P_1922_E.R_4764_Y();
    }

    private static boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, b_257_Y side, FluidState state) {
        c_1514_x blockpos = pos.offset(side);
        FluidState fluidstate = worldIn.getFluidState(blockpos);
        return fluidstate.n_1700_B().n_1700_B(state.n_1700_B());
    }

    private static boolean n_1700_B(BlockGetter p_239284_0_, b_257_Y p_239284_1_, float p_239284_2_, c_1514_x p_239284_3_, K_4074_S p_239284_4_) {
        if (p_239284_4_.M_588_G()) {
            s_1395_c voxelshape = x_268_Y.n_1700_B(0.0, 0.0, 0.0, 1.0, p_239284_2_, 1.0);
            s_1395_c voxelshape1 = p_239284_4_.R_4764_Y(p_239284_0_, p_239284_3_);
            return x_268_Y.n_1700_B(voxelshape, voxelshape1, p_239284_1_);
        }
        return false;
    }

    private static boolean n_1700_B(BlockGetter p_239283_0_, c_1514_x p_239283_1_, b_257_Y p_239283_2_, float p_239283_3_) {
        c_1514_x blockpos = p_239283_1_.offset(p_239283_2_);
        K_4074_S blockstate = p_239283_0_.getBlockState(blockpos);
        return R_4273_N.n_1700_B(p_239283_0_, p_239283_2_, p_239283_3_, blockpos, blockstate);
    }

    private static boolean n_1700_B(BlockGetter p_239282_0_, c_1514_x p_239282_1_, K_4074_S p_239282_2_, b_257_Y p_239282_3_) {
        return R_4273_N.n_1700_B(p_239282_0_, p_239282_3_.u_1723_Y(), 1.0f, p_239282_1_, p_239282_2_);
    }

    public static boolean n_1700_B(BlockAndTintGetter p_239281_0_, c_1514_x p_239281_1_, FluidState p_239281_2_, K_4074_S p_239281_3_, b_257_Y p_239281_4_) {
        return !R_4273_N.n_1700_B((BlockGetter)p_239281_0_, p_239281_1_, p_239281_3_, p_239281_4_) && !R_4273_N.n_1700_B((BlockGetter)p_239281_0_, p_239281_1_, p_239281_4_, p_239281_2_);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean n_1700_B(BlockAndTintGetter lightReaderIn, c_1514_x posIn, D_4792_h vertexBuilderIn, FluidState fluidStateIn) {
        boolean flag7;
        K_4074_S blockstate = fluidStateIn.v_4262_N();
        try {
            Object object;
            B_3871_I[] atextureatlassprite1;
            boolean flag;
            if (fluidStateIn.n_1700_B(FluidTags.R_4764_Y)) {
                h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.Y_259_p);
                A_4115_X.n_1700_B(event);
                if (event.n_1700_B()) {
                    boolean bl = false;
                    return bl;
                }
            }
            if (Config.isShaders()) {
                SVertexBuilder.pushEntity(blockstate, vertexBuilderIn);
            }
            B_3871_I[] atextureatlassprite = (flag = fluidStateIn.n_1700_B(FluidTags.R_4764_Y)) ? this.n_1700_B : this.J_1907_R;
            K_4074_S blockstate1 = lightReaderIn.getBlockState(posIn);
            if (Reflector.ForgeHooksClient_getFluidSprites.exists() && (atextureatlassprite1 = (B_3871_I[])Reflector.call(Reflector.ForgeHooksClient_getFluidSprites, lightReaderIn, posIn, fluidStateIn)) != null) {
                atextureatlassprite = atextureatlassprite1;
            }
            RenderEnv renderenv = vertexBuilderIn.n_1700_B(blockstate, posIn);
            int i = -1;
            float f = 1.0f;
            if (Reflector.IForgeFluid_getAttributes.exists() && (object = Reflector.call(fluidStateIn.n_1700_B(), Reflector.IForgeFluid_getAttributes, new Object[0])) != null && Reflector.FluidAttributes_getColor.exists()) {
                i = Reflector.callInt(object, Reflector.FluidAttributes_getColor, lightReaderIn, posIn);
                f = (float)(i >> 24 & 0xFF) / 255.0f;
            }
            boolean flag9 = !R_4273_N.n_1700_B((BlockGetter)lightReaderIn, posIn, b_257_Y.J_1907_R, fluidStateIn);
            boolean flag1 = R_4273_N.n_1700_B(lightReaderIn, posIn, fluidStateIn, blockstate1, b_257_Y.n_1700_B) && !R_4273_N.n_1700_B((BlockGetter)lightReaderIn, posIn, b_257_Y.n_1700_B, 0.8888889f);
            boolean flag2 = R_4273_N.n_1700_B(lightReaderIn, posIn, fluidStateIn, blockstate1, b_257_Y.R_4764_Y);
            boolean flag3 = R_4273_N.n_1700_B(lightReaderIn, posIn, fluidStateIn, blockstate1, b_257_Y.G_564_y);
            boolean flag4 = R_4273_N.n_1700_B(lightReaderIn, posIn, fluidStateIn, blockstate1, b_257_Y.P_1922_E);
            boolean flag5 = R_4273_N.n_1700_B(lightReaderIn, posIn, fluidStateIn, blockstate1, b_257_Y.u_1723_Y);
            if (flag9 || flag1 || flag5 || flag4 || flag2 || flag3) {
                float f30;
                if (i < 0) {
                    i = CustomColors.getFluidColor(lightReaderIn, blockstate, posIn, renderenv);
                }
                float f28 = (float)(i >> 16 & 0xFF) / 255.0f;
                float f1 = (float)(i >> 8 & 0xFF) / 255.0f;
                float f2 = (float)(i & 0xFF) / 255.0f;
                boolean flag72 = false;
                float f3 = lightReaderIn.func_230487_a_(b_257_Y.n_1700_B, true);
                float f4 = lightReaderIn.func_230487_a_(b_257_Y.J_1907_R, true);
                float f5 = lightReaderIn.func_230487_a_(b_257_Y.R_4764_Y, true);
                float f6 = lightReaderIn.func_230487_a_(b_257_Y.P_1922_E, true);
                float f7 = this.n_1700_B(lightReaderIn, posIn, fluidStateIn.n_1700_B());
                float f8 = this.n_1700_B(lightReaderIn, posIn.south(), fluidStateIn.n_1700_B());
                float f9 = this.n_1700_B(lightReaderIn, posIn.east().south(), fluidStateIn.n_1700_B());
                float f10 = this.n_1700_B(lightReaderIn, posIn.east(), fluidStateIn.n_1700_B());
                double d0 = posIn.getX() & 0xF;
                double d1 = posIn.getY() & 0xF;
                double d2 = posIn.getZ() & 0xF;
                if (Config.isRenderRegions()) {
                    int j = posIn.getX() >> 4 << 4;
                    int k = posIn.getY() >> 4 << 4;
                    int l = posIn.getZ() >> 4 << 4;
                    int i1 = 8;
                    int j1 = j >> i1 << i1;
                    int k1 = l >> i1 << i1;
                    int l1 = j - j1;
                    int i2 = l - k1;
                    d0 += (double)l1;
                    d1 += (double)k;
                    d2 += (double)i2;
                }
                if (Config.isShaders() && Shaders.useMidBlockAttrib) {
                    vertexBuilderIn.setMidBlock((float)(d0 + 0.5), (float)(d1 + 0.5), (float)(d2 + 0.5));
                }
                float f29 = 0.001f;
                float f11 = f30 = flag1 ? 0.001f : 0.0f;
                if (flag9 && !R_4273_N.n_1700_B((BlockGetter)lightReaderIn, posIn, b_257_Y.J_1907_R, Math.min(Math.min(f7, f8), Math.min(f9, f10)))) {
                    float f13;
                    float f40;
                    float f12;
                    float f38;
                    float f42;
                    float f35;
                    float f112;
                    float f32;
                    flag72 = true;
                    f7 -= 0.001f;
                    f8 -= 0.001f;
                    f9 -= 0.001f;
                    f10 -= 0.001f;
                    e_2866_D vector3d = fluidStateIn.R_4764_Y(lightReaderIn, posIn);
                    if (vector3d.J_1907_R == 0.0 && vector3d.G_564_y == 0.0) {
                        B_3871_I textureatlassprite1 = atextureatlassprite[0];
                        vertexBuilderIn.setSprite(textureatlassprite1);
                        f32 = textureatlassprite1.n_1700_B(0.0);
                        f112 = textureatlassprite1.J_1907_R(0.0);
                        f35 = f32;
                        f42 = textureatlassprite1.J_1907_R(16.0);
                        f38 = textureatlassprite1.n_1700_B(16.0);
                        f12 = f42;
                        f40 = f38;
                        f13 = f112;
                    } else {
                        B_3871_I textureatlassprite = atextureatlassprite[1];
                        vertexBuilderIn.setSprite(textureatlassprite);
                        float f14 = (float)u_530_F.G_564_y(vector3d.G_564_y, vector3d.J_1907_R) - 1.5707964f;
                        float f15 = u_530_F.n_1700_B(f14) * 0.25f;
                        float f16 = u_530_F.J_1907_R(f14) * 0.25f;
                        float f17 = 8.0f;
                        f32 = textureatlassprite.n_1700_B((double)(8.0f + (-f16 - f15) * 16.0f));
                        f112 = textureatlassprite.J_1907_R((double)(8.0f + (-f16 + f15) * 16.0f));
                        f35 = textureatlassprite.n_1700_B((double)(8.0f + (-f16 + f15) * 16.0f));
                        f42 = textureatlassprite.J_1907_R((double)(8.0f + (f16 + f15) * 16.0f));
                        f38 = textureatlassprite.n_1700_B((double)(8.0f + (f16 + f15) * 16.0f));
                        f12 = textureatlassprite.J_1907_R((double)(8.0f + (f16 - f15) * 16.0f));
                        f40 = textureatlassprite.n_1700_B((double)(8.0f + (f16 - f15) * 16.0f));
                        f13 = textureatlassprite.J_1907_R((double)(8.0f + (-f16 - f15) * 16.0f));
                    }
                    float f46 = (f32 + f35 + f38 + f40) / 4.0f;
                    float f47 = (f112 + f42 + f12 + f13) / 4.0f;
                    float f48 = (float)atextureatlassprite[0].G_564_y() / (atextureatlassprite[0].v_4262_N() - atextureatlassprite[0].u_1723_Y());
                    float f49 = (float)atextureatlassprite[0].P_1922_E() / (atextureatlassprite[0].t_148_a() - atextureatlassprite[0].w_1484_f());
                    float f50 = 4.0f / Math.max(f49, f48);
                    f32 = u_530_F.v_4262_N(f50, f32, f46);
                    f35 = u_530_F.v_4262_N(f50, f35, f46);
                    f38 = u_530_F.v_4262_N(f50, f38, f46);
                    f40 = u_530_F.v_4262_N(f50, f40, f46);
                    f112 = u_530_F.v_4262_N(f50, f112, f47);
                    f42 = u_530_F.v_4262_N(f50, f42, f47);
                    f12 = u_530_F.v_4262_N(f50, f12, f47);
                    f13 = u_530_F.v_4262_N(f50, f13, f47);
                    int j2 = this.n_1700_B(lightReaderIn, posIn);
                    float f18 = f4 * f28;
                    float f19 = f4 * f1;
                    float f20 = f4 * f2;
                    this.n_1700_B(vertexBuilderIn, d0 + 0.0, d1 + (double)f7, d2 + 0.0, f18, f19, f20, f, f32, f112, j2);
                    this.n_1700_B(vertexBuilderIn, d0 + 0.0, d1 + (double)f8, d2 + 1.0, f18, f19, f20, f, f35, f42, j2);
                    this.n_1700_B(vertexBuilderIn, d0 + 1.0, d1 + (double)f9, d2 + 1.0, f18, f19, f20, f, f38, f12, j2);
                    this.n_1700_B(vertexBuilderIn, d0 + 1.0, d1 + (double)f10, d2 + 0.0, f18, f19, f20, f, f40, f13, j2);
                    if (fluidStateIn.J_1907_R(lightReaderIn, posIn.up())) {
                        this.n_1700_B(vertexBuilderIn, d0 + 0.0, d1 + (double)f7, d2 + 0.0, f18, f19, f20, f, f32, f112, j2);
                        this.n_1700_B(vertexBuilderIn, d0 + 1.0, d1 + (double)f10, d2 + 0.0, f18, f19, f20, f, f40, f13, j2);
                        this.n_1700_B(vertexBuilderIn, d0 + 1.0, d1 + (double)f9, d2 + 1.0, f18, f19, f20, f, f38, f12, j2);
                        this.n_1700_B(vertexBuilderIn, d0 + 0.0, d1 + (double)f8, d2 + 1.0, f18, f19, f20, f, f35, f42, j2);
                    }
                }
                if (flag1) {
                    vertexBuilderIn.setSprite(atextureatlassprite[0]);
                    float f31 = atextureatlassprite[0].u_1723_Y();
                    float f33 = atextureatlassprite[0].v_4262_N();
                    float f36 = atextureatlassprite[0].w_1484_f();
                    float f39 = atextureatlassprite[0].t_148_a();
                    int i3 = this.n_1700_B(lightReaderIn, posIn.down());
                    float f41 = lightReaderIn.func_230487_a_(b_257_Y.n_1700_B, true);
                    float f43 = f41 * f28;
                    float f44 = f41 * f1;
                    float f45 = f41 * f2;
                    this.n_1700_B(vertexBuilderIn, d0, d1 + (double)f30, d2 + 1.0, f43, f44, f45, f, f31, f39, i3);
                    this.n_1700_B(vertexBuilderIn, d0, d1 + (double)f30, d2, f43, f44, f45, f, f31, f36, i3);
                    this.n_1700_B(vertexBuilderIn, d0 + 1.0, d1 + (double)f30, d2, f43, f44, f45, f, f33, f36, i3);
                    this.n_1700_B(vertexBuilderIn, d0 + 1.0, d1 + (double)f30, d2 + 1.0, f43, f44, f45, f, f33, f39, i3);
                    flag72 = true;
                }
                for (int l2 = 0; l2 < 4; ++l2) {
                    boolean flag11;
                    boolean flag10;
                    b_257_Y direction;
                    double d6;
                    double d4;
                    double d5;
                    double d3;
                    float f37;
                    float f34;
                    if (l2 == 0) {
                        f34 = f7;
                        f37 = f10;
                        d3 = d0;
                        d5 = d0 + 1.0;
                        d4 = d2 + (double)0.001f;
                        d6 = d2 + (double)0.001f;
                        direction = b_257_Y.R_4764_Y;
                        flag10 = flag2;
                    } else if (l2 == 1) {
                        f34 = f9;
                        f37 = f8;
                        d3 = d0 + 1.0;
                        d5 = d0;
                        d4 = d2 + 1.0 - (double)0.001f;
                        d6 = d2 + 1.0 - (double)0.001f;
                        direction = b_257_Y.G_564_y;
                        flag10 = flag3;
                    } else if (l2 == 2) {
                        f34 = f8;
                        f37 = f7;
                        d3 = d0 + (double)0.001f;
                        d5 = d0 + (double)0.001f;
                        d4 = d2 + 1.0;
                        d6 = d2;
                        direction = b_257_Y.P_1922_E;
                        flag10 = flag4;
                    } else {
                        f34 = f10;
                        f37 = f9;
                        d3 = d0 + 1.0 - (double)0.001f;
                        d5 = d0 + 1.0 - (double)0.001f;
                        d4 = d2;
                        d6 = d2 + 1.0;
                        direction = b_257_Y.u_1723_Y;
                        flag10 = flag5;
                    }
                    if (!flag10 || R_4273_N.n_1700_B((BlockGetter)lightReaderIn, posIn, direction, Math.max(f34, f37))) continue;
                    flag72 = true;
                    c_1514_x blockpos = posIn.offset(direction);
                    B_3871_I textureatlassprite2 = atextureatlassprite[1];
                    float f51 = 0.0f;
                    float f52 = 0.0f;
                    boolean bl = flag11 = !flag;
                    if (Reflector.IForgeBlockState_shouldDisplayFluidOverlay.exists()) {
                        boolean bl2 = flag11 = atextureatlassprite[2] != null;
                    }
                    if (flag11) {
                        K_4074_S blockstate2 = lightReaderIn.getBlockState(blockpos);
                        T_2915_h block = blockstate2.J_1907_R();
                        boolean flag8 = false;
                        if (Reflector.IForgeBlockState_shouldDisplayFluidOverlay.exists()) {
                            flag8 = Reflector.callBoolean(blockstate2, Reflector.IForgeBlockState_shouldDisplayFluidOverlay, lightReaderIn, blockpos, fluidStateIn);
                        }
                        if (flag8 || block instanceof HalfTransparentBlock || block instanceof LeavesBlock || block == a_3742_W.k_578_l) {
                            textureatlassprite2 = this.R_4764_Y;
                        }
                        if (block == a_3742_W.Z_735_d || block == a_3742_W.InvManager) {
                            f51 = 0.9375f;
                            f52 = 0.9375f;
                        }
                        if (block instanceof y_3008_A) {
                            y_3008_A slabblock = (y_3008_A)block;
                            if (blockstate2.R_4764_Y(y_3008_A.P_4830_p) == n_1769_f.J_1907_R) {
                                f51 = 0.5f;
                                f52 = 0.5f;
                            }
                        }
                    }
                    vertexBuilderIn.setSprite(textureatlassprite2);
                    if (f34 <= f51 && f37 <= f52) continue;
                    f51 = Math.min(f51, f34);
                    f52 = Math.min(f52, f37);
                    if (f51 > f29) {
                        f51 -= f29;
                    }
                    if (f52 > f29) {
                        f52 -= f29;
                    }
                    float f53 = textureatlassprite2.J_1907_R((double)((1.0f - f51) * 16.0f * 0.5f));
                    float f54 = textureatlassprite2.J_1907_R((double)((1.0f - f52) * 16.0f * 0.5f));
                    float f55 = textureatlassprite2.n_1700_B(0.0);
                    float f56 = textureatlassprite2.n_1700_B(8.0);
                    float f21 = textureatlassprite2.J_1907_R((double)((1.0f - f34) * 16.0f * 0.5f));
                    float f22 = textureatlassprite2.J_1907_R((double)((1.0f - f37) * 16.0f * 0.5f));
                    float f23 = textureatlassprite2.J_1907_R(8.0);
                    int k2 = this.n_1700_B(lightReaderIn, blockpos);
                    float f24 = l2 < 2 ? lightReaderIn.func_230487_a_(b_257_Y.R_4764_Y, true) : lightReaderIn.func_230487_a_(b_257_Y.P_1922_E, true);
                    float f25 = 1.0f * f24 * f28;
                    float f26 = 1.0f * f24 * f1;
                    float f27 = 1.0f * f24 * f2;
                    this.n_1700_B(vertexBuilderIn, d3, d1 + (double)f34, d4, f25, f26, f27, f, f55, f21, k2);
                    this.n_1700_B(vertexBuilderIn, d5, d1 + (double)f37, d6, f25, f26, f27, f, f56, f22, k2);
                    this.n_1700_B(vertexBuilderIn, d5, d1 + (double)f30, d6, f25, f26, f27, f, f56, f54, k2);
                    this.n_1700_B(vertexBuilderIn, d3, d1 + (double)f30, d4, f25, f26, f27, f, f55, f53, k2);
                    if (textureatlassprite2 == this.R_4764_Y) continue;
                    this.n_1700_B(vertexBuilderIn, d3, d1 + (double)f30, d4, f25, f26, f27, f, f55, f53, k2);
                    this.n_1700_B(vertexBuilderIn, d5, d1 + (double)f30, d6, f25, f26, f27, f, f56, f54, k2);
                    this.n_1700_B(vertexBuilderIn, d5, d1 + (double)f37, d6, f25, f26, f27, f, f56, f22, k2);
                    this.n_1700_B(vertexBuilderIn, d3, d1 + (double)f34, d4, f25, f26, f27, f, f55, f21, k2);
                }
                vertexBuilderIn.setSprite(null);
                boolean bl = flag72;
                return bl;
            }
            flag7 = false;
        }
        finally {
            if (Config.isShaders()) {
                SVertexBuilder.popEntity(vertexBuilderIn);
            }
        }
        return flag7;
    }

    private void n_1700_B(D_4792_h vertexBuilderIn, double x, double y, double z, float red, float green, float blue, float u, float v, int packedLight) {
        vertexBuilderIn.pos(x, y, z).n_1700_B(red, green, blue, 1.0f).tex(u, v).J_1907_R(packedLight).normal(0.0f, 1.0f, 0.0f).endVertex();
    }

    private void n_1700_B(D_4792_h p_vertexVanilla_1_, double p_vertexVanilla_2_, double p_vertexVanilla_4_, double p_vertexVanilla_6_, float p_vertexVanilla_8_, float p_vertexVanilla_9_, float p_vertexVanilla_10_, float p_vertexVanilla_11_, float p_vertexVanilla_12_, float p_vertexVanilla_13_, int p_vertexVanilla_14_) {
        p_vertexVanilla_1_.pos(p_vertexVanilla_2_, p_vertexVanilla_4_, p_vertexVanilla_6_).n_1700_B(p_vertexVanilla_8_, p_vertexVanilla_9_, p_vertexVanilla_10_, p_vertexVanilla_11_).tex(p_vertexVanilla_12_, p_vertexVanilla_13_).J_1907_R(p_vertexVanilla_14_).normal(0.0f, 1.0f, 0.0f).endVertex();
    }

    private int n_1700_B(BlockAndTintGetter lightReaderIn, c_1514_x posIn) {
        int i = z_883_p.n_1700_B(lightReaderIn, posIn);
        int j = z_883_p.n_1700_B(lightReaderIn, posIn.up());
        int k = i & 0xFF;
        int l = j & 0xFF;
        int i1 = i >> 16 & 0xFF;
        int j1 = j >> 16 & 0xFF;
        return (k > l ? k : l) | (i1 > j1 ? i1 : j1) << 16;
    }

    private float n_1700_B(BlockGetter reader, c_1514_x pos, Fluid fluidIn) {
        int i = 0;
        float f = 0.0f;
        for (int j = 0; j < 4; ++j) {
            c_1514_x blockpos = pos.add(-(j & 1), 0, -(j >> 1 & 1));
            if (reader.getFluidState(blockpos.up()).n_1700_B().n_1700_B(fluidIn)) {
                return 1.0f;
            }
            FluidState fluidstate = reader.getFluidState(blockpos);
            if (fluidstate.n_1700_B().n_1700_B(fluidIn)) {
                float f1 = fluidstate.n_1700_B(reader, blockpos);
                if (f1 >= 0.8f) {
                    f += f1 * 10.0f;
                    i += 10;
                    continue;
                }
                f += f1;
                ++i;
                continue;
            }
            if (reader.getBlockState(blockpos).R_4764_Y().J_1907_R()) continue;
            ++i;
        }
        return f / (float)i;
    }
}




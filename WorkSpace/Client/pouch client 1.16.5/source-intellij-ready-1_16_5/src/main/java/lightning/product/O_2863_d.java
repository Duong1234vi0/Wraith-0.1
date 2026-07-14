/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.D_4024_W;
import lightning.product.D_590_W;
import lightning.product.F_2904_S;
import lightning.product.BlockHitResult;
import lightning.product.HitResult;
import lightning.product.K_1289_S;
import lightning.product.K_3638_N;
import lightning.product.K_4074_S;
import lightning.product.M_2935_g;
import lightning.product.N_4263_v;
import lightning.product.O_1487_n;
import lightning.product.O_694_j;
import lightning.product.Q_4113_P;
import lightning.product.U_1085_u;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.SimpleOptionsSubScreen;
import lightning.product.V_3137_a;
import lightning.product.W_2853_p;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.f_71_T;
import lightning.product.g_2336_b;
import lightning.product.i_2154_H;
import lightning.product.i_4434_b;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.r_1166_b;
import lightning.product.ControlsScreen;
import lightning.product.u_530_F;
import lightning.product.EntityHitResult;
import lightning.product.x_282_a;
import lightning.product.x_3498_p;
import mods.voicechat.eventforge.InputEvent;
import net.optifine.Config;
import net.optifine.reflect.Reflector;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.gui.GuiShaderOptions;

public class O_2863_d {
    private final MinecraftClient n_1700_B;
    private boolean J_1907_R;
    private final K_3638_N R_4764_Y = new K_3638_N();
    private long G_564_y = -1L;
    private long P_1922_E = -1L;
    private long u_1723_Y = -1L;
    private boolean v_4262_N;
    private final Set<Integer> w_1484_f = new HashSet<Integer>();

    public O_2863_d(MinecraftClient mcIn) {
        this.n_1700_B = mcIn;
    }

    private void n_1700_B(String message, Object ... args) {
        this.n_1700_B.M_588_G.R_4764_Y().n_1700_B(new U_2871_b("").n_1700_B(new F_2904_S("debug.prefix").n_1700_B(D_4024_W.Q_4569_t, D_4024_W.multiplayerClientSuggestionProvider)).n_1700_B(" ").n_1700_B(new F_2904_S(message, args)));
    }

    private void J_1907_R(String message, Object ... args) {
        this.n_1700_B.M_588_G.R_4764_Y().n_1700_B(new U_2871_b("").n_1700_B(new F_2904_S("debug.prefix").n_1700_B(D_4024_W.P_4830_p, D_4024_W.multiplayerClientSuggestionProvider)).n_1700_B(" ").n_1700_B(new F_2904_S(message, args)));
    }

    private boolean n_1700_B(int key) {
        if (this.G_564_y > 0L && this.G_564_y < j_3341_s.J_1907_R() - 100L) {
            return true;
        }
        switch (key) {
            case 65: {
                this.n_1700_B.u_1723_Y.P_1922_E();
                this.n_1700_B("debug.reload_chunks.message", new Object[0]);
                return true;
            }
            case 66: {
                boolean flag = !this.n_1700_B.O_508_d().J_1907_R();
                this.n_1700_B.O_508_d().J_1907_R(flag);
                this.n_1700_B(flag ? "debug.show_hitboxes.on" : "debug.show_hitboxes.off", new Object[0]);
                return true;
            }
            case 67: {
                if (this.n_1700_B.Y_259_p.y_3417_N()) {
                    return false;
                }
                W_2853_p clientplaynethandler = this.n_1700_B.Y_259_p.n_1700_B;
                if (clientplaynethandler == null) {
                    return false;
                }
                this.n_1700_B("debug.copy_location.message", new Object[0]);
                this.n_1700_B(String.format(Locale.ROOT, "/execute in %s run tp @s %.2f %.2f %.2f %.2f %.2f", this.n_1700_B.Y_259_p.O_508_d.g_2268_R().n_1700_B(), this.n_1700_B.Y_259_p.O_3598_v(), this.n_1700_B.Y_259_p.X_2960_b(), this.n_1700_B.Y_259_p.l_2647_k(), Float.valueOf(this.n_1700_B.Y_259_p.p_178_J), Float.valueOf(this.n_1700_B.Y_259_p.f_4016_n)));
                return true;
            }
            case 68: {
                if (this.n_1700_B.M_588_G != null) {
                    this.n_1700_B.M_588_G.R_4764_Y().n_1700_B(false);
                }
                return true;
            }
            case 70: {
                M_2935_g.RENDER_DISTANCE.set(this.n_1700_B.P_4830_p, u_530_F.n_1700_B((double)(this.n_1700_B.P_4830_p.J_1907_R + (k_2603_m.hasShiftDown() ? -1 : 1)), M_2935_g.RENDER_DISTANCE.getMinValue(), M_2935_g.RENDER_DISTANCE.getMaxValue()));
                this.n_1700_B("debug.cycle_renderdistance.message", this.n_1700_B.P_4830_p.J_1907_R);
                return true;
            }
            case 71: {
                boolean flag1 = this.n_1700_B.u_2550_I.J_1907_R();
                this.n_1700_B(flag1 ? "debug.chunk_boundaries.on" : "debug.chunk_boundaries.off", new Object[0]);
                return true;
            }
            case 72: {
                this.n_1700_B.P_4830_p.M_182_A = !this.n_1700_B.P_4830_p.M_182_A;
                this.n_1700_B(this.n_1700_B.P_4830_p.M_182_A ? "debug.advanced_tooltips.on" : "debug.advanced_tooltips.off", new Object[0]);
                this.n_1700_B.P_4830_p.J_1907_R();
                return true;
            }
            case 73: {
                if (!this.n_1700_B.Y_259_p.y_3417_N()) {
                    this.n_1700_B(this.n_1700_B.Y_259_p.t_148_a(2), !k_2603_m.hasShiftDown());
                }
                return true;
            }
            case 76: {
                MinecraftClient minecraft = Config.getMinecraft();
                minecraft.u_1723_Y.w_1484_f = 1;
                U_2871_b stringtextcomponent = new U_2871_b(K_1289_S.n_1700_B("of.message.loadingVisibleChunks", new Object[0]));
                minecraft.M_588_G.R_4764_Y().n_1700_B(stringtextcomponent, 201435902);
                return true;
            }
            case 78: {
                if (!this.n_1700_B.Y_259_p.t_148_a(2)) {
                    this.n_1700_B("debug.creative_spectator.error", new Object[0]);
                } else if (!this.n_1700_B.Y_259_p.d_2461_k()) {
                    this.n_1700_B.Y_259_p.n_1700_B("/gamemode spectator");
                } else {
                    this.n_1700_B.Y_259_p.n_1700_B("/gamemode " + this.n_1700_B.w_1457_N.func_241822_k().J_1907_R());
                }
                return true;
            }
            case 79: {
                if (Config.isShaders()) {
                    GuiShaderOptions guishaderoptions = new GuiShaderOptions(null, Config.getGameSettings());
                    Config.getMinecraft().n_1700_B(guishaderoptions);
                }
                return true;
            }
            case 80: {
                this.n_1700_B.P_4830_p.t_1786_h = !this.n_1700_B.P_4830_p.t_1786_h;
                this.n_1700_B.P_4830_p.J_1907_R();
                this.n_1700_B(this.n_1700_B.P_4830_p.t_1786_h ? "debug.pause_focus.on" : "debug.pause_focus.off", new Object[0]);
                return true;
            }
            case 81: {
                this.n_1700_B("debug.help.message", new Object[0]);
                U_1085_u newchatgui = this.n_1700_B.M_588_G.R_4764_Y();
                newchatgui.n_1700_B(new F_2904_S("debug.reload_chunks.help"));
                newchatgui.n_1700_B(new F_2904_S("debug.show_hitboxes.help"));
                newchatgui.n_1700_B(new F_2904_S("debug.copy_location.help"));
                newchatgui.n_1700_B(new F_2904_S("debug.clear_chat.help"));
                newchatgui.n_1700_B(new F_2904_S("debug.cycle_renderdistance.help"));
                newchatgui.n_1700_B(new F_2904_S("debug.chunk_boundaries.help"));
                newchatgui.n_1700_B(new F_2904_S("debug.advanced_tooltips.help"));
                newchatgui.n_1700_B(new F_2904_S("debug.inspect.help"));
                newchatgui.n_1700_B(new F_2904_S("debug.creative_spectator.help"));
                newchatgui.n_1700_B(new F_2904_S("debug.pause_focus.help"));
                newchatgui.n_1700_B(new F_2904_S("debug.help.help"));
                newchatgui.n_1700_B(new F_2904_S("debug.reload_resourcepacks.help"));
                newchatgui.n_1700_B(new F_2904_S("debug.pause.help"));
                newchatgui.n_1700_B(new F_2904_S("debug.gamemodes.help"));
                return true;
            }
            case 82: {
                if (Config.isShaders()) {
                    Shaders.uninit();
                    Shaders.loadShaderPack();
                }
                return true;
            }
            case 84: {
                this.n_1700_B("debug.reload_resourcepacks.message", new Object[0]);
                this.n_1700_B.w_1484_f();
                return true;
            }
            case 293: {
                this.n_1700_B.n_1700_B(new O_1487_n());
                return true;
            }
        }
        return false;
    }

    private void n_1700_B(boolean privileged, boolean askServer) {
        HitResult raytraceresult = this.n_1700_B.Z_875_P;
        if (raytraceresult != null) {
            switch (raytraceresult.R_4764_Y()) {
                case J_1907_R: {
                    c_1514_x blockpos = ((BlockHitResult)raytraceresult).n_1700_B();
                    K_4074_S blockstate = this.n_1700_B.Y_259_p.O_508_d.getBlockState(blockpos);
                    if (privileged) {
                        if (askServer) {
                            this.n_1700_B.Y_259_p.n_1700_B.M_588_G().n_1700_B(blockpos, (U_2912_j p_lambda$copyHoveredObject$0_3_) -> {
                                this.n_1700_B(blockstate, blockpos, (U_2912_j)p_lambda$copyHoveredObject$0_3_);
                                this.n_1700_B("debug.inspect.server.block", new Object[0]);
                            });
                            break;
                        }
                        i_2154_H tileentity = this.n_1700_B.Y_259_p.O_508_d.getTileEntity(blockpos);
                        U_2912_j compoundnbt1 = tileentity != null ? tileentity.n_1700_B(new U_2912_j()) : null;
                        this.n_1700_B(blockstate, blockpos, compoundnbt1);
                        this.n_1700_B("debug.inspect.client.block", new Object[0]);
                        break;
                    }
                    this.n_1700_B(blockstate, blockpos, (U_2912_j)null);
                    this.n_1700_B("debug.inspect.client.block", new Object[0]);
                    break;
                }
                case R_4764_Y: {
                    N_4263_v entity = ((EntityHitResult)raytraceresult).n_1700_B();
                    g_2336_b resourcelocation = V_3137_a.g_221_o.J_1907_R(entity.f_4016_n());
                    if (privileged) {
                        if (askServer) {
                            this.n_1700_B.Y_259_p.n_1700_B.M_588_G().n_1700_B(entity.j_276_v(), (U_2912_j p_lambda$copyHoveredObject$1_3_) -> {
                                this.n_1700_B(resourcelocation, entity.s_4990_V(), (U_2912_j)p_lambda$copyHoveredObject$1_3_);
                                this.n_1700_B("debug.inspect.server.entity", new Object[0]);
                            });
                            break;
                        }
                        U_2912_j compoundnbt = entity.P_1922_E(new U_2912_j());
                        this.n_1700_B(resourcelocation, entity.s_4990_V(), compoundnbt);
                        this.n_1700_B("debug.inspect.client.entity", new Object[0]);
                        break;
                    }
                    this.n_1700_B(resourcelocation, entity.s_4990_V(), (U_2912_j)null);
                    this.n_1700_B("debug.inspect.client.entity", new Object[0]);
                }
            }
        }
    }

    private void n_1700_B(K_4074_S state, c_1514_x pos, @Nullable U_2912_j compound) {
        if (compound != null) {
            compound.multiplayerClientSuggestionProvider("x");
            compound.multiplayerClientSuggestionProvider("y");
            compound.multiplayerClientSuggestionProvider("z");
            compound.multiplayerClientSuggestionProvider("id");
        }
        StringBuilder stringbuilder = new StringBuilder(f_71_T.n_1700_B(state));
        if (compound != null) {
            stringbuilder.append(compound);
        }
        String s = String.format(Locale.ROOT, "/setblock %d %d %d %s", pos.getX(), pos.getY(), pos.getZ(), stringbuilder);
        this.n_1700_B(s);
    }

    private void n_1700_B(g_2336_b entityIdIn, e_2866_D pos, @Nullable U_2912_j compound) {
        String s;
        if (compound != null) {
            compound.multiplayerClientSuggestionProvider("UUID");
            compound.multiplayerClientSuggestionProvider("Pos");
            compound.multiplayerClientSuggestionProvider("Dimension");
            String s1 = compound.P_4830_p().getString();
            s = String.format(Locale.ROOT, "/summon %s %.2f %.2f %.2f %s", entityIdIn.toString(), pos.J_1907_R, pos.R_4764_Y, pos.G_564_y, s1);
        } else {
            s = String.format(Locale.ROOT, "/summon %s %.2f %.2f %.2f", entityIdIn.toString(), pos.J_1907_R, pos.R_4764_Y, pos.G_564_y);
        }
        this.n_1700_B(s);
    }

    public void n_1700_B(long windowPointer, int key, int scanCode, int action, int modifiers) {
        if (windowPointer == this.n_1700_B.RealmsServerPing().t_148_a()) {
            boolean flag;
            if (this.G_564_y > 0L) {
                if (!Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), 67) || !Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), 292)) {
                    this.G_564_y = -1L;
                }
            } else if (Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), 67) && Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), 292)) {
                this.v_4262_N = true;
                this.G_564_y = j_3341_s.J_1907_R();
                this.P_1922_E = j_3341_s.J_1907_R();
                this.u_1723_Y = 0L;
            }
            k_2603_m inestedguieventhandler = this.n_1700_B.Y_1740_V;
            if (!(this.n_1700_B.Y_1740_V instanceof ControlsScreen) || ((ControlsScreen)inestedguieventhandler).J_1907_R <= j_3341_s.J_1907_R() - 20L) {
                if (action == 1) {
                    if (this.n_1700_B.Y_1740_V == null) {
                        this.w_1484_f.add(key);
                        A_4115_X.n_1700_B(new i_4434_b(key, true));
                    }
                    if (this.n_1700_B.P_4830_p.q_1982_R.n_1700_B(key, scanCode)) {
                        this.n_1700_B.RealmsServerPing().w_1484_f();
                        this.n_1700_B.P_4830_p.g_2268_R = this.n_1700_B.RealmsServerPing().s_956_w();
                        this.n_1700_B.P_4830_p.J_1907_R();
                        return;
                    }
                    if (this.n_1700_B.P_4830_p.PlayerInfo.n_1700_B(key, scanCode)) {
                        if (k_2603_m.hasControlDown()) {
                            // empty if block
                        }
                        x_3498_p.n_1700_B(this.n_1700_B.M_182_A, this.n_1700_B.RealmsServerPing().u_2550_I(), this.n_1700_B.RealmsServerPing().M_588_G(), this.n_1700_B.G_564_y(), p_lambda$onKeyEvent$3_1_ -> this.n_1700_B.execute(() -> this.n_1700_B.M_588_G.R_4764_Y().n_1700_B((x_282_a)p_lambda$onKeyEvent$3_1_)));
                        return;
                    }
                } else if (action == 0 && this.n_1700_B.Y_1740_V instanceof ControlsScreen) {
                    ((ControlsScreen)this.n_1700_B.Y_1740_V).n_1700_B = null;
                }
                if (action == 0 && this.n_1700_B.Y_1740_V == null) {
                    if (this.w_1484_f.remove(key)) {
                        A_4115_X.n_1700_B(new i_4434_b(key, false));
                    }
                } else if (action == 0) {
                    this.w_1484_f.remove(key);
                }
            }
            boolean bl = flag = inestedguieventhandler == null || !(inestedguieventhandler.getListener() instanceof O_694_j) || !((O_694_j)inestedguieventhandler.getListener()).canWrite();
            if (action != 0 && key == 66 && k_2603_m.hasControlDown() && flag) {
                M_2935_g.NARRATOR.setValueIndex(this.n_1700_B.P_4830_p, 1);
                if (inestedguieventhandler instanceof SimpleOptionsSubScreen) {
                    ((SimpleOptionsSubScreen)inestedguieventhandler).J_1907_R();
                }
            }
            if (inestedguieventhandler != null) {
                boolean[] aboolean = new boolean[]{false};
                k_2603_m.wrapScreenError(() -> {
                    if (!(action == 1 || action == 2 && this.J_1907_R)) {
                        if (action == 0) {
                            if (Reflector.ForgeHooksClient_onGuiKeyReleasedPre.exists()) {
                                aboolean[0] = Reflector.callBoolean(Reflector.ForgeHooksClient_onGuiKeyReleasedPre, this.n_1700_B.Y_1740_V, key, scanCode, modifiers);
                                if (aboolean[0]) {
                                    return;
                                }
                            }
                            aboolean[0] = inestedguieventhandler.keyReleased(key, scanCode, modifiers);
                            if (Reflector.ForgeHooksClient_onGuiKeyReleasedPost.exists() && !aboolean[0]) {
                                aboolean[0] = Reflector.callBoolean(Reflector.ForgeHooksClient_onGuiKeyReleasedPost, this.n_1700_B.Y_1740_V, key, scanCode, modifiers);
                            }
                        }
                    } else {
                        if (Reflector.ForgeHooksClient_onGuiKeyPressedPre.exists()) {
                            aboolean[0] = Reflector.callBoolean(Reflector.ForgeHooksClient_onGuiKeyPressedPre, this.n_1700_B.Y_1740_V, key, scanCode, modifiers);
                            if (aboolean[0]) {
                                return;
                            }
                        }
                        aboolean[0] = inestedguieventhandler.keyPressed(key, scanCode, modifiers);
                        if (Reflector.ForgeHooksClient_onGuiKeyPressedPost.exists() && !aboolean[0]) {
                            aboolean[0] = Reflector.callBoolean(Reflector.ForgeHooksClient_onGuiKeyPressedPost, this.n_1700_B.Y_1740_V, key, scanCode, modifiers);
                        }
                    }
                }, "keyPressed event handler", inestedguieventhandler.getClass().getCanonicalName());
                if (aboolean[0]) {
                    return;
                }
            }
            if (this.n_1700_B.Y_1740_V == null || this.n_1700_B.Y_1740_V.passEvents) {
                Q_4113_P.n_1700_B inputmappings$input = Q_4113_P.n_1700_B(key, scanCode);
                if (action == 0) {
                    D_590_W.n_1700_B(inputmappings$input, false);
                    if (key == 292) {
                        if (this.v_4262_N) {
                            this.v_4262_N = false;
                        } else {
                            this.n_1700_B.P_4830_p.r_3651_U = !this.n_1700_B.P_4830_p.r_3651_U;
                            this.n_1700_B.P_4830_p.RowButton = this.n_1700_B.P_4830_p.r_3651_U && k_2603_m.hasShiftDown();
                            boolean bl2 = this.n_1700_B.P_4830_p.LongRunningTask = this.n_1700_B.P_4830_p.r_3651_U && k_2603_m.hasAltDown();
                            if (this.n_1700_B.P_4830_p.r_3651_U) {
                                if (this.n_1700_B.P_4830_p.f_3449_S) {
                                    this.n_1700_B.P_4830_p.LongRunningTask = true;
                                }
                                if (this.n_1700_B.P_4830_p.u_55_V) {
                                    this.n_1700_B.P_4830_p.RowButton = true;
                                }
                            }
                        }
                    }
                } else {
                    if (key == 293 && this.n_1700_B.s_956_w != null) {
                        this.n_1700_B.s_956_w.P_1922_E();
                    }
                    boolean flag1 = false;
                    if (this.n_1700_B.Y_1740_V == null) {
                        if (key == 256) {
                            boolean flag2 = Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), 292);
                            this.n_1700_B.J_1907_R(flag2);
                        }
                        flag1 = Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), 292) && this.n_1700_B(key);
                        this.v_4262_N |= flag1;
                        if (key == 290) {
                            boolean bl3 = this.n_1700_B.P_4830_p.RetryCallException = !this.n_1700_B.P_4830_p.RetryCallException;
                        }
                    }
                    if (flag1) {
                        D_590_W.n_1700_B(inputmappings$input, false);
                    } else {
                        D_590_W.n_1700_B(inputmappings$input, true);
                        D_590_W.n_1700_B(inputmappings$input);
                    }
                    if (this.n_1700_B.P_4830_p.RowButton && key >= 48 && key <= 57) {
                        this.n_1700_B.n_1700_B(key - 48);
                    }
                }
            }
            A_4115_X.n_1700_B(new InputEvent.KeyInputEvent(key, scanCode, action, modifiers));
            Reflector.ForgeHooksClient_fireKeyInput.call(key, scanCode, action, modifiers);
        }
    }

    private void n_1700_B(long windowPointer, int codePoint, int modifiers) {
        k_2603_m iguieventlistener;
        if (windowPointer == this.n_1700_B.RealmsServerPing().t_148_a() && (iguieventlistener = this.n_1700_B.Y_1740_V) != null && this.n_1700_B.q_1982_R() == null) {
            if (Character.charCount(codePoint) == 1) {
                k_2603_m.wrapScreenError(() -> {
                    if (!Reflector.ForgeHooksClient_onGuiCharTypedPre.exists() || !Reflector.callBoolean(Reflector.ForgeHooksClient_onGuiCharTypedPre, this.n_1700_B.Y_1740_V, Character.valueOf((char)codePoint), modifiers)) {
                        boolean flag = iguieventlistener.charTyped((char)codePoint, modifiers);
                        if (Reflector.ForgeHooksClient_onGuiCharTypedPost.exists() && !flag) {
                            Reflector.callBoolean(Reflector.ForgeHooksClient_onGuiCharTypedPost, this.n_1700_B.Y_1740_V, Character.valueOf((char)codePoint), modifiers);
                        }
                    }
                }, "charTyped event handler", iguieventlistener.getClass().getCanonicalName());
            } else {
                for (char c0 : Character.toChars(codePoint)) {
                    k_2603_m.wrapScreenError(() -> {
                        if (!Reflector.ForgeHooksClient_onGuiCharTypedPre.exists() || !Reflector.callBoolean(Reflector.ForgeHooksClient_onGuiCharTypedPre, this.n_1700_B.Y_1740_V, Character.valueOf(c0), modifiers)) {
                            boolean flag = iguieventlistener.charTyped(c0, modifiers);
                            if (Reflector.ForgeHooksClient_onGuiCharTypedPost.exists() && !flag) {
                                Reflector.callBoolean(Reflector.ForgeHooksClient_onGuiCharTypedPost, this.n_1700_B.Y_1740_V, Character.valueOf(c0), modifiers);
                            }
                        }
                    }, "charTyped event handler", iguieventlistener.getClass().getCanonicalName());
                }
            }
        }
    }

    public void n_1700_B(boolean repeatEvents) {
        this.J_1907_R = repeatEvents;
    }

    public void n_1700_B(long window) {
        Q_4113_P.n_1700_B(window, (p_lambda$setupCallbacks$8_1_, p_lambda$setupCallbacks$8_3_, p_lambda$setupCallbacks$8_4_, p_lambda$setupCallbacks$8_5_, p_lambda$setupCallbacks$8_6_) -> this.n_1700_B.execute(() -> this.n_1700_B(p_lambda$setupCallbacks$8_1_, p_lambda$setupCallbacks$8_3_, p_lambda$setupCallbacks$8_4_, p_lambda$setupCallbacks$8_5_, p_lambda$setupCallbacks$8_6_)), (p_lambda$setupCallbacks$10_1_, p_lambda$setupCallbacks$10_3_, p_lambda$setupCallbacks$10_4_) -> this.n_1700_B.execute(() -> this.n_1700_B(p_lambda$setupCallbacks$10_1_, p_lambda$setupCallbacks$10_3_, p_lambda$setupCallbacks$10_4_)));
    }

    public String n_1700_B() {
        return this.R_4764_Y.n_1700_B(this.n_1700_B.RealmsServerPing().t_148_a(), (p_lambda$getClipboardString$11_1_, p_lambda$getClipboardString$11_2_) -> {
            if (p_lambda$getClipboardString$11_1_ != 65545) {
                this.n_1700_B.RealmsServerPing().n_1700_B(p_lambda$getClipboardString$11_1_, p_lambda$getClipboardString$11_2_);
            }
        });
    }

    public void n_1700_B(String string) {
        this.R_4764_Y.n_1700_B(this.n_1700_B.RealmsServerPing().t_148_a(), string);
    }

    public void J_1907_R() {
        if (this.G_564_y > 0L) {
            long i = j_3341_s.J_1907_R();
            long j = 10000L - (i - this.G_564_y);
            long k = i - this.P_1922_E;
            if (j < 0L) {
                if (k_2603_m.hasControlDown()) {
                    r_1166_b.n_1700_B();
                }
                throw new ReportedException(new n_3236_c("Manually triggered debug crash", new Throwable()));
            }
            if (k >= 1000L) {
                if (this.u_1723_Y == 0L) {
                    this.n_1700_B("debug.crash.message", new Object[0]);
                } else {
                    this.J_1907_R("debug.crash.warning", u_530_F.u_1723_Y((float)j / 1000.0f));
                }
                this.P_1922_E = i;
                ++this.u_1723_Y;
            }
        }
    }
}




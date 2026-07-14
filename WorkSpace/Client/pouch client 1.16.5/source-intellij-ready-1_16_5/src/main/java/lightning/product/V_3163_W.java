/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import lightning.product.A_4115_X;
import lightning.product.D_38_f;
import lightning.product.ServerboundInteractPacket;
import lightning.product.F_1464_b;
import lightning.product.BlockHitResult;
import lightning.product.H_4075_o;
import lightning.product.I_14_v;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.ServerboundContainerClickPacket;
import lightning.product.T_2915_h;
import lightning.product.SimpleSoundInstance;
import lightning.product.StructureBlock;
import lightning.product.U_2534_D;
import lightning.product.V_772_m;
import lightning.product.W_2853_p;
import lightning.product.UseOnContext;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.Z_3504_M;
import lightning.product.a_3913_L;
import lightning.product.a_408_T;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1070_s;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.SoundType;
import lightning.product.e_2866_D;
import lightning.product.StatsCounter;
import lightning.product.h_2739_B;
import lightning.product.CommandBlock;
import lightning.product.h_516_K;
import lightning.product.i_2572_h;
import lightning.product.Recipe;
import lightning.product.ServerboundPlayerActionPacket;
import lightning.product.k_4690_i;
import lightning.product.JigsawBlock;
import lightning.product.m_1621_v;
import lightning.product.m_3054_I;
import lightning.product.o_1800_r;
import lightning.product.p_1183_T;
import lightning.product.r_586_S;
import lightning.product.LevelAccessor;
import lightning.product.u_530_F;
import lightning.product.EntityHitResult;
import lightning.product.x_1688_C;
import mods.baritone.utils.accessor.IPlayerControllerMP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class V_3163_W
implements IPlayerControllerMP {
    private static final Logger LOGGER = LogManager.getLogger();
    private final MinecraftClient mc;
    private final W_2853_p connection;
    private c_1514_x currentBlock = new c_1514_x(-1, -1, -1);
    private Z_1993_T currentItemHittingBlock = Z_1993_T.J_1907_R;
    public float curBlockDamageMP;
    private float stepSoundTickCounter;
    public int blockHitDelay;
    private boolean isHittingBlock;
    private I_14_v currentGameType = I_14_v.J_1907_R;
    private I_14_v field_239166_k_ = I_14_v.n_1700_B;
    private final Object2ObjectLinkedOpenHashMap<Pair<c_1514_x, ServerboundPlayerActionPacket.n_1700_B>, e_2866_D> unacknowledgedDiggingPackets = new Object2ObjectLinkedOpenHashMap();
    private int currentPlayerItem;

    public V_3163_W(MinecraftClient mcIn, W_2853_p netHandler) {
        this.mc = mcIn;
        this.connection = netHandler;
    }

    public void setPlayerCapabilities(a_3913_L player) {
        this.currentGameType.n_1700_B(player.C_415_h);
    }

    public void func_241675_a_(I_14_v p_241675_1_) {
        this.field_239166_k_ = p_241675_1_;
    }

    public void setGameType(I_14_v type) {
        if (type != this.currentGameType) {
            this.field_239166_k_ = this.currentGameType;
        }
        this.currentGameType = type;
        this.currentGameType.n_1700_B(this.mc.Y_259_p.C_415_h);
    }

    public boolean shouldDrawHUD() {
        return this.currentGameType.u_1723_Y();
    }

    public boolean onPlayerDestroyBlock(c_1514_x pos) {
        if (this.mc.Y_259_p.n_1700_B(this.mc.Y_601_j, pos, this.currentGameType)) {
            return false;
        }
        k_4690_i world = this.mc.Y_601_j;
        K_4074_S blockstate = world.getBlockState(pos);
        if (!this.mc.Y_259_p.A_2714_y().J_1907_R().n_1700_B(blockstate, (b_4507_u)world, pos, this.mc.Y_259_p)) {
            return false;
        }
        T_2915_h block = blockstate.J_1907_R();
        if ((block instanceof CommandBlock || block instanceof StructureBlock || block instanceof JigsawBlock) && !this.mc.Y_259_p.ModuleManager()) {
            return false;
        }
        if (blockstate.v_4262_N()) {
            return false;
        }
        block.n_1700_B((b_4507_u)world, pos, blockstate, (a_3913_L)this.mc.Y_259_p);
        FluidState fluidstate = world.getFluidState(pos);
        boolean flag = ((b_4507_u)world).n_1700_B(pos, fluidstate.v_4262_N(), 11);
        if (flag) {
            block.n_1700_B((LevelAccessor)world, pos, blockstate);
        }
        return flag;
    }

    public boolean clickBlock(c_1514_x loc, b_257_Y face) {
        if (this.mc.Y_259_p.n_1700_B(this.mc.Y_601_j, loc, this.currentGameType)) {
            return false;
        }
        if (!this.mc.Y_601_j.H_2857_Y().n_1700_B(loc)) {
            return false;
        }
        if (this.currentGameType.P_1922_E()) {
            K_4074_S blockstate = this.mc.Y_601_j.getBlockState(loc);
            this.mc.D_60_a().n_1700_B(this.mc.Y_601_j, loc, blockstate, 1.0f);
            this.sendDiggingPacket(ServerboundPlayerActionPacket.n_1700_B.n_1700_B, loc, face);
            this.onPlayerDestroyBlock(loc);
            this.blockHitDelay = 5;
        } else if (!this.isHittingBlock || !this.isHittingPosition(loc)) {
            boolean flag;
            if (this.isHittingBlock) {
                this.sendDiggingPacket(ServerboundPlayerActionPacket.n_1700_B.J_1907_R, this.currentBlock, face);
            }
            K_4074_S blockstate1 = this.mc.Y_601_j.getBlockState(loc);
            this.mc.D_60_a().n_1700_B(this.mc.Y_601_j, loc, blockstate1, 0.0f);
            this.sendDiggingPacket(ServerboundPlayerActionPacket.n_1700_B.n_1700_B, loc, face);
            boolean bl = flag = !blockstate1.v_4262_N();
            if (flag && this.curBlockDamageMP == 0.0f) {
                blockstate1.n_1700_B((b_4507_u)this.mc.Y_601_j, loc, this.mc.Y_259_p);
            }
            if (flag && blockstate1.n_1700_B(this.mc.Y_259_p, this.mc.Y_259_p.O_508_d, loc) >= 1.0f) {
                this.onPlayerDestroyBlock(loc);
            } else {
                this.isHittingBlock = true;
                this.currentBlock = loc;
                this.currentItemHittingBlock = this.mc.Y_259_p.A_2714_y();
                this.curBlockDamageMP = 0.0f;
                this.stepSoundTickCounter = 0.0f;
                this.mc.Y_601_j.n_1700_B(this.mc.Y_259_p.j_276_v(), this.currentBlock, (int)(this.curBlockDamageMP * 10.0f) - 1);
            }
        }
        return true;
    }

    public void resetBlockRemoving() {
        if (this.isHittingBlock) {
            K_4074_S blockstate = this.mc.Y_601_j.getBlockState(this.currentBlock);
            this.mc.D_60_a().n_1700_B(this.mc.Y_601_j, this.currentBlock, blockstate, -1.0f);
            this.sendDiggingPacket(ServerboundPlayerActionPacket.n_1700_B.J_1907_R, this.currentBlock, b_257_Y.n_1700_B);
            this.isHittingBlock = false;
            this.curBlockDamageMP = 0.0f;
            this.mc.Y_601_j.n_1700_B(this.mc.Y_259_p.j_276_v(), this.currentBlock, -1);
            this.mc.Y_259_p.ModuleCategory();
        }
    }

    public boolean onPlayerDamageBlock(c_1514_x posBlock, b_257_Y directionFacing) {
        this.syncCurrentPlayItem();
        if (this.blockHitDelay > 0) {
            --this.blockHitDelay;
            return true;
        }
        if (this.currentGameType.P_1922_E() && this.mc.Y_601_j.H_2857_Y().n_1700_B(posBlock)) {
            this.blockHitDelay = 5;
            K_4074_S blockstate1 = this.mc.Y_601_j.getBlockState(posBlock);
            this.mc.D_60_a().n_1700_B(this.mc.Y_601_j, posBlock, blockstate1, 1.0f);
            this.sendDiggingPacket(ServerboundPlayerActionPacket.n_1700_B.n_1700_B, posBlock, directionFacing);
            this.onPlayerDestroyBlock(posBlock);
            return true;
        }
        if (this.isHittingPosition(posBlock)) {
            K_4074_S blockstate = this.mc.Y_601_j.getBlockState(posBlock);
            if (blockstate.v_4262_N()) {
                this.isHittingBlock = false;
                return false;
            }
            this.curBlockDamageMP += blockstate.n_1700_B(this.mc.Y_259_p, this.mc.Y_259_p.O_508_d, posBlock);
            if (this.stepSoundTickCounter % 4.0f == 0.0f) {
                SoundType soundtype = blockstate.Q_4569_t();
                this.mc.Z_976_R().n_1700_B(new SimpleSoundInstance(soundtype.u_1723_Y(), D_38_f.P_1922_E, (soundtype.n_1700_B() + 1.0f) / 8.0f, soundtype.J_1907_R() * 0.5f, posBlock));
            }
            this.stepSoundTickCounter += 1.0f;
            this.mc.D_60_a().n_1700_B(this.mc.Y_601_j, posBlock, blockstate, u_530_F.n_1700_B(this.curBlockDamageMP, 0.0f, 1.0f));
            if (this.curBlockDamageMP >= 1.0f) {
                this.isHittingBlock = false;
                this.sendDiggingPacket(ServerboundPlayerActionPacket.n_1700_B.R_4764_Y, posBlock, directionFacing);
                this.onPlayerDestroyBlock(posBlock);
                this.curBlockDamageMP = 0.0f;
                this.stepSoundTickCounter = 0.0f;
                this.blockHitDelay = 5;
            }
            this.mc.Y_601_j.n_1700_B(this.mc.Y_259_p.j_276_v(), this.currentBlock, (int)(this.curBlockDamageMP * 10.0f) - 1);
            return true;
        }
        return this.clickBlock(posBlock, directionFacing);
    }

    public float getBlockReachDistance() {
        return this.currentGameType.P_1922_E() ? 5.0f : 4.5f;
    }

    public void tick() {
        this.syncCurrentPlayItem();
        if (this.connection.getNetworkManager().u_1723_Y()) {
            this.connection.getNetworkManager().n_1700_B();
        } else {
            this.connection.getNetworkManager().u_2550_I();
        }
    }

    private boolean isHittingPosition(c_1514_x pos) {
        boolean flag;
        Z_1993_T itemstack = this.mc.Y_259_p.A_2714_y();
        boolean bl = flag = this.currentItemHittingBlock.n_1700_B() && itemstack.n_1700_B();
        if (!this.currentItemHittingBlock.n_1700_B() && !itemstack.n_1700_B()) {
            flag = itemstack.J_1907_R() == this.currentItemHittingBlock.J_1907_R() && Z_1993_T.n_1700_B(itemstack, this.currentItemHittingBlock) && (itemstack.P_1922_E() || itemstack.v_4262_N() == this.currentItemHittingBlock.v_4262_N());
        }
        return pos.equals(this.currentBlock) && flag;
    }

    public void syncCurrentPlayItem() {
        int i = this.mc.Y_259_p.l_1268_F.G_564_y;
        if (i != this.currentPlayerItem) {
            this.currentPlayerItem = i;
            this.connection.n_1700_B(new p_1183_T(this.currentPlayerItem));
        }
    }

    public m_3054_I func_217292_a(V_772_m p_217292_1_, k_4690_i p_217292_2_, x_1688_C p_217292_3_, BlockHitResult p_217292_4_) {
        m_3054_I actionresulttype;
        boolean flag1;
        o_1800_r event = new o_1800_r(p_217292_1_, p_217292_2_, p_217292_3_, p_217292_4_);
        A_4115_X.n_1700_B(event);
        if (event.n_1700_B()) {
            this.syncCurrentPlayItem();
            return m_3054_I.R_4764_Y;
        }
        this.syncCurrentPlayItem();
        c_1514_x blockpos = p_217292_4_.n_1700_B();
        if (!this.mc.Y_601_j.H_2857_Y().n_1700_B(blockpos)) {
            return m_3054_I.G_564_y;
        }
        Z_1993_T itemstack = p_217292_1_.R_4764_Y(p_217292_3_);
        if (this.currentGameType == I_14_v.P_1922_E) {
            this.connection.n_1700_B(new F_1464_b(p_217292_3_, p_217292_4_));
            return m_3054_I.n_1700_B;
        }
        boolean flag = !p_217292_1_.A_2714_y().n_1700_B() || !p_217292_1_.S_4035_N().n_1700_B();
        boolean bl = flag1 = p_217292_1_.z_3000_g() && flag;
        if (!flag1 && (actionresulttype = p_217292_2_.getBlockState(blockpos).n_1700_B((b_4507_u)p_217292_2_, p_217292_1_, p_217292_3_, p_217292_4_)).n_1700_B()) {
            this.connection.n_1700_B(new F_1464_b(p_217292_3_, p_217292_4_));
            return actionresulttype;
        }
        this.connection.n_1700_B(new F_1464_b(p_217292_3_, p_217292_4_));
        if (!itemstack.n_1700_B() && !p_217292_1_.p_1458_L().n_1700_B(itemstack.J_1907_R())) {
            m_3054_I actionresulttype1;
            UseOnContext itemusecontext = new UseOnContext(p_217292_1_, p_217292_3_, p_217292_4_);
            if (this.currentGameType.P_1922_E()) {
                int i = itemstack.t_4043_B();
                actionresulttype1 = itemstack.n_1700_B(itemusecontext);
                itemstack.P_1922_E(i);
            } else {
                actionresulttype1 = itemstack.n_1700_B(itemusecontext);
            }
            return actionresulttype1;
        }
        return m_3054_I.R_4764_Y;
    }

    public m_3054_I processRightClick(a_3913_L player, b_4507_u worldIn, x_1688_C hand) {
        if (this.currentGameType == I_14_v.P_1922_E) {
            return m_3054_I.R_4764_Y;
        }
        this.syncCurrentPlayItem();
        this.connection.n_1700_B(new Z_3504_M(hand));
        Z_1993_T itemstack = player.R_4764_Y(hand);
        if (player.p_1458_L().n_1700_B(itemstack.J_1907_R())) {
            return m_3054_I.R_4764_Y;
        }
        int i = itemstack.t_4043_B();
        InteractionResultHolder<Z_1993_T> actionresult = itemstack.n_1700_B(worldIn, player, hand);
        Z_1993_T itemstack1 = actionresult.J_1907_R();
        if (itemstack1 != itemstack) {
            player.n_1700_B(hand, itemstack1);
        }
        return actionresult.n_1700_B();
    }

    public V_772_m createPlayer(k_4690_i worldIn, StatsCounter statsManager, c_1070_s recipes) {
        return this.func_239167_a_(worldIn, statsManager, recipes, false, false);
    }

    public V_772_m func_239167_a_(k_4690_i p_239167_1_, StatsCounter p_239167_2_, c_1070_s p_239167_3_, boolean p_239167_4_, boolean p_239167_5_) {
        return new V_772_m(this.mc, p_239167_1_, this.connection, p_239167_2_, p_239167_3_, p_239167_4_, p_239167_5_);
    }

    public void attackEntity(a_3913_L playerIn, N_4263_v targetEntity) {
        h_2739_B event = new h_2739_B(targetEntity);
        A_4115_X.n_1700_B(event);
        if (targetEntity.equals(playerIn) || event.n_1700_B()) {
            return;
        }
        this.syncCurrentPlayItem();
        this.connection.n_1700_B(new ServerboundInteractPacket(targetEntity, playerIn.q_2307_F()));
        if (this.currentGameType != I_14_v.P_1922_E) {
            playerIn.H_2857_Y(targetEntity);
            playerIn.ModuleCategory();
        }
    }

    public m_3054_I interactWithEntity(a_3913_L player, N_4263_v target, x_1688_C hand) {
        this.syncCurrentPlayItem();
        this.connection.n_1700_B(new ServerboundInteractPacket(target, hand, player.q_2307_F()));
        return this.currentGameType == I_14_v.P_1922_E ? m_3054_I.R_4764_Y : player.n_1700_B(target, hand);
    }

    public m_3054_I interactWithEntity(a_3913_L player, N_4263_v target, EntityHitResult ray, x_1688_C hand) {
        this.syncCurrentPlayItem();
        e_2866_D vector3d = ray.P_1922_E().n_1700_B(target.O_3598_v(), target.X_2960_b(), target.l_2647_k());
        this.connection.n_1700_B(new ServerboundInteractPacket(target, hand, vector3d, player.q_2307_F()));
        return this.currentGameType == I_14_v.P_1922_E ? m_3054_I.R_4764_Y : target.n_1700_B(player, vector3d, hand);
    }

    public Z_1993_T windowClick(int windowId, int slotId, int mouseButton, a_408_T type, a_3913_L player) {
        short short1 = player.H_1873_g.n_1700_B(player.l_1268_F);
        Z_1993_T itemstack = player.H_1873_g.n_1700_B(slotId, mouseButton, type, player);
        m_1621_v event = new m_1621_v(windowId, slotId, mouseButton, type, itemstack, short1);
        A_4115_X.n_1700_B(event);
        if (!event.n_1700_B()) {
            this.connection.n_1700_B(new ServerboundContainerClickPacket(event.J_1907_R(), event.R_4764_Y(), event.G_564_y(), event.P_1922_E(), event.u_1723_Y(), event.v_4262_N()));
        }
        return itemstack;
    }

    public void sendPlaceRecipePacket(int p_203413_1_, Recipe<?> p_203413_2_, boolean p_203413_3_) {
        this.connection.n_1700_B(new H_4075_o(p_203413_1_, p_203413_2_, p_203413_3_));
    }

    public void sendEnchantPacket(int windowID, int button) {
        this.connection.n_1700_B(new h_516_K(windowID, button));
    }

    public void sendSlotPacket(Z_1993_T itemStackIn, int slotId) {
        if (this.currentGameType.P_1922_E()) {
            this.connection.n_1700_B(new r_586_S(slotId, itemStackIn));
        }
    }

    public void sendPacketDropItem(Z_1993_T itemStackIn) {
        if (this.currentGameType.P_1922_E() && !itemStackIn.n_1700_B()) {
            this.connection.n_1700_B(new r_586_S(-1, itemStackIn));
        }
    }

    public void onStoppedUsingItem(a_3913_L playerIn) {
        this.syncCurrentPlayItem();
        this.connection.n_1700_B(new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.n_1700_B.u_1723_Y, c_1514_x.ZERO, b_257_Y.n_1700_B));
        playerIn.g_134_G();
    }

    public boolean gameIsSurvivalOrAdventure() {
        return this.currentGameType.u_1723_Y();
    }

    public boolean isNotCreative() {
        return !this.currentGameType.P_1922_E();
    }

    public boolean isInCreativeMode() {
        return this.currentGameType.P_1922_E();
    }

    public boolean extendedReach() {
        return this.currentGameType.P_1922_E();
    }

    public boolean isRidingHorse() {
        return this.mc.Y_259_p.y_2772_m() && this.mc.Y_259_p.l_3609_d() instanceof U_2534_D;
    }

    public boolean isSpectatorMode() {
        return this.currentGameType == I_14_v.P_1922_E;
    }

    public I_14_v func_241822_k() {
        return this.field_239166_k_;
    }

    public I_14_v getCurrentGameType() {
        return this.currentGameType;
    }

    public boolean getIsHittingBlock() {
        return this.isHittingBlock;
    }

    public void pickItem(int index) {
        this.connection.n_1700_B(new i_2572_h(index));
    }

    private void sendDiggingPacket(ServerboundPlayerActionPacket.n_1700_B action, c_1514_x pos, b_257_Y dir) {
        V_772_m clientplayerentity = this.mc.Y_259_p;
        this.unacknowledgedDiggingPackets.put((Object)Pair.of((Object)pos, (Object)((Object)action)), (Object)clientplayerentity.s_4990_V());
        this.connection.n_1700_B(new ServerboundPlayerActionPacket(action, pos, dir));
    }

    public void acknowledgePlayerDiggingReceived(k_4690_i worldIn, c_1514_x pos, K_4074_S blockIn, ServerboundPlayerActionPacket.n_1700_B action, boolean successful) {
        e_2866_D vector3d = (e_2866_D)this.unacknowledgedDiggingPackets.remove((Object)Pair.of((Object)pos, (Object)((Object)action)));
        K_4074_S blockstate = worldIn.getBlockState(pos);
        if ((vector3d == null || !successful || action != ServerboundPlayerActionPacket.n_1700_B.n_1700_B && blockstate != blockIn) && blockstate != blockIn) {
            worldIn.n_1700_B(pos, blockIn);
            V_772_m playerentity = this.mc.Y_259_p;
            if (vector3d != null && worldIn == playerentity.O_508_d && playerentity.n_1700_B(pos, blockIn)) {
                playerentity.G_564_y(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y);
            }
        }
        while (this.unacknowledgedDiggingPackets.size() >= 50) {
            Pair pair = (Pair)this.unacknowledgedDiggingPackets.firstKey();
            this.unacknowledgedDiggingPackets.removeFirst();
            LOGGER.error("Too many unacked block actions, dropping " + String.valueOf(pair));
        }
    }

    @Override
    public void setIsHittingBlock(boolean isHittingBlock) {
        this.isHittingBlock = isHittingBlock;
    }

    @Override
    public c_1514_x getCurrentBlock() {
        return this.currentBlock;
    }

    @Override
    public void callSyncCurrentPlayItem() {
        this.syncCurrentPlayItem();
    }

    public void spoofInstantDig(c_1514_x pos, b_257_Y dir) {
        this.connection.n_1700_B(new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.n_1700_B.n_1700_B, pos, dir));
        this.connection.n_1700_B(new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.n_1700_B.R_4764_Y, pos, dir));
    }
}




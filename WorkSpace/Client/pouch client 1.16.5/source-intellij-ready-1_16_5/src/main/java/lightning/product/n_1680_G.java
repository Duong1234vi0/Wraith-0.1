/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  it.unimi.dsi.fastutil.objects.Object2IntMap$Entry
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.SharedConstants;
import lightning.product.K_4074_S;
import lightning.product.AbstractCookingRecipe;
import lightning.product.L_2125_Q;
import lightning.product.AbstractFurnaceBlock;
import lightning.product.ItemTags;
import lightning.product.NonNullList;
import lightning.product.U_2912_j;
import lightning.product.X_1924_A;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.e_2866_D;
import lightning.product.g_2336_b;
import lightning.product.RecipeType;
import lightning.product.Recipe;
import lightning.product.j_3341_s;
import lightning.product.ContainerHelper;
import lightning.product.ContainerData;
import lightning.product.n_4637_L;
import lightning.product.BaseContainerBlockEntity;
import lightning.product.WorldlyContainer;
import lightning.product.q_1613_l;
import lightning.product.q_1803_e;
import lightning.product.Items;
import lightning.product.r_109_r;
import lightning.product.BlockEntityType;
import lightning.product.r_4432_i;
import lightning.product.u_530_F;
import lightning.product.y_452_M;

public abstract class n_1680_G
extends BaseContainerBlockEntity
implements L_2125_Q,
X_1924_A,
WorldlyContainer,
y_452_M {
    private static final int[] G_564_y = new int[]{0};
    private static final int[] P_1922_E = new int[]{2, 1};
    private static final int[] u_1723_Y = new int[]{1};
    protected NonNullList<Z_1993_T> n_1700_B = NonNullList.n_1700_B(3, Z_1993_T.J_1907_R);
    private int v_4262_N;
    private int w_1484_f;
    private int t_148_a;
    private int s_956_w;
    protected final ContainerData J_1907_R = new ContainerData(){

        @Override
        public int n_1700_B(int index) {
            switch (index) {
                case 0: {
                    return n_1680_G.this.v_4262_N;
                }
                case 1: {
                    return n_1680_G.this.w_1484_f;
                }
                case 2: {
                    return n_1680_G.this.t_148_a;
                }
                case 3: {
                    return n_1680_G.this.s_956_w;
                }
            }
            return 0;
        }

        @Override
        public void n_1700_B(int index, int value) {
            switch (index) {
                case 0: {
                    n_1680_G.this.v_4262_N = value;
                    break;
                }
                case 1: {
                    n_1680_G.this.w_1484_f = value;
                    break;
                }
                case 2: {
                    n_1680_G.this.t_148_a = value;
                    break;
                }
                case 3: {
                    n_1680_G.this.s_956_w = value;
                }
            }
        }

        @Override
        public int n_1700_B() {
            return 4;
        }
    };
    private final Object2IntOpenHashMap<g_2336_b> h_1847_R = new Object2IntOpenHashMap();
    protected final RecipeType<? extends AbstractCookingRecipe> R_4764_Y;

    protected n_1680_G(BlockEntityType<?> tileTypeIn, RecipeType<? extends AbstractCookingRecipe> recipeTypeIn) {
        super(tileTypeIn);
        this.R_4764_Y = recipeTypeIn;
    }

    public static Map<q_1613_l, Integer> G_564_y() {
        LinkedHashMap map = Maps.newLinkedHashMap();
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, Items.u_1934_K, 20000);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.ItemHelper, 16000);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, Items.A_2487_t, 2400);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, Items.T_797_O, 1600);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, Items.d_560_A, 1600);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, ItemTags.t_1786_h, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, ItemTags.R_4764_Y, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, ItemTags.t_148_a, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, ItemTags.s_956_w, 150);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, ItemTags.P_4830_p, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, ItemTags.M_588_G, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.h_2848_I, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.AutoJoiner, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.AutoFish, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.AutoLeave, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.AutoPilot, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.AutoLes, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.k_3129_Y, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.AutoBuy, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.AutoArmor, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.AutoDupe, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.AutoFarm, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.AutoEat, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.PlayerInfo, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.UploadTokenCache, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.F_489_x, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.r_2478_U, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.L_1362_X, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.NumberSetting, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.O_2934_T, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.k_1608_N, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, ItemTags.Z_875_P, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, Items.R_1796_s, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, Items.w_2223_C, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.L_3570_A, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, ItemTags.X_933_l, 200);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, Items.B_707_U, 200);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, Items.S_234_U, 200);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, Items.D_1410_T, 200);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, Items.a_2727_J, 200);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, Items.N_1833_W, 200);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, ItemTags.w_1484_f, 200);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, ItemTags.B_1668_F, 1200);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, ItemTags.J_1907_R, 100);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, ItemTags.P_1922_E, 100);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, Items.A_4514_U, 100);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, ItemTags.Q_4569_t, 100);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, Items.S_4088_D, 100);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, ItemTags.v_4262_N, 67);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.T_797_O, 4001);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, Items.V_2454_J, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.t_1509_b, 50);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.r_3651_U, 100);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.i_770_g, 400);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.m_1628_s, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.y_254_d, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.j_1376_w, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.W_3801_h, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.i_4833_u, 300);
        n_1680_G.n_1700_B((Map<q_1613_l, Integer>)map, a_3742_W.P_2068_y, 300);
        return map;
    }

    private static boolean J_1907_R(q_1613_l item) {
        return ItemTags.g_221_o.n_1700_B(item);
    }

    private static void n_1700_B(Map<q_1613_l, Integer> map, r_109_r<q_1613_l> itemTag, int burnTimeIn) {
        for (q_1613_l item : itemTag.n_1700_B()) {
            if (n_1680_G.J_1907_R(item)) continue;
            map.put(item, burnTimeIn);
        }
    }

    private static void n_1700_B(Map<q_1613_l, Integer> map, q_1803_e itemProvider, int burnTimeIn) {
        q_1613_l item = itemProvider.u_1723_Y();
        if (n_1680_G.J_1907_R(item)) {
            if (SharedConstants.G_564_y) {
                throw j_3341_s.R_4764_Y(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item.w_1484_f(null).getString() + " a furnace fuel. That will not work!"));
            }
        } else {
            map.put(item, burnTimeIn);
        }
    }

    private boolean v_4262_N() {
        return this.v_4262_N > 0;
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.n_1700_B = NonNullList.n_1700_B(this.Y_259_p(), Z_1993_T.J_1907_R);
        ContainerHelper.J_1907_R(nbt, this.n_1700_B);
        this.v_4262_N = nbt.v_4262_N("BurnTime");
        this.t_148_a = nbt.v_4262_N("CookTime");
        this.s_956_w = nbt.v_4262_N("CookTimeTotal");
        this.w_1484_f = this.n_1700_B(this.n_1700_B.get(1));
        U_2912_j compoundnbt = nbt.M_182_A("RecipesUsed");
        for (String s : compoundnbt.G_564_y()) {
            this.h_1847_R.put((Object)new g_2336_b(s), compoundnbt.w_1484_f(s));
        }
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("BurnTime", (short)this.v_4262_N);
        compound.n_1700_B("CookTime", (short)this.t_148_a);
        compound.n_1700_B("CookTimeTotal", (short)this.s_956_w);
        ContainerHelper.n_1700_B(compound, this.n_1700_B);
        U_2912_j compoundnbt = new U_2912_j();
        this.h_1847_R.forEach((recipeId, craftedAmount) -> compoundnbt.J_1907_R(recipeId.toString(), (int)craftedAmount));
        compound.n_1700_B("RecipesUsed", compoundnbt);
        return compound;
    }

    @Override
    public void P_1922_E() {
        boolean flag = this.v_4262_N();
        boolean flag1 = false;
        if (this.v_4262_N()) {
            --this.v_4262_N;
        }
        if (!this.u_2550_I.Y_259_p) {
            Z_1993_T itemstack = this.n_1700_B.get(1);
            if (this.v_4262_N() || !itemstack.n_1700_B() && !this.n_1700_B.get(0).n_1700_B()) {
                Recipe irecipe = this.u_2550_I.s_956_w().n_1700_B(this.R_4764_Y, this, this.u_2550_I).orElse(null);
                if (!this.v_4262_N() && this.J_1907_R(irecipe)) {
                    this.w_1484_f = this.v_4262_N = this.n_1700_B(itemstack);
                    if (this.v_4262_N()) {
                        flag1 = true;
                        if (!itemstack.n_1700_B()) {
                            q_1613_l item = itemstack.J_1907_R();
                            itemstack.v_4262_N(1);
                            if (itemstack.n_1700_B()) {
                                q_1613_l item1 = item.t_1786_h();
                                this.n_1700_B.set(1, item1 == null ? Z_1993_T.J_1907_R : new Z_1993_T(item1));
                            }
                        }
                    }
                }
                if (this.v_4262_N() && this.J_1907_R(irecipe)) {
                    ++this.t_148_a;
                    if (this.t_148_a == this.s_956_w) {
                        this.t_148_a = 0;
                        this.s_956_w = this.u_1723_Y();
                        this.R_4764_Y(irecipe);
                        flag1 = true;
                    }
                } else {
                    this.t_148_a = 0;
                }
            } else if (!this.v_4262_N() && this.t_148_a > 0) {
                this.t_148_a = u_530_F.n_1700_B(this.t_148_a - 2, 0, this.s_956_w);
            }
            if (flag != this.v_4262_N()) {
                flag1 = true;
                this.u_2550_I.n_1700_B(this.M_588_G, (K_4074_S)this.u_2550_I.getBlockState(this.M_588_G).n_1700_B(AbstractFurnaceBlock.h_1847_R, this.v_4262_N()), 3);
            }
        }
        if (flag1) {
            this.J_1907_R();
        }
    }

    protected boolean J_1907_R(@Nullable Recipe<?> recipeIn) {
        if (!this.n_1700_B.get(0).n_1700_B() && recipeIn != null) {
            Z_1993_T itemstack = recipeIn.R_4764_Y();
            if (itemstack.n_1700_B()) {
                return false;
            }
            Z_1993_T itemstack1 = this.n_1700_B.get(2);
            if (itemstack1.n_1700_B()) {
                return true;
            }
            if (!itemstack1.n_1700_B(itemstack)) {
                return false;
            }
            if (itemstack1.t_4043_B() < this.J_() && itemstack1.t_4043_B() < itemstack1.R_4764_Y()) {
                return true;
            }
            return itemstack1.t_4043_B() < itemstack.R_4764_Y();
        }
        return false;
    }

    private void R_4764_Y(@Nullable Recipe<?> recipe) {
        if (recipe != null && this.J_1907_R(recipe)) {
            Z_1993_T itemstack = this.n_1700_B.get(0);
            Z_1993_T itemstack1 = recipe.R_4764_Y();
            Z_1993_T itemstack2 = this.n_1700_B.get(2);
            if (itemstack2.n_1700_B()) {
                this.n_1700_B.set(2, itemstack1.t_148_a());
            } else if (itemstack2.J_1907_R() == itemstack1.J_1907_R()) {
                itemstack2.u_1723_Y(1);
            }
            if (!this.u_2550_I.Y_259_p) {
                this.n_1700_B(recipe);
            }
            if (itemstack.J_1907_R() == a_3742_W.UploadStatus.u_1723_Y() && !this.n_1700_B.get(1).n_1700_B() && this.n_1700_B.get(1).J_1907_R() == Items.G_1539_D) {
                this.n_1700_B.set(1, new Z_1993_T(Items.W_2770_z));
            }
            itemstack.v_4262_N(1);
        }
    }

    protected int n_1700_B(Z_1993_T fuel) {
        if (fuel.n_1700_B()) {
            return 0;
        }
        q_1613_l item = fuel.J_1907_R();
        return n_1680_G.G_564_y().getOrDefault(item, 0);
    }

    protected int u_1723_Y() {
        return this.u_2550_I.s_956_w().n_1700_B(this.R_4764_Y, this, this.u_2550_I).map(AbstractCookingRecipe::P_1922_E).orElse(200);
    }

    public static boolean J_1907_R(Z_1993_T stack) {
        return n_1680_G.G_564_y().containsKey(stack.J_1907_R());
    }

    @Override
    public int[] n_1700_B(b_257_Y side) {
        if (side == b_257_Y.n_1700_B) {
            return P_1922_E;
        }
        return side == b_257_Y.J_1907_R ? G_564_y : u_1723_Y;
    }

    @Override
    public boolean n_1700_B(int index, Z_1993_T itemStackIn, @Nullable b_257_Y direction) {
        return this.a_(index, itemStackIn);
    }

    @Override
    public boolean J_1907_R(int index, Z_1993_T stack, b_257_Y direction) {
        q_1613_l item;
        return direction != b_257_Y.n_1700_B || index != 1 || (item = stack.J_1907_R()) == Items.W_2770_z || item == Items.G_1539_D;
    }

    @Override
    public int Y_259_p() {
        return this.n_1700_B.size();
    }

    @Override
    public boolean Q_2552_b() {
        for (Z_1993_T itemstack : this.n_1700_B) {
            if (itemstack.n_1700_B()) continue;
            return false;
        }
        return true;
    }

    @Override
    public Z_1993_T s_956_w(int index) {
        return this.n_1700_B.get(index);
    }

    @Override
    public Z_1993_T n_1700_B(int index, int count) {
        return ContainerHelper.n_1700_B(this.n_1700_B, index, count);
    }

    @Override
    public Z_1993_T u_2550_I(int index) {
        return ContainerHelper.n_1700_B(this.n_1700_B, index);
    }

    @Override
    public void J_1907_R(int index, Z_1993_T stack) {
        Z_1993_T itemstack = this.n_1700_B.get(index);
        boolean flag = !stack.n_1700_B() && stack.n_1700_B(itemstack) && Z_1993_T.n_1700_B(stack, itemstack);
        this.n_1700_B.set(index, stack);
        if (stack.t_4043_B() > this.J_()) {
            stack.P_1922_E(this.J_());
        }
        if (index == 0 && !flag) {
            this.s_956_w = this.u_1723_Y();
            this.t_148_a = 0;
            this.J_1907_R();
        }
    }

    @Override
    public boolean R_4764_Y(a_3913_L player) {
        if (this.u_2550_I.getTileEntity(this.M_588_G) != this) {
            return false;
        }
        return player.v_4262_N((double)this.M_588_G.getX() + 0.5, (double)this.M_588_G.getY() + 0.5, (double)this.M_588_G.getZ() + 0.5) <= 64.0;
    }

    @Override
    public boolean a_(int index, Z_1993_T stack) {
        if (index == 2) {
            return false;
        }
        if (index != 1) {
            return true;
        }
        Z_1993_T itemstack = this.n_1700_B.get(1);
        return n_1680_G.J_1907_R(stack) || stack.J_1907_R() == Items.G_1539_D && itemstack.J_1907_R() != Items.G_1539_D;
    }

    @Override
    public void C_2741_M() {
        this.n_1700_B.clear();
    }

    @Override
    public void n_1700_B(@Nullable Recipe<?> recipe) {
        if (recipe != null) {
            g_2336_b resourcelocation = recipe.u_1723_Y();
            this.h_1847_R.addTo((Object)resourcelocation, 1);
        }
    }

    @Override
    @Nullable
    public Recipe<?> R_4764_Y() {
        return null;
    }

    @Override
    public void n_1700_B(a_3913_L player) {
    }

    public void G_564_y(a_3913_L player) {
        List<Recipe<?>> list = this.n_1700_B(player.O_508_d, player.s_4990_V());
        player.J_1907_R(list);
        this.h_1847_R.clear();
    }

    public List<Recipe<?>> n_1700_B(b_4507_u world, e_2866_D pos) {
        ArrayList list = Lists.newArrayList();
        for (Object2IntMap.Entry entry : this.h_1847_R.object2IntEntrySet()) {
            world.s_956_w().n_1700_B((g_2336_b)entry.getKey()).ifPresent(recipe -> {
                list.add(recipe);
                n_1680_G.n_1700_B(world, pos, entry.getIntValue(), ((AbstractCookingRecipe)recipe).J_1907_R());
            });
        }
        return list;
    }

    private static void n_1700_B(b_4507_u world, e_2866_D pos, int craftedAmount, float experience) {
        int i = u_530_F.G_564_y((float)craftedAmount * experience);
        float f = u_530_F.w_1484_f((float)craftedAmount * experience);
        if (f != 0.0f && Math.random() < (double)f) {
            ++i;
        }
        while (i > 0) {
            int j = n_4637_L.n_1700_B(i);
            i -= j;
            world.a_(new n_4637_L(world, pos.J_1907_R, pos.R_4764_Y, pos.G_564_y, j));
        }
    }

    @Override
    public void n_1700_B(r_4432_i helper) {
        for (Z_1993_T itemstack : this.n_1700_B) {
            helper.J_1907_R(itemstack);
        }
    }
}




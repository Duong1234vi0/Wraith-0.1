/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.I_408_V;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.Container;
import lightning.product.N_4263_v;
import lightning.product.NonNullList;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.V_4572_l;
import lightning.product.W_3491_f;
import lightning.product.X_1924_A;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.c_1788_D;
import lightning.product.i_2154_H;
import lightning.product.j_2011_l;
import lightning.product.ContainerHelper;
import lightning.product.n_1494_c;
import lightning.product.WorldlyContainer;
import lightning.product.BlockEntityType;
import lightning.product.t_693_s;
import lightning.product.v_3445_Z;
import lightning.product.x_268_Y;
import lightning.product.x_282_a;
import lightning.product.BooleanOp;
import lightning.product.z_1477_l;
import lightning.product.Hopper;

public class w_748_f
extends V_4572_l
implements X_1924_A,
Hopper {
    private NonNullList<Z_1993_T> u_1723_Y = NonNullList.n_1700_B(5, Z_1993_T.J_1907_R);
    private int v_4262_N = -1;
    private long w_1484_f;

    public w_748_f() {
        super(BlockEntityType.t_1786_h);
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.u_1723_Y = NonNullList.n_1700_B(this.Y_259_p(), Z_1993_T.J_1907_R);
        if (!this.J_1907_R(nbt)) {
            ContainerHelper.J_1907_R(nbt, this.u_1723_Y);
        }
        this.v_4262_N = nbt.w_1484_f("TransferCooldown");
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (!this.R_4764_Y(compound)) {
            ContainerHelper.n_1700_B(compound, this.u_1723_Y);
        }
        compound.J_1907_R("TransferCooldown", this.v_4262_N);
        return compound;
    }

    @Override
    public int Y_259_p() {
        return this.u_1723_Y.size();
    }

    @Override
    public Z_1993_T n_1700_B(int index, int count) {
        this.G_564_y(null);
        return ContainerHelper.n_1700_B(this.L_(), index, count);
    }

    @Override
    public void J_1907_R(int index, Z_1993_T stack) {
        this.G_564_y(null);
        this.L_().set(index, stack);
        if (stack.t_4043_B() > this.J_()) {
            stack.P_1922_E(this.J_());
        }
    }

    @Override
    protected x_282_a F_() {
        return new F_2904_S("container.hopper");
    }

    @Override
    public void P_1922_E() {
        if (this.u_2550_I != null && !this.u_2550_I.Y_259_p) {
            --this.v_4262_N;
            this.w_1484_f = this.u_2550_I.X_933_l();
            if (!this.u_2550_I()) {
                this.n_1700_B(0);
                this.n_1700_B(() -> w_748_f.n_1700_B(this));
            }
        }
    }

    private boolean n_1700_B(Supplier<Boolean> p_200109_1_) {
        if (this.u_2550_I != null && !this.u_2550_I.Y_259_p) {
            if (!this.u_2550_I() && this.e_4240_b().R_4764_Y(c_1788_D.h_1847_R).booleanValue()) {
                boolean flag = false;
                if (!this.Q_2552_b()) {
                    flag = this.w_1484_f();
                }
                if (!this.v_4262_N()) {
                    flag |= p_200109_1_.get().booleanValue();
                }
                if (flag) {
                    this.n_1700_B(8);
                    this.J_1907_R();
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private boolean v_4262_N() {
        for (Z_1993_T itemstack : this.u_1723_Y) {
            if (!itemstack.n_1700_B() && itemstack.t_4043_B() == itemstack.R_4764_Y()) continue;
            return false;
        }
        return true;
    }

    private boolean w_1484_f() {
        Container iinventory = this.s_956_w();
        if (iinventory == null) {
            return false;
        }
        b_257_Y direction = this.e_4240_b().R_4764_Y(c_1788_D.P_4830_p).u_1723_Y();
        if (this.J_1907_R(iinventory, direction)) {
            return false;
        }
        for (int i = 0; i < this.Y_259_p(); ++i) {
            if (this.s_956_w(i).n_1700_B()) continue;
            Z_1993_T itemstack = this.s_956_w(i).t_148_a();
            Z_1993_T itemstack1 = w_748_f.n_1700_B((Container)this, iinventory, this.n_1700_B(i, 1), direction);
            if (itemstack1.n_1700_B()) {
                iinventory.J_1907_R();
                return true;
            }
            this.J_1907_R(i, itemstack);
        }
        return false;
    }

    private static IntStream n_1700_B(Container p_213972_0_, b_257_Y p_213972_1_) {
        return p_213972_0_ instanceof WorldlyContainer ? IntStream.of(((WorldlyContainer)p_213972_0_).n_1700_B(p_213972_1_)) : IntStream.range(0, p_213972_0_.Y_259_p());
    }

    private boolean J_1907_R(Container inventoryIn, b_257_Y side) {
        return w_748_f.n_1700_B(inventoryIn, side).allMatch(p_213970_1_ -> {
            Z_1993_T itemstack = inventoryIn.s_956_w(p_213970_1_);
            return itemstack.t_4043_B() >= itemstack.R_4764_Y();
        });
    }

    private static boolean R_4764_Y(Container inventoryIn, b_257_Y side) {
        return w_748_f.n_1700_B(inventoryIn, side).allMatch(p_213973_1_ -> inventoryIn.s_956_w(p_213973_1_).n_1700_B());
    }

    public static boolean n_1700_B(Hopper hopper) {
        Container iinventory = w_748_f.J_1907_R(hopper);
        if (iinventory != null) {
            b_257_Y direction = b_257_Y.n_1700_B;
            return w_748_f.R_4764_Y(iinventory, direction) ? false : w_748_f.n_1700_B(iinventory, direction).anyMatch(p_213971_3_ -> w_748_f.n_1700_B(hopper, iinventory, p_213971_3_, direction));
        }
        for (n_1494_c itementity : w_748_f.R_4764_Y(hopper)) {
            if (!w_748_f.n_1700_B((Container)hopper, itementity)) continue;
            return true;
        }
        return false;
    }

    private static boolean n_1700_B(Hopper hopper, Container inventoryIn, int index, b_257_Y direction) {
        Z_1993_T itemstack = inventoryIn.s_956_w(index);
        if (!itemstack.n_1700_B() && w_748_f.J_1907_R(inventoryIn, itemstack, index, direction)) {
            Z_1993_T itemstack1 = itemstack.t_148_a();
            Z_1993_T itemstack2 = w_748_f.n_1700_B(inventoryIn, (Container)hopper, inventoryIn.n_1700_B(index, 1), (b_257_Y)null);
            if (itemstack2.n_1700_B()) {
                inventoryIn.J_1907_R();
                return true;
            }
            inventoryIn.J_1907_R(index, itemstack1);
        }
        return false;
    }

    public static boolean n_1700_B(Container p_200114_0_, n_1494_c p_200114_1_) {
        boolean flag = false;
        Z_1993_T itemstack = p_200114_1_.P_1922_E().t_148_a();
        Z_1993_T itemstack1 = w_748_f.n_1700_B((Container)null, p_200114_0_, itemstack, (b_257_Y)null);
        if (itemstack1.n_1700_B()) {
            flag = true;
            p_200114_1_.Ops();
        } else {
            p_200114_1_.J_1907_R(itemstack1);
        }
        return flag;
    }

    public static Z_1993_T n_1700_B(@Nullable Container source, Container destination, Z_1993_T stack, @Nullable b_257_Y direction) {
        if (destination instanceof WorldlyContainer && direction != null) {
            WorldlyContainer isidedinventory = (WorldlyContainer)destination;
            int[] aint = isidedinventory.n_1700_B(direction);
            for (int k = 0; k < aint.length && !stack.n_1700_B(); ++k) {
                stack = w_748_f.n_1700_B(source, destination, stack, aint[k], direction);
            }
        } else {
            int i = destination.Y_259_p();
            for (int j = 0; j < i && !stack.n_1700_B(); ++j) {
                stack = w_748_f.n_1700_B(source, destination, stack, j, direction);
            }
        }
        return stack;
    }

    private static boolean n_1700_B(Container inventoryIn, Z_1993_T stack, int index, @Nullable b_257_Y side) {
        if (!inventoryIn.a_(index, stack)) {
            return false;
        }
        return !(inventoryIn instanceof WorldlyContainer) || ((WorldlyContainer)inventoryIn).n_1700_B(index, stack, side);
    }

    private static boolean J_1907_R(Container inventoryIn, Z_1993_T stack, int index, b_257_Y side) {
        return !(inventoryIn instanceof WorldlyContainer) || ((WorldlyContainer)inventoryIn).J_1907_R(index, stack, side);
    }

    private static Z_1993_T n_1700_B(@Nullable Container source, Container destination, Z_1993_T stack, int index, @Nullable b_257_Y direction) {
        Z_1993_T itemstack = destination.s_956_w(index);
        if (w_748_f.n_1700_B(destination, stack, index, direction)) {
            boolean flag = false;
            boolean flag1 = destination.Q_2552_b();
            if (itemstack.n_1700_B()) {
                destination.J_1907_R(index, stack);
                stack = Z_1993_T.J_1907_R;
                flag = true;
            } else if (w_748_f.n_1700_B(itemstack, stack)) {
                int i = stack.R_4764_Y() - itemstack.t_4043_B();
                int j = Math.min(stack.t_4043_B(), i);
                stack.v_4262_N(j);
                itemstack.u_1723_Y(j);
                boolean bl = flag = j > 0;
            }
            if (flag) {
                w_748_f hoppertileentity1;
                if (flag1 && destination instanceof w_748_f && !(hoppertileentity1 = (w_748_f)destination).M_588_G()) {
                    int k = 0;
                    if (source instanceof w_748_f) {
                        w_748_f hoppertileentity = (w_748_f)source;
                        if (hoppertileentity1.w_1484_f >= hoppertileentity.w_1484_f) {
                            k = 1;
                        }
                    }
                    hoppertileentity1.n_1700_B(8 - k);
                }
                destination.J_1907_R();
            }
        }
        return stack;
    }

    @Nullable
    private Container s_956_w() {
        b_257_Y direction = this.e_4240_b().R_4764_Y(c_1788_D.P_4830_p);
        return w_748_f.n_1700_B(this.c_3005_b(), this.M_588_G.offset(direction));
    }

    @Nullable
    public static Container J_1907_R(Hopper hopper) {
        return w_748_f.n_1700_B(hopper.c_3005_b(), hopper.H_2857_Y(), hopper.A_4115_X() + 1.0, hopper.Y_1740_V());
    }

    public static List<n_1494_c> R_4764_Y(Hopper p_200115_0_) {
        return p_200115_0_.R_4764_Y().G_564_y().stream().flatMap(p_200110_1_ -> p_200115_0_.c_3005_b().n_1700_B(n_1494_c.class, p_200110_1_.offset(p_200115_0_.H_2857_Y() - 0.5, p_200115_0_.A_4115_X() - 0.5, p_200115_0_.Y_1740_V() - 0.5), I_408_V.n_1700_B).stream()).collect(Collectors.toList());
    }

    @Nullable
    public static Container n_1700_B(b_4507_u p_195484_0_, c_1514_x p_195484_1_) {
        return w_748_f.n_1700_B(p_195484_0_, (double)p_195484_1_.getX() + 0.5, (double)p_195484_1_.getY() + 0.5, (double)p_195484_1_.getZ() + 0.5);
    }

    @Nullable
    public static Container n_1700_B(b_4507_u worldIn, double x, double y, double z) {
        List<N_4263_v> list;
        i_2154_H tileentity;
        Container iinventory = null;
        c_1514_x blockpos = new c_1514_x(x, y, z);
        K_4074_S blockstate = worldIn.getBlockState(blockpos);
        T_2915_h block = blockstate.J_1907_R();
        if (block instanceof j_2011_l) {
            iinventory = ((j_2011_l)((Object)block)).n_1700_B(blockstate, worldIn, blockpos);
        } else if (block.G_564_y() && (tileentity = worldIn.getTileEntity(blockpos)) instanceof Container && (iinventory = (Container)((Object)tileentity)) instanceof t_693_s && block instanceof v_3445_Z) {
            iinventory = v_3445_Z.n_1700_B((v_3445_Z)block, blockstate, worldIn, blockpos, true);
        }
        if (iinventory == null && !(list = worldIn.J_1907_R((N_4263_v)null, new I_4817_s(x - 0.5, y - 0.5, z - 0.5, x + 0.5, y + 0.5, z + 0.5), I_408_V.G_564_y)).isEmpty()) {
            iinventory = (Container)((Object)list.get(worldIn.w_1457_N.nextInt(list.size())));
        }
        return iinventory;
    }

    private static boolean n_1700_B(Z_1993_T stack1, Z_1993_T stack2) {
        if (stack1.J_1907_R() != stack2.J_1907_R()) {
            return false;
        }
        if (stack1.v_4262_N() != stack2.v_4262_N()) {
            return false;
        }
        if (stack1.t_4043_B() > stack1.R_4764_Y()) {
            return false;
        }
        return Z_1993_T.n_1700_B(stack1, stack2);
    }

    @Override
    public double H_2857_Y() {
        return (double)this.M_588_G.getX() + 0.5;
    }

    @Override
    public double A_4115_X() {
        return (double)this.M_588_G.getY() + 0.5;
    }

    @Override
    public double Y_1740_V() {
        return (double)this.M_588_G.getZ() + 0.5;
    }

    private void n_1700_B(int ticks) {
        this.v_4262_N = ticks;
    }

    private boolean u_2550_I() {
        return this.v_4262_N > 0;
    }

    private boolean M_588_G() {
        return this.v_4262_N > 8;
    }

    @Override
    protected NonNullList<Z_1993_T> L_() {
        return this.u_1723_Y;
    }

    @Override
    protected void n_1700_B(NonNullList<Z_1993_T> itemsIn) {
        this.u_1723_Y = itemsIn;
    }

    public void n_1700_B(N_4263_v p_200113_1_) {
        if (p_200113_1_ instanceof n_1494_c) {
            c_1514_x blockpos = this.x_607_J();
            if (x_268_Y.R_4764_Y(x_268_Y.n_1700_B(p_200113_1_.i_601_W().offset(-blockpos.getX(), -blockpos.getY(), -blockpos.getZ())), this.R_4764_Y(), BooleanOp.t_148_a)) {
                this.n_1700_B(() -> w_748_f.n_1700_B((Container)this, (n_1494_c)p_200113_1_));
            }
        }
    }

    @Override
    protected a_2900_S n_1700_B(int id, W_3491_f player) {
        return new z_1477_l(id, player, this);
    }
}



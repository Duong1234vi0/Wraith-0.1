/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lightning.product.NumberSetting;
import lightning.product.T_2915_h;
import lightning.product.V_4557_X;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_408_T;
import lightning.product.MinecraftAccess;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.AirItem;
import lightning.product.AirBlock;
import lightning.product.n_3932_q;
import lightning.product.BooleanSetting;
import lightning.product.q_1613_l;
import lightning.product.KeyBindSetting;
import lightning.product.q_3386_W;
import lightning.product.ModeSetting;
import lightning.product.Items;
import lightning.product.v_1900_v;
import lightning.product.x_1688_C;

public class M_1321_u
extends n_3932_q {
    private final List<KeyBindSetting> J_1907_R;
    private final ModeSetting R_4764_Y;
    private final NumberSetting G_564_y;
    private final BooleanSetting P_1922_E;
    private final NumberSetting u_1723_Y;
    private final V_4557_X v_4262_N = new V_4557_X();
    private Z_1993_T w_1484_f = null;
    private boolean t_148_a = false;
    public c_1514_x n_1700_B = c_1514_x.ZERO;

    public M_1321_u(List<KeyBindSetting> binds, ModeSetting helmetType, NumberSetting swapDelay, BooleanSetting autoBoots, NumberSetting ecRadius) {
        this.J_1907_R = binds;
        this.R_4764_Y = helmetType;
        this.G_564_y = swapDelay;
        this.P_1922_E = autoBoots;
        this.u_1723_Y = ecRadius;
    }

    @Override
    public String J_1907_R() {
        return "MetaHvH";
    }

    @Override
    public String R_4764_Y() {
        return "Meta";
    }

    @Override
    public List<KeyBindSetting> G_564_y() {
        return this.J_1907_R;
    }

    @Override
    public List<q_3386_W.n_1700_B> n_1700_B(boolean onlyBar) {
        ArrayList<q_3386_W.n_1700_B> renderItems = new ArrayList<q_3386_W.n_1700_B>();
        boolean hasSantaHead = this.t_1786_h();
        String headStatus = hasSantaHead ? "\u2713" : "\u2014";
        q_3386_W.n_1700_B headData = new q_3386_W.n_1700_B("\u0421\u0432\u0430\u043f \u0433\u043e\u043b\u043e\u0432\u044b", "H", headStatus, Items.C_3560_B);
        if (this.J_1907_R != null && !this.J_1907_R.isEmpty()) {
            headData.n_1700_B((Integer)this.J_1907_R.get(0).J_1907_R());
        }
        renderItems.add(headData);
        boolean hasGoldBoots = this.n_1700_B(Items.m_4644_u) != -1;
        boolean hasNormalBoots = this.n_1700_B(Items.j_2129_E) != -1 || this.n_1700_B(Items.V_4557_X) != -1;
        String bootsStatus = hasGoldBoots || hasNormalBoots ? "\u2713" : "\u2014";
        q_3386_W.n_1700_B bootsData = new q_3386_W.n_1700_B("\u0421\u0432\u0430\u043f \u0431\u043e\u0442\u0438\u043d\u043e\u043a", "B", bootsStatus, Items.m_4644_u);
        if (this.J_1907_R != null && this.J_1907_R.size() > 1) {
            bootsData.n_1700_B((Integer)this.J_1907_R.get(1).J_1907_R());
        }
        renderItems.add(bootsData);
        String ecStatus = this.t_148_a ? "ON" : "OFF";
        q_3386_W.n_1700_B ecData = new q_3386_W.n_1700_B("\u0410\u0432\u0442\u043e EC", "EC", ecStatus, Items.N_260_m);
        if (this.J_1907_R != null && this.J_1907_R.size() > 2) {
            ecData.n_1700_B((Integer)this.J_1907_R.get(2).J_1907_R());
        }
        renderItems.add(ecData);
        return renderItems;
    }

    @Override
    public boolean n_1700_B(int keyCode) {
        KeyBindSetting ecBind;
        KeyBindSetting bootsBind;
        if (this.J_1907_R == null || this.J_1907_R.isEmpty()) {
            return false;
        }
        if (MinecraftAccess.c_3005_b.Y_259_p == null) {
            return false;
        }
        KeyBindSetting headBind = this.J_1907_R.get(0);
        if ((Integer)headBind.J_1907_R() != -1 && (Integer)headBind.J_1907_R() == keyCode) {
            if (this.v_4262_N.n_1700_B((double)(((Float)this.G_564_y.J_1907_R()).longValue() * 100L))) {
                this.u_2550_I();
                this.v_4262_N.n_1700_B();
            }
            return true;
        }
        if (this.J_1907_R.size() > 1 && (Integer)(bootsBind = this.J_1907_R.get(1)).J_1907_R() != -1 && (Integer)bootsBind.J_1907_R() == keyCode) {
            this.M_588_G();
            return true;
        }
        if (this.J_1907_R.size() > 2 && (Integer)(ecBind = this.J_1907_R.get(2)).J_1907_R() != -1 && (Integer)ecBind.J_1907_R() == keyCode) {
            this.t_148_a = !this.t_148_a;
            v_1900_v.n_1700_B(this.t_148_a ? "\u00a7a\u0410\u0432\u0442\u043e EC \u0432\u043a\u043b\u044e\u0447\u0435\u043d" : "\u00a7c\u0410\u0432\u0442\u043e EC \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d", new Object[0]);
            if (!this.t_148_a) {
                this.n_1700_B = c_1514_x.ZERO;
            }
            return true;
        }
        return false;
    }

    private void u_2550_I() {
        boolean isWearingHelmet;
        Z_1993_T currentHead = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.J_1907_R.get(3);
        boolean isHelmetNotEmpty = !(currentHead.J_1907_R() instanceof AirItem);
        q_1613_l helmetItem = this.Q_4569_t();
        int helmetSlot = this.n_1700_B(helmetItem);
        boolean isWearingSantaHead = currentHead.J_1907_R() == Items.C_3560_B && (this.n_1700_B(currentHead) || this.J_1907_R(currentHead));
        boolean bl = isWearingHelmet = currentHead.J_1907_R() == helmetItem;
        if (isWearingHelmet) {
            if (isHelmetNotEmpty) {
                this.w_1484_f = currentHead.t_148_a();
            }
            this.M_182_A();
        } else if (isWearingSantaHead && helmetSlot != -1) {
            if (isHelmetNotEmpty) {
                this.w_1484_f = currentHead.t_148_a();
            }
            this.n_1700_B(helmetSlot, 5);
        } else {
            v_1900_v.n_1700_B("\u00a7c\u041d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0448\u043b\u0435\u043c \u0438\u043b\u0438 \u0433\u043e\u043b\u043e\u0432\u0430 \u0434\u043b\u044f \u0441\u0432\u0430\u043f\u0430!", new Object[0]);
        }
    }

    private void M_588_G() {
        int normalSlot;
        Z_1993_T currentBoots = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.J_1907_R.get(0);
        q_1613_l goldBoots = Items.m_4644_u;
        int goldSlot = this.n_1700_B(goldBoots);
        q_1613_l normalBoots = null;
        int netheriteSlot = this.n_1700_B(Items.j_2129_E);
        int diamondSlot = this.n_1700_B(Items.V_4557_X);
        if (netheriteSlot != -1) {
            normalBoots = Items.j_2129_E;
        } else if (diamondSlot != -1) {
            normalBoots = Items.V_4557_X;
        }
        int n = normalSlot = normalBoots == null ? -1 : this.n_1700_B(normalBoots);
        if (currentBoots.J_1907_R() == goldBoots && normalSlot != -1) {
            this.n_1700_B(normalSlot, 8);
        } else if (goldSlot != -1) {
            this.n_1700_B(goldSlot, 8);
        } else {
            v_1900_v.n_1700_B("\u00a7c\u041d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u044b \u0431\u043e\u0442\u0438\u043d\u043a\u0438 \u0434\u043b\u044f \u0441\u0432\u0430\u043f\u0430!", new Object[0]);
        }
    }

    public void w_1484_f() {
        if (!this.P_1922_E.t_148_a().booleanValue() || MinecraftAccess.c_3005_b.Y_259_p == null) {
            return;
        }
        Z_1993_T currentBoots = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.J_1907_R.get(0);
        q_1613_l goldBoots = Items.m_4644_u;
        int goldSlot = this.n_1700_B(goldBoots);
        q_1613_l normalBoots = null;
        int netheriteSlot = this.n_1700_B(Items.j_2129_E);
        int diamondSlot = this.n_1700_B(Items.V_4557_X);
        if (netheriteSlot != -1) {
            normalBoots = Items.j_2129_E;
        } else if (diamondSlot != -1) {
            normalBoots = Items.V_4557_X;
        }
        int normalSlot = normalBoots == null ? -1 : this.n_1700_B(normalBoots);
        boolean inWater = MinecraftAccess.c_3005_b.Y_259_p.RowButton();
        if (inWater && normalSlot != -1 && currentBoots.J_1907_R() != normalBoots) {
            this.n_1700_B(normalSlot, 8);
        } else if (!inWater && goldSlot != -1 && currentBoots.J_1907_R() != goldBoots) {
            this.n_1700_B(goldSlot, 8);
        }
    }

    private void P_4830_p() {
        int pickSlot;
        if (!this.t_148_a || MinecraftAccess.c_3005_b.Y_259_p == null || MinecraftAccess.c_3005_b.Y_601_j == null) {
            return;
        }
        int netheritePick = this.n_1700_B(Items.K_1964_I);
        int diamondPick = this.n_1700_B(Items.C_1577_A);
        int n = pickSlot = netheritePick != -1 ? netheritePick : diamondPick;
        if (pickSlot == -1) {
            return;
        }
        if (!MinecraftAccess.c_3005_b.Y_259_p.Y_601_j()) {
            for (c_1514_x pos : this.h_1847_R()) {
                int invFrom;
                T_2915_h block = MinecraftAccess.c_3005_b.Y_601_j.getBlockState(pos).J_1907_R();
                if (block != a_3742_W.k_2348_i) continue;
                int oldSlot = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.G_564_y;
                if (pickSlot < 9) {
                    MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.G_564_y = pickSlot;
                } else {
                    invFrom = pickSlot < 9 ? pickSlot + 36 : pickSlot;
                    MinecraftAccess.c_3005_b.w_1457_N.windowClick(0, invFrom, oldSlot, a_408_T.R_4764_Y, MinecraftAccess.c_3005_b.Y_259_p);
                }
                this.n_1700_B(pos);
                if (pickSlot >= 9) {
                    invFrom = pickSlot < 9 ? pickSlot + 36 : pickSlot;
                    MinecraftAccess.c_3005_b.w_1457_N.windowClick(0, invFrom, oldSlot, a_408_T.R_4764_Y, MinecraftAccess.c_3005_b.Y_259_p);
                }
                MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.G_564_y = oldSlot;
                break;
            }
        }
    }

    private CopyOnWriteArrayList<c_1514_x> h_1847_R() {
        CopyOnWriteArrayList<c_1514_x> blocks = new CopyOnWriteArrayList<c_1514_x>();
        if (MinecraftAccess.c_3005_b.Y_259_p == null || MinecraftAccess.c_3005_b.Y_601_j == null) {
            return blocks;
        }
        c_1514_x start = MinecraftAccess.c_3005_b.Y_259_p.b_2312_j();
        int dis = ((Float)this.u_1723_Y.J_1907_R()).intValue();
        for (int y = 3; y >= 0; --y) {
            for (int x = dis; x >= -dis; --x) {
                for (int z = dis; z >= -dis; --z) {
                    T_2915_h block;
                    c_1514_x pos = start.add(x, y, z);
                    if (pos.getY() <= 0 || (block = MinecraftAccess.c_3005_b.Y_601_j.getBlockState(pos).J_1907_R()) instanceof AirBlock || block != a_3742_W.k_2348_i) continue;
                    blocks.add(pos);
                }
            }
        }
        return blocks;
    }

    public void n_1700_B(c_1514_x blockpos) {
        if (MinecraftAccess.c_3005_b.Y_259_p == null || MinecraftAccess.c_3005_b.Y_259_p.Y_601_j() || MinecraftAccess.c_3005_b.w_1457_N == null) {
            return;
        }
        b_257_Y direction = b_257_Y.J_1907_R;
        if (MinecraftAccess.c_3005_b.w_1457_N.clickBlock(blockpos, direction)) {
            MinecraftAccess.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
            this.n_1700_B = blockpos;
        }
    }

    private q_1613_l Q_4569_t() {
        return ((String)this.R_4764_Y.J_1907_R()).equals("\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439") ? Items.u_488_m : Items.h_3066_J;
    }

    private void M_182_A() {
        for (int slot = 0; slot < MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.Y_259_p(); ++slot) {
            Z_1993_T stack = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.s_956_w(slot);
            if (stack.n_1700_B() || stack.Q_4569_t() == null || stack.J_1907_R() != Items.C_3560_B || !this.n_1700_B(stack) && !this.J_1907_R(stack)) continue;
            this.n_1700_B(slot, 5);
            return;
        }
        v_1900_v.n_1700_B("\u00a7c\u0413\u043e\u043b\u043e\u0432\u0430 \u0441\u0430\u043d\u0442\u044b \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u0430 \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435!", new Object[0]);
    }

    private boolean t_1786_h() {
        for (int slot = 0; slot < MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.Y_259_p(); ++slot) {
            Z_1993_T stack = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.s_956_w(slot);
            if (stack.n_1700_B() || stack.Q_4569_t() == null || stack.J_1907_R() != Items.C_3560_B || !this.n_1700_B(stack) && !this.J_1907_R(stack)) continue;
            return true;
        }
        Z_1993_T currentHead = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.J_1907_R.get(3);
        return currentHead.J_1907_R() == Items.C_3560_B && (this.n_1700_B(currentHead) || this.J_1907_R(currentHead));
    }

    private boolean n_1700_B(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.C_3560_B || !stack.h_1847_R()) {
            return false;
        }
        String tag = stack.Q_4569_t().toString();
        return tag.contains("AttributeModifiers") && tag.split("AttributeModifiers", 2)[1].startsWith(":[{Amount:3.0d,Slot:\"head\",AttributeName:\"minecraft:generic.armor\",Operation:0,UUID:[I;427683850,761809167,-1124274585,-962634053]");
    }

    private boolean J_1907_R(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.C_3560_B || !stack.h_1847_R()) {
            return false;
        }
        String tag = stack.Q_4569_t().toString();
        return tag.contains("AttributeModifiers") && tag.split("AttributeModifiers", 2)[1].startsWith(":[{Amount:3.5d,Slot:\"head\",AttributeName:\"minecraft:generic.armor\",Operation:0,UUID:[I;-368453572,-1112977890,-1779712266,-159547550]");
    }

    private int n_1700_B(q_1613_l item) {
        if (MinecraftAccess.c_3005_b == null || MinecraftAccess.c_3005_b.Y_259_p == null) {
            return -1;
        }
        for (int i = 0; i < 36; ++i) {
            Z_1993_T stack = MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || stack.J_1907_R() != item) continue;
            return i;
        }
        return -1;
    }

    private void n_1700_B(int fromSlot, int armorSlot) {
        if (MinecraftAccess.c_3005_b.Y_259_p == null || MinecraftAccess.c_3005_b.w_1457_N == null) {
            return;
        }
        int invFrom = fromSlot < 9 ? fromSlot + 36 : fromSlot;
        MinecraftAccess.c_3005_b.w_1457_N.windowClick(0, invFrom, 0, a_408_T.n_1700_B, MinecraftAccess.c_3005_b.Y_259_p);
        MinecraftAccess.c_3005_b.w_1457_N.windowClick(0, armorSlot, 0, a_408_T.n_1700_B, MinecraftAccess.c_3005_b.Y_259_p);
        MinecraftAccess.c_3005_b.w_1457_N.windowClick(0, invFrom, 0, a_408_T.n_1700_B, MinecraftAccess.c_3005_b.Y_259_p);
    }

    public boolean t_148_a() {
        return this.t_148_a;
    }

    public c_1514_x s_956_w() {
        return this.n_1700_B;
    }

    @Override
    public boolean P_1922_E() {
        if (this.P_1922_E.t_148_a().booleanValue()) {
            this.w_1484_f();
        }
        if (this.t_148_a) {
            this.P_4830_p();
        }
        return false;
    }

    @Override
    public void u_1723_Y() {
        this.w_1484_f = null;
        this.t_148_a = false;
        this.n_1700_B = c_1514_x.ZERO;
    }
}




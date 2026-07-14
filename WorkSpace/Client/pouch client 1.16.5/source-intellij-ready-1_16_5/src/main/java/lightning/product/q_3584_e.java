/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.base.Joiner;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.ContextAwareComponent;
import lightning.product.MutableComponent;
import lightning.product.J_2545_z;
import lightning.product.K_1178_t;
import lightning.product.L_3144_D;
import lightning.product.N_4263_v;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.Tag;
import lightning.product.Y_995_C;
import lightning.product.Coordinates;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_2336_b;
import lightning.product.h_2396_v;
import lightning.product.i_2154_H;
import lightning.product.BlockPosArgument;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class q_3584_e
extends L_3144_D
implements ContextAwareComponent {
    private static final Logger v_4262_N = LogManager.getLogger();
    protected final boolean R_4764_Y;
    protected final String G_564_y;
    @Nullable
    protected final K_1178_t.v_4262_N P_1922_E;

    @Nullable
    private static K_1178_t.v_4262_N G_564_y(String p_218672_0_) {
        try {
            return new K_1178_t().n_1700_B(new StringReader(p_218672_0_));
        }
        catch (CommandSyntaxException commandsyntaxexception) {
            return null;
        }
    }

    public q_3584_e(String p_i50781_1_, boolean p_i50781_2_) {
        this(p_i50781_1_, q_3584_e.G_564_y(p_i50781_1_), p_i50781_2_);
    }

    protected q_3584_e(String p_i50782_1_, @Nullable K_1178_t.v_4262_N p_i50782_2_, boolean p_i50782_3_) {
        this.G_564_y = p_i50782_1_;
        this.P_1922_E = p_i50782_2_;
        this.R_4764_Y = p_i50782_3_;
    }

    protected abstract Stream<U_2912_j> n_1700_B(y_2498_m var1) throws CommandSyntaxException;

    public String v_4262_N() {
        return this.G_564_y;
    }

    public boolean w_1484_f() {
        return this.R_4764_Y;
    }

    @Override
    public MutableComponent n_1700_B(@Nullable y_2498_m p_230535_1_, @Nullable N_4263_v p_230535_2_, int p_230535_3_) throws CommandSyntaxException {
        if (p_230535_1_ != null && this.P_1922_E != null) {
            Stream<String> stream = this.n_1700_B(p_230535_1_).flatMap(p_218675_1_ -> {
                try {
                    return this.P_1922_E.n_1700_B((Tag)p_218675_1_).stream();
                }
                catch (CommandSyntaxException commandsyntaxexception) {
                    return Stream.empty();
                }
            }).map(Tag::M_588_G);
            return this.R_4764_Y ? (MutableComponent)stream.flatMap(p_223137_3_ -> {
                try {
                    MutableComponent iformattabletextcomponent = x_282_a.n_1700_B.n_1700_B(p_223137_3_);
                    return Stream.of(ComponentUtils.n_1700_B(p_230535_1_, iformattabletextcomponent, p_230535_2_, p_230535_3_));
                }
                catch (Exception exception) {
                    v_4262_N.warn("Failed to parse component: " + p_223137_3_, (Throwable)exception);
                    return Stream.of(new MutableComponent[0]);
                }
            }).reduce((p_240704_0_, p_240704_1_) -> p_240704_0_.n_1700_B(", ").n_1700_B((x_282_a)p_240704_1_)).orElse(new U_2871_b("")) : new U_2871_b(Joiner.on((String)", ").join(stream.iterator()));
        }
        return new U_2871_b("");
    }

    public static class R_4764_Y
    extends q_3584_e {
        private final g_2336_b v_4262_N;

        public R_4764_Y(String p_i226087_1_, boolean p_i226087_2_, g_2336_b p_i226087_3_) {
            super(p_i226087_1_, p_i226087_2_);
            this.v_4262_N = p_i226087_3_;
        }

        public R_4764_Y(String p_i226086_1_, @Nullable K_1178_t.v_4262_N p_i226086_2_, boolean p_i226086_3_, g_2336_b p_i226086_4_) {
            super(p_i226086_1_, p_i226086_2_, p_i226086_3_);
            this.v_4262_N = p_i226086_4_;
        }

        public g_2336_b s_956_w() {
            return this.v_4262_N;
        }

        public R_4764_Y u_2550_I() {
            return new R_4764_Y(this.G_564_y, this.P_1922_E, this.R_4764_Y, this.v_4262_N);
        }

        @Override
        protected Stream<U_2912_j> n_1700_B(y_2498_m p_218673_1_) {
            U_2912_j compoundnbt = p_218673_1_.w_1457_N().l_4537_E().n_1700_B(this.v_4262_N);
            return Stream.of(compoundnbt);
        }

        @Override
        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (!(p_equals_1_ instanceof R_4764_Y)) {
                return false;
            }
            R_4764_Y nbttextcomponent$storage = (R_4764_Y)p_equals_1_;
            return Objects.equals(this.v_4262_N, nbttextcomponent$storage.v_4262_N) && Objects.equals(this.G_564_y, nbttextcomponent$storage.G_564_y) && super.equals(p_equals_1_);
        }

        @Override
        public String toString() {
            return "StorageNbtComponent{id='" + String.valueOf(this.v_4262_N) + "'path='" + this.G_564_y + "', siblings=" + String.valueOf(this.u_1723_Y) + ", style=" + String.valueOf(this.n_1700_B()) + "}";
        }

        @Override
        public /* synthetic */ L_3144_D t_148_a() {
            return this.u_2550_I();
        }

        @Override
        public /* synthetic */ MutableComponent G_564_y() {
            return this.u_2550_I();
        }
    }

    public static class J_1907_R
    extends q_3584_e {
        private final String v_4262_N;
        @Nullable
        private final Y_995_C w_1484_f;

        public J_1907_R(String p_i51292_1_, boolean p_i51292_2_, String p_i51292_3_) {
            super(p_i51292_1_, p_i51292_2_);
            this.v_4262_N = p_i51292_3_;
            this.w_1484_f = J_1907_R.G_564_y(p_i51292_3_);
        }

        @Nullable
        private static Y_995_C G_564_y(String p_218686_0_) {
            try {
                J_2545_z entityselectorparser = new J_2545_z(new StringReader(p_218686_0_));
                return entityselectorparser.w_1457_N();
            }
            catch (CommandSyntaxException commandsyntaxexception) {
                return null;
            }
        }

        private J_1907_R(String p_i51293_1_, @Nullable K_1178_t.v_4262_N p_i51293_2_, boolean p_i51293_3_, String p_i51293_4_, @Nullable Y_995_C p_i51293_5_) {
            super(p_i51293_1_, p_i51293_2_, p_i51293_3_);
            this.v_4262_N = p_i51293_4_;
            this.w_1484_f = p_i51293_5_;
        }

        public String s_956_w() {
            return this.v_4262_N;
        }

        public J_1907_R u_2550_I() {
            return new J_1907_R(this.G_564_y, this.P_1922_E, this.R_4764_Y, this.v_4262_N, this.w_1484_f);
        }

        @Override
        protected Stream<U_2912_j> n_1700_B(y_2498_m p_218673_1_) throws CommandSyntaxException {
            if (this.w_1484_f != null) {
                List<? extends N_4263_v> list = this.w_1484_f.J_1907_R(p_218673_1_);
                return list.stream().map(h_2396_v::J_1907_R);
            }
            return Stream.empty();
        }

        @Override
        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (!(p_equals_1_ instanceof J_1907_R)) {
                return false;
            }
            J_1907_R nbttextcomponent$entity = (J_1907_R)p_equals_1_;
            return Objects.equals(this.v_4262_N, nbttextcomponent$entity.v_4262_N) && Objects.equals(this.G_564_y, nbttextcomponent$entity.G_564_y) && super.equals(p_equals_1_);
        }

        @Override
        public String toString() {
            return "EntityNbtComponent{selector='" + this.v_4262_N + "'path='" + this.G_564_y + "', siblings=" + String.valueOf(this.u_1723_Y) + ", style=" + String.valueOf(this.n_1700_B()) + "}";
        }

        @Override
        public /* synthetic */ L_3144_D t_148_a() {
            return this.u_2550_I();
        }

        @Override
        public /* synthetic */ MutableComponent G_564_y() {
            return this.u_2550_I();
        }
    }

    public static class n_1700_B
    extends q_3584_e {
        private final String v_4262_N;
        @Nullable
        private final Coordinates w_1484_f;

        public n_1700_B(String p_i51294_1_, boolean p_i51294_2_, String p_i51294_3_) {
            super(p_i51294_1_, p_i51294_2_);
            this.v_4262_N = p_i51294_3_;
            this.w_1484_f = this.G_564_y(this.v_4262_N);
        }

        @Nullable
        private Coordinates G_564_y(String p_218682_1_) {
            try {
                return BlockPosArgument.n_1700_B().n_1700_B(new StringReader(p_218682_1_));
            }
            catch (CommandSyntaxException commandsyntaxexception) {
                return null;
            }
        }

        private n_1700_B(String p_i51295_1_, @Nullable K_1178_t.v_4262_N p_i51295_2_, boolean p_i51295_3_, String p_i51295_4_, @Nullable Coordinates p_i51295_5_) {
            super(p_i51295_1_, p_i51295_2_, p_i51295_3_);
            this.v_4262_N = p_i51295_4_;
            this.w_1484_f = p_i51295_5_;
        }

        @Nullable
        public String s_956_w() {
            return this.v_4262_N;
        }

        public n_1700_B u_2550_I() {
            return new n_1700_B(this.G_564_y, this.P_1922_E, this.R_4764_Y, this.v_4262_N, this.w_1484_f);
        }

        @Override
        protected Stream<U_2912_j> n_1700_B(y_2498_m p_218673_1_) {
            i_2154_H tileentity;
            c_1514_x blockpos;
            e_3591_l serverworld;
            if (this.w_1484_f != null && (serverworld = p_218673_1_.h_1847_R()).multiplayerClientSuggestionProvider(blockpos = this.w_1484_f.R_4764_Y(p_218673_1_)) && (tileentity = serverworld.getTileEntity(blockpos)) != null) {
                return Stream.of(tileentity.n_1700_B(new U_2912_j()));
            }
            return Stream.empty();
        }

        @Override
        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (!(p_equals_1_ instanceof n_1700_B)) {
                return false;
            }
            n_1700_B nbttextcomponent$block = (n_1700_B)p_equals_1_;
            return Objects.equals(this.v_4262_N, nbttextcomponent$block.v_4262_N) && Objects.equals(this.G_564_y, nbttextcomponent$block.G_564_y) && super.equals(p_equals_1_);
        }

        @Override
        public String toString() {
            return "BlockPosArgument{pos='" + this.v_4262_N + "'path='" + this.G_564_y + "', siblings=" + String.valueOf(this.u_1723_Y) + ", style=" + String.valueOf(this.n_1700_B()) + "}";
        }

        @Override
        public /* synthetic */ L_3144_D t_148_a() {
            return this.u_2550_I();
        }

        @Override
        public /* synthetic */ MutableComponent G_564_y() {
            return this.u_2550_I();
        }
    }
}



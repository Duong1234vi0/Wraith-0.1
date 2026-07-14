/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.C_2701_A;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.GuiEventListener;
import lightning.product.MutableComponent;
import lightning.product.K_1289_S;
import lightning.product.O_694_j;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.FormattedCharSequence;
import lightning.product.ContainerObjectSelectionList;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;

public class a_658_u
extends k_2603_m {
    private final Consumer<Optional<A_2352_Z>> n_1700_B;
    private R_4764_Y J_1907_R;
    private final Set<J_1907_R> R_4764_Y = Sets.newHashSet();
    private Button G_564_y;
    @Nullable
    private List<FormattedCharSequence> P_1922_E;
    private final A_2352_Z u_1723_Y;

    public a_658_u(A_2352_Z p_i232310_1_, Consumer<Optional<A_2352_Z>> p_i232310_2_) {
        super(new F_2904_S("editGamerule.title"));
        this.u_1723_Y = p_i232310_1_;
        this.n_1700_B = p_i232310_2_;
    }

    @Override
    protected void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        super.init();
        this.J_1907_R = new R_4764_Y(this.u_1723_Y);
        this.children.add(this.J_1907_R);
        this.addButton(new Button(this.width / 2 - 155 + 160, this.height - 29, 150, 20, CommonComponents.G_564_y, p_238976_1_ -> this.n_1700_B.accept(Optional.empty())));
        this.G_564_y = this.addButton(new Button(this.width / 2 - 155, this.height - 29, 150, 20, CommonComponents.R_4764_Y, p_238971_1_ -> this.n_1700_B.accept(Optional.of(this.u_1723_Y))));
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    @Override
    public void closeScreen() {
        this.n_1700_B.accept(Optional.empty());
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.P_1922_E = null;
        this.J_1907_R.render(matrixStack, mouseX, mouseY, partialTicks);
        a_658_u.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.P_1922_E != null) {
            this.renderTooltip(matrixStack, this.P_1922_E, mouseX, mouseY);
        }
    }

    private void n_1700_B(@Nullable List<FormattedCharSequence> p_238980_1_) {
        this.P_1922_E = p_238980_1_;
    }

    private void n_1700_B() {
        this.G_564_y.active = this.R_4764_Y.isEmpty();
    }

    private void n_1700_B(J_1907_R p_238972_1_) {
        this.R_4764_Y.add(p_238972_1_);
        this.n_1700_B();
    }

    private void J_1907_R(J_1907_R p_238977_1_) {
        this.R_4764_Y.remove(p_238977_1_);
        this.n_1700_B();
    }

    public class R_4764_Y
    extends ContainerObjectSelectionList<J_1907_R> {
        public R_4764_Y(final A_2352_Z p_i232316_2_) {
            super(a_658_u.this.minecraft, a_658_u.this.width, a_658_u.this.height, 43, a_658_u.this.height - 32, 24);
            final HashMap map = Maps.newHashMap();
            A_2352_Z.n_1700_B(new A_2352_Z.G_564_y(){

                @Override
                public void n_1700_B(A_2352_Z.u_1723_Y<A_2352_Z.n_1700_B> value1, A_2352_Z.v_4262_N<A_2352_Z.n_1700_B> value2) {
                    this.n_1700_B(value1, (x_282_a p_239012_1_, List<FormattedCharSequence> p_239012_2_, String p_239012_3_, T p_239012_4_) -> {
                        a_658_u a_658_u2 = a_658_u.this;
                        Objects.requireNonNull(a_658_u2);
                        return new n_1700_B(a_658_u2, p_239012_1_, p_239012_2_, p_239012_3_, (A_2352_Z.n_1700_B)p_239012_4_);
                    });
                }

                @Override
                public void J_1907_R(A_2352_Z.u_1723_Y<A_2352_Z.P_1922_E> value1, A_2352_Z.v_4262_N<A_2352_Z.P_1922_E> value2) {
                    this.n_1700_B(value1, (x_282_a p_239013_1_, List<FormattedCharSequence> p_239013_2_, String p_239013_3_, T p_239013_4_) -> {
                        a_658_u a_658_u2 = a_658_u.this;
                        Objects.requireNonNull(a_658_u2);
                        return a_658_u2.new P_1922_E(p_239013_1_, p_239013_2_, p_239013_3_, (A_2352_Z.P_1922_E)p_239013_4_);
                    });
                }

                private <T extends A_2352_Z.w_1484_f<T>> void n_1700_B(A_2352_Z.u_1723_Y<T> p_239011_1_, G_564_y<T> p_239011_2_) {
                    Object s2;
                    ImmutableList list;
                    F_2904_S itextcomponent = new F_2904_S(p_239011_1_.J_1907_R());
                    MutableComponent itextcomponent1 = new U_2871_b(p_239011_1_.n_1700_B()).n_1700_B(D_4024_W.Q_4569_t);
                    T t = p_i232316_2_.n_1700_B(p_239011_1_);
                    String s = ((A_2352_Z.w_1484_f)t).J_1907_R();
                    MutableComponent itextcomponent2 = new F_2904_S("editGamerule.default", new U_2871_b(s)).n_1700_B(D_4024_W.w_1484_f);
                    String s1 = p_239011_1_.J_1907_R() + ".description";
                    if (K_1289_S.n_1700_B(s1)) {
                        ImmutableList.Builder builder = ImmutableList.builder().add((Object)itextcomponent1.u_1723_Y());
                        F_2904_S itextcomponent3 = new F_2904_S(s1);
                        a_658_u.this.font.J_1907_R(itextcomponent3, 150).forEach(arg_0 -> ((ImmutableList.Builder)builder).add(arg_0));
                        list = builder.add((Object)itextcomponent2.u_1723_Y()).build();
                        s2 = itextcomponent3.getString() + "\n" + itextcomponent2.getString();
                    } else {
                        list = ImmutableList.of((Object)itextcomponent1.u_1723_Y(), (Object)itextcomponent2.u_1723_Y());
                        s2 = itextcomponent2.getString();
                    }
                    map.computeIfAbsent(p_239011_1_.R_4764_Y(), p_239010_0_ -> Maps.newHashMap()).put(p_239011_1_, p_239011_2_.create(itextcomponent, (List<FormattedCharSequence>)list, (String)s2, t));
                }
            });
            map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(p_239004_1_ -> {
                a_658_u a_658_u2 = a_658_u.this;
                Objects.requireNonNull(a_658_u2);
                this.addEntry(a_658_u2.new u_1723_Y(new F_2904_S(((A_2352_Z.J_1907_R)((Object)((Object)p_239004_1_.getKey()))).n_1700_B()).n_1700_B(D_4024_W.multiplayerClientSuggestionProvider, D_4024_W.Q_4569_t)));
                ((Map)p_239004_1_.getValue()).entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.comparing(A_2352_Z.u_1723_Y::n_1700_B))).forEach(p_239005_1_ -> this.addEntry((J_1907_R)p_239005_1_.getValue()));
            });
        }

        @Override
        public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
            J_1907_R editgamerulesscreen$gamerule;
            super.render(matrixStack, mouseX, mouseY, partialTicks);
            if (this.isMouseOver(mouseX, mouseY) && (editgamerulesscreen$gamerule = (J_1907_R)this.getEntryAtPosition(mouseX, mouseY)) != null) {
                a_658_u.this.n_1700_B(editgamerulesscreen$gamerule.n_1700_B);
            }
        }
    }

    public abstract class v_4262_N
    extends J_1907_R {
        private final List<FormattedCharSequence> n_1700_B;
        protected final List<GuiEventListener> J_1907_R;

        public v_4262_N(List<FormattedCharSequence> p_i241256_2_, x_282_a p_i241256_3_) {
            super(a_658_u.this, p_i241256_2_);
            this.J_1907_R = Lists.newArrayList();
            this.n_1700_B = a_658_u.this.minecraft.t_148_a.J_1907_R(p_i241256_3_, 175);
        }

        @Override
        public List<? extends GuiEventListener> getEventListeners() {
            return this.J_1907_R;
        }

        protected void n_1700_B(g_221_o p_241649_1_, int p_241649_2_, int p_241649_3_) {
            if (this.n_1700_B.size() == 1) {
                a_658_u.this.minecraft.t_148_a.J_1907_R(p_241649_1_, this.n_1700_B.get(0), (float)p_241649_3_, (float)(p_241649_2_ + 5), 0xFFFFFF);
            } else if (this.n_1700_B.size() >= 2) {
                a_658_u.this.minecraft.t_148_a.J_1907_R(p_241649_1_, this.n_1700_B.get(0), (float)p_241649_3_, (float)p_241649_2_, 0xFFFFFF);
                a_658_u.this.minecraft.t_148_a.J_1907_R(p_241649_1_, this.n_1700_B.get(1), (float)p_241649_3_, (float)(p_241649_2_ + 10), 0xFFFFFF);
            }
        }
    }

    public class u_1723_Y
    extends J_1907_R {
        private final x_282_a J_1907_R;

        public u_1723_Y(x_282_a p_i232313_2_) {
            super(a_658_u.this, null);
            this.J_1907_R = p_i232313_2_;
        }

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            C_2701_A.drawCenteredString(p_230432_1_, a_658_u.this.minecraft.t_148_a, this.J_1907_R, p_230432_4_ + p_230432_5_ / 2, p_230432_3_ + 5, 0xFFFFFF);
        }

        @Override
        public List<? extends GuiEventListener> getEventListeners() {
            return ImmutableList.of();
        }
    }

    public class P_1922_E
    extends v_4262_N {
        private final O_694_j G_564_y;

        public P_1922_E(x_282_a p_i232314_2_, List<FormattedCharSequence> p_i232314_3_, String p_i232314_4_, A_2352_Z.P_1922_E p_i232314_5_) {
            super(p_i232314_3_, p_i232314_2_);
            this.G_564_y = new O_694_j(a_658_u.this.minecraft.t_148_a, 10, 5, 42, 20, p_i232314_2_.P_1922_E().n_1700_B("\n").n_1700_B(p_i232314_4_).n_1700_B("\n"));
            this.G_564_y.setText(Integer.toString(p_i232314_5_.n_1700_B()));
            this.G_564_y.setResponder(p_238999_2_ -> {
                if (p_i232314_5_.J_1907_R((String)p_238999_2_)) {
                    this.G_564_y.setTextColor(0xE0E0E0);
                    a_658_u.this.J_1907_R(this);
                } else {
                    this.G_564_y.setTextColor(0xFF0000);
                    a_658_u.this.n_1700_B(this);
                }
            });
            this.J_1907_R.add(this.G_564_y);
        }

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            this.n_1700_B(p_230432_1_, p_230432_3_, p_230432_4_);
            this.G_564_y.x = p_230432_4_ + p_230432_5_ - 44;
            this.G_564_y.y = p_230432_3_;
            this.G_564_y.render(p_230432_1_, p_230432_7_, p_230432_8_, p_230432_10_);
        }
    }

    @FunctionalInterface
    static interface G_564_y<T extends A_2352_Z.w_1484_f<T>> {
        public J_1907_R create(x_282_a var1, List<FormattedCharSequence> var2, String var3, T var4);
    }

    public abstract class J_1907_R
    extends ContainerObjectSelectionList.n_1700_B<J_1907_R> {
        @Nullable
        private final List<FormattedCharSequence> n_1700_B;

        public J_1907_R(@Nullable a_658_u this$0, List<FormattedCharSequence> p_i232315_2_) {
            this.n_1700_B = p_i232315_2_;
        }
    }

    public class n_1700_B
    extends v_4262_N {
        private final Button n_1700_B;

        public n_1700_B(final a_658_u this$0, final x_282_a p_i232311_2_, List<FormattedCharSequence> p_i232311_3_, final String p_i232311_4_, final A_2352_Z.n_1700_B p_i232311_5_) {
            super(p_i232311_3_, p_i232311_2_);
            this.n_1700_B = new Button(this, 10, 5, 44, 20, CommonComponents.n_1700_B(p_i232311_5_.n_1700_B()), p_238988_1_ -> {
                boolean flag = !p_i232311_5_.n_1700_B();
                p_i232311_5_.n_1700_B(flag, (net.minecraft.server.G_564_y)null);
                p_238988_1_.setMessage(CommonComponents.n_1700_B(p_i232311_5_.n_1700_B()));
            }){

                @Override
                protected MutableComponent getNarrationMessage() {
                    return CommonComponents.n_1700_B(p_i232311_2_, p_i232311_5_.n_1700_B()).n_1700_B("\n").n_1700_B(p_i232311_4_);
                }
            };
            this.J_1907_R.add(this.n_1700_B);
        }

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            this.n_1700_B(p_230432_1_, p_230432_3_, p_230432_4_);
            this.n_1700_B.x = p_230432_4_ + p_230432_5_ - 45;
            this.n_1700_B.y = p_230432_3_;
            this.n_1700_B.render(p_230432_1_, p_230432_7_, p_230432_8_, p_230432_10_);
        }
    }
}



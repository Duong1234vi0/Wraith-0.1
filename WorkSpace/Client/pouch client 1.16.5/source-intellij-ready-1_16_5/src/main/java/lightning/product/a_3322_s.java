/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 *  org.apache.commons.io.IOUtils
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.BlockInput;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.M_3212_T;
import lightning.product.N_4263_v;
import lightning.product.T_1368_k;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.W_2163_m;
import lightning.product.Y_1387_d;
import lightning.product.a_2886_t;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.g_2336_b;
import lightning.product.j_2644_e;
import lightning.product.q_4099_E;
import lightning.product.r_4318_c;
import lightning.product.z_2376_a;
import org.apache.commons.io.IOUtils;

public class a_3322_s {
    public static String n_1700_B = "gameteststructures";

    public static W_2163_m n_1700_B(int p_240562_0_) {
        switch (p_240562_0_) {
            case 0: {
                return W_2163_m.n_1700_B;
            }
            case 1: {
                return W_2163_m.J_1907_R;
            }
            case 2: {
                return W_2163_m.R_4764_Y;
            }
            case 3: {
                return W_2163_m.G_564_y;
            }
        }
        throw new IllegalArgumentException("rotationSteps must be a value from 0-3. Got value " + p_240562_0_);
    }

    public static I_4817_s n_1700_B(j_2644_e p_229594_0_) {
        c_1514_x blockpos = p_229594_0_.x_607_J();
        c_1514_x blockpos1 = blockpos.add(p_229594_0_.u_2550_I().add(-1, -1, -1));
        c_1514_x blockpos2 = a_2886_t.n_1700_B(blockpos1, q_4099_E.n_1700_B, p_229594_0_.P_4830_p(), blockpos);
        return new I_4817_s(blockpos, blockpos2);
    }

    public static BoundingBox J_1907_R(j_2644_e p_240568_0_) {
        c_1514_x blockpos = p_240568_0_.x_607_J();
        c_1514_x blockpos1 = blockpos.add(p_240568_0_.u_2550_I().add(-1, -1, -1));
        c_1514_x blockpos2 = a_2886_t.n_1700_B(blockpos1, q_4099_E.n_1700_B, p_240568_0_.P_4830_p(), blockpos);
        return new BoundingBox(blockpos, blockpos2);
    }

    public static void n_1700_B(c_1514_x p_240564_0_, c_1514_x p_240564_1_, W_2163_m p_240564_2_, e_3591_l p_240564_3_) {
        c_1514_x blockpos = a_2886_t.n_1700_B(p_240564_0_.add(p_240564_1_), q_4099_E.n_1700_B, p_240564_2_, p_240564_0_);
        p_240564_3_.J_1907_R(blockpos, a_3742_W.N_260_m.multiplayerClientSuggestionProvider());
        T_1368_k commandblocktileentity = (T_1368_k)p_240564_3_.getTileEntity(blockpos);
        commandblocktileentity.P_1922_E().n_1700_B("test runthis");
        c_1514_x blockpos1 = a_2886_t.n_1700_B(blockpos.add(0, 0, -1), q_4099_E.n_1700_B, p_240564_2_, blockpos);
        p_240564_3_.J_1907_R(blockpos1, a_3742_W.o_3599_Z.multiplayerClientSuggestionProvider().n_1700_B(p_240564_2_));
    }

    public static void n_1700_B(String p_229603_0_, c_1514_x p_229603_1_, c_1514_x p_229603_2_, W_2163_m p_229603_3_, e_3591_l p_229603_4_) {
        BoundingBox mutableboundingbox = a_3322_s.n_1700_B(p_229603_1_, p_229603_2_, p_229603_3_);
        a_3322_s.n_1700_B(mutableboundingbox, p_229603_1_.getY(), p_229603_4_);
        p_229603_4_.J_1907_R(p_229603_1_, a_3742_W.l_14_c.multiplayerClientSuggestionProvider());
        j_2644_e structureblocktileentity = (j_2644_e)p_229603_4_.getTileEntity(p_229603_1_);
        structureblocktileentity.n_1700_B(false);
        structureblocktileentity.n_1700_B(new g_2336_b(p_229603_0_));
        structureblocktileentity.J_1907_R(p_229603_2_);
        structureblocktileentity.n_1700_B(M_3212_T.n_1700_B);
        structureblocktileentity.P_1922_E(true);
    }

    public static j_2644_e n_1700_B(String p_240565_0_, c_1514_x p_240565_1_, W_2163_m p_240565_2_, int p_240565_3_, e_3591_l p_240565_4_, boolean p_240565_5_) {
        c_1514_x blockpos1;
        c_1514_x blockpos = a_3322_s.n_1700_B(p_240565_0_, p_240565_4_).n_1700_B();
        BoundingBox mutableboundingbox = a_3322_s.n_1700_B(p_240565_1_, blockpos, p_240565_2_);
        if (p_240565_2_ == W_2163_m.n_1700_B) {
            blockpos1 = p_240565_1_;
        } else if (p_240565_2_ == W_2163_m.J_1907_R) {
            blockpos1 = p_240565_1_.add(blockpos.getZ() - 1, 0, 0);
        } else if (p_240565_2_ == W_2163_m.R_4764_Y) {
            blockpos1 = p_240565_1_.add(blockpos.getX() - 1, 0, blockpos.getZ() - 1);
        } else {
            if (p_240565_2_ != W_2163_m.G_564_y) {
                throw new IllegalArgumentException("Invalid rotation: " + String.valueOf((Object)p_240565_2_));
            }
            blockpos1 = p_240565_1_.add(0, 0, blockpos.getX() - 1);
        }
        a_3322_s.n_1700_B(p_240565_1_, p_240565_4_);
        a_3322_s.n_1700_B(mutableboundingbox, p_240565_1_.getY(), p_240565_4_);
        j_2644_e structureblocktileentity = a_3322_s.n_1700_B(p_240565_0_, blockpos1, p_240565_2_, p_240565_4_, p_240565_5_);
        p_240565_4_.Q_2552_b().n_1700_B(mutableboundingbox, true, false);
        p_240565_4_.n_1700_B(mutableboundingbox);
        return structureblocktileentity;
    }

    private static void n_1700_B(c_1514_x p_229608_0_, e_3591_l p_229608_1_) {
        Y_1387_d chunkpos = new Y_1387_d(p_229608_0_);
        for (int i = -1; i < 4; ++i) {
            for (int j = -1; j < 4; ++j) {
                int k = chunkpos.J_1907_R + i;
                int l = chunkpos.R_4764_Y + j;
                p_229608_1_.n_1700_B(k, l, true);
            }
        }
    }

    public static void n_1700_B(BoundingBox p_229595_0_, int p_229595_1_, e_3591_l p_229595_2_) {
        BoundingBox mutableboundingbox = new BoundingBox(p_229595_0_.n_1700_B - 2, p_229595_0_.J_1907_R - 3, p_229595_0_.R_4764_Y - 3, p_229595_0_.G_564_y + 3, p_229595_0_.P_1922_E + 20, p_229595_0_.u_1723_Y + 3);
        c_1514_x.getAllInBox(mutableboundingbox).forEach(p_229592_2_ -> a_3322_s.n_1700_B(p_229595_1_, p_229592_2_, p_229595_2_));
        p_229595_2_.Q_2552_b().n_1700_B(mutableboundingbox, true, false);
        p_229595_2_.n_1700_B(mutableboundingbox);
        I_4817_s axisalignedbb = new I_4817_s(mutableboundingbox.n_1700_B, mutableboundingbox.J_1907_R, mutableboundingbox.R_4764_Y, mutableboundingbox.G_564_y, mutableboundingbox.P_1922_E, mutableboundingbox.u_1723_Y);
        List<N_4263_v> list = p_229595_2_.n_1700_B(N_4263_v.class, axisalignedbb, p_229593_0_ -> !(p_229593_0_ instanceof a_3913_L));
        list.forEach(N_4263_v::Ops);
    }

    public static BoundingBox n_1700_B(c_1514_x p_229598_0_, c_1514_x p_229598_1_, W_2163_m p_229598_2_) {
        c_1514_x blockpos = p_229598_0_.add(p_229598_1_).add(-1, -1, -1);
        c_1514_x blockpos1 = a_2886_t.n_1700_B(blockpos, q_4099_E.n_1700_B, p_229598_2_, p_229598_0_);
        BoundingBox mutableboundingbox = BoundingBox.n_1700_B(p_229598_0_.getX(), p_229598_0_.getY(), p_229598_0_.getZ(), blockpos1.getX(), blockpos1.getY(), blockpos1.getZ());
        int i = Math.min(mutableboundingbox.n_1700_B, mutableboundingbox.G_564_y);
        int j = Math.min(mutableboundingbox.R_4764_Y, mutableboundingbox.u_1723_Y);
        c_1514_x blockpos2 = new c_1514_x(p_229598_0_.getX() - i, 0, p_229598_0_.getZ() - j);
        mutableboundingbox.n_1700_B(blockpos2);
        return mutableboundingbox;
    }

    public static Optional<c_1514_x> n_1700_B(c_1514_x p_229596_0_, int p_229596_1_, e_3591_l p_229596_2_) {
        return a_3322_s.R_4764_Y(p_229596_0_, p_229596_1_, p_229596_2_).stream().filter(p_229601_2_ -> a_3322_s.n_1700_B(p_229601_2_, p_229596_0_, p_229596_2_)).findFirst();
    }

    @Nullable
    public static c_1514_x J_1907_R(c_1514_x p_229607_0_, int p_229607_1_, e_3591_l p_229607_2_) {
        Comparator<c_1514_x> comparator = Comparator.comparingInt(p_229597_1_ -> p_229597_1_.manhattanDistance(p_229607_0_));
        Collection<c_1514_x> collection = a_3322_s.R_4764_Y(p_229607_0_, p_229607_1_, p_229607_2_);
        Optional<c_1514_x> optional = collection.stream().min(comparator);
        return optional.orElse(null);
    }

    public static Collection<c_1514_x> R_4764_Y(c_1514_x p_229609_0_, int p_229609_1_, e_3591_l p_229609_2_) {
        ArrayList collection = Lists.newArrayList();
        I_4817_s axisalignedbb = new I_4817_s(p_229609_0_);
        axisalignedbb = axisalignedbb.grow(p_229609_1_);
        for (int i = (int)axisalignedbb.minX; i <= (int)axisalignedbb.maxX; ++i) {
            for (int j = (int)axisalignedbb.minY; j <= (int)axisalignedbb.maxY; ++j) {
                for (int k = (int)axisalignedbb.minZ; k <= (int)axisalignedbb.maxZ; ++k) {
                    c_1514_x blockpos = new c_1514_x(i, j, k);
                    K_4074_S blockstate = p_229609_2_.getBlockState(blockpos);
                    if (!blockstate.n_1700_B(a_3742_W.l_14_c)) continue;
                    collection.add(blockpos);
                }
            }
        }
        return collection;
    }

    private static a_2886_t n_1700_B(String p_229605_0_, e_3591_l p_229605_1_) {
        b_2085_h templatemanager = p_229605_1_.O_508_d();
        a_2886_t template = templatemanager.J_1907_R(new g_2336_b(p_229605_0_));
        if (template != null) {
            return template;
        }
        String s = p_229605_0_ + ".snbt";
        Path path = Paths.get(n_1700_B, s);
        U_2912_j compoundnbt = a_3322_s.n_1700_B(path);
        if (compoundnbt == null) {
            throw new RuntimeException("Could not find structure file " + String.valueOf(path) + ", and the structure is not available in the world structures either.");
        }
        return templatemanager.n_1700_B(compoundnbt);
    }

    private static j_2644_e n_1700_B(String p_240566_0_, c_1514_x p_240566_1_, W_2163_m p_240566_2_, e_3591_l p_240566_3_, boolean p_240566_4_) {
        p_240566_3_.J_1907_R(p_240566_1_, a_3742_W.l_14_c.multiplayerClientSuggestionProvider());
        j_2644_e structureblocktileentity = (j_2644_e)p_240566_3_.getTileEntity(p_240566_1_);
        structureblocktileentity.n_1700_B(M_3212_T.J_1907_R);
        structureblocktileentity.n_1700_B(p_240566_2_);
        structureblocktileentity.n_1700_B(false);
        structureblocktileentity.n_1700_B(new g_2336_b(p_240566_0_));
        structureblocktileentity.n_1700_B(p_240566_3_, p_240566_4_);
        if (structureblocktileentity.u_2550_I() != c_1514_x.ZERO) {
            return structureblocktileentity;
        }
        a_2886_t template = a_3322_s.n_1700_B(p_240566_0_, p_240566_3_);
        structureblocktileentity.n_1700_B(p_240566_3_, p_240566_4_, template);
        if (structureblocktileentity.u_2550_I() == c_1514_x.ZERO) {
            throw new RuntimeException("Failed to load structure " + p_240566_0_);
        }
        return structureblocktileentity;
    }

    @Nullable
    private static U_2912_j n_1700_B(Path p_229606_0_) {
        try {
            BufferedReader bufferedreader = Files.newBufferedReader(p_229606_0_);
            String s = IOUtils.toString((Reader)bufferedreader);
            return r_4318_c.n_1700_B(s);
        }
        catch (IOException ioexception) {
            return null;
        }
        catch (CommandSyntaxException commandsyntaxexception) {
            throw new RuntimeException("Error while trying to load structure " + String.valueOf(p_229606_0_), commandsyntaxexception);
        }
    }

    private static void n_1700_B(int p_229591_0_, c_1514_x p_229591_1_, e_3591_l p_229591_2_) {
        K_4074_S blockstate = null;
        z_2376_a flatgenerationsettings = z_2376_a.n_1700_B(p_229591_2_.t_1786_h().J_1907_R(V_3137_a.PlayerInfo));
        if (flatgenerationsettings instanceof z_2376_a) {
            K_4074_S[] ablockstate = flatgenerationsettings.v_4262_N();
            if (p_229591_1_.getY() < p_229591_0_ && p_229591_1_.getY() <= ablockstate.length) {
                blockstate = ablockstate[p_229591_1_.getY() - 1];
            }
        } else if (p_229591_1_.getY() == p_229591_0_ - 1) {
            blockstate = p_229591_2_.P_1922_E(p_229591_1_).P_1922_E().P_1922_E().n_1700_B();
        } else if (p_229591_1_.getY() < p_229591_0_ - 1) {
            blockstate = p_229591_2_.P_1922_E(p_229591_1_).P_1922_E().P_1922_E().J_1907_R();
        }
        if (blockstate == null) {
            blockstate = a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
        }
        BlockInput blockstateinput = new BlockInput(blockstate, Collections.emptySet(), null);
        blockstateinput.n_1700_B(p_229591_2_, p_229591_1_, 2);
        p_229591_2_.n_1700_B(p_229591_1_, blockstate.J_1907_R());
    }

    private static boolean n_1700_B(c_1514_x p_229599_0_, c_1514_x p_229599_1_, e_3591_l p_229599_2_) {
        j_2644_e structureblocktileentity = (j_2644_e)p_229599_2_.getTileEntity(p_229599_0_);
        I_4817_s axisalignedbb = a_3322_s.n_1700_B(structureblocktileentity).grow(1.0);
        return axisalignedbb.contains(e_2866_D.n_1700_B(p_229599_1_));
    }
}



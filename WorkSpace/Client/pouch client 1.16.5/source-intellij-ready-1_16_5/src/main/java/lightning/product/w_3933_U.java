/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.BoolArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 *  org.apache.commons.io.IOUtils
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.DebugPackets;
import lightning.product.D_4024_W;
import lightning.product.BlockInput;
import lightning.product.TestFunction;
import lightning.product.BlockHitResult;
import lightning.product.MutableComponent;
import lightning.product.I_4817_s;
import lightning.product.Q_2241_p;
import lightning.product.R_4849_J;
import lightning.product.T_2915_h;
import lightning.product.U_2871_b;
import lightning.product.W_2163_m;
import lightning.product.Z_1567_W;
import lightning.product.a_3322_s;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.c_973_a;
import lightning.product.e_3591_l;
import lightning.product.g_2336_b;
import lightning.product.i_2909_p;
import lightning.product.j_2644_e;
import lightning.product.j_3341_s;
import lightning.product.GameTestTicker;
import lightning.product.GameTestListener;
import lightning.product.n_3842_i;
import lightning.product.r_1827_u;
import lightning.product.r_2127_N;
import lightning.product.r_4318_c;
import lightning.product.MultipleTestTracker;
import lightning.product.s_4514_h;
import lightning.product.u_3096_I;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;
import lightning.product.z_2963_s;
import net.minecraft.data.t_4043_B;
import org.apache.commons.io.IOUtils;

public class w_3933_U {
    public static void n_1700_B(CommandDispatcher<y_2498_m> p_229613_0_) {
        p_229613_0_.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("test").then(Q_2241_p.n_1700_B("runthis").executes(p_229647_0_ -> w_3933_U.n_1700_B((y_2498_m)p_229647_0_.getSource())))).then(Q_2241_p.n_1700_B("runthese").executes(p_229646_0_ -> w_3933_U.J_1907_R((y_2498_m)p_229646_0_.getSource())))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("runfailed").executes(p_240582_0_ -> w_3933_U.n_1700_B((y_2498_m)p_240582_0_.getSource(), false, 0, 8))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("onlyRequiredTests", BoolArgumentType.bool()).executes(p_240585_0_ -> w_3933_U.n_1700_B((y_2498_m)p_240585_0_.getSource(), BoolArgumentType.getBool((CommandContext)p_240585_0_, (String)"onlyRequiredTests"), 0, 8))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("rotationSteps", IntegerArgumentType.integer()).executes(p_240588_0_ -> w_3933_U.n_1700_B((y_2498_m)p_240588_0_.getSource(), BoolArgumentType.getBool((CommandContext)p_240588_0_, (String)"onlyRequiredTests"), IntegerArgumentType.getInteger((CommandContext)p_240588_0_, (String)"rotationSteps"), 8))).then(Q_2241_p.n_1700_B("testsPerRow", IntegerArgumentType.integer()).executes(p_240586_0_ -> w_3933_U.n_1700_B((y_2498_m)p_240586_0_.getSource(), BoolArgumentType.getBool((CommandContext)p_240586_0_, (String)"onlyRequiredTests"), IntegerArgumentType.getInteger((CommandContext)p_240586_0_, (String)"rotationSteps"), IntegerArgumentType.getInteger((CommandContext)p_240586_0_, (String)"testsPerRow")))))))).then(Q_2241_p.n_1700_B("run").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("testName", R_4849_J.n_1700_B()).executes(p_229645_0_ -> w_3933_U.n_1700_B((y_2498_m)p_229645_0_.getSource(), R_4849_J.n_1700_B((CommandContext<y_2498_m>)p_229645_0_, "testName"), 0))).then(Q_2241_p.n_1700_B("rotationSteps", IntegerArgumentType.integer()).executes(p_240584_0_ -> w_3933_U.n_1700_B((y_2498_m)p_240584_0_.getSource(), R_4849_J.n_1700_B((CommandContext<y_2498_m>)p_240584_0_, "testName"), IntegerArgumentType.getInteger((CommandContext)p_240584_0_, (String)"rotationSteps"))))))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("runall").executes(p_229644_0_ -> w_3933_U.n_1700_B((y_2498_m)p_229644_0_.getSource(), 0, 8))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("testClassName", r_2127_N.n_1700_B()).executes(p_229643_0_ -> w_3933_U.n_1700_B((y_2498_m)p_229643_0_.getSource(), r_2127_N.n_1700_B((CommandContext<y_2498_m>)p_229643_0_, "testClassName"), 0, 8))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("rotationSteps", IntegerArgumentType.integer()).executes(p_240580_0_ -> w_3933_U.n_1700_B((y_2498_m)p_240580_0_.getSource(), r_2127_N.n_1700_B((CommandContext<y_2498_m>)p_240580_0_, "testClassName"), IntegerArgumentType.getInteger((CommandContext)p_240580_0_, (String)"rotationSteps"), 8))).then(Q_2241_p.n_1700_B("testsPerRow", IntegerArgumentType.integer()).executes(p_240579_0_ -> w_3933_U.n_1700_B((y_2498_m)p_240579_0_.getSource(), r_2127_N.n_1700_B((CommandContext<y_2498_m>)p_240579_0_, "testClassName"), IntegerArgumentType.getInteger((CommandContext)p_240579_0_, (String)"rotationSteps"), IntegerArgumentType.getInteger((CommandContext)p_240579_0_, (String)"testsPerRow"))))))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("rotationSteps", IntegerArgumentType.integer()).executes(p_240569_0_ -> w_3933_U.n_1700_B((y_2498_m)p_240569_0_.getSource(), IntegerArgumentType.getInteger((CommandContext)p_240569_0_, (String)"rotationSteps"), 8))).then(Q_2241_p.n_1700_B("testsPerRow", IntegerArgumentType.integer()).executes(p_218527_0_ -> w_3933_U.n_1700_B((y_2498_m)p_218527_0_.getSource(), IntegerArgumentType.getInteger((CommandContext)p_218527_0_, (String)"rotationSteps"), IntegerArgumentType.getInteger((CommandContext)p_218527_0_, (String)"testsPerRow"))))))).then(Q_2241_p.n_1700_B("export").then(Q_2241_p.n_1700_B("testName", StringArgumentType.word()).executes(p_229642_0_ -> w_3933_U.R_4764_Y((y_2498_m)p_229642_0_.getSource(), StringArgumentType.getString((CommandContext)p_229642_0_, (String)"testName")))))).then(Q_2241_p.n_1700_B("exportthis").executes(p_240587_0_ -> w_3933_U.R_4764_Y((y_2498_m)p_240587_0_.getSource())))).then(Q_2241_p.n_1700_B("import").then(Q_2241_p.n_1700_B("testName", StringArgumentType.word()).executes(p_229641_0_ -> w_3933_U.G_564_y((y_2498_m)p_229641_0_.getSource(), StringArgumentType.getString((CommandContext)p_229641_0_, (String)"testName")))))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("pos").executes(p_229640_0_ -> w_3933_U.n_1700_B((y_2498_m)p_229640_0_.getSource(), "pos"))).then(Q_2241_p.n_1700_B("var", StringArgumentType.word()).executes(p_229639_0_ -> w_3933_U.n_1700_B((y_2498_m)p_229639_0_.getSource(), StringArgumentType.getString((CommandContext)p_229639_0_, (String)"var")))))).then(Q_2241_p.n_1700_B("create").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("testName", StringArgumentType.word()).executes(p_229637_0_ -> w_3933_U.n_1700_B((y_2498_m)p_229637_0_.getSource(), StringArgumentType.getString((CommandContext)p_229637_0_, (String)"testName"), 5, 5, 5))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("width", IntegerArgumentType.integer()).executes(p_229635_0_ -> w_3933_U.n_1700_B((y_2498_m)p_229635_0_.getSource(), StringArgumentType.getString((CommandContext)p_229635_0_, (String)"testName"), IntegerArgumentType.getInteger((CommandContext)p_229635_0_, (String)"width"), IntegerArgumentType.getInteger((CommandContext)p_229635_0_, (String)"width"), IntegerArgumentType.getInteger((CommandContext)p_229635_0_, (String)"width")))).then(Q_2241_p.n_1700_B("height", IntegerArgumentType.integer()).then(Q_2241_p.n_1700_B("depth", IntegerArgumentType.integer()).executes(p_229632_0_ -> w_3933_U.n_1700_B((y_2498_m)p_229632_0_.getSource(), StringArgumentType.getString((CommandContext)p_229632_0_, (String)"testName"), IntegerArgumentType.getInteger((CommandContext)p_229632_0_, (String)"width"), IntegerArgumentType.getInteger((CommandContext)p_229632_0_, (String)"height"), IntegerArgumentType.getInteger((CommandContext)p_229632_0_, (String)"depth"))))))))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("clearall").executes(p_229628_0_ -> w_3933_U.n_1700_B((y_2498_m)p_229628_0_.getSource(), 200))).then(Q_2241_p.n_1700_B("radius", IntegerArgumentType.integer()).executes(p_229614_0_ -> w_3933_U.n_1700_B((y_2498_m)p_229614_0_.getSource(), IntegerArgumentType.getInteger((CommandContext)p_229614_0_, (String)"radius"))))));
    }

    private static int n_1700_B(y_2498_m p_229618_0_, String p_229618_1_, int p_229618_2_, int p_229618_3_, int p_229618_4_) {
        if (p_229618_2_ <= 48 && p_229618_3_ <= 48 && p_229618_4_ <= 48) {
            e_3591_l serverworld = p_229618_0_.h_1847_R();
            c_1514_x blockpos = new c_1514_x(p_229618_0_.P_4830_p());
            c_1514_x blockpos1 = new c_1514_x(blockpos.getX(), p_229618_0_.h_1847_R().n_1700_B(z_2963_s.n_1700_B.J_1907_R, blockpos).getY(), blockpos.getZ() + 3);
            a_3322_s.n_1700_B(p_229618_1_.toLowerCase(), blockpos1, new c_1514_x(p_229618_2_, p_229618_3_, p_229618_4_), W_2163_m.n_1700_B, serverworld);
            for (int i = 0; i < p_229618_2_; ++i) {
                for (int j = 0; j < p_229618_4_; ++j) {
                    c_1514_x blockpos2 = new c_1514_x(blockpos1.getX() + i, blockpos1.getY() + 1, blockpos1.getZ() + j);
                    T_2915_h block = a_3742_W.w_1484_f;
                    BlockInput blockstateinput = new BlockInput(block.multiplayerClientSuggestionProvider(), Collections.EMPTY_SET, null);
                    blockstateinput.n_1700_B(serverworld, blockpos2, 2);
                }
            }
            a_3322_s.n_1700_B(blockpos1, new c_1514_x(1, 0, -1), W_2163_m.n_1700_B, serverworld);
            return 0;
        }
        throw new IllegalArgumentException("The structure must be less than 48 blocks big in each axis");
    }

    private static int n_1700_B(y_2498_m p_229617_0_, String p_229617_1_) throws CommandSyntaxException {
        e_3591_l serverworld;
        BlockHitResult blockraytraceresult = (BlockHitResult)p_229617_0_.t_1786_h().n_1700_B(10.0, 1.0f, false);
        c_1514_x blockpos = blockraytraceresult.n_1700_B();
        Optional<c_1514_x> optional = a_3322_s.n_1700_B(blockpos, 15, serverworld = p_229617_0_.h_1847_R());
        if (!optional.isPresent()) {
            optional = a_3322_s.n_1700_B(blockpos, 200, serverworld);
        }
        if (!optional.isPresent()) {
            p_229617_0_.n_1700_B(new U_2871_b("Can't find a structure block that contains the targeted pos " + String.valueOf(blockpos)));
            return 0;
        }
        j_2644_e structureblocktileentity = (j_2644_e)serverworld.getTileEntity(optional.get());
        c_1514_x blockpos1 = blockpos.subtract(optional.get());
        String s = blockpos1.getX() + ", " + blockpos1.getY() + ", " + blockpos1.getZ();
        String s1 = structureblocktileentity.v_4262_N();
        MutableComponent itextcomponent = new U_2871_b(s).n_1700_B(Z_1567_W.n_1700_B.n_1700_B(true).n_1700_B(D_4024_W.u_2550_I).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b("Click to copy to clipboard"))).n_1700_B(new i_2909_p(i_2909_p.n_1700_B.u_1723_Y, "final BlockPos " + p_229617_1_ + " = new BlockPos(" + s + ");")));
        p_229617_0_.n_1700_B(new U_2871_b("Position relative to " + s1 + ": ").n_1700_B(itextcomponent), false);
        DebugPackets.n_1700_B(serverworld, new c_1514_x(blockpos), s, -2147418368, 10000);
        return 1;
    }

    private static int n_1700_B(y_2498_m p_229615_0_) {
        e_3591_l serverworld;
        c_1514_x blockpos = new c_1514_x(p_229615_0_.P_4830_p());
        c_1514_x blockpos1 = a_3322_s.J_1907_R(blockpos, 15, serverworld = p_229615_0_.h_1847_R());
        if (blockpos1 == null) {
            w_3933_U.n_1700_B(serverworld, "Couldn't find any structure block within 15 radius", D_4024_W.P_4830_p);
            return 0;
        }
        n_3842_i.n_1700_B(serverworld);
        w_3933_U.n_1700_B(serverworld, blockpos1, (MultipleTestTracker)null);
        return 1;
    }

    private static int J_1907_R(y_2498_m p_229629_0_) {
        e_3591_l serverworld;
        c_1514_x blockpos = new c_1514_x(p_229629_0_.P_4830_p());
        Collection<c_1514_x> collection = a_3322_s.R_4764_Y(blockpos, 200, serverworld = p_229629_0_.h_1847_R());
        if (collection.isEmpty()) {
            w_3933_U.n_1700_B(serverworld, "Couldn't find any structure blocks within 200 block radius", D_4024_W.P_4830_p);
            return 1;
        }
        n_3842_i.n_1700_B(serverworld);
        w_3933_U.J_1907_R(p_229629_0_, "Running " + collection.size() + " tests...");
        MultipleTestTracker testresultlist = new MultipleTestTracker();
        collection.forEach(p_229626_2_ -> w_3933_U.n_1700_B(serverworld, p_229626_2_, testresultlist));
        return 1;
    }

    private static void n_1700_B(e_3591_l p_229623_0_, c_1514_x p_229623_1_, @Nullable MultipleTestTracker p_229623_2_) {
        j_2644_e structureblocktileentity = (j_2644_e)p_229623_0_.getTileEntity(p_229623_1_);
        String s = structureblocktileentity.v_4262_N();
        TestFunction testfunctioninfo = u_3096_I.P_1922_E(s);
        s_4514_h testtracker = new s_4514_h(testfunctioninfo, structureblocktileentity.P_4830_p(), p_229623_0_);
        if (p_229623_2_ != null) {
            p_229623_2_.n_1700_B(testtracker);
            testtracker.n_1700_B(new n_1700_B(p_229623_0_, p_229623_2_));
        }
        w_3933_U.n_1700_B(testfunctioninfo, p_229623_0_);
        I_4817_s axisalignedbb = a_3322_s.n_1700_B(structureblocktileentity);
        c_1514_x blockpos = new c_1514_x(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        n_3842_i.n_1700_B(testtracker, blockpos, GameTestTicker.n_1700_B);
    }

    private static void n_1700_B(e_3591_l p_229631_0_, MultipleTestTracker p_229631_1_) {
        if (p_229631_1_.v_4262_N()) {
            w_3933_U.n_1700_B(p_229631_0_, "GameTest done! " + p_229631_1_.u_1723_Y() + " tests were run", D_4024_W.M_182_A);
            if (p_229631_1_.G_564_y()) {
                w_3933_U.n_1700_B(p_229631_0_, p_229631_1_.n_1700_B() + " required tests failed :(", D_4024_W.P_4830_p);
            } else {
                w_3933_U.n_1700_B(p_229631_0_, "All required tests passed :)", D_4024_W.u_2550_I);
            }
            if (p_229631_1_.P_1922_E()) {
                w_3933_U.n_1700_B(p_229631_0_, p_229631_1_.J_1907_R() + " optional tests failed", D_4024_W.w_1484_f);
            }
        }
    }

    private static int n_1700_B(y_2498_m p_229616_0_, int p_229616_1_) {
        e_3591_l serverworld = p_229616_0_.h_1847_R();
        n_3842_i.n_1700_B(serverworld);
        c_1514_x blockpos = new c_1514_x(p_229616_0_.P_4830_p().J_1907_R, (double)p_229616_0_.h_1847_R().n_1700_B(z_2963_s.n_1700_B.J_1907_R, new c_1514_x(p_229616_0_.P_4830_p())).getY(), p_229616_0_.P_4830_p().G_564_y);
        n_3842_i.n_1700_B(serverworld, blockpos, GameTestTicker.n_1700_B, u_530_F.n_1700_B(p_229616_1_, 0, 1024));
        return 1;
    }

    private static int n_1700_B(y_2498_m p_229620_0_, TestFunction p_229620_1_, int p_229620_2_) {
        e_3591_l serverworld = p_229620_0_.h_1847_R();
        c_1514_x blockpos = new c_1514_x(p_229620_0_.P_4830_p());
        int i = p_229620_0_.h_1847_R().n_1700_B(z_2963_s.n_1700_B.J_1907_R, blockpos).getY();
        c_1514_x blockpos1 = new c_1514_x(blockpos.getX(), i, blockpos.getZ() + 3);
        n_3842_i.n_1700_B(serverworld);
        w_3933_U.n_1700_B(p_229620_1_, serverworld);
        W_2163_m rotation = a_3322_s.n_1700_B(p_229620_2_);
        s_4514_h testtracker = new s_4514_h(p_229620_1_, rotation, serverworld);
        n_3842_i.n_1700_B(testtracker, blockpos1, GameTestTicker.n_1700_B);
        return 1;
    }

    private static void n_1700_B(TestFunction p_229622_0_, e_3591_l p_229622_1_) {
        Consumer<e_3591_l> consumer = u_3096_I.R_4764_Y(p_229622_0_.P_1922_E());
        if (consumer != null) {
            consumer.accept(p_229622_1_);
        }
    }

    private static int n_1700_B(y_2498_m p_229633_0_, int p_229633_1_, int p_229633_2_) {
        n_3842_i.n_1700_B(p_229633_0_.h_1847_R());
        Collection<TestFunction> collection = u_3096_I.n_1700_B();
        w_3933_U.J_1907_R(p_229633_0_, "Running all " + collection.size() + " tests...");
        u_3096_I.G_564_y();
        w_3933_U.n_1700_B(p_229633_0_, collection, p_229633_1_, p_229633_2_);
        return 1;
    }

    private static int n_1700_B(y_2498_m p_229630_0_, String p_229630_1_, int p_229630_2_, int p_229630_3_) {
        Collection<TestFunction> collection = u_3096_I.n_1700_B(p_229630_1_);
        n_3842_i.n_1700_B(p_229630_0_.h_1847_R());
        w_3933_U.J_1907_R(p_229630_0_, "Running " + collection.size() + " tests from " + p_229630_1_ + "...");
        u_3096_I.G_564_y();
        w_3933_U.n_1700_B(p_229630_0_, collection, p_229630_2_, p_229630_3_);
        return 1;
    }

    private static int n_1700_B(y_2498_m p_240574_0_, boolean p_240574_1_, int p_240574_2_, int p_240574_3_) {
        Collection collection = p_240574_1_ ? (Collection)u_3096_I.R_4764_Y().stream().filter(TestFunction::G_564_y).collect(Collectors.toList()) : u_3096_I.R_4764_Y();
        if (collection.isEmpty()) {
            w_3933_U.J_1907_R(p_240574_0_, "No failed tests to rerun");
            return 0;
        }
        n_3842_i.n_1700_B(p_240574_0_.h_1847_R());
        w_3933_U.J_1907_R(p_240574_0_, "Rerunning " + collection.size() + " failed tests (" + (p_240574_1_ ? "only required tests" : "including optional tests") + ")");
        w_3933_U.n_1700_B(p_240574_0_, collection, p_240574_2_, p_240574_3_);
        return 1;
    }

    private static void n_1700_B(y_2498_m p_229619_0_, Collection<TestFunction> p_229619_1_, int p_229619_2_, int p_229619_3_) {
        c_1514_x blockpos = new c_1514_x(p_229619_0_.P_4830_p());
        c_1514_x blockpos1 = new c_1514_x(blockpos.getX(), p_229619_0_.h_1847_R().n_1700_B(z_2963_s.n_1700_B.J_1907_R, blockpos).getY(), blockpos.getZ() + 3);
        e_3591_l serverworld = p_229619_0_.h_1847_R();
        W_2163_m rotation = a_3322_s.n_1700_B(p_229619_2_);
        Collection<s_4514_h> collection = n_3842_i.J_1907_R(p_229619_1_, blockpos1, rotation, serverworld, GameTestTicker.n_1700_B, p_229619_3_);
        MultipleTestTracker testresultlist = new MultipleTestTracker(collection);
        testresultlist.n_1700_B(new n_1700_B(serverworld, testresultlist));
        testresultlist.n_1700_B(p_240576_0_ -> u_3096_I.n_1700_B(p_240576_0_.Q_4569_t()));
    }

    private static void J_1907_R(y_2498_m p_229634_0_, String p_229634_1_) {
        p_229634_0_.n_1700_B(new U_2871_b(p_229634_1_), false);
    }

    private static int R_4764_Y(y_2498_m p_240581_0_) {
        e_3591_l serverworld;
        c_1514_x blockpos = new c_1514_x(p_240581_0_.P_4830_p());
        c_1514_x blockpos1 = a_3322_s.J_1907_R(blockpos, 15, serverworld = p_240581_0_.h_1847_R());
        if (blockpos1 == null) {
            w_3933_U.n_1700_B(serverworld, "Couldn't find any structure block within 15 radius", D_4024_W.P_4830_p);
            return 0;
        }
        j_2644_e structureblocktileentity = (j_2644_e)serverworld.getTileEntity(blockpos1);
        String s = structureblocktileentity.v_4262_N();
        return w_3933_U.R_4764_Y(p_240581_0_, s);
    }

    private static int R_4764_Y(y_2498_m p_229636_0_, String p_229636_1_) {
        Path path = Paths.get(a_3322_s.n_1700_B, new String[0]);
        g_2336_b resourcelocation = new g_2336_b("minecraft", p_229636_1_);
        Path path1 = p_229636_0_.h_1847_R().O_508_d().n_1700_B(resourcelocation, ".nbt");
        Path path2 = t_4043_B.n_1700_B(path1, p_229636_1_, path);
        if (path2 == null) {
            w_3933_U.J_1907_R(p_229636_0_, "Failed to export " + String.valueOf(path1));
            return 1;
        }
        try {
            Files.createDirectories(path2.getParent(), new FileAttribute[0]);
        }
        catch (IOException ioexception) {
            w_3933_U.J_1907_R(p_229636_0_, "Could not create folder " + String.valueOf(path2.getParent()));
            ioexception.printStackTrace();
            return 1;
        }
        w_3933_U.J_1907_R(p_229636_0_, "Exported " + p_229636_1_ + " to " + String.valueOf(path2.toAbsolutePath()));
        return 0;
    }

    private static int G_564_y(y_2498_m p_229638_0_, String p_229638_1_) {
        Path path = Paths.get(a_3322_s.n_1700_B, p_229638_1_ + ".snbt");
        g_2336_b resourcelocation = new g_2336_b("minecraft", p_229638_1_);
        Path path1 = p_229638_0_.h_1847_R().O_508_d().n_1700_B(resourcelocation, ".nbt");
        try {
            BufferedReader bufferedreader = Files.newBufferedReader(path);
            String s = IOUtils.toString((Reader)bufferedreader);
            Files.createDirectories(path1.getParent(), new FileAttribute[0]);
            try (OutputStream outputstream = Files.newOutputStream(path1, new OpenOption[0]);){
                r_1827_u.n_1700_B(r_4318_c.n_1700_B(s), outputstream);
            }
            w_3933_U.J_1907_R(p_229638_0_, "Imported to " + String.valueOf(path1.toAbsolutePath()));
            return 0;
        }
        catch (CommandSyntaxException | IOException ioexception) {
            System.err.println("Failed to load structure " + p_229638_1_);
            ioexception.printStackTrace();
            return 1;
        }
    }

    private static void n_1700_B(e_3591_l p_229624_0_, String p_229624_1_, D_4024_W p_229624_2_) {
        p_229624_0_.n_1700_B(p_229627_0_ -> true).forEach(p_229621_2_ -> p_229621_2_.n_1700_B((x_282_a)new U_2871_b(String.valueOf((Object)p_229624_2_) + p_229624_1_), j_3341_s.J_1907_R));
    }

    static class n_1700_B
    implements GameTestListener {
        private final e_3591_l n_1700_B;
        private final MultipleTestTracker J_1907_R;

        public n_1700_B(e_3591_l p_i226073_1_, MultipleTestTracker p_i226073_2_) {
            this.n_1700_B = p_i226073_1_;
            this.J_1907_R = p_i226073_2_;
        }

        @Override
        public void n_1700_B(s_4514_h p_225644_1_) {
        }

        @Override
        public void J_1907_R(s_4514_h p_225645_1_) {
            w_3933_U.n_1700_B(this.n_1700_B, this.J_1907_R);
        }
    }
}



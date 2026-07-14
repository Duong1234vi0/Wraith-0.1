/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  joptsimple.AbstractOptionSpec
 *  joptsimple.ArgumentAcceptingOptionSpec
 *  joptsimple.OptionParser
 *  joptsimple.OptionSet
 *  joptsimple.OptionSpec
 *  joptsimple.OptionSpecBuilder
 */
package net.minecraft.data;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Collectors;
import joptsimple.AbstractOptionSpec;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import joptsimple.OptionSpecBuilder;
import net.minecraft.data.J_1907_R;
import net.minecraft.data.M_588_G;
import net.minecraft.data.Q_4569_t;
import net.minecraft.data.R_4764_Y;
import net.minecraft.data.Y_601_j;
import net.minecraft.data.Z_875_P;
import net.minecraft.data.c_3005_b;
import net.minecraft.data.e_4240_b;
import net.minecraft.data.n_1700_B;
import net.minecraft.data.n_3318_d;
import net.minecraft.data.q_4610_l;
import net.minecraft.data.t_148_a;
import net.minecraft.data.t_1786_h;
import net.minecraft.data.t_4043_B;
import net.minecraft.data.u_2550_I;
import net.minecraft.data.x_607_J;

public class Main {
    public static void main(String[] p_main_0_) throws IOException {
        OptionParser optionparser = new OptionParser();
        AbstractOptionSpec optionspec = optionparser.accepts("help", "Show the help menu").forHelp();
        OptionSpecBuilder optionspec1 = optionparser.accepts("server", "Include server generators");
        OptionSpecBuilder optionspec2 = optionparser.accepts("client", "Include client generators");
        OptionSpecBuilder optionspec3 = optionparser.accepts("dev", "Include development tools");
        OptionSpecBuilder optionspec4 = optionparser.accepts("reports", "Include data reports");
        OptionSpecBuilder optionspec5 = optionparser.accepts("validate", "Validate inputs");
        OptionSpecBuilder optionspec6 = optionparser.accepts("all", "Include all generators");
        ArgumentAcceptingOptionSpec optionspec7 = optionparser.accepts("output", "Output folder").withRequiredArg().defaultsTo((Object)"generated", (Object[])new String[0]);
        ArgumentAcceptingOptionSpec optionspec8 = optionparser.accepts("input", "Input folder").withRequiredArg();
        OptionSet optionset = optionparser.parse(p_main_0_);
        if (!optionset.has((OptionSpec)optionspec) && optionset.hasOptions()) {
            Path path = Paths.get((String)optionspec7.value(optionset), new String[0]);
            boolean flag = optionset.has((OptionSpec)optionspec6);
            boolean flag1 = flag || optionset.has((OptionSpec)optionspec2);
            boolean flag2 = flag || optionset.has((OptionSpec)optionspec1);
            boolean flag3 = flag || optionset.has((OptionSpec)optionspec3);
            boolean flag4 = flag || optionset.has((OptionSpec)optionspec4);
            boolean flag5 = flag || optionset.has((OptionSpec)optionspec5);
            Q_4569_t datagenerator = Main.n_1700_B(path, optionset.valuesOf((OptionSpec)optionspec8).stream().map(p_200263_0_ -> Paths.get(p_200263_0_, new String[0])).collect(Collectors.toList()), flag1, flag2, flag3, flag4, flag5);
            datagenerator.R_4764_Y();
        } else {
            optionparser.printHelpOn((OutputStream)System.out);
        }
    }

    public static Q_4569_t n_1700_B(Path output, Collection<Path> inputs, boolean client, boolean server, boolean dev, boolean reports, boolean validate) {
        Q_4569_t datagenerator = new Q_4569_t(output, inputs);
        if (client || server) {
            datagenerator.n_1700_B(new n_3318_d(datagenerator).n_1700_B(new q_4610_l()));
        }
        if (client) {
            datagenerator.n_1700_B(new t_148_a(datagenerator));
        }
        if (server) {
            datagenerator.n_1700_B(new Y_601_j(datagenerator));
            u_2550_I blocktagsprovider = new u_2550_I(datagenerator);
            datagenerator.n_1700_B(blocktagsprovider);
            datagenerator.n_1700_B(new Z_875_P(datagenerator, blocktagsprovider));
            datagenerator.n_1700_B(new t_1786_h(datagenerator));
            datagenerator.n_1700_B(new x_607_J(datagenerator));
            datagenerator.n_1700_B(new n_1700_B(datagenerator));
            datagenerator.n_1700_B(new c_3005_b(datagenerator));
        }
        if (dev) {
            datagenerator.n_1700_B(new t_4043_B(datagenerator));
        }
        if (reports) {
            datagenerator.n_1700_B(new R_4764_Y(datagenerator));
            datagenerator.n_1700_B(new e_4240_b(datagenerator));
            datagenerator.n_1700_B(new M_588_G(datagenerator));
            datagenerator.n_1700_B(new J_1907_R(datagenerator));
        }
        return datagenerator;
    }
}


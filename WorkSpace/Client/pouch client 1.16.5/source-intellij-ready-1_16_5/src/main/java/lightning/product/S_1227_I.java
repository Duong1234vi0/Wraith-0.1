/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Streams
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Streams;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import lightning.product.A_2352_Z;
import lightning.product.V_4604_M;
import lightning.product.j_3341_s;
import lightning.product.n_3236_c;
import lightning.product.CrashReportCategory;
import lightning.product.y_3482_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class S_1227_I
implements Runnable {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final V_4604_M J_1907_R;
    private final long R_4764_Y;

    public S_1227_I(V_4604_M server) {
        this.J_1907_R = server;
        this.R_4764_Y = server.w_728_N();
    }

    @Override
    public void run() {
        while (this.J_1907_R.Q_2552_b()) {
            long i = this.J_1907_R.j_1564_a();
            long j = j_3341_s.J_1907_R();
            long k = j - i;
            if (k > this.R_4764_Y) {
                n_1700_B.fatal("A single server tick took {} seconds (should be max {})", (Object)String.format(Locale.ROOT, "%.2f", Float.valueOf((float)k / 1000.0f)), (Object)String.format(Locale.ROOT, "%.2f", Float.valueOf(0.05f)));
                n_1700_B.fatal("Considering it to be crashed, server will forcibly shutdown.");
                ThreadMXBean threadmxbean = ManagementFactory.getThreadMXBean();
                ThreadInfo[] athreadinfo = threadmxbean.dumpAllThreads(true, true);
                StringBuilder stringbuilder = new StringBuilder();
                Error error = new Error("Watchdog");
                for (ThreadInfo threadinfo : athreadinfo) {
                    if (threadinfo.getThreadId() == this.J_1907_R.l_1233_K().getId()) {
                        error.setStackTrace(threadinfo.getStackTrace());
                    }
                    stringbuilder.append(threadinfo);
                    stringbuilder.append("\n");
                }
                n_3236_c crashreport = new n_3236_c("Watching Server", error);
                this.J_1907_R.J_1907_R(crashreport);
                CrashReportCategory crashreportcategory = crashreport.n_1700_B("Thread Dump");
                crashreportcategory.n_1700_B("Threads", stringbuilder);
                CrashReportCategory crashreportcategory1 = crashreport.n_1700_B("Performance stats");
                crashreportcategory1.n_1700_B("Random tick rate", () -> this.J_1907_R.c_132_F().s_956_w().n_1700_B(A_2352_Z.P_4830_p).toString());
                crashreportcategory1.n_1700_B("Level stats", () -> Streams.stream(this.J_1907_R.n_3318_d()).map(p_244716_0_ -> String.valueOf(p_244716_0_.g_2268_R()) + ": " + p_244716_0_.e_1992_r()).collect(Collectors.joining(",\n")));
                y_3482_a.n_1700_B("Crash report:\n" + crashreport.G_564_y());
                File file1 = new File(new File(this.J_1907_R.H_2857_Y(), "crash-reports"), "crash-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + "-server.txt");
                if (crashreport.n_1700_B(file1)) {
                    n_1700_B.error("This crash report has been saved to: {}", (Object)file1.getAbsolutePath());
                } else {
                    n_1700_B.error("We were unable to save this crash report to disk.");
                }
                this.n_1700_B();
            }
            try {
                Thread.sleep(i + this.R_4764_Y - j);
            }
            catch (InterruptedException interruptedException) {}
        }
    }

    private void n_1700_B() {
        try {
            Timer timer = new Timer();
            timer.schedule(new TimerTask(this){

                @Override
                public void run() {
                    Runtime.getRuntime().halt(1);
                }
            }, 10000L);
            System.exit(1);
        }
        catch (Throwable throwable) {
            Runtime.getRuntime().halt(1);
        }
    }
}



/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  org.apache.commons.io.IOUtils
 *  org.apache.commons.lang3.ArrayUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;
import lightning.product.SharedConstants;
import lightning.product.j_3341_s;
import lightning.product.ReportedException;
import lightning.product.CrashReportCategory;
import net.optifine.CrashReporter;
import net.optifine.reflect.Reflector;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class n_3236_c {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final String J_1907_R;
    private final Throwable R_4764_Y;
    private final CrashReportCategory G_564_y = new CrashReportCategory(this, "System Details");
    private final List<CrashReportCategory> P_1922_E = Lists.newArrayList();
    private File u_1723_Y;
    private boolean v_4262_N = true;
    private StackTraceElement[] w_1484_f = new StackTraceElement[0];
    private boolean t_148_a = false;

    public n_3236_c(String descriptionIn, Throwable causeThrowable) {
        this.J_1907_R = descriptionIn;
        this.R_4764_Y = causeThrowable;
        this.w_1484_f();
    }

    private void w_1484_f() {
        this.G_564_y.n_1700_B("Minecraft Version", () -> SharedConstants.n_1700_B().getName());
        this.G_564_y.n_1700_B("Minecraft Version ID", () -> SharedConstants.n_1700_B().getId());
        this.G_564_y.n_1700_B("Operating System", () -> System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version"));
        this.G_564_y.n_1700_B("Java Version", () -> System.getProperty("java.version") + ", " + System.getProperty("java.vendor"));
        this.G_564_y.n_1700_B("Java VM Version", () -> System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor"));
        this.G_564_y.n_1700_B("Memory", () -> {
            Runtime runtime = Runtime.getRuntime();
            long i = runtime.maxMemory();
            long j = runtime.totalMemory();
            long k = runtime.freeMemory();
            long l = i / 1024L / 1024L;
            long i1 = j / 1024L / 1024L;
            long j1 = k / 1024L / 1024L;
            return k + " bytes (" + j1 + " MB) / " + j + " bytes (" + i1 + " MB) up to " + i + " bytes (" + l + " MB)";
        });
        this.G_564_y.n_1700_B("CPUs", Runtime.getRuntime().availableProcessors());
        this.G_564_y.n_1700_B("JVM Flags", () -> {
            List list = j_3341_s.s_956_w().collect(Collectors.toList());
            return String.format("%d total; %s", list.size(), list.stream().collect(Collectors.joining(" ")));
        });
        if (Reflector.CrashReportExtender_enhanceCrashReport != null) {
            Reflector.CrashReportExtender_enhanceCrashReport.call(this, this.G_564_y);
        }
    }

    public String n_1700_B() {
        return this.J_1907_R;
    }

    public Throwable J_1907_R() {
        return this.R_4764_Y;
    }

    public void n_1700_B(StringBuilder builder) {
        if (!(this.w_1484_f != null && this.w_1484_f.length > 0 || this.P_1922_E.isEmpty())) {
            this.w_1484_f = (StackTraceElement[])ArrayUtils.subarray((Object[])this.P_1922_E.get(0).n_1700_B(), (int)0, (int)1);
        }
        if (this.w_1484_f != null && this.w_1484_f.length > 0) {
            builder.append("-- Head --\n");
            builder.append("Thread: ").append(Thread.currentThread().getName()).append("\n");
            if (Reflector.CrashReportExtender_generateEnhancedStackTraceSTE.exists()) {
                builder.append("Stacktrace:");
                builder.append(Reflector.CrashReportExtender_generateEnhancedStackTraceSTE.callString1(this.w_1484_f));
            } else {
                builder.append("Stacktrace:\n");
                for (StackTraceElement stacktraceelement : this.w_1484_f) {
                    builder.append("\t").append("at ").append(stacktraceelement);
                    builder.append("\n");
                }
                builder.append("\n");
            }
        }
        for (CrashReportCategory crashreportcategory : this.P_1922_E) {
            crashreportcategory.n_1700_B(builder);
            builder.append("\n\n");
        }
        this.G_564_y.n_1700_B(builder);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String R_4764_Y() {
        String s;
        StringWriter stringwriter = null;
        PrintWriter printwriter = null;
        Throwable throwable = this.R_4764_Y;
        if (throwable.getMessage() == null) {
            if (throwable instanceof NullPointerException) {
                throwable = new NullPointerException(this.J_1907_R);
            } else if (throwable instanceof StackOverflowError) {
                throwable = new StackOverflowError(this.J_1907_R);
            } else if (throwable instanceof OutOfMemoryError) {
                throwable = new OutOfMemoryError(this.J_1907_R);
            }
            throwable.setStackTrace(this.R_4764_Y.getStackTrace());
        }
        if (Reflector.CrashReportExtender_generateEnhancedStackTraceT.exists()) {
            return Reflector.CrashReportExtender_generateEnhancedStackTraceT.callString(throwable);
        }
        try {
            stringwriter = new StringWriter();
            printwriter = new PrintWriter(stringwriter);
            throwable.printStackTrace(printwriter);
            s = stringwriter.toString();
        }
        catch (Throwable throwable2) {
            IOUtils.closeQuietly((Writer)stringwriter);
            IOUtils.closeQuietly(printwriter);
            throw throwable2;
        }
        IOUtils.closeQuietly((Writer)stringwriter);
        IOUtils.closeQuietly((Writer)printwriter);
        return s;
    }

    public String G_564_y() {
        if (!this.t_148_a) {
            this.t_148_a = true;
            CrashReporter.onCrashReport(this, this.G_564_y);
        }
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("---- Minecraft Crash Report ----\n");
        if (Reflector.CrashReportExtender_addCrashReportHeader != null && Reflector.CrashReportExtender_addCrashReportHeader.exists()) {
            Reflector.CrashReportExtender_addCrashReportHeader.call(stringbuilder, this);
        }
        stringbuilder.append("// ");
        stringbuilder.append(n_3236_c.t_148_a());
        stringbuilder.append("\n\n");
        stringbuilder.append("Time: ");
        stringbuilder.append(new SimpleDateFormat().format(new Date()));
        stringbuilder.append("\n");
        stringbuilder.append("Description: ");
        stringbuilder.append(this.J_1907_R);
        stringbuilder.append("\n\n");
        stringbuilder.append(this.R_4764_Y());
        stringbuilder.append("\n\nA detailed walkthrough of the error, its code path and all known details is as follows:\n");
        for (int i = 0; i < 87; ++i) {
            stringbuilder.append("-");
        }
        stringbuilder.append("\n\n");
        this.n_1700_B(stringbuilder);
        return stringbuilder.toString();
    }

    public File P_1922_E() {
        return this.u_1723_Y;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean n_1700_B(File toFile) {
        boolean bl;
        if (this.u_1723_Y != null) {
            return false;
        }
        if (toFile.getParentFile() != null) {
            toFile.getParentFile().mkdirs();
        }
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter((OutputStream)new FileOutputStream(toFile), StandardCharsets.UTF_8);
            writer.write(this.G_564_y());
            this.u_1723_Y = toFile;
            bl = true;
        }
        catch (Throwable throwable) {
            boolean flag;
            try {
                n_1700_B.error("Could not save crash report to {}", (Object)toFile, (Object)throwable);
                flag = false;
            }
            catch (Throwable throwable2) {
                IOUtils.closeQuietly(writer);
                throw throwable2;
            }
            IOUtils.closeQuietly((Writer)writer);
            return flag;
        }
        IOUtils.closeQuietly((Writer)writer);
        return bl;
    }

    public CrashReportCategory u_1723_Y() {
        return this.G_564_y;
    }

    public CrashReportCategory n_1700_B(String name) {
        return this.n_1700_B(name, 1);
    }

    public CrashReportCategory n_1700_B(String categoryName, int stacktraceLength) {
        CrashReportCategory crashreportcategory = new CrashReportCategory(this, categoryName);
        try {
            if (this.v_4262_N) {
                int i = crashreportcategory.n_1700_B(stacktraceLength);
                StackTraceElement[] astacktraceelement = this.R_4764_Y.getStackTrace();
                StackTraceElement stacktraceelement = null;
                StackTraceElement stacktraceelement1 = null;
                int j = astacktraceelement.length - i;
                if (astacktraceelement != null && 0 <= j && j < astacktraceelement.length) {
                    stacktraceelement = astacktraceelement[j];
                    if (astacktraceelement.length + 1 - i < astacktraceelement.length) {
                        stacktraceelement1 = astacktraceelement[astacktraceelement.length + 1 - i];
                    }
                }
                this.v_4262_N = crashreportcategory.n_1700_B(stacktraceelement, stacktraceelement1);
                if (i > 0 && !this.P_1922_E.isEmpty()) {
                    CrashReportCategory crashreportcategory1 = this.P_1922_E.get(this.P_1922_E.size() - 1);
                    crashreportcategory1.J_1907_R(i);
                } else if (astacktraceelement != null && astacktraceelement.length >= i && 0 <= j && j < astacktraceelement.length) {
                    this.w_1484_f = new StackTraceElement[j];
                    System.arraycopy(astacktraceelement, 0, this.w_1484_f, 0, this.w_1484_f.length);
                } else {
                    this.v_4262_N = false;
                }
            }
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        this.P_1922_E.add(crashreportcategory);
        return crashreportcategory;
    }

    private static String t_148_a() {
        String[] astring = new String[]{"Who set us up the TNT?", "Everything's going to plan. No, really, that was supposed to happen.", "Uh... Did I do that?", "Oops.", "Why did you do that?", "I feel sad now :(", "My bad.", "I'm sorry, Dave.", "I let you down. Sorry :(", "On the bright side, I bought you a teddy bear!", "Daisy, daisy...", "Oh - I know what I did wrong!", "Hey, that tickles! Hehehe!", "I blame Dinnerbone.", "You should try our sister game, Minceraft!", "Don't be sad. I'll do better next time, I promise!", "Don't be sad, have a hug! <3", "I just don't know what went wrong :(", "Shall we play a game?", "Quite honestly, I wouldn't worry myself about that.", "I bet Cylons wouldn't have this problem.", "Sorry :(", "Surprise! Haha. Well, this is awkward.", "Would you like a cupcake?", "Hi. I'm Minecraft, and I'm a crashaholic.", "Ooh. Shiny.", "This doesn't make any sense!", "Why is it breaking :(", "Don't do that.", "Ouch. That hurt :(", "You're mean.", "This is a token for 1 free hug. Redeem at your nearest Mojangsta: [~~HUG~~]", "There are four lights!", "But it works on my machine."};
        try {
            return astring[(int)(j_3341_s.R_4764_Y() % (long)astring.length)];
        }
        catch (Throwable throwable) {
            return "Witty comment unavailable :(";
        }
    }

    public static n_3236_c n_1700_B(Throwable causeIn, String descriptionIn) {
        while (causeIn instanceof CompletionException && causeIn.getCause() != null) {
            causeIn = causeIn.getCause();
        }
        n_3236_c crashreport = causeIn instanceof ReportedException ? ((ReportedException)causeIn).n_1700_B() : new n_3236_c(descriptionIn, causeIn);
        return crashreport;
    }

    public static void v_4262_N() {
        new n_3236_c("Don't panic!", new Throwable()).G_564_y();
    }
}



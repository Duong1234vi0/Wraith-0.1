/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.util.HashMap;
import lightning.product.V_4423_d;
import lightning.product.n_3236_c;
import lightning.product.CrashReportCategory;
import net.optifine.Config;
import net.optifine.http.FileUploadThread;
import net.optifine.http.IFileUploadListener;
import net.optifine.shaders.Shaders;

public class CrashReporter {
    public static void onCrashReport(n_3236_c crashReport, CrashReportCategory category) {
        try {
            Throwable throwable = crashReport.J_1907_R();
            if (throwable == null) {
                return;
            }
            if (throwable.getClass().getName().contains(".fml.client.SplashProgress")) {
                return;
            }
            if (throwable.getClass() == Throwable.class) {
                return;
            }
            CrashReporter.extendCrashReport(category);
            V_4423_d gamesettings = Config.getGameSettings();
            if (gamesettings == null) {
                return;
            }
            if (!gamesettings.Z_976_R) {
                return;
            }
            String s = "http://optifine.net/crashReport";
            String s1 = CrashReporter.makeReport(crashReport);
            byte[] abyte = s1.getBytes("ASCII");
            IFileUploadListener ifileuploadlistener = new IFileUploadListener(){

                @Override
                public void fileUploadFinished(String url, byte[] content, Throwable exception) {
                }
            };
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("OF-Version", Config.getVersion());
            map.put("OF-Summary", CrashReporter.makeSummary(crashReport));
            FileUploadThread fileuploadthread = new FileUploadThread(s, map, abyte, ifileuploadlistener);
            fileuploadthread.setPriority(10);
            fileuploadthread.start();
            Thread.sleep(1000L);
        }
        catch (Exception exception) {
            Config.dbg(exception.getClass().getName() + ": " + exception.getMessage());
        }
    }

    private static String makeReport(n_3236_c crashReport) {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("OptiFineVersion: " + Config.getVersion() + "\n");
        stringbuffer.append("Summary: " + CrashReporter.makeSummary(crashReport) + "\n");
        stringbuffer.append("\n");
        stringbuffer.append(crashReport.G_564_y());
        stringbuffer.append("\n");
        return stringbuffer.toString();
    }

    private static String makeSummary(n_3236_c crashReport) {
        Throwable throwable = crashReport.J_1907_R();
        if (throwable == null) {
            return "Unknown";
        }
        StackTraceElement[] astacktraceelement = throwable.getStackTrace();
        String s = "unknown";
        if (astacktraceelement.length > 0) {
            s = astacktraceelement[0].toString().trim();
        }
        return throwable.getClass().getName() + ": " + throwable.getMessage() + " (" + crashReport.n_1700_B() + ") [" + s + "]";
    }

    public static void extendCrashReport(CrashReportCategory cat) {
        cat.n_1700_B("OptiFine Version", Config.getVersion());
        cat.n_1700_B("OptiFine Build", Config.getBuild());
        if (Config.getGameSettings() != null) {
            cat.n_1700_B("Render Distance Chunks", "" + Config.getChunkViewDistance());
            cat.n_1700_B("Mipmaps", "" + Config.getMipmapLevels());
            cat.n_1700_B("Anisotropic Filtering", "" + Config.getAnisotropicFilterLevel());
            cat.n_1700_B("Antialiasing", "" + Config.getAntialiasingLevel());
            cat.n_1700_B("Multitexture", "" + Config.isMultiTexture());
        }
        cat.n_1700_B("Shaders", Shaders.getShaderPackName());
        cat.n_1700_B("OpenGlVersion", Config.openGlVersion);
        cat.n_1700_B("OpenGlRenderer", Config.openGlRenderer);
        cat.n_1700_B("OpenGlVendor", Config.openGlVendor);
        cat.n_1700_B("CpuCount", "" + Config.getAvailableProcessors());
    }
}



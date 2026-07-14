/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.io;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public final class IoUtils {
    static String[] splitOnce(String s, char c) {
        int i = s.lastIndexOf(c);
        if (i < 0) {
            return new String[]{s};
        }
        return new String[]{s.substring(0, i), s.substring(i + 1, s.length())};
    }

    public static String tempConfigFileName(Path originalFile) {
        String filename = originalFile.toString();
        String[] parts = IoUtils.splitOnce(filename, '.');
        if (parts.length == 1) {
            return filename + ".new.tmp";
        }
        return parts[0] + ".new.tmp." + parts[1];
    }

    public static void retryIfAccessDenied(String name, IoRunnable r) throws IOException {
        IoUtils.retryIfAccessDenied(name, r, OptionHolder.RETRY_MAX_TIMES, OptionHolder.RETRY_DELAY_MILLIS, TimeUnit.MILLISECONDS);
    }

    public static void retryIfAccessDenied(String name, IoRunnable r, int maxRetries, long retryDelay, TimeUnit delayUnit) throws IOException {
        AccessDeniedException lastException = null;
        for (int i = 0; i <= maxRetries; ++i) {
            try {
                r.run();
                return;
            }
            catch (AccessDeniedException ex) {
                lastException = ex;
                try {
                    Thread.sleep(delayUnit.toMillis(retryDelay));
                }
                catch (InterruptedException interruptedException) {}
                continue;
            }
            catch (IOException ex) {
                throw ex;
            }
        }
        String msg = String.format("IO operation '%s' failed after %s attempts", name, maxRetries);
        throw new RetryFailedException(msg, lastException);
    }

    static class OptionHolder {
        static final long RETRY_DELAY_MILLIS;
        static final int RETRY_MAX_TIMES;

        OptionHolder() {
        }

        static {
            int times;
            long delay;
            boolean isWindows = System.getProperty("os.name", "?").trim().toLowerCase().startsWith("windows");
            String delayProps = System.getProperty("nightconfig.accessDeniedRetryDelayMillis", "?");
            try {
                delay = Long.parseLong(delayProps);
            }
            catch (NumberFormatException ex) {
                delay = 500L;
            }
            String timesProps = System.getProperty("nightconfig.accessDeniedRetryMaxTimes", "?");
            try {
                times = Integer.parseInt(timesProps);
            }
            catch (NumberFormatException ex) {
                times = isWindows ? 3 : 1;
            }
            RETRY_DELAY_MILLIS = delay;
            RETRY_MAX_TIMES = times;
        }
    }

    @FunctionalInterface
    public static interface IoRunnable {
        public void run() throws IOException;
    }

    public static final class RetryFailedException
    extends IOException {
        RetryFailedException(String msg, IOException cause) {
            super(msg, cause);
        }
    }
}


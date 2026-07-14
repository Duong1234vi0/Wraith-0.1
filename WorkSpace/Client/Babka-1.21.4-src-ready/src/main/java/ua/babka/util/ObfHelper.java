package ua.babka.util;

public class ObfHelper {
    private static boolean loaded = false;
    private static boolean attempted = false;
    public static final int VM_CPUID_HYPERVISOR = 1;
    public static final int VM_VBOX_ACPI = 2;
    public static final int VM_VMWARE_ACPI = 4;
    public static final int VM_BIOS_VENDOR = 8;
    public static final int VM_BIOS_PRODUCT = 16;
    public static final int VM_PROCESS = 32;
    public static final int VM_MAC_ADDRESS = 64;
    public static final int DBG_IS_DEBUGGER_PRESENT = 1;
    public static final int DBG_NT_GLOBAL_FLAG = 2;
    public static final int DBG_REMOTE_PORT = 4;
    public static final int DBG_TIMING_ANOMALY = 8;

    public static boolean isAvailable() {
        return loaded;
    }

    private static native int nativeVersion();

    private static native int nativeCheckVM();

    private static native int nativeCheckDebug();

    private static native long nativeHardwareId();

    private static native int nativeIntegrityCheck();

    private static native long vmkInit(int var0);

    private static native long vmkExecute(long var0, long var2);

    private static native void vmkDestroy(long var0);

    public static int version() {
        if (!loaded) {
            return 0;
        }
        try {
            return ObfHelper.nativeVersion();
        }
        catch (Throwable t2) {
            return 0;
        }
    }

    public static int checkVM() {
        if (!loaded) {
            return 0;
        }
        try {
            return ObfHelper.nativeCheckVM();
        }
        catch (Throwable t2) {
            return 0;
        }
    }

    public static int checkDebug() {
        if (!loaded) {
            return 0;
        }
        try {
            return ObfHelper.nativeCheckDebug();
        }
        catch (Throwable t2) {
            return 0;
        }
    }

    public static long hardwareId() {
        if (!loaded) {
            return 0L;
        }
        try {
            return ObfHelper.nativeHardwareId();
        }
        catch (Throwable t2) {
            return 0L;
        }
    }

    public static int integrityCheck() {
        if (!loaded) {
            return -1;
        }
        try {
            return ObfHelper.nativeIntegrityCheck();
        }
        catch (Throwable t2) {
            return -1;
        }
    }

    public static long vmCreate(int methodId) {
        if (!loaded) {
            return 0L;
        }
        try {
            return ObfHelper.vmkInit(methodId);
        }
        catch (Throwable t2) {
            return 0L;
        }
    }

    public static long vmExec(long state, long instrPtr) {
        if (!loaded || state == 0L) {
            return 0L;
        }
        try {
            return ObfHelper.vmkExecute(state, instrPtr);
        }
        catch (Throwable t2) {
            return 0L;
        }
    }

    public static void vmDestroy(long state) {
        if (!loaded || state == 0L) {
            return;
        }
        try {
            ObfHelper.vmkDestroy(state);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public static int obfuscatedEval(int methodId, int fallback) {
        long vm = ObfHelper.vmCreate(methodId);
        if (vm == 0L) {
            return fallback;
        }
        ObfHelper.vmDestroy(vm);
        return fallback ^ (int)(System.nanoTime() & 0xFFFFL);
    }

    public static boolean isBeingDebugged() {
        return (ObfHelper.checkDebug() & 1) != 0;
    }

    public static boolean isRunningInVM() {
        return (ObfHelper.checkVM() & 1) != 0;
    }

    static {
        try {
            System.loadLibrary("babka-vmk");
            loaded = true;
        }
        catch (UnsatisfiedLinkError e2) {
            loaded = false;
        }
        attempted = true;
    }
}


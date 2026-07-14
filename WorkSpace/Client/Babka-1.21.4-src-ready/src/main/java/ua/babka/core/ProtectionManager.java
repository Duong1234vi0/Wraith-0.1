package ua.babka.core;

import ua.babka.util.ObfHelper;

public class ProtectionManager {
    private static int vmFlags = 0;
    private static int dbgFlags = 0;
    private static long hwId = 0L;
    private static int integrity = -1;
    private static boolean ready = false;

    private static void log(String msg) {
        System.out.println("[Guard] " + msg);
    }

    public static void init() {
        if (ready) {
            return;
        }
        ProtectionManager.log("Initialising native protection layer...");
        if (!ObfHelper.isAvailable()) {
            ProtectionManager.log("Native library not loaded — protection runs in pure-Java mode");
            ready = true;
            return;
        }
        ProtectionManager.log("Native VMK version: 0x" + Integer.toHexString(ObfHelper.version()));
        try {
            vmFlags = ObfHelper.checkVM();
            ProtectionManager.log("Anti-VM flags: " + vmFlags + " " + ProtectionManager.describeVmFlags(vmFlags));
        }
        catch (Exception e2) {
            ProtectionManager.log("Anti-VM check failed: " + e2.getMessage());
        }
        try {
            dbgFlags = ObfHelper.checkDebug();
            ProtectionManager.log("Anti-debug flags: " + dbgFlags + " " + ProtectionManager.describeDbgFlags(dbgFlags));
        }
        catch (Exception e3) {
            ProtectionManager.log("Anti-debug check failed: " + e3.getMessage());
        }
        try {
            hwId = ObfHelper.hardwareId();
            ProtectionManager.log("Hardware fingerprint: 0x" + Long.toHexString(hwId));
        }
        catch (Exception e4) {
            ProtectionManager.log("Hardware ID check failed: " + e4.getMessage());
        }
        try {
            integrity = ObfHelper.integrityCheck();
            ProtectionManager.log("Native integrity CRC: 0x" + Integer.toHexString(integrity));
        }
        catch (Exception e5) {
            ProtectionManager.log("Integrity check failed: " + e5.getMessage());
        }
        ready = true;
        ProtectionManager.log("Protection layer ready");
    }

    public static boolean isReady() {
        return ready;
    }

    public static boolean isNativeAvailable() {
        return ObfHelper.isAvailable();
    }

    public static boolean isSuspiciousEnvironment() {
        return vmFlags != 0 || dbgFlags != 0;
    }

    public static int getVmFlags() {
        return vmFlags;
    }

    public static int getDbgFlags() {
        return dbgFlags;
    }

    public static long getHardwareId() {
        return hwId;
    }

    public static int getIntegrityCrc() {
        return integrity;
    }

    private static String describeVmFlags(int flags) {
        StringBuilder sb = new StringBuilder();
        if ((flags & 1) != 0) {
            sb.append("[CPUID_HYPERVISOR]");
        }
        if ((flags & 2) != 0) {
            sb.append("[VBOX_ACPI]");
        }
        if ((flags & 4) != 0) {
            sb.append("[VMWARE_ACPI]");
        }
        if ((flags & 8) != 0) {
            sb.append("[BIOS_VENDOR]");
        }
        if ((flags & 0x10) != 0) {
            sb.append("[BIOS_PRODUCT]");
        }
        if ((flags & 0x20) != 0) {
            sb.append("[VM_PROCESS]");
        }
        if ((flags & 0x40) != 0) {
            sb.append("[VM_MAC]");
        }
        return sb.length() > 0 ? sb.toString() : "clean";
    }

    private static String describeDbgFlags(int flags) {
        StringBuilder sb = new StringBuilder();
        if ((flags & 1) != 0) {
            sb.append("[IsDebuggerPresent]");
        }
        if ((flags & 2) != 0) {
            sb.append("[NtGlobalFlag]");
        }
        if ((flags & 4) != 0) {
            sb.append("[RemoteDebugger]");
        }
        if ((flags & 8) != 0) {
            sb.append("[TimingAnomaly]");
        }
        return sb.length() > 0 ? sb.toString() : "clean";
    }
}


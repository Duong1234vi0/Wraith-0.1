package mods.viaversion.vialoadingbase;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;

public class ViaLoadingBase {
    private static final ViaLoadingBase INSTANCE = new ViaLoadingBase();
    private ProtocolVersion targetVersion = ProtocolVersion.getProtocol(754);

    public static ViaLoadingBase getInstance() {
        return INSTANCE;
    }

    public ProtocolVersion getTargetVersion() {
        return this.targetVersion;
    }

    public void setTargetVersion(ProtocolVersion targetVersion) {
        this.targetVersion = targetVersion == null ? ProtocolVersion.getProtocol(754) : targetVersion;
    }
}

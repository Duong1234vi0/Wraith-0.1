/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders;

import lightning.product.P_3504_Q;
import lightning.product.z_3539_x;
import net.optifine.shaders.ProgramStage;

public class ComputeProgram {
    private final String name;
    private final ProgramStage programStage;
    private int id;
    private int ref;
    private z_3539_x localSize;
    private z_3539_x workGroups;
    private P_3504_Q workGroupsRender;
    private int compositeMipmapSetting;

    public ComputeProgram(String name, ProgramStage programStage) {
        this.name = name;
        this.programStage = programStage;
    }

    public void resetProperties() {
    }

    public void resetId() {
        this.id = 0;
        this.ref = 0;
    }

    public void resetConfiguration() {
        this.localSize = null;
        this.workGroups = null;
        this.workGroupsRender = null;
    }

    public String getName() {
        return this.name;
    }

    public ProgramStage getProgramStage() {
        return this.programStage;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRef() {
        return this.ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public z_3539_x getLocalSize() {
        return this.localSize;
    }

    public void setLocalSize(z_3539_x localSize) {
        this.localSize = localSize;
    }

    public z_3539_x getWorkGroups() {
        return this.workGroups;
    }

    public void setWorkGroups(z_3539_x workGroups) {
        this.workGroups = workGroups;
    }

    public P_3504_Q getWorkGroupsRender() {
        return this.workGroupsRender;
    }

    public void setWorkGroupsRender(P_3504_Q workGroupsRender) {
        this.workGroupsRender = workGroupsRender;
    }

    public int getCompositeMipmapSetting() {
        return this.compositeMipmapSetting;
    }

    public void setCompositeMipmapSetting(int compositeMipmapSetting) {
        this.compositeMipmapSetting = compositeMipmapSetting;
    }

    public boolean hasCompositeMipmaps() {
        return this.compositeMipmapSetting != 0;
    }

    public String toString() {
        return "name: " + this.name + ", id: " + this.id;
    }
}


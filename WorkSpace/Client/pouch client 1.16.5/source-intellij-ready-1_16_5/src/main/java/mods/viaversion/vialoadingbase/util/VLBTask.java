/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.platform.PlatformTask
 *  com.viaversion.viaversion.api.scheduler.Task
 *  com.viaversion.viaversion.api.scheduler.TaskStatus
 */
package mods.viaversion.vialoadingbase.util;

import com.viaversion.viaversion.api.platform.PlatformTask;
import com.viaversion.viaversion.api.scheduler.Task;
import com.viaversion.viaversion.api.scheduler.TaskStatus;

public class VLBTask
implements PlatformTask<Task> {
    private final Task object;

    public VLBTask(Task object) {
        this.object = object;
    }

    @Deprecated
    public Task getObject() {
        return this.object;
    }

    public void cancel() {
        this.object.cancel();
    }

    @Deprecated
    public TaskStatus getStatus() {
        return this.getObject().status();
    }
}


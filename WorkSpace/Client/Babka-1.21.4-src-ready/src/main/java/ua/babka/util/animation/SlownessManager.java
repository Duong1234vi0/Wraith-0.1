package ua.babka.util.animation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import ua.babka.util.IMinecraft;

public final class SlownessManager
implements IMinecraft {
    private static final KeyBinding[] keybindsList = new KeyBinding[]{SlownessManager.mc.options.forwardKey, SlownessManager.mc.options.leftKey, SlownessManager.mc.options.rightKey, SlownessManager.mc.options.backKey, SlownessManager.mc.options.jumpKey, SlownessManager.mc.options.sprintKey, SlownessManager.mc.options.sneakKey};
    private static final List<SlowTask> tasks = new ArrayList<SlowTask>();
    private static final List<TimeTask> timeTasks = new ArrayList<TimeTask>();
    private static SlowTask lastTask = null;

    public static void addTask(SlowTask task) {
        for (SlowTask existing : tasks) {
            if (existing.runnable == null || task.runnable == null || !existing.runnable.equals(task.runnable)) continue;
            return;
        }
        lastTask = task;
        tasks.add(task);
    }

    public static void addTimeTask(TimeTask task) {
        timeTasks.add(task);
    }

    public static void updateSlowTasks() {
        long now = System.currentTimeMillis();
        if (lastTask != null) {
            if (now - SlownessManager.lastTask.time < SlownessManager.lastTask.duration) {
                for (KeyBinding key : new KeyBinding[]{SlownessManager.mc.options.forwardKey, SlownessManager.mc.options.leftKey, SlownessManager.mc.options.rightKey, SlownessManager.mc.options.backKey, SlownessManager.mc.options.jumpKey, SlownessManager.mc.options.sprintKey}) {
                    key.setPressed(false);
                }
            } else {
                for (KeyBinding key : keybindsList) {
                    if (SlownessManager.mc.currentScreen != null) continue;
                    key.setPressed(InputUtil.isKeyPressed((long)mc.getWindow().getHandle(), (int)key.getDefaultKey().getCode()));
                }
            }
        }
        ArrayList<SlowTask> copy = new ArrayList<SlowTask>(tasks);
        for (SlowTask task : copy) {
            if (now - task.time <= task.duration - task.reflection || task.runnable == null) continue;
            task.runnable.run();
            task.runnable = null;
        }
        Iterator<SlowTask> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            SlowTask task = iterator.next();
            if (now - task.time <= task.duration && task.condition) continue;
            iterator.remove();
        }
    }

    public static void updateTimeTasks(boolean ticksUpdate) {
        long now = System.currentTimeMillis();
        for (TimeTask task : new ArrayList<TimeTask>(timeTasks)) {
            if (ticksUpdate) {
                ++task.ticksExisted;
            }
            if (!(task.mode == TimeTask.Mode.TICKS ? task.ticksExisted >= task.time : now - task.time > task.duration && task.runnable != null)) continue;
            task.runnable.run();
            task.runnable = null;
        }
        Iterator<TimeTask> iterator = timeTasks.iterator();
        while (iterator.hasNext()) {
            TimeTask task;
            task = iterator.next();
            if (!(task.mode != TimeTask.Mode.TICKS ? now - task.time > task.duration : task.ticksExisted >= task.time) && task.condition) continue;
            iterator.remove();
        }
    }

    public static boolean slowTasksIsEmpty() {
        return tasks.isEmpty();
    }

    public static boolean timeTasksIsEmpty() {
        return timeTasks.isEmpty();
    }

    private SlownessManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static class SlowTask {
        public long duration;
        public long time;
        public long reflection;
        public Runnable runnable;
        public boolean condition;

        public SlowTask(long duration, long reflection, Runnable runnable) {
            this.duration = duration;
            this.reflection = reflection;
            this.runnable = runnable;
            this.time = System.currentTimeMillis();
            this.condition = true;
        }

        public SlowTask(long duration, long reflection, Runnable runnable, boolean condition) {
            this.duration = duration;
            this.reflection = reflection;
            this.runnable = runnable;
            this.time = System.currentTimeMillis();
            this.condition = condition;
        }

        public SlowTask(long duration, Runnable runnable) {
            this.duration = duration;
            this.reflection = 20L;
            this.runnable = runnable;
            this.time = System.currentTimeMillis();
            this.condition = true;
        }

        public SlowTask(long duration, Runnable runnable, boolean condition) {
            this.duration = duration;
            this.reflection = 20L;
            this.runnable = runnable;
            this.time = System.currentTimeMillis();
            this.condition = condition;
        }
    }

    public static class TimeTask {
        public long duration;
        public long time;
        public long ticksExisted;
        public Runnable runnable;
        public boolean condition;
        public Mode mode;

        public TimeTask(long duration, Runnable runnable, boolean condition) {
            this.mode = Mode.TIME;
            this.duration = duration;
            this.runnable = runnable;
            this.time = System.currentTimeMillis();
            this.condition = condition;
        }

        public TimeTask(Mode mode, int ticks, Runnable runnable, boolean condition) {
            this.mode = mode;
            this.runnable = runnable;
            this.time = ticks;
            this.condition = condition;
        }

        public static enum Mode {
            TIME,
            TICKS;

        }
    }
}


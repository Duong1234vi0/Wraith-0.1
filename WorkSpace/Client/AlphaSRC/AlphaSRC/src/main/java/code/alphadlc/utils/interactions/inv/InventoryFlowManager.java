/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_304
 *  net.minecraft.class_3675
 *  net.minecraft.class_463
 *  net.minecraft.class_471
 *  net.minecraft.class_476
 *  net.minecraft.class_497
 *  net.minecraft.class_498
 */
package code.alphadlc.utils.interactions.inv;

import code.alphadlc.events.player.InputEvent;
import code.alphadlc.features.impl.movement.GuiMove;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.warp.TurnsConfig;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.interactions.simulate.Simulations;
import code.alphadlc.utils.math.script.Script;
import code.alphadlc.utils.math.task.TaskPriority;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.class_304;
import net.minecraft.class_3675;
import net.minecraft.class_463;
import net.minecraft.class_471;
import net.minecraft.class_476;
import net.minecraft.class_497;
import net.minecraft.class_498;

public final class InventoryFlowManager
implements QuickImports {
    public static final List<class_304> moveKeys = List.of(InventoryFlowManager.mc.field_1690.field_1894, InventoryFlowManager.mc.field_1690.field_1881, InventoryFlowManager.mc.field_1690.field_1913, InventoryFlowManager.mc.field_1690.field_1849, InventoryFlowManager.mc.field_1690.field_1903);
    public static final Script script = new Script();
    public static final Script postScript = new Script();
    private static final Queue<Runnable> taskQueue = new ConcurrentLinkedQueue<Runnable>();
    public static boolean canMove = true;

    private static void log(String message) {
    }

    public static void tick() {
        Runnable nextTask;
        script.update();
        InventoryTask.updateSwapAndUseScript();
        if (script.isFinished() && !taskQueue.isEmpty() && (nextTask = taskQueue.poll()) != null) {
            InventoryFlowManager.log("\u00a7a[InventoryFlow] \u041e\u0431\u0440\u0430\u0431\u0430\u0442\u044b\u0432\u0430\u044e \u0437\u0430\u0434\u0430\u0447\u0443 \u0438\u0437 \u043e\u0447\u0435\u0440\u0435\u0434\u0438");
            InventoryFlowManager.addTask(nextTask);
        }
    }

    public static void postMotion() {
        postScript.update();
    }

    public static void input(InputEvent e) {
    }

    private static void waitForGroundAndExecute(Runnable task) {
        script.cleanup().addTickStep(0, () -> {
            InventoryFlowManager.disableMoveKeys();
            script.addTickStep(2, () -> {
                task.run();
                InventoryFlowManager.enableMoveKeys();
            });
        });
    }

    public static void addTask(Runnable task) {
        boolean scriptFinished = script.isFinished();
        boolean hasMovement = Simulations.hasPlayerMovement();
        String selectedMode = GuiMove.mode.getSelected();
        InventoryFlowManager.log("\u00a7b[InventoryFlow] addTask \u0432\u044b\u0437\u0432\u0430\u043d | scriptFinished=" + scriptFinished + " | hasMovement=" + hasMovement + " | mode=" + selectedMode);
        if (scriptFinished) {
            InventoryFlowManager.log("\u00a7a[InventoryFlow] \u0423\u0441\u043b\u043e\u0432\u0438\u0435 \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u043e, \u0434\u043e\u0431\u0430\u0432\u043b\u044f\u044e \u0432 script");
            switch (selectedMode) {
                case "Funtime": {
                    InventoryFlowManager.log("\u00a7a[InventoryFlow] \u0420\u0435\u0436\u0438\u043c: Funtime");
                    script.cleanup().addTickStep(0, () -> {
                        InventoryFlowManager.log("\u00a7a[InventoryFlow] Funtime: disableMoveKeys");
                        InventoryFlowManager.disableMoveKeys();
                    }).addTickStep(2, () -> {
                        InventoryFlowManager.log("\u00a7a[InventoryFlow] Funtime: \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u0435 \u0437\u0430\u0434\u0430\u0447\u0438");
                        task.run();
                    }).addTickStep(2, () -> {
                        InventoryFlowManager.log("\u00a7a[InventoryFlow] Funtime: enableMoveKeys");
                        InventoryFlowManager.enableMoveKeys();
                    });
                    return;
                }
                case "SpookyTime": {
                    InventoryFlowManager.log("\u00a7a[InventoryFlow] \u0420\u0435\u0436\u0438\u043c: SpookyTime");
                    script.cleanup().addTickStep(0, () -> {
                        InventoryFlowManager.log("\u00a7a[InventoryFlow] SpookyTime: disableMoveKeys");
                        InventoryFlowManager.disableMoveKeys();
                    }).addTickStep(2, () -> {
                        InventoryFlowManager.log("\u00a7a[InventoryFlow] SpookyTime: \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u0435 \u0437\u0430\u0434\u0430\u0447\u0438 (swapAndUse)");
                        task.run();
                    }).addTickStep(2, () -> {
                        InventoryFlowManager.log("\u00a7a[InventoryFlow] SpookyTime: enableMoveKeys");
                        InventoryFlowManager.enableMoveKeys();
                    });
                    return;
                }
                case "Reallyworld": {
                    InventoryFlowManager.log("\u00a7a[InventoryFlow] \u0420\u0435\u0436\u0438\u043c: Reallyworld");
                    script.cleanup().addTickStep(0, () -> {
                        InventoryFlowManager.log("\u00a7a[InventoryFlow] Reallyworld: disableMoveKeys");
                        InventoryFlowManager.disableMoveKeys();
                        script.addTickStep(0, () -> {
                            InventoryFlowManager.log("\u00a7a[InventoryFlow] Reallyworld: \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u0435 \u0437\u0430\u0434\u0430\u0447\u0438");
                            task.run();
                            InventoryFlowManager.log("\u00a7a[InventoryFlow] Reallyworld: enableMoveKeys");
                            InventoryFlowManager.enableMoveKeys();
                        });
                    });
                    return;
                }
                case "LonyGrief": {
                    InventoryFlowManager.log("\u00a7a[InventoryFlow] \u0420\u0435\u0436\u0438\u043c: LonyGrief");
                    script.cleanup().addTickStep(0, () -> {
                        InventoryFlowManager.log("\u00a7a[InventoryFlow] LonyGrief: disableMoveKeys");
                        InventoryFlowManager.disableMoveKeys();
                    }).addTickStep(2, () -> {
                        InventoryFlowManager.log("\u00a7a[InventoryFlow] LonyGrief: \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u0435 \u0437\u0430\u0434\u0430\u0447\u0438");
                        task.run();
                    }).addTickStep(3, () -> {
                        InventoryFlowManager.log("\u00a7a[InventoryFlow] LonyGrief: enableMoveKeys");
                        InventoryFlowManager.enableMoveKeys();
                    });
                    return;
                }
                case "Holyworld": {
                    InventoryFlowManager.log("\u00a7a[InventoryFlow] \u0420\u0435\u0436\u0438\u043c: Holyworld");
                    script.cleanup().addTickStep(0, () -> {
                        InventoryFlowManager.log("\u00a7a[InventoryFlow] Holyworld: disableMoveKeys");
                        InventoryFlowManager.disableMoveKeys();
                    }).addTickStep(4, () -> {
                        InventoryFlowManager.log("\u00a7a[InventoryFlow] Holyworld: \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u0435 \u0437\u0430\u0434\u0430\u0447\u0438");
                        task.run();
                    }).addTickStep(5, () -> {
                        InventoryFlowManager.log("\u00a7a[InventoryFlow] Holyworld: enableMoveKeys");
                        InventoryFlowManager.enableMoveKeys();
                    });
                    return;
                }
                case "Vanilla": 
                case "\u041b\u0435\u0433\u0438\u0442": {
                    task.run();
                    return;
                }
            }
            InventoryFlowManager.log("\u00a7c[InventoryFlow] \u041d\u0435\u0438\u0437\u0432\u0435\u0441\u0442\u043d\u044b\u0439 \u0440\u0435\u0436\u0438\u043c: " + selectedMode + ", \u0434\u043e\u0431\u0430\u0432\u043b\u044f\u044e \u0432 \u043e\u0447\u0435\u0440\u0435\u0434\u044c \u0437\u0430\u0434\u0430\u0447");
            taskQueue.offer(task);
            return;
        }
        InventoryFlowManager.log("\u00a7c[InventoryFlow] \u0423\u0441\u043b\u043e\u0432\u0438\u0435 \u041d\u0415 \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u043e, \u0434\u043e\u0431\u0430\u0432\u043b\u044f\u044e \u0432 \u043e\u0447\u0435\u0440\u0435\u0434\u044c \u0437\u0430\u0434\u0430\u0447");
        taskQueue.offer(task);
    }

    private static void rotateToCamera() {
        Module module = new Module("InventoryComponent", "Inventory Component", ModuleCategory.PLAYER);
        module.state = true;
        TurnsConnection.INSTANCE.rotateTo(MathAngle.cameraAngle(), TurnsConfig.DEFAULT, TaskPriority.HIGH_IMPORTANCE_3, module);
    }

    public static void disableMoveKeys() {
        InventoryFlowManager.log("\u00a7c[InventoryFlow] disableMoveKeys | scriptFinished=" + script.isFinished());
        canMove = false;
        InventoryFlowManager.unPressMoveKeys();
    }

    public static void enableMoveKeys() {
        InventoryFlowManager.log("\u00a7a[InventoryFlow] enableMoveKeys | scriptFinished=" + script.isFinished());
        InventoryTask.closeScreen(true);
        canMove = true;
        InventoryFlowManager.updateMoveKeys();
    }

    public static void unPressMoveKeys() {
        moveKeys.forEach(keyBinding -> keyBinding.method_23481(false));
        InventoryFlowManager.mc.field_1724.method_5728(false);
    }

    public static void updateMoveKeys() {
        moveKeys.forEach(keyBinding -> keyBinding.method_23481(class_3675.method_15987((long)mc.method_22683().method_4490(), (int)keyBinding.method_1429().method_1444())));
    }

    public static boolean shouldSkipExecution() {
        if (InventoryFlowManager.mc.field_1755 == null) {
            return false;
        }
        if (PlayerInteractionHelper.isChat(InventoryFlowManager.mc.field_1755)) {
            return false;
        }
        if (InventoryFlowManager.mc.field_1755 instanceof class_498 || InventoryFlowManager.mc.field_1755 instanceof class_471 || InventoryFlowManager.mc.field_1755 instanceof class_463 || InventoryFlowManager.mc.field_1755 instanceof class_497) {
            return false;
        }
        return !GuiMove.mode.isSelected("Holyworld") || !(InventoryFlowManager.mc.field_1755 instanceof class_476);
    }

    private InventoryFlowManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1268
 *  net.minecraft.class_1707
 *  net.minecraft.class_1713
 *  net.minecraft.class_1735
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2371
 *  net.minecraft.class_2561
 *  net.minecraft.class_2596
 *  net.minecraft.class_2886
 *  net.minecraft.class_437
 *  net.minecraft.class_476
 *  net.minecraft.class_9290
 *  net.minecraft.class_9334
 *  org.apache.commons.lang3.StringUtils
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.display.hud.Notifications;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.packet.network.Network;
import code.alphadlc.utils.client.sound.SoundManager;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.math.time.StopWatch;
import java.util.Iterator;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1707;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2371;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2886;
import net.minecraft.class_437;
import net.minecraft.class_476;
import net.minecraft.class_9290;
import net.minecraft.class_9334;
import org.apache.commons.lang3.StringUtils;

public class HolyWorldAutoJoin
implements QuickImports {
    private int targetAnarchy = -1;
    private boolean isJoining = false;
    private StopWatch hubDelayWatch = new StopWatch();
    private StopWatch actionDelayWatch = new StopWatch();
    private int currentStep = 0;
    private boolean completed = false;
    private boolean active = false;

    @EventHandler
    public void onTick(TickEvent event) {
        if (!this.active) {
            if (this.isJoining || this.completed) {
                this.reset();
            }
            return;
        }
        if (!Network.isHolyWorld()) {
            if (this.isJoining || this.completed) {
                this.reset();
            }
            return;
        }
        if (this.completed) {
            return;
        }
        if (this.currentStep == 0 && !this.isJoining) {
            if (this.targetAnarchy <= 0) {
                int anarchy = Network.getAnarchy();
                if (anarchy > 0) {
                    this.targetAnarchy = anarchy;
                } else {
                    return;
                }
            }
            this.isJoining = true;
            this.currentStep = 1;
            this.hubDelayWatch.reset();
            HolyWorldAutoJoin.mc.field_1724.field_3944.method_45730("hub");
            return;
        }
        if (this.currentStep == 1) {
            if (this.hubDelayWatch.elapsedTime() >= 50L) {
                InventoryTask.selectCompass();
                this.actionDelayWatch.reset();
                this.currentStep = 2;
            }
            return;
        }
        if (this.currentStep == 2) {
            class_1799 mainHandStack = HolyWorldAutoJoin.mc.field_1724.method_6047();
            if (mainHandStack.method_7909() == class_1802.field_8251) {
                if (this.actionDelayWatch.elapsedTime() >= 50L) {
                    HolyWorldAutoJoin.mc.field_1724.field_3944.method_52787((class_2596)new class_2886(class_1268.field_5808, HolyWorldAutoJoin.mc.field_1724.method_31548().field_7545, HolyWorldAutoJoin.mc.field_1724.method_36454(), HolyWorldAutoJoin.mc.field_1724.method_36455()));
                    this.actionDelayWatch.reset();
                    this.currentStep = 3;
                }
            } else if (this.actionDelayWatch.elapsedTime() >= 50L) {
                InventoryTask.selectCompass();
                this.actionDelayWatch.reset();
            }
            return;
        }
        if (this.currentStep == 3) {
            if (HolyWorldAutoJoin.mc.field_1755 instanceof class_476) {
                if (this.actionDelayWatch.elapsedTime() >= 250L) {
                    this.currentStep = 4;
                    this.actionDelayWatch.reset();
                }
            } else if (this.actionDelayWatch.elapsedTime() >= 250L) {
                class_1799 mainHandStack = HolyWorldAutoJoin.mc.field_1724.method_6047();
                if (mainHandStack.method_7909() == class_1802.field_8251) {
                    HolyWorldAutoJoin.mc.field_1724.field_3944.method_52787((class_2596)new class_2886(class_1268.field_5808, HolyWorldAutoJoin.mc.field_1724.method_31548().field_7545, HolyWorldAutoJoin.mc.field_1724.method_36454(), HolyWorldAutoJoin.mc.field_1724.method_36455()));
                    this.actionDelayWatch.reset();
                } else {
                    InventoryTask.selectCompass();
                    this.currentStep = 2;
                    this.actionDelayWatch.reset();
                }
            }
            return;
        }
        if (this.currentStep == 4) {
            if (HolyWorldAutoJoin.mc.field_1755 instanceof class_476) {
                if (this.actionDelayWatch.elapsedTime() >= 500L) {
                    InventoryTask.clickSlot(12, 0, class_1713.field_7790, true);
                    this.currentStep = 5;
                    this.actionDelayWatch.reset();
                }
            } else {
                this.reset();
            }
            return;
        }
        if (this.currentStep == 5) {
            if (HolyWorldAutoJoin.mc.field_1755 instanceof class_476) {
                if (this.actionDelayWatch.elapsedTime() >= 500L) {
                    int categorySlot = -1;
                    if (this.targetAnarchy <= 16) {
                        categorySlot = 0;
                    } else if (this.targetAnarchy <= 37) {
                        categorySlot = 1;
                    } else if (this.targetAnarchy <= 53) {
                        categorySlot = 2;
                    } else if (this.targetAnarchy >= 54) {
                        categorySlot = 3;
                    }
                    if (categorySlot != -1) {
                        InventoryTask.clickSlot(categorySlot, 0, class_1713.field_7790, true);
                        this.currentStep = 6;
                        this.actionDelayWatch.reset();
                    }
                }
            } else {
                this.reset();
            }
            return;
        }
        if (this.currentStep == 6) {
            class_437 class_4372 = HolyWorldAutoJoin.mc.field_1755;
            if (class_4372 instanceof class_476) {
                class_476 screen = (class_476)class_4372;
                if (this.actionDelayWatch.elapsedTime() >= 500L) {
                    boolean found = false;
                    class_2371 slots = ((class_1707)screen.method_17577()).field_7761;
                    String title = screen.method_25440().getString();
                    boolean isLightAnarchy = title != null && (title.toLowerCase().contains("\u043b\u0430\u0439\u0442") || title.contains("1") && title.contains("64"));
                    for (int i = 0; i < slots.size(); ++i) {
                        String name;
                        class_9290 lore;
                        class_1735 slot = (class_1735)slots.get(i);
                        class_1799 stack = slot.method_7677();
                        if (stack.method_7960()) continue;
                        Integer anarchyNumber = null;
                        if (stack.method_7909() == class_1802.field_8575 && (lore = (class_9290)stack.method_57824(class_9334.field_49632)) != null && !lore.comp_2400().isEmpty()) {
                            for (class_2561 line : lore.comp_2400()) {
                                String numberAfterHash;
                                String loreText = line.getString();
                                if (!loreText.contains("#") || (numberAfterHash = StringUtils.substringAfter((String)loreText, (String)"#")) == null || numberAfterHash.isEmpty()) continue;
                                anarchyNumber = HolyWorldAutoJoin.parseLeadingInt(numberAfterHash.trim());
                                break;
                            }
                        }
                        if (anarchyNumber == null && (isLightAnarchy || this.targetAnarchy >= 1 && this.targetAnarchy <= 64) && (anarchyNumber = HolyWorldAutoJoin.parseLeadingInt(name = stack.method_7964().getString().replaceAll("\u00a7[0-9a-fk-or]", "").replaceAll("\u00a7.", "").trim())) == null && stack.method_57824(class_9334.field_49632) != null) {
                            class_2561 line;
                            Iterator iterator = ((class_9290)stack.method_57824(class_9334.field_49632)).comp_2400().iterator();
                            while (iterator.hasNext() && (anarchyNumber = HolyWorldAutoJoin.parseLeadingInt((line = (class_2561)iterator.next()).getString().replaceAll("\u00a7[0-9a-fk-or]", "").trim())) == null) {
                            }
                        }
                        if (anarchyNumber == null || anarchyNumber != this.targetAnarchy) continue;
                        InventoryTask.clickSlot(slot.field_7874, 0, class_1713.field_7790, false);
                        found = true;
                        HolyWorldAutoJoin.mc.field_1724.method_7346();
                        SoundManager.playSound(SoundManager.ENABLE_MODULE);
                        Notifications.getInstance().addList("\u041f\u0435\u0440\u0435\u0437\u0430\u0445\u043e\u0434 \u043d\u0430 \u0430\u043d\u0430\u0440\u0445\u0438\u044e #" + String.valueOf(class_124.field_1078) + anarchyNumber + String.valueOf(class_124.field_1070) + " \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d", 3000);
                        this.reset();
                        this.active = false;
                        break;
                    }
                    if (!found && this.actionDelayWatch.elapsedTime() >= 1000L) {
                        this.reset();
                    }
                }
            } else if (!this.completed) {
                this.reset();
            }
            return;
        }
    }

    private static Integer parseLeadingInt(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        StringBuilder num = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) break;
            num.append(c);
        }
        if (num.length() == 0) {
            return null;
        }
        try {
            return Integer.parseInt(num.toString());
        }
        catch (NumberFormatException e) {
            return null;
        }
    }

    public void reset() {
        this.targetAnarchy = -1;
        this.isJoining = false;
        this.currentStep = 0;
        this.completed = false;
        this.hubDelayWatch.reset();
        this.actionDelayWatch.reset();
    }

    public void setTargetAnarchy(int anarchy) {
        this.targetAnarchy = anarchy;
    }

    public void setActive(boolean active) {
        this.active = active;
        if (!active) {
            this.reset();
        }
    }

    public boolean isActive() {
        return this.active;
    }
}

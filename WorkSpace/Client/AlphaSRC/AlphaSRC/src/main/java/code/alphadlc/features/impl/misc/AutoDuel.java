/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1713
 *  net.minecraft.class_2596
 *  net.minecraft.class_437
 *  net.minecraft.class_476
 *  net.minecraft.class_7439
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.math.time.TimerUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import net.minecraft.class_1713;
import net.minecraft.class_2596;
import net.minecraft.class_437;
import net.minecraft.class_476;
import net.minecraft.class_7439;

public class AutoDuel
extends Module
implements QuickImports {
    private final SelectSetting kits = new SelectSetting("\u0420\u0430\u0437\u0440\u0435\u0448\u0435\u043d\u043d\u044b\u0435 \u043a\u0438\u0442\u044b", "auto_duel.kits.desc").value("\u0429\u0438\u0442\u044b", "\u0428\u0438\u043f\u044b 3", "\u041b\u0443\u043a", "\u0422\u043e\u0442\u0435\u043c\u044b", "\u041d\u043e\u0414\u0435\u0431\u0430\u0444\u0444", "\u0428\u0430\u0440\u044b", "\u041a\u043b\u0430\u0441\u0441\u0438\u043a", "\u0427\u0438\u0442\u0435\u0440\u0441\u043a\u0438\u0439 \u0440\u0430\u0439", "\u0411\u0435\u0437 \u044d\u043d\u0434\u0435\u0440-\u0436\u0435\u043c\u0447\u0443\u0433\u0430").selected("\u0428\u0430\u0440\u044b");
    private final List<String> sent = new ArrayList<String>();
    private final TimerUtil duelDelay = TimerUtil.create();
    private final TimerUtil clearSentDelay = TimerUtil.create();
    private final TimerUtil kitChoiceDelay = TimerUtil.create();
    private final TimerUtil duelSetupDelay = TimerUtil.create();
    static final Pattern NAME_PATTERN = Pattern.compile("^\\w{3,16}$");

    public AutoDuel() {
        super("AutoDuel", ModuleCategory.MISC);
        this.setup(this.kits);
    }

    @Override
    public void activate() {
        this.sent.clear();
    }

    @EventHandler
    public void onTick(TickEvent e) {
        class_437 class_4372;
        if (AutoDuel.mc.field_1724 == null || mc.method_1562() == null) {
            return;
        }
        List<String> players = this.getOnlinePlayers();
        if (this.clearSentDelay.hasTimeElapsed(800L * (long)Math.max(players.size(), 1))) {
            this.sent.clear();
            this.clearSentDelay.resetCounter();
        }
        for (String string : players) {
            if (this.sent.contains(string) || string.equals(AutoDuel.mc.field_1724.method_7334().getName())) continue;
            if (!this.duelDelay.hasTimeElapsed(1000L)) break;
            AutoDuel.mc.field_1724.field_3944.method_45729("/duel " + string);
            this.sent.add(string);
            this.duelDelay.resetCounter();
            break;
        }
        if ((class_4372 = AutoDuel.mc.field_1755) instanceof class_476) {
            class_476 screen = (class_476)class_4372;
            String string = screen.method_25440().getString();
            if (string.contains("\u0412\u044b\u0431\u043e\u0440 \u043d\u0430\u0431\u043e\u0440\u0430 (1/1)") || string.contains("Kit selection")) {
                this.handleKitSelection();
            } else if (string.contains("\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0430 \u043f\u043e\u0435\u0434\u0438\u043d\u043a\u0430") || string.contains("Duel setup")) {
                this.handleDuelSetup();
            }
        }
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        class_7439 packet;
        String text;
        class_2596<?> class_25962 = e.getPacket();
        if (class_25962 instanceof class_7439 && ((text = (packet = (class_7439)class_25962).comp_763().getString().toLowerCase()).contains("\u043d\u0430\u0447\u0430\u043b\u043e") && text.contains("\u0447\u0435\u0440\u0435\u0437") && text.contains("\u0441\u0435\u043a\u0443\u043d\u0434") || text.contains("\u0434\u0443\u044d\u043b\u0438 \u00bb \u0432\u043e \u0432\u0440\u0435\u043c\u044f \u043f\u043e\u0435\u0434\u0438\u043d\u043a\u0430 \u0437\u0430\u043f\u0440\u0435\u0449\u0435\u043d\u043e \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u043a\u043e\u043c\u0430\u043d\u0434\u044b") || text.contains("duel") && text.contains("during") && text.contains("forbidden")) && this.isState()) {
            this.switchState();
        }
    }

    private List<String> getOnlinePlayers() {
        return mc.method_1562().method_2880().stream().map(entry -> entry.method_2966().getName()).filter(name -> NAME_PATTERN.matcher((CharSequence)name).matches()).collect(Collectors.toList());
    }

    private void handleKitSelection() {
        List<String> list = this.kits.getList();
        if (list == null || list.isEmpty()) {
            return;
        }
        ArrayList<Integer> slots = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); ++i) {
            if (!this.kits.isSelected(list.get(i))) continue;
            slots.add(i);
        }
        if (slots.isEmpty()) {
            return;
        }
        Collections.shuffle(slots);
        int slotId = (Integer)slots.get(0);
        if (this.kitChoiceDelay.hasTimeElapsed(90L)) {
            InventoryTask.clickSlot(slotId, 0, class_1713.field_7794, false);
            this.kitChoiceDelay.resetCounter();
        }
    }

    private void handleDuelSetup() {
        if (this.duelSetupDelay.hasTimeElapsed(90L)) {
            InventoryTask.clickSlot(0, 0, class_1713.field_7794, false);
            this.duelSetupDelay.resetCounter();
        }
    }
}

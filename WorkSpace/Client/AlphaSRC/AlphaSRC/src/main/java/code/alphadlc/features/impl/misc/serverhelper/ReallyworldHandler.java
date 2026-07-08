/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2596
 *  net.minecraft.class_2815
 *  net.minecraft.class_3944
 */
package code.alphadlc.features.impl.misc.serverhelper;

import code.alphadlc.events.chat.ChatEvent;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.features.impl.misc.ServerHelper;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperHandler;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperRenderItem;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2596;
import net.minecraft.class_2815;
import net.minecraft.class_3944;

public class ReallyworldHandler
extends ServerHelperHandler {
    private static final Set<String> BAN_PATTERNS = new HashSet<String>(Arrays.asList("\u0430\u043a\u0440\u0438\u0435\u043d(\u0430|\u0443|\u043e\u043c|\u0435|\u0447\u0438\u043a)?", "\u0440\u0438\u0447(\u0430|\u0443|\u043e\u043c|\u0435\u0439|\u0435)?", "\u043d\u044c\u044e\u043a\u043e\u0434(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0438\u043a|\u0435)?", "\u044d\u043a\u0441\u043f\u0435\u043d\u0441\u0438\u0432(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0435)?", "\u0438\u043c\u043f\u0430\u043a\u0442(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0438\u043a|\u0435)?", "\u044d\u043a\u0441\u0435\u043b\u043b\u0435\u043d\u0442(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0438\u043a|\u0435)?", "\u044d\u043a\u0441\u0435\u043b\u0435\u043d\u0442(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0438\u043a)?", "\u043a\u0430\u0442\u043b\u0430\u0432\u0430\u043d(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0447\u0438\u043a)?", "\u043a\u0430\u0442\u043b\u043e\u0432\u0430\u043d(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0447\u0438\u043a)?", "\u0446\u0435\u043b\u0435\u0441\u0442\u0438\u0430\u043b(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0435)?", "\u0446\u0435\u043b\u043a(\u043e\u0439|\u0430|\u0443|\u0430\u043c\u0438|\u043e\u0447\u043a\u0430|\u0435)?", "\u043c\u0430\u0442\u0438\u043a\u0441(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438|\u0435)?", "\u0438\u043d\u0435\u0440\u0442\u0438(\u044f|\u0435|\u0435\u0439|\u044e|\u044f\u043c\u0438|\u0435)?", "\u044d\u043a\u0441\u043f(\u0430|\u043e\u0439|\u043e\u044e|\u0443|\u0443\u043b\u0438\u0447\u043a\u0430|\u0435)?", "\u0444\u043b\u044e\u0433\u0435\u0440(\u043e\u043c|\u0430|\u0443|\u0430\u043c\u0438)?", "\u0440\u0438\u043a\u0435\u0440(\u0430|\u0443|\u043e\u043c|\u043e\u0447\u0435\u043a)?", "\u0444\u0430\u043d\u043f\u0435\u0439", "\u0444\u0430\u043d\u0442\u0430\u0439\u043c(\u0435|\u0430|\u0443)?", "\u0445\u043e\u043b\u0438\u0432\u043e\u0440\u043b\u0434", "wwe", "hvh", "rogalik", "thunderhack", "liquidbounce", "expensive", "celestial", "newcode", "arbuz", "akrien", "nursultan", "relake", "wild", "wurst", "catlovan", "excellent", "rockstar", "catlavan", "impact", "matix", "inertia", "wex", "wexside", "nurik", "nurlan", "rich", "funpay", "fluger", "riker", "funtime", "holyworld"));
    private final List<BindSetting> binds;
    private final BooleanSetting closeMenu;
    private final BooleanSetting chatFilter;

    public ReallyworldHandler(List<BindSetting> binds, BooleanSetting closeMenu, BooleanSetting chatFilter) {
        this.binds = binds;
        this.closeMenu = closeMenu;
        this.chatFilter = chatFilter;
    }

    @Override
    public String getModeId() {
        return "Reallyworld";
    }

    @Override
    public String getOverlayTitle() {
        return "RW";
    }

    @Override
    public List<BindSetting> getBinds() {
        return this.binds;
    }

    @Override
    public List<ServerHelperRenderItem> buildRenderItems(boolean onlyBar) {
        ArrayList<ServerHelperRenderItem> items = new ArrayList<ServerHelperRenderItem>();
        items.add(new ServerHelperRenderItem("\u0410\u043d\u0442\u0438 \u043f\u043e\u043b\u0435\u0442", "AP", ReallyworldHandler.findSlotByItem(class_1802.field_8450) == -1 ? "\u2014" : "\u2713", class_1802.field_8450).withBind(this.binds.isEmpty() ? -1 : this.binds.get(0).getKey()));
        this.addPotion(items, "\u0417\u0435\u043b\u044c\u0435 \u0413\u0440\u0438\u043d\u0447\u0430", "\u0433\u0440\u0438\u043d\u0447", 1, ColorAssist.getColor(50, 205, 50, 200));
        items.add(new ServerHelperRenderItem("AutoShift", "H", "\u2713", class_1802.field_8575).withBind(this.binds.size() > 2 ? this.binds.get(2).getKey() : -1));
        this.addPotion(items, "\u041d\u043e\u0432\u043e\u0433\u043e\u0434\u043d\u0438\u0439 \u0443\u0436\u0430\u0441", "\u043d\u043e\u0432\u043e\u0433\u043e\u0434\u043d\u0438\u0439 \u0443\u0436\u0430\u0441", 3, ColorAssist.getColor(220, 20, 60, 200));
        this.addPotion(items, "\u042d\u0441\u0441\u0435\u043d\u0446\u0438\u044f \u043a\u0440\u043e\u043c\u0435\u0448\u043d\u0438\u043a\u0430", "\u044d\u0441\u0441\u0435\u043d\u0446\u0438\u044f \u043a\u0440\u043e\u043c\u0435\u0448\u043d\u0438\u043a\u0430", 4, ColorAssist.getColor(75, 0, 130, 200));
        this.addPotion(items, "\u0417\u0435\u043b\u044c\u0435 \u0421\u043d\u0435\u0436\u043e\u043a", "\u0441\u043d\u0435\u0436\u043e\u043a", 5, ColorAssist.getColor(135, 206, 250, 200));
        this.addPotion(items, "\u041b\u043e\u0432\u0443\u0448\u043a\u0430", "\u043b\u043e\u0432\u0443\u0448", 6, ColorAssist.getColor(255, 69, 0, 200));
        return items;
    }

    private void addPotion(List<ServerHelperRenderItem> items, String name, String search, int bindIndex, int tint) {
        int slot = this.findPotionSlot(search);
        String shortName = name.length() > 8 ? name.substring(0, 8) : name;
        items.add(new ServerHelperRenderItem(name, shortName, slot == -1 ? "\u2014" : "\u2713", class_1802.field_8436).withBind(this.binds.size() > bindIndex ? this.binds.get(bindIndex).getKey() : -1).withTint(tint));
    }

    @Override
    public boolean onKey(int keyCode) {
        if (this.binds.isEmpty()) {
            return false;
        }
        if (this.binds.get(0).getKey() != -1 && this.binds.get(0).getKey() == keyCode) {
            ReallyworldHandler.useAntiFlight();
            return true;
        }
        if (this.binds.size() > 1 && this.binds.get(1).getKey() != -1 && this.binds.get(1).getKey() == keyCode) {
            this.usePotion("\u0433\u0440\u0438\u043d\u0447", "\u0417\u0435\u043b\u044c\u0435 \u0413\u0440\u0438\u043d\u0447\u0430");
            return true;
        }
        if (this.binds.size() > 2 && this.binds.get(2).getKey() != -1 && this.binds.get(2).getKey() == keyCode) {
            this.pulseShift();
            return true;
        }
        if (this.binds.size() > 3 && this.binds.get(3).getKey() != -1 && this.binds.get(3).getKey() == keyCode) {
            this.usePotion("\u043d\u043e\u0432\u043e\u0433\u043e\u0434\u043d\u0438\u0439 \u0443\u0436\u0430\u0441", "\u041d\u043e\u0432\u043e\u0433\u043e\u0434\u043d\u0438\u0439 \u0443\u0436\u0430\u0441");
            return true;
        }
        if (this.binds.size() > 4 && this.binds.get(4).getKey() != -1 && this.binds.get(4).getKey() == keyCode) {
            this.usePotion("\u044d\u0441\u0441\u0435\u043d\u0446\u0438\u044f \u043a\u0440\u043e\u043c\u0435\u0448\u043d\u0438\u043a\u0430", "\u042d\u0441\u0441\u0435\u043d\u0446\u0438\u044f \u043a\u0440\u043e\u043c\u0435\u0448\u043d\u0438\u043a\u0430");
            return true;
        }
        if (this.binds.size() > 5 && this.binds.get(5).getKey() != -1 && this.binds.get(5).getKey() == keyCode) {
            this.usePotion("\u0441\u043d\u0435\u0436\u043e\u043a", "\u0421\u043d\u0435\u0436\u043e\u043a");
            return true;
        }
        if (this.binds.size() > 6 && this.binds.get(6).getKey() != -1 && this.binds.get(6).getKey() == keyCode) {
            this.usePotion("\u043b\u043e\u0432\u0443\u0448", "\u041b\u043e\u0432\u0443\u0448\u043a\u0430");
            return true;
        }
        return false;
    }

    private void usePotion(String search, String display) {
        int slot = this.findPotionSlot(search);
        if (slot == -1) {
            ReallyworldHandler.notifyMissing(display);
            return;
        }
        ServerHelper.getInstance().queueSlotUse(slot);
    }

    private int findPotionSlot(String searchQuery) {
        if (ReallyworldHandler.mc.field_1724 == null) {
            return -1;
        }
        String needle = searchQuery.toLowerCase(Locale.ROOT);
        boolean trap = needle.contains("\u043b\u043e\u0432\u0443\u0448");
        String trapStem = trap ? "\u043b\u043e\u0432\u0443\u0448\u043a" : "";
        for (int i = 0; i < 45; ++i) {
            String cleanName;
            class_1799 stack = ReallyworldHandler.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7960() || !(cleanName = ReallyworldHandler.cleanName(stack.method_7964().getString())).contains(needle) && (!trap || !cleanName.contains(trapStem))) continue;
            return i;
        }
        return -1;
    }

    private void pulseShift() {
        if (ReallyworldHandler.mc.field_1690 == null) {
            return;
        }
        ReallyworldHandler.mc.field_1690.field_1832.method_23481(true);
        new Timer().schedule(new TimerTask(this){

            @Override
            public void run() {
                if (QuickImports.mc.field_1690 != null) {
                    QuickImports.mc.field_1690.field_1832.method_23481(false);
                }
            }
        }, 150L);
    }

    @Override
    public void onPacketReceive(PacketEvent event) {
        class_3944 screen;
        String title;
        class_2596<?> class_25962;
        if (event.isSend() || ReallyworldHandler.mc.field_1724 == null) {
            return;
        }
        if (this.closeMenu.isValue() && (class_25962 = event.getPacket()) instanceof class_3944 && (title = (screen = (class_3944)class_25962).method_17594().getString()).contains("\ua201\ua000\ua202\ua301\ua202\ua001") && ReallyworldHandler.mc.field_1724.field_6012 < 100) {
            ReallyworldHandler.mc.field_1724.field_3944.method_52787((class_2596)new class_2815(screen.method_17592()));
            event.cancel();
        }
    }

    @Override
    public void onChatSend(ChatEvent event) {
        if (!this.chatFilter.isValue()) {
            return;
        }
        String message = event.getMessage().toLowerCase(Locale.ROOT);
        for (String pattern : BAN_PATTERNS) {
            if (!message.matches(".*" + pattern + ".*")) continue;
            event.cancel();
            ReallyworldHandler.notify("\u0412 \u0432\u0430\u0448\u0435\u043c \u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0438 \u0431\u044b\u043b\u043e \u043d\u0430\u0439\u0434\u0435\u043d\u043e \u0437\u0430\u043f\u0440\u0435\u0449\u0435\u043d\u043d\u043e\u0435 \u0441\u043b\u043e\u0432\u043e, \u043e\u0442\u043f\u0440\u0430\u0432\u043a\u0430 \u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u044f \u043e\u0442\u043c\u0435\u043d\u0435\u043d\u0430!");
            return;
        }
    }

    @Override
    public boolean hasPendingUse() {
        return false;
    }

    @Override
    public int getPendingSlot() {
        return -1;
    }

    @Override
    public void clearPending() {
    }
}

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1707
 *  net.minecraft.class_1735
 *  net.minecraft.class_1738
 *  net.minecraft.class_1799
 *  net.minecraft.class_1810
 *  net.minecraft.class_1829
 *  net.minecraft.class_2371
 *  net.minecraft.class_2561
 *  net.minecraft.class_332
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 *  net.minecraft.class_476
 *  net.minecraft.class_5321
 *  net.minecraft.class_6880
 *  net.minecraft.class_9290
 *  net.minecraft.class_9304
 *  net.minecraft.class_9334
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.container.HandledScreenEvent;
import code.alphadlc.events.keyboard.KeyEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.features.module.setting.implement.RadioSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.chat.ChatCommandSender;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.class_124;
import net.minecraft.class_1707;
import net.minecraft.class_1735;
import net.minecraft.class_1738;
import net.minecraft.class_1799;
import net.minecraft.class_1810;
import net.minecraft.class_1829;
import net.minecraft.class_2371;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_476;
import net.minecraft.class_5321;
import net.minecraft.class_6880;
import net.minecraft.class_9290;
import net.minecraft.class_9304;
import net.minecraft.class_9334;

public class AuctionHelper
extends Module {
    static final Pattern PRICE_WITH_LABEL = Pattern.compile("(?iu)(?:\u0446\u0435\u043d\u0430|\u0441\u0442\u043e\u0438\u043c\u043e\u0441\u0442\u044c|price|cost)\\s*[:]?\\s*([\\d\\s,._]+)");
    static final Pattern PRICE_WITH_CURRENCY = Pattern.compile("(?iu)(?:\\$|\u20bd|\u0440|\u0440\u0443\u0431|\u0440\\.)\\s*([\\d\\s,._]+)|([\\d\\s,._]+)\\s*(?:\\$|\u20bd|\u0440|\u0440\u0443\u0431|\u0440\\.)");
    static final Pattern DIGITS_PATTERN = Pattern.compile("([\\d][\\d\\s,._]{1,18})");
    private final BooleanSetting onlyMending = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u043f\u043e\u0447\u0438\u043d\u043a\u0430", "\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b \u0441 \u043f\u043e\u0447\u0438\u043d\u043a\u043e\u0439").setValue(false);
    private final BooleanSetting showPricePerItem = new BooleanSetting("\u0426\u0435\u043d\u0430 \u0437\u0430 1", "\u0414\u043e\u0431\u0430\u0432\u043b\u044f\u0442\u044c \u0432 \u043b\u043e\u0440 \u0446\u0435\u043d\u0443 \u0437\u0430 \u043e\u0434\u043d\u0443 \u0448\u0442\u0443\u043a\u0443").setValue(true);
    private final BooleanSetting enchantSort = new BooleanSetting("\u0424\u0438\u043b\u044c\u0442\u0440 \u0437\u0430\u0447\u0430\u0440\u043e\u0432", "\u041f\u043e\u0434\u0441\u0432\u0435\u0442\u043a\u0430 \u0438 \u0441\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u043a\u0430 \u043f\u043e \u0437\u0430\u0447\u0430\u0440\u0430\u043c \u0438 \u043f\u0440\u043e\u0447\u043d\u043e\u0441\u0442\u0438").setValue(true);
    private final RadioSetting sortPreset = new RadioSetting("\u041f\u0440\u0435\u0441\u0435\u0442 \u0444\u0438\u043b\u044c\u0442\u0440\u0430", "\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0442\u0438\u043f \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u043e\u0432", new String[]{"\u041b\u044e\u0431\u044b\u0435", "\u0411\u0440\u043e\u043d\u044f Prot 5+", "\u041a\u0438\u0440\u043a\u0430 Eff 7-10", "\u041c\u0435\u0447 Sharp 7+"}, "\u041b\u044e\u0431\u044b\u0435").visible(this.enchantSort::isValue);
    private final BooleanSetting onlyMatching = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u043f\u043e\u0434\u0445\u043e\u0434\u044f\u0449\u0438\u0435", "\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b, \u043f\u043e\u0434\u0445\u043e\u0434\u044f\u0449\u0438\u0435 \u043f\u043e\u0434 \u0444\u0438\u043b\u044c\u0442\u0440").setValue(true).visible(this.enchantSort::isValue);
    private final SliderSettings minDurability = new SliderSettings("\u041c\u0438\u043d. \u043f\u0440\u043e\u0447\u043d\u043e\u0441\u0442\u044c %", "\u041c\u0438\u043d\u0438\u043c\u0430\u043b\u044c\u043d\u0430\u044f \u043f\u0440\u043e\u0447\u043d\u043e\u0441\u0442\u044c \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430 \u0432 \u043f\u0440\u043e\u0446\u0435\u043d\u0442\u0430\u0445").setValue(0.0f).range(0, 100).step(1.0f).visible(this.enchantSort::isValue);
    private final SliderSettings minProtection = new SliderSettings("\u041c\u0438\u043d. Protection", "\u041c\u0438\u043d\u0438\u043c\u0430\u043b\u044c\u043d\u044b\u0439 \u0443\u0440\u043e\u0432\u0435\u043d\u044c Protection").setValue(5.0f).range(1, 10).step(1.0f).visible(this.enchantSort::isValue);
    private final SliderSettings minSharpness = new SliderSettings("\u041c\u0438\u043d. Sharpness", "\u041c\u0438\u043d\u0438\u043c\u0430\u043b\u044c\u043d\u044b\u0439 \u0443\u0440\u043e\u0432\u0435\u043d\u044c Sharpness").setValue(7.0f).range(1, 10).step(1.0f).visible(this.enchantSort::isValue);
    private final SliderSettings minEfficiency = new SliderSettings("\u041c\u0438\u043d. Efficiency", "\u041c\u0438\u043d\u0438\u043c\u0430\u043b\u044c\u043d\u044b\u0439 \u0443\u0440\u043e\u0432\u0435\u043d\u044c Efficiency").setValue(7.0f).range(1, 10).step(1.0f).visible(this.enchantSort::isValue);
    private final SliderSettings maxEfficiency = new SliderSettings("\u041c\u0430\u043a\u0441. Efficiency", "\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u044b\u0439 \u0443\u0440\u043e\u0432\u0435\u043d\u044c Efficiency").setValue(10.0f).range(1, 10).step(1.0f).visible(this.enchantSort::isValue);
    private final ColorSetting cheapestColor = new ColorSetting("\u0426\u0432\u0435\u0442 \u0434\u0435\u0448\u0435\u0432\u043e\u0433\u043e", "\u0426\u0432\u0435\u0442 \u043f\u043e\u0434\u0441\u0432\u0435\u0442\u043a\u0438 \u0441\u0430\u043c\u043e\u0433\u043e \u0434\u0435\u0448\u0435\u0432\u043e\u0433\u043e \u0441\u043b\u043e\u0442\u0430").setColor(new Color(75, 255, 75, 255).getRGB());
    private final ColorSetting bestValueColor = new ColorSetting("\u0426\u0432\u0435\u0442 \u0432\u044b\u0433\u043e\u0434\u043d\u043e\u0433\u043e", "\u0426\u0432\u0435\u0442 \u043f\u043e\u0434\u0441\u0432\u0435\u0442\u043a\u0438 \u043b\u0443\u0447\u0448\u0435\u0433\u043e \u0441\u043b\u043e\u0442\u0430 \u043f\u043e \u0446\u0435\u043d\u0435 \u0437\u0430 \u0448\u0442\u0443\u043a\u0443").setColor(new Color(51, 170, 255, 255).getRGB());
    private final BindSetting searchHeldItemBind = new BindSetting("\u041f\u043e\u0438\u0441\u043a \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430", "\u0418\u0449\u0435\u0442 \u043f\u0440\u0435\u0434\u043c\u0435\u0442 \u0438\u0437 \u0440\u0443\u043a\u0438 \u0447\u0435\u0440\u0435\u0437 /ah search");
    private final BindSetting autoSellBind = new BindSetting("\u041f\u0440\u043e\u0434\u0430\u0442\u044c \u043f\u043e \u043d\u0430\u0446\u0435\u043d\u043a\u0435", "\u0418\u0449\u0435\u0442 \u043c\u0438\u043d\u0438\u043c\u0430\u043b\u044c\u043d\u0443\u044e \u0446\u0435\u043d\u0443 \u0438 \u043f\u0440\u043e\u0434\u0430\u0435\u0442 \u043f\u0440\u0435\u0434\u043c\u0435\u0442 \u0438\u0437 \u0440\u0443\u043a\u0438");
    private final SliderSettings sellMarkupPercent = new SliderSettings("\u041d\u0430\u0446\u0435\u043d\u043a\u0430 %", "\u041d\u0430\u0446\u0435\u043d\u043a\u0430 \u043a \u0441\u0430\u043c\u043e\u0439 \u0434\u0435\u0448\u0435\u0432\u043e\u0439 \u0446\u0435\u043d\u0435").setValue(25.0f).range(0, 500).step(1.0f);
    private class_1735 cheapestSlot;
    private class_1735 bestValueSlot;
    private int cheapestTotalPrice = 0;
    private int cheapestCount = 1;
    private int lastUpdateTick = 0;
    private int lastSlotCount = 0;
    private class_476 lastScreen = null;
    private Map<Integer, String> pricePerItemCache = new HashMap<Integer, String>();
    private boolean waitingForAutoSellResult = false;
    private int autoSellStartTick = 0;
    private long lastActionMs = 0L;

    public AuctionHelper() {
        super("AuctionHelper", "AuctionHelper", ModuleCategory.RENDER);
        this.setup(this.onlyMending, this.showPricePerItem, this.enchantSort, this.sortPreset, this.onlyMatching, this.minDurability, this.minProtection, this.minSharpness, this.minEfficiency, this.maxEfficiency, this.cheapestColor, this.bestValueColor, this.searchHeldItemBind, this.autoSellBind, this.sellMarkupPercent);
    }

    @EventHandler
    public void onKey(KeyEvent e) {
        if (AuctionHelper.mc.field_1724 == null || AuctionHelper.mc.field_1724.field_3944 == null) {
            return;
        }
        if (e.isKeyDown(this.searchHeldItemBind.getKey())) {
            this.searchHeldItemByBind();
        }
        if (e.isKeyDown(this.autoSellBind.getKey())) {
            this.startAutoSellByBind();
        }
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (AuctionHelper.mc.field_1724 == null) {
            return;
        }
        class_437 class_4372 = AuctionHelper.mc.field_1755;
        if (!(class_4372 instanceof class_476)) {
            this.cheapestSlot = null;
            this.bestValueSlot = null;
            this.cheapestTotalPrice = 0;
            this.lastScreen = null;
            this.pricePerItemCache.clear();
            return;
        }
        class_476 screen = (class_476)class_4372;
        int currentSlotCount = ((class_1707)screen.method_17577()).field_7761.size();
        if (currentSlotCount != this.lastSlotCount || AuctionHelper.mc.field_1724.field_6012 - this.lastUpdateTick > 5) {
            this.lastUpdateTick = AuctionHelper.mc.field_1724.field_6012;
            this.lastSlotCount = currentSlotCount;
            this.updateBestSlots(screen);
        }
        if (this.waitingForAutoSellResult) {
            this.handleAutoSellAfterSearch(screen);
        }
    }

    @EventHandler
    public void onHandledScreen(HandledScreenEvent e) {
        int color;
        class_437 class_4372 = AuctionHelper.mc.field_1755;
        if (!(class_4372 instanceof class_476)) {
            return;
        }
        class_476 screen = (class_476)class_4372;
        if (this.lastScreen != screen) {
            this.pricePerItemCache.clear();
            this.lastScreen = screen;
        }
        class_332 context = e.getDrawContext();
        class_4587 matrix = context.method_51448();
        int offsetX = (screen.field_22789 - e.getBackgroundWidth()) / 2;
        int offsetY = (screen.field_22790 - e.getBackgroundHeight()) / 2;
        matrix.method_22903();
        matrix.method_46416((float)offsetX, (float)offsetY, 0.0f);
        long time = System.currentTimeMillis();
        if (this.cheapestSlot != null && this.isValidSlot(this.cheapestSlot, screen)) {
            color = this.getBlinkingColor(this.cheapestColor.getColor(), time, 500);
            this.highlightSlot(context, this.cheapestSlot, color);
        }
        if (this.bestValueSlot != null && this.isValidSlot(this.bestValueSlot, screen) && this.bestValueSlot != this.cheapestSlot) {
            color = this.getBlinkingColor(this.bestValueColor.getColor(), time, 600);
            this.highlightSlot(context, this.bestValueSlot, color);
        }
        matrix.method_22909();
        if (this.showPricePerItem.isValue()) {
            this.addPricePerItemToSlots(screen);
        }
    }

    private void searchHeldItemByBind() {
        class_1799 held = AuctionHelper.mc.field_1724.method_6047();
        if (held.method_7960()) {
            ChatMessage.brandmessage("\u00a7c[AuctionHelper] \u0412 \u0440\u0443\u043a\u0435 \u043d\u0435\u0442 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430 \u0434\u043b\u044f \u043f\u043e\u0438\u0441\u043a\u0430.");
            return;
        }
        String query = this.getCleanItemName(held);
        if (query.isBlank()) {
            ChatMessage.brandmessage("\u00a7c[AuctionHelper] \u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043f\u043e\u043b\u0443\u0447\u0438\u0442\u044c \u043d\u0430\u0437\u0432\u0430\u043d\u0438\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430.");
            return;
        }
        if (!this.canSendActionNow()) {
            return;
        }
        ChatCommandSender.sendCommand(AuctionHelper.mc.field_1724, "/ah search " + query);
        this.lastActionMs = System.currentTimeMillis();
        ChatMessage.brandmessage("\u00a7a[AuctionHelper] \u041f\u043e\u0438\u0441\u043a: \u00a7f" + query);
    }

    private void startAutoSellByBind() {
        class_1799 held = AuctionHelper.mc.field_1724.method_6047();
        if (held.method_7960()) {
            ChatMessage.brandmessage("\u00a7c[AuctionHelper] \u0414\u043b\u044f \u043f\u0440\u043e\u0434\u0430\u0436\u0438 \u0434\u0435\u0440\u0436\u0438\u0442\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442 \u0432 \u0440\u0443\u043a\u0435.");
            return;
        }
        String query = this.getCleanItemName(held);
        if (query.isBlank()) {
            ChatMessage.brandmessage("\u00a7c[AuctionHelper] \u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043f\u043e\u043b\u0443\u0447\u0438\u0442\u044c \u043d\u0430\u0437\u0432\u0430\u043d\u0438\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430.");
            return;
        }
        if (!this.canSendActionNow()) {
            return;
        }
        this.waitingForAutoSellResult = true;
        this.autoSellStartTick = AuctionHelper.mc.field_1724.field_6012;
        ChatCommandSender.sendCommand(AuctionHelper.mc.field_1724, "/ah search " + query);
        this.lastActionMs = System.currentTimeMillis();
        ChatMessage.brandmessage("\u00a7e[AuctionHelper] \u0418\u0449\u0443 \u043c\u0438\u043d\u0438\u043c\u0430\u043b\u044c\u043d\u0443\u044e \u0446\u0435\u043d\u0443 \u0434\u043b\u044f: \u00a7f" + query);
    }

    private void handleAutoSellAfterSearch(class_476 screen) {
        String lowerTitle;
        String title = class_124.method_539((String)screen.method_25440().getString());
        String string = lowerTitle = title == null ? "" : title.toLowerCase(Locale.ROOT);
        if (!lowerTitle.contains("\u0430\u0443\u043a\u0446\u0438\u043e\u043d") && !lowerTitle.contains("auction")) {
            if (AuctionHelper.mc.field_1724.field_6012 - this.autoSellStartTick > 120) {
                this.waitingForAutoSellResult = false;
                ChatMessage.brandmessage("\u00a7c[AuctionHelper] \u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043e\u0442\u043a\u0440\u044b\u0442\u044c \u0430\u0443\u043a\u0446\u0438\u043e\u043d \u0441 \u0440\u0435\u0437\u0443\u043b\u044c\u0442\u0430\u0442\u0430\u043c\u0438.");
            }
            return;
        }
        if (AuctionHelper.mc.field_1724.field_6012 - this.autoSellStartTick < 6) {
            return;
        }
        if (this.cheapestTotalPrice <= 0) {
            if (AuctionHelper.mc.field_1724.field_6012 - this.autoSellStartTick > 120) {
                this.waitingForAutoSellResult = false;
                ChatMessage.brandmessage("\u00a7c[AuctionHelper] \u041d\u0435 \u043d\u0430\u0448\u0435\u043b \u0446\u0435\u043d\u0443 \u0434\u043b\u044f \u0430\u0432\u0442\u043e\u043f\u0440\u043e\u0434\u0430\u0436\u0438.");
            }
            return;
        }
        double markup = (double)this.sellMarkupPercent.getValue() / 100.0;
        int sellPrice = (int)Math.max(1L, Math.round((double)this.cheapestTotalPrice * (1.0 + markup)));
        this.waitingForAutoSellResult = false;
        if (!this.canSendActionNow()) {
            return;
        }
        ChatCommandSender.sendCommand(AuctionHelper.mc.field_1724, "/ah sell " + sellPrice);
        this.lastActionMs = System.currentTimeMillis();
        ChatMessage.brandmessage("\u00a7a[AuctionHelper] \u041f\u0440\u043e\u0434\u0430\u0436\u0430 \u0432\u044b\u0441\u0442\u0430\u0432\u043b\u0435\u043d\u0430 \u0437\u0430: \u00a7f" + sellPrice + "\u00a7a (\u0440\u044b\u043d\u043e\u043a: " + this.cheapestTotalPrice + ", \u043d\u0430\u0446\u0435\u043d\u043a\u0430: " + (int)this.sellMarkupPercent.getValue() + "%)");
    }

    private boolean canSendActionNow() {
        return System.currentTimeMillis() - this.lastActionMs >= 350L;
    }

    private String getCleanItemName(class_1799 stack) {
        String raw = class_124.method_539((String)stack.method_7964().getString());
        if (raw == null) {
            return "";
        }
        String clean = raw.replace("[\u2605]", "").replace("\u2605", "").trim();
        return clean.replaceAll("\\s{2,}", " ");
    }

    private boolean isValidSlot(class_1735 slot, class_476 screen) {
        if (slot == null) {
            return false;
        }
        if (slot.field_7874 < 0 || slot.field_7874 >= ((class_1707)screen.method_17577()).field_7761.size()) {
            return false;
        }
        class_1735 currentSlot = ((class_1707)screen.method_17577()).method_7611(slot.field_7874);
        return currentSlot.method_7681() && !currentSlot.method_7677().method_7960();
    }

    private void updateBestSlots(class_476 screen) {
        class_2371 slots = ((class_1707)screen.method_17577()).field_7761;
        ArrayList<ItemPriceData> validItems = new ArrayList<ItemPriceData>();
        for (class_1735 slot : slots) {
            int totalPrice;
            class_1799 stack;
            if (slot.field_7871 == AuctionHelper.mc.field_1724.method_31548() || (stack = slot.method_7677()).method_7960() || this.isServiceItem(stack) || this.onlyMending.isValue() && !this.hasMending(stack) || !this.matchesAdvancedFilters(stack) || (totalPrice = this.parsePriceFromLore(stack)) <= 0) continue;
            int count = Math.max(1, stack.method_7947());
            int pricePerItem = totalPrice / count;
            int durabilityPercent = this.getDurabilityPercent(stack);
            int enchantScore = this.calculateEnchantScore(stack);
            validItems.add(new ItemPriceData(slot, totalPrice, pricePerItem, count, durabilityPercent, enchantScore));
        }
        if (validItems.isEmpty()) {
            this.cheapestSlot = null;
            this.bestValueSlot = null;
            this.cheapestTotalPrice = 0;
            return;
        }
        ItemPriceData cheapest = validItems.stream().min(Comparator.comparingInt(d -> d.totalPrice)).orElse(null);
        this.cheapestSlot = cheapest == null ? null : cheapest.slot;
        this.cheapestTotalPrice = cheapest == null ? 0 : cheapest.totalPrice;
        this.bestValueSlot = validItems.stream().min(Comparator.comparingInt(d -> d.pricePerItem).thenComparing(d -> d.enchantmentScore, Comparator.reverseOrder()).thenComparing(d -> d.durabilityPercent, Comparator.reverseOrder())).map(d -> d.slot).orElse(null);
    }

    private boolean matchesAdvancedFilters(class_1799 stack) {
        if (!this.enchantSort.isValue()) {
            return true;
        }
        int durability = this.getDurabilityPercent(stack);
        if (durability < (int)this.minDurability.getValue()) {
            return false;
        }
        String preset = this.sortPreset.get();
        boolean armorMatch = stack.method_7909() instanceof class_1738 && this.getEnchantmentLevel(stack, "protection") >= (int)this.minProtection.getValue();
        int efficiencyLevel = this.getEnchantmentLevel(stack, "efficiency");
        boolean pickaxeMatch = stack.method_7909() instanceof class_1810 && efficiencyLevel >= (int)this.minEfficiency.getValue() && efficiencyLevel <= (int)Math.max(this.minEfficiency.getValue(), this.maxEfficiency.getValue());
        boolean swordMatch = stack.method_7909() instanceof class_1829 && this.getEnchantmentLevel(stack, "sharpness") >= (int)this.minSharpness.getValue();
        return switch (preset) {
            case "\u0411\u0440\u043e\u043d\u044f Prot 5+" -> armorMatch;
            case "\u041a\u0438\u0440\u043a\u0430 Eff 7-10" -> pickaxeMatch;
            case "\u041c\u0435\u0447 Sharp 7+" -> swordMatch;
            default -> !this.onlyMatching.isValue() || armorMatch || pickaxeMatch || swordMatch;
        };
    }

    private int getDurabilityPercent(class_1799 stack) {
        if (stack == null || stack.method_7960() || !stack.method_7963()) {
            return 100;
        }
        int max = stack.method_7936();
        if (max <= 0) {
            return 100;
        }
        int left = max - stack.method_7919();
        return Math.max(0, Math.min(100, Math.round((float)left * 100.0f / (float)max)));
    }

    private int getEnchantmentLevel(class_1799 stack, String shortId) {
        class_9304 enchantments = (class_9304)stack.method_57824(class_9334.field_49633);
        if (enchantments == null || enchantments.method_57543()) {
            return 0;
        }
        for (class_6880 enchantment : enchantments.method_57534()) {
            String fullId = enchantment.method_55840();
            if (fullId == null || !fullId.endsWith(":" + shortId) && !fullId.equals(shortId)) continue;
            return enchantments.method_57536(enchantment);
        }
        return 0;
    }

    private int calculateEnchantScore(class_1799 stack) {
        int prot = this.getEnchantmentLevel(stack, "protection");
        int eff = this.getEnchantmentLevel(stack, "efficiency");
        int sharp = this.getEnchantmentLevel(stack, "sharpness");
        int score = 0;
        if (stack.method_7909() instanceof class_1738) {
            score += prot * 10;
        }
        if (stack.method_7909() instanceof class_1810) {
            score += eff * 10;
        }
        if (stack.method_7909() instanceof class_1829) {
            score += sharp * 10;
        }
        if (this.hasMending(stack)) {
            score += 7;
        }
        return score;
    }

    private boolean hasMending(class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return false;
        }
        class_9304 enchantments = stack.method_58657();
        for (class_6880 entry : enchantments.method_57534()) {
            String enchantmentId;
            if (!entry.method_40230().isPresent() || !"minecraft:mending".equals(enchantmentId = ((class_5321)entry.method_40230().get()).method_29177().toString())) continue;
            return true;
        }
        return false;
    }

    private boolean isServiceItem(class_1799 stack) {
        if (stack.method_7960()) {
            return true;
        }
        String itemName = stack.method_7909().toString().toLowerCase(Locale.ROOT);
        return itemName.contains("glass_pane") || itemName.contains("stained_glass") || itemName.contains("barrier") || itemName.contains("air");
    }

    private int parsePriceFromLore(class_1799 stack) {
        int parsed;
        String displayName;
        if (stack == null || stack.method_7960()) {
            return 0;
        }
        class_9290 loreComp = (class_9290)stack.method_57824(class_9334.field_49632);
        if (loreComp != null) {
            for (class_2561 text : loreComp.comp_2400()) {
                int parsed2;
                String line = class_124.method_539((String)text.getString());
                if (line == null || (parsed2 = this.parsePriceFromLine(line)) <= 0) continue;
                return parsed2;
            }
        }
        if ((displayName = class_124.method_539((String)stack.method_7964().getString())) != null && !displayName.isBlank() && (parsed = this.parsePriceFromLine(displayName)) > 0) {
            return parsed;
        }
        return 0;
    }

    private int parsePriceFromLine(String line) {
        String candidate;
        int parsed;
        int parsed2;
        if (line == null || line.isBlank()) {
            return 0;
        }
        Matcher m = PRICE_WITH_LABEL.matcher(line);
        if (m.find() && (parsed2 = this.parseNumberSafely(m.group(1))) > 0) {
            return parsed2;
        }
        Matcher m2 = PRICE_WITH_CURRENCY.matcher(line);
        if (m2.find() && (parsed = this.parseNumberSafely(candidate = m2.group(1) != null ? m2.group(1) : m2.group(2))) > 0) {
            return parsed;
        }
        String lower = line.toLowerCase(Locale.ROOT);
        if (lower.contains("\u0446\u0435\u043d\u0430") || lower.contains("\u0441\u0442\u043e\u0438\u043c\u043e\u0441\u0442\u044c") || lower.contains("price") || lower.contains("cost") || lower.contains("$") || lower.contains("\u20bd") || lower.contains("\u0440\u0443\u0431") || lower.contains(" \u0440")) {
            Matcher digits = DIGITS_PATTERN.matcher(line);
            while (digits.find()) {
                int parsed3 = this.parseNumberSafely(digits.group(1));
                if (parsed3 <= 0) continue;
                return parsed3;
            }
        }
        return 0;
    }

    private int parseNumberSafely(String value) {
        if (value == null || value.isBlank()) {
            return 0;
        }
        String digits = value.replaceAll("[^0-9]", "");
        if (digits.isBlank()) {
            return 0;
        }
        try {
            return Integer.parseInt(digits);
        }
        catch (NumberFormatException ignored) {
            return 0;
        }
    }

    private void addPricePerItemToSlots(class_476 screen) {
        for (class_1735 slot : ((class_1707)screen.method_17577()).field_7761) {
            int totalPrice;
            class_1799 stack;
            if (slot.field_7871 == AuctionHelper.mc.field_1724.method_31548() || (stack = slot.method_7677()).method_7960() || stack.method_7947() <= 1 || this.pricePerItemCache.containsKey(slot.field_7874) || (totalPrice = this.parsePriceFromLore(stack)) <= 0) continue;
            int count = stack.method_7947();
            int pricePerItem = totalPrice / count;
            this.addPricePerItemToLore(stack, pricePerItem);
            this.pricePerItemCache.put(slot.field_7874, this.formatPrice(pricePerItem));
        }
    }

    private void addPricePerItemToLore(class_1799 stack, int pricePerItem) {
        String pricePerItemText = "\u0426\u0435\u043d\u0430 \u0437\u0430 1 \u0448\u0442: " + this.formatPrice(pricePerItem);
        class_9290 existingLore = (class_9290)stack.method_57824(class_9334.field_49632);
        ArrayList<Object> loreLines = new ArrayList<Object>();
        if (existingLore != null) {
            for (class_2561 line : existingLore.comp_2400()) {
                String lineStr = line.getString().toLowerCase(Locale.ROOT);
                if (lineStr.contains("\u0446\u0435\u043d\u0430 \u0437\u0430 1 \u0448\u0442")) {
                    return;
                }
                loreLines.add(line);
            }
            boolean inserted = false;
            ArrayList<Object> newLoreLines = new ArrayList<Object>();
            for (class_2561 class_25612 : loreLines) {
                newLoreLines.add(class_25612);
                String lineStr = class_25612.getString().toLowerCase(Locale.ROOT);
                if (inserted || !lineStr.contains("\u0446\u0435\u043d\u0430") && !lineStr.contains("\u0441\u0442\u043e\u0438\u043c\u043e\u0441\u0442\u044c") && !lineStr.contains("price") && !lineStr.contains("cost")) continue;
                newLoreLines.add(class_2561.method_43470((String)pricePerItemText));
                inserted = true;
            }
            if (!inserted) {
                newLoreLines.add(class_2561.method_43470((String)pricePerItemText));
            }
            stack.method_57379(class_9334.field_49632, (Object)new class_9290(newLoreLines));
        } else {
            loreLines.add(class_2561.method_43470((String)pricePerItemText));
            stack.method_57379(class_9334.field_49632, (Object)new class_9290(loreLines));
        }
    }

    private int getBlinkingColor(int color, long time, int periodMs) {
        float alpha = (float)(Math.sin((double)time / (double)periodMs * Math.PI) * (double)0.3f + (double)0.7f);
        return ColorAssist.multAlpha(color, Math.min(1.0f, Math.max(0.4f, alpha)));
    }

    private void highlightSlot(class_332 context, class_1735 slot, int color) {
        if (slot == null) {
            return;
        }
        Render2D.rectangle.render(ShapeProperties.create(context.method_51448(), slot.field_7873, slot.field_7872, 16.0, 16.0).color(color).build());
    }

    private String formatPrice(int price) {
        if (price >= 1000000) {
            return String.format("%.2fM", (double)price / 1000000.0).replace(",", ".");
        }
        if (price >= 1000) {
            return String.format("%.1fK", (double)price / 1000.0).replace(",", ".");
        }
        return String.valueOf(price);
    }

    private static final class ItemPriceData {
        private final class_1735 slot;
        private final int totalPrice;
        private final int pricePerItem;
        private final int count;
        private final int durabilityPercent;
        private final int enchantmentScore;

        public ItemPriceData(class_1735 slot, int totalPrice, int pricePerItem, int count, int durabilityPercent, int enchantmentScore) {
            this.slot = slot;
            this.totalPrice = totalPrice;
            this.pricePerItem = pricePerItem;
            this.count = count;
            this.durabilityPercent = durabilityPercent;
            this.enchantmentScore = enchantmentScore;
        }

        public class_1735 getSlot() {
            return this.slot;
        }

        public int getTotalPrice() {
            return this.totalPrice;
        }

        public int getPricePerItem() {
            return this.pricePerItem;
        }

        public int getCount() {
            return this.count;
        }

        public int getDurabilityPercent() {
            return this.durabilityPercent;
        }

        public int getEnchantmentScore() {
            return this.enchantmentScore;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ItemPriceData)) {
                return false;
            }
            ItemPriceData other = (ItemPriceData)o;
            if (this.getTotalPrice() != other.getTotalPrice()) {
                return false;
            }
            if (this.getPricePerItem() != other.getPricePerItem()) {
                return false;
            }
            if (this.getCount() != other.getCount()) {
                return false;
            }
            if (this.getDurabilityPercent() != other.getDurabilityPercent()) {
                return false;
            }
            if (this.getEnchantmentScore() != other.getEnchantmentScore()) {
                return false;
            }
            class_1735 this$slot = this.getSlot();
            class_1735 other$slot = other.getSlot();
            return !(this$slot == null ? other$slot != null : !this$slot.equals(other$slot));
        }

        public int hashCode() {
            int PRIME = 59;
            int result = 1;
            result = result * 59 + this.getTotalPrice();
            result = result * 59 + this.getPricePerItem();
            result = result * 59 + this.getCount();
            result = result * 59 + this.getDurabilityPercent();
            result = result * 59 + this.getEnchantmentScore();
            class_1735 $slot = this.getSlot();
            result = result * 59 + ($slot == null ? 43 : $slot.hashCode());
            return result;
        }

        public String toString() {
            return "AuctionHelper.ItemPriceData(slot=" + String.valueOf(this.getSlot()) + ", totalPrice=" + this.getTotalPrice() + ", pricePerItem=" + this.getPricePerItem() + ", count=" + this.getCount() + ", durabilityPercent=" + this.getDurabilityPercent() + ", enchantmentScore=" + this.getEnchantmentScore() + ")";
        }
    }
}

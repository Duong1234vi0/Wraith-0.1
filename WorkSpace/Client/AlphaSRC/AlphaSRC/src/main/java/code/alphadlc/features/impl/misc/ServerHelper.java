/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_1713
 *  net.minecraft.class_2596
 *  net.minecraft.class_2664
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.events.chat.ChatEvent;
import code.alphadlc.events.item.UsingItemEvent;
import code.alphadlc.events.keyboard.KeyEvent;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.events.player.InputEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.misc.serverhelper.BravoHvHHandler;
import code.alphadlc.features.impl.misc.serverhelper.CustomHandler;
import code.alphadlc.features.impl.misc.serverhelper.FunTimeHandler;
import code.alphadlc.features.impl.misc.serverhelper.HolyWorldHandler;
import code.alphadlc.features.impl.misc.serverhelper.LonyGriefHandler;
import code.alphadlc.features.impl.misc.serverhelper.MetaHvHHandler;
import code.alphadlc.features.impl.misc.serverhelper.MineBlazeHandler;
import code.alphadlc.features.impl.misc.serverhelper.ReallyworldHandler;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperHandler;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperOverlay;
import code.alphadlc.features.impl.misc.serverhelper.ServerHelperRenderItem;
import code.alphadlc.features.impl.misc.serverhelper.SpookyTimeHandler;
import code.alphadlc.features.impl.misc.serverhelper.SunriseHandler;
import code.alphadlc.features.impl.movement.GuiMove;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.features.module.setting.Setting;
import code.alphadlc.features.module.setting.implement.BindSetting;
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.packet.network.Network;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.interactions.inv.InventoryFlowManager;
import code.alphadlc.utils.interactions.inv.InventoryTask;
import code.alphadlc.utils.interactions.item.ItemUseHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_2596;
import net.minecraft.class_2664;

public class ServerHelper
extends Module {
    private final SelectSetting serverMode = new SelectSetting("\u0421\u0435\u0440\u0432\u0435\u0440", "\u0420\u0435\u0436\u0438\u043c \u0441\u0435\u0440\u0432\u0435\u0440\u0430").value("SpookyTime", "HolyWorld", "LonyGrief", "Reallyworld", "MetaHvH", "BravoHvH", "Sunrise", "MineBlaze", "FunTime", "Custom").selected("SpookyTime");
    private final BooleanSetting onlyHotbar = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0441 \u0445\u043e\u0442\u0431\u0430\u0440\u0430", "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b \u0438\u0437 \u0445\u043e\u0442\u0431\u0430\u0440\u0430").setValue(false);
    private final BindSetting dezorientaciyaKeyBind = this.bind("\u0414\u0435\u0437\u043e\u0440\u0438\u0435\u043d\u0442\u0430\u0446\u0438\u044f", () -> this.isMode("SpookyTime"));
    private final BindSetting yavnayaPylKeyBind = this.bind("\u042f\u0432\u043d\u0430\u044f \u043f\u044b\u043b\u044c", () -> this.isMode("SpookyTime"));
    private final BindSetting plastKeyBind = this.bind("\u041f\u043b\u0430\u0441\u0442", () -> this.isMode("SpookyTime"));
    private final BindSetting trapkaSpookyKeyBind = this.bind("\u0422\u0440\u0430\u043f\u043a\u0430 Spooky", () -> this.isMode("SpookyTime"));
    private final BindSetting ognennyySmerchKeyBind = this.bind("\u041e\u0433\u043d\u0435\u043d\u043d\u044b\u0439 \u0441\u043c\u0435\u0440\u0447", () -> this.isMode("SpookyTime"));
    private final BindSetting snezhokZamorozkaKeyBind = this.bind("\u0421\u043d\u0435\u0436\u043e\u043a \u0437\u0430\u043c\u043e\u0440\u043e\u0437\u043a\u0430", () -> this.isMode("SpookyTime"));
    private final BindSetting bozhyaAuraKeyBind = this.bind("\u0411\u043e\u0436\u044c\u044f \u0430\u0443\u0440\u0430", () -> this.isMode("SpookyTime"));
    private final BindSetting obychnayaLivalkaKeyBind = this.bind("\u041e\u0431\u044b\u0447\u043d\u0430\u044f \u043b\u0438\u0432\u0430\u043b\u043a\u0430", () -> this.isMode("LonyGrief"));
    private final BindSetting unikalnoePeryshkoKeyBind = this.bind("\u0423\u043d\u0438\u043a\u0430\u043b\u044c\u043d\u043e\u0435 \u043f\u0435\u0440\u044b\u0448\u043a\u043e", () -> this.isMode("LonyGrief"));
    private final BindSetting livalkaSPlatformoyKeyBind = this.bind("\u041b\u0438\u0432\u0430\u043b\u043a\u0430 \u0441 \u043f\u043b\u0430\u0442\u0444\u043e\u0440\u043c\u043e\u0439", () -> this.isMode("LonyGrief"));
    private final BindSetting unikalnayaTrapkaKeyBind = this.bind("\u0423\u043d\u0438\u043a\u0430\u043b\u044c\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", () -> this.isMode("LonyGrief"));
    private final BooleanSetting avtoChatIgraUravnenieEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e \u0447\u0430\u0442-\u0438\u0433\u0440\u0430 (\u0443\u0440\u0430\u0432\u043d\u0435\u043d\u0438\u0435)", "\u0420\u0435\u0448\u0430\u0435\u0442 \u0443\u0440\u0430\u0432\u043d\u0435\u043d\u0438\u044f \u0432 \u0447\u0430\u0442\u0435").setValue(false).visible(() -> this.isMode("LonyGrief"));
    private final BindSetting puzyrekOpytaKeyBind = this.bind("\u041f\u0443\u0437\u044b\u0440\u0451\u043a \u043e\u043f\u044b\u0442\u0430", () -> this.isMode("HolyWorld"));
    private final BindSetting trapkaKeyBind = this.bind("\u0422\u0440\u0430\u043f\u043a\u0430", () -> this.isMode("HolyWorld"));
    private final BindSetting vzryvnayaTrapkaKeyBind = this.bind("\u0412\u0437\u0440\u044b\u0432\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", () -> this.isMode("HolyWorld"));
    private final BindSetting proschalnyyGulKeyBind = this.bind("\u041f\u0440\u043e\u0449\u0430\u043b\u044c\u043d\u044b\u0439 \u0433\u0443\u043b", () -> this.isMode("HolyWorld"));
    private final BindSetting vzryvnayaShtuchkaKeyBind = this.bind("\u0412\u0437\u0440\u044b\u0432\u043d\u0430\u044f \u0448\u0442\u0443\u0447\u043a\u0430", () -> this.isMode("HolyWorld"));
    private final BindSetting stanKeyBind = this.bind("\u0421\u0442\u0430\u043d", () -> this.isMode("HolyWorld"));
    private final BindSetting komSnegaKeyBind = this.bind("\u041a\u043e\u043c \u0441\u043d\u0435\u0433\u0430", () -> this.isMode("HolyWorld"));
    private final BindSetting ryukzakKeyBind = this.bind("\u0420\u044e\u043a\u0437\u0430\u043a", () -> this.isMode("HolyWorld"));
    private final BooleanSetting antiCrashEnabled = new BooleanSetting("Anti-Crash", "\u0411\u043b\u043e\u043a\u0438\u0440\u0443\u0435\u0442 \u043a\u0440\u0430\u0448-\u043f\u0430\u043a\u0435\u0442\u044b HolyWorld").setValue(true).visible(() -> this.isMode("HolyWorld"));
    private final BindSetting antiPoletKeyBind = this.bind("\u0410\u043d\u0442\u0438 \u043f\u043e\u043b\u0435\u0442", () -> this.isMode("Reallyworld"));
    private final BindSetting zeleGrinchaKeyBind = this.bind("\u0417\u0435\u043b\u044c\u0435 \u0413\u0440\u0438\u043d\u0447\u0430", () -> this.isMode("Reallyworld"));
    private final BindSetting autoshiftShiftDlyaSharaKeyBind = this.bind("AutoShift (\u0428\u0438\u0444\u0442 \u0434\u043b\u044f \u0448\u0430\u0440\u0430)", () -> this.isMode("Reallyworld"));
    private final BindSetting novogodniyUzhasKeyBind = this.bind("\u041d\u043e\u0432\u043e\u0433\u043e\u0434\u043d\u0438\u0439 \u0443\u0436\u0430\u0441", () -> this.isMode("Reallyworld"));
    private final BindSetting essenciyaKromeshnikaKeyBind = this.bind("\u042d\u0441\u0441\u0435\u043d\u0446\u0438\u044f \u043a\u0440\u043e\u043c\u0435\u0448\u043d\u0438\u043a\u0430", () -> this.isMode("Reallyworld"));
    private final BindSetting snezhokKeyBind = this.bind("\u0421\u043d\u0435\u0436\u043e\u043a", () -> this.isMode("Reallyworld"));
    private final BindSetting lovushkaKeyBind = this.bind("\u041b\u043e\u0432\u0443\u0448\u043a\u0430", () -> this.isMode("Reallyworld"));
    private final BooleanSetting zakryvatMenyuEnabled = new BooleanSetting("\u0417\u0430\u043a\u0440\u044b\u0432\u0430\u0442\u044c \u043c\u0435\u043d\u044e", "\u0417\u0430\u043a\u0440\u044b\u0432\u0430\u0435\u0442 \u043c\u0435\u043d\u044e Reallyworld").setValue(false).visible(() -> this.isMode("Reallyworld"));
    private final BooleanSetting filtrChataEnabled = new BooleanSetting("\u0424\u0438\u043b\u044c\u0442\u0440 \u0447\u0430\u0442\u0430", "\u0424\u0438\u043b\u044c\u0442\u0440\u0443\u0435\u0442 \u0437\u0430\u043f\u0440\u0435\u0449\u0451\u043d\u043d\u044b\u0435 \u0441\u043b\u043e\u0432\u0430").setValue(false).visible(() -> this.isMode("Reallyworld"));
    private final BooleanSetting autoFixAllEnabled = new BooleanSetting("Auto /fix all", "\u0410\u0432\u0442\u043e /fix all \u043f\u043e\u0441\u043b\u0435 PvP").setValue(false).visible(() -> this.isMode("Reallyworld"));
    private final BindSetting svapGolovyKeyBind = this.bind("\u0421\u0432\u0430\u043f \u0433\u043e\u043b\u043e\u0432\u044b", () -> this.isMode("MetaHvH"));
    private final BindSetting svapBotinokKeyBind = this.bind("\u0421\u0432\u0430\u043f \u0431\u043e\u0442\u0438\u043d\u043e\u043a", () -> this.isMode("MetaHvH"));
    private final BindSetting avtoEcVklVyklKeyBind = this.bind("\u0410\u0432\u0442\u043e EC (\u0432\u043a\u043b/\u0432\u044b\u043a\u043b)", () -> this.isMode("MetaHvH"));
    private final SelectSetting vyborShlemaMode = new SelectSetting("\u0412\u044b\u0431\u043e\u0440 \u0448\u043b\u0435\u043c\u0430", "\u0422\u0438\u043f \u0448\u043b\u0435\u043c\u0430 \u0434\u043b\u044f MetaHvH").value("\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439", "\u0417\u043e\u043b\u043e\u0442\u043e\u0439").selected("\u041d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439").visible(() -> this.isMode("MetaHvH"));
    private final SliderSettings zaderzhkaSvapaSetting = new SliderSettings("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0441\u0432\u0430\u043f\u0430", "\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0441\u0432\u0430\u043f\u0430 \u0433\u043e\u043b\u043e\u0432\u044b").setValue(2.0f).range(1.0f, 5.0f).step(1.0f).visible(() -> this.isMode("MetaHvH"));
    private final BooleanSetting avtoBotinkiVodaEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e \u0431\u043e\u0442\u0438\u043d\u043a\u0438 (\u0432\u043e\u0434\u0430)", "\u041c\u0435\u043d\u044f\u0435\u0442 \u0431\u043e\u0442\u0438\u043d\u043a\u0438 \u0432 \u0432\u043e\u0434\u0435").setValue(false).visible(() -> this.isMode("MetaHvH"));
    private final SliderSettings radiusEcSetting = new SliderSettings("\u0420\u0430\u0434\u0438\u0443\u0441 EC", "\u0420\u0430\u0434\u0438\u0443\u0441 \u043f\u043e\u0438\u0441\u043a\u0430 EC").setValue(3.0f).range(1.0f, 5.0f).step(1.0f).visible(() -> this.isMode("MetaHvH"));
    private final BindSetting zeleKilleraKeyBind = this.bind("\u0417\u0435\u043b\u044c\u0435 \u043a\u0438\u043b\u043b\u0435\u0440\u0430", () -> this.isMode("BravoHvH"));
    private final BindSetting zeleMedikaKeyBind = this.bind("\u0417\u0435\u043b\u044c\u0435 \u043c\u0435\u0434\u0438\u043a\u0430", () -> this.isMode("BravoHvH"));
    private final BindSetting pozvatPomoschKeyBind = this.bind("\u041f\u043e\u0437\u0432\u0430\u0442\u044c \u043f\u043e\u043c\u043e\u0449\u044c", () -> this.isMode("BravoHvH"));
    private final SliderSettings porogHpDlyaAvtoSetting = new SliderSettings("\u041f\u043e\u0440\u043e\u0433 HP \u0434\u043b\u044f \u0430\u0432\u0442\u043e", "HP \u0434\u043b\u044f \u0430\u0432\u0442\u043e \u043c\u0435\u0434\u0438\u043a\u0430").setValue(6.0f).range(1.0f, 20.0f).step(1.0f).visible(() -> this.isMode("BravoHvH"));
    private final BooleanSetting avtoMedikPriNizkomHpEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e \u043c\u0435\u0434\u0438\u043a \u043f\u0440\u0438 \u043d\u0438\u0437\u043a\u043e\u043c HP", "\u0410\u0432\u0442\u043e \u0437\u0435\u043b\u044c\u0435 \u043c\u0435\u0434\u0438\u043a\u0430").setValue(false).visible(() -> this.isMode("BravoHvH"));
    private final BindSetting obychnayaTrapkaKeyBind = this.bind("\u041e\u0431\u044b\u0447\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", () -> this.isMode("Sunrise"));
    private final BindSetting horoshayaTrapkaKeyBind = this.bind("\u0425\u043e\u0440\u043e\u0448\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", () -> this.isMode("Sunrise"));
    private final BindSetting otlichnayaTrapkaKeyBind = this.bind("\u041e\u0442\u043b\u0438\u0447\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", () -> this.isMode("Sunrise"));
    private final BindSetting epicheskayaTrapkaKeyBind = this.bind("\u042d\u043f\u0438\u0447\u0435\u0441\u043a\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", () -> this.isMode("Sunrise"));
    private final BindSetting legendarnayaTrapkaKeyBind = this.bind("\u041b\u0435\u0433\u0435\u043d\u0434\u0430\u0440\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430", () -> this.isMode("Sunrise"));
    private final BindSetting livalkaKeyBind = this.bind("\u041b\u0438\u0432\u0430\u043b\u043a\u0430", () -> this.isMode("Sunrise"));
    private final BindSetting amuletTeleportaciiKeyBind = this.bind("\u0410\u043c\u0443\u043b\u0435\u0442 \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0430\u0446\u0438\u0438", () -> this.isMode("Sunrise"));
    private final BindSetting skoplenieSvetaKeyBind = this.bind("\u0421\u043a\u043e\u043f\u043b\u0435\u043d\u0438\u0435 \u0441\u0432\u0435\u0442\u0430", () -> this.isMode("Sunrise"));
    private final BindSetting volnaOgnyaKeyBind = this.bind("\u0412\u043e\u043b\u043d\u0430 \u043e\u0433\u043d\u044f", () -> this.isMode("Sunrise"));
    private final BindSetting poroshokVozvrascheniyaKeyBind = this.bind("\u041f\u043e\u0440\u043e\u0448\u043e\u043a \u0432\u043e\u0437\u0432\u0440\u0430\u0449\u0435\u043d\u0438\u044f", () -> this.isMode("Sunrise"));
    private final BindSetting poroshokDezorganizaciiKeyBind = this.bind("\u041f\u043e\u0440\u043e\u0448\u043e\u043a \u0434\u0435\u0437\u043e\u0440\u0433\u0430\u043d\u0438\u0437\u0430\u0446\u0438\u0438", () -> this.isMode("Sunrise"));
    private final BindSetting razrushitelStoekKeyBind = this.bind("\u0420\u0430\u0437\u0440\u0443\u0448\u0438\u0442\u0435\u043b\u044c \u0421\u0442\u043e\u0435\u043a", () -> this.isMode("Sunrise"));
    private final BindSetting fitilKeyBind = this.bind("\u0424\u0438\u0442\u0438\u043b\u044c", () -> this.isMode("MineBlaze"));
    private final BindSetting perestavlyayuscheeZeleKeyBind = this.bind("\u041f\u0435\u0440\u0435\u0441\u0442\u0430\u0432\u043b\u044f\u044e\u0449\u0435\u0435 \u0437\u0435\u043b\u044c\u0435", () -> this.isMode("MineBlaze"));
    private final BindSetting vzryvnoeZeleKeyBind = this.bind("\u0412\u0437\u0440\u044b\u0432\u043d\u043e\u0435 \u0437\u0435\u043b\u044c\u0435", () -> this.isMode("MineBlaze"));
    private final BindSetting trapaKeyBind = this.bind("\u0422\u0440\u0430\u043f\u0430", () -> this.isMode("FunTime"));
    private final BindSetting dezorentKeyBind = this.bind("\u0414\u0435\u0437\u043e\u0440\u0435\u043d\u0442", () -> this.isMode("FunTime"));
    private final BindSetting plastKeyBind2 = this.bind("\u041f\u043b\u0430\u0441\u0442", () -> this.isMode("FunTime"));
    private final BindSetting yavnayaPylKeyBind2 = this.bind("\u042f\u0432\u043d\u0430\u044f \u043f\u044b\u043b\u044c", () -> this.isMode("FunTime"));
    private final BindSetting bozhyaAuraKeyBind2 = this.bind("\u0411\u043e\u0436\u044c\u044f \u0430\u0443\u0440\u0430", () -> this.isMode("FunTime"));
    private final BindSetting ognenSmerchKeyBind = this.bind("\u041e\u0433\u043d\u0435\u043d \u0441\u043c\u0435\u0440\u0447", () -> this.isMode("FunTime"));
    private final BindSetting snezhokKeyBind2 = this.bind("\u0421\u043d\u0435\u0436\u043e\u043a", () -> this.isMode("FunTime"));
    private final CustomHandler customHandler = new CustomHandler();
    private final Map<String, ServerHelperHandler> handlers = new HashMap<String, ServerHelperHandler>();
    private final ServerHelperOverlay overlay = new ServerHelperOverlay();
    private final List<BindSetting> customDynamicBinds = new ArrayList<BindSetting>();
    private int usePhase;
    private int savedSlot = -1;
    private int inventorySlot = -1;
    private int executeTick;
    private int inputDelayTicks;
    private boolean wasInPvp;
    private int nextFixTick;
    private ServerHelperHandler cachedRenderHandler;
    private int cachedRenderTick = -1;
    private boolean cachedOnlyHotbar;
    private String cachedMode = "";
    private List<ServerHelperRenderItem> cachedRenderItems = List.of();

    public static ServerHelper getInstance() {
        return Instance.get(ServerHelper.class);
    }

    public ServerHelper() {
        super("ServerHelper", "ServerHelper", ModuleCategory.MISC);
        SpookyTimeHandler spooky = new SpookyTimeHandler(List.of(this.dezorientaciyaKeyBind, this.yavnayaPylKeyBind, this.plastKeyBind, this.trapkaSpookyKeyBind, this.ognennyySmerchKeyBind, this.snezhokZamorozkaKeyBind, this.bozhyaAuraKeyBind));
        LonyGriefHandler lony = new LonyGriefHandler(List.of(this.obychnayaLivalkaKeyBind, this.unikalnoePeryshkoKeyBind, this.livalkaSPlatformoyKeyBind, this.unikalnayaTrapkaKeyBind), () -> this.avtoChatIgraUravnenieEnabled.isValue() && this.isMode("LonyGrief"));
        HolyWorldHandler holy = new HolyWorldHandler(List.of(this.puzyrekOpytaKeyBind, this.trapkaKeyBind, this.vzryvnayaTrapkaKeyBind, this.proschalnyyGulKeyBind, this.vzryvnayaShtuchkaKeyBind, this.stanKeyBind, this.komSnegaKeyBind), this.antiCrashEnabled, this.ryukzakKeyBind);
        ReallyworldHandler rw = new ReallyworldHandler(List.of(this.antiPoletKeyBind, this.zeleGrinchaKeyBind, this.autoshiftShiftDlyaSharaKeyBind, this.novogodniyUzhasKeyBind, this.essenciyaKromeshnikaKeyBind, this.snezhokKeyBind, this.lovushkaKeyBind), this.zakryvatMenyuEnabled, this.filtrChataEnabled);
        MetaHvHHandler meta = new MetaHvHHandler(List.of(this.svapGolovyKeyBind, this.svapBotinokKeyBind, this.avtoEcVklVyklKeyBind), this.vyborShlemaMode, this.zaderzhkaSvapaSetting, this.avtoBotinkiVodaEnabled, this.radiusEcSetting);
        BravoHvHHandler bravo = new BravoHvHHandler(List.of(this.zeleKilleraKeyBind, this.zeleMedikaKeyBind, this.pozvatPomoschKeyBind), this.porogHpDlyaAvtoSetting, this.avtoMedikPriNizkomHpEnabled);
        SunriseHandler sunrise = new SunriseHandler(List.of(this.obychnayaTrapkaKeyBind, this.horoshayaTrapkaKeyBind, this.otlichnayaTrapkaKeyBind, this.epicheskayaTrapkaKeyBind, this.legendarnayaTrapkaKeyBind, this.livalkaKeyBind, this.amuletTeleportaciiKeyBind, this.skoplenieSvetaKeyBind, this.volnaOgnyaKeyBind, this.poroshokVozvrascheniyaKeyBind, this.poroshokDezorganizaciiKeyBind, this.razrushitelStoekKeyBind));
        MineBlazeHandler mineBlaze = new MineBlazeHandler(List.of(this.fitilKeyBind, this.perestavlyayuscheeZeleKeyBind, this.vzryvnoeZeleKeyBind));
        FunTimeHandler funTime = new FunTimeHandler(List.of(this.trapaKeyBind, this.dezorentKeyBind, this.plastKeyBind2, this.yavnayaPylKeyBind2, this.bozhyaAuraKeyBind2, this.ognenSmerchKeyBind, this.snezhokKeyBind2));
        this.handlers.put("SpookyTime", spooky);
        this.handlers.put("LonyGrief", lony);
        this.handlers.put("HolyWorld", holy);
        this.handlers.put("Reallyworld", rw);
        this.handlers.put("MetaHvH", meta);
        this.handlers.put("BravoHvH", bravo);
        this.handlers.put("Sunrise", sunrise);
        this.handlers.put("MineBlaze", mineBlaze);
        this.handlers.put("FunTime", funTime);
        this.handlers.put("Custom", this.customHandler);
        this.setup(this.serverMode, this.onlyHotbar, this.dezorientaciyaKeyBind, this.yavnayaPylKeyBind, this.plastKeyBind, this.trapkaSpookyKeyBind, this.ognennyySmerchKeyBind, this.snezhokZamorozkaKeyBind, this.bozhyaAuraKeyBind, this.obychnayaLivalkaKeyBind, this.unikalnoePeryshkoKeyBind, this.livalkaSPlatformoyKeyBind, this.unikalnayaTrapkaKeyBind, this.avtoChatIgraUravnenieEnabled, this.antiPoletKeyBind, this.zeleGrinchaKeyBind, this.autoshiftShiftDlyaSharaKeyBind, this.novogodniyUzhasKeyBind, this.essenciyaKromeshnikaKeyBind, this.snezhokKeyBind, this.lovushkaKeyBind, this.zakryvatMenyuEnabled, this.filtrChataEnabled, this.autoFixAllEnabled, this.puzyrekOpytaKeyBind, this.trapkaKeyBind, this.vzryvnayaTrapkaKeyBind, this.proschalnyyGulKeyBind, this.vzryvnayaShtuchkaKeyBind, this.stanKeyBind, this.komSnegaKeyBind, this.ryukzakKeyBind, this.antiCrashEnabled, this.svapGolovyKeyBind, this.svapBotinokKeyBind, this.avtoEcVklVyklKeyBind, this.vyborShlemaMode, this.zaderzhkaSvapaSetting, this.avtoBotinkiVodaEnabled, this.radiusEcSetting, this.zeleKilleraKeyBind, this.zeleMedikaKeyBind, this.pozvatPomoschKeyBind, this.porogHpDlyaAvtoSetting, this.avtoMedikPriNizkomHpEnabled, this.obychnayaTrapkaKeyBind, this.horoshayaTrapkaKeyBind, this.otlichnayaTrapkaKeyBind, this.epicheskayaTrapkaKeyBind, this.legendarnayaTrapkaKeyBind, this.livalkaKeyBind, this.amuletTeleportaciiKeyBind, this.skoplenieSvetaKeyBind, this.volnaOgnyaKeyBind, this.poroshokVozvrascheniyaKeyBind, this.poroshokDezorganizaciiKeyBind, this.razrushitelStoekKeyBind, this.fitilKeyBind, this.perestavlyayuscheeZeleKeyBind, this.vzryvnoeZeleKeyBind, this.trapaKeyBind, this.dezorentKeyBind, this.plastKeyBind2, this.yavnayaPylKeyBind2, this.bozhyaAuraKeyBind2, this.ognenSmerchKeyBind, this.snezhokKeyBind2, this.customHandler.getOpenGuiBind());
        this.refreshCustomBinds();
    }

    private BindSetting bind(String name, Supplier<Boolean> visible) {
        return new BindSetting(name, name).visible(visible);
    }

    private boolean isMode(String mode) {
        return this.serverMode.isSelected(mode);
    }

    public boolean isCustomMode() {
        return this.isMode("Custom");
    }

    public String getServerMode() {
        return this.serverMode.getSelected();
    }

    public void refreshCustomBinds() {
        this.customDynamicBinds.clear();
        this.customDynamicBinds.addAll(this.customHandler.getDynamicBinds());
        this.customDynamicBinds.forEach(xva$0 -> this.setup((Setting)xva$0));
    }

    public void queueSlotUse(int slot) {
        if (ServerHelper.mc.field_1724 == null || slot < 0) {
            return;
        }
        if (slot < 9) {
            this.savedSlot = ServerHelper.mc.field_1724.method_31548().field_7545;
            ServerHelper.mc.field_1724.method_31548().field_7545 = slot;
            this.syncSelectedSlot();
            this.usePhase = 6;
            this.executeTick = ServerHelper.mc.field_1724.field_6012 + 2;
        } else {
            this.inputDelayTicks = 2;
            this.usePhase = 1;
            this.inventorySlot = slot;
            this.savedSlot = ServerHelper.mc.field_1724.method_31548().field_7545;
        }
    }

    private ServerHelperHandler currentHandler() {
        return this.handlers.get(this.serverMode.getSelected());
    }

    private void applyOnlyHotbar(ServerHelperHandler handler) {
        if (this.helperSupportsHotbarOnly(handler)) {
            handler.setOnlyHotbar(this.onlyHotbar.isValue());
        }
    }

    private boolean helperSupportsHotbarOnly(ServerHelperHandler helper) {
        return helper instanceof SpookyTimeHandler || helper instanceof LonyGriefHandler || helper instanceof HolyWorldHandler || helper instanceof SunriseHandler || helper instanceof FunTimeHandler || helper instanceof CustomHandler;
    }

    private List<ServerHelperRenderItem> getRenderItems(ServerHelperHandler helper) {
        int tick = ServerHelper.mc.field_1724.field_6012;
        String mode = this.serverMode.getSelected();
        boolean onlyBar = this.onlyHotbar.isValue();
        if (helper == this.cachedRenderHandler && tick == this.cachedRenderTick && onlyBar == this.cachedOnlyHotbar && mode.equals(this.cachedMode)) {
            return this.cachedRenderItems;
        }
        this.cachedRenderHandler = helper;
        this.cachedRenderTick = tick;
        this.cachedOnlyHotbar = onlyBar;
        this.cachedMode = mode;
        this.cachedRenderItems = helper.buildRenderItems(onlyBar);
        return this.cachedRenderItems;
    }

    @EventHandler
    public void onKey(KeyEvent event) {
        if (ServerHelper.mc.field_1755 != null || event.action() != 1) {
            return;
        }
        ServerHelperHandler helper = this.currentHandler();
        if (helper == null) {
            return;
        }
        this.applyOnlyHotbar(helper);
        helper.onKey(event.key());
    }

    @EventHandler
    public void onTick(TickEvent event) {
        HolyWorldHandler holy;
        if (ServerHelper.mc.field_1724 == null) {
            return;
        }
        if (this.inputDelayTicks > 0) {
            --this.inputDelayTicks;
        }
        this.handleAutoFixAll();
        ServerHelperHandler helper = this.currentHandler();
        if (helper == null) {
            return;
        }
        if (helper instanceof HolyWorldHandler && (holy = (HolyWorldHandler)helper).isBackpackBusy()) {
            holy.tick();
            return;
        }
        helper.tick();
        this.processUseStateMachine(helper);
    }

    private void processUseStateMachine(ServerHelperHandler helper) {
        if (this.usePhase == 6 && ServerHelper.mc.field_1724.field_6012 >= this.executeTick) {
            PlayerInteractionHelper.interactItem(class_1268.field_5808);
            this.usePhase = 5;
            this.executeTick = ServerHelper.mc.field_1724.field_6012 + 2;
        }
        if (this.usePhase == 5 && ServerHelper.mc.field_1724.field_6012 >= this.executeTick) {
            ServerHelper.mc.field_1724.method_31548().field_7545 = this.savedSlot;
            this.syncSelectedSlot();
            this.savedSlot = -1;
            this.usePhase = 0;
        }
        if (this.usePhase == 1 && this.inputDelayTicks == 0) {
            this.usePhase = 2;
        }
        if (this.usePhase == 2 && this.inventorySlot != -1) {
            this.handleInventorySwap(true);
            InventoryTask.clickSlot(this.inventorySlot, this.savedSlot, class_1713.field_7791, false);
            if (this.isFuntimeGuiMove()) {
                ServerHelper.mc.field_1724.method_7346();
            }
            this.usePhase = 3;
            this.executeTick = ServerHelper.mc.field_1724.field_6012 + 2;
        }
        if (this.usePhase == 3 && ServerHelper.mc.field_1724.field_6012 >= this.executeTick) {
            PlayerInteractionHelper.interactItem(class_1268.field_5808);
            this.usePhase = 4;
            this.executeTick = ServerHelper.mc.field_1724.field_6012 + 3;
        }
        if (this.usePhase == 4 && ServerHelper.mc.field_1724.field_6012 >= this.executeTick) {
            this.handleInventorySwap(false);
            InventoryTask.clickSlot(this.inventorySlot, this.savedSlot, class_1713.field_7791, false);
            if (this.isFuntimeGuiMove()) {
                ServerHelper.mc.field_1724.method_7346();
            }
            this.usePhase = 0;
            this.inventorySlot = -1;
            this.savedSlot = -1;
            helper.clearPending();
        }
        if (!helper.hasPendingUse() || this.usePhase != 0) {
            return;
        }
        int slot = helper.getPendingSlot();
        if (slot == -1) {
            helper.clearPending();
            return;
        }
        if (slot < 9) {
            this.savedSlot = ServerHelper.mc.field_1724.method_31548().field_7545;
            ServerHelper.mc.field_1724.method_31548().field_7545 = slot;
            this.syncSelectedSlot();
            this.usePhase = 6;
            this.executeTick = ServerHelper.mc.field_1724.field_6012 + 2;
        } else {
            this.inputDelayTicks = 2;
            this.usePhase = 1;
            this.inventorySlot = slot;
            this.savedSlot = ServerHelper.mc.field_1724.method_31548().field_7545;
        }
        helper.clearPending();
    }

    private void handleInventorySwap(boolean beforeUse) {
        GuiMove guiMove = GuiMove.getInstance();
        if (this.isFuntimeGuiMove() && beforeUse) {
            ServerHelper.mc.field_1724.method_7346();
        }
        if (guiMove != null && guiMove.isState()) {
            if (beforeUse) {
                InventoryFlowManager.disableMoveKeys();
            } else {
                InventoryFlowManager.enableMoveKeys();
            }
        }
    }

    private boolean isFuntimeGuiMove() {
        GuiMove guiMove = GuiMove.getInstance();
        return guiMove != null && guiMove.isState() && GuiMove.mode.isSelected("Funtime");
    }

    private boolean isGuiMoveActive() {
        GuiMove guiMove = GuiMove.getInstance();
        return guiMove != null && guiMove.isState();
    }

    private void handleAutoFixAll() {
        if (!this.isMode("Reallyworld") || !this.autoFixAllEnabled.isValue()) {
            this.wasInPvp = Network.isPvp();
            return;
        }
        boolean inPvp = Network.isPvp();
        if (this.wasInPvp && !inPvp && ServerHelper.mc.field_1724.field_6012 >= this.nextFixTick) {
            ServerHelper.mc.field_1724.field_3944.method_45730("fix all");
            this.nextFixTick = ServerHelper.mc.field_1724.field_6012 + 40;
        }
        this.wasInPvp = inPvp;
    }

    @EventHandler
    public void onInput(InputEvent event) {
        if (ServerHelper.mc.field_1724 != null && ItemUseHelper.isUsingPriorityItem((class_1657)ServerHelper.mc.field_1724)) {
            return;
        }
        if (this.inputDelayTicks > 0 || this.usePhase >= 1 && this.usePhase <= 4) {
            event.inputNone();
        }
    }

    @EventHandler
    public void onUsingItem(UsingItemEvent event) {
        if (ServerHelper.mc.field_1724 != null && this.isConsumingUse(event)) {
            return;
        }
        if (ServerHelper.mc.field_1724 != null && ItemUseHelper.isUsingPriorityItem((class_1657)ServerHelper.mc.field_1724)) {
            return;
        }
        if (this.inputDelayTicks > 0 || this.usePhase >= 1 && this.usePhase <= 6) {
            event.cancel();
        }
    }

    private boolean isConsumingUse(UsingItemEvent event) {
        if (event.getType() == -1) {
            if (event.getHand() != null) {
                return ItemUseHelper.isPriorityUseItem(ItemUseHelper.getStackForHand((class_1657)ServerHelper.mc.field_1724, event.getHand()));
            }
            return ItemUseHelper.isPriorityUseItem(ServerHelper.mc.field_1724.method_6047()) || ItemUseHelper.isPriorityUseItem(ServerHelper.mc.field_1724.method_6079());
        }
        if (ServerHelper.mc.field_1724.method_6115()) {
            return ItemUseHelper.isPriorityUseItem(ServerHelper.mc.field_1724.method_6030());
        }
        return false;
    }

    @EventHandler
    public void onPacket(PacketEvent event) {
        class_2596<?> class_25962;
        if (ServerHelper.mc.field_1724 == null || ServerHelper.mc.field_1687 == null) {
            return;
        }
        ServerHelperHandler helper = this.currentHandler();
        if (helper != null && !event.isSend()) {
            helper.onPacketReceive(event);
        }
        if (!event.isSend() && this.isMode("HolyWorld") && this.antiCrashEnabled.isValue() && (class_25962 = event.getPacket()) instanceof class_2664) {
            class_2664 explosion = (class_2664)class_25962;
            explosion.comp_2884().ifPresent(knockback -> {
                if (Double.isInfinite(knockback.field_1352) || Double.isInfinite(knockback.field_1351) || Double.isInfinite(knockback.field_1350)) {
                    event.cancel();
                }
            });
        }
    }

    @EventHandler
    public void onChat(ChatEvent event) {
        ServerHelperHandler helper = this.currentHandler();
        if (helper != null) {
            helper.onChatSend(event);
        }
    }

    private void syncSelectedSlot() {
        if (ServerHelper.mc.field_1761 != null) {
            ServerHelper.mc.field_1761.method_2911();
        }
    }

    private void resetState() {
        this.usePhase = 0;
        this.savedSlot = -1;
        this.inventorySlot = -1;
        this.executeTick = 0;
        this.inputDelayTicks = 0;
        this.wasInPvp = false;
        this.nextFixTick = 0;
        this.cachedRenderItems = List.of();
        this.cachedRenderHandler = null;
        this.cachedRenderTick = -1;
        ServerHelperHandler helper = this.currentHandler();
        if (helper != null) {
            helper.clearPending();
        }
    }

    @Override
    public void deactivate() {
        super.deactivate();
        this.resetState();
    }
}

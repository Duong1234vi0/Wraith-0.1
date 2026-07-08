/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_2596
 *  net.minecraft.class_2678
 *  net.minecraft.class_2724
 *  net.minecraft.class_2799
 *  net.minecraft.class_2799$class_2800
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 */
package code.alphadlc.utils.interactions.item;

import code.alphadlc.AlphaDLC;
import code.alphadlc.events.packet.PacketEvent;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.managers.event.EventManager;
import code.alphadlc.utils.display.interfaces.QuickImports;
import java.lang.runtime.SwitchBootstraps;
import java.util.Objects;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_2596;
import net.minecraft.class_2678;
import net.minecraft.class_2724;
import net.minecraft.class_2799;
import net.minecraft.class_2846;

public class ItemToolkit
implements QuickImports {
    public static final ItemToolkit INSTANCE = new ItemToolkit();
    public boolean useItem;
    public boolean releaseItem = true;

    public ItemToolkit() {
        AlphaDLC.getInstance().getEventManager();
        EventManager.register(this);
    }

    @EventHandler
    public void onPacket(PacketEvent e) {
        class_2596<?> class_25962 = e.getPacket();
        Objects.requireNonNull(class_25962);
        class_2596<?> class_25963 = class_25962;
        int n = 0;
        block6: while (true) {
            switch (SwitchBootstraps.typeSwitch("typeSwitch", new Object[]{class_2846.class, class_2799.class, class_2724.class, class_2678.class}, class_25963, n)) {
                case 0: {
                    class_2846 player = (class_2846)class_25963;
                    if (!player.method_12363().equals((Object)class_2846.class_2847.field_12974)) {
                        n = 1;
                        continue block6;
                    }
                    this.releaseItem = true;
                    break block6;
                }
                case 1: {
                    class_2799 status = (class_2799)class_25963;
                    if (!status.method_12119().equals((Object)class_2799.class_2800.field_12774)) {
                        n = 2;
                        continue block6;
                    }
                    this.releaseItem = true;
                    break block6;
                }
                case 2: {
                    class_2724 respawn = (class_2724)class_25963;
                    this.releaseItem = true;
                    break block6;
                }
                case 3: {
                    class_2678 join = (class_2678)class_25963;
                    this.releaseItem = true;
                    break block6;
                }
            }
            break;
        }
    }

    public void useHand(class_1268 hand) {
        if (this.releaseItem) {
            ItemToolkit.mc.field_1761.method_2919((class_1657)ItemToolkit.mc.field_1724, hand);
            this.releaseItem = false;
        }
        this.useItem = true;
    }

    public void setUseItem(boolean useItem) {
        this.useItem = useItem;
    }

    public void setReleaseItem(boolean releaseItem) {
        this.releaseItem = releaseItem;
    }

    public boolean isUseItem() {
        return this.useItem;
    }

    public boolean isReleaseItem() {
        return this.releaseItem;
    }
}

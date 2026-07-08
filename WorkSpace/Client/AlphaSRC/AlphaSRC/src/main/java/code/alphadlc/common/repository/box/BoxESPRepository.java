/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_1922
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_3545
 */
package code.alphadlc.common.repository.box;

import code.alphadlc.events.block.BlockUpdateEvent;
import code.alphadlc.events.render.WorldLoadEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.managers.event.EventManager;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.interfaces.QuickLogger;
import code.alphadlc.utils.display.render.geometry.Render3D;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.math.projection.Projection;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_1299;
import net.minecraft.class_1922;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3545;

public class BoxESPRepository
implements QuickImports,
QuickLogger {
    private final Map<class_2338, class_3545<class_265, Integer>> boxes = new HashMap<class_2338, class_3545<class_265, Integer>>();
    public final Map<class_1299<?>, Integer> entities = new HashMap();
    public final Map<class_2248, Integer> blocks = new HashMap<class_2248, Integer>();
    public boolean drawFill = true;

    public BoxESPRepository(EventManager eventManager) {
        EventManager.register(this);
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent e) {
        this.boxes.forEach((pos, pair) -> {
            if (this.drawFill) {
                Render3D.drawShape(pos, (class_265)pair.method_15442(), (Integer)pair.method_15441(), 1.0f);
            } else {
                Render3D.drawShapeAlternative(pos, (class_265)pair.method_15442(), (Integer)pair.method_15441(), 1.0f, false, false);
            }
        });
        PlayerInteractionHelper.streamEntities().filter(ent -> this.entities.containsKey(ent.method_5864()) && ent != BoxESPRepository.mc.field_1724).forEach(ent -> {
            int entityColor = this.entities.get(ent.method_5864());
            int color = entityColor == 0 ? ColorAssist.getClientColor() : entityColor;
            class_238 box = ent.method_5829().method_997(Calculate.interpolate(ent).method_1020(ent.method_19538()));
            if (Projection.canSee(box)) {
                Render3D.drawBox(box, color, 1.0f);
            }
        });
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent e) {
        this.boxes.clear();
    }

    @EventHandler
    public void onBlockUpdate(BlockUpdateEvent e) {
        class_2338 pos = e.pos();
        class_2680 state = e.state();
        class_2248 block = state.method_26204();
        switch (e.type()) {
            case LOAD: {
                if (!this.blocks.containsKey(block)) break;
                this.putBox(pos, state, block);
                break;
            }
            case UPDATE: {
                if (this.blocks.containsKey(block) && !this.boxes.containsKey(pos)) {
                    this.putBox(pos, state, block);
                }
                if (!this.boxes.containsKey(pos) || !state.method_26215() && ((class_265)this.boxes.get(pos).method_15442()).equals(state.method_26218((class_1922)BoxESPRepository.mc.field_1687, pos))) break;
                this.boxes.remove(pos);
                break;
            }
            case UNLOAD: {
                this.boxes.remove(pos);
            }
        }
    }

    private void putBox(class_2338 pos, class_2680 state, class_2248 block) {
        class_265 shape = state.method_26218((class_1922)BoxESPRepository.mc.field_1687, pos);
        int blockColor = this.blocks.get(block);
        int color = blockColor == 0 ? ColorAssist.replAlpha(state.method_26205((class_1922)BoxESPRepository.mc.field_1687, (class_2338)pos).field_16011, 1.0f) : blockColor;
        this.boxes.put(pos, (class_3545<class_265, Integer>)new class_3545((Object)shape, (Object)color));
    }
}

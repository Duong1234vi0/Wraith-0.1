/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1542
 *  net.minecraft.class_1665
 *  net.minecraft.class_1667
 *  net.minecraft.class_1676
 *  net.minecraft.class_1680
 *  net.minecraft.class_1681
 *  net.minecraft.class_1683
 *  net.minecraft.class_1684
 *  net.minecraft.class_1685
 *  net.minecraft.class_1686
 *  net.minecraft.class_1753
 *  net.minecraft.class_1764
 *  net.minecraft.class_1771
 *  net.minecraft.class_1776
 *  net.minecraft.class_1779
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1823
 *  net.minecraft.class_1828
 *  net.minecraft.class_1835
 *  net.minecraft.class_1937
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2374
 *  net.minecraft.class_239
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_332
 *  net.minecraft.class_3486
 *  net.minecraft.class_3532
 *  net.minecraft.class_3857
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 *  net.minecraft.class_4587
 *  net.minecraft.class_4587$class_4665
 *  net.minecraft.class_7833
 *  net.minecraft.class_9278
 *  net.minecraft.class_9334
 *  org.joml.Quaternionf
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.events.render.DrawEvent;
import code.alphadlc.events.render.WorldRenderEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.geometry.Render3D;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.features.aura.utils.RaycastAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.features.aura.warp.TurnsConnection;
import code.alphadlc.utils.interactions.interact.PlayerInteractionHelper;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.math.projection.Projection;
import java.awt.Color;
import java.lang.runtime.SwitchBootstraps;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1542;
import net.minecraft.class_1665;
import net.minecraft.class_1667;
import net.minecraft.class_1676;
import net.minecraft.class_1680;
import net.minecraft.class_1681;
import net.minecraft.class_1683;
import net.minecraft.class_1684;
import net.minecraft.class_1685;
import net.minecraft.class_1686;
import net.minecraft.class_1753;
import net.minecraft.class_1764;
import net.minecraft.class_1771;
import net.minecraft.class_1776;
import net.minecraft.class_1779;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1823;
import net.minecraft.class_1828;
import net.minecraft.class_1835;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_332;
import net.minecraft.class_3486;
import net.minecraft.class_3532;
import net.minecraft.class_3857;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_9278;
import net.minecraft.class_9334;
import org.joml.Quaternionf;

public class Prediction
extends Module {
    final List<Point> points = new ArrayList<Point>();

    public static Prediction getInstance() {
        return Instance.get(Prediction.class);
    }

    public Prediction() {
        super("Prediction", "Prediction", ModuleCategory.RENDER);
    }

    @EventHandler
    public void onDraw(DrawEvent e) {
        if (e == null || e.getDrawContext() == null) {
            return;
        }
        class_332 context = e.getDrawContext();
        for (Point point : this.points) {
            class_243 vec3d = Projection.worldSpaceToScreenSpace(point.pos);
            int ticks = point.ticks;
            if (!Projection.canSee(point.pos)) continue;
            FontRenderer font = Fonts.getSize(13);
            double time = (double)(ticks * 50) / 1000.0;
            String text = String.format("%.1f", time).replace(",", ".").replace(".0", "") + " \u0441\u0435\u043a";
            float textWidth = font.getStringWidth(text);
            float posX = (float)(vec3d.method_10216() + (double)(textWidth / 2.0f) - 6.0);
            float posY = (float)(vec3d.method_10214() + 4.0);
            float padding = 3.0f;
            float iconSize = 8.0f;
            rectangle.render(ShapeProperties.create(context.method_51448(), posX - textWidth + iconSize + padding - 8.0f, posY - padding, padding + textWidth + padding + 8.0f, 10.0).round(1.5f).color(new Color(27, 27, 30, 255).getRGB()).build());
            font.drawString(context.method_51448(), text, posX - textWidth + 8.0f + padding * 2.0f, posY + 0.5f, -1);
            Render2D.defaultDrawStack(context, point.stack, posX - textWidth - padding + 7.0f, posY - padding + 0.35f, true, false, 0.45f);
        }
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent e) {
        if (Prediction.mc.field_1724 == null || Prediction.mc.field_1687 == null) {
            return;
        }
        this.points.clear();
        this.drawPredictionInHand(e.getStack(), Prediction.mc.field_1724.method_5877(), TurnsConnection.INSTANCE.getRotation());
        this.getProjectiles().forEach(entity -> {
            class_243 motion = entity.method_18798();
            class_243 pos = entity.method_19538();
            for (int i = 0; i < 300; ++i) {
                class_243 prevPos = pos;
                pos = pos.method_1019(motion);
                motion = this.calculateMotion((class_1297)entity, prevPos, motion);
                class_3965 result = RaycastAngle.raycast(prevPos, pos, class_3959.class_3960.field_17558, entity);
                if (!result.method_17783().equals((Object)class_239.class_240.field_1333)) {
                    pos = result.method_17784();
                }
                Render3D.drawLine(prevPos, pos, ColorAssist.multAlpha(ColorAssist.fade(i), class_3532.method_15363((float)((float)i / 25.0f), (float)0.0f, (float)1.0f)), 2.0f, false);
                if (result.method_17783().equals((Object)class_239.class_240.field_1333) && !(pos.field_1351 < -128.0)) continue;
                this.BreakingBad((class_1297)entity, pos, i);
                break;
            }
        });
    }

    public void drawPredictionInHand(class_4587 matrix, Iterable<class_1799> stacks, Turns angle) {
        class_1792 activeItem = Prediction.mc.field_1724.method_6030().method_7909();
        Iterator<class_1799> iterator = stacks.iterator();
        if (iterator.hasNext()) {
            List<class_239> list;
            class_1799 stack = iterator.next();
            class_1792 class_17922 = stack.method_7909();
            Objects.requireNonNull(class_17922);
            class_1792 class_17923 = class_17922;
            int n = 0;
            block10: while (true) {
                switch (SwitchBootstraps.typeSwitch("typeSwitch", new Object[]{class_1779.class, class_1828.class, class_1835.class, class_1823.class, class_1771.class, class_1776.class, class_1753.class, class_1764.class}, (Object)class_17923, n)) {
                    case 0: {
                        class_1779 item = (class_1779)class_17923;
                        list = this.checkTrajectory((class_1676)new class_1683((class_1937)Prediction.mc.field_1687, (class_1309)Prediction.mc.field_1724, stack), 0.8, angle);
                        break block10;
                    }
                    case 1: {
                        class_1828 item = (class_1828)class_17923;
                        list = this.checkTrajectory((class_1676)new class_1686((class_1937)Prediction.mc.field_1687, (class_1309)Prediction.mc.field_1724, stack), 0.55, angle);
                        break block10;
                    }
                    case 2: {
                        class_1835 item = (class_1835)class_17923;
                        if (!item.equals(activeItem) || Prediction.mc.field_1724.method_6048() < 10) {
                            n = 3;
                            continue block10;
                        }
                        list = this.checkTrajectory((class_1676)new class_1685((class_1937)Prediction.mc.field_1687, (class_1309)Prediction.mc.field_1724, stack), 2.5, angle);
                        break block10;
                    }
                    case 3: {
                        class_1823 item = (class_1823)class_17923;
                        list = this.checkTrajectory((class_1676)new class_1680((class_1937)Prediction.mc.field_1687, (class_1309)Prediction.mc.field_1724, stack), 1.5, angle);
                        break block10;
                    }
                    case 4: {
                        class_1771 item = (class_1771)class_17923;
                        list = this.checkTrajectory((class_1676)new class_1681((class_1937)Prediction.mc.field_1687, (class_1309)Prediction.mc.field_1724, stack), 1.5, angle);
                        break block10;
                    }
                    case 5: {
                        class_1776 item = (class_1776)class_17923;
                        list = this.checkTrajectory((class_1676)new class_1684((class_1937)Prediction.mc.field_1687, (class_1309)Prediction.mc.field_1724, stack), 1.5, angle);
                        break block10;
                    }
                    case 6: {
                        class_1753 item = (class_1753)class_17923;
                        if (!item.equals(activeItem) || !Prediction.mc.field_1724.method_6115()) {
                            n = 7;
                            continue block10;
                        }
                        list = this.checkTrajectory((class_1676)new class_1667((class_1937)Prediction.mc.field_1687, (class_1309)Prediction.mc.field_1724, stack, stack), 3.0f * class_3532.method_15363((float)(((float)Prediction.mc.field_1724.method_6048() + tickCounter.method_60637(false)) / 20.0f), (float)0.0f, (float)1.0f), angle);
                        break block10;
                    }
                    case 7: {
                        class_1764 item = (class_1764)class_17923;
                        if (!class_1764.method_7781((class_1799)stack)) {
                            n = 8;
                            continue block10;
                        }
                        class_9278 component = (class_9278)stack.method_57824(class_9334.field_49649);
                        ArrayList<class_239> list2 = new ArrayList<class_239>();
                        if (component != null) {
                            float velocity = ((class_1799)component.method_57437().getFirst()).method_31574(class_1802.field_8639) ? 100.0f : 3.0f;
                            list2.add(this.checkTrajectory(angle.toVector(), (class_1676)new class_1667((class_1937)Prediction.mc.field_1687, (class_1309)Prediction.mc.field_1724, stack, stack), velocity));
                            if (component.method_57437().size() > 2) {
                                float pitchAbs = angle.getPitch() / 90.0f;
                                float delta = pitchAbs * pitchAbs * pitchAbs * pitchAbs * pitchAbs;
                                float yaw = class_3532.method_48781((float)Math.abs(delta), (int)10, (int)90);
                                float pitch = class_3532.method_48781((float)delta, (int)0, (int)10);
                                list2.add(this.checkTrajectory(angle.addYaw(-yaw).addPitch(-pitch).toVector(), (class_1676)new class_1667((class_1937)Prediction.mc.field_1687, (class_1309)Prediction.mc.field_1724, stack, stack), velocity));
                                list2.add(this.checkTrajectory(angle.addYaw(yaw).toVector(), (class_1676)new class_1667((class_1937)Prediction.mc.field_1687, (class_1309)Prediction.mc.field_1724, stack, stack), velocity));
                            }
                        }
                        list = list2;
                        break block10;
                    }
                    default: {
                        list = null;
                        break block10;
                    }
                }
                break;
            }
            List<class_239> results = list;
            if (results != null && !(results = results.stream().filter(Objects::nonNull).toList()).isEmpty()) {
                this.renderProjectileResults(matrix, results);
            }
            return;
        }
    }

    public void renderProjectileResults(class_4587 matrix, List<class_239> results) {
        for (class_239 result : results) {
            class_2350 direction = this.getDirection(result);
            int color = result.method_17783().equals((Object)class_239.class_240.field_1331) ? new Color(255, 0, 0, 250).getRGB() : new Color(0, 255, 0, 250).getRGB();
            double width = 0.3;
            Quaternionf quaternionf = switch (direction) {
                case class_2350.field_11039, class_2350.field_11034 -> class_7833.field_40718.rotationDegrees(90.0f);
                case class_2350.field_11035, class_2350.field_11043 -> class_7833.field_40714.rotationDegrees(90.0f);
                default -> new Quaternionf();
            };
            matrix.method_22903();
            matrix.method_61958(result.method_17784());
            matrix.method_22907(quaternionf);
            class_4587.class_4665 entry = matrix.method_23760().method_56822();
            int size = 90;
            for (int i = 0; i <= size; ++i) {
                Render3D.drawLine(entry, Calculate.cosSin(i, size, width), Calculate.cosSin(i + 1, size, width), color, color, 2.0f, false);
            }
            Render3D.drawLine(entry, new class_243(0.0, 0.0, -width), new class_243(0.0, 0.0, width), color, color, 2.0f, false);
            Render3D.drawLine(entry, new class_243(-width, 0.0, 0.0), new class_243(width, 0.0, 0.0), color, color, 2.0f, false);
            matrix.method_22909();
        }
    }

    public List<class_1297> getProjectiles() {
        return PlayerInteractionHelper.streamEntities().filter(e -> (e instanceof class_1665 || e instanceof class_3857 || e instanceof class_1542) && !this.visible((class_1297)e)).toList();
    }

    public List<class_239> checkTrajectory(class_1676 entity, double velocity, Turns angle) {
        return new ArrayList<class_239>(Collections.singleton(this.checkTrajectory(angle.toVector(), entity, velocity)));
    }

    public class_239 checkTrajectory(class_243 lookVec, class_1676 entity, double velocity) {
        class_243 class_2432;
        float sqrt = class_3532.method_15355((float)lookVec.method_46409().lengthSquared());
        class_1676 class_16762 = entity;
        Objects.requireNonNull(class_16762);
        class_1676 class_16763 = class_16762;
        int n = 0;
        block3: while (true) {
            switch (SwitchBootstraps.typeSwitch("typeSwitch", new Object[]{class_1667.class}, (Object)class_16763, n)) {
                case 0: {
                    class_1667 arrow = (class_1667)class_16763;
                    if (!arrow.method_54759().method_7909().equals(class_1802.field_8399)) {
                        n = 1;
                        continue block3;
                    }
                    class_2432 = class_243.field_1353;
                    break block3;
                }
                default: {
                    class_2432 = Prediction.mc.field_1724.method_18798();
                    break block3;
                }
            }
            break;
        }
        class_243 motion = class_2432;
        return this.traceTrajectory(Prediction.mc.field_1724.method_33571().method_1019(Calculate.interpolate((class_1297)Prediction.mc.field_1724).method_1020(Prediction.mc.field_1724.method_19538())), lookVec.method_1021(velocity / (double)sqrt).method_1019(motion), entity);
    }

    public class_239 calcTrajectory(class_1676 e) {
        return this.traceTrajectory(e.method_19538(), e.method_18798(), e);
    }

    public class_239 traceTrajectory(class_243 pos, class_243 motion, class_1676 entity) {
        for (int i = 0; i < 300; ++i) {
            class_243 prevPos = pos;
            pos = pos.method_1019(motion);
            motion = this.calculateMotion((class_1297)entity, prevPos, motion);
            class_3965 result = RaycastAngle.raycast(prevPos, pos, class_3959.class_3960.field_17558, (class_1297)entity);
            if (!result.method_17783().equals((Object)class_239.class_240.field_1333)) {
                return result;
            }
            class_243 finalPos = pos;
            class_243 finalPrevPos = prevPos;
            if (PlayerInteractionHelper.streamEntities().filter(ent -> {
                class_1309 living;
                return ent instanceof class_1309 && (living = (class_1309)ent) != entity.method_24921() && living.method_5805();
            }).anyMatch(ent -> ent.method_5829().method_1014(0.3).method_993(finalPrevPos, finalPos))) {
                return new class_239(this, pos){

                    public class_239.class_240 method_17783() {
                        return class_239.class_240.field_1331;
                    }
                };
            }
            if (pos.field_1351 < -128.0) break;
        }
        return null;
    }

    public class_243 calculateMotion(class_1297 entity, class_243 prevPos, class_243 motion) {
        double d;
        boolean isInWater = Prediction.mc.field_1687.method_8316(class_2338.method_49638((class_2374)prevPos)).method_15767(class_3486.field_15517);
        class_1297 class_12972 = entity;
        Objects.requireNonNull(class_12972);
        class_1297 class_12973 = class_12972;
        int n = 0;
        block4: while (true) {
            switch (SwitchBootstraps.typeSwitch("typeSwitch", new Object[]{class_1685.class, class_1665.class}, (Object)class_12973, n)) {
                case 0: {
                    class_1685 trident = (class_1685)class_12973;
                    d = 0.99;
                    break block4;
                }
                case 1: {
                    class_1665 persistent = (class_1665)class_12973;
                    if (!isInWater) {
                        n = 2;
                        continue block4;
                    }
                    d = 0.6;
                    break block4;
                }
                default: {
                    if (isInWater) {
                        d = 0.8;
                        break block4;
                    }
                    d = 0.99;
                    break block4;
                }
            }
            break;
        }
        double multiply = d;
        return motion.method_1021(multiply).method_1031(0.0, -entity.method_56989(), 0.0);
    }

    private void BreakingBad(class_1297 entity, class_243 pos, int ticks) {
        class_1297 class_12972 = entity;
        Objects.requireNonNull(class_12972);
        class_1297 class_12973 = class_12972;
        int n = 0;
        switch (SwitchBootstraps.typeSwitch("typeSwitch", new Object[]{class_1542.class, class_3857.class, class_1665.class}, (Object)class_12973, n)) {
            case 0: {
                class_1542 item = (class_1542)class_12973;
                this.points.add(new Point(item.method_6983(), pos, ticks));
                break;
            }
            case 1: {
                class_3857 thrown = (class_3857)class_12973;
                this.points.add(new Point(thrown.method_7495(), pos, ticks));
                break;
            }
            case 2: {
                class_1665 persistent = (class_1665)class_12973;
                this.points.add(new Point(persistent.method_54759(), pos, ticks));
                break;
            }
        }
    }

    private class_2350 getDirection(class_239 result) {
        if (result instanceof class_3965) {
            class_3965 blockHitResult = (class_3965)result;
            return blockHitResult.method_17780();
        }
        return class_2350.method_58251((class_243)result.method_17784().method_1020(Prediction.mc.field_1724.method_33571()).method_1029());
    }

    private boolean visible(class_1297 entity) {
        boolean posChange = entity.method_23317() == entity.field_6014 && entity.method_23318() == entity.field_6036 && entity.method_23321() == entity.field_5969;
        boolean itemEntityCheck = entity instanceof class_1542 && (entity.method_24828() || PlayerInteractionHelper.isBoxInBlock(entity.method_5829().method_1014(2.0), class_2246.field_10382));
        return posChange || itemEntityCheck;
    }

    private record Point(class_1799 stack, class_243 pos, int ticks) {
    }
}

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1296
 *  net.minecraft.class_1297
 *  net.minecraft.class_1308
 *  net.minecraft.class_1309
 *  net.minecraft.class_1429
 *  net.minecraft.class_1531
 *  net.minecraft.class_1588
 *  net.minecraft.class_1657
 */
package code.alphadlc.utils.features.combat;

import code.alphadlc.common.repository.friend.FriendUtils;
import code.alphadlc.utils.display.interfaces.QuickImports;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;
import net.minecraft.class_1296;
import net.minecraft.class_1297;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1429;
import net.minecraft.class_1531;
import net.minecraft.class_1588;
import net.minecraft.class_1657;

public class TargetFinder
implements QuickImports {
    private class_1309 currentTarget;

    public void searchTargets(Iterable<class_1297> entities, float range, float fov, boolean ignoreWalls) {
        this.currentTarget = StreamSupport.stream(entities.spliterator(), false).filter(e -> {
            class_1309 living;
            return e instanceof class_1309 && (living = (class_1309)e) != TargetFinder.mc.field_1724 && living.method_5805();
        }).map(e -> (class_1309)e).filter(l -> TargetFinder.mc.field_1724 != null && TargetFinder.mc.field_1724.method_5739((class_1297)l) <= range).filter(l -> ignoreWalls || TargetFinder.mc.field_1724.method_6057((class_1297)l)).min(Comparator.comparingDouble(l -> TargetFinder.mc.field_1724.method_5739((class_1297)l))).orElse(null);
    }

    public void validateTarget(Predicate<class_1309> predicate) {
        if (this.currentTarget != null && !predicate.test(this.currentTarget)) {
            this.currentTarget = null;
        }
    }

    public class_1309 getCurrentTarget() {
        return this.currentTarget;
    }

    public static class EntityFilter
    implements Predicate<class_1309> {
        private final List<String> selected;

        public EntityFilter(List<String> selected) {
            this.selected = selected;
        }

        @Override
        public boolean test(class_1309 entity) {
            if (entity == null || entity == QuickImports.mc.field_1724 || !entity.method_5805()) {
                return false;
            }
            if (entity instanceof class_1531) {
                return this.selected.contains("\u041f\u043e\u0434\u0441\u0442\u0430\u0432\u043a\u0430 \u0434\u043b\u044f \u0431\u0440\u043e\u043d\u0438");
            }
            if (entity instanceof class_1657) {
                class_1657 player = (class_1657)entity;
                if (!this.selected.contains("\u0418\u0433\u0440\u043e\u043a\u0438")) {
                    return false;
                }
                if (this.selected.contains("\u0414\u0440\u0443\u0437\u044c\u044f") && FriendUtils.isFriend(player.method_5477().getString())) {
                    return true;
                }
                return !FriendUtils.isFriend(player.method_5477().getString());
            }
            if (entity instanceof class_1429 || entity instanceof class_1296) {
                return this.selected.contains("\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0435");
            }
            if (entity instanceof class_1588 || entity instanceof class_1308) {
                return this.selected.contains("\u041c\u043e\u0431\u044b");
            }
            return false;
        }

        public boolean isValid(class_1309 entity) {
            return this.test(entity);
        }
    }
}

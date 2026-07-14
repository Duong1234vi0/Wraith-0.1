/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api;

import java.awt.Color;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.MinecraftClient;
import lightning.product.q_1613_l;
import lightning.product.x_282_a;
import lightning.product.z_3539_x;
import mods.baritone.api.api.java.baritone.api.utils.NotificationHelper;
import mods.baritone.api.api.java.baritone.api.utils.SettingsUtil;
import mods.baritone.api.api.java.baritone.api.utils.TypeUtils;
import mods.baritone.api.api.java.baritone.api.utils.gui.BaritoneToast;

public final class Settings {
    public final Setting<Boolean> allowBreak = new Setting<Boolean>(true);
    public final Setting<List<T_2915_h>> allowBreakAnyway = new Setting(new ArrayList());
    public final Setting<Boolean> allowSprint = new Setting<Boolean>(true);
    public final Setting<Boolean> allowPlace = new Setting<Boolean>(true);
    public final Setting<Boolean> allowInventory = new Setting<Boolean>(false);
    public final Setting<Integer> ticksBetweenInventoryMoves = new Setting<Integer>(1);
    public final Setting<Boolean> inventoryMoveOnlyIfStationary = new Setting<Boolean>(false);
    public final Setting<Boolean> assumeExternalAutoTool = new Setting<Boolean>(false);
    public final Setting<Boolean> autoTool = new Setting<Boolean>(true);
    public final Setting<Double> blockPlacementPenalty = new Setting<Double>(20.0);
    public final Setting<Double> blockBreakAdditionalPenalty = new Setting<Double>(2.0);
    public final Setting<Double> jumpPenalty = new Setting<Double>(2.0);
    public final Setting<Double> walkOnWaterOnePenalty = new Setting<Double>(3.0);
    public final Setting<Boolean> strictLiquidCheck = new Setting<Boolean>(false);
    public final Setting<Boolean> allowWaterBucketFall = new Setting<Boolean>(true);
    public final Setting<Boolean> assumeWalkOnWater = new Setting<Boolean>(false);
    public final Setting<Boolean> assumeWalkOnLava = new Setting<Boolean>(false);
    public final Setting<Boolean> assumeStep = new Setting<Boolean>(false);
    public final Setting<Boolean> assumeSafeWalk = new Setting<Boolean>(false);
    public final Setting<Boolean> allowJumpAt256 = new Setting<Boolean>(false);
    public final Setting<Boolean> allowParkourAscend = new Setting<Boolean>(true);
    public final Setting<Boolean> allowDiagonalDescend = new Setting<Boolean>(false);
    public final Setting<Boolean> allowDiagonalAscend = new Setting<Boolean>(false);
    public final Setting<Boolean> allowDownward = new Setting<Boolean>(true);
    public final Setting<List<q_1613_l>> acceptableThrowawayItems = new Setting<ArrayList<q_1613_l>>(new ArrayList<q_1613_l>(Arrays.asList(a_3742_W.s_956_w.u_1723_Y(), a_3742_W.P_4830_p.u_1723_Y(), a_3742_W.i_3196_G.u_1723_Y(), a_3742_W.J_1907_R.u_1723_Y())));
    public final Setting<List<T_2915_h>> blocksToAvoid = new Setting(new ArrayList());
    public final Setting<List<T_2915_h>> blocksToDisallowBreaking = new Setting(new ArrayList());
    public final Setting<List<T_2915_h>> blocksToAvoidBreaking = new Setting<ArrayList<T_2915_h>>(new ArrayList<T_2915_h>(Arrays.asList(a_3742_W.O_2934_T, a_3742_W.P_925_e, a_3742_W.L_1362_X, a_3742_W.NumberSetting)));
    public final Setting<Double> avoidBreakingMultiplier = new Setting<Double>(0.1);
    public final Setting<List<T_2915_h>> buildIgnoreBlocks = new Setting<ArrayList<T_2915_h>>(new ArrayList<T_2915_h>(Arrays.asList(new T_2915_h[0])));
    public final Setting<List<T_2915_h>> buildSkipBlocks = new Setting<ArrayList<T_2915_h>>(new ArrayList<T_2915_h>(Arrays.asList(new T_2915_h[0])));
    public final Setting<Map<T_2915_h, List<T_2915_h>>> buildValidSubstitutes = new Setting(new HashMap());
    public final Setting<Map<T_2915_h, List<T_2915_h>>> buildSubstitutes = new Setting(new HashMap());
    public final Setting<List<T_2915_h>> okIfAir = new Setting<ArrayList<T_2915_h>>(new ArrayList<T_2915_h>(Arrays.asList(new T_2915_h[0])));
    public final Setting<Boolean> buildIgnoreExisting = new Setting<Boolean>(false);
    public final Setting<Boolean> buildIgnoreDirection = new Setting<Boolean>(false);
    public final Setting<List<String>> buildIgnoreProperties = new Setting<ArrayList<String>>(new ArrayList<String>(Arrays.asList(new String[0])));
    public final Setting<Boolean> avoidUpdatingFallingBlocks = new Setting<Boolean>(true);
    public final Setting<Boolean> allowVines = new Setting<Boolean>(false);
    public final Setting<Boolean> allowWalkOnBottomSlab = new Setting<Boolean>(true);
    public final Setting<Boolean> allowParkour = new Setting<Boolean>(false);
    public final Setting<Boolean> allowParkourPlace = new Setting<Boolean>(false);
    public final Setting<Boolean> considerPotionEffects = new Setting<Boolean>(true);
    public final Setting<Boolean> sprintAscends = new Setting<Boolean>(true);
    public final Setting<Boolean> overshootTraverse = new Setting<Boolean>(true);
    public final Setting<Boolean> pauseMiningForFallingBlocks = new Setting<Boolean>(true);
    public final Setting<Integer> rightClickSpeed = new Setting<Integer>(4);
    public final Setting<Double> randomLooking113 = new Setting<Double>(2.0);
    public final Setting<Float> blockReachDistance = new Setting<Float>(Float.valueOf(4.5f));
    public final Setting<Double> randomLooking = new Setting<Double>(0.01);
    public final Setting<Double> costHeuristic = new Setting<Double>(3.563);
    public final Setting<Integer> pathingMaxChunkBorderFetch = new Setting<Integer>(50);
    public final Setting<Double> backtrackCostFavoringCoefficient = new Setting<Double>(0.5);
    public final Setting<Boolean> avoidance = new Setting<Boolean>(false);
    public final Setting<Double> mobSpawnerAvoidanceCoefficient = new Setting<Double>(2.0);
    public final Setting<Integer> mobSpawnerAvoidanceRadius = new Setting<Integer>(16);
    public final Setting<Double> mobAvoidanceCoefficient = new Setting<Double>(1.5);
    public final Setting<Integer> mobAvoidanceRadius = new Setting<Integer>(8);
    public final Setting<Boolean> rightClickContainerOnArrival = new Setting<Boolean>(true);
    public final Setting<Boolean> enterPortal = new Setting<Boolean>(true);
    public final Setting<Boolean> minimumImprovementRepropagation = new Setting<Boolean>(true);
    public final Setting<Boolean> cutoffAtLoadBoundary = new Setting<Boolean>(false);
    public final Setting<Double> maxCostIncrease = new Setting<Double>(10.0);
    public final Setting<Integer> costVerificationLookahead = new Setting<Integer>(5);
    public final Setting<Double> pathCutoffFactor = new Setting<Double>(0.9);
    public final Setting<Integer> pathCutoffMinimumLength = new Setting<Integer>(30);
    public final Setting<Integer> planningTickLookahead = new Setting<Integer>(150);
    public final Setting<Integer> pathingMapDefaultSize = new Setting<Integer>(1024);
    public final Setting<Float> pathingMapLoadFactor = new Setting<Float>(Float.valueOf(0.75f));
    public final Setting<Integer> maxFallHeightNoWater = new Setting<Integer>(3);
    public final Setting<Integer> maxFallHeightBucket = new Setting<Integer>(20);
    public final Setting<Boolean> allowOvershootDiagonalDescend = new Setting<Boolean>(true);
    public final Setting<Boolean> simplifyUnloadedYCoord = new Setting<Boolean>(true);
    public final Setting<Boolean> repackOnAnyBlockChange = new Setting<Boolean>(true);
    public final Setting<Integer> movementTimeoutTicks = new Setting<Integer>(100);
    public final Setting<Long> primaryTimeoutMS = new Setting<Long>(500L);
    public final Setting<Long> failureTimeoutMS = new Setting<Long>(2000L);
    public final Setting<Long> planAheadPrimaryTimeoutMS = new Setting<Long>(4000L);
    public final Setting<Long> planAheadFailureTimeoutMS = new Setting<Long>(5000L);
    public final Setting<Boolean> slowPath = new Setting<Boolean>(false);
    public final Setting<Long> slowPathTimeDelayMS = new Setting<Long>(100L);
    public final Setting<Long> slowPathTimeoutMS = new Setting<Long>(40000L);
    public final Setting<Boolean> doBedWaypoints = new Setting<Boolean>(true);
    public final Setting<Boolean> doDeathWaypoints = new Setting<Boolean>(true);
    public final Setting<Boolean> chunkCaching = new Setting<Boolean>(true);
    public final Setting<Boolean> pruneRegionsFromRAM = new Setting<Boolean>(true);
    public final Setting<Integer> chunkPackerQueueMaxSize = new Setting<Integer>(2000);
    public final Setting<Boolean> backfill = new Setting<Boolean>(false);
    public final Setting<Boolean> logAsToast = new Setting<Boolean>(false);
    public final Setting<Long> toastTimer = new Setting<Long>(5000L);
    public final Setting<Boolean> chatDebug = new Setting<Boolean>(false);
    public final Setting<Boolean> chatControl = new Setting<Boolean>(true);
    public final Setting<Boolean> chatControlAnyway = new Setting<Boolean>(false);
    public final Setting<Boolean> renderPath = new Setting<Boolean>(true);
    public final Setting<Boolean> renderPathAsLine = new Setting<Boolean>(false);
    public final Setting<Boolean> renderGoal = new Setting<Boolean>(true);
    public final Setting<Boolean> renderGoalAnimated = new Setting<Boolean>(true);
    public final Setting<Boolean> renderSelectionBoxes = new Setting<Boolean>(true);
    public final Setting<Boolean> renderGoalIgnoreDepth = new Setting<Boolean>(true);
    public final Setting<Boolean> renderGoalXZBeacon = new Setting<Boolean>(false);
    public final Setting<Boolean> renderSelectionBoxesIgnoreDepth = new Setting<Boolean>(true);
    public final Setting<Boolean> renderPathIgnoreDepth = new Setting<Boolean>(true);
    public final Setting<Float> pathRenderLineWidthPixels = new Setting<Float>(Float.valueOf(5.0f));
    public final Setting<Float> goalRenderLineWidthPixels = new Setting<Float>(Float.valueOf(3.0f));
    public final Setting<Boolean> fadePath = new Setting<Boolean>(false);
    public final Setting<Boolean> freeLook = new Setting<Boolean>(true);
    public final Setting<Boolean> blockFreeLook = new Setting<Boolean>(false);
    public final Setting<Boolean> remainWithExistingLookDirection = new Setting<Boolean>(true);
    public final Setting<Boolean> antiCheatCompatibility = new Setting<Boolean>(true);
    public final Setting<Boolean> pathThroughCachedOnly = new Setting<Boolean>(false);
    public final Setting<Boolean> sprintInWater = new Setting<Boolean>(true);
    public final Setting<Boolean> blacklistClosestOnFailure = new Setting<Boolean>(true);
    public final Setting<Boolean> renderCachedChunks = new Setting<Boolean>(false);
    public final Setting<Float> cachedChunksOpacity = new Setting<Float>(Float.valueOf(0.5f));
    public final Setting<Boolean> prefixControl = new Setting<Boolean>(true);
    public final Setting<String> prefix = new Setting<String>("#");
    public final Setting<Boolean> shortBaritonePrefix = new Setting<Boolean>(false);
    public final Setting<Boolean> echoCommands = new Setting<Boolean>(true);
    public final Setting<Boolean> censorCoordinates = new Setting<Boolean>(false);
    public final Setting<Boolean> censorRanCommands = new Setting<Boolean>(false);
    public final Setting<Boolean> itemSaver = new Setting<Boolean>(false);
    public final Setting<Integer> itemSaverThreshold = new Setting<Integer>(10);
    public final Setting<Boolean> preferSilkTouch = new Setting<Boolean>(false);
    public final Setting<Boolean> walkWhileBreaking = new Setting<Boolean>(true);
    public final Setting<Boolean> splicePath = new Setting<Boolean>(true);
    public final Setting<Integer> maxPathHistoryLength = new Setting<Integer>(300);
    public final Setting<Integer> pathHistoryCutoffAmount = new Setting<Integer>(50);
    public final Setting<Integer> mineGoalUpdateInterval = new Setting<Integer>(5);
    public final Setting<Integer> maxCachedWorldScanCount = new Setting<Integer>(10);
    public final Setting<Integer> minYLevelWhileMining = new Setting<Integer>(0);
    public final Setting<Integer> maxYLevelWhileMining = new Setting<Integer>(255);
    public final Setting<Boolean> allowOnlyExposedOres = new Setting<Boolean>(false);
    public final Setting<Integer> allowOnlyExposedOresDistance = new Setting<Integer>(1);
    public final Setting<Boolean> exploreForBlocks = new Setting<Boolean>(true);
    public final Setting<Integer> worldExploringChunkOffset = new Setting<Integer>(0);
    public final Setting<Integer> exploreChunkSetMinimumSize = new Setting<Integer>(10);
    public final Setting<Integer> exploreMaintainY = new Setting<Integer>(64);
    public final Setting<Boolean> replantCrops = new Setting<Boolean>(true);
    public final Setting<Boolean> replantNetherWart = new Setting<Boolean>(false);
    public final Setting<Boolean> extendCacheOnThreshold = new Setting<Boolean>(false);
    public final Setting<Boolean> buildInLayers = new Setting<Boolean>(false);
    public final Setting<Boolean> layerOrder = new Setting<Boolean>(false);
    public final Setting<Integer> layerHeight = new Setting<Integer>(1);
    public final Setting<Integer> startAtLayer = new Setting<Integer>(0);
    public final Setting<Boolean> skipFailedLayers = new Setting<Boolean>(false);
    public final Setting<Boolean> buildOnlySelection = new Setting<Boolean>(false);
    public final Setting<z_3539_x> buildRepeat = new Setting<z_3539_x>(new z_3539_x(0, 0, 0));
    public final Setting<Integer> buildRepeatCount = new Setting<Integer>(-1);
    public final Setting<Boolean> buildRepeatSneaky = new Setting<Boolean>(true);
    public final Setting<Boolean> breakFromAbove = new Setting<Boolean>(false);
    public final Setting<Boolean> goalBreakFromAbove = new Setting<Boolean>(false);
    public final Setting<Boolean> mapArtMode = new Setting<Boolean>(false);
    public final Setting<Boolean> okIfWater = new Setting<Boolean>(false);
    public final Setting<Integer> incorrectSize = new Setting<Integer>(100);
    public final Setting<Double> breakCorrectBlockPenaltyMultiplier = new Setting<Double>(10.0);
    public final Setting<Boolean> schematicOrientationX = new Setting<Boolean>(false);
    public final Setting<Boolean> schematicOrientationY = new Setting<Boolean>(false);
    public final Setting<Boolean> schematicOrientationZ = new Setting<Boolean>(false);
    public final Setting<String> schematicFallbackExtension = new Setting<String>("schematic");
    public final Setting<Integer> builderTickScanRadius = new Setting<Integer>(5);
    public final Setting<Boolean> mineScanDroppedItems = new Setting<Boolean>(true);
    public final Setting<Long> mineDropLoiterDurationMSThanksLouca = new Setting<Long>(250L);
    public final Setting<Boolean> distanceTrim = new Setting<Boolean>(true);
    public final Setting<Boolean> cancelOnGoalInvalidation = new Setting<Boolean>(true);
    public final Setting<Integer> axisHeight = new Setting<Integer>(120);
    public final Setting<Boolean> disconnectOnArrival = new Setting<Boolean>(false);
    public final Setting<Boolean> legitMine = new Setting<Boolean>(false);
    public final Setting<Integer> legitMineYLevel = new Setting<Integer>(11);
    public final Setting<Boolean> legitMineIncludeDiagonals = new Setting<Boolean>(false);
    public final Setting<Boolean> forceInternalMining = new Setting<Boolean>(true);
    public final Setting<Boolean> internalMiningAirException = new Setting<Boolean>(true);
    public final Setting<Double> followOffsetDistance = new Setting<Double>(0.0);
    public final Setting<Float> followOffsetDirection = new Setting<Float>(Float.valueOf(0.0f));
    public final Setting<Integer> followRadius = new Setting<Integer>(3);
    public final Setting<Boolean> disableCompletionCheck = new Setting<Boolean>(false);
    public final Setting<Long> cachedChunksExpirySeconds = new Setting<Long>(-1L);
    @JavaOnly
    public final Setting<Consumer<x_282_a>> logger = new Setting<Consumer<x_282_a>>(msg -> MinecraftClient.A_4115_X().M_588_G.R_4764_Y().n_1700_B((x_282_a)msg));
    @JavaOnly
    public final Setting<BiConsumer<String, Boolean>> notifier = new Setting<BiConsumer<String, Boolean>>(NotificationHelper::notify);
    @JavaOnly
    public final Setting<BiConsumer<x_282_a, x_282_a>> toaster = new Setting<BiConsumer<x_282_a, x_282_a>>(BaritoneToast::addOrUpdate);
    public final Setting<Boolean> verboseCommandExceptions = new Setting<Boolean>(false);
    public final Setting<Double> yLevelBoxSize = new Setting<Double>(15.0);
    public final Setting<Color> colorCurrentPath = new Setting<Color>(Color.RED);
    public final Setting<Color> colorNextPath = new Setting<Color>(Color.MAGENTA);
    public final Setting<Color> colorBlocksToBreak = new Setting<Color>(Color.RED);
    public final Setting<Color> colorBlocksToPlace = new Setting<Color>(Color.GREEN);
    public final Setting<Color> colorBlocksToWalkInto = new Setting<Color>(Color.MAGENTA);
    public final Setting<Color> colorBestPathSoFar = new Setting<Color>(Color.BLUE);
    public final Setting<Color> colorMostRecentConsidered = new Setting<Color>(Color.CYAN);
    public final Setting<Color> colorGoalBox = new Setting<Color>(Color.GREEN);
    public final Setting<Color> colorInvertedGoalBox = new Setting<Color>(Color.RED);
    public final Setting<Color> colorSelection = new Setting<Color>(Color.CYAN);
    public final Setting<Color> colorSelectionPos1 = new Setting<Color>(Color.BLACK);
    public final Setting<Color> colorSelectionPos2 = new Setting<Color>(Color.ORANGE);
    public final Setting<Float> selectionOpacity = new Setting<Float>(Float.valueOf(0.5f));
    public final Setting<Float> selectionLineWidth = new Setting<Float>(Float.valueOf(2.0f));
    public final Setting<Boolean> renderSelection = new Setting<Boolean>(true);
    public final Setting<Boolean> renderSelectionIgnoreDepth = new Setting<Boolean>(true);
    public final Setting<Boolean> renderSelectionCorners = new Setting<Boolean>(true);
    public final Setting<Boolean> useSwordToMine = new Setting<Boolean>(true);
    public final Setting<Boolean> desktopNotifications = new Setting<Boolean>(false);
    public final Setting<Boolean> notificationOnPathComplete = new Setting<Boolean>(true);
    public final Setting<Boolean> notificationOnFarmFail = new Setting<Boolean>(true);
    public final Setting<Boolean> notificationOnBuildFinished = new Setting<Boolean>(true);
    public final Setting<Boolean> notificationOnExploreFinished = new Setting<Boolean>(true);
    public final Setting<Boolean> notificationOnMineFail = new Setting<Boolean>(true);
    public final Map<String, Setting<?>> byLowerName;
    public final List<Setting<?>> allSettings;
    public final Map<Setting<?>, Type> settingTypes;

    Settings() {
        Field[] temp = this.getClass().getFields();
        HashMap<String, Setting> tmpByName = new HashMap<String, Setting>();
        ArrayList<Setting> tmpAll = new ArrayList<Setting>();
        HashMap<Setting, Type> tmpSettingTypes = new HashMap<Setting, Type>();
        try {
            for (Field field : temp) {
                String name;
                if (!field.getType().equals(Setting.class)) continue;
                Setting setting = (Setting)field.get(this);
                setting.name = name = field.getName();
                setting.javaOnly = field.isAnnotationPresent(JavaOnly.class);
                if (tmpByName.containsKey(name = name.toLowerCase())) {
                    throw new IllegalStateException("Duplicate setting name");
                }
                tmpByName.put(name, setting);
                tmpAll.add(setting);
                tmpSettingTypes.put(setting, ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0]);
            }
        }
        catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        this.byLowerName = Collections.unmodifiableMap(tmpByName);
        this.allSettings = Collections.unmodifiableList(tmpAll);
        this.settingTypes = Collections.unmodifiableMap(tmpSettingTypes);
    }

    public <T> List<Setting<T>> getAllValuesByType(Class<T> cla$$) {
        ArrayList<Setting<T>> result = new ArrayList<Setting<T>>();
        for (Setting<?> setting : this.allSettings) {
            if (!setting.getValueClass().equals(cla$$)) continue;
            result.add(setting);
        }
        return result;
    }

    public final class Setting<T> {
        public T value;
        public final T defaultValue;
        private String name;
        private boolean javaOnly;

        private Setting(T value) {
            if (value == null) {
                throw new IllegalArgumentException("Cannot determine value type class from null");
            }
            this.value = value;
            this.defaultValue = value;
            this.javaOnly = false;
        }

        @Deprecated
        public final T get() {
            return this.value;
        }

        public final String getName() {
            return this.name;
        }

        public Class<T> getValueClass() {
            return TypeUtils.resolveBaseClass(this.getType());
        }

        public String toString() {
            return SettingsUtil.settingToString(this);
        }

        public void reset() {
            this.value = this.defaultValue;
        }

        public final Type getType() {
            return Settings.this.settingTypes.get(this);
        }

        public boolean isJavaOnly() {
            return this.javaOnly;
        }
    }

    @Retention(value=RetentionPolicy.RUNTIME)
    @Target(value={ElementType.FIELD})
    private static @interface JavaOnly {
    }
}



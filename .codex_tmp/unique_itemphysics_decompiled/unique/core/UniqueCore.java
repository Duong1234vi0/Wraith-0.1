package unique.core;

import com.mojang.logging.LogUtils;
import java.awt.Color;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.IEventBus;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.class_2338;
import net.minecraft.class_310;
import org.slf4j.Logger;
import unique.core.core.Core;
import unique.core.core.Managers;
import unique.core.core.hooks.ManagerShutdownHook;
import unique.core.core.hooks.ModuleShutdownHook;
import unique.core.utility.ThunderUtility;
import unique.core.utility.render.Render2DEngine;

public class UniqueCore implements ModInitializer {
   public static final ModMetadata MOD_META = ((ModContainer)FabricLoader.getInstance().getModContainer("uniquecore").orElseThrow()).getMetadata();
   public static final String MOD_ID = "uniquecore";
   public static final String VERSION = "1.7b2407";
   public static String GITHUB_HASH = "0";
   public static String BUILD_DATE = "1 Jan 1970";
   public static final Logger LOGGER = LogUtils.getLogger();
   public static final Runtime RUNTIME = Runtime.getRuntime();
   public static final boolean baritone = FabricLoader.getInstance().isModLoaded("baritone") || FabricLoader.getInstance().isModLoaded("baritone-meteor");
   public static final IEventBus EVENT_BUS = new EventBus();
   public static String[] contributors = new String[32];
   public static Color copy_color = new Color(-1);
   public static UniqueCore.KeyListening currentKeyListener;
   public static boolean isOutdated = false;
   public static class_2338 gps_position;
   public static float TICK_TIMER = 1.0F;
   public static class_310 mc;
   public static long initTime;
   public static Core core = new Core();

   public void onInitialize() {
      mc = class_310.method_1551();
      initTime = System.currentTimeMillis();
      BUILD_DATE = ThunderUtility.readManifestField("Build-Timestamp");
      GITHUB_HASH = ThunderUtility.readManifestField("Git-Commit");
      ThunderUtility.syncVersion();
      EVENT_BUS.registerLambdaFactory("unique.core", (lookupInMethod, klass) -> (Lookup)lookupInMethod.invoke(null, klass, MethodHandles.lookup()));
      EVENT_BUS.subscribe(core);
      Managers.init();
      Managers.subscribe();
      Render2DEngine.initShaders();
      LOGGER.info("[UniqueCore] Init time: {} ms.", System.currentTimeMillis() - initTime);
      initTime = System.currentTimeMillis();
      RUNTIME.addShutdownHook(new ManagerShutdownHook());
      RUNTIME.addShutdownHook(new ModuleShutdownHook());
   }

   public static boolean isFuturePresent() {
      return FabricLoader.getInstance().getModContainer("future").isPresent();
   }

   public static enum KeyListening {
      ThunderGui,
      ClickGui,
      Search,
      Sliders,
      Strings;
   }
}

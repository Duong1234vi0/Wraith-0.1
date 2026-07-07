package unique.core.features.modules.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_155;
import net.minecraft.class_8518;
import unique.core.core.Managers;
import unique.core.core.manager.client.ConfigManager;
import unique.core.features.modules.Module;
import unique.core.utility.math.MathUtility;

public class UnHook extends Module {
   List<Module> list;
   public int code = 0;

   public UnHook() {
      super("UnHook", Module.Category.CLIENT);
   }

   @Override
   public void onEnable() {
      this.code = (int)MathUtility.random(10.0F, 99.0F);

      for (int i = 0; i < 20; i++) {
         this.sendMessage(
            ClientSettings.isRu()
               ? class_124.field_1061 + "Ща все свернется, напиши в чат " + class_124.field_1068 + this.code + class_124.field_1061 + " чтобы все вернуть!"
               : class_124.field_1061
                  + "It's all close now, write to the chat "
                  + class_124.field_1068
                  + this.code
                  + class_124.field_1061
                  + " to return everything!"
         );
      }

      this.list = Managers.MODULE.getEnabledModules();
      mc.method_1507(null);
      Managers.ASYNC
         .run(
            () -> mc.method_40000(
               () -> {
                  for (Module module : this.list) {
                     if (!module.equals(this)) {
                        module.disable();
                     }
                  }

                  ClientSettings.customMainMenu.setValue(false);

                  try {
                     mc.method_22683()
                        .method_4491(mc.method_45573(), class_155.method_16673().method_48022() ? class_8518.field_44650 : class_8518.field_44651);
                  } catch (Exception var10) {
                  }

                  mc.field_1705.method_1743().method_1808(true);
                  this.setEnabled(true);

                  try {
                     File file = new File(mc.field_1697 + File.separator + "logs" + File.separator + "latest.log");
                     FileInputStream fis = new FileInputStream(file);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
                     ArrayList<String> lines = new ArrayList<>();

                     String line;
                     while ((line = reader.readLine()) != null) {
                        if (!line.contains("unique")
                           && !line.contains("UniqueCore")
                           && !line.contains("$$")
                           && !line.contains("\\______/")
                           && !line.contains("By pan4ur, 06ED")
                           && !line.contains("⚡")
                           && !line.contains("unique.core")) {
                           lines.add(line);
                        }
                     }

                     fis.close();

                     try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
                        for (String s : lines) {
                           writer.write(s + "\n");
                        }
                     } catch (Exception var12) {
                     }

                     ConfigManager.MAIN_FOLDER.renameTo(new File("XaeroWaypoints_BACKUP092738"));
                  } catch (IOException var13) {
                  }
               }
            ),
            5000L
         );
   }

   @Override
   public void onDisable() {
      if (this.list != null) {
         for (Module module : this.list) {
            if (!module.equals(this)) {
               module.enable();
            }
         }

         ClientSettings.customMainMenu.setValue(true);

         try {
            new File("XaeroWaypoints_BACKUP092738").renameTo(new File("ThunderHackRecode"));
         } catch (Exception var3) {
         }
      }
   }
}

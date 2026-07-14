package sky.core.utils.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import sky.core.utils.misc.HasherUtil;

public abstract class BaseManager {
   protected static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
   protected static final String BASE_PATH;
   protected final File configFile;
   protected final boolean encrypt;
   protected Object data;

   protected BaseManager(String relativePath, boolean encrypt) {
      this.configFile = new File(BASE_PATH + relativePath);
      this.encrypt = encrypt;
   }

   protected BaseManager(String relativePath) {
      this(relativePath, true);
   }

   public void init() {
      this.createDirectoriesIfNeeded();
      this.initializeData();
      this.load();
   }

   protected void createDirectoriesIfNeeded() {
      File parentDir = this.configFile.getParentFile();
      if (parentDir != null && !parentDir.exists()) {
         parentDir.mkdirs();
      }

   }

   public void load() {
      if (this.configFile.exists()) {
         try {
            String fileContent = Files.readString(this.configFile.toPath());
            String processedContent = this.encrypt ? HasherUtil.decrypt(fileContent) : fileContent;
            JsonObject jsonObject = (new JsonParser()).parse(processedContent).getAsJsonObject();
            this.deserializeData(jsonObject);
         } catch (Exception var4) {
            PrintStream var10000 = System.err;
            String var10001 = this.configFile.getName();
            var10000.println("Failed to load config from " + var10001 + ": " + var4.getMessage());
            this.handleLoadError(var4);
         }

      }
   }

   public void save() {
      try {
         this.createDirectoriesIfNeeded();
         JsonObject jsonObject = this.serializeData();
         String jsonContent = GSON.toJson(jsonObject);
         String outputContent = this.encrypt ? HasherUtil.encrypt(jsonContent) : jsonContent;
         Files.writeString(this.configFile.toPath(), outputContent);
      } catch (Exception var4) {
         PrintStream var10000 = System.err;
         String var10001 = this.configFile.getName();
         var10000.println("Failed to save config to " + var10001 + ": " + var4.getMessage());
         this.handleSaveError(var4);
      }

   }

   public boolean exists() {
      return this.configFile.exists();
   }

   public boolean delete() {
      return this.configFile.exists() && this.configFile.delete();
   }

   public String getConfigPath() {
      return this.configFile.getAbsolutePath();
   }

   public String getConfigName() {
      return this.configFile.getName();
   }

   protected abstract void initializeData();

   protected abstract JsonObject serializeData();

   protected abstract void deserializeData(JsonObject var1);

   protected void handleLoadError(Exception exception) {
   }

   protected void handleSaveError(Exception exception) {
   }

   public Object getData() {
      return this.data;
   }

   protected void setData(Object data) {
      this.data = data;
   }

   static {
      BASE_PATH = FilePath.BASE_PATH;
   }
}

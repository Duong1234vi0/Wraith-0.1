package sky.core.utils.render.shader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL20;
import sky.core.utils.Wrapper;

public class ShaderUtil implements Wrapper {
   private final int programID = ARBShaderObjects.glCreateProgramObjectARB();
   public static ShaderUtil rounded_rectangle = new ShaderUtil("rounded_rectangle");
   public static ShaderUtil outline = new ShaderUtil("outline");
   public static ShaderUtil rounded_rectangle_gradient = new ShaderUtil("rounded_rectangle_gradient");
   public static ShaderUtil rounded_rectangle_gradient_glowed = new ShaderUtil("rounded_rectangle_gradient_glowed");
   public static ShaderUtil light_kawase_down = new ShaderUtil("light_kawase_down");
   public static ShaderUtil light_kawase_up = new ShaderUtil("light_kawase_up");
   public static ShaderUtil blurred_round_rectangle = new ShaderUtil("blurred_round_rectangle");
   public static ShaderUtil liquid_glass_rectangle = new ShaderUtil("liquid_glass_rectangle");
   public static ShaderUtil rounded_head_texture = new ShaderUtil("rounded_head_texture");
   public static ShaderUtil rounded_texture = new ShaderUtil("rounded_texture");
   public static ShaderUtil substring = new ShaderUtil("substring");
   public static ShaderUtil outline_glow_esp = new ShaderUtil("outline_glow_esp");
   public static ShaderUtil glow_esp = new ShaderUtil("glow_esp");
   public static ShaderUtil circle = new ShaderUtil("circle");
   public static ShaderUtil outline_esp = new ShaderUtil("outline_esp");
   public static ShaderUtil hands = new ShaderUtil("hands");
   public static ShaderUtil round = new ShaderUtil("round");
   public static ShaderUtil blockout = new ShaderUtil("ovbew");
   public static ShaderUtil saturation = new ShaderUtil("saturation");
   public static ShaderUtil sky_summer = new ShaderUtil("sky_summer");
   public static ShaderUtil hands_uv = new ShaderUtil("hands_uv");
   public static ShaderUtil block_overlay_sky_plasma = new ShaderUtil("block_overlay_sky_plasma", "space_sky_vertex");
   public static ShaderUtil block_overlay_sky_balatro = new ShaderUtil("block_overlay_sky_balatro", "space_sky_vertex");

   public ShaderUtil(String fragmentShaderLoc) {
      try {
         int fragmentShaderID = this.createShaderFromFile(fragmentShaderLoc, 35632);
         ARBShaderObjects.glAttachObjectARB(this.programID, fragmentShaderID);
         int vertexShaderID = this.createShaderFromFile("vertex", 35633);
         ARBShaderObjects.glAttachObjectARB(this.programID, vertexShaderID);
         ARBShaderObjects.glLinkProgramARB(this.programID);
         if (ARBShaderObjects.glGetObjectParameteriARB(this.programID, 35714) == 0) {
            throw new IllegalStateException("Shader program failed to link: " + ARBShaderObjects.glGetInfoLogARB(this.programID, 4096));
         }
      } catch (IOException var4) {
         var4.printStackTrace();
         System.out.println("Ошибка при загрузке шейдера: " + fragmentShaderLoc);
      }

   }

   public ShaderUtil(String fragmentShaderLoc, String vertexShaderLoc) {
      try {
         int fragmentShaderID = this.createShaderFromFile(fragmentShaderLoc, 35632);
         ARBShaderObjects.glAttachObjectARB(this.programID, fragmentShaderID);
         int vertexShaderID = this.createShaderFromFile(vertexShaderLoc, 35633);
         ARBShaderObjects.glAttachObjectARB(this.programID, vertexShaderID);
         ARBShaderObjects.glLinkProgramARB(this.programID);
         if (ARBShaderObjects.glGetObjectParameteriARB(this.programID, 35714) == 0) {
            throw new IllegalStateException("Shader program failed to link: " + ARBShaderObjects.glGetInfoLogARB(this.programID, 4096));
         }
      } catch (IOException var5) {
         var5.printStackTrace();
         System.out.println("Ошибка при загрузке шейдера: " + fragmentShaderLoc);
      }

   }

   public void attach() {
      ARBShaderObjects.glUseProgramObjectARB(this.programID);
   }

   public void detach() {
      GL20.glUseProgram(0);
   }

   public void unloadProgram() {
      GL20.glUseProgram(0);
   }

   public void useProgram() {
      GL20.glUseProgram(this.programID);
   }

   public void setUniform(String name, float... args) {
      int loc = ARBShaderObjects.glGetUniformLocationARB(this.programID, name);
      switch (args.length) {
         case 1:
            ARBShaderObjects.glUniform1fARB(loc, args[0]);
            break;
         case 2:
            ARBShaderObjects.glUniform2fARB(loc, args[0], args[1]);
            break;
         case 3:
            ARBShaderObjects.glUniform3fARB(loc, args[0], args[1], args[2]);
            break;
         case 4:
            ARBShaderObjects.glUniform4fARB(loc, args[0], args[1], args[2], args[3]);
            break;
         default:
            throw new IllegalArgumentException("Недопустимое количество аргументов для uniform '" + name + "'");
      }

   }

   public void setUniform(String name, int... args) {
      int loc = ARBShaderObjects.glGetUniformLocationARB(this.programID, name);
      switch (args.length) {
         case 1:
            ARBShaderObjects.glUniform1iARB(loc, args[0]);
            break;
         case 2:
            ARBShaderObjects.glUniform2iARB(loc, args[0], args[1]);
            break;
         case 3:
            ARBShaderObjects.glUniform3iARB(loc, args[0], args[1], args[2]);
            break;
         case 4:
            ARBShaderObjects.glUniform4iARB(loc, args[0], args[1], args[2], args[3]);
            break;
         default:
            throw new IllegalArgumentException("Недопустимое количество аргументов для uniform '" + name + "'");
      }

   }

   public void setUniformf(String name, float... args) {
      int loc = ARBShaderObjects.glGetUniformLocationARB(this.programID, name);
      switch (args.length) {
         case 1:
            ARBShaderObjects.glUniform1fARB(loc, args[0]);
            break;
         case 2:
            ARBShaderObjects.glUniform2fARB(loc, args[0], args[1]);
            break;
         case 3:
            ARBShaderObjects.glUniform3fARB(loc, args[0], args[1], args[2]);
            break;
         case 4:
            ARBShaderObjects.glUniform4fARB(loc, args[0], args[1], args[2], args[3]);
      }

   }

   public void setUniformf(String var1, double... args) {
      int loc = ARBShaderObjects.glGetUniformLocationARB(this.programID, var1);
      switch (args.length) {
         case 1:
            ARBShaderObjects.glUniform1fARB(loc, (float)args[0]);
            break;
         case 2:
            ARBShaderObjects.glUniform2fARB(loc, (float)args[0], (float)args[1]);
            break;
         case 3:
            ARBShaderObjects.glUniform3fARB(loc, (float)args[0], (float)args[1], (float)args[2]);
            break;
         case 4:
            ARBShaderObjects.glUniform4fARB(loc, (float)args[0], (float)args[1], (float)args[2], (float)args[3]);
      }

   }

   private int createShaderFromFile(String shaderName, int shaderType) throws IOException {
      int shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType);
      String shaderSource = this.loadShaderFromFile(shaderName);
      ARBShaderObjects.glShaderSourceARB(shader, shaderSource);
      ARBShaderObjects.glCompileShaderARB(shader);
      if (GL20.glGetShaderi(shader, 35713) == 0) {
         String errorLog = GL20.glGetShaderInfoLog(shader, 4096);
         throw new IllegalStateException("Shader (" + shaderName + ") failed to compile: " + errorLog);
      } else {
         return shader;
      }
   }

   private String loadShaderFromFile(String shaderName) throws IOException {
      String path = "assets/minecraft/SkyCore/shaders/" + shaderName + ".glsl";
      InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);

      String var4;
      try {
         if (inputStream == null) {
            throw new FileNotFoundException("Shader file not found: " + path);
         }

         var4 = (String)(new BufferedReader(new InputStreamReader(inputStream))).lines().collect(Collectors.joining("\n"));
      } catch (Throwable var7) {
         if (inputStream != null) {
            try {
               inputStream.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }
         }

         throw var7;
      }

      if (inputStream != null) {
         inputStream.close();
      }

      return var4;
   }

   public int getUniform(String name) {
      return GL20.glGetUniformLocation(this.programID, name);
   }
}

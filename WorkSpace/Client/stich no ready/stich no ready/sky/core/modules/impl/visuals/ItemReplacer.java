package sky.core.modules.impl.visuals;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Quaternion;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.ModeSetting;

public class ItemReplacer extends Module {
   private static final SwordVariant[] SWORD_VARIANTS = createSwordVariants();
   private static final Map VARIANTS_BY_LABEL = createVariantMap();
   private static ItemReplacer instance;
   private final ModeSetting swordModel;
   private String cachedSelectedLabel;
   private SwordVariant cachedSelectedVariant;

   public ItemReplacer() {
      super("ItemReplacer", "Заменяет модели мечей", Category.Visuals);
      this.swordModel = new ModeSetting("Модель меча", SWORD_VARIANTS[0].label, getVariantLabels());
      this.cachedSelectedVariant = SWORD_VARIANTS[0];
      instance = this;
      this.addSettings(new Setting[]{this.swordModel});
   }

   public static boolean renderReplacement(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
      if (stack != null && !stack.isEmpty() && stack.getItem() instanceof SwordItem) {
         ItemReplacer replacer = instance;
         return replacer != null && replacer.isEnabled() ? replacer.getSelectedVariant().model.render(stack, transformType, matrixStack, buffer, combinedLight, combinedOverlay) : false;
      } else {
         return false;
      }
   }

   public SwordVariant getSelectedVariant() {
      String selected = (String)this.swordModel.get();
      if (selected == null) {
         return SWORD_VARIANTS[0];
      } else if (selected.equalsIgnoreCase(this.cachedSelectedLabel)) {
         return this.cachedSelectedVariant;
      } else {
         SwordVariant variant = (SwordVariant)VARIANTS_BY_LABEL.get(selected.toLowerCase(Locale.ROOT));
         if (variant == null) {
            variant = SWORD_VARIANTS[0];
         }

         this.cachedSelectedLabel = selected;
         this.cachedSelectedVariant = variant;
         return variant;
      }
   }

   public void onDisable() {
      this.clearLoadedModels();
      super.onDisable();
   }

   public void clearLoadedModels() {
      SwordVariant[] var1 = SWORD_VARIANTS;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         SwordVariant variant = var1[var3];
         variant.model.clear();
      }

   }

   public static String[] getVariantLabels() {
      String[] labels = new String[SWORD_VARIANTS.length];

      for(int i = 0; i < SWORD_VARIANTS.length; ++i) {
         labels[i] = SWORD_VARIANTS[i].label;
      }

      return labels;
   }

   private static Map createVariantMap() {
      Map variants = new HashMap(SWORD_VARIANTS.length * 2);
      SwordVariant[] var1 = SWORD_VARIANTS;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         SwordVariant variant = var1[var3];
         variants.put(variant.label.toLowerCase(Locale.ROOT), variant);
      }

      return variants;
   }

   private static SwordVariant[] createSwordVariants() {
      String[][] data = new String[][]{{"Abominable Blade", "abominableblade"}, {"Abominable Greatsaber", "abominablegreatsaber"}, {"Abominable Scythe", "abominablescythe"}, {"Acid Demon", "aciddemon"}, {"Amethyst Shuriken", "amethyst_shuriken"}, {"Ancient Royal Greatsword", "ancient_royal_great_sword"}, {"Aquantic Sacred Blade", "aquantic_sacred_blade"}, {"Aquantic Trident", "aquantictrident"}, {"Arcanethyst", "arcanethyst"}, {"Ashura Blade", "ashura_blade"}, {"Awakened Lichblade", "awakened_lichblade"}, {"Blood Edge", "bloodedge"}, {"Bloody Death", "bloodydeath"}, {"Bramblethorn", "bramblethorn"}, {"Brimstone Claymore", "brimstone_claymore"}, {"Carian Sword", "cariansword"}, {"Chrono Blade", "chrono_blade"}, {"Corrupted Mythic Blade", "corruptedmythicblade"}, {"Creation Splitter", "creationsplitter"}, {"Crescent Rose", "crescentrose"}, {"Cyber Katana", "cyberkatana"}, {"Cyber Mantis Blade", "cybermantisblade"}, {"Cybernetic Katana", "cybernetickatana"}, {"Cybernetic Knife", "cyberneticknife"}, {"Cybernetic Sawblade", "cyberneticsawblade"}, {"Cyber Sword", "cybersword"}, {"Dainsleif", "dainsleif"}, {"Dark Blade", "dark_blade"}, {"Dark Cleaver", "dark_cleaver"}, {"Death Knight Dagger", "death_knight_dagger"}, {"Death Knight Sword", "death_knight_sword"}, {"Demigod's Unholy Blade", "demigodsunholyblade"}, {"Demigod's Unholy Halberd", "demigodsunholyhalberd"}, {"Demonic Blade", "demonicblade"}, {"Demonic Cleaver", "demoniccleaver"}, {"Demon Lord's Great Axe", "demonlordsgreataxe"}, {"Demon Lord's Sword", "demonlordsword"}, {"Divine Justice", "divine_justice"}, {"Divine Reaper", "divine_reaper"}, {"Divine Axe Rhitta", "divineaxerhitta"}, {"Divine Punisher", "divinepunisher"}, {"Dragon Slaying Blade", "dragonslayingblade"}, {"Edge of the Astral Plane", "edgeoftheastralplane"}, {"Emberblade", "emberblade"}, {"Enigma", "enigma"}, {"Epic Sword", "epicsword"}, {"Estoc", "estoc"}, {"Excalibur", "excalibur"}, {"Fallen God Spear", "fallengodspear"}, {"Fallen God Sword", "fallengodsword"}, {"Floral Longsword", "floral_longsword"}, {"Floral Sabre", "floral_sabre"}, {"Forest Guardian Glaive", "forest_guardian_glaive"}, {"Frost Axe", "frostaxe"}, {"Frostblade", "frostblade"}, {"Frost Scythe", "frostscythe"}, {"Green Scythe", "greenscythe"}, {"Hearthflame", "hearthflame"}, {"Hero Sword", "herosword"}, {"Holy Moonlight Sword", "holymoonlightsword"}, {"Hornet's Needle", "hornetsneedle"}, {"Ice Whisper", "icewhisper"}, {"Jade Halberd", "jadehalberd"}, {"Katana", "katana"}, {"Legendary Sword", "legendarysword"}, {"Longsword", "longsword"}, {"Magi Scythe", "magiscythe"}, {"Masamune", "masamune"}, {"Mjolnir", "mjolnir"}, {"Molten Blade", "moltenblade"}, {"Molten Sword", "moltensword"}, {"Muramasa", "muramasa"}, {"Mystical Spellblade", "mysticalspellblade"}, {"Mythic Blade", "mythicblade"}, {"Partisan", "partisan"}, {"Pharaoh's Treasure", "pharaohs_treasure"}, {"Phoenix Grace", "pheonixgrace"}, {"Powerfuse Hammer", "powerfusehammer"}, {"Powerfuse Sword", "powerfusesword"}, {"Requiem of Hell", "requiem_of_hell"}, {"Ribbon Cleaver", "ribboncleaver"}, {"Righteous Relic", "righteous_relic"}, {"Rivers of Blood", "riversofblood"}, {"Royal Chakram", "royalchakram"}, {"Royal Rapier", "royalrapier"}, {"Sabre", "sabre"}, {"Scissor Blade", "scissorblade"}, {"Sculk Cleaver", "sculkcleaver"}, {"Sculk Scythe", "sculkscythe"}, {"Sculk Sword", "sculksword"}, {"Sentinel's Will", "sentinels_will"}, {"Silverine Blade", "silverine_blade"}, {"Soul Claws", "soulclaws"}, {"Soul Edge", "souledge"}, {"Soul Harvester", "soulharvester"}, {"Soul Render", "soulrender"}, {"Soul Stealer", "soulstealer"}, {"Soul Collector", "soul_collector"}, {"Soul Devourer", "soul_devourer"}, {"Star's Edge", "stars_edge"}, {"Steel Sword", "steelsword"}, {"Stop Sign", "stop_sign"}, {"Stormbringer", "stormbringer"}, {"Storm's Edge", "storms_edge"}, {"Sunbreak", "sunbreak"}, {"Tengen's Blade", "tengensblade"}, {"Terrablade", "terrablade"}, {"Thousand Demon Daggers", "thousanddemondaggers"}, {"Thunderbrand", "thunderbrand"}, {"Thunderbringer", "thunderbringer"}, {"Toxic Longsword", "toxic_longsword"}, {"Vampiric Needle", "vampiricneedle"}, {"Wakizashi", "wakizashi"}, {"Watcher Claymore", "watcher_claymore"}, {"Watching Warglaive", "watching_warglaive"}, {"Waxweaver", "waxweaver"}, {"Whisperwind", "whisperwind"}, {"Wickpiercer", "wickpiercer"}, {"Yoru", "yoru"}};
      SwordVariant[] variants = new SwordVariant[data.length];

      for(int i = 0; i < data.length; ++i) {
         variants[i] = new SwordVariant(data[i][0], data[i][1]);
      }

      return variants;
   }

   public static final class SwordVariant {
      public final String label;
      public final String modelName;
      private final RuntimeSwordModel model;

      private SwordVariant(String label, String modelName) {
         this.label = label;
         this.modelName = modelName;
         this.model = new RuntimeSwordModel(modelName);
      }
   }

   private static final class RuntimeSwordModel {
      private static final String MODEL_ROOT = "SkyCore/item_replacer/sword/models/";
      private static final String TEXTURE_ROOT = "SkyCore/item_replacer/sword/textures/";
      private static final float PIXEL = 0.0625F;
      private static final float MODEL_CENTER = 0.5F;
      private static final int VERTEX_STRIDE = 8;
      private final Minecraft mc = Minecraft.getInstance();
      private final String modelPath;
      private final ArrayList cubes = new ArrayList();
      private final DisplayTransform[] displayTransforms = new DisplayTransform[8];
      private ResourceLocation textureLocation;
      private RenderType renderType;
      private float[] bakedMesh = new float[0];
      private int vertexCount;
      private boolean loaded;

      private RuntimeSwordModel(String modelName) {
         this.modelPath = "SkyCore/item_replacer/sword/models/" + modelName + ".json";
      }

      private void clear() {
         this.loaded = false;
         this.textureLocation = null;
         this.renderType = null;
         this.bakedMesh = new float[0];
         this.vertexCount = 0;
         this.cubes.clear();

         for(int i = 0; i < this.displayTransforms.length; ++i) {
            this.displayTransforms[i] = null;
         }

      }

      private boolean render(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
         this.loadIfNeeded();
         if (this.vertexCount != 0 && this.renderType != null) {
            IVertexBuilder builder = ItemRenderer.getEntityGlintVertexBuilder(buffer, this.renderType, true, stack.hasEffect());
            matrixStack.push();
            this.applyDisplayTransform(matrixStack, transformType);
            MatrixStack.Entry entry = matrixStack.getLast();
            this.drawMesh(builder, entry.getMatrix(), entry.getNormal(), combinedLight, combinedOverlay);
            matrixStack.pop();
            return true;
         } else {
            return false;
         }
      }

      private void loadIfNeeded() {
         if (!this.loaded) {
            this.loaded = true;

            try {
               InputStream input = this.mc.getResourceManager().getResource(new ResourceLocation("minecraft", this.modelPath)).getInputStream();

               try {
                  Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);

                  try {
                     JsonObject root = (new JsonParser()).parse(reader).getAsJsonObject();
                     this.readModel(root);
                  } catch (Throwable var7) {
                     try {
                        reader.close();
                     } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                     }

                     throw var7;
                  }

                  reader.close();
               } catch (Throwable var8) {
                  if (input != null) {
                     try {
                        input.close();
                     } catch (Throwable var5) {
                        var8.addSuppressed(var5);
                     }
                  }

                  throw var8;
               }

               if (input != null) {
                  input.close();
               }
            } catch (Exception var9) {
               String var10001 = this.modelPath;
               System.err.println("[ItemReplacer] Failed to load model " + var10001 + ": " + var9.getMessage());
            }

         }
      }

      private void readModel(JsonObject root) {
         JsonObject texturesObject = root.getAsJsonObject("textures");
         if (texturesObject != null) {
            Map textureMap = new HashMap();
            Iterator var4 = texturesObject.entrySet().iterator();

            while(var4.hasNext()) {
               Map.Entry entry = (Map.Entry)var4.next();
               textureMap.put((String)entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
            }

            String primaryTextureKey = this.resolvePrimaryTextureKey(textureMap, root);
            String primaryTexturePath = (String)textureMap.get(primaryTextureKey);
            if (primaryTexturePath != null) {
               String textureName = primaryTexturePath.contains("/") ? primaryTexturePath.substring(primaryTexturePath.lastIndexOf(47) + 1) : primaryTexturePath;
               this.textureLocation = new ResourceLocation("minecraft", "SkyCore/item_replacer/sword/textures/" + textureName + ".png");
               this.renderType = this.hasPartialAlpha(this.textureLocation) ? RenderType.getEntityTranslucent(this.textureLocation) : RenderType.getEntityCutout(this.textureLocation);
               JsonArray elements = root.getAsJsonArray("elements");
               if (elements != null) {
                  String primaryTextureReference = "#" + primaryTextureKey;
                  Iterator var9 = elements.iterator();

                  while(var9.hasNext()) {
                     JsonElement element = (JsonElement)var9.next();
                     JsonObject object = element.getAsJsonObject();
                     JsonArray from = object.getAsJsonArray("from");
                     JsonArray to = object.getAsJsonArray("to");
                     if (from != null && to != null) {
                        ModelCube cube = new ModelCube(from.get(0).getAsFloat() * 0.0625F, from.get(1).getAsFloat() * 0.0625F, from.get(2).getAsFloat() * 0.0625F, to.get(0).getAsFloat() * 0.0625F, to.get(1).getAsFloat() * 0.0625F, to.get(2).getAsFloat() * 0.0625F);
                        JsonObject faces = object.getAsJsonObject("faces");
                        if (faces != null && this.readFaces(cube, faces, primaryTextureReference)) {
                           if (object.has("rotation")) {
                              this.readRotation(cube, object.getAsJsonObject("rotation"));
                           }

                           this.cubes.add(cube);
                        }
                     }
                  }

                  this.bakeMesh();
                  this.readDisplayTransforms(root);
               }
            }
         }
      }

      private boolean readFaces(ModelCube cube, JsonObject faces, String primaryTextureReference) {
         boolean hasFace = false;
         if (faces.has("north")) {
            cube.north = this.readFace(faces.getAsJsonObject("north"), primaryTextureReference);
            hasFace |= cube.north != null;
         }

         if (faces.has("south")) {
            cube.south = this.readFace(faces.getAsJsonObject("south"), primaryTextureReference);
            hasFace |= cube.south != null;
         }

         if (faces.has("east")) {
            cube.east = this.readFace(faces.getAsJsonObject("east"), primaryTextureReference);
            hasFace |= cube.east != null;
         }

         if (faces.has("west")) {
            cube.west = this.readFace(faces.getAsJsonObject("west"), primaryTextureReference);
            hasFace |= cube.west != null;
         }

         if (faces.has("up")) {
            cube.up = this.readFace(faces.getAsJsonObject("up"), primaryTextureReference);
            hasFace |= cube.up != null;
         }

         if (faces.has("down")) {
            cube.down = this.readFace(faces.getAsJsonObject("down"), primaryTextureReference);
            hasFace |= cube.down != null;
         }

         return hasFace;
      }

      private FaceUv readFace(JsonObject face, String primaryTextureReference) {
         if (face.has("texture") && !face.get("texture").getAsString().equals(primaryTextureReference)) {
            return null;
         } else {
            JsonArray uv = face.getAsJsonArray("uv");
            return uv != null && uv.size() >= 4 ? new FaceUv(uv.get(0).getAsFloat() * 0.0625F, uv.get(1).getAsFloat() * 0.0625F, uv.get(2).getAsFloat() * 0.0625F, uv.get(3).getAsFloat() * 0.0625F) : null;
         }
      }

      private void readRotation(ModelCube cube, JsonObject rotation) {
         if (rotation != null && rotation.has("origin") && rotation.has("axis") && rotation.has("angle")) {
            JsonArray origin = rotation.getAsJsonArray("origin");
            cube.rotationOriginX = origin.get(0).getAsFloat() * 0.0625F;
            cube.rotationOriginY = origin.get(1).getAsFloat() * 0.0625F;
            cube.rotationOriginZ = origin.get(2).getAsFloat() * 0.0625F;
            cube.setRotation(rotation.get("axis").getAsString(), rotation.get("angle").getAsFloat());
         }
      }

      private String resolvePrimaryTextureKey(Map textureMap, JsonObject root) {
         if (textureMap.containsKey("0")) {
            return "0";
         } else {
            Map faceUseCount = new HashMap();
            JsonArray elements = root.getAsJsonArray("elements");
            if (elements != null) {
               Iterator var5 = elements.iterator();

               label60:
               while(true) {
                  JsonObject faces;
                  do {
                     if (!var5.hasNext()) {
                        break label60;
                     }

                     JsonElement element = (JsonElement)var5.next();
                     faces = element.getAsJsonObject().getAsJsonObject("faces");
                  } while(faces == null);

                  Iterator var8 = faces.entrySet().iterator();

                  while(var8.hasNext()) {
                     Map.Entry faceEntry = (Map.Entry)var8.next();
                     JsonObject face = ((JsonElement)faceEntry.getValue()).getAsJsonObject();
                     if (face.has("texture")) {
                        String reference = face.get("texture").getAsString().replace("#", "");
                        faceUseCount.merge(reference, 1, Integer::sum);
                     }
                  }
               }
            }

            String bestKey = null;
            int bestCount = -1;
            Iterator var14 = faceUseCount.entrySet().iterator();

            while(var14.hasNext()) {
               Map.Entry entry = (Map.Entry)var14.next();
               String key = (String)entry.getKey();
               int count = (Integer)entry.getValue();
               if (textureMap.containsKey(key) && !"particle".equals(key) && count > bestCount) {
                  bestKey = key;
                  bestCount = count;
               }
            }

            if (bestKey != null) {
               return bestKey;
            } else {
               var14 = textureMap.keySet().iterator();

               String key;
               do {
                  if (!var14.hasNext()) {
                     return (String)textureMap.keySet().iterator().next();
                  }

                  key = (String)var14.next();
               } while("particle".equals(key));

               return key;
            }
         }
      }

      private boolean hasPartialAlpha(ResourceLocation location) {
         try {
            InputStream input = this.mc.getResourceManager().getResource(location).getInputStream();

            boolean var7;
            label95: {
               boolean var13;
               try {
                  NativeImage image;
                  label97: {
                     image = NativeImage.read(input);

                     try {
                        int y = 0;

                        label83:
                        while(true) {
                           if (y >= image.getHeight()) {
                              var13 = false;
                              break label97;
                           }

                           for(int x = 0; x < image.getWidth(); ++x) {
                              int alpha = image.getPixelRGBA(x, y) >>> 24 & 255;
                              if (alpha > 0 && alpha < 255) {
                                 var7 = true;
                                 break label83;
                              }
                           }

                           ++y;
                        }
                     } catch (Throwable var10) {
                        if (image != null) {
                           try {
                              image.close();
                           } catch (Throwable var9) {
                              var10.addSuppressed(var9);
                           }
                        }

                        throw var10;
                     }

                     if (image != null) {
                        image.close();
                     }
                     break label95;
                  }

                  if (image != null) {
                     image.close();
                  }
               } catch (Throwable var11) {
                  if (input != null) {
                     try {
                        input.close();
                     } catch (Throwable var8) {
                        var11.addSuppressed(var8);
                     }
                  }

                  throw var11;
               }

               if (input != null) {
                  input.close();
               }

               return var13;
            }

            if (input != null) {
               input.close();
            }

            return var7;
         } catch (Exception var12) {
            return true;
         }
      }

      private void bakeMesh() {
         int faceCount = 0;

         ModelCube cube;
         for(Iterator var2 = this.cubes.iterator(); var2.hasNext(); faceCount += cube.countFaces()) {
            cube = (ModelCube)var2.next();
         }

         float[] baked = new float[faceCount * 4 * 8];
         int offset = 0;

         ModelCube cube;
         for(Iterator var4 = this.cubes.iterator(); var4.hasNext(); offset = this.bakeCube(baked, offset, cube)) {
            cube = (ModelCube)var4.next();
         }

         this.bakedMesh = baked;
         this.vertexCount = offset / 8;
         this.cubes.clear();
         this.cubes.trimToSize();
      }

      private int bakeCube(float[] out, int offset, ModelCube cube) {
         float x1 = cube.x1;
         float y1 = cube.y1;
         float z1 = cube.z1;
         float x2 = cube.x2;
         float y2 = cube.y2;
         float z2 = cube.z2;
         FaceUv face;
         if (cube.north != null) {
            face = cube.north;
            offset = this.writeBakedVertex(out, offset, cube, x2, y1, z1, face.u1, face.v2, 0.0F, 0.0F, -1.0F);
            offset = this.writeBakedVertex(out, offset, cube, x1, y1, z1, face.u2, face.v2, 0.0F, 0.0F, -1.0F);
            offset = this.writeBakedVertex(out, offset, cube, x1, y2, z1, face.u2, face.v1, 0.0F, 0.0F, -1.0F);
            offset = this.writeBakedVertex(out, offset, cube, x2, y2, z1, face.u1, face.v1, 0.0F, 0.0F, -1.0F);
         }

         if (cube.south != null) {
            face = cube.south;
            offset = this.writeBakedVertex(out, offset, cube, x1, y1, z2, face.u1, face.v2, 0.0F, 0.0F, 1.0F);
            offset = this.writeBakedVertex(out, offset, cube, x2, y1, z2, face.u2, face.v2, 0.0F, 0.0F, 1.0F);
            offset = this.writeBakedVertex(out, offset, cube, x2, y2, z2, face.u2, face.v1, 0.0F, 0.0F, 1.0F);
            offset = this.writeBakedVertex(out, offset, cube, x1, y2, z2, face.u1, face.v1, 0.0F, 0.0F, 1.0F);
         }

         if (cube.east != null) {
            face = cube.east;
            offset = this.writeBakedVertex(out, offset, cube, x2, y1, z2, face.u1, face.v2, 1.0F, 0.0F, 0.0F);
            offset = this.writeBakedVertex(out, offset, cube, x2, y1, z1, face.u2, face.v2, 1.0F, 0.0F, 0.0F);
            offset = this.writeBakedVertex(out, offset, cube, x2, y2, z1, face.u2, face.v1, 1.0F, 0.0F, 0.0F);
            offset = this.writeBakedVertex(out, offset, cube, x2, y2, z2, face.u1, face.v1, 1.0F, 0.0F, 0.0F);
         }

         if (cube.west != null) {
            face = cube.west;
            offset = this.writeBakedVertex(out, offset, cube, x1, y1, z1, face.u1, face.v2, -1.0F, 0.0F, 0.0F);
            offset = this.writeBakedVertex(out, offset, cube, x1, y1, z2, face.u2, face.v2, -1.0F, 0.0F, 0.0F);
            offset = this.writeBakedVertex(out, offset, cube, x1, y2, z2, face.u2, face.v1, -1.0F, 0.0F, 0.0F);
            offset = this.writeBakedVertex(out, offset, cube, x1, y2, z1, face.u1, face.v1, -1.0F, 0.0F, 0.0F);
         }

         if (cube.up != null) {
            face = cube.up;
            offset = this.writeBakedVertex(out, offset, cube, x1, y2, z1, face.u1, face.v1, 0.0F, 1.0F, 0.0F);
            offset = this.writeBakedVertex(out, offset, cube, x1, y2, z2, face.u1, face.v2, 0.0F, 1.0F, 0.0F);
            offset = this.writeBakedVertex(out, offset, cube, x2, y2, z2, face.u2, face.v2, 0.0F, 1.0F, 0.0F);
            offset = this.writeBakedVertex(out, offset, cube, x2, y2, z1, face.u2, face.v1, 0.0F, 1.0F, 0.0F);
         }

         if (cube.down != null) {
            face = cube.down;
            offset = this.writeBakedVertex(out, offset, cube, x1, y1, z2, face.u1, face.v1, 0.0F, -1.0F, 0.0F);
            offset = this.writeBakedVertex(out, offset, cube, x1, y1, z1, face.u1, face.v2, 0.0F, -1.0F, 0.0F);
            offset = this.writeBakedVertex(out, offset, cube, x2, y1, z1, face.u2, face.v2, 0.0F, -1.0F, 0.0F);
            offset = this.writeBakedVertex(out, offset, cube, x2, y1, z2, face.u2, face.v1, 0.0F, -1.0F, 0.0F);
         }

         return offset;
      }

      private int writeBakedVertex(float[] out, int offset, ModelCube cube, float x, float y, float z, float u, float v, float normalX, float normalY, float normalZ) {
         if (cube.rotationAxis != 0) {
            float dx = x - cube.rotationOriginX;
            float dy = y - cube.rotationOriginY;
            float dz = z - cube.rotationOriginZ;
            float sin = cube.rotationSin;
            float cos = cube.rotationCos;
            float rotatedNormalX;
            float rotatedNormalY;
            switch (cube.rotationAxis) {
               case 1:
                  y = cube.rotationOriginY + dy * cos - dz * sin;
                  z = cube.rotationOriginZ + dy * sin + dz * cos;
                  rotatedNormalX = normalY * cos - normalZ * sin;
                  rotatedNormalY = normalY * sin + normalZ * cos;
                  normalY = rotatedNormalX;
                  normalZ = rotatedNormalY;
                  break;
               case 2:
                  x = cube.rotationOriginX + dx * cos + dz * sin;
                  z = cube.rotationOriginZ - dx * sin + dz * cos;
                  rotatedNormalX = normalX * cos + normalZ * sin;
                  rotatedNormalY = -normalX * sin + normalZ * cos;
                  normalX = rotatedNormalX;
                  normalZ = rotatedNormalY;
                  break;
               case 3:
                  x = cube.rotationOriginX + dx * cos - dy * sin;
                  y = cube.rotationOriginY + dx * sin + dy * cos;
                  rotatedNormalX = normalX * cos - normalY * sin;
                  rotatedNormalY = normalX * sin + normalY * cos;
                  normalX = rotatedNormalX;
                  normalY = rotatedNormalY;
            }
         }

         out[offset++] = x - 0.5F;
         out[offset++] = y - 0.5F;
         out[offset++] = z - 0.5F;
         out[offset++] = u;
         out[offset++] = v;
         out[offset++] = normalX;
         out[offset++] = normalY;
         out[offset++] = normalZ;
         return offset;
      }

      private void readDisplayTransforms(JsonObject root) {
         if (root.has("display")) {
            JsonObject display = root.getAsJsonObject("display");
            this.displayTransforms[0] = this.readDisplayTransform(display, "thirdperson_righthand");
            this.displayTransforms[1] = this.readDisplayTransform(display, "thirdperson_lefthand");
            this.displayTransforms[2] = this.readDisplayTransform(display, "firstperson_righthand");
            this.displayTransforms[3] = this.readDisplayTransform(display, "firstperson_lefthand");
            this.displayTransforms[4] = this.readDisplayTransform(display, "ground");
            this.displayTransforms[5] = this.readDisplayTransform(display, "gui");
            this.displayTransforms[6] = this.readDisplayTransform(display, "head");
            this.displayTransforms[7] = this.readDisplayTransform(display, "fixed");
         }
      }

      private DisplayTransform readDisplayTransform(JsonObject display, String key) {
         if (!display.has(key)) {
            return null;
         } else {
            JsonObject object = display.getAsJsonObject(key);
            float[] rotation = new float[]{0.0F, 0.0F, 0.0F};
            float[] translation = new float[]{0.0F, 0.0F, 0.0F};
            float[] scale = new float[]{1.0F, 1.0F, 1.0F};
            if (object.has("rotation")) {
               this.readFloatArray(object.getAsJsonArray("rotation"), rotation);
            }

            if (object.has("translation")) {
               this.readFloatArray(object.getAsJsonArray("translation"), translation);
            }

            if (object.has("scale")) {
               this.readFloatArray(object.getAsJsonArray("scale"), scale);
            }

            return new DisplayTransform(rotation, translation, scale);
         }
      }

      private void readFloatArray(JsonArray array, float[] target) {
         for(int i = 0; i < target.length && i < array.size(); ++i) {
            target[i] = array.get(i).getAsFloat();
         }

      }

      private void applyDisplayTransform(MatrixStack matrixStack, ItemCameraTransforms.TransformType type) {
         DisplayTransform transform = this.getDisplayTransform(type);
         if (transform != null) {
            boolean leftHand = type == TransformType.THIRD_PERSON_LEFT_HAND || type == TransformType.FIRST_PERSON_LEFT_HAND;
            transform.apply(matrixStack, leftHand);
         }
      }

      private DisplayTransform getDisplayTransform(ItemCameraTransforms.TransformType type) {
         switch (type) {
            case THIRD_PERSON_RIGHT_HAND:
               return this.displayTransforms[0];
            case THIRD_PERSON_LEFT_HAND:
               return this.displayTransforms[1];
            case FIRST_PERSON_RIGHT_HAND:
               return this.displayTransforms[2];
            case FIRST_PERSON_LEFT_HAND:
               return this.displayTransforms[3];
            case GROUND:
               return this.displayTransforms[4];
            case GUI:
               return this.displayTransforms[5];
            case HEAD:
               return this.displayTransforms[6];
            case FIXED:
               return this.displayTransforms[7];
            default:
               return null;
         }
      }

      private void drawMesh(IVertexBuilder builder, Matrix4f matrix, Matrix3f normal, int light, int overlay) {
         int limit = this.vertexCount * 8;

         for(int i = 0; i < limit; i += 8) {
            builder.pos(matrix, this.bakedMesh[i], this.bakedMesh[i + 1], this.bakedMesh[i + 2]).color(255, 255, 255, 255).tex(this.bakedMesh[i + 3], this.bakedMesh[i + 4]).overlay(overlay).lightmap(light).normal(normal, this.bakedMesh[i + 5], this.bakedMesh[i + 6], this.bakedMesh[i + 7]).endVertex();
         }

      }
   }

   private static final class FaceUv {
      private final float u1;
      private final float v1;
      private final float u2;
      private final float v2;

      private FaceUv(float u1, float v1, float u2, float v2) {
         this.u1 = u1;
         this.v1 = v1;
         this.u2 = u2;
         this.v2 = v2;
      }
   }

   private static final class ModelCube {
      private static final int NO_ROTATION = 0;
      private static final int ROTATE_X = 1;
      private static final int ROTATE_Y = 2;
      private static final int ROTATE_Z = 3;
      private final float x1;
      private final float y1;
      private final float z1;
      private final float x2;
      private final float y2;
      private final float z2;
      private FaceUv north;
      private FaceUv south;
      private FaceUv east;
      private FaceUv west;
      private FaceUv up;
      private FaceUv down;
      private float rotationOriginX;
      private float rotationOriginY;
      private float rotationOriginZ;
      private int rotationAxis = 0;
      private float rotationSin;
      private float rotationCos = 1.0F;

      private ModelCube(float x1, float y1, float z1, float x2, float y2, float z2) {
         this.x1 = x1;
         this.y1 = y1;
         this.z1 = z1;
         this.x2 = x2;
         this.y2 = y2;
         this.z2 = z2;
      }

      private int countFaces() {
         int faces = 0;
         if (this.north != null) {
            ++faces;
         }

         if (this.south != null) {
            ++faces;
         }

         if (this.east != null) {
            ++faces;
         }

         if (this.west != null) {
            ++faces;
         }

         if (this.up != null) {
            ++faces;
         }

         if (this.down != null) {
            ++faces;
         }

         return faces;
      }

      private void setRotation(String axis, float angle) {
         switch (axis) {
            case "x":
               this.rotationAxis = 1;
               break;
            case "y":
               this.rotationAxis = 2;
               break;
            case "z":
               this.rotationAxis = 3;
               break;
            default:
               this.rotationAxis = 0;
               return;
         }

         float radians = (float)Math.toRadians((double)angle);
         this.rotationSin = (float)Math.sin((double)radians);
         this.rotationCos = (float)Math.cos((double)radians);
      }
   }

   private static final class DisplayTransform {
      private final double translationX;
      private final double mirroredTranslationX;
      private final double translationY;
      private final double translationZ;
      private final Quaternion rotation;
      private final Quaternion mirroredRotation;
      private final float scaleX;
      private final float scaleY;
      private final float scaleZ;
      private final boolean hasTranslation;
      private final boolean hasRotation;
      private final boolean hasScale;

      private DisplayTransform(float[] rotation, float[] translation, float[] scale) {
         this.translationX = (double)(translation[0] / 16.0F);
         this.mirroredTranslationX = -this.translationX;
         this.translationY = (double)(translation[1] / 16.0F);
         this.translationZ = (double)(translation[2] / 16.0F);
         this.rotation = new Quaternion(rotation[0], rotation[1], rotation[2], true);
         this.mirroredRotation = new Quaternion(rotation[0], -rotation[1], -rotation[2], true);
         this.scaleX = scale[0];
         this.scaleY = scale[1];
         this.scaleZ = scale[2];
         this.hasTranslation = translation[0] != 0.0F || translation[1] != 0.0F || translation[2] != 0.0F;
         this.hasRotation = rotation[0] != 0.0F || rotation[1] != 0.0F || rotation[2] != 0.0F;
         this.hasScale = scale[0] != 1.0F || scale[1] != 1.0F || scale[2] != 1.0F;
      }

      private void apply(MatrixStack matrixStack, boolean leftHand) {
         if (this.hasTranslation) {
            matrixStack.translate(leftHand ? this.mirroredTranslationX : this.translationX, this.translationY, this.translationZ);
         }

         if (this.hasRotation) {
            matrixStack.rotate(leftHand ? this.mirroredRotation : this.rotation);
         }

         if (this.hasScale) {
            matrixStack.scale(this.scaleX, this.scaleY, this.scaleZ);
         }

      }
   }
}

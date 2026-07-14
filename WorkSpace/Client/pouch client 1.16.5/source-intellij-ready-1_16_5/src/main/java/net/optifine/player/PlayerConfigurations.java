/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package net.optifine.player;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;
import lightning.product.V_772_m;
import lightning.product.X_4340_E;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import lightning.product.n_1658_l;
import lightning.product.ClientBootstrap;
import lightning.product.o_3091_w;
import lightning.product.SantaHat;
import net.optifine.http.FileDownloadThread;
import net.optifine.http.HttpUtils;
import net.optifine.player.PlayerConfiguration;
import net.optifine.player.PlayerConfigurationReceiver;
import net.optifine.player.PlayerItemModel;
import net.optifine.player.PlayerItemParser;

public class PlayerConfigurations {
    private static Map mapConfigurations = null;
    private static boolean reloadPlayerItems = Boolean.getBoolean("player.models.reload");
    private static long timeReloadPlayerItemsMs = System.currentTimeMillis();
    private static PlayerConfiguration santaHatConfiguration = null;
    private static boolean santaHatInitialized = false;
    private static boolean loggedOnce = false;
    private static volatile int initAttempts = 0;

    public static void renderPlayerItems(n_1658_l modelBiped, X_4340_E player, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, int packedOverlayIn) {
        PlayerConfiguration playerconfiguration = PlayerConfigurations.getPlayerConfiguration(player);
        if (playerconfiguration != null) {
            playerconfiguration.renderPlayerItems(modelBiped, player, matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        }
    }

    public static void renderHatItem(n_1658_l modelBiped, X_4340_E player, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, int packedOverlayIn) {
        SantaHat playerHat = (SantaHat)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(SantaHat.class);
        if (playerHat == null || !playerHat.w_1484_f()) {
            return;
        }
        MinecraftClient mc = MinecraftClient.A_4115_X();
        if (player != mc.Y_259_p) {
            return;
        }
        PlayerConfigurations.initSantaHat();
        if (santaHatConfiguration != null) {
            if (!loggedOnce) {
                System.out.println("[SantaHat] Rendering hat, models count: " + santaHatConfiguration.getPlayerItemModels().length);
                loggedOnce = true;
            }
            santaHatConfiguration.renderPlayerItemsForced(modelBiped, player, matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        }
    }

    private static synchronized void initSantaHat() {
        block7: {
            if (santaHatInitialized) {
                return;
            }
            if (initAttempts > 0) {
                return;
            }
            ++initAttempts;
            System.out.println("[SantaHat] Initializing santa hat...");
            santaHatConfiguration = new PlayerConfiguration();
            PlayerItemModel playeritemmodel = PlayerConfigurations.downloadModel();
            if (playeritemmodel != null) {
                g_2336_b resourcelocation = new g_2336_b("minecraft", "Pouch/hats/santa.png");
                System.out.println("[SantaHat] Looking for texture at: " + String.valueOf(resourcelocation));
                try {
                    if (MinecraftClient.A_4115_X().T_2506_i().J_1907_R(resourcelocation)) {
                        i_2518_W nativeimage = i_2518_W.n_1700_B(MinecraftClient.A_4115_X().T_2506_i().n_1700_B(resourcelocation).J_1907_R());
                        playeritemmodel.setTextureImage(nativeimage);
                        playeritemmodel.setTextureLocation(resourcelocation);
                        santaHatConfiguration.addPlayerItemModel(playeritemmodel);
                        santaHatInitialized = true;
                        System.out.println("[SantaHat] Model loaded successfully, models: " + santaHatConfiguration.getPlayerItemModels().length);
                        break block7;
                    }
                    System.out.println("[SantaHat] Texture not found: " + String.valueOf(resourcelocation));
                }
                catch (Exception e) {
                    System.out.println("[SantaHat] Failed to load texture: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                System.out.println("[SantaHat] Failed to parse model");
            }
        }
    }

    public static synchronized PlayerConfiguration getPlayerConfiguration(X_4340_E player) {
        String s1;
        V_772_m abstractclientplayerentity;
        if (reloadPlayerItems && System.currentTimeMillis() > timeReloadPlayerItemsMs + 5000L && (abstractclientplayerentity = MinecraftClient.A_4115_X().Y_259_p) != null) {
            PlayerConfigurations.setPlayerConfiguration(abstractclientplayerentity.c_4037_x(), null);
            timeReloadPlayerItemsMs = System.currentTimeMillis();
        }
        if ((s1 = player.c_4037_x()) == null) {
            return null;
        }
        PlayerConfiguration playerconfiguration = (PlayerConfiguration)PlayerConfigurations.getMapConfigurations().get(s1);
        if (playerconfiguration == null) {
            playerconfiguration = new PlayerConfiguration();
            PlayerConfigurations.getMapConfigurations().put(s1, playerconfiguration);
            PlayerConfigurationReceiver playerconfigurationreceiver = new PlayerConfigurationReceiver(s1);
            String s = HttpUtils.getPlayerItemsUrl() + "/users/" + s1 + ".cfg";
            FileDownloadThread filedownloadthread = new FileDownloadThread(s, playerconfigurationreceiver);
            filedownloadthread.start();
        }
        return playerconfiguration;
    }

    public static synchronized void setPlayerConfiguration(String player, PlayerConfiguration pc) {
        PlayerConfigurations.getMapConfigurations().put(player, pc);
    }

    private static Map getMapConfigurations() {
        if (mapConfigurations == null) {
            mapConfigurations = new HashMap();
        }
        return mapConfigurations;
    }

    private static PlayerItemModel downloadModel() {
        try {
            System.out.println("[SantaHat] Parsing model JSON...");
            String s1 = "{\n\t\"type\" : \"PlayerItem\",\n\t\"texture\": \"optifine:textures/features/hat_santa.png\",\n\t\"textureSize\": [64, 32],\n\t\"models\": [\n\t\t{\n\t\t\t\"part\": \"santa_hat\",\n\t\t\t\"id\": \"santa_hat\",\n\t\t\t\"type\": \"ModelBox\",\n\t\t\t\"attachTo\": \"head\", \n\t\t\t\"invertAxis\": \"xy\",\n\t\t\t\"translate\": [0, 0, 0],\n\t\t\t\"rotate\": [0, -90, 0],\n\t\t\t\"submodels\": [\n\t\t\t\t{\n\t\t\t\t\t\"id\": \"sant_hat_top2\",\n\t\t\t\t\t\"invertAxis\": \"xy\",\n\t\t\t\t\t\"translate\": [0.53024, 10.61642, 0],\n\t\t\t\t\t\"rotate\": [0, 0, -60],\n\t\t\t\t\t\"boxes\": [\n\t\t\t\t\t\t{\"coordinates\": [-0.5, 2, -1.5, 3, 3, 3], \"textureOffset\": [0, 0]}\n\t\t\t\t\t]\n\t\t\t\t},\n\t\t\t\t{\n\t\t\t\t\t\"id\": \"sant_hat_top1\",\n\t\t\t\t\t\"invertAxis\": \"xy\",\n\t\t\t\t\t\"translate\": [1.03024, 9.75039, 0],\n\t\t\t\t\t\"rotate\": [0, 0, -50],\n\t\t\t\t\t\"boxes\": [\n\t\t\t\t\t\t{\"coordinates\": [-3, -1, -3, 6, 4, 6], \"textureOffset\": [0, 0]}\n\t\t\t\t\t]\n\t\t\t\t},\n\t\t\t\t{\n\t\t\t\t\t\"id\": \"santa_hat_top0\",\n\t\t\t\t\t\"invertAxis\": \"xy\",\n\t\t\t\t\t\"translate\": [0.2892, 8.72718, 0],\n\t\t\t\t\t\"rotate\": [0, 0, -20],\n\t\t\t\t\t\"boxes\": [\n\t\t\t\t\t\t{\"coordinates\": [-4, -1, -4, 9, 3, 8], \"textureOffset\": [0, 0]}\n\t\t\t\t\t]\n\t\t\t\t},\n\t\t\t\t{\n\t\t\t\t\t\"id\": \"sant_hat_top3\",\n\t\t\t\t\t\"invertAxis\": \"xy\",\n\t\t\t\t\t\"translate\": [5.78024, 12.11642, 0],\n\t\t\t\t\t\"rotate\": [0, 0, 15],\n\t\t\t\t\t\"boxes\": [\n\t\t\t\t\t\t{\"coordinates\": [-0.90192, -1.83013, -2, 4, 4, 4], \"textureOffset\": [0, 16]}\n\t\t\t\t\t]\n\t\t\t\t},\n\t\t\t\t{\n\t\t\t\t\t\"id\": \"santa_hat_base\",\n\t\t\t\t\t\"invertAxis\": \"xy\",\n\t\t\t\t\t\"translate\": [0.2892, 8.72718, 0],\n\t\t\t\t\t\"rotate\": [0, 0, -15],\n\t\t\t\t\t\"boxes\": [\n\t\t\t\t\t\t{\"coordinates\": [-4.5, -3, -4.5, 10, 4, 9], \"textureOffset\": [0, 16], \"sizeAdd\": 0.1}\n\t\t\t\t\t]\n\t\t\t\t}\n\t\t\t]\n\t\t}\n\t]\n}";
            JsonParser jsonparser = new JsonParser();
            JsonObject jsonobject = (JsonObject)jsonparser.parse(s1);
            return PlayerItemParser.parseItemModel(jsonobject);
        }
        catch (Exception exception) {
            System.out.println("[SantaHat] Exception parsing model: " + exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }
}



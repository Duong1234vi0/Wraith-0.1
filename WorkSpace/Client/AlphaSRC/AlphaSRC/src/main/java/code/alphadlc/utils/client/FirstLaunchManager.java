/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.mojang.authlib.minecraft.UserApiService
 *  net.minecraft.class_310
 *  net.minecraft.class_320
 *  net.minecraft.class_320$class_321
 *  net.minecraft.class_5520
 *  net.minecraft.class_7569
 *  net.minecraft.class_7574
 *  net.minecraft.class_7853
 */
package code.alphadlc.utils.client;

import code.alphadlc.AlphaDLC;
import code.alphadlc.display.screens.mainmenu.MainMenu;
import code.alphadlc.mixins.IMinecraftClient;
import code.alphadlc.utils.client.managers.file.impl.AccountFile;
import code.alphadlc.utils.client.managers.file.impl.account.Account;
import code.alphadlc.utils.client.managers.file.impl.account.AccountRepository;
import code.alphadlc.utils.theme.ThemeManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.authlib.minecraft.UserApiService;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.class_310;
import net.minecraft.class_320;
import net.minecraft.class_5520;
import net.minecraft.class_7569;
import net.minecraft.class_7574;
import net.minecraft.class_7853;

public final class FirstLaunchManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE_NAME = "AuthInfo.json";

    private FirstLaunchManager() {
    }

    public static File getAuthInfoFile() {
        try {
            File filesDir = AlphaDLC.getInstance().getClientInfoProvider().filesDir();
            if (!filesDir.exists()) {
                filesDir.mkdirs();
            }
            return new File(filesDir, FILE_NAME);
        }
        catch (Exception e) {
            File fallbackDir = new File("AlphaDLC/files");
            if (!fallbackDir.exists()) {
                fallbackDir.mkdirs();
            }
            return new File(fallbackDir, FILE_NAME);
        }
    }

    public static boolean shouldShowFirstLaunchAnimation() {
        return !FirstLaunchManager.getAuthInfoFile().exists();
    }

    public static AuthInfo loadAuthInfo() {
        AuthInfo authInfo;
        File file = FirstLaunchManager.getAuthInfoFile();
        if (!file.exists()) {
            return null;
        }
        FileReader reader = new FileReader(file, StandardCharsets.UTF_8);
        try {
            authInfo = (AuthInfo)GSON.fromJson((Reader)reader, AuthInfo.class);
        }
        catch (Throwable throwable) {
            try {
                try {
                    reader.close();
                }
                catch (Throwable throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
            catch (Exception e) {
                return null;
            }
        }
        reader.close();
        return authInfo;
    }

    public static void completeFirstLaunch(String nickname, boolean darkTheme) {
        String normalizedNickname = FirstLaunchManager.sanitizeNickname(nickname);
        String themeName = "default_dark";
        FirstLaunchManager.applyTheme(themeName);
        FirstLaunchManager.setDefaultGuiScaleIfNeeded();
        FirstLaunchManager.saveAuthInfo(normalizedNickname, themeName);
        FirstLaunchManager.registerOfflineAccount(normalizedNickname);
        FirstLaunchManager.applySession(normalizedNickname);
    }

    public static void applySavedPreferences() {
        AuthInfo authInfo = FirstLaunchManager.loadAuthInfo();
        if (authInfo == null) {
            return;
        }
        String nickname = FirstLaunchManager.sanitizeNickname(authInfo.nickname);
        FirstLaunchManager.applyTheme("default_dark");
        FirstLaunchManager.registerOfflineAccount(nickname);
        Account active = FirstLaunchManager.findActiveAccount();
        if (active != null) {
            FirstLaunchManager.applySession(active.name, active.uuid);
        } else {
            FirstLaunchManager.applySession(nickname);
        }
    }

    private static Account findActiveAccount() {
        try {
            AccountRepository repository = AlphaDLC.getInstance().getAccountRepository();
            if (repository == null) {
                return null;
            }
            for (Account account : repository.accountList) {
                if (account == null || !account.active) continue;
                return account;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return null;
    }

    private static void saveAuthInfo(String nickname, String themeName) {
        JsonObject json = new JsonObject();
        json.addProperty("nickname", nickname);
        json.addProperty("theme", themeName);
        json.addProperty("completedAt", (Number)System.currentTimeMillis());
        File file = FirstLaunchManager.getAuthInfoFile();
        try (FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8);){
            GSON.toJson((JsonElement)json, (Appendable)writer);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private static void applyTheme(String themeName) {
        ThemeManager.loadTheme(themeName);
        MainMenu.INSTANCE.isDarkMode = true;
        MainMenu.INSTANCE.themeIcon = "I";
    }

    private static void setDefaultGuiScaleIfNeeded() {
        try {
            class_310 client = class_310.method_1551();
            if (client != null && client.field_1690 != null && (Integer)client.field_1690.method_42474().method_41753() == 0) {
                client.field_1690.method_42474().method_41748((Object)2);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private static void registerOfflineAccount(String nickname) {
        try {
            AccountRepository repository = AlphaDLC.getInstance().getAccountRepository();
            boolean exists = repository.accountList.stream().anyMatch(account -> account.name.equalsIgnoreCase(nickname));
            if (!exists) {
                String offlineUuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + nickname).getBytes(StandardCharsets.UTF_8)).toString();
                repository.accountList.add(0, new Account(nickname, true, false, null, offlineUuid, "0"));
            }
            if (AlphaDLC.getInstance().getFileController() != null) {
                AlphaDLC.getInstance().getFileController().saveFile(AccountFile.class);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private static void applySession(String nickname) {
        String offlineUuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + nickname).getBytes(StandardCharsets.UTF_8)).toString();
        FirstLaunchManager.applySession(nickname, offlineUuid);
    }

    private static void applySession(String nickname, String uuid) {
        try {
            UUID parsedUuid;
            try {
                parsedUuid = UUID.fromString(uuid);
            }
            catch (Exception e) {
                parsedUuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + nickname).getBytes(StandardCharsets.UTF_8));
            }
            class_320 session = new class_320(nickname, parsedUuid, "0", Optional.empty(), Optional.empty(), class_320.class_321.field_1988);
            IMinecraftClient client = (IMinecraftClient)class_310.method_1551();
            client.setSessionT(session);
            class_310.method_1551().method_53462().getProperties().clear();
            UserApiService apiService = UserApiService.OFFLINE;
            client.setUserApiService(apiService);
            client.setSocialInteractionsManagerT(new class_5520(class_310.method_1551(), apiService));
            client.setProfileKeys(class_7853.method_46532((UserApiService)apiService, (class_320)session, (Path)class_310.method_1551().field_1697.toPath()));
            client.setAbuseReportContextT(class_7574.method_44599((class_7569)class_7569.method_44586(), (UserApiService)apiService));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private static String sanitizeNickname(String nickname) {
        if (nickname == null) {
            return "Player";
        }
        String cleaned = nickname.replaceAll("[^A-Za-z0-9_]", "");
        if (cleaned.length() < 3) {
            cleaned = "Player";
        }
        if (cleaned.length() > 16) {
            cleaned = cleaned.substring(0, 16);
        }
        return cleaned;
    }

    public static class AuthInfo {
        public String nickname;
        public String theme;
        public long completedAt;
    }
}

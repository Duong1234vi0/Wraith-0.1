package ua.babka.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import ua.babka.integration.discord.IPCUser;
import ua.babka.module.ModuleInformation;

public final class StartupLogger {
    private StartupLogger() {
    }

    public static void send(String nickname, IPCUser discordUser) {
        StartupLogger.postAsync(() -> {
            String discord = discordUser != null ? discordUser.username() : "unknown";
            StartupLogger.sendEmbed(ModuleInformation.WaifuStore.get(), "babka reborn — запуск", 5793266, StartupLogger.field("Никнейм", nickname), StartupLogger.field("Discord", discord));
        });
    }

    private static void postAsync(Runnable r2) {
        Thread t2 = new Thread(() -> {
            try {
                r2.run();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }, "babka-logger");
        t2.setDaemon(true);
        t2.start();
    }

    private static void sendEmbed(String webhookUrl, String title, int color, String ... fields) {
        try {
            StringBuilder fieldsJson = new StringBuilder();
            for (int i2 = 0; i2 < fields.length; ++i2) {
                if (i2 > 0) {
                    fieldsJson.append(",");
                }
                fieldsJson.append(fields[i2]);
            }
            String body = "{\"embeds\":[{\"title\":\"" + StartupLogger.esc(title) + "\",\"color\":" + color + ",\"fields\":[" + String.valueOf(fieldsJson) + "]}]}";
            HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5L)).build().send(HttpRequest.newBuilder().uri(URI.create(webhookUrl)).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(body)).timeout(Duration.ofSeconds(8L)).build(), HttpResponse.BodyHandlers.discarding());
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private static String field(String name, String value) {
        return "{\"name\":\"" + StartupLogger.esc(name) + "\",\"value\":\"" + StartupLogger.esc(value) + "\",\"inline\":true}";
    }

    private static String esc(String s2) {
        if (s2 == null) {
            return "null";
        }
        return s2.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}


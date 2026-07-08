/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 *  net.minecraft.class_5250
 */
package code.alphadlc.irc;

import code.alphadlc.display.hud.Notifications;
import code.alphadlc.events.chat.ChatEvent;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.irc.IrcMessage;
import code.alphadlc.irc.IrcRelayClient;
import code.alphadlc.main.listener.Listener;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.sound.SoundManager;
import code.alphadlc.utils.display.interfaces.QuickImports;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_5250;

public class IrcManager
implements Listener,
QuickImports {
    private static final String RELAY_URL = "wss://AlphaDLC-emotions.onrender.com";
    private static final String MESSAGE_PREFIX = "__irc__:";
    private static final String IRC_SERVER_ID = "irc";
    private static final long SEND_COOLDOWN_MS = 10000L;
    private static final int MAX_HISTORY = 160;
    private static IrcManager instance;
    private final CopyOnWriteArrayList<IrcMessage> messages = new CopyOnWriteArrayList();
    private final ConcurrentHashMap.KeySetView<String, Boolean> knownMessageIds = ConcurrentHashMap.newKeySet();
    private IrcRelayClient relayClient;
    private long nextReconnectAttemptMs;
    private long lastSendAtMs;
    private String selectedNickname = "";
    private String lastPrivateContact = "-";

    public static IrcManager create() {
        instance = new IrcManager();
        return instance;
    }

    public static IrcManager getInstance() {
        return instance;
    }

    @EventHandler
    public void onTick(TickEvent event) {
        this.ensureConnected();
    }

    @EventHandler
    public void onChat(ChatEvent event) {
        int split;
        String payload;
        String raw = event.getMessage();
        if (raw == null) {
            return;
        }
        String message = raw.trim();
        if (message.isEmpty() || message.startsWith("/")) {
            return;
        }
        if (!message.toLowerCase(Locale.ROOT).startsWith(".irc")) {
            return;
        }
        String string = payload = message.length() > 4 ? message.substring(4).trim() : "";
        if (payload.isEmpty()) {
            return;
        }
        if (payload.startsWith("@@") && payload.length() > 2) {
            if (this.replyToLastPrivate(payload.substring(2).trim())) {
                event.cancel();
            }
            return;
        }
        if (payload.startsWith("@") && (split = payload.indexOf(32)) > 1 && split < payload.length() - 1) {
            String text;
            String target = payload.substring(1, split).trim();
            if (this.sendPrivateMessage(target, text = payload.substring(split + 1).trim())) {
                event.cancel();
            }
            return;
        }
        if (this.sendGlobalMessage(payload)) {
            event.cancel();
        }
    }

    public List<IrcMessage> getMessages() {
        return List.copyOf(this.messages);
    }

    public boolean sendGlobalMessage(String text) {
        if (!this.canSend(text) || this.isOnCooldown()) {
            return false;
        }
        String finalText = text.trim();
        JsonObject payload = new JsonObject();
        payload.addProperty("messageId", UUID.randomUUID().toString());
        payload.addProperty("kind", "global");
        payload.addProperty("ircNick", this.getDisplayName());
        payload.addProperty("text", finalText);
        payload.addProperty("timestamp", (Number)System.currentTimeMillis());
        if (!this.sendPayload(payload)) {
            return false;
        }
        this.lastSendAtMs = System.currentTimeMillis();
        this.appendMessage(IrcMessage.outgoing(payload.get("messageId").getAsString(), this.getDisplayName(), finalText, this.lastSendAtMs, false, false));
        return true;
    }

    public boolean sendPrivateMessage(String targetName, String text) {
        if (!this.canSend(text) || targetName == null || targetName.isBlank() || this.isOnCooldown()) {
            return false;
        }
        String finalText = text.trim();
        String cleanTarget = targetName.trim();
        JsonObject payload = new JsonObject();
        payload.addProperty("messageId", UUID.randomUUID().toString());
        payload.addProperty("kind", "pm");
        payload.addProperty("ircNick", this.getDisplayName());
        payload.addProperty("targetName", cleanTarget);
        payload.addProperty("text", finalText);
        payload.addProperty("timestamp", (Number)System.currentTimeMillis());
        if (!this.sendPayload(payload)) {
            return false;
        }
        this.lastSendAtMs = System.currentTimeMillis();
        this.lastPrivateContact = cleanTarget;
        this.appendMessage(IrcMessage.outgoing(payload.get("messageId").getAsString(), this.getDisplayName(), finalText, this.lastSendAtMs, true, false));
        return true;
    }

    public boolean replyToLastPrivate(String text) {
        if (this.lastPrivateContact == null || this.lastPrivateContact.isBlank() || "-".equals(this.lastPrivateContact)) {
            return false;
        }
        return this.sendPrivateMessage(this.lastPrivateContact, text);
    }

    public void setNickname(String nickname) {
        this.selectedNickname = this.sanitizeNickname(nickname);
    }

    public String getSelectedNickname() {
        return this.selectedNickname.isBlank() ? this.getMinecraftName() : this.selectedNickname;
    }

    public String getLastPrivateContact() {
        return this.lastPrivateContact;
    }

    public boolean isConnected() {
        return this.relayClient != null && this.relayClient.isOpen();
    }

    public boolean isConnecting() {
        return this.relayClient != null && this.relayClient.isConnectingState();
    }

    public String getConnectionState() {
        if (this.isConnected()) {
            return "connected";
        }
        if (this.isConnecting()) {
            return "connecting";
        }
        return "disconnected";
    }

    public String getRelayUrl() {
        return RELAY_URL;
    }

    public long getRemainingCooldownMs() {
        return Math.max(0L, 10000L - (System.currentTimeMillis() - this.lastSendAtMs));
    }

    public boolean isOnCooldown() {
        return this.getRemainingCooldownMs() > 0L;
    }

    public boolean isIrcViewOpen() {
        return false;
    }

    public String buildMentionTemplate(String playerName) {
        return ".irc @" + playerName + " ";
    }

    public String getDisplayName() {
        return this.getMinecraftName();
    }

    private void ensureConnected() {
        if (this.relayClient != null && (this.relayClient.isOpen() || this.relayClient.isConnectingState())) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now < this.nextReconnectAttemptMs) {
            return;
        }
        this.nextReconnectAttemptMs = now + 10000L;
        try {
            this.relayClient = new IrcRelayClient(URI.create(RELAY_URL), this::handleIncomingMessage);
            this.relayClient.connect();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private boolean canSend(String text) {
        return IrcManager.mc.field_1724 != null && text != null && !text.isBlank();
    }

    private boolean sendPayload(JsonObject payload) {
        if (this.relayClient == null || !this.relayClient.isOpen() || IrcManager.mc.field_1724 == null) {
            return false;
        }
        String encodedPayload = Base64.getUrlEncoder().withoutPadding().encodeToString(gson.toJson((JsonElement)payload).getBytes(StandardCharsets.UTF_8));
        JsonObject packet = new JsonObject();
        packet.addProperty("type", "play_emotion");
        packet.addProperty("playerUuid", IrcManager.mc.field_1724.method_5845());
        packet.addProperty("playerName", IrcManager.mc.field_1724.method_5477().getString());
        packet.addProperty("emotion", MESSAGE_PREFIX + encodedPayload);
        packet.addProperty("startedAt", (Number)(payload.has("timestamp") ? payload.get("timestamp").getAsLong() : System.currentTimeMillis()));
        packet.addProperty("serverId", IRC_SERVER_ID);
        this.relayClient.send(gson.toJson((JsonElement)packet));
        return true;
    }

    private void handleIncomingMessage(String rawMessage) {
        try {
            String kind;
            JsonObject packet = JsonParser.parseString((String)rawMessage).getAsJsonObject();
            if (!packet.has("emotion")) {
                return;
            }
            String emotion = packet.get("emotion").getAsString();
            if (!emotion.startsWith(MESSAGE_PREFIX)) {
                return;
            }
            String decodedPayload = new String(Base64.getUrlDecoder().decode(emotion.substring(MESSAGE_PREFIX.length())), StandardCharsets.UTF_8);
            JsonObject payload = JsonParser.parseString((String)decodedPayload).getAsJsonObject();
            String string = kind = payload.has("kind") ? payload.get("kind").getAsString() : "";
            String senderName = payload.has("ircNick") ? this.sanitizeNickname(payload.get("ircNick").getAsString()) : (packet.has("playerName") ? packet.get("playerName").getAsString() : "Unknown");
            String text = payload.has("text") ? payload.get("text").getAsString() : "";
            String messageId = payload.has("messageId") ? payload.get("messageId").getAsString() : UUID.randomUUID().toString();
            long timestamp = payload.has("timestamp") ? payload.get("timestamp").getAsLong() : System.currentTimeMillis();
            boolean mentioned = this.shouldPing(text, senderName);
            if ("global".equals(kind)) {
                this.appendMessage(IrcMessage.incoming(messageId, senderName, text, timestamp, false, mentioned));
                if (mentioned) {
                    this.notifyMention(senderName, text);
                }
                return;
            }
            if (!"pm".equals(kind) || IrcManager.mc.field_1724 == null || !payload.has("targetName")) {
                return;
            }
            String targetName = payload.get("targetName").getAsString();
            if (!this.matchesSelfIdentity(targetName)) {
                return;
            }
            this.lastPrivateContact = senderName;
            this.appendMessage(IrcMessage.incoming(messageId, senderName, text, timestamp, true, true));
            this.notifyMention(senderName, text);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void appendMessage(IrcMessage message) {
        if (!this.knownMessageIds.add(message.id())) {
            return;
        }
        this.messages.add(message);
        while (this.messages.size() > 160) {
            IrcMessage removed = this.messages.remove(0);
            this.knownMessageIds.remove(removed.id());
        }
        this.pushToIngameChat(message);
    }

    private void pushToIngameChat(IrcMessage message) {
        class_5250 line = message.outgoing() ? class_2561.method_43470((String)"").method_10852((class_2561)class_2561.method_43470((String)(message.direct() ? "[IRC PM ->] " : "[IRC ->] ")).method_27692(class_124.field_1075)).method_10852((class_2561)class_2561.method_43470((String)message.senderName()).method_27692(class_124.field_1068)).method_10852((class_2561)class_2561.method_43470((String)": ").method_27692(class_124.field_1063)).method_10852((class_2561)class_2561.method_43470((String)message.text()).method_27692(class_124.field_1080)) : class_2561.method_43470((String)"").method_10852((class_2561)class_2561.method_43470((String)(message.direct() ? "[IRC PM] " : "[IRC] ")).method_27692(class_124.field_1075)).method_10852((class_2561)class_2561.method_43470((String)message.senderName()).method_27692(class_124.field_1068)).method_10852((class_2561)class_2561.method_43470((String)": ").method_27692(class_124.field_1063)).method_10852((class_2561)class_2561.method_43470((String)message.text()).method_27692(class_124.field_1080));
        ChatMessage.send((class_2561)line);
    }

    private boolean matchesSelfIdentity(String targetName) {
        String displayName = this.getDisplayName();
        String minecraftName = this.getMinecraftName();
        return targetName.equalsIgnoreCase(displayName) || targetName.equalsIgnoreCase(minecraftName);
    }

    private void notifyMention(String senderName, String text) {
        if (this.isIrcViewOpen()) {
            return;
        }
        class_5250 notification = class_2561.method_43470((String)"").method_10852((class_2561)class_2561.method_43470((String)"IRC").method_27692(class_124.field_1075)).method_10852((class_2561)class_2561.method_43470((String)("  " + senderName)).method_27692(class_124.field_1068)).method_10852((class_2561)class_2561.method_43470((String)("  " + this.preview(text))).method_27692(class_124.field_1080));
        Notifications.getInstance().addList((class_2561)notification, 4200, (Object)SoundManager.NOTIF);
    }

    private String preview(String text) {
        if (text == null || text.isBlank()) {
            return "";
        }
        return text.length() > 42 ? text.substring(0, 42) + "..." : text;
    }

    private boolean shouldPing(String text, String senderName) {
        String selfName = this.getDisplayName();
        if (selfName.isBlank() || senderName.equalsIgnoreCase(selfName)) {
            return false;
        }
        String mention = "@" + selfName.toLowerCase(Locale.ROOT);
        return text.toLowerCase(Locale.ROOT).contains(mention);
    }

    private String sanitizeNickname(String nickname) {
        if (nickname == null) {
            return "";
        }
        String cleaned = nickname.trim().replaceAll("[^\\p{L}\\p{N}_-]", "");
        if (cleaned.isBlank()) {
            return "";
        }
        return cleaned.length() > 16 ? cleaned.substring(0, 16) : cleaned;
    }

    private String getMinecraftName() {
        return IrcManager.mc.field_1724 != null ? IrcManager.mc.field_1724.method_5477().getString() : "Unknown";
    }
}

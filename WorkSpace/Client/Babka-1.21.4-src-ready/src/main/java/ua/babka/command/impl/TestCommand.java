package ua.babka.command.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Formatting;
import ua.babka.Babka;
import ua.babka.command.CommandException;
import ua.babka.command.TabCompleteHelper;
import ua.babka.command.argument.IArgConsumer;
import ua.babka.command.impl.Command;

public class TestCommand
extends Command {
    static final String API_BASE = "https://text.pollinations.ai/";
    static final int MAX_REQUESTS_PER_PLAYER = 100;
    static final Map<String, Integer> playerRequests = new HashMap<String, Integer>();
    static String lastResetDate = LocalDate.now().toString();
    static final Map<String, List<Message>> playerHistory = new HashMap<String, List<Message>>();
    static final int MAX_HISTORY = 6;

    public TestCommand(Babka onetap) {
        super("ai");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) {
            this.logDirect("Игрок не найден", Formatting.RED);
            return;
        }
        String playerName = mc.player.getName().getString();
        this.checkDailyReset();
        playerHistory.putIfAbsent(playerName, new ArrayList());
        if (!args.hasAny()) {
            this.logDirect("Использование: ai <вопрос>", Formatting.RED);
            this.logDirect("Команды: ai clear - очистить историю, ai limit - проверить лимит", Formatting.GRAY);
            return;
        }
        String first = args.peekString().toLowerCase(Locale.ROOT);
        if (first.equals("clear")) {
            args.getString();
            playerHistory.get(playerName).clear();
            this.logDirect(String.valueOf(Formatting.DARK_AQUA) + "[AI] " + String.valueOf(Formatting.GREEN) + "История диалога очищена!");
            return;
        }
        if (first.equals("limit")) {
            args.getString();
            int used = playerRequests.getOrDefault(playerName, 0);
            this.logDirect(String.valueOf(Formatting.DARK_AQUA) + "[AI] " + String.valueOf(Formatting.GRAY) + "Использовано запросов: " + used + "/100");
            return;
        }
        int requestsUsed = playerRequests.getOrDefault(playerName, 0);
        if (requestsUsed >= 100) {
            this.logDirect(String.valueOf(Formatting.RED) + "Ты исчерпал лимит запросов на сегодня (100)");
            return;
        }
        StringBuilder question = new StringBuilder();
        while (args.hasAny()) {
            question.append(args.getString()).append(" ");
        }
        String finalQuestion = question.toString().trim();
        if (finalQuestion.isEmpty()) {
            this.logDirect("Что хочешь спросить?", Formatting.RED);
            return;
        }
        this.logDirect(String.valueOf(Formatting.GRAY) + "Вы: " + String.valueOf(Formatting.WHITE) + finalQuestion);
        this.logDirect(String.valueOf(Formatting.DARK_AQUA) + "[AI] " + String.valueOf(Formatting.YELLOW) + "Думаю...");
        playerRequests.put(playerName, requestsUsed + 1);
        playerHistory.get(playerName).add(new Message("user", finalQuestion));
        CompletableFuture.runAsync(() -> {
            try {
                String answer = this.askAI(playerName, finalQuestion);
                mc.execute(() -> this.logDirect(String.valueOf(Formatting.DARK_AQUA) + "[AI] " + String.valueOf(Formatting.WHITE) + answer));
                List<Message> history = playerHistory.get(playerName);
                if (history != null) {
                    history.add(new Message("assistant", answer));
                    while (history.size() > 6) {
                        history.remove(0);
                    }
                }
            }
            catch (Exception ex) {
                mc.execute(() -> {
                    this.logDirect(String.valueOf(Formatting.DARK_AQUA) + "[AI] " + String.valueOf(Formatting.RED) + "Ошибка: " + ex.getMessage());
                    ex.printStackTrace();
                });
            }
        });
    }

    private void checkDailyReset() {
        String todayStr = LocalDate.now().toString();
        if (!todayStr.equals(lastResetDate)) {
            playerRequests.clear();
            playerHistory.clear();
            lastResetDate = todayStr;
        }
    }
    private String askAI(String playerName, String latestQuestion) {
        HttpURLConnection conn = null;
        try {
            String string;
            int startIndex;
            List<Message> history = playerHistory.get(playerName);
            if (history == null) {
                history = new ArrayList<Message>();
            }
            StringBuilder prompt = new StringBuilder();
            prompt.append("Ты AI-ассистент в Майнкрафте. Отвечай кратко и по делу на русском языке.\n\n");
            for (int i2 = startIndex = Math.max(0, history.size() - 6); i2 < history.size(); ++i2) {
                Message msg = history.get(i2);
                if (msg.role().equals("user")) {
                    prompt.append("Пользователь: ").append(msg.content()).append("\n");
                    continue;
                }
                prompt.append("Ассистент: ").append(msg.content()).append("\n");
            }
            prompt.append("Ассистент:");
            String encoded = URLEncoder.encode(prompt.toString(), StandardCharsets.UTF_8);
            String urlString = API_BASE + encoded;
            URL url = new URL(urlString);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            int responseCode = conn.getResponseCode();
            if (responseCode == 429) {
                conn.disconnect();
                Thread.sleep(3000L);
                conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0");
                conn.setConnectTimeout(15000);
                conn.setReadTimeout(30000);
                responseCode = conn.getResponseCode();
            }
            if (responseCode != 200) {
                String string2 = "Сервер недоступен (код " + responseCode + "), попробуй позже.";
                return string2;
            }
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));){
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line).append("\n");
                }
            }
            String answer = response.toString().trim();
            if (answer.isEmpty()) {
                string = "Получен пустой ответ, попробуй ещё раз.";
                return string;
            }
            if (answer.length() > 400) {
                answer = answer.substring(0, 397) + "...";
            }
            return answer;
        }
        catch (SocketTimeoutException e2) {
            return "Сервер не ответил вовремя, попробуй ещё раз.";
        }
        catch (InterruptedException e3) {
            Thread.currentThread().interrupt();
            return "Запрос прерван.";
        }
        catch (Exception e4) {
            e4.printStackTrace();
            return "Ошибка: " + e4.getClass().getSimpleName() + " — " + e4.getMessage();
        }
        finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasExactlyOne()) {
            return new TabCompleteHelper().prepend("clear", "limit").filterPrefix(args.getString()).sortAlphabetically().stream();
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Задать вопрос AI";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("Команда для общения с AI через pollinations.ai.", "", "Использование:", "> ai <вопрос> — задать вопрос.", "> ai clear — очистить историю диалога.", "> ai limit — посмотреть лимит запросов.");
    }

    private record Message(String role, String content) {
    }
}


package ua.babka.ui.component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.util.math.MatrixStack;
import ua.babka.module.impl.render.ClickGuiScreen;
import ua.babka.render.ColorProvider;
import ua.babka.render.DrawUtil;
import ua.babka.render.HoverUtil;
import ua.babka.render.Scissor;
import ua.babka.render.builder.Builder;
import ua.babka.render.builder.BuiltTexture;
import ua.babka.render.builder.QuadColorState;
import ua.babka.render.builder.SizeState;
import ua.babka.render.font.Fonts;
import ua.babka.render.font.SkycoreIcons;
import ua.babka.theme.Theme;
import ua.babka.theme.ThemeManager;
import ua.babka.util.IMinecraft;
import ua.babka.util.animation.Animation;
import ua.babka.util.animation.Easing;

public class ThemeManagerWindow
implements IMinecraft {
    private float x;
    private float y;
    private float width = 150.0f;
    private float height = 260.0f;
    private final Animation slideX = new Animation(Easing.QUINTIC_OUT, 333L);
    private final Animation closeAnimX = new Animation(Easing.QUINTIC_IN, 300L);
    private boolean isClosing = false;
    private long slideStartDelay = 0L;
    private final ClickGuiScreen parent;
    private float scroll;
    private static final File THEME_DIR = new File("BabkaCleint");
    private static final File THEME_FILE = new File(THEME_DIR, "themes.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final int[][] PRESETS = new int[][]{{-5618433, -15922668}, {-12276993, -16119272}, {-12255318, -16246257}, {-26300, -15069174}, {-48026, -15071220}, {-7829368, -16119286}, {-10496, -15462392}, {-12285697, -13312}, {-16711766, -47975}, {-10027077, -15061462}, {-39356, -14022118}, {-65536, -15922668}};
    private static final String[] PRESET_NAMES = new String[]{"Pink", "Blue", "Зелёная", "Orange", "Blood", "Gray", "Gold", "Blue-Yellow", "Cyan-Pink", "Mint", "Sunset", "Rainbow"};
    private final List<SavedTheme> savedThemes = new ArrayList<SavedTheme>();
    private int[] currentColors;
    private static final float PAD = 5.0f;
    private static final float CORNER = 8.0f;
    private static final float HDR_H = 30.0f;
    private static final float ROW_H = 22.0f;

    public static int[][] getPresets() {
        return PRESETS;
    }

    public List<SavedTheme> getSavedThemes() {
        return this.savedThemes;
    }

    public ThemeManagerWindow(ClickGuiScreen parent) {
        this.parent = parent;
        this.currentColors = ThemeManagerWindow.buildDefaultColors(-5618433, -15922668);
        this.load();
        this.applyColors();
    }

    public void startCloseAnimation() {
        this.isClosing = true;
        this.closeAnimX.setValue(0.0f);
    }

    public void resetSlide() {
        this.isClosing = false;
        this.closeAnimX.setValue(0.0f);
        this.slideX.setValue(320.0f);
        this.slideStartDelay = System.currentTimeMillis() + 400L;
    }

    public void toggle() {
        if (this.isClosing) {
            this.resetSlide();
        } else {
            this.startCloseAnimation();
        }
    }

    public static int[] buildDefaultColors(int c1, int c2) {
        int[] c3 = new int[]{c2, c1, -1513240, -7829351, -16119283, c1, c1, -1, -14013899, c1, -14013899, c1, -14013899, -14013899, -15066590, -15066590, -1, -15921904, c1, c1};
        return c3;
    }

    public void setCurrentColors(int[] colors) {
        this.currentColors = colors;
    }

    public void applyColors() {
        Theme t2 = ThemeManager.getInstance().getCurrentTheme();
        int[] c2 = this.currentColors;
        t2.setAllColors(c2[1], c2[0], c2[0], c2[1], c2[2], c2[3], c2[4], c2[5], c2[6], c2[7], c2[8], c2[9], c2[10], c2[11], c2[12], c2[13], c2[14], c2[15], c2[16], c2[17], c2[18], c2[19]);
    }

    public void save() {
        try {
            if (!THEME_DIR.exists()) {
                THEME_DIR.mkdirs();
            }
            JsonObject json = new JsonObject();
            JsonArray arr = new JsonArray();
            for (int col : this.currentColors) {
                arr.add((Number)col);
            }
            json.add("colors", (JsonElement)arr);
            JsonArray saved = new JsonArray();
            for (SavedTheme t2 : this.savedThemes) {
                JsonObject obj = new JsonObject();
                obj.addProperty("name", t2.name);
                JsonArray ca = new JsonArray();
                for (int col : t2.colors) {
                    ca.add((Number)col);
                }
                obj.add("colors", (JsonElement)ca);
                saved.add((JsonElement)obj);
            }
            json.add("savedThemes", (JsonElement)saved);
            Files.writeString(THEME_FILE.toPath(), (CharSequence)GSON.toJson((JsonElement)json), new OpenOption[0]);
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void load() {
        if (!THEME_FILE.exists()) {
            return;
        }
        try {
            JsonObject json = JsonParser.parseString((String)Files.readString(THEME_FILE.toPath())).getAsJsonObject();
            if (json.has("colors")) {
                JsonArray arr = json.getAsJsonArray("colors");
                for (int i2 = 0; i2 < Math.min(arr.size(), 20); ++i2) {
                    this.currentColors[i2] = arr.get(i2).getAsInt();
                }
            }
            if (json.has("savedThemes")) {
                this.savedThemes.clear();
                for (JsonElement el : json.getAsJsonArray("savedThemes")) {
                    JsonObject obj = el.getAsJsonObject();
                    String name = obj.get("name").getAsString();
                    JsonArray ca = obj.getAsJsonArray("colors");
                    int[] cols = new int[20];
                    for (int i3 = 0; i3 < Math.min(ca.size(), 20); ++i3) {
                        cols[i3] = ca.get(i3).getAsInt();
                    }
                    this.savedThemes.add(new SavedTheme(name, cols));
                }
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void render(MatrixStack ms, int mouseX, int mouseY, float pt) {
        int i2;
        if (this.slideStartDelay > 0L && System.currentTimeMillis() >= this.slideStartDelay) {
            this.slideStartDelay = 0L;
        }
        this.slideX.run(this.slideStartDelay > 0L ? 320.0f : 0.0f);
        float ox = this.slideX.getValue();
        if (this.isClosing) {
            this.closeAnimX.run(1.0f);
        }
        float tox = 320.0f;
        float cox = this.isClosing ? this.closeAnimX.getValue() * tox : 0.0f;
        float ca = this.isClosing ? (float)(1.0 - (double)this.closeAnimX.getValue()) : 1.0f;
        float rx = this.x + ox + cox;
        float a2 = Math.min(255.0f * ca, 255.0f);
        float ar2 = a2 / 255.0f;
        int ai2 = (int)a2;
        if (a2 < 1.0f) {
            return;
        }
        int themeColor = ColorProvider.getThemeColor();
        int bgColor = ColorProvider.interpolateColor(themeColor, -16777216, 0.88f);
        DrawUtil.drawRound(rx, this.y, this.width, this.height, 8.0f, ColorProvider.setAlpha(bgColor, (int)(190.0f * ar2)));
        char iconChar = 'g';
        float iconSize = 22.0f;
        SkycoreIcons.IconEntry iconEntry = SkycoreIcons.getIcon(iconChar);
        if (iconEntry != null) {
            float iconY = this.y + (30.0f - iconSize) / 2.0f + 0.4f;
            ((BuiltTexture)Builder.texture().size(new SizeState(iconSize, iconSize)).texture(0.0f, 0.0f, 1.0f, 1.0f, iconEntry.textureId()).color(new QuadColorState(themeColor, themeColor, themeColor, themeColor)).build()).render(ms.peek().getPositionMatrix(), rx + 5.0f + 2.0f, iconY, 0.0f);
        }
        String title = "Theme Editor";
        float textX = rx + 5.0f + 2.0f + (iconEntry != null ? iconSize + 4.0f : 0.0f);
        float textY = this.y + 10.5f - 1.0f;
        DrawUtil.drawText(Fonts.SFREGULAR.get(), title, textX, textY, ColorProvider.rgba(255, 255, 255, ai2), 9.0f);
        float closeSize = 16.0f;
        float closeX = rx + this.width - 5.0f - 2.0f - closeSize;
        float closeY = this.y + (30.0f - closeSize) / 2.0f;
        boolean closeHov = HoverUtil.isHovered(mouseX, mouseY, closeX, closeY, closeSize, closeSize);
        int closeBg = closeHov ? ColorProvider.rgba(200, 30, 30, (int)(60.0f * ar2)) : ColorProvider.rgba(0, 0, 0, 0.0f);
        DrawUtil.drawRound(closeX, closeY, closeSize, closeSize, 3.0f, closeBg);
        DrawUtil.drawText(Fonts.SFREGULAR.get(), "✕", closeX + 4.0f, closeY + 3.5f, ColorProvider.rgba(255, 40, 40, ai2), 10.0f);
        DrawUtil.drawRound(rx + 5.0f + 2.0f, this.y + 30.0f - 0.5f, this.width - 10.0f - 4.0f, 1.0f, 0.0f, ColorProvider.setAlpha(themeColor, (int)(50.0f * ar2)));
        float contentH = (float)PRESETS.length * 22.0f + 5.0f + (float)this.savedThemes.size() * 22.0f;
        float viewH = this.height - 30.0f - 5.0f;
        float maxScroll = Math.max(0.0f, contentH - viewH + 5.0f);
        if (this.scroll > 0.0f) {
            this.scroll = 0.0f;
        }
        if (this.scroll < -maxScroll) {
            this.scroll = -maxScroll;
        }
        Scissor.push();
        Scissor.setFromComponentCoordinates(rx, this.y + 30.0f, this.width, this.height - 30.0f - 5.0f);
        float ry = this.y + 30.0f + 5.0f + this.scroll;
        float sq = 10.0f;
        for (i2 = 0; i2 < PRESETS.length; ++i2) {
            boolean hov = HoverUtil.isHovered(mouseX, mouseY, rx + 5.0f, ry, this.width - 10.0f, 22.0);
            if (hov) {
                DrawUtil.drawRound(rx + 5.0f, ry, this.width - 10.0f, 21.0f, 2.5f, ColorProvider.rgba(255, 255, 255, (int)(6.0f * ar2)));
            }
            int color = PRESET_NAMES[i2].equals("Rainbow") ? Color.HSBtoRGB((float)(System.currentTimeMillis() % 3600L) / 3600.0f, 0.85f, 1.0f) : PRESETS[i2][0];
            DrawUtil.drawRound(rx + 5.0f + 4.0f, ry + (22.0f - sq) / 2.0f, sq, sq, 2.5f, ColorProvider.setAlpha(color, ai2));
            DrawUtil.drawText(Fonts.SFREGULAR.get(), PRESET_NAMES[i2], rx + 5.0f + 4.0f + sq + 5.0f, ry + 5.5f, ColorProvider.rgba(220, 220, 225, ai2), 6.5f);
            ry += 22.0f;
        }
        ry += 5.0f;
        for (i2 = 0; i2 < this.savedThemes.size(); ++i2) {
            SavedTheme st = this.savedThemes.get(i2);
            boolean hov = HoverUtil.isHovered(mouseX, mouseY, rx + 5.0f, ry, this.width - 10.0f, 22.0);
            if (hov) {
                DrawUtil.drawRound(rx + 5.0f, ry, this.width - 10.0f, 21.0f, 2.5f, ColorProvider.rgba(255, 255, 255, (int)(6.0f * ar2)));
            }
            DrawUtil.drawRound(rx + 5.0f + 4.0f, ry + (22.0f - sq) / 2.0f, sq, sq, 2.5f, ColorProvider.setAlpha(st.colors[1], ai2));
            DrawUtil.drawText(Fonts.SFREGULAR.get(), st.name, rx + 5.0f + 4.0f + sq + 5.0f, ry + 5.5f, ColorProvider.rgba(220, 220, 225, ai2), 6.5f);
            ry += 22.0f;
        }
        Scissor.unset();
        Scissor.pop();
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        int i2;
        float closeY;
        float closeSize;
        if (this.isClosing) {
            return;
        }
        if (button != 0) {
            return;
        }
        float rx = this.x + this.slideX.getValue();
        float closeX = rx + this.width - 5.0f - 2.0f - (closeSize = 16.0f);
        if (HoverUtil.isHovered(mouseX, mouseY, closeX, closeY = this.y + (30.0f - closeSize) / 2.0f, closeSize, closeSize)) {
            this.parent.getThemeManager().startCloseAnimation();
            return;
        }
        float ry = this.y + 30.0f + 5.0f + this.scroll;
        for (i2 = 0; i2 < PRESETS.length; ++i2) {
            if (HoverUtil.isHovered(mouseX, mouseY, this.x + 5.0f, ry, this.width - 10.0f, 22.0)) {
                this.currentColors = ThemeManagerWindow.buildDefaultColors(PRESETS[i2][0], PRESETS[i2][1]);
                this.applyColors();
                Theme t2 = ThemeManager.getInstance().getCurrentTheme();
                t2.name = "Rainbow".equals(PRESET_NAMES[i2]) ? "Радужный" : PRESET_NAMES[i2];
                this.save();
                return;
            }
            ry += 22.0f;
        }
        ry += 5.0f;
        for (i2 = 0; i2 < this.savedThemes.size(); ++i2) {
            if (HoverUtil.isHovered(mouseX, mouseY, this.x + 5.0f, ry, this.width - 10.0f, 22.0)) {
                this.currentColors = (int[])this.savedThemes.get((int)i2).colors.clone();
                this.applyColors();
                this.save();
                return;
            }
            ry += 22.0f;
        }
    }

    public void mouseScrolled(double mx, double my, double ha, double va) {
        if (this.isClosing) {
            return;
        }
        if (mx < (double)this.x || mx > (double)(this.x + this.width) || my < (double)this.y || my > (double)(this.y + this.height)) {
            return;
        }
        this.scroll += (float)(va * 20.0);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public Animation getSlideX() {
        return this.slideX;
    }

    public Animation getCloseAnimX() {
        return this.closeAnimX;
    }

    public boolean isClosing() {
        return this.isClosing;
    }

    public long getSlideStartDelay() {
        return this.slideStartDelay;
    }

    public ClickGuiScreen getParent() {
        return this.parent;
    }

    public float getScroll() {
        return this.scroll;
    }

    public int[] getCurrentColors() {
        return this.currentColors;
    }

    public void setX(float x2) {
        this.x = x2;
    }

    public void setY(float y2) {
        this.y = y2;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setClosing(boolean isClosing) {
        this.isClosing = isClosing;
    }

    public void setSlideStartDelay(long slideStartDelay) {
        this.slideStartDelay = slideStartDelay;
    }

    public void setScroll(float scroll) {
        this.scroll = scroll;
    }

    public static class SavedTheme {
        String name;
        int[] colors;

        SavedTheme(String n2, int[] c2) {
            this.name = n2;
            this.colors = c2;
        }
    }
}


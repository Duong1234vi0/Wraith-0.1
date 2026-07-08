/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  org.joml.Matrix4f
 */
package code.alphadlc.display.screens.clickgui.components.implement.other;

import code.alphadlc.AlphaDLC;
import code.alphadlc.display.screens.clickgui.MenuScreen;
import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.managers.file.exception.FileLoadException;
import code.alphadlc.utils.client.managers.file.exception.FileSaveException;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.geometry.Render2D;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.display.scissor.ScissorAssist;
import code.alphadlc.utils.math.calc.Calculate;
import com.google.gson.Gson;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import org.joml.Matrix4f;

public class BackgroundComponent
extends AbstractComponent {
    private String editingConfig = null;
    private String newName = "";
    private int editCursor = 0;
    private boolean isDefaultTab = true;
    private float highlightX = 55.0f;
    private List<Map<String, Object>> configs = new ArrayList<Map<String, Object>>();
    private String configInput = "";
    private boolean editingInput = false;
    private int inputCursor = 0;
    private float scroll = 0.0f;
    private float smoothedScroll = 0.0f;
    private boolean loadedConfigs = false;
    private boolean cloudLoading = false;
    private long cloudLoadStartTime = 0L;
    private boolean cloudDataReady = false;
    private List<Map<String, Object>> tempConfigs = new ArrayList<Map<String, Object>>();
    private float loadingAlpha = 0.0f;
    private float configsAlpha = 0.0f;

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        class_4587 matrix = context.method_51448();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentTime = LocalTime.now().format(formatter);
        String point = " \u2022 ";
        rectangle.render(ShapeProperties.create(matrix, this.x, this.y, this.width, this.height).round(8.0f).softness(1.0f).thickness(2.0f).outlineColor(new Color(33, 33, 33, 255).getRGB()).color(new Color(18, 19, 20, 255).getRGB()).build());
        List<Object> displayedConfigs = new ArrayList();
        if (MenuScreen.getInstance().getCategory() == ModuleCategory.CONFIGS) {
            if (!this.loadedConfigs) {
                this.refreshConfigs();
                this.loadedConfigs = true;
            }
            if (this.cloudLoading && !this.isDefaultTab) {
                if (this.cloudDataReady && System.currentTimeMillis() - this.cloudLoadStartTime >= 3000L) {
                    this.configs = this.tempConfigs;
                    this.cloudLoading = false;
                }
                this.loadingAlpha = Calculate.interpolate(this.loadingAlpha, 1.0f, 0.1f);
                this.configsAlpha = Calculate.interpolate(this.configsAlpha, 0.0f, 0.1f);
            } else {
                this.loadingAlpha = Calculate.interpolate(this.loadingAlpha, 0.0f, 0.1f);
                this.configsAlpha = Calculate.interpolate(this.configsAlpha, 1.0f, 0.1f);
            }
            rectangle.render(ShapeProperties.create(context.method_51448(), this.x + 55.0f, this.y + 38.0f, 70.0, 15.0).round(3.0f).thickness(2.0f).softness(1.0f).outlineColor(new Color(54, 54, 56, 255).getRGB()).color(new Color(31, 27, 35, 75).getRGB(), new Color(31, 27, 35, 75).getRGB(), new Color(31, 27, 35, 75).getRGB(), new Color(31, 27, 35, 75).getRGB()).build());
            float targetX = this.isDefaultTab ? 55.0f : 90.0f;
            this.highlightX = Calculate.interpolate(this.highlightX, targetX, 0.2f);
            rectangle.render(ShapeProperties.create(context.method_51448(), this.x + this.highlightX, this.y + 38.0f, 35.0, 15.0).round(3.0f).thickness(0.0f).softness(0.0f).outlineColor(new Color(54, 54, 56, 0).getRGB()).color(new Color(65, 65, 65, 255).getRGB(), new Color(65, 65, 65, 255).getRGB(), new Color(65, 65, 65, 255).getRGB(), new Color(65, 65, 65, 255).getRGB()).build());
            rectangle.render(ShapeProperties.create(context.method_51448(), this.x + 43.0f, this.y + 60.0f, this.width - 43.0f, 0.5).color(new Color(55, 55, 70, 250).getRGB(), new Color(55, 55, 70, 15).getRGB(), new Color(55, 55, 70, 250).getRGB(), new Color(55, 55, 70, 15).getRGB()).build());
            Fonts.getSize(16, Fonts.Type.DEFAULT).drawString(matrix, "Default", this.x + 60.0f, this.y + 43.0f, ColorAssist.getText(0.7f));
            Fonts.getSize(16, Fonts.Type.DEFAULT).drawString(matrix, "Cloud", this.x + 97.0f, this.y + 43.0f, ColorAssist.getText(0.7f));
            rectangle.render(ShapeProperties.create(context.method_51448(), this.x + 340.0f, this.y + 38.0f, 80.0, 15.0).round(3.0f).thickness(2.0f).softness(1.0f).outlineColor(new Color(54, 54, 56, 255).getRGB()).color(new Color(31, 27, 35, 75).getRGB(), new Color(31, 27, 35, 75).getRGB(), new Color(31, 27, 35, 75).getRGB(), new Color(31, 27, 35, 75).getRGB()).build());
            rectangle.render(ShapeProperties.create(context.method_51448(), this.x + 292.0f, this.y + 38.0f, 40.0, 15.0).round(3.0f).thickness(2.0f).softness(1.0f).outlineColor(new Color(54, 54, 56, 255).getRGB()).color(new Color(31, 27, 35, 75).getRGB(), new Color(31, 27, 35, 75).getRGB(), new Color(31, 27, 35, 75).getRGB(), new Color(31, 27, 35, 75).getRGB()).build());
            Fonts.getSize(20, Fonts.Type.GUIICONS).drawString(matrix, "M", this.x + 296.0f, this.y + 42.0f, ColorAssist.getText(1.0f));
            Fonts.getSize(16, Fonts.Type.REGULAR).drawString(matrix, "Save", this.x + 307.0f, this.y + 43.5f, ColorAssist.getText(1.0f));
            rectangle.render(ShapeProperties.create(context.method_51448(), this.x + 250.0f, this.y + 38.0f, 38.0, 15.0).round(3.0f).thickness(2.0f).softness(1.0f).outlineColor(new Color(54, 54, 56, 255).getRGB()).color(new Color(31, 27, 35, 75).getRGB(), new Color(31, 27, 35, 75).getRGB(), new Color(31, 27, 35, 75).getRGB(), new Color(31, 27, 35, 75).getRGB()).build());
            Fonts.getSize(21, Fonts.Type.GUIICONS).drawString(matrix, "O", this.x + 253.0f, this.y + 43.0f, ColorAssist.getText(1.0f));
            Fonts.getSize(16, Fonts.Type.REGULAR).drawString(matrix, "Clear", this.x + 263.0f, this.y + 43.5f, ColorAssist.getText(1.0f));
            String placeholder = this.isDefaultTab ? "\u041f\u043e\u0438\u0441\u043a" : "\u0414\u043e\u0431\u0430\u0432\u0438\u0442\u044c \u043f\u043e ID";
            String inputDisplay = this.configInput.isEmpty() && !this.editingInput ? placeholder : this.configInput;
            Fonts.getSize(15, Fonts.Type.REGULAR).drawString(matrix, inputDisplay, this.x + 343.0f, this.y + 43.5f, ColorAssist.getText(0.6f));
            Fonts.getSize(26, Fonts.Type.ICONS).drawString(matrix, "U", this.x + 405.0f, this.y + 41.0f, ColorAssist.getText(0.6f));
            if (this.editingInput && System.currentTimeMillis() % 1000L < 500L) {
                float curWidth = Fonts.getSize(15, Fonts.Type.REGULAR).getStringWidth(this.configInput.substring(0, this.inputCursor));
                Fonts.getSize(15, Fonts.Type.DEFAULT).drawString(matrix, "|", this.x + 342.0f + curWidth, this.y + 43.5f - 0.5f, ColorAssist.getText(0.7f));
            }
            displayedConfigs = this.isDefaultTab ? this.configs.stream().filter(m -> ((String)m.get("name")).toLowerCase().contains(this.configInput.toLowerCase())).collect(Collectors.toList()) : this.configs;
            int configsPerRow = 2;
            int numConfigs = displayedConfigs.size();
            int rows = (numConfigs + configsPerRow - 1) / configsPerRow;
            float contentHeight = numConfigs > 0 ? 50.0f + (float)(rows - 1) * 55.0f : 0.0f;
            float viewHeight = this.height - 70.0f;
            float maxScrollAmount = Math.max(0.0f, contentHeight - viewHeight) + 7.0f;
            if (numConfigs < 7) {
                maxScrollAmount = 0.0f;
                this.scroll = 0.0f;
                this.smoothedScroll = 0.0f;
            }
            this.scroll = class_3532.method_15363((float)this.scroll, (float)(-maxScrollAmount), (float)0.0f);
            this.smoothedScroll = Calculate.interpolate(this.smoothedScroll, this.scroll, 0.2f);
            Matrix4f positionMatrix = matrix.method_23760().method_23761();
            ScissorAssist scissorManager = AlphaDLC.getInstance().getScissorManager();
            float listX = this.x + 43.0f;
            float listY = this.y + 65.0f;
            float listWidth = this.width - 43.0f - 15.0f;
            float listHeight = viewHeight;
            scissorManager.push(positionMatrix, listX, listY, listWidth, listHeight);
            if (!this.isDefaultTab) {
                RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)this.configsAlpha);
            }
            float configY = this.y + 70.0f + this.smoothedScroll;
            int index = 0;
            for (Map map : displayedConfigs) {
                String config = (String)map.get("name");
                float configX = this.x + 55.0f + (float)(index % configsPerRow * 190);
                if (index % configsPerRow == 0 && index > 0) {
                    configY += 55.0f;
                }
                if (configY + 50.0f > this.y + 60.0f && configY < this.y + this.height) {
                    Object avatarHash;
                    rectangle.render(ShapeProperties.create(context.method_51448(), configX, configY, 180.0, 50.0).round(5.0f).thickness(2.0f).softness(1.0f).outlineColor(new Color(54, 54, 56, 255).getRGB()).color(new Color(31, 27, 35, 75).getRGB(), new Color(31, 27, 35, 75).getRGB(), new Color(31, 27, 35, 75).getRGB(), new Color(31, 27, 35, 75).getRGB()).build());
                    rectangle.render(ShapeProperties.create(context.method_51448(), configX, configY + 22.0f, 180.0, 0.5).color(new Color(55, 55, 70, 250).getRGB(), new Color(55, 55, 70, 15).getRGB(), new Color(55, 55, 70, 250).getRGB(), new Color(55, 55, 70, 15).getRGB()).build());
                    rectangle.render(ShapeProperties.create(context.method_51448(), configX, configY, 20.5, 19.0).round(1.0f, 7.0f, 4.0f, 1.0f).thickness(2.0f).softness(1.0f).outlineColor(new Color(54, 54, 56, 255).getRGB()).color(new Color(55, 55, 55, 255).getRGB(), new Color(55, 55, 55, 255).getRGB(), new Color(55, 55, 55, 255).getRGB(), new Color(55, 55, 55, 255).getRGB()).build());
                    Fonts.getSize(26, Fonts.Type.ICONSCATEGORY).drawString(matrix, "F", configX + 3.5f, configY + 5.0f, ColorAssist.getText());
                    String displayName = config;
                    if (config.equals(this.editingConfig)) {
                        displayName = this.newName;
                        Fonts.getSize(16, Fonts.Type.DEFAULT).drawString(matrix, displayName, configX + 25.0f, configY + 9.0f, ColorAssist.getText());
                        if (System.currentTimeMillis() % 1000L < 500L) {
                            float curWidth = Fonts.getSize(16, Fonts.Type.DEFAULT).getStringWidth(this.newName.substring(0, this.editCursor));
                            Fonts.getSize(16, Fonts.Type.DEFAULT).drawString(matrix, "|", configX + 24.0f + curWidth, configY + 9.0f - 0.5f, ColorAssist.getText());
                        }
                    } else {
                        Fonts.getSize(16, Fonts.Type.DEFAULT).drawString(matrix, displayName, configX + 25.0f, configY + 9.0f, ColorAssist.getText());
                    }
                    Number createdNum = map.getOrDefault("created", 0L);
                    Number updatedNum = map.getOrDefault("updated", 0L);
                    long createdTime = createdNum.longValue();
                    long updatedTime = updatedNum.longValue();
                    String createdStr = createdTime == 0L ? "unknown" : LocalDateTime.ofInstant(Instant.ofEpochMilli(createdTime), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    String updatedStr = updatedTime == 0L ? "unknown" : LocalDateTime.ofInstant(Instant.ofEpochMilli(updatedTime), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    Fonts.getSize(11, Fonts.Type.REGULAR).drawString(matrix, "Created: " + createdStr, configX + 4.0f, configY + 35.0f, ColorAssist.getText(1.0f));
                    String updateText = "Updated: " + updatedStr;
                    boolean hasUpdate = map.getOrDefault("has_update", false);
                    if (hasUpdate) {
                        updateText = updateText + " (\u0434\u043e\u0441\u0442\u0443\u043f\u043d\u043e \u043e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u0439: 1)";
                    }
                    Fonts.getSize(11, Fonts.Type.REGULAR).drawString(matrix, updateText, configX + 4.0f, configY + 28.0f, ColorAssist.getText(1.0f));
                    String author = map.getOrDefault("owner", this.isDefaultTab ? "username" : "Unknown");
                    Fonts.getSize(11, Fonts.Type.REGULAR).drawString(matrix, "Author: " + author, configX + 4.0f, configY + 42.0f, ColorAssist.getText(1.0f));
                    class_2960 avatarObj = map.getOrDefault("avatar_hash", class_2960.method_60655((String)"minecraft", (String)"textures/gui/default_avatar.png"));
                    if (avatarObj instanceof Map) {
                        Map idMap = (Map)avatarObj;
                        avatarHash = (String)idMap.get("namespace") + ":" + (String)idMap.get("path");
                    } else {
                        avatarHash = avatarObj.toString();
                    }
                    Render2D.drawTexture(context, class_2960.method_60654((String)avatarHash), configX + 157.0f, configY + 3.0f, 16.0f, 7.5f, 0, 15, 21, ColorAssist.getGuiRectColor(1.0f));
                    rectangle.render(ShapeProperties.create(context.method_51448(), configX + 162.0f, configY + 35.0f, 14.0, 15.0).round(3.0f, 0.0f, 3.0f, 0.0f).thickness(2.0f).softness(1.0f).outlineColor(new Color(54, 54, 56, 255).getRGB()).color(new Color(55, 55, 55, 0).getRGB(), new Color(55, 55, 55, 0).getRGB(), new Color(55, 55, 55, 0).getRGB(), new Color(55, 55, 55, 0).getRGB()).build());
                    rectangle.render(ShapeProperties.create(context.method_51448(), configX + 146.0f, configY + 35.0f, 14.0, 15.0).round(3.0f, 0.0f, 3.0f, 0.0f).thickness(2.0f).softness(1.0f).outlineColor(new Color(54, 54, 56, 255).getRGB()).color(new Color(55, 55, 55, 0).getRGB(), new Color(55, 55, 55, 0).getRGB(), new Color(55, 55, 55, 0).getRGB(), new Color(55, 55, 55, 0).getRGB()).build());
                    rectangle.render(ShapeProperties.create(context.method_51448(), configX + 130.25f, configY + 35.0f, 14.0, 15.0).round(3.0f, 0.0f, 3.0f, 0.0f).thickness(2.0f).softness(1.0f).outlineColor(new Color(54, 54, 56, 255).getRGB()).color(new Color(55, 55, 55, 0).getRGB(), new Color(55, 55, 55, 0).getRGB(), new Color(55, 55, 55, 0).getRGB(), new Color(55, 55, 55, 0).getRGB()).build());
                    rectangle.render(ShapeProperties.create(context.method_51448(), configX + 114.35f, configY + 35.0f, 14.0, 15.0).round(3.0f, 0.0f, 3.0f, 0.0f).thickness(2.0f).softness(1.0f).outlineColor(new Color(54, 54, 56, 255).getRGB()).color(new Color(55, 55, 55, 0).getRGB(), new Color(55, 55, 55, 0).getRGB(), new Color(55, 55, 55, 0).getRGB(), new Color(55, 55, 55, 0).getRGB()).build());
                    Fonts.getSize(31, Fonts.Type.GUIICONS).drawString(matrix, "P", configX + 164.0f, configY + 36.0f, ColorAssist.getText(1.0f));
                    Fonts.getSize(21, Fonts.Type.GUIICONS).drawString(matrix, "N", configX + 149.0f, configY + 39.5f, ColorAssist.getText(1.0f));
                    Fonts.getSize(22, Fonts.Type.GUIICONS).drawString(matrix, "M", configX + 133.0f, configY + 38.5f, ColorAssist.getText(1.0f));
                    Fonts.getSize(24, Fonts.Type.GUIICONS).drawString(matrix, "O", configX + 117.0f, configY + 38.0f, ColorAssist.getText(1.0f));
                }
                ++index;
            }
            if (!this.isDefaultTab) {
                RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
            scissorManager.pop();
            if (maxScrollAmount > 0.0f) {
                float scrollbarWidth = 4.0f;
                float f = this.x + this.width - 10.0f;
                float scrollbarY = this.y + 65.0f;
                float scrollbarHeight = this.height - 70.0f;
                rectangle.render(ShapeProperties.create(context.method_51448(), f, scrollbarY, scrollbarWidth, scrollbarHeight).round(2.0f).color(new Color(30, 30, 30, 100).getRGB()).build());
                float handleHeight = Math.max(20.0f, scrollbarHeight * (viewHeight / contentHeight));
                float scrollRatio = maxScrollAmount > 0.0f ? -this.smoothedScroll / maxScrollAmount : 0.0f;
                float handleY = scrollbarY + (scrollbarHeight - handleHeight) * scrollRatio;
                rectangle.render(ShapeProperties.create(context.method_51448(), f, handleY, scrollbarWidth, handleHeight).round(2.0f).color(new Color(100, 100, 100, 150).getRGB()).build());
            }
        } else {
            this.loadedConfigs = false;
        }
        rectangle.render(ShapeProperties.create(context.method_51448(), this.x + 42.5f, this.y, 0.5, this.height).color(new Color(55, 55, 70, 15).getRGB(), new Color(55, 55, 70, 250).getRGB(), new Color(55, 55, 70, 15).getRGB(), new Color(55, 55, 70, 250).getRGB()).build());
        rectangle.render(ShapeProperties.create(context.method_51448(), this.x + 43.0f, this.y + 28.0f, this.width - 43.0f, 0.5).color(new Color(55, 55, 70, 250).getRGB(), new Color(55, 55, 70, 15).getRGB(), new Color(55, 55, 70, 250).getRGB(), new Color(55, 55, 70, 15).getRGB()).build());
        rectangle.render(ShapeProperties.create(matrix, this.x + 10.5f, this.y + 10.0f, 20.0, 20.0).round(4.0f).softness(1.0f).thickness(3.0f).outlineColor(new Color(33, 33, 33, 255).getRGB()).color(new Color(18, 19, 20, 255).getRGB()).build());
        Fonts.getSize(26, Fonts.Type.ICONS).drawString(matrix, "A ", this.x + 14.0f, this.y + 15.0f, ColorAssist.getClientColor());
        switch (MenuScreen.getInstance().getCategory()) {
            case COMBAT: {
                String icon = "A";
                Fonts.getSize(17, Fonts.Type.ICONSCATEGORY).drawString(matrix, icon, this.x + 55.0f, this.y + 14.5f, ColorAssist.getClientColor());
                break;
            }
            case MOVEMENT: {
                String icon = "B";
                Fonts.getSize(18, Fonts.Type.ICONSCATEGORY).drawString(matrix, icon, this.x + 54.0f, this.y + 14.0f, ColorAssist.getText(1.0f));
                break;
            }
            case RENDER: {
                String icon = "C";
                Fonts.getSize(17, Fonts.Type.ICONSCATEGORY).drawString(matrix, icon, this.x + 54.0f, this.y + 14.0f, ColorAssist.getText(1.0f));
                break;
            }
            case ESP: {
                String icon = "G";
                Fonts.getSize(17, Fonts.Type.ICONSCATEGORY).drawString(matrix, icon, this.x + 54.0f, this.y + 14.0f, ColorAssist.getText(1.0f));
                break;
            }
            case PLAYER: {
                String icon = "D";
                Fonts.getSize(17, Fonts.Type.ICONSCATEGORY).drawString(matrix, icon, this.x + 54.0f, this.y + 14.0f, ColorAssist.getText(1.0f));
                break;
            }
            case MISC: {
                String icon = "E";
                Fonts.getSize(18, Fonts.Type.ICONSCATEGORY).drawString(matrix, icon, this.x + 54.0f, this.y + 14.0f, ColorAssist.getText(1.0f));
                break;
            }
            case CONFIGS: {
                String icon = "F";
                Fonts.getSize(17, Fonts.Type.ICONSCATEGORY).drawString(matrix, icon, this.x + 54.0f, this.y + 14.0f, ColorAssist.getText(1.0f));
                break;
            }
            case CUSTOMIZATION: {
                String icon = "H";
                Fonts.getSize(17, Fonts.Type.ICONSCATEGORY).drawString(matrix, icon, this.x + 54.0f, this.y + 14.0f, ColorAssist.getText(1.0f));
                break;
            }
            default: {
                String icon = MenuScreen.getInstance().getCategory().getReadableName().substring(0, 1);
                Fonts.getSize(21, Fonts.Type.ICONSCATEGORY).drawString(matrix, icon, this.x + 50.0f, this.y + 13.5f, ColorAssist.getText(1.0f));
            }
        }
        if (MenuScreen.getInstance().getCategory() == ModuleCategory.CONFIGS && displayedConfigs.isEmpty()) {
            Object message = "\u0422\u0443\u0442\u0430 \u043f\u0443\u0441\u0442\u0430 :(";
            float textAlpha = 0.7f;
            if (!this.isDefaultTab && this.cloudLoading) {
                long time = System.currentTimeMillis() - this.cloudLoadStartTime;
                int dotCount = (int)(time / 500L % 3L) + 1;
                message = "Loading" + ".".repeat(dotCount);
                textAlpha = this.loadingAlpha;
            } else if (!this.cloudLoading) {
                textAlpha = this.configsAlpha;
            }
            Fonts.getSize(20, Fonts.Type.DEFAULT).drawString(matrix, (String)message, this.x + this.width / 2.0f - Fonts.getSize(20, Fonts.Type.DEFAULT).getStringWidth((String)message) / 2.0f + 10.0f, this.y + this.height / 2.0f + 15.0f, ColorAssist.getText(textAlpha));
        }
        if (MenuScreen.getInstance().getCategory() == ModuleCategory.CONFIGS) {
            Fonts.getSize(15, Fonts.Type.DEFAULT).drawGradientString(matrix, point + MenuScreen.getInstance().getCategory().getReadableName() + " | Beta", this.x + 63.0f, this.y + 13.5f, ColorAssist.getText(1.0f), ColorAssist.getText(1.0f));
        } else {
            Fonts.getSize(15, Fonts.Type.DEFAULT).drawGradientString(matrix, point + MenuScreen.getInstance().getCategory().getReadableName(), this.x + 63.0f, this.y + 13.5f, ColorAssist.getText(1.0f), ColorAssist.getText(1.0f));
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            this.editingInput = false;
        }
        if (MenuScreen.getInstance().getCategory() == ModuleCategory.CONFIGS && button == 0) {
            if (Calculate.isHovered(mouseX, mouseY, this.x + 55.0f, this.y + 38.0f, 35.0, 15.0)) {
                this.isDefaultTab = true;
                this.loadingAlpha = 0.0f;
                this.configsAlpha = 1.0f;
                this.refreshConfigs();
                return true;
            }
            if (Calculate.isHovered(mouseX, mouseY, this.x + 90.0f, this.y + 38.0f, 35.0, 15.0)) {
                this.isDefaultTab = false;
                this.loadingAlpha = 0.0f;
                this.configsAlpha = 0.0f;
                this.refreshConfigs();
                return true;
            }
            if (Calculate.isHovered(mouseX, mouseY, this.x + 340.0f, this.y + 38.0f, 80.0, 15.0)) {
                this.editingInput = true;
                this.inputCursor = this.configInput.length();
                return true;
            }
            if (Calculate.isHovered(mouseX, mouseY, this.x + 292.0f, this.y + 38.0f, 40.0, 15.0)) {
                if (this.isDefaultTab) {
                    this.createDefaultConfig();
                } else {
                    this.createCloudConfig();
                }
                this.refreshConfigs();
                return true;
            }
            if (Calculate.isHovered(mouseX, mouseY, this.x + 250.0f, this.y + 38.0f, 38.0, 15.0)) {
                this.clearAllConfigs();
                this.refreshConfigs();
                return true;
            }
            List<Map<String, Object>> displayedConfigs = this.isDefaultTab ? this.configs.stream().filter(m -> ((String)m.get("name")).toLowerCase().contains(this.configInput.toLowerCase())).collect(Collectors.toList()) : this.configs;
            float configY = this.y + 70.0f + this.smoothedScroll;
            int index = 0;
            for (Map<String, Object> map : displayedConfigs) {
                double nameHeight;
                double nameWidth;
                double nameY;
                double nameX;
                String config = (String)map.get("name");
                float configX = this.x + 55.0f + (float)(index % 2 * 190);
                if (index % 2 == 0 && index > 0) {
                    configY += 55.0f;
                }
                if (Calculate.isHovered(mouseX, mouseY, nameX = (double)(configX + 25.0f), (nameY = (double)(configY + 9.0f)) - 2.0, nameWidth = (double)Fonts.getSize(16, Fonts.Type.DEFAULT).getStringWidth(config), nameHeight = 10.0)) {
                    if (this.isDefaultTab) {
                        this.editingConfig = config;
                        this.newName = config;
                        this.editCursor = this.newName.length();
                    } else {
                        class_310.method_1551().field_1774.method_1455(config);
                    }
                    return true;
                }
                if (Calculate.isHovered(mouseX, mouseY, configX + 162.0f, configY + 35.0f, 14.0, 15.0)) {
                    if (this.isDefaultTab) {
                        try {
                            AlphaDLC.getInstance().getFileController().loadFile(config + ".json");
                        }
                        catch (FileLoadException fileLoadException) {}
                    } else {
                        this.loadCloudConfig(config);
                        this.isDefaultTab = true;
                        this.refreshConfigs();
                    }
                    return true;
                }
                if (Calculate.isHovered(mouseX, mouseY, configX + 146.0f, configY + 35.0f, 14.0, 15.0)) {
                    if (this.isDefaultTab) {
                        try {
                            AlphaDLC.getInstance().getFileController().loadFile(config + ".json");
                        }
                        catch (FileLoadException fileLoadException) {}
                    } else {
                        this.loadCloudConfig(config);
                        this.isDefaultTab = true;
                        this.refreshConfigs();
                    }
                    return true;
                }
                if (Calculate.isHovered(mouseX, mouseY, (double)configX + 130.25, configY + 35.0f, 14.0, 15.0)) {
                    if (this.isDefaultTab) {
                        String cloudId = (String)map.get("cloud_id");
                        if (cloudId != null) {
                            this.updateFromCloud(config, cloudId);
                        } else {
                            try {
                                AlphaDLC.getInstance().getFileController().saveFile(config + ".json");
                            }
                            catch (FileSaveException fileSaveException) {}
                        }
                    } else {
                        this.saveCloudConfig(config);
                    }
                    this.refreshConfigs();
                    return true;
                }
                if (Calculate.isHovered(mouseX, mouseY, (double)configX + 114.35, configY + 35.0f, 14.0, 15.0)) {
                    if (this.isDefaultTab) {
                        File file = new File(AlphaDLC.getInstance().getClientInfoProvider().configsDir(), config + ".json");
                        file.delete();
                    } else {
                        this.removeCloudConfig(config);
                    }
                    this.refreshConfigs();
                    return true;
                }
                ++index;
            }
        } else {
            this.editingConfig = null;
            this.editingInput = false;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (MenuScreen.getInstance().getCategory() == ModuleCategory.CONFIGS && Calculate.isHovered(mouseX, mouseY, this.x + 43.0f, this.y + 65.0f, this.width - 43.0f - 15.0f, this.height - 70.0f)) {
            this.scroll = (float)((double)this.scroll + amount * 20.0);
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.editingInput) {
            if (keyCode == 257) {
                if (!this.isDefaultTab && this.configInput.length() == 8 && this.configInput.matches("\\d+")) {
                    this.loadCloudConfig(this.configInput);
                    this.isDefaultTab = true;
                }
                this.configInput = "";
                this.inputCursor = 0;
                this.editingInput = false;
                this.refreshConfigs();
                return true;
            }
            if (keyCode == 256) {
                this.configInput = "";
                this.inputCursor = 0;
                this.editingInput = false;
                return true;
            }
            if (keyCode == 259) {
                if (this.inputCursor > 0) {
                    this.configInput = this.configInput.substring(0, this.inputCursor - 1) + this.configInput.substring(this.inputCursor);
                    --this.inputCursor;
                }
                return true;
            }
            if (keyCode == 263) {
                if (this.inputCursor > 0) {
                    --this.inputCursor;
                }
                return true;
            }
            if (keyCode == 262) {
                if (this.inputCursor < this.configInput.length()) {
                    ++this.inputCursor;
                }
                return true;
            }
            if (keyCode == 86 && (modifiers & 2) != 0) {
                String clip = class_310.method_1551().field_1774.method_1460().trim();
                if (!this.isDefaultTab) {
                    clip = clip.replaceAll("\\D", "");
                    int avail = 8 - this.configInput.length();
                    if (clip.length() > avail) {
                        clip = clip.substring(0, avail);
                    }
                } else {
                    int avail = 15 - this.configInput.length();
                    if (clip.length() > avail) {
                        clip = clip.substring(0, avail);
                    }
                }
                this.configInput = this.configInput.substring(0, this.inputCursor) + clip + this.configInput.substring(this.inputCursor);
                this.inputCursor += clip.length();
                return true;
            }
        } else if (this.editingConfig != null) {
            if (keyCode == 257) {
                if (this.isDefaultTab) {
                    File dir = AlphaDLC.getInstance().getClientInfoProvider().configsDir();
                    File oldFile = new File(dir, this.editingConfig + ".json");
                    File newFile = new File(dir, this.newName + ".json");
                    if (!this.newName.isEmpty() && this.newName.length() <= 15 && oldFile.exists() && (!newFile.exists() || this.newName.equals(this.editingConfig))) {
                        oldFile.renameTo(newFile);
                    }
                }
                this.editingConfig = null;
                this.refreshConfigs();
                return true;
            }
            if (keyCode == 256) {
                this.editingConfig = null;
                return true;
            }
            if (keyCode == 259) {
                if (this.editCursor > 0) {
                    this.newName = this.newName.substring(0, this.editCursor - 1) + this.newName.substring(this.editCursor);
                    --this.editCursor;
                }
                return true;
            }
            if (keyCode == 263) {
                if (this.editCursor > 0) {
                    --this.editCursor;
                }
                return true;
            }
            if (keyCode == 262) {
                if (this.editCursor < this.newName.length()) {
                    ++this.editCursor;
                }
                return true;
            }
            if (keyCode == 86 && (modifiers & 2) != 0) {
                String clip = class_310.method_1551().field_1774.method_1460().trim();
                int avail = 15 - this.newName.length();
                if (clip.length() > avail) {
                    clip = clip.substring(0, avail);
                }
                this.newName = this.newName.substring(0, this.editCursor) + clip + this.newName.substring(this.editCursor);
                this.editCursor += clip.length();
                return true;
            }
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (this.editingInput) {
            if (this.isDefaultTab && this.configInput.length() < 15 && Character.isLetterOrDigit(chr)) {
                this.configInput = this.configInput.substring(0, this.inputCursor) + chr + this.configInput.substring(this.inputCursor);
                ++this.inputCursor;
                return true;
            }
            if (!this.isDefaultTab && this.configInput.length() < 8 && Character.isDigit(chr)) {
                this.configInput = this.configInput.substring(0, this.inputCursor) + chr + this.configInput.substring(this.inputCursor);
                ++this.inputCursor;
                return true;
            }
        } else if (this.editingConfig != null && this.newName.length() < 15 && Character.isLetterOrDigit(chr)) {
            this.newName = this.newName.substring(0, this.editCursor) + chr + this.newName.substring(this.editCursor);
            ++this.editCursor;
            return true;
        }
        return super.charTyped(chr, modifiers);
    }

    private void refreshConfigs() {
        if (this.isDefaultTab) {
            this.configs = this.getLocalConfigs();
        } else {
            if (this.cloudLoading) {
                return;
            }
            this.cloudLoading = true;
            this.cloudDataReady = false;
            this.configs = new ArrayList<Map<String, Object>>();
            this.cloudLoadStartTime = System.currentTimeMillis();
            new Thread(() -> {
                List<Map<String, Object>> cl = this.getCloudConfigs();
                class_310.method_1551().execute(() -> {
                    this.tempConfigs = cl;
                    this.cloudDataReady = true;
                });
            }).start();
        }
    }

    private List<Map<String, Object>> getLocalConfigs() {
        ArrayList<Map<String, Object>> localConfigs = new ArrayList<Map<String, Object>>();
        File dir = AlphaDLC.getInstance().getClientInfoProvider().configsDir();
        File[] configFiles = dir.listFiles();
        if (configFiles != null) {
            for (File configFile : configFiles) {
                Gson gson;
                Map configData;
                String cloudId;
                if (!configFile.isFile() || !configFile.getName().endsWith(".json")) continue;
                String configName = configFile.getName().replace(".json", "");
                long mod = configFile.lastModified();
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("name", configName);
                map.put("created", mod);
                map.put("updated", mod);
                String jsonContent = "";
                try {
                    jsonContent = new String(Files.readAllBytes(configFile.toPath()));
                }
                catch (IOException iOException) {
                    // empty catch block
                }
                if (!jsonContent.isEmpty() && (cloudId = (String)(configData = (Map)(gson = new Gson()).fromJson(jsonContent, Map.class)).get("cloud_id")) != null) {
                    map.put("cloud_id", cloudId);
                    Map<String, Object> metadata = this.getCloudMetadata(cloudId);
                    if (metadata != null) {
                        long serverUpdated = ((Number)metadata.get("updated")).longValue();
                        if (serverUpdated > mod) {
                            map.put("has_update", true);
                        }
                        map.put("owner", metadata.get("owner"));
                        map.put("avatar_hash", metadata.get("avatar_hash"));
                    }
                }
                localConfigs.add(map);
            }
        }
        return localConfigs;
    }

    private List<Map<String, Object>> getCloudConfigs() {
        Gson gson = new Gson();
        HashMap<String, String> request = new HashMap<String, String>();
        request.put("command", "list");
        request.put("username", AlphaDLC.getInstance().getNativeUsername());
        request.put("uuid", AlphaDLC.getInstance().getNativeUserIdentifier());
        String message = gson.toJson(request);
        String response = AlphaDLC.getInstance().getCloudConfigClient().sendAndWaitForResponse(message);
        if (response == null) {
            return new ArrayList<Map<String, Object>>();
        }
        Map respMap = (Map)gson.fromJson(response, Map.class);
        if (((Boolean)respMap.get("success")).booleanValue()) {
            return (List)respMap.get("data");
        }
        return new ArrayList<Map<String, Object>>();
    }

    private void createDefaultConfig() {
        String name;
        Random random = new Random();
        File dir = AlphaDLC.getInstance().getClientInfoProvider().configsDir();
        while (new File(dir, (name = "AlphaDLCConfig" + String.format("%03d", random.nextInt(1000))) + ".json").exists()) {
        }
        try {
            AlphaDLC.getInstance().getFileController().saveFile(name + ".json");
        }
        catch (FileSaveException fileSaveException) {
            // empty catch block
        }
    }

    private void createCloudConfig() {
        String id;
        Random random = new Random();
        while (this.getCloudConfigJson(id = String.format("%08d", random.nextInt(100000000))) != null) {
        }
        this.saveCloudConfig(id);
    }

    private void clearAllConfigs() {
        block3: {
            block2: {
                if (!this.isDefaultTab) break block2;
                File dir = AlphaDLC.getInstance().getClientInfoProvider().configsDir();
                File[] files = dir.listFiles();
                if (files == null) break block3;
                for (File f : files) {
                    if (!f.getName().endsWith(".json")) continue;
                    f.delete();
                }
                break block3;
            }
            for (Map<String, Object> map : this.configs) {
                this.removeCloudConfig((String)map.get("name"));
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private String getCurrentConfigJson() {
        String json = "";
        File dir = AlphaDLC.getInstance().getClientInfoProvider().configsDir();
        File temp = new File(dir, "temp.json");
        try {
            AlphaDLC.getInstance().getFileController().saveFile("temp.json");
            json = new String(Files.readAllBytes(temp.toPath()));
        }
        catch (FileSaveException | IOException exception) {
        }
        finally {
            temp.delete();
        }
        return json;
    }

    private String getCloudConfigJson(String name) {
        Gson gson = new Gson();
        HashMap<String, String> request = new HashMap<String, String>();
        request.put("command", "load");
        request.put("username", "username");
        request.put("uuid", "uid");
        request.put("configName", name);
        String message = gson.toJson(request);
        String response = AlphaDLC.getInstance().getCloudConfigClient().sendAndWaitForResponse(message);
        if (response == null) {
            return null;
        }
        Map respMap = (Map)gson.fromJson(response, Map.class);
        if (((Boolean)respMap.get("success")).booleanValue()) {
            Object data = respMap.get("data");
            return gson.toJson(data);
        }
        return null;
    }

    private Map<String, Object> getCloudMetadata(String name) {
        Gson gson = new Gson();
        HashMap<String, String> request = new HashMap<String, String>();
        request.put("command", "metadata");
        request.put("username", "username");
        request.put("uuid", "uid");
        request.put("configName", name);
        String message = gson.toJson(request);
        String response = AlphaDLC.getInstance().getCloudConfigClient().sendAndWaitForResponse(message);
        if (response == null) {
            return null;
        }
        Map respMap = (Map)gson.fromJson(response, Map.class);
        if (((Boolean)respMap.get("success")).booleanValue()) {
            return (Map)respMap.get("data");
        }
        return null;
    }

    private void saveCloudConfig(String name) {
        String json = this.getCurrentConfigJson();
        if (json.isEmpty()) {
            return;
        }
        Gson gson = new Gson();
        LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
        long now = System.currentTimeMillis();
        String existing = this.getCloudConfigJson(name);
        long created = now;
        if (existing != null) {
            Map oldData = (Map)gson.fromJson(existing, Map.class);
            created = ((Number)oldData.get("created")).longValue();
        }
        data.put("owner", "username");
        data.put("created", created);
        data.put("updated", now);
        data.put("avatar_hash", "minecraft:textures/gui/default_avatar.png");
        Map configData = (Map)gson.fromJson(json, Map.class);
        data.putAll(configData);
        json = gson.toJson(data);
        this.saveCloudConfigWithJson(name, json);
    }

    private void saveCloudConfigWithJson(String name, String json) {
        if (json.isEmpty()) {
            return;
        }
        Gson gson = new Gson();
        HashMap<String, Object> request = new HashMap<String, Object>();
        request.put("command", "save");
        request.put("username", "username");
        request.put("uuid", "uid");
        request.put("configName", name);
        request.put("configData", gson.fromJson(json, Map.class));
        String message = gson.toJson(request);
        AlphaDLC.getInstance().getCloudConfigClient().sendAndWaitForResponse(message);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void loadCloudConfig(String name) {
        String baseName;
        String json = this.getCloudConfigJson(name);
        if (json == null) {
            return;
        }
        Gson gson = new Gson();
        Map data = (Map)gson.fromJson(json, Map.class);
        String owner = (String)data.get("owner");
        long created = ((Number)data.get("created")).longValue();
        long updated = ((Number)data.get("updated")).longValue();
        Object avatarObj = data.get("avatar_hash");
        if (avatarObj instanceof Map) {
            Map idMap = (Map)avatarObj;
            avatarHash = (String)idMap.get("namespace") + ":" + (String)idMap.get("path");
        } else {
            avatarHash = String.valueOf(avatarObj);
        }
        data.remove("owner");
        data.remove("created");
        data.remove("updated");
        data.remove("avatar_hash");
        data.put("cloud_id", name);
        json = gson.toJson((Object)data);
        File dir = AlphaDLC.getInstance().getClientInfoProvider().configsDir();
        File temp = new File(dir, "temp.json");
        try {
            Files.write(temp.toPath(), json.getBytes(), new OpenOption[0]);
            AlphaDLC.getInstance().getFileController().loadFile("temp.json");
        }
        catch (FileLoadException | IOException exception) {
        }
        finally {
            temp.delete();
        }
        String localName = baseName = owner + " Config";
        if (!owner.equals("username")) {
            int num = 1;
            while (new File(dir, localName + ".json").exists()) {
                localName = baseName + " (" + num++ + ")";
            }
        } else {
            localName = "Cloud_" + name;
        }
        try {
            Files.write(new File(dir, localName + ".json").toPath(), json.getBytes(), new OpenOption[0]);
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void updateFromCloud(String localName, String cloudId) {
        String json = this.getCloudConfigJson(cloudId);
        if (json == null) {
            return;
        }
        Gson gson = new Gson();
        Map data = (Map)gson.fromJson(json, Map.class);
        data.remove("owner");
        data.remove("created");
        data.remove("updated");
        data.remove("avatar_hash");
        data.put("cloud_id", cloudId);
        json = gson.toJson((Object)data);
        File dir = AlphaDLC.getInstance().getClientInfoProvider().configsDir();
        File temp = new File(dir, "temp.json");
        try {
            Files.write(temp.toPath(), json.getBytes(), new OpenOption[0]);
            AlphaDLC.getInstance().getFileController().loadFile("temp.json");
            Files.write(new File(dir, localName + ".json").toPath(), json.getBytes(), new OpenOption[0]);
        }
        catch (FileLoadException | IOException exception) {
        }
        finally {
            temp.delete();
        }
    }

    private void removeCloudConfig(String name) {
        Gson gson = new Gson();
        HashMap<String, String> request = new HashMap<String, String>();
        request.put("command", "remove");
        request.put("username", "username");
        request.put("uuid", "uid");
        request.put("configName", name);
        String message = gson.toJson(request);
        AlphaDLC.getInstance().getCloudConfigClient().sendAndWaitForResponse(message);
    }

    public BackgroundComponent setEditingConfig(String editingConfig) {
        this.editingConfig = editingConfig;
        return this;
    }

    public BackgroundComponent setNewName(String newName) {
        this.newName = newName;
        return this;
    }

    public BackgroundComponent setEditCursor(int editCursor) {
        this.editCursor = editCursor;
        return this;
    }

    public BackgroundComponent setDefaultTab(boolean isDefaultTab) {
        this.isDefaultTab = isDefaultTab;
        return this;
    }

    public BackgroundComponent setHighlightX(float highlightX) {
        this.highlightX = highlightX;
        return this;
    }

    public BackgroundComponent setConfigs(List<Map<String, Object>> configs) {
        this.configs = configs;
        return this;
    }

    public BackgroundComponent setConfigInput(String configInput) {
        this.configInput = configInput;
        return this;
    }

    public BackgroundComponent setEditingInput(boolean editingInput) {
        this.editingInput = editingInput;
        return this;
    }

    public BackgroundComponent setInputCursor(int inputCursor) {
        this.inputCursor = inputCursor;
        return this;
    }

    public BackgroundComponent setScroll(float scroll) {
        this.scroll = scroll;
        return this;
    }

    public BackgroundComponent setSmoothedScroll(float smoothedScroll) {
        this.smoothedScroll = smoothedScroll;
        return this;
    }

    public BackgroundComponent setLoadedConfigs(boolean loadedConfigs) {
        this.loadedConfigs = loadedConfigs;
        return this;
    }

    public BackgroundComponent setCloudLoading(boolean cloudLoading) {
        this.cloudLoading = cloudLoading;
        return this;
    }

    public BackgroundComponent setCloudLoadStartTime(long cloudLoadStartTime) {
        this.cloudLoadStartTime = cloudLoadStartTime;
        return this;
    }

    public BackgroundComponent setCloudDataReady(boolean cloudDataReady) {
        this.cloudDataReady = cloudDataReady;
        return this;
    }

    public BackgroundComponent setTempConfigs(List<Map<String, Object>> tempConfigs) {
        this.tempConfigs = tempConfigs;
        return this;
    }

    public BackgroundComponent setLoadingAlpha(float loadingAlpha) {
        this.loadingAlpha = loadingAlpha;
        return this;
    }

    public BackgroundComponent setConfigsAlpha(float configsAlpha) {
        this.configsAlpha = configsAlpha;
        return this;
    }
}

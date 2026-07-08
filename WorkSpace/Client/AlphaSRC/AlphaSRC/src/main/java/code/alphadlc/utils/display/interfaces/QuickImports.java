/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  net.minecraft.class_310
 *  net.minecraft.class_9779
 */
package code.alphadlc.utils.display.interfaces;

import code.alphadlc.display.screens.clickgui.components.implement.window.WindowManager;
import code.alphadlc.utils.display.interfaces.LazyWindow;
import code.alphadlc.utils.display.interfaces.QuickLogger;
import code.alphadlc.utils.display.render.draw.DrawEngine;
import code.alphadlc.utils.display.render.draw.DrawEngineImpl;
import code.alphadlc.utils.display.render.shape.implement.Arc;
import code.alphadlc.utils.display.render.shape.implement.Blur;
import code.alphadlc.utils.display.render.shape.implement.Image;
import code.alphadlc.utils.display.render.shape.implement.Rectangle;
import com.google.gson.Gson;
import net.minecraft.class_310;
import net.minecraft.class_9779;

public interface QuickImports
extends QuickLogger {
    public static final class_310 mc = class_310.method_1551();
    public static final class_9779 tickCounter = mc.method_61966();
    public static final LazyWindow window = new LazyWindow();
    public static final DrawEngine drawEngine = new DrawEngineImpl();
    public static final Rectangle rectangle = new Rectangle();
    public static final Blur blur = new Blur();
    public static final Arc arc = new Arc();
    public static final Image image = new Image();
    public static final Gson gson = new Gson();
    public static final WindowManager windowManager = new WindowManager();
}

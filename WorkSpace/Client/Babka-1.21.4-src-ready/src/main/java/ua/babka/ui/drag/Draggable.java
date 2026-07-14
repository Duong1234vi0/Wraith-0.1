package ua.babka.ui.drag;

import com.google.gson.annotations.Expose;
import net.minecraft.client.gui.screen.ChatScreen;
import ua.babka.module.Module;
import ua.babka.util.IMinecraft;
import ua.babka.util.math.MathUtil;

public class Draggable
implements IMinecraft {
    @Expose
    private float x;
    @Expose
    private float y;
    @Expose
    public float initialXVal;
    @Expose
    public float initialYVal;
    private float startX;
    private float startY;
    private boolean dragging;
    private float width;
    private float height;
    @Expose
    private final String name;
    private final Module module;

    public Draggable(Module module, String name, float initialXVal, float initialYVal) {
        this.module = module;
        this.name = name;
        this.x = this.roundToHalf(initialXVal);
        this.y = this.roundToHalf(initialYVal);
        this.initialXVal = initialXVal;
        this.initialYVal = initialYVal;
    }

    public final void onDraw() {
        if (!(Draggable.mc.currentScreen instanceof ChatScreen)) {
            return;
        }
        if (this.dragging) {
            int mouseX = this.normaliseX();
            int mouseY = this.normaliseY();
            this.x = this.roundToHalf((float)mouseX - this.startX);
            this.y = this.roundToHalf((float)mouseY - this.startY);
            int screenWidth = (int)MathUtil.calc(mc.getWindow().getScaledWidth());
            int screenHeight = (int)MathUtil.calc(mc.getWindow().getScaledHeight());
            this.x = Math.max(0.0f, Math.min(this.x, (float)screenWidth - this.width));
            this.y = Math.max(0.0f, Math.min(this.y, (float)screenHeight - this.height));
        }
    }

    public final void onClick(int button) {
        if (button == 0) {
            this.dragging = true;
            this.startX = (int)((float)this.normaliseX() - this.x);
            this.startY = (int)((float)this.normaliseY() - this.y);
        }
    }

    public boolean isHovering() {
        return (float)this.normaliseX() > Math.min(this.x, this.x + this.width) && (float)this.normaliseX() < Math.max(this.x, this.x + this.width) && (float)this.normaliseY() > Math.min(this.y, this.y + this.height) && (float)this.normaliseY() < Math.max(this.y, this.y + this.height);
    }

    public int normaliseX() {
        return (int)(Draggable.mc.mouse.getX() / mc.getWindow().getScaleFactor());
    }

    public int normaliseY() {
        return (int)(Draggable.mc.mouse.getY() / mc.getWindow().getScaleFactor());
    }

    public final void onRelease(int button) {
        if (button == 0) {
            this.dragging = false;
        }
    }

    private float roundToHalf(float value) {
        return (float)Math.round(value * 2.0f) / 2.0f;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getInitialXVal() {
        return this.initialXVal;
    }

    public float getInitialYVal() {
        return this.initialYVal;
    }

    public float getStartX() {
        return this.startX;
    }

    public float getStartY() {
        return this.startY;
    }

    public boolean isDragging() {
        return this.dragging;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public String getName() {
        return this.name;
    }

    public Module getModule() {
        return this.module;
    }

    public void setX(float x2) {
        this.x = x2;
    }

    public void setY(float y2) {
        this.y = y2;
    }

    public void setInitialXVal(float initialXVal) {
        this.initialXVal = initialXVal;
    }

    public void setInitialYVal(float initialYVal) {
        this.initialYVal = initialYVal;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}


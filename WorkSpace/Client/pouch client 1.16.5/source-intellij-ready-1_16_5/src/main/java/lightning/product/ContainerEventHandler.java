/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.GuiEventListener;

public interface ContainerEventHandler
extends GuiEventListener {
    public List<? extends GuiEventListener> getEventListeners();

    default public Optional<GuiEventListener> J_1907_R(double mouseX, double mouseY) {
        for (GuiEventListener f_4907_p : this.getEventListeners()) {
            if (!f_4907_p.isMouseOver(mouseX, mouseY)) continue;
            return Optional.of(f_4907_p);
        }
        return Optional.empty();
    }

    @Override
    default public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (GuiEventListener f_4907_p : this.getEventListeners()) {
            if (!f_4907_p.mouseClicked(mouseX, mouseY, button)) continue;
            this.setListener(f_4907_p);
            if (button == 0) {
                this.setDragging(true);
            }
            return true;
        }
        return false;
    }

    @Override
    default public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.setDragging(false);
        return this.J_1907_R(mouseX, mouseY).filter(listener -> listener.mouseReleased(mouseX, mouseY, button)).isPresent();
    }

    @Override
    default public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        return this.getListener() != null && this.isDragging() && button == 0 ? this.getListener().mouseDragged(mouseX, mouseY, button, dragX, dragY) : false;
    }

    public boolean isDragging();

    public void setDragging(boolean var1);

    @Override
    default public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        return this.J_1907_R(mouseX, mouseY).filter(listener -> listener.mouseScrolled(mouseX, mouseY, delta)).isPresent();
    }

    @Override
    default public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return this.getListener() != null && this.getListener().keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    default public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return this.getListener() != null && this.getListener().keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    default public boolean charTyped(char codePoint, int modifiers) {
        return this.getListener() != null && this.getListener().charTyped(codePoint, modifiers);
    }

    @Nullable
    public GuiEventListener getListener();

    public void setListener(@Nullable GuiEventListener var1);

    default public void n_1700_B(@Nullable GuiEventListener eventListener) {
        this.setListener(eventListener);
        eventListener.changeFocus(true);
    }

    default public void J_1907_R(@Nullable GuiEventListener eventListener) {
        this.setListener(eventListener);
    }

    @Override
    default public boolean changeFocus(boolean focus) {
        Supplier<GuiEventListener> supplier;
        BooleanSupplier booleansupplier;
        boolean flag;
        GuiEventListener iguieventlistener = this.getListener();
        boolean bl = flag = iguieventlistener != null;
        if (flag && iguieventlistener.changeFocus(focus)) {
            return true;
        }
        List<? extends GuiEventListener> list = this.getEventListeners();
        int j = list.indexOf(iguieventlistener);
        int i = flag && j >= 0 ? j + (focus ? 1 : 0) : (focus ? 0 : list.size());
        ListIterator<? extends GuiEventListener> listiterator = list.listIterator(i);
        BooleanSupplier booleanSupplier = focus ? listiterator::hasNext : (booleansupplier = listiterator::hasPrevious);
        Supplier<GuiEventListener> supplier2 = focus ? listiterator::next : (supplier = listiterator::previous);
        while (booleansupplier.getAsBoolean()) {
            GuiEventListener iguieventlistener1 = supplier.get();
            if (!iguieventlistener1.changeFocus(focus)) continue;
            this.setListener(iguieventlistener1);
            return true;
        }
        this.setListener(null);
        return false;
    }
}



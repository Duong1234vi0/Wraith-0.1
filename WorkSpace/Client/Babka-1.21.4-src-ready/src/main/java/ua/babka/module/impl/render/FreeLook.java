package ua.babka.module.impl.render;

import com.google.common.eventbus.Subscribe;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;
import org.lwjgl.glfw.GLFW;
import ua.babka.event.LookEvent;
import ua.babka.event.RotationEvent;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.BindSetting;

@ModuleInformation(moduleName="Third Person", moduleDesc="Свободное вращение камеры", moduleCategory=ModuleCategory.RENDER)
public class FreeLook
extends Module {
    public final BindSetting key = new BindSetting("Клавиша", 342);
    private boolean active = false;
    private float cameraYaw = 0.0f;
    private float cameraPitch = 0.0f;
    private float playerYaw = 0.0f;
    private float playerPitch = 0.0f;

    @Override
    public void onEnable() {
        super.onEnable();
        this.resetCamera();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.active = false;
    }

    private void resetCamera() {
        if (this.mc.player != null) {
            this.cameraYaw = this.mc.player.getYaw();
            this.cameraPitch = this.mc.player.getPitch();
            this.playerYaw = this.mc.player.getYaw();
            this.playerPitch = this.mc.player.getPitch();
        }
    }

    private boolean isKeyPressed() {
        int keyCode;
        long handle = MinecraftClient.getInstance().getWindow().getHandle();
        return GLFW.glfwGetKey((long)handle, (int)(keyCode = this.key.getValue().intValue())) == 1;
    }

    @Subscribe
    public void onLook(LookEvent event) {
        if (this.mc.player == null) {
            return;
        }
        boolean wasActive = this.active;
        this.active = this.isKeyPressed();
        if (this.active) {
            this.mc.options.setPerspective(Perspective.THIRD_PERSON_BACK);
            if (!wasActive) {
                this.playerYaw = this.mc.player.getYaw();
                this.playerPitch = this.mc.player.getPitch();
                this.cameraYaw = this.playerYaw;
                this.cameraPitch = this.playerPitch;
            }
            this.cameraYaw += (float)event.getYaw() * 0.15f;
            this.cameraPitch += (float)event.getPitch() * 0.15f;
            this.cameraPitch = Math.max(-90.0f, Math.min(90.0f, this.cameraPitch));
            event.setCancelled(true);
        } else if (wasActive) {
            this.mc.options.setPerspective(Perspective.FIRST_PERSON);
            this.resetCamera();
        }
    }

    @Subscribe
    public void onRotation(RotationEvent event) {
        if (this.mc.player == null) {
            return;
        }
        if (this.active) {
            event.setYaw(this.cameraYaw);
            event.setPitch(this.cameraPitch);
        }
    }

    public boolean isActive() {
        return this.active && this.isEnabled();
    }

    public float getCameraYaw() {
        return this.cameraYaw;
    }

    public float getCameraPitch() {
        return this.cameraPitch;
    }
}


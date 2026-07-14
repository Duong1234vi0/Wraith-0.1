package ua.babka.module.impl.render;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.setting.SliderSetting;

@ModuleInformation(moduleName="View Model", moduleDesc="Изменяет положение рук в камере", moduleCategory=ModuleCategory.RENDER)
public class ViewModel
extends Module {
    private final SliderSetting offsetLeftX = new SliderSetting("Левая рука по X", 0.0, -2.0, 2.0, 0.1f);
    private final SliderSetting offsetLeftY = new SliderSetting("Левая рука по Y", 0.0, -2.0, 2.0, 0.1f);
    private final SliderSetting offsetLeftZ = new SliderSetting("Левая рука по Z", 0.0, -2.0, 2.0, 0.1f);
    private final SliderSetting offsetRightX = new SliderSetting("Правая рука по X", 0.0, -2.0, 2.0, 0.1f);
    private final SliderSetting offsetRightY = new SliderSetting("Правая рука по Y", 0.0, -2.0, 2.0, 0.1f);
    private final SliderSetting offsetRightZ = new SliderSetting("Правая рука по Z", 0.0, -2.0, 2.0, 0.1f);

    public void applyHandPosition(MatrixStack matrices, Arm arm) {
        if (this.isEnabled()) {
            if (arm == Arm.LEFT) {
                matrices.translate(this.offsetLeftX.getValue(), this.offsetLeftY.getValue(), this.offsetLeftZ.getValue());
            } else {
                matrices.translate(this.offsetRightX.getValue(), this.offsetRightY.getValue(), this.offsetRightZ.getValue());
            }
        } else {
            matrices.translate(0.0f, 0.0f, 0.0f);
        }
    }
}


/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_3300
 */
package code.alphadlc.utils.debug;

import code.alphadlc.utils.client.logs.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3300;

public class ResourceValidator {
    private static final List<String> CRITICAL_FONT_RESOURCES = List.of("mre:fonts/medium.json", "mre:fonts/medium.png", "mre:fonts/regular.json", "mre:fonts/regular.png", "mre:fonts/bold.json", "mre:fonts/bold.png", "mre:fonts/suisseintlmedium.json", "mre:fonts/suisseintlmedium.png", "mre:fonts/suisseintlregular.json", "mre:fonts/suisseintlregular.png", "mre:fonts/alphadlcicons.json", "mre:fonts/alphadlcicons.png");
    private static final List<String> CRITICAL_SHADER_RESOURCES = List.of("mre:shaders/core/msdf_font.json", "mre:shaders/core/msdf_font.fsh", "mre:shaders/core/msdf_font.vsh", "mre:shaders/core/rectangle.json", "mre:shaders/core/rectangle.fsh", "mre:shaders/core/rectangle.vsh");

    public static boolean validateCriticalResources() {
        class_310 client = class_310.method_1551();
        if (client == null) {
            Logger.warn("[ResourceValidator] MinecraftClient not initialized yet, skipping validation");
            return true;
        }
        class_3300 resourceManager = client.method_1478();
        if (resourceManager == null) {
            Logger.warn("[ResourceValidator] ResourceManager not ready yet, skipping validation");
            return true;
        }
        ArrayList<String> missingResources = new ArrayList<String>();
        for (String resource : CRITICAL_FONT_RESOURCES) {
            if (ResourceValidator.resourceExists(resourceManager, resource)) continue;
            missingResources.add(resource);
        }
        for (String resource : CRITICAL_SHADER_RESOURCES) {
            if (ResourceValidator.resourceExists(resourceManager, resource)) continue;
            missingResources.add(resource);
        }
        if (!missingResources.isEmpty()) {
            Logger.error("[ResourceValidator] CRITICAL: Missing " + missingResources.size() + " resources!");
            for (String missing : missingResources) {
                Logger.error("[ResourceValidator]   - Missing: " + missing);
            }
            return false;
        }
        Logger.info("[ResourceValidator] All critical resources validated successfully!");
        return true;
    }

    private static boolean resourceExists(class_3300 resourceManager, String resourcePath) {
        try {
            class_2960 id = class_2960.method_60654((String)resourcePath);
            return resourceManager.method_14486(id).isPresent();
        }
        catch (Exception e) {
            Logger.warn("[ResourceValidator] Failed to check resource: " + resourcePath);
            return false;
        }
    }

    public static void debugFontResources() {
        class_310 client = class_310.method_1551();
        if (client == null || client.method_1478() == null) {
            Logger.warn("[ResourceValidator] Cannot debug fonts - client not ready");
            return;
        }
        class_3300 resourceManager = client.method_1478();
        Logger.info("[ResourceValidator] === Font Resources Debug ===");
        Iterator<String> iterator = CRITICAL_FONT_RESOURCES.iterator();
        while (iterator.hasNext()) {
            String resource;
            boolean exists = ResourceValidator.resourceExists(resourceManager, resource = iterator.next());
            Logger.info("[ResourceValidator] " + resource + " : " + (exists ? "PRESENT" : "MISSING"));
        }
        Logger.info("[ResourceValidator] === End Debug ===");
    }
}

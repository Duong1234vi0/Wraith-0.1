/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1921
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_3298
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 */
package code.alphadlc.utils.display.gif;

import code.alphadlc.utils.display.render.font.glyph.GlyphMap;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3298;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GifTextureRenderer {
    private final class_2960 gifResource;
    private final List<class_2960> frameTextures = new ArrayList<class_2960>();
    private final List<Integer> frameDurationsMs = new ArrayList<Integer>();
    private final List<PendingFrame> pendingFrames = new ArrayList<PendingFrame>();
    private final Object lock = new Object();
    private volatile boolean loading;
    private volatile boolean ready;
    private long animationStartTime;

    public GifTextureRenderer(class_2960 gifResource) {
        this.gifResource = gifResource;
    }

    public void preload() {
        this.ensureLoaded();
    }

    public void preloadEarly() {
        this.ensureLoaded();
    }

    public void render(class_332 context, float x, float y, float width, float height) {
        this.render(context, x, y, width, height, 1.0f);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void render(class_332 context, float x, float y, float width, float height, float alpha) {
        class_2960 frame;
        if (alpha <= 0.01f) {
            return;
        }
        this.ensureLoaded();
        this.flushPendingFrames();
        Object object = this.lock;
        synchronized (object) {
            if (!this.ready || this.frameTextures.isEmpty()) {
                return;
            }
            frame = this.getCurrentFrame();
        }
        if (frame == null) {
            return;
        }
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        int alphaBits = (int)(class_3532.method_15363((float)alpha, (float)0.0f, (float)1.0f) * 255.0f);
        int tint = alphaBits << 24 | 0xFFFFFF;
        context.method_25293(class_1921::method_62277, frame, (int)x, (int)y, 0.0f, 0.0f, (int)width, (int)height, (int)width, (int)height, (int)width, (int)height, tint);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void ensureLoaded() {
        if (this.ready && this.pendingFrames.isEmpty()) {
            return;
        }
        if (this.loading) {
            return;
        }
        Object object = this.lock;
        synchronized (object) {
            if (this.loading) {
                return;
            }
            if (this.ready && this.pendingFrames.isEmpty()) {
                return;
            }
            this.loading = true;
        }
        CompletableFuture.runAsync(this::decodeAndQueueFrames).whenComplete((ignored, error) -> {
            if (error != null) {
                this.loading = false;
            }
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void decodeAndQueueFrames() {
        try (InputStream input = this.openGifInputStream();){
            if (input == null) {
                this.loading = false;
                return;
            }
            try (ImageInputStream imageInput = ImageIO.createImageInputStream(input);){
                Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("gif");
                if (!readers.hasNext()) {
                    this.loading = false;
                    return;
                }
                ImageReader reader = readers.next();
                try {
                    String formatName;
                    reader.setInput(imageInput, false);
                    int frameCount = reader.getNumImages(true);
                    int canvasWidth = 0;
                    int canvasHeight = 0;
                    IIOMetadata streamMetadata = reader.getStreamMetadata();
                    if (streamMetadata != null && (formatName = streamMetadata.getNativeMetadataFormatName()) != null) {
                        Node streamRoot = streamMetadata.getAsTree(formatName);
                        Node descriptor = this.findNode(streamRoot, "LogicalScreenDescriptor");
                        canvasWidth = this.getIntAttribute(descriptor, "logicalScreenWidth", 0);
                        canvasHeight = this.getIntAttribute(descriptor, "logicalScreenHeight", 0);
                    }
                    BufferedImage canvas = null;
                    BufferedImage previousCanvas = null;
                    FrameInfo previousInfo = null;
                    for (int i = 0; i < frameCount; ++i) {
                        BufferedImage rawFrame = reader.read(i);
                        FrameInfo info = this.readFrameInfo(reader.getImageMetadata(i), rawFrame);
                        if (canvas == null) {
                            int width = Math.max(canvasWidth, info.left + rawFrame.getWidth());
                            int height = Math.max(canvasHeight, info.top + rawFrame.getHeight());
                            canvas = new BufferedImage(Math.max(1, width), Math.max(1, height), 2);
                        }
                        this.applyDisposal(canvas, previousCanvas, previousInfo);
                        previousCanvas = "restoreToPrevious".equals(info.disposalMethod) ? this.deepCopy(canvas) : null;
                        Graphics2D graphics = canvas.createGraphics();
                        graphics.setComposite(AlphaComposite.SrcOver);
                        graphics.drawImage((Image)rawFrame, info.left, info.top, null);
                        graphics.dispose();
                        boolean lastFrame = i == frameCount - 1;
                        this.queueFrameRegistration(i, this.deepCopy(canvas), info.delayMs, lastFrame);
                        previousInfo = info;
                    }
                }
                finally {
                    reader.dispose();
                }
            }
        }
        catch (IOException ignored) {
            this.loading = false;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void queueFrameRegistration(int index, BufferedImage frame, int durationMs, boolean lastFrame) {
        Runnable task = () -> {
            this.registerFrame(index, frame, durationMs);
            if (lastFrame) {
                this.loading = false;
            }
        };
        class_310 mc = class_310.method_1551();
        if (mc != null) {
            mc.execute(task);
            return;
        }
        Object object = this.lock;
        synchronized (object) {
            this.pendingFrames.add(new PendingFrame(index, frame, durationMs, lastFrame));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void flushPendingFrames() {
        ArrayList<PendingFrame> toRegister;
        Object object = this.lock;
        synchronized (object) {
            if (this.pendingFrames.isEmpty()) {
                return;
            }
            toRegister = new ArrayList<PendingFrame>(this.pendingFrames);
            this.pendingFrames.clear();
        }
        class_310 mc = class_310.method_1551();
        if (mc == null) {
            Object object2 = this.lock;
            synchronized (object2) {
                this.pendingFrames.addAll(0, toRegister);
            }
            return;
        }
        for (PendingFrame pending : toRegister) {
            this.registerFrame(pending.index(), pending.frame(), pending.durationMs());
            if (!pending.lastFrame()) continue;
            this.loading = false;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void registerFrame(int index, BufferedImage frame, int durationMs) {
        Object object = this.lock;
        synchronized (object) {
            this.ensureCapacity(index + 1);
            class_2960 textureId = class_2960.method_60655((String)"alphadlc", (String)("dynamic/gif/" + this.sanitizePath(this.gifResource.method_12832()) + "_" + index));
            GlyphMap.registerBufferedImageTexture(textureId, frame);
            this.frameTextures.set(index, textureId);
            this.frameDurationsMs.set(index, durationMs);
            if (!this.ready) {
                this.animationStartTime = System.currentTimeMillis();
                this.ready = true;
            }
        }
    }

    private void ensureCapacity(int size) {
        while (this.frameTextures.size() < size) {
            this.frameTextures.add(null);
            this.frameDurationsMs.add(0);
        }
    }

    private InputStream openGifInputStream() throws IOException {
        String classpathPath = "/assets/" + this.gifResource.method_12836() + "/" + this.gifResource.method_12832();
        InputStream classpathStream = GifTextureRenderer.class.getResourceAsStream(classpathPath);
        if (classpathStream != null) {
            return classpathStream;
        }
        class_310 mc = class_310.method_1551();
        if (mc == null) {
            return null;
        }
        Optional resource = mc.method_1478().method_14486(this.gifResource);
        if (resource.isEmpty()) {
            return null;
        }
        return ((class_3298)resource.get()).method_14482();
    }

    private void applyDisposal(BufferedImage canvas, BufferedImage previousCanvas, FrameInfo previousInfo) {
        if (canvas == null || previousInfo == null) {
            return;
        }
        switch (previousInfo.disposalMethod) {
            case "restoreToBackgroundColor": {
                Graphics2D graphics = canvas.createGraphics();
                graphics.setComposite(AlphaComposite.Clear);
                graphics.fillRect(previousInfo.left, previousInfo.top, previousInfo.width, previousInfo.height);
                graphics.dispose();
                break;
            }
            case "restoreToPrevious": {
                if (previousCanvas == null) break;
                Graphics2D graphics = canvas.createGraphics();
                graphics.setComposite(AlphaComposite.Src);
                graphics.drawImage((Image)previousCanvas, 0, 0, null);
                graphics.dispose();
                break;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private class_2960 getCurrentFrame() {
        Object object = this.lock;
        synchronized (object) {
            if (this.frameTextures.isEmpty()) {
                return null;
            }
            List<class_2960> available = this.frameTextures.stream().filter(id -> id != null).toList();
            if (available.isEmpty()) {
                return null;
            }
            if (available.size() == 1) {
                return available.getFirst();
            }
            long elapsed = Math.max(0L, System.currentTimeMillis() - this.animationStartTime);
            long totalDuration = 0L;
            int registeredCount = 0;
            for (int i = 0; i < this.frameDurationsMs.size() && this.frameTextures.get(i) != null; ++i) {
                totalDuration += (long)Math.max(20, this.frameDurationsMs.get(i));
                ++registeredCount;
            }
            if (totalDuration <= 0L || registeredCount == 0) {
                return available.getFirst();
            }
            long loopTime = elapsed % totalDuration;
            long accumulated = 0L;
            for (int i = 0; i < registeredCount; ++i) {
                if (loopTime >= (accumulated += (long)Math.max(20, this.frameDurationsMs.get(i)))) continue;
                return this.frameTextures.get(i);
            }
            return this.frameTextures.get(registeredCount - 1);
        }
    }

    private FrameInfo readFrameInfo(IIOMetadata metadata, BufferedImage frame) {
        String formatName;
        int left = 0;
        int top = 0;
        int delayMs = 100;
        String disposalMethod = "none";
        if (metadata != null && (formatName = metadata.getNativeMetadataFormatName()) != null) {
            Node root = metadata.getAsTree(formatName);
            Node imageDescriptor = this.findNode(root, "ImageDescriptor");
            left = this.getIntAttribute(imageDescriptor, "imageLeftPosition", 0);
            top = this.getIntAttribute(imageDescriptor, "imageTopPosition", 0);
            Node graphicControl = this.findNode(root, "GraphicControlExtension");
            delayMs = Math.max(20, this.getIntAttribute(graphicControl, "delayTime", 10) * 10);
            disposalMethod = this.getStringAttribute(graphicControl, "disposalMethod", "none");
        }
        return new FrameInfo(left, top, frame.getWidth(), frame.getHeight(), delayMs, disposalMethod);
    }

    private int getIntAttribute(Node node, String attribute, int fallback) {
        String value = this.getStringAttribute(node, attribute, null);
        if (value == null) {
            return fallback;
        }
        try {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException ignored) {
            return fallback;
        }
    }

    private String getStringAttribute(Node node, String attribute, String fallback) {
        if (node == null) {
            return fallback;
        }
        NamedNodeMap attributes = node.getAttributes();
        if (attributes == null) {
            return fallback;
        }
        Node value = attributes.getNamedItem(attribute);
        return value != null ? value.getNodeValue() : fallback;
    }

    private Node findNode(Node node, String name) {
        if (node == null) {
            return null;
        }
        if (name.equals(node.getNodeName())) {
            return node;
        }
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); ++i) {
            Node found = this.findNode(children.item(i), name);
            if (found == null) continue;
            return found;
        }
        return null;
    }

    private BufferedImage deepCopy(BufferedImage image) {
        BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), 2);
        Graphics2D graphics = copy.createGraphics();
        graphics.setComposite(AlphaComposite.Src);
        graphics.drawImage((Image)image, 0, 0, null);
        graphics.dispose();
        return copy;
    }

    private String sanitizePath(String path) {
        return path.replace('/', '_').replace('\\', '_').replace('.', '_');
    }

    private record FrameInfo(int left, int top, int width, int height, int delayMs, String disposalMethod) {
    }

    private record PendingFrame(int index, BufferedImage frame, int durationMs, boolean lastFrame) {
    }
}

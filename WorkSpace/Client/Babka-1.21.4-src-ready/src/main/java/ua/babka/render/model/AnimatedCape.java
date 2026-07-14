package ua.babka.render.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;

public class AnimatedCape {
    private static final int DEFAULT_DELAY_MS = 50;
    private static AnimatedCape instance;
    private final List<NativeImage> frames = new ArrayList<NativeImage>();
    private final List<Integer> delays = new ArrayList<Integer>();
    private int currentFrame = 0;
    private long lastSwitchTime = 0L;
    private Identifier currentId;
    private boolean loaded = false;

    private AnimatedCape() {
    }

    public static AnimatedCape getInstance() {
        if (instance == null) {
            instance = new AnimatedCape();
        }
        return instance;
    }

    public void setCape(Identifier id) {
        if (id.equals((Object)this.currentId) && this.loaded) {
            return;
        }
        this.closeFrames();
        this.currentId = id;
        this.loaded = false;
        this.loadFrames(id);
    }

    private void loadFrames(Identifier id) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) {
            return;
        }
        try {
            byte[] rawBytes;
            Optional opt = client.getResourceManager().getResource(id);
            if (opt.isEmpty()) {
                return;
            }
            try (InputStream is = ((Resource)opt.get()).getInputStream();){
                rawBytes = is.readAllBytes();
            }
            this.parseDelaysFromRaw(rawBytes);
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("gif");
            if (!readers.hasNext()) {
                return;
            }
            ImageReader reader = readers.next();
            ImageInputStream iis = ImageIO.createImageInputStream(new ByteArrayInputStream(rawBytes));
            reader.setInput(iis);
            int numFrames = reader.getNumImages(true);
            for (int i2 = 0; i2 < numFrames; ++i2) {
                BufferedImage bi = reader.read(i2);
                int w2 = bi.getWidth();
                int h2 = bi.getHeight();
                NativeImage ni = new NativeImage(w2, h2, false);
                int[] pixels = bi.getRGB(0, 0, w2, h2, null, 0, w2);
                for (int y2 = 0; y2 < h2; ++y2) {
                    for (int x2 = 0; x2 < w2; ++x2) {
                        int argb = pixels[y2 * w2 + x2];
                        int a2 = argb >> 24 & 0xFF;
                        int r2 = argb >> 16 & 0xFF;
                        int g2 = argb >> 8 & 0xFF;
                        int b2 = argb & 0xFF;
                        ni.setColorArgb(x2, y2, a2 << 24 | r2 << 16 | g2 << 8 | b2);
                    }
                }
                this.frames.add(ni);
                if (i2 < this.delays.size()) continue;
                this.delays.add(50);
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!this.frames.isEmpty()) {
            this.refreshTexture(0);
            this.loaded = true;
        }
    }

    private void parseDelaysFromRaw(byte[] data) {
        int b2;
        this.delays.clear();
        if (data.length < 6) {
            return;
        }
        String header = new String(data, 0, 6);
        if (!header.startsWith("GIF")) {
            return;
        }
        int pos = 13;
        boolean gceFound = false;
        block0: while (pos < data.length - 1 && (b2 = data[pos] & 0xFF) != 59) {
            if (b2 == 33) {
                int extLabel = data[pos + 1] & 0xFF;
                if (extLabel == 249) {
                    int blockSize = data[pos + 2] & 0xFF;
                    if (blockSize == 4) {
                        int delayHi = data[pos + 5] & 0xFF;
                        int delayLo = data[pos + 4] & 0xFF;
                        int delayCs = delayHi << 8 | delayLo;
                        int delayMs = delayCs * 10;
                        if (delayMs < 10) {
                            delayMs = 50;
                        }
                        this.delays.add(delayMs);
                        gceFound = true;
                    }
                    pos += 2 + blockSize + 1;
                    continue;
                }
                pos += 2;
                while (pos < data.length) {
                    int sz = data[pos] & 0xFF;
                    if (sz == 0) {
                        ++pos;
                        continue block0;
                    }
                    pos += sz + 1;
                }
                continue;
            }
            if (b2 == 44) {
                boolean hasLCT;
                int packed = data[(pos += 10) - 1] & 0xFF;
                boolean bl = hasLCT = (packed & 0x80) != 0;
                if (hasLCT) {
                    pos += (2 << (packed & 7)) * 3;
                }
                while (pos < data.length) {
                    int sz = data[pos] & 0xFF;
                    if (sz == 0) {
                        ++pos;
                        break;
                    }
                    pos += sz + 1;
                }
                if (!gceFound) {
                    this.delays.add(50);
                }
                gceFound = false;
                continue;
            }
            ++pos;
        }
    }

    private void refreshTexture(int index) {
        if (index >= this.frames.size() || this.currentId == null) {
            return;
        }
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) {
            return;
        }
        NativeImage src = this.frames.get(index);
        int w2 = src.getWidth();
        int h2 = src.getHeight();
        NativeImage copy = new NativeImage(w2, h2, false);
        for (int y2 = 0; y2 < h2; ++y2) {
            for (int x2 = 0; x2 < w2; ++x2) {
                copy.setColorArgb(x2, y2, src.getColorArgb(x2, y2));
            }
        }
        NativeImageBackedTexture tex = new NativeImageBackedTexture(copy);
        client.getTextureManager().registerTexture(this.currentId, (AbstractTexture)tex);
    }

    public void tick() {
        int delay;
        if (!this.loaded || this.frames.size() <= 1) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - this.lastSwitchTime > (long)(delay = this.delays.get(this.currentFrame).intValue())) {
            this.currentFrame = (this.currentFrame + 1) % this.frames.size();
            this.refreshTexture(this.currentFrame);
            this.lastSwitchTime = now;
        }
    }

    public Identifier getCurrentId() {
        return this.currentId;
    }

    private void closeFrames() {
        for (NativeImage ni : this.frames) {
            ni.close();
        }
        this.frames.clear();
        this.delays.clear();
        this.currentFrame = 0;
        this.lastSwitchTime = 0L;
        this.loaded = false;
    }
}


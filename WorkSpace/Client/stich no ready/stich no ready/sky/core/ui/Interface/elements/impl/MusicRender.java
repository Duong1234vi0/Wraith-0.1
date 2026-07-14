package sky.core.ui.Interface.elements.impl;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.util.ResourceLocation;
import sky.core.events.EventRender2D;
import sky.core.ui.Interface.elements.ElementRender;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.managers.impl.dragmanager.Dragging;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.ScissorUtil;
import sky.core.utils.render.font.Fonts;

public class MusicRender implements ElementRender {
   private final Dragging dragging;
   private final ExecutorService executor = Executors.newSingleThreadExecutor();
   private volatile boolean polling = false;
   private long lastPoll = 0L;
   private byte[] lastArtworkBytes = null;
   private final ResourceLocation dynamicArtwork = new ResourceLocation("minecraft", "SkyCore/music_dynamic_art");
   private ResourceLocation currentArtwork = null;
   private static final float MAX_WIDTH = 155.0F;
   private static final float MIN_WIDTH = 65.0F;
   private static final float HEIGHT = 18.0F;
   private volatile MediaInfo mediaInfo = new MediaInfo();

   public MusicRender(Dragging dragging) {
      this.dragging = dragging;
   }

   public void render(EventRender2D.Post event) {
      this.pollMedia();
      MatrixStack ms = event.getStack();
      float x = this.dragging.getX();
      float y = this.dragging.getY();
      float padding = 3.0F;
      float coverSize = 11.0F;
      float radius = 6.0F;
      float coverRadius = 3.0F;
      float textGap = 3.0F;
      float separatorGap = 5.0F;
      float separatorWidth = 0.75F;
      float separatorHeight = 12.0F;
      String title = "No media";
      String artist = "Nothing is playing";
      String time = "0:00";
      if (this.mediaInfo != null) {
         if (this.mediaInfo.getTitle() != null && !this.mediaInfo.getTitle().isEmpty()) {
            title = this.mediaInfo.getTitle();
         }

         if (this.mediaInfo.getArtist() != null && !this.mediaInfo.getArtist().isEmpty()) {
            artist = this.mediaInfo.getArtist();
         } else {
            artist = "";
         }

         time = this.formatTime(this.mediaInfo.getPosition());
      }

      String textSeparator = artist != null && !artist.isEmpty() ? " " : "";
      float titleWidth = Fonts.sfregular[12].getWidth(title);
      float textSeparatorWidth = Fonts.sfregular[11].getWidth(textSeparator);
      float artistWidth = artist != null && !artist.isEmpty() ? Fonts.sfregular[11].getWidth(artist) : 0.0F;
      float timeWidth = Fonts.sfregular[12].getWidth(time);
      float textLineWidth = titleWidth + textSeparatorWidth + artistWidth;
      float contentWidth = padding + coverSize + textGap + textLineWidth + separatorGap + separatorWidth + separatorGap + timeWidth + padding + 2.0F;
      float width = Math.max(65.0F, Math.min(155.0F, contentWidth));
      boolean needsScroll = contentWidth > 155.0F;
      this.dragging.setWidth(width);
      this.dragging.setHeight(18.0F);
      int logoColor = ThemeEditor.getColor(ThemeSettings.LOGO);
      int bgColor = ColorUtil.darken(logoColor, 0.1F);
      int titleColor = ColorUtil.rgb(255, 255, 255);
      int artistColor = ColorUtil.rgb(113, 113, 113);
      int separatorColor = ColorUtil.applyOpacity(ThemeEditor.getColor(ThemeSettings.SEPARATOR), ThemeEditor.getAlpha(ThemeSettings.SEPARATOR) / 255.0F * 0.2F);
      int placeholderColor = ColorUtil.rgb(35, 35, 35);
      int placeholderIconColor = ColorUtil.rgb(170, 170, 170);
      RenderUtil.drawRoundedRectangle(x, y, width, 18.0F, radius, bgColor);
      float coverX = x + padding;
      float coverY = y + (18.0F - coverSize) / 2.0F;
      if (this.currentArtwork != null) {
         RenderUtil.drawRoundedTexture(ms, this.currentArtwork, coverX, coverY, coverSize, coverSize, coverRadius, 1.0F);
      } else {
         RenderUtil.drawRoundedRectangle(coverX, coverY, coverSize, coverSize, coverRadius, placeholderColor);
         Fonts.divine_icons[11].drawString(ms, "i", (double)(coverX + 3.2F), (double)(coverY + 5.0F), placeholderIconColor);
      }

      float timeX = x + width - padding - timeWidth;
      float sepX = timeX - separatorGap - separatorWidth - 2.0F;
      float sepY = y + (18.0F - separatorHeight) / 2.0F;
      RenderUtil.drawMinecraftRectangle(ms, sepX, sepY - 2.5F, separatorWidth, separatorHeight + 5.0F, separatorColor);
      float textX = coverX + coverSize + textGap;
      float maxLineWidth = Math.max(10.0F, sepX - separatorGap - textX);
      float lineY = y + 8.0F;
      if (needsScroll) {
         this.renderScrollingTrackLine(ms, title, artist, textX, lineY, titleColor, artistColor, maxLineWidth);
      } else {
         Fonts.sfregular[12].drawString(ms, title, (double)textX, (double)lineY, titleColor);
         float drawX = textX + titleWidth;
         if (!textSeparator.isEmpty()) {
            Fonts.sfregular[11].drawString(ms, textSeparator, (double)drawX, (double)(lineY + 0.5F), artistColor);
            drawX += textSeparatorWidth;
            Fonts.sfregular[11].drawString(ms, artist, (double)drawX, (double)(lineY + 0.5F), artistColor);
         }
      }

      Fonts.sfregular[12].drawString(ms, time, (double)(timeX - 2.0F), (double)(y + 8.0F), logoColor);
   }

   private void pollMedia() {
      long now = System.currentTimeMillis();
      if (!this.polling && now - this.lastPoll >= 500L) {
         this.lastPoll = now;
         this.polling = true;
         this.executor.execute(() -> {
            try {
               try {
                  Class cls = Class.forName("dev.redstones.mediaplayerinfo.MediaPlayerInfo");
                  Object instance = cls.getField("Instance").get((Object)null);
                  List sessions = (List)cls.getMethod("getMediaSessions").invoke(instance);
                  if (sessions != null && !sessions.isEmpty()) {
                     Iterator var4 = sessions.iterator();

                     while(var4.hasNext()) {
                        Object session = var4.next();
                        Object media = session.getClass().getMethod("getMedia").invoke(session);
                        if (media != null) {
                           MediaInfo info = new MediaInfo();
                           info.title = (String)media.getClass().getMethod("getTitle").invoke(media);
                           info.artist = (String)media.getClass().getMethod("getArtist").invoke(media);
                           info.position = (Long)media.getClass().getMethod("getPosition").invoke(media);
                           info.playing = (Boolean)media.getClass().getMethod("getPlaying").invoke(media);
                           info.artworkPng = (byte[])media.getClass().getMethod("getArtworkPng").invoke(media);
                           this.mediaInfo = info;
                           this.updateArtwork(info.artworkPng);
                           break;
                        }
                     }
                  }
               } catch (NoSuchFieldException | NoSuchMethodException | ClassNotFoundException var13) {
                  this.mediaInfo = new MediaInfo();
                  this.currentArtwork = null;
               } catch (Exception var14) {
                  this.mediaInfo = new MediaInfo();
                  this.currentArtwork = null;
               }
            } catch (Throwable var15) {
               this.mediaInfo = new MediaInfo();
               this.currentArtwork = null;
            } finally {
               this.polling = false;
            }

         });
      }
   }

   private void updateArtwork(byte[] artworkBytes) {
      if (artworkBytes != null && artworkBytes.length != 0) {
         Minecraft.getInstance().execute(() -> {
            try {
               ByteArrayInputStream input = new ByteArrayInputStream(artworkBytes);
               NativeImage image = NativeImage.read(input);
               DynamicTexture texture = new DynamicTexture(image);
               Minecraft.getInstance().getTextureManager().deleteTexture(this.dynamicArtwork);
               Minecraft.getInstance().getTextureManager().loadTexture(this.dynamicArtwork, texture);
               this.currentArtwork = this.dynamicArtwork;
            } catch (Exception var5) {
               this.currentArtwork = null;
            }

         });
      } else {
         this.currentArtwork = null;
      }
   }

   private void renderScrollingTrackLine(MatrixStack ms, String title, String artist, float x, float y, int titleColor, int artistColor, float maxWidth) {
      String separator = artist != null && !artist.isEmpty() ? " " : "";
      float titleWidth = Fonts.sfregular[12].getWidth(title);
      float separatorWidth = Fonts.sfregular[11].getWidth(separator);
      float artistWidth = artist != null && !artist.isEmpty() ? Fonts.sfregular[11].getWidth(artist) : 0.0F;
      float fullWidth = titleWidth + separatorWidth + artistWidth;
      float scroll = 0.0F;
      float scrollMax;
      if (fullWidth > maxWidth) {
         scrollMax = fullWidth - maxWidth;
         float pause = 1000.0F;
         float duration = 4000.0F;
         float cycle = pause + duration + pause + duration;
         float time = (float)(System.currentTimeMillis() % (long)cycle);
         if (time < pause) {
            scroll = 0.0F;
         } else {
            float t;
            if (time < pause + duration) {
               t = (time - pause) / duration;
               scroll = t * scrollMax;
            } else if (time < pause + duration + pause) {
               scroll = scrollMax;
            } else {
               t = (time - pause - duration - pause) / duration;
               scroll = scrollMax * (1.0F - t);
            }
         }
      }

      scrollMax = x - scroll;
      ScissorUtil.start((double)(x - 1.0F), (double)(y - 2.0F), (double)(maxWidth + 2.0F), (double)(Fonts.sfregular[12].getHeight() + 6.0F));
      Fonts.sfregular[12].drawString(ms, title, (double)scrollMax, (double)y, titleColor);
      scrollMax += titleWidth;
      if (!separator.isEmpty()) {
         Fonts.sfregular[11].drawString(ms, separator, (double)scrollMax, (double)(y + 0.5F), artistColor);
         scrollMax += separatorWidth;
         Fonts.sfregular[11].drawString(ms, artist, (double)scrollMax, (double)(y + 0.5F), artistColor);
      }

      ScissorUtil.end();
   }

   private String formatTime(long seconds) {
      long minutes = seconds / 60L;
      long secs = seconds % 60L;
      return String.format("%d:%02d", minutes, secs);
   }

   private static class MediaInfo {
      String title = "No media";
      String artist = "Nothing is playing";
      long position = 0L;
      byte[] artworkPng = null;
      boolean playing = false;

      String getTitle() {
         return this.title;
      }

      String getArtist() {
         return this.artist;
      }

      long getPosition() {
         return this.position;
      }

      byte[] getArtworkPng() {
         return this.artworkPng;
      }

      boolean getPlaying() {
         return this.playing;
      }
   }

   private static class MediaPlayerInfo {
      static MediaPlayerInfo Instance = new MediaPlayerInfo();

      List getMediaSessions() {
         return Arrays.asList(new IMediaSession());
      }
   }

   private static class IMediaSession {
      MediaInfo media = new MediaInfo();

      MediaInfo getMedia() {
         return this.media;
      }
   }
}

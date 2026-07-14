package sky.core.ui.altmanager;

import com.adl.nativeprotect.Native;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import net.minecraft.client.gui.screen.ConnectingScreen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.ServerPinger;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import sky.core.utils.animation.AnimationUtil;
import sky.core.utils.animation.Easings;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.ScissorUtil;
import sky.core.utils.render.font.Fonts;

public class NewAltManager extends Screen {
   private static final ResourceLocation MENU_BACKGROUND = new ResourceLocation("minecraft", "SkyCore/mainmenu/background.png");
   private float fade;
   private boolean fadingOut;
   private Screen pendingScreen;
   private long fadeFrameTime;
   private final boolean showFadeIn;
   private final List accounts;
   private String selectedString;
   private float scroll;
   private float scrollAnimation;
   private boolean loaded;
   private long lastClickTime;
   private String lastClickedAccount;
   private float addHover;
   private long addHoverTime;
   private long lastFrameTime;
   private float modalAnim;
   private long modalAnimTime;
   private final Map hoverMap;
   private final Map hoverTimeMap;
   private final AnimationUtil scrollAnim;
   private final Map selectAnimMap;
   private final Map deleteAnimMap;
   private final Map deletingMap;
   private boolean showAddModal;
   private String modalInput;
   private boolean modalFieldFocused;
   private boolean modalCursorVisible;
   private long modalLastBlink;
   private float modalCreateHover;
   private long modalCreateHoverTime;
   private float modalGenHover;
   private long modalGenHoverTime;
   private static final String[] RANDOM_NAMES = new String[]{"leoapwoer", "rixando", "lucarde", "hengiro", "bevunho", "thionme", "andmurgo", "guivuson", "reilherpe", "ferano", "Alemurer", "cloonca", "Stepanh", "vanosik", "madboss", "wh1tolo01x", "Kamizaker", "Gent12ddelman991"};
   private static final String SERVER_NAME = "Лучший ХвХ сервер";
   private static final String SERVER_IP = "mc.slimeworld.pw";
   private static final String SERVER2_NAME = "ReallyWorld";
   private static final String SERVER2_IP = "mc.reallyworld.ru";
   private final ServerData serverData;
   private final ServerData serverData2;
   private final ServerPinger serverPinger;
   private ResourceLocation serverIconLoc;
   private DynamicTexture serverIcon;
   private String lastIconB64;
   private ResourceLocation serverIconLoc2;
   private DynamicTexture serverIcon2;
   private String lastIconB642;

   public NewAltManager() {
      this(true);
   }

   public NewAltManager(boolean fadeIn) {
      super(new StringTextComponent("AltManager"));
      this.fade = 1.0F;
      this.fadingOut = false;
      this.pendingScreen = null;
      this.fadeFrameTime = -1L;
      this.accounts = new ArrayList();
      this.selectedString = "";
      this.lastClickedAccount = "";
      this.addHover = 0.0F;
      this.addHoverTime = -1L;
      this.lastFrameTime = -1L;
      this.modalAnim = 0.0F;
      this.modalAnimTime = -1L;
      this.hoverMap = new HashMap();
      this.hoverTimeMap = new HashMap();
      this.scrollAnim = new AnimationUtil(0.0F, 8.0F, Easings.CUBIC_OUT);
      this.selectAnimMap = new HashMap();
      this.deleteAnimMap = new HashMap();
      this.deletingMap = new HashMap();
      this.showAddModal = false;
      this.modalInput = "";
      this.modalFieldFocused = true;
      this.modalCursorVisible = true;
      this.modalLastBlink = System.currentTimeMillis();
      this.modalCreateHover = 0.0F;
      this.modalCreateHoverTime = -1L;
      this.modalGenHover = 0.0F;
      this.modalGenHoverTime = -1L;
      this.serverData = new ServerData("Лучший ХвХ сервер", "mc.slimeworld.pw", false);
      this.serverData2 = new ServerData("ReallyWorld", "mc.reallyworld.ru", false);
      this.serverPinger = new ServerPinger();
      this.showFadeIn = fadeIn;
   }

   public void tick() {
      super.tick();
   }

   @Native
   protected void init() {
      super.init();
      if (this.showFadeIn) {
         this.fade = 0.0F;
      } else {
         this.fade = 1.0F;
      }

      this.fadingOut = false;
      this.pendingScreen = null;
      this.fadeFrameTime = -1L;
      if (!this.loaded) {
         AltConfig.init(this.accounts);
         this.loaded = true;
         if (this.minecraft.session != null) {
            this.selectedString = this.minecraft.session.getUsername();
         }
      }

      HashFunction var10003 = Hashing.sha1();
      this.serverIconLoc = new ResourceLocation("servers/" + String.valueOf(var10003.hashUnencodedChars("mc.slimeworld.pw")) + "/icon");
      this.serverIcon = (DynamicTexture)this.minecraft.getTextureManager().getTexture(this.serverIconLoc);
      var10003 = Hashing.sha1();
      this.serverIconLoc2 = new ResourceLocation("servers/" + String.valueOf(var10003.hashUnencodedChars("mc.reallyworld.ru")) + "/icon");
      this.serverIcon2 = (DynamicTexture)this.minecraft.getTextureManager().getTexture(this.serverIconLoc2);
      this.pingServerIfNeeded(this.serverData, "ServerPinger-AltManager-1");
      this.pingServerIfNeeded(this.serverData2, "ServerPinger-AltManager-2");
   }

   private void pingServerIfNeeded(ServerData data, String threadName) {
      if (!data.pinged) {
         data.pinged = true;
         data.pingToServer = -2L;
         (new Thread(() -> {
            try {
               this.serverPinger.ping(data, () -> {
               });
            } catch (Exception var3) {
            }

         }, threadName)).start();
      }
   }

   private void updateServerIconFromPing(ServerData data, ResourceLocation iconLoc, DynamicTexture currentTexture, String lastB64, IconUpdateResult out) {
      String iconB64 = data.getBase64EncodedIconData();
      if (!Objects.equals(iconB64, lastB64)) {
         out.lastB64 = iconB64;
         if (iconB64 != null) {
            try {
               NativeImage img = NativeImage.readBase64(iconB64);
               DynamicTexture tex = currentTexture;
               if (currentTexture == null) {
                  tex = new DynamicTexture(img);
               } else {
                  currentTexture.setTextureData(img);
                  currentTexture.updateDynamicTexture();
               }

               this.minecraft.getTextureManager().loadTexture(iconLoc, tex);
               out.texture = tex;
            } catch (Exception var9) {
            }
         }
      }

   }

   public void addAccount(String name) {
      if (name != null && !name.trim().isEmpty()) {
         Account account = new Account(name.trim());
         this.accounts.add(account);
         account.loadSkinAsync();
         AltConfig.updateFile(this.accounts);
      }
   }

   private void loginSelected() {
      if (this.selectedString != null && !this.selectedString.isEmpty()) {
         Iterator var1 = this.accounts.iterator();

         while(var1.hasNext()) {
            Account account = (Account)var1.next();
            if (account.accountName.equals(this.selectedString)) {
               this.minecraft.session = new Session(account.accountName, "", "", "mojang");
               AltConfig.updateFile(this.accounts);
               break;
            }
         }

      }
   }

   private void deleteSelected() {
      if (this.selectedString != null && !this.selectedString.isEmpty()) {
         Iterator it = this.accounts.iterator();

         while(it.hasNext()) {
            Account a = (Account)it.next();
            if (a.accountName.equals(this.selectedString)) {
               it.remove();
               this.selectedString = "";
               AltConfig.updateFile(this.accounts);
               break;
            }
         }

      }
   }

   public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
      GlStateManager.disableDepthTest();
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      long nowFade = System.currentTimeMillis();
      float fadeDelta = this.fadeFrameTime < 0L ? 0.0F : Math.min((float)(nowFade - this.fadeFrameTime) / 1000.0F, 0.1F);
      this.fadeFrameTime = nowFade;
      float fadeSpeed = 6.5F;
      if (this.fadingOut) {
         this.fade = Math.max(this.fade - fadeDelta * fadeSpeed, 0.0F);
         if (this.fade <= 0.0F && this.pendingScreen != null) {
            this.minecraft.displayGuiScreen(this.pendingScreen);
            return;
         }
      } else {
         this.fade = Math.min(this.fade + fadeDelta * fadeSpeed, 1.0F);
      }

      long nowMs = System.currentTimeMillis();
      if (this.lastFrameTime < 0L) {
         float var10000 = 0.0F;
      } else {
         Math.min((float)(nowMs - this.lastFrameTime) / 1000.0F, 0.1F);
      }

      this.lastFrameTime = nowMs;
      float fa = this.fade;
      int fa255 = (int)(255.0F * fa);
      int bgFill = (int)(255.0F * this.fade) << 24 | 1381400;
      fill(ms, 0, 0, this.width, this.height, bgFill);
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.fade);
      this.minecraft.getTextureManager().bindTexture(MENU_BACKGROUND);
      blit(ms, 0, 0, 0.0F, 0.0F, this.width, this.height, this.width, this.height);
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      IconUpdateResult icon1 = new IconUpdateResult();
      icon1.lastB64 = this.lastIconB64;
      icon1.texture = this.serverIcon;
      this.updateServerIconFromPing(this.serverData, this.serverIconLoc, this.serverIcon, this.lastIconB64, icon1);
      this.lastIconB64 = icon1.lastB64;
      this.serverIcon = icon1.texture;
      IconUpdateResult icon2 = new IconUpdateResult();
      icon2.lastB64 = this.lastIconB642;
      icon2.texture = this.serverIcon2;
      this.updateServerIconFromPing(this.serverData2, this.serverIconLoc2, this.serverIcon2, this.lastIconB642, icon2);
      this.lastIconB642 = icon2.lastB64;
      this.serverIcon2 = icon2.texture;
      int gray = ColorUtil.getColor(103, 106, 114, (int)(0.85F * (float)fa255));
      int white = ColorUtil.getColor(255, 255, 255, fa255);
      int cardBorder = ColorUtil.getColor(103, 106, 114, (int)(0.15F * (float)fa255));
      int cba = (int)(0.15F * (float)fa255);
      float cardW = 160.0F;
      float cardH = 28.0F;
      float cardRadius = 4.0F;
      float cardGapX = 6.0F;
      float cardGapY = 6.0F;
      float skinSize = 18.0F;
      float blockW = cardW * 2.0F + cardGapX;
      float blockX = (float)Math.round(((float)this.width - blockW) / 2.0F);
      float secH = Fonts.sf_regular[16].getHeight();
      int maxRowsConst = 3;
      float headerH = Fonts.sf_regular[20].getHeight() + Fonts.sf_regular[14].getHeight() + 1.0F;
      float accountsH = secH + 5.0F + (float)maxRowsConst * (cardH + cardGapY) - cardGapY;
      float serversH = secH + 5.0F + cardH;
      float addH = 20.0F;
      float totalH = headerH + 12.0F + accountsH + 8.0F + serversH + 8.0F + addH;
      float footerBlock = Fonts.sf_regular[13].getHeight() * 2.0F + 10.0F;
      float titleY = Math.min(((float)this.height - totalH) / 2.0F, (float)this.height - totalH - footerBlock);
      titleY = Math.max(titleY, 16.0F);
      boolean blockBgInteraction = this.showAddModal || this.modalAnim > 0.01F;
      Fonts.sf_regular[25].drawString(ms, "AltManager", (double)blockX, (double)(titleY - 6.0F), white);
      Fonts.sf_regular[18].drawString(ms, "Create or select account", (double)blockX, (double)(titleY + Fonts.sf_regular[20].getHeight() + 1.0F), gray);
      float blockY = titleY + headerH + 12.0F;
      this.drawSectionHeader(ms, blockX - 3.0F, blockY, "Accounts", white, fa255);
      float rowY = blockY + secH + 5.0F;
      this.scrollAnim.update(this.scroll);
      this.scrollAnimation = this.scrollAnim.getValue();
      float scrollOff = this.scrollAnimation;
      int count = 0;
      float col0X = blockX;
      float col1X = blockX + cardW + cardGapX;
      int maxRows = 3;
      float scissorTop = rowY;
      float scissorBot = rowY + (float)maxRows * (cardH + cardGapY) - cardGapY;
      ScissorUtil.start(0.0, (double)rowY, (double)this.width, (double)(scissorBot - rowY));

      float plusH;
      float footerX;
      float footerY;
      for(Iterator var48 = this.accounts.iterator(); var48.hasNext(); ++count) {
         Account account = (Account)var48.next();
         int col = count % 2;
         int row = count / 2;
         float cx2 = col == 0 ? col0X : col1X;
         float cy2 = rowY + (float)row * (cardH + cardGapY) + scrollOff * (cardH + cardGapY);
         if (cy2 + cardH > scissorTop && cy2 < scissorBot) {
            boolean isSelected = account.accountName.equals(this.selectedString);
            boolean isLoggedIn = this.minecraft.session != null && account.accountName.equalsIgnoreCase(this.minecraft.session.getUsername());
            long nt = System.currentTimeMillis();
            float ph = (Float)this.hoverMap.getOrDefault(account.accountName, 0.0F);
            long lt = (Long)this.hoverTimeMap.getOrDefault(account.accountName, nt);
            float dh = Math.min((float)(nt - lt) / 1000.0F, 0.05F);
            this.hoverTimeMap.put(account.accountName, nt);
            boolean hov = !blockBgInteraction && (float)mouseX >= cx2 && (float)mouseX <= cx2 + cardW && (float)mouseY >= cy2 && (float)mouseY <= cy2 + cardH;
            plusH = blockBgInteraction ? Math.max(ph - dh * 10.0F, 0.0F) : (hov ? Math.min(ph + dh * 7.0F, 1.0F) : Math.max(ph - dh * 7.0F, 0.0F));
            this.hoverMap.put(account.accountName, plusH);
            AnimationUtil selAnim = (AnimationUtil)this.selectAnimMap.computeIfAbsent(account.accountName, (k) -> {
               return new AnimationUtil(0.0F, 6.0F, Easings.CUBIC_OUT);
            });
            selAnim.update(isSelected ? 1.0F : 0.0F);
            float sel = selAnim.getValue();
            int bgR = (int)(30.0F + 124.0F * sel + 20.0F * plusH * (1.0F - sel));
            int bgG = (int)(30.0F + 162.0F * sel + 20.0F * plusH * (1.0F - sel));
            int bgB = (int)(35.0F + 220.0F * sel + 20.0F * plusH * (1.0F - sel));
            footerX = 0.55F + 0.15F * sel + 0.1F * plusH * (1.0F - sel);
            RenderUtil.drawBlurredRoundedRectangle(cx2, cy2, cardW, cardH, cardRadius + 2.0F, ColorUtil.getColor(bgR, bgG, bgB, 255), footerX * this.fade);
            footerY = cx2 + 5.0F;
            float skinY = cy2 + (cardH - skinSize) / 2.0F;
            this.minecraft.getTextureManager().bindTexture(account.skin);
            RenderUtil.drawRoundedHead(account.skin, (LivingEntity)null, footerY, skinY, skinSize, skinSize, 2.0F, this.fade);
            float textX = footerY + skinSize + 5.0F;
            int nameCol = isSelected ? ColorUtil.getColor(255, 255, 255, fa255) : ColorUtil.getColor((int)(140.0F + 115.0F * plusH), (int)(140.0F + 115.0F * plusH), (int)(140.0F + 115.0F * plusH), fa255);
            Fonts.sf_regular[16].drawString(ms, account.accountName, (double)((float)Math.round(textX)), (double)((float)Math.round(skinY) + 2.5F), nameCol);
            String dateStr = (new SimpleDateFormat("dd.MM.yyyy")).format(new Date(account.dateAdded));
            int dateCol = isSelected ? ColorUtil.getColor(255, 255, 255, fa255) : gray;
            Fonts.sf_regular[13].drawString(ms, dateStr, (double)((float)Math.round(textX)), (double)((float)Math.round(skinY + Fonts.sf_regular[14].getHeight() + 1.0F) + 6.5F), dateCol);
            float sBtnW = Fonts.krisa[16].getWidth("F") + 10.0F;
            float sBtnX = cx2 + cardW - sBtnW - 4.0F;
            float sBtnY = cy2 + (cardH - Fonts.sf_regular[13].getHeight()) / 2.0F;
            boolean var100;
            if ((float)mouseX >= sBtnX - 2.0F && (float)mouseX <= sBtnX + sBtnW + 2.0F && (float)mouseY >= cy2 && (float)mouseY <= cy2 + cardH) {
               var100 = true;
            } else {
               var100 = false;
            }

            int fBtnTxC = isLoggedIn ? 255 : 103;
            int sCol = ColorUtil.getColor(fBtnTxC, fBtnTxC, fBtnTxC, fa255);
            Fonts.krisa[16].drawString(ms, "F", (double)((float)Math.round(sBtnX + 5.0F)), (double)((float)Math.round(sBtnY)), sCol);
         }
      }

      ScissorUtil.end();
      int visibleRows = (int)Math.ceil((double)((float)count / 2.0F));
      this.scroll = MathHelper.clamp(this.scroll, -((float)Math.max(0, visibleRows - maxRows)), 0.0F);
      this.scrollAnimation = MathHelper.clamp(this.scrollAnimation, -((float)Math.max(0, visibleRows - maxRows)), 0.0F);
      float serversY = scissorBot + 8.0F;
      this.drawSectionHeader(ms, blockX - 3.0F, serversY, "Servers", white, fa255);
      float srvRowY = serversY + secH + 5.0F;
      this.drawServerCard(ms, col0X, srvRowY, cardW, cardH, cardRadius, skinSize, "__srv0", "Лучший ХвХ сервер", "mc.slimeworld.pw", this.serverIconLoc, this.serverIcon, blockBgInteraction, mouseX, mouseY, this.fade, fa255, gray);
      this.drawServerCard(ms, col1X, srvRowY, cardW, cardH, cardRadius, skinSize, "__srv1", "ReallyWorld", "mc.reallyworld.ru", this.serverIconLoc2, this.serverIcon2, blockBgInteraction, mouseX, mouseY, this.fade, fa255, gray);
      float addBtnH = 20.0F;
      float addBtnY = srvRowY + cardH + 8.0F;
      long nowA = System.currentTimeMillis();
      if (this.addHoverTime < 0L) {
         this.addHoverTime = nowA;
      }

      float dA = Math.min((float)(nowA - this.addHoverTime) / 1000.0F, 0.05F);
      this.addHoverTime = nowA;
      boolean addHov = !blockBgInteraction && (float)mouseX >= blockX && (float)mouseX <= blockX + blockW && (float)mouseY >= addBtnY && (float)mouseY <= addBtnY + addBtnH;
      this.addHover = blockBgInteraction ? Math.max(this.addHover - dA * 10.0F, 0.0F) : (addHov ? Math.min(this.addHover + dA * 7.0F, 1.0F) : Math.max(this.addHover - dA * 7.0F, 0.0F));
      int addBgR = (int)(30.0F + 20.0F * this.addHover);
      int addBgG = (int)(30.0F + 20.0F * this.addHover);
      int addBgB = (int)(35.0F + 20.0F * this.addHover);
      RenderUtil.drawBlurredRoundedRectangle(blockX, addBtnY, blockW, addBtnH, cardRadius + 2.0F, ColorUtil.getColor(addBgR, addBgG, addBgB, 255), (0.55F + 0.1F * this.addHover) * this.fade);
      float plusW = Fonts.sf_regular[16].getWidth("+");
      plusH = Fonts.sf_regular[16].getHeight();
      int addTxC = (int)(140.0F + 115.0F * this.addHover);
      int plusCol = ColorUtil.getColor(addTxC, addTxC, addTxC, fa255);
      Fonts.sf_regular[16].drawString(ms, "+", (double)((float)Math.round(blockX + (blockW - plusW) / 2.0F)), (double)((float)Math.round(addBtnY + (addBtnH - plusH) / 2.0F)), plusCol);
      String fLine1 = "SkyCore 2026. All rights reserved.";
      String fLine2 = "Before playing, please read the Rules of use.";
      float fh = Fonts.sf_regular[13].getHeight();
      footerX = ((float)this.width - Fonts.sf_regular[13].getWidth(fLine1)) / 2.0F;
      footerY = (float)this.height - fh * 2.0F - 8.0F;
      Fonts.sf_regular[13].drawString(ms, fLine1, (double)footerX, (double)footerY, gray);
      Fonts.sf_regular[13].drawString(ms, fLine2, (double)(footerX - 12.0F), (double)(footerY + fh + 2.0F), gray);
      if (this.showAddModal || this.modalAnim > 0.0F) {
         long nowModal = System.currentTimeMillis();
         float dModal = this.modalAnimTime < 0L ? 0.0F : Math.min((float)(nowModal - this.modalAnimTime) / 1000.0F, 0.1F);
         this.modalAnimTime = nowModal;
         this.modalAnim = this.showAddModal ? Math.min(this.modalAnim + dModal * 12.0F, 1.0F) : Math.max(this.modalAnim - dModal * 12.0F, 0.0F);
         float modalGlobalAnim = this.modalAnim * this.fade;
         if (modalGlobalAnim > 0.0F) {
            this.renderAddModal(ms, mouseX, mouseY, modalGlobalAnim);
         }
      }

      super.render(ms, mouseX, mouseY, partialTicks);
   }

   private void startFadeOut(Screen next) {
      if (!this.fadingOut) {
         this.fadingOut = true;
         this.pendingScreen = next;
      }
   }

   private void renderAddModal(MatrixStack ms, int mouseX, int mouseY, float anim) {
      RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/mainmenu/black_background.png"), 0.0F, 0.0F, (float)this.width, (float)this.height, ColorUtil.applyOpacity(-1, (int)(130.0F * anim)));
      float mw = 200.0F;
      float mh = 95.0F;
      float mx = ((float)this.width - mw) / 2.0F;
      float my = ((float)this.height - mh) / 2.0F + 10.0F;
      float pad = 12.0F;
      float gap = 8.0F;
      int modalAlpha = (int)(255.0F * anim);
      RenderUtil.drawRoundedRectangle(mx, my, mw, mh - 5.0F, 7.0F, ColorUtil.getColor(21, 20, 24, modalAlpha));
      int gray85 = ColorUtil.getColor(103, 106, 114, (int)(216.75F * anim));
      int white = ColorUtil.getColor(255, 255, 255, (int)(255.0F * anim));
      float cy = my + pad;
      float titleH = Fonts.sf_regular[18].getHeight();
      Fonts.sf_regular[18].drawString(ms, "Менеджер аккаунтов", (double)((float)Math.round(mx + pad)), (double)((float)Math.round(cy)), gray85);
      cy += titleH + gap;
      float fieldW = mw - pad * 2.0F;
      float fieldH = 21.0F;
      RenderUtil.drawBlurredRoundedRectangle(mx + pad, cy, fieldW, fieldH, 5.0F, ColorUtil.getColor(103, 106, 114, 255), 0.1F * anim);
      RenderUtil.drawOutlineRectangle(mx + pad, cy, fieldW, fieldH, 6.0F, ColorUtil.getColor(103, 106, 114, 255), 38.25F * anim);
      float textY = cy + (fieldH - Fonts.sf_regular[18].getHeight()) / 2.0F;
      if (this.modalInput.isEmpty() && !this.modalFieldFocused) {
         Fonts.sf_regular[18].drawString(ms, "Введите никнейм", (double)((float)Math.round(mx + pad + 8.0F)), (double)((float)Math.round(textY)), gray85);
      } else {
         Fonts.sf_regular[18].drawString(ms, this.modalInput, (double)((float)Math.round(mx + pad + 8.0F)), (double)((float)Math.round(textY) - 0.5F), white);
      }

      cy += fieldH + gap;
      float btnW = (fieldW - gap) / 2.0F;
      float btnH = 21.0F;
      long nowC = System.currentTimeMillis();
      if (this.modalCreateHoverTime < 0L) {
         this.modalCreateHoverTime = nowC;
      }

      float dC = Math.min((float)(nowC - this.modalCreateHoverTime) / 1000.0F, 0.05F);
      this.modalCreateHoverTime = nowC;
      boolean onCreate = (float)mouseX >= mx + pad && (float)mouseX <= mx + pad + btnW && (float)mouseY >= cy && (float)mouseY <= cy + btnH;
      this.modalCreateHover = onCreate ? Math.min(this.modalCreateHover + dC * 10.0F, 1.0F) : Math.max(this.modalCreateHover - dC * 10.0F, 0.0F);
      int createBgR = (int)(154.0F + 66.0F * this.modalCreateHover);
      int createBgG = (int)(192.0F + 38.0F * this.modalCreateHover);
      RenderUtil.drawRoundedRectangle(mx + pad, cy, btnW, btnH, 5.0F, ColorUtil.getColor(createBgR, createBgG, 255, modalAlpha));
      float createTxtW = Fonts.sf_regular[18].getWidth("Создать");
      Fonts.sf_regular[18].drawString(ms, "Создать", (double)((float)Math.round(mx + pad + (btnW - createTxtW) / 2.0F)), (double)((float)Math.round(cy + (btnH - Fonts.sf_regular[18].getHeight()) / 2.0F)), ColorUtil.getColor(16, 17, 18, modalAlpha));
      float genX = mx + pad + btnW + gap;
      long nowG = System.currentTimeMillis();
      if (this.modalGenHoverTime < 0L) {
         this.modalGenHoverTime = nowG;
      }

      float dG = Math.min((float)(nowG - this.modalGenHoverTime) / 1000.0F, 0.05F);
      this.modalGenHoverTime = nowG;
      boolean onGen = (float)mouseX >= genX && (float)mouseX <= genX + btnW && (float)mouseY >= cy && (float)mouseY <= cy + btnH;
      this.modalGenHover = onGen ? Math.min(this.modalGenHover + dG * 10.0F, 1.0F) : Math.max(this.modalGenHover - dG * 10.0F, 0.0F);
      int genBg = (int)(25.0F + 20.0F * this.modalGenHover);
      RenderUtil.drawRoundedRectangle(genX, cy, btnW, btnH, 5.0F, ColorUtil.getColor(genBg, genBg, genBg + 5, modalAlpha));
      RenderUtil.drawOutlineRectangle(genX, cy, btnW, btnH, 6.0F, ColorUtil.getColor(103, 106, 114, (int)(0.15F * (float)modalAlpha)), 125.0F);
      float genTxtW = Fonts.sf_regular[18].getWidth("Сгенерировать");
      int genTxC = (int)(103.0F + 57.0F * this.modalGenHover);
      Fonts.sf_regular[18].drawString(ms, "Сгенерировать", (double)((float)Math.round(genX + (btnW - genTxtW) / 2.0F)), (double)((float)Math.round(cy + (btnH - Fonts.sf_regular[18].getHeight()) / 2.0F)), ColorUtil.getColor(genTxC, genTxC, genTxC, modalAlpha));
   }

   private void openAddModal() {
      this.showAddModal = true;
      this.modalInput = "";
      this.modalFieldFocused = true;
      this.modalCreateHover = 0.0F;
      this.modalGenHover = 0.0F;
      this.modalAnimTime = -1L;
      this.hoverMap.clear();
      this.hoverTimeMap.clear();
   }

   private void closeAddModal() {
      this.showAddModal = false;
      this.modalInput = "";
   }

   private void confirmAddModal() {
      String text = this.modalInput.trim();
      if (text.isEmpty()) {
         String var10000 = RANDOM_NAMES[(new Random()).nextInt(RANDOM_NAMES.length)];
         text = var10000 + (new Random()).nextInt(1500);
      }

      this.addAccount(text);
      this.closeAddModal();
   }

   private void drawSectionHeader(MatrixStack ms, float x, float y, String title, int color, int fa255) {
      Fonts.sf_regular[18].drawString(ms, title, (double)((float)Math.round(x) + 3.0F), (double)((float)Math.round(y)), color);
   }

   private void drawServerCard(MatrixStack ms, float x, float y, float cardW, float cardH, float cardRadius, float iconSize, String hoverKey, String name, String ip, ResourceLocation iconLoc, DynamicTexture iconTex, boolean blockBgInteraction, int mouseX, int mouseY, float fade, int fa255, int gray) {
      long now = System.currentTimeMillis();
      float ph = (Float)this.hoverMap.getOrDefault(hoverKey, 0.0F);
      long lt = (Long)this.hoverTimeMap.getOrDefault(hoverKey, now);
      float dh = Math.min((float)(now - lt) / 1000.0F, 0.05F);
      this.hoverTimeMap.put(hoverKey, now);
      boolean hov = !blockBgInteraction && (float)mouseX >= x && (float)mouseX <= x + cardW && (float)mouseY >= y && (float)mouseY <= y + cardH;
      float th = blockBgInteraction ? Math.max(ph - dh * 10.0F, 0.0F) : (hov ? Math.min(ph + dh * 7.0F, 1.0F) : Math.max(ph - dh * 7.0F, 0.0F));
      this.hoverMap.put(hoverKey, th);
      int bgR = (int)(30.0F + 20.0F * th);
      int bgG = (int)(30.0F + 20.0F * th);
      int bgB = (int)(35.0F + 20.0F * th);
      RenderUtil.drawBlurredRoundedRectangle(x, y, cardW, cardH, cardRadius + 2.0F, ColorUtil.getColor(bgR, bgG, bgB, 255), (0.55F + 0.1F * th) * fade);
      float iconX = x + 5.0F;
      float iconY = y + (cardH - iconSize) / 2.0F;
      ResourceLocation tex = iconTex != null ? iconLoc : new ResourceLocation("textures/misc/unknown_server.png");
      RenderUtil.drawRoundedTexture(ms, tex, iconX, iconY, iconSize, iconSize, 3.0F, fade);
      float textX = iconX + iconSize + 5.0F;
      int txC = (int)(140.0F + 115.0F * th);
      int nameCol = ColorUtil.getColor(txC, txC, txC, fa255);
      Fonts.sf_regular[16].drawString(ms, name, (double)((float)Math.round(textX)), (double)((float)Math.round(iconY) + 2.0F), nameCol);
      Fonts.sf_regular[13].drawString(ms, ip, (double)((float)Math.round(textX) + 0.5F), (double)((float)Math.round(iconY + Fonts.sf_regular[14].getHeight() + 1.0F) + 6.5F), gray);
   }

   private float[] getLayoutCoords() {
      float cardW = 160.0F;
      float cardH = 28.0F;
      float cardGapX = 6.0F;
      float cardGapY = 6.0F;
      float blockW = cardW * 2.0F + cardGapX;
      float blockX = (float)Math.round(((float)this.width - blockW) / 2.0F);
      float secH = Fonts.sf_regular[16].getHeight();
      int maxRows = 3;
      float headerH = Fonts.sf_regular[20].getHeight() + Fonts.sf_regular[14].getHeight() + 1.0F;
      float accountsH = secH + 5.0F + (float)maxRows * (cardH + cardGapY) - cardGapY;
      float serversH = secH + 5.0F + cardH;
      float addH = 20.0F;
      float totalH = headerH + 12.0F + accountsH + 8.0F + serversH + 8.0F + addH;
      float footerBlock = Fonts.sf_regular[13].getHeight() * 2.0F + 10.0F;
      float titleY = Math.min(((float)this.height - totalH) / 2.0F, (float)this.height - totalH - footerBlock);
      titleY = Math.max(titleY, 16.0F);
      float blockY = titleY + headerH + 12.0F;
      float rowY = blockY + secH + 5.0F;
      float scissorBot = rowY + (float)maxRows * (cardH + cardGapY) - cardGapY;
      float srvRowY = scissorBot + 8.0F + secH + 5.0F;
      float addBtnY = srvRowY + cardH + 8.0F;
      return new float[]{cardW, cardH, cardGapX, cardGapY, blockW, blockX, rowY, srvRowY, addBtnY};
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      float mh;
      float mx;
      float my;
      float pad;
      float blockW;
      float blockX;
      float fieldW;
      float fieldH;
      float btnH;
      float btnW;
      float fieldY;
      float btnY;
      if (!this.showAddModal && !(this.modalAnim > 0.01F)) {
         float[] L = this.getLayoutCoords();
         mh = L[0];
         mx = L[1];
         my = L[2];
         pad = L[3];
         blockW = L[4];
         blockX = L[5];
         fieldW = L[6];
         fieldH = L[7];
         btnH = L[8];
         btnW = blockX;
         fieldY = blockX + mh + my;
         btnY = this.scrollAnimation;
         int count = 0;
         float scissorTop2 = fieldW;
         float scissorBot2 = fieldW + 3.0F * (mx + pad) - pad;
         Iterator var22 = this.accounts.iterator();

         while(true) {
            while(var22.hasNext()) {
               Account account = (Account)var22.next();
               int col = count % 2;
               int row = count / 2;
               float cx2 = col == 0 ? btnW : fieldY;
               float cy2 = fieldW + (float)row * (mx + pad) + btnY * (mx + pad);
               if (!(cy2 + mx <= scissorTop2) && !(cy2 >= scissorBot2)) {
                  float sBtnW = Fonts.sf_regular[13].getWidth("S") + 10.0F;
                  float sBtnX = cx2 + mh - sBtnW - 4.0F;
                  boolean onSBtn = mouseX >= (double)(sBtnX - 2.0F) && mouseX <= (double)(sBtnX + sBtnW + 2.0F) && mouseY >= (double)cy2 && mouseY <= (double)(cy2 + mx);
                  if (button == 0 && onSBtn) {
                     String name = account.accountName;
                     this.accounts.remove(account);
                     if (name.equals(this.selectedString)) {
                        this.selectedString = "";
                     }

                     AltConfig.updateFile(this.accounts);
                     this.selectAnimMap.remove(name);
                     this.hoverMap.remove(name);
                     this.hoverTimeMap.remove(name);
                     return true;
                  }

                  if (mouseX >= (double)cx2 && mouseX <= (double)(cx2 + mh) && mouseY >= (double)cy2 && mouseY <= (double)(cy2 + mx) && button == 0) {
                     this.selectedString = account.accountName;
                     this.loginSelected();
                  }

                  ++count;
               } else {
                  ++count;
               }
            }

            float addBtnH = 20.0F;
            if (button == 0 && mouseX >= (double)btnW && mouseX <= (double)(btnW + mh) && mouseY >= (double)fieldH && mouseY <= (double)(fieldH + mx)) {
               this.minecraft.displayGuiScreen(new ConnectingScreen(this, this.minecraft, this.serverData));
               return true;
            }

            if (button == 0 && mouseX >= (double)fieldY && mouseX <= (double)(fieldY + mh) && mouseY >= (double)fieldH && mouseY <= (double)(fieldH + mx)) {
               this.minecraft.displayGuiScreen(new ConnectingScreen(this, this.minecraft, this.serverData2));
               return true;
            }

            if (button == 0 && mouseX >= (double)blockX && mouseX <= (double)(blockX + blockW) && mouseY >= (double)btnH && mouseY <= (double)(btnH + addBtnH)) {
               this.openAddModal();
               return true;
            }

            return super.mouseClicked(mouseX, mouseY, button);
         }
      } else if (!this.showAddModal) {
         return true;
      } else {
         float mw = 200.0F;
         mh = 95.0F;
         mx = ((float)this.width - mw) / 2.0F;
         my = ((float)this.height - mh) / 2.0F;
         pad = 12.0F;
         blockW = 8.0F;
         blockX = Fonts.sf_regular[18].getHeight();
         fieldW = mw - pad * 2.0F;
         fieldH = 21.0F;
         btnH = 21.0F;
         btnW = (fieldW - blockW) / 2.0F;
         fieldY = my + pad + blockX + blockW;
         btnY = fieldY + fieldH + blockW;
         if (button == 0) {
            if (mouseX >= (double)(mx + pad) && mouseX <= (double)(mx + pad + btnW) && mouseY >= (double)btnY && mouseY <= (double)(btnY + btnH)) {
               this.confirmAddModal();
               return true;
            }

            float genX = mx + pad + btnW + blockW;
            if (mouseX >= (double)genX && mouseX <= (double)(genX + btnW) && mouseY >= (double)btnY && mouseY <= (double)(btnY + btnH)) {
               String var10001 = RANDOM_NAMES[(new Random()).nextInt(RANDOM_NAMES.length)];
               this.modalInput = var10001 + (new Random()).nextInt(1500);
               return true;
            }

            if (mouseX >= (double)(mx + pad) && mouseX <= (double)(mx + mw - pad) && mouseY >= (double)fieldY && mouseY <= (double)(fieldY + fieldH)) {
               this.modalFieldFocused = true;
               return true;
            }

            if (mouseX < (double)mx || mouseX > (double)(mx + mw) || mouseY < (double)my || mouseY > (double)(my + mh)) {
               this.closeAddModal();
               return true;
            }
         }

         return true;
      }
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
      if (!this.showAddModal && !(this.modalAnim > 0.01F)) {
         this.scroll += (float)(delta * 2.0);
         return super.mouseScrolled(mouseX, mouseY, delta);
      } else {
         return true;
      }
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      if (this.showAddModal) {
         if (keyCode == 256) {
            this.closeAddModal();
            return true;
         } else if (keyCode != 257 && keyCode != 335) {
            if (keyCode == 259 && !this.modalInput.isEmpty()) {
               this.modalInput = this.modalInput.substring(0, this.modalInput.length() - 1);
               return true;
            } else {
               return true;
            }
         } else {
            this.confirmAddModal();
            return true;
         }
      } else if (keyCode == 256) {
         this.startFadeOut(new MainMenuScreen(true));
         return true;
      } else if (keyCode != 257 && keyCode != 335) {
         if (keyCode == 261) {
            this.deleteSelected();
            return true;
         } else {
            return super.keyPressed(keyCode, scanCode, modifiers);
         }
      } else {
         this.loginSelected();
         return true;
      }
   }

   public boolean charTyped(char c, int modifiers) {
      if (this.showAddModal && this.modalInput.length() < 32) {
         this.modalInput = this.modalInput + c;
         return true;
      } else {
         return super.charTyped(c, modifiers);
      }
   }

   private static final class IconUpdateResult {
      String lastB64;
      DynamicTexture texture;
   }
}

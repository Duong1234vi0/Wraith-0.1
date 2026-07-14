package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.ActiveRenderInfo;

public class EventTest implements Event {
   private final MatrixStack matrixStack;
   private final float partialTicks;
   private final ActiveRenderInfo renderInfo;

   public MatrixStack getMatrixStack() {
      return this.matrixStack;
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }

   public ActiveRenderInfo getRenderInfo() {
      return this.renderInfo;
   }

   public EventTest(MatrixStack matrixStack, float partialTicks, ActiveRenderInfo renderInfo) {
      this.matrixStack = matrixStack;
      this.partialTicks = partialTicks;
      this.renderInfo = renderInfo;
   }
}

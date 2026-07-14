package sky.core.events;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class EventLayerHead extends EventCancellable implements Event {
   private LivingEntity entity;
   private MatrixStack matrix;
   private EntityModel model;

   public LivingEntity getEntity() {
      return this.entity;
   }

   public MatrixStack getMatrix() {
      return this.matrix;
   }

   public EntityModel getModel() {
      return this.model;
   }

   public void setEntity(LivingEntity entity) {
      this.entity = entity;
   }

   public void setMatrix(MatrixStack matrix) {
      this.matrix = matrix;
   }

   public void setModel(EntityModel model) {
      this.model = model;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventLayerHead)) {
         return false;
      } else {
         EventLayerHead other = (EventLayerHead)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label47: {
               Object this$entity = this.getEntity();
               Object other$entity = other.getEntity();
               if (this$entity == null) {
                  if (other$entity == null) {
                     break label47;
                  }
               } else if (this$entity.equals(other$entity)) {
                  break label47;
               }

               return false;
            }

            Object this$matrix = this.getMatrix();
            Object other$matrix = other.getMatrix();
            if (this$matrix == null) {
               if (other$matrix != null) {
                  return false;
               }
            } else if (!this$matrix.equals(other$matrix)) {
               return false;
            }

            Object this$model = this.getModel();
            Object other$model = other.getModel();
            if (this$model == null) {
               if (other$model != null) {
                  return false;
               }
            } else if (!this$model.equals(other$model)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventLayerHead;
   }

   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $entity = this.getEntity();
      result = result * 59 + ($entity == null ? 43 : $entity.hashCode());
      Object $matrix = this.getMatrix();
      result = result * 59 + ($matrix == null ? 43 : $matrix.hashCode());
      Object $model = this.getModel();
      result = result * 59 + ($model == null ? 43 : $model.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = String.valueOf(this.getEntity());
      return "EventLayerHead(entity=" + var10000 + ", matrix=" + String.valueOf(this.getMatrix()) + ", model=" + String.valueOf(this.getModel()) + ")";
   }

   public EventLayerHead(LivingEntity entity, MatrixStack matrix, EntityModel model) {
      this.entity = entity;
      this.matrix = matrix;
      this.model = model;
   }
}

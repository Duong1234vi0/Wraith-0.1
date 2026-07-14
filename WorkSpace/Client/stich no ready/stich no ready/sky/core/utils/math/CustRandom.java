package sky.core.utils.math;

public class CustRandom {
   private static final int N = 624;
   private static final int M = 397;
   private static final int MATRIX_A = -1727483681;
   private static final int UPPER_MASK = Integer.MIN_VALUE;
   private static final int LOWER_MASK = Integer.MAX_VALUE;
   private int[] mt;
   private int index;
   private long lastGenerationTime;
   private long delay;
   private float lastGeneratedNumber;

   public CustRandom(long seed) {
      this.mt = new int[624];
      this.index = 625;
      this.lastGenerationTime = 0L;
      this.delay = 0L;
      this.lastGeneratedNumber = 0.0F;
      this.init(seed);
   }

   public CustRandom() {
      this(System.currentTimeMillis());
   }

   public void setDelay(long delay) {
      this.delay = delay;
   }

   public long getDelay() {
      return this.delay;
   }

   private void init(long seed) {
      this.mt[0] = (int)(seed & 4294967295L);

      for(this.index = 1; this.index < 624; ++this.index) {
         this.mt[this.index] = 1812433253 * (this.mt[this.index - 1] ^ this.mt[this.index - 1] >>> 30) + this.index;
         int[] var10000 = this.mt;
         int var10001 = this.index;
         var10000[var10001] &= -1;
      }

   }

   private int nextInt() {
      if (this.index >= 624) {
         this.twist();
      }

      int y = this.mt[this.index++];
      y ^= y >>> 11;
      y ^= y << 7 & -1658038656;
      y ^= y << 15 & -272236544;
      y ^= y >>> 18;
      return y;
   }

   public float nextFloat() {
      return (float)((long)this.nextInt() & 4294967295L) / 4.2949673E9F;
   }

   public float randomNumber(float firstNumber, float secondNumber, boolean isInt) {
      long currentTime = System.currentTimeMillis();
      if (currentTime - this.lastGenerationTime < this.delay) {
         return this.lastGeneratedNumber;
      } else {
         float result;
         if (firstNumber > secondNumber) {
            result = firstNumber;
            firstNumber = secondNumber;
            secondNumber = result;
         }

         do {
            float random = this.nextFloat();
            result = firstNumber + random * (secondNumber - firstNumber);
            if (isInt) {
               result = (float)((int)result);
            }
         } while(result == this.lastGeneratedNumber);

         this.lastGenerationTime = currentTime;
         this.lastGeneratedNumber = result;
         return result;
      }
   }

   private void twist() {
      for(int i = 0; i < 624; ++i) {
         int x = (this.mt[i] & Integer.MIN_VALUE) + (this.mt[(i + 1) % 624] & Integer.MAX_VALUE);
         int xA = x >>> 1;
         if (x % 2 != 0) {
            xA ^= -1727483681;
         }

         this.mt[i] = this.mt[(i + 397) % 624] ^ xA;
      }

      this.index = 0;
   }
}

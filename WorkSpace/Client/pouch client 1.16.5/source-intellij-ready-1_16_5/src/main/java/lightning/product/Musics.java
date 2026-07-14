/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Music;

public class Musics {
    public static final Music n_1700_B = new Music(SoundEvents.AutoJoiner, 20, 600, true);
    public static final Music J_1907_R = new Music(SoundEvents.NoSlow, 12000, 24000, false);
    public static final Music R_4764_Y = new Music(SoundEvents.NoWeb, 0, 0, true);
    public static final Music G_564_y = new Music(SoundEvents.AutoEat, 0, 0, true);
    public static final Music P_1922_E = new Music(SoundEvents.AutoFarm, 6000, 24000, true);
    public static final Music u_1723_Y = Musics.n_1700_B(SoundEvents.AutoSoup);
    public static final Music v_4262_N = Musics.n_1700_B(SoundEvents.AutoFish);

    public static Music n_1700_B(SoundEvent soundEvent) {
        return new Music(soundEvent, 12000, 24000, false);
    }
}




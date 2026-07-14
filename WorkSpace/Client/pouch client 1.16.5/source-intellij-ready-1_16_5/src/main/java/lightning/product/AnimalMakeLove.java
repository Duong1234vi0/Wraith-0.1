/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Optional;
import lightning.product.AgableMob;
import lightning.product.E_4668_a;
import lightning.product.Animal;
import lightning.product.N_4263_v;
import lightning.product.S_50_d;
import lightning.product.a_3236_r;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;

public class AnimalMakeLove
extends Behavior<Animal> {
    private final t_5_h<? extends Animal> n_1700_B;
    private final float R_4764_Y;
    private long G_564_y;

    public AnimalMakeLove(t_5_h<? extends Animal> breedTarget, float speed) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.w_1484_f, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.multiplayerClientSuggestionProvider, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.R_4764_Y)), 325);
        this.n_1700_B = breedTarget;
        this.R_4764_Y = speed;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, Animal owner) {
        return owner.P_2295_B() && this.R_4764_Y(owner).isPresent();
    }

    protected void n_1700_B(e_3591_l worldIn, Animal entityIn, long gameTimeIn) {
        Animal animalentity = this.R_4764_Y(entityIn).get();
        entityIn.y_1945_D().n_1700_B(MemoryModuleType.multiplayerClientSuggestionProvider, animalentity);
        animalentity.y_1945_D().n_1700_B(MemoryModuleType.multiplayerClientSuggestionProvider, entityIn);
        a_3236_r.n_1700_B((r_4811_B)entityIn, (r_4811_B)animalentity, this.R_4764_Y);
        int i = 275 + entityIn.M_3508_C().nextInt(50);
        this.G_564_y = gameTimeIn + (long)i;
    }

    protected boolean J_1907_R(e_3591_l worldIn, Animal entityIn, long gameTimeIn) {
        if (!this.J_1907_R(entityIn)) {
            return false;
        }
        Animal animalentity = this.n_1700_B(entityIn);
        return animalentity.RealmsLongRunningMcoTaskScreen() && entityIn.n_1700_B(animalentity) && a_3236_r.n_1700_B(entityIn.y_1945_D(), (r_4811_B)animalentity) && gameTimeIn <= this.G_564_y;
    }

    @Override
    protected void R_4764_Y(e_3591_l worldIn, Animal owner, long gameTime) {
        Animal animalentity = this.n_1700_B(owner);
        a_3236_r.n_1700_B((r_4811_B)owner, (r_4811_B)animalentity, this.R_4764_Y);
        if (owner.n_1700_B((N_4263_v)animalentity, 3.0) && gameTime >= this.G_564_y) {
            owner.n_1700_B(worldIn, animalentity);
            owner.y_1945_D().J_1907_R(MemoryModuleType.multiplayerClientSuggestionProvider);
            animalentity.y_1945_D().J_1907_R(MemoryModuleType.multiplayerClientSuggestionProvider);
        }
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, Animal entityIn, long gameTimeIn) {
        entityIn.y_1945_D().J_1907_R(MemoryModuleType.multiplayerClientSuggestionProvider);
        entityIn.y_1945_D().J_1907_R(MemoryModuleType.P_4830_p);
        entityIn.y_1945_D().J_1907_R(MemoryModuleType.h_1847_R);
        this.G_564_y = 0L;
    }

    private Animal n_1700_B(Animal animal) {
        return (Animal)animal.y_1945_D().R_4764_Y(MemoryModuleType.multiplayerClientSuggestionProvider).get();
    }

    private boolean J_1907_R(Animal animal) {
        E_4668_a<AgableMob> brain = animal.y_1945_D();
        return brain.n_1700_B(MemoryModuleType.multiplayerClientSuggestionProvider) && brain.R_4764_Y(MemoryModuleType.multiplayerClientSuggestionProvider).get().f_4016_n() == this.n_1700_B;
    }

    private Optional<? extends Animal> R_4764_Y(Animal animal) {
        return animal.y_1945_D().R_4764_Y(MemoryModuleType.w_1484_f).get().stream().filter(livingEntity -> livingEntity.f_4016_n() == this.n_1700_B).map(breedableEntities -> (Animal)breedableEntities).filter(animal::n_1700_B).findFirst();
    }

    @Override
    protected /* synthetic */ boolean n_1700_B(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        return this.J_1907_R(e_3591_l2, (Animal)r_4811_B2, l);
    }

    @Override
    protected /* synthetic */ void J_1907_R(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.G_564_y(e_3591_l2, (Animal)r_4811_B2, l);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (Animal)r_4811_B2, l);
    }
}



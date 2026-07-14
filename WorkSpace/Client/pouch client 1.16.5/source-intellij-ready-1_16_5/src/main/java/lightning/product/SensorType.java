/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Supplier;
import lightning.product.VillagerHostilesSensor;
import lightning.product.PlayerSensor;
import lightning.product.NearestItemSensor;
import lightning.product.DummySensor;
import lightning.product.GolemSensor;
import lightning.product.V_3137_a;
import lightning.product.HoglinSpecificSensor;
import lightning.product.Sensor;
import lightning.product.VillagerBabiesSensor;
import lightning.product.d_2322_c;
import lightning.product.SecondaryPoiSensor;
import lightning.product.g_2336_b;
import lightning.product.j_4943_O;
import lightning.product.NearestBedSensor;
import lightning.product.AdultSensor;
import lightning.product.NearestLivingEntitySensor;
import lightning.product.HurtBySensor;

public class SensorType<U extends Sensor<?>> {
    public static final SensorType<DummySensor> n_1700_B = SensorType.n_1700_B("dummy", DummySensor::new);
    public static final SensorType<NearestItemSensor> J_1907_R = SensorType.n_1700_B("nearest_items", NearestItemSensor::new);
    public static final SensorType<NearestLivingEntitySensor> R_4764_Y = SensorType.n_1700_B("nearest_living_entities", NearestLivingEntitySensor::new);
    public static final SensorType<PlayerSensor> G_564_y = SensorType.n_1700_B("nearest_players", PlayerSensor::new);
    public static final SensorType<NearestBedSensor> P_1922_E = SensorType.n_1700_B("nearest_bed", NearestBedSensor::new);
    public static final SensorType<HurtBySensor> u_1723_Y = SensorType.n_1700_B("hurt_by", HurtBySensor::new);
    public static final SensorType<VillagerHostilesSensor> v_4262_N = SensorType.n_1700_B("villager_hostiles", VillagerHostilesSensor::new);
    public static final SensorType<VillagerBabiesSensor> w_1484_f = SensorType.n_1700_B("villager_babies", VillagerBabiesSensor::new);
    public static final SensorType<SecondaryPoiSensor> t_148_a = SensorType.n_1700_B("secondary_pois", SecondaryPoiSensor::new);
    public static final SensorType<GolemSensor> s_956_w = SensorType.n_1700_B("golem_detected", GolemSensor::new);
    public static final SensorType<d_2322_c> u_2550_I = SensorType.n_1700_B("piglin_specific_sensor", d_2322_c::new);
    public static final SensorType<j_4943_O> M_588_G = SensorType.n_1700_B("piglin_brute_specific_sensor", j_4943_O::new);
    public static final SensorType<HoglinSpecificSensor> P_4830_p = SensorType.n_1700_B("hoglin_specific_sensor", HoglinSpecificSensor::new);
    public static final SensorType<AdultSensor> h_1847_R = SensorType.n_1700_B("nearest_adult", AdultSensor::new);
    private final Supplier<U> Q_4569_t;

    private SensorType(Supplier<U> sensorSupplier) {
        this.Q_4569_t = sensorSupplier;
    }

    public U n_1700_B() {
        return (U)((Sensor)this.Q_4569_t.get());
    }

    private static <U extends Sensor<?>> SensorType<U> n_1700_B(String key, Supplier<U> sensorSupplier) {
        return V_3137_a.n_1700_B(V_3137_a.Ping, new g_2336_b(key), new SensorType<U>(sensorSupplier));
    }
}



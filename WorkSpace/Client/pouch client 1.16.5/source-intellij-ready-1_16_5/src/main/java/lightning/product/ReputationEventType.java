/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public interface ReputationEventType {
    public static final ReputationEventType n_1700_B = ReputationEventType.n_1700_B("zombie_villager_cured");
    public static final ReputationEventType J_1907_R = ReputationEventType.n_1700_B("golem_killed");
    public static final ReputationEventType R_4764_Y = ReputationEventType.n_1700_B("villager_hurt");
    public static final ReputationEventType G_564_y = ReputationEventType.n_1700_B("villager_killed");
    public static final ReputationEventType P_1922_E = ReputationEventType.n_1700_B("trade");

    public static ReputationEventType n_1700_B(final String key) {
        return new ReputationEventType(){

            public String toString() {
                return key;
            }
        };
    }
}



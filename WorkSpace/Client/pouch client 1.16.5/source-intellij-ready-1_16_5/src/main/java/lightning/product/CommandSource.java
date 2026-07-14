/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.UUID;
import lightning.product.x_282_a;

public interface CommandSource {
    public static final CommandSource T_3594_S = new CommandSource(){

        @Override
        public void n_1700_B(x_282_a component, UUID senderUUID) {
        }

        @Override
        public boolean O_508_d() {
            return false;
        }

        @Override
        public boolean r_715_M() {
            return false;
        }

        @Override
        public boolean A_1038_p() {
            return false;
        }
    };

    public void n_1700_B(x_282_a var1, UUID var2);

    public boolean O_508_d();

    public boolean r_715_M();

    public boolean A_1038_p();
}



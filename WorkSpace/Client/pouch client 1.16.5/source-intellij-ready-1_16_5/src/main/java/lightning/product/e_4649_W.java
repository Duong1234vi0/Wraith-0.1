/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Vector;
import javax.swing.JList;
import net.minecraft.server.G_564_y;

public class e_4649_W
extends JList<String> {
    private final G_564_y n_1700_B;
    private int J_1907_R;

    public e_4649_W(G_564_y server) {
        this.n_1700_B = server;
        server.G_564_y(this::n_1700_B);
    }

    public void n_1700_B() {
        if (this.J_1907_R++ % 20 == 0) {
            Vector<String> vector = new Vector<String>();
            for (int i = 0; i < this.n_1700_B.p_178_J().w_1457_N().size(); ++i) {
                vector.add(this.n_1700_B.p_178_J().w_1457_N().get(i).y_4642_Y().getName());
            }
            this.setListData(vector);
        }
    }
}


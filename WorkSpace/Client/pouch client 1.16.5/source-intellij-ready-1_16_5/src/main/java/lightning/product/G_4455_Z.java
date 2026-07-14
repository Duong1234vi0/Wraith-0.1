/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.util.QueueLogAppender
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.util.QueueLogAppender;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import lightning.product.V_4604_M;
import lightning.product.e_4649_W;
import lightning.product.o_1543_q;
import lightning.product.DefaultUncaughtExceptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class G_4455_Z
extends JComponent {
    private static final Font n_1700_B = new Font("Monospaced", 0, 12);
    private static final Logger J_1907_R = LogManager.getLogger();
    private final V_4604_M R_4764_Y;
    private Thread G_564_y;
    private final Collection<Runnable> P_1922_E = Lists.newArrayList();
    private final AtomicBoolean u_1723_Y = new AtomicBoolean();

    public static G_4455_Z n_1700_B(final V_4604_M p_219048_0_) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception exception) {
            // empty catch block
        }
        final JFrame jframe = new JFrame("Minecraft server");
        final G_4455_Z minecraftservergui = new G_4455_Z(p_219048_0_);
        jframe.setDefaultCloseOperation(2);
        jframe.add(minecraftservergui);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        jframe.addWindowListener(new WindowAdapter(){

            public void n_1700_B(WindowEvent p_windowClosing_1_) {
                if (!minecraftservergui.u_1723_Y.getAndSet(true)) {
                    jframe.setTitle("Minecraft server - shutting down!");
                    p_219048_0_.n_1700_B(true);
                    minecraftservergui.u_1723_Y();
                }
            }
        });
        minecraftservergui.n_1700_B(jframe::dispose);
        minecraftservergui.n_1700_B();
        return minecraftservergui;
    }

    private G_4455_Z(V_4604_M serverIn) {
        this.R_4764_Y = serverIn;
        this.setPreferredSize(new Dimension(854, 480));
        this.setLayout(new BorderLayout());
        try {
            this.add((Component)this.P_1922_E(), "Center");
            this.add((Component)this.R_4764_Y(), "West");
        }
        catch (Exception exception) {
            J_1907_R.error("Couldn't build server GUI", (Throwable)exception);
        }
    }

    public void n_1700_B(Runnable p_219045_1_) {
        this.P_1922_E.add(p_219045_1_);
    }

    private JComponent R_4764_Y() {
        JPanel jpanel = new JPanel(new BorderLayout());
        o_1543_q statscomponent = new o_1543_q(this.R_4764_Y);
        this.P_1922_E.add(statscomponent::n_1700_B);
        jpanel.add((Component)statscomponent, "North");
        jpanel.add((Component)this.G_564_y(), "Center");
        jpanel.setBorder(new TitledBorder(new EtchedBorder(), "Stats"));
        return jpanel;
    }

    private JComponent G_564_y() {
        e_4649_W jlist = new e_4649_W(this.R_4764_Y);
        JScrollPane jscrollpane = new JScrollPane(jlist, 22, 30);
        jscrollpane.setBorder(new TitledBorder(new EtchedBorder(), "Players"));
        return jscrollpane;
    }

    private JComponent P_1922_E() {
        JPanel jpanel = new JPanel(new BorderLayout());
        JTextArea jtextarea = new JTextArea();
        JScrollPane jscrollpane = new JScrollPane(jtextarea, 22, 30);
        jtextarea.setEditable(false);
        jtextarea.setFont(n_1700_B);
        JTextField jtextfield = new JTextField();
        jtextfield.addActionListener(p_210465_2_ -> {
            String s = jtextfield.getText().trim();
            if (!s.isEmpty()) {
                this.R_4764_Y.n_1700_B(s, this.R_4764_Y.R_3908_n());
            }
            jtextfield.setText("");
        });
        jtextarea.addFocusListener(new FocusAdapter(this){

            public void n_1700_B(FocusEvent p_focusGained_1_) {
            }
        });
        jpanel.add((Component)jscrollpane, "Center");
        jpanel.add((Component)jtextfield, "South");
        jpanel.setBorder(new TitledBorder(new EtchedBorder(), "Log and chat"));
        this.G_564_y = new Thread(() -> {
            String s;
            while ((s = QueueLogAppender.getNextLogEvent((String)"ServerGuiConsole")) != null) {
                this.n_1700_B(jtextarea, jscrollpane, s);
            }
        });
        this.G_564_y.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(J_1907_R));
        this.G_564_y.setDaemon(true);
        return jpanel;
    }

    public void n_1700_B() {
        this.G_564_y.start();
    }

    public void J_1907_R() {
        if (!this.u_1723_Y.getAndSet(true)) {
            this.u_1723_Y();
        }
    }

    private void u_1723_Y() {
        this.P_1922_E.forEach(Runnable::run);
    }

    public void n_1700_B(JTextArea textArea, JScrollPane scrollPane, String line) {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(() -> this.n_1700_B(textArea, scrollPane, line));
        } else {
            Document document = textArea.getDocument();
            JScrollBar jscrollbar = scrollPane.getVerticalScrollBar();
            boolean flag = false;
            if (scrollPane.getViewport().getView() == textArea) {
                flag = (double)jscrollbar.getValue() + jscrollbar.getSize().getHeight() + (double)(n_1700_B.getSize() * 4) > (double)jscrollbar.getMaximum();
            }
            try {
                document.insertString(document.getLength(), line, null);
            }
            catch (BadLocationException badLocationException) {
                // empty catch block
            }
            if (flag) {
                jscrollbar.setValue(Integer.MAX_VALUE);
            }
        }
    }
}



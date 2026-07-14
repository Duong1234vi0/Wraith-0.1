/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;

public class m_3828_C {
    private final String n_1700_B;
    private String J_1907_R;
    private String R_4764_Y;
    private String G_564_y;
    private boolean P_1922_E;
    private List<J_1907_R> u_1723_Y = new ArrayList<J_1907_R>();
    private List<n_1700_B> v_4262_N = new ArrayList<n_1700_B>();

    public m_3828_C(String url) {
        this.n_1700_B = url;
    }

    public void n_1700_B(String content) {
        this.J_1907_R = content;
    }

    public void J_1907_R(String username) {
        this.R_4764_Y = username;
    }

    public void R_4764_Y(String avatarUrl) {
        this.G_564_y = avatarUrl;
    }

    public void n_1700_B(boolean tts) {
        this.P_1922_E = tts;
    }

    public void n_1700_B(J_1907_R embed) {
        this.u_1723_Y.add(embed);
    }

    public void n_1700_B(n_1700_B component) {
        this.v_4262_N.add(component);
    }

    public void n_1700_B() throws IOException {
        if (this.J_1907_R == null && this.u_1723_Y.isEmpty()) {
            throw new IllegalArgumentException("Set content or add at least one EmbedObject");
        }
        R_4764_Y json = new R_4764_Y(this);
        json.n_1700_B("content", this.J_1907_R);
        json.n_1700_B("username", this.R_4764_Y);
        json.n_1700_B("avatar_url", this.G_564_y);
        json.n_1700_B("tts", this.P_1922_E);
        if (!this.u_1723_Y.isEmpty()) {
            ArrayList<R_4764_Y> embedObjects = new ArrayList<R_4764_Y>();
            for (J_1907_R embed : this.u_1723_Y) {
                R_4764_Y jsonEmbed = new R_4764_Y(this);
                jsonEmbed.n_1700_B("title", embed.n_1700_B());
                jsonEmbed.n_1700_B("description", embed.J_1907_R());
                jsonEmbed.n_1700_B("url", embed.R_4764_Y());
                if (embed.G_564_y() != null) {
                    Color color = embed.G_564_y();
                    int rgb = color.getRed();
                    rgb = (rgb << 8) + color.getGreen();
                    rgb = (rgb << 8) + color.getBlue();
                    jsonEmbed.n_1700_B("color", rgb);
                }
                J_1907_R.R_4764_Y footer = embed.P_1922_E();
                J_1907_R.G_564_y image = embed.v_4262_N();
                J_1907_R.P_1922_E thumbnail = embed.u_1723_Y();
                J_1907_R.n_1700_B author = embed.w_1484_f();
                List<J_1907_R.J_1907_R> fields = embed.t_148_a();
                if (footer != null) {
                    R_4764_Y jsonFooter = new R_4764_Y(this);
                    jsonFooter.n_1700_B("text", footer.n_1700_B());
                    jsonFooter.n_1700_B("icon_url", footer.J_1907_R());
                    jsonEmbed.n_1700_B("footer", jsonFooter);
                }
                if (image != null) {
                    R_4764_Y jsonImage = new R_4764_Y(this);
                    jsonImage.n_1700_B("url", image.n_1700_B());
                    jsonEmbed.n_1700_B("image", jsonImage);
                }
                if (thumbnail != null) {
                    R_4764_Y jsonThumbnail = new R_4764_Y(this);
                    jsonThumbnail.n_1700_B("url", thumbnail.n_1700_B());
                    jsonEmbed.n_1700_B("thumbnail", jsonThumbnail);
                }
                if (author != null) {
                    R_4764_Y jsonAuthor = new R_4764_Y(this);
                    jsonAuthor.n_1700_B("name", author.n_1700_B());
                    jsonAuthor.n_1700_B("url", author.J_1907_R());
                    jsonAuthor.n_1700_B("icon_url", author.R_4764_Y());
                    jsonEmbed.n_1700_B("author", jsonAuthor);
                }
                ArrayList<R_4764_Y> jsonFields = new ArrayList<R_4764_Y>();
                for (J_1907_R.J_1907_R field : fields) {
                    R_4764_Y jsonField = new R_4764_Y(this);
                    jsonField.n_1700_B("name", field.n_1700_B());
                    jsonField.n_1700_B("value", field.J_1907_R());
                    jsonField.n_1700_B("inline", field.R_4764_Y());
                    jsonFields.add(jsonField);
                }
                jsonEmbed.n_1700_B("fields", jsonFields.toArray());
                embedObjects.add(jsonEmbed);
            }
            json.n_1700_B("embeds", embedObjects.toArray());
        }
        if (!this.v_4262_N.isEmpty()) {
            ArrayList<R_4764_Y> componentObjects = new ArrayList<R_4764_Y>();
            R_4764_Y actionRow = new R_4764_Y(this);
            actionRow.n_1700_B("type", 1);
            ArrayList<R_4764_Y> buttons = new ArrayList<R_4764_Y>();
            for (n_1700_B component : this.v_4262_N) {
                R_4764_Y button = new R_4764_Y(this);
                button.n_1700_B("type", 2);
                button.n_1700_B("style", component.n_1700_B());
                button.n_1700_B("label", component.J_1907_R());
                if (component.R_4764_Y() != null) {
                    button.n_1700_B("url", component.R_4764_Y());
                }
                if (component.G_564_y() != null) {
                    button.n_1700_B("custom_id", component.G_564_y());
                }
                buttons.add(button);
            }
            actionRow.n_1700_B("components", buttons.toArray());
            componentObjects.add(actionRow);
            json.n_1700_B("components", componentObjects.toArray());
        }
        URL url = new URL(this.n_1700_B);
        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
        connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        OutputStream stream = connection.getOutputStream();
        stream.write(json.toString().getBytes());
        stream.flush();
        stream.close();
        connection.getInputStream().close();
        connection.disconnect();
    }

    private class R_4764_Y {
        private final HashMap<String, Object> n_1700_B = new HashMap();

        private R_4764_Y(m_3828_C m_3828_C2) {
        }

        void n_1700_B(String key, Object value) {
            if (value != null) {
                this.n_1700_B.put(key, value);
            }
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            Set<Map.Entry<String, Object>> entrySet = this.n_1700_B.entrySet();
            builder.append("{");
            int i = 0;
            for (Map.Entry<String, Object> entry : entrySet) {
                Object val = entry.getValue();
                builder.append(this.n_1700_B(entry.getKey())).append(":");
                if (val instanceof String) {
                    builder.append(this.n_1700_B(String.valueOf(val)));
                } else if (val instanceof Integer) {
                    builder.append(Integer.valueOf(String.valueOf(val)));
                } else if (val instanceof Boolean) {
                    builder.append(val);
                } else if (val instanceof R_4764_Y) {
                    builder.append(val.toString());
                } else if (val.getClass().isArray()) {
                    builder.append("[");
                    int len = Array.getLength(val);
                    for (int j = 0; j < len; ++j) {
                        builder.append(Array.get(val, j).toString()).append(j != len - 1 ? "," : "");
                    }
                    builder.append("]");
                }
                builder.append(++i == entrySet.size() ? "}" : ",");
            }
            return builder.toString();
        }

        private String n_1700_B(String string) {
            return "\"" + string + "\"";
        }
    }

    public static class lightning.product.m_3828_C$J_1907_R {
        private String n_1700_B;
        private String J_1907_R;
        private String R_4764_Y;
        private Color G_564_y;
        private R_4764_Y P_1922_E;
        private P_1922_E u_1723_Y;
        private G_564_y v_4262_N;
        private n_1700_B w_1484_f;
        private List<J_1907_R> t_148_a = new ArrayList<J_1907_R>();

        public String n_1700_B() {
            return this.n_1700_B;
        }

        public String J_1907_R() {
            return this.J_1907_R;
        }

        public String R_4764_Y() {
            return this.R_4764_Y;
        }

        public Color G_564_y() {
            return this.G_564_y;
        }

        public R_4764_Y P_1922_E() {
            return this.P_1922_E;
        }

        public P_1922_E u_1723_Y() {
            return this.u_1723_Y;
        }

        public G_564_y v_4262_N() {
            return this.v_4262_N;
        }

        public n_1700_B w_1484_f() {
            return this.w_1484_f;
        }

        public List<J_1907_R> t_148_a() {
            return this.t_148_a;
        }

        public lightning.product.m_3828_C$J_1907_R n_1700_B(String title) {
            this.n_1700_B = title;
            return this;
        }

        public lightning.product.m_3828_C$J_1907_R J_1907_R(String description) {
            this.J_1907_R = description;
            return this;
        }

        public lightning.product.m_3828_C$J_1907_R R_4764_Y(String url) {
            this.R_4764_Y = url;
            return this;
        }

        public lightning.product.m_3828_C$J_1907_R n_1700_B(Color color) {
            this.G_564_y = color;
            return this;
        }

        public lightning.product.m_3828_C$J_1907_R n_1700_B(String text, String icon) {
            this.P_1922_E = new R_4764_Y(this, text, icon);
            return this;
        }

        public lightning.product.m_3828_C$J_1907_R G_564_y(String url) {
            this.u_1723_Y = new P_1922_E(this, url);
            return this;
        }

        public lightning.product.m_3828_C$J_1907_R P_1922_E(String url) {
            this.v_4262_N = new G_564_y(this, url);
            return this;
        }

        public lightning.product.m_3828_C$J_1907_R n_1700_B(String name, String url, String icon) {
            this.w_1484_f = new n_1700_B(this, name, url, icon);
            return this;
        }

        public lightning.product.m_3828_C$J_1907_R n_1700_B(String name, String value, boolean inline) {
            this.t_148_a.add(new J_1907_R(this, name, value, inline));
            return this;
        }

        private class R_4764_Y {
            private String n_1700_B;
            private String J_1907_R;

            private R_4764_Y(lightning.product.m_3828_C$J_1907_R j_1907_R, String text, String iconUrl) {
                this.n_1700_B = text;
                this.J_1907_R = iconUrl;
            }

            private String n_1700_B() {
                return this.n_1700_B;
            }

            private String J_1907_R() {
                return this.J_1907_R;
            }
        }

        private class P_1922_E {
            private String n_1700_B;

            private P_1922_E(lightning.product.m_3828_C$J_1907_R j_1907_R, String url) {
                this.n_1700_B = url;
            }

            private String n_1700_B() {
                return this.n_1700_B;
            }
        }

        private class G_564_y {
            private String n_1700_B;

            private G_564_y(lightning.product.m_3828_C$J_1907_R j_1907_R, String url) {
                this.n_1700_B = url;
            }

            private String n_1700_B() {
                return this.n_1700_B;
            }
        }

        private class n_1700_B {
            private String n_1700_B;
            private String J_1907_R;
            private String R_4764_Y;

            private n_1700_B(lightning.product.m_3828_C$J_1907_R j_1907_R, String name, String url, String iconUrl) {
                this.n_1700_B = name;
                this.J_1907_R = url;
                this.R_4764_Y = iconUrl;
            }

            private String n_1700_B() {
                return this.n_1700_B;
            }

            private String J_1907_R() {
                return this.J_1907_R;
            }

            private String R_4764_Y() {
                return this.R_4764_Y;
            }
        }

        private class J_1907_R {
            private String n_1700_B;
            private String J_1907_R;
            private boolean R_4764_Y;

            private J_1907_R(lightning.product.m_3828_C$J_1907_R j_1907_R, String name, String value, boolean inline) {
                this.n_1700_B = name;
                this.J_1907_R = value;
                this.R_4764_Y = inline;
            }

            private String n_1700_B() {
                return this.n_1700_B;
            }

            private String J_1907_R() {
                return this.J_1907_R;
            }

            private boolean R_4764_Y() {
                return this.R_4764_Y;
            }
        }
    }

    public static class n_1700_B {
        private int n_1700_B;
        private String J_1907_R;
        private String R_4764_Y;
        private String G_564_y;

        public n_1700_B(int style, String label, String url, String customId) {
            this.n_1700_B = style;
            this.J_1907_R = label;
            this.R_4764_Y = url;
            this.G_564_y = customId;
        }

        public int n_1700_B() {
            return this.n_1700_B;
        }

        public String J_1907_R() {
            return this.J_1907_R;
        }

        public String R_4764_Y() {
            return this.R_4764_Y;
        }

        public String G_564_y() {
            return this.G_564_y;
        }

        public static n_1700_B n_1700_B(int style, String label, String url, String customId) {
            return new n_1700_B(style, label, url, customId);
        }
    }
}


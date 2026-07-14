package ua.babka.integration.discord.connection;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.function.Consumer;
import ua.babka.integration.discord.connection.UnixConnection;
import ua.babka.integration.discord.connection.WinConnection;
import ua.babka.integration.discord.packet.C2SPacket;
import ua.babka.integration.discord.packet.S2CPacket;

public abstract class Connection {
    private static final String[] UNIX_TEMP_PATHS = new String[]{"XDG_RUNTIME_DIR", "TMPDIR", "TMP", "TEMP"};
    protected final Consumer<S2CPacket> callback;

    public Connection(Consumer<S2CPacket> onPacketCallback) {
        this.callback = onPacketCallback;
    }

    public static Connection open(Consumer<S2CPacket> callback) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            for (int i2 = 0; i2 < 10; ++i2) {
                try {
                    return new WinConnection("\\\\.\\pipe\\discord-ipc-" + i2, callback);
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                    continue;
                }
            }
        } else {
            String tempPath;
            Object name = null;
            String[] e2 = UNIX_TEMP_PATHS;
            int n2 = e2.length;
            for (int i3 = 0; i3 < n2 && (name = System.getenv(tempPath = e2[i3])) == null; ++i3) {
            }
            if (name == null) {
                name = "/tmp";
            }
            name = (String)name + "/discord-ipc-";
            for (int i4 = 0; i4 < 10; ++i4) {
                try {
                    return new UnixConnection((String)name + i4, callback);
                }
                catch (IOException iOException) {
                    continue;
                }
            }
        }
        return null;
    }

    public void write(C2SPacket packet) {
        this.write(C2SPacket.writeToBuffer(packet));
    }

    protected abstract void write(ByteBuffer var1);

    public abstract void close();
}


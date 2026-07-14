package ua.babka.integration.discord.connection;

import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.function.Consumer;
import ua.babka.integration.discord.connection.Connection;
import ua.babka.integration.discord.packet.Opcode;
import ua.babka.integration.discord.packet.S2CPacket;

public class WinConnection
extends Connection {
    private final RandomAccessFile raf;

    public WinConnection(String name, Consumer<S2CPacket> callback) throws IOException {
        super(callback);
        this.raf = new RandomAccessFile(name, "rw");
        Thread thread = new Thread(this::run);
        thread.setName("Discord IPC - Read thread");
        thread.start();
    }

    @Override
    protected void write(ByteBuffer buffer) {
        try {
            this.raf.write(buffer.array());
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void run() {
        ByteBuffer intB = ByteBuffer.allocate(4);
        try {
            while (true) {
                this.readFully(intB);
                Opcode opcode = Opcode.valueOf(Integer.reverseBytes(intB.getInt(0)));
                this.readFully(intB);
                int length = Integer.reverseBytes(intB.getInt(0));
                ByteBuffer dataB = ByteBuffer.allocate(length);
                this.readFully(dataB);
                String data = Charset.defaultCharset().decode(dataB.rewind()).toString();
                this.callback.accept(opcode.toPacketFunction.apply(JsonParser.parseString((String)data).getAsJsonObject()));
            }
        }
        catch (Exception exception) {
            return;
        }
    }

    private void readFully(ByteBuffer buffer) throws IOException {
        buffer.rewind();
        while (this.raf.length() < (long)buffer.remaining()) {
            Thread.onSpinWait();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        while (buffer.hasRemaining()) {
            this.raf.getChannel().read(buffer);
        }
    }

    @Override
    public void close() {
        try {
            this.raf.close();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}


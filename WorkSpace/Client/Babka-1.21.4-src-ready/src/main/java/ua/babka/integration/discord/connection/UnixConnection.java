package ua.babka.integration.discord.connection;

import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.UnixDomainSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.function.Consumer;
import ua.babka.integration.discord.connection.Connection;
import ua.babka.integration.discord.packet.Opcode;
import ua.babka.integration.discord.packet.S2CPacket;

public class UnixConnection
extends Connection {
    private final Selector s = Selector.open();
    private final SocketChannel sc;

    public UnixConnection(String name, Consumer<S2CPacket> callback) throws IOException {
        super(callback);
        this.sc = SocketChannel.open(UnixDomainSocketAddress.of(name));
        this.sc.configureBlocking(false);
        this.sc.register(this.s, 1);
        Thread thread = new Thread(this::run);
        thread.setName("Discord IPC - Read thread");
        thread.start();
    }

    private void run() {
        State state = State.Opcode;
        ByteBuffer intB = ByteBuffer.allocate(4);
        Buffer dataB = null;
        Opcode opcode = null;
        try {
            block7: while (true) {
                this.s.select();
                switch (state.ordinal()) {
                    case 0: {
                        this.sc.read(intB);
                        if (intB.hasRemaining()) continue block7;
                        opcode = Opcode.valueOf(Integer.reverseBytes(intB.getInt(0)));
                        state = State.Length;
                        intB.rewind();
                        break;
                    }
                    case 1: {
                        this.sc.read(intB);
                        if (intB.hasRemaining()) continue block7;
                        dataB = ByteBuffer.allocate(Integer.reverseBytes(intB.getInt(0)));
                        state = State.Data;
                        intB.rewind();
                        break;
                    }
                    case 2: {
                        this.sc.read((ByteBuffer)dataB);
                        if (dataB.hasRemaining()) continue block7;
                        String data = Charset.defaultCharset().decode(((ByteBuffer)dataB).rewind()).toString();
                        this.callback.accept(opcode.toPacketFunction.apply(JsonParser.parseString((String)data).getAsJsonObject()));
                        dataB = null;
                        state = State.Opcode;
                    }
                }
            }
        }
        catch (Exception exception) {
            return;
        }
    }

    @Override
    protected void write(ByteBuffer buffer) {
        try {
            this.sc.write(buffer);
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            this.s.close();
            this.sc.close();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private static enum State {
        Opcode,
        Length,
        Data;

    }
}


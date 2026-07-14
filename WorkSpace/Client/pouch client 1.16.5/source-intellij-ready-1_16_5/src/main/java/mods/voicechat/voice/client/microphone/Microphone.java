/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.client.microphone;

import mods.voicechat.voice.client.MicrophoneException;

public interface Microphone {
    public void open() throws MicrophoneException;

    public void start();

    public void stop();

    public void close();

    public boolean isOpen();

    public boolean isStarted();

    public int available();

    public short[] read();
}


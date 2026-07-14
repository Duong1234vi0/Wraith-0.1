/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.http;

import lightning.product.MinecraftClient;
import net.optifine.http.HttpPipeline;
import net.optifine.http.IFileDownloadListener;

public class FileDownloadThread
extends Thread {
    private String urlString = null;
    private IFileDownloadListener listener = null;

    public FileDownloadThread(String urlString, IFileDownloadListener listener) {
        this.urlString = urlString;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            byte[] abyte = HttpPipeline.get(this.urlString, MinecraftClient.A_4115_X().d_2461_k());
            this.listener.fileDownloadFinished(this.urlString, abyte, null);
        }
        catch (Exception exception) {
            this.listener.fileDownloadFinished(this.urlString, null, exception);
        }
    }

    public String getUrlString() {
        return this.urlString;
    }

    public IFileDownloadListener getListener() {
        return this.listener;
    }
}



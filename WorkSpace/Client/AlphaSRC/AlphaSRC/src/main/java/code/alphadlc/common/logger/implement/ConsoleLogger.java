/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package code.alphadlc.common.logger.implement;

import code.alphadlc.common.logger.Logger;
import net.minecraft.class_2561;
import org.apache.logging.log4j.LogManager;

public class ConsoleLogger
implements Logger {
    private final org.apache.logging.log4j.Logger logger = LogManager.getLogger((String)"AlphaDLC");

    @Override
    public void log(Object message) {
        this.logger.info("[AlphaDLC] {}", message);
    }

    @Override
    public void minecraftLog(class_2561 ... components) {
    }
}

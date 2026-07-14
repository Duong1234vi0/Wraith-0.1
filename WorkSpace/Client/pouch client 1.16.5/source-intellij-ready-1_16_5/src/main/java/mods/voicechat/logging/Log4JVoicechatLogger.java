/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.apache.logging.log4j.core.Appender
 *  org.apache.logging.log4j.core.Logger
 *  org.apache.logging.log4j.core.config.Configurator
 */
package mods.voicechat.logging;

import java.util.Map;
import mods.voicechat.Voicechat;
import mods.voicechat.logging.LogLevel;
import mods.voicechat.logging.VoicechatLogger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.config.Configurator;

public class Log4JVoicechatLogger
implements VoicechatLogger {
    private final boolean debugMode;
    private final Logger logger;

    public Log4JVoicechatLogger(Logger logger) {
        this.logger = logger;
        this.debugMode = Voicechat.debugMode();
        try {
            if (this.debugMode) {
                this.initDebugLogLevel();
            }
        }
        catch (Throwable t) {
            logger.error("Failed to set log level", t);
        }
    }

    public Log4JVoicechatLogger(String name) {
        this(LogManager.getLogger((String)name));
    }

    private void initDebugLogLevel() throws Exception {
        if (!(this.logger instanceof org.apache.logging.log4j.core.Logger)) {
            throw new IllegalStateException("Logger is not an instance of org.apache.logging.log4j.core.Logger");
        }
        org.apache.logging.log4j.core.Logger coreLogger = (org.apache.logging.log4j.core.Logger)this.logger;
        Map appenders = coreLogger.getAppenders();
        coreLogger.setAdditive(false);
        Configurator.setLevel((String)this.logger.getName(), (Level)Level.DEBUG);
        for (Appender appender : appenders.values()) {
            coreLogger.addAppender(appender);
        }
    }

    @Override
    public void log(LogLevel level, String message, Object ... args) {
        if (!this.isEnabled(level)) {
            return;
        }
        this.logger.log(this.fromLogLevel(level), this.modifyMessage(message), args);
    }

    @Override
    public boolean isEnabled(LogLevel level) {
        return this.logger.isEnabled(this.fromLogLevel(level));
    }

    private Level fromLogLevel(LogLevel level) {
        switch (level) {
            case TRACE: {
                return Level.TRACE;
            }
            case DEBUG: {
                return Level.DEBUG;
            }
            case WARN: {
                return Level.WARN;
            }
            case ERROR: {
                return Level.ERROR;
            }
            case FATAL: {
                return Level.FATAL;
            }
        }
        return Level.INFO;
    }

    private String modifyMessage(String message) {
        return String.format("[%s] %s", this.logger.getName(), message);
    }

    public Logger getLogger() {
        return this.logger;
    }
}


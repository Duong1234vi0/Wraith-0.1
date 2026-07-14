/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.command.defaults;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.command.Command;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandInvalidStateException;
import mods.baritone.api.api.java.baritone.api.process.IBaritoneProcess;
import mods.baritone.api.api.java.baritone.api.process.PathingCommand;
import mods.baritone.api.api.java.baritone.api.process.PathingCommandType;

public class ExecutionControlCommands {
    Command pauseCommand;
    Command resumeCommand;
    Command pausedCommand;
    Command cancelCommand;

    public ExecutionControlCommands(final IBaritone baritone) {
        final boolean[] paused = new boolean[]{false};
        baritone.getPathingControlManager().registerProcess(new IBaritoneProcess(){

            @Override
            public boolean isActive() {
                return paused[0];
            }

            @Override
            public PathingCommand onTick(boolean calcFailed, boolean isSafeToCancel) {
                baritone.getInputOverrideHandler().clearAllKeys();
                return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
            }

            @Override
            public boolean isTemporary() {
                return true;
            }

            @Override
            public void onLostControl() {
            }

            @Override
            public double priority() {
                return 0.0;
            }

            @Override
            public String displayName0() {
                return "Pause/Resume Commands";
            }
        });
        this.pauseCommand = new Command(this, baritone, new String[]{"pause", "p", "paws"}){

            @Override
            public void execute(String label, IArgConsumer args) throws CommandException {
                args.requireMax(0);
                if (paused[0]) {
                    throw new CommandInvalidStateException("Already paused");
                }
                paused[0] = true;
                this.logDirect("Paused");
            }

            @Override
            public Stream<String> tabComplete(String label, IArgConsumer args) {
                return Stream.empty();
            }

            @Override
            public String getShortDesc() {
                return "Pauses Baritone until you use resume";
            }

            @Override
            public List<String> getLongDesc() {
                return Arrays.asList("The pause command tells Baritone to temporarily stop whatever it's doing.", "", "This can be used to pause pathing, building, following, whatever. A single use of the resume command will start it right back up again!", "", "Usage:", "> pause");
            }
        };
        this.resumeCommand = new Command(this, baritone, new String[]{"resume", "r", "unpause", "unpaws"}){

            @Override
            public void execute(String label, IArgConsumer args) throws CommandException {
                args.requireMax(0);
                this.baritone.getBuilderProcess().resume();
                if (!paused[0]) {
                    throw new CommandInvalidStateException("Not paused");
                }
                paused[0] = false;
                this.logDirect("Resumed");
            }

            @Override
            public Stream<String> tabComplete(String label, IArgConsumer args) {
                return Stream.empty();
            }

            @Override
            public String getShortDesc() {
                return "Resumes Baritone after a pause";
            }

            @Override
            public List<String> getLongDesc() {
                return Arrays.asList("The resume command tells Baritone to resume whatever it was doing when you last used pause.", "", "Usage:", "> resume");
            }
        };
        this.pausedCommand = new Command(this, baritone, new String[]{"paused"}){

            @Override
            public void execute(String label, IArgConsumer args) throws CommandException {
                args.requireMax(0);
                this.logDirect(String.format("Baritone is %spaused", paused[0] ? "" : "not "));
            }

            @Override
            public Stream<String> tabComplete(String label, IArgConsumer args) {
                return Stream.empty();
            }

            @Override
            public String getShortDesc() {
                return "Tells you if Baritone is paused";
            }

            @Override
            public List<String> getLongDesc() {
                return Arrays.asList("The paused command tells you if Baritone is currently paused by use of the pause command.", "", "Usage:", "> paused");
            }
        };
        this.cancelCommand = new Command(this, baritone, new String[]{"cancel", "c", "stop"}){

            @Override
            public void execute(String label, IArgConsumer args) throws CommandException {
                args.requireMax(0);
                if (paused[0]) {
                    paused[0] = false;
                }
                this.baritone.getPathingBehavior().cancelEverything();
                this.logDirect("ok canceled");
            }

            @Override
            public Stream<String> tabComplete(String label, IArgConsumer args) {
                return Stream.empty();
            }

            @Override
            public String getShortDesc() {
                return "Cancel what Baritone is currently doing";
            }

            @Override
            public List<String> getLongDesc() {
                return Arrays.asList("The cancel command tells Baritone to stop whatever it's currently doing.", "", "Usage:", "> cancel");
            }
        };
    }
}


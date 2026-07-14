/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.command.defaults;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.z_3539_x;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.command.Command;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.datatypes.ForAxis;
import mods.baritone.api.api.java.baritone.api.command.datatypes.ForBlockOptionalMeta;
import mods.baritone.api.api.java.baritone.api.command.datatypes.ForDirection;
import mods.baritone.api.api.java.baritone.api.command.datatypes.RelativeBlockPos;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandInvalidStateException;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandInvalidTypeException;
import mods.baritone.api.api.java.baritone.api.command.helpers.TabCompleteHelper;
import mods.baritone.api.api.java.baritone.api.event.events.RenderEvent;
import mods.baritone.api.api.java.baritone.api.event.listener.AbstractGameEventListener;
import mods.baritone.api.api.java.baritone.api.schematic.CompositeSchematic;
import mods.baritone.api.api.java.baritone.api.schematic.FillSchematic;
import mods.baritone.api.api.java.baritone.api.schematic.ISchematic;
import mods.baritone.api.api.java.baritone.api.schematic.MaskSchematic;
import mods.baritone.api.api.java.baritone.api.schematic.ReplaceSchematic;
import mods.baritone.api.api.java.baritone.api.schematic.ShellSchematic;
import mods.baritone.api.api.java.baritone.api.schematic.WallsSchematic;
import mods.baritone.api.api.java.baritone.api.schematic.mask.shape.CylinderMask;
import mods.baritone.api.api.java.baritone.api.schematic.mask.shape.SphereMask;
import mods.baritone.api.api.java.baritone.api.selection.ISelection;
import mods.baritone.api.api.java.baritone.api.selection.ISelectionManager;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.BlockOptionalMeta;
import mods.baritone.api.api.java.baritone.api.utils.BlockOptionalMetaLookup;
import mods.baritone.utils.BlockStateInterface;
import mods.baritone.utils.IRenderer;
import mods.baritone.utils.schematic.StaticSchematic;

public class SelCommand
extends Command {
    private ISelectionManager manager;
    private BetterBlockPos pos1;
    private ISchematic clipboard;
    private z_3539_x clipboardOffset;

    public SelCommand(IBaritone baritone) {
        super(baritone, "sel", "selection", "s");
        this.manager = this.baritone.getSelectionManager();
        this.pos1 = null;
        this.clipboard = null;
        this.clipboardOffset = null;
        baritone.getGameEventHandler().registerEventListener(new AbstractGameEventListener(){

            @Override
            public void onRenderPass(RenderEvent event) {
                if (!((Boolean)Baritone.settings().renderSelectionCorners.value).booleanValue() || SelCommand.this.pos1 == null) {
                    return;
                }
                Color color = (Color)Baritone.settings().colorSelectionPos1.value;
                float opacity = ((Float)Baritone.settings().selectionOpacity.value).floatValue();
                float lineWidth = ((Float)Baritone.settings().selectionLineWidth.value).floatValue();
                boolean ignoreDepth = (Boolean)Baritone.settings().renderSelectionIgnoreDepth.value;
                IRenderer.startLines(color, opacity, lineWidth, ignoreDepth);
                IRenderer.emitAABB(event.getModelViewStack(), new I_4817_s(SelCommand.this.pos1, SelCommand.this.pos1.add(1, 1, 1)));
                IRenderer.endLines(ignoreDepth);
            }
        });
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        Action action = Action.getByName(args.getString());
        if (action == null) {
            throw new CommandInvalidTypeException(args.consumed(), "an action");
        }
        if (action == Action.POS1 || action == Action.POS2) {
            if (action == Action.POS2 && this.pos1 == null) {
                throw new CommandInvalidStateException("Set pos1 first before using pos2");
            }
            BetterBlockPos playerPos = this.ctx.viewerPos();
            BetterBlockPos pos = args.hasAny() ? (BetterBlockPos)args.getDatatypePost(RelativeBlockPos.INSTANCE, playerPos) : playerPos;
            args.requireMax(0);
            if (action == Action.POS1) {
                this.pos1 = pos;
                this.logDirect("Position 1 has been set");
            } else {
                this.manager.addSelection(this.pos1, pos);
                this.pos1 = null;
                this.logDirect("Selection added");
            }
        } else if (action == Action.CLEAR) {
            args.requireMax(0);
            this.pos1 = null;
            this.logDirect(String.format("Removed %d selections", this.manager.removeAllSelections().length));
        } else if (action == Action.UNDO) {
            args.requireMax(0);
            if (this.pos1 != null) {
                this.pos1 = null;
                this.logDirect("Undid pos1");
            } else {
                ISelection[] selections = this.manager.getSelections();
                if (selections.length < 1) {
                    throw new CommandInvalidStateException("Nothing to undo!");
                }
                this.pos1 = this.manager.removeSelection(selections[selections.length - 1]).pos1();
                this.logDirect("Undid pos2");
            }
        } else if (action.isFillAction()) {
            b_257_Y.n_1700_B alignment;
            BlockOptionalMetaLookup replaces;
            BlockOptionalMeta type;
            BlockOptionalMeta blockOptionalMeta = type = action == Action.CLEARAREA ? new BlockOptionalMeta(a_3742_W.n_1700_B) : (BlockOptionalMeta)args.getDatatypeFor(ForBlockOptionalMeta.INSTANCE);
            if (action == Action.REPLACE) {
                args.requireMin(1);
                ArrayList<BlockOptionalMeta> replacesList = new ArrayList<BlockOptionalMeta>();
                replacesList.add(type);
                while (args.has(2)) {
                    replacesList.add((BlockOptionalMeta)args.getDatatypeFor(ForBlockOptionalMeta.INSTANCE));
                }
                type = (BlockOptionalMeta)args.getDatatypeFor(ForBlockOptionalMeta.INSTANCE);
                replaces = new BlockOptionalMetaLookup(replacesList.toArray(new BlockOptionalMeta[0]));
                alignment = null;
            } else if (action == Action.CYLINDER || action == Action.HCYLINDER) {
                args.requireMax(1);
                alignment = args.hasAny() ? (b_257_Y.n_1700_B)args.getDatatypeFor(ForAxis.INSTANCE) : b_257_Y.n_1700_B.J_1907_R;
                replaces = null;
            } else {
                args.requireMax(0);
                replaces = null;
                alignment = null;
            }
            ISelection[] selections = this.manager.getSelections();
            if (selections.length == 0) {
                throw new CommandInvalidStateException("No selections");
            }
            BetterBlockPos origin = selections[0].min();
            CompositeSchematic composite = new CompositeSchematic(0, 0, 0);
            for (ISelection selection : selections) {
                BetterBlockPos min = selection.min();
                origin = new BetterBlockPos(Math.min(origin.x, min.x), Math.min(origin.y, min.y), Math.min(origin.z, min.z));
            }
            for (ISelection selection : selections) {
                z_3539_x size = selection.size();
                BetterBlockPos min = selection.min();
                UnaryOperator create = fill -> {
                    int w = fill.widthX();
                    int h = fill.heightY();
                    int l = fill.lengthZ();
                    switch (action.ordinal()) {
                        case 5: {
                            return new WallsSchematic((ISchematic)fill);
                        }
                        case 6: {
                            return new ShellSchematic((ISchematic)fill);
                        }
                        case 12: {
                            return new ReplaceSchematic((ISchematic)fill, replaces);
                        }
                        case 7: {
                            return MaskSchematic.create(fill, new SphereMask(w, h, l, true).compute());
                        }
                        case 8: {
                            return MaskSchematic.create(fill, new SphereMask(w, h, l, false).compute());
                        }
                        case 9: {
                            return MaskSchematic.create(fill, new CylinderMask(w, h, l, true, alignment).compute());
                        }
                        case 10: {
                            return MaskSchematic.create(fill, new CylinderMask(w, h, l, false, alignment).compute());
                        }
                    }
                    return fill;
                };
                ISchematic schematic = (ISchematic)create.apply(new FillSchematic(size.getX(), size.getY(), size.getZ(), type));
                composite.put(schematic, min.x - origin.x, min.y - origin.y, min.z - origin.z);
            }
            this.baritone.getBuilderProcess().build("Fill", composite, (z_3539_x)origin);
            this.logDirect("Filling now");
        } else if (action == Action.COPY) {
            BetterBlockPos playerPos = this.ctx.viewerPos();
            BetterBlockPos pos = args.hasAny() ? (BetterBlockPos)args.getDatatypePost(RelativeBlockPos.INSTANCE, playerPos) : playerPos;
            args.requireMax(0);
            ISelection[] selections = this.manager.getSelections();
            if (selections.length < 1) {
                throw new CommandInvalidStateException("No selections");
            }
            BlockStateInterface bsi = new BlockStateInterface(this.ctx);
            BetterBlockPos origin = selections[0].min();
            CompositeSchematic composite = new CompositeSchematic(0, 0, 0);
            for (ISelection selection : selections) {
                BetterBlockPos min = selection.min();
                origin = new BetterBlockPos(Math.min(origin.x, min.x), Math.min(origin.y, min.y), Math.min(origin.z, min.z));
            }
            for (ISelection selection : selections) {
                final z_3539_x size = selection.size();
                BetterBlockPos min = selection.min();
                final K_4074_S[][][] blockstates = new K_4074_S[size.getX()][size.getZ()][size.getY()];
                for (int x = 0; x < size.getX(); ++x) {
                    for (int y = 0; y < size.getY(); ++y) {
                        for (int z = 0; z < size.getZ(); ++z) {
                            blockstates[x][z][y] = bsi.get0(min.x + x, min.y + y, min.z + z);
                        }
                    }
                }
                StaticSchematic schematic = new StaticSchematic(this){
                    {
                        this.states = blockstates;
                        this.x = size.getX();
                        this.y = size.getY();
                        this.z = size.getZ();
                    }
                };
                composite.put(schematic, min.x - origin.x, min.y - origin.y, min.z - origin.z);
            }
            this.clipboard = composite;
            this.clipboardOffset = origin.subtract(pos);
            this.logDirect("Selection copied");
        } else if (action == Action.PASTE) {
            BetterBlockPos playerPos = this.ctx.viewerPos();
            BetterBlockPos pos = args.hasAny() ? (BetterBlockPos)args.getDatatypePost(RelativeBlockPos.INSTANCE, playerPos) : playerPos;
            args.requireMax(0);
            if (this.clipboard == null) {
                throw new CommandInvalidStateException("You need to copy a selection first");
            }
            this.baritone.getBuilderProcess().build("Fill", this.clipboard, (z_3539_x)pos.add(this.clipboardOffset));
            this.logDirect("Building now");
        } else if (action == Action.EXPAND || action == Action.CONTRACT || action == Action.SHIFT) {
            args.requireExactly(3);
            TransformTarget transformTarget = TransformTarget.getByName(args.getString());
            if (transformTarget == null) {
                throw new CommandInvalidStateException("Invalid transform type");
            }
            b_257_Y direction = (b_257_Y)args.getDatatypeFor(ForDirection.INSTANCE);
            int blocks = args.getAs(Integer.class);
            ISelection[] selections = this.manager.getSelections();
            if (selections.length < 1) {
                throw new CommandInvalidStateException("No selections found");
            }
            for (ISelection selection : selections = transformTarget.transform(selections)) {
                if (action == Action.EXPAND) {
                    this.manager.expand(selection, direction, blocks);
                    continue;
                }
                if (action == Action.CONTRACT) {
                    this.manager.contract(selection, direction, blocks);
                    continue;
                }
                this.manager.shift(selection, direction, blocks);
            }
            this.logDirect(String.format("Transformed %d selections", selections.length));
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasExactlyOne()) {
            return new TabCompleteHelper().append(Action.getAllNames()).filterPrefix(args.getString()).sortAlphabetically().stream();
        }
        Action action = Action.getByName(args.getString());
        if (action != null) {
            if (action == Action.POS1 || action == Action.POS2) {
                if (args.hasAtMost(3)) {
                    return args.tabCompleteDatatype(RelativeBlockPos.INSTANCE);
                }
            } else if (action.isFillAction()) {
                if (args.hasExactlyOne() || action == Action.REPLACE) {
                    while (args.has(2)) {
                        args.get();
                    }
                    return args.tabCompleteDatatype(ForBlockOptionalMeta.INSTANCE);
                }
                if (args.hasExactly(2) && (action == Action.CYLINDER || action == Action.HCYLINDER)) {
                    args.get();
                    return args.tabCompleteDatatype(ForAxis.INSTANCE);
                }
            } else if (action == Action.EXPAND || action == Action.CONTRACT || action == Action.SHIFT) {
                if (args.hasExactlyOne()) {
                    return new TabCompleteHelper().append(TransformTarget.getAllNames()).filterPrefix(args.getString()).sortAlphabetically().stream();
                }
                TransformTarget target = TransformTarget.getByName(args.getString());
                if (target != null && args.hasExactlyOne()) {
                    return args.tabCompleteDatatype(ForDirection.INSTANCE);
                }
            }
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "WorldEdit-like commands";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("The sel command allows you to manipulate Baritone's selections, similarly to WorldEdit.", "", "Using these selections, you can clear areas, fill them with blocks, or something else.", "", "The expand/contract/shift commands use a kind of selector to choose which selections to target. Supported ones are a/all, n/newest, and o/oldest.", "", "Usage:", "> sel pos1/p1/1 - Set position 1 to your current position.", "> sel pos1/p1/1 <x> <y> <z> - Set position 1 to a relative position.", "> sel pos2/p2/2 - Set position 2 to your current position.", "> sel pos2/p2/2 <x> <y> <z> - Set position 2 to a relative position.", "", "> sel clear/c - Clear the selection.", "> sel undo/u - Undo the last action (setting positions, creating selections, etc.)", "> sel set/fill/s/f [block] - Completely fill all selections with a block.", "> sel walls/w [block] - Fill in the walls of the selection with a specified block.", "> sel shell/shl [block] - The same as walls, but fills in a ceiling and floor too.", "> sel sphere/sph [block] - Fills the selection with a sphere bounded by the sides.", "> sel hsphere/hsph [block] - The same as sphere, but hollow.", "> sel cylinder/cyl [block] <axis> - Fills the selection with a cylinder bounded by the sides, oriented about the given axis. (default=y)", "> sel hcylinder/hcyl [block] <axis> - The same as cylinder, but hollow.", "> sel cleararea/ca - Basically 'set air'.", "> sel replace/r <blocks...> <with> - Replaces blocks with another block.", "> sel copy/cp <x> <y> <z> - Copy the selected area relative to the specified or your position.", "> sel paste/p <x> <y> <z> - Build the copied area relative to the specified or your position.", "", "> sel expand <target> <direction> <blocks> - Expand the targets.", "> sel contract <target> <direction> <blocks> - Contract the targets.", "> sel shift <target> <direction> <blocks> - Shift the targets (does not resize).");
    }

    static enum Action {
        POS1("pos1", "p1", "1"),
        POS2("pos2", "p2", "2"),
        CLEAR("clear", "c"),
        UNDO("undo", "u"),
        SET("set", "fill", "s", "f"),
        WALLS("walls", "w"),
        SHELL("shell", "shl"),
        SPHERE("sphere", "sph"),
        HSPHERE("hsphere", "hsph"),
        CYLINDER("cylinder", "cyl"),
        HCYLINDER("hcylinder", "hcyl"),
        CLEARAREA("cleararea", "ca"),
        REPLACE("replace", "r"),
        EXPAND("expand", "ex"),
        COPY("copy", "cp"),
        PASTE("paste", "p"),
        CONTRACT("contract", "ct"),
        SHIFT("shift", "sh");

        private final String[] names;

        private Action(String ... names) {
            this.names = names;
        }

        public static Action getByName(String name) {
            for (Action action : Action.values()) {
                for (String alias : action.names) {
                    if (!alias.equalsIgnoreCase(name)) continue;
                    return action;
                }
            }
            return null;
        }

        public static String[] getAllNames() {
            HashSet<String> names = new HashSet<String>();
            for (Action action : Action.values()) {
                names.addAll(Arrays.asList(action.names));
            }
            return names.toArray(new String[0]);
        }

        public final boolean isFillAction() {
            return this == SET || this == WALLS || this == SHELL || this == SPHERE || this == HSPHERE || this == CYLINDER || this == HCYLINDER || this == CLEARAREA || this == REPLACE;
        }
    }

    /*
     * Exception performing whole class analysis.
     */
    static final class TransformTarget
    extends Enum<TransformTarget> {
        public static final /* enum */ TransformTarget ALL;
        public static final /* enum */ TransformTarget NEWEST;
        public static final /* enum */ TransformTarget OLDEST;
        private final Function<ISelection[], ISelection[]> transform;
        private final String[] names;
        private static final /* synthetic */ TransformTarget[] $VALUES;

        public static TransformTarget[] values() {
            return (TransformTarget[])$VALUES.clone();
        }

        public static TransformTarget valueOf(String name) {
            return Enum.valueOf(TransformTarget.class, name);
        }

        private TransformTarget(Function<ISelection[], ISelection[]> transform, String ... names) {
            super(string, n);
            this.transform = transform;
            this.names = names;
        }

        public ISelection[] transform(ISelection[] selections) {
            return this.transform.apply(selections);
        }

        public static TransformTarget getByName(String name) {
            for (TransformTarget target : TransformTarget.values()) {
                for (String alias : target.names) {
                    if (!alias.equalsIgnoreCase(name)) continue;
                    return target;
                }
            }
            return null;
        }

        public static String[] getAllNames() {
            HashSet<String> names = new HashSet<String>();
            for (TransformTarget target : TransformTarget.values()) {
                names.addAll(Arrays.asList(target.names));
            }
            return names.toArray(new String[0]);
        }

        private static /* synthetic */ ISelection[] lambda$static$2(ISelection[] sels) {
            return new ISelection[]{sels[0]};
        }

        private static /* synthetic */ TransformTarget[] $values() {
            return new TransformTarget[]{ALL, NEWEST, OLDEST};
        }

        /*
         * Exception decompiling
         */
        static {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * java.lang.UnsupportedOperationException
             *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.NewAnonymousArray.getDimSize(NewAnonymousArray.java:142)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.LambdaRewriter.isNewArrayLambda(LambdaRewriter.java:455)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.LambdaRewriter.rewriteDynamicExpression(LambdaRewriter.java:409)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.LambdaRewriter.rewriteDynamicExpression(LambdaRewriter.java:167)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.LambdaRewriter.rewriteExpression(LambdaRewriter.java:105)
             *     at org.benf.cfr.reader.bytecode.analysis.parse.rewriters.ExpressionRewriterHelper.applyForwards(ExpressionRewriterHelper.java:12)
             *     at org.benf.cfr.reader.bytecode.analysis.parse.expression.AbstractConstructorInvokation.applyExpressionRewriter(AbstractConstructorInvokation.java:65)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.LambdaRewriter.rewriteExpression(LambdaRewriter.java:103)
             *     at org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredAssignment.rewriteExpressions(StructuredAssignment.java:146)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.LambdaRewriter.rewrite(LambdaRewriter.java:88)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.rewriteLambdas(Op04StructuredStatement.java:1137)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:912)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
             *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
             *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:923)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1035)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
             *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
             *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
             *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
             *     at org.benf.cfr.reader.Main.main(Main.java:54)
             */
            throw new IllegalStateException("Decompilation failed");
        }
    }
}


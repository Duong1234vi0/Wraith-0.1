/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.File;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import lightning.product.T_797_O;
import lightning.product.d_560_A;
import lightning.product.k_2273_q;

public class S_1165_y {
    private final String n_1700_B;
    private final String J_1907_R;
    private d_560_A R_4764_Y;
    private T_797_O G_564_y;
    private Class<?> P_1922_E;
    private URLClassLoader u_1723_Y;

    public S_1165_y(String scriptName, String sourceCode) {
        this.n_1700_B = scriptName;
        this.J_1907_R = sourceCode;
        this.R_4764_Y = new k_2273_q();
    }

    public boolean n_1700_B() {
        try {
            String fullSource;
            String className;
            Path tempDir = Files.createTempDirectory("pouch_scripts_", new FileAttribute[0]);
            File tempDirFile = tempDir.toFile();
            tempDirFile.deleteOnExit();
            if (this.J_1907_R.contains("class ") && this.J_1907_R.contains("implements Script")) {
                int classIndex = this.J_1907_R.indexOf("class ");
                if (classIndex != -1) {
                    int start;
                    int end;
                    for (end = start = classIndex + 6; end < this.J_1907_R.length() && (Character.isJavaIdentifierPart(this.J_1907_R.charAt(end)) || this.J_1907_R.charAt(end) == '_' || this.J_1907_R.charAt(end) == '$'); ++end) {
                    }
                    className = this.J_1907_R.substring(start, end).trim();
                } else {
                    className = this.P_1922_E();
                }
                fullSource = this.n_1700_B(this.J_1907_R);
            } else {
                className = this.P_1922_E();
                fullSource = this.n_1700_B(className, this.J_1907_R);
            }
            File sourceFile = new File(tempDirFile, className + ".java");
            Files.write(sourceFile.toPath(), fullSource.getBytes(), new OpenOption[0]);
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            if (compiler == null) {
                System.err.println("JavaCompiler \u043d\u0435\u0434\u043e\u0441\u0442\u0443\u043f\u0435\u043d! \u0423\u0431\u0435\u0434\u0438\u0442\u0435\u0441\u044c, \u0447\u0442\u043e \u0432\u044b \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0435\u0442\u0435 JDK, \u0430 \u043d\u0435 JRE.");
                return false;
            }
            DiagnosticCollector diagnostics = new DiagnosticCollector();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
            ArrayList<File> sourceFiles = new ArrayList<File>();
            sourceFiles.add(sourceFile);
            ArrayList<String> options = new ArrayList<String>();
            options.add("-d");
            options.add(tempDirFile.getAbsolutePath());
            String classpath = System.getProperty("java.class.path");
            if (classpath != null && !classpath.isEmpty()) {
                options.add("-cp");
                options.add(classpath);
            }
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null, fileManager.getJavaFileObjectsFromFiles(sourceFiles));
            boolean success = task.call();
            fileManager.close();
            if (!success) {
                System.err.println("\u041e\u0448\u0438\u0431\u043a\u0430 \u043a\u043e\u043c\u043f\u0438\u043b\u044f\u0446\u0438\u0438 \u0441\u043a\u0440\u0438\u043f\u0442\u0430: " + this.n_1700_B);
                for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
                    System.err.println("  " + String.valueOf((Object)diagnostic.getKind()) + " \u0432 \u0441\u0442\u0440\u043e\u043a\u0435 " + diagnostic.getLineNumber() + ", \u0441\u0442\u043e\u043b\u0431\u0435\u0446 " + diagnostic.getColumnNumber() + ": " + diagnostic.getMessage(null));
                }
                return false;
            }
            URL[] urls = new URL[]{tempDirFile.toURI().toURL()};
            this.u_1723_Y = new URLClassLoader(urls);
            this.P_1922_E = this.u_1723_Y.loadClass(className);
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            MethodHandle constructor = lookup.findConstructor(this.P_1922_E, MethodType.methodType(Void.TYPE));
            try {
                this.G_564_y = constructor.invoke();
                this.G_564_y.n_1700_B(this.R_4764_Y);
                this.G_564_y.n_1700_B();
            }
            catch (Throwable e) {
                if (e instanceof Exception) {
                    throw (Exception)e;
                }
                throw new RuntimeException("\u041e\u0448\u0438\u0431\u043a\u0430 \u0441\u043e\u0437\u0434\u0430\u043d\u0438\u044f \u044d\u043a\u0437\u0435\u043c\u043f\u043b\u044f\u0440\u0430 \u0441\u043a\u0440\u0438\u043f\u0442\u0430", e);
            }
            return true;
        }
        catch (Exception e) {
            System.err.println("\u041e\u0448\u0438\u0431\u043a\u0430 \u0437\u0430\u0433\u0440\u0443\u0437\u043a\u0438 Java \u0441\u043a\u0440\u0438\u043f\u0442\u0430: " + this.n_1700_B);
            e.printStackTrace();
            return false;
        }
    }

    public void J_1907_R() {
        try {
            if (this.G_564_y != null) {
                this.G_564_y.J_1907_R();
            }
            if (this.R_4764_Y instanceof k_2273_q) {
                ((k_2273_q)this.R_4764_Y).c_3005_b();
            }
            if (this.u_1723_Y != null) {
                this.u_1723_Y.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String P_1922_E() {
        Object name = this.n_1700_B.replace(".java", "").replace(".pouch", "").replaceAll("[^a-zA-Z0-9_$]", "_");
        if (((String)name).isEmpty() || Character.isDigit(((String)name).charAt(0))) {
            name = "Script_" + (String)name;
        }
        return name;
    }

    private String n_1700_B(String sourceCode) {
        Object result = sourceCode;
        result = ((String)result).replaceAll("import\\s+dev\\.pouch\\.fun\\.scripts\\.Script\\s*;", "import Script;");
        result = ((String)result).replaceAll("import\\s+dev\\.pouch\\.fun\\.scripts\\.api\\.ScriptAPI\\s*;", "import ScriptAPI;");
        result = ((String)result).replaceAll("import\\s+pouch\\.Script\\s*;", "import Script;");
        result = ((String)result).replaceAll("import\\s+pouch\\.ScriptAPI\\s*;", "import ScriptAPI;");
        result = ((String)result).replaceAll("import\\s+Script\\s*;", "import dev.pouch.fun.scripts.Script;");
        result = ((String)result).replaceAll("import\\s+ScriptAPI\\s*;", "import dev.pouch.fun.scripts.api.ScriptAPI;");
        boolean hasScriptImport = ((String)result).contains("import dev.pouch.fun.scripts.Script;");
        boolean hasAPIImport = ((String)result).contains("import dev.pouch.fun.scripts.api.ScriptAPI;");
        if (!hasScriptImport || !hasAPIImport) {
            StringBuilder imports = new StringBuilder();
            if (!hasScriptImport) {
                imports.append("import dev.pouch.fun.scripts.Script;\n");
            }
            if (!hasAPIImport) {
                imports.append("import dev.pouch.fun.scripts.api.ScriptAPI;\n");
            }
            imports.append("\n");
            if (((String)result).contains("package ")) {
                int packageEnd;
                for (packageEnd = ((String)result).indexOf(";", ((String)result).indexOf("package ")) + 1; packageEnd < ((String)result).length() && (((String)result).charAt(packageEnd) == ' ' || ((String)result).charAt(packageEnd) == '\n' || ((String)result).charAt(packageEnd) == '\r'); ++packageEnd) {
                }
                result = ((String)result).substring(0, packageEnd) + String.valueOf(imports) + ((String)result).substring(packageEnd);
            } else {
                result = String.valueOf(imports) + (String)result;
            }
        }
        return result;
    }

    private String n_1700_B(String className, String userCode) {
        StringBuilder wrapper = new StringBuilder();
        wrapper.append("import Script;\n");
        wrapper.append("import ScriptAPI;\n");
        wrapper.append("\n");
        wrapper.append("public class ").append(className).append(" implements Script {\n");
        wrapper.append("    private ScriptAPI api;\n");
        wrapper.append("\n");
        wrapper.append("    @Override\n");
        wrapper.append("    public void init(ScriptAPI api) {\n");
        wrapper.append("        this.api = api;\n");
        wrapper.append("    }\n");
        wrapper.append("\n");
        wrapper.append("    @Override\n");
        wrapper.append("    public void onLoad() {\n");
        String indentedCode = userCode.lines().map(line -> "        " + line).reduce((a, b) -> a + "\n" + b).orElse("");
        wrapper.append(indentedCode);
        wrapper.append("\n");
        wrapper.append("    }\n");
        wrapper.append("\n");
        wrapper.append("    @Override\n");
        wrapper.append("    public void onUnload() {\n");
        wrapper.append("        // \u041e\u0447\u0438\u0441\u0442\u043a\u0430 \u043f\u0440\u0438 \u0432\u044b\u0433\u0440\u0443\u0437\u043a\u0435\n");
        wrapper.append("    }\n");
        wrapper.append("}\n");
        return this.n_1700_B(wrapper.toString());
    }

    public String R_4764_Y() {
        return this.n_1700_B;
    }

    public boolean G_564_y() {
        return this.G_564_y != null;
    }
}


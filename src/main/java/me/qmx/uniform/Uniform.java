package me.qmx.uniform;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.TextEdit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Uniform {

    private Options options;

    public Uniform(Options options) {
        this.options = options;
    }

    public void process() {
        InputStream configStream = null;
        try {
            configStream = new FileInputStream(options.getConfigFile());
            final Map<String, String> config = new ConfigParser(configStream).parse();
            final Map<String, String> options = new HashMap<String, String>() {{
                putAll(config);
                put(JavaCore.COMPILER_SOURCE, "1.6");
            }};
            final CodeFormatter formatter = ToolFactory.createCodeFormatter(options);
            String source = "";
            final TextEdit edit = formatter.format(CodeFormatter.K_COMPILATION_UNIT, source, 0, source.length(), 0, "\n");
            final Document document = new Document(source);
            edit.apply(document);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (configStream != null) {
                try {
                    configStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}

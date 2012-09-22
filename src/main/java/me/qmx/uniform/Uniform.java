package me.qmx.uniform;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.TextEdit;

import java.util.HashMap;
import java.util.Map;

public class Uniform {

    private Options options;

    public Uniform(Options options) {
        this.options = options;
    }

    public void process() {
        try {
            final Map<String, String> options = new HashMap<String, String>() {{
                put(JavaCore.COMPILER_SOURCE, "1.6");
            }};
            final CodeFormatter formatter = ToolFactory.createCodeFormatter(options);
            String source = "";
            final TextEdit edit = formatter.format(CodeFormatter.K_COMPILATION_UNIT, source, 0, source.length(), 0, "\n");
            final Document document = new Document(source);
            edit.apply(document);
        } catch (BadLocationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}

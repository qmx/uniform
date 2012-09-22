package me.qmx.uniform;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.TextEdit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class Uniform {

    private CodeFormatter formatter;
    private final File[] files;

    public Uniform(InputStream configFile, String sourceVersion, File... files) {
        final Map<String, String> config = new ConfigParser(configFile).parse();
        config.put(JavaCore.COMPILER_SOURCE, sourceVersion);
        config.put(JavaCore.COMPILER_COMPLIANCE, sourceVersion);
        config.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, sourceVersion);
        this.formatter = ToolFactory.createCodeFormatter(config);
        this.files = files;
    }

    public void process() {
        try {
            for (File file : files) {
                formatFile(formatter, file);
            }
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void formatFile(CodeFormatter formatter, File file) throws IOException, BadLocationException {
        final String source = Files.toString(file, Charsets.UTF_8);
        final TextEdit edit = formatter.format(CodeFormatter.K_COMPILATION_UNIT, source, 0, source.length(), 0, "\n");
        if (edit == null) {
            System.err.println("failed!!!!!");
            return;
        }
        final Document document = new Document(source);
        edit.apply(document);
        final String formattedSource = document.get();
        Files.write(formattedSource, file, Charsets.UTF_8);
    }

}

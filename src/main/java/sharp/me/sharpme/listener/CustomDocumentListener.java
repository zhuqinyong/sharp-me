package sharp.me.sharpme.listener;


import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import org.jetbrains.annotations.NotNull;

public class CustomDocumentListener implements DocumentListener {
    @Override
    public void documentChanged(@NotNull DocumentEvent event) {
        System.out.println(this.getClass());
    }
}

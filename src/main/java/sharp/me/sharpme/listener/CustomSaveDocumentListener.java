package sharp.me.sharpme.listener;


import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManagerListener;
import org.jetbrains.annotations.NotNull;

public class CustomSaveDocumentListener implements FileDocumentManagerListener {

    @Override
    public void beforeDocumentSaving(@NotNull Document document) {
        System.out.println(this.getClass());
    }

    @Override
    public void beforeAllDocumentsSaving() {
        System.out.println(this.getClass());
    }
}

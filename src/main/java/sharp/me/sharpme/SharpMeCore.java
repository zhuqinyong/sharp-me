package sharp.me.sharpme;

import com.intellij.AppTopics;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.util.messages.MessageBusConnection;
import sharp.me.sharpme.listener.CustomDocumentListener;
import sharp.me.sharpme.listener.CustomMouseListener;
import sharp.me.sharpme.listener.CustomSaveDocumentListener;
import sharp.me.sharpme.listener.CustomVisibleAreaListener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SharpMeCore implements ApplicationComponent {

    public static final Logger logger = Logger.getInstance(SharpMeCore.class);

    private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

    @Override
    public void initComponent() {
        ApplicationManager.getApplication().invokeLater(() -> {
            MessageBusConnection busConnection = ApplicationManager.getApplication().getMessageBus().connect();
            busConnection.subscribe(AppTopics.FILE_DOCUMENT_SYNC, new CustomSaveDocumentListener());
            EditorFactory.getInstance().getEventMulticaster().addEditorMouseListener(new CustomMouseListener());
            EditorFactory.getInstance().getEventMulticaster().addVisibleAreaListener(new CustomVisibleAreaListener());
            EditorFactory.getInstance().getEventMulticaster().addDocumentListener(new CustomDocumentListener());
            scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("test online"), 1, 5, TimeUnit.SECONDS);
        });
    }
}

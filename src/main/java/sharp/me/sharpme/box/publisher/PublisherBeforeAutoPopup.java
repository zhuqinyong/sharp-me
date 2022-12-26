package sharp.me.sharpme.box.publisher;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import sharp.me.sharpme.box.consumer.GeneralConsumer;

import java.util.LinkedList;
import java.util.List;

public class PublisherBeforeAutoPopup {
    private static final List<GeneralConsumer> list = new LinkedList<>();

    public static void add(GeneralConsumer consumer) {
        synchronized (list) {
            if (!list.contains(consumer)) {
                list.add(consumer);
            }
        }
    }

    public static void invoke(CompletionParameters param, CompletionResultSet result) {
        for (GeneralConsumer generalConsumer : list) {
            generalConsumer.addLookElement(param, result);
        }
    }
}

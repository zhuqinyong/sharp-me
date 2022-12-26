package sharp.me.sharpme.contributor;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import org.jetbrains.annotations.NotNull;
import sharp.me.sharpme.box.consumer.SearchConsumer;
import sharp.me.sharpme.box.publisher.PublisherBeforeAutoPopup;

public class SharpMeCompletionContributor extends CompletionContributor {
    @Override
    public void fillCompletionVariants(@NotNull CompletionParameters parameters, @NotNull CompletionResultSet result) {
        PublisherBeforeAutoPopup.add(SearchConsumer.instance());
        PublisherBeforeAutoPopup.invoke(parameters, result);
    }
}

package sharp.me.sharpme.box.consumer;

import sharp.me.sharpme.ano.AtFun;
import sharp.me.sharpme.ano.AtMe;

@AtMe(desc = "搜索字典")
public class SearchConsumer extends GeneralConsumer {
    private GeneralConsumer instance = new SearchConsumer();

    private SearchConsumer() {
    }

    public static GeneralConsumer instance() {
        return instance();
    }

    @AtFun(desc = "搜过字典", name = "search")
    public void search(String key0, String key1) {
        System.out.println(key0);
        System.out.println(key1);
    }
}

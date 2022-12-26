package sharp.me.sharpme.box.consumer;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.openapi.editor.Editor;
import sharp.me.sharpme.ano.AtArg;
import sharp.me.sharpme.ano.AtFun;
import sharp.me.sharpme.entity.FunAnoEntity;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GeneralConsumer {
    /**
     * 其实开始的地方
     */
    private String CMD_FLAG = "@";

    /**
     * 提示函数名
     */
    private String FUN_NM_POPUP_REGEX = "^@/([a-zA-Z0-9])*$";
    /**
     * 提示参数名
     */
    private String ARG_NM_POPUP_REGEX = "^@/(([a-zA-Z0-9]+)=([a-zA-Z0-9]+)/)+[a-zA-Z0-9]*";

    /**
     * 结束正则
     */
    private String POPUP_FINISH_REGEX = "^@(/([a-zA-Z0-9]+)=([a-zA-Z0-9]+))+(!|！)$";

    /**
     * 提示返回结果
     */
    protected CompletionResultSet result;
    /**
     * 触发参数
     */
    protected CompletionParameters param;

    /**
     * 当前编辑器实例
     */
    protected Editor editor;


    private String cmd() {
        String ctx = this.param.getEditor().getDocument().getText();
        String subCtx = ctx.substring(0, this.param.getOffset());
        if (subCtx.contains(CMD_FLAG)) {
            return subCtx.substring(subCtx.lastIndexOf(CMD_FLAG), this.param.getOffset());
        }
        return "";
    }

    public List<FunAnoEntity> funAndArgMap() {
        List<FunAnoEntity> list = new LinkedList<>();
        Method[] methods = this.getClass().getMethods();
        for (Method method : methods) {
            AtFun[] atFunGroup = method.getAnnotationsByType(AtFun.class);
            if (atFunGroup.length != 1) {
                continue;
            }
            AtFun atFun = atFunGroup[0];
            FunAnoEntity funAnoEntity = new FunAnoEntity();
            list.add(funAnoEntity);
            funAnoEntity.setAtFun(atFun);
            for (Parameter parameter : method.getParameters()) {
                AtArg[] atArgs = parameter.getAnnotationsByType(AtArg.class);
                funAnoEntity.getAtArgs().add(atArgs[0]);
            }
        }
        return list;
    }

    public void addLookElement(CompletionParameters param, CompletionResultSet result) {
        this.param = param;
        this.result = result;
        this.editor = this.param.getEditor();
        String rawCmd = cmd();
        if (rawCmd.matches(POPUP_FINISH_REGEX)) {
            finish();
        }
        if (rawCmd.matches(ARG_NM_POPUP_REGEX)) {

        }
        if (rawCmd.matches(FUN_NM_POPUP_REGEX)) {

        }
    }

    public void finish() {
        try {
            String rawCmd = cmd();
            System.out.println("结束执行" + rawCmd);
            String kvRaw = rawCmd.replace("@/|!", "");
            String[] kvGroup = kvRaw.split("/");
            HashMap<String, String> kvMap = new HashMap<>();
            for (String kvStr : kvGroup) {
                String[] kv = kvStr.split("=");
                kvMap.put(kv[0], kv[1]);
            }
            String funNm = kvMap.get("fun");
            List<FunAnoEntity> list = funAndArgMap();
            for (FunAnoEntity funAnoEntity : list) {
                if (funAnoEntity.getAtFun().name().equals(funNm)) {
                    int size = funAnoEntity.getAtArgs().size();
                    Object[] args = new Object[size];
                    for (int seq = 0; seq < args.length; seq++) {
                        args[seq] = kvMap.get(funAnoEntity.getAtArgs().get(seq).name());
                    }
                }
            }
        } catch (Exception e) {

        }
    }
}

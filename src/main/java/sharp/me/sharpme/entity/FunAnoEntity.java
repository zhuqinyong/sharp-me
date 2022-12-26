package sharp.me.sharpme.entity;

import sharp.me.sharpme.ano.AtArg;
import sharp.me.sharpme.ano.AtFun;

import java.util.Vector;

public class FunAnoEntity {
    private AtFun atFun;
    private Vector<AtArg> atArgs = new Vector<>();

    public AtFun getAtFun() {
        return atFun;
    }

    public void setAtFun(AtFun atFun) {
        this.atFun = atFun;
    }

    public Vector<AtArg> getAtArgs() {
        return atArgs;
    }

    public void setAtArgs(Vector<AtArg> atArgs) {
        this.atArgs = atArgs;
    }
}

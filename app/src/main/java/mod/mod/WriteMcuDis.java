package mod.mod;

import static mod.mod.Final.*;
import static mod.mod.Start.miscFunc;
import static mod.mod.Start.blockMcu;

public class WriteMcuDis {
    public static int[] args1 = null;
    public static int count;
    public static boolean match = false;

    public static int[] WriteMcuDisable(int[] args) {
        match = false;
        if (miscFunc[blockMcuMFD] & !Start.allowPass) {
            for(int i=0;i<count;i++){
                for (int t=0;t<args.length;t++){
                    if(t<blockMcu[i].howMany) {
                        if (match) {
                            if (blockMcu[i].val[t] != skip & blockMcu[i].val[t] != args[t]) {
                                match = false;
                                break;
                            }
                        } else {
                            if (blockMcu[i].val[t] == skip) {
                                match = true;
                            } else if (blockMcu[i].val[t] == args[t]) {
                                match = true;
                            }
                        }
                        if (!match) {
                            break;
                        }
                    }else {
                        if (match){
                            args1=null;
                            break;
                        }
                    }
                }
                if(match){
                    break;
                }
            }
            if(match){
                args1=null;
            }else {
                args1=args;
            }
        } else {
            args1 = args;
        }
        Start.allowPass = false;
        return args1;
    }
}

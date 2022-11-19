package mod.mod;

public class BridgeMod2Ms {
    public static void KillAll(){
        // replace invoke-static {}, Lmod/mod/dummy;->killAll()V with invoke-static {}, Lapp/ad;->o()V
        dummy.killAll();
    }
    public static int returnCode(int code){
        return code;
    }
}

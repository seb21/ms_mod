package mod.mod;

public class KillApp {
    public static void KillApp(String app){
        // replace invoke-static {}, Lmod/mod/dummy;->killApp(app)V with invoke-static {}, Lapp/ad;->h()V
        dummy.killApp(app);
    }
}

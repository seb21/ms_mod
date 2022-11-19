package mod.mod;

public class writeMcuLogger {
    // put :
    //invoke-static {p0}, Lmod/mod/writeMcuLogger;->spy([I)[I
    //
    //    move-result-object p0
    // after
    // .method public static varargs b([I)V
    // in: b/s.smali
    public static int[] spy(int[] args){
        args=Tools.spyMCU(args);
        return args;
    }
}

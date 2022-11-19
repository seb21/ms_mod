package mod.mod;

import android.content.Context;
import android.widget.Toast;
import static mod.mod.Final.*;
import static mod.mod.Start.miscFunc;

public class BridgeMs2Mod {
    public static Context context;
    public static void killAllAppButSome(){
        // Replace in app/ad.smali
        //invoke-static {}, Lapp/ad;->o()V
        // with
        // invoke-static {}, Lmod/mod/BridgeMs2Mod;->killAllAppButSome()V
        if (!miscFunc[noKill]){
            BridgeMod2Ms.KillAll();
        }
    }
    public static void Start(Context context1){
        // put
        // invoke-static {p0}, Lmod/mod/BridgeMs2Mod;->Start(Landroid/content/Context;)V
        // after
        // invoke-static {p0}, Lapp/Receiver;->a(Landroid/content/Context;)V
        // in app/App.smali
        context=context1;
        Start.setUp(context);
    }
    public static int keyChange(int code,int action){
        //put
        // invoke-static {p0, p1}, Lmod/mod/BridgeMs2Mod;->keyChange(II)I
        //
        //    move-result p0
        // after
        //sget v0, Lmodule/i/e;->E:I
        // and before
        //packed-switch p0, :pswitch_data_0
        //
        // sget v2, Lmodule/i/e;->bt:I
        // in module/canbus/a/w

            code = CanSWC.keyFunction(code, action,keyCan);

        return code;
    }
    public static int keyChangeUnit(int code,int action){
        //put
        // invoke-static {p0, p1}, Lmod/mod/BridgeMs2Mod;->keyChange(II)I
        //
        //    move-result p0
        // after
        //sget v0, Lmodule/i/e;->E:I
        // and before
        //packed-switch p0, :pswitch_data_0
        //
        // sget v2, Lmodule/i/e;->bt:I
        // in module/canbus/a/w
        if(miscFunc[unitKeys]) {
            code = code - 220;
            code = CanSWC.keyFunction(code, action, keyUnit);
        }else {
            CanSWC.action1=action;
        }
       // code=code;
        return code;
    }
    public static int keyChangeAnalog(int code,int action){
        //put
        // invoke-static {p0, p1}, Lmod/mod/BridgeMs2Mod;->keyChange(II)I
        //
        //    move-result p0
        // after
        //sget v0, Lmodule/i/e;->E:I
        // and before
        //packed-switch p0, :pswitch_data_0
        //
        // sget v2, Lmodule/i/e;->bt:I
        // in module/canbus/a/w
        if(miscFunc[analogKeys]) {
            code = CanSWC.keyFunction(code, action, keySteer);
        }else{
            CanSWC.action1=action;
        }
        return code;
    }
}

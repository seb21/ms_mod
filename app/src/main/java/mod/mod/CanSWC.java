package mod.mod;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Looper;

import java.util.Calendar;
import java.util.Date;

import static mod.mod.Final.*;
import static mod.mod.Start.keysFunctions;

public class CanSWC {
    public static int sourceSelect = 0;
    public static boolean keyPressed=false;
    public static int lastKey;
    public static int nextPress;
    public static int action1;
    public static int code1;
    public static int secCode;
    public static int secAction;
    public static int lastK;
    public static int what;
    public static boolean done = false;
    public static boolean doNotReact =false;
    public static Boolean doNothing = false;
    public static Boolean timerWork = false;
    public static CountDownTimer timer;
    public static boolean count = false;
    public static boolean sent = false;
    public static boolean dbc = false;



    public static int keyFunction(int code, int action, int what1) {
        code=code+what1;
        what=what1;
        action1 = action;
        code1 = code;
        if (Start.setApp) {
            Start.codeIntent(code-what1,what1,action);
            code = -1;
        } else {
            if (code != 7+what1 & code != 8+what1) {

                if(!doNotReact) {
                    if (!done) {
                        if (!timerWork) {
                            sent = false;
                            nextPress = 99;
                        }
                        if (timerWork & action1 == 0) {
                            count = true;
                        }
                        if (!timerWork & action == 0) {
                            count = false;
                            nextPress = code1;
                            keyPressed = true;
                            Start.keyCIntent();
                        }
                        action1=1;
                        code = -1;
                    } else {
                        if (done & sent) {
                            done = false;
                            action1 = 0;
                        }
                    }
                }else {
                    doNotReact=false;
                    if(action==1){
                        action1=1;
                        code=-1;
                    }
                }

            }if(code!=-1) {
                if (what1 == keyUnit) {
                    code = code + 120;
                } else if (what1 == keySteer) {
                    code = code - keySteer;
                }
            }
        }
   // System.out.println("returm :"+code+"   action:"+action);
        return code;
    }
}

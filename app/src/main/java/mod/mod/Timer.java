package mod.mod;



import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static mod.mod.CanSWC.action1;
import static mod.mod.CanSWC.code1;
import static mod.mod.CanSWC.doNothing;
import static mod.mod.CanSWC.lastK;
import static mod.mod.CanSWC.nextPress;
import static mod.mod.CanSWC.timerWork;
import static mod.mod.Final.*;
import static mod.mod.Start.*;
import static mod.mod.CanSWC.*;

public class Timer {
    public static Runnable a;
    public static boolean work=false;
    public static boolean aaa=false;
    public static int toGo=1500;
    public static void KeyTimer(){
       if(!work) {
           a = () -> TimerThread();
           ScheduledExecutorService execKey = Executors.newScheduledThreadPool(1);
           execKey.scheduleAtFixedRate(a,0,50, TimeUnit.MILLISECONDS);
           work=true;
       }
    }
    public static void TimerThread(){
        int code2=-1;
       if(!timerWork){
           toGo=pressLong;
       }

       if (keyPressed){

            toGo=toGo-50;
            timerWork=true;

            if(action1==0) {
                if (keysFunctions[code1].doubleKey != -1) {
                    if (toGo > (pressLong-pressDouble)) {

                        if ((code1 == nextPress) & count) {

                            switch (keysFunctions[code1].doubleKey) {
                                case uApk:
                                    if (keysFunctions[code1].dPackage != "") {
                                        action1 = 0;
                                        packageIntent(keysFunctions[code1].dPackage, false);
                                    }
                                    break;
                                case uSources:
                                    action1 = 0;
                                    packageIntent("", true);
                                    break;
                                default:
                                    done = true;
                                    action1 = 0;
                                    sent = true;
                                    keyIntent(keysFunctions[code1].doubleKey);
                            }

                            timerWork = false;
                            keyPressed=false;
                        }
                    } else {
                        if (keysFunctions[code1].longKey == -1) {

                            switch (keysFunctions[code1].assignedKey) {
                                case -1:
                                    done = true;
                                    action1 = 0;
                                    sent = true;

                                    switch (what){
                                        case keyUnit:
                                            code2=code1-what+220;
                                            break;
                                        case keySteer:
                                            code2=code1-what;
                                            break;
                                        default:
                                            code2=code1;
                                    }
                                    keyIntent(code2);
                                    break;
                                case uApk:
                                    if (keysFunctions[code1].aPackage != "") {
                                        action1 = 0;
                                        packageIntent(keysFunctions[code1].aPackage, false);
                                    }
                                    break;
                                case uSources:
                                    action1 = 0;
                                    packageIntent("", true);
                                    break;
                                default:
                                    done = true;
                                    action1 = 0;
                                    sent = true;
                                    keyIntent(keysFunctions[code1].assignedKey);
                            }
                            timerWork = false;
                            keyPressed=false;
                        }

                    }
                }
            }

            if(action1==1){
                if(!sent){

                    if(toGo<(pressLong-pressDouble)|keysFunctions[code1].doubleKey==-1) {

                        if (keysFunctions[code1].assignedKey == -1) {
                            done = true;
                            action1 = 0;
                            sent = true;
                            switch (what){
                                case keyUnit:
                                    code2=code1-what+220;
                                    break;
                                case keySteer:
                                    code2=code1-what;
                                    break;
                                default:
                                    code2=code1;
                            }
                            keyIntent(code2);
                        } else {

                            switch (keysFunctions[code1].assignedKey) {
                                case uApk:
                                    if (keysFunctions[code1].aPackage != "") {
                                        action1 = 0;
                                        packageIntent(keysFunctions[code1].aPackage, false);
                                    }
                                    break;
                                case uSources:
                                    action1 = 0;
                                    packageIntent("", true);
                                    break;
                                default:

                                    done = true;
                                    action1 = 0;
                                    sent = true;
                                    keyIntent(keysFunctions[code1].assignedKey);
                            }
                        }
                        timerWork=false;
                        keyPressed=false;
                    }


                }
            }


        if(toGo<=0) {
            if (!sent) {

                if (keysFunctions[code1].longKey == -1) {
                    done = true;
                    action1 = 0;
                    sent = true;
                } else {
                    switch (keysFunctions[code1].longKey) {
                        case uApk:
                            if (keysFunctions[code1].lPackage != "") {
                                action1 = 0;
                                packageIntent(keysFunctions[code1].lPackage, false);
                            }
                            break;
                        case uSources:
                            action1 = 0;
                            packageIntent("", true);
                            break;
                        default:
                            done = true;
                            action1 = 0;
                            sent = true;
                            keyIntent(keysFunctions[code1].longKey);
                    }
                }
            }
            timerWork = false;
            keyPressed=false;
        }
        }
    }
}

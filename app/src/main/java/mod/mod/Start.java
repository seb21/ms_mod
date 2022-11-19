package mod.mod;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static mod.mod.Final.*;
import static mod.mod.Settings.editor;
import static mod.mod.Settings.saveValue;
import static mod.mod.CanSWC.*;
import static mod.mod.MFD.*;

public class Start {
    public static final Boolean debug = true;
    public static boolean hasSources = false;
    public static int pos, value;
    public static int sourcesCount = 0;
    public static Boolean valueB;
    public static String name;
    public static Boolean setApp = false;
    public static String[] sourcesList = new String[20];
    public static boolean[] sourcesListPlay = new boolean[20];
    public static boolean[] sourcesListKill = new boolean[20];
    public static keys[] keysFunctions = new keys[300];
    public static block[] blockMcu = new block[21];
    public static boolean[] miscFunc = new boolean[30];
    public static boolean[] miscRec = new boolean[30];
    public static boolean allowPass = false;
    public static int pressLong = pressLongDef;
    public static int pressDouble = pressDoubleDef;
    public static Context context1;

    public static void setUp(Context context) {
        context1=context;
        for (int i = 0; i < keysFunctions.length; i++) {
            keysFunctions[i] = new keys();
            keysFunctions[i].aApkName = "";
            keysFunctions[i].lApkName = "";
            keysFunctions[i].dApkName = "";
            keysFunctions[i].assignedKey = -1;
            keysFunctions[i].doubleKey = -1;
            keysFunctions[i].longKey = -1;
            keysFunctions[i].aPackage = "";
            keysFunctions[i].lPackage = "";
            keysFunctions[i].dPackage = "";
        }
        for(int i=0;i<blockMcu.length;i++){
            blockMcu[i] = new block();
            blockMcu[i].howMany=0;
            blockMcu[i].name="";
            blockMcu[i].used=false;
            for (int t=0;t<blockMcu[i].val.length;t++){
                blockMcu[i].val[t]=0;
            }
        }
        Settings.sharedPref= BridgeMs2Mod.context.getSharedPreferences(Settings.prefFile, BridgeMs2Mod.context.MODE_PRIVATE);
        editor=Settings.sharedPref.edit();
        Settings.LoadSettings();
        SettingsReceiver(context);
        KeyToastReceiver(context);
        WriteMcuReceiver(context);
        WriteCanbusDirect(context);
        SettingsSWCReceiver(context);
        KeyCodeReceiver(context);
        packageRunner(context);
        keyRec(context);
        miscRec[spotifyRec]=false;
        miscRec[navRadioRec]=false;
            SpotifyReceiver(context);
            navRadioRec(context);
    }

    public static void SettingsReceiver(Context context) {

        IntentFilter modSet = new IntentFilter("modSettings");
        BroadcastReceiver modSetReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("modSettings")) {
                    if (intent.hasExtra(assigned)) {
                        pos = intent.getIntExtra(assigned, -1);
                        value = intent.getIntExtra(valueStr, -1);
                        keysFunctions[pos].assignedKey = value;
                        saveValue(assignedVal, pos, value, "");

                    }
                    if (intent.hasExtra(longPress)) {
                        pos = intent.getIntExtra(longPress, -1);
                        value = intent.getIntExtra(valueStr, -1);
                        keysFunctions[pos].longKey = value;
                        saveValue(longVal, pos, value, "");
                    }
                    if (intent.hasExtra(doubleClick)) {
                        pos = intent.getIntExtra(doubleClick, -1);
                        value = intent.getIntExtra(valueStr, -1);
                        keysFunctions[pos].doubleKey = value;
                        saveValue(doubleVal, pos, value, "");
                    }
                    if (intent.hasExtra(assignedApk)) {
                        pos = intent.getIntExtra(assignedApk, -1);
                        name = intent.getStringExtra(valueStr);
                        keysFunctions[pos].aApkName = name;
                        saveValue(assignApkVal, pos, 0, name);
                    }
                    if (intent.hasExtra(longPressApk)) {
                        pos = intent.getIntExtra(longPressApk, -1);
                        name = intent.getStringExtra(valueStr);
                        keysFunctions[pos].lApkName = name;
                        saveValue(longApkVal, pos, 0, name);
                    }
                    if (intent.hasExtra(doubleClickApk)) {
                        pos = intent.getIntExtra(doubleClickApk, -1);
                        name = intent.getStringExtra(valueStr);
                        keysFunctions[pos].dApkName = name;
                        saveValue(doubleApkVal, pos, 0, name);
                    }
                    if (intent.hasExtra(misc)) {
                        pos = intent.getIntExtra(misc, -1);
                        valueB = intent.getBooleanExtra(valueStr, false);
                        miscFunc[pos] = valueB;
                        if (valueB == true) {
                            saveValue(miscVal, pos, 1, "");
                        } else saveValue(miscVal, pos, 0, "");
                            SpotifyReceiver(context);
                            navRadioRec(context);
                    }
                    if (intent.hasExtra(userSources)) {
                        if(intent.getStringArrayExtra(valueStr)!=null & intent.getBooleanArrayExtra("value1")!=null) {
                            sourcesList = intent.getStringArrayExtra(valueStr);
                            sourcesListPlay = intent.getBooleanArrayExtra("value1");
                            sourcesListKill = intent.getBooleanArrayExtra("value2");
                        }
                        sourcesCount=0;
                        hasSources=false;
                        for(int i =0;i<20;i++){
                            if(sourcesList[i].length()>0){
                                hasSources=true;
                                sourcesCount++;
                            }
                        }
                        saveValue(userVal, 0, 0, "");
                    }
                    if (intent.hasExtra(assignedPack)) {
                        pos = intent.getIntExtra(assignedPack, -1);
                        name = intent.getStringExtra(valueStr);
                        keysFunctions[pos].aPackage = name;
                        saveValue(assignPackVal, pos, 0, name);
                    }
                    if (intent.hasExtra(longPack)) {
                        pos = intent.getIntExtra(longPack, -1);
                        name = intent.getStringExtra(valueStr);
                        keysFunctions[pos].lPackage = name;
                        saveValue(longPackVal, pos, 0, name);
                    }
                    if (intent.hasExtra(doublePack)) {
                        pos = intent.getIntExtra(doublePack, -1);
                        name = intent.getStringExtra(valueStr);
                        keysFunctions[pos].dPackage = name;
                        saveValue(doublePackVal, pos, 0, name);
                    }
                    if (intent.hasExtra(resetAll)){
                        editor.clear();
                        editor.apply();
                        hasSources=false;
                        sourcesCount=0;
                        Settings.LoadSettings();
                    }
                    if (intent.hasExtra(blockMcuSet+"value")){
                        int[][] tempVal = (int[][]) intent.getSerializableExtra(blockMcuSet+"value");
                        int[] tempHowMany= intent.getIntArrayExtra(blockMcuSet+"howmany");
                        boolean[] tempUsed = intent.getBooleanArrayExtra(blockMcuSet+"used");
                        String[] tempName = intent.getStringArrayExtra(blockMcuSet+"name");
                        int count=0;
                        for(int i=0;i<blockMcu.length;i++) {
                            if (tempUsed[count]) {
                                for (int t = 0; t < blockMcu[i].val.length; t++) {
                                    blockMcu[count].val[t] = tempVal[i][t];
                                }
                                blockMcu[count].used = tempUsed[i];
                                blockMcu[count].name = tempName[i];
                                blockMcu[count].howMany = tempHowMany[i];
                                count++;
                                WriteMcuDis.count=count;

                            }
                        }
                        Settings.saveValue(blockMcuVal,0,0,"");
                    }
                    if(intent.hasExtra(press+"L")){
                        pressLong=intent.getIntExtra(press+"L",pressLongDef);
                        Settings.saveValue(pressVal,0,0,"");
                    }
                    if(intent.hasExtra(press+"D")){
                        pressDouble=intent.getIntExtra(press+"D",pressDoubleDef);
                        Settings.saveValue(pressVal,0,0,"");
                    }
                }
            }
        };
        modSetReceiver.goAsync();
        BridgeMs2Mod.context.registerReceiver(modSetReceiver, modSet);
    }

    public static void KeyToastReceiver(Context context) {
        IntentFilter toast = new IntentFilter("toast");
        BroadcastReceiver toastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("toast")) {
                    String txt = "";
                    txt = intent.getStringExtra("txt");
                    Toast.makeText(context, txt, Toast.LENGTH_SHORT).show();
                }
            }
        };
        toastReceiver.goAsync();
        context.registerReceiver(toastReceiver, toast);
    }

    public static void WriteMcuReceiver(Context context) {
        IntentFilter writeMcu = new IntentFilter("writeMcu");
        BroadcastReceiver writeMcuReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("writeMcu")) {
                    int[] args = intent.getIntArrayExtra("args");
                    if (args != null) {
                        allowPass = true;
                        MsMcu.WriteMcu(args);
                    }
                }
            }
        };
        writeMcuReceiver.goAsync();
        context.registerReceiver(writeMcuReceiver, writeMcu);
    }
    public static void WriteCanbusDirect(Context context) {
        IntentFilter writeMcu = new IntentFilter("writeCanbus");
        BroadcastReceiver writeMcuReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("writeCanbus")) {
                    byte[] args = intent.getByteArrayExtra("args");
                    if (args != null) {
                        //allowPass = true;
                        Canbus.writeObd(args);
                    }
                }
            }
        };
        writeMcuReceiver.goAsync();
        context.registerReceiver(writeMcuReceiver, writeMcu);
    }

    public static void SpotifyReceiver(Context context) {
        IntentFilter spotify = new IntentFilter("com.spotify.music.metadatachanged");
        spotify.addAction("com.spotify.music.playbackstatechanged");
        BroadcastReceiver spotifyReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals("com.spotify.music.metadatachanged")) {
                    String trackId = intent.getStringExtra("id");
                    String artistName = intent.getStringExtra("artist");
                    String albumName = intent.getStringExtra("album");
                    String trackName = intent.getStringExtra("track");
                    int trackLengthInSec = intent.getIntExtra("length", 0);
                    Tools.id3Cmd((byte) 113, trackName);
                    Tools.id3Cmd((byte) 114, artistName);
                } else if (action.equals("com.spotify.music.playbackstatechanged")) {

                }
            }
        };
        if(miscFunc[spotifyRec]&!miscRec[spotifyRec]) {
            spotifyReceiver.goAsync();
            context.registerReceiver(spotifyReceiver, spotify);
            miscRec[spotifyRec]=true;
        }else if (!miscFunc[spotifyRec]&miscRec[spotifyRec]){
            context.unregisterReceiver(spotifyReceiver);
            miscRec[spotifyRec]=false;
        }

    }

    public static void SettingsSWCReceiver(Context context) {
        IntentFilter setapp = new IntentFilter("settingApp");
        BroadcastReceiver setappReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("settingApp")) {
                    setApp = intent.getBooleanExtra("SWC", false);
                }
            }
        };
        setappReceiver.goAsync();
        context.registerReceiver(setappReceiver, setapp);
    }

    public static void KeyCodeReceiver(Context context) {
        IntentFilter keyC = new IntentFilter("keycode");
        BroadcastReceiver keyCReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("keycode")) {
                    Timer.KeyTimer();

                }
            }
        };
        keyCReceiver.goAsync();
        context.registerReceiver(keyCReceiver, keyC);
    }

    public static void codeIntent(int code,int what,int action) {
        Intent intent = new Intent();
        intent.setAction("ms.swc");
        intent.putExtra("code", code);
        intent.putExtra("keySource",what);
        intent.putExtra("action",action);
        BridgeMs2Mod.context.sendBroadcast(intent);
    }

    public static void keyCIntent() {
        Intent intent = new Intent();
        intent.setAction("keycode");
        intent.putExtra("ala", 10);
        BridgeMs2Mod.context.sendBroadcast(intent);
    }

    public static void packageIntent(String name, boolean uSource) {
        Intent intent = new Intent();
        intent.setAction("packageRun");
        int a = 0;
        int b= sourceSelect;
        if (uSource & hasSources) {
            if (sourceSelect + 1 >= sourcesCount) {
                sourceSelect = 0;
            } else {
                sourceSelect++;
            }
            
            a = sourceSelect;
            if (sourcesList[a].length() >0) {
                intent.putExtra("package", sourcesList[a]);
                intent.putExtra("play", sourcesListPlay[a]);
                intent.putExtra("kill",b);
                intent.putExtra("kill1",sourcesListKill[b]);
            }
        } else {
            intent.putExtra("package", name);
        }
        BridgeMs2Mod.context.sendBroadcast(intent);
    }

    public static void packageRunner(Context context) {
        IntentFilter pack = new IntentFilter("packageRun");
        BroadcastReceiver packReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("packageRun")) {
                    String pac = "";
                    pac = intent.getStringExtra("package");
                    if(intent.getBooleanExtra("kill1",false)){
                       int b=intent.getIntExtra("kill",0);
                        if(sourcesList[b].length()>0){
                            KillApp.KillApp(sourcesList[b]);
                        }
                    }
                    if (pac.length()>0) {
                        Intent launchIntent = BridgeMs2Mod.context.getPackageManager().getLaunchIntentForPackage(pac);
                        launchIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        BridgeMs2Mod.context.startActivity(launchIntent);


                        if (intent.hasExtra("play")) {
                            if (intent.getBooleanExtra("play", false)) {
                                try {
                                    TimeUnit.MILLISECONDS.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                               KeyCodes.keyPlayPause16(0);
                            }
                        }
                       if (miscFunc[backToHome]& intent.hasExtra("play")) {
                           try {
                               TimeUnit.MILLISECONDS.sleep(1000);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                           KeyCodes.keyHome5(0);
                        }
                    }
                }
            }
        };

        packReceiver.goAsync();
        context.registerReceiver(packReceiver, pack);
    }

    public static void keyIntent(int code) {
        Intent intent = new Intent();
        intent.setAction("keyRun");
        intent.putExtra("code", code);
       // System.out.println("key I code:"+code);
        BridgeMs2Mod.context.sendBroadcast(intent);
    }

    public static void keyRec(Context context) {
        IntentFilter keyC = new IntentFilter("keyRun");
        BroadcastReceiver keyCReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("keyRun")) {
                    CallKeyCode.callMsKeyCode(intent.getIntExtra("code", -1), 0);

                }
            }
        };
        keyCReceiver.goAsync();
        context.registerReceiver(keyCReceiver, keyC);
    }

    public static void navRadioRec(Context context){
        IntentFilter navRadio = new IntentFilter("com.navimods.radio.info.frequency");
        BroadcastReceiver navRadioReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("com.navimods.radio.info.frequency")) {
                    int freq = intent.getIntExtra("value",0);
                    int B7 = freq/256;
                    int B6 =freq-(B7*256);
                    int[] args = {can,media,8,radio,0,fm,B6,B7,1,0,0};
                    allowPass = true;
                    MsMcu.WriteMcu(args);
                }
            }
        };
        if(miscFunc[navRadioRec]&!miscRec[navRadioRec]) {
            navRadioReceiver.goAsync();
            context.registerReceiver(navRadioReceiver, navRadio);
            miscRec[navRadioRec]=true;
        }else if (!miscFunc[navRadioRec]&miscRec[navRadioRec]){
            context.unregisterReceiver(navRadioReceiver);
            miscRec[navRadioRec]=false;
        }
    }

}

package mod.mod;

import android.content.SharedPreferences;
import static mod.mod.Final.*;
import static mod.mod.Start.miscFunc;
import static mod.mod.Start.keysFunctions;
import static mod.mod.Start.pressDouble;
import static mod.mod.Start.pressLong;
import static mod.mod.Start.sourcesList;
import static mod.mod.Start.sourcesListKill;
import static mod.mod.Start.sourcesListPlay;
import static mod.mod.Start.blockMcu;

public class Settings {
    public static String[] settingVal = {assigned, longPress, doubleClick, misc, assignedApk, longPressApk, doubleClickApk, userSources,assignedPack,longPack,doublePack,userSourcesLabel};

    public static SharedPreferences sharedPref;
    public static SharedPreferences.Editor editor;
    public static final String prefFile = "ms.mod.settings";
    public static void LoadSettings(){

        for(int i=0;i<keysFunctions.length;i++){
            keysFunctions[i].assignedKey=sharedPref.getInt(assigned+i,-1);
            keysFunctions[i].longKey=sharedPref.getInt(longPress+i,-1);
            keysFunctions[i].doubleKey=sharedPref.getInt(doubleClick+i,-1);
            keysFunctions[i].aApkName=sharedPref.getString(assignedApk+i,"");
            keysFunctions[i].lApkName=sharedPref.getString(longPressApk+i,"");
            keysFunctions[i].dApkName=sharedPref.getString(doubleClickApk+i,"");
            keysFunctions[i].aPackage=sharedPref.getString(assignedPack+i,"");
            keysFunctions[i].lPackage=sharedPref.getString(longPack+i,"");
            keysFunctions[i].dPackage=sharedPref.getString(doublePack+i,"");
        }
        for (int i=0;i<miscFunc.length;i++){
            miscFunc[i]=sharedPref.getBoolean(misc+i,false);
        }
        Start.sourcesCount=0;
        Start.hasSources=false;
        for (int i=0;i<sourcesList.length;i++){

            sourcesList[i]=sharedPref.getString(userSources+i,"");
            sourcesListPlay[i]=sharedPref.getBoolean(userSourcesPlayState+i,false);
            sourcesListKill[i]=sharedPref.getBoolean(userSourcesKillState+i,false);
            if(sourcesList[i].length()>0){
                Start.sourcesCount++;
                Start.hasSources=true;
            }
        }
        for (int i=0;i<blockMcu.length;i++){
            blockMcu[i].name=sharedPref.getString(blockMcuSet+"name"+i,"");
            blockMcu[i].used=sharedPref.getBoolean(blockMcuSet+"used"+i,false);
            blockMcu[i].howMany=sharedPref.getInt(blockMcuSet+"howmany"+i,0);
            for(int t=0;t<blockMcu[i].val.length;t++){
                blockMcu[i].val[t]=sharedPref.getInt(blockMcuSet+"val"+i+":"+t,0);
            }
        }
            WriteMcuDis.count=sharedPref.getInt(blockMcuSet+"count",0);
        pressLong=sharedPref.getInt(press+"L",pressLongDef);
        pressDouble=sharedPref.getInt(press+"D",pressDoubleDef);
    }

    public static void saveValue(int what, int key, int func, String app) {
        boolean fu = false;
        switch (what) {
            case assignedVal:
            case longVal:
            case doubleVal:
                editor.putInt(settingVal[what] + key, func);
                editor.apply();
                break;
            case miscVal:
                if (func == 1) {
                    fu = true;
                } else fu = false;
                editor.putBoolean(settingVal[what] + key, fu);
                editor.apply();
                break;
            case longApkVal:
            case doubleApkVal:
            case assignApkVal:
            case assignPackVal:
            case doublePackVal:
            case longPackVal:
                editor.putString(settingVal[what] + key, app);
                editor.apply();
                break;
            case userVal:
                for (int i = 0; i < sourcesList.length; i++) {
                    if (sourcesList[i] != "") {
                        editor.putString(settingVal[what] + i, sourcesList[i]);
                        editor.apply();
                        editor.putBoolean(userSourcesPlayState + i, sourcesListPlay[i]);
                        editor.apply();
                        editor.putBoolean(userSourcesKillState + i, sourcesListKill[i]);
                        editor.apply();
                    }
                }
            case blockMcuVal:
                for(int i=0;i<blockMcu.length;i++){
                    editor.putString(blockMcuSet+"name"+i,blockMcu[i].name);
                    editor.putBoolean(blockMcuSet+"used"+i,blockMcu[i].used);
                    editor.putInt(blockMcuSet+"howmany"+i,blockMcu[i].howMany);
                    for(int t=0;t<blockMcu[i].val.length;t++){
                        editor.putInt(blockMcuSet+"val"+i+":"+t,blockMcu[i].val[t]);
                    }
                }
                editor.putInt(blockMcuSet+"count",WriteMcuDis.count);
                editor.apply();
                break;
            case pressVal:
                editor.putInt(press+"L",pressLong);
                editor.putInt(press+"D",pressDouble);
                editor.apply();
                break;
        }
    }
}

package mod.mod;

import java.util.Arrays;

public class dummy {

    public static void killAll(){}
    public static void ala(int[] args){
      args=  writeMcuLogger.spy(args);
    }
    public static void ret(int action){
        action=ReturnAction.returnAction();
    }
    public static void keycode(int code,int action){
     //   System.out.println("code:"+code);
      //  System.out.println("assigned:"+Start.keysFunctions[code+Final.keySteer].assignedKey);
      //  System.out.println("long:"+Start.keysFunctions[code+Final.keySteer].longKey);
      //  System.out.println("double:"+Start.keysFunctions[code+Final.keySteer].doubleKey);
    int code2=CanSWC.keyFunction(code,action,Final.keyUnit);
       int action2=ReturnAction.returnAction();
    //System.out.println("code:"+code2+"    actiom:"+action2);
    }
    public static void writeMcu(int[] args){
        args=WriteMcuDis.WriteMcuDisable(args);
        System.out.println(Arrays.toString(args));
    }
    public static void writeCanbusDirect(byte[] args){

    }
    public static void m8471a(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr2.length; i++) {
            bArr2[i] = bArr[i];
        }
    }
    public static void k5(int i){System.out.println("k5");}
    public static void k16(int i){System.out.println("k16");}
    public static void killApp(String app){
        int a=10;
        CarId.GetCarID(a);
    }
    public static void keyTester(){
        if(MainActivity.pressed){

        }
    }
    public static void katcz(byte[] a,int i,int i2){
            CanCatcherMS.catchCan(a,i,i2);
        switch (i){
            case -29:

        }
    }
}

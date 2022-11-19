package mod.mod;

import android.content.Intent;
import android.widget.Toast;

public class Tools {
    public static void id3Cmd(byte cmd, String str) {
        int len = 44;
        if (str == null) {
            str = "";
        }
        byte[] nums = str.getBytes();
        if (nums.length <= 44) {
            len = nums.length;
        }
        int[] data = new int[len + 4];
        data[0] = 227;
        data[1] = cmd;
        data[2] = len + 1;
        data[3] = 1;
        for (int i = 0; i < len; i++) {
            data[i + 4] = nums[i];
        }
        MsMcu.WriteMcu(data);
    }
    public static int[] spyMCU(int[] args){
            Intent intent = new Intent();
            intent.setAction("spyMCU");
            intent.putExtra("args",args);
            if (BridgeMs2Mod.context != null) {
                BridgeMs2Mod.context.sendBroadcast(intent);
            }

    return args;
    }
    public static void sendToastIntent(String txt){
        Intent intent = new Intent();
        intent.setAction("toast");
        intent.putExtra("txt",txt);
        if(BridgeMs2Mod.context !=null){
            BridgeMs2Mod.context.sendBroadcast(intent);
        }
    }
}

package mod.mod;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static mod.mod.Start.allowPass;
import static mod.mod.Start.blockMcu;
import static mod.mod.Start.miscFunc;
import static mod.mod.Final.*;

public class MainActivity extends AppCompatActivity {
    public static int kc=2;
    public static int action2=0;
    public static  int action3=0;
    public static  int cnt=0;
    public static boolean pressed=false;
    public static int[] testArr= {10,20,30,40};
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        BridgeMs2Mod.context = getContext(this);
        setContentView(R.layout.activity_main);
        Start.setUp(this);
        Start.allowPass=true;


        Button button = findViewById(R.id.button);
        Button button1 = findViewById(R.id.button2);
        Button button2 = findViewById(R.id.button3);
        Button button3 = findViewById(R.id.button4);
        TextView textView = findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            kc--;
            if(kc<0){kc=0;}
            textView.setText(Integer.toString(kc));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kc++;
                textView.setText(Integer.toString(kc));
            }
        });
        button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                action3=event.getAction();

                if (action3!=action2){
                    action2=action3;
                   // System.out.println("ac3"+action3);
                    dummy.keycode(kc,action3);
                }else {

                }
                return false;
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int[] a =new int[3];
                a[0]=227;
                a[1]=kc;
                a[2]=10;
                dummy.writeMcu(a);
            }
        });
        IntentFilter intentFilter = new IntentFilter("AUTONAVI_STANDARD_BROADCAST_SEND");
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getExtras();
            }
        };
        this.registerReceiver(receiver,intentFilter);
    }
    public static Context getContext(Context context){
        return context;
    }
}

package mod.mod;

public class CanTest {
    static int count=0;
    static int[] args = new int[4];
    public static void SourceId(){
        args[0]=227;
        args[1]=192;
        args[2]=8;
    switch (count){
        case 0:
            args[3]=0;
            args[4]=0;
            break;
        case 1:
            args[3]=1;
            args[4]=1;
            break;
        case 2:
            args[3]=5;
            args[4]=64;
            break;
        case 3:
            args[3]=11;
            args[4]=0;
            break;
        case 4:
            args[3]=6;
            args[4]=18;
            break;
        case 5:
            args[3]=9;
            args[4]=19;
            break;
    }
    MsMcu.WriteMcu(args);
    count++;
    if(count>5)count=0;
    }
}

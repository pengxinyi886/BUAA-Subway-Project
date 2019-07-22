
public class Subway {
    public static void main(String [] args){
        new Subway();
        if(args.length==2) {
            new FileOperateOp(args[0], args[1]);
        }else if(args.length==6){
            new FileOperateOp(args[0],args[1],args[2],args[3],args[4],args[5]);
        }else if(args.length==7){
            new FileOperateOp(args[0],args[1],args[2],args[3],args[4],args[5],args[6]);
        }else{
            System.out.println("command error!");
        }
    }
}

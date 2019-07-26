import main.com.core.DijkstraUtil;
import main.com.data.DistanceBuilder;
import main.com.model.Result;
import main.com.model.Station;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class subway {
    public static void main(String[] args) throws IOException {

       switch (args[0]){
           
           case "-map":
               //-map subway.txt
               //System.out.println("1111");
               if(args.length==2){
                   
                   DistanceBuilder.FILE_PATH = System.getProperty("user.dir") + File.separator + "\\" + args[1];
                   //根据路径，读取地铁信息，并打印。
                   DistanceBuilder.readSubway();
               }else{
                   System.out.println("验证参数格式！");
               }
               break;

           case "-a":
               //-a 1号线 -map subway.txt -o station.txt
               if(args.length==6){
                   DistanceBuilder.FILE_PATH = System.getProperty("user.dir") + File.separator + "\\" + args[3];
                   DistanceBuilder.WRITE_PATH = System.getProperty("user.dir") + File.separator + "\\" + args[5];
                   DistanceBuilder.readSubway();
                   System.out.println(DistanceBuilder.writeLineData(args[1]));
                   System.out.println("已将结果写入station.txt文件");
               }else{

                   System.out.println("验证参数格式！");
               }
               break;
           case "-b":
               //-b 洪湖里 复兴路 -map subway.txt -o routine.txt
               Arrays.stream(args).forEach(System.out::println);
               if(args.length==7){
                   DistanceBuilder.FILE_PATH = System.getProperty("user.dir") + File.separator + "\\" + args[4];
                   DistanceBuilder.WRITE_PATH = System.getProperty("user.dir") + File.separator + "\\" + args[6];
                   DistanceBuilder.readSubway();
                   Result result = DijkstraUtil.calculate(new Station(args[1]), new Station(args[2]));
                   DistanceBuilder.writePassStationLine(result);
                   System.out.println("已将结果写入routine. txt文件");
               }else{
                   System.out.println("验证参数格式！");
               }
               break;
       }
    }
}

package main.com.TestMain;

import main.com.core.DijkstraUtil;
import main.com.data.DistanceBuilder;
import main.com.model.Result;
import main.com.model.Station;

import java.io.File;

public class test {
    public static void main(String[] args) {
       //==== 读取subway.txt文件========
         read();
        //==== 读取caculate 计算两个站点之间怎么乘 ====
        Result result = DijkstraUtil.calculate(new Station("洪湖里"),new Station("辽河北道"));

        // DistanceBuilder.getPassStation(result);

        //===
        DistanceBuilder.WRITE_PATH  = System.getProperty("user.dir") + File.separator + "\\resource\\routine.txt";
        DistanceBuilder.writePassStationLine(result);
    }


    public static void read(){
        DistanceBuilder.FILE_PATH = System.getProperty("user.dir") + File.separator + "\\resource\\subway.txt";
        DistanceBuilder.readSubway();
    }
}

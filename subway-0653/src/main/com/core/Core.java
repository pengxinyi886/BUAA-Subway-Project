package main.com.core;

import main.com.data.DistanceBuilder;
import main.com.model.Result;
import main.com.model.Station;

public class Core {
   public Result  getShort(Station start,Station end ,String path){
       DistanceBuilder.FILE_PATH = "E:\\pxy\\se培训学习\\项目案例\\BUAA-Subway-Project\\subway-0653\\subway.txt";

       DistanceBuilder.readSubway();
       Result  value = DijkstraUtil.calculate(start,end);

       return  value;
   }
}

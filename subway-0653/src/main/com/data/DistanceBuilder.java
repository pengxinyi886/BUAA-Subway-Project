package main.com.data;

import main.com.model.Result;
import main.com.model.Station;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class DistanceBuilder {

    //读取距离信息  getDistance，通过输入key返回相应距离，key为两个地名，中间加上冒号。
    public  static String FILE_PATH;

    public  static String WRITE_PATH;
    //这里改为HashMap<String,HashMap<String,Double>>  1号线对应一个HashMap,key对应某线路的所有站点。
    //public static HashMap<String, Double> distanceMap = new HashMap<>();
    public static HashMap<String,HashMap<String,Double>> distanceMap = new HashMap<String,HashMap<String,Double>>();
    public static LinkedHashSet<List<Station>> lineSet = new LinkedHashSet<>();//所有线集合
    public static HashMap<String,List<Station>> lineData;
    private DistanceBuilder() {
    }

    static {
        createlineData();
    }
    public static void createlineData(){
        lineData = new HashMap<>();
        for (List<Station> stations : lineSet) {
            lineData.put(stations.get(1).getLine(),stations);
        }
        System.out.println(lineData);
    }
    public static String getLineNameByStation(Station station){
        createlineData();
        String startname = station.getName();
        for (Map.Entry<String,List<Station>> entry : lineData.entrySet()) {
            List<Station> stations =  entry.getValue();
            for (Station sta : stations){
                if(sta.getName().equals(startname)){
                    return entry.getKey();
                }
            }
        }
        return "";
    }
    //传入 AA:BB 通过遍历每条线，在每条线中找AA:BB
    public static Double getDistance(String key) {

        //entrySet返回类型：Set<Map.Entry<String,HashMap<String,Double>>>   get返回符合条件的流中的元素，get返回类型 Map.Entry<String,HashMap<String,Double>>
        return distanceMap.entrySet().stream().filter(x->x.getValue().keySet().contains(key)).findFirst().get().getValue().get(key);
    }
    //key格式为：“ 洪湖里:西站”
    public static String getLineName(String key) {
        //如果某地铁线包含该key，则返回地铁名。 findFirst返回Option<String> 其中String为地铁线名。
        return distanceMap.keySet().stream().filter(x -> distanceMap.get(x).containsKey(key)).findFirst().orElse("");
    }
   /* public static String getLineNameByStation(Station station){
        String name = station.getName();
        lineSet.stream().map(x-> x.stream().filter(x->))
    }*/
    public static boolean isContains(String key){
        return distanceMap.entrySet().stream().anyMatch(x->x.getValue().keySet().contains(key));
    }
    public static ArrayList<Station> getLine(String lineStr,String lineName){
        ArrayList<Station> line =  new ArrayList<Station>();
        String[] lineArr = lineStr.split(",");
        for (String s : lineArr) {
            line.add(new Station(s,lineName));
        }
        return line;
    }

    public static String writeLineData(String lineName){
        createlineData();
        System.out.println(lineName.substring(0,1 ));
        lineName = lineName.substring(0,1);
        List<Station> lineInfo = lineData.get(lineName);
        System.out.println("writeLineData lineInfo"+lineInfo.toString());
        String lineStr = lineInfo.stream().map(x->x.getName()).collect(Collectors.joining(",","[","]"));
        try {
            Files.write(Paths.get(WRITE_PATH), lineStr.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  lineStr;
    }

    public static void getPassStation(Result result){
        Station starStation = result.getStar();
        String starLine = getLineNameByStation(starStation);
        String converLine = starLine;
        System.out.println("起始地铁线："+starLine);
        for (Station station : result.getPassStations()) {
            if(!converLine.equals(station.getLine())){
                System.out.print("换乘地铁线："+station.getLine()+"  ");
                converLine = station.getLine();
                converLine = station.getLine();
            }
            System.out.print(station.getName() + ",");
        }
    }
    public static void writePassStationLine(Result result){
  /*     3
        洪湖里
        西站
        6号线
         复兴路*/
        //首先向文件中写入站台数。
        FileWriter fw= null;
        BufferedWriter bw = null;
        try {
            //追加写入：
            fw = new FileWriter(new File(WRITE_PATH),true);
            bw=new BufferedWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(WRITE_PATH)), "UTF-8")));
            //bw = new BufferedWriter(fw);
            //写入起始点到终止点的站台数。
            bw.write((result.getPassStations().size()+1) + "\t\n");
            //写入起始站名称。
            bw.write(result.getStar().getName() + "\t\n");
            //通过起始站名称获取所在地铁线名称。
            String startLineName = getLineNameByStation(result.getStar());
            //默认转乘地铁的名称和起始站所在地铁线为一样。
            String convertLine = startLineName;
            for (Station station : result.getPassStations()){
                if(!convertLine.equals(station.getLine())){
                    //写入转乘名称：
                    bw.write(station.getLine()+"号线" + "\t\n");
                    convertLine = station.getLine();
                    bw.write(station.getName() + "\t\n");
                }else{
                    bw.write(station.getName() + "\t\n");
                }
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //写入中文字符时会出现乱码
        //BufferedWriter  bw=new BufferedWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8")));

    }
    public static void readSubway() {
        System.out.println("readsubwayTTTT开始位置输出");
        File file = new File(FILE_PATH);
        BufferedReader reader = null;

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file),"UTF-8");

            reader = new BufferedReader(inputStreamReader);
            String line = null;
            //String如：西朗:坑口   double为距离，如：1.6
            //默认起始为1号线
            String lineName = "1";
            distanceMap.put("1",new HashMap<>());

            while ((line = reader.readLine()) != null) {
                //长度为1或2，则该行值为地铁线号。
                //在cmd中读取：锘?2
                if(line.trim().length()==1||line.trim().length()==3||line.trim().length()==2){
                    if(line.trim().length()==3||line.trim().length()==2){ //  \uFEFF 默认以这个开头！！！
                        continue;
                    }
                    lineName = line;
                    //判断distanceMap中是否已创建该地铁线号。如果为创建该地铁线则加入distanceMap
                    if(!distanceMap.keySet().contains(line)){
                        distanceMap.put(line.trim(),new HashMap<>());
                    }
                }else{
                    //判断是否以"*"开头，如果*开头则为某条线的所有站点字符串。
                    if(line.trim().startsWith("*")){
                        String[] lineInfo = line.substring(1).split("-");
                        lineSet.add(getLine(lineInfo[1].trim(),lineInfo[0].trim()));
                    }else{
                        //地铁某站点到某站点信息，如: 客村:广州塔 1
                        String texts[] = line.split("\t");
                        String key = texts[0].trim();
                        Double value = Double.valueOf(texts[1]);
                        distanceMap.get(lineName).put(key,value);
                        String other = key.split(":")[1].trim()+":"+key.split(":")[0].trim();
                        distanceMap.get(lineName).put(other,value);
                    }
                }
            }
            // System.out.println(distanceMap);
            // FileWriter fw= null;
            // BufferedWriter bw = null;
            // try {
            //     //追加写入：
            //     fw = new FileWriter(new File("mian/path.txt"),true);
            //     bw=new BufferedWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(WRITE_PATH)), "UTF-8")));
            //     //bw = new BufferedWriter(fw);
            //     //写入起始点到终止点的站台数。
            //     bw.write(distanceMap);
                
                
            //     bw.close();
            //     fw.close();
            // } catch (IOException e) {
            //     e.printStackTrace();
            // }
        //写入中文字符时会出现乱码
        //BufferedWriter  bw=new BufferedWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8")));


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
        }


    }
}

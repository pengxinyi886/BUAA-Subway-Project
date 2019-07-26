package main.com.core;

import main.com.data.DistanceBuilder;
import main.com.model.Result;
import main.com.model.Station;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DijkstraUtil {
    /**
     * 结果集.
     *resultMap
     */
    private static HashMap<Station, Result> resultMap = new HashMap<>();
    /**
     * 分析过的站点集合.
     *
     */
    private static List<Station> analysisList = new ArrayList<>();

    /**
     * 迪杰斯特拉算法应用在地铁的实现.
     *
     * @param star the star
     * @param end  the end
     * @return the result
     * @since hui_project 1.0.0
     */
    public static Result calculate(Station star, Station end) {
        //将开始站点加入到分析过的站点集合。
        if (!analysisList.contains(star)) {
            analysisList.add(star);
        }
        System.out.println();
        //如果开始站点等于终止站点，则设置result，设置距离和station。
        if (star.equals(end)) {
            Result result = new Result();
            result.setDistance(0.0D);
            result.setEnd(star);
            result.setStar(star);
            return resultMap.put(star, result);
        }
        //System.out.println("DU:1"+star+"    "+end);
        //第一次调用calculate，且起始点和终止点不同，则resultMap为空。
        if (resultMap.isEmpty()) {
            //第一次调用获取起始点的相邻站点（在所有地铁线中，这里涉及转线交叉的周围站点）
            List<Station> linkStations = getLinkStations(star);
            //把相邻站点集合中的所有站点，加入resultMap中。 因为相邻，则可以直接获取Distance。
            for (Station station : linkStations) {

                Result result = new Result();
                result.setStar(star);
                result.setEnd(station);
                String key = star.getName() + ":" + station.getName();
                Double distance = DistanceBuilder.getDistance(key);
                result.setDistance(distance);
                result.getPassStations().add(station);
                resultMap.put(station, result);
            }
        }
        //System.out.println("DU:2"+resultMap);
        Station parent = getNextStation();
        //如果resultMap所有点keySet被分析完了，则返回的parent为null。
        if (parent == null) {
            Result result = new Result();
            result.setDistance(0.0D);
            result.setStar(star);
            result.setEnd(end);
            //put方法的返回值就是value值。
            //System.out.println("DU:3 "+resultMap);
            return resultMap.put(end, result);
        }
        //如果得到的最佳邻点与目标点相同，则直接返回最佳邻点对应的result对象。
        if (parent.equals(end)) {
            //System.out.println("DU:4 "+resultMap);
            return resultMap.get(parent);
        }


        //在路径经过点中加入parent后，更新resultMap集合，要么起始点经过parent达到parent相邻点是最优的，要么起始点到parent相邻点不可达，而通过parent可达。
        //获取parent对象(最佳点)的相邻点。
        //分析一个parent最佳点后，把它的相邻点都会加入到resultMap中，在下一次调用getNextStation获取resultMap中未被标记且距离（起始点到该station的距离）最短。
        List<Station> childLinkStations = getLinkStations(parent);
        //D：B C E
        for (Station child : childLinkStations) {
            if (analysisList.contains(child)) {
                continue;
            }
            String key = parent.getName() + ":" + child.getName();
            Double distance;

            distance = DistanceBuilder.getDistance(key);

            DistanceBuilder.getDistance(key);
            if (parent.getName().equals(child.getName())) {
                distance = 0.0D;
            }

            Double parentDistance = resultMap.get(parent).getDistance();
            distance = doubleAdd(distance, parentDistance);

            List<Station> parentPassStations = resultMap.get(parent).getPassStations();
            Result childResult = resultMap.get(child);
            if (childResult != null) {
                //既可以A->B，也可以通过最佳点D，从A->D->B
                if (childResult.getDistance() > distance) {
                    //如果通过最佳点比直接到距离小，则更新resultMap中的对应result对象。
                    childResult.setDistance(distance);
                    childResult.getPassStations().clear();
                    //路径更新为A->最佳点->child点。
                    childResult.getPassStations().addAll(parentPassStations);
                    childResult.getPassStations().add(child);
                }
            } else {
                //如果在resultMap中没有最佳点的相邻点，则往resultMap中添加通过最佳点（初始为起始点的最佳邻点）到达该点。
                childResult = new Result();
                childResult.setDistance(distance);
                childResult.setStar(star);
                childResult.setEnd(child);
                childResult.getPassStations().addAll(parentPassStations);
                childResult.getPassStations().add(child);
            }
            resultMap.put(child, childResult);
        }
        //初始时，即第一次调用该方法时，在分析点中加入起始点的最佳相邻点，后面嵌套调用时，就为获取某点的最佳邻点，在用最佳邻点更新resultMap后，往analysisList中加入最佳邻点。
        analysisList.add(parent);
        //加入最佳邻点后，更新resultMap，再次调用calculate
        //System.out.println("DU:3 "+);
        return calculate(star, end);
        //或：
        // calculate(star, end); 继续往下走，选择最佳点，然后更新resultMap。
        // return resultMap.get(end);
    }


    /**
     * 获取所有相邻节点.
     *
     * @param station the station
     * @return the link stations
     * @since hui_project 1.0.0
     */
    //传入起始点station对象。
    public static List<Station> getLinkStations(Station station) {
        List<Station> linkedStaions = new ArrayList<Station>();

       for (List<Station> line : DistanceBuilder.lineSet) {
            for (int i = 0; i < line.size(); i++) {
                //遍历每条地铁线，若地铁线中存在该站点，则判断，如果该站点位于地铁线的起始站，则相邻站为地铁线的第二个站点(i+1)，
                //如果该站点位于地铁线的最后一个站，则相邻站为地铁线的倒数第二个站点（i-1），
                //如果该站点位于地铁线的其余位置，则相邻站点为该站点前后位置（i-1/i+1）
                if (station.equals(line.get(i))) {
                    if (i == 0) {
                        linkedStaions.add(line.get(i + 1));
                    } else if (i == (line.size() - 1)) {
                        linkedStaions.add(line.get(i - 1));
                    } else {
                        linkedStaions.add(line.get(i + 1));
                        linkedStaions.add(line.get(i - 1));
                    }
                }
            }
        }
        return linkedStaions;
    }

    /**
     * 通过计算最小权值 计算下一个需要分析的点
     * 将resultMap中的station集合循环遍历，获取“未被标记的点(未在analysisList中)”中，对应result对象中距离最小的终点station对象。
     * @return the next station
     * @since hui_project 1.0.0
     */
    private static Station getNextStation() {
        Double min = Double.MAX_VALUE;
        Station rets = null;
        //获取resultMap中的station集合。
        Set<Station> stations = resultMap.keySet();
        for (Station station : stations) {
            //如果该点被标记为“已被分析”（已被分析表示起始点到该点的最短路径已求出）
            if (analysisList.contains(station)) {
                continue;
            }
            //循环分析resultMap中未被标记的点，求出最短路径的result对象。
            Result result = resultMap.get(station);
            if (result.getDistance() < min) {
                min = result.getDistance();
                //得到终点的station对象
                rets = result.getEnd();
            }
        }
        return rets;
    }

    /**
     * Double相加方法（防止丢失精度）.
     *
     * @param v1 the v 1
     * @param v2 the v 2
     * @return the double
     * @since hui_project 1.0.0
     */
    private static double doubleAdd(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 结果写到文件里面.
     * 这个方法写的比较乱，因为需求变了 为了快速拿出来前后站点分析出站所属线路 直接用的for循环。储存结构用LinkedHashMap会快很多。
     * @param filePath the file path
     * @throws IOException the io exception
     * @since hui_project 1.0.0
     */
    public static void getResultToText(String filePath) throws IOException {
        if (filePath == null) {
            throw new FileNotFoundException("兄弟来个路径保存路径吧");
        }
        BufferedWriter writer = null;
        //追加路径信息...
        writer = new BufferedWriter(new FileWriter(filePath, true));
        Set<List<Station>> lineSet = DistanceBuilder.lineSet;
        for (List<Station> stations : lineSet) {
            for (Station station : stations) {
                for (List<Station> stations2 : lineSet) {
                    for (Station stationTarget : stations2) {
                       DijkstraUtil dijkstraUtil = new DijkstraUtil();
                        Result result = dijkstraUtil.calculate(station, stationTarget);
                        resultMap = new HashMap<>();
                        analysisList = new ArrayList<>();
                        for (Station s : result.getPassStations()) {
                            if (s.getName().equals(stationTarget.getName())) {
                                String text = station.getName() + "\t" + stationTarget.getName() + "\t" + result.getPassStations().size() + "\t" + result.getDistance() + "\t";
                                for (Station test : result.getPassStations()) {
                                //    LOGGER.info(test.getName() + ",");
                                    text = text + test.getName() + ",";
                                }
                            /*    LOGGER.info("{}" , text);
                                LOGGER.info("↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑\n");*/
                                writer.write(text);
                                writer.newLine();
                            }
                        }
                    }

                }
            }
        }
        writer.flush();
        writer.close();
    }
}

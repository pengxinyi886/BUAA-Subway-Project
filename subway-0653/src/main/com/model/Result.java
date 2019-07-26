package main.com.model;


import java.util.ArrayList;
import java.util.List;

public class Result {
    /**
     * 开始站点.
     *
     * @since hui_project 1.0.0
     */
    private Station star;
    /**
     * 结束站点.
     *
     * @since hui_project 1.0.0
     */
    private Station end;
    /**
     * 两个站点距离.
     *
     * @since hui_project 1.0.0
     */
    private Double distance = 0.0D;
    /**
     * 中间经过站点.
     *
     * @since hui_project 1.0.0
     */
    //类型： ArrayList<Map<String,Station>>  经过的站点，每个元素由Map<String,Station>构成，Map的键为该站点的地铁线名，值为"站点"。
    private List<Station> passStations = new ArrayList<>();


    /**
     * Gets star.
     *
     * @return the star
     * @since hui_project 1.0.0
     */
    public Station getStar() {
        return star;
    }

    /**
     * Sets star.
     *
     * @param star the star
     * @since hui_project 1.0.0
     */
    public void setStar(Station star) {
        this.star = star;
    }

    /**
     * Gets end.
     *
     * @return the end
     * @since hui_project 1.0.0
     */
    public Station getEnd() {
        return end;
    }

    /**
     * Sets end.
     *
     * @param end the end
     * @since hui_project 1.0.0
     */
    public void setEnd(Station end) {
        this.end = end;
    }

    /**
     * Gets distance.
     *
     * @return the distance
     * @since hui_project 1.0.0
     */
    public Double getDistance() {
        return distance;
    }

    /**
     * Sets distance.
     *
     * @param distance the distance
     * @since hui_project 1.0.0
     */
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    /**
     * Gets pass stations.
     *
     * @return the pass stations
     * @since hui_project 1.0.0
     */
    public List<Station> getPassStations() {
        return passStations;
    }

    /**
     * Sets pass stations.
     *
     * @param passStations the pass stations
     * @since hui_project 1.0.0
     */
    public void setPassStations(List<Station> passStations) {
        this.passStations = passStations;
    }

    /**
     * Instantiates a new Result.
     *
     * @param star     the star
     * @param end      the end
     * @param distance the distance
     */
    public Result(Station star, Station end, Double distance) {
        this.star = star;
        this.end = end;
        this.distance = distance;
    }

    /**
     * Instantiates a new Result.
     */
    public Result() {

    }

    @Override
    /*public String toString() {
        return "Result{" +
                "star=" + star +
                ", end=" + end +
                ", distance=" + distance +
                ", passStations=" + passStations +
                '}';
    }*/
    public String toString() {
        String  passStationsName = "";
        for(int i = 0;i<passStations.size();i++){
            Station  ss = passStations.get(i);

            passStationsName= passStationsName+"   "+ss.getName();
        }

        return "Result{" +
                "star=" + star +
                ", end=" + end +
                ", distance=" + distance +
                ", passStations=" + passStationsName +
                '}';
    }
}

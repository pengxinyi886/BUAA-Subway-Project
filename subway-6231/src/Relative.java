public class Relative {
    public int getNum(String Station){
        int staNum=0;
        switch(Station){
            case "张兴庄":{staNum=1;break;}
            case "西站":{staNum=2;break;}
            case "北站":{staNum=3;break;}
            case "金钟河大街":{staNum=4;break;}
            case "长虹公园":{staNum=5;break;}
            case "西南角":{staNum=6;break;}
            case "天津站":{staNum=7;break;}
            case "靖江路":{staNum=8;break;}
            case "营口道":{staNum=9;break;}
            case "直沽":{staNum=10;break;}
            case "红旗南路":{staNum=11;break;}
            case "下瓦房":{staNum=12;break;}
            case "肿瘤医院":{staNum=13;break;}
            case "天津宾馆":{staNum=14;break;}
            case "文化中心":{staNum=15;break;}
        }
        return staNum;
    }

    public String getStation(int num){
        String station="error";
        switch(num){
            case 1:{station="张兴庄";break;}
            case 2:{station="西站";break;}
            case 3:{station="北站";break;}
            case 4:{station="金钟河大街";break;}
            case 5:{station="长虹公园";break;}
            case 6:{station="西南角";break;}
            case 7:{station="天津站";break;}
            case 8:{station="靖江路";break;}
            case 9:{station="营口道";break;}
            case 10:{station="直沽";break;}
            case 11:{station="红旗南路";break;}
            case 12:{station="下瓦房";break;}
            case 13:{station="肿瘤医院";break;}
            case 14:{station="天津宾馆";break;}
            case 15:{station="文化中心";break;}
        }
        return station;
    }
}

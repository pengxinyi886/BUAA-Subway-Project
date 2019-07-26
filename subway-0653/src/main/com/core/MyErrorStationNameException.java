package main.com.core;

public class MyErrorStationNameException extends  Exception {
    String s="";
    public MyErrorStationNameException(String s){
        this.s = s;
    }
    public String toString(){
        return  s;
    }
}


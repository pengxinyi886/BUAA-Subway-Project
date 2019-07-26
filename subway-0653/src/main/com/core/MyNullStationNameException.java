package main.com.core;

public class MyNullStationNameException extends  Exception {
    String s="";
    public   MyNullStationNameException(String s){
        this.s = s;
    }
    public String toString(){
        return  s;
    }
}


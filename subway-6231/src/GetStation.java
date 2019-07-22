import java.util.*;

public class GetStation {
    public class StationToLine{
            public int length;
            public String strStation;
            String lineNum;
            String [] midStation= new String[10];

            public StationToLine(int length,String strStation,String lineNum){
                this.length=length;
                this.strStation=strStation;
                this.lineNum=lineNum;
            }

            public String toString(){
                return lineNum+strStation+":"+length;
            }
    }

    String pos,line,pos1;
    String lineNum;
    public int len,num=0,flag=0,flag1=0;
    public StationToLine getGoStation(String str,String str1) {
            for(String st1:new FileOperate().getFileSpecLine(str,0,6)) {
                if (st1.contains(str1)) {
                    pos=st1;
                   // lineNum=pos.substring(0,3);
                                            //System.out.println(lineNum);
                }
            }
            String [] strArray1 = pos.split(":");
            //System.out.println(strArray1[0]);
            String [] strArray = strArray1[1].split(",");
            for(int i=0;i<strArray.length;i++){
                if(strArray[i].contains(str1)){
                   // System.out.print(strArray[i].charAt(0));
                    flag=1;
                }

                if(flag==1){
                    String s1=strArray[i].replace(("f"),"");
                                            System.out.println(s1.replace("t",""));
                }

                if(strArray[i].contains("t")) {
                    len = num;
                    line = strArray[i].replace(" t", "");
                    flag=0;
                   // break;
                }
                num++;
            }
            StationToLine stl=new StationToLine(len,line,lineNum);
            return stl;
    }

    public StationToLine getEndStation(String str,String str2){
        for(String st2:new FileOperate().getFileSpecLine(str,0,6)) {
            if (st2.contains(str2)) {
                pos=st2;
            }
        }
        String [] strArray3=pos.split(":");
        String [] strArray4 = strArray3[1].split(",");
        for(int i=0;i<strArray4.length;i++){
            if(strArray4[i].contains(str2)){
                // System.out.print(strArray1[i].charAt(0));
                flag=i;
                for(int j=flag;j>0;j--){
                    if(strArray4[j].contains("t")){
                        //strArray4[j].replace(" t","");
                        flag1=j;
                        break;
                    }
                }
            }
        }
        //System.out.println(flag1);
            len=flag1-flag;

            StationToLine st3=new StationToLine(len,strArray4[flag1].replace(" t",""),lineNum);
            return st3;
    }

    public void show(String str,String str2){
        for(String st2:new FileOperate().getFileSpecLine(str,0,6)) {
            if (st2.contains(str2)) {
                pos1=st2;
            }
        }
        String [] strArray5=pos1.split(":");
        String [] strArray6 = strArray5[1].split(",");
        for(int i=0;i<strArray6.length;i++){
            if(strArray6[i].contains(str2)){
                // System.out.print(strArray1[i].charAt(0));
                flag=i;
                for(int j=flag;j>0;j--){
                    if(strArray6[j].contains("t")){
                        //strArray4[j].replace(" t","");
                        flag1=j;
                        break;
                    }
                }
            }
        }
        for(int k=flag1+1;k<flag+1;k++){
            String s1=strArray6[k].replace(" f","");
            System.out.println(s1.replaceAll(" t",""));
        }
    }
}

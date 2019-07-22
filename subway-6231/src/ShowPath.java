
import java.util.*;
public class ShowPath {
    public void show(String str,List<Integer> list){
        int num=0;
        int flag=0,flag1=0;
        String lineNum;
        String pos="error";

        Iterator it=list.iterator();
        while(it.hasNext()){
            Object obj=it.next();
            num++;
        }

       // i=list.get(0);
        //System.out.println(i);
        for(int l=0;l<num-1;l++){
            int pre=list.get(l);
            int pro=list.get(l+1);
            String preStation=new Relative().getStation(pre);
            String proStation=new Relative().getStation(pro);
            for(String str1:new FileOperate().getFileSpecLine(str,0,6)){
                if(str1.contains(preStation) && str1.contains(proStation)){
                    pos=str1;
                    lineNum=pos.substring(0,3);
                    System.out.println(lineNum);
                }
            }

            String [] str2=pos.split(":");
            //System.out.println(str2[1]);
            String [] str3=str2[1].split(",");
           // System.out.println(preStation);
           // System.out.println(proStation);
            for(int g=0;g<str3.length;g++){
                if(str3[g].contains(preStation)){
                    flag=g;
                }

                if(str3[g].contains(proStation)){
                    flag1=g;
                }
            }
            //System.out.print(flag);
           // System.out.println(flag1);
            for(int k=flag+1;k<flag1+1;k++){
                System.out.println(str3[k].replaceAll(" f","").replaceAll(" t",""));
            }
        }
    }
}

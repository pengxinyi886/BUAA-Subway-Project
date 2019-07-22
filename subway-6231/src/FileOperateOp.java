import java.util.*;
class FileOperateOp extends FileOperate {
        public FileOperateOp(String str1, String str2) {
            if(str1.equals("-map")){
                for(int i=0;i<getFile(str2).size();i++){
                    getFilePrint(str2,i);
                }
            }else{
                System.out.println("command error!");
            }
        }

        public FileOperateOp(String str, String str1, String str2, String str3, String str4, String str5) {
             if(str.equals("-a") && str4.equals("-o") && str2.equals("-map")){
                 char ch=str1.charAt(0);
                 switch(ch){
                     case '1' : {getFilePrint(str3,0);writeFile(getFile(str3).get(0),str5);break;}
                     case '2' : {getFilePrint(str3,1);writeFile(getFile(str3).get(1),str5);break;}
                     case '3' : {getFilePrint(str3,2);writeFile(getFile(str3).get(2),str5);break;}
                     case '5' : {getFilePrint(str3,3);writeFile(getFile(str3).get(3),str5);break;}
                     case '6' : {getFilePrint(str3,4);writeFile(getFile(str3).get(4),str5);break;}
                     case '9' : {getFilePrint(str3,5);writeFile(getFile(str3).get(5),str5);break;}
                     default:System.out.println("error");
                 }
             }else{
                 System.out.println("command error!");
             }
        }

        public FileOperateOp(String str, String str1, String str2, String str3, String str4, String str5, String str6) {
                String beginStation=new GetStation().getGoStation(str4,str1).strStation;
                String endStation=new GetStation().getEndStation(str4,str2).strStation;
                //System.out.println(beginStation);
                int begin=new Relative().getNum(beginStation);
                int end=new Relative().getNum(endStation);
                List <Integer> list=new FloydInGraph(15).getPath(begin,end);
                new ShowPath().show(str4,list);
                new GetStation().show(str4,str2);
        }
}

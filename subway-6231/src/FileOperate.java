import java.io.*;
import java.util.ArrayList;
import java.util.List;

class FileOperate {
     public List<String> getFile(String path){
        List<String> line = new ArrayList();

        try {
                File file = new File(path);
                InputStream in = new FileInputStream(file);
                Reader reader = new InputStreamReader(in, "gbk");
                BufferedReader br = new BufferedReader(reader);
                String s;
                while ((s = br.readLine()) != null) {
                    line.add(s);
                }
                br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    public void getFilePrint(String path1,int num){
        String str=getFile(path1).get(num).replace("f,","->");
        System.out.println(str.replace("t,","->"));
     }

     public List<String> getFileSpecLine(String path1,int startNum,int endNum){
         List<String> line=new ArrayList();
         for(int i=startNum;i<endNum;i++){
             line.add(getFile(path1).get(i));
         }
         return line;
     }

    public void writeFile(String line1,String path1){
         try {
            FileWriter writer=new FileWriter(path1);
            BufferedWriter bw=new BufferedWriter(writer);
            bw.write(line1);
            bw.close();
         }catch(Exception e){
             e.printStackTrace();
         }

    }
}


package input;

import java.io.*;
import java.util.ArrayList;

public class InputFromText extends AbstractInput{
    private String textName;

    public InputFromText() {
        super();
    }

    public void setTextName(String textName ) {
        this.textName = textName;
    }

   @Override
    public ArrayList<String> getInputList()throws Exception{

       try {

           File file=new File(textName);
           if(file.isFile() && file.exists()) {
               InputStreamReader reader = new InputStreamReader( new FileInputStream(file), "GBK");
               BufferedReader bufferedReader = new BufferedReader(reader);
               String lineTxt;
               while ((lineTxt = bufferedReader.readLine()) != null) {
                   inputList.add( lineTxt );
               }
               reader.close();
           }else{
              throw  new Exception("找不到指定的文件");
           }

       } catch (IOException e) {

           throw  new Exception("读取文件错误");
       }
       return inputList;
   }

}

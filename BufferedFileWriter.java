import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
public class BufferedFileWriter{
    private boolean override;
    private String defaultPath = "C:\\Computer Science\\Mason's Files\\java projects\\Recreational\\API&Scrapers\\";
    private String path; 

    public BufferedFileWriter(String fileName){
        path = defaultPath + fileName;
    }
    public void BufferedFileWriter(String fileName, boolean isPath){
        if(isPath){
            path = fileName;
        } else {
            path = defaultPath + fileName;
        }
    }
    
    public void writeLine(String line, boolean override){
        try {
            BufferedWriter f_writer = new BufferedWriter(new FileWriter(path,override));
            f_writer.write(line);
            f_writer.newLine();
            f_writer.close();
            System.out.println("This bit works");
            
            

        } catch (Exception e){
        }
    }
    public void writeLines(ArrayList<String> arr, boolean override){
        try {
            BufferedWriter f_writer = new BufferedWriter(new FileWriter(path));
            for(String line: arr){
                f_writer.write(line);
                f_writer.write("\n");
            }
            f_writer.close();
        } catch (Exception e){
        }
    }
   
}

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;
public class BufferedFileWriter{
    private String currDir = System.getProperty("user.dir");
    private String path; 

    

    public BufferedFileWriter(String fileName){ //used
        path = currDir + fileName;
        System.out.println(currDir); 
    }
    public BufferedFileWriter(String fileName, boolean isPath){
        if(isPath){
            path = fileName;
        } else {
            path = currDir + fileName;
        }
    }
    
    public void writeLine(String line, boolean override){
        try {
            BufferedWriter f_writer = new BufferedWriter(new FileWriter(path,override));
            f_writer.write(line);
            f_writer.newLine();
            f_writer.close();

        } catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
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
            System.out.println("ERROR: " + e.getMessage());
        }
    }
   
}

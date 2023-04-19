import java.io.IOException;
import java.util.*;

import javax.swing.text.AbstractDocument.ElementEdit;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class StocksWebScraper {
    public static void main(String[] args) throws IOException{
        Document page = Jsoup.connect("https://www.tradingview.com/markets/stocks-usa/market-movers-all-stocks/").get();
        Elements abrreviations = page.select("img + a, span + a");
        Elements names = page.select("sup");
        Elements everythingElse = page.select("tr > td");
        ArrayList<String[]> infoLines = new ArrayList<String[]>();
        // companyNames = 0  
        // prices = 1 
        // percentChange = 2
        // technicalRating = 3
        // volume = 4
        // employees = 5
        // sectors = 6
        // stockInfo = 7

        // Putting elemnets into their respective lists. Purposely missing names and omitting them
        int i = 0;
        int j = 0;
        String[] infoLine = new String[13];
        for(Element e : everythingElse){
            
            if(i!=0){
                // // Checking to see if a attribute is empty (?) and replacing it with (-)
                // if(e.text().equals("?")){
                //     infoLine[i+1] = "-";
                // } else if (e.text().indexOf("?") != -1){
                //     infoLine[i+1] = "-" + e.text().substring(1);
                // }
                infoLine[i+1] = e.text();
            } else { // Adding the name and abrreviation of the company to the info line
                infoLine[0] = abrreviations.get(j).text();
                infoLine[1] = names.get(j).text();
            }
            i++;
            if(i==12){
                infoLines.add(Arrays.copyOf(infoLine,13));
                j++;
                i=0; 
            }
        }



        // iterating and extracting
        for (Element e:abrreviations) {
            System.out.println(e.text());
        }
        BufferedFileWriter writer = new BufferedFileWriter("testFile.txt");
        for(String[] x : infoLines){
            System.out.println(Arrays.toString(x));
             
            writer.writeLine(Arrays.toString(x),true);

        }
    }
}

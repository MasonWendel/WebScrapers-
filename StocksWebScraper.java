import java.io.IOException;
import java.util.*;
import java.nio.charset.*;
import javax.swing.text.AbstractDocument.ElementEdit;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class StocksWebScraper {
    public static void main(String[] args) throws IOException{

        System.out.println(System.getProperty("user.dir"));

        Document page = Jsoup.connect("https://www.tradingview.com/markets/stocks-usa/market-movers-all-stocks/").get();
        Elements abrreviations = page.select("img + a, span + a");
        Elements names = page.select("sup");
        Elements everythingElse = page.select("tr > td");
        ArrayList<String[]> infoLines = new ArrayList<String[]>();

        String jsonPayload = "{\"columns\":[\"name\",\"description\",\"logoid\",\"update_mode\",\"type\",\"typespecs\",\"close\",\"pricescale\",\"minmov\",\"fractional\",\"minmove2\",\"currency\",\"change\",\"volume\",\"relative_volume_10d_calc\",\"market_cap_basic\",\"fundamental_currency_code\",\"price_earnings_ttm\",\"earnings_per_share_diluted_ttm\",\"earnings_per_share_diluted_yoy_growth_ttm\",\"dividends_yield_current\",\"sector.tr\",\"market\",\"sector\",\"recommendation_mark\"],\"ignore_unknown_fields\":false,\"options\":{\"lang\":\"en\"},\"range\":[0,200],\"sort\":{\"sortBy\":\"name\",\"sortOrder\":\"asc\",\"nullsFirst\":false},\"preset\":\"all_stocks\"}";
        String json = Jsoup.connect("https://scanner.tradingview.com/america/scan").ignoreContentType(true).requestBody(jsonPayload).post().body().text();
        System.out.println(json);
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
        BufferedFileWriter writer = new BufferedFileWriter("testFile.txt", true);
        for(String[] x : infoLines){
            System.out.println(Arrays.toString(x));
             
            writer.writeLine(Arrays.toString(x),true);

        }
    }
}

import java.io.IOException;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class RecipeWebScraper {
    public static void main(String[] args) throws IOException{
        Document page = Jsoup.connect("https://www.waterless-toilet.com/top-6-best-composting-toilets-to-choose/").get();
        Elements pageElements = page.select("a[href]");
        ArrayList<String> hyperLinks = new ArrayList<String>(); //you mom
        
        // iterating and extracting
        for (Element e:pageElements) {
            hyperLinks.add("Text: " + e.text());
            hyperLinks.add("Link: " + e.attr("href"));
        }

        for (String s : hyperLinks) {
            System.out.println(s);
        }
    }
}
// Hello World

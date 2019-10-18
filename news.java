
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.util.*;
import java.io.*;

public class news {

    static LinkedList<String> linksParsed = new LinkedList<String>(); // links those are already parsed into tree 
    static LinkedList<String> linksToExplore = new LinkedList<String>();
    static LinkedList<String> linksExplored = new LinkedList<String>();
    
    // links those are parsed into tree, but to be used as pageURL for new links
    static int count = 0;
    static File file;
    static BufferedWriter bw;
    static int limit = 1000;
    
    
    public static void main(String[] args) {
        try {

            file = new File("url.txt");
            bw = new BufferedWriter(new FileWriter(file));

            String guardianUrl = "http://www.theguardian.com";
            Node root = new Node("http://www.theguardian.com", 0);

//            System.setProperty("http.proxyHost", "");
//            System.setProperty("http.proxyPort", "");
            
            fromPageToTree(root, guardianUrl);
            linksParsed.add(guardianUrl);
            linksExplored.add(guardianUrl);
            
            while(!linksToExplore.isEmpty()){
                if(count >= limit){
                    break;
                }
                String url = linksToExplore.pop();
                System.out.print(++count+". ");
                fromPageToTree(root, url);
                
            }
            
            recursiveUrl.printTree(root, "");
            
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String removePrefix(String url) {
        return url.replace("http://www.theguardian.com", "");
    }

    static void fromPageToTree(Node root, String pageUrl) throws Exception {
        String guardianUrl = "http://www.theguardian.com";
        Document doc = Jsoup.connect(pageUrl).get();
        System.out.println(doc.title());
        Elements elements = doc.getElementsByTag("a");
        for (Element element : elements) {
            String str = element.attr("href");
            if (str.startsWith(guardianUrl)) {
                // starts with guardian.com
                if (linksParsed.contains(str)) {
                    //already used for tree
                } else {
                    linksParsed.add(str);
                    if(linksExplored.contains(str)){
                        
                    }else{
                        linksToExplore.add(str);
                    }
                    
                    str = str.replace("http://www.theguardian.com/", "");
                    StringTokenizer st = new StringTokenizer(str, "/");
                    recursiveUrl.buildTree(root, st);
                    
                }

            }

        }
    }

}

package com.ull.DS3;

import com.ull.DS3.Search;
import java.io.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class _webServiceSearch {

    public static String indexFileLocation = "C:\\Users\\saies\\Downloads\\DS3\\DS3\\index";
    public static String indexFileName = "Index.txt";

    public static final String template = "%s!";

   /* @GetMapping("/search")
    public Search search(@RequestParam(defaultValue = "Client!") String key) {
        return new Search(key, String.format(template, searchTermInIndex(key)));
    }*/

    public static String searchTermInIndex(String searchKeyword){
        File file = new File(indexFileLocation + File.separator + indexFileName);
        BufferedReader br = null;
        try {

            br = new BufferedReader(new FileReader(file));
            String st;

            while ((st = br.readLine()) != null) {

                String [] spiltWords =  st.split("\\|.\\|");
                if(spiltWords[0].equals(searchKeyword)){
                    System.out.println("Match Find on Index File!");
                    System.out.println("Sending following result to the client side:");
                    System.out.println(st);
                    return st;
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return "";
    }
}


package facebookapiparser;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FacebookAPIParser {

    private static Constants Constants = new Constants();
    private static WebDriver drv = null;
    private static String username = "";
    private static String pw = "";
    private static String loginPage = "https://www.facebook.com/login/";

    /*
     input goes: your user id first, then your friend's, then how many messages you want. Start number with zero for a starting point for the number of expected messages.
     EX: 0600 = start at 600 and go up to find the number of messages to get dynamically until there are no more older messages.
     */
    public static void main(String[] args) {

        drv = new WebBrowser().getBrowser();
        drv.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        doFBLogin();
        boolean isOffset = false;
        String yourID = args[0];
        String friendID = args[1];
        if (args[2].startsWith("0")) {
            args[2] = args[2].substring(1);
            isOffset = true;
        }
        int limit = Integer.parseInt(args[2]);
        int hash = 0;
        String msgSource = "";
        boolean done = false;

        while (!done) {
            //Iterate until not changed response, meaning we have gotten all of the messages..
//            String url = Constants.getMessageAPIURL(friendID, yourID, limit);
            String url = Constants.getMessageAPIURL(friendID, yourID, limit);
            System.out.print(url + "   ");
            drv.get(url);
            msgSource = drv.getPageSource();
//            System.out.println("\n\n\nPAGE SOURCE:" + drv.getPageSource() + "\n\n\n");
            if (hash != 0) {
                if (hash == (hash = occurrencesOf("\"message_id\"", drv.getPageSource()))) {
                    done = true;
                } else {
                    limit++;
                }
            } else {
                hash = occurrencesOf("\"message_id\"", drv.getPageSource());
                limit++;
            }
            System.out.print("Limit: " + limit);
            ArrayList<String> arr = new ArrayList<String>();
            arr.add(drv.getPageSource());
            new FileIO().writeArrayToFile(arr, "C:\\Users\\Public\\a.txt");
        }
//        ArrayList<String> arr = new ArrayList<String>();
//        arr.add(drv.getPageSource());
//        new FileIO().writeArrayToFile(arr, "C:\\Users\\Public\\a.txt");
        String result = drv.getPageSource();
        
        drv.manage().deleteAllCookies();
        drv.quit();
        return;
    }

    public static void doFBLogin() {
        drv.get(loginPage);
        drv.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(username);
        drv.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(pw);
        drv.findElement(By.cssSelector("label[for=\"persist_box\"]")).click();
        drv.findElement(By.cssSelector("input[value=\"Log In\"]")).click();
    }

//    private static void getMSGURL(String facebookServerURLNick) {
//        drv.get("https://www.facebook.com/messages/" + facebookServerURLNick);
//    }
    private static int occurrencesOf(String regex, String string) {
        int numberOf = 0;
        Pattern patt = Pattern.compile(regex);
        Matcher m = patt.matcher(string);
        while (m.find()) {
            m.group();
            numberOf++;
        }
        System.out.println("Number found: " + numberOf + "\n\n");
        return numberOf;
    }
}

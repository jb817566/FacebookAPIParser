package facebookapiparser;

public class Constants {

    public Constants() {

    }

    public String getMessageAPIURL(String friendID, String yourUserID, int limit) {

        String apiURLBase = "https://facebook.com/ajax/mercury/thread_info.php?messages[user_ids]["
                + friendID
                + "][offset]=0&messages[user_ids]["
                + friendID
                + "][limit]="
                + limit
                + "&&client=web_messenger&__user="
                + yourUserID
                + "&__a=1&__dyn=7nmajEyl35zO29Q9UoHaEWC5ECiq78hyaF7By8VFLHwxBxCbzES2N6xybxu3fzob8iUkUgxacFoydCxuFEW&__req=13&fb_dtsg=AQHLwl7E9BOE&ttstamp=265817276119108556957667969&__rev=1723465";
        return apiURLBase;
    }

}

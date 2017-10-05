package za.co.codetribe.kid.activities;

/**
 * Created by Codetribe on 2017/09/08.
 */

public class Activity1 {


        private String url1;
        private String url2;

        private static String comment;


        public Activity1() {

        }

        public Activity1(String url1,  String comment) {
            this.url1 = url1;
            this.url2 = url2;
            this.comment = comment;
        }

        public String getUrl1() {
            return url1;
        }

        public void setUrl1(String url1) {
            this.url1 = url1;
        }

        public String getUrl2() {
            return url2;
        }

        public void setUrl2(String url2) {
            this.url2 = url2;
        }

        public static String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }



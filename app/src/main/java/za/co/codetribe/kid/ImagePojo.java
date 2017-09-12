package za.co.codetribe.kid;

/**
 * Created by Codetribe on 2017/09/02.
 */

public class ImagePojo {

    public String name;
    String url;


    public ImagePojo() {

    }

    public ImagePojo(String name,String url) {
        this.name = name;
        this.url=url;



    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

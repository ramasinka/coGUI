package data;

/**
 * Created by CodeAcademy on 2017.07.14.
 */
public class UrlData {
    int id;
    String url;

    public UrlData(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public UrlData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

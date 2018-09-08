package facilities.samir.andrew.facilities.SingleTon;

/**
 * Created by andre on 15-May-17.
 */

public class FacSingleton {

    private static FacSingleton mInstance = null;

    private String title, body, imageUri,date;

    private FacSingleton() {
    }

    public static FacSingleton getInstance() {
        if (mInstance == null) {
            mInstance = new FacSingleton();
        }
        return mInstance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

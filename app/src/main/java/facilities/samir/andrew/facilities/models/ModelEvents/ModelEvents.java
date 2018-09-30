package facilities.samir.andrew.facilities.models.ModelEvents;

import com.google.gson.annotations.SerializedName;

public class ModelEvents {


    @SerializedName("title")
    public String title;
    @SerializedName("date")
    public String date;
    @SerializedName("content")
    public String content;
    @SerializedName("image")
    public String image;
    @SerializedName("lat")
    public double lat;
    @SerializedName("lng")
    public double lng;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}

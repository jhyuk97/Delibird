package com.example.rider;

public class user23_data {
    String title;
    float rating;
    String Img;
    String story;
    int reviewnum;

    public user23_data(String title, float rating, String img, String story, int reviewnum) {
        this.title = title;
        this.rating = rating;
        Img = img;
        this.story = story;
        this.reviewnum = reviewnum;
    }

    public int getReviewnum(){return reviewnum;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}

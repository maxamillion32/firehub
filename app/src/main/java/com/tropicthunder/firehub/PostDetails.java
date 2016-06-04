package com.tropicthunder.firehub;

/**
 * Created by Bryan Lee on 4/6/2016.
 */
public class PostDetails {
    private String title, category, name, rating, coursePicture, teacherPicture;

    public PostDetails(String title, String category, String name, String rating, String coursePicture, String teacherPicture) {
        this.title = title;
        this.category = category;
        this.name = name;
        this.rating = rating;
        this.coursePicture = coursePicture;
        this.teacherPicture = teacherPicture;
    }

    public PostDetails() {

    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getCoursePicture() {
        return coursePicture;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getTeacherPicture() {
        return teacherPicture;
    }
}

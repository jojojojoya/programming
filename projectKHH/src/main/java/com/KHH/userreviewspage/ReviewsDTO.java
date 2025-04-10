package com.KHH.userreviewspage;

import java.util.Date;

public class ReviewsDTO {

    private int review_shop;
    private String review_content;
    private Date review_date;
    private String review_nickname;
    private String shop_name;

    public ReviewsDTO() {
    }

    public String getReview_content() {
        return review_content;
    }
    public void setReview_content(String review_content) {
    this.review_content = review_content;
}
public String getReview_nickname() {
        return review_nickname;
}
public void setReview_nickname(String review_nickname) {
    this.review_nickname = review_nickname;
}

    public Date getReview_date() {
        return review_date;
    }
    public void setReview_date(Date review_date) {
        this.review_date = review_date;
    }

    public int getReview_shop(){
        return review_shop;
    }

    public void setReview_shop(int review_shop) {
        this.review_shop = review_shop;
    }
    public String getShop_name() {
        return shop_name;
    }
    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    @Override
    public String toString() {
        return "ReviewsDTO{" +
                "review_shop='" + review_shop + '\'' +
                ", review_content='" + review_content + '\'' +
                ", review_date='" + review_date + '\'' +
                ", review_nickname='" + review_nickname + '\'' +
                ", shop_name='" + shop_name + '\'' +
                '}';
    }
}
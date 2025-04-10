
package com.KHH.ShopInfo;

public class ReviewDTO {
    private int review_no;
    private int review_shop;
    private String review_content;
    private String review_date;
    private String review_nickname;
    private int review_star;
    private String review_image;



    public ReviewDTO() {
        super();
    }
    public ReviewDTO(int review_no, int review_shop, String review_content, String review_date, int review_star, String review_image) {
        super();
        this.review_no = review_no;
        this.review_shop = review_shop;
        this.review_content = review_content;
        this.review_date = review_date;
        this.review_star = review_star;
        this.review_image = review_image;
    }
    public int getReview_no() {
        return review_no;
    }
    public void setReview_no(int review_no) {
        this.review_no = review_no;
    }
    public int getReview_shop() {
        return review_shop;
    }
    public void setReview_shop(int review_shop) {
        this.review_shop = review_shop;
    }
    public String getReview_content() {
        return review_content;
    }
    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }
    public String getReview_date() {
        return review_date;
    }
    public void setReview_date(String review_date) {
        this.review_date = review_date;
    }
    public String getReview_nickname() {
        return review_nickname;
    }
    public void setReview_nickname(String review_nickname) {
        this.review_nickname = review_nickname;
    }

    public int getReview_star() {
        return review_star;
    }
    public void setReview_star(int review_star) {
        this.review_star = review_star;

    }

    public String getReview_image() {
        return review_image;
    }
    public void setReview_image(String review_image) {
        this.review_image = review_image;
    }
    @Override
    public String toString() {
        return "ReviewDTO{" +
                "review_no=" + review_no +
                ", review_shop=" + review_shop +
                ", review_content='" + review_content + '\'' +
                ", review_date='" + review_date + '\'' +
                ", review_nickname='" + review_nickname + '\'' +
                ", review_star=" + review_star +
                ", review_image='" + review_image + '\'' +
                '}';
    }
}

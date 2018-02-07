package bsuir.vintsarevich.entity;

import java.util.Objects;

public class Review {
    private String text;
    private Float mark;
    private Integer clientId;
    private Integer reviewId;

    public Review() {}

    public Review(String text, Float mark, Integer clientId) {
        this.text = text;
        this.mark = mark;
        this.clientId = clientId;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Float getMark() {
        return mark;
    }

    public void setMark(Float mark) {
        this.mark = mark;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + reviewId +
                ", text='" + text + '\'' +
                ", mark=" + mark +
                ", clientId=" + clientId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(text, review.text) &&
                Objects.equals(mark, review.mark) &&
                Objects.equals(clientId, review.clientId) &&
                Objects.equals(reviewId, review.reviewId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, mark, clientId, reviewId);
    }
}

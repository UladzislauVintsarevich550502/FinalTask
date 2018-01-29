package bsuir.vintsarevich.entity;

public class Review {
    private String text;
    private Double mark;
    private Integer orderId;

    public Review(String text, Double mark, Integer orderId) {
        this.text = text;
        this.mark = mark;
        this.orderId = orderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "text='" + text + '\'' +
                ", mark=" + mark +
                ", orderId=" + orderId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (text != null ? !text.equals(review.text) : review.text != null) return false;
        if (mark != null ? !mark.equals(review.mark) : review.mark != null) return false;
        return orderId != null ? orderId.equals(review.orderId) : review.orderId == null;
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        return result;
    }
}

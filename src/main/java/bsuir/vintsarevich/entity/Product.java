package bsuir.vintsarevich.entity;

import java.util.Objects;

public class Product {

    private Integer id;
    private String type;
    private String nameRu;
    private String nameEn;
    private Double weight;
    private Double cost;
    private String status;
    private String descriptionRu;
    private String descriptionEn;
    private String imagePath;
    private Integer number;
    private Integer ordered;
    private Integer orderId;
    private Double commonCost;


    public Product(Integer id, String type, String nameRu, String nameEn, Double weight, Double cost, String status, String descriptionRu, String descriptionEn, String imagePath, Integer number, Integer ordered, Integer orderId) {
        this.id = id;
        this.type = type;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.weight = weight;
        this.cost = cost;
        this.status = status;
        this.descriptionRu = descriptionRu;
        this.descriptionEn = descriptionEn;
        this.imagePath = imagePath;
        this.number = number;
        this.ordered = ordered;
        this.orderId = orderId;
    }

    public Product() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getCommonCost() {
        return commonCost;
    }

    public void setCommonCost(Double commonCost) {
        this.commonCost = commonCost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", nameRu='" + nameRu + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", weight=" + weight +
                ", cost=" + cost +
                ", status='" + status + '\'' +
                ", descriptionRu='" + descriptionRu + '\'' +
                ", descriptionEn='" + descriptionEn + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", number=" + number +
                ", ordered=" + ordered +
                ", orderId=" + orderId +
                ", commonCost=" + commonCost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(type, product.type) &&
                Objects.equals(nameRu, product.nameRu) &&
                Objects.equals(nameEn, product.nameEn) &&
                Objects.equals(weight, product.weight) &&
                Objects.equals(cost, product.cost) &&
                Objects.equals(status, product.status) &&
                Objects.equals(descriptionRu, product.descriptionRu) &&
                Objects.equals(descriptionEn, product.descriptionEn) &&
                Objects.equals(imagePath, product.imagePath) &&
                Objects.equals(number, product.number) &&
                Objects.equals(ordered, product.ordered) &&
                Objects.equals(orderId, product.orderId) &&
                Objects.equals(commonCost, product.commonCost);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, nameRu, nameEn, weight, cost, status, descriptionRu, descriptionEn, imagePath, number, ordered, orderId, commonCost);
    }
}

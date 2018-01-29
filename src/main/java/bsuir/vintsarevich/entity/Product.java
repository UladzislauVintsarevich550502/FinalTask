package bsuir.vintsarevich.entity;

public class Product {
    private Integer id;
    private String type;
    private String nameRu;
    private String nameEn;
    private Integer weight;
    private Double cost;
    private String status;
    private String descriptionRu;
    private String descriptionEn;
    private String imagePath;

    public Product() {
    }

    public Product(String type, String nameRu, String nameEn, Integer weight, Double cost, String status, String descriptionRu, String descriptionEn, String imagePath) {
        this.type = type;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.weight = weight;
        this.cost = cost;
        this.status = status;
        this.descriptionRu = descriptionRu;
        this.descriptionEn = descriptionEn;
        this.imagePath = imagePath;
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (weight != product.weight) return false;
        if (Double.compare(product.cost, cost) != 0) return false;
        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (type != null ? !type.equals(product.type) : product.type != null) return false;
        if (nameRu != null ? !nameRu.equals(product.nameRu) : product.nameRu != null) return false;
        if (nameEn != null ? !nameEn.equals(product.nameEn) : product.nameEn != null) return false;
        if (status != null ? !status.equals(product.status) : product.status != null) return false;
        if (descriptionRu != null ? !descriptionRu.equals(product.descriptionRu) : product.descriptionRu != null)
            return false;
        if (descriptionEn != null ? !descriptionEn.equals(product.descriptionEn) : product.descriptionEn != null)
            return false;
        return imagePath != null ? imagePath.equals(product.imagePath) : product.imagePath == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (nameRu != null ? nameRu.hashCode() : 0);
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        result = 31 * result + weight;
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (descriptionRu != null ? descriptionRu.hashCode() : 0);
        result = 31 * result + (descriptionEn != null ? descriptionEn.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }
}

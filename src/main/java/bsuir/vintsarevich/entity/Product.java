package bsuir.vintsarevich.entity;

public class Product {
    private Integer id;
    private String type;
    private String name;
    private String weight;
    private double cost;
    private String status;
    private String decription;
    private String imagePath;

    public Product() {
    }

    public Product(Integer id, String type, String name, String weight, double cost, String status, String decription, String imagePath) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.weight = weight;
        this.cost = cost;
        this.status = status;
        this.decription = decription;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (Double.compare(product.cost, cost) != 0) return false;
        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (type != null ? !type.equals(product.type) : product.type != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (weight != null ? !weight.equals(product.weight) : product.weight != null) return false;
        if (status != null ? !status.equals(product.status) : product.status != null) return false;
        if (decription != null ? !decription.equals(product.decription) : product.decription != null) return false;
        return imagePath != null ? imagePath.equals(product.imagePath) : product.imagePath == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (decription != null ? decription.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }
}

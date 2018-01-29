package bsuir.vintsarevich.entity;

public class Order {
    private Integer id;
    private String data;
    private String status;
    private Double cost;
    private Integer clientId;

    public Order(String status, Double cost, Integer clientId) {
        this.status = status;
        this.cost = cost;
        this.clientId = clientId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", status='" + status + '\'' +
                ", cost=" + cost +
                ", clientId=" + clientId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (data != null ? !data.equals(order.data) : order.data != null) return false;
        if (status != null ? !status.equals(order.status) : order.status != null) return false;
        if (cost != null ? !cost.equals(order.cost) : order.cost != null) return false;
        return clientId != null ? clientId.equals(order.clientId) : order.clientId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        return result;
    }
}

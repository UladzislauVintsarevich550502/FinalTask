package com.epam.entity;

import java.util.Objects;

public class Medicament implements Id<Integer> {
    private Integer id;
    private String name;
    private String producer;
    private float price;
    private boolean prescription;
    private String image;
    private Boolean availability;

    public Medicament() {
    }

    public Medicament(int id, String name, String producer, float price, boolean prescription, String image, Boolean availability) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.price = price;
        this.prescription = prescription;
        this.image = image;
        this.availability = availability;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isPrescription() {
        return prescription;
    }

    public void setPrescription(boolean prescription) {
        this.prescription = prescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean isAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicament that = (Medicament) o;
        return id == that.id &&
                Float.compare(that.price, price) == 0 &&
                prescription == that.prescription &&
                Objects.equals(name, that.name) &&
                Objects.equals(producer, that.producer) &&
                Objects.equals(image, that.image) &&
                availability == that.availability;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, producer, price, prescription, image, availability);
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", price=" + price + '\'' +
                ", prescription=" + prescription + '\'' +
                ", image=" + image + '\'' +
                ", availability=" + availability + '\'' +
                '}';
    }
}

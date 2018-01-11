package com.epam.entity;

import java.util.Objects;

public class Order implements Id<Integer> {
    private Integer id;
    private int idUser;
    private int idMedicament;
    private int number;
    private int dosage;
    private int idOrderStatus;

    public Order() {
    }

    public Order(int id, int idUser, int idMedicament, int number, int dosage, int idOrderStatus) {
        this.id = id;
        this.idUser = idUser;
        this.idMedicament = idMedicament;
        this.number = number;
        this.dosage = dosage;
        this.idOrderStatus = idOrderStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdMedicament() {
        return idMedicament;
    }

    public void setIdMedicament(int idMedicament) {
        this.idMedicament = idMedicament;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public int getIdOrderStatus() {
        return idOrderStatus;
    }

    public void setIdOrderStatus(int idOrderStatus) {
        this.idOrderStatus = idOrderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                idMedicament == order.idMedicament &&
                number == order.number &&
                dosage == order.dosage &&
                idOrderStatus == order.idOrderStatus &&
                idUser == order.idUser;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idUser, idMedicament, number, dosage, idOrderStatus);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", idUser='" + idUser + '\'' +
                ", idMedicament=" + idMedicament +
                ", number=" + number +
                ", dosage=" + dosage +
                ", idOrderStatus=" + idOrderStatus +
                '}';
    }
}

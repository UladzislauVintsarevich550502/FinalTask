package com.epam.entity;

import java.sql.Date;
import java.util.Objects;


public class Prescription implements Id<Integer> {
    private Integer id;
    private int idDoctor;
    private int idUser;
    private int idMedicament;
    private Date dateOfIssue;
    private Date dateOfCompletion;
    private boolean status;

    public Prescription() {
    }

    public Prescription(int id, int idDoctor, int idUser, int idMedicament, Date dateOfIssue, Date dateOfCompletion, boolean status) {
        this.id = id;
        this.idDoctor = idDoctor;
        this.idUser = idUser;
        this.idMedicament = idMedicament;
        this.dateOfIssue = dateOfIssue;
        this.dateOfCompletion = dateOfCompletion;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setDoctorId(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setUserId(int idUser) {
        this.idUser = idUser;
    }

    public int getIdMedicament() {
        return idMedicament;
    }

    public void setIdMedicament(int idMedicament) {
        this.idMedicament = idMedicament;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Date getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(Date dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return id == that.id &&
                idMedicament == that.idMedicament &&
                idDoctor == that.idDoctor &&
                idUser == that.idUser &&
                Objects.equals(dateOfIssue, that.dateOfIssue) &&
                Objects.equals(dateOfCompletion, that.dateOfCompletion);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idDoctor, idUser, idMedicament, dateOfIssue, dateOfCompletion);
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", doctorLogin='" + idDoctor + '\'' +
                ", doctorLogin='" + idUser + '\'' +
                ", idMedicament=" + idMedicament +
                ", dateOfIssue=" + dateOfIssue +
                ", dateOfCompletion=" + dateOfCompletion +
                '}';
    }
}

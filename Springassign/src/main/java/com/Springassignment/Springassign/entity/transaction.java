package com.Springassignment.Springassign.entity;
import com.Springassignment.Springassign.entity.order;
import jakarta.persistence.*;
import lombok.Data;

// ID, OrderID, PaymentMethod, Amount, TransactionDate, Status
@Entity
@Data
@Table(name = "Transaction_Details")
public class transaction {

    public void setID(int ID) {
        this.ID = ID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID;


    @ManyToOne
    @JoinColumn(name = "order_Id")
    private order order;

    @Column(name = "PaymentMethod")
    private String PaymentMethod;

    @Column(name = "Amount")
    private int Amount;

    @Column(name = "TransactionDate")
    private String TransactionDate;

    @Column(name = "Status")
    private String Status;
}
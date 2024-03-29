package com.Springassignment.Springassign.entity;
import com.Springassignment.Springassign.entity.customer;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="Order Details")
public class order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "orderID")
    private int orderID;

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    @OneToMany(mappedBy = "order")
    private List<transaction> transactions;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private customer customer;

    @Column(name="Order Quantity")
    private int order_qnt;

}



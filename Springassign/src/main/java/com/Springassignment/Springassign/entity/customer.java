package com.Springassignment.Springassign.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="CUSTOMER")
public class customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "custID")
    private int custID;

    @Column(name =  "NAME")
    private String name;

    @Column(name =  "PhoneNo")
    private String phoneNo;

    @Column(name="Email")
    private String Email;

    @OneToMany(mappedBy = "customer")
    private List<order> orders;

}

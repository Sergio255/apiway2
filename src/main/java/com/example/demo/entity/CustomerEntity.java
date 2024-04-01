package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name", length = 255)
    private String customerName;

    @Column(name = "public_place", length = 255)
    private String publicPlace;

    @Column(name = "neighborhood", length = 255)
    private String neighborhood;

    @Column(name = "zip_code", length = 8)
    private String zipCode;

    @Column(name = "city", length = 255)
    private String city;

    @Column(name = "state", length = 2)
    private String state;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<InvoiceEntity> invoices;

}

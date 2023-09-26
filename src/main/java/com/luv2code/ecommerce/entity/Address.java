package com.luv2code.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="address")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="street")
    private String street;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="country")
    private String country;

    @Column(name="zip_code")
    private String zipCode;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Order order;

// @PrimaryKeyJoinColumn   Annotation này xác định rằng Address sẽ sử dụng khóa chính của nó (id) làm khóa chính cũng như khóa ngoại để liên kết với Order.
// Điều này nghĩa là mỗi Order sẽ có một Address riêng và ngược lại
}






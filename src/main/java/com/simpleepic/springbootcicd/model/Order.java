package com.simpleepic.springbootcicd.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "amount", nullable = false)
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY) // LAZY loading is generally best practice
    @JoinColumn(name = "customer_id", nullable = false) // Foreign key column
    private Customer customer;

    // No-argument constructor
    public Order() {}

    // Constructor with necessary fields (excluding ID)
    public Order(LocalDate orderDate, double amount, Customer customer) {
        this.orderDate = orderDate;
        this.amount = amount;
        this.customer = customer;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", amount=" + amount +
                '}';  // Don't include Customer in toString to avoid infinite recursion
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
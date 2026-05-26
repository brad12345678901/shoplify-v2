package com.shoplify.ecommerce_springboot.model;

import jakarta.persistence.*;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
@SoftDelete(columnName = "deleted_at", strategy = SoftDeleteType.TIMESTAMP)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int stock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable=false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.DETACH)
    private List<ProductImage> productImages;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", category=" + category +
                ", productImages=" + productImages +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }
}

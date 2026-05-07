package com.shoplify.ecommerce_springboot.model;

import jakarta.persistence.*;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name="product_images")
@EntityListeners(AuditingEntityListener.class)
@SoftDelete(columnName="deleted_at", strategy = SoftDeleteType.TIMESTAMP)
public class ProductImages extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String filetype;
    private long filesize;
    private String filepath;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProductImages(String filename, String filetype, long filesize, String filepath, Product product) {
        this.filename = filename;
        this.filetype = filetype;
        this.filesize = filesize;
        this.filepath = filepath;
        this.product = product;
    }

    public ProductImages() {

    }
}

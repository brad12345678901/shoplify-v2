package com.shoplify.ecommerce_springboot.model;

import jakarta.persistence.*;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="product_images")
@EntityListeners(AuditingEntityListener.class)
@SoftDelete(columnName="deleted_at", strategy = SoftDeleteType.TIMESTAMP)
public class ProductImage extends BaseEntity {

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

    public ProductImage(String filename, String filetype, long filesize, String filepath, Product product) {
        this.filename = filename;
        this.filetype = filetype;
        this.filesize = filesize;
        this.filepath = filepath;
        this.product = product;
    }

    public ProductImage() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

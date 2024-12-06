package com.dkstore.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hinhanhsanpham")
public class HinhAnhSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "url_image")
    private String urlImage;
    private boolean isMain;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonBackReference
    private Product product;

    public HinhAnhSanPham() {
		super();
	}

	public HinhAnhSanPham(Integer id, String urlImage, boolean isMain, Product product) {
		super();
		this.id = id;
		this.urlImage = urlImage;
		this.isMain = isMain;
		this.product = product;
	}

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getUrlImage() {
		return urlImage;
	}



	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}



	public boolean isMain() {
		return isMain;
	}



	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}
}

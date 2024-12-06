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
@Table(name = "sanphamtonkho")
public class SanPhamTonKho {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "size")
	private Integer size;
	@Column(name = "tonkho")
	private Integer tonkho;
	@ManyToOne
	@JoinColumn(name="product_id", referencedColumnName = "id")
	@JsonBackReference
	private Product product;
	public SanPhamTonKho() {
		super();
	}
	public SanPhamTonKho(Integer id, Integer size, Integer tonkho, Product product) {
		super();
		this.id = id;
		this.size = size;
		this.tonkho = tonkho;
		this.product = product;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getTonkho() {
		return tonkho;
	}
	public void setTonkho(Integer tonkho) {
		this.tonkho = tonkho;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}

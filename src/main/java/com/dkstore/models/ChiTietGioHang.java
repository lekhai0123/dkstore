package com.dkstore.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "chitietgiohang")
public class ChiTietGioHang {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "size")
	private Integer size;
    
    @Column(name = "soluong")
    private Integer soLuong;

    @Column(name = "gia")
    private Float gia;
    
    @Column(name = "tonggiasanpham")
    private Float tonggiasanpham;

    @ManyToOne
    @JoinColumn(name = "giohang_id", referencedColumnName = "id")
    private GioHang giohang;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

	public ChiTietGioHang() {
		super();
	}		

	public ChiTietGioHang(Integer id, Integer size, Integer soLuong, Float gia, Float tonggiasanpham, GioHang giohang,
			Product product) {
		super();
		this.id = id;
		this.size = size;
		this.soLuong = soLuong;
		this.gia = gia;
		this.tonggiasanpham = tonggiasanpham;
		this.giohang = giohang;
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

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public Float getGia() {
		return gia;
	}

	public void setGia(Float gia) {
		this.gia = gia;
	}

	public Float getTonggiasanpham() {
		return tonggiasanpham;
	}

	public void setTonggiasanpham(Float tonggiasanpham) {
		this.tonggiasanpham = tonggiasanpham;
	}

	public GioHang getGiohang() {
		return giohang;
	}

	public void setGiohang(GioHang giohang) {
		this.giohang = giohang;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}

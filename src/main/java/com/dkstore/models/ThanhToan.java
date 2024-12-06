package com.dkstore.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "thanhtoan")
public class ThanhToan {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "phuongthuc")
    private PhuongThucThanhToan phuongthuc;
	
	 @Column(name = "trangthai")
	 private Boolean trangthai;
	
	@OneToOne
    @JoinColumn(name = "giohang_id",referencedColumnName = "id")
    private GioHang giohang;

	public ThanhToan() {
		super();
	}

	public ThanhToan(Integer id, PhuongThucThanhToan phuongthuc, Boolean trangthai, GioHang giohang) {
		super();
		this.id = id;
		this.phuongthuc = phuongthuc;
		this.trangthai = trangthai;
		this.giohang = giohang;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PhuongThucThanhToan getPhuongthuc() {
		return phuongthuc;
	}

	public void setPhuongthuc(PhuongThucThanhToan phuongthuc) {
		this.phuongthuc = phuongthuc;
	}

	public Boolean getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(Boolean trangthai) {
		this.trangthai = trangthai;
	}

	public GioHang getGiohang() {
		return giohang;
	}

	public void setGiohang(GioHang giohang) {
		this.giohang = giohang;
	}
	
}

package com.dkstore.models;


import jakarta.persistence.*;

@Entity
@Table(name = "chitiethoadon")
public class ChiTietHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "soluong")
    private Integer soluong;

    @Column(name = "gia")
    private Float gia;
   
    @Column(name = "tonggiasanpham")
    private Float tonggiasanpham;

    @ManyToOne
    @JoinColumn(name = "hoadon_id", referencedColumnName = "id")
    private HoaDon hoadon;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public ChiTietHoaDon() {
        super();
    }

    public ChiTietHoaDon(Integer id, Integer soluong, Float gia,Float tonggiasanpham, HoaDon hoadon, Product product) {
        super();
        this.id = id;
        this.soluong = soluong;
        this.gia = gia;
        this.tonggiasanpham = tonggiasanpham;
        this.hoadon = hoadon;
        this.product = product;
    }

    public ChiTietHoaDon(Integer soluong, Float gia,Float tonggiasanpham, HoaDon hoadon, Product product) {
        super();
        this.soluong = soluong;
        this.gia = gia;
        this.tonggiasanpham = tonggiasanpham;
        this.hoadon = hoadon;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
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

	public HoaDon getHoadon() {
        return hoadon;
    }

    public void setHoadon(HoaDon hoadon) {
        this.hoadon = hoadon;
    }

    public Product getProduct() {
        return product; // Đổi kiểu trả về thành Product
    }

    public void setProduct(Product product) { // Thay đổi kiểu tham số thành Product
        this.product = product;
    }
}

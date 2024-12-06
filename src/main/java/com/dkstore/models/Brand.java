package com.dkstore.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "brand")
public class Brand {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name =  "name")
	private String name;
	@Column(name =  "country")
    @Enumerated(EnumType.STRING) 
    private Country country;
	@Column(name =  "logo_url")
	private String logo_url;
	@Column(name = "status")
	private Boolean status;
	@OneToMany(mappedBy = "brand",cascade = CascadeType.ALL)
	private Set<Product> products;
	public Brand() {
		// TODO Auto-generated constructor stub
	}

    public Brand(Integer id, String name, Country country, Boolean status, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.status = status;
        this.products = products;
    }
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    public Country getCountry() {
        return country; // Getter cho country (kiểu enum)
    }

    public void setCountry(Country country) {
        this.country = country; // Setter cho country (kiểu enum)
    }
    
	public String getLogo_url() {
		return logo_url;
	}

	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public Set<Product> getProducts() {
		return products;
	}
	
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
    public String getCountryName() {
        return country != null ? country.getName() : "Unknown";
    }
}

package com.dkstore.models;

public enum PhuongThucThanhToan {
	COD("Thanh toán khi nhận hàng"),
    BANK("Chuyển khoản ngân hàng");

    private final String description;

    PhuongThucThanhToan(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

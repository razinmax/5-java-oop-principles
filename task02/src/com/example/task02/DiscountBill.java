package com.example.task02;

public class DiscountBill extends Bill{
    private final int discount;

    public DiscountBill(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public long getPrice() {
        return (long)(super.getPrice() - super.getPrice() * (discount / 100.0));
    }

    public long getAbsoluteDiscountAmount (){
        return super.getPrice() - getPrice();
    }
}

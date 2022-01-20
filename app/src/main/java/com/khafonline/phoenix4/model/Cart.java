package com.khafonline.phoenix4.model;

import java.util.List;

public class Cart {
    List<CartLine> cartLines;
    Customer customer;


    public List<CartLine> getCartLines() {
        return cartLines;
    }

    public void setCartLines(List<CartLine> cartLines) {
        this.cartLines = cartLines;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

package com.example.ltwebnhom23.cart;

import com.example.ltwebnhom23.model.Product;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Cart implements Serializable {
    Map<Integer, CartItem> data;

    public Cart(){
        data = new HashMap<>();
    }

    public void addProduct(Product product, int quantity){
        if(data.containsKey(product.getId())) data.get(product.getId()).upQuantity(quantity);
        else data.put(product.getId(), new CartItem(product, quantity, product.getPrice()));
    }

    public boolean deleteProduct(int pid){
        return data.remove(pid) != null;
    }

    public List<CartItem> deleteAll(){
        Collection<CartItem> values = data.values();
        data.clear();
        return new ArrayList<>(values);
    }

    public List<CartItem> getList(){
        return new ArrayList<>(data.values());
    }

    public int getTotalQuantity(){
        AtomicInteger total = new AtomicInteger();
        data.values().stream().forEach(p -> total.addAndGet(p.getQuantity()));
        return total.get();
    }

    public double getTotal(){
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        data.values().stream().forEach(p -> total.updateAndGet(v -> (v + (p.getQuantity() * p.getPrice()))));
        return total.get();
    }

    public boolean update(int id, Product product){
        return true;
    }
}

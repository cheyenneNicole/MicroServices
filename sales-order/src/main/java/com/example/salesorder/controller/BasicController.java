package com.example.salesorder.controller;

import java.util.List;

//Why use an interface? because they provide contracts that objects can use to work together without
// needing to know anything else about each other
public interface BasicController <T>{
    T add(T t);

    T get(Long id);

    T modify(T t);

    boolean delete(T t);

    List<T> getAll();
}


package com.example.shopify_backend_challenge.repository;

import com.example.shopify_backend_challenge.model.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {}

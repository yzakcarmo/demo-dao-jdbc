package model.dao;

import model.entities.Seller;

import java.util.List;

 public interface SellerDao {
     void insert(Seller dep);
     void update(Seller dep);
     void deleteById(int id);
     Seller findById(int id);
     List<Seller> findAll();
}

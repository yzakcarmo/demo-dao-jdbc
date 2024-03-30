package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;

public class Program {
    public static void main(String[] args) {
        Department obj = new Department(5,"Toys");
        Seller seller = new Seller(1,"Joao", "joao@joao", LocalDate.now(),5000.0,obj);
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println(seller);
    }
}

package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        Department dep = new Department(1,null);
        Seller seller;
        List<Seller> sellers;

//        System.out.println("**** Testando findById ****");
//        seller = sellerDao.findById(1);
//        System.out.println(seller);
//        System.out.println();
//
//        System.out.println("**** Testando findByDepartmentId ****");
//        List<Seller> sellers = sellerDao.findByDepartmentId(1);
//        for (Seller obj : sellers) System.out.println(obj);
//        System.out.println();
//
//        System.out.println("**** Testando findByDepartment ****");
//        sellers = sellerDao.findByDepartment(dep);
//        for (Seller obj : sellers) System.out.println(obj);
//        System.out.println();
//
//        System.out.println("**** Testando findAll ****");
//        sellers = sellerDao.findAll();
//        for (Seller obj : sellers) System.out.println(obj);
//        System.out.println();
//
//        System.out.println("**** Testando insert ****");
//        seller = new Seller(null,"Teste2", "teste@", LocalDate.now(), 1000.0, dep);
//        sellerDao.insert(seller);
//        System.out.println(sellerDao.findById(seller.getId()));
//        System.out.println();

//        System.out.println("**** Testando update ****");
//        seller = sellerDao.findById(10);
//        System.out.println(seller);
//
//        seller.setName("Jonas");
//        sellerDao.update(seller);
//        System.out.println(sellerDao.findById(10));
//        System.out.println();

        System.out.println("**** Testando delete ****");
        sellerDao.deleteById(10);
        sellers = sellerDao.findAll();
        for (Seller obj : sellers) System.out.println(obj);
        System.out.println();

        DB.closeConnection();
    }
}

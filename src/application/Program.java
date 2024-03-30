package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("**** Testando findById ****");
        Seller seller = sellerDao.findById(1);
        System.out.println(seller);
        System.out.println();

        System.out.println("**** Testando findByDepartmentId ****");
        List<Seller> sellers = sellerDao.findByDepartmentId(1);
        for (Seller obj : sellers) System.out.println(obj);
        System.out.println();

        System.out.println("**** Testando findByDepartment ****");
        Department dep = new Department(4,"Books");
        List<Seller> sellers2 = sellerDao.findByDepartment(dep);
        for (Seller obj : sellers2) System.out.println(obj);
        System.out.println();

        System.out.println("**** Testando findAll ****");
        List<Seller> sellers3 = sellerDao.findAll();
        for (Seller obj : sellers3) System.out.println(obj);
        System.out.println();

        DB.closeConnection();
    }
}

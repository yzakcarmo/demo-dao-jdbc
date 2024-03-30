package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("**** Testando findbyId ****");
        Seller seller = sellerDao.findById(1);
        System.out.println(seller);
        System.out.println();

        System.out.println("**** Testando findbyId ****");

        DB.closeConnection();
    }
}

package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;

public class Program2 {
    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        Department department;
        List<Department> departments;

//        System.out.println("**** Testando findById ****");
//        department = departmentDao.findById(1);
//        System.out.println(department);
//        System.out.println();

//        System.out.println("**** Testando insert ****");
//        department = new Department(null,"Teste2");
//        departmentDao.insert(department);
//        System.out.println(departmentDao.findById(department.getId()));
//        System.out.println();

//        System.out.println("**** Testando findAll ****");
//        departments = departmentDao.findAll();
//        for (Department obj : departments) System.out.println(obj);
//        System.out.println();

//        System.out.println("**** Testando update ****");
//        department = departmentDao.findById(1);
//        if (department == null) System.out.println("Departamento não encontrado");
//        else {
//            System.out.println(department);
//
//            department.setName("Robots");
//            departmentDao.update(department);
//            System.out.println(departmentDao.findById(1));
//            System.out.println();
//        }

        System.out.println("**** Testando delete ****");
        departmentDao.deleteById(6);

        DB.closeConnection();
    }
}

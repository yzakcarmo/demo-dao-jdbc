package model.dao;

import model.entities.Department;

import java.util.List;

public interface DepartmentDao {
     void insert(Department dep);
     void update(Department dep);
     void deleteById(int id);
     Department findById(int id);
     List<Department> findAll();
}

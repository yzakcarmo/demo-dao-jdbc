package model.dao.impl;

import db.DB;
import db.DbException;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements model.dao.DepartmentDao {

    private final Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void insert(Department dep) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, dep.getName());


            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) dep.setId(rs.getInt(1));
                DB.closeResultSet(rs);
            }
            else throw new DbException("Falha ao inserir dados");


        }
        catch (SQLException e) {
            throw new DbException("Erro na criação, causado por: " + e.getMessage());
        }
        finally {

            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department dep) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("UPDATE department SET Name = ?  WHERE Id = ?");

            st.setString(1, dep.getName());
            st.setInt(2, dep.getId());

            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbException("Erro na criação, causado por: " + e.getMessage());
        }
        finally {

            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(int id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE FROM department WHERE Id = ?",
                    Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();
            if(rowsAffected == 0) System.out.println("Departamento não encontrado");

        }
        catch (SQLException e) {
            throw new DbException("Erro na remoção, causado por: " + e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");
            st.setInt(1, id);

            rs = st.executeQuery();

            if(rs.next()) return instanciateDepartment(rs);

            return null;

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("SELECT * FROM department ORDER BY Id");

            rs = st.executeQuery();
            if(rs.next()) {
                List<Department> departments = new ArrayList<>();

                do {
                    departments.add(instanciateDepartment(rs));
                }
                while (rs.next());

                return departments;
            }
            return null;
        }
        catch (SQLException e) {
            throw new DbException("Erro na busca, causado por: " + e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Department instanciateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
}
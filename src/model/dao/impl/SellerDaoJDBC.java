package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private final Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void insert(Seller seller) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, seller.getName());
            st.setString(2, seller.getEmail());
            st.setDate(3, Date.valueOf(seller.getBirthDate()));
            st.setDouble(4, seller.getBaseSalary());
            st.setInt(5, seller.getDepartment().getId());


            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) seller.setId(rs.getInt(1));
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
    public void update(Seller dep) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Seller findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?");
            st.setInt(1,id);

            rs = st.executeQuery();
            if(rs.next()) {
                Department dep = instanciateDepartment(rs);

                return instanciateSeller(rs, dep);

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

    @Override
    public List<Seller> findByDepartmentId(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE DepartmentId = ? "
                    + "ORDER BY Name");
            st.setInt(1,id);

            rs = st.executeQuery();
            if(rs.next()) {
                List<Seller> sellers = new ArrayList<>();
                Department dep = instanciateDepartment(rs);

                do {
                    sellers.add(instanciateSeller(rs, dep));
                }
                while (rs.next());
                return sellers;
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

    public List<Seller> findByDepartment(Department dep) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE DepartmentId = ? "
                    + "ORDER BY Name");
            st.setInt(1,dep.getId());

            rs = st.executeQuery();
            if(rs.next()) {
                List<Seller> sellers = new ArrayList<>();
                Department depResult = instanciateDepartment(rs);

                do {
                    sellers.add(instanciateSeller(rs, depResult));
                }
                while (rs.next());
                return sellers;
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

    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "ORDER BY Name");

            rs = st.executeQuery();
            if(rs.next()) {
                List<Seller> sellers = new ArrayList<>();
                Map<Integer, Department> map = new HashMap<>();

                do {
                    Department dep = map.get(rs.getInt("DepartmentId"));

                    if (dep == null) {
                        dep = instanciateDepartment(rs);
                        map.put(rs.getInt("DepartmentId"), dep);
                    }
                    sellers.add(instanciateSeller(rs, dep));
                }
                while (rs.next());

                return sellers;
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

    private Seller instanciateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBirthDate(rs.getDate("BirthDate").toLocalDate());
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setDepartment(dep);
        return obj;
    }

    private Department instanciateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }
}

package main.dao;


import main.LawyerDAO;
import main.exception.DatabaseException;
import main.model.Lawyer;
import main.db.DatabaseConnection;

import java.sql.*;
        import java.util.ArrayList;
import java.util.List;

public class LawyerDAOimplementation implements LawyerDAO {

    // Add a new Lawyer
    @Override
    public boolean addLawyer(Lawyer lawyer) throws DatabaseException {
        String sql = "INSERT INTO Lawyerr (Person_ID, Name, Date_of_Birth, Contact, Address, National_ID, License_Number) VALUES (?, ?, '1987-04-05', ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, lawyer.getPersonId());
            stmt.setString(2, lawyer.getName());
//            stmt.setDate(3, lawyer.getDateOfBirth());
            stmt.setString(3, lawyer.getContact());
            stmt.setString(4, lawyer.getAddress());
            stmt.setString(5, lawyer.getNationalId());
            stmt.setString(6, lawyer.getLicenseNumber());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // ✅ Return true if insert is successful

        } catch (SQLException e) {
            throw new DatabaseException("Error adding lawyer: " + e.getMessage(), e);
        }
    }

    // Get all Lawyers
    @Override
    public List<Lawyer> getAllLawyers() throws DatabaseException {
        List<Lawyer> lawyers = new ArrayList<>();
        String sql = "SELECT * FROM Lawyerr";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Lawyer lawyer = new Lawyer();
                lawyer.setPersonId(rs.getInt("Person_ID"));
                lawyer.setName(rs.getString("Name"));
                lawyer.setDateOfBirth(rs.getDate("Date_of_Birth"));
                lawyer.setContact(rs.getString("Contact"));
                lawyer.setAddress(rs.getString("Address"));
                lawyer.setNationalId(rs.getString("National_ID"));
                lawyer.setLicenseNumber(rs.getString("License_Number"));

                lawyers.add(lawyer);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching lawyers: " + e.getMessage(), e);
        }

        return lawyers;
    }

    // Get Lawyer by ID
    @Override
    public Lawyer getLawyerById(int lawyerId) throws DatabaseException {
        String sql = "SELECT * FROM Lawyerr WHERE Person_ID = ?";
        Lawyer lawyer = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, lawyerId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    lawyer = new Lawyer();
                    lawyer.setPersonId(rs.getInt("Person_ID"));
                    lawyer.setName(rs.getString("Name"));
                    lawyer.setDateOfBirth(rs.getDate("Date_of_Birth"));
                    lawyer.setContact(rs.getString("Contact"));
                    lawyer.setAddress(rs.getString("Address"));
                    lawyer.setNationalId(rs.getString("National_ID"));
                    lawyer.setLicenseNumber(rs.getString("License_Number"));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching lawyer: " + e.getMessage(), e);
        }

        return lawyer;
    }

    // Update Lawyer
    @Override
    public boolean updateLawyer(Lawyer lawyer) throws DatabaseException {
        String sql = "UPDATE Lawyerr SET Name = ?, Date_of_Birth = ?, Contact = ?, Address = ?, National_ID = ?, License_Number = ? WHERE Person_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, lawyer.getName());
            stmt.setDate(2, lawyer.getDateOfBirth());
            stmt.setString(3, lawyer.getContact());
            stmt.setString(4, lawyer.getAddress());
            stmt.setString(5, lawyer.getNationalId());
            stmt.setString(6, lawyer.getLicenseNumber());
            stmt.setInt(7, lawyer.getPersonId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Error updating lawyer: " + e.getMessage(), e);
        }
    }

    // Delete Lawyer
    @Override
    public boolean deleteLawyer(int lawyerId) throws DatabaseException {
        String sql = "DELETE FROM Lawyerr WHERE Person_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, lawyerId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new DatabaseException("Error deleting lawyer: " + e.getMessage(), e);
        }
    }
}


package main;


import main.model.Lawyer;
import main.exception.DatabaseException;

import java.util.List;

public interface LawyerDAO {
    // Add a new Lawyer
    boolean addLawyer(Lawyer lawyer) throws DatabaseException;

    // Get all Lawyers
    List<Lawyer> getAllLawyers() throws DatabaseException;

    // Get Lawyer by ID
    Lawyer getLawyerById(int lawyerId) throws DatabaseException;

    // Update Lawyer
    boolean updateLawyer(Lawyer lawyer) throws DatabaseException;

    // Delete Lawyer
    boolean deleteLawyer(int lawyerId) throws DatabaseException;
}

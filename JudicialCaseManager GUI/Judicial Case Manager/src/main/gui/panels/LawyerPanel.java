package main.gui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import main.dao.LawyerDAOimplementation;
import main.model.Lawyer;
import main.exception.DatabaseException;
import java.util.List;

public class LawyerPanel extends JPanel {

    private JTextField txtName, txtContact, txtAddress, txtNationalId, txtLicenseNumber;
//    private JDateChooser dateOfBirthChooser; // Assuming you're using JDateChooser for the Date field
    private JButton btnAdd, btnUpdate, btnDelete, btnFetchAll;
    private JTable lawyerTable;
    private LawyerDAOimplementation lawyerDAO;

    public LawyerPanel() {
        lawyerDAO = new LawyerDAOimplementation();
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2));

        inputPanel.add(new JLabel("Name:"));
        txtName = new JTextField();
        inputPanel.add(txtName);

//        inputPanel.add(new JLabel("Date of Birth:"));
//        dateOfBirthChooser = new JDateChooser();
//        inputPanel.add(dateOfBirthChooser);

        inputPanel.add(new JLabel("Contact:"));
        txtContact = new JTextField();
        inputPanel.add(txtContact);

        inputPanel.add(new JLabel("Address:"));
        txtAddress = new JTextField();
        inputPanel.add(txtAddress);

        inputPanel.add(new JLabel("National ID:"));
        txtNationalId = new JTextField();
        inputPanel.add(txtNationalId);

        inputPanel.add(new JLabel("License Number:"));
        txtLicenseNumber = new JTextField();
        inputPanel.add(txtLicenseNumber);

        // Buttons for Add, Update, Delete
        JPanel buttonPanel = new JPanel();
        btnAdd = new JButton("Add Lawyer");
        btnAdd.addActionListener(e -> addLawyer());
        buttonPanel.add(btnAdd);

        btnUpdate = new JButton("Update Lawyer");
        btnUpdate.addActionListener(e -> updateLawyer());
        buttonPanel.add(btnUpdate);

        btnDelete = new JButton("Delete Lawyer");
        btnDelete.addActionListener(e -> deleteLawyer());
        buttonPanel.add(btnDelete);

        // Fetch All Lawyers button
        btnFetchAll = new JButton("Fetch All Lawyers");
        btnFetchAll.addActionListener(e -> fetchAllLawyers());
        buttonPanel.add(btnFetchAll);

        // Create table for displaying lawyers
        lawyerTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(lawyerTable);

        // Add all components to the panel
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void addLawyer() {
        try {
            Lawyer lawyer = new Lawyer();
            lawyer.setName(txtName.getText());
//            lawyer.setDateOfBirth(new java.sql.Date(dateOfBirthChooser.getDate().getTime()));
            lawyer.setContact(txtContact.getText());
            lawyer.setAddress(txtAddress.getText());
            lawyer.setNationalId(txtNationalId.getText());
            lawyer.setLicenseNumber(txtLicenseNumber.getText());

            boolean isAdded = lawyerDAO.addLawyer(lawyer);
            if (isAdded) {
                JOptionPane.showMessageDialog(this, "Lawyer added successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add lawyer.");
            }
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void updateLawyer() {
        try {
            // Get the selected lawyer (you would have a way to select a lawyer from the table)
            int lawyerId = 1; // For example, get the lawyer ID somehow
            Lawyer lawyer = lawyerDAO.getLawyerById(lawyerId);

            if (lawyer != null) {
                lawyer.setName(txtName.getText());
//                lawyer.setDateOfBirth(new java.sql.Date(dateOfBirthChooser.getDate().getTime()));
                lawyer.setContact(txtContact.getText());
                lawyer.setAddress(txtAddress.getText());
                lawyer.setNationalId(txtNationalId.getText());
                lawyer.setLicenseNumber(txtLicenseNumber.getText());

                boolean isUpdated = lawyerDAO.updateLawyer(lawyer);
                if (isUpdated) {
                    JOptionPane.showMessageDialog(this, "Lawyer updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update lawyer.");
                }
            }
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void deleteLawyer() {
        try {
            // Get the selected lawyer's ID
            int lawyerId = 1; // For example, get the lawyer ID somehow
            boolean isDeleted = lawyerDAO.deleteLawyer(lawyerId);
            if (isDeleted) {
                JOptionPane.showMessageDialog(this, "Lawyer deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete lawyer.");
            }
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void fetchAllLawyers() {
        try {
            List<Lawyer> lawyers = lawyerDAO.getAllLawyers();
            Object[][] data = new Object[lawyers.size()][7];

            for (int i = 0; i < lawyers.size(); i++) {
                Lawyer lawyer = lawyers.get(i);
                data[i][0] = lawyer.getPersonId();
                data[i][1] = lawyer.getName();
                data[i][2] = lawyer.getDateOfBirth();
                data[i][3] = lawyer.getContact();
                data[i][4] = lawyer.getAddress();
                data[i][5] = lawyer.getNationalId();
                data[i][6] = lawyer.getLicenseNumber();
            }

            String[] columnNames = {"ID", "Name", "DOB", "Contact", "Address", "National ID", "License Number"};
            lawyerTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));

        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}


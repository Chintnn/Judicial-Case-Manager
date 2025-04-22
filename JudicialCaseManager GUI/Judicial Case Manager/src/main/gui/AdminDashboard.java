////package main.gui;
////
////import main.gui.panels.*;
////
////import javax.swing.*;
////import java.awt.*;
////
////public class AdminDashboard extends JFrame {
////
////    private final CardLayout cardLayout = new CardLayout();
////    private final JPanel contentPanel = new JPanel(cardLayout);
////
////    public AdminDashboard() {
////        setTitle("Judicial Case Manager - Admin Dashboard");
////        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        setExtendedState(JFrame.MAXIMIZED_BOTH);
////        setLayout(new BorderLayout());
////
////        // Sidebar menu
////        JPanel menuPanel = new JPanel();
////        menuPanel.setLayout(new GridLayout(0, 1));
////        menuPanel.setPreferredSize(new Dimension(200, 0));
////
////        addMenuButton(menuPanel, "Cases", new CaseManagementPanel());
////        addMenuButton(menuPanel, "Persons", new PersonManagementPanel());
////        addMenuButton(menuPanel, "Evidence", new EvidencePanel());
////        addMenuButton(menuPanel, "Hearings", new HearingPanel());
////        addMenuButton(menuPanel, "Settlements", new SettlementPanel());
////        addMenuButton(menuPanel, "Appeals", new AppealPanel());
////        addMenuButton(menuPanel, "CourtRooms", new CourtRoomPanel());
////        addMenuButton(menuPanel, "Court Staff", new CourtStaffPanel());
////        addMenuButton(menuPanel, "Bail Requests", new BailPanel());
////        addMenuButton(menuPanel, "Case History", new CaseHistoryPanel());
////
////        add(menuPanel, BorderLayout.WEST);
////        add(contentPanel, BorderLayout.CENTER);
////    }
////
////    private void addMenuButton(JPanel menuPanel, String name, JPanel panel) {
////        JButton button = new JButton(name);
////        button.addActionListener(e -> cardLayout.show(contentPanel, name));
////        menuPanel.add(button);
////
////        contentPanel.add(panel, name);
////    }
////}
//package main.gui;
//
//import main.gui.panels.*;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class AdminDashboard extends JFrame {
//
//    private final CardLayout cardLayout = new CardLayout();
//    private final JPanel contentPanel = new JPanel(cardLayout);
//
//    public AdminDashboard() {
//        setTitle("Judicial Case Manager - Admin Dashboard");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setLayout(new BorderLayout());
//
//        // Sidebar menu
//        JPanel menuPanel = new JPanel(new GridLayout(0, 1));
//        menuPanel.setPreferredSize(new Dimension(220, 0));
//        menuPanel.setBackground(new Color(30, 30, 60));
//
//        // Add main category buttons
//        addMainCategoryButton(menuPanel, "📁 Case Management", createCaseManagementMenu());
//        addMainCategoryButton(menuPanel, "⚖️ Law & Legal", createLawAndLegalMenu());
//        addMainCategoryButton(menuPanel, "👤 Person", createPersonMenu());
//        addMainCategoryButton(menuPanel, "🏛️ Infrastructure", createInfrastructureMenu());
//
//        // Initial screen
//        JLabel welcomeLabel = new JLabel("Welcome to Admin Dashboard", SwingConstants.CENTER);
//        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
//        contentPanel.add(welcomeLabel, "Welcome");
//        cardLayout.show(contentPanel, "Welcome");
//
//        add(menuPanel, BorderLayout.WEST);
//        add(contentPanel, BorderLayout.CENTER);
//        setVisible(true);
//    }
//
//    private void addMainCategoryButton(JPanel menuPanel, String name, JPanel submenuPanel) {
//        JButton button = new JButton(name);
//        styleButton(button);
//        button.addActionListener(e -> {
//            contentPanel.removeAll();
//            contentPanel.add(submenuPanel, name);
//            cardLayout.show(contentPanel, name);
//            contentPanel.revalidate();
//            contentPanel.repaint();
//        });
//        menuPanel.add(button);
//    }
//
//    private JButton createNavButton(String name)/*, JPanel panelToShow)*/ {
//        JButton button = new JButton(name);
//        styleButton(button);
////        button.addActionListener(e -> {
////            contentPanel.removeAll();
////            contentPanel.add(panelToShow, name);
////            cardLayout.show(contentPanel, name);
////            contentPanel.revalidate();
////            contentPanel.repaint();
////        });
//        return button;
//    }
//
//    // Style for all buttons
//    private void styleButton(JButton button) {
//        button.setFocusPainted(false);
//        button.setFont(new Font("SansSerif", Font.BOLD, 14));
//        button.setBackground(new Color(50, 50, 100));
//        button.setForeground(Color.WHITE);
//        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
//    }
//
//    // Submenu Panels
//
//    private JPanel createCaseManagementMenu() {
//        JPanel panel = new JPanel(new GridLayout(0, 1));
//        panel.add(createNavButton("📜 Case History"));
//        panel.add(createNavButton("📂 Case Management"));
//        return panel;
//    }
//
//    private JPanel createLawAndLegalMenu() {
//        JPanel panel = new JPanel(new GridLayout(0, 1));
//        panel.add(createNavButton("📄 Appeal"));
//        panel.add(createNavButton("🔓 Bail"));
//        panel.add(createNavButton("🧾 Evidence"));
//        panel.add(createNavButton("📅 Hearing"));
//        panel.add(createNavButton("🤝 Settlement"));
//        return panel;
//    }
//
//    private JPanel createPersonMenu() {
//        JPanel panel = new JPanel(new GridLayout(0, 1));
//        panel.add(createNavButton("🙍 Person Management"));
//        panel.add(createNavButton("👨‍⚖️ Court Staff"));
//        panel.add(createNavButton("Judge"));
//        panel.add(createNavButton("Lawyer"));
//        return panel;
//    }
//
//    private JPanel createInfrastructureMenu() {
//        JPanel panel = new JPanel(new GridLayout(0, 1));
//        panel.add(createNavButton("🏟️ Court"));
//        panel.add(createNavButton("🏛️ Court Room"));
//        return panel;
//    }
//
//    public static void main(String[] args) {
//       new AdminDashboard();
//    }
//}

package main.gui;

import main.gui.panels.*;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel contentPanel = new JPanel(cardLayout);

    public AdminDashboard() {
        setTitle("Judicial Case Manager - Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // Sidebar menu
        JPanel menuPanel = new JPanel(new GridLayout(0, 1));
        menuPanel.setPreferredSize(new Dimension(220, 0));
        menuPanel.setBackground(new Color(30, 30, 60));

        // Add main category buttons
        addMainCategoryButton(menuPanel, "📁 Case Management", createCaseManagementMenu());
        addMainCategoryButton(menuPanel, "⚖️ Law & Legal", createLawAndLegalMenu());
        addMainCategoryButton(menuPanel, "👤 Person", createPersonMenu());
        addMainCategoryButton(menuPanel, "🏛️ Infrastructure", createInfrastructureMenu());

        // Initial screen
        JLabel welcomeLabel = new JLabel("Welcome to Admin Dashboard", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contentPanel.add(welcomeLabel, "Welcome");
        cardLayout.show(contentPanel, "Welcome");

        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    private void addMainCategoryButton(JPanel menuPanel, String name, JPanel submenuPanel) {
        JButton button = new JButton(name);
        styleButton(button);
        button.addActionListener(e -> {
            contentPanel.removeAll();
            contentPanel.add(submenuPanel, name);
            cardLayout.show(contentPanel, name);
            contentPanel.revalidate();
            contentPanel.repaint();
        });
        menuPanel.add(button);
    }

    private JButton createNavButton(String name, JPanel panelToShow) {
        JButton button = new JButton(name);
        styleButton(button);
        button.addActionListener(e -> {
            contentPanel.removeAll();
            contentPanel.add(panelToShow, name);
            cardLayout.show(contentPanel, name);
            contentPanel.revalidate();
            contentPanel.repaint();
        });
        return button;
    }

    // Style for all buttons
    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(new Color(50, 50, 100));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    // Submenu Panels

    private JPanel createCaseManagementMenu() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(createNavButton("📜 Case History", new CaseHistoryPanel()));
        panel.add(createNavButton("📂 Case Management", new CaseManagementPanel()));
        return panel;
    }

    private JPanel createLawAndLegalMenu() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(createNavButton("📄 Appeal", new AppealPanel()));
        panel.add(createNavButton("🔓 Bail", new BailPanel()));
        panel.add(createNavButton("🧾 Evidence", new EvidencePanel()));
        panel.add(createNavButton("📅 Hearing", new HearingPanel()));
        panel.add(createNavButton("🤝 Settlement", new SettlementPanel()));
        return panel;
    }

    private JPanel createPersonMenu() {
        JPanel panel = new JPanel(new GridLayout(0, 1));

        // Adding navigation buttons
        panel.add(createNavButton("🙍 Person Management", new PersonManagementPanel()));
        panel.add(createNavButton("👨‍⚖️ Court Staff", new CourtStaffPanel()));

        // Uncomment and add the LawyerPanel to the menu
        panel.add(createNavButton("🧑‍⚖️ Lawyer Management", new LawyerPanel()));

        return panel;
    }


    private JPanel createInfrastructureMenu() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(createNavButton("🏟️ Court", new CourtPanel()));
        panel.add(createNavButton("🏛️ Court Room", new CourtRoomPanel()));
        return panel;
    }
}

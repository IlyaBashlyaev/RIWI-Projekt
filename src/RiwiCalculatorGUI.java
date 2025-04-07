package src;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import src.util.CalculatorUtil;

/**
 * RiwiCalculatorGUI - Grafische Benutzeroberfläche für den RIWI Rechner
 */
public class RiwiCalculatorGUI extends JFrame {
    
    // Panel-Komponenten
    private JPanel welcomePanel;
    private JPanel inputPanel;
    private JPanel productsPanel;
    private JPanel resultsPanel;
    private JPanel buttonPanel;
    
    // Eingabefelder und Ergebnislabels
    private JTextField countField;
    private JLabel sumValueLabel;
    private JLabel vatValueLabel;
    private JLabel countValueLabel;
    
    // Buttons
    private JButton calculateButton;
    private JButton resetButton;
    
    // Liste für Produkteingaben
    private List<ProductEntryPanel> productEntries;
    
    // Calculator-Instanz
    private CalculatorUtil calculator;
    
    /**
     * Konstruktor - Erstellt und initialisiert die GUI
     */
    public RiwiCalculatorGUI() {
        // Fenster-Eigenschaften
        setTitle("RIWI Rechner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setMinimumSize(new Dimension(600, 500));
        
        // Instanzen initialisieren
        productEntries = new ArrayList<>();
        calculator = new CalculatorUtil();
        
        // Panels initialisieren
        initWelcomePanel();
        initInputPanel();
        initProductsPanel();
        initResultsPanel();
        initButtonPanel();
        
        // Panels dem Hauptfenster hinzufügen
        add(welcomePanel, BorderLayout.NORTH);
        
        // Container für die mittleren Panels
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        centerPanel.add(inputPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(productsPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(resultsPanel);
        
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Fenster zentrieren
        setLocationRelativeTo(null);
    }
    
    /**
     * Initialisiert das Begrüßungs-Panel
     */
    private void initWelcomePanel() {
        welcomePanel = new JPanel();
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel welcomeLabel = new JLabel("Hallo! Das ist ein Rechner-Programm von RIWI.");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomePanel.add(welcomeLabel);
    }
    
    /**
     * Initialisiert das Eingabe-Panel für die Anzahl der Produkte
     */
    private void initInputPanel() {
        inputPanel = new JPanel();
        inputPanel.setBorder(BorderFactory.createTitledBorder("Produktanzahl"));
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JLabel countLabel = new JLabel("Geben Sie die Anzahl der Produkte ein:");
        countField = new JTextField(5);
        JButton confirmButton = new JButton("Bestätigen");
        
        inputPanel.add(countLabel);
        inputPanel.add(countField);
        inputPanel.add(confirmButton);
        
        // ActionListener für den Bestätigen-Button
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createProductEntryForms();
            }
        });
    }
    
    /**
     * Initialisiert das Panel für die Produkteingaben
     */
    private void initProductsPanel() {
        productsPanel = new JPanel();
        productsPanel.setBorder(BorderFactory.createTitledBorder("Produkteingaben"));
        productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));
        
        // Hinweistext
        JLabel infoLabel = new JLabel("<html>Hier werden Eingabefelder für Ihre Produkte angezeigt.<br>" +
                "Bitte geben Sie zuerst die Anzahl der Produkte ein und klicken Sie auf 'Bestätigen'.</html>");
        infoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        productsPanel.add(infoLabel);
    }
    
    /**
     * Initialisiert das Ergebnis-Panel
     */
    private void initResultsPanel() {
        resultsPanel = new JPanel();
        resultsPanel.setBorder(BorderFactory.createTitledBorder("Ergebnisse"));
        resultsPanel.setLayout(new GridLayout(3, 2, 5, 5));
        
        // Ergebnis-Labels erstellen
        JLabel sumLabel = new JLabel("Gesamtsumme:");
        sumValueLabel = new JLabel("0.00 €");
        
        JLabel vatLabel = new JLabel("Mehrwertsteuer:");
        vatValueLabel = new JLabel("0.00 €");
        
        JLabel countLabel = new JLabel("Anzahl der Produkte:");
        countValueLabel = new JLabel("0");
        
        // Labels zum Panel hinzufügen
        resultsPanel.add(sumLabel);
        resultsPanel.add(sumValueLabel);
        resultsPanel.add(vatLabel);
        resultsPanel.add(vatValueLabel);
        resultsPanel.add(countLabel);
        resultsPanel.add(countValueLabel);
    }
    
    /**
     * Initialisiert das Panel für die Aktions-Buttons
     */
    private void initButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        
        calculateButton = new JButton("Berechnen");
        resetButton = new JButton("Zurücksetzen");
        
        buttonPanel.add(calculateButton);
        buttonPanel.add(resetButton);
        
        // ActionListener für den Berechnen-Button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResults();
            }
        });
        
        // ActionListener für den Zurücksetzen-Button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });
    }
    
    /**
     * Erstellt Produkteingabeformulare basierend auf der eingegebenen Anzahl
     */
    private void createProductEntryForms() {
        // Bestehende Produkteinträge entfernen
        productsPanel.removeAll();
        productEntries.clear();
        
        try {
            int count = Integer.parseInt(countField.getText().trim());
            
            if (count <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Bitte geben Sie eine positive Zahl ein.",
                        "Ungültige Eingabe",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Produktanzahl im Ergebnisbereich aktualisieren
            countValueLabel.setText(String.valueOf(count));
            
            // Scrollpane für die Produkteingaben
            JPanel productsContainer = new JPanel();
            productsContainer.setLayout(new BoxLayout(productsContainer, BoxLayout.Y_AXIS));
            
            // Hinweistext für die Eingabe
            JLabel instructionLabel = new JLabel("<html>Geben Sie für jedes Produkt die folgenden Informationen ein:<br>" +
                    "- Der Name des Produkts<br>" +
                    "- Der Preis des Produkts (mit \".\" als Dezimaltrennzeichen)<br>" +
                    "- Der Typ des Produkts</html>");
            instructionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            productsContainer.add(instructionLabel);
            productsContainer.add(Box.createRigidArea(new Dimension(0, 10)));
            
            // Produkteingabefelder erstellen
            for (int i = 0; i < count; i++) {
                ProductEntryPanel productPanel = new ProductEntryPanel(i + 1);
                productEntries.add(productPanel);
                productsContainer.add(productPanel);
                productsContainer.add(Box.createRigidArea(new Dimension(0, 5)));
            }
            
            // Scrollpane hinzufügen
            JScrollPane scrollPane = new JScrollPane(productsContainer);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBorder(null);
            
            productsPanel.setLayout(new BorderLayout());
            productsPanel.add(scrollPane, BorderLayout.CENTER);
            
            // GUI aktualisieren
            productsPanel.revalidate();
            productsPanel.repaint();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Bitte geben Sie eine gültige Zahl ein.",
                    "Ungültige Eingabe",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Berechnet die Ergebnisse basierend auf den Produkteingaben
     */
    private void calculateResults() {
        if (productEntries.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Bitte geben Sie zuerst die Anzahl der Produkte ein und klicken Sie auf 'Bestätigen'.",
                    "Keine Produkte",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!validateProductEntries()) {
            return;
        }
        
        try {
            // Arrays für Preise und MwSt-Sätze erstellen
            float[] prices = new float[productEntries.size()];
            float[] vatRates = new float[productEntries.size()];
            
            for (int i = 0; i < productEntries.size(); i++) {
                ProductEntryPanel panel = productEntries.get(i);
                prices[i] = panel.getProductPrice();
                
                // Produkttyp in MwSt-Satz umwandeln
                String type = panel.getProductType();
                if ("Grundbedarf".equals(type)) {
                    vatRates[i] = 0.07f; // 7% MwSt für Grundbedarf
                } else if ("Konsumgut".equals(type)) {
                    vatRates[i] = 0.19f; // 19% MwSt für Konsumgüter
                }
            }
            
            // Berechnung durchführen mit den korrekten Methodennamen
            float sum = calculator.calcSum(prices);
            float vat = calculator.calcVAT(prices, vatRates);
            
            // Ergebnisse anzeigen (mit 2 Nachkommastellen)
            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
            sumValueLabel.setText(formatter.format(sum));
            vatValueLabel.setText(formatter.format(vat));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Bei der Berechnung ist ein Fehler aufgetreten: " + e.getMessage(),
                    "Berechnungsfehler",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Setzt das Formular zurück
     */
    private void resetForm() {
        // Eingabefelder zurücksetzen
        countField.setText("");
        
        // Produkteingaben entfernen
        productsPanel.removeAll();
        productEntries.clear();
        
        // Hinweistext wieder anzeigen
        JLabel infoLabel = new JLabel("<html>Hier werden Eingabefelder für Ihre Produkte angezeigt.<br>" +
                "Bitte geben Sie zuerst die Anzahl der Produkte ein und klicken Sie auf 'Bestätigen'.</html>");
        infoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));
        productsPanel.add(infoLabel);
        
        // Ergebnis-Labels zurücksetzen
        sumValueLabel.setText("0.00 €");
        vatValueLabel.setText("0.00 €");
        countValueLabel.setText("0");
        
        // GUI aktualisieren
        productsPanel.revalidate();
        productsPanel.repaint();
    }
    
    /**
     * Überprüft, ob alle Produkteingaben gültig sind
     */
    private boolean validateProductEntries() {
        for (int i = 0; i < productEntries.size(); i++) {
            ProductEntryPanel panel = productEntries.get(i);
            
            // Name überprüfen
            if (panel.getProductName().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Bitte geben Sie einen Namen für Produkt #" + (i + 1) + " ein.",
                        "Ungültige Eingabe",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // Preis überprüfen
            try {
                float price = panel.getProductPrice();
                if (price <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "Der Preis für Produkt #" + (i + 1) + " muss größer als 0 sein.",
                            "Ungültige Eingabe",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this,
                        "Bitte geben Sie einen gültigen Preis für Produkt #" + (i + 1) + " ein.",
                        "Ungültige Eingabe",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // Produkttyp überprüfen
            if (panel.getProductType() == null) {
                JOptionPane.showMessageDialog(this,
                        "Bitte wählen Sie einen Typ für Produkt #" + (i + 1) + " aus.",
                        "Ungültige Eingabe",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Innere Klasse für das Produkteingabeformular
     */
    private class ProductEntryPanel extends JPanel {
        private JTextField nameField;
        private JFormattedTextField priceField;
        private JRadioButton basicRadio;
        private JRadioButton luxuryRadio;
        private ButtonGroup typeGroup;
        
        public ProductEntryPanel(int productNumber) {
            setBorder(BorderFactory.createTitledBorder("Produkt #" + productNumber));
            setLayout(new GridBagLayout());
            setMaximumSize(new Dimension(Short.MAX_VALUE, 120));
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(2, 5, 2, 5);
            
            // Name
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.weightx = 0.2;
            add(new JLabel("Name:"), gbc);
            
            nameField = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            gbc.weightx = 0.8;
            add(nameField, gbc);
            
            // Preis
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weightx = 0.2;
            add(new JLabel("Preis:"), gbc);
            
            // Formatiertes Textfeld für den Preis
            NumberFormat format = NumberFormat.getNumberInstance(Locale.GERMAN);
            format.setMinimumFractionDigits(2);
            format.setMaximumFractionDigits(2);
            
            NumberFormatter formatter = new NumberFormatter(format);
            formatter.setValueClass(Float.class);
            formatter.setMinimum(0.0f);
            formatter.setAllowsInvalid(false);
            
            priceField = new JFormattedTextField(format);
            priceField.setValue(0.0f);
            priceField.setColumns(10);
            
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weightx = 0.4;
            add(priceField, gbc);
            
            // Produkttyp (RadioButtons)
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weightx = 0.1;
            add(new JLabel("Typ:"), gbc);
            
            // RadioButtons für den Produkttyp
            typeGroup = new ButtonGroup();
            
            basicRadio = new JRadioButton("Grundbedarf (7%)");
            luxuryRadio = new JRadioButton("Konsumgut (19%)");
            
            typeGroup.add(basicRadio);
            typeGroup.add(luxuryRadio);
            
            JPanel radioPanel = new JPanel();
            radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
            radioPanel.add(basicRadio);
            radioPanel.add(luxuryRadio);
            
            gbc.gridx = 3;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weightx = 0.3;
            add(radioPanel, gbc);
        }
        
        /**
         * Gibt den Namen des Produkts zurück
         */
        public String getProductName() {
            return nameField.getText();
        }
        
        /**
         * Gibt den Preis des Produkts zurück
         */
        public float getProductPrice() throws ParseException {
            Number value = (Number) priceField.getValue();
            if (value == null) {
                return 0.0f;
            }
            return value.floatValue();
        }
        
        /**
         * Gibt den Typ des Produkts zurück
         */
        public String getProductType() {
            if (basicRadio.isSelected()) {
                return "Grundbedarf";
            } else if (luxuryRadio.isSelected()) {
                return "Konsumgut";
            }
            return null;
        }
        
        /**
         * Überprüft, ob ein Produkttyp ausgewählt wurde
         */
        public boolean isProductTypeSelected() {
            return basicRadio.isSelected() || luxuryRadio.isSelected();
        }
    }
    
    /**
     * Hauptmethode - Startet die Anwendung
     */
    public static void main(String[] args) {
        // GUI im Event-Dispatch-Thread erstellen
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RiwiCalculatorGUI gui = new RiwiCalculatorGUI();
                gui.setVisible(true);
            }
        });
    }
}

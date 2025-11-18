import javax.swing.*;
import java.awt.*;
import java.io.*;

public class RandProductSearch extends JFrame {
    JTextField searchField = new JTextField(20);
    JTextArea resultsArea = new JTextArea(15, 40);

    public RandProductSearch() {
        setTitle("Random Product Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search Name:"));
        searchPanel.add(searchField);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        resultsArea.setEditable(false);
        add(new JScrollPane(resultsArea), BorderLayout.CENTER);

        JButton quitButton = new JButton("Quit");
        add(quitButton, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> search());
        searchField.addActionListener(e -> search());
        quitButton.addActionListener(e -> System.exit(0));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void search() {
        String searchTerm = searchField.getText().trim().toLowerCase();
        if (searchTerm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a search term");
            return;
        }

        File file = new File("ProductData.dat");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "No data file found");
            return;
        }

        StringBuilder results = new StringBuilder();
        int matches = 0;

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            int numRecords = (int) (raf.length() / Product.RECORD_SIZE);

            for (int i = 0; i < numRecords; i++) {
                raf.seek(i * Product.RECORD_SIZE);

                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < Product.NAME_LENGTH; j++) {
                    sb.append(raf.readChar());
                }
                String name = sb.toString().trim();

                sb = new StringBuilder();
                for (int j = 0; j < Product.DESCRIPTION_LENGTH; j++) {
                    sb.append(raf.readChar());
                }
                String desc = sb.toString().trim();

                sb = new StringBuilder();
                for (int j = 0; j < Product.ID_LENGTH; j++) {
                    sb.append(raf.readChar());
                }
                String id = sb.toString().trim();

                double cost = raf.readDouble();

                if (name.toLowerCase().contains(searchTerm)) {
                    matches++;
                    results.append("ID: ").append(id).append("\n");
                    results.append("Name: ").append(name).append("\n");
                    results.append("Description: ").append(desc).append("\n");
                    results.append("Cost: $").append(String.format("%.2f", cost)).append("\n\n");
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file");
            return;
        }

        if (matches == 0) {
            resultsArea.setText("No matches found");
        } else {
            resultsArea.setText("Found " + matches + " match(es):\n\n" + results);
        }
    }

    public static void main(String[] args) {
        new RandProductSearch();
    }
}

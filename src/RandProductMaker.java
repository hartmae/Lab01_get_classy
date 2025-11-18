import javax.swing.*;
import java.awt.*;
import java.io.*;

public class RandProductMaker extends JFrame {
    JTextField nameField = new JTextField(20);
    JTextField descriptionField = new JTextField(20);
    JTextField idField = new JTextField(6);
    JTextField costField = new JTextField(10);
    JTextField recordCountField = new JTextField("0");

    RandomAccessFile raf;
    int recordCount = 0;

    public RandProductMaker() {
        setTitle("Random Product Maker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Description:"));
        formPanel.add(descriptionField);

        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Cost:"));
        formPanel.add(costField);

        formPanel.add(new JLabel("Record Count:"));
        recordCountField.setEditable(false);
        formPanel.add(recordCountField);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton quitButton = new JButton("Quit");
        buttonPanel.add(addButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addRecord());
        quitButton.addActionListener(e -> {
            try { if (raf != null) raf.close(); } catch (IOException ex) {}
            System.exit(0);
        });

        try {
            raf = new RandomAccessFile("ProductData.dat", "rw");
            recordCount = (int) (raf.length() / Product.RECORD_SIZE);
            recordCountField.setText(String.valueOf(recordCount));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error opening file");
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addRecord() {
        String name = nameField.getText().trim();
        String description = descriptionField.getText().trim();
        String id = idField.getText().trim();
        String costStr = costField.getText().trim();

        if (name.isEmpty() || id.isEmpty() || costStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill in all required fields");
            return;
        }

        double cost;
        try {
            cost = Double.parseDouble(costStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid cost");
            return;
        }

        Product product = new Product(name, description, id, cost);

        try {
            raf.seek(raf.length());

            String paddedName = product.getPaddedName();
            String paddedDesc = product.getPaddedDescription();
            String paddedID = product.getPaddedID();

            for (int i = 0; i < Product.NAME_LENGTH; i++) {
                raf.writeChar(paddedName.charAt(i));
            }
            for (int i = 0; i < Product.DESCRIPTION_LENGTH; i++) {
                raf.writeChar(paddedDesc.charAt(i));
            }
            for (int i = 0; i < Product.ID_LENGTH; i++) {
                raf.writeChar(paddedID.charAt(i));
            }
            raf.writeDouble(cost);

            recordCount++;
            recordCountField.setText(String.valueOf(recordCount));

            nameField.setText("");
            descriptionField.setText("");
            idField.setText("");
            costField.setText("");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing record");
        }
    }

    public static void main(String[] args) {
        new RandProductMaker();
    }
}

package org.example;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CiteManually extends JFrame {

    private JTextArea citationText;
    private JTextArea citedInformationText; // New JTextArea for displaying cited information
    private JComboBox<String> citationStyleBox;
    private JTextField authorField, publisherField, publishingDateField, titleField, websiteTitleField, urlField;

    public CiteManually(String initialCitation) {
        setTitle("Manually Cite");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 900); // Adjust size as needed
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 35 45 30 45", "[grow, fill]"));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%);");

        // Display initial citation
        JLabel initialCitationLabel = new JLabel("Not Satisfied? Cite Manually!");
        initialCitationLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +3");

        citationText = new JTextArea(initialCitation, 50, 100); // Increased rows to 50
        citationText.setWrapStyleWord(true);
        citationText.setLineWrap(true);
        citationText.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(citationText);
        scrollPane.setPreferredSize(new Dimension(600, 300)); // Adjusted scroll pane size

        panel.add(scrollPane, "gapy 10, grow");
        panel.add(initialCitationLabel, "gapy 10");

        JLabel authorLabel = new JLabel("Author:");
        JLabel publisherLabel = new JLabel("Publisher:");
        JLabel publishingDateLabel = new JLabel("Publishing Date:");
        JLabel titleLabel = new JLabel("Title:");
        JLabel websiteTitleLabel = new JLabel("Website Title:");
        JLabel urlLabel = new JLabel("URL:");

        authorField = new JTextField(30);
        publisherField = new JTextField(30);
        publishingDateField = new JTextField(30);
        titleField = new JTextField(30);
        websiteTitleField = new JTextField(30);
        urlField = new JTextField(30);

        JPanel citationPanel = new JPanel(new MigLayout("wrap 2, fillx", "[right][fill]"));
        citationPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%);");

        citationPanel.add(authorLabel);
        citationPanel.add(authorField, "wrap, gaptop 3");
        citationPanel.add(publisherLabel);
        citationPanel.add(publisherField, "wrap, gaptop 3");
        citationPanel.add(publishingDateLabel);
        citationPanel.add(publishingDateField, "wrap, gaptop 3");
        citationPanel.add(titleLabel);
        citationPanel.add(titleField, "wrap, gaptop 3");
        citationPanel.add(websiteTitleLabel);
        citationPanel.add(websiteTitleField, "wrap, gaptop 3");
        citationPanel.add(urlLabel);
        citationPanel.add(urlField, "wrap, gaptop 3");

        citationStyleBox = new JComboBox<>(new String[]{"MLA9", "MLA8", "APA", "Chicago"});
        citationStyleBox.putClientProperty(FlatClientProperties.STYLE, "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,10%);" +
                "borderWidth:0;" +
                "focusWidth:3;" +
                "innerFocusWidth:3");

        JButton citeManuallyButton = new JButton("Cite Manually");
        citeManuallyButton.putClientProperty(FlatClientProperties.STYLE, "[light]background: darken(@background,10%);" +
                "[dark]background: lighten(@background,10%);" +
                "borderWidth: 0;" +
                "focusWidth: 0;" +
                "innerFocusWidth: 0;" +
                "font: bold +5;");
        citeManuallyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String author = authorField.getText();
                String publisher = publisherField.getText();
                String publishingDate = publishingDateField.getText();
                String title = titleField.getText();
                String websiteTitle = websiteTitleField.getText();
                String url = urlField.getText();
                String selectedStyle = (String) citationStyleBox.getSelectedItem();

                String citation = Citation.makeCitation(selectedStyle, title, author, publisher, publishingDate,"" ,websiteTitle, url);

                // Display the cited information in the new text area
                citedInformationText.setText(citation);
            }
        });

        // New JTextArea for displaying cited information
        citedInformationText = new JTextArea(50, 100); // Increased rows to 50
        citedInformationText.setWrapStyleWord(true);
        citedInformationText.setLineWrap(true);
        citedInformationText.setEditable(false);
        JScrollPane citedScrollPane = new JScrollPane(citedInformationText);
        citedScrollPane.setPreferredSize(new Dimension(600, 300)); // Adjusted scroll pane size

        panel.add(citationPanel, "span, wrap, gaptop 10");
        panel.add(citationStyleBox, "span, wrap, gaptop 10");
        panel.add(citeManuallyButton, "span, wrap, gaptop 10");
        panel.add(citedScrollPane, "gapy 10, grow");

        setContentPane(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String initialCitation = "Initial citation from URL"; // Replace with actual citation
            CiteManually frame = new CiteManually(initialCitation);
            frame.setVisible(true);
        });
    }
}

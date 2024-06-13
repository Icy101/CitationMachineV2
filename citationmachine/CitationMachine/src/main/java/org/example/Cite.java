package org.example;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cite extends JPanel {
    private JTextField url_text;
    private JButton cite;
    private String currentStyle = "MLA9";
    private String currentNews = "Post Media";

    public Cite() {
        components();
    }

    private void components() {
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));

        url_text = new JTextField();
        cite = new JButton("Cite");

        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "[grow, fill]"));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20;"
                + "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%);");

        url_text.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter the URL");

        cite.putClientProperty(FlatClientProperties.STYLE, "[light]background: darken(@background,10%);" +
                "[dark]background: lighten(@background,10%);" +
                "borderWidth: 0;" +
                "focusWidth: 0;" +
                "innerFocusWidth: 0;" +
                "font: bold +5;");
        cite.addActionListener(e -> {
            String url = url_text.getText();
            String citation = generateCitation(url);

            // Create a new instance of CiteManually
            CiteManually citeManuallyWindow = new CiteManually(citation);

            // Set the window properties
            citeManuallyWindow.setTitle("Manually Cite");
            citeManuallyWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            citeManuallyWindow.setSize(800, 900);
            citeManuallyWindow.setLocationRelativeTo(null);

            // Make the window visible
            citeManuallyWindow.setVisible(true);
        });

        JLabel title = new JLabel("Welcome Back!");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold +10");

        JLabel startCiting = new JLabel("Start citing information from TorStar, Post Media, or The Canadian Encyclopedia!");
        startCiting.putClientProperty(FlatClientProperties.STYLE, "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%);");

        panel.add(title);
        panel.add(startCiting, "gapy 8");
        panel.add(new JLabel("URL"), "gapy 8");
        panel.add(url_text);

        JLabel citationStyle = new JLabel("Pick a citation style:");
        citationStyle.putClientProperty(FlatClientProperties.STYLE, "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%);");

        JLabel website = new JLabel("Pick a website to cite:");
        website.putClientProperty(FlatClientProperties.STYLE, "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%);");

        panel.add(website, "gapy 8");

        String[] websites = {"Post Media", "TorStar", "The Canadian Encyclopedia"};
        JComboBox<String> websiteList = new JComboBox<>(websites);
        websiteList.addActionListener(e -> {
            String selected = (String) websiteList.getSelectedItem();
            if (selected != null) {
                currentNews = selected;
            }
        });

        websiteList.putClientProperty(FlatClientProperties.STYLE, "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,10%);" +
                "borderWidth:0;" +
                "focusWidth:3;" +
                "innerFocusWidth:3");

        String[] options = {"MLA9", "MLA8", "APA", "Chicago"};
        JComboBox<String> optionList = new JComboBox<>(options);
        optionList.addActionListener(e -> {
            String selected = (String) optionList.getSelectedItem();
            if (selected != null) {
                currentStyle = selected;
            }
        });

        optionList.putClientProperty(FlatClientProperties.STYLE, "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,10%);" +
                "borderWidth:0;" +
                "focusWidth:3;" +
                "innerFocusWidth:3");

        panel.add(websiteList, "gapy 3");
        panel.add(citationStyle, "gapy 8");
        panel.add(optionList, "gapy 3");
        panel.add(cite, "gapy 10");

        add(panel);
    }

    private String generateCitation(String url) {
        String title = Scraper.getTitle(url);
        String accessDate = Scraper.getDate();
        String author = "";
        String publisher = "";
        String publishingDate = "";
        String newsletter = "";

        switch (currentNews) {
            case "Post Media" -> {
                author = Scraper.getAuthorPM(url);
                publisher = "Postmedia Network Inc";
                publishingDate = Scraper.getPublishingDatePM(url);
                newsletter = Scraper.getNewsletterPM(url);
            }
            case "TorStar" -> {
                author = Scraper.getAuthorTS(url);
                publisher = "Jordan Bitove";
                publishingDate = Scraper.getPublishingDateTS(url);
                newsletter = Scraper.getNewsletterTS(url);
            }
            case "The Canadian Encyclopedia" -> {
                author = Scraper.getAuthorCNE(url);
                publisher = "Historica Canada";
                publishingDate = Scraper.getPublishingDateCNE(url);
                newsletter = "The Canadian Encyclopedia";
            }
        }

        return Citation.makeCitation(currentStyle, title, author, publisher, publishingDate, accessDate, newsletter, url);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Citation Software");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 700);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new Cite());
            frame.setVisible(true);
        });
    }
}

package org.example;

public class Citation {

    public static String makeCitation(String style, String title, String author, String publisher, String publishingDate, String accessDate, String newsletter, String url) {
        switch (style) {
            case "MLA9":
                return MLA9(author, title, newsletter, publisher, publishingDate, url);
            case "MLA8":
                return MLA8(author, title, newsletter, publisher, publishingDate, url, accessDate);
            case "APA":
                return APA(author, title, newsletter, publishingDate, url);
            case "Chicago":
                return Chicago(author, title, newsletter, publishingDate, url);
            default:
                throw new IllegalArgumentException("Unknown citation style: " + style);
        }
    }

    public static String MLA9(String author, String title, String newsletter, String publisher, String publishingDate, String url) {
        String[] authorArray = author.split(" ");
        author = authorArray[1] + ", " + authorArray[0];
        String newDate = publishingDate.replace(",", "");
        String[] dateArray = newDate.split(" ");
        publishingDate = dateArray[1] + " " + dateArray[0] + " " + dateArray[2];
        return author + ". " + "\"" + title + ".\" " + newsletter + ", " + publisher + ", " + publishingDate + "\n" + url;
    }

    public static String MLA8(String author, String title, String newsletter, String publisher, String publishingDate, String url, String date) {
        String[] authorArray = author.split(" ");
        author = authorArray[1] + ", " + authorArray[0];
        String newDate = publishingDate.replace(",", "");
        String[] dateArray = newDate.split(" ");
        publishingDate = dateArray[1] + " " + dateArray[0] + " " + dateArray[2];
        return author + ". " + "\"" + title + ".\" " + newsletter + ", " + publisher + ", " + publishingDate + "\n" + url + ". Accessed " + date + ".";
    }

    public static String APA(String author, String title, String newsletter, String publishingDate, String url) {
        String[] authorArray = author.split(" ");
        char first = authorArray[0].charAt(0);
        author = authorArray[1] + ", " + first;
        String newDate = publishingDate.replace(",", "");
        String[] dateArray = newDate.split(" ");
        publishingDate = dateArray[1] + " " + dateArray[0] + " " + dateArray[2];
        return author + ". " + "(" + publishingDate + "). " + title + ". " + newsletter + ". " + url;
    }

    public static String Chicago(String author, String title, String newsletter, String publishingDate, String url) {
        String[] authorArray = author.split(" ");
        author = authorArray[1] + ", " + authorArray[0];
        return author + ". " + "\"" + title + ".\" " + newsletter + ". " + publishingDate + ". " + url;
    }
}

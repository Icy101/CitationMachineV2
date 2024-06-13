package org.example;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Scraper {

    public static String getTitle(String url){
        try {
            Document doc = Jsoup.connect(url).get();
            return doc.title();

        }catch (IOException e){
            return null;
        }
    }
    public static String getAuthorTS(String url){
        try{
            Document doc = Jsoup.connect(url).get();
            Element author = doc.selectFirst("meta[name=author]");
            if (author != null){
                return author.attr("content");
            }else{
                return null;
            }
        }catch (IOException e){
            return null;
        }
    }
    public static String getPublishingDateTS(String url){
        try{
            Document doc = Jsoup.connect(url).get();
            Element date =  doc.selectFirst("div.dates .tnt-date.asset-date.text-muted");
            if (date != null){
                return date.text();
            }else{
                return null;
            }
        }catch (IOException e){
            return null;
        }
    }
    public static String getNewsletterTS(String url){
        try{
            Document doc = Jsoup.connect(url).get();
            Element news = doc.selectFirst("meta[property=og:site_name]");
            if (news != null){
                return news.attr("content");
            }else{
                return null;
            }
        }catch (IOException e){
            return null;
        }
    }
    public static String getAuthorPM(String url){
        try{
            Document doc = Jsoup.connect(url).get();
            Elements author = doc.getElementsByClass("published-by__author");
            if (author.isEmpty()){
                return null;
            }else{
                return author.text();
            }
        }catch (IOException e){
            return null;
        }
    }
    public static String getPublishingDatePM(String url){
        try{
            Document doc = Jsoup.connect(url).get();
            Elements date =  doc.getElementsByClass("published-date__since");
            if (date.isEmpty()){
                return null;
            }else{
                String publishedDate = date.text();
                return publishedDate.replace("Published ", "");
            }
        }catch (IOException e){
            return null;
        }
    }
    public static String getNewsletterPM(String url){
        try{
            Document doc = Jsoup.connect(url).get();
            Element news = doc.selectFirst("link[title]");
            if (news != null){
                String newsletter = news.attr("title");
                return newsletter.replace(" » Feed", "");
            }else{
                return null;
            }
        }catch (IOException e){
            return null;
        }
    }
    public static String getTitleCNE(String url){
        try{
            Document doc = Jsoup.connect(url).get();
            Element title = doc.selectFirst("meta[name=title]");
            if(title != null){
                return title.attr("content");
            }else{
                return null;
            }
        }catch (IOException e){
            return null;
        }
    }

    public static String getAuthorCNE(String url){
        try{
            Document doc = Jsoup.connect(url).get();
            Element author = doc.selectFirst("div.article-info__column .info-text__value a.b-link");
            if(author != null){
                return author.text();
            }else{
                return null;
            }
        }catch (IOException e){
            return null;
        }
    }
    public static String getPublishingDateCNE(String url){
        try{
            Document doc = Jsoup.connect(url).get();
            Element date =  doc.selectFirst("div.article-info__column .info-text__value--date");
            if (date != null){
                return date.text();
            }else{
                return null;
            }
        }catch (IOException e){
            return null;
        }
    }

    public static String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}



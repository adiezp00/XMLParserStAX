package com.staxmod.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Adrian Diez
 * @author Emanuel Iosif
 * @author Ivan de Paz
 * @since 15/06/2014
 * @version 1
 *
 * Book class that store all the data and we use it for formatting the result.
 * DAO for books, have the same elements that XML book, the we import the content by iterator or cursor.
 */
public class Book {

    /** Book's ISBN **/
    private String m_sISBN;

    /** Book's title **/
    private String m_sTitle;

    /** Book's authors **/
    private List<Author> m_lAuthors;

    /** Book's editorial **/
    private String m_sEditorial;

    /** Book's price **/
    private double m_dPrice;

    /**
     * Book's constructor
     */
    public Book() {
        this.setTitle("");
        this.setAuthors(new ArrayList<Author>());
        this.setEditorial("");
        this.setISBN("");
        this.setPrice(0.0d);
    }

    /**
     * Getter of the ISBN
     *
     * @return the ISBN
     */
    public String getISBN() {
        return m_sISBN;
    }

    /**
     * Getter of the title.
     *
     * @return the title
     */
    public String getTitle() {
        return m_sTitle;
    }

    /**
     * Getter of the authors.
     *
     * @return the authors
     */
    public List<Author> getAuthors() {
        return m_lAuthors;
    }

    /**
     * Getter of the editorial.
     *
     * @return the editorial
     */
    public String getEditorial() {
        return m_sEditorial;
    }

    /**
     * Getter of the price.
     *
     * @return the price.
     */
    public double getPrice() {
        return m_dPrice;
    }

    /**
     * Setter of the ISBN
     *
     * @param ISBN String
     */
    public void setISBN(String ISBN) {
        this.m_sISBN = ISBN;
    }

    /**
     * Setter of the title.
     *
     * @param title String
     */
    public void setTitle(String title) {
        this.m_sTitle = title;
    }

    /**
     * Setter of the authors.
     *
     * @param authors List<Author>
     */
    public void setAuthors(List<Author> authors) {
        this.m_lAuthors = authors;
    }

    /**
     * Setter of the editorial.
     *
     * @param editorial String
     */
    public void setEditorial(String editorial) {
        this.m_sEditorial = editorial;
    }

    /**
     * Setter of the price
     *
     * @param price double
     */
    public void setPrice(double price) {
        this.m_dPrice = price;
    }

    /**
     * Add one author to the author list.
     *
     * @param author Author
     */
    public void addAuthor(Author author) {
        this.getAuthors().add(author);
    }

    @Override public String toString() {
        String authorText = "";
        for(Author author : getAuthors()) {
            authorText += author.toString() + ",";
        }
        return "Los autores: " + authorText + " publicaron el libro \"" + this.getTitle() + "\" en la editorial " +  this.getEditorial() + ". Cuesta " + this.getPrice() + " euros.";
    }
}

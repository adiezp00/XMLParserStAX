package com.staxmod.dao;

import com.staxmod.core.Globals;

/**
 * @author Adrian Diez
 * @author Emanuel Iosif
 * @author Ivan de Paz
 * @since 15/06/2014
 * @version 1
 *
 * Author class that store all the data and we use it for formatting the result.
 * DAO for authors, have the same elements that XML, the we import the content by iterator or cursor.
 */
public class Author {

    /** Name of the author **/
    private String m_sName;

    /** First name of the author **/
    private String m_sFirstName;

    /** Second name of the author **/
    private String m_sSecondName;

    /** Title of the author **/
    private String m_sTitle;

    /**
     * Author's empty constructor.
     */
    public Author() {
        this.setTitle(Globals.EMPTY);
        this.setName(Globals.EMPTY);
        this.setFirstName(Globals.EMPTY);
        this.setSecondName(Globals.EMPTY);
    }

    /**
     * Getter of the name.
     *
     * @return the name
     */
    public String getName() {
        return this.m_sName;
    }

    /**
     * Getter of the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return this.m_sFirstName;
    }

    /**
     * Getter of the second name.
     *
     * @return the second name
     */
    public String getSecondName() {
        return this.m_sSecondName;
    }

    /**
     * Getter of the title if exists
     *
     * @return the title or ""
     */
    public String getTitle() {
        return this.m_sTitle;
    }

    /**
     * Setter of the name.
     *
     * @param name String
     */
    public void setName(String name) {
        this.m_sName = name;
    }

    /**
     * Setter of the first name.
     *
     * @param firstName String
     */
    public void setFirstName(String firstName) {
        this.m_sFirstName = firstName;
    }

    /**
     * Setter of the second name.
     *
     * @param secondName String
     */
    public void setSecondName(String secondName) {
        this.m_sSecondName = secondName;
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
     * Override of toString method, return the name of the author with title and all surnames.
     *
     * @return author object
     */
    @Override public String toString() {
        return this.getTitle()
                + this.getName()
                + this.getFirstName()
                + this.getSecondName();
    }
}

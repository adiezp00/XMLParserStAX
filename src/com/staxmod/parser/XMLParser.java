package com.staxmod.parser;

import com.staxmod.core.Globals;
import com.staxmod.dao.Author;
import com.staxmod.dao.Book;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian Diez
 * @author Emanuel Iosif
 * @author Ivan de Paz
 * @since 16/06/2014
 * @version 1
 *
 * Class that parse the xml file into the correct format. Cursor based.
 */
public class XMLParser {

    /** Semaphore about title **/
    private static boolean m_bTitle;

    /** Semaphore about authors **/
    private static boolean m_bAuthors;

    /** Semaphore about editorial **/
    private static boolean m_bEditorial;

    /** Semaphore about price **/
    private static boolean m_bPrice;

    /**
     * This method parse the xml to load the dao's for show the
     * required info to the user.
     *
     * @param fileName String
     * @return List of books.
     */
    public static List<Book> parseXML(String fileName) {

        List<Book> bookList = new ArrayList<Book>();                                            // Must load here the books
        Book bookToStore = new Book();

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();                        // Need it for create the stream reader.

        try {
            XMLStreamReader xmlStreamReader
                    = xmlInputFactory.createXMLStreamReader(new FileInputStream(fileName));     // For navigate in the xml

            int event = xmlStreamReader.getEventType();                                         // Use the events for navigate into the xml

            do {
                switch(event) {
                    //TODO finalizar el parser, testearlo y refactorizar lo necesario.
                    case XMLStreamConstants.START_ELEMENT:
                        if(xmlStreamReader.getLocalName().equals(Globals.BOOK)){
                            bookToStore = new Book();
                            bookToStore.setISBN(xmlStreamReader.getAttributeValue(Globals.ZERO_I));
                        }else if(xmlStreamReader.getLocalName().equals(Globals.TITLE_B)){
                            m_bTitle = Globals.EXISTS;
                        }else if(xmlStreamReader.getLocalName().equals("autores")){
                            m_bAuthors = Globals.EXISTS;
                        }else if(xmlStreamReader.getLocalName().equals("editorial")){
                            m_bPrice = Globals.EXISTS;
                        }else if(xmlStreamReader.getLocalName().equals("editorial")){
                            m_bEditorial = Globals.EXISTS;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        if(m_bTitle) {
                            bookToStore.setTitle(xmlStreamReader.getText());
                            m_bTitle = Globals.NO_EXISTS;
                        }else if(m_bAuthors){
                            Author author = null;
                            boolean name = Globals.NO_EXISTS, firstname = Globals.NO_EXISTS,
                                    secondname = Globals.NO_EXISTS, title = Globals.NO_EXISTS;
                            switch (xmlStreamReader.getEventType()) {
                                case XMLStreamConstants.START_ELEMENT:
                                    if(xmlStreamReader.getLocalName().equals(Globals.AUTHOR)){
                                        author = new Author();
                                    }else if(xmlStreamReader.getLocalName().equals(Globals.NAME)){
                                        name = Globals.EXISTS;
                                    }else if(xmlStreamReader.getLocalName().equals("primerApellido")){
                                        firstname = Globals.EXISTS;
                                    }else if(xmlStreamReader.getLocalName().equals("segundoApellido")){
                                        secondname = Globals.EXISTS;
                                    }else if(xmlStreamReader.getLocalName().equals("titulo")){
                                        title = Globals.EXISTS;
                                    }
                                    break;
                                case XMLStreamConstants.CHARACTERS:
                                    if(name) {
                                       author.setName(xmlStreamReader.getText());
                                        name = Globals.NO_EXISTS;
                                    }
                                    //TODO Lo mismo que con name para el resto de casos.
                            }

                            m_bAuthors =false;
                        }else if(m_bEditorial){
                            //TODO
                            m_bEditorial =false;
                        }else if(m_bPrice){
                            //TODO
                            m_bPrice =false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        if(xmlStreamReader.getLocalName().equals(Globals.BOOK)){
                            bookList.add(bookToStore);
                        }
                        break;
                }

            } while(xmlStreamReader.hasNext() && (event = xmlStreamReader.next()) != Globals.ERROR);

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
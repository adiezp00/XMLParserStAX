package com.staxmod.parser;

import com.staxmod.com.staxmod.core.Globals;
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
                        if(xmlStreamReader.getLocalName().equals("Libro")){
                            bookToStore = new Book();
                            bookToStore.setISBN(xmlStreamReader.getAttributeValue(0));
                        }else if(xmlStreamReader.getLocalName().equals("titulo")){
                            m_bTitle = true;
                        }else if(xmlStreamReader.getLocalName().equals("autores")){
                            m_bAuthors = true;
                        }else if(xmlStreamReader.getLocalName().equals("editorial")){
                            m_bPrice = true;
                        }else if(xmlStreamReader.getLocalName().equals("editorial")){
                            m_bEditorial = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        if(m_bTitle) {
                            bookToStore.setTitle(xmlStreamReader.getText());
                            m_bTitle =false;
                        }else if(m_bAuthors){
                            Author author = null;
                            boolean name = false, firstname = false, secondname = false, title = false;
                            switch (xmlStreamReader.getEventType()) {
                                case XMLStreamConstants.START_ELEMENT:
                                    if(xmlStreamReader.getLocalName().equals("autor")){
                                        author = new Author();
                                    }else if(xmlStreamReader.getLocalName().equals("nombre")){
                                        name = true;
                                    }else if(xmlStreamReader.getLocalName().equals("primerApellido")){
                                        firstname = true;
                                    }else if(xmlStreamReader.getLocalName().equals("segundoApellido")){
                                        secondname = true;
                                    }else if(xmlStreamReader.getLocalName().equals("titulo")){
                                        title = true;
                                    }
                                    break;
                                case XMLStreamConstants.CHARACTERS:
                                    if(name) {
                                       author.setName(xmlStreamReader.getText());
                                        name = false;
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
                        if(xmlStreamReader.getLocalName().equals("Libro")){
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
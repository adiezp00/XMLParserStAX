package com.staxmod.com.staxmod.core;

import com.staxmod.dao.Book;
import com.staxmod.parser.XMLParser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.List;

/**
 * @author Adrian Diez
 * @author Emanuel Iosif
 * @author Ivan de Paz
 * @since 16/06/2014
 * @version 1
 *
 * Main class of the program that let the user to select one XML file,
 * and show the result in the screen.
 */
public class Main {

    /** File chooser **/
    private static JFileChooser m_fChooser;

    /**
     * Main method that allow the user to use the application
     * @param args String...
     */
    public static void main(String... args) {

        m_fChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(Globals.XML_FILES, Globals.XML);
        m_fChooser.setFileFilter(filter);

        if (m_fChooser.showOpenDialog(m_fChooser) == JFileChooser.APPROVE_OPTION) {
            String message;
            File file = m_fChooser.getSelectedFile();

            // Parse all the books in the file.
            List<Book> bookList = XMLParser.parseXML(file.getName());

            // Make the message with the format.
            message = Globals.MSG_COUNT_1
                    + String.valueOf(bookList.size())
                    + Globals.MSG_COUNT_2
                    + Globals.NEXT_LINE;

            // Iterate over the books and create the message.
            for(Book author : bookList){
                message += author.toString() + Globals.NEXT_LINE;
            }

            showInfo(message, Globals.CORRECT_TITLE, JOptionPane.PLAIN_MESSAGE);
        } else {
            showInfo(Globals.ERROR_MSG, Globals.ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Show one message in screen with some information.
     *
     * @param message String
     * @param title String
     * @param flag Integer
     */
    private static void showInfo(String message, String title, int flag) {
        JOptionPane.showMessageDialog(m_fChooser, message, title, flag);
    }
}

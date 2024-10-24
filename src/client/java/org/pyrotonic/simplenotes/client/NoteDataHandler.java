package org.pyrotonic.simplenotes.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.pyrotonic.simplenotes.Simplenotes;

public class NoteDataHandler {
    static String Content;
    static String Filename;


    public NoteDataHandler(String content, String filename) {
        Content = content;
        Filename = filename;
    }


    public String getFilename() {
        return Filename;
    }

    public static void saveContent(String content, String filename) {
        try {
            if (filename.contains(".txt")) {
                filename = filename.replace(".txt", "");
            }
            FileWriter note = new FileWriter(SimplenotesClient.NOTE_DIRECTORY_PATH + filename + ".txt");
            note.write(content);
            note.close();
        } catch (FileNotFoundException err) {
            Simplenotes.LOGGER.error("Simplenotes directory not found, please restart your game!");
        } catch (IOException err) {
            Simplenotes.LOGGER.error("An IOException error occurred while saving the file.", err.fillInStackTrace());

        }
        }
        public void saveFilename(String oldFilename, String newFilename) throws IOException {
        File OldFile = new File(SimplenotesClient.NOTE_DIRECTORY_PATH + oldFilename);
        File NewFile = new File(SimplenotesClient.NOTE_DIRECTORY_PATH + newFilename);

        try {
            FileUtils.moveFile(OldFile, new File(NewFile + ".txt"));
            Simplenotes.LOGGER.info("File renamed!");
        } catch (IOException err) {
            Simplenotes.LOGGER.error("An IOException occurred while renaming the file.", err.fillInStackTrace());
        }
        }

    public static String readNote(String filename) {
        String data = "";
        try {
            data = new String(
                    Files.readAllBytes(Paths.get(SimplenotesClient.NOTE_DIRECTORY_PATH + filename)));
        } catch (IOException err) {
            Simplenotes.LOGGER.error("An IOException error occurred while reading the file.", err.fillInStackTrace());
        }
        return data;
        }

    public static String[] readFilenames() {
        File Path = new File("simplenotes/notes/");
        return Path.list();
        }
    }


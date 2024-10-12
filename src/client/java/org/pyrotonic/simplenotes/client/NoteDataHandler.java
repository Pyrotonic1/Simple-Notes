package org.pyrotonic.simplenotes.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
            FileWriter note = new FileWriter("simplenotes/notes/" + filename + ".txt");
            note.write(content);
            note.close();
        } catch (FileNotFoundException err) {
            Simplenotes.LOGGER.error("Simplenotes directory not found, please restart your game!");
        } catch (IOException err) {
            Simplenotes.LOGGER.error("An IOException error occurred while saving the file.");
        }
        }

    public static String readNote(String filename) {
        String data = "";
        try {
            data = new String(
                    Files.readAllBytes(Paths.get("simplenotes/notes/" + filename)));
        } catch (IOException err) {
            Simplenotes.LOGGER.error("An IOException error occurred while reading the file.");
        }
        return data;
        }

    public static String[] readFilenames() {
        File Path = new File("simplenotes/notes/");
        return Path.list();
        }
    }


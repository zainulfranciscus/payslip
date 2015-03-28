package org.myob.infrastructure.persistence.file.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * Created by Zainul Franciscus on 28/03/2015.
 */
public enum FileReaderType {

    CLASSLOADER,
    FILEREADER;

    public java.io.Reader getReader(String fileName) throws FileNotFoundException {
        switch (this) {
            case CLASSLOADER:
                return new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName));
            case FILEREADER:
                return new FileReader(fileName);
            default:
                return null;
        }
    }
}

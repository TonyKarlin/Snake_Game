package utils;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;


public class FetchFxml {
    public static FXMLLoader fetchAndValidateLoader(Object object, String fileName) throws IOException {
        String path = validatePath(fileName);
        URL fxmlView = validateURL(path, object);
        
        return new FXMLLoader(fxmlView);
    }

    private static String validatePath(String fileName) {
        if(fileName == null) {
            throw new NullPointerException("Parameter: {fileName} is null");
        } else if (fileName.length() < 1) {
            throw new NullPointerException("Parameter: {fileName} is empty");
        }

        return "/fxml/" + fileName + ".fxml";
    }

    private static URL validateURL(String path, Object object) {
        URL fxmlUrl = object.getClass().getResource(path);

        if (fxmlUrl == null) {
            throw new IllegalArgumentException("FXML file not found at: " + fxmlUrl);
        }
        return fxmlUrl;
    }
}

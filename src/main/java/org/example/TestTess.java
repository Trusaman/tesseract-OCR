package org.example;

import java.io.*;
import java.net.URL;
import java.util.Base64;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TestTess {
    public static void main(String[] args) throws IOException {
        //get current path

        String currentPath = System.getProperty("user.dir");
        System.out.println("Current working directory : " + currentPath);

        // delete all file "img.png" in currentPath
        File file = new File(currentPath);
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.getName().equals("img.png")) {
                f.delete();
            }
        }

        // get image from this web
        String encodedImage = "data:image/jpg;base64, /9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoAF8DASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD1SisfxRNLb+HLuWGV4pF2YdGKkfOvcVUs/E9pBZafHci5PmRIrXLRkoX2jPzHqc9aAOjormNT1S/HiA2AvotMthHvWeSMMJDgcfNx3P5Va8Nale6hHdC6KypDJsjuFXaJRzz+g/OgDdorB8Jajd6npUs15L5sizlAdoXjap7Aepo0HUbu91XWYbiXfHbz7IhtA2jc47DnoOtAG9RXGa/4i1Bd8+mSiOzhlEBk2hvMcgk4yDwMfrXU6jfw6ZYyXU3IXhVHVm7AUAWqK5bQtT1afxHc2WoyjCQeb5QQDYTtIGcZ4DYqK81S+bxTeWA1mLT7aJFZGkjQjO1ePmx6k9e1AHXUVQ0hna0YyanFqJ3n97GqqBwPl+U49/xq/QBj+KIZbjw5dxQxPLI2zCIpYn517CsfXrK5l8G6XBDazPMnlbo0jJZcRnOQOnNdhRQByep200Xihr2806bULFotsSRx+ZsOB/D9Qfzq14UtLu2S9eaF7e3ll3QQOeUHPbt1A/CuiooA5HRnu/DUdzYz6ZeXCGUvHLbR7wwwBz6dBVjQ9KvDb6vPcK1rJqLMVXq0ed3P/j36V01FAHC65oGp2uiwW0V097CkoCwRWgBXhvmyMk/j61vXuh3t7HYl9V/0i1Zm8026kOSeDtzgYHFblFAHHWGmatH4yuJZLqUgIpe5NsAsy/JlB2Hpkc8VZi0z7R441CW6sfNtWgXY8sW5C2E6EjGev611FFAEVvbW9rGY7eCKFCclY0Cgn14qWiigAooooAKKKKACiiigAooooAKKKKACiiigD//Z";

        // Remove any whitespace characters from the encoded image data string
        encodedImage = encodedImage.replaceAll("\\s+", "");

        // Extract the Base64-encoded image data from the data URI
        String encodedImageData = encodedImage.substring(encodedImage.indexOf(",") + 1);

        // Decode the Base64 image data
        byte[] imageData = Base64.getDecoder().decode(encodedImageData);

        // Save the image data to a file
        try (FileOutputStream fos = new FileOutputStream(new File("output.jpg"))) {
            fos.write(imageData);
            System.out.println("Image saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving image: " + e.getMessage());
        }

        Tesseract tesseract = new Tesseract();
        try {

//            tesseract.setDatapath("D:/Tess4J/tessdata");

            // the path of your tess data folder
            // inside the extracted file
            String text
                    = tesseract.doOCR(new File("img_1.png"));

            // path of your image file
            System.out.print(text);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }


}

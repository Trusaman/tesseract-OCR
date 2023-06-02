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
            if (f.getName().equals("img.jpg")) {
                f.delete();
            }
        }

        // get image from this web
        String encodedImage = "data:image/jpg;base64, /9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAoAF8DASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD1SisvxHeT2Gg3NzbPsmTbtbAOMsAeD7GsrQNTlvrq38zxAs7lNz2htQhzt6Bu+D6elAHU0VzuoaxNZeLrK0kukhsXgLyB9oGfnx8x6cgd6Zq2vFdV0iHTr6F45p9k4jZXyNygZ646mgDpaK5+LUru38XSaddzb7edN9vlQMd8ZA9mHPoKJdSu7nxdFp1pNst4E33GFBz3xkj3UcepoA6CiuX0PUWTUvED3l2/kQT/AC+Y5KoNz8DPToOBWk3iG0j04X08c8MLNtjEiDdJ7qAc4+uKANaismz8QWt3dravDc2s7jKJcxbN49qxoPE92fEVzG9pfvaiIFLVbceYh+Xkjrjr37igDr6KKKAKepTtb2wdbJ7xd43RoASB1zg9eQK5+dLnXPEGn3MOnXFpFatukluE2MwyDjHfp+prrKKAOY1LT3uvG9hJJaNNaC3Idmj3Rg/PgHjHXH6UzW9JSPWdEksNPVUW4zK0EOAAGTBbA+vX3rqqKAOc8V2NxIlpqFlE8l1ayghUUklfoOvIH5mjwpY3EaXeoXsTx3V1KSVdSCF+h6ck/kK6OigDhI9DvrnUdXuEidHju/OhjmQiOf5n454PbB9/erutQXmuWFjeQ2lzDNbSEywEFH5xypI56cH3rrqKAOLtrMXOpWcht/EEjwyqwa8dQicjJyRyOOg61Zl+06X4yu9Qawu7i3nhCKbePfzhev8A3ya6uigAooooAKKKKACiiigAooooAKKKKACiiigAooooA//Z";

        // Remove any whitespace characters from the encoded image data string
        encodedImage = encodedImage.replaceAll("\\s+", "");

        // Extract the Base64-encoded image data from the data URI
        String encodedImageData = encodedImage.substring(encodedImage.indexOf(",") + 1);

        // Decode the Base64 image data
        byte[] imageData = Base64.getDecoder().decode(encodedImageData);

        // Save the image data to a file
        try (FileOutputStream fos = new FileOutputStream(new File("img.jpg"))) {
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
                    = tesseract.doOCR(new File("img.jpg"));

            // path of your image file
            System.out.print(text);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }


}

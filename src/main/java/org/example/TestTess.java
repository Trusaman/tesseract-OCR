package org.example;

import java.io.*;
import java.net.URL;

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
        String imageUrl = "http://cdn.benhvienquan11.vn/editor/news/da-thuong.jpg";
        String destinationFile = "img.png";

        saveImage(imageUrl, destinationFile);


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

    public static void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }
}

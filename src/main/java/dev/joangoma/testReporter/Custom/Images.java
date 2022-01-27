package dev.joangoma.testReporter.Custom;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Images {
    public static double CompareImagesRed(BufferedImage imgA, BufferedImage imgB) {
        double percentage = 0;
        try {


            // Assigning dimnesions to image
            int width1 = imgA.getWidth();
            int width2 = imgB.getWidth();
            int height1 = imgA.getHeight();
            int height2 = imgB.getHeight();

            // Checking whether the images are of same size or
            // not
            percentage = 0;
            if ((width1 != width2) || (height1 != height2))

                // Display message straightaway
                System.out.println("WARNING: Images dimensions"
                        + " mismatch");
            else {

                // By now, images are of same size

                long difference = 0;

                // treating images likely 2D matrix

                // Outer loop for rows(height)
                for (int y = 0; y < height1; y++) {

                    // Inner loop for columns(width)
                    for (int x = 0; x < width1; x++) {

                        int rgbA = imgA.getRGB(x, y);
                        int rgbB = imgB.getRGB(x, y);
                        int redA = (rgbA >> 16) & 0xff;

                        int redB = (rgbB >> 16) & 0xff;


                        difference += Math.abs(redA - redB);

                    }
                }

                // Total number of red pixels = width * height
                // Total number of blue pixels = width * height
                // Total number of green pixels = width * height
                // So total number of pixels = width * height *
                // 3
                double total_pixels = width1 * height1;

                // Normalizing the value of different pixels
                // for accuracy

                // Note: Average pixels per color component
                double avg_different_pixels
                        = difference / total_pixels;

                // There are 255 values of pixels in total
                percentage = (avg_different_pixels / 255) * 100;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return percentage;
    }
    public static double CompareImagesBlue(BufferedImage imgA, BufferedImage imgB) {
        double percentage = 0;
        try {


            // Assigning dimnesions to image
            int width1 = imgA.getWidth();
            int width2 = imgB.getWidth();
            int height1 = imgA.getHeight();
            int height2 = imgB.getHeight();

            // Checking whether the images are of same size or
            // not
            percentage = 0;
            if ((width1 != width2) || (height1 != height2))

                // Display message straightaway
                System.out.println("WARNING: Images dimensions"
                        + " mismatch");
            else {

                // By now, images are of same size

                long difference = 0;

                // treating images likely 2D matrix

                // Outer loop for rows(height)
                for (int y = 0; y < height1; y++) {

                    // Inner loop for columns(width)
                    for (int x = 0; x < width1; x++) {

                        int rgbA = imgA.getRGB(x, y);
                        int rgbB = imgB.getRGB(x, y);
                        int blueA = (rgbA) & 0xff;
                        int blueB = (rgbB) & 0xff;


                        difference += Math.abs(blueA - blueB);
                    }
                }

                // Total number of red pixels = width * height
                // Total number of blue pixels = width * height
                // Total number of green pixels = width * height
                // So total number of pixels = width * height *
                // 3
                double total_pixels = width1 * height1;

                // Normalizing the value of different pixels
                // for accuracy

                // Note: Average pixels per color component
                double avg_different_pixels
                        = difference / total_pixels;

                // There are 255 values of pixels in total
                percentage = (avg_different_pixels / 255) * 100;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return percentage;
    }
    public static double CompareImages(BufferedImage imgA, BufferedImage imgB) {
        double percentage = 0;
        try {


            // Assigning dimnesions to image
            int width1 = imgA.getWidth();
            int width2 = imgB.getWidth();
            int height1 = imgA.getHeight();
            int height2 = imgB.getHeight();

            // Checking whether the images are of same size or
            // not
            percentage = 0;
            if ((width1 != width2) || (height1 != height2))

                // Display message straightaway
                System.out.println("WARNING: Images dimensions"
                        + " mismatch");
            else {

                // By now, images are of same size

                long difference = 0;

                // treating images likely 2D matrix

                // Outer loop for rows(height)
                for (int y = 0; y < height1; y++) {

                    // Inner loop for columns(width)
                    for (int x = 0; x < width1; x++) {

                        int rgbA = imgA.getRGB(x, y);
                        int rgbB = imgB.getRGB(x, y);
                        int blueA = (rgbA) & 0xff;
                        int blueB = (rgbB) & 0xff;


                        difference += Math.abs(blueA - blueB);
                    }
                }

                // Total number of red pixels = width * height
                // Total number of blue pixels = width * height
                // Total number of green pixels = width * height
                // So total number of pixels = width * height *
                // 3
                double total_pixels = width1 * height1;

                // Normalizing the value of different pixels
                // for accuracy

                // Note: Average pixels per color component
                double avg_different_pixels
                        = difference / total_pixels;

                // There are 255 values of pixels in total
                percentage = (avg_different_pixels / 255) * 100;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return percentage;
    }
    public static BufferedImage joinBufferedImage(BufferedImage img1, BufferedImage img2) {
        int offset = 2;
        int width = img1.getWidth() + img2.getWidth() + offset;
        int height = Math.max(img1.getHeight(), img2.getHeight()) + offset;
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        g2.setPaint(Color.BLACK);
        g2.fillRect(0, 0, width, height);
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, img1.getWidth() + offset, 0);
        g2.dispose();
        return newImage;
    }
}

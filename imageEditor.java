import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

//Convert to GrayScale
public class imageEditor {
    public static BufferedImage toGrayScale(BufferedImage InputImage){
        int height=InputImage.getHeight();
        int width=InputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                outputImage.setRGB(j,i,InputImage.getRGB(j,i));
            }
        }
        return outputImage;
    }

//Rotate 90 degrees Clockwise
    public static BufferedImage rotateClockwise(BufferedImage inImage) {
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        BufferedImage rotatedImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rotatedImage.setRGB(i, j, inImage.getRGB(j, i));
            }
        }
        int index = rotatedImage.getWidth() - 1;
        for (int i = 0; i < rotatedImage.getHeight(); i++) {
            for (int j = 0; j < rotatedImage.getWidth() / 2; j++) {
                Color temp = new Color(rotatedImage.getRGB(j, i));
                rotatedImage.setRGB(j, i, rotatedImage.getRGB(index - j, i));
                rotatedImage.setRGB(index - j, i, temp.getRGB());

            }
        }
        return rotatedImage;
    }

//Rotate 90 Anti-clockwise
    public static BufferedImage rotateAntiClockwise(BufferedImage inImage) {
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        BufferedImage rotatedImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rotatedImage.setRGB(i, j, inImage.getRGB(j, i));
            }
        }
        int index = rotatedImage.getHeight() - 1;
        for (int j = 0; j < rotatedImage.getWidth(); j++) {
            for (int i = 0; i < rotatedImage.getHeight() / 2; i++) {
                Color temp = new Color(rotatedImage.getRGB(j, i));
                rotatedImage.setRGB(j, i, rotatedImage.getRGB(j, index - i));
                rotatedImage.setRGB(j, index - i, temp.getRGB());

            }
        }

        return rotatedImage;
    }
//Adjust Brightness
public static BufferedImage brightness(BufferedImage inputImage, int A) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage brightImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color output = new Color(inputImage.getRGB(j, i));
                int green = output.getGreen();
                int blue = output.getBlue();
                int red = output.getRed();
                red += (red * A) / 100;
                green += (green * A) / 100;
                blue += (blue * A) / 100;
                if (red > 255)
                    red = 255;
                if (green > 255)
                    green = 255;
                if (blue > 255)
                    blue = 255;
                if (red < 0)
                    red = 0;
                if (blue < 0)
                    blue = 0;
                if (green < 0)
                    green = 0;
                Color newoutput = new Color(red, green, blue);
                brightImage.setRGB(j, i, newoutput.getRGB());
            }
        }
        return brightImage;
    }

//Invert the Image

       
    public static BufferedImage horizontallyinvert(BufferedImage inImage) {
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        BufferedImage horizontalinvertImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                horizontalinvertImage.setRGB(width - j - 1, i, inImage.getRGB(j, i));
            }
        }

        return horizontalinvertImage;
    }

    public static BufferedImage verticallyinvert(BufferedImage inImage) {
        int height = inImage.getHeight();
        int width = inImage.getWidth();
        BufferedImage verticallyinvertImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int j = 0; j < width; j++) {
        for (int i = 0; i < height; i++) {
                verticallyinvertImage.setRGB(j, height - i - 1, inImage.getRGB(j,i));
            }
        }

        return verticallyinvertImage;
    }

//Blur the Image
    public static BufferedImage BlurredImage (BufferedImage input, int pixels) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
    
        for (int i = 0; i < height / pixels; i++) {
            for (int j = 0; j < width / pixels; j++) {
    
                int red = 0;
                int green = 0;
                int blue = 0;
    
                for (int k = i * pixels; k < i * pixels + pixels; k++) {
                    for (int l = j * pixels; l < j * pixels + pixels; l++) {
                        Color pixel = new Color(input.getRGB(l, k));
                        red += pixel.getRed();
                        blue += pixel.getBlue();
                        green += pixel.getGreen();
                    }
                }
                int finalRed = red / (pixels * pixels);
                int finalGreen = green / (pixels * pixels);
                int finalBlue = blue / (pixels * pixels);
    
                for (int k = i * pixels; k < i * pixels + pixels; k++) {
                    for (int l = j * pixels; l < j * pixels + pixels; l++) {
                        Color newPixel = new Color(finalRed, finalGreen, finalBlue);
                        outputImage.setRGB(l, k, newPixel.getRGB());
                    }
                }
            }
        }
    
        return outputImage;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the file: ");
        String S = sc.next();
        File inputFile = new File(S);
        BufferedImage inputImage = ImageIO.read(inputFile);

        System.out.println("Enter 1 for Convert image to Gray Scale");
        System.out.println("Enter 2 for Adjust the brightness of the image");
        System.out.println("Enter 3 for Rotate the image Clockwise");
        System.out.println("Enter 4 for Rotate the image Anti-Clockwise");
        System.out.println("Enter 5 for Invert the image horizontally");
        System.out.println("Enter 6 for Invert the image vertically");
        System.out.println("Enter 7 for Blur the Image");


        int Ch = sc.nextInt();
 
        if (Ch == 1) {
            BufferedImage result = toGrayScale(inputImage);
            File output = new File("output.jpg");
            ImageIO.write(result, "jpg", output);
        }

        else if (Ch == 2) {
            System.out.println("Please specify the percentage of brightness you require");
            int a = sc.nextInt();
            BufferedImage result = brightness(inputImage, a);
            File output = new File("output.jpg");
            ImageIO.write(result, "jpg", output);

        }

        else if (Ch == 3) {
            BufferedImage result = rotateClockwise(inputImage);
            File output = new File("output.jpg");
            ImageIO.write(result, "jpg", output);
        }

        else if (Ch == 4) {
            BufferedImage result = rotateAntiClockwise(inputImage);
            File output = new File("output.jpg");
            ImageIO.write(result, "jpg", output);
        }
        else if (Ch == 5){
            BufferedImage result = horizontallyinvert(inputImage);
            File output = new File("output.jpg");
            ImageIO.write(result, "jpg", output);
        }

        else if (Ch == 6){
            BufferedImage result = verticallyinvert(inputImage);
            File output = new File("output.jpg");
            ImageIO.write(result, "jpg", output);
        }
        else if (Ch == 7){
            int amount =sc.nextInt();
            BufferedImage result = BlurredImage(inputImage, amount);
            File output = new File("output.jpg");
            ImageIO.write(result,"jpg",output);
        }
    }
}

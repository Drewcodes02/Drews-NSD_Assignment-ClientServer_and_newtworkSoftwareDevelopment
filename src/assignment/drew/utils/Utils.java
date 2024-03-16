package Assigment.utils.;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

public class Utils {


    public static String encode(File file) throws IOException {
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fileInputStreamReader.read(bytes);
        return new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8);

    }

    public static Image decodeImage(String encodedImage) throws IOException {
        BufferedImage image = null;
        byte[] imageByte = Base64.getDecoder().decode(encodedImage);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();
        return SwingFXUtils.toFXImage(image, null);
    }


    public static String randomFileName() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

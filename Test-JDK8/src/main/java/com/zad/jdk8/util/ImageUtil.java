package com.zad.jdk8.util;

import com.google.common.hash.Hashing;
import lombok.SneakyThrows;
import net.coobird.thumbnailator.Thumbnails;
import net.sf.image4j.codec.ico.ICODecoder;
import net.sf.image4j.codec.ico.ICOEncoder;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * @Author waiter
 * @Date 2020/6/14 0014 15:43
 * @Version 1.0
 * @Description
 */
public class ImageUtil {
    private static String ICO_SUFFIX = "ICO";

    private static boolean isICO(String fileName) {
        return StringUtils.endsWithIgnoreCase(fileName, ICO_SUFFIX);
    }


    private ImageUtil() {
        throw new UnsupportedOperationException("no support");
    }


    @SneakyThrows(IOException.class)
    public static void convert2ICO(String inputPath, String outputPath) {
        if (!isICO(outputPath)) {
            return;
        }
        BufferedImage inImage = getImageFromPath(inputPath);
        ICOEncoder.write(Collections.singletonList(inImage), new File(outputPath));
    }

    @SneakyThrows(IOException.class)
    public static void convert2ICOAndScale(String inputPath, String outputPath, int width, int height) {
        if (!isICO(outputPath)) {
            return;
        }
        ICOEncoder.write(Collections.singletonList(Thumbnails.of(inputPath).size(width, height).asBufferedImage()), new File(outputPath));
    }


    private static BufferedImage getImageFromPath(String path) throws IOException {
        return isICO(path) ? ICODecoder.read(new File(path)).get(0) : ImageIO.read(new File(path));
    }


    public static void main(String[] args) {
        String res = Hashing.murmur3_32().hashString("http://www.baidu.com", StandardCharsets.UTF_8).toString();
        System.out.println(res);
        //convert2ICOAndScale("D:\\Software/jingxuan_jingxuanyitu_257142_11.jpg","D:\\Software/test.ico",16,10);
    }
}
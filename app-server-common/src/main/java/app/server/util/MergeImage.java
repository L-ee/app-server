package app.server.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * 合成图片
 * @author 薛斌
 */
public class MergeImage {
    /*
     * 合成图片的格式
     */
    private static final String PICTRUE_FORMATE_JPG = "jpg";

    private final static List<Map<String, Point>> coordinateList = new ArrayList<Map<String, Point>>();

    private MergeImage() {
    }


    /*
     * 最多有9张图片数
     */
    public static String getCombinationOfhead(List<String> paths, int innerImageLength, int gapLength, boolean zoom) throws IOException {

        List<BufferedImage> bufferedImages = new ArrayList<BufferedImage>();

        for (int i = 0; i < paths.size(); i++) {
            if (zoom) {
                bufferedImages.add(MergeImage.resize2(paths.get(i), innerImageLength, innerImageLength, true));
            } else {
                File f = new File(paths.get(i));
                BufferedImage bi = ImageIO.read(f);
                bufferedImages.add(bi);
            }
        }


        int width;
        int height;
        int graphWidth;
        if (bufferedImages.size() >= 5) {
            width = innerImageLength * 3 + gapLength * 4; // ���ǻ���Ŀ��
            height = innerImageLength * 3 + gapLength * 4; // ���ǻ���ĸ߶�
        } else {
            width = innerImageLength * 2 + gapLength * 3; // ���ǻ���Ŀ��
            height = innerImageLength * 2 + gapLength * 3; // ���ǻ���ĸ߶�
        }
        graphWidth = width;

        BufferedImage outImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = outImage.getGraphics();
        Graphics2D g2d = (Graphics2D) g;

        g2d.setBackground(new Color(231, 231, 231));

        g2d.clearRect(0, 0, width, height);

        int j = 1;
        int q = 1;
        int p = 1;
        int u = 1;
        for (int i = 1; i <= bufferedImages.size(); i++) {
            if (bufferedImages.size() == 1) {
                g2d.drawImage(bufferedImages.get(i - 1), (graphWidth - innerImageLength) / 2, (graphWidth - innerImageLength) / 2, null);
            } else if (bufferedImages.size() == 2) {
                g2d.drawImage(bufferedImages.get(i - 1), innerImageLength * i + gapLength * i - innerImageLength, (graphWidth - innerImageLength) / 2, null);
            } else if (bufferedImages.size() == 3) {
                int pointY = (graphWidth - innerImageLength * 2 - gapLength) / 2;
                if (i <= 1) {
                    int pointX = (graphWidth - innerImageLength) / 2;
                    g2d.drawImage(bufferedImages.get(i - 1), pointX, pointY, null);
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), innerImageLength * j + gapLength * j - innerImageLength, pointY + innerImageLength + gapLength, null);
                    j++;
                }
            } else if (bufferedImages.size() == 4) {
                if (i <= 2) {
                    g2d.drawImage(bufferedImages.get(i - 1), innerImageLength * j + gapLength * j - innerImageLength, gapLength, null);
                    j++;
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), innerImageLength * q + gapLength * q - innerImageLength, gapLength * 2 + innerImageLength, null);
                    q++;
                }
            } else if (bufferedImages.size() == 5) {
                int point11X = (graphWidth - innerImageLength * 2 - gapLength) / 2;
                if (i == 1) {
                    g2d.drawImage(bufferedImages.get(i - 1), point11X, point11X, null);
                } else if (i == 2) {
                    g2d.drawImage(bufferedImages.get(i - 1), point11X + innerImageLength + gapLength, point11X, null);
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), innerImageLength * q + gapLength * q - innerImageLength, point11X + innerImageLength + gapLength, null);
                    q++;
                }
            } else if (bufferedImages.size() == 6) {
                if (i <= 3) {
                    g2d.drawImage(bufferedImages.get(i - 1), innerImageLength * j + gapLength * j - innerImageLength, (graphWidth - innerImageLength * 2 - gapLength) / 2, null);
                    j++;
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), innerImageLength * q + gapLength * q - innerImageLength, (graphWidth - innerImageLength * 2 - gapLength) / 2 + innerImageLength + gapLength, null);
                    q++;
                }
            } else if (bufferedImages.size() == 7) {
                if (i == 1) {
                    g2d.drawImage(bufferedImages.get(i - 1), (graphWidth - innerImageLength) / 2, gapLength, null);
                } else if (i <= 4) {
                    g2d.drawImage(bufferedImages.get(i - 1), innerImageLength * q + gapLength * q - innerImageLength, gapLength + innerImageLength + gapLength, null);
                    q++;
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), innerImageLength * p + gapLength * p - innerImageLength, innerImageLength * 2 + gapLength * 3, null);
                    p++;
                }
            } else if (bufferedImages.size() == 8) {
                if (i == 1) {
                    g2d.drawImage(bufferedImages.get(i - 1), (graphWidth - innerImageLength * 2 - gapLength) / 2, gapLength, null);
                } else if (i == 2) {
                    g2d.drawImage(bufferedImages.get(i - 1), (graphWidth - innerImageLength * 2 - gapLength) / 2 + innerImageLength + gapLength, gapLength, null);
                } else if (i <= 5) {
                    g2d.drawImage(bufferedImages.get(i - 1), innerImageLength * j + gapLength * j - innerImageLength, gapLength + innerImageLength + gapLength, null);
                    j++;
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), innerImageLength * q + gapLength * q - innerImageLength, innerImageLength * 2 + gapLength * 3, null);
                    q++;
                }
            } else if (bufferedImages.size() == 9) {
                if (i <= 3) {
                    g2d.drawImage(bufferedImages.get(i - 1), innerImageLength * q + gapLength * q - innerImageLength, gapLength, null);
                    q++;
                } else if (i <= 6) {
                    g2d.drawImage(bufferedImages.get(i - 1), innerImageLength * p + gapLength * p - innerImageLength, gapLength + innerImageLength + gapLength, null);
                    p++;
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), innerImageLength * u + gapLength * u - innerImageLength, innerImageLength * 2 + gapLength * 3, null);
                    u++;
                }
            }

            // ��Ҫ�ı���ɫ�Ļ������������ɫ�����ܻ��õ�AlphaComposite��
        }

        String outPath = "E:/img/" + UUID.randomUUID().toString().replaceAll("-","") + ".jpg";
        String format = "JPG";

        ImageIO.write(outImage, format, new File(outPath));
        return outPath;
    }


    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        List<String> list = new ArrayList<String>();
        list.add("E:/img/1.jpg");
        list.add("E:/img/2.jpg");
        //list.add("E:/img/3.jpg");
        //list.add("E:/img/4.jpg");
        //list.add("E:/img/5.jpg");
        //list.add("E:/img/6.jpg");
        //list.add("E:/img/7.jpg");
        //list.add("E:/img/8.jpg");
        list.add("E:/img/10.jpg");
        MergeImage.getCombinationOfhead(list, 70, 10, true);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

//        BufferedImage ImageNew =  resize2("E:/999.png", 200, 200, true);
//        File outFile = new File("E:/a.jpg");
//        ImageIO.write(ImageNew, "jpg", outFile);//дͼƬ

    }

    /*
     * 缩放图片的大小
     */
    public static BufferedImage resize2(String filePath, int height, int width, boolean bb) {
        try {
            double ratio = 0;
            File f = new File(filePath);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue() / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            if (bb) {
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                g.dispose();
                itemp = image;
            }
            return (BufferedImage) itemp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
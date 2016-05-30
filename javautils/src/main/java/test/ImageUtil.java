package test;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * ������ͼƬ������
 *
 * @author ������
 *         ����:http://www.cnblogs.com/zovon/p/4345501.html
 */
public final class ImageUtil {
    /**
     * ͼƬ��ʽ��JPG
     */
    private static final String PICTRUE_FORMATE_JPG = "jpg";

    private ImageUtil() {
    }

    /**
     * �������ͷ��
     *
     * @param paths �û�ͼ��
     * @throws java.io.IOException
     */
    public static void getCombinationOfhead(List<String> paths) throws IOException {
        List<BufferedImage> bufferedImages = new ArrayList<BufferedImage>();
        // ѹ��ͼƬ���е�ͼƬ���ɳߴ�ͬ��� Ϊ 50x50
        for (int i = 0; i < paths.size(); i++) {
            bufferedImages.add(ImageUtil.resize2(paths.get(i), 50, 50, true));
        }

        int width;
        int height;
        if (bufferedImages.size() >= 5) {
            width = 162; // ���ǻ���Ŀ��
            height = 162; // ���ǻ���ĸ߶�
        } else {
            width = 112; // ���ǻ���Ŀ��
            height = 112; // ���ǻ���ĸ߶�
        }

        // BufferedImage.TYPE_INT_RGB�����Լ�����ɲ鿴API
        BufferedImage outImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // ���ɻ���
        Graphics g = outImage.getGraphics();
        Graphics2D g2d = (Graphics2D) g;

        // ���ñ���ɫ
        g2d.setBackground(new Color(231, 231, 231));

        // ͨ��ʹ�õ�ǰ��ͼ����ı���ɫ������������ָ���ľ��Ρ�
        g2d.clearRect(0, 0, width, height);

        // ��ʼƴ�� ����ͼƬ�������жϸ�����������ʽ�����ͷ��ĿǰΪ4��
        int j = 1;
        int q = 1;
        for (int i = 1; i <= bufferedImages.size(); i++) {
            if (bufferedImages.size() == 1) {
                g2d.drawImage(bufferedImages.get(i - 1), 31, 31, null);
            }
            else if (bufferedImages.size() == 2) {
                g2d.drawImage(bufferedImages.get(i - 1), 50 * i + 4 * i - 50, 31, null);
            }
            else if (bufferedImages.size() == 3) {
                if (i <= 1) {
                    g2d.drawImage(bufferedImages.get(i - 1), 31, 4, null);
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * j + 4 * j - 50, 58, null);
                    j++;
                }
            }
            else  if (bufferedImages.size() == 4) {
                if (i <= 2) {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * i + 4 * i - 50, 4, null);
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * j + 4 * j - 50, 58, null);
                    j++;
                }
            }
            else if (bufferedImages.size() == 5) {
                if (i == 1) {
                    g2d.drawImage(bufferedImages.get(i - 1), 29, 29, null);
                } else if (i == 2) {
                    g2d.drawImage(bufferedImages.get(i - 1), 83, 29, null);
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * j + 4 * j - 50, 83, null);
                    j++;
                }
            }
            else if (bufferedImages.size() == 6) {
                if (i <= 3) {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * i + 4 * i - 50, 29, null);
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * j + 4 * j - 50, 83, null);
                    j++;
                }
            }
            else if (bufferedImages.size() == 7) {
                if (i == 1) {
                    g2d.drawImage(bufferedImages.get(i - 1), 56, 4, null);
                } else if (i <= 4) {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * j + 4 * j - 50, 58, null);
                    j++;
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * q + 4 * q - 50, 116, null);
                    q++;
                }
            }
            else if (bufferedImages.size() == 8) {
                if (i == 1) {
                    g2d.drawImage(bufferedImages.get(i - 1), 29, 4, null);
                } else if (i == 2) {
                    g2d.drawImage(bufferedImages.get(i - 1), 83, 4, null);
                } else if (i <= 5) {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * j + 4 * j - 50, 58, null);
                    j++;
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * q + 4 * q - 50, 116, null);
                    q++;
                }
            }
            else if (bufferedImages.size() == 9) {
                if (i <= 3) {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * i + 4 * i
                            - 50, 4, null);
                } else if (i <= 6) {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * j + 4 * j - 50, 58, null);
                    j++;
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * q + 4 * q - 50, 116, null);
                    q++;
                }
            }

            // ��Ҫ�ı���ɫ�Ļ������������ɫ�����ܻ��õ�AlphaComposite��
        }

        String outPath = "E:\\b9.jpg";
        String format = "JPG";

        ImageIO.write(outImage, format, new File(outPath));
    }

    /**
     * ͼƬ����
     *
     * @param filePath ͼƬ·��
     * @param height   �߶�
     * @param width    ���
     * @param bb       ��������ʱ�Ƿ���Ҫ����
     */
    public static BufferedImage resize2(String filePath, int height, int width, boolean bb) {
        try {
            double ratio = 0; // ���ű���
            File f = new File(filePath);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            // �������
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
                // copyimg(filePath, "D:\\img");
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

    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<String>();
        list.add("E:/1.jpg");
        list.add("E:/999.png");
        list.add("E:/3.jpg");
        list.add("E:/4.jpg");
        list.add("E:/5.jpg");
        list.add("E:/6.jpg");
        list.add("E:/7.jpg");
        list.add("E:/8.jpg");
        list.add("E:/10.jpg");
        ImageUtil.getCombinationOfhead(list);

//        BufferedImage ImageNew =  resize2("E:/999.png", 200, 200, true);
//        File outFile = new File("E:/a.jpg");
//        ImageIO.write(ImageNew, "jpg", outFile);//дͼƬ

    }

}
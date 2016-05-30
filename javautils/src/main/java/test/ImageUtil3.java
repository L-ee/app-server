package test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ������ͼƬ������
 *
 * @author ������
 *         ����:http://www.cnblogs.com/zovon/p/4345501.html
 */
public final class ImageUtil3 {
    /**
     * ͼƬ��ʽ��JPG
     */
    private static final String PICTRUE_FORMATE_JPG = "jpg";

    private ImageUtil3() {
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
            bufferedImages.add(ImageUtil3.resize2(paths.get(i), 50, 50, true));
        }

        int width;
        int height;
        if (bufferedImages.size() >= 5) {
            width = 166; // ���ǻ���Ŀ��
            height = 166; // ���ǻ���ĸ߶�
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
        ImageUtil3.getCombinationOfhead(list);

//        BufferedImage ImageNew =  resize2("E:/999.png", 200, 200, true);
//        File outFile = new File("E:/a.jpg");
//        ImageIO.write(ImageNew, "jpg", outFile);//дͼƬ

    }

    public static List<Map<String,Point>> calculateCoordinate(int graphWidth, int innerImageLength, int gapLength) {
        int maxLength = innerImageLength * 3 + gapLength * 4;
        if(maxLength > graphWidth){
            return null;
        }

        List<Map<String,Point>> coordinateList = new ArrayList<Map<String, Point>>();
        for(int i=1; i<=9; i++){
            if(i == 1){
                int coordinateOne = (graphWidth - innerImageLength) / 2;
                Map oneMap = new HashMap<String, Point>();
                oneMap.put("1",new Point(coordinateOne,coordinateOne));
                coordinateList.add(i-1,oneMap);
            }
            else if(i == 2){
                int coordinateTwoX = (graphWidth - (innerImageLength * i + gapLength)) / 2;
                int coordinateTwoY = (graphWidth - innerImageLength) / 2;
                Map twoMap = new HashMap<String, Point>();
                twoMap.put("1",new Point(coordinateTwoX,coordinateTwoY));
                twoMap.put("2",new Point(coordinateTwoX+innerImageLength+gapLength, coordinateTwoY));
                coordinateList.add(i-1,twoMap);
            }
            else if(i == 3){
                Map threeMap = new HashMap<String, Point>();
                int q = 1;
                int point11X ;
                int point11Y = 0;
                for(int j = 1; j<= 3; j++){
                    if(j == 1){
                        point11X = (graphWidth - innerImageLength) / 2;
                        point11Y = (graphWidth - innerImageLength * 2 - gapLength) / 2;
                        threeMap.put(new Integer(j).toString(), new Point(point11X, point11Y));
                    }else{
                        threeMap.put(new Integer(j).toString(), new Point(innerImageLength * q + gapLength * q - innerImageLength,point11Y+innerImageLength+gapLength ));
                        q++;
                    }
                }
                coordinateList.add(i-1,threeMap);
            }
            else if(i == 4){
                Map fourMap = new HashMap<String, Point>();
                int q = 1;

                for(int j = 1; j<= 4; j++){
                    if(j <= 2){
                        fourMap.put(new Integer(j).toString(), new Point(innerImageLength * j + gapLength * j - innerImageLength, gapLength));
                    }else{
                        fourMap.put(new Integer(j).toString(), new Point(innerImageLength * q + gapLength * q - innerImageLength,gapLength*2 + innerImageLength));
                        q++;
                    }
                }
                coordinateList.add(i-1,fourMap);
            }
            else if(i == 5){
                Map fiveMap = new HashMap<String, Point>();
                int q = 1;
                int point11X = (graphWidth - innerImageLength *2 -gapLength) / 2;

                for(int j = 1; j<= 5; j++){
                    if(j == 1){
                        fiveMap.put(new Integer(j).toString(), new Point(point11X, point11X));
                    }else if (j == 2) {
                        fiveMap.put(new Integer(j).toString(), new Point(point11X+innerImageLength+gapLength, point11X));
                    }else{
                        fiveMap.put(new Integer(j).toString(), new Point(innerImageLength * q + gapLength * q - innerImageLength,point11X+innerImageLength+gapLength));
                        q++;
                    }
                }
                coordinateList.add(i-1,fiveMap);
            }
            else if(i == 6){
                Map sixMap = new HashMap<String, Point>();
                int q = 1;
                for(int j = 1; j<= 6; j++){
                    if(j <= 3){
                        sixMap.put(new Integer(j).toString(), new Point(innerImageLength * j + gapLength * j - innerImageLength, gapLength));
                    }else{
                        sixMap.put(new Integer(j).toString(), new Point(innerImageLength * q + gapLength * q - innerImageLength,gapLength+innerImageLength+gapLength));
                        q++;
                    }
                }
                coordinateList.add(i-1,sixMap);
            }
            else if(i == 7){
                Map sevenMap = new HashMap<String, Point>();
                int q = 1;
                int p = 1;
                for(int j = 1; j<= 7; j++){
                    if(j == 1){
                        sevenMap.put(new Integer(j).toString(), new Point((graphWidth - innerImageLength)/2, gapLength));
                    }else if(j <= 4){
                        sevenMap.put(new Integer(j).toString(), new Point(innerImageLength * q + gapLength * q - innerImageLength,gapLength+innerImageLength+gapLength));
                        q++;
                    }
                    else{
                        sevenMap.put(new Integer(j).toString(), new Point(innerImageLength * p + gapLength * p - innerImageLength,innerImageLength*2+gapLength*3));
                        p++;
                    }
                }
                coordinateList.add(i-1,sevenMap);
            }
            else if(i == 8){
                Map eightMap = new HashMap<String, Point>();
                int q = 1;
                int p = 1;
                for(int j = 1; j<= 8; j++){
                    if(j == 1){
                        eightMap.put(new Integer(j).toString(), new Point((graphWidth - innerImageLength * 2 -gapLength)/2, gapLength));
                    }else if(j == 2){
                        eightMap.put(new Integer(j).toString(), new Point((graphWidth - innerImageLength * 2 -gapLength)/2 + innerImageLength+gapLength,gapLength));
                    }else if(j <= 5){
                        eightMap.put(new Integer(j).toString(), new Point(innerImageLength * q + gapLength * q - innerImageLength,gapLength+innerImageLength+gapLength));
                        q++;
                    }
                    else{
                        eightMap.put(new Integer(j).toString(), new Point(innerImageLength * p + gapLength * p - innerImageLength,innerImageLength*2+gapLength*3));
                        p++;
                    }
                }
                coordinateList.add(i-1,eightMap);
            }
            else if(i == 9){
                Map nineMap = new HashMap<String, Point>();
                int q = 1;
                int p = 1;
                int u =1;
                for(int j = 1; j<= 9; j++){
                    if(j <= 3){
                        nineMap.put(new Integer(j).toString(), new Point(innerImageLength * q + gapLength * q - innerImageLength, gapLength));
                        q++;
                    }else if(j <= 6){
                        nineMap.put(new Integer(j).toString(), new Point(innerImageLength * p + gapLength * p - innerImageLength,gapLength+innerImageLength+gapLength));
                        p++;
                    }else {
                        nineMap.put(new Integer(j).toString(), new Point(innerImageLength * u + gapLength * u - innerImageLength,innerImageLength*2+gapLength*3));
                        q++;
                    }
                }
                coordinateList.add(i-1,nineMap);
            }


        }
        return null;
    }

}
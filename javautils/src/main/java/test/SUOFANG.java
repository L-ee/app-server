package test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by loovee on 2016/3/31.
 */
public class SUOFANG { /**
 * ��ͼƬ���зŴ�
 * @param originalImage ԭʼͼƬ
 * @param times �Ŵ���
 * @return
 */
public static BufferedImage zoomInImage(BufferedImage  originalImage, Integer times){
    int width = originalImage.getWidth()*times;
    int height = originalImage.getHeight()*times;
    BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());
    Graphics g = newImage.getGraphics();
    g.drawImage(originalImage, 0,0,width,height,null);
    g.dispose();
    return newImage;
}
    /**
     * ��ͼƬ���зŴ�
     * @param srcPath ԭʼͼƬ·��(����·��)
     * @param newPath �Ŵ��ͼƬ·��������·����
     * @param times �Ŵ���
     * @return �Ƿ�Ŵ�ɹ�
     */
    public static boolean zoomInImage(String srcPath,String newPath,Integer times){
        BufferedImage bufferedImage = null;
        try {
            File of = new File(srcPath);
            if(of.canRead()){
                bufferedImage =  ImageIO.read(of);
            }
        } catch (IOException e) {
            //TODO: ��ӡ��־
            return false;
        }
        if(bufferedImage != null){
            bufferedImage = zoomInImage(bufferedImage,times);
            try {
                //TODO: �������·����Ҫ�������Ӻ�һ��
                ImageIO.write(bufferedImage, "JPG", new File(newPath)); //�����޸ĺ��ͼ��,ȫ������ΪJPG��ʽ
            } catch (IOException e) {
                // TODO ��ӡ������Ϣ
                return false;
            }
        }
        return true;
    }
    /**
     * ��ͼƬ������С
     * @param originalImage ԭʼͼƬ
     * @param times ��С����
     * @return ��С���Image
     */
    public static BufferedImage  zoomOutImage(BufferedImage  originalImage, Integer times){
        int width = originalImage.getWidth()/times;
        int height = originalImage.getHeight()/times;
        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0,0,width,height,null);
        g.dispose();
        return newImage;
    }
    /**
     * ��ͼƬ������С
     * @param srcPath ԴͼƬ·��������·����
     * @param newPath ��ͼƬ·��������·����
     * @param times ��С����
     * @return �����Ƿ�ɹ�
     */
    public static boolean zoomOutImage(String srcPath,String newPath,Integer times){
        BufferedImage bufferedImage = null;
        try {
            File of = new File(srcPath);
            if(of.canRead()){
                bufferedImage =  ImageIO.read(of);
            }
        } catch (IOException e) {
            //TODO: ��ӡ��־
            return false;
        }
        if(bufferedImage != null){
            bufferedImage = zoomOutImage(bufferedImage,times);
            try {
                //TODO: �������·����Ҫ�������Ӻ�һ��
                ImageIO.write(bufferedImage, "JPG", new File(newPath)); //�����޸ĺ��ͼ��,ȫ������ΪJPG��ʽ
            } catch (IOException e) {
                // TODO ��ӡ������Ϣ
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        boolean testIn = zoomInImage("E:/11.jpg","E:\\in.jpg",4);
        if(testIn){
            System.out.println("in ok");
        }
        boolean testOut = zoomOutImage("E:/11.jpg","E:\\out.jpg",4);
        if(testOut){
            System.out.println("out ok");
        }
    }
}

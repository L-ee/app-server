package test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by loovee on 2016/3/30.
 */
public class rrr {
    public static void main1(String args[]) {
        try {
            //��ȡ��һ��ͼƬ
            File fileOne = new File("E:/1.jpg");
            BufferedImage ImageOne = ImageIO.read(fileOne);
            int width = ImageOne.getWidth();//ͼƬ���
            int height = ImageOne.getHeight();//ͼƬ�߶�

            //��ͼƬ�ж�ȡRGB
            int[] ImageArrayOne = new int[width*height];
            ImageArrayOne = ImageOne.getRGB(0,0,width,height,ImageArrayOne,0,width);

            //�Եڶ���ͼƬ����ͬ�Ĵ���
            File fileTwo = new File("E:/2.jpg");
            BufferedImage ImageTwo = ImageIO.read(fileTwo);
            int[] ImageArrayTwo = new int[width*height];
            ImageArrayTwo = ImageTwo.getRGB(0,0,width,height,ImageArrayTwo,0,width);

            //�Ե�����ͼƬ����ͬ�Ĵ���
            File fileThree = new File("E:/3.jpg");
            BufferedImage three = ImageIO.read(fileThree);
            int[] aThree = new int[width*height];
            aThree = three.getRGB(0,0,width,height,aThree,0,width);

            //�Ե�����ͼƬ����ͬ�Ĵ���
            File fileFour = new File("E:/4.jpg");
            BufferedImage four = ImageIO.read(fileFour);
            int[] aFour = new int[width*height];
            aFour = four.getRGB(0,0,width,height,aFour,0,width);

            //������ͼƬ
            BufferedImage ImageNew = new BufferedImage(width*2,height*2,BufferedImage.TYPE_INT_RGB);



            ImageNew.setRGB(0,0,width,height,ImageArrayOne,0,width);//������벿�ֵ�RGB
            ImageNew.setRGB(width,0,width,height,ImageArrayTwo,0,width);//�����Ұ벿�ֵ�RGB
            ImageNew.setRGB(0,width,width,height,aThree,0,width);//�����Ұ벿�ֵ�RGB
            ImageNew.setRGB(width,width,width,height,aFour,0,width);//�����Ұ벿�ֵ�RGB
            File outFile = new File("E:/9.jpg");
            ImageIO.write(ImageNew, "jpg", outFile);//дͼƬ



        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        try {
            //��ȡ��һ��ͼƬ
            File fileOne = new File("E:/1.jpg");
            BufferedImage ImageOne = ImageIO.read(fileOne);
            int width = ImageOne.getWidth();//ͼƬ���
            int height = ImageOne.getHeight();//ͼƬ�߶�

            //��ͼƬ�ж�ȡRGB
            int[] ImageArrayOne = new int[width*height];
            ImageArrayOne = ImageOne.getRGB(0,0,width,height,ImageArrayOne,0,width);

            //�Եڶ���ͼƬ����ͬ�Ĵ���
            File fileTwo = new File("E:/2.jpg");
            BufferedImage ImageTwo = ImageIO.read(fileTwo);
            int[] ImageArrayTwo = new int[width*height];
            ImageArrayTwo = ImageTwo.getRGB(0,0,width,height,ImageArrayTwo,0,width);

            //�Ե�����ͼƬ����ͬ�Ĵ���
//            File fileThree = new File("E:/3.jpg");
//            BufferedImage three = ImageIO.read(fileThree);
//            int[] aThree = new int[width*height];
//            aThree = three.getRGB(0,0,width,height,aThree,0,width);
//
//            //�Ե�����ͼƬ����ͬ�Ĵ���
//            File fileFour = new File("E:/4.jpg");
//            BufferedImage four = ImageIO.read(fileFour);
//            int[] aFour = new int[width*height];
//            aFour = four.getRGB(0,0,width,height,aFour,0,width);

            //������ͼƬ
            //BufferedImage ImageNew = new BufferedImage(width*2,height*2,BufferedImage.TYPE_INT_RGB);
            BufferedImage ImageNew = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);



            ImageNew.setRGB(1,51,98,98,ImageArrayOne,0,width);//������벿�ֵ�RGB
            ImageNew.setRGB(101,51,98,98,ImageArrayTwo,0,width);//�����Ұ벿�ֵ�RGB
            //ImageNew.setRGB(0,width,width,height,aThree,0,width);//�����Ұ벿�ֵ�RGB
            //ImageNew.setRGB(width,width,width,height,aFour,0,width);//�����Ұ벿�ֵ�RGB
            File outFile = new File("E:/9.jpg");
            ImageIO.write(ImageNew, "jpg", outFile);//дͼƬ



        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}

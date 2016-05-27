package test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by loovee1 on 2016/3/30.
 */
public class ImageText {
    public static void main(String args[]) {
        try {
            //读取第一张图片
            File fileOne = new File("E:/1.jpg");
            BufferedImage ImageOne = ImageIO.read(fileOne);
            int width = ImageOne.getWidth();//图片宽度
            int height = ImageOne.getHeight();//图片高度

            //从图片中读取RGB
            int[] ImageArrayOne = new int[width*height];
            ImageArrayOne = ImageOne.getRGB(0,0,width,height,ImageArrayOne,0,width);

            //对第二张图片做相同的处理
            File fileTwo = new File("E:/2.jpg");
            BufferedImage ImageTwo = ImageIO.read(fileTwo);
            int[] ImageArrayTwo = new int[width*height];
            ImageArrayTwo = ImageTwo.getRGB(0,0,width,height,ImageArrayTwo,0,width);

            //对第三张图片做相同的处理
            File fileThree = new File("E:/3.jpg");
            BufferedImage three = ImageIO.read(fileThree);
            int[] aThree = new int[width*height];
            aThree = three.getRGB(0,0,width,height,aThree,0,width);

            //对第四张图片做相同的处理
            File fileFour = new File("E:/4.jpg");
            BufferedImage four = ImageIO.read(fileFour);
            int[] aFour = new int[width*height];
            aFour = four.getRGB(0,0,width,height,aFour,0,width);

            //生成新图片
            BufferedImage ImageNew = new BufferedImage(width*2,height*2,BufferedImage.TYPE_INT_RGB);



            ImageNew.setRGB(0,0,width,height,ImageArrayOne,0,width);//设置左半部分的RGB
            ImageNew.setRGB(width,0,width,height,ImageArrayTwo,0,width);//设置右半部分的RGB
            ImageNew.setRGB(0,width,width,height,aThree,0,width);//设置右半部分的RGB
            ImageNew.setRGB(width,width,width,height,aFour,0,width);//设置右半部分的RGB
            File outFile = new File("E:/9.jpg");
            ImageIO.write(ImageNew, "jpg", outFile);//写图片



        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}

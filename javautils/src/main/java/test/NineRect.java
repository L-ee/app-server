package test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NineRect {
	private static float maxWidth = 150f;
	private static float maxHeight = 150f;
	
	private static Map<Integer, List<MyBitmap>> mMap = new HashMap<Integer, List<MyBitmap>>();
	
	private static class ColumnRowCount{
		int rows;
		int columns;
		int count;
		
		public ColumnRowCount(int rows, int column, int count) {
			super();
			this.rows = rows;
			this.columns = column;
			this.count = count;
		}

		@Override
		public String toString() {
			return "ColumnRowCount [rows=" + rows + ", columns=" + columns
					+ ", count=" + count + "]";
		}
	}
	
	private static ColumnRowCount generateColumnRowCountByCount(int count){
		switch (count) {
		case 2:
			return new ColumnRowCount(1, 2, count);
		case 3:
		case 4:
			return new ColumnRowCount(2, 2, count);
		case 5:
		case 6:
			return new ColumnRowCount(2, 3, count);
		case 7:
		case 8:
		case 9:
			return new ColumnRowCount(3, 3, count);
		default:
			return new ColumnRowCount(1, 1, count);
		}
	}
	
	private static class MyBitmap{
		float x;
		float y;
		float width;
		float height;
		static int devide = 1;
		int index = -1;
		
		@Override
		public String toString() {
			return "MyBitmap [x=" + x + ", y=" + y + ", width=" + width
					+ ", height=" + height + ", devide=" + devide + ", index="
					+ index + "]";
		}
	}
	
	private static int getMeetingMemberCount(){
		return 1;
	}
	
	public static void main(String[] args) {
		for (int i = 1; i < 10; i++) {
			ColumnRowCount mCRC = generateColumnRowCountByCount(i);
			MyBitmap bitmap = null;
			
			float perBitmapWidth = (maxWidth - MyBitmap.devide * 2 * mCRC.columns) / mCRC.columns;
			float topDownDelta = maxHeight - (mCRC.rows * (perBitmapWidth + MyBitmap.devide * 2));
			List<MyBitmap> mList = new LinkedList<MyBitmap>();
			for (int row = 0; row < mCRC.rows; row++) {
				for (int column = 0; column < mCRC.columns; column++) {
					bitmap = new MyBitmap();
					if (i == 7) {
						System.out.println("row=>" + row);
					}
					bitmap.y = 1 + topDownDelta/2 + row * 2 + row * perBitmapWidth;
					bitmap.x = 1 + column * 2 + column * perBitmapWidth;
					bitmap.width = bitmap.height = perBitmapWidth;
					if (i == 7) {
						System.out.println("bitmap=>" + bitmap);
					}
					mList.add(bitmap);
				}
			}
			mMap.put(i, mList);
		}
		modifyListWhenCountThree();
		modifyListWhenCountFive();
		modifyListWhenCountSeven();
		modifyListWhenCountEight();
		printAllBitmapCoordinates();
	}
	
	private static void modifyListWhenCountThree(){
		List<MyBitmap> mList1 = mMap.get(3);
		MyBitmap mBitmap1 = mList1.get(0);
		MyBitmap mBitmap2 = mList1.get(1);
		MyBitmap mDesBitmap = new MyBitmap();
		mDesBitmap.width = mBitmap1.width;
		mDesBitmap.height = mBitmap1.height;
		mDesBitmap.y = mBitmap1.y;
		mDesBitmap.x = (mBitmap1.x + mBitmap2.x)/2;
		mList1.set(0, mDesBitmap);
		mList1.remove(1);
	}
	
	private static void modifyListWhenCountFive(){
		List<MyBitmap> mList1 = mMap.get(5);
		MyBitmap mBitmap1 = mList1.get(0);
		MyBitmap mBitmap2 = mList1.get(1);
		MyBitmap mBitmap3 = mList1.get(2);
		MyBitmap mDesBitmap = new MyBitmap();
		mDesBitmap.width = mBitmap1.width;
		mDesBitmap.height = mBitmap1.height;
		mDesBitmap.y = mBitmap1.y;
		mDesBitmap.x = (mBitmap1.x + mBitmap2.x)/2;
		
		MyBitmap mDesBitmap2 = new MyBitmap();
		mDesBitmap2.width = mBitmap2.width;
		mDesBitmap2.height = mBitmap2.height;
		mDesBitmap2.y = mBitmap2.y;
		mDesBitmap2.x = (mBitmap2.x + mBitmap3.x)/2;
		mList1.set(0, mDesBitmap);
		mList1.set(1, mDesBitmap2);
		mList1.remove(2);
	}
	
	private static void modifyListWhenCountSeven(){
		List<MyBitmap> mList1 = mMap.get(7);
		System.out.println("[modifyListWhenCountSeven]" + mList1.size());
		MyBitmap mBitmap1 = mList1.get(4);
		System.out.println("[modifyListWhenCountSeven]" + mBitmap1);
//		MyBitmap mDesBitmap = new MyBitmap();
//		mDesBitmap.width = mBitmap1.width;
//		mDesBitmap.height = mBitmap1.height;
//		mDesBitmap.y = mList1.get(0).y;
//		mDesBitmap.x = mBitmap1.x;
//		
//		mList1.set(0, mDesBitmap);
		mList1.remove(0);
		mList1.remove(1);
	}
	
	private static void modifyListWhenCountEight(){
		List<MyBitmap> mList1 = mMap.get(8);
		MyBitmap mBitmap1 = mList1.get(0);
		MyBitmap mBitmap2 = mList1.get(1);
		MyBitmap mBitmap3 = mList1.get(2);
		MyBitmap mDesBitmap = new MyBitmap();
		mDesBitmap.width = mBitmap1.width;
		mDesBitmap.height = mBitmap1.height;
		mDesBitmap.y = mBitmap1.y;
		mDesBitmap.x = (mBitmap1.x + mBitmap2.x)/2;
		
		MyBitmap mDesBitmap2 = new MyBitmap();
		mDesBitmap2.width = mBitmap2.width;
		mDesBitmap2.height = mBitmap2.height;
		mDesBitmap2.y = mBitmap2.y;
		mDesBitmap2.x = (mBitmap2.x + mBitmap3.x)/2;
		mList1.set(0, mDesBitmap);
		mList1.set(1, mDesBitmap2);
		mList1.remove(2);
	}
	
	private static void printAllBitmapCoordinates(){
		
		for (int i = 1; i < 10; i++) {
//			if (i != 7) {
//				continue;
//			}
			List<MyBitmap> mList = mMap.get(i);
			System.out.println("********enter count=" + i + " *********");
			StringBuffer buffer = new StringBuffer();
			for (MyBitmap myBitmap : mList) {
				buffer.append(myBitmap.x);
				buffer.append(",");
				buffer.append(myBitmap.y);
				buffer.append(",");
				buffer.append(myBitmap.width);
				buffer.append(",");
				buffer.append(myBitmap.height);
				buffer.append(";");
				System.out.println("myBitmap=>" + myBitmap);
			}
			PropertiesUtil.writeData(String.valueOf(i), buffer.toString());
			System.out.println("********exit count=" + i + " *********");
		}
	}
}

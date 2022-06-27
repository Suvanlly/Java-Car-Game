package GamePlay;

public class Random {

	public int generateRandom(int min, int max)//1,100
	{
		// 0~0.99
		return (int)(Math.random() * (max - min + 1) + min);
	}
	
//	public static void main(String[] args)
//	{
//		Random r = new Random();
//		System.out.println(r.generateRandom(1,100));
//		System.out.println(r.generateRandom(1,100));
//		System.out.println(r.generateRandom(1,100));
//		System.out.println(r.generateRandom(1,100));
//		System.out.println(r.generateRandom(1,100));
//		System.out.println(r.generateRandom(1,100));
//		System.out.println(r.generateRandom(1,100));
//		System.out.println(r.generateRandom(1,100));
//		System.out.println(r.generateRandom(1,100));
//
//
//	}
}

import java.util.Scanner;

public class NumberVoiceMain {

	public static void main(String[] args) {
        
		String s = NumberVoice.getNumberVoiceString(inputNumber());
		System.out.println(s) ;
        
	}

	public static long inputNumber() {
		Scanner sc = new Scanner(System.in);
		long inputNumber;
		System.out.println("数値を入力して下さい( 1 〜 " + Long.MAX_VALUE + " )--->");
		while (true) {
			if (sc.hasNext()) {
				if (sc.hasNextLong()) {
					inputNumber = sc.nextLong();
					break;
				} else {
					System.out.println(sc.next() + "は有効な数値ではありません。");
					System.out.println("数値を入力してください--->");
				}
			}
		}
		return inputNumber;
	}

}

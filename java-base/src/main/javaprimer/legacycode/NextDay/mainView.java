
import java.util.Scanner;

public class mainView {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int year = 0, month = 0, day = 0;
		MyDate mydate = null;
		int count = 0;
		while (true) {
			if (count != 0) {
				System.out.println();
				System.out.println("������ѡ�������Y����������ԣ������˳���");
				if (scanner.hasNext()) {
					String input = scanner.next();
					if (!"Y".equalsIgnoreCase(input)) {
						break;
					}
				}
			}
			count++;
			year = 0;
			month = 0 ;
			day = 0;
			/**
			 * ����ֻ������Ϸ�������
			 */

				System.out.print("****��������ݣ�");
				if (scanner.hasNextInt()) {
					year = scanner.nextInt();
					System.out.print("****�������·ݣ�");
				}
				if (scanner.hasNextInt()) {
					month = scanner.nextInt();
					System.out.print("****������������");
				}
				if (scanner.hasNextInt()) {
					day = scanner.nextInt();
				}
				// if(year==0 || month== 0|| day==0){
				// break;
				// }
				System.out.print("****���������Ϊ��");
				System.out.println(year + "-" + month + "-" + day);
				mydate = new MyDate(year, month, day);
				boolean isValidate = HandleDate.validateDate(mydate);
				if (!isValidate) {
					System.out.println("****��������ڲ����ڣ�");
					continue;
				}
			System.out.print("****��һ��֮�������Ϊ��");
			mydate = HandleDate.addOneDay(mydate);
			System.out.println(mydate.getYear() + "-" + mydate.getMonth() + "-"
					+ mydate.getDay());
		}
		scanner.close();
	}
}

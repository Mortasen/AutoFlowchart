//import java.util.Scanner;
//public class Main
//{
//	public static void main(String[] args) {
//		boolean menu=true;
//		//Виведення тесту меню
//		System.out.println("1. Задача 1 (про паралелограм)");
//		System.out.println("2. Задача 2 (координати точки)");
//		System.out.println("3. Задача 3 (цілі числа між ч та у)");
//		System.out.println("4. Задача 4 (всі числа від 1 до 100)");
//		System.out.println("5. Задача 5 (точки трикутника)");
//		System.out.println("6. Завершити роботу програми");
//		//Для зручного використання програми скористуємось циклом while та switch...case
//		while (menu){
//
//			int num=0;
//			Scanner in = new Scanner(System.in);
//			System.out.print("Введіть номер команди: ");
//			num = in.nextInt();
//			switch (num){
//				case 1:
//					System.out.println("Обчислити периметр, площу та довжину діагоналі паралелограма за
//							відомими довжинами його сторін та кутом між ними");
//					double lengthA, lengthB, angle, angle2;
//					System.out.print("Введіть першу довжину сторони");
//					lengthA=in.nextDouble();
//					System.out.print("Введіть другу довжину сторони");
//					lengthB=in.nextDouble();
//					System.out.print("Введіть ГОСТРИЙ кут між ними");
//					angle=in.nextDouble();
//					double P=2*(lengthA + lengthB);
//					System.out.println("периметр = "+ P);
//					angle=Math.toRadians(angle);
//
//					double diagonal = Math.sqrt(Math.pow(lengthA, 2) + Math.pow(lengthB, 2) -
//												2*lengthA*lengthB*Math.cos(angle));
//					System.out.println("мала діагональ = " + diagonal);
//					diagonal=Math.sqrt(Math.pow(lengthA, 2) + Math.pow(lengthB, 2) +
//									   2*lengthA*lengthB*Math.cos(angle));
//					System.out.println("велика діагональ = " + diagonal);
//					break;
//
//				case 2:
//					System.out.println("Дано два числа – x та y . З'ясувати, чи належить точка з координатами
//					(x;y) кругу одиничного радіуса із центром у точці (1;2) .");
//					double x, y;
//					System.out.print("введіть Х: ");
//					x=in.nextDouble();
//					System.out.print("введіть У: ");
//					y=in.nextDouble();
//
//					if ( (Math.pow((x-1),2)) + (Math.pow((y-2),2)) == 1){
//						System.out.println("Належить.");
//					}
//					else {System.out.println("Не належить.");}
//					break;
//
//				case 3:
//					int X, Y;
//					int N=0;
//					System.out.println(" Дано два цілих числа x і y (x<y). Вивести всі цілі числа, розташовані
//							між даними числами (включаючи самі ці числа), а також кількість цих чисел.");
//							System.out.print("введіть Х: ");
//					X=in.nextInt();
//					System.out.print("введіть У: ");
//					Y=in.nextInt();
//
//					if((X>Y)|(X==Y)){
//						System.out.print("Помилка, введіть Х та У так, що х<у");
//						System.out.print("введіть Х: ");
//						X=in.nextInt();
//						System.out.print("введіть У: ");
//						Y=in.nextInt();
//					}
//					for (int i=0; (X<(Y-1)); i++){
//						X++;
//						N++;
//						System.out.print(X + "; ");
//					}
//					System.out.println("кількість чисел = " + N);
//					break;
//
//
//				case 4:
//					System.out.println("Вивести всі числа від 1 до 100, що діляться на 5 без остачі.");
//					int[] arr = new int[20];
//					int a=5;
//					for (int i=0; i<20; i++){
//						arr[i]=a;
//						System.out.print(arr[i] + "; ");
//						a+=5;
//					}
//					break;
//
//				case 5:
//					int xx;
//					int yy;
//					System.out.println("Вивести координати всіх точок з цілими координатами, які знаходяться
//							всередині трикутника");
//							//x є (-2;3) у є (1;5)
//
//					for (xx=-2; xx<=3; xx++){
//						for(yy=1; yy<=5; yy++){
//							if ((-4*xx + 5*yy >= 13)&&(3*xx - 2*yy >= -8)&&(3*yy - xx <= 12)){
//								System.out.print("(" + xx + ";" + yy + ") ");
//							}
//
//						}
//					}
//					break;
//				//У разы введеної команди «6» програма закінчує роботу
//				case 6:
//					menu=false;
//					break;
//				//У разі введення команди, якої немає в переліку, виводиться помилка
//				default:
//					System.out.println("Такої команди не існує, введіть команду зі списку");
//					break;
//			}
//		}
//	}
//}
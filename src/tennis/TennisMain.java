package tennis;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TennisMain {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("=".repeat(48));
		System.out.println(" \t\t【테니스 게임】 ");
		System.out.println("=".repeat(48));
		System.out.println("\t\t\t\tMade by 귀염 2조 ");
		System.out.println();
		System.out.println("   ▶ ▶ ▶ 게임을 시작하려면 Enter키를 누르세요\t\n");
		System.out.print("\n\t      입력 : ▣ Press Enter");

		String input = scanner.nextLine(); // Enter키

		if (input.isEmpty()) { // Enter키 치면
			System.out.println("\n");
			System.out.println("-".repeat(48));
			System.out.println("☞ 경기방법 설정을 위한 정보값 입력을 시작합니다.");
			System.out.println("-".repeat(48));
			System.out.println("\n");
		}

		// 게임 세팅
		// 세트수, 플레이어 이름
		boolean flag = false;
		int numSet = 0; //세트수

		while (!flag) {
			System.out.println(" 성별에 따라 경기 시행횟수가 달라집니다. ");
			System.out.println("▶ 경기를 선택해주세요. [ 남자 경기(1) / 여자경기(2) ]");

			try {
				int play = scanner.nextInt();

				if (play == 1) {	//남자경기
					System.out.println(" ☞ 남자 경기를 선택하셨습니다. 5전3승 경기로 진행됩니다.");
					numSet = 5;
					flag = true;
				} else if (play == 2) {		//여자경기
					System.out.println(" ☞ 여자 경기를 선택하셨습니다. 3전2승 경기로 진행됩니다.");
					numSet = 3;
					flag = true;
				} else {		//잘못입력
					System.out.println("잘못 입력하셨습니다. 숫자만 입력하셔야 합니다.");
				}
			} catch (InputMismatchException e) {
				System.out.println("다시 입력해주세요. 입력: ");
				scanner.next();
			}
		}

		System.out.print("player1 이름을 입력하세요 : ");	
		String player1 = scanner.next();		
		System.out.print("player2 이름을 입력하세요 : ");
		String player2 = scanner.next();

		ScoreCounter scoreCounter = new ScoreCounter(numSet, player1, player2); // scoreCounter객체생성
		
		while (scoreCounter.winner == null) { //winner가 나올 때까지 반복
			Random random = new Random(); 
			int p = random.nextInt(2) + 1;  //1 or 2
			
			scoreCounter.pointWinner(p); 

			scoreCounter.scoreBoard(); 

			scoreCounter.dispScoreBoard();
		} // while

	} // main
}// class
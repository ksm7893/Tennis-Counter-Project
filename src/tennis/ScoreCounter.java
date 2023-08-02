package tennis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class ScoreCounter implements I { // 인터페이스 I를 구현하고 override 사용하는 클래스

	// 필드
	int numSet; // 세트수
	String player1;
	String player2;

	int player1Point = 0; // 포인트 점수
	int player2Point = 0;

	int player1Game = 0; // 게임 점수
	int player2Game = 0;

	int player1Set = 0; // 세트 점수
	int player2Set = 0;

	int player1Duece = 0; // 듀스 점수
	int player2Duece = 0;
	int a; // 랜덤값 변수
	String winner = null; // 승리자

	String list1 = null; // 파일입출력 변수
	String list2 = null;
	String list201 = null;
	String list3 = null;
	String list4 = null;
	String list5 = null;
	String list6 = null;
	String list7 = null;
	boolean flag = true;

	WriteResult writeResult = new WriteResult(); // writeResult 객체생성

	// 생성자
	public ScoreCounter(int numSet, String player1, String player2) {
		super();
		this.numSet = numSet;
		this.player1 = player1;
		this.player2 = player2;
	}

	@Override
	public void pointWinner(int p) { // pointWinner 오버라이딩
		if (p == 1) {
			player1Point++;
		} else
			player2Point++;
	}

	public void scoreBoard() { // 계수기 메서드

		if ((player1Game == 5 && player2Game == 6) || (player1Game == 6 && player2Game == 5)) { //게임듀스 로직
			System.out.println("        게임듀스 발생!!");
			while (true) {
				a = (int) (Math.random() * 2 + 1);  // 1 or 2
				if (a == 1)
					player1Duece++;
				else
					player2Duece++;
				if (player1Duece - player2Duece == 2) {  //player1이 2점차로 앞설때
					player1Set++;
					pointReset();
					gameReset();
					dueceReset();
					System.out.printf("게임듀스에서 %s 승리!!\n", player1);
					break;
				}
				if (player2Duece - player1Duece == 2) {	//player2가 2점차로 앞설때
					player2Set++;
					pointReset();
					gameReset();
					dueceReset();
					System.out.printf("게임듀스에서 %s 승리!!\n", player2);
					break;
				}
			} //while
		} //if 게임듀스

		if ((player1Point == 3 && player2Point == 4) || (player1Point == 4 && player2Point == 3)) { //포인트듀스
			System.out.println("      포인트듀스 발생!!");
			while (true) {
				a = (int) (Math.random() * 2 + 1); // 1 or 2
				if (a == 1)
					player1Duece++;
				else
					player2Duece++;
				if (player1Duece - player2Duece == 2) {
					player1Game++;
					dueceReset();
					pointReset();
					System.out.printf("포인트듀스에서 %s 승리!!\n", player1);
					break;
				}
				if (player2Duece - player1Duece == 2) {
					player2Game++;
					dueceReset();
					pointReset();

					System.out.printf("포인트듀스에서 %s 승리!!\n", player2);
					break;
				}
			} //while
		}

		if (player1Point == 4) { //player1이 4포인트를 이겼을때
			player1Game++;
			pointReset();
		}
		if (player2Point == 4) { //player2이 4포인트를 이겼을때
			player2Game++;
			pointReset();
		}
		if (player1Game == 6) { //player1이 6게임를 이겼을때
			player1Set++;
			gameReset();
			pointReset();
		}
		if (player2Game == 6) { //player이 6게임를 이겼을때
			player2Set++;
			gameReset();
			pointReset();
		}

		
		if (player1Set > player2Set && player1Set >= numSet / 2 + 1) {// 최종 승자 도출 
			winner = player1;
		} else if (player2Set > player1Set && player2Set >= numSet / 2 + 1) {
			winner = player2;
		}
	} //scoreBoard

	private void dueceReset() { //듀스점수리셋 메서드
		player1Duece = 0;
		player2Duece = 0;
	}

	private void gameReset() { //게임점수리셋 메서드
		player1Game = 0;
		player2Game = 0;
	}

	private void pointReset() { //세트점수리셋 메서드
		player1Point = 0;
		player2Point = 0;
	}

	@Override
	public void dispScoreBoard() {  //현재 점수 출력 메서드
		int[] player1Points = { 0, 15, 30, 40 }; //포인트 점수
		int[] player2Points = { 0, 15, 30, 40 };
		int printplayer1Point = player1Points[player1Point % 4];
		int printplayer2Point = player2Points[player2Point % 4];

		System.out.println("\tSCORE\tGAME \tSET");
		System.out.println("-".repeat(30));
		System.out.printf("%s\t %d\t %d\t %d\n", player1, printplayer1Point, player1Game, player1Set);
		System.out.println();
		System.out.printf("%s\t %d\t %d\t %d\n", player2, printplayer2Point, player2Game, player2Set);
		System.out.println("-".repeat(30));
		if (winner != null) {
			System.out.printf("승자가 나왔습니다 : %s", winner);
			list1 = String.format("\t   SCORE\t GAME \t    SET\n"); //리스트 저장함수
			list2 = String.format("-".repeat(35));
			list201 = String.format("\n");
			list3 = String.format("%s\t\t %d\t\t %d\t\t\t %d\n", player1, printplayer1Point, player1Game, player1Set);
			list4 = String.format("%s\t\t %d\t\t %d\t\t\t %d\n", player2, printplayer2Point, player2Game, player2Set);
			list5 = String.format("-".repeat(35));
			list6 = String.format("\n");
			list7 = String.format("승자는 %s입니다!!!!", winner);
			writeResult.writeTennisResult(list1, list2, list201, list3, list4, list5, list6, list7);
		}
		try {
			Thread.sleep(100); // 1초씩 찍히게한다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	} //dispScoreBoard()

} // class


public class Judge {
	
	public void play(Player player, Enemy enemy) {
		int comNum = enemy.chooseN(); // 개발자 확인용 // enemy 클래스에 choose N가 있다는 이야기
		System.out.println(comNum);
		
		
		for (int turn = 0; turn <= 10; turn++) {
			System.out.printf("현재 %d번째 도전 중!\n",turn);
			System.out.println("뭐?");
			int playNum = player.say();
			
			if (playNum < 1 || playNum > 100 ) {
				System.err.println("입력 오류! 범위를 초과했습니다.");
				turn--;
				continue;
			}
		
			
			boolean isOver = result(comNum,playNum,turn);
			if (isOver) {
				break;
			}
		
		
		
		}
		int playNum = player.say();
		System.out.println(playNum);
	}
	
		private boolean result(int comNum, int playNum, int turn) {
			
			 if (comNum == playNum) {
				 System.out.printf("correct! you tried %d times", turn);
				 return true;
			}else if (playNum > comNum) {
				System.out.println("다운");
			} else {
				System.out.println("업");
		}	
			 return false;

}}

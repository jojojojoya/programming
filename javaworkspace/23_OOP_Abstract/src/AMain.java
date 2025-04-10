
public class AMain {
	public static void main(String[] args) {
		
		
		// 추상적인 상위개념이 있기에 하위에 구체적인 개념들이 존재
		// 히어로 /영웅
		
		// 슈퍼맨 - 클라크
		SuperMan superMan = new SuperMan();
		superMan.attack();
		
		// 아이언맨 - 토니
		IronMan ironMan = new IronMan();
		ironMan.attack();
		
		// 헐크 - 배너
		Hulk hulk = new Hulk();
		hulk.attack();
		
		
		
	}
}

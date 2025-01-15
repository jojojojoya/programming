package poly;

public class FaceBookSender implements Sender {
	@Override
	public void sendMessage(String message) {
	System.out.println("FB 메시지를 발송합니다" + message);
	
	
}}
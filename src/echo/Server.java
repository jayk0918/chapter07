package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket();
		serverSocket.bind(new InetSocketAddress("192.168.0.12", 10001)); // (ip주소, port)
		
		System.out.println("<서버 시작>");
		System.out.println("============================================================");
		System.out.println("[연결을 기다리고 있습니다.]");
		
		// 반복의 시작(클라이언트 대기 & Thread로 보내기 작업)
		while(true) {
			Socket socket = serverSocket.accept();
			// 여기서는 소켓만 만들어주고 나머지는 Thread로 보낸 다음 Thread가 알아서 작업하게끔 유도
			Thread thread = new ServerThread(socket); // 여러개의 서버 소켓이 생길 예정이기 때문에 socket을 생성자()안으로
			thread.start();
		}
		// 서버는 종료되지 않음을 목적으로 하기 떄문에 탈출조건을 일단 만들지 않음 (필요하면 꺼야겠지만)
		
	}

}

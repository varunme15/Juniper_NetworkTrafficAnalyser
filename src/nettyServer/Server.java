package nettyServer;

import java.util.ArrayList;

public class Server {
	public final static ArrayList<String> queue = new ArrayList<String>();
	public final static kafka.KafkaProducer kp = new kafka.KafkaProducer();
	
	public static ArrayList<String> getMessage(){
		//System.out.println("Size"+ queue.size());
		return queue;
	}
	public static void main(String[] args) {
			System.out.println("I am here");
			
			NettyServer svr = new NettyServer();
			
			svr.run();
			
		

	}

}

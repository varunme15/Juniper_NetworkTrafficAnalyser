package queue;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

import com.googlecode.protobuf.format.JsonFormat;

import io.netty.channel.Channel;
import analytics.Analytics.AnRecord;

public class InboundQueue {
	public static ArrayList<String> inbound = new ArrayList<String>();
	
	public InboundQueue(){
		System.out.println("Queue");
	}
	public void enqueueRequest(AnRecord req, Channel notused) {
		try {
			String jsonString = JsonFormat.printToString(req);
			
			// kp = new kafka.KafkaProducer();
			//System.out.println(jsonString);
			//KafkaProducer.startConsumer(jsonString);
			
		} catch (Exception e) {
			System.out.println("message not enqueued for processing"+e);
		}
	}
	
	
}

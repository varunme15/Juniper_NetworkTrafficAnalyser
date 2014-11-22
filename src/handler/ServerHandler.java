package handler;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

import nettyServer.NettyServer;
import nettyServer.Server;

import com.google.protobuf.GeneratedMessage;
import com.googlecode.protobuf.format.JsonFormat;

import kafka.KafkaProducer;
import kafka.producer.KeyedMessage;
//import queue.queue;
import scala.util.parsing.combinator.testing.Str;
import scala.util.parsing.json.JSONFormat;
import analytics.Analytics.AnRecord;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import queue.*;

public class ServerHandler extends SimpleChannelInboundHandler<analytics.Analytics.AnRecord> {
	public static ArrayList<String> inbound = new ArrayList<String>();
	public static InboundQueue q = new InboundQueue();
	//kafka.KafkaProducer kp;
	public ServerHandler() {
		System.out.println("** ServerHandler created **");
		//kp = new kafka.KafkaProducer();
	}
	
	
	@Override
	public void channelRead0(ChannelHandlerContext ctx, analytics.Analytics.AnRecord req) throws Exception {
		String jsonString = JsonFormat.printToString(req);
		
		//kp = new kafka.KafkaProducer();
		//System.out.println(jsonString);
		Server.kp.startConsumer(jsonString);
		//q.enqueueRequest(req,ctx.channel());
		
	}
	
	
}

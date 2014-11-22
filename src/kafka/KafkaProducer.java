package kafka;
import handler.ServerHandler;
import queue.*;
import nettyServer.*;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Queue;

import com.google.protobuf.GeneratedMessage;
import com.googlecode.protobuf.format.JsonFormat;

import scala.util.parsing.json.JSON;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducer{

		final static String TOPIC = "sparkNetwork";
		kafka.javaapi.producer.Producer<String,String> producer;
		int i = 0;
		public KafkaProducer(){
			System.out.println("Producer");
	    	Properties properties = new Properties();
	        properties.put("metadata.broker.list","localhost:9093");
	        properties.put("serializer.class","kafka.serializer.StringEncoder");
	        ProducerConfig producerConfig = new ProducerConfig(properties);
	         producer = new kafka.javaapi.producer.Producer<String, String>(producerConfig);
	        SimpleDateFormat sdf = new SimpleDateFormat();
	        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+ i);
		}
		 public void startConsumer(String msg){        //Thread.sleep(1000);
		     KeyedMessage<String, String> message =new KeyedMessage<String, String>(TOPIC, msg);
		     producer.send(message);
		   }
	   
	}


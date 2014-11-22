package nettyServer;

import initializer.ServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import nettyServer.Server;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class NettyServer {
	protected static ChannelGroup allChannels;
	protected static HashMap<Integer, ServerBootstrap> bootstrap = new HashMap<Integer, ServerBootstrap>();
	
	public static void shutdown() {
		try {
			if (allChannels != null) {
				ChannelGroupFuture grp = allChannels.close();
				grp.awaitUninterruptibly(5, TimeUnit.SECONDS);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Server shutdown");
		System.exit(0);
	}
	
	public void run() {
	EventLoopGroup bossGroup = new NioEventLoopGroup();
	EventLoopGroup workerGroup = new NioEventLoopGroup();

	try {
		ServerBootstrap b = new ServerBootstrap();
		bootstrap.put(50005, b);
		System.out.println("Conf"+ 50005);
		b.group(bossGroup, workerGroup);
		b.channel(NioServerSocketChannel.class);
		b.option(ChannelOption.SO_BACKLOG, 100);
		b.option(ChannelOption.TCP_NODELAY, true);
		b.option(ChannelOption.SO_KEEPALIVE, true);
		// b.option(ChannelOption.MESSAGE_SIZE_ESTIMATOR);

		boolean compressComm = false;
		b.childHandler(new ServerInitializer(compressComm));

		// Start the server.
		System.out.println("Starting server  listening on port = 50005");
		ChannelFuture f = b.bind(50005).syncUninterruptibly();
		
		// should use a future channel listener to do this step
		//.add(f.channel());

		// block until the server socket is closed.
		//System.out.println("CHannel CLoseing");
		f.channel().closeFuture().sync();
		
		
			    
	

		// We can also accept connections from a other ports (e.g., isolate
		// read
		// and writes)


		
		
	} catch (Exception ex) {
		// on bind().sync()
		System.out.println("Failed to setup public handler."+ ex);
	} finally {
		// Shut down all event loops to terminate all threads.
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}

	

}
}

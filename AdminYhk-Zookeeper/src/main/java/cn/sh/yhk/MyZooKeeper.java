package cn.sh.yhk;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

//@EnableAdminServer
public class MyZooKeeper implements Watcher {
	Logger logger = Logger.getLogger(MyZooKeeper.class);

	protected CountDownLatch countDownLatch = new CountDownLatch(1);
	private static final int SESSION_TIME = 2000;
	public static ZooKeeper zooKeeper = null;

	public static void main(String[] args) {
	}

	/**
	 * 监控所有被触发的事件
	 */
	public void process(WatchedEvent event) {
		logger.info("收到事件通知：" + event.getState());
		if (event.getState() == KeeperState.SyncConnected) {
			countDownLatch.countDown();
		}
	}

	public void connect(String hosts) {
		try {
			if (zooKeeper == null) {
				// ZK客户端允许我们将ZK服务器的所有地址都配置在这里
				zooKeeper = new ZooKeeper(hosts, SESSION_TIME, this);
				// 使用CountDownLatch.await()的线程（当前线程）阻塞直到所有其它拥有
				// CountDownLatch的线程执行完毕（countDown()结果为0）
				countDownLatch.await();
			}
		} catch (IOException e) {
			logger.error("连接创建失败，发生 InterruptedException , e " + e.getMessage(), e);
		} catch (InterruptedException e) {
			logger.error("连接创建失败，发生 IOException , e " + e.getMessage(), e);
		}
	}

	/**
	 * 关闭连接
	 */
	public void close() {
		try {
			if (zooKeeper != null) {
				zooKeeper.close();
			}
		} catch (InterruptedException e) {
			logger.error("release connection error ," + e.getMessage(), e);
		}
	}

}

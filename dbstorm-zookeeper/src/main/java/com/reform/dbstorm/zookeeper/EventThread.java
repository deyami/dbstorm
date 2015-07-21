package com.reform.dbstorm.zookeeper;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 事件分发线程，用于将zookeeper修改触发的各种事件分发给listener。
 * 
 * @author huaiyu.du@opi-corp.com 2012-1-19 下午3:44:08
 */
public class EventThread extends Thread {

	public static final Logger				log		= LoggerFactory.getLogger(EventThread.class);

	private static AtomicInteger			eventId	= new AtomicInteger(0);
	private LinkedBlockingQueue<ZKEvent>	queue	= new LinkedBlockingQueue<ZKEvent>();

	public EventThread(String name) {
		setDaemon(true);//不影响进程
		setName("ZKClient-EventThread-" + getId() + "-" + name);
	}

	public void run() {
		log.info("start zk-event thread");
		try {
			ZKEvent event = null;
			while (!isInterrupted()) {
				event = queue.take();//可能发生中断
				int id = eventId.incrementAndGet();
				try {
					event.run();
					log.debug("dispatch event {}, id {}", event, id);
				} catch (InterruptedException e) {
					interrupt();//任务中有中断需求
				} catch (Exception e) {
					//可能会发生异常
					log.error("error  handling event " + event + " ,id " + id, e);
				}
			}
		} catch (InterruptedException e) {
			log.info("zk-event thread interrupted");
		}
	}

	public void send(final ZKEvent zkEvent) {
		queue.add(zkEvent);
	}
}

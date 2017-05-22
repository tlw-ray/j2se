package com.tlw.eg.obj.notify;
/**
 * @author liwei.tang@magustek.com
 * @since 2011-12-2
 * 
 * 		我好土，真的。学用了四五年的JAVA，还从没用过 wait/notify，也不清楚它跟synchronized 关键字 相比有什么好处。
 * 今天查了些资料，终于明白了：wait/notify  机制是为了避免轮询带来的性能损失。 
 * 为了说清道理，我们用“图书馆借书”这个经典例子来作解释。 
 * 一本书同时只能借给一个人。现在有一本书，图书馆已经把这本书借了张三。 
 * 在简单的synchrnozed 同步机制下，李四如果想借，先要去图书馆查看书有没有还回来。
 * 李四是个心急的人，他每天都去图书馆查；而张三看书看得慢，过了半个月才把书还回来，
 * 结果李四在这半个月里全都白跑了，浪费了不少交通车费 而如果使用wait/notify机制，李四就不用白忙了。
 * 他第一次去图书馆时发现书已借走，就回家静静等待(wait)；
 * 张三把书还掉后，通知(notify)李四，李四去图书馆拿书即可。
 * 整个过程中，李四没有白跑，没浪费钱。 
 * 
 * 回到计算机世界： 
 *  书           --  临界资源，需互斥地访问 
 *  张三，李四      --  两个竞争的线程
 *  坐车去图书馆查书   --  轮询 
 *  车费          --  CPU空间 
 *  等待          --  wait
 *  通知下一个借书者   --  notify 
 *  
 *  也就是说，若使用简单的synchonized机制实现互斥，会导致线程主动发起轮询，
 *  若N次轮询没有成功，就产生了N次的CPU空间浪费；如果加上了 wait/notify机制，
 *  就可以避免这些无谓的轮询，节省CPU的消耗。
 *  
 *  wait与notify是java同步机制中重要的组成部分。结合与synchronized关键字使用，可以建立很多优秀的同步模型。
 *  synchronized(this){}等价与public synchronized void method(){.....}
 *  同步分为类级别和对象级别，分别对应着类锁和对象锁。
 *  类锁是每个类只有一个，如果static的方法被synchronized关键字修饰，则在这个方法被执行前必须获得类锁；对象锁类同。
 *  (static synchronized是类级别的,非static的synchronized和synchronized块都是对象级别的,即作用在同一new出来的对象上)
 *  首先，调用一个Object的wait与notify/notifyAll的时候，必须保证调用代码对该Object是同步的，
 *  也就是说必须在作用等同于synchronized(obj){......}的内部才能够去调用obj的wait与notify/notifyAll三个方法，
 *  否则就会报错：java.lang.IllegalMonitorStateException: current thread not owner
 *  在调用wait的时候，线程自动释放其占有的对象锁，同时不会去申请对象锁。
 *  当线程被唤醒的时候，它才再次获得了去获得对象锁的权利。
 *  所以，notify与notifyAll没有太多的区别，只是notify仅唤醒一个线程并允许它去获得锁，
 *  notifyAll是唤醒所有等待这个对象的线程并允许它们去获得对象锁，只要是在synchronied块中的代码，
 *  没有对象锁是寸步难行的。其实唤醒一个线程就是重新允许这个线程去获得对象锁并向下运行。
 *  顺便说一下notifyall，虽然是对每个wait的对象都调用一次notify，但是这个还是有顺序的，
 *  每个对象都保存这一个等待对象链，调用的顺序就是这个链的顺序。
 *  其实启动等待对象链中各个线程的也是一个线程，在具体应用的时候，需要注意一下。
 * 
 * wait和notify用于争用对象的共享，并能够避免轮询，试探访问该争用对象造成的性能损耗。
 * 1：加锁的对象是被争用的；既同时只能有一个线程在使用该对象。
 * 		必须要处于synchronized()中的对象才可以notify和wait.
 * 		要真正的notify其他线程使用该对象，需要唤醒线程中的该对象进入wait状态,其他线程才会被真正激活。
 * 2:锁的对象有两类，一种是对象级，一种是类级(static)。wait和notify的对象需要和方法处于同级别。
 * 
 */
public class WaitNotify {
	public static void main(String[] args) {
		final Integer o1=new Integer(100);
		Thread th1=new Thread(){
			public void run(){
				synchronized(o1){
					while(true){
						try {
							System.out.println("Thread1 before wait()");
							o1.wait();
							System.out.println("Thread1 after wait()");
							o1.notifyAll();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		th1.start();
		Thread th2=new Thread(){
			public void run(){
				synchronized(o1){
					while(true){
						try {
							System.out.println("Thread2 before notify()");
							sleep(3000);
							o1.notifyAll();
							o1.wait();
							System.out.println("Thread2 after notify()");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		th2.start();
	}
}
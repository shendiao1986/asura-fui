import com.asura.fui.apps.sites.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cpkf.yyjd.tools.util.DateUtil;

public class PageCacheBatch {
	private static List<PageCacheTask> tasks = new ArrayList();
	private static List<Thread> threads = new ArrayList();

	static {
		for (int i = 0; i < 2; ++i) {
			Thread th = new Thread(getRunnable());
			threads.add(th);
			th.start();
		}
	}

	public static void addTask(PageCacheTask task) {
		synchronized (tasks) {
			tasks.add(task);
			System.out.println("wait task " + tasks.size());
		}
	}

	public static boolean isDone() {
		return (tasks.size() == 0);
	}

	public static void start() {
		for (Thread th : threads)
			if (!(th.isAlive()))
				th.start();
	}

	private static Runnable getRunnable() {
		return new Runnable() {
			public void run() {
				while (true) {
					PageCacheTask task = null;
					synchronized (PageCacheBatch.tasks) {
						if (PageCacheBatch.tasks.size() > 0) {
							task = (PageCacheTask) PageCacheBatch.tasks.get(0);
							PageCacheBatch.tasks.remove(task);
							System.out.println(
									"wait task " + PageCacheBatch.tasks.size() + " " + Thread.currentThread().getId()
											+ " at " + DateUtil.getDateAndTimeString(new Date()));
						}
					}
					if (task != null) {
						try {
							task.cache();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
					try {
						Thread.sleep(1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
	}
}

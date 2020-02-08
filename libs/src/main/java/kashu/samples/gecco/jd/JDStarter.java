package kashu.samples.gecco.jd;

import com.geccocrawler.gecco.GeccoEngine;

/**
 * @author 叶勤勇(卡叔)
 * @date 2020/02/08
 */
public class JDStarter {
	public static void main(String[] args) {
		GeccoEngine.create()
				//工程的包路径
				.classpath("kashu.samples.gecco.jd")
				//开始抓取的页面地址
				.start("https://jd.com/allSort.aspx")
				//开启几个爬虫线程
				.thread(1)
				//单个爬虫每次抓取完一个请求后的间隔时间
				.interval(2000)
				//循环抓取
//				.loop(true)
				//使用pc端userAgent
				.mobile(false)
				//开始运行
				.run();
	}
}

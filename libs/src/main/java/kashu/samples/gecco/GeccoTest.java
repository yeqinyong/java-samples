package kashu.samples.gecco;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 卡叔
 * @date 2020/02/08
 */
@Getter
@Setter
@Gecco(matchUrl = "https://github.com/{user}/{project}", pipelines = "consolePipeline")
public class GeccoTest implements HtmlBean {

	private static final long serialVersionUID = -7127412585200687225L;

	@RequestParameter("user")
	private String user;//url中的{user}值

	@RequestParameter("project")
	private String project;//url中的{project}值

	@Text
	@HtmlField(cssPath = ".repository-meta-content")
	private String title;//抽取页面中的title

	@Text
	@HtmlField(cssPath = ".pagehead-actions li:nth-child(2) .social-count")
	private int star;//抽取页面中的star

	@Text
	@HtmlField(cssPath = ".pagehead-actions li:nth-child(3) .social-count")
	private int fork;//抽取页面中的fork

	@Html
	@HtmlField(cssPath = ".entry-content")
	private String readme;//抽取页面中的readme

	public static void main(String[] args) {
		GeccoEngine.create()
				//工程的包路径
				.classpath("kashu.samples.gecco")
				//开始抓取的页面地址
				.start("https://github.com/xtuhcy/gecco")
				//开启几个爬虫线程
				.thread(1)
				//单个爬虫每次抓取完一个请求后的间隔时间
				.interval(2000)
				//循环抓取
				.loop(true)
				//使用pc端userAgent
				.mobile(false)
				//开始运行
				.run();
	}
}

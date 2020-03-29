package kashu.samples.gecco;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.dynamic.DynamicGecco;

/**
 * @author 卡叔
 * @date 2020/02/08
 * <p>
 * DynamicGecco的目的是在不定义SpiderBean的情况下实现爬取规则的运行时配置。其实现原理是采用字节码编程，
 * 动态生成SpiderBean，而且通过自定义的GeccoClassLoader实现了抓取规则的热部署。
 * 下面是一个简单Demo，更复杂的Demo可以参考com.geccocrawler.gecco.demo.dynamic下的例子。
 */
public class DynamicGeccoTest {
	public static void main(String[] args) {
		DynamicGecco.html()
				.gecco("https://github.com/{user}/{project}", "consolePipeline")
				.requestField("request").request().build()
				.stringField("user").requestParameter("user").build()
				.stringField("project").requestParameter().build()
				.stringField("title").csspath(".repository-meta-content").text(false).build()
				.intField("star").csspath(".pagehead-actions li:nth-child(2) .social-count").text(false).build()
				.intField("fork").csspath(".pagehead-actions li:nth-child(3) .social-count").text().build()
				.stringField("contributors").csspath("ul.numbers-summary > li:nth-child(4) > a").href().build()
				.register();

//开始抓取
		GeccoEngine.create()
				.classpath("com.geccocrawler.gecco.demo")
				.start("https://github.com/xtuhcy/gecco")
				.run();
	}
}

package kashu.samples.gecco.jd;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * @author 卡叔
 * @date 2020/02/08
 */
public class ProductBrief implements HtmlBean {

	private static final long serialVersionUID = -377053120283382723L;

	@Attr("data-sku")
	@HtmlField(cssPath=".j-sku-item")
	private String code;

	@Text
	@HtmlField(cssPath=".p-name> a > em")
	private String title;

	@Image({"data-lazy-img", "src"})
	@HtmlField(cssPath=".p-img > a > img")
	private String preview;

	// 这里需要说明一下@Href(click=true)的click属性，click属性形象的说明了，
	// 这个链接我们希望gecco继续点击抓取。对于增加了click=true的链接，
	// gecco会自动加入下载队列中，不需要在手动调用SchedulerContext.into()增加
	@Href(click=true)
	@HtmlField(cssPath=".p-name > a")
	private String detailUrl;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}

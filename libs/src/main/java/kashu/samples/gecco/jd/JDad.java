package kashu.samples.gecco.jd;

import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.JSONPath;
import com.geccocrawler.gecco.spider.JsonBean;

import java.util.List;

/**
 * @author 叶勤勇(卡叔)
 * @date 2020/02/08
 */
public class JDad implements JsonBean {

	private static final long serialVersionUID = 2250225801616402995L;

	@JSONPath("$.ads[0].ad")
	private String ad;

	@JSONPath("$.ads")
	private List<JSONObject> ads;

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public List<JSONObject> getAds() {
		return ads;
	}

	public void setAds(List<JSONObject> ads) {
		this.ads = ads;
	}

}

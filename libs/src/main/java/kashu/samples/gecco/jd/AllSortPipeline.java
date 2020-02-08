package kashu.samples.gecco.jd;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.SchedulerContext;
import com.geccocrawler.gecco.spider.HrefBean;

import java.util.List;

/**
 * @author 卡叔
 * @date 2020/02/08
 */
@PipelineName("allSortPipeline")
public class AllSortPipeline implements Pipeline<AllSort> {

	@Override
	public void process(AllSort allSort) {
		List<Category> categorys = allSort.getMobile();
		for(Category category : categorys) {
			List<HrefBean> hrefs = category.getCategorys();
			for(HrefBean href : hrefs) {
				// 为每个子链接增加"&delivery=1&page=1&JL=4_10_0&go=0"的目的是只抓取京东自营并且有货的商品
				String url = href.getUrl()+"&delivery=1&page=1&JL=4_10_0&go=0";
				HttpRequest currRequest = allSort.getRequest();
				SchedulerContext.into(currRequest.subRequest(url));
			}
		}
	}

}

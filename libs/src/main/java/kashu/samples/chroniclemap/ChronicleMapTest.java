package kashu.samples.chroniclemap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 卡叔
 * @date 2020/02/08
 */
public class ChronicleMapTest {
	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();

//        ChronicleMap<Long, Short> rateMap = ChronicleMap
//                .of(Long.class, Short.class)
//                .name("rate-limit-map")
//                .constantKeySizeBySample(1L)
//                .constantValueSizeBySample((short)1)
//                .entries(1000_0000)
//                .create();

		Map<Long, Short> rateMap = new HashMap<>(1000_0000);
		for (int i = 0; i < 1000_000_0; i++) {
			rateMap.put((long)i, (short)1);
		}

		long end = System.currentTimeMillis();
		System.out.println(end - start);
		Thread.sleep(1000_000);
	}
}

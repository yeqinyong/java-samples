package kashu.samples.koloboke;

import com.koloboke.compile.KolobokeMap;
import com.koloboke.compile.MethodForm;

import java.util.Map;

/**
 * @author 卡叔
 * @date 2020/02/08
 */

@KolobokeMap
abstract class MyMap<K, V> implements Map<K, V> {
	static <K, V> Map<K, V> withExpectedSize(int expectedSize) {
		return new KolobokeMyMap<K, V>(expectedSize);
	}
}

@KolobokeMap
abstract class MyIntLongMap implements Map<Long, Short> {
	static MyIntLongMap withExpectedSize(int expectedSize) {
		return new KolobokeMyIntLongMap(expectedSize);
	}

	abstract short put(long key, short value);

	abstract short get(long key);
}

@KolobokeMap
abstract class SynchronizedMap {
	public static SynchronizedMap withExpectedSize(int expectedSize) {
		return new KolobokeSynchronizedMap(expectedSize);
	}

	public final synchronized short get(long key) {
		return subGet(key);
	}

	public final synchronized short put(long key, short value) {
		return subPut(key, value);
	}

	public final synchronized int size() {
		return subSize();
	}

	@MethodForm("get")
	abstract short subGet(long key);

	@MethodForm("put")
	abstract short subPut(long key, short value);

	@MethodForm("size")
	abstract int subSize();
}

public class KolobokeTest {
	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
//        MyIntLongMap rateMap = MyIntLongMap.withExpectedSize(10);
		SynchronizedMap rateMap = SynchronizedMap.withExpectedSize(10000_000);
		System.out.println(rateMap.size());
		for (int i = 0; i < 1000_0000; i++) {
			rateMap.put((long)i, (short)1);
		}

		long end = System.currentTimeMillis();
		System.out.println(end - start);
		System.out.println(rateMap.size());
		Thread.sleep(1000_000);
	}
}

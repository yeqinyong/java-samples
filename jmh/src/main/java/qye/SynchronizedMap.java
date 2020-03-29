package qye;

import com.koloboke.compile.KolobokeMap;
import com.koloboke.compile.MethodForm;

import java.util.Map;

/**
 * Created by yeqinyong on 17/4/28.
 */
@KolobokeMap
public abstract class SynchronizedMap implements Map<Integer, Integer> {
	public static SynchronizedMap withExpectedSize(int expectedSize) {
		return new KolobokeSynchronizedMap(expectedSize);
	}

	public final synchronized int get(int key) {
		return subGet(key);
	}

	public final synchronized int put(int key, int value) {
		return subPut(key, value);
	}

	public final synchronized int size() {
		return subSize();
	}

	@MethodForm("get")
	abstract int subGet(int key);

	@MethodForm("put")
	abstract int subPut(int key, int value);

	@MethodForm("size")
	abstract int subSize();
}

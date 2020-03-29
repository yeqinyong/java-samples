package qye;

import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yeqinyong on 17/4/28.
 */
public class MemoryMeasure {
	public static void main(String[] args) throws IOException {
		System.out.println(VM.current().details());

		final int capacity = 1_000_000;

		// measure ConcurrentHashMap's memory
		ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>(capacity);
		for (int i = 0; i < capacity / 2; i++) {
			concurrentHashMap.put(i, i);
		}

		GraphLayout graphLayout = GraphLayout.parseInstance(concurrentHashMap);
		System.out.println(graphLayout.toFootprint());
		graphLayout.toImage("chm.jpg");

		// measure ConcurrentHashMap's memory
		SynchronizedMap synchronizedMap = SynchronizedMap.withExpectedSize(capacity);
		for (int i = 0; i < capacity / 2; i++) {
			synchronizedMap.put(i, i);
		}

		graphLayout = GraphLayout.parseInstance(synchronizedMap);
		System.out.println(graphLayout.toFootprint());
		graphLayout.toImage("syncm.jpg");
	}
}

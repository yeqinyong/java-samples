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

        final int initialCapacity = 1 << 10;
        final int size = 1000_000;

        // measure ConcurrentHashMap's memory
        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>(initialCapacity);
        for (int i = 0; i < size ; i++) {
            concurrentHashMap.put(i,i);
        }

        System.out.println("size = " + concurrentHashMap.size());
        GraphLayout graphLayout = GraphLayout.parseInstance(concurrentHashMap);
        System.out.println(graphLayout.toFootprint());

        // measure ConcurrentHashMap's memory
        SynchronizedMap synchronizedMap = SynchronizedMap.withExpectedSize(initialCapacity);
        for (int i = 0; i < size ; i++) {
            synchronizedMap.put(i,i);
        }

        System.out.println("size = " + synchronizedMap.size());
        graphLayout = GraphLayout.parseInstance(synchronizedMap);
        System.out.println(graphLayout.toFootprint());

    }
}

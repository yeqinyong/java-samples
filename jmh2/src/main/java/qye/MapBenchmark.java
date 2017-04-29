package qye;

import org.openjdk.jmh.annotations.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yeqinyong on 17/4/27.
 */
@State(Scope.Group)
public class MapBenchmark {

    private static AtomicInteger offset = new AtomicInteger(0);
    private static final int SIZE = (2 << 10);
    private static final int MASK = SIZE - 1;
    private static final int ITEMS = SIZE / 3;

    @State(Scope.Thread)
    public static class ThreadState {
        int index = offset.getAndAdd(SIZE / 16);
    }

    @Param({"ConcurrentHashMap", "KolobokeSynchronizedMap"})
    public String mapType;

    Map<Integer, Integer> map;

    @Setup(Level.Iteration)
    public void setup() throws Exception {
        if(mapType.equals("ConcurrentHashMap")) {
            map = new ConcurrentHashMap<>(SIZE);
            for (int i = 0; i < SIZE; i++) {
                map.put(i, i);
            }
        }else {
            map = SynchronizedMap.withExpectedSize(SIZE);
            for (int i = 0; i < SIZE; i++) {
                map.put(i, i);
            }
        }
    }

    @TearDown(Level.Iteration)
    public void tearDown() {

    }

    @Benchmark @Group("readOnly4") @GroupThreads(4) @BenchmarkMode(Mode.Throughput)
    public Integer readOnly4(ThreadState threadState) {
        return map.get(threadState.index++ & MASK);
    }

    @Benchmark @Group("writeOnly4") @GroupThreads(4) @BenchmarkMode(Mode.Throughput)
    public void writeOnly4(ThreadState threadState) {
        map.put(threadState.index++ & MASK, 0);
    }

    @Benchmark @Group("readOnly2") @GroupThreads(2) @BenchmarkMode(Mode.Throughput)
    public Integer readOnly(ThreadState threadState) {
        return map.get(threadState.index++ & MASK);
    }

    @Benchmark @Group("writeOnly2") @GroupThreads(2) @BenchmarkMode(Mode.Throughput)
    public void writeOnly2(ThreadState threadState) {
        map.put(threadState.index++ & MASK, 0);
    }

    @Benchmark @Group("readWrite13") @GroupThreads(3) @BenchmarkMode(Mode.Throughput)
    public Integer readWrite13_get(ThreadState threadState) {
        return map.get(threadState.index++ & MASK);
    }

    @Benchmark @Group("readWrite13") @GroupThreads(1) @BenchmarkMode(Mode.Throughput)
    public void readWrite13_put(ThreadState threadState) {
        map.put(threadState.index++ & MASK, 0);
    }

    @Benchmark @Group("readWrite22") @GroupThreads(2) @BenchmarkMode(Mode.Throughput)
    public Integer readWrite22_get(ThreadState threadState) {
        return map.get(threadState.index++ & MASK);
    }

    @Benchmark @Group("readWrite22") @GroupThreads(2) @BenchmarkMode(Mode.Throughput)
    public void readWrite22_put(ThreadState threadState) {
        map.put(threadState.index++ & MASK, 0);
    }
}

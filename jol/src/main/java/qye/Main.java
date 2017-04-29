package qye;

import com.google.common.collect.ImmutableMap;
import org.openjdk.jol.info.GraphLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yeqinyong on 17/4/27.
 */
public class Main {
    public static void main(String[] args) {
        Map<String, String> imap = ImmutableMap.of("k1", "v1", "k2", "v2");
        Map<String, String> treeMap = new TreeMap<>(imap);
        Map<String, String> hashMap = new HashMap<>(imap);

        GraphLayout gl= GraphLayout.parseInstance(imap);
        System.out.println("" + gl.toFootprint() + " " + gl.totalCount() + " " + gl.totalSize());
        gl= GraphLayout.parseInstance(treeMap);
        System.out.println("" + gl.toFootprint() + " " + gl.totalCount() + " " + gl.totalSize());
        gl= GraphLayout.parseInstance(hashMap);
        System.out.println("" + gl.toFootprint() + " " + gl.totalCount() + " " + gl.totalSize());
    }
}

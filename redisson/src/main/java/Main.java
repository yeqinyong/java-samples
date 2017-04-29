import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RScheduledExecutorService;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Created by yeqinyong on 17/3/16.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Create config object
        Config config = new Config();

// 2. Create Redisson instance
        RedissonClient redisson = Redisson.create(config);

// 3. Get object you need
//        RMap<MyKey, MyValue> map = redisson.getMap("myMap");

        RLock lock = redisson.getLock("myLock");

        RScheduledExecutorService executor = redisson.getExecutorService("myExecutorService");


    }
}

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.EventStream;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Event;

/**
 * Created by yeqinyong on 17/4/22.
 */
public class Main {
    public static void main(String[] args) throws DockerCertificateException, DockerException, InterruptedException {
        // Create a client based on DOCKER_HOST and DOCKER_CERT_PATH env vars
        final DockerClient docker = DefaultDockerClient.fromEnv().build();
        EventStream eventStream = docker.events(DockerClient.EventsParam.type(Event.Type.CONTAINER), DockerClient.EventsParam.since(System.currentTimeMillis()));
        for (int i = 0; i < 100; i++) {
            while(eventStream.hasNext()){
                Event e = eventStream.next();
                System.out.println(e.action() + e.time().toString() + e.id());
            }
            Thread.sleep(3000);
        }

//        eventStream.close();
//        docker.close();
    }
}

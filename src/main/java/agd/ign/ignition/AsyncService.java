package agd.ign.ignition;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

/**
 * @author aillusions
 */
@EnableAsync
@Service
public class AsyncService {

    @Async()
    public String myEndpoint() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "This is async";
    }
}

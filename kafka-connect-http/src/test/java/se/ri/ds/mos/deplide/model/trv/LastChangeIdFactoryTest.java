package se.ri.ds.mos.deplide.model.trv;

import com.github.castorm.kafka.connect.http.model.HttpResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LastChangeIdFactoryTest {

    @Test
    void extractWorks() throws IOException {
        HttpResponse httpResponse = buildResponse("src/test/resources/TRV_response.json");
        Optional<Long> optId = LastChangeIdFactory.extract(httpResponse);
        assertEquals(6970831285476393263l,optId.orElse(-1l));
    }
    @Test
    void extractWorksWithoutInfoElement() throws IOException {
        HttpResponse httpResponse = buildResponse("src/test/resources/TRV_response_without_lastchangeid.json");
        Optional<Long> optId = LastChangeIdFactory.extract(httpResponse);
        assertEquals(-17l,optId.orElse(-17l));
    }

    private HttpResponse buildResponse( String fileName ) throws IOException {
        Path path = Paths.get(fileName);
        byte[] body = Files.readAllBytes(path);
        return HttpResponse.builder().code(200).body(body).build();
    }
}
package se.ri.ds.mos.deplide.model.trv;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.castorm.kafka.connect.http.model.HttpResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class LastChangeIdFactory {
    private static final JsonPointer POINTER = JsonPointer.compile("/RESPONSE/RESULT/0/INFO/LASTCHANGEID");

    public static Optional<Long> extract(HttpResponse response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode neoJsonNode = mapper.readTree(response.getBody()).at(POINTER);
            if(!neoJsonNode.isMissingNode()) {
                return Optional.of(neoJsonNode.asLong());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

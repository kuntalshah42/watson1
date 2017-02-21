package pro.scislowski.sae;

import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author Maciej.Scislowski@gmail.com
 */
public class RestClient {

    public static final String MESSAGES_ENDPOINT = "https://shah-sentimental-and-emotion.mybluemix.net/messages";
    public static final String APPLICATION_JSON = "application/json";
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String BASIC = "Basic ";
    public static final String DELIMITER = ":";
    private String authHeader;
    private Gson gson = new Gson();

    public RestClient() {
        java.io.Console console = System.console();
        String username = console.readLine("Username: ");
        String password = new String(console.readPassword("Password: "));
        String auth = username + DELIMITER + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName(ISO_8859_1)));
        authHeader = BASIC + new String(encodedAuth);
    }

    public String send(String record) {
        Content response = null;
        try {
            response = Request.Post(MESSAGES_ENDPOINT)
                    .setHeader(HttpHeaders.AUTHORIZATION, authHeader)
                    .bodyString(gson.toJson(new MessageRequest(record)), ContentType.create(APPLICATION_JSON))
                    .execute().returnContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(gson.toJsonTree(response.asString()).getAsString(), MessageResponse.class).getResult().toString();
    }

}

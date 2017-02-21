package pro.scislowski.sae;

/**
 * @author Maciej.Scislowski@gmail.com
 */
public class MessageRequest {

    private String message;

    public MessageRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

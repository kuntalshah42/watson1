package pro.scislowski.sae;

import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentEmotion;

/**
 * @author Maciej.Scislowski@gmail.com
 */
public class MessageResponse {

    private MessageRequest messageRequest;
    private DocumentEmotion.Emotion result;

    public MessageResponse() {
    }

    public MessageResponse(MessageRequest messageRequest, DocumentEmotion.Emotion result) {
        this.messageRequest = messageRequest;
        this.result = result;
    }

    public MessageRequest getMessageRequest() {
        return messageRequest;
    }

    public void setMessageRequest(MessageRequest messageRequest) {
        this.messageRequest = messageRequest;
    }

    public DocumentEmotion.Emotion getResult() {
        return result;
    }

    public void setResult(DocumentEmotion.Emotion result) {
        this.result = result;
    }
}

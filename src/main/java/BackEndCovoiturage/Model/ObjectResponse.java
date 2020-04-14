package BackEndCovoiturage.Model;

import java.io.Serializable;

public class ObjectResponse implements Serializable {
    private String responseMessage;
    private String responseError;

    public ObjectResponse() {
    }

    public ObjectResponse(String responseMessage) {
        super();
        this.responseMessage = responseMessage;
    }

    public ObjectResponse(String responseMessage, String responseError) {
        this.responseMessage = responseMessage;
        this.responseError = responseError;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseError() {
        return responseError;
    }

    public void setResponseError(String responseError) {
        this.responseError = responseError;
    }
}

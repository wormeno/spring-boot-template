package template.generic.response;

public class ResponseModelBuilder<T> {

//    private Object T;
    private String responseCode = "";
    private String responseMessage = "";

/*    public ResponseModelBuilder(Object T){
        this.T = T;
    }*/

    public ResponseModelBuilder() {
    }

    public ResponseModelBuilder<T> setResponseCode(String responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public ResponseModelBuilder<T> setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
        return this;
    }

    public ResponseModel<T> build() {
        ResponseModel<T> newInstance = new ResponseModel<T>();
        newInstance.setResponseCode(responseCode);
        newInstance.setResponseMessage(responseMessage);
        return newInstance;
    }
}

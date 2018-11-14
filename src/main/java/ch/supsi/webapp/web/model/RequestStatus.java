package ch.supsi.webapp.web.model;

public class RequestStatus {
    private boolean success;
    public RequestStatus(boolean success){
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}

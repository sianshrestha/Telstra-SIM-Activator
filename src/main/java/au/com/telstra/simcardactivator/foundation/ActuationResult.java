package au.com.telstra.simcardactivator.foundation;

public class ActuationResult{
    private boolean success;

    public ActuationResult() {
        // Required for frameworks like JPA/Jackson that use reflection
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

package learn.pawpals.domain;

import java.util.ArrayList;
import java.util.List;

public class result<T> {

    private final ArrayList<String> messages = new ArrayList<>();
    private T payload;
    private resultType resultType = learn.pawpals.domain.resultType.SUCCESS;

    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }

    public void addErrorMessage(String message, resultType resultType) {
        messages.add(message);
        this.resultType = resultType;
    }

    public void addErrorMessage(String format, resultType resultType, Object... args) {
        messages.add(String.format(format, args));
        this.resultType = resultType;
    }

    public boolean isSuccess() {
        return resultType == resultType.SUCCESS;
    }

    public resultType getResultType() {
        return this.resultType;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public void setSolarPanel(T payload) {

        this.payload = payload;
    }
}

package br.com.nt.voteSystem.base;

import java.util.List;

public class BaseDto<T> {
    private T result;
    private List<ErrorDto> errors;
    private StatusDto status;

    public BaseDto(T result, List<ErrorDto> errors, StatusDto status) {
        this.result = result;
        this.errors = errors;
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public List<ErrorDto> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDto> errors) {
        this.errors = errors;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

    public void addError(ErrorDto error) {
        errors.add(error);
    }
}

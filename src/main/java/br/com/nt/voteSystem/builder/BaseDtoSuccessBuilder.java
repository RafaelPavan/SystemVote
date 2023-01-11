package br.com.nt.voteSystem.builder;

import br.com.nt.voteSystem.base.BaseDto;
import br.com.nt.voteSystem.base.StatusDto;
import org.springframework.http.HttpStatus;

public class BaseDtoSuccessBuilder<T> {

    private BaseDto<T> result;

    public BaseDtoSuccessBuilder(T result, HttpStatus status) {
        StatusDto statusDto = new StatusDto(status.value(), status.name());
        this.result = new BaseDto<>(result, null, statusDto);
    }

    public BaseDto get() {
        return result;
    }

}

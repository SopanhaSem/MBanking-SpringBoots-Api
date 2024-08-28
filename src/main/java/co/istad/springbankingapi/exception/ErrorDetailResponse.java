package co.istad.springbankingapi.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDetailResponse<T> {
    private String code;
    private T description;
}

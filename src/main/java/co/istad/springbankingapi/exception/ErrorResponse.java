package co.istad.springbankingapi.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse<T> {
    private T error;
}

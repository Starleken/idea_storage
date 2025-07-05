package ru.leafall.mainstarter.exception;


import java.util.Objects;

public class ErrorDto {
    private Integer statusCode;
    private String message;


    public static class ErrorDtoBuilder {
        private Integer statusCode;
        private String message;

        public ErrorDtoBuilder statusCode(Integer statusCode) {
            ErrorDtoBuilder.this.statusCode = statusCode;
            return this;
        }

        public ErrorDtoBuilder message(String message) {
            ErrorDtoBuilder.this.message = message;
            return this;
        }

        public ErrorDto build() {
            var errorDto = new ErrorDto();
            errorDto.setStatusCode(ErrorDtoBuilder.this.statusCode);
            errorDto.setMessage(ErrorDtoBuilder.this.message);
            return new ErrorDto();
        }
    }

    public static ErrorDtoBuilder builder() {
        return new ErrorDtoBuilder();
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorDto{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDto errorDto = (ErrorDto) o;
        return Objects.equals(statusCode, errorDto.statusCode) && Objects.equals(message, errorDto.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCode, message);
    }
}

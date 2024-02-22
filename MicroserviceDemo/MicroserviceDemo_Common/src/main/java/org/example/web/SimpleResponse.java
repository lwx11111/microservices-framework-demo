package org.example.web;

/**
 * @Author: lwx
 */
public class SimpleResponse {
    private Integer code = 200;
    private String message = "成功";
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public static class SimpleResponseBuilder {
        private Integer code = 200;
        private String message = "成功";
        private Object data;


        public SimpleResponseBuilder withCode(Integer code) {
            this.code = code;
            return this;
        }

        public SimpleResponseBuilder withMessage(String message) {
            this.message = message;
            return this;
        }


        public SimpleResponseBuilder withData(Object data) {
            this.data = data;
            return this;
        }

        public SimpleResponse build() {
            SimpleResponse simpleResponse = new SimpleResponse();
            simpleResponse.setCode(code);
            simpleResponse.setMessage(message);
            simpleResponse.setData(data);
            return simpleResponse;
        }
    }

    @Override
    public String toString() {
        return "SimpleResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

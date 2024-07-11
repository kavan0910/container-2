package com.kavankumar.ms2.response;

import java.util.Optional;

public class Response {
    String file;
    Optional<Integer> sum;
    Optional<String> error;
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getSum() {
        return sum.orElse(0);
    }

    public void setSum(int sum) {
        this.sum = Optional.of(sum);
    }

    public String getError() {
        return error.orElse("");
    }

    public void setError(String error) {
        this.error = Optional.of(error);
    }


}

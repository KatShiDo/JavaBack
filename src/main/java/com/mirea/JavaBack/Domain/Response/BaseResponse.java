package com.mirea.JavaBack.Domain.Response;

import com.mirea.JavaBack.Domain.Enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseResponse<T> {

    public String description;

    public T data;

    public StatusCode statusCode;
}

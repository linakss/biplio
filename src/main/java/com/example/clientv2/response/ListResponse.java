package com.example.clientv2.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListResponse<T> extends BaseResponse{
    private List<T> dataList;
    public ListResponse(boolean success, String message, List<T> dataList){
        super(success,message);
        this.dataList = dataList;
    }
}

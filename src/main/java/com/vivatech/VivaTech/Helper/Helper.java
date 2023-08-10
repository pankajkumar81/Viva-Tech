package com.vivatech.VivaTech.Helper;

import com.vivatech.VivaTech.Dto.PageableResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Helper<U,V> {

    public static <U,V> PageableResponse<V> getPageableResponse(List<U> page, Class<V> type){
        List<U> entity=page.stream().toList();
        List<V> dtoList=entity.stream().map(object-> new ModelMapper().map(object,type)).collect(Collectors.toList());

        PageableResponse<V> response=new PageableResponse<>();
        response.setContent(dtoList);

        return response;
    }
}

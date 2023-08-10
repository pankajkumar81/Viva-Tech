package com.vivatech.VivaTech.Dto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableResponse <T> {

    private List<T> content;

}

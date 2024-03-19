package com.smtaps.demoApi.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopperDTO {
    private String shopperId;
    private List<ShelfItemDTO> shelf;
}

package com.ghostappi.backend.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {


    private String comercialName;

    private BigDecimal price;

    private Short stock;

    private Integer servingSize;

    private String unitServingSize;

    private Short servings;

    private String productRecomendation;

    private String imgProductPath;

    private Date caducity;

    private String flavor;

    private String lote;

    private String description;

    private String presentation;

    private Short netContent;

    private String unitNetContent;

    private Integer idBrand;

    private Integer idCategory;

    private Integer idAdministrationVia;

    // private List<NutrientProductDTO> nutrientProducts;
}

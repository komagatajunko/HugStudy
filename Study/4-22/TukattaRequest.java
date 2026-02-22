package com.example.tukatta.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 支出情報 リクエストデータ
 */
@Data
public class TukattaRequest implements Serializable{
    /**
     * 日付
     */
    @NotNull(message = "日付を入力してください")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate payDate;
    
    /**
     * 支払金額
     */
    @NotNull(message = "金額を入力してください")
    private Integer amount;
        
    /**
     * 支払方法
     */
    @NotEmpty(message = "支払方法を入力してください")
    @Size(max = 20, message = "支払方法は20桁以内で入力してください")
    private String method;
    /**
     * 支払先
     */
    @NotEmpty(message = "支払先を入力してください")
    @Size(max = 20, message = "支払先は20桁以内で入力してください")
    private String shop;
    
    
}

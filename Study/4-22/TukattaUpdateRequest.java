package com.example.tukatta.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支出情報更新リクエストデータ
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class TukattaUpdateRequest  extends TukattaRequest implements Serializable{
    /**
     * ID
     */
    @NotNull
    private Integer id;
}

package com.example.tukatta.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
* 支出情報 Entity
*/
@Data
@Entity
@Table(name = "sisyutu", schema = "public")
public class TukattaEntity {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private Integer id;

    /** 日付 */
    @Column(name = "pay_date")
    private LocalDate payDate;
    
    /** 支払先 */
    @Column(name = "pay_shop")
    private String shop;
    
    /** 支払方法 */
    @Column(name = "pay_method")
    private String method;
    
    /** 支払金額 */
    @Column(name = "pay_amount")
    private Integer amount;
}

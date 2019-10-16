package com.lovomall.csc.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;

/**
 * @author GYM
 * @version 1.0
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_priceReview")
public class PriceReview {
    @Id
    @GenericGenerator(name = "pruuid",strategy = "uuid")
    @GeneratedValue(generator = "pruuid")
    @Column(length = 32,name = "priceReview_id")
    private  String priceReviewId;
    //招标号
    @Column(name = "priceReview_bid",length = 48,nullable = false)
    private String bidNumber;
    //供应商名称
    @Column(name = "priceReview_supplierName",length = 48,nullable = false)
    private String supplierName;
    //商品名称
    @Column(name = "priceReview_productName",length = 48,nullable = false)
    private String productName;
    //商品报价
    @Column(name = "priceReview_allMoney",length = 48,nullable = false)
    private int allMoney;
    //商品数量
    @Column(name = "priceReview_productNum",length = 48,nullable = false)
    private int productNum;
    //是否审核
    @Column(name = "priceReview_status",length = 48,nullable = false)
    private String priceReviewStatus = "未审核";
    @JsonIgnore
    @OneToMany(mappedBy = "priceReview")
    List<PriceReviewResult> listPriceReviewResult;

    //供应商唯一标识
    @Column(name = "priceReview_supplierLogo",length = 48,nullable = false)
    private String supplierLogo;

}

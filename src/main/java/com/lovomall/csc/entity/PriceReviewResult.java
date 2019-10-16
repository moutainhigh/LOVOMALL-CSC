package com.lovomall.csc.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
/**
 * @author GYM
 * @version 1.0
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_priceReviewResult")
public class PriceReviewResult {
    @Id
    @GenericGenerator(name = "prruuid",strategy = "uuid")
    @GeneratedValue(generator = "prruuid")
    @Column(length = 32,name = "priceReviewResult_id")
    private  String priceReviewResultId;
    //审核状态
    @Column(name = "priceReviewResult_status",length = 48,nullable = false)
    private String priceReviewResultStatus;
    //审核意见
    @Column(name = "priceReviewResult_opinion",length = 48)
    private String priceReviewOpinion;
    //审核时间
    @Column(name = "priceReviewResult_time",length = 48,columnDefinition = "TIMESTAMP")
    private String priceReviewTime;
    //审核人
    @Column(name = "priceReviewResult_people",length = 48)
    private String priceReviewPeople;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "priceReview_id")
    private  PriceReview priceReview;
}

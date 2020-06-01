package vip.bblog.cunadmin.modules.es;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author <a href="1396513066@qq.com">Yu Yong</a>
 * @version 1.0
 * @date 2019年11月05日 16:29
 * @desc OrderVO 订单记录基本信息VO
 */
@Data
@Accessors(chain = true)
@Document(indexName = "biz_order")
public class OrderVO implements Serializable {

    private static final long serialVersionUID = -6798572802213335948L;

    @ApiModelProperty(value = "id")
    @Id
    private Integer id;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "发货用户id")
    private Integer webUserId;

    @ApiModelProperty(value = "司机id")
    private Integer driverId;

    @ApiModelProperty(value = "司机姓名")
    private String driverName;

    @ApiModelProperty(value = "司机电话")
    private String driverPhone;

    @ApiModelProperty(value = "服务机构id，如果司机是属于服务机构的，则此必填")
    private Integer serviceOrgId;

    @ApiModelProperty(value = "服务机构名称")
    private String serviceName;

    @ApiModelProperty(value = "服务机构电话")
    private String servicePhone;

    @ApiModelProperty(value = "发货人姓名")
    private String senderName;

    @ApiModelProperty(value = "发货人手机号")
    private String senderPhone;

    @ApiModelProperty(value = "发货人地址")
    private String senderAddress;

    @ApiModelProperty(value = "发货人经纬度,填入规则：X,Y，采用,分隔，例如117.500244, 40.417801小数点后不得超过6位")
    @GeoPointField
    private GeoPoint senderGeo;

    @ApiModelProperty(value = "发货人省编码")
    private String senderProvinceCode;

    @ApiModelProperty(value = "发货人城市编码")
    private String senderCityCode;

    @ApiModelProperty(value = "发货人行政区编码")
    private String senderAdCode;

    @ApiModelProperty(value = "发货人乡镇街道编码")
    private String senderTownCode;

    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;

    @ApiModelProperty(value = "收货人手机号")
    private String receiverPhone;

    @ApiModelProperty(value = "收货人地址")
    private String receiverAddress;

    @ApiModelProperty(value = "收货人经纬度,填入规则：X,Y，采用,分隔，例如117.500244, 40.417801小数点后不得超过6位")
    @GeoPointField
    private GeoPoint receiverGeo;

    @ApiModelProperty(value = "收货人省编码")
    private String receiverProvinceCode;

    @ApiModelProperty(value = "收货人城市编码")
    private String receiverCityCode;

    @ApiModelProperty(value = "收货人行政区编码")
    private String receiverAdCode;

    @ApiModelProperty(value = "收货人乡镇街道编码")
    private String receiverTownCode;

    @ApiModelProperty(value = "总距离数 单位：km")
    private Double distance;

    @ApiModelProperty(value = "车辆类型要求")
    private String carType;

    @ApiModelProperty(value = "车辆长度要求")
    private Double carLengthDemand;

    @ApiModelProperty(value = "车辆的其他要求")
    private String carOtherDemand;

    @ApiModelProperty(value = "总运费")
    private Double postFee;

    @ApiModelProperty(value = "物流企业/司机运费待设置，1：是，0：否")
    private Boolean postFeeSetting;

    @ApiModelProperty(value = "运费是否支付")
    private Boolean postFeePay;

    @ApiModelProperty(value = "运费支付时间")
    private LocalDateTime postFeePayTime;

    @ApiModelProperty(value = "预付费金额")
    private Double prepaidFee;

    @ApiModelProperty(value = "预付费是否支付，1：是，0：否")
    private Boolean prepaidFeePay;

    @ApiModelProperty(value = "预付费时间")
    private LocalDateTime prepaidFeePayTime;

    @ApiModelProperty(value = "代收款金额")
    private Double codMoney;

    @ApiModelProperty(value = "付款方式，1：发件人付款，2：收件人付款")
    private Integer postFeeType;

    @ApiModelProperty(value = "发货类型，1：整车，2：零担")
    private Integer deliveryType;

    @ApiModelProperty(value = "上门取件时间")
    private LocalDateTime appointTime;

    @ApiModelProperty(value = "订单状态，0：已经取消，1：待接单，2：已接单（待确认），3：待装货，4：运输中，5：已完成，6：接单方取消，11：订单异常")
    private Integer status;

    @ApiModelProperty(value = "运单状态(<b style='color:red;'>用status判断</b>)，0:司机待接单，1：待装货，3：运输中，5：已完成，11：异常运单（如果用户发车前要求取消订单或者出现其他原因，都算异常运单）")
    private Integer wayStatus;

    @ApiModelProperty(value = "用户订单的其他说明")
    private String otherDesc;

    @ApiModelProperty(value = "订单取消原因描述")
    private String cancelDesc;

    @ApiModelProperty(value = "订单异常说明")
    private String exceptionDesc;

    @ApiModelProperty(value = "货物类型")
    private String goodsType;

    @ApiModelProperty(value = "打包方式")
    private String packageType;

    @ApiModelProperty(value = "货物重量，单位：kg")
    private Double packageWeight;

    @ApiModelProperty(value = "货物体积，单位：平方米")
    private Double packageSquareMetre;

    @ApiModelProperty(value = "货物数量，单位：件")
    private Integer packageNum;

    @ApiModelProperty(value = "取货方式，1：自送至物流点，2：上门取货")
    private Integer pickUpType;

    @ApiModelProperty(value = "收获方式，1：自提，2：配送上门")
    private Integer receiptType;

    @ApiModelProperty(value = "邀请单，会展示到司机端的市场里面")
    private Boolean isInvitation;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}

//
//  UsedCarInfo.h
//  Entity
//
//  Created by 舒联勇 on 14/6/7.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface UsedCarInfo : NSObject

/**
 *  @brief 产品Id
 */
@property (nonatomic) NSInteger productId;

/**
 *  @brief 对应数据库表model的model_id字段
 */
@property (nonatomic) NSInteger modelId            ;
/**
 *  @brief 款式名，2013 款 35 TFSI 自动 舒适型
 */
@property (nonatomic,strong) NSString *modelDisplayName;

/**
 *  @brief 对应数据库表series的series_id字段
 */
@property (nonatomic) NSInteger seriesId           ;
/**
 *  @brief 系列名，奥迪A4L
 */
@property (nonatomic,strong) NSString *seriesName;

/**
 *  @brief 对应数据库表brand的brand_id字段
 */
@property (nonatomic) NSInteger brandId            ;

/**
 *  @brief 品牌名
 */
@property (nonatomic,strong) NSString *brandName;

/**
 *  @brief 产品名，如A 奥迪 奥迪A4L 2013 款 35 TFSI 自动 舒适型
 */
@property (nonatomic,strong) NSString *productName;

/**
 *  @brief 上传图片后生成的imageId
 */
@property (nonatomic,strong) NSArray *imageIds           ;

/**
 *  @brief 上传行驶证图片后生成的imageId
 */
@property (nonatomic) NSInteger licenseImageId;
/**
 *  @brief 上传认证图片后生成的imageId
 */
@property (nonatomic,strong) NSString *certificateImageId ;

/**
 *  @brief 第一次购买日期
 */
@property (nonatomic,strong) NSString *purchaseDate       ;
/**
 *  @brief 里程数
 */
@property (nonatomic) UInt64 odometer           ;
/**
 *  @brief 价格
 */
@property (nonatomic) UInt64 listPrice          ;
/**
 *  @brief 价格类型，只能是‘可议价’或‘一口价’
 */
@property (nonatomic,strong) NSString *priceType          ;
/**
 *  @brief vin码，17位字符
 */
@property (nonatomic,strong) NSString *carVin             ;
/**
 *  @brief 联系人姓名
 */
@property (nonatomic,strong) NSString *carContact         ;
/**
 *  @brief 联系人电话号码
 */
@property (nonatomic,strong) NSString *contactPhone       ;

/**
 *  @brief 状态
 */
@property (nonatomic) NSInteger status;

/**
 *  @brief 更新时间
 */
@property (nonatomic,strong) NSString *updateTime;

/**
 *  @brief 省Id
 */
@property (nonatomic) NSInteger provinceId         ;
/**
 *  @brief 省
 */
@property (nonatomic,strong) NSString *province;

/**
 *  @brief 城市Id
 */
@property (nonatomic) NSInteger cityId             ;
/**
 *  @brief 城市名
 */
@property (nonatomic,strong) NSString *city;

/**
 *  @brief countyId
 */
@property (nonatomic) NSInteger countyId           ;
/**
 *  @brief 区，锦江区
 */
@property (nonatomic,strong) NSString *county;

/**
 *  @brief 街道名称
 */
@property (nonatomic,strong) NSString *street             ;

@end

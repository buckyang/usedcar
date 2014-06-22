//
//  Brand.h
//  Entity
//
//  Created by 舒联勇 on 14/6/7.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <Foundation/Foundation.h>

/**
 *  @brief 品牌车名，如宝马，奔驰
 */
@interface Brand : NSObject

/**
 *  @brief 车名Id
 */
@property (nonatomic) NSInteger brandId          ;
/**
 *  @brief 车名的 A,B
 */
@property (nonatomic,strong) NSString *brandInitial     ;
/**
 *  @brief 品牌名
 */
@property (nonatomic,strong) NSString *brandName        ;
/**
 *  @brief 品牌介绍
 */
@property (nonatomic,strong) NSString *brandDescription ;


@end

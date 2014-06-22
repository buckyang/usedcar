//
//  Series.h
//  Entity
//
//  Created by 舒联勇 on 14/6/7.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <Foundation/Foundation.h>

/**
 *  @brief 车系
 */
@interface Series : NSObject

/**
 *  @brief 车系Id
 */
@property (nonatomic) NSInteger seriesId   ;
/**
 *  @brief 车系名
 */
@property (nonatomic,strong) NSString *seriesName ;
/**
 *  @brief 车系类型
 */
@property (nonatomic,strong) NSString *type       ;
/**
 *  @brief 品牌Id
 */
@property (nonatomic) NSInteger brandId    ;

@end

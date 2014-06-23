//
//  Model.h
//  Entity
//
//  Created by 舒联勇 on 14/6/7.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <Foundation/Foundation.h>

/**
 *  @brief 原型
 */
@interface Model : NSObject

@property (nonatomic) NSInteger modelId          ;
@property (nonatomic,strong) NSString *subSeries        ;
@property (nonatomic,strong) NSString *modelName        ;
@property (nonatomic,strong) NSString *displacement      ;
@property (nonatomic,strong) NSString *transmissionType ;
@property (nonatomic) NSInteger launchYear       ;
@property (nonatomic,strong) NSString *level             ;
@property (nonatomic,strong) NSString *country           ;
@property (nonatomic) NSInteger seriesId         ;

@end

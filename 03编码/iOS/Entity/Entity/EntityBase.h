//
//  Entity.h
//  Entity
//
//  Created by 舒联勇 on 14-3-31.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <Foundation/Foundation.h>

/**
 *  @brief 实体基类
 */
@interface EntityBase : NSObject

@property (nonatomic) BOOL executionResult;
@property (nonatomic,strong) NSString *message;
@property (nonatomic,strong) NSString *code;

@end

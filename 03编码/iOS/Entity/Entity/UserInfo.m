//
//  UserInfo.m
//  Entity
//
//  Created by 舒联勇 on 14-4-1.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "UserInfo.h"

@implementation UserInfo

+ (instancetype)shareInstance
{
    static UserInfo *instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = [[UserInfo alloc] init];
    });
    
    return instance;
}

@end

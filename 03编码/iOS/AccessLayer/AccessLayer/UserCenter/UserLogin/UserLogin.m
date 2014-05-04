//
//  UserLogin.m
//  AccessLayer
//
//  Created by 舒联勇 on 14-4-1.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "UserLogin.h"

@implementation UserLogin

- (void)userLoginWithUserInfo:(UserInfo *)aUser withCallback:(HttpCallback)aCallback
{
    NSMutableDictionary *parameters = [NSMutableDictionary dictionary];
    parameters[@"phoneOrEmail"] = aUser.userName;
    parameters[@"password"] = aUser.password;
    
    [self accessURL:@"login.json" withParameters:parameters withCallback:^(id info, HTTPAccessState isSuccess) {
        if (isSuccess==HTTPAccessStateSuccess) {
            [aUser reflectDataFromOtherObject:info];
            aCallback(aUser,HTTPAccessStateSuccess);
        }
        else
        {
            aCallback(info,isSuccess);
        }
    }];
}

@end

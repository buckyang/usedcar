//
//  UserRegister.m
//  AccessLayer
//
//  Created by 舒联勇 on 14-4-23.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "UserRegister.h"

@implementation UserRegister

- (void)obtainCodeWithUserInfo:(UserInfo*)aUser withCallback:(HttpCallback)aCallback
{
    NSMutableDictionary *parameters = [NSMutableDictionary dictionary];
    parameters[@"phoneNumber"] = aUser.phone;
    [self accessURL:@"account/obtainCode.json" withParameters:parameters withCallback:^(id info, HTTPAccessState isSuccess) {
        if (isSuccess==HTTPAccessStateSuccess) {
            aUser.phoneVerifyCode = info[@"code"];
            aCallback(aUser,HTTPAccessStateSuccess);
        }
        else
            aCallback(info,isSuccess);
    }];
}

- (void)registerIndividualUser:(UserInfo*)aUser withCallback:(HttpCallback)aCallback
{    
    NSMutableDictionary *parameters = [NSMutableDictionary dictionary];
    parameters[@"phone"] = aUser.phone;
    parameters[@"phoneVerifyCode"] = aUser.phoneVerifyCode;
    parameters[@"password"] = aUser.password;
    parameters[@"repassword"] = aUser.repassword;
    parameters[@"accountType"] = @1;
    parameters[@"acceptTerm"] = @YES;
    
    [self accessURL:@"signon.json"
     withParameters:parameters withCallback:^(id info, HTTPAccessState isSuccess) {
         if (isSuccess == HTTPAccessStateSuccess) {
             UserInfo *user = [[UserInfo alloc] initWithDictionary:info];
             aUser.accessToken = user.accessToken;
             aCallback(aUser,HTTPAccessStateSuccess);
         }
         else
             aCallback(info,isSuccess);
     }];
}

- (void)reselleSignonUser:(UserInfo*)aUser withCallback:(HttpCallback)aCallback
{
    NSMutableDictionary *parameters = [NSMutableDictionary dictionary];
    parameters[@"phone"] = aUser.phone;
    parameters[@"phoneVerifyCode"] = aUser.phoneVerifyCode;
    parameters[@"resellerName"] = aUser.resellerName;
    parameters[@"resellerType"] = @(aUser.resellerType);
    parameters[@"adress"]=aUser.adress;
    parameters[@"password"] = aUser.password;
    parameters[@"repassword"] = aUser.repassword;
    parameters[@"accountType"] = @2;//用户注册类型，必须为2
    parameters[@"acceptTerm"] = @YES;
    
    [self accessURL:@"resellerSignon.json" withParameters:parameters withCallback:^(id info, HTTPAccessState isSuccess) {
        if (isSuccess==HTTPAccessStateSuccess) {
            UserInfo *user = [[UserInfo alloc] initWithDictionary:info];
            aUser.accessToken = user.accessToken;
            aCallback(aUser,HTTPAccessStateSuccess);
        }
        else
        {
            aCallback(info,isSuccess);
        }
    }];
    
}

@end

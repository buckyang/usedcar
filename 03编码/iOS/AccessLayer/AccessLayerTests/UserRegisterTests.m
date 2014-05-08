//
//  UserRegisterTests.m
//  AccessLayer
//
//  Created by 舒联勇 on 14-4-23.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <XCTest/XCTest.h>
#import "UserRegister.h"

@interface UserRegisterTests : XCTestCase

@end

@implementation UserRegisterTests

- (void)setUp
{
    [super setUp];
    // Put setup code here. This method is called before the invocation of each test method in the class.
}

- (void)tearDown
{
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}
//
//- (void)testExample
//{
//    XCTFail(@"No implementation for \"%s\"", __PRETTY_FUNCTION__);
//}

- (void)testObtainCodeWithUserInfo
{
    UserRegister *access = [UserRegister createInstance];
    UserInfo *user = [UserInfo createInstance];
    user.phone = @"13540780694";
    
    [access obtainCodeWithUserInfo:user withCallback:^(id info, HTTPAccessState isSuccess) {
        
        CFRunLoopStop(CFRunLoopGetCurrent());
        XCTAssertEqual(isSuccess, HTTPAccessStateSuccess, @"请求失败");
        XCTAssertNotNil(user.phoneVerifyCode,  @"未能得到phone");
        
    }];
    
    CFRunLoopRun();
}

@end

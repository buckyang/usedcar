//
//  AccessLayerTests.m
//  AccessLayerTests
//
//  Created by 舒联勇 on 14-3-31.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import <XCTest/XCTest.h>

#import "UserLogin.h"

@interface AccessLayerTests : XCTestCase

@end

@implementation AccessLayerTests

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

- (void)testExample
{
//    XCTFail(@"No implementation for \"%s\"", __PRETTY_FUNCTION__);
}

- (void)testUserLogin
{
    UserLogin *login = [UserLogin createInstance];
    UserInfo *user = [UserInfo createInstance];
    user.userName = @"sly";
    user.password = @"sly";
    [login userLoginWithUserInfo:user withCallback:^(id info, HTTPAccessState isSuccess) {
        CFRunLoopStop(CFRunLoopGetCurrent());
    }];
    CFRunLoopRun();
}

@end

//
//  CornerButton.m
//  UserCar
//
//  Created by 舒联勇 on 14-5-6.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "CornerButton.h"

@implementation CornerButton

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        [self configCornerRadius];
    }
    return self;
}

- (instancetype)initWithCoder:(NSCoder *)coder
{
    self = [super initWithCoder:coder];
    if (self) {
        [self configCornerRadius];
    }
    return self;
}

/**
 *  @brief 设置圆角
 */
- (void)configCornerRadius
{
    self.userInteractionEnabled = YES;
    self.layer.cornerRadius = 4;
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect
{
    // Drawing code
}
*/

@end

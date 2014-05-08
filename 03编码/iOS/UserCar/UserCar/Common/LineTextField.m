//
//  LineTextField.m
//  UserCar
//
//  Created by 舒联勇 on 14-4-29.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "LineTextField.h"
#import <QuartzCore/QuartzCore.h>

@interface LineTextField ()

@property (nonatomic,strong) CALayer *bottomBorder;

@end

@implementation LineTextField

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
    }
    return self;
}


- (void)drawRect:(CGRect)rect
{
    [super drawRect:rect];
    if (self.bottomBorder) {
        [self.bottomBorder removeFromSuperlayer];
    }
    self.bottomBorder = [CALayer layer];
    float height=self.frame.size.height-1.0f;
    float width=self.frame.size.width;
    self.bottomBorder.frame = CGRectMake(0.0f, height, width, 1.0f);
    self.bottomBorder.backgroundColor = [UIColor colorWithWhite:0.94f alpha:1.0f].CGColor;
    [self.layer addSublayer:self.bottomBorder];

}

@end

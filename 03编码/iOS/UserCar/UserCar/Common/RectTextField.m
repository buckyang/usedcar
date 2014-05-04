//
//  RectTextField.m
//  UserCar
//
//  Created by 舒联勇 on 14-4-30.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "RectTextField.h"
#import <QuartzCore/QuartzCore.h>

@implementation RectTextField

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
    }
    return self;
}

- (void)setHasRect:(BOOL)hasRect
{
    _hasRect = hasRect;
    self.enabled = hasRect;
    [self setNeedsDisplay];
}

- (void)awakeFromNib
{
    [super awakeFromNib];
    UIView *leftView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 5, 5)];
    self.leftViewMode = UITextFieldViewModeAlways;
    self.leftView = leftView;
    self.layer.cornerRadius = 4;
}


// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect
{
    [super drawRect:rect];
    
    if (self.hasRect) {
        self.layer.borderWidth = 1;
        self.layer.borderColor = [UIColor colorWithWhite:0.9 alpha:1].CGColor;
    }
    else
    {
        self.layer.borderWidth = 0;
    }
}


@end

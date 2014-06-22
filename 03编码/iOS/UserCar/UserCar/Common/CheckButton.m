//
//  CheckButton.m
//  UserCar
//
//  Created by 舒联勇 on 14-4-22.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "CheckButton.h"

@implementation CheckButton

/**
 *  @brief 配置选择按扭事件与图片设置
 */
- (void)configButtonEvent
{
    [self setImage:[UIImage imageNamed:@"imgUnSelected.png"] forState:UIControlStateNormal];
    [self setImage:[UIImage imageNamed:@"imgSelected.png"] forState:UIControlStateSelected];
    [self addTarget:self action:@selector(click_button:) forControlEvents:UIControlEventTouchUpInside];
}

- (void)click_button:(id)sender
{
    self.selected = !self.selected;
}

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        [self configButtonEvent];
    }
    return self;
}

- (void)awakeFromNib
{
    [super awakeFromNib];
    [self configButtonEvent];
}

@end

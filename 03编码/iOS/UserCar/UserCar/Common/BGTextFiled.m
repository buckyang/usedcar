//
//  ImageBorderTextFiled.m
//  UserCar
//
//  Created by 舒联勇 on 14-6-1.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "BGTextFiled.h"
#import <CoreText/CoreText.h>

@implementation BGTextFiled

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
    }
    return self;
}

- (void)setBackground:(UIImage *)background
{
    UIImage *temp = [background stretchableImageWithLeftCapWidth:15 topCapHeight:10];
    [super setBackground:temp];
    
    UIView *leftView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 10, 10)];
    self.leftView = leftView;
    self.leftViewMode = UITextFieldViewModeAlways;
}

- (void)awakeFromNib
{
    [super awakeFromNib];
    UIImage *temp = [self background];
    [self setBackground:temp];
    
    
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

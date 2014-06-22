//
//  BGButton.m
//  UserCar
//
//  Created by 舒联勇 on 14-6-1.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "BGButton.h"

@implementation BGButton

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
    }
    return self;
}

- (void)setBackgroundImage:(UIImage *)image forState:(UIControlState)state
{
    UIImage *temp = [image stretchableImageWithLeftCapWidth:15 topCapHeight:10];
    [super setBackgroundImage:temp forState:state];
}

- (void)awakeFromNib
{
    [super awakeFromNib];
    UIImage *temp = [self backgroundImageForState:UIControlStateNormal];
    if (temp) {        
        [self setBackgroundImage:temp forState:UIControlStateNormal];
    }
    
    UIImage *hTemp = [self backgroundImageForState:UIControlStateHighlighted];
    if (hTemp) {
        [self setBackgroundImage:hTemp forState:UIControlStateHighlighted];
    }
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

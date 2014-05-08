//
//  PayButton.m
//  UserCar
//
//  Created by 舒联勇 on 14-5-5.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "PayButton.h"

@implementation PayButton

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
    }
    return self;
}

- (void)awakeFromNib
{
    [super awakeFromNib];
    self.layer.cornerRadius = 4;
    self.layer.borderWidth = 1;
    self.layer.borderColor = RGBColor(228, 132, 80).CGColor;
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

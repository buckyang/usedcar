//
//  CornerRadiusButton.m
//  UserCar
//
//  Created by 舒联勇 on 14/6/21.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "CornerRadiusButton.h"
#import <CoreText/CoreText.h>

@implementation CornerRadiusButton

- (void)awakeFromNib
{
    [super awakeFromNib];
    
    self.userInteractionEnabled = YES;
    self.layer.masksToBounds = YES;
    self.layer.cornerRadius = self.bounds.size.height/2;    
}

@end

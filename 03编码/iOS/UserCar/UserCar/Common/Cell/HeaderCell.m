//
//  HeaderCell.m
//  UserCar
//
//  Created by 舒联勇 on 14/6/21.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "HeaderCell.h"

@implementation HeaderCell

- (void)layoutSubviews
{
    [super layoutSubviews];
    
    //图片位置
    CGFloat Margin = 5;
    CGFloat imgHeight = self.frame.size.height - 2*Margin;
    CGFloat imgWith = (100.f/60.f)*imgHeight;
    
    self.imageView.frame = CGRectMake(Margin,Margin,imgWith, imgHeight);
    self.imageView.contentMode = UIViewContentModeScaleToFill;
}
@end

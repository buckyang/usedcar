//
//  LeftRightCell.m
//  UserCar
//
//  Created by 舒联勇 on 14/6/21.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "LeftRightCell.h"

@implementation LeftRightCell

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        // Initialization code
    }
    return self;
}

- (void)awakeFromNib
{
    // Initialization code
}

- (void)layoutSubviews
{
    [super layoutSubviews];
    
    CGRect textLabelFrame = self.textLabel.frame;
    CGRect detailTextFrame = self.detailTextLabel.frame;
    
    int space = 10;
    
    detailTextFrame.origin.x = textLabelFrame.origin.x+textLabelFrame.size.width + space;
    self.detailTextLabel.frame = detailTextFrame;
}
@end

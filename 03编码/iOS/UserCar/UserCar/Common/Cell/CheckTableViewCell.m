//
//  CheckTableViewCell.m
//  UserCar
//
//  Created by 舒联勇 on 14-4-22.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "CheckTableViewCell.h"

@implementation CheckTableViewCell

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
    [super awakeFromNib];
    self.checked = NO;
}

- (void)setChecked:(BOOL)checked
{
    _checked = checked;
    
    UIImageView *imgCheck = self.accessoryView==nil?[[UIImageView alloc] initWithFrame:CGRectMake(0, 0, 23, 23)]:(UIImageView*)self.accessoryView;
    UIImage *temp = [UIImage imageNamed:checked?@"imgCheck.png":@"imgUncheck.png"];
    imgCheck.image = temp;
    self.accessoryView = imgCheck;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];
    if (selected) {
        self.checked = !self.checked;
    }
    [super setSelected:NO animated:YES];

    // Configure the view for the selected state
}

@end

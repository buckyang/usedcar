//
//  ToolTableViewCell.m
//  UserCar
//
//  Created by 舒联勇 on 14-5-4.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "ToolTableViewCell.h"

@implementation ToolTableViewCell

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
    UIImageView *imgBackground = [[UIImageView alloc] initWithFrame:self.bounds];
    imgBackground.image = [UIImage imageNamed:@"imgToolCell.png"];
    imgBackground.contentMode = UIViewContentModeScaleToFill;
    
    self.backgroundView = imgBackground;
    self.backgroundColor = [UIColor clearColor];
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];
    if (selected) {
        [self setSelected:NO animated:YES];
    }

    // Configure the view for the selected state
}

@end

//
//  LeftBGTextField.m
//  UserCar
//
//  Created by 舒联勇 on 14-6-1.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "LeftBGTextField.h"

@implementation LeftBGTextField

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
    }
    return self;
}

- (UIImage*)LeftImage
{
    NSAssert(NO, @"请重写该方法");
    return nil;
}

- (CGSize)sizeOfImage:(UIImage*)aImage
{
    CGSize imgSize = CGSizeZero;
    CGFloat scale = [UIScreen mainScreen].scale;
    
    imgSize.width = CGImageGetWidth(aImage.CGImage)/scale;
    imgSize.height = CGImageGetHeight(aImage.CGImage)/scale;
    return imgSize;
}

- (void)configStyle
{
    UIImage *imgLeftUser = [self LeftImage];
    CGRect userFrame = CGRectZero;
    userFrame.size = [self sizeOfImage:imgLeftUser];
    UIImageView *uimgLeftUser = [[UIImageView alloc] initWithFrame:userFrame];
    uimgLeftUser.image = imgLeftUser;
    
    self.leftViewMode = UITextFieldViewModeAlways;
    self.leftView = uimgLeftUser;
}

- (void)setText:(NSString *)text
{
    [super setText:text];
    [self configStyle];
}

- (void)awakeFromNib
{
    [super awakeFromNib];
    [self configStyle];
    [self addTarget:self action:@selector(textChangeWithSender:) forControlEvents:UIControlEventEditingChanged];
    
}

- (void)textChangeWithSender:(UITextField*)textField
{
    [self configStyle];
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

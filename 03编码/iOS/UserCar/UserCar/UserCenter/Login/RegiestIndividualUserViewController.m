//
//  RegiestIndividualUserViewController.m
//  UserCar
//
//  Created by 舒联勇 on 14-3-21.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "RegiestIndividualUserViewController.h"

@interface RegiestIndividualUserViewController ()

@property (strong, nonatomic) IBOutlet UITextField *txtPhoneNumber;
@property (weak, nonatomic) IBOutlet UIButton *btnRegist;

@end

@implementation RegiestIndividualUserViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.btnRegist.userInteractionEnabled = YES;
    self.btnRegist.layer.cornerRadius = 8;
    self.btnRegist.layer.borderWidth=1;
    self.btnRegist.layer.borderColor=[UIColor redColor].CGColor;
    
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end

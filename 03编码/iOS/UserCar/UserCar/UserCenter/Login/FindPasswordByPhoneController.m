//
//  FindPasswordByPhoneController.m
//  UserCar
//
//  Created by 舒联勇 on 14-5-7.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "FindPasswordByPhoneController.h"

@interface FindPasswordByPhoneController ()

@end

@implementation FindPasswordByPhoneController

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
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (UIView*)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section
{
    UIView *header = nil;
    if (section==1) {
        header = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 320, 20)];
        header.backgroundColor = [UIColor whiteColor];
    }
    return header;
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

//
//  RegiestUserViewController.m
//  UserCar
//
//  Created by 舒联勇 on 14-3-21.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "RegiestUserViewController.h"

@interface RegiestUserViewController ()

@property (weak, nonatomic) IBOutlet UIView *mainView;
@property (nonatomic,strong) UIViewController *individualUserViewController;
@property (nonatomic,strong) UIViewController *busynessUserviewController;

@end

@implementation RegiestUserViewController

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
    self.individualUserViewController=[self.childViewControllers objectAtIndex:0];
    
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
    self.busynessUserviewController = [storyboard instantiateViewControllerWithIdentifier:@"BusynessUserviewController"];
    [self addChildViewController:self.busynessUserviewController];
    
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
- (IBAction)switchType:(UISegmentedControl*)sender
{
    if (sender.selectedSegmentIndex==0) {
        [self transitionFromViewController:self.busynessUserviewController toViewController:self.individualUserViewController duration:0 options:UIViewAnimationOptionTransitionNone animations:^{
            
        } completion:^(BOOL finished) {
            
        }];
    }
    else
    {[self transitionFromViewController:self.individualUserViewController toViewController:self.busynessUserviewController duration:0 options:UIViewAnimationOptionTransitionNone animations:^{
        
    } completion:^(BOOL finished) {
        
    }];
    }
}

@end

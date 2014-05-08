//
//  FindPasswordController.m
//  UserCar
//
//  Created by 舒联勇 on 14-5-7.
//  Copyright (c) 2014年 shulianyong. All rights reserved.
//

#import "FindPasswordController.h"

@interface FindPasswordController ()

@property (strong, nonatomic) UIViewController *phoneViewController;
@property (strong, nonatomic) UIViewController *mailViewController;

@end

@implementation FindPasswordController

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
    self.phoneViewController=[self.childViewControllers objectAtIndex:0];
    self.mailViewController = [self.storyboard instantiateViewControllerWithIdentifier:@"EmailFindPassword"];
    [self addChildViewController:self.mailViewController];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


- (IBAction)switch_FindType:(UISegmentedControl*)sender
{
    UIViewController *fromViewController = nil;
    UIViewController *toViewController = nil;
    NSInteger XOffset = self.view.frame.size.width;
    if (sender.selectedSegmentIndex==0) {
        fromViewController = self.mailViewController;
        toViewController = self.phoneViewController;
        XOffset *= -1;
    }
    else
    {
        fromViewController = self.phoneViewController;
        toViewController = self.mailViewController;
    }
    
    CGRect oldFrame = toViewController.view.frame;
    CGRect newFrame = oldFrame;
    newFrame.origin.x+=XOffset;
    toViewController.view.frame = newFrame;
    [sender setEnabled:NO forSegmentAtIndex:(sender.selectedSegmentIndex==0)?1:0];
    [self transitionFromViewController:fromViewController
                      toViewController:toViewController
                              duration:0.5f
                               options:UIViewAnimationOptionTransitionNone
                            animations:^{
        toViewController.view.frame = oldFrame;
    }
                            completion:^(BOOL finished) {
        [sender setEnabled:YES forSegmentAtIndex:(sender.selectedSegmentIndex==0)?1:0];
    }];
    
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([segue.identifier isEqualToString:@"embedContainer"]) {
    self.findPasswordContainer = segue.destinationViewController;
        self.findPasswordContainer.SegueIdentifierFirst = @"EmbedPhone";
        self.findPasswordContainer.SegueIdentifierSecond = @"EmbedEmail";
}
}
 */

@end

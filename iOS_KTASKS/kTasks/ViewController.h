//
//  ViewController.h
//  kTasks
//
//  Created by Kenny Lo on 11/11/12.
//  Copyright (c) 2012 Kenny Lo. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController{
    NSString *responseData;
}
@property (weak, nonatomic) IBOutlet UITextField *usernameField;
@property (weak, nonatomic) IBOutlet UITextField *passwordField;
- (IBAction)loginAction:(id)sender;
@property (weak, nonatomic) IBOutlet UIButton *loginButton;
- (IBAction)bgClick:(id)sender;
@property (weak, nonatomic) IBOutlet UILabel *label;
- (IBAction)newUserAction:(id)sender;



@end

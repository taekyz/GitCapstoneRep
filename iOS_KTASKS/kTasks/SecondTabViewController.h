//
//  SecondTabViewController.h
//  kTasks
//
//  Created by Kenny Lo on 11/11/12.
//  Copyright (c) 2012 Kenny Lo. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface SecondTabViewController : UIViewController

@property (nonatomic, retain) NSMutableArray *responseData;
@property (nonatomic, retain) NSString *username;
@property (weak, nonatomic) IBOutlet UITextField *userNameField;
@property (weak, nonatomic) IBOutlet UITextField *firstNameField;
@property (weak, nonatomic) IBOutlet UITextField *lastNameField;
@property (weak, nonatomic) IBOutlet UITextField *passwordField;
@property (nonatomic, retain) NSDictionary *userData;

- (IBAction)bgClick:(id)sender;

@property (weak, nonatomic) IBOutlet UIButton *updateAction;
- (IBAction)updateAction:(id)sender;

@end

//
//  NewUserViewController.m
//  kTasks
//
//  Created by Kenny Lo on 12/11/12.
//  Copyright (c) 2012 Kenny Lo. All rights reserved.
//

#import "NewUserViewController.h"

@interface NewUserViewController ()

@end

@implementation NewUserViewController
@synthesize usernameField, firstNameField, lastNameField, passwordField;

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

- (void)viewDidUnload {
    [self setUsernameField:nil];
    [self setFirstNameField:nil];
    [self setLastNameField:nil];
    [self setPasswordField:nil];
    [self setRegisterAction:nil];
    [super viewDidUnload];
}
- (IBAction)registerAction:(id)sender {
    NSString *myURL = @"http://10.1.1.9:8080/KTASKS_Servlets/addUser?";
    
    NSString *ands = @"&";
    NSString *strRR = [NSString stringWithFormat:@"%@%@%@%@%@%@%@%@%@%@%@%@", myURL, @"username=", usernameField.text, ands, @"password=", passwordField.text, ands, @"firstName=", firstNameField.text, ands, @"lastName=", lastNameField.text];
    //connect
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        //Call servlet to get data
        //NSString* response = [NSString stringWithContentsOfURL:[NSURL URLWithString:strRR] encoding:NSASCIIStringEncoding error:nil];
        NSData* data = [NSData dataWithContentsOfURL:[NSURL URLWithString:strRR]];
        NSString* response = [[NSString alloc] initWithData:data encoding:NSASCIIStringEncoding];
        response = [response stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
        
        NSLog(@"%@",response);
        //disconenct
        dispatch_async(dispatch_get_main_queue(), ^{
            //load data
            if (response) {
                if ([response isEqualToString:@"OK"]){
                    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Register" message:@"Welcome you are registered" delegate:self cancelButtonTitle:@"Close" otherButtonTitles:nil, nil];
                    [alert show];
                    [self performSegueWithIdentifier:@"loginReturnSegue" sender:self];
                } else if ([response isEqualToString:@"USERNAME_EXIST"]){
                    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Register" message:@"Username already in use, Try Again!" delegate:self cancelButtonTitle:@"Close" otherButtonTitles:nil, nil];
                    [alert show];
                }
            }
        });
    });
    

}

-(IBAction)bgClick:(id)sender{
    [usernameField resignFirstResponder];
    [firstNameField resignFirstResponder];
    [lastNameField resignFirstResponder];
    [passwordField resignFirstResponder];
}
@end

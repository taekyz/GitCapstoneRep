//
//  ViewController.m
//  kTasks
//
//  Created by Kenny Lo on 11/11/12.
//  Copyright (c) 2012 Kenny Lo. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController
@synthesize usernameField, passwordField, label;

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)loginAction:(id)sender {
    
    NSString *myURL = @"http://10.1.1.9:8080/KTASKS_Servlets/login?";
    
    NSString *ands = @"&";
    NSString *strRR = [NSString stringWithFormat:@"%@%@%@%@%@%@", myURL, @"username=", usernameField.text, ands, @"password=", passwordField.text];
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
                    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
                    [defaults setObject:usernameField.text forKey:@"username"];
                    [defaults synchronize];
                    NSLog(@"Data saved");
                    [self performSegueWithIdentifier:@"LoginSegue" sender:self];
                } else if ([response isEqualToString:@"PASSWORD_FAIL"]){
                    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Login Failed" message:@"Wrong Password" delegate:self cancelButtonTitle:@"Close" otherButtonTitles:nil, nil];
                    [alert show];
                } else if ([response isEqualToString:@"NO_USER"]){
                    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Login Failed" message:@"Username Not Found" delegate:self cancelButtonTitle:@"Close" otherButtonTitles:nil, nil];
                    [alert show];
                }
            }
        });
    });
}

- (IBAction)bgClick:(id)sender {
    [usernameField resignFirstResponder];
    [passwordField resignFirstResponder];
    
}

- (IBAction)newUserAction:(id)sender {
    [self performSegueWithIdentifier:@"newUserSegue" sender:self];
}
@end

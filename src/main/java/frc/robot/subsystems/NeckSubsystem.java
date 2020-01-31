/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class NeckSubsystem extends SubsystemBase {

  Compressor Airow = new Compressor(21);

  Solenoid NeckPusher = new Solenoid(21 , 0);

  boolean neckOut = false;

  public NeckSubsystem() {

  }

  public void out() {
   NeckPusher.set(true);
   neckOut = true;
  }

  public void in() {
   NeckPusher.set(false);
   neckOut = false;
  }
  
  public void switchState() {
   if (neckOut = false) {
    this.in();
   }
   else {
    this.out();
   }

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

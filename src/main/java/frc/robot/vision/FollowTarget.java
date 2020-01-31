/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.vision;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveSubsystem;


public class FollowTarget extends CommandBase {
  private Limelight limelight = new Limelight();
  private DriveSubsystem drive = new DriveSubsystem();

  
  private PIDController distanceCorrector 
    = new PIDController(0.1, 0, 0);

  private PIDController angleCorrector 
    = new PIDController(0.1, 0, 0);
  
  /**
   * Creates a new FollowTarget.
   */
  public FollowTarget() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(limelight, drive);

    distanceCorrector.setSetpoint(5.0);
    angleCorrector.setSetpoint(0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.getDifferentialDrive().arcadeDrive(
    distanceCorrector.calculate(limelight.getTargetDistanceMeasured(0, 0)), 
    angleCorrector.calculate(limelight.getXError()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

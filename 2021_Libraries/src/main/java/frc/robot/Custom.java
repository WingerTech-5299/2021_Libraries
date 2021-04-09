package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.TimedRobot;

public final class Custom extends TimedRobot{

    public Double getTargetDistance (Double Hight_of_Target, Double Hight_of_camera, double ty){
        Double absTargetHight = Hight_of_Target - Hight_of_camera;

        Double targetDistance = absTargetHight / Math.abs(ty);

        return targetDistance;
    }


    public Double driftFixTalonSRX(TalonSRX leftMotorController, TalonSRX rightMotorController){

         Double driftFix;

         Double skewAmount = Math.abs(leftMotorController.getSelectedSensorPosition()) - Math.abs(rightMotorController.getSelectedSensorPosition());

         if (Math.abs(skewAmount) > 1000){
             if (skewAmount > 0){
                 driftFix = -0.3;
             }else{
                 driftFix = 0.3;
             }
         } else {
             driftFix = 0.0;
         }

        return driftFix;
    }
}